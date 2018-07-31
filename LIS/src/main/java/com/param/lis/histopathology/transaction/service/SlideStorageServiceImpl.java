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
import com.param.lis.histopathology.transaction.dao.ISlideStorageDao;
import com.param.lis.histopathology.transaction.dto.HistoParamDto;
import com.param.lis.histopathology.transaction.dto.TSlideStorageMasterDto;
import in.param.exception.ApplicationException;

@Service
@SuppressWarnings({"rawtypes", "unchecked"})
public class SlideStorageServiceImpl  implements ISlideStorageService, ICommonConstants, ITransactionConstants, IError{

  final  Logger logger = Logger.getLogger(SlideStorageServiceImpl.class);
  
  @Autowired
  private ISlideStorageDao iSlideStorageDao;

  @Override
  @Transactional
  public Response getSlidesForStorage(HistoParamDto histoParamDto) 
    throws ApplicationException {
      try {
        return iSlideStorageDao.getSlidesForStorage(histoParamDto);
      } catch (Exception e) {
        logger.error("Exception", e);
        return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
      }
  }

  @Override
  @Transactional
  public Response getSlidesForStorageCount(HistoParamDto histoParamDto)
    throws ApplicationException {
      try {
        return iSlideStorageDao.getSlidesForStorageCount(histoParamDto);
      } catch (Exception e) {
        logger.error("Exception", e);
        return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
      }
  }

  @Override
  @Transactional
  public Response saveSlideStorageDetails(List<TSlideStorageMasterDto> tSlideStorageMasterDto )
      throws ApplicationException {
    try {
      return iSlideStorageDao.saveSlideStorageDetails(tSlideStorageMasterDto);
    } catch (Exception e) {
      logger.error("Exception", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  @Transactional
  public Response getStoredSlideDetails(Integer rackId, Integer shelfId, Integer orgId,Integer orgUnitId)
      throws ApplicationException {
    try {
      return iSlideStorageDao.getStoredSlideDetails(rackId,shelfId,orgId,orgUnitId);
    } catch (Exception e) {
      logger.error("Exception", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  @Transactional
  public Response getStoredSlideDetails(HistoParamDto histoParamDto) 
    throws ApplicationException {
      try {
        return iSlideStorageDao.getStoredSlideDetails(histoParamDto);
      } catch (Exception e) {
        logger.error("Exception", e);
        return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
      }
  }

  @Override
  @Transactional
  public Response getStoredSlideDetailsCount(HistoParamDto histoParamDto)
      throws ApplicationException {
    try {
      return iSlideStorageDao.getStoredSlideDetailsCount(histoParamDto);
    } catch (Exception e) {
      logger.error("Exception", e);
      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }
  
  
}
