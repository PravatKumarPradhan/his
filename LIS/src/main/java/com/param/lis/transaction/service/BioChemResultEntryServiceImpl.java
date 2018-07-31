package com.param.lis.transaction.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
import com.param.lis.transaction.dto.BioChemParamDto;
import com.param.lis.transaction.dto.LabResultDetailsViewDto;
import com.param.lis.transaction.dto.LabResultMasterDto;
import com.param.lis.transaction.dto.MultyParamTestHeaderDto;
import com.param.lis.transaction.dto.SingParamTestResDto;
import com.param.lis.unit.dao.IParameterMasterDao;
import com.param.lis.unit.dto.FormulaDetailsDto;
import com.param.lis.unit.dto.ParamMultiTextualRangeMasterDto;
import com.param.lis.unit.dto.ParamRefrengMasterDto;
import com.param.lis.unit.dto.ParamTextualRanageMasterDto;
import com.param.lis.unit.dto.ParameterMasterDto;
import com.param.lis.unit.dto.TPanelMasterDto;
import com.param.lis.unit.dto.TestMasterDto;
import com.param.lis.unit.dto.TestParamMpprDto;
import com.param.lis.unit.service.IFormulaMasterService;
import com.param.lis.unit.service.IPanelTestService;
import in.param.exception.ApplicationException;

@Service
@SuppressWarnings({ "rawtypes", "unchecked" })
public class BioChemResultEntryServiceImpl
		implements IBioChemResultEntryService, ICommonConstants, ITransactionConstants, IError {
	
	@Autowired
	IBioChemResultEntryDao iBioChemResultEntryDao;
	
	 @Autowired
	 private IPanelTestService iPanelTestService;

	   @Autowired
	     private IFormulaMasterService iFormulaMasterService;

	@Autowired
	IParameterMasterDao iParameterMasterDao;

	final static Logger logger = Logger.getLogger(BioChemResultEntryServiceImpl.class);

	@Override
	@Transactional
	public Response getResultEntryList(BioChemParamDto bioChemParamDto) throws ApplicationException {
		try {
			return iBioChemResultEntryDao.getResultEntryList(bioChemParamDto);
		} catch (Exception e) {
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response getResultEntryListCount(BioChemParamDto bioChemParamDto) throws ApplicationException {
		try {
			return iBioChemResultEntryDao.getResultEntryListCount(bioChemParamDto);
		} catch (Exception e) {
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response getResultEntryDetails(List<BioChemParamDto> bioChemParamDtoList) throws ApplicationException {
		try {
		  
		  List<SingParamTestResDto> testMasterDtoList = new ArrayList<>();
		  for (Iterator iterator = bioChemParamDtoList.iterator(); iterator.hasNext();) 
		  {
            BioChemParamDto bioChemParamDto = (BioChemParamDto) iterator.next();
            Response<TestMasterDto, Integer> response = null;
              if (bioChemParamDto.getTestType().equals(SINGLE_PARAM_TEST)) 
            {
                 Integer panelId = bioChemParamDto.getPanelId()==null?0:bioChemParamDto.getPanelId();
                 Response<TPanelMasterDto, Integer> panelMstRes =  iPanelTestService.getPanelDescByPanelId(panelId, bioChemParamDto.getOrgId(), bioChemParamDto.getOrgUnitId());  
                
                SingParamTestResDto singParamTestResDto = new SingParamTestResDto();
                LabResultDetailsViewDto labResultDetailsViewDto = new LabResultDetailsViewDto();
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
                
                   
                
                for (Iterator iterator1 = testMasterDto.getListParameterMasterDto().iterator(); iterator1.hasNext();) 
                {
                    ParameterMasterDto parameterMasterDto = (ParameterMasterDto) iterator1.next();
                    labResultDetailsViewDto.setCreatedBy(0);
                    labResultDetailsViewDto.setTestId(testMasterDto.getTestId());
                    labResultDetailsViewDto.setParameterId(parameterMasterDto.getParameterId());
                    labResultDetailsViewDto.setParamName(parameterMasterDto.getParameterName());
                    labResultDetailsViewDto.setListHelpValueMaster(parameterMasterDto.getListHelpValueMaster());
                   
                    if(parameterMasterDto.getRefrangeTypeId()==REFERENCE_VALUE)
                    {
                      Optional<ParamRefrengMasterDto> paramRefrengMstDto = parameterMasterDto.getListParamRefrengMaster()
                          .stream()
                          .filter(ageGrp -> (bioChemParamDto.getPatientAgeDays() >= ageGrp.getAgeFromDay()
                                  && bioChemParamDto.getPatientAgeDays() <= ageGrp.getAgeToDay()
                                  && ageGrp.getIsDeleted() == 'N'&& ageGrp.getGenderId().intValue() == bioChemParamDto.getGenderId().intValue()))
                          .findFirst();

                  ParamRefrengMasterDto paramRefrengMasterDto = paramRefrengMstDto.orElse(null);
                  if (paramRefrengMasterDto != null) {
                      labResultDetailsViewDto.setParameterId(paramRefrengMasterDto.getParameterId());
                      labResultDetailsViewDto.setIsFormula(parameterMasterDto.getIsFormula());
                      labResultDetailsViewDto.setParameterMin(paramRefrengMasterDto.getMinValue());
                      labResultDetailsViewDto.setParameterMax(paramRefrengMasterDto.getMaxValue());
                      labResultDetailsViewDto.setParamAbnrmlMax(paramRefrengMasterDto.getMoreThan());
                      labResultDetailsViewDto.setParamAbnrmlMin(paramRefrengMasterDto.getLessThan());
                      labResultDetailsViewDto.setParamName(parameterMasterDto.getParameterName());
                      labResultDetailsViewDto.setParamUnit(parameterMasterDto.getUnitName());
                      labResultDetailsViewDto.setRefrangeTypeId(parameterMasterDto.getRefrangeTypeId());
                     // labResultDetailsViewDto.setDeltaDaysId(parameterMasterDto.getDeltaDaysId());
                      labResultDetailsViewDto.setDeltaPer(parameterMasterDto.getDeltaPer());
                      labResultDetailsViewDto.setHeaderId(0);
                      labResultDetailsViewDto.setOrgId(testMasterDto.getOrgId());
                      labResultDetailsViewDto.setOrgUnitId(testMasterDto.getOrgUnitId());
                      labResultDetailsViewDto.setParamPrintOrder(0);
                  }
                    }
                    else if(parameterMasterDto.getRefrangeTypeId()==TEXTUAL_RANGE)
                    {
                      Optional<ParamTextualRanageMasterDto> ParamTextualRanageMstDto = parameterMasterDto.getListParamTextualRanageMaster()
                          .stream()
                          .filter(ageGrp -> (bioChemParamDto.getPatientAgeDays() >= ageGrp.getAgeFromDay()
                                  && bioChemParamDto.getPatientAgeDays() <= ageGrp.getAgeToDay()
                                  && ageGrp.getIsDeleted() == 'N'&& ageGrp.getGenderId().intValue() == bioChemParamDto.getGenderId().intValue()))
                          .findFirst();

                      ParamTextualRanageMasterDto paramTextualRanageMstDto = ParamTextualRanageMstDto.orElse(null);
                  if (paramTextualRanageMstDto != null) {
                      labResultDetailsViewDto.setParameterId(paramTextualRanageMstDto.getParameterId());
                      labResultDetailsViewDto.setIsFormula(parameterMasterDto.getIsFormula());
                      labResultDetailsViewDto.setTextualRangeId(paramTextualRanageMstDto.getTextualRangeId());
                      labResultDetailsViewDto.setTextualRangeName(paramTextualRanageMstDto.getTextualRangeName());
                      labResultDetailsViewDto.setRefrangeTypeId(parameterMasterDto.getRefrangeTypeId());
                      labResultDetailsViewDto.setParamUnit(parameterMasterDto.getUnitName());
                      labResultDetailsViewDto.setDeltaDaysId(parameterMasterDto.getDeltaDaysId());
                      labResultDetailsViewDto.setRefrangeTypeId(parameterMasterDto.getRefrangeTypeId());
                      
                      //labResultDetailsViewDto.setDeltaPer(parameterMasterDto.getDeltaPer());
                      labResultDetailsViewDto.setHeaderId(0);
                      labResultDetailsViewDto.setOrgId(testMasterDto.getOrgId());
                      labResultDetailsViewDto.setOrgUnitId(testMasterDto.getOrgUnitId());
                      labResultDetailsViewDto.setParamPrintOrder(0);
                      }
                    }
                    else if(parameterMasterDto.getRefrangeTypeId()==MULTIVALUED_RANGE)
                    {
                      Optional<ParamMultiTextualRangeMasterDto> paramMultiTextualRangeMstDto = parameterMasterDto.getListParamMultiTextualRangeMaster()
                          .stream()
                          .filter(ageGrp -> (bioChemParamDto.getPatientAgeDays() >= ageGrp.getAgeFromDay()
                                  && bioChemParamDto.getPatientAgeDays() <= ageGrp.getAgeToDay()
                                  && ageGrp.getIsDeleted() == 'N'&& ageGrp.getGenderId().intValue() == bioChemParamDto.getGenderId().intValue()))
                          .findFirst();

                      ParamMultiTextualRangeMasterDto paramMultiTextualRangeMasterDto = paramMultiTextualRangeMstDto.orElse(null);
                  if (paramMultiTextualRangeMasterDto != null) {
                      labResultDetailsViewDto.setParameterId(paramMultiTextualRangeMasterDto.getParameterId());
                      labResultDetailsViewDto.setIsFormula(parameterMasterDto.getIsFormula());
                      labResultDetailsViewDto.setMultitextaulRange(paramMultiTextualRangeMasterDto.getMultitextaulRange());
                      labResultDetailsViewDto.setRefrangeTypeId(parameterMasterDto.getRefrangeTypeId());
                      labResultDetailsViewDto.setParamUnit(parameterMasterDto.getUnitName());
                      labResultDetailsViewDto.setDeltaDaysId(parameterMasterDto.getDeltaDaysId());
                      labResultDetailsViewDto.setRefrangeTypeId(parameterMasterDto.getRefrangeTypeId());
                      labResultDetailsViewDto.setHeaderId(0);
                      labResultDetailsViewDto.setOrgId(testMasterDto.getOrgId());
                      labResultDetailsViewDto.setOrgUnitId(testMasterDto.getOrgUnitId());
                      labResultDetailsViewDto.setParamPrintOrder(0);
                      }
                    }
                }
                singParamTestResDto.setLabResultDetailsViewDto(labResultDetailsViewDto);
                testMasterDtoList.add(singParamTestResDto);
               // return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, testMasterDtoList, null);

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
                singParamTestResDto.setPanelAlies( panelMstRes.getObject()!=null?panelMstRes.getObject().getPanelAlies():"");
                singParamTestResDto.setPanelCode(panelMstRes.getObject()!=null?panelMstRes.getObject().getPanelCode():"");
                
                //Group By HeaderId
                Map<Integer, List<TestParamMpprDto>> testParamMpprDto =  testMasterDto
                                                                        .getListTestParamMppr()
                                                                        .stream()
                                                                        .collect(Collectors.groupingBy(TestParamMpprDto::getHeaderId));
                
                for(Map.Entry<Integer, List<TestParamMpprDto>> entry : testParamMpprDto.entrySet())
                { 
                    MultyParamTestHeaderDto multyParamTestHeaderDto = new MultyParamTestHeaderDto();
                    List<LabResultDetailsViewDto> listLabResultDetailsViewDto = new ArrayList<>();
                    
                    for (Iterator iterator2 = entry.getValue().iterator(); iterator2.hasNext();) 
                        {
                             TestParamMpprDto tstParamMpprDto = (TestParamMpprDto) iterator2.next();
                             multyParamTestHeaderDto.setHeaderId(tstParamMpprDto.getHeaderId());
                             multyParamTestHeaderDto.setHeaderCode(tstParamMpprDto.getHeaderDesc());
                             multyParamTestHeaderDto.setHeaderDesc(tstParamMpprDto.getHeaderDesc());
                            
                            Response<ParameterMasterDto, Integer> parameterRes = iParameterMasterDao
                                    .getParameterById(tstParamMpprDto.getOrgId(),tstParamMpprDto.getOrgUnitId(), tstParamMpprDto.getParameterId());
                            ParameterMasterDto parameterMasterDto = parameterRes.getObject();
                            
                            LabResultDetailsViewDto labResultDetailsViewDto = new LabResultDetailsViewDto();
                            labResultDetailsViewDto.setTestId(testMasterDto.getTestId());
                            labResultDetailsViewDto.setParameterId(parameterMasterDto.getParameterId());
                            labResultDetailsViewDto.setListHelpValueMaster(parameterMasterDto.getListHelpValueMaster());
                            labResultDetailsViewDto.setParamName(parameterMasterDto.getParameterName());
                            if(parameterMasterDto.getRefrangeTypeId()==REFERENCE_VALUE)
                            {
                              Optional<ParamRefrengMasterDto> paramRefrengMstDto = parameterMasterDto.getListParamRefrengMaster()
                                  .stream()
                                  .filter(ageGrp -> (bioChemParamDto.getPatientAgeDays() >= ageGrp.getAgeFromDay()
                                          && bioChemParamDto.getPatientAgeDays() <= ageGrp.getAgeToDay()
                                          && ageGrp.getIsDeleted() == 'N'&& ageGrp.getGenderId().intValue() == bioChemParamDto.getGenderId().intValue()))
                                  .findFirst();

                          ParamRefrengMasterDto paramRefrengMasterDto = paramRefrengMstDto.orElse(null);
                          
                          
                          if (paramRefrengMasterDto != null) 
                          {
                             if(parameterMasterDto.getIsFormula()=='Y')
                             {
                                  Response<FormulaDetailsDto, Integer> formulaResponse =   iFormulaMasterService.getFormulaByParameter(parameterMasterDto.getParameterId(), bioChemParamDto.getOrgId(), bioChemParamDto.getOrgUnitId());
                                  if(formulaResponse.getCode().equals(SUCCESS_200)) {
                                    labResultDetailsViewDto.setParameterFormula(formulaResponse.getListObject().get(0).getFormula());
                                    labResultDetailsViewDto.setListFormulaDetails(formulaResponse.getListObject());
                                  }
                                  else {
                                    return new Response(ERROR, ERR_500, "Formula Not Found..", null, null);
                                  }
                             }
                            
                              labResultDetailsViewDto.setParameterId(paramRefrengMasterDto.getParameterId());
                              labResultDetailsViewDto.setIsFormula(parameterMasterDto.getIsFormula());
                              labResultDetailsViewDto.setParameterMin(paramRefrengMasterDto.getMinValue());
                              labResultDetailsViewDto.setParameterMax(paramRefrengMasterDto.getMaxValue());
                              labResultDetailsViewDto.setParamAbnrmlMax(paramRefrengMasterDto.getMoreThan());
                              labResultDetailsViewDto.setParamAbnrmlMin(paramRefrengMasterDto.getLessThan());
                              labResultDetailsViewDto.setParamName(parameterMasterDto.getParameterName());
                              labResultDetailsViewDto.setParamUnit(parameterMasterDto.getUnitName());
                              labResultDetailsViewDto.setRefrangeTypeId(parameterMasterDto.getRefrangeTypeId());
                             // labResultDetailsViewDto.setDeltaDaysId(parameterMasterDto.getDeltaDaysId());
                              labResultDetailsViewDto.setDeltaPer(parameterMasterDto.getDeltaPer());
                              labResultDetailsViewDto.setHeaderId(tstParamMpprDto.getHeaderId());
                              labResultDetailsViewDto.setOrgId(testMasterDto.getOrgId());
                              labResultDetailsViewDto.setOrgUnitId(testMasterDto.getOrgUnitId());
                              labResultDetailsViewDto.setParamPrintOrder(0);
                              listLabResultDetailsViewDto.add(labResultDetailsViewDto);
                          }
                            
                            }
                            else if(parameterMasterDto.getRefrangeTypeId()==TEXTUAL_RANGE)
                            {
                              Optional<ParamTextualRanageMasterDto> ParamTextualRanageMstDto = parameterMasterDto.getListParamTextualRanageMaster()
                                  .stream()
                                  .filter(ageGrp -> (bioChemParamDto.getPatientAgeDays() >= ageGrp.getAgeFromDay()
                                          && bioChemParamDto.getPatientAgeDays() <= ageGrp.getAgeToDay()
                                          && ageGrp.getIsDeleted() == 'N'&& ageGrp.getGenderId().intValue() == bioChemParamDto.getGenderId().intValue()))
                                  .findFirst();

                              ParamTextualRanageMasterDto paramTextualRanageMstDto = ParamTextualRanageMstDto.orElse(null);
                          if (paramTextualRanageMstDto != null) {
                              labResultDetailsViewDto.setParameterId(paramTextualRanageMstDto.getParameterId());
                              labResultDetailsViewDto.setIsFormula(parameterMasterDto.getIsFormula());
                              labResultDetailsViewDto.setTextualRangeId(paramTextualRanageMstDto.getTextualRangeId());
                              labResultDetailsViewDto.setTextualRangeName(paramTextualRanageMstDto.getTextualRangeName());
                              labResultDetailsViewDto.setRefrangeTypeId(parameterMasterDto.getRefrangeTypeId());
                              labResultDetailsViewDto.setParamUnit(parameterMasterDto.getUnitName());
                              labResultDetailsViewDto.setDeltaDaysId(parameterMasterDto.getDeltaDaysId());
                              labResultDetailsViewDto.setRefrangeTypeId(parameterMasterDto.getRefrangeTypeId());
                              //labResultDetailsViewDto.setDeltaPer(parameterMasterDto.getDeltaPer());
                              labResultDetailsViewDto.setHeaderId(tstParamMpprDto.getHeaderId());
                              labResultDetailsViewDto.setOrgId(testMasterDto.getOrgId());
                              labResultDetailsViewDto.setOrgUnitId(testMasterDto.getOrgUnitId());
                              labResultDetailsViewDto.setParamPrintOrder(0);
                              listLabResultDetailsViewDto.add(labResultDetailsViewDto);
                              }
                         
                            }
                            else if(parameterMasterDto.getRefrangeTypeId()==MULTIVALUED_RANGE)
                            {
                              Optional<ParamMultiTextualRangeMasterDto> paramMultiTextualRangeMstDto = parameterMasterDto.getListParamMultiTextualRangeMaster()
                                  .stream()
                                  .filter(ageGrp -> (bioChemParamDto.getPatientAgeDays() >= ageGrp.getAgeFromDay()
                                          && bioChemParamDto.getPatientAgeDays() <= ageGrp.getAgeToDay()
                                          && ageGrp.getIsDeleted() == 'N'&& ageGrp.getGenderId().intValue() == bioChemParamDto.getGenderId().intValue()))
                                  .findFirst();

                              ParamMultiTextualRangeMasterDto paramMultiTextualRangeMasterDto = paramMultiTextualRangeMstDto.orElse(null);
                          if (paramMultiTextualRangeMasterDto != null) {
                              labResultDetailsViewDto.setParameterId(paramMultiTextualRangeMasterDto.getParameterId());
                              labResultDetailsViewDto.setIsFormula(parameterMasterDto.getIsFormula());
                              labResultDetailsViewDto.setMultitextaulRange(paramMultiTextualRangeMasterDto.getMultitextaulRange());
                              labResultDetailsViewDto.setRefrangeTypeId(parameterMasterDto.getRefrangeTypeId());
                              labResultDetailsViewDto.setParamUnit(parameterMasterDto.getUnitName());
                              labResultDetailsViewDto.setDeltaDaysId(parameterMasterDto.getDeltaDaysId());
                              labResultDetailsViewDto.setRefrangeTypeId(parameterMasterDto.getRefrangeTypeId());
                              labResultDetailsViewDto.setHeaderId(tstParamMpprDto.getHeaderId());
                              labResultDetailsViewDto.setOrgId(testMasterDto.getOrgId());
                              labResultDetailsViewDto.setOrgUnitId(testMasterDto.getOrgUnitId());
                              labResultDetailsViewDto.setParamPrintOrder(0);
                              listLabResultDetailsViewDto.add(labResultDetailsViewDto);
                              }
                            }
                        }
                    multyParamTestHeaderDto.setListLabResultDetailsViewDto(listLabResultDetailsViewDto);
                    listMultyParamTestHeaderDto.add(multyParamTestHeaderDto);
                    
                }

                singParamTestResDto.setListMultyParamTestHeaderDto(listMultyParamTestHeaderDto);
                testMasterDtoList.add(singParamTestResDto);
            }
          }
		  return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, testMasterDtoList, null);
			
		} catch (

		Exception e) {
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response saveEntryDetails(List<LabResultMasterDto> listLabResultMasterDto) throws ApplicationException {
		try {
			return iBioChemResultEntryDao.saveEntryDetails(listLabResultMasterDto);
		} catch (Exception e) {
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response getSingleParamTestResult(BioChemParamDto bioChemParamDto) throws ApplicationException {
		try {
			return iBioChemResultEntryDao.getSingleParamTestResult(bioChemParamDto);
		} catch (Exception e) {
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response getMultyParamTestResult(BioChemParamDto bioChemParamDto) throws ApplicationException {
		try {
			return iBioChemResultEntryDao.getMultyParamTestResult(bioChemParamDto);
		} catch (Exception e) {
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

}
