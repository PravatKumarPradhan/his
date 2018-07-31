package com.param.global.dao;

import com.param.global.common.Response;
import com.param.global.dto.AreaMasterDto;
import com.param.global.dto.CityMasterDto;
import com.param.global.model.AreaMaster;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IAreaMasterDao extends IGenericDao<AreaMaster, Integer>{

	
	Response getAreaByName(AreaMasterDto areaMasterDto)throws ApplicationException;

	Response addAreaMaster(AreaMasterDto areaMasterDto)throws ApplicationException;

	Response getAreaMasterList(AreaMasterDto areaMasterDto)throws ApplicationException;

	Response getAreaByNameNotId(AreaMasterDto areaMasterDto)throws ApplicationException;

	Response updateAreaMaster(AreaMasterDto areaMasterDto)throws ApplicationException;

	Response getAreaById(AreaMasterDto areaMasterDto)throws ApplicationException;

	Response updateAreaStatus(AreaMasterDto areaMasterDto)throws ApplicationException;

	Response getActiveAreaList() throws ApplicationException;

	Response getActiveCityListByDistrictId(CityMasterDto cityMasterDto)throws ApplicationException;

	Response getCount(AreaMasterDto areaMasterDto) throws ApplicationException;

}
