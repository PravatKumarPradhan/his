package com.param.adt.transfer.service;

import javax.transaction.Transactional;

import com.param.adt.common.Response;
import com.param.adt.transfer.dto.TransferDto;

import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface ITransferRequestService {

	@Transactional
	Response getTransferRequestList(TransferDto transferDto)throws ApplicationException;

	@Transactional
	Response saveTransferRequest(TransferDto transferDto)throws ApplicationException;

	@Transactional
	Response getTransferRequestListByAdmissionId(TransferDto transferDto);

}
