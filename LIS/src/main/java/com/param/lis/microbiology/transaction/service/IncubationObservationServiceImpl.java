package com.param.lis.microbiology.transaction.service;

import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.param.entity.lis.micro.MicrobioResultEntryMaster;
import com.param.entity.lis.transaction.LabSampleDetailsMaster;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.ITransactionConstants;
import com.param.lis.global.common.Response;
import com.param.lis.microbiology.transaction.dao.IincubationObservationDao;
import com.param.lis.microbiology.transaction.dto.MicrobioParamDto;
import com.param.lis.microbiology.transaction.dto.MicrobioResultEntryMasterDto;
import com.param.lis.microbiology.transaction.dto.TMicroIncubationMasterDto;
import com.param.lis.transaction.dto.LabSampleDetailsMasterDto;
import in.param.exception.ApplicationException;

@Service
@SuppressWarnings({"rawtypes", "unchecked"})
public class IncubationObservationServiceImpl
    implements IincubationObservationService, ICommonConstants, ITransactionConstants, IError {

  @Autowired
  IincubationObservationDao iincubationObservationDao;

  @Autowired
  IMicrobiologyService iMicrobiologyService;



  final static Logger logger = Logger.getLogger(MicrobiologyServiceImpl.class);


  @Override
  @Transactional
  public Response getIncubationObservationList(MicrobioParamDto microbioParamDto)
      throws ApplicationException {
    try {
      return iincubationObservationDao.getIncubationObservationList(microbioParamDto);
    } catch (Exception e) {
      logger.error("Exception", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }


  @Override
  @Transactional
  public Response getIncubationObservationListCount(MicrobioParamDto microbioParamDto)
      throws ApplicationException {
    try {
      return iincubationObservationDao.getIncubationObservationListCount(microbioParamDto);
    } catch (Exception e) {
      logger.error("Exception", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }





  @Override
  @Transactional
  public Response saveIncubationTrasaction(TMicroIncubationMasterDto tMicroIncubationMasterDto)
      throws ApplicationException {
    try {
      Response<TMicroIncubationMasterDto, Integer> tMicroIncubationMasterRes =
          iincubationObservationDao.saveIncubationTrasaction(tMicroIncubationMasterDto);
      if (tMicroIncubationMasterRes.getCode().equals(SUCCESS_200)) {
        LabSampleDetailsMasterDto labSampleDetailsMasterDto = new LabSampleDetailsMasterDto();
        labSampleDetailsMasterDto.setCreatedBy(tMicroIncubationMasterDto.getCreatedBy());
        labSampleDetailsMasterDto.setLabSampleDtlsId(tMicroIncubationMasterDto.getLabSampleDtlsId());
        labSampleDetailsMasterDto
            .setCreatedDate(new Date(tMicroIncubationMasterDto.getCreatedDate()));
        if (tMicroIncubationMasterDto.getIsCompleted().equals('Y')) {
          labSampleDetailsMasterDto.setSampleStatusId(INCUBATION_OBJSERVATION_COMPLETED);
          Response<LabSampleDetailsMasterDto, Integer> sampleRes =
              iMicrobiologyService.updateLabSampleDetailsMaster(labSampleDetailsMasterDto);
          if (sampleRes.getCode().equals(SUCCESS_200)) {
            return new Response(SUCCESS, SUCCESS_200, "Incubation Started Successfully.", null,
                null);
          } else {
            return new Response(ERROR, ERR_500, "Failed To Start Incubation.", null, null);
          }

        } else {
          labSampleDetailsMasterDto.setSampleStatusId(DURING_OBJSERVATION);
          Response<LabSampleDetailsMasterDto, Integer> sampleRes =
              iMicrobiologyService.updateLabSampleDetailsMaster(labSampleDetailsMasterDto);
          if (sampleRes.getCode().equals(SUCCESS_200)) {
            return new Response(SUCCESS, SUCCESS_200, "Incubation Started Successfully.", null,
                null);
          } else {
            return new Response(ERROR, ERR_500, "Failed To Start Incubation.", null, null);
          }
        }
      } else {
        return new Response(ERROR, ERR_500, "Failed To Start Incubation.", null, null);
      }

    } catch (Exception e) {
      logger.error("Exception", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }

  }

  @Override
  @Transactional
  public Response updateIncubationTransaction(TMicroIncubationMasterDto tMicroIncubationMasterDto)
      throws ApplicationException {
    try {
      Response<TMicroIncubationMasterDto, Integer> tMicroIncubationMasterRes =
          iincubationObservationDao.updateIncubationTransaction(tMicroIncubationMasterDto);
      if (tMicroIncubationMasterRes.getCode().equals(SUCCESS_200)) {
        LabSampleDetailsMasterDto labSampleDetailsMasterDto = new LabSampleDetailsMasterDto();
        labSampleDetailsMasterDto.setLabSampleDtlsId(tMicroIncubationMasterDto.getLabSampleDtlsId());
        labSampleDetailsMasterDto.setCreatedBy(tMicroIncubationMasterDto.getCreatedBy());
        labSampleDetailsMasterDto
            .setCreatedDate(new Date(tMicroIncubationMasterDto.getCreatedDate()));
        if (tMicroIncubationMasterDto.getIsCompleted().equals('Y')) {
          labSampleDetailsMasterDto.setSampleStatusId(INCUBATION_OBJSERVATION_COMPLETED);
          Response<LabSampleDetailsMasterDto, Integer> sampleRes =
              iMicrobiologyService.updateLabSampleDetailsMaster(labSampleDetailsMasterDto);
          if (sampleRes.getCode().equals(SUCCESS_200)) {
            return new Response(SUCCESS, SUCCESS_200, "Incubation Restarted Successfully.", null,
                null);
          } else {
            return new Response(ERROR, ERR_500, "Failed To ReStart Incubation.", null, null);

          }

        } else {
          labSampleDetailsMasterDto.setSampleStatusId(DURING_OBJSERVATION);
          Response<LabSampleDetailsMasterDto, Integer> sampleRes =
              iMicrobiologyService.updateLabSampleDetailsMaster(labSampleDetailsMasterDto);
          if (sampleRes.getCode().equals(SUCCESS_200)) {
            return new Response(SUCCESS, SUCCESS_200, "Incubation Restarted Successfully.", null,
                null);
          } else {
            return new Response(ERROR, ERR_500, "Failed To ReStart Incubation.", null, null);
          }
        }
      } else {
        return new Response(ERROR, ERR_500, "Failed To ReStart Incubation.", null, null);
      }

    } catch (Exception e) {
      logger.error("Exception", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  @Transactional
  public Response getIncubationDetails(Integer tIncubationId, Integer orgId, Integer orgUnitId)
      throws ApplicationException {
    try {
      return iincubationObservationDao.getIncubationDetails(tIncubationId, orgId, orgUnitId);
    } catch (Exception e) {
      logger.error("Exception", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }


 


}
