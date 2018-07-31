package com.param.adt.discharge.service;

import javax.transaction.Transactional;

import com.param.adt.common.Response;
import com.param.adt.discharge.dto.DischargeDto;
import com.param.global.dto.AdmissionDto;

import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface IDueForDischargeService {
	@Transactional
	Response saveDischargeRequest(DischargeDto dischargeDto)throws ApplicationException;

	@Transactional
	Response getDischagePatientList(AdmissionDto admissionDto)throws ApplicationException;

	@Transactional
	Response updatePDD(DischargeDto dischargeDto);

}
