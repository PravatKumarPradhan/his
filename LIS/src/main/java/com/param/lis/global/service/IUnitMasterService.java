package com.param.lis.global.service;

import in.param.exception.ApplicationException;

import com.param.lis.global.common.Response;
import com.param.lis.global.dto.BlockMasterDto;
import com.param.lis.global.dto.MediaMasterDto;
import com.param.lis.global.dto.UnitMasterDto;

@SuppressWarnings("rawtypes")
public interface IUnitMasterService {
	public Response addUnitMaster(UnitMasterDto unitMasterDto)throws ApplicationException;
	public Response getUnitMasterById(Integer orgId, Integer unitId) throws ApplicationException;

	public Response updateUnitMaster(UnitMasterDto unitMasterDto) throws ApplicationException;
	
	public Response ActivateInactivateUnitMaster(Integer orgId, Integer unitId, Character unitStatus) throws ApplicationException;

	public Response listUnitMaster(Integer orgId, Integer offset, Integer recordPerPage) throws ApplicationException;

	public Response getToTalUnitMasterRecord(Integer orgId) throws ApplicationException;
}
