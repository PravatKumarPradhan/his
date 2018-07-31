package com.param.global.service;

import java.util.List;

import com.param.global.common.Response;
import com.param.global.dto.OrderDetailsMasterDto;

import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IOrderDetailsMasterService {
	Response updateIsBilledStatus(List<Integer> orderDetailsList, Integer billMasterId) throws ApplicationException;
	Response saveOrderDetailsMaster(OrderDetailsMasterDto orderDetailsMasterDto)throws ApplicationException;
	Response updateOrderDetailsWithDiscount(List<OrderDetailsMasterDto> orderDetailsList) throws ApplicationException;
	Response cancelOrder(OrderDetailsMasterDto orderDetailsMasterDto) throws ApplicationException;

}
