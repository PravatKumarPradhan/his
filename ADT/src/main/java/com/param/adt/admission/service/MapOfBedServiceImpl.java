package com.param.adt.admission.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.adt.admission.dao.IMapOfBedDao;
import com.param.adt.common.ICommonConstants;
import com.param.adt.common.Response;
import com.param.adt.transfer.dto.TransferDto;

import in.param.exception.ApplicationException;
@Service
@SuppressWarnings({"rawtypes","unchecked"})
public class MapOfBedServiceImpl implements IMapOfBedService,ICommonConstants{

	@Autowired
	IMapOfBedDao iMapOfBedDao;
	
	@Override
	public Response getTransferRequestListByAdmissoinId(TransferDto transferDto) throws ApplicationException 
	{
		try {
			return iMapOfBedDao.getTransferRequestListByAdmissoinId(transferDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

}
