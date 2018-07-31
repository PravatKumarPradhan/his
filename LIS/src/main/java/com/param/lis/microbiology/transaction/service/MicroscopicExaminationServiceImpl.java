package com.param.lis.microbiology.transaction.service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.transaction.Transactional;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.ITransactionConstants;
import com.param.lis.global.common.Response;
import com.param.lis.microbiology.transaction.dao.IMicroscopicExaminationDao;
import com.param.lis.microbiology.transaction.dto.MicrobioParamDto;
import com.param.lis.microbiology.transaction.dto.MicroscopicExaminationMasterDto;
import com.param.lis.microbiology.transaction.dto.MicroscopicExaminationSubDetailsMasterDto;
import com.param.lis.transaction.dto.LabSampleDetailsMasterDto;
import in.param.exception.ApplicationException;

@Service
@SuppressWarnings({"rawtypes", "unchecked"})
public class MicroscopicExaminationServiceImpl  implements IMicroscopicExaminationService, ICommonConstants, ITransactionConstants, IError {
  
  @Autowired
  IMicroscopicExaminationDao iMicroscopicExaminationDao;
 
  @Autowired
  IMicrobiologyService iMicrobiologyService;
  
  final static Logger logger = Logger.getLogger(MicroscopicExaminationServiceImpl.class);


  @Override
  @Transactional
  public Response getMicroScopicExaminationList(MicrobioParamDto microbioParamDto)
      throws ApplicationException {
    try {
      return iMicroscopicExaminationDao.getMicroScopicExaminationList(microbioParamDto);
    } catch (Exception e) {
      logger.error("Exception", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }


  @Override
  @Transactional
  public Response getMicroScopicExaminationListCount(MicrobioParamDto microbioParamDto)
      throws ApplicationException {
    try {
      return iMicroscopicExaminationDao.getMicroScopicExaminationListCount(microbioParamDto);
    } catch (Exception e) {
      logger.error("Exception", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }


  @Override
  @Transactional
  public Response saveMicroScopicExamination(
      List<MicroscopicExaminationMasterDto> listMicroscopicExaminationMaster)
      throws ApplicationException {
    try {
      
        boolean flag=  false;
        
        LabSampleDetailsMasterDto labSampleDetailsMasterDto = new LabSampleDetailsMasterDto();
        labSampleDetailsMasterDto.setCreatedBy(listMicroscopicExaminationMaster.get(0).getCreatedBy());
        labSampleDetailsMasterDto.setLabSampleDtlsId(listMicroscopicExaminationMaster.get(0).getLabSampleDtlsId());
        labSampleDetailsMasterDto.setCreatedDate(new Date(listMicroscopicExaminationMaster.get(0).getCreatedDate()));
        
       for (Iterator iterator = listMicroscopicExaminationMaster.iterator(); iterator.hasNext();) {
        MicroscopicExaminationMasterDto microscopicExaminationMasterDto = 
            (MicroscopicExaminationMasterDto) iterator.next();
        flag = microscopicExaminationMasterDto.getIsCompleted()=='Y'?true:false;
        if(microscopicExaminationMasterDto.getMicroscopicExaId()!=null&&
            microscopicExaminationMasterDto.getMicroscopicExaId()>0)
        {
         
          Response<MicroscopicExaminationMasterDto, Integer> microscopicExaminationMstRes = iMicroscopicExaminationDao.updateMicroScopicExamination(microscopicExaminationMasterDto);
          if(microscopicExaminationMstRes.getCode().equals(SUCCESS_200))
          {
            continue;
          }
          else {
            return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
          }
        }
        else 
        {
          Response<MicroscopicExaminationMasterDto, Integer> microscopicExaminationMstRes =  iMicroscopicExaminationDao.saveMicroScopicExamination(microscopicExaminationMasterDto);
          if(microscopicExaminationMstRes.getCode().equals(SUCCESS_200))
          {
            continue;
          }
          else {
            return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
          }
        }
      }
       if(flag)
       {
         labSampleDetailsMasterDto.setSampleStatusId(RELEASE_FOR_SENSITIVITY_ENTRY);
         Response<LabSampleDetailsMasterDto, Integer> sampleRes =
             iMicrobiologyService.updateLabSampleDetailsMaster(labSampleDetailsMasterDto);
         if (sampleRes.getCode().equals(SUCCESS_200)) {
           return new Response(SUCCESS, SUCCESS_200, "Result Saved Successfully.", null,
               null);
         } else {
           return new Response(ERROR, ERR_500, "Failed To Save Result.", null, null);
         }
       }
       else {
         labSampleDetailsMasterDto.setSampleStatusId(MICROSCOPIC_EXAMINATION);
         Response<LabSampleDetailsMasterDto, Integer> sampleRes =
             iMicrobiologyService.updateLabSampleDetailsMaster(labSampleDetailsMasterDto);
         if (sampleRes.getCode().equals(SUCCESS_200)) {
           return new Response(SUCCESS, SUCCESS_200, "Result Saved Successfully.", null,
               null);
         } else {
           return new Response(ERROR, ERR_500, "Failed To Save Result.", null, null);
         }
       }
    } catch (Exception e) {
      logger.error("Exception", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }


  @Override
  @Transactional
  public Response updateMicroScopicExamination(
      List<MicroscopicExaminationMasterDto> listMicroscopicExaminationMaster)
      throws ApplicationException {
    try {
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    } catch (Exception e) {
      logger.error("Exception", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }


  @Override
  @Transactional
  public Response getMicroScopicExamination(Integer labSampleDtlsId, Integer orgId,
      Integer orgUnitId) throws ApplicationException {
    try {
      return iMicroscopicExaminationDao.getMicroScopicExamination(labSampleDtlsId, orgId, orgUnitId);
    } catch (Exception e) {
      logger.error("Exception", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

}
