package com.param.adt.discharge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.adt.common.ICommonConstants;
import com.param.adt.common.Response;
import com.param.adt.discharge.dao.IShortLeaveDao;
import com.param.adt.discharge.dto.DischargeDto;

import in.param.exception.ApplicationException;
@Service
@SuppressWarnings({"rawtypes","unchecked"})
public class ShortLeaveServiceImpl implements IShortLeaveService,ICommonConstants{

	@Autowired
	IShortLeaveDao iShortLeaveDao;
	
	@Override
	public Response saveShortLeaveRequest(DischargeDto dischargeDto) throws ApplicationException {
		try {
			return iShortLeaveDao.saveShortLeaveRequest(dischargeDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getShortLeaveRequestList(DischargeDto dischargeDto) throws ApplicationException {
		try {
			return iShortLeaveDao.getShortLeaveRequestList(dischargeDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getShortLeaveRequestListForDoctor(DischargeDto dischargeDto) throws ApplicationException {
		try {
			return iShortLeaveDao.getShortLeaveRequestListForDoctor(dischargeDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response acceptShotLeaveByDoctor(DischargeDto dischargeDto) throws ApplicationException {
		try {
			return iShortLeaveDao.acceptShotLeaveByDoctor(dischargeDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response rejectShotLeaveByDoctor(DischargeDto dischargeDto) throws ApplicationException {
		try {
			return iShortLeaveDao.rejectShotLeaveByDoctor(dischargeDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response acceptShotLeaveByBilling(DischargeDto dischargeDto) throws ApplicationException {
		try {
			return iShortLeaveDao.acceptShotLeaveByBilling(dischargeDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response rejectShotLeaveByBilling(DischargeDto dischargeDto) throws ApplicationException {
		try {
			return iShortLeaveDao.rejectShotLeaveByBilling(dischargeDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response releasePatientForShotLeave(DischargeDto dischargeDto) throws ApplicationException {
		try {
			return iShortLeaveDao.releasePatientForShotLeave(dischargeDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

}
