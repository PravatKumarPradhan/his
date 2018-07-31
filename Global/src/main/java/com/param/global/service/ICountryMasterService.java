package com.param.global.service;

import javax.transaction.Transactional;

import com.param.global.common.Response;
import com.param.global.dto.CountryMasterDto;

import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface ICountryMasterService {
	@Transactional
	Response addCountryMaster(CountryMasterDto countryMasterDto) throws ApplicationException;

	@Transactional
	Response getCountryMasterList(CountryMasterDto countryMasterDto) throws ApplicationException;

	@Transactional
	Response updateCountryMaster(CountryMasterDto countryMasterDto) throws ApplicationException;

	@Transactional
	Response getCountryById(CountryMasterDto countryMasterDto) throws ApplicationException;

	@Transactional
	Response updateStatusForCountry(CountryMasterDto countryMasterDto) throws ApplicationException;

	@Transactional
	Response getActiveCountryList() throws ApplicationException;

	@Transactional
	Response getCountryCount(CountryMasterDto countryMasterDto)throws ApplicationException;
	
	@Transactional
	public Response getCountryMasterList() throws ApplicationException;

}
