package com.param.global.dao;

import com.param.adt.master.global.model.UnitMaster;
import com.param.global.common.Response;
import com.param.global.org.dto.UnitMasterDto;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface IGlobalUnitMasterDao extends IGenericDao<UnitMaster, Integer>{

	public Response getUnitMasterForSyncById(Integer unitId)throws ApplicationException;
	public Response saveUnitMaster(UnitMasterDto unitMasterDto)throws ApplicationException;
	public Response updateUnitMaster(UnitMasterDto unitMasterDto)throws ApplicationException;
	public Response getUnitById(Integer unitId)throws ApplicationException;
	public Response getUnitByName(UnitMasterDto unitMasterDto)throws ApplicationException;
	public Response getUnitByNameAndNotById(UnitMasterDto unitMasterDto)throws ApplicationException;
	public Response updateUnitStatusById(UnitMasterDto unitMasterDto)throws ApplicationException;
	public Response getAllUnitList(UnitMasterDto unitMasterDto)throws ApplicationException;
	public Response getUnitTotalCount()throws ApplicationException;
	
	
	
}
