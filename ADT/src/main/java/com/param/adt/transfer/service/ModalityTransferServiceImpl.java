package com.param.adt.transfer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.adt.common.ICommonConstants;
import com.param.adt.common.Response;
import com.param.adt.transfer.dao.IModalityTransferDao;
import com.param.adt.transfer.dto.TransferModalityRequestDto;
import com.param.global.dto.AdmissionDto;
import com.param.global.dto.PatientSearchDto;

import in.param.exception.ApplicationException;
@Service
@SuppressWarnings({"rawtypes","unchecked"})
public class ModalityTransferServiceImpl implements IModalityTransferService,ICommonConstants
{

	@Autowired
	IModalityTransferDao iModalityTransferDao;
	
	
	@Override
	public Response searchAdmittedPatientByNameAndUhid(AdmissionDto admissionDto) throws ApplicationException {
		try {
			return iModalityTransferDao.searchAdmittedPatientByNameAndUhid(admissionDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}


	@Override
	public Response getPatientServiceOrderListByAdmissionId(AdmissionDto admissionDto) throws ApplicationException {
		try {
			return iModalityTransferDao.getPatientServiceOrderListByAdmissionId(admissionDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}


	@Override
	public Response saveModalityTransferRequest(TransferModalityRequestDto transferModalityRequestDto)
			throws ApplicationException {
		try {
			transferModalityRequestDto.setTransferStatusId(1);
			return iModalityTransferDao.saveModalityTransferRequest(transferModalityRequestDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}


	@Override
	public Response getModalityTransferRequestList(TransferModalityRequestDto transferModalityRequestDto)
			throws ApplicationException {
		try {
			return iModalityTransferDao.getModalityTransferRequestList(transferModalityRequestDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}


	@Override
	public Response getPatientServiceOrderList(AdmissionDto admissionDto) throws ApplicationException {
		try {
			return iModalityTransferDao.getPatientServiceOrderList(admissionDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}


	@Override
	public Response searchPatientServiceOrdersByMulCriteria(PatientSearchDto patientSearchDto)
			throws ApplicationException {
		try {
			return iModalityTransferDao.searchPatientServiceOrdersByMulCriteria(patientSearchDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}


	
}
