package com.param.adt.transfer.dao;

import com.param.adt.common.Response;
import com.param.adt.transfer.dto.TransferOTRequestDto;
import com.param.adt.transfer.model.TransferOTRequest;
import com.param.global.dto.AdmissionDto;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface ITransferToOTDao extends IGenericDao<TransferOTRequest, Integer>{
	
	Response getOTRequestListByAdmissionId(AdmissionDto admissionDto)throws ApplicationException;

	Response saveOTTransferRequest(TransferOTRequestDto transferOTRequestDto)throws ApplicationException;

	Response getOTTransferRequestList(TransferOTRequestDto transferOTRequestDto)throws ApplicationException;

	Response getOTRequestList(AdmissionDto admissionDto)throws ApplicationException;

	Response getOTTransferRequestListAdmissionId(TransferOTRequestDto transferOTRequestDto)throws ApplicationException;

}
