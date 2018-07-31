package com.param.global.dao;

import com.param.global.common.Response;
import com.param.global.dto.CountryMasterDto;
import com.param.global.model.CountryMaster;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface ICountryMasterDao extends IGenericDao<CountryMaster, Integer>{

	
	Response getCountryByName(CountryMasterDto countryMasterDto)throws ApplicationException;

	Response addCountryMaster(CountryMasterDto countryMasterDto)throws ApplicationException;

	Response getCountryMasterList(CountryMasterDto countryMasterDto)throws ApplicationException;

	Response getCountryByID(CountryMasterDto countryMasterDto)throws ApplicationException;

	Response updateCountryStatus(CountryMasterDto countryMasterDto)throws ApplicationException;

	Response updateCountryMaster(CountryMasterDto countryMasterDto)throws ApplicationException;

	Response getCountryByNameNotId(CountryMasterDto countryMasterDto)throws ApplicationException;

	Response getActiveCountryList()throws ApplicationException;

	Response getCount(CountryMasterDto countryMasterDto) throws ApplicationException;
	
	public Response getCountryMasterList()throws ApplicationException;

}
