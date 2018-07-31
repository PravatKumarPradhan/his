package com.param.lis.unit.service;

import com.param.lis.global.common.Response;
import com.param.lis.unit.dto.StorageLocationMasterDto;

import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface IStorageLocationMasterService
{
	public Response addStorageLocationMaster(StorageLocationMasterDto storageLocationMasterDto) throws ApplicationException;

	public Response getStorageLocationMasterById(Integer orgId,Integer unitId, Integer storageLocationtId) throws ApplicationException;

	public Response updateStorageLocationMaster(StorageLocationMasterDto storageLocationMasterDto) throws ApplicationException;
	
	public Response ActivateInactivateStorageLocationMaster(Integer orgId, Integer storageLocationtId, Character storageLocationStatus) throws ApplicationException;

	public Response listStorageLocationMaster(Integer orgId, Integer offset, Integer recordPerPage) throws ApplicationException;

	public Response getToTalStorageLocationMasterRecord(Integer orgId) throws ApplicationException;

}
