package com.param.global.service;

import in.param.exception.ApplicationException;

import com.param.global.common.Response;
import com.param.global.dto.ClinicMasterDto;

@SuppressWarnings("rawtypes")
public interface IClinicMasterService {

	
	public Response getListOfClinicMasterByDoctorId(ClinicMasterDto clinicMasterDto)throws ApplicationException;
	public Response saveClinicMaster(ClinicMasterDto clinicMasterDto)throws ApplicationException;
	public Response getClinicMasterById(ClinicMasterDto clinicMasterDto)throws ApplicationException;
	public Response updateClinicMaster(ClinicMasterDto clinicMasterDto)throws ApplicationException;
	public Response updateClinicMasterStatus(Integer clinicMasterId,Character status) throws ApplicationException;
	public Response getClinicMasterListByOrgANdUnitId(ClinicMasterDto clinicMasterDto)throws ApplicationException;
	public Response getClinicMasterCountByOrgAndUnit(int organizationId, int unitId)throws ApplicationException;
}
