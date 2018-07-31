package com.param.adt.transfer.dao;

import com.param.adt.common.Response;
import com.param.adt.transfer.dto.TransferDto;
import com.param.adt.transfer.model.TransferRequest;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface ITransferRequestDao extends IGenericDao<TransferRequest, Integer>{
	
	Response getTransferRequestList(TransferDto transferDto)throws ApplicationException;

	Response saveTransferRequest(TransferDto transferDto) throws ApplicationException;

	Response getTransferRequestListByAdmissionId(TransferDto transferDto)throws ApplicationException;

}
