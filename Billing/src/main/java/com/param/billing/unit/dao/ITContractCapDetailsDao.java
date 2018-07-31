package com.param.billing.unit.dao;

import com.param.global.common.Response;
import com.param.service.dto.TContractCapDetailsDto;
import com.param.service.global.model.TContractCapDetails;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface ITContractCapDetailsDao extends IGenericDao<TContractCapDetails,Integer> {
	public  Response saveContractCapDetails(TContractCapDetailsDto tContractCapDetails)throws ApplicationException;

}
