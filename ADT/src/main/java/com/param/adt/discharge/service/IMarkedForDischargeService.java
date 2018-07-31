package com.param.adt.discharge.service;

import javax.transaction.Transactional;

import com.param.adt.common.Response;
import com.param.adt.discharge.dto.DischargeDto;
import com.param.global.dto.AdmissionDto;

import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IMarkedForDischargeService {

	@Transactional
	Response getDischagePatientListForNursing(DischargeDto dischargeDto)throws ApplicationException;

	@Transactional
	Response getPatientServiceOrderList(AdmissionDto admissionDto) throws ApplicationException;

	@Transactional
	Response updatePatientServiceOrder(AdmissionDto admissionDto)throws ApplicationException;

	@Transactional
	Response getPatientReadyForBillingList(DischargeDto dischargeDto)throws ApplicationException;
	
	@Transactional
	Response updatePatientReadyForBillingStatus(DischargeDto dischargeDto)throws ApplicationException;

	@Transactional
	Response getPatientFinalDischargeList(DischargeDto dischargeDto)throws ApplicationException;

	@Transactional
	Response vacateBed(DischargeDto dischargeDto)throws ApplicationException;

}
