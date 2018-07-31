package com.param.adt.transfer.dao;

import com.param.adt.common.Response;
import com.param.adt.transfer.dto.TransferModalityRequestDto;
import com.param.adt.transfer.model.TransferModalityRequest;
import com.param.global.dto.AdmissionDto;
import com.param.global.dto.PatientSearchDto;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IModalityTransferDao extends IGenericDao<TransferModalityRequest, Integer>{

	
	Response searchAdmittedPatientByNameAndUhid(AdmissionDto admissionDto)throws ApplicationException;

	Response getPatientServiceOrderListByAdmissionId(AdmissionDto admissionDto)throws ApplicationException;

	Response saveModalityTransferRequest(TransferModalityRequestDto transferModalityRequestDto)throws ApplicationException;

	Response getModalityTransferRequestList(TransferModalityRequestDto transferModalityRequestDto)throws ApplicationException;

	Response getPatientServiceOrderList(AdmissionDto admissionDto)throws ApplicationException;

	Response searchPatientServiceOrdersByMulCriteria(PatientSearchDto patientSearchDto)throws ApplicationException;


}
