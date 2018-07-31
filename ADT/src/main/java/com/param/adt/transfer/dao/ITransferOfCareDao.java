package com.param.adt.transfer.dao;

import com.param.adt.common.Response;
import com.param.adt.transfer.dto.TransferDto;
import com.param.adt.transfer.model.TransferOfCareRequest;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface ITransferOfCareDao extends IGenericDao<TransferOfCareRequest, Integer>{

	
	Response saveTransferOfCareRequest(TransferDto transferDto)throws ApplicationException;

	Response getTransferOfCareRequestList(TransferDto transferDto) throws ApplicationException;

	Response getTransferOfCareRequestListForDoctor(TransferDto transferDto)throws ApplicationException;

	Response acceptTransferOfCareRequest(TransferDto transferDto)throws ApplicationException;

	Response rejectTransferOfCareRequest(TransferDto transferDto)throws ApplicationException;

	Response acceptConsultOrderRequest(TransferDto transferDto)throws ApplicationException;

	Response searchConsultOrderRequestByAdmissionId(TransferDto transferDto)throws ApplicationException;


}
