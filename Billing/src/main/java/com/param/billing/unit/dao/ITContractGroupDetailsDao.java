package com.param.billing.unit.dao;

import com.param.global.common.Response;
import com.param.service.dto.TContractGroupDetailsDto;
import com.param.service.global.model.TContractGroupDetails;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface ITContractGroupDetailsDao extends IGenericDao<TContractGroupDetails,Integer> {
	public  Response saveContractGroupDetails(TContractGroupDetailsDto tContractGroupDetailsDto)throws ApplicationException;

}
