package com.param.adt.admission.service;

import javax.transaction.Transactional;

import com.param.adt.admission.dto.AdmissionNoteDto;
import com.param.adt.admission.model.AdmissionNote;
import com.param.adt.common.Response;

import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IPendingRequestService 
{
	@Transactional
	Response getPendingAdmissionRequestDetails(AdmissionNoteDto admissionNoteDto) throws ApplicationException;

	@Transactional
	Response getAvtivePatientCategoryList() throws ApplicationException;

	@Transactional
	Response getAvtivePaymentEntitlementList() throws ApplicationException;

	@Transactional
	Response updatePendingAdmissionRequestDetails(AdmissionNoteDto admissionNoteDto);

	@Transactional
	Response updateAdmissionRequestMapperDetails(AdmissionNoteDto admissionNoteDto, AdmissionNote admissionNoteObiject);

	@Transactional
	Response updateReserveBedDetails(AdmissionNoteDto admissionNoteDto)throws ApplicationException;

	@Transactional
	Response uploadAdmissionDocuments(AdmissionNoteDto admissionNoteDto) throws ApplicationException;

	@Transactional
	Response getPendingAdmissionCount(AdmissionNoteDto admissionNoteDto) throws ApplicationException;
}
