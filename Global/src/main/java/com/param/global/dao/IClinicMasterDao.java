package com.param.global.dao;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;

import com.param.global.common.Response;
import com.param.global.dto.ClinicMasterDto;
import com.param.global.model.ClinicMaster;

@SuppressWarnings("rawtypes")
public interface IClinicMasterDao  extends IGenericDao<ClinicMaster, Integer>{

	public Response getListOfClinicMasterByDoctorId(ClinicMasterDto clinicMasterDto)throws ApplicationException;
	public Response saveClinicMaster(ClinicMasterDto clinicMasterDto)throws ApplicationException;
	public Response getClinicMasterById(ClinicMasterDto clinicMasterDto)throws ApplicationException;
	public Response updateClinicMaster(ClinicMasterDto clinicMasterDto)throws ApplicationException;
	public Response updateClinicMasterStatus(Integer clinicMasterId , Character status)throws ApplicationException;
	public Response getClinicMasterByName(ClinicMasterDto clinicMasterDto)throws ApplicationException;
	public Response getClinicMasterByNameAndNotById(ClinicMasterDto clinicMasterDto)throws ApplicationException;
	public Response getClinicMasterListByOrgANdUnitId(ClinicMasterDto clinicMasterDto)throws ApplicationException;
	public Response getClinicMasterCountByOrgAndUnit(int organizationId, int unitId)throws ApplicationException;
	
}
