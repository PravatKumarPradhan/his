package com.param.global.billing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.global.billing.dao.IDoctorCategoryMasterDao;
import com.param.global.billing.dto.DoctorCategoryMasterDto;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;

import in.param.exception.ApplicationException;

@Service
@SuppressWarnings({ "rawtypes", "unchecked" })
public class DoctorCategoryMasterServiceImpl implements IDoctorCategoryMasterService, ICommonConstants {

	@Autowired
	IDoctorCategoryMasterDao iDoctorCategoryMasterDao;

	@Override
	public Response saveDoctorCategoryMaster(DoctorCategoryMasterDto doctorCategoryMasterDto)
			throws ApplicationException {
		Response response = iDoctorCategoryMasterDao.getDoctorCategoryMasterByName(doctorCategoryMasterDto);

		try {
			if (response.getListObject().size() > 0 && response.getListObject() != null) {
				return new Response(ERROR, null, ALREADY_EXIST, null, null);
			} else {
				return iDoctorCategoryMasterDao.saveDoctorCategoryMaster(doctorCategoryMasterDto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new Response<>(ERROR, ERROR_CODE, null, null, null);
	}

	@Override
	public Response getDoctorCategoryMasterById(int doctorId, int orgId) throws ApplicationException {
		try {
			return iDoctorCategoryMasterDao.getDoctorCategoryMasterById(doctorId, orgId);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new Response<>(ERROR, ERROR_CODE, null, null, null);
	}

	@Override
	public Response getDoctorCategoryMasterList(DoctorCategoryMasterDto doctorCategoryMasterDto) throws ApplicationException {
		try {
			return iDoctorCategoryMasterDao.getDoctorCategoryMasterList(doctorCategoryMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new Response<>(ERROR, ERROR_CODE, null, null, null);
	}

	@Override
	public Response updateDoctorCategoryMaster(DoctorCategoryMasterDto doctorCategoryMasterDto)
			throws ApplicationException {
		Response response = iDoctorCategoryMasterDao.getDoctorCategoryMasterByNameNotId(doctorCategoryMasterDto);

		try {
			if (response.getListObject().size() > 0 && response.getListObject() != null) {
				return new Response(ERROR, null, ALREADY_EXIST, null, null);
			} else {
				return iDoctorCategoryMasterDao.updateDoctorCategoryMaster(doctorCategoryMasterDto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new Response<>(ERROR, ERROR_CODE, null, null, null);
	}

	@Override
	public Response updateDoctorCategoryMasterStatus(DoctorCategoryMasterDto doctorCategoryMasterDto)
			throws ApplicationException {
		try {
			return iDoctorCategoryMasterDao.updateDoctorCategoryMasterStatus(doctorCategoryMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new Response<>(ERROR, ERROR_CODE, null, null, null);
	}

	@Override
	public Response getDoctorCategoryCount(DoctorCategoryMasterDto doctorCategoryMasterDto) throws ApplicationException {
		try{
			return iDoctorCategoryMasterDao.getDoctorCategoryCount(doctorCategoryMasterDto);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, null, null, null);
	}

}
