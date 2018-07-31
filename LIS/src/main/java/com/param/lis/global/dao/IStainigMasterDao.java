package com.param.lis.global.dao;

import com.param.entity.lis.global.StainigMaster;
import com.param.lis.global.common.Response;
import com.param.lis.global.dto.StainigMasterDto;
import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface IStainigMasterDao extends IGenericDao<StainigMaster, Integer>{
	
	
	public Response addStainigMaster(StainigMasterDto stainigMasterDto)throws ApplicationException;
	public Response checkStainigMaster(StainigMasterDto stainigMasterDto) throws ApplicationException;
	public Response updateStainigMaster(StainigMasterDto stainigMasterDto) throws ApplicationException;
	public Response updateCheckStainigMasterCodeAvaiable(StainigMasterDto stainigMasterDto) throws ApplicationException;
	public Response ActivateInactivateStainigMaster(Integer orgId, Integer  stainigMasterDto, Character  stainigMasterStatus) throws ApplicationException;
	public Response listStainigMaster(Integer orgId, Integer offset, Integer recordPerPage) throws ApplicationException;
	public Response getToTalStainigMaster(Integer orgId) throws ApplicationException;
	public Response getStainigMasterById(Integer orgId, Integer  stainigMasterId)
			throws ApplicationException;
}
