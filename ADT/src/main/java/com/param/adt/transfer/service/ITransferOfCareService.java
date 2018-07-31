package com.param.adt.transfer.service;
import javax.transaction.Transactional;

import com.param.adt.common.Response;
import com.param.adt.transfer.dto.TransferDto;

import in.param.exception.ApplicationException;


@SuppressWarnings("rawtypes")
public interface ITransferOfCareService {

	
	@Transactional
	Response saveTransferOfCareRequest(TransferDto transferDto)throws ApplicationException;

	@Transactional
	Response getTransferOfCareRequestList(TransferDto transferDto)throws ApplicationException;

	@Transactional
	Response getTransferOfCareRequestListForDoctor(TransferDto transferDto)throws ApplicationException;

	@Transactional
	Response acceptTransferOfCareRequest(TransferDto transferDto)throws ApplicationException;

	@Transactional
	Response rejectTransferOfCareRequest(TransferDto transferDto)throws ApplicationException;

	@Transactional
	Response acceptConsultOrderRequest(TransferDto transferDto)throws ApplicationException;

	@Transactional
	Response searchConsultOrderRequestByAdmissionId(TransferDto transferDto)throws ApplicationException;



}
