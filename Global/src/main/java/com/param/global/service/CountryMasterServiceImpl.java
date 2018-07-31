package com.param.global.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dao.ICountryMasterDao;
import com.param.global.dto.CountryMasterDto;

import in.param.exception.ApplicationException;

@SuppressWarnings({ "rawtypes", "unchecked" })
@Service

public class CountryMasterServiceImpl implements ICountryMasterService, ICommonConstants {

	@Autowired
	private ICountryMasterDao iCountryDao;

	@Override
	public Response addCountryMaster(CountryMasterDto countryMasterDto) throws ApplicationException {
		try {

			Response deptResponse = iCountryDao.getCountryByName(countryMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {

				return new Response(ERROR, null, ALREADY_EXIST, null, null);

			} else {
				/*countryMasterDto
						.setCreatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));
				countryMasterDto
						.setUpdatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));
				*/
				iCountryDao.addCountryMaster(countryMasterDto);
				return new Response(SUCCESS, null, COMMON_SUCCESS, null, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getCountryMasterList(CountryMasterDto countryMasterDto) throws ApplicationException {
		try {
			return iCountryDao.getCountryMasterList(countryMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateCountryMaster(CountryMasterDto countryMasterDto) throws ApplicationException {
		try {
			Response deptResponse = iCountryDao.getCountryByNameNotId(countryMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {

				return new Response(ERROR, null, ALREADY_EXIST, null, null);

			} else {
				/*countryMasterDto
						.setUpdatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));*/
				iCountryDao.updateCountryMaster(countryMasterDto);
				return new Response(SUCCESS, null, COMMON_UPDATE, null, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getCountryById(CountryMasterDto countryMasterDto) throws ApplicationException {
		try {
			return iCountryDao.getCountryByID(countryMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateStatusForCountry(CountryMasterDto countryMasterDto) throws ApplicationException {
		try {
			Response deptResponse = iCountryDao.getCountryByID(countryMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {
				/*System.out
						.println("Date:" + CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));
				countryMasterDto
						.setUpdatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));*/
				iCountryDao.updateCountryStatus(countryMasterDto);
				return new Response(SUCCESS, null, COMMON_UPDATE, null, null);

			} else {
				return new Response(ERROR, null, ALREADY_EXIST, null, null);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getActiveCountryList() throws ApplicationException {
		try {
			return iCountryDao.getActiveCountryList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getCountryCount(CountryMasterDto countryMasterDto) throws ApplicationException {
		try {
			return iCountryDao.getCount(countryMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getCountryMasterList() throws ApplicationException {
		try {
			return iCountryDao.getCountryMasterList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

}
