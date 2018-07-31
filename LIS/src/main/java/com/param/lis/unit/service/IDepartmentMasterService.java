package com.param.lis.unit.service;

import com.param.lis.global.common.Response;
import com.param.lis.unit.dto.DepartmentMasterDto;

import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface IDepartmentMasterService
{
	public Response addDepartmentMaster(DepartmentMasterDto cMasterDto) throws ApplicationException;

	public Response getDepartmentMasterById(Integer orgId,Integer unitId, Integer departmentId) throws ApplicationException;

	public Response updateDepartmentMaster(DepartmentMasterDto departmentMasterDto) throws ApplicationException;
	
	public Response ActivateInactivateDepartmentMaster(Integer orgId, Integer departmentId, Character departmentStatus) throws ApplicationException;

	public Response listDepartmentMaster(Integer orgId, Integer offset, Integer recordPerPage) throws ApplicationException;

	public Response getToTalDepartmentMasterRecord(Integer orgId) throws ApplicationException;

}
