package com.param.global.service;

import in.param.exception.ApplicationException;

import com.param.global.common.Response;
import com.param.global.dto.SeverityMasterDto;

public interface ISeverityMasterService {

	public Response getListOfSeverityMaster(SeverityMasterDto severityMasterDto)throws ApplicationException;
}
