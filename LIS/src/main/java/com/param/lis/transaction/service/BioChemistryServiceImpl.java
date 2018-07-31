package com.param.lis.transaction.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.param.entity.lis.transaction.LabSampleDetailsMaster;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.ITransactionConstants;
import com.param.lis.global.common.Response;
import com.param.lis.global.common.SearchDto;
import com.param.lis.transaction.dao.IBioChemResultEntryDao;
import com.param.lis.transaction.dao.IBioChemistryDao;
import com.param.lis.transaction.dao.IBiochemValidationWorklistDao;
import com.param.lis.transaction.dto.BioChemParamDto;
import com.param.lis.transaction.dto.LabResultDetailsViewDto;
import com.param.lis.transaction.dto.LabResultMasterDto;
import com.param.lis.transaction.dto.LabSampleDetailsMasterDto;
import com.param.lis.transaction.dto.MultyParamTestHeaderDto;
import com.param.lis.transaction.dto.ParameterHistoryDto;
import com.param.lis.transaction.dto.ParameterResultDto;
import com.param.lis.transaction.dto.ParameterSearchDto;
import com.param.lis.transaction.dto.ResultEntryDataDto;
import com.param.lis.transaction.dto.RetestRecollectDto;
import com.param.lis.transaction.dto.SearchCommonDto;
import com.param.lis.transaction.dto.SingParamTestResDto;
import com.param.lis.unit.dao.IParameterMasterDao;
import com.param.lis.unit.dto.HelpValueMasterDto;
import com.param.lis.unit.dto.TestMasterDto;
import com.param.lis.unit.dto.TestParamMpprDto;
import in.param.exception.ApplicationException;

@Service
@SuppressWarnings({"rawtypes", "unchecked"})
public class BioChemistryServiceImpl
    implements IBioChemistryService, ICommonConstants, IError, ITransactionConstants {
  @Autowired
  IBioChemistryDao iBioChemistryDao;

  @Autowired
  IBioChemResultEntryDao iBioChemResultEntryDao;

  @Autowired
  IBiochemValidationWorklistDao iBiochemValidationWorklistDao;

  @Autowired
  IParameterMasterDao iParameterMasterDao;
  
  @Autowired
  SessionFactory sessionFactory;

  final static Logger logger = Logger.getLogger(BioChemistryServiceImpl.class);

  @Override
  @Transactional
  public Response sampleAcceptancePending(BioChemParamDto bioChemParamDto)
      throws ApplicationException {
    try {
      return iBioChemistryDao.sampleAcceptancePending(bioChemParamDto);
    } catch (Exception e) {
      logger.error("Exception", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  @Transactional
  public Response sampleAcceptancePendingCount(BioChemParamDto bioChemParamDto)
      throws ApplicationException {
    try {
      return iBioChemistryDao.sampleAcceptancePendingCount(bioChemParamDto);
    } catch (Exception e) {
      logger.error("Exception", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  @Transactional
  public Response bioChemistryAcceptRejectSample(
      List<LabSampleDetailsMasterDto> listlabSampleDetailsMasterDto) throws ApplicationException {
    try {
      return iBioChemistryDao.bioChemistryAcceptRejectSample(listlabSampleDetailsMasterDto);
    } catch (Exception e) {
      logger.error("Exception", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  @Transactional
  public Response getBioChemistryWorklist(BioChemParamDto bioChemParamDto)
      throws ApplicationException {
    try {
      return iBioChemistryDao.getBioChemistryWorklist(bioChemParamDto);
    } catch (Exception e) {
      logger.error("Exception", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  @Transactional
  public Response getBioChemistryWorklistCount(BioChemParamDto bioChemParamDto)
      throws ApplicationException {
    try {
      return iBioChemistryDao.getBioChemistryWorklistCount(bioChemParamDto);
    } catch (Exception e) {
      logger.error("Exception", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  @Transactional
  public Response sendToResultEntry(List<LabSampleDetailsMasterDto> listlabSampleDetailsMasterDto)
      throws ApplicationException {
    try {
      return iBioChemistryDao.sendToResultEntry(listlabSampleDetailsMasterDto);
    } catch (Exception e) {
      logger.error("Exception", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  @Transactional
  public Response sampleRetest(List<LabResultMasterDto> listLabResultMasterDto)
      throws ApplicationException {
    try {
      return iBioChemistryDao.sampleRetest(listLabResultMasterDto);
    } catch (Exception e) {
      logger.error("Exception", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  @Transactional
  public Response biochemFinalReportRelease(LabSampleDetailsMasterDto labSampleDetailsMasterDto)
      throws ApplicationException {
    try {
      return iBioChemistryDao.biochemFinalReportRelease(labSampleDetailsMasterDto);
    } catch (Exception e) {
      logger.error("Exception", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  @Transactional
  public Response sampleRecollect(List<LabResultMasterDto> listLabResultMasterDto)
      throws ApplicationException {
    try {
      return iBioChemistryDao.sampleRecollect(listLabResultMasterDto);
    } catch (Exception e) {
      logger.error("Exception", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  @Transactional
  public Response resultEntryData(ResultEntryDataDto resultEntryDataDto)
      throws ApplicationException {
    try {
      return iBioChemistryDao.resultEntryData(resultEntryDataDto);
    } catch (Exception e) {
      logger.error("Exception", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  @Transactional
  public Response addReportHistroyMaster(LabSampleDetailsMaster labSampleDetailsMaster,
      Integer resultId) throws ApplicationException {
    try {
      return iBioChemistryDao.addReportHistroyMaster(labSampleDetailsMaster, resultId);
    } catch (Exception e) {
      logger.error("Exception", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  @Transactional
  public Response searchbioChemistryPatientlist(SearchCommonDto searchCommonDto)
      throws ApplicationException {
    try {
      return iBioChemistryDao.searchbioChemistryPatientlist(searchCommonDto);
    } catch (Exception e) {
      logger.error("Exception", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  @Transactional
  public Response searchBioChemistrySampleNo(SearchCommonDto searchCommonDto)
      throws ApplicationException {
    try {
      return iBioChemistryDao.searchBioChemistrySampleNo(searchCommonDto);
    } catch (Exception e) {
      logger.error("Exception", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  @Transactional
  public Response getFilteredBioChemistryList(SearchDto searchDto) throws ApplicationException {
    try {
      return iBioChemistryDao.getFilteredBioChemistryList(searchDto);
    } catch (Exception e) {
      logger.error("Exception", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  @Transactional
  public Response getFilteredWorklistForDepartment(SearchDto searchDto)
      throws ApplicationException {
    try {
      return iBioChemistryDao.getFilteredWorklistForDepartment(searchDto);
    } catch (Exception e) {
      logger.error("Exception", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  @Transactional
  public Response getParameterHistory(ParameterSearchDto parameterSearchDto)
      throws ApplicationException {
    try {
      return iBioChemistryDao.getParameterHistory(parameterSearchDto);
    } catch (Exception e) {
      logger.error("Exception", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  @Transactional
  public Response getPreviousResultIdByTest(BioChemParamDto bioChemParamDto)
      throws ApplicationException {
    try {
      return iBioChemistryDao.getPreviousResultIdByTest(bioChemParamDto);
    } catch (Exception e) {
      logger.error("Exception", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  @Transactional
  public Response getRetestedRecollectedResult(BioChemParamDto bioChemParamDto)
      throws ApplicationException {
    try {
      Response<SingParamTestResDto,Integer> retestRecollectRes = iBioChemistryDao.getRetestedRecollectedResult(bioChemParamDto);
      
      List<SingParamTestResDto> listSingParam = retestRecollectRes.getListObject();
      
      HashMap<ParameterHistoryDto,List<ParameterResultDto>> hashMapKey= new HashMap<>();
      
      List<ParameterHistoryDto> listParameterHistory =  new ArrayList<>();
      
      List<ParameterHistoryDto> listParameterHistoryDto = sessionFactory.getCurrentSession()
          .getNamedQuery("GET_PARAMETER_FOR_HISTORY")
          .setInteger("testId", bioChemParamDto.getTestId())
          .setInteger("orgId", bioChemParamDto.getOrgId())
          .setInteger("orgUnitId", bioChemParamDto.getOrgUnitId())
          .setResultTransformer(Transformers.aliasToBean(ParameterHistoryDto.class)).list();
   
   
      if(listSingParam!=null && listSingParam.size()>0)
      {
        RetestRecollectDto retestRecollectDto = new RetestRecollectDto();
        
        for (Iterator iterator = listSingParam.iterator(); iterator.hasNext();) 
        {
          SingParamTestResDto singParamTestResDto = (SingParamTestResDto) iterator.next();
          retestRecollectDto.setCreatedDate(singParamTestResDto.getCreatedDate());
          retestRecollectDto.setTestId(singParamTestResDto.getTestId());
          retestRecollectDto.setTestType(singParamTestResDto.getTestType());
          
          for (Iterator iterator2 = listParameterHistoryDto.iterator(); iterator2.hasNext();) 
          {
            ParameterHistoryDto parameterHistoryDto = (ParameterHistoryDto) iterator2.next();
            ParameterResultDto parameterResultDto = (ParameterResultDto) sessionFactory.getCurrentSession()
                                                          .getNamedQuery("GET_RETEST_RECOLLECT_PARAMETERS")
                                                          .setInteger("labResultId", singParamTestResDto.getLabTestResId())
                                                          .setInteger("parameterId", parameterHistoryDto.getParameterId())
                                                          .setResultTransformer(Transformers.aliasToBean(ParameterResultDto.class)).uniqueResult();  
            
            if(hashMapKey.containsKey(parameterHistoryDto.getParameterId()))
            {
               hashMapKey.get(parameterHistoryDto.getParameterId()).add(parameterResultDto);
            }
            else 
            {
                 List<ParameterResultDto> listParameterResultDto = new ArrayList<>();
                 listParameterResultDto.add(parameterResultDto);
                 hashMapKey.put(parameterHistoryDto, listParameterResultDto);
            }
          }
        }
        for(Map.Entry<ParameterHistoryDto, List<ParameterResultDto>> entry : hashMapKey.entrySet()) 
        {
          
          ParameterHistoryDto parameterHistoryDto = entry.getKey();
          parameterHistoryDto.setListParameterResultDto(entry.getValue());
          listParameterHistory.add(parameterHistoryDto);
        }
        retestRecollectDto.setListParameterHistoryDto(listParameterHistory);
        return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, retestRecollectDto);
      }
      else {
        return new Response(SUCCESS, SUCCESS_200, "No Previous Lab Tests Found.", null, null);
      }
    } catch (Exception e) {
      logger.error("Exception", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  @Transactional
  public Response getResultEnteryByDateTime(BioChemParamDto bioChemParamDto)
      throws ApplicationException {
      try {
        return iBioChemistryDao.getResultEnteryByDateTime(bioChemParamDto);
      } catch (Exception e) {
        logger.error("Exception", e);
        return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
      }
  }

  @Override
  @Transactional
  public Response getResultValidatedByDateTime(BioChemParamDto bioChemParamDto)
      throws ApplicationException {
      try {
        return iBioChemistryDao.getResultValidatedByDateTime(bioChemParamDto);
      } catch (Exception e) {
        logger.error("Exception", e);
        return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
      }
  }

  @Override
  @Transactional
  public Response getResultReleaseByDateTime(BioChemParamDto bioChemParamDto)
      throws ApplicationException {
    try {
      return iBioChemistryDao.getResultReleaseByDateTime(bioChemParamDto);
    } catch (Exception e) {
      logger.error("Exception", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

}

/*List<SingParamTestResDto> listSingParamTestResDto = new ArrayList<>();
for (Iterator iterator = listSingParam.iterator(); iterator.hasNext();) 
{
  
  SingParamTestResDto labResultMasterDto = (SingParamTestResDto) iterator.next();
  
  bioChemParamDto.setTestId(labResultMasterDto.getTestId());
  bioChemParamDto.setTestType(labResultMasterDto.getTestType());
  bioChemParamDto.setLabResultId(labResultMasterDto.getLabTestResId());
  Response<TestMasterDto, Integer> response = null;
  if (bioChemParamDto.getTestType().intValue()==SINGLE_PARAM_TEST) 
  {
      SingParamTestResDto singParamTestResDto = new SingParamTestResDto();
      
      response = iBioChemResultEntryDao.getSingleParamTestResult(bioChemParamDto);
      TestMasterDto testMasterDto = response.getObject();
      singParamTestResDto.setTestId(testMasterDto.getTestId());
      singParamTestResDto.setTestDesc(testMasterDto.getTestDesc());
      singParamTestResDto.setTestCode(testMasterDto.getTestCode());
      singParamTestResDto.setTestType(bioChemParamDto.getTestType());
      singParamTestResDto.setTechniqueName(testMasterDto.getTechniqueName());
      singParamTestResDto.setFootsNotes(labResultMasterDto.getFootsNotes());
      singParamTestResDto.setResultEnterByUser(labResultMasterDto.getResultEnterByUser());
      singParamTestResDto.setResultEnterDatetime(labResultMasterDto.getResultEnterDatetime());
      singParamTestResDto.setResultValidatedByUser(labResultMasterDto.getResultValidatedByUser());
      singParamTestResDto.setResultValidatedDatetime(labResultMasterDto.getResultValidatedDatetime());
      singParamTestResDto.setResultAuthorisedByUser(labResultMasterDto.getResultAuthorisedByUser());
      singParamTestResDto.setResultAuthorisedDatetime(labResultMasterDto.getResultAuthorisedDatetime());
      singParamTestResDto.setLabSampleNo(labResultMasterDto.getLabSampleNo());

      Response<LabResultDetailsViewDto, Integer> labResDetails = iBiochemValidationWorklistDao
              .getPrivousResultBySample(bioChemParamDto);
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
      listSingParamTestResDto.add(singParamTestResDto);

  } else if (bioChemParamDto.getTestType().intValue()==MULTY_PARAM_TEST) {
      response = iBioChemResultEntryDao.getMultyParamTestResult(bioChemParamDto);
      TestMasterDto testMasterDto = response.getObject();
      SingParamTestResDto singParamTestResDto = new SingParamTestResDto();
      List<MultyParamTestHeaderDto> listMultyParamTestHeaderDto = new ArrayList<>();

      singParamTestResDto.setTestId(testMasterDto.getTestId());
      singParamTestResDto.setTestDesc(testMasterDto.getTestDesc());
      singParamTestResDto.setTestCode(testMasterDto.getTestCode());
      singParamTestResDto.setTechniqueName(testMasterDto.getTechniqueName());
      singParamTestResDto.setTestType(bioChemParamDto.getTestType());
      singParamTestResDto.setFootsNotes(labResultMasterDto.getFootsNotes());
      singParamTestResDto.setResultEnterByUser(labResultMasterDto.getResultEnterByUser());
      singParamTestResDto.setResultEnterDatetime(labResultMasterDto.getResultEnterDatetime());
      singParamTestResDto.setResultValidatedByUser(labResultMasterDto.getResultValidatedByUser());
      singParamTestResDto.setResultValidatedDatetime(labResultMasterDto.getResultValidatedDatetime());
      singParamTestResDto.setResultAuthorisedByUser(labResultMasterDto.getResultAuthorisedByUser());
      singParamTestResDto.setResultAuthorisedDatetime(labResultMasterDto.getResultAuthorisedDatetime());
      singParamTestResDto.setLabSampleNo(labResultMasterDto.getLabSampleNo());

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
  }
  }*/
