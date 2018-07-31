package com.param.global.org.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.org.common.dao.IDegreeMasterDao;
import com.param.global.org.common.dto.DegreeMasterDto;

import in.param.exception.ApplicationException;

@Service
@SuppressWarnings("rawtypes")
public class DegreeMasterServiceImpl implements IDegreeMasterService, ICommonConstants {

	@Autowired
	IDegreeMasterDao iDegreeMasterDao;

	@Override
	public Response saveDegreeMaster(DegreeMasterDto degreeMasterDto) throws ApplicationException {
		Response response = iDegreeMasterDao.getDegreeMasterByName(degreeMasterDto);
		try {
			if (response.getListObject().size() > 0 && response.getListObject() != null) {
				return new Response<>(ERROR, ALREADY_EXIST_CODE, ALREADY_EXIST, null, null);
			} else {
				return iDegreeMasterDao.saveDegreeMaster(degreeMasterDto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getDegreeMasterById(int degreeId, int orgId) throws ApplicationException {
		try {
			return iDegreeMasterDao.getDegreeMasterById(degreeId, orgId);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getDegreeMasterList(DegreeMasterDto degreeMasterDto) throws ApplicationException {
		try {
			return iDegreeMasterDao.getDegreeMasterList(degreeMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response updateDegreeMaster(DegreeMasterDto degreeMasterDto) throws ApplicationException {
		Response response = iDegreeMasterDao.getDegreeMasterByNameNotById(degreeMasterDto);
		try {
			if (response.getListObject().size() > 0 && response.getListObject() != null) {
				return new Response<>(ERROR, ALREADY_EXIST_CODE, ALREADY_EXIST, null, null);
			} else {
				return iDegreeMasterDao.updateDegreeMaster(degreeMasterDto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response updateDegreeMasterStatus(DegreeMasterDto degreeMasterDto) throws ApplicationException {
		try {
			return iDegreeMasterDao.updateDegreeMasterStatus(degreeMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getDegreeMasterCount(DegreeMasterDto degreeMasterDto) throws ApplicationException {
		try {

			return iDegreeMasterDao.getDegreeMasterCount(degreeMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

}
