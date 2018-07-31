package com.param.global.service;

import javax.transaction.Transactional;

import com.param.global.common.Response;
import com.param.global.dto.PatientSponsorDetailsDto;

import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IPatientSponsorDetailsService {

	@Transactional
	Response savePatientSponsorDetails(PatientSponsorDetailsDto patientSponsorDetailsDto)throws ApplicationException;

}
