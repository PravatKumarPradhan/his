package com.param.lis.microbiology.transaction.service;

import java.util.Date;
import javax.transaction.Transactional;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.param.entity.lis.micro.TBiochemicalTestMaster;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.ITransactionConstants;
import com.param.lis.global.common.Response;
import com.param.lis.microbiology.transaction.dao.IBiochemicalTestResultDao;
import com.param.lis.microbiology.transaction.dto.MicrobioParamDto;
import com.param.lis.microbiology.transaction.dto.TBiochemicalTestMasterDto;
import com.param.lis.transaction.dto.LabSampleDetailsMasterDto;
import in.param.exception.ApplicationException;

@Service
@SuppressWarnings({"rawtypes", "unchecked"})
public class BiochemicalTestResultServiceImpl  implements IBiochemicalTestResultService, ICommonConstants, ITransactionConstants, IError {
  
  
  @Autowired
  IBiochemicalTestResultDao iBiochemicalTestResultDao;

  @Autowired
  IMicrobiologyService iMicrobiologyService;

  final static Logger logger = Logger.getLogger(BiochemicalTestResultServiceImpl.class);


  @Override
  @Transactional
  public Response getBiochemicalWorkList(MicrobioParamDto microbioParamDto)
      throws ApplicationException {
    try {
      return iBiochemicalTestResultDao.getBiochemicalWorkList(microbioParamDto);
    } catch (Exception e) {
      logger.error("Exception", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }


  @Override
  @Transactional
  public Response getBiochemicalWorkListCount(MicrobioParamDto microbioParamDto)
      throws ApplicationException {
    try {
      return iBiochemicalTestResultDao.getBiochemicalWorkListCount(microbioParamDto);
    } catch (Exception e) {
      logger.error("Exception", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }


  @Override
  @Transactional
  public Response getBiochemicalResultDetails(Integer tBiochemicalTestId, Integer orgId,
      Integer orgUnitId) throws ApplicationException {
    try {
      return iBiochemicalTestResultDao.getBiochemicalResultDetails(tBiochemicalTestId, orgId, orgUnitId);
    } catch (Exception e) {
      logger.error("Exception", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }




  @Override
  @Transactional
  public Response saveBiochemicalResult(TBiochemicalTestMasterDto tBiochemicalTestMasterDto)
      throws ApplicationException {
    try {
      Response<TBiochemicalTestMaster, Integer> tBiochemicalTestMasterRes =
          iBiochemicalTestResultDao.saveBiochemicalResult(tBiochemicalTestMasterDto);
      if (tBiochemicalTestMasterRes.getCode().equals(SUCCESS_200)) {
        LabSampleDetailsMasterDto labSampleDetailsMasterDto = new LabSampleDetailsMasterDto();
        labSampleDetailsMasterDto.setCreatedBy(tBiochemicalTestMasterDto.getCreatedBy());
        labSampleDetailsMasterDto.setLabSampleDtlsId(tBiochemicalTestMasterDto.getLabSampleDtlsId());
        labSampleDetailsMasterDto
            .setCreatedDate(new Date(tBiochemicalTestMasterDto.getCreatedDate()));
        if (tBiochemicalTestMasterDto.getIsCompleted().equals('Y')) {
          labSampleDetailsMasterDto.setSampleStatusId(BIOCHEMICAL_RESULT_ENTRY_DONE);
          Response<LabSampleDetailsMasterDto, Integer> sampleRes =
              iMicrobiologyService.updateLabSampleDetailsMaster(labSampleDetailsMasterDto);
          if (sampleRes.getCode().equals(SUCCESS_200)) {
            return new Response(SUCCESS, SUCCESS_200, "Biochemical Result Saved Successfully.", null,
                null);
          } else {
            return new Response(ERROR, ERR_500, "Failed To Save Biochemical Result.", null, null);
          }

        } else {
          labSampleDetailsMasterDto.setSampleStatusId(DURING_BIOCHEMICAL_RESULT_ENTRY);
          Response<LabSampleDetailsMasterDto, Integer> sampleRes =
              iMicrobiologyService.updateLabSampleDetailsMaster(labSampleDetailsMasterDto);
          if (sampleRes.getCode().equals(SUCCESS_200)) {
            return new Response(SUCCESS, SUCCESS_200, "Biochemical Result Saved Successfully.", null,
                null);
          } else {
            return new Response(ERROR, ERR_500, "Failed To Save Biochemical Result.", null, null);
          }
        }
      } else {
        return new Response(ERROR, ERR_500, "Failed To Save Biochemical Result.", null, null);
      }

    } catch (Exception e) {
      logger.error("Exception", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }

  
  }

  @Override
  @Transactional
  public Response updateBiochemicalResult(TBiochemicalTestMasterDto tBiochemicalTestMasterDto)
      throws ApplicationException {
    try {
      Response<TBiochemicalTestMaster, Integer> tBiochemicalTestMasterRes =
          iBiochemicalTestResultDao.updateBiochemicalResult(tBiochemicalTestMasterDto);
      if (tBiochemicalTestMasterRes.getCode().equals(SUCCESS_200)) {
        LabSampleDetailsMasterDto labSampleDetailsMasterDto = new LabSampleDetailsMasterDto();
        labSampleDetailsMasterDto.setLabSampleDtlsId(tBiochemicalTestMasterDto.getLabSampleDtlsId());
        labSampleDetailsMasterDto.setCreatedBy(tBiochemicalTestMasterDto.getCreatedBy());
        labSampleDetailsMasterDto
            .setCreatedDate(new Date(tBiochemicalTestMasterDto.getCreatedDate()));
        if (tBiochemicalTestMasterDto.getIsCompleted().equals('Y')) {
          labSampleDetailsMasterDto.setSampleStatusId(BIOCHEMICAL_RESULT_ENTRY_DONE);
          Response<LabSampleDetailsMasterDto, Integer> sampleRes =
              iMicrobiologyService.updateLabSampleDetailsMaster(labSampleDetailsMasterDto);
          if (sampleRes.getCode().equals(SUCCESS_200)) {
            return new Response(SUCCESS, SUCCESS_200, "Biochemical Result Saved Successfully.", null,
                null);
          } else {
            return new Response(ERROR, ERR_500, "Failed To ReStart Incubation.", null, null);

          }

        } else {
          labSampleDetailsMasterDto.setSampleStatusId(DURING_BIOCHEMICAL_RESULT_ENTRY);
          Response<LabSampleDetailsMasterDto, Integer> sampleRes =
              iMicrobiologyService.updateLabSampleDetailsMaster(labSampleDetailsMasterDto);
          if (sampleRes.getCode().equals(SUCCESS_200)) {
            return new Response(SUCCESS, SUCCESS_200, "Biochemical Result Saved Successfully.", null,
                null);
          } else {
            return new Response(ERROR, ERR_500, "Failed To Save Biochemical Result.", null, null);
          }
        }
      } else {
        return new Response(ERROR, ERR_500, "Failed To Save Biochemical Result.", null, null);
      }

    } catch (Exception e) {
      logger.error("Exception", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }



}
