package com.param.lis.histopathology.transaction.dao;

import com.param.entity.lis.histo.TRestainingRequestDetailsMaster;
import com.param.lis.global.common.Response;
import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;

@SuppressWarnings({"rawtypes"})
public interface IRestainingRequestDetailsDao  extends IGenericDao<TRestainingRequestDetailsMaster, Integer>{
  public Response saveRestainingRequestDetails(TRestainingRequestDetailsMaster tRestainingRequestDetailsMaster) throws ApplicationException;
  public Integer updateRestainSlideCreated(Integer tRestainingSubDetailId,Integer orgId,Integer orgUnitId) throws ApplicationException;
}
