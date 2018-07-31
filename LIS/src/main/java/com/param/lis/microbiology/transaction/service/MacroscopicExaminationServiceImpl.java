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
import com.param.lis.microbiology.transaction.dao.IMacroscopicExaminationDao;
import com.param.lis.microbiology.transaction.dto.MicrobioParamDto;
import com.param.lis.microbiology.transaction.dto.TMacroscopicExaminationMasterDto;
import com.param.lis.microbiology.transaction.dto.TMicroIncubationMasterDto;
import com.param.lis.transaction.dto.LabSampleDetailsMasterDto;
import in.param.exception.ApplicationException;

@Service
@SuppressWarnings({"rawtypes", "unchecked"})
public class MacroscopicExaminationServiceImpl implements IMacroscopicExaminationService, ICommonConstants, ITransactionConstants, IError {
  
  @Autowired
  IMacroscopicExaminationDao iMacroscopicExaminationDao;
  
  @Autowired
  IMicrobiologyService iMicrobiologyService;
  
  final static Logger logger = Logger.getLogger(MacroscopicExaminationServiceImpl.class);

  @Override
  @Transactional
  public Response getMacroScopicExaminationList(MicrobioParamDto microbioParamDto)
      throws ApplicationException {
    try {
      return iMacroscopicExaminationDao.getMacroScopicExaminationList(microbioParamDto);
    } catch (Exception e) {
      logger.error("Exception", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  @Transactional
  public Response getMacroScopicExaminationListCount(MicrobioParamDto microbioParamDto)
      throws ApplicationException {
    try {
      return iMacroscopicExaminationDao.getMacroScopicExaminationListCount(microbioParamDto);
    } catch (Exception e) {
      logger.error("Exception", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  @Transactional
  public Response saveMacroScopicExamination(
      List<TMacroscopicExaminationMasterDto> tMacroscopicExaminationMasterDtoList)
      throws ApplicationException {
    try {
      for (Iterator iterator = tMacroscopicExaminationMasterDtoList.iterator(); iterator
          .hasNext();) {
        TMacroscopicExaminationMasterDto tMacroscopicExaminationMasterDto = (TMacroscopicExaminationMasterDto) iterator.next();
        Response<TMicroIncubationMasterDto, Integer> tMicroIncubationMasterRes =
            iMacroscopicExaminationDao.saveMacroScopicExamination(tMacroscopicExaminationMasterDto);
        if (tMicroIncubationMasterRes.getCode().equals(SUCCESS_200)) {
          LabSampleDetailsMasterDto labSampleDetailsMasterDto = new LabSampleDetailsMasterDto();
          labSampleDetailsMasterDto.setCreatedBy(tMacroscopicExaminationMasterDto.getCreatedBy());
          labSampleDetailsMasterDto.setLabSampleDtlsId(tMacroscopicExaminationMasterDto.getLabSampleDtlsId());
          labSampleDetailsMasterDto
              .setCreatedDate(new Date(tMacroscopicExaminationMasterDto.getCreatedDate()));
          if (tMacroscopicExaminationMasterDto.getIsCompleted().equals('Y')) 
          {
            labSampleDetailsMasterDto.setSampleStatusId(MACROSCOPIC_EXAMINATION_COMPLETED);
            Response<LabSampleDetailsMasterDto, Integer> sampleRes =
                iMicrobiologyService.updateLabSampleDetailsMaster(labSampleDetailsMasterDto);
            if (sampleRes.getCode().equals(SUCCESS_200)) {
              return new Response(SUCCESS, SUCCESS_200, "Macroscopic Examination Saved Successfully.", null,
                  null);
            } else {
              return new Response(ERROR, ERR_500, "Failed To Save Macroscopic Examination.", null, null);
            }

          } else {
            labSampleDetailsMasterDto.setSampleStatusId(MACROSCOPIC_EXAMINATION);
            Response<LabSampleDetailsMasterDto, Integer> sampleRes =
                iMicrobiologyService.updateLabSampleDetailsMaster(labSampleDetailsMasterDto);
            if (sampleRes.getCode().equals(SUCCESS_200)) {
              return new Response(SUCCESS, SUCCESS_200, "Macroscopic Examination Saved Successfully.", null,
                  null);
            } else {
              return new Response(ERROR, ERR_500, "Failed To Save Macroscopic Examination.", null, null);
            }
          }
        } else {
          return new Response(ERROR, ERR_500, "Failed To Save Macroscopic Examination.", null, null);
        }
      }
      return new Response(ERROR, ERR_500, "Failed To Save Macroscopic Examination.", null, null);
    } catch (Exception e) {
      logger.error("Exception", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  @Transactional
  public Response updateMacroScopicExamination(
      List<TMacroscopicExaminationMasterDto> tMacroscopicExaminationMasterDtoList)
      throws ApplicationException {
    try 
    {
      
      for (Iterator iterator = tMacroscopicExaminationMasterDtoList.iterator(); iterator
          .hasNext();) {
        TMacroscopicExaminationMasterDto tMacroscopicExaminationMasterDto =
            (TMacroscopicExaminationMasterDto) iterator.next();
        Response<TMicroIncubationMasterDto, Integer> tMicroIncubationMasterRes =
            iMacroscopicExaminationDao.updateMacroScopicExamination(tMacroscopicExaminationMasterDto);
        if (tMicroIncubationMasterRes.getCode().equals(SUCCESS_200)) {
          LabSampleDetailsMasterDto labSampleDetailsMasterDto = new LabSampleDetailsMasterDto();
          labSampleDetailsMasterDto.setLabSampleDtlsId(tMacroscopicExaminationMasterDto.getLabSampleDtlsId());
          labSampleDetailsMasterDto.setCreatedBy(tMacroscopicExaminationMasterDto.getCreatedBy());
          labSampleDetailsMasterDto
              .setCreatedDate(new Date(tMacroscopicExaminationMasterDto.getUpdatedDate()));
          if (tMacroscopicExaminationMasterDto.getIsCompleted().equals('Y')) {
            labSampleDetailsMasterDto.setSampleStatusId(MACROSCOPIC_EXAMINATION_COMPLETED);
            Response<LabSampleDetailsMasterDto, Integer> sampleRes =
                iMicrobiologyService.updateLabSampleDetailsMaster(labSampleDetailsMasterDto);
            if (sampleRes.getCode().equals(SUCCESS_200)) {
              return new Response(SUCCESS, SUCCESS_200, "Macroscopic Examination Saved Successfully.", null,
                  null);
            } else {
              return new Response(ERROR, ERR_500, "Failed To Save Macroscopic Examination.", null, null);

            }

          } else {
            labSampleDetailsMasterDto.setSampleStatusId(MACROSCOPIC_EXAMINATION);
            Response<LabSampleDetailsMasterDto, Integer> sampleRes =
                iMicrobiologyService.updateLabSampleDetailsMaster(labSampleDetailsMasterDto);
            if (sampleRes.getCode().equals(SUCCESS_200)) {
              return new Response(SUCCESS, SUCCESS_200, "Macroscopic Examination Saved Successfully.", null,
                  null);
            } else {
              return new Response(ERROR, ERR_500, "Failed To Save Macroscopic Examination.", null, null);
            }
          }
        } else {
          return new Response(ERROR, ERR_500, "Failed To Save Macroscopic Examination.", null, null);
        }
      }
      return new Response(ERROR, ERR_500, "Failed To Save Macroscopic Examination.", null, null);
    } catch (Exception e) {
      logger.error("Exception", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  @Transactional
  public Response getMacroScopicExamination(Integer tMacroExaId, Integer orgId,
      Integer orgUnitId) throws ApplicationException {
    try {
      return iMacroscopicExaminationDao.getMacroScopicExamination(tMacroExaId, orgId, orgUnitId);
    } catch (Exception e) {
      logger.error("Exception", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }
}

