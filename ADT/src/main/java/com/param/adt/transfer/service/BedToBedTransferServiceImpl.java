package com.param.adt.transfer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.adt.common.ICommonConstants;
import com.param.adt.common.Response;
import com.param.adt.master.global.dto.BedMasterDto;
import com.param.adt.transfer.dao.IBedToBedTransferDao;
import com.param.adt.transfer.dto.TransferDto;

import in.param.exception.ApplicationException;

@Service
@SuppressWarnings({ "rawtypes", "unchecked" })
public class BedToBedTransferServiceImpl implements IBedToBedTransferService, ICommonConstants {

	@Autowired
	IBedToBedTransferDao iBedToBedTransferDao;

	@Override
	public Response getInitiateTransferRequestList(TransferDto transferDto) throws ApplicationException {
		try {

			return iBedToBedTransferDao.getInitiateTransferRequestList(transferDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getBedListByWardId(BedMasterDto bedMasterDto) throws ApplicationException {
		try {
			return iBedToBedTransferDao.getBedListByWardId(bedMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

}
