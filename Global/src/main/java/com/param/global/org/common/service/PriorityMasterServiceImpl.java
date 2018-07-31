package com.param.global.org.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.org.common.dao.IPriorityMasterDao;
import com.param.global.org.common.dto.PriorityMasterDto;

import in.param.exception.ApplicationException;

@Service
@SuppressWarnings("rawtypes")
public class PriorityMasterServiceImpl implements IPriorityMasterService, ICommonConstants {

	@Autowired
	IPriorityMasterDao iPriorityMasterDao;

	@Override
	public Response savePriorityMasterMaster(PriorityMasterDto priorityMasterDto) throws ApplicationException {
		Response response = iPriorityMasterDao.getPriorityMasterByName(priorityMasterDto);
		try {
			if (response.getListObject().size() > 0 && response.getListObject() != null) {
				return new Response<>(ERROR, ALREADY_EXIST_CODE, ALREADY_EXIST, null, null);
			} else {
				return iPriorityMasterDao.savePriorityMasterMaster(priorityMasterDto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getPriorityMasterById(int priorityId, int orgId) throws ApplicationException {
		try {
			return iPriorityMasterDao.getPriorityMasterById(priorityId, orgId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getPriorityMasterList(PriorityMasterDto priorityMasterDto) throws ApplicationException {
		try {
			return iPriorityMasterDao.getPriorityMasterList(priorityMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response updatePriorityMaster(PriorityMasterDto priorityMasterDto) throws ApplicationException {
		Response response = iPriorityMasterDao.getPriorityMasterByNameNotById(priorityMasterDto);

		try {
			if (response.getListObject().size() > 0 && response.getListObject() != null) {
				return new Response<>(ERROR, ALREADY_EXIST_CODE, ALREADY_EXIST, null, null);
			} else {
				return iPriorityMasterDao.updatePriorityMaster(priorityMasterDto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response updatePriorityMasterStatus(PriorityMasterDto priorityMasterDto) throws ApplicationException {
		try {
			return iPriorityMasterDao.updatePriorityMasterStatus(priorityMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getPriorityMasterCount(PriorityMasterDto priorityMasterDto) throws ApplicationException {
		try {
			return iPriorityMasterDao.getPriorityMasterCount(priorityMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

}
