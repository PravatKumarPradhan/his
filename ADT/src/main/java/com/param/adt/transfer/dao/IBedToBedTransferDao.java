package com.param.adt.transfer.dao;

import com.param.adt.common.Response;
import com.param.adt.master.global.dto.BedMasterDto;
import com.param.adt.transfer.dto.TransferDto;
import com.param.adt.transfer.model.Transfer;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IBedToBedTransferDao extends IGenericDao<Transfer, Integer>{

	
	Response getInitiateTransferRequestList(TransferDto transferDto)throws ApplicationException;

	Response getBedListByWardId(BedMasterDto bedMasterDto)throws ApplicationException;

}
