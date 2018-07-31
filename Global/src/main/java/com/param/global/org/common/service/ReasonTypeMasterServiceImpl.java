package com.param.global.org.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.org.common.dao.IReasonTypeMasterDao;
import com.param.global.org.common.dto.ReasonTypeMasterDto;

@Service
@SuppressWarnings("rawtypes")
public class ReasonTypeMasterServiceImpl implements IReasonTypeMasterService, ICommonConstants {

	@Autowired
	IReasonTypeMasterDao iReasonTypeMasterDao;

	@Override
	public Response getReasonTypeMasterById(int reasonTypeId, int orgId) {

		try {
			return iReasonTypeMasterDao.getReasonTypeMasterById(reasonTypeId, orgId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getReasonTypeMasterList(ReasonTypeMasterDto reasonTypeMasterDto) {
		try {
			return iReasonTypeMasterDao.getReasonTypeMasterList(reasonTypeMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response updateReasonTypeMaster(ReasonTypeMasterDto reasonTypeMasterDto) {
		Response response = iReasonTypeMasterDao.getReasonTypeMasterByNameNotById(reasonTypeMasterDto);
		try {
			if (response.getListObject().size() > 0 && response.getListObject() != null) {
				return new Response<>(ERROR, ALREADY_EXIST_CODE, ALREADY_EXIST, null, null);
			} else {
				return iReasonTypeMasterDao.updateReasonTypeMaster(reasonTypeMasterDto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response updateReasonTypeMasterStatus(ReasonTypeMasterDto reasonTypeMasterDto) {
		try {
			return iReasonTypeMasterDao.updateReasonTypeMasterStatus(reasonTypeMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getReasonTypeMasterCount(ReasonTypeMasterDto reasonTypeMasterDto) {
		try {
			return iReasonTypeMasterDao.getReasonTypeMasterCount(reasonTypeMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response saveReasonTypeMaster(ReasonTypeMasterDto reasonTypeMasterDto) {
		Response response = iReasonTypeMasterDao.getReasonTypeMasterByName(reasonTypeMasterDto);
		try {
			if (response.getListObject().size() > 0 && response.getListObject() != null) {
				return new Response<>(ERROR, ALREADY_EXIST_CODE, ALREADY_EXIST, null, null);
			} else {
				return iReasonTypeMasterDao.saveReasonTypeMaster(reasonTypeMasterDto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

}
