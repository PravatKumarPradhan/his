package com.param.lis.histopathology.transaction.service;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.param.entity.lis.transaction.LabSampleDetailsMaster;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.ITransactionConstants;
import com.param.lis.global.common.Response;
import com.param.lis.histopathology.transaction.dao.ISlideCreationDao;
import com.param.lis.histopathology.transaction.dao.ISpecimanReceiptDao;
import com.param.lis.histopathology.transaction.dto.HistoParamDto;
import com.param.lis.histopathology.transaction.dto.TGrossMasterDto;
import com.param.lis.histopathology.transaction.dto.TSubSpecimanMasterDto;
import in.param.exception.ApplicationException;

@Service
@SuppressWarnings({"rawtypes", "unchecked"})
public class SlideCreationServiceImpl
    implements ISlideCreationService, ICommonConstants, ITransactionConstants, IError {

  final  Logger logger = Logger.getLogger(SlideCreationServiceImpl.class);

  @Autowired
  private ISlideCreationDao iSlideCreationDao;

  @Autowired
  private ISpecimanReceiptDao iSpecimanReceiptDao;

  @Override
  @Transactional
  public Response getSlideCreationWorkList(HistoParamDto histoParamDto)
      throws ApplicationException {
    try {
      return iSlideCreationDao.getSlideCreationWorkList(histoParamDto);
    } catch (Exception e) {
      logger.error("Exception", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  @Transactional
  public Response getSlideCreationWorkListCount(HistoParamDto histoParamDto)
      throws ApplicationException {
    try {
      return iSlideCreationDao.getSlideCreationWorkListCount(histoParamDto);
    } catch (Exception e) {
      logger.error("Exception", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  @Transactional
  public Response getCreatedBlockList(TSubSpecimanMasterDto tSubSpecimanMasterDto)
      throws ApplicationException {
    try {
      return iSlideCreationDao.getCreatedBlockList(tSubSpecimanMasterDto);
    } catch (Exception e) {
      logger.error("Exception", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  @Transactional
  public Response createSlides(List<TGrossMasterDto> listTGrossMasterDto)
      throws ApplicationException {
    try {
      Response grossRes = iSlideCreationDao.createSlides(listTGrossMasterDto);
      if (grossRes.getCode().equals(SUCCESS_200) && grossRes.getObject() != null) {
        LabSampleDetailsMaster labSampleDetailsMaster =
            (LabSampleDetailsMaster) grossRes.getObject();
        labSampleDetailsMaster.setSampleStatusId(SLIDE_CREATED);
        Response labSampleDtlsRes =
            iSpecimanReceiptDao.updateLabSampleDetailsMaster(labSampleDetailsMaster);
        if (labSampleDtlsRes.getCode().equals(SUCCESS_200)) {
          return new Response(SUCCESS, SUCCESS_200, SLIDES_CREATE_SUCCESS, null, null);
        } else {
          return new Response(ERROR, ERR_500, SLIDES_CREATE_FAIL, null, null);
        }
      } else {
        return new Response(ERROR, ERR_500, SLIDES_CREATE_FAIL, null, null);
      }

    } catch (Exception e) {
      logger.error("Exception", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }

  }
}
