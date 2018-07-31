package com.param.global.service;

import javax.transaction.Transactional;

import com.param.global.common.Response;
import com.param.global.dto.DistrictMasterDto;
import com.param.global.dto.StateMasterDto;

import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IDistrictMasterService {

	@Transactional
	Response addDistrictMaster(DistrictMasterDto districtMasterDto) throws ApplicationException;

	@Transactional
	Response getDistrictMasterList(DistrictMasterDto districtMasterDto) throws ApplicationException;

	@Transactional
	Response updateDistrictMaster(DistrictMasterDto districtMasterDto)throws ApplicationException;

	@Transactional
	Response getDistrictById(DistrictMasterDto districtMasterDto)throws ApplicationException;

	@Transactional
	Response updateStatusForDistrict(DistrictMasterDto districtMasterDto)throws ApplicationException;

	@Transactional
	Response getActiveDistrictList()throws ApplicationException;

	@Transactional
	Response getActiveStateListByCountryId(StateMasterDto stateMasterDto) throws ApplicationException;

	@Transactional
	Response getDistrictCount(DistrictMasterDto districtMasterDto) throws ApplicationException;
	
	

}
