package com.param.lis.transaction.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.ITransactionConstants;
import com.param.lis.global.common.Response;
import com.param.lis.transaction.dao.IBioChemResultEntryDao;
import com.param.lis.transaction.dao.IBioChemistryDao;
import com.param.lis.transaction.dao.IBiochemValidationWorklistDao;
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
public class BiochemValidationWorklistServiceImpl
		implements IBiochemValidationWorklistService, ICommonConstants, ITransactionConstants, IError {

	@Autowired
	IBiochemValidationWorklistDao iBiochemValidationWorklistDao;

	@Autowired
	IBioChemResultEntryDao iBioChemResultEntryDao;

	@Autowired
	IParameterMasterDao iParameterMasterDao;
	
	@Autowired
    private IPanelTestService iPanelTestService;
	
	@Autowired
	IBioChemistryDao iBioChemistryDao;
	    
    @Autowired
    private IFormulaMasterService iFormulaMasterService;	

	final static Logger logger = Logger.getLogger(IBiochemValidationWorklistService.class);

	@Override
	@Transactional
	public Response getValidationWorkList(BioChemParamDto bioChemParamDto) throws ApplicationException {
		try {
			return iBiochemValidationWorklistDao.getValidationWorkList(bioChemParamDto);
		} catch (Exception e) {
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response getValidationWorkListCount(BioChemParamDto bioChemParamDto) throws ApplicationException {
		try {
			return iBiochemValidationWorklistDao.getValidationWorkListCount(bioChemParamDto);
		} catch (Exception e) {
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response getValidationWorkListDetails(List<BioChemParamDto> bioChemParamDtoList) throws ApplicationException {
		try {
		  
		    List<SingParamTestResDto> testMasterDtoList = new ArrayList<>();
		for (Iterator iterator = bioChemParamDtoList.iterator(); iterator.hasNext();) {
          BioChemParamDto bioChemParamDto = (BioChemParamDto) iterator.next();
          Response<SingParamTestResDto, Integer> resultEnterDateRes =  iBioChemistryDao.getResultEnteryByDateTime(bioChemParamDto);
          Response<TestMasterDto, Integer> response = null;
          if (bioChemParamDto.getTestType().equals(SINGLE_PARAM_TEST)) {
            
            Integer panelId = bioChemParamDto.getPanelId()==null?0:bioChemParamDto.getPanelId();
            Response<TPanelMasterDto, Integer> panelMstRes =  iPanelTestService.getPanelDescByPanelId(panelId, bioChemParamDto.getOrgId(), bioChemParamDto.getOrgUnitId());  
             
            
              SingParamTestResDto singParamTestResDto = new SingParamTestResDto();
              response = iBioChemResultEntryDao.getSingleParamTestResult(bioChemParamDto);
              TestMasterDto testMasterDto = response.getObject();
              singParamTestResDto.setTestId(testMasterDto.getTestId());
              singParamTestResDto.setTestDesc(testMasterDto.getTestDesc());
              singParamTestResDto.setTestCode(testMasterDto.getTestCode());
              singParamTestResDto.setTestType(testMasterDto.getTestType());
              singParamTestResDto.setTechniqueName(testMasterDto.getTechniqueName());
              singParamTestResDto.setLabSampleNo(bioChemParamDto.getLabSampleNo());
              singParamTestResDto.setPanelAlies( panelMstRes.getObject()!=null?panelMstRes.getObject().getPanelAlies():"");
              singParamTestResDto.setPanelCode(panelMstRes.getObject()!=null?panelMstRes.getObject().getPanelCode():"");
              singParamTestResDto.setResultEnterByUser(resultEnterDateRes.getObject().getResultEnterByUser());
              singParamTestResDto.setResultEnterDatetime(resultEnterDateRes.getObject().getResultEnterDatetime());

              Response<LabResultDetailsViewDto, Integer> labResDetails = iBiochemValidationWorklistDao
                      .getValidationWorkListDetails(bioChemParamDto);
              
      
              if (labResDetails.getListObject() != null) {
                  for (Iterator iterator1 = labResDetails.getListObject().iterator(); iterator1.hasNext();) {
                      LabResultDetailsViewDto labResultDetailsViewDto = (LabResultDetailsViewDto) iterator1.next();
                      List<HelpValueMasterDto> listHelpValueMasterDto = (List<HelpValueMasterDto>) iParameterMasterDao
                              .getHelpValueByParameter(bioChemParamDto.getOrgId(),
                                      bioChemParamDto.getOrgUnitId(),
                                      labResultDetailsViewDto.getParameterId()).getListObject();
                      labResultDetailsViewDto.setListHelpValueMaster(listHelpValueMasterDto);
                      singParamTestResDto.setLabResultDetailsViewDto(labResultDetailsViewDto);
                  }
              }

              testMasterDtoList.add(singParamTestResDto);

          } else if (bioChemParamDto.getTestType().equals(MULTY_PARAM_TEST)) {
            
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
              singParamTestResDto.setPanelAlies( panelMstRes.getObject()!=null?panelMstRes.getObject().getPanelAlies():"");
              singParamTestResDto.setPanelCode(panelMstRes.getObject()!=null?panelMstRes.getObject().getPanelCode():"");
              singParamTestResDto.setResultEnterByUser(resultEnterDateRes.getObject().getResultEnterByUser());
              singParamTestResDto.setResultEnterDatetime(resultEnterDateRes.getObject().getResultEnterDatetime());

              // Group By HeaderId
              Map<Integer, List<TestParamMpprDto>> testParamMpprDto = testMasterDto.getListTestParamMppr().stream()
                      .collect(Collectors.groupingBy(TestParamMpprDto::getHeaderId));
              

              for (Map.Entry<Integer, List<TestParamMpprDto>> entry : testParamMpprDto.entrySet())
              {
                  MultyParamTestHeaderDto multyParamTestHeaderDto = new MultyParamTestHeaderDto();
                  for (Iterator iterator2 = entry.getValue().iterator(); iterator2.hasNext();) 
                  {
                      List<LabResultDetailsViewDto> listLabResultDetailsViewDto = new ArrayList<>();
                      TestParamMpprDto tstParamMpprDto = (TestParamMpprDto) iterator2.next();
                      multyParamTestHeaderDto.setHeaderId(tstParamMpprDto.getHeaderId());
                      multyParamTestHeaderDto.setHeaderCode(tstParamMpprDto.getHeaderDesc());
                      multyParamTestHeaderDto.setHeaderDesc(tstParamMpprDto.getHeaderDesc());

                      bioChemParamDto.setHeaderId(multyParamTestHeaderDto.getHeaderId());
                      Response<LabResultDetailsViewDto, Integer> labResDetails = iBiochemValidationWorklistDao
                              .getValidationWorkListDetails(bioChemParamDto);
                      if (labResDetails.getListObject() != null) 
                      {
                          for (Iterator iterator1 = labResDetails.getListObject().iterator(); iterator1.hasNext();)
                          {
                              LabResultDetailsViewDto labResultDetailsViewDto = (LabResultDetailsViewDto) iterator1.next();
                              
                              
                              
                              List<HelpValueMasterDto> listHelpValueMasterDto = (List<HelpValueMasterDto>) iParameterMasterDao
                                      .getHelpValueByParameter(bioChemParamDto.getOrgId(),
                                              bioChemParamDto.getOrgUnitId(),
                                              labResultDetailsViewDto.getParameterId()).getListObject();
                              labResultDetailsViewDto.setListHelpValueMaster(listHelpValueMasterDto);
                              labResultDetailsViewDto.setParamPrintOrder(tstParamMpprDto.getParaSequence());
                              
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
	public Response saveValidationWorkList(List<LabResultMasterDto> listLabResultMasterDto) throws ApplicationException {
		try {
			return iBiochemValidationWorklistDao.saveValidationWorkList(listLabResultMasterDto);
		} catch (Exception e) {
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response getPrivousResultBySample(BioChemParamDto bioChemParamDto) throws ApplicationException {
		try {
			List<SingParamTestResDto> listSingParamTestResDto = new ArrayList<>();
			Response<TestMasterDto, Integer> response = null;
			
			 Response<SingParamTestResDto, Integer> resultEnterDateRes =  iBioChemistryDao.getResultReleaseByDateTime(bioChemParamDto);
			if (bioChemParamDto.getTestType().equals(SINGLE_PARAM_TEST)) {
				SingParamTestResDto singParamTestResDto = new SingParamTestResDto();
				
				response = iBioChemResultEntryDao.getSingleParamTestResult(bioChemParamDto);
				TestMasterDto testMasterDto = response.getObject();
				singParamTestResDto.setTestId(testMasterDto.getTestId());
				singParamTestResDto.setTestDesc(testMasterDto.getTestDesc());
				singParamTestResDto.setTestCode(testMasterDto.getTestCode());
			    singParamTestResDto.setTestType(bioChemParamDto.getTestType());
				singParamTestResDto.setTechniqueName(testMasterDto.getTechniqueName());
				singParamTestResDto.setResultEnterByUser(resultEnterDateRes.getObject().getResultEnterByUser());
	            singParamTestResDto.setResultEnterDatetime(resultEnterDateRes.getObject().getResultEnterDatetime());
	            singParamTestResDto.setResultValidatedBy(resultEnterDateRes.getObject().getResultValidatedBy());
	            singParamTestResDto.setResultValidatedByUser(resultEnterDateRes.getObject().getResultValidatedByUser());
	            singParamTestResDto.setResultAuthorisedBy(resultEnterDateRes.getObject().getResultAuthorisedBy());
	            singParamTestResDto.setResultAuthorisedDatetime(resultEnterDateRes.getObject().getResultAuthorisedDatetime());

				Response<LabResultDetailsViewDto, Integer> labResDetails = iBiochemValidationWorklistDao
						.getPrivousResultBySample(bioChemParamDto);
				
		
				if (labResDetails.getListObject() != null) {
					for (Iterator iterator = labResDetails.getListObject().iterator(); iterator.hasNext();) {
						LabResultDetailsViewDto labResultDetailsViewDto = (LabResultDetailsViewDto) iterator.next();
						List<HelpValueMasterDto> listHelpValueMasterDto = (List<HelpValueMasterDto>) iParameterMasterDao
								.getHelpValueByParameter(bioChemParamDto.getOrgId(),
										bioChemParamDto.getOrgUnitId(),
										labResultDetailsViewDto.getParameterId()).getListObject();
						labResultDetailsViewDto.setListHelpValueMaster(listHelpValueMasterDto);
						singParamTestResDto.setLabResultDetailsViewDto(labResultDetailsViewDto);
					}
				}
				listSingParamTestResDto.add(singParamTestResDto);
				return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, listSingParamTestResDto, null);

			} else if (bioChemParamDto.getTestType().equals(MULTY_PARAM_TEST)) {
				response = iBioChemResultEntryDao.getMultyParamTestResult(bioChemParamDto);
				TestMasterDto testMasterDto = response.getObject();
				SingParamTestResDto singParamTestResDto = new SingParamTestResDto();
				List<MultyParamTestHeaderDto> listMultyParamTestHeaderDto = new ArrayList<>();

				singParamTestResDto.setTestId(testMasterDto.getTestId());
				singParamTestResDto.setTestDesc(testMasterDto.getTestDesc());
				singParamTestResDto.setTestCode(testMasterDto.getTestCode());
				singParamTestResDto.setTechniqueName(testMasterDto.getTechniqueName());
				singParamTestResDto.setTestType(bioChemParamDto.getTestType());
				singParamTestResDto.setResultEnterByUser(resultEnterDateRes.getObject().getResultEnterByUser());
	            singParamTestResDto.setResultEnterDatetime(resultEnterDateRes.getObject().getResultEnterDatetime());
	            singParamTestResDto.setResultValidatedBy(resultEnterDateRes.getObject().getResultValidatedBy());
                singParamTestResDto.setResultValidatedByUser(resultEnterDateRes.getObject().getResultValidatedByUser());
                singParamTestResDto.setResultAuthorisedBy(resultEnterDateRes.getObject().getResultAuthorisedBy());
                singParamTestResDto.setResultAuthorisedDatetime(resultEnterDateRes.getObject().getResultAuthorisedDatetime());

				// Group By HeaderId
				Map<Integer, List<TestParamMpprDto>> testParamMpprDto = testMasterDto.getListTestParamMppr().stream()
						.collect(Collectors.groupingBy(TestParamMpprDto::getHeaderId));
				

				for (Map.Entry<Integer, List<TestParamMpprDto>> entry : testParamMpprDto.entrySet())
				{
					MultyParamTestHeaderDto multyParamTestHeaderDto = new MultyParamTestHeaderDto();
					for (Iterator iterator = entry.getValue().iterator(); iterator.hasNext();) 
					{
						List<LabResultDetailsViewDto> listLabResultDetailsViewDto = new ArrayList<>();
						TestParamMpprDto tstParamMpprDto = (TestParamMpprDto) iterator.next();
						multyParamTestHeaderDto.setHeaderId(tstParamMpprDto.getHeaderId());
						multyParamTestHeaderDto.setHeaderCode(tstParamMpprDto.getHeaderDesc());
						multyParamTestHeaderDto.setHeaderDesc(tstParamMpprDto.getHeaderDesc());

						bioChemParamDto.setHeaderId(multyParamTestHeaderDto.getHeaderId());
						Response<LabResultDetailsViewDto, Integer> labResDetails = iBiochemValidationWorklistDao
								.getPrivousResultBySample(bioChemParamDto);
						
						
						if (labResDetails.getListObject() != null) 
						{
							for (Iterator iterator1 = labResDetails.getListObject().iterator(); iterator1.hasNext();)
							{
								LabResultDetailsViewDto labResultDetailsViewDto = (LabResultDetailsViewDto) iterator1.next();
								List<HelpValueMasterDto> listHelpValueMasterDto = (List<HelpValueMasterDto>) iParameterMasterDao
										.getHelpValueByParameter(bioChemParamDto.getOrgId(),
												bioChemParamDto.getOrgUnitId(),
												labResultDetailsViewDto.getParameterId()).getListObject();
								labResultDetailsViewDto.setListHelpValueMaster(listHelpValueMasterDto);
								labResultDetailsViewDto.setParamPrintOrder(tstParamMpprDto.getParaSequence());
								listLabResultDetailsViewDto.add(labResultDetailsViewDto);
							}
						}
						multyParamTestHeaderDto.setListLabResultDetailsViewDto(listLabResultDetailsViewDto);
					}
					listMultyParamTestHeaderDto.add(multyParamTestHeaderDto);
				}
				singParamTestResDto.setListMultyParamTestHeaderDto(listMultyParamTestHeaderDto);
				listSingParamTestResDto.add(singParamTestResDto);
				return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, listSingParamTestResDto, null);

			}
		} catch (Exception e) {
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	}

}
