package com.param.global.dao;

import com.param.global.common.Response;
import com.param.global.dto.CityMasterDto;
import com.param.global.dto.DistrictMasterDto;
import com.param.global.model.CityMaster;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface ICityMasterDao extends IGenericDao<CityMaster, Integer> 
{

	
	Response getCityByName(CityMasterDto cityMasterDto) throws ApplicationException;

	Response addCityMaster(CityMasterDto cityMasterDto) throws ApplicationException;

	Response getCityByNameNotId(CityMasterDto citytMasterDto)throws ApplicationException;

	Response updateCityMaster(CityMasterDto citytMasterDto) throws ApplicationException;

	Response getCityByID(CityMasterDto cityMasterDto) throws ApplicationException;

	Response getCityMasterList(CityMasterDto cityMasterDto) throws ApplicationException;

	Response getActiveCityList()throws ApplicationException;

	Response getActiveDistrictListByStateId(DistrictMasterDto districtMasterDto) throws ApplicationException;

	Response updateCityStatus(CityMasterDto cityMasterDto)throws ApplicationException;

	Response getCount(CityMasterDto cityMasterDto) throws ApplicationException;
	
	Response getCityByStateId(CityMasterDto cityMasterDto) throws ApplicationException;

}
