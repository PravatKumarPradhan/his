package com.param.adt.transfer.service;

import javax.transaction.Transactional;

import com.param.adt.common.Response;
import com.param.adt.transfer.dto.TransferOTRequestDto;
import com.param.global.dto.AdmissionDto;

import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface ITransferToOTService {

	@Transactional
	Response getOTRequestListByAdmissionId(AdmissionDto admissionDto)throws ApplicationException;

	@Transactional
	Response saveOTTransferRequest(TransferOTRequestDto transferOTRequestDto)throws ApplicationException;

	@Transactional
	Response getOTTransferRequestList(TransferOTRequestDto transferOTRequestDto)throws ApplicationException;

	@Transactional
	Response getOTRequestList(AdmissionDto admissionDto)throws ApplicationException;

	@Transactional
	Response getOTTransferRequestListAdmissionId(TransferOTRequestDto transferOTRequestDto)throws ApplicationException;

}
