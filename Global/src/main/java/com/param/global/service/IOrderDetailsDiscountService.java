package com.param.global.service;

import java.util.List;

import com.param.global.common.Response;
import com.param.global.dto.OrderDetailsDiscountDto;

import in.param.exception.ApplicationException;

public interface IOrderDetailsDiscountService {

	Response saveCancelledDiscounts(List<OrderDetailsDiscountDto> cancelledDiscountsList)throws ApplicationException;

	Response getOrderDetailsDiscountByOrderDetailsId(Integer orderDetailsId)throws ApplicationException;

}
