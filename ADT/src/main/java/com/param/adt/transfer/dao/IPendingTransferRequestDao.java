package com.param.adt.transfer.dao;

import com.param.adt.common.Response;
import com.param.adt.transfer.dto.TransferDto;
import com.param.adt.transfer.model.Transfer;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IPendingTransferRequestDao extends IGenericDao<Transfer, Integer>{

	Response getPendingTransferRequestListForDoctor(TransferDto transferDto) throws ApplicationException;

	Response getPendingTransferRequestListForADT(TransferDto transferDto)throws ApplicationException;

	Response acceptTransferRequestForADT(TransferDto transferDto)throws ApplicationException;

	Response rejectTransferRequestForADT(TransferDto transferDto)throws ApplicationException;

	Response acceptFinalTransferRequest(TransferDto transferDto)throws ApplicationException;

	Response rejectFinalTransferRequest(TransferDto transferDto)throws ApplicationException;

	Response acceptTransferRequestForDoctor(TransferDto transferDto)throws ApplicationException;

	Response rejectTransferRequestForDoctor(TransferDto transferDto)throws ApplicationException;

	Response getFinalTransferRequestList(TransferDto transferDto)throws ApplicationException;

	Response acceptTransferRequest(TransferDto transferDto)throws ApplicationException;

	Response rejectTransferRequest(TransferDto transferDto)throws ApplicationException;

	Response getFinalTransferRequestListByAdmission(TransferDto transferDto)throws ApplicationException;



}
