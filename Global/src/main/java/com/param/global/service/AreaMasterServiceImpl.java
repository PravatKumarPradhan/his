package com.param.global.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dao.IAreaMasterDao;
import com.param.global.dto.AreaMasterDto;
import com.param.global.dto.CityMasterDto;

import in.param.exception.ApplicationException;

@Service
@SuppressWarnings({ "rawtypes", "unchecked" })
public class AreaMasterServiceImpl implements ICommonConstants, IAreaMasterService {
	@Autowired
	private IAreaMasterDao iAreaDao;

	@Override
	public Response addAreaMaster(AreaMasterDto areaMasterDto) throws ApplicationException {
		try {
			Response deptResponse = iAreaDao.getAreaByName(areaMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {

				return new Response(ERROR, null, ALREADY_EXIST, null, null);

			} else {
				/*areaMasterDto
						.setCreatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));
				areaMasterDto
						.setUpdatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));*/
				iAreaDao.addAreaMaster(areaMasterDto);
				return new Response(SUCCESS, null, COMMON_SUCCESS, null, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getAreaMasterList(AreaMasterDto areaMasterDto) throws ApplicationException {
		try {
			return iAreaDao.getAreaMasterList(areaMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateAreaMaster(AreaMasterDto areaMasterDto) throws ApplicationException {
		try {
			Response deptResponse = iAreaDao.getAreaByNameNotId(areaMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {
				return new Response(ERROR, null, ALREADY_EXIST, null, null);

			} else {
				/*areaMasterDto
						.setUpdatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));*/
				iAreaDao.updateAreaMaster(areaMasterDto);
				return new Response(SUCCESS, null, COMMON_UPDATE, null, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getAreaById(AreaMasterDto areaMasterDto) throws ApplicationException {
		try {
			return iAreaDao.getAreaById(areaMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateStatusForArea(AreaMasterDto areaMasterDto) throws ApplicationException {
		try {
			Response deptResponse = iAreaDao.getAreaById(areaMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {
				/*areaMasterDto
						.setUpdatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));*/
				iAreaDao.updateAreaStatus(areaMasterDto);
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
	public Response getActiveAreaList() throws ApplicationException {
		try {
			return iAreaDao.getActiveAreaList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getActiveCityListByDistrictId(CityMasterDto cityMasterDto) throws ApplicationException {
		try {
			return iAreaDao.getActiveCityListByDistrictId(cityMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getAreaCount(AreaMasterDto areaMasterDto) throws ApplicationException {
		try {
			return iAreaDao.getCount(areaMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

}
