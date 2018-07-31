package com.param.adt.transfer.service;

import javax.transaction.Transactional;

import com.param.adt.common.Response;
import com.param.adt.transfer.dto.TransferDto;
import com.param.global.dto.DoctorMasterDto;

import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface ITransferToICUService {

	@Transactional
	Response getDoctorForICU(DoctorMasterDto doctorMasterDto)throws ApplicationException;

	@Transactional
	Response saveICUTransferRequest(TransferDto transferDto)throws ApplicationException;

	@Transactional
	Response saveICUTransfer(TransferDto transferDto)throws ApplicationException;

	@Transactional
	Response getICUTransferRequestList(TransferDto transferDto)throws ApplicationException;

	@Transactional
	Response getICUBedsByIUCTypeId(TransferDto transferDto)throws ApplicationException;

	@Transactional
	Response getICUTransferRequestListByAdmissionId(TransferDto transferDto)throws ApplicationException;

}
