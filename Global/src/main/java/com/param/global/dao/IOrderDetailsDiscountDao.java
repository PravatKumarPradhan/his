package com.param.global.dao;

import com.param.global.common.Response;
import com.param.global.dto.OrderDetailsDiscountDto;
import com.param.global.model.OrderDetailsDiscount;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface IOrderDetailsDiscountDao extends IGenericDao<OrderDetailsDiscount, Integer> {
	
	Response saveOrderDetailsDiscount(OrderDetailsDiscountDto orderDetailsDiscountDto) throws ApplicationException;

	Response getOrderDetailsDiscountByOrderDetailsId(Integer orderDetailsId) throws ApplicationException;

	Response inactiveCancelledDiscount(Integer oldOrderDetailsDiscountId)throws ApplicationException;

}
