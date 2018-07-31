package com.param.adt.master.unit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.adt.master.unit.dao.IWardMasterDao;
import com.param.adt.master.unit.dto.WardMasterDto;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;

import in.param.exception.ApplicationException;

@Service
@SuppressWarnings({ "rawtypes", "unchecked" })
public class WardMasterServiceImpl implements IWardMasterService, ICommonConstants {

	@Autowired
	IWardMasterDao iWardMasterDao;

	@Override
	public Response saveWardMasterMaster(WardMasterDto wardMasterDto) throws ApplicationException {

		Response response = iWardMasterDao.getWardMasterByName(wardMasterDto);

		try {
			if (response.getListObject().size() > 0 && response.getListObject() != null) {
				return new Response(ERROR, null, ALREADY_EXIST, null, null);
			} else {
				return iWardMasterDao.saveWardMasterMaster(wardMasterDto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getWardMasterById(int wardId, int orgId, int unitId) throws ApplicationException {
		try {
			return iWardMasterDao.getWardMasterById(wardId, orgId, unitId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getWardMasterList(WardMasterDto wardMasterDto) throws ApplicationException {
		try {
			return iWardMasterDao.getWardMasterList(wardMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response updateWardMaster(WardMasterDto wardMasterDto) throws ApplicationException {
		Response response = iWardMasterDao.getWardMasterByNameNotById(wardMasterDto);

		try {
			if (response.getListObject().size() > 0 && response.getListObject() != null) {
				return new Response(ERROR, null, ALREADY_EXIST, null, null);
			} else {
				return iWardMasterDao.updateWardMaster(wardMasterDto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response updateWardMasterStatus(WardMasterDto wardMasterDto) throws ApplicationException {
		try {
			return iWardMasterDao.updateWardMasterStatus(wardMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getWardMasterCount(WardMasterDto wardMasterDto) throws ApplicationException {
		try {
			return iWardMasterDao.getWardMasterCount(wardMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

}
