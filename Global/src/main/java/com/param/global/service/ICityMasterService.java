package com.param.global.service;

import javax.transaction.Transactional;

import com.param.global.common.Response;
import com.param.global.dto.CityMasterDto;
import com.param.global.dto.DistrictMasterDto;

import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface ICityMasterService 
{
	@Transactional
	Response addCityMaster(CityMasterDto cityMasterDto) throws ApplicationException;

	@Transactional
	Response getCityMasterList(CityMasterDto cityMasterDto) throws ApplicationException;

	@Transactional
	Response updateCityMaster(CityMasterDto citytMasterDto) throws ApplicationException;

	@Transactional
	Response getCityById(CityMasterDto cityMasterDto) throws ApplicationException;

	@Transactional
	Response updateStatusForDistrict(CityMasterDto cityMasterDto) throws ApplicationException;

	@Transactional
	Response getActiveCityList() throws ApplicationException;

	@Transactional
	Response getActiveDistrictListByStateId(DistrictMasterDto districtMasterDto) throws ApplicationException;

	@Transactional
	Response getCityCount(CityMasterDto cityMasterDto) throws ApplicationException;

	@Transactional
	public Response getCityByStateIs(CityMasterDto cityMasterDto)throws ApplicationException;

}
