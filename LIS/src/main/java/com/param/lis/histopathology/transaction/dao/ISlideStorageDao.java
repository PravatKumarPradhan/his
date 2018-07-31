package com.param.lis.histopathology.transaction.dao;

import java.util.List;

import com.param.lis.global.common.Response;
import com.param.lis.histopathology.transaction.dto.HistoParamDto;
import com.param.lis.histopathology.transaction.dto.TSlideStorageMasterDto;
import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface ISlideStorageDao {
  
  public Response getSlidesForStorage(HistoParamDto histoParamDto) throws ApplicationException;
  public Response getSlidesForStorageCount(HistoParamDto histoParamDto) throws ApplicationException;
  public Response saveSlideStorageDetails(List<TSlideStorageMasterDto> TSlideStorageMasterDto ) throws ApplicationException;
  public Response getStoredSlideDetails( Integer rackId, Integer shelfId, Integer orgId, Integer orgUnitId) throws ApplicationException;
  public Response getStoredSlideDetails(HistoParamDto histoParamDto) throws ApplicationException;
  public Response getStoredSlideDetailsCount(HistoParamDto histoParamDto) throws ApplicationException;

}
