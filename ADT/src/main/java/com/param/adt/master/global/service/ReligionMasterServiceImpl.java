package com.param.adt.master.global.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.adt.common.ICommonConstants;
import com.param.adt.common.Response;
import com.param.adt.master.global.dao.IReligionMasterDao;
import com.param.adt.master.global.dto.ReligionMasterDto;

import in.param.exception.ApplicationException;

@Service
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReligionMasterServiceImpl implements IReligionMasterService, ICommonConstants {

	@Autowired
	private IReligionMasterDao iReligionDao;

	@Override
	public Response addReligionMaster(ReligionMasterDto religionMasterDto) throws ApplicationException {
		try {

			Response deptResponse = iReligionDao.getReligionByName(religionMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {

				return new Response(ERROR, null, ALREADY_EXIST, null, null);

			} else {
				/*religionMasterDto
						.setCreatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));
				religionMasterDto
						.setUpdatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));*/
				iReligionDao.addReligionMaster(religionMasterDto);
				return new Response(SUCCESS, null, COMMON_SUCCESS, null, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getReligionMasterList(ReligionMasterDto religionMasterDto) throws ApplicationException {
		try {
			return iReligionDao.getReligionMasterList(religionMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateReligionMaster(ReligionMasterDto religionMasterDto) throws ApplicationException {
		try {
			Response deptResponse = iReligionDao.getReligionByNameNotId(religionMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {

				return new Response(ERROR, null, ALREADY_EXIST, null, null);

			} else {
				/*religionMasterDto
						.setUpdatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));*/
				iReligionDao.updateReligionMaster(religionMasterDto);
				return new Response(SUCCESS, null, COMMON_UPDATE, null, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getReligionById(ReligionMasterDto religionMasterDto) throws ApplicationException {
		try {
			return iReligionDao.getReligionById(religionMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateStatusForReligion(ReligionMasterDto religionMasterDto) throws ApplicationException {
		try {
			Response deptResponse = iReligionDao.getReligionById(religionMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {
				/*religionMasterDto
						.setUpdatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));*/
				iReligionDao.updateReligionStatus(religionMasterDto);
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
	public Response getActiveReligionList() throws ApplicationException {
		try {
			return iReligionDao.getActiveReligionList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getReligionCount(ReligionMasterDto religionMasterDto) throws ApplicationException {
		try {
			return iReligionDao.getCount(religionMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

}
