package com.param.global.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dao.IStateMasterDao;
import com.param.global.dto.StateMasterDto;

import in.param.exception.ApplicationException;

@Service
@SuppressWarnings({ "rawtypes", "unchecked" })
public class StateMasterServiceImpl implements IStateMasterService, ICommonConstants {

	@Autowired
	private IStateMasterDao iStateDao;

	@Override
	public Response addStateMaster(StateMasterDto stateMasterDto) throws ApplicationException {
		try {
			Response deptResponse = iStateDao.getStateByName(stateMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {
				
					return new Response(ERROR, null, ALREADY_EXIST, null, null);
				
			} else {
				/*stateMasterDto
						.setCreatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));
				stateMasterDto
						.setUpdatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));
				*/
				iStateDao.addStateMaster(stateMasterDto);
				return new Response(SUCCESS, null, COMMON_SUCCESS, null, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getStateMasterList(StateMasterDto stateMasterDto) throws ApplicationException {
		try {
			return iStateDao.getStateMasterList(stateMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateStateMaster(StateMasterDto stateMasterDto) throws ApplicationException {
		try {
			Response deptResponse = iStateDao.getStateByNameNotId(stateMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {
				return new Response(ERROR, null, ALREADY_EXIST, null, null);

			} else {
				/*stateMasterDto
						.setUpdatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));
				*/
				iStateDao.updateStateMaster(stateMasterDto);
				return new Response(SUCCESS, null, COMMON_UPDATE, null, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getStateById(StateMasterDto stateMasterDto) throws ApplicationException {
		try {
			return iStateDao.getStateById(stateMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateStatusForState(StateMasterDto stateMasterDto) throws ApplicationException {
		try {
			Response deptResponse = iStateDao.getStateById(stateMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {
				
				/*
				stateMasterDto
						.setUpdatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));
				*/iStateDao.updateStateStatus(stateMasterDto);
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
	public Response getActiveStateList() throws ApplicationException {
		try {
			return iStateDao.getActiveStateList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getStateCount(StateMasterDto stateMasterDto) throws ApplicationException {
		try {
			return iStateDao.getCount(stateMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getStateByCountryId(StateMasterDto stateMasterDto)throws ApplicationException {
		try {
			return iStateDao.getStateByCountryId(stateMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

}
