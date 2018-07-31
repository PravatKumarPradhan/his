package com.param.lis.histopathology.transaction.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.param.entity.lis.histo.TTemplateResult;
import com.param.entity.lis.transaction.LabSampleDetailsMaster;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.ITransactionConstants;
import com.param.lis.global.common.Response;
import com.param.lis.histopathology.transaction.dao.ITTemplateResultDao;
import com.param.lis.histopathology.transaction.dto.TTemplateResultDto;
import com.param.lis.microbiology.transaction.service.IMicrobiologyService;
import com.param.lis.transaction.dto.LabSampleDetailsMasterDto;
import in.param.exception.ApplicationException;

@Service
@SuppressWarnings({"rawtypes", "unchecked"})
public class TTemplateResultServiceImpl
    implements ITTemplateResultService, ICommonConstants, ITransactionConstants, IError {

  final Logger logger = Logger.getLogger(TTemplateResultServiceImpl.class);

  @Autowired
  private ITTemplateResultDao iTTemplateResultDao;

  @Autowired
  IMicrobiologyService iMicrobiologyService;


  @Override
  @Transactional
  public Response saveTemplateResult(TTemplateResultDto tTemplateResultDto) throws ApplicationException {
    try {
      Response<LabSampleDetailsMaster, Integer> sampleStatusRes = null;
      Response<TTemplateResult, Integer> tTemplateReslt =
          iTTemplateResultDao.saveTemplateResult(tTemplateResultDto);
      if (tTemplateReslt.getCode().equals(SUCCESS_200)) {

        if (tTemplateResultDto.getSampleStatusId()!= 0) {

          LabSampleDetailsMasterDto labSampleDetailsMasterDto =
              new LabSampleDetailsMasterDto();
          labSampleDetailsMasterDto.setCreatedBy(tTemplateResultDto.getCreatedBy());
          labSampleDetailsMasterDto.setSampleStatusId(tTemplateResultDto.getSampleStatusId());
          labSampleDetailsMasterDto.setLabSampleDtlsId(tTemplateResultDto.getLabSampleDtlsId());

          sampleStatusRes =
              iMicrobiologyService.updateLabSampleDetailsMaster(labSampleDetailsMasterDto);
          if (sampleStatusRes.getCode().equals(SUCCESS_200)) {
            return new Response(SUCCESS, SUCCESS_200, "Out Source Details Added Successfully.",
                null, null);
          }

        } else {
          return new Response(SUCCESS, SUCCESS_200, "Out Source Details Added Successfully.", null,
              null);
        }
        return new Response(ERROR, ERR_500, "Failed to Added Template Result.", null, null);
      } else {

        return new Response(ERROR, ERR_500, "Failed to Added Template Result.", null, null);
      }

    } catch (Exception e) {
      logger.error("Exception", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }



  @Override
  @Transactional
  public Response getHistopathReport(Integer templateResId, Integer orgId, Integer orgUnitId)
      throws ApplicationException {
    try {
      return iTTemplateResultDao.getHistopathReport(templateResId, orgId, orgUnitId);
    } catch (Exception e) {
      e.printStackTrace();
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

}
