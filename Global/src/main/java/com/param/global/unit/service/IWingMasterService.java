package com.param.global.unit.service;

import in.param.exception.ApplicationException;

import com.param.global.common.Response;
import com.param.global.unit.dto.WingMasterDto;

@SuppressWarnings("rawtypes")
public interface IWingMasterService {

	
	public Response getWingMasterListByOrgAndUnit(WingMasterDto wingMasterDto)throws ApplicationException;
}
