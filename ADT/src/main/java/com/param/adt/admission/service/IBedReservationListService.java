package com.param.adt.admission.service;

import javax.transaction.Transactional;

import com.param.adt.admission.dto.UnreservedPatientDto;
import com.param.adt.common.Response;
import com.param.global.dto.AdmissionDto;

import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IBedReservationListService {

	@Transactional
	Response getBedReservationList(AdmissionDto admissionDto) throws ApplicationException;

	@Transactional
	Response unreservePatrientBooking(UnreservedPatientDto unreservedPatientDto)throws ApplicationException;

	@Transactional
	Response getBedReservationCount(AdmissionDto admissionDto) throws ApplicationException;

	

}
