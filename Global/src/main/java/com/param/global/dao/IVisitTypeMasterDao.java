package com.param.global.dao;

import com.param.global.common.Response;
import com.param.global.model.VisitTypeMaster;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IVisitTypeMasterDao extends IGenericDao<VisitTypeMaster, Integer>{
	public Response getActiveVisitTypeList(Integer unitId, Integer orgId)throws ApplicationException;
}
