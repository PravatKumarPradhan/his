package com.param.global.dao;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;

import com.param.global.common.Response;
import com.param.global.dto.MasterOfSytemDto;
import com.param.global.dto.SystemMasterDto;
import com.param.global.model.SystemMaster;

@SuppressWarnings("rawtypes")
public interface ISystemMasterDao extends IGenericDao<SystemMaster, Integer>{
	
	public Response getListOfSystemMaster(SystemMasterDto systemMasterDto)throws ApplicationException;
	
	/*CRUD FOR SYSTEM MASTER*/	
	public Response saveSystemMaster(MasterOfSytemDto masterOfSytemDto)throws ApplicationException;
	public Response getSystemMaster(MasterOfSytemDto masterOfSytemDto)throws ApplicationException;
	public Response updateSystemMasterStatus(MasterOfSytemDto masterOfSytemDto)throws ApplicationException;
	
	// added by dinesh jagatap on 21-04-18 Start
	public Response getListOfSystemMasterById(MasterOfSytemDto masterOfSytemDto) throws ApplicationException;

	public Response updateSystemMaster(MasterOfSytemDto masterOfSytemDto) throws ApplicationException;

	public Response getListOfSystemMasterByType(MasterOfSytemDto masterOfSytemDto) throws ApplicationException;
}
