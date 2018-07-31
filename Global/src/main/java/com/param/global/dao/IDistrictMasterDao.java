package com.param.global.dao;

import com.param.global.common.Response;
import com.param.global.dto.DistrictMasterDto;
import com.param.global.dto.StateMasterDto;
import com.param.global.model.DistrictMaster;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IDistrictMasterDao extends IGenericDao<DistrictMaster, Integer>{

	Response getDistrictByName(DistrictMasterDto districtMasterDto)throws ApplicationException;

	Response addDistrictMaster(DistrictMasterDto districtMasterDto)throws ApplicationException;

	Response getDistrictMasterList(DistrictMasterDto districtMasterDto)throws ApplicationException;

	Response getDistrictByNameNotId(DistrictMasterDto districtMasterDto)throws ApplicationException;

	Response updateDistrictMaster(DistrictMasterDto districtMasterDto) throws ApplicationException;

	Response getDistrictById(DistrictMasterDto districtMasterDto)throws ApplicationException;

	Response updateDistrictStatus(DistrictMasterDto districtMasterDto)throws ApplicationException;

	Response getActiveDistrictList() throws ApplicationException;

	Response getActiveStateListByCountryId(StateMasterDto stateMasterDto)throws ApplicationException;

	Response getCount(DistrictMasterDto districtMasterDto) throws ApplicationException;

}
