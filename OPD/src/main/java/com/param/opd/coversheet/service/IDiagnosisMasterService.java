package com.param.opd.coversheet.service;

import in.param.exception.ApplicationException;

import com.param.global.common.Response;
import com.param.opd.coversheet.dto.DiagnosisMasterDto;

public interface IDiagnosisMasterService {

	public Response getListOfDiagnosisMaster(DiagnosisMasterDto diagnosisMasterDto)throws ApplicationException;
}
