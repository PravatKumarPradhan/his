package com.param.adt.master.global.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.adt.common.ICommonConstants;
import com.param.adt.common.Response;
import com.param.adt.master.global.dao.IReferralMasterDao;
import com.param.adt.master.global.dto.ReferralMasterDto;
import com.param.global.dto.AreaMasterDto;

import in.param.exception.ApplicationException;

@Service
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReferralMasterServiceImpl implements IReferralMasterService, ICommonConstants {

	@Autowired
	private IReferralMasterDao iReferralMasterDao;

	@Override
	public Response addReferralMaster(ReferralMasterDto referralMasterDto) throws ApplicationException {
		try {

			Response deptResponse = iReferralMasterDao.getReferralByName(referralMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {

				return new Response(ERROR, null, ALREADY_EXIST, null, null);

			} else {
				/*referralMasterDto
						.setCreatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));
				referralMasterDto
						.setUpdatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));*/
				iReferralMasterDao.addReferralTypeTypeMaster(referralMasterDto);
				return new Response(SUCCESS, null, COMMON_SUCCESS, null, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getReferralMasterList(ReferralMasterDto referralMasterDto) throws ApplicationException {
		try {
			return iReferralMasterDao.getReferralMasterList(referralMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateReferralMaster(ReferralMasterDto referralMasterDto) throws ApplicationException {
		try {
			Response deptResponse = iReferralMasterDao.getReferralByNameNotId(referralMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {

				return new Response(ERROR, null, ALREADY_EXIST, null, null);

			} else {
				/*referralMasterDto
						.setUpdatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));*/
				iReferralMasterDao.updateReferralMaster(referralMasterDto);
				return new Response(SUCCESS, null, COMMON_UPDATE, null, null);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getReferralById(ReferralMasterDto referralMasterDto) throws ApplicationException {
		try {
			return iReferralMasterDao.getReferralById(referralMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateStatusForReferral(ReferralMasterDto referralMasterDto) throws ApplicationException {
		try {
			Response deptResponse = iReferralMasterDao.getReferralById(referralMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) 
			{
				/*referralMasterDto
						.setUpdatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));*/
				iReferralMasterDao.updateReferralMasterStatus(referralMasterDto);
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
	public Response getActiveReferralList() throws ApplicationException {
		try {
			return iReferralMasterDao.getActiveReferralList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getAreaByCityId(AreaMasterDto areaMasterDto) throws ApplicationException {
		try {
			return iReferralMasterDao.getAreaByCityId(areaMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getReferralCount(ReferralMasterDto referralMasterDto) throws ApplicationException {
		try {
			return iReferralMasterDao.getCount(referralMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getReferralListByReferralTypeId(ReferralMasterDto referralMasterDto) throws ApplicationException {
		try {
			return iReferralMasterDao.getReferralListByReferralTypeId(referralMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	
}
