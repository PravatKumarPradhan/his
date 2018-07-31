package com.param.lis.unit.dao;

import com.param.entity.lis.unit.StorageLocationMaster;
import com.param.lis.global.common.Response;
import com.param.lis.unit.dto.StorageLocationMasterDto;
import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface IStorageLocationMasterDao extends IGenericDao<StorageLocationMaster, Integer>
{
	public Response addStorageLocationMaster(StorageLocationMasterDto storageLocationMasterDto) throws ApplicationException;

	public Response getStorageLocationMasterById(Integer orgId,Integer unitId, Integer storageLocationId) throws ApplicationException;

	public Response updateStorageLocationMaster(StorageLocationMasterDto storageLocationMasterDto) throws ApplicationException;

	public Response ActivateInactivateStorageLocationMaster(Integer orgId, Integer storageLocationId, Character storageLocationStatus)
			throws ApplicationException;

	public Response listStorageLocationMaster(Integer orgId, Integer offset, Integer recordPerPage) throws ApplicationException;

	public Response getToTalStorageLocationMasterRecord(Integer orgId) throws ApplicationException;

}
