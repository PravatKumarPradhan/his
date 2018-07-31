package com.param.global.service;

import in.param.exception.ApplicationException;

import com.param.global.common.Response;
import com.param.global.dto.SurgeryMasterDto;

@SuppressWarnings("rawtypes")
public interface ISurgeryMasterService {

	public Response getListOfSurgeryMaster(SurgeryMasterDto surgeryMasterDto) throws ApplicationException;

	public Response getSurgeryMasterList(SurgeryMasterDto surgeryMasterDto) throws ApplicationException;

	public Response saveSurgeryMaster(SurgeryMasterDto surgeryMasterDto) throws ApplicationException;

	public Response getSurgeryMasterById(SurgeryMasterDto surgeryMasterDto) throws ApplicationException;

	public Response updateSurgeryMaster(SurgeryMasterDto surgeryMasterDto) throws ApplicationException;

	public Response updateStatusForSurgeryMaster(SurgeryMasterDto surgeryMasterDto) throws ApplicationException;
}
