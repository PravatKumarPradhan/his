package com.param.adt.transfer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.adt.common.ICommonConstants;
import com.param.adt.common.Response;
import com.param.adt.transfer.dao.ITransferToICUDao;
import com.param.adt.transfer.dto.TransferDto;
import com.param.global.dto.DoctorMasterDto;

import in.param.exception.ApplicationException;
@Service
@SuppressWarnings({"rawtypes","unchecked"})
public class TransferToICUServiceImpl implements ITransferToICUService,ICommonConstants
{

	@Autowired
	ITransferToICUDao iTransferToICUDao;

	@Override
	public Response getDoctorForICU(DoctorMasterDto doctorMasterDto) throws ApplicationException {
		try {
			return iTransferToICUDao.getDoctorForICU(doctorMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response saveICUTransferRequest(TransferDto transferDto) throws ApplicationException {
		try {
			
			return iTransferToICUDao.saveICUTransferRequest(transferDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response saveICUTransfer(TransferDto transferDto) throws ApplicationException {
		try {
			return iTransferToICUDao.saveICUTransfer(transferDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getICUTransferRequestList(TransferDto transferDto) throws ApplicationException {
		try {
			return iTransferToICUDao.getICUTransferRequestList(transferDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getICUBedsByIUCTypeId(TransferDto transferDto) throws ApplicationException {
		try {
			return iTransferToICUDao.getICUBedsByIUCTypeId(transferDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getICUTransferRequestListByAdmissionId(TransferDto transferDto) throws ApplicationException {
		try {
			return iTransferToICUDao.getICUTransferRequestListByAdmissionId(transferDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

}
