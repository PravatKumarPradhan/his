package com.param.adt.discharge.service;

import javax.transaction.Transactional;

import com.param.adt.common.Response;
import com.param.adt.discharge.dto.DischargeDto;

import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface IShortLeaveService {

	@Transactional
	Response saveShortLeaveRequest(DischargeDto dischargeDto)throws ApplicationException;

	@Transactional
	Response getShortLeaveRequestList(DischargeDto dischargeDto)throws ApplicationException;

	@Transactional
	Response getShortLeaveRequestListForDoctor(DischargeDto dischargeDto)throws ApplicationException;

	@Transactional
	Response acceptShotLeaveByDoctor(DischargeDto dischargeDto)throws ApplicationException;

	@Transactional
	Response rejectShotLeaveByDoctor(DischargeDto dischargeDto)throws ApplicationException;

	@Transactional
	Response acceptShotLeaveByBilling(DischargeDto dischargeDto)throws ApplicationException;

	@Transactional
	Response rejectShotLeaveByBilling(DischargeDto dischargeDto)throws ApplicationException;

	@Transactional
	Response releasePatientForShotLeave(DischargeDto dischargeDto)throws ApplicationException;

}
