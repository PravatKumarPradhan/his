package com.param.billing.global.service;

import com.param.billing.common.Response;
import com.param.billing.global.dto.CardTypeMasterDto;

import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface ICardTypeMasterService {
	public Response gerActiveCardTypeMaster(CardTypeMasterDto cardTypeMasterDto)throws ApplicationException;
}
