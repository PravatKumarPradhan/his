package com.param.lis.histopathology.transaction.service;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.ITransactionConstants;
import com.param.lis.global.common.Response;
import com.param.lis.histopathology.transaction.dao.IFrozenSectionReqDao;
import com.param.lis.histopathology.transaction.dto.HistoParamDto;
import com.param.lis.histopathology.transaction.dto.TFrozenSectionReqMasterDto;
import in.param.exception.ApplicationException;

@Service
@SuppressWarnings({"rawtypes", "unchecked"})
public class FrozenSectionReqServiceImpl implements IFrozenSectionReqService, ICommonConstants, ITransactionConstants, IError {
  
  final  Logger logger = Logger.getLogger(SlideCreationServiceImpl.class);
  
  @Autowired
  private IFrozenSectionReqDao iFrozenSectionReqDao;

  @Override
  @Transactional
  public Response saveFrozerSectionRequest(TFrozenSectionReqMasterDto tFrozenSectionReqMasterDto)
      throws ApplicationException {
    try {
      return iFrozenSectionReqDao.saveFrozerSectionRequest(tFrozenSectionReqMasterDto);
    } catch (Exception e) {
      logger.error("Exception", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  @Transactional
  public Response getFrozenSectionReqList(HistoParamDto histoParamDto) throws ApplicationException {
    try {
      return iFrozenSectionReqDao.getFrozenSectionReqList(histoParamDto);
    } catch (Exception e) {
      logger.error("Exception", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  @Transactional
  public Response getFrozenSectionReqListCount(HistoParamDto histoParamDto)
      throws ApplicationException {
    try {
      return iFrozenSectionReqDao.getFrozenSectionReqListCount(histoParamDto);
    } catch (Exception e) {
      logger.error("Exception", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  @Transactional
  public Response acceptFrozensectionReq(
      List<TFrozenSectionReqMasterDto> listTFrozenSectionReqMasterDto) throws ApplicationException {
    try {
      return iFrozenSectionReqDao.acceptFrozensectionReq(listTFrozenSectionReqMasterDto);
    } catch (Exception e) {
      logger.error("Exception", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

}
