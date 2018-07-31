package com.param.global.service;

import javax.transaction.Transactional;

import com.param.global.common.Response;
import com.param.global.dto.AreaMasterDto;
import com.param.global.dto.CityMasterDto;

import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")

public interface IAreaMasterService
{

	@Transactional
	Response addAreaMaster(AreaMasterDto areaMasterDto)throws ApplicationException;

	@Transactional
	Response getAreaMasterList(AreaMasterDto areaMasterDto)throws ApplicationException;

	@Transactional
	Response updateAreaMaster(AreaMasterDto areaMasterDto)throws ApplicationException;

	@Transactional
	Response getAreaById(AreaMasterDto areaMasterDto)throws ApplicationException;

	@Transactional
	Response updateStatusForArea(AreaMasterDto areaMasterDto)throws ApplicationException;

	@Transactional
	Response getActiveAreaList()throws ApplicationException;

	@Transactional
	Response getActiveCityListByDistrictId(CityMasterDto cityMasterDto) throws ApplicationException;

	@Transactional
	Response getAreaCount(AreaMasterDto areaMasterDto) throws ApplicationException;
	
	
}
