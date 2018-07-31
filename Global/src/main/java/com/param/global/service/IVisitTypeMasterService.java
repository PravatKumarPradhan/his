package com.param.global.service;

import com.param.global.common.Response;

import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IVisitTypeMasterService {
	public Response getActiveVisitTypeList(Integer unitId, Integer orgId)throws ApplicationException;
}
