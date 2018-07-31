package com.param.adt.transfer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.adt.common.ICommonConstants;
import com.param.adt.common.Response;
import com.param.adt.transfer.dao.ITransferToOTDao;
import com.param.adt.transfer.dto.TransferOTRequestDto;
import com.param.global.dto.AdmissionDto;

import in.param.exception.ApplicationException;
@SuppressWarnings({"rawtypes","unchecked"})
@Service
public class TransferToOTServiceImpl implements ITransferToOTService,ICommonConstants{

	@Autowired
	ITransferToOTDao iTransferToOTDao;

	
	@Override
	public Response getOTRequestListByAdmissionId(AdmissionDto admissionDto) throws ApplicationException {
		try {
			return iTransferToOTDao.getOTRequestListByAdmissionId(admissionDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}


	@Override
	public Response saveOTTransferRequest(TransferOTRequestDto transferOTRequestDto) throws ApplicationException {
		try {
			return iTransferToOTDao.saveOTTransferRequest(transferOTRequestDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}


	@Override
	public Response getOTTransferRequestList(TransferOTRequestDto transferOTRequestDto)
			throws ApplicationException {
		try {
			return iTransferToOTDao.getOTTransferRequestList(transferOTRequestDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}


	@Override
	public Response getOTRequestList(AdmissionDto admissionDto) throws ApplicationException {
		try {
			return iTransferToOTDao.getOTRequestList(admissionDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}


	@Override
	public Response getOTTransferRequestListAdmissionId(TransferOTRequestDto transferOTRequestDto)
			throws ApplicationException {
		try {
			return iTransferToOTDao.getOTTransferRequestListAdmissionId(transferOTRequestDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

}
