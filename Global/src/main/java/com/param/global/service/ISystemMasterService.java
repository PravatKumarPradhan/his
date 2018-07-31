package com.param.global.service;

import in.param.exception.ApplicationException;

import com.param.global.common.Response;
import com.param.global.dto.MasterOfSytemDto;
import com.param.global.dto.SystemMasterDto;

@SuppressWarnings("rawtypes")
public interface ISystemMasterService {

	public Response getListOfSystemMaster(SystemMasterDto systemMasterDto)throws ApplicationException;
	
	/*CRUD FOR SYSTEM MASTER*/	
	
	public Response saveSystemMaster(MasterOfSytemDto masterOfSytemDto)throws ApplicationException;
	
	public Response getSystemMaster(MasterOfSytemDto masterOfSytemDto)throws ApplicationException;
	
	public Response updateSystemMasterStatus(MasterOfSytemDto masterOfSytemDto)throws ApplicationException;

	public Response getListOfSystemMasterById(MasterOfSytemDto masterOfSytemDto) throws ApplicationException;

	public Response updateSystemMaster(MasterOfSytemDto masterOfSytemDto) throws ApplicationException;

	public Response getListOfSystemMasterByType(MasterOfSytemDto masterOfSytemDto) throws ApplicationException;
}
