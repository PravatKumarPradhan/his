package com.param.adt.transfer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.adt.common.ICommonConstants;
import com.param.adt.common.Response;
import com.param.adt.transfer.dao.ITransferOfCareDao;
import com.param.adt.transfer.dto.TransferDto;

import in.param.exception.ApplicationException;
@Service
@SuppressWarnings({"rawtypes","unchecked"})
public class TransferOfCareServiceImpl implements ITransferOfCareService,ICommonConstants{

	@Autowired
	ITransferOfCareDao iTransferOfCareDao;
	
	@Override
	public Response saveTransferOfCareRequest(TransferDto transferDto) throws ApplicationException {
		try {
			return iTransferOfCareDao.saveTransferOfCareRequest(transferDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getTransferOfCareRequestList(TransferDto transferDto) throws ApplicationException {
		try {
			return iTransferOfCareDao.getTransferOfCareRequestList(transferDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getTransferOfCareRequestListForDoctor(TransferDto transferDto) throws ApplicationException {
		try {
			return iTransferOfCareDao.getTransferOfCareRequestListForDoctor(transferDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response acceptTransferOfCareRequest(TransferDto transferDto) {
		try {
			return iTransferOfCareDao.acceptTransferOfCareRequest(transferDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response rejectTransferOfCareRequest(TransferDto transferDto) throws ApplicationException {
		try {
			return iTransferOfCareDao.rejectTransferOfCareRequest(transferDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response acceptConsultOrderRequest(TransferDto transferDto) throws ApplicationException {
		try {
			return iTransferOfCareDao.acceptConsultOrderRequest(transferDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response searchConsultOrderRequestByAdmissionId(TransferDto transferDto) throws ApplicationException {
		try {
			return iTransferOfCareDao.searchConsultOrderRequestByAdmissionId(transferDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}


}
