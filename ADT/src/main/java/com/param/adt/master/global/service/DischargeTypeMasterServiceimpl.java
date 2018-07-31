package com.param.adt.master.global.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.adt.common.ICommonConstants;
import com.param.adt.common.Response;
import com.param.adt.master.global.dao.IDischargeTypeMasterDao;
import com.param.adt.master.global.dto.DischargeTypeMasterDto;

import in.param.exception.ApplicationException;

@Service
@SuppressWarnings({ "rawtypes", "unchecked" })
public class DischargeTypeMasterServiceimpl implements IDischargeTypeMasterService, ICommonConstants {
	@Autowired
	IDischargeTypeMasterDao iDischargeTypeMasterDao;

	@Override
	public Response addDischargeTypeMaster(DischargeTypeMasterDto dischargeTypeMasterDto) throws ApplicationException {
		try {
			Response deptResponse = iDischargeTypeMasterDao.getDischargeTypeByName(dischargeTypeMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {

				return new Response(ERROR, null, ALREADY_EXIST, null, null);

			} else {

				/*dischargeTypeMasterDto
						.setCreatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyy HH:mm:ss"));
				dischargeTypeMasterDto
						.setUpdatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyy HH:mm:ss"));*/
				iDischargeTypeMasterDao.addDischargeTypeMaster(dischargeTypeMasterDto);
				return new Response(SUCCESS, null, COMMON_SUCCESS, null, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getDischargeTypeMasterList(DischargeTypeMasterDto dischargeTypeMasterDto) throws ApplicationException {
		try {
			return iDischargeTypeMasterDao.getDischargeTypeMasterList(dischargeTypeMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateDischargeTypeMaster(DischargeTypeMasterDto dischargeTypeMasterDto)
			throws ApplicationException {
		try {
			Response deptResponse = iDischargeTypeMasterDao.getDischargeTypeByNameNotId(dischargeTypeMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {
				return new Response(ERROR, null, ALREADY_EXIST, null, null);

			} else {

				/*dischargeTypeMasterDto
						.setUpdatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyy HH:mm:ss"));*/

				iDischargeTypeMasterDao.updateDischargeTypeMaster(dischargeTypeMasterDto);
				return new Response(SUCCESS, null, COMMON_UPDATE, null, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getDischargeTypeById(DischargeTypeMasterDto dischargeTypeMasterDto) throws ApplicationException {
		try {
			return iDischargeTypeMasterDao.getDischargeTypeById(dischargeTypeMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateStatusForDischargeType(DischargeTypeMasterDto dischargeTypeMasterDto)
			throws ApplicationException {
		try {
			Response deptResponse = iDischargeTypeMasterDao.getDischargeTypeById(dischargeTypeMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {
				/*dischargeTypeMasterDto
						.setUpdatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyy HH:mm:ss"));*/

				iDischargeTypeMasterDao.updateStatusForDischargeType(dischargeTypeMasterDto);
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
	public Response getActiveDischargeTypeList(DischargeTypeMasterDto dischargeTypeMasterDto) throws ApplicationException {
		try {
			return iDischargeTypeMasterDao.getActiveDischargeTypeList(dischargeTypeMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getDischargeTypeCount(DischargeTypeMasterDto dischargeTypeMasterDto) throws ApplicationException {
		try {
			return iDischargeTypeMasterDao.getCount(dischargeTypeMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

}
