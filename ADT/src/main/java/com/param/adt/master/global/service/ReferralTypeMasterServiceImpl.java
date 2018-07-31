package com.param.adt.master.global.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.adt.common.ICommonConstants;
import com.param.adt.common.Response;
import com.param.adt.master.global.dao.IReferralTypeMasterDao;
import com.param.adt.master.global.dto.ReferralTypeMasterDto;

import in.param.exception.ApplicationException;

@Service
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReferralTypeMasterServiceImpl implements IReferralTypeMasterService, ICommonConstants {

	@Autowired
	private IReferralTypeMasterDao iReferralTypeMasterDao;

	@Override
	public Response addReferralTypeMaster(ReferralTypeMasterDto referralTypeMasterDto) throws ApplicationException {
		try {

			Response deptResponse = iReferralTypeMasterDao.getReferralTypeByName(referralTypeMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {

				return new Response(ERROR, null, ALREADY_EXIST, null, null);

			} else {
				/*referralTypeMasterDto
						.setCreatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));
				referralTypeMasterDto
						.setUpdatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));*/
				iReferralTypeMasterDao.addReferralTypeTypeMaster(referralTypeMasterDto);
				return new Response(SUCCESS, null, COMMON_SUCCESS, null, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getReferralTypeMasterList(ReferralTypeMasterDto referralTypeMasterDto) throws ApplicationException {
		try {
			return iReferralTypeMasterDao.getReferralTypeMasterList(referralTypeMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateReferralTypeMaster(ReferralTypeMasterDto referralTypeMasterDto) throws ApplicationException {
		try {
			Response deptResponse = iReferralTypeMasterDao.getReferralTypeByNameNotId(referralTypeMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {

				return new Response(ERROR, null, ALREADY_EXIST, null, null);

			} else {
				/*referralTypeMasterDto
						.setUpdatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));*/
				iReferralTypeMasterDao.updateReferralTypeMaster(referralTypeMasterDto);
				return new Response(SUCCESS, null, COMMON_UPDATE, null, null);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getReferralTypeById(ReferralTypeMasterDto referralTypeMasterDto) throws ApplicationException {
		try {
			return iReferralTypeMasterDao.getReferralTypeById(referralTypeMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateStatusForReferralType(ReferralTypeMasterDto referralTypeMasterDto)
			throws ApplicationException {
		try {
			Response deptResponse = iReferralTypeMasterDao.getReferralTypeById(referralTypeMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {
			/*	referralTypeMasterDto
						.setUpdatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));*/
				iReferralTypeMasterDao.updateStatusForReferralType(referralTypeMasterDto);
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
	public Response getReferralTypeCount(ReferralTypeMasterDto referralTypeMasterDto) throws ApplicationException {
		try {
			return iReferralTypeMasterDao.getCount(referralTypeMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getActiveReferralTypeList(ReferralTypeMasterDto referralTypeMasterDto) throws ApplicationException {
		try {
			return iReferralTypeMasterDao.getActiveReferralTypeList(referralTypeMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

}
