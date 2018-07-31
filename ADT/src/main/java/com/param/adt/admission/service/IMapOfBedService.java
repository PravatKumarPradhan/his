package com.param.adt.admission.service;

import javax.transaction.Transactional;

import com.param.adt.common.Response;
import com.param.adt.transfer.dto.TransferDto;

import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IMapOfBedService {

	@Transactional
	Response getTransferRequestListByAdmissoinId(TransferDto transferDto)throws ApplicationException;


}
