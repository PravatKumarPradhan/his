package com.param.global.service;

import in.param.exception.ApplicationException;

import com.param.global.common.Response;
import com.param.global.dto.NursingStationMasterDto;

public interface INursingStationService {

	public Response getListOfNursingStationMaster(NursingStationMasterDto nursingStationMasterDto)throws ApplicationException;
	public Response getListOfNursingStationMasterByClinicId(NursingStationMasterDto nursingStationMasterDto)throws ApplicationException;
}
