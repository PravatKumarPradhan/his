package com.param.adt.admission.dao;

import com.param.adt.admission.dto.AdmissionNoteDto;
import com.param.adt.admission.model.AdmissionNote;
import com.param.adt.admission.model.AdmissionPatientDocuments;
import com.param.adt.common.Response;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IPendingRequestDao extends IGenericDao<AdmissionNote, Integer>
{
	
	Response getPendingAdmissionRequestDetails(AdmissionNoteDto admissionNoteDto) throws ApplicationException;

	Response getAvtivePatientCategoryList() throws ApplicationException;

	Response getAvtivePaymentEntitlementList() throws ApplicationException;

	Response updatePendingAdmissionRequestDetails(AdmissionNoteDto admissionNoteDto) throws ApplicationException;

	Response updateAdmissionRequestMapperDetails(AdmissionNoteDto admissionPatientMapperDto);

	Response getPreviousData(AdmissionNoteDto admissionNoteDto) throws ApplicationException;

	Response updateReserveBedDetails(AdmissionNoteDto admissionNoteDto) throws ApplicationException;

	Response uploadAdmissionDocuments(AdmissionPatientDocuments admissionPatientDocumentsDto) throws ApplicationException;

	Response getCount(AdmissionNoteDto admissionNoteDto) throws ApplicationException;

	
}
