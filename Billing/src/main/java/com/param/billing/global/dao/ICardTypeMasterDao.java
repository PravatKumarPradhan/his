package com.param.billing.global.dao;

import com.param.billing.common.Response;
import com.param.billing.global.dto.CardTypeMasterDto;
import com.param.billing.global.model.CardTypeMaster;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface ICardTypeMasterDao extends IGenericDao<CardTypeMaster, Integer>{
	public Response gerActiveCardTypeMaster(CardTypeMasterDto cardTypeMasterDto)throws ApplicationException;
}
