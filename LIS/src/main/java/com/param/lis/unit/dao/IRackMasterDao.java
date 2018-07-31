package com.param.lis.unit.dao;

import com.param.entity.lis.unit.RackMaster;
import com.param.lis.global.common.Response;
import com.param.lis.unit.dto.RackMasterDto;
import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface IRackMasterDao extends IGenericDao<RackMaster, Integer>
{
	public Response addRackMaster(RackMasterDto rackMasterDto) throws ApplicationException;

	public Response getRackMasterById(Integer orgId,Integer unitId, Integer departmentId) throws ApplicationException;

	public Response updateRackMaster(RackMasterDto rackMasterDto) throws ApplicationException;

	public Response ActivateInactivateRackMaster(Integer orgId, Integer departmentId, Character departmentStatus)
			throws ApplicationException;

	public Response listRackMaster(Integer orgId, Integer orgUnitId,Integer offset, Integer recordPerPage) throws ApplicationException;

	public Response getToTalRackMasterRecord(Integer orgId,Integer orgUnitId) throws ApplicationException;

}
