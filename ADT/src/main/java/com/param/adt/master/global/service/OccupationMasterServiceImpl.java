package com.param.adt.master.global.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.adt.common.ICommonConstants;
import com.param.adt.common.Response;
import com.param.adt.master.global.dao.IOccupationMasterDao;
import com.param.adt.master.global.dto.OccupationMasterDto;

import in.param.exception.ApplicationException;

@SuppressWarnings({ "unchecked", "rawtypes" })
@Service
public class OccupationMasterServiceImpl implements IOccupationMasterService, ICommonConstants {

	@Autowired
	private IOccupationMasterDao iOccupationMasterDao;

	@Override
	public Response getOccupationMasterList(OccupationMasterDto occupationMasterDto) throws ApplicationException {
		try {
			return iOccupationMasterDao.getOccupationMasterList(occupationMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response addOccupationMaster(OccupationMasterDto occupationMasterDto) throws ApplicationException {
		try {

			Response deptResponse = iOccupationMasterDao.getOccupationByName(occupationMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {

				return new Response(ERROR, null, ALREADY_EXIST, null, null);

			} else {
				/*occupationMasterDto
						.setCreatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));
				occupationMasterDto
						.setUpdatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));*/
				iOccupationMasterDao.addOccupationMaster(occupationMasterDto);
				return new Response(SUCCESS, null, COMMON_SUCCESS, null, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getOccupationById(OccupationMasterDto occupationMasterDto) throws ApplicationException {
		try {
			return iOccupationMasterDao.getOccupationByID(occupationMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateOccupationMaster(OccupationMasterDto occupationMasterDto) throws ApplicationException {
		try {
			Response deptResponse = iOccupationMasterDao.getOccupationByNameNotId(occupationMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {

				return new Response(ERROR, null, ALREADY_EXIST, null, null);

			} else {
				/*occupationMasterDto
						.setUpdatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));*/
				iOccupationMasterDao.updateOccupationMaster(occupationMasterDto);
				return new Response(SUCCESS, null, COMMON_UPDATE, null, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateStatusForOccupation(OccupationMasterDto occupationMasterDto) throws ApplicationException {
		try {
			Response deptResponse = iOccupationMasterDao.getOccupationByID(occupationMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {
			/*	occupationMasterDto
						.setUpdatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));*/
				iOccupationMasterDao.updateOccupationStatus(occupationMasterDto);
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
	public Response getActiveMaritalStatusList() throws ApplicationException {
		try {
			return iOccupationMasterDao.getActiveOccupationList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getOccupationCount(OccupationMasterDto occupationMasterDto) throws ApplicationException {
		try {
			return iOccupationMasterDao.getCount(occupationMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

}
