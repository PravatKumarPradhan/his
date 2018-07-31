package com.param.adt.master.global.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.adt.common.ICommonConstants;
import com.param.adt.common.Response;
import com.param.adt.master.global.dao.IConsentMasterDao;
import com.param.adt.master.global.dto.ConsentMasterDto;

import in.param.exception.ApplicationException;
@SuppressWarnings({"rawtypes","unchecked"})
@Service
public class ConsentMasterServiceImpl implements IConsentMasterService,ICommonConstants
{

	
	@Autowired
	private IConsentMasterDao iConsentMasterDao;
	
	@Override
	public Response addConsentMaster(ConsentMasterDto consentMasterDto) throws ApplicationException {
		try {

			Response deptResponse = iConsentMasterDao.getConsentByName(consentMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {

				return new Response(ERROR, null, ALREADY_EXIST, null, null);

			} else {
				/*consentMasterDto
						.setCreatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));
				consentMasterDto
						.setUpdatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));*/
				iConsentMasterDao.addConsentMaster(consentMasterDto);
				return new Response(SUCCESS, null, COMMON_SUCCESS, null, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateConsentMaster(ConsentMasterDto consentMasterDto) throws ApplicationException {
		try {
			Response deptResponse = iConsentMasterDao.getConsentByNameNotId(consentMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {

				return new Response(ERROR, null, ALREADY_EXIST, null, null);

			} else {
				/*consentMasterDto
						.setUpdatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));*/
				iConsentMasterDao.updateConsentMaster(consentMasterDto);
				return new Response(SUCCESS, null, COMMON_UPDATE, null, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateStatusForConsent(ConsentMasterDto consentMasterDto) throws ApplicationException {
		try {
			Response deptResponse = iConsentMasterDao.getConsentById(consentMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {

				/*consentMasterDto
						.setUpdatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));*/
				iConsentMasterDao.updateConsentStatus(consentMasterDto);
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
	public Response getConsentMasterList(ConsentMasterDto consentMasterDto) throws ApplicationException {
		try {
			return iConsentMasterDao.getConsentMasterList(consentMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getConsentById(ConsentMasterDto consentMasterDto) throws ApplicationException {
		try {
			return iConsentMasterDao.getConsentById(consentMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getActiveConsentList() throws ApplicationException {
		try {
			return iConsentMasterDao.getActiveConsentList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getConsentCount(ConsentMasterDto consentMasterDto) throws ApplicationException {
		try {
			return iConsentMasterDao.getCount(consentMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

}
