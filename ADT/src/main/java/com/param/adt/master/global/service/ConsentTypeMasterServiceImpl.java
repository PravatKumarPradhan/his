package com.param.adt.master.global.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.adt.common.ICommonConstants;
import com.param.adt.common.Response;
import com.param.adt.master.global.dao.IConsentTypeMasterDao;
import com.param.adt.master.global.dto.ConsentTypeMasterDto;

import in.param.exception.ApplicationException;

@Service
@SuppressWarnings({"rawtypes","unchecked"})
public class ConsentTypeMasterServiceImpl implements ICommonConstants,IConsentTypeMasterService
{

	@Autowired
	IConsentTypeMasterDao iConsentTypeDao;
	
	@Override
	public Response addConsentTypeMaster(ConsentTypeMasterDto consentTypeMasterDto) throws ApplicationException {
		try {

			Response deptResponse = iConsentTypeDao.getConsentTypeByName(consentTypeMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {

				return new Response(ERROR, null, ALREADY_EXIST, null, null);

			} else {
				/*consentTypeMasterDto
						.setCreatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));
				consentTypeMasterDto
						.setUpdatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));*/
				iConsentTypeDao.addConsentTypeMaster(consentTypeMasterDto);
				return new Response(SUCCESS, null, COMMON_SUCCESS, null, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getConsentTypeMasterList(ConsentTypeMasterDto consentTypeMasterDto) throws ApplicationException {
		try {
			return iConsentTypeDao.getConsentTypeMasterList(consentTypeMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateConsentTypeMaster(ConsentTypeMasterDto consentTypeMasterDto) throws ApplicationException {
		try {
			Response deptResponse = iConsentTypeDao.getConsentTypeByNameNotId(consentTypeMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {

				return new Response(ERROR, null, ALREADY_EXIST, null, null);

			} else {
				/*consentTypeMasterDto
						.setUpdatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));*/
				iConsentTypeDao.updateConsentTypeMaster(consentTypeMasterDto);
				return new Response(SUCCESS, null, COMMON_UPDATE, null, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getConsentTypeById(ConsentTypeMasterDto consentTypeMasterDto) throws ApplicationException {
		try {
			return iConsentTypeDao.getConsentTypeById(consentTypeMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateStatusForConsentType(ConsentTypeMasterDto consentTypeMasterDto) throws ApplicationException {
		try {
			Response deptResponse = iConsentTypeDao.getConsentTypeById(consentTypeMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {
			
				/*consentTypeMasterDto
						.setUpdatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));*/
				iConsentTypeDao.updateConsentTypeStatus(consentTypeMasterDto);
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
	public Response getActiveConsentTypeList() throws ApplicationException {
		try {
			return iConsentTypeDao.getActiveConsentTypeList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getConsentTypeCount(ConsentTypeMasterDto consentTypeMasterDto) throws ApplicationException {
		try {
			return iConsentTypeDao.getCount(consentTypeMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

}
