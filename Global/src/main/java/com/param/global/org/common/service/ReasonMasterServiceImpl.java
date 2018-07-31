package com.param.global.org.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.org.common.dao.IReasonMasterDao;
import com.param.global.org.common.dto.ReasonMasterDto;

import in.param.exception.ApplicationException;

@Service
@SuppressWarnings("rawtypes")
public class ReasonMasterServiceImpl implements IReasonMasterService, ICommonConstants {

	@Autowired
	IReasonMasterDao iReasonMasterDao;

	@Override
	public Response getReasonMasterById(int reasonId, int orgId) throws ApplicationException {
		try {
			return iReasonMasterDao.getReasonMasterById(reasonId, orgId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response saveReasonMaster(ReasonMasterDto reasonMasterDto) throws ApplicationException {
		Response response = iReasonMasterDao.getReasonMasterByName(reasonMasterDto);
		try {
			if (response.getListObject().size() > 0 && response.getListObject() != null) {
				return new Response<>(ERROR, ALREADY_EXIST_CODE, ALREADY_EXIST, null, null);
			} else {
				return iReasonMasterDao.saveReasonMaster(reasonMasterDto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getReasonMasterList(ReasonMasterDto reasonMasterDto) throws ApplicationException {
		try {
			return iReasonMasterDao.getReasonMasterList(reasonMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response updateReasonMaster(ReasonMasterDto reasonMasterDto) throws ApplicationException {
		Response response = iReasonMasterDao.getReasonMasterByNameNotById(reasonMasterDto);
		try {
			if (response.getListObject().size() > 0 && response.getListObject() != null) {
				return new Response<>(ERROR, ALREADY_EXIST_CODE, ALREADY_EXIST, null, null);
			} else {
				return iReasonMasterDao.updateReasonMaster(reasonMasterDto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response updateReasonMasterStatus(ReasonMasterDto reasonMasterDto) throws ApplicationException {
		try {
			return iReasonMasterDao.updateReasonMasterStatus(reasonMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getReasonMasterCount(ReasonMasterDto reasonMasterDto) throws ApplicationException {
		try {
			return iReasonMasterDao.getReasonMasterCount(reasonMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

}
