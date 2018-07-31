package com.param.global.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dao.ICityMasterDao;
import com.param.global.dto.CityMasterDto;
import com.param.global.dto.DistrictMasterDto;

import in.param.exception.ApplicationException;

@Service
@SuppressWarnings({ "rawtypes", "unchecked" })
public class CityMasterServiceImpl implements ICityMasterService, ICommonConstants {

	@Autowired
	private ICityMasterDao iCityDao;

	@Override
	public Response addCityMaster(CityMasterDto cityMasterDto) throws ApplicationException {
		try {

			Response deptResponse = iCityDao.getCityByName(cityMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {

				return new Response(ERROR, null, ALREADY_EXIST, null, null);

			} else {
				/*cityMasterDto
						.setCreatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));
				cityMasterDto
						.setUpdatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));*/
				iCityDao.addCityMaster(cityMasterDto);
				return new Response(SUCCESS, null, COMMON_SUCCESS, null, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getCityMasterList(CityMasterDto cityMasterDto) throws ApplicationException {
		try {
			return iCityDao.getCityMasterList(cityMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateCityMaster(CityMasterDto citytMasterDto) throws ApplicationException {
		try {
			Response deptResponse = iCityDao.getCityByNameNotId(citytMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {

				return new Response(ERROR, null, ALREADY_EXIST, null, null);

			} else {
				/*citytMasterDto
						.setUpdatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));*/
				iCityDao.updateCityMaster(citytMasterDto);
				return new Response(SUCCESS, null, COMMON_UPDATE, null, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getCityById(CityMasterDto cityMasterDto) throws ApplicationException {
		try {
			return iCityDao.getCityByID(cityMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateStatusForDistrict(CityMasterDto cityMasterDto) throws ApplicationException {
		try {
			Response deptResponse = iCityDao.getCityByID(cityMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {

				/*cityMasterDto
						.setUpdatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));*/
				iCityDao.updateCityStatus(cityMasterDto);
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
	public Response getActiveCityList() throws ApplicationException {
		try {
			return iCityDao.getActiveCityList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getActiveDistrictListByStateId(DistrictMasterDto districtMasterDto) throws ApplicationException {
		try {
			return iCityDao.getActiveDistrictListByStateId(districtMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getCityCount(CityMasterDto cityMasterDto) throws ApplicationException {
		try {
			return iCityDao.getCount(cityMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getCityByStateIs(CityMasterDto cityMasterDto)throws ApplicationException {
		try {
			return iCityDao.getCityByStateId(cityMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	

}
