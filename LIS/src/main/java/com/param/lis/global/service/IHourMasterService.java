package com.param.lis.global.service;

import com.param.lis.global.common.Response;

import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface IHourMasterService {
	
	public Response getListHourMaster() throws ApplicationException;
}
