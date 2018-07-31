package com.param.adt.master.global.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.adt.common.ICommonConstants;
import com.param.adt.common.Response;
import com.param.adt.master.global.dao.IICUTypeMasterDao;
import com.param.adt.master.global.dto.ICUTypeMasterDto;

import in.param.exception.ApplicationException;

@Service
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ICUTypeMasterService implements IICUTypeService, ICommonConstants {

	@Autowired
	IICUTypeMasterDao iICUTypeDao;

	@Transactional
	@Override
	public Response addICUTypeMaster(ICUTypeMasterDto icuTypeMasterDto) throws ApplicationException {
		try {

			Response deptResponse = iICUTypeDao.getICUByName(icuTypeMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {

				return new Response(ERROR, null, ALREADY_EXIST, null, null);

			} else {
				/*icuTypeMasterDto
						.setCreatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));
				icuTypeMasterDto
						.setUpdatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));*/

				iICUTypeDao.addICUTypeMaster(icuTypeMasterDto);
				return new Response(SUCCESS, null, COMMON_SUCCESS, null, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Transactional
	@Override
	public Response getICUTypeMasterList(ICUTypeMasterDto icuTypeMasterDto) throws ApplicationException {
		try {
			return iICUTypeDao.getICUMasterList(icuTypeMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Transactional
	@Override
	public Response getSpecialityById(ICUTypeMasterDto icuTypeMasterDto) throws ApplicationException {
		try {
			return iICUTypeDao.getICUByID(icuTypeMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Transactional
	@Override
	public Response updateICUTypeMaster(ICUTypeMasterDto icuTypeMasterDto) throws ApplicationException {
		try {
			Response deptResponse = iICUTypeDao.getICUByNameNotId(icuTypeMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {

				return new Response(ERROR, null, ALREADY_EXIST, null, null);

			} else {

				/*icuTypeMasterDto
						.setUpdatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));*/

				icuTypeMasterDto.setCreatedBy(icuTypeMasterDto.getCreatedBy());
				iICUTypeDao.updateICUTypeMaster(icuTypeMasterDto);
				return new Response(SUCCESS, null, COMMON_UPDATE, null, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Transactional
	@Override
	public Response updateStatusForICU(ICUTypeMasterDto icuTypeMasterDto) throws ApplicationException {
		try {
			Response deptResponse = iICUTypeDao.getICUByID(icuTypeMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {
				/*icuTypeMasterDto
						.setUpdatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));*/
				iICUTypeDao.updateICUStatus(icuTypeMasterDto);
				return new Response(SUCCESS, null, COMMON_UPDATE, null, null);

			} else {

				return new Response(ERROR, null, ALREADY_EXIST, null, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Transactional
	@Override
	public Response getICUCount(ICUTypeMasterDto icuTypeMasterDto) throws ApplicationException {
		try {
			return iICUTypeDao.getCount(icuTypeMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

}
