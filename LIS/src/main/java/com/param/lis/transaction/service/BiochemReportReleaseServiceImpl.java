package com.param.lis.transaction.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.param.entity.lis.transaction.LabResultMaster;
import com.param.entity.lis.transaction.LabSampleDetailsMaster;
import com.param.lis.global.common.CheckReportType;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.ITransactionConstants;
import com.param.lis.global.common.Response;
import com.param.lis.transaction.dao.IBioChemResultEntryDao;
import com.param.lis.transaction.dao.IBioChemistryDao;
import com.param.lis.transaction.dao.IBiochemReportReleaseDao;
import com.param.lis.transaction.dto.BioChemParamDto;
import com.param.lis.transaction.dto.LabResultDetailsViewDto;
import com.param.lis.transaction.dto.LabResultMasterDto;
import com.param.lis.transaction.dto.MultyParamTestHeaderDto;
import com.param.lis.transaction.dto.SingParamTestResDto;
import com.param.lis.unit.dao.IParameterMasterDao;
import com.param.lis.unit.dto.FormulaDetailsDto;
import com.param.lis.unit.dto.HelpValueMasterDto;
import com.param.lis.unit.dto.TPanelMasterDto;
import com.param.lis.unit.dto.TestMasterDto;
import com.param.lis.unit.dto.TestParamMpprDto;
import com.param.lis.unit.service.IFormulaMasterService;
import com.param.lis.unit.service.IPanelTestService;
import in.param.exception.ApplicationException;

@Service
@SuppressWarnings({ "rawtypes", "unchecked" })
public class BiochemReportReleaseServiceImpl
		implements IBiochemReportReleaseService, ICommonConstants, ITransactionConstants, IError {

	@Autowired
	IBiochemReportReleaseDao iBiochemReportReleaseDao;

	@Autowired
	IBioChemResultEntryDao iBioChemResultEntryDao;

	@Autowired
	IParameterMasterDao iParameterMasterDao;
	
	@Autowired
    IBioChemistryDao iBioChemistryDao;
	
	@Autowired
	SessionFactory sessionFactory;
	
	   @Autowired
	    private IFormulaMasterService iFormulaMasterService;    
	
	 @Autowired
     private IPanelTestService iPanelTestService;

	final static Logger logger = Logger.getLogger(BiochemReportReleaseServiceImpl.class);

	@Override
	@Transactional
	public Response getReportReleaseWorkList(BioChemParamDto bioChemParamDto) throws ApplicationException {
		try {
			return iBiochemReportReleaseDao.getReportReleaseWorkList(bioChemParamDto);
		} catch (Exception e) {
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response getReportReleaseWorkListCount(BioChemParamDto bioChemParamDto) throws ApplicationException {
		try {
			return iBiochemReportReleaseDao.getReportReleaseWorkListCount(bioChemParamDto);
		} catch (Exception e) {
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response getReportReleaseWorkListDetails(List<BioChemParamDto> bioChemParamDtoList) throws ApplicationException {
		try {
		  List<SingParamTestResDto> testMasterDtoList = new ArrayList<>();
          for (Iterator iterator = bioChemParamDtoList.iterator(); iterator.hasNext();) 
          {
            BioChemParamDto bioChemParamDto = (BioChemParamDto) iterator.next();
            Response<SingParamTestResDto, Integer> resultEnterDateRes =  iBioChemistryDao.getResultValidatedByDateTime(bioChemParamDto);
            
			Response<TestMasterDto, Integer> response = null;
			if (bioChemParamDto.getTestType().equals(SINGLE_PARAM_TEST)) {
				SingParamTestResDto singParamTestResDto = new SingParamTestResDto();
				

                Integer panelId = bioChemParamDto.getPanelId()==null?0:bioChemParamDto.getPanelId();
                Response<TPanelMasterDto, Integer> panelMstRes =  iPanelTestService.getPanelDescByPanelId(panelId, bioChemParamDto.getOrgId(), bioChemParamDto.getOrgUnitId());

				response = iBioChemResultEntryDao.getSingleParamTestResult(bioChemParamDto);
				TestMasterDto testMasterDto = response.getObject();
				singParamTestResDto.setTestId(testMasterDto.getTestId());
				singParamTestResDto.setTestType(testMasterDto.getTestType());
				singParamTestResDto.setTestDesc(testMasterDto.getTestDesc());
				singParamTestResDto.setTestCode(testMasterDto.getTestCode());
				singParamTestResDto.setTechniqueName(testMasterDto.getTechniqueName());
				singParamTestResDto.setLabSampleNo(bioChemParamDto.getLabSampleNo());
                singParamTestResDto.setPanelAlies( panelMstRes.getObject()!=null?panelMstRes.getObject().getPanelAlies():"");
                singParamTestResDto.setPanelCode(panelMstRes.getObject()!=null?panelMstRes.getObject().getPanelCode():"");
                singParamTestResDto.setResultEnterByUser(resultEnterDateRes.getObject().getResultEnterByUser());
                singParamTestResDto.setResultEnterDatetime(resultEnterDateRes.getObject().getResultEnterDatetime());
                singParamTestResDto.setResultValidatedByUser(resultEnterDateRes.getObject().getResultValidatedByUser());
                singParamTestResDto.setResultValidatedDatetime(resultEnterDateRes.getObject().getResultValidatedDatetime());

				Response<LabResultDetailsViewDto, Integer> labResDetails = iBiochemReportReleaseDao
						.getReportReleaseWorkListDetails(bioChemParamDto);
				
				LabResultMaster labResultMaste=	(LabResultMaster) sessionFactory.getCurrentSession().get(LabResultMaster.class, bioChemParamDto.getLabResultId());
				if(labResultMaste.getResultEnterDatetime()!=null)
				singParamTestResDto.setSampleTestTime(new Date(labResultMaste.getResultEnterDatetime()));
				
				if(labResultMaste.getResultAuthorisedDatetime()!=null)
				singParamTestResDto.setTestReleaseTime(new Date(labResultMaste.getResultAuthorisedDatetime()));
				
				
				LabSampleDetailsMaster labSampleDetailsMaster=	(LabSampleDetailsMaster) sessionFactory.getCurrentSession().get(LabSampleDetailsMaster.class, labResultMaste.getLabSampleDtlsId());
				if(labSampleDetailsMaster.getSampleCollectionDatetime()!=null)
				singParamTestResDto.setSampleCollectionTime(new Date(labSampleDetailsMaster.getSampleCollectionDatetime()));
				
				
				if (labResDetails.getListObject() != null) 
				{
					for (Iterator iterator1 = labResDetails.getListObject().iterator(); iterator1.hasNext();) 
					{
						LabResultDetailsViewDto labResultDetailsViewDto = (LabResultDetailsViewDto) iterator1.next();
						List<HelpValueMasterDto> listHelpValueMasterDto = (List<HelpValueMasterDto>) iParameterMasterDao
								.getHelpValueByParameter(labResultDetailsViewDto.getOrgId(),
										labResultDetailsViewDto.getOrgUnitId(),
										labResultDetailsViewDto.getParameterId()).getListObject();
						
						  if(labResultDetailsViewDto.getIsFormula()=='Y')
                          {
                               Response<FormulaDetailsDto, Integer> formulaResponse =   iFormulaMasterService.getFormulaByParameter(labResultDetailsViewDto.getParameterId(), bioChemParamDto.getOrgId(), bioChemParamDto.getOrgUnitId());
                               if(formulaResponse.getCode().equals(SUCCESS_200)) {
                                 labResultDetailsViewDto.setParameterFormula(formulaResponse.getListObject().get(0).getFormula());
                                 labResultDetailsViewDto.setListFormulaDetails(formulaResponse.getListObject());
                               }
                               else {
                                 return new Response(ERROR, ERR_500, "Formula Not Found..", null, null);
                               }
                          }
						
						labResultDetailsViewDto.setListHelpValueMaster(listHelpValueMasterDto);
						singParamTestResDto.setLabResultDetailsViewDto(labResultDetailsViewDto);
					}
				}

                testMasterDtoList.add(singParamTestResDto);

			} else if (bioChemParamDto.getTestType().equals(MULTY_PARAM_TEST)) 
			{
			    Integer panelId = bioChemParamDto.getPanelId()==null?0:bioChemParamDto.getPanelId();
	            Response<TPanelMasterDto, Integer> panelMstRes =  iPanelTestService.getPanelDescByPanelId(panelId, bioChemParamDto.getOrgId(), bioChemParamDto.getOrgUnitId());
			  
			  response = iBioChemResultEntryDao.getMultyParamTestResult(bioChemParamDto);
              TestMasterDto testMasterDto = response.getObject();
              SingParamTestResDto singParamTestResDto = new SingParamTestResDto();
              List<MultyParamTestHeaderDto> listMultyParamTestHeaderDto = new ArrayList<>();

              singParamTestResDto.setTestId(testMasterDto.getTestId());
              singParamTestResDto.setTestType(testMasterDto.getTestType());
              singParamTestResDto.setTestDesc(testMasterDto.getTestDesc());
              singParamTestResDto.setTestCode(testMasterDto.getTestCode());
              singParamTestResDto.setTechniqueName(testMasterDto.getTechniqueName());
              singParamTestResDto.setLabSampleNo(bioChemParamDto.getLabSampleNo());
              singParamTestResDto.setPanelAlies(panelMstRes.getObject()!=null?panelMstRes.getObject().getPanelAlies():"");
              singParamTestResDto.setPanelCode(panelMstRes.getObject()!=null?panelMstRes.getObject().getPanelCode():"");
              singParamTestResDto.setResultEnterByUser(resultEnterDateRes.getObject().getResultEnterByUser());
              singParamTestResDto.setResultEnterDatetime(resultEnterDateRes.getObject().getResultEnterDatetime());
              singParamTestResDto.setResultValidatedByUser(resultEnterDateRes.getObject().getResultValidatedByUser());
              singParamTestResDto.setResultValidatedDatetime(resultEnterDateRes.getObject().getResultValidatedDatetime());


				// Group By HeaderId
				Map<Integer, List<TestParamMpprDto>> testParamMpprDto = testMasterDto.getListTestParamMppr().stream()
						.collect(Collectors.groupingBy(TestParamMpprDto::getHeaderId));

				for (Map.Entry<Integer, List<TestParamMpprDto>> entry : testParamMpprDto.entrySet()) {
					MultyParamTestHeaderDto multyParamTestHeaderDto = new MultyParamTestHeaderDto();
					for (Iterator iterator2 = entry.getValue().iterator(); iterator2.hasNext();) {
						List<LabResultDetailsViewDto> listLabResultDetailsViewDto = new ArrayList<>();
						TestParamMpprDto tstParamMpprDto = (TestParamMpprDto) iterator2.next();
						multyParamTestHeaderDto.setHeaderId(tstParamMpprDto.getHeaderId());
						multyParamTestHeaderDto.setHeaderCode(tstParamMpprDto.getHeaderDesc());
						multyParamTestHeaderDto.setHeaderDesc(tstParamMpprDto.getHeaderDesc());

						bioChemParamDto.setHeaderId(multyParamTestHeaderDto.getHeaderId());
						Response<LabResultDetailsViewDto, Integer> labResDetails = iBiochemReportReleaseDao
								.getReportReleaseWorkListDetails(bioChemParamDto);
						
						LabResultMaster labResultMaste=	(LabResultMaster) sessionFactory.getCurrentSession().get(LabResultMaster.class, bioChemParamDto.getLabResultId());
						if(labResultMaste.getResultEnterDatetime()!=null)
						  singParamTestResDto.setSampleTestTime(new Date(labResultMaste.getResultEnterDatetime()));
						if(labResultMaste.getResultAuthorisedDatetime()!=null)
						  singParamTestResDto.setTestReleaseTime(new Date(labResultMaste.getResultAuthorisedDatetime()));
						
						
						LabSampleDetailsMaster labSampleDetailsMaster=	(LabSampleDetailsMaster) sessionFactory.getCurrentSession().get(LabSampleDetailsMaster.class, labResultMaste.getLabSampleDtlsId());
						if(labSampleDetailsMaster.getSampleCollectionDatetime()!=null)
						  singParamTestResDto.setSampleCollectionTime(new Date(labSampleDetailsMaster.getSampleCollectionDatetime()));

						if (labResDetails.getListObject() != null) {
							for (Iterator iterator1 = labResDetails.getListObject().iterator(); iterator1.hasNext();) {
								LabResultDetailsViewDto labResultDetailsViewDto = (LabResultDetailsViewDto) iterator1
										.next();
								List<HelpValueMasterDto> listHelpValueMasterDto = (List<HelpValueMasterDto>) iParameterMasterDao
										.getHelpValueByParameter(labResultDetailsViewDto.getOrgId(),
												labResultDetailsViewDto.getOrgUnitId(),
												labResultDetailsViewDto.getParameterId())
										        .getListObject();
								labResultDetailsViewDto.setListHelpValueMaster(listHelpValueMasterDto);
								listLabResultDetailsViewDto.add(labResultDetailsViewDto);
							}

						}
						multyParamTestHeaderDto.setListLabResultDetailsViewDto(listLabResultDetailsViewDto);
					}
				      listMultyParamTestHeaderDto.add(multyParamTestHeaderDto);
				}
				  singParamTestResDto.setListMultyParamTestHeaderDto(listMultyParamTestHeaderDto);
	              testMasterDtoList.add(singParamTestResDto);
			}
          }
			return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, testMasterDtoList, null);
		} catch (Exception e) {
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response saveReportReleaseWorkList(List<LabResultMasterDto> listLabResultMasterDto) throws ApplicationException {
		try {
			
		  for (Iterator iterator = listLabResultMasterDto.iterator(); iterator.hasNext();) {
            LabResultMasterDto labResultMasterDto = (LabResultMasterDto) iterator.next();
            Set<Integer> reportType = new HashSet<>();
            if (labResultMasterDto == null) {
                logger.info("INFO : labResultMasterDto is NULL");
                return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
            } else {
                
                labResultMasterDto.setUpdatedDate(new Date().getTime());
                labResultMasterDto.setUpdatedBy(labResultMasterDto.getCreatedBy());
                labResultMasterDto.setResultAuthorisedBy(labResultMasterDto.getCreatedBy());
                labResultMasterDto.setResultAuthorisedDatetime(new Date().getTime());
                labResultMasterDto.setResultAuthorisedFlag('Y');
                for (Iterator iterator1 = labResultMasterDto.getListLabSampleResultDetailsMaster().iterator(); iterator1
                        .hasNext();) {
                    LabResultDetailsViewDto labResultDetailsViewDto = (LabResultDetailsViewDto) iterator1.next();
                    labResultDetailsViewDto.setUpdatedBy(labResultMasterDto.getCreatedBy());
                    labResultDetailsViewDto.setUpdatedDate(new Date().getTime());
                    CheckReportType.reportType(labResultDetailsViewDto);
                    reportType.add(labResultDetailsViewDto.getReportType());
                    
                }
                labResultMasterDto.setReportType(Collections.max(reportType));
              }
            return iBiochemReportReleaseDao.saveReportReleaseWorkList(labResultMasterDto);
			}
		  return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
			
		} catch (Exception e) {
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}



}
