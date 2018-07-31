package com.param.adt.transfer.dao;

import com.param.adt.common.Response;
import com.param.adt.transfer.dto.TransferDto;
import com.param.adt.transfer.model.TransferRequest;
import com.param.global.dto.DoctorMasterDto;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface ITransferToICUDao extends IGenericDao<TransferRequest, Integer>{

	Response getDoctorForICU(DoctorMasterDto doctorMasterDto)throws ApplicationException;

	Response saveICUTransferRequest(TransferDto transferDto)throws ApplicationException;

	Response saveICUTransfer(TransferDto transferDto)throws ApplicationException;

	Response getICUTransferRequestList(TransferDto transferDto)throws ApplicationException;

	Response getICUBedsByIUCTypeId(TransferDto transferDto)throws ApplicationException;

	Response getICUTransferRequestListByAdmissionId(TransferDto transferDto)throws ApplicationException;

}
