package com.param.adt.master.global.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.adt.common.ICommonConstants;
import com.param.adt.common.Response;
import com.param.adt.master.global.dao.IShortLeaveReasonMasterDao;
import com.param.adt.master.global.dto.ShortLeaveReasonMasterDto;

import in.param.exception.ApplicationException;
@Service
@SuppressWarnings({"rawtypes","unchecked"})
public class ShortLeaveReasonServiceImpl implements IShortLeaveReasonService,ICommonConstants
{
	@Autowired
	private IShortLeaveReasonMasterDao iShortLeaveReasonMasterDao; 
	
	@Override
	public Response addShortLeaveReasonMaster(ShortLeaveReasonMasterDto shortLeaveReasonMasterDto)
			throws ApplicationException {
		try {
			Response deptResponse = iShortLeaveReasonMasterDao.getShortLeaveReasonByName(shortLeaveReasonMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {

				return new Response(ERROR, null, ALREADY_EXIST, null, null);

			} else {

				/*shortLeaveReasonMasterDto
						.setCreatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyy HH:mm:ss"));
				shortLeaveReasonMasterDto
						.setUpdatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyy HH:mm:ss"));*/
				iShortLeaveReasonMasterDao.addShortLeaveReasonMaster(shortLeaveReasonMasterDto);
				return new Response(SUCCESS, null, COMMON_SUCCESS, null, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getShortLeaveReasonMasterList(ShortLeaveReasonMasterDto shortLeaveReasonMasterDto) throws ApplicationException {
		try {
			return iShortLeaveReasonMasterDao.getShortLeaveReasonMasterList(shortLeaveReasonMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateShortLeaveReasonMaster(ShortLeaveReasonMasterDto shortLeaveReasonMasterDto)
			throws ApplicationException {
		try {
			Response deptResponse = iShortLeaveReasonMasterDao.getShortLeaveReasonByNameNotId(shortLeaveReasonMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {
				return new Response(ERROR, null, ALREADY_EXIST, null, null);

			} else {

				/*shortLeaveReasonMasterDto
						.setUpdatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyy HH:mm:ss"));*/

				iShortLeaveReasonMasterDao.updateShortLeaveReasonMaster(shortLeaveReasonMasterDto);
				return new Response(SUCCESS, null, COMMON_UPDATE, null, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getShortLeaveReasonById(ShortLeaveReasonMasterDto shortLeaveReasonMasterDto)
			throws ApplicationException {
		try {
			return iShortLeaveReasonMasterDao.getShortLeaveReasonById(shortLeaveReasonMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateStatusForShortLeaveReason(ShortLeaveReasonMasterDto shortLeaveReasonMasterDto)
			throws ApplicationException {
		try {
			Response deptResponse = iShortLeaveReasonMasterDao.getShortLeaveReasonById(shortLeaveReasonMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {

				/*shortLeaveReasonMasterDto
						.setUpdatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyy HH:mm:ss"));*/

				iShortLeaveReasonMasterDao.updateShortLeaveReasonStatus(shortLeaveReasonMasterDto);
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
	public Response getActiveShortLeaveReasonList(ShortLeaveReasonMasterDto shortLeaveReasonMasterDto) throws ApplicationException {
		try {
			return iShortLeaveReasonMasterDao.getActiveShortLeaveReasonList(shortLeaveReasonMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getShortLeaveCount(ShortLeaveReasonMasterDto shortLeaveReasonMasterDto)
			throws ApplicationException {
		try {
			return iShortLeaveReasonMasterDao.getCount(shortLeaveReasonMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

}
