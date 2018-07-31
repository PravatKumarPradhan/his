package com.param.global.service;

import com.param.global.common.Response;
import com.param.global.dto.OrderMasterDto;

import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IOrderMasterService {
	public Response saveOrderMaster(OrderMasterDto orderMasterDto)throws ApplicationException;
	public Response saveOrderMasterWithDiscount(OrderMasterDto orderMasterDto)throws ApplicationException;
}
