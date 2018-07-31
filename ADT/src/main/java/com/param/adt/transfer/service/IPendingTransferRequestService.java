package com.param.adt.transfer.service;

import javax.transaction.Transactional;

import com.param.adt.common.Response;
import com.param.adt.transfer.dto.TransferDto;

import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IPendingTransferRequestService {

	@Transactional
	Response getPendingTransferRequestListForDoctor(TransferDto transferDto)throws ApplicationException;

	/*@Transactional
	Response acceptRejectTransferRequest(TransferDto transferDto) throws ApplicationException;
*/
	@Transactional
	Response getPendingTransferRequestListForADT(TransferDto transferDto)throws ApplicationException;

	@Transactional
	Response acceptTransferRequestForADT(TransferDto transferDto)throws ApplicationException;

	@Transactional
	Response rejectTransferRequestForADT(TransferDto transferDto)throws ApplicationException;

	@Transactional
	Response acceptFinalTransferRequest(TransferDto transferDto)throws ApplicationException;

	@Transactional
	Response rejectFinalTransferRequest(TransferDto transferDto)throws ApplicationException;

	@Transactional
	Response acceptTransferRequestForDoctor(TransferDto transferDto)throws ApplicationException;

	@Transactional
	Response rejectTransferRequestForDoctor(TransferDto transferDto)throws ApplicationException;

	@Transactional
	Response getFinalTransferRequestList(TransferDto transferDto)throws ApplicationException;

	@Transactional
	Response acceptTransferRequest(TransferDto transferDto)throws ApplicationException;

	@Transactional
	Response rejectTransferRequest(TransferDto transferDto)throws ApplicationException;

	@Transactional
	Response getFinalTransferRequestListByAdmission(TransferDto transferDto)throws ApplicationException;


}
