package com.param.lis.histopathology.transaction.service;

import com.param.lis.global.common.Response;
import com.param.lis.histopathology.transaction.dto.HistoParamDto;
import com.param.lis.histopathology.transaction.dto.TRestainingRequestMasterDto;
import com.param.lis.histopathology.transaction.dto.TSubSpecimanMasterDto;
import in.param.exception.ApplicationException;

@SuppressWarnings({"rawtypes"})
public interface IRestainRequestService {

  public Response getSlidesDetails(TSubSpecimanMasterDto tSubSpecimanMasterDto)
      throws ApplicationException;

  public Response saveRestainRequest(TRestainingRequestMasterDto tRestainingRequestMasterDto)
      throws ApplicationException;

  public Response getRestainRequestWorkList(HistoParamDto histoParamDto)
      throws ApplicationException;

  public Response getRestainRequestWorkListCount(HistoParamDto histoParamDto)
      throws ApplicationException;

  public Response getRestainRequestWorkListDetails(Integer tRestainingReqId, Integer orgId,
      Integer orgUnitId) throws ApplicationException;

  public Response getRestainRequestWorkListSlides(Integer tRestainingDetailId,Integer tRestainingSubDetailId,Character isNew, Integer orgId,
      Integer orgUnitId) throws ApplicationException; 
  
  public Response getMaxSlideNumber( Integer orgId,
      Integer orgUnitId,Integer tBlockId) throws ApplicationException; 
  
}
