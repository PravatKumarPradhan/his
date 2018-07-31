package com.param.adt.admission.dao;

import com.param.adt.common.Response;
import com.param.adt.transfer.dto.TransferDto;
import com.param.adt.transfer.model.Transfer;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface IMapOfBedDao extends IGenericDao<Transfer, Integer>{

	
	Response getTransferRequestListByAdmissoinId(TransferDto transferDto)throws ApplicationException;

}
