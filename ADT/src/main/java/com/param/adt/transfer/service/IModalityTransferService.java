package com.param.adt.transfer.service;

import javax.transaction.Transactional;

import com.param.adt.common.Response;
import com.param.adt.transfer.dto.TransferModalityRequestDto;
import com.param.global.dto.AdmissionDto;
import com.param.global.dto.PatientSearchDto;

import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IModalityTransferService {

	@Transactional
	Response searchAdmittedPatientByNameAndUhid(AdmissionDto admissionDto)throws ApplicationException;

	@Transactional
	Response getPatientServiceOrderListByAdmissionId(AdmissionDto admissionDto)throws ApplicationException;

	@Transactional
	Response saveModalityTransferRequest(TransferModalityRequestDto transferModalityRequestDto)throws ApplicationException;

	@Transactional
	Response getModalityTransferRequestList(TransferModalityRequestDto transferModalityRequestDto)throws ApplicationException;

	@Transactional
	Response getPatientServiceOrderList(AdmissionDto admissionDto)throws ApplicationException;

	@Transactional
	Response searchPatientServiceOrdersByMulCriteria(PatientSearchDto patientSearchDto) throws ApplicationException;

}
