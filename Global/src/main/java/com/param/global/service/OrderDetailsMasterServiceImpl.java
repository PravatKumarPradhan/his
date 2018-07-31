package com.param.global.service;

import java.util.List;
import java.util.ListIterator;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dao.IOrderDetailsDiscountDao;
import com.param.global.dao.IOrderDetailsMasterDao;
import com.param.global.dto.OrderDetailsDiscountDto;
import com.param.global.dto.OrderDetailsMasterDto;

import in.param.exception.ApplicationException;

@Service
@SuppressWarnings("rawtypes")
public class OrderDetailsMasterServiceImpl implements IOrderDetailsMasterService,ICommonConstants{

	@Autowired
	private IOrderDetailsMasterDao iOrderDetailsMasterDao;
	
	@Autowired
	private IOrderDetailsDiscountDao iOrderDetailsDiscountDao;
	
	@Override
	@Transactional
	public Response updateIsBilledStatus(List<Integer> orderDetailsList,Integer billId) throws ApplicationException {
		try {
				return iOrderDetailsMasterDao.updateIsBilledStatus(orderDetailsList, billId);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	@Override
	@Transactional
	public Response updateOrderDetailsWithDiscount(List<OrderDetailsMasterDto> orderDetailsList) throws ApplicationException {
		try {
			Integer ordDtlsSuccessCount=0;
			Integer ordDisSuccessCount=0;
			ListIterator<OrderDetailsMasterDto> iterator = 	orderDetailsList.listIterator();
			while(iterator.hasNext())
			{
				OrderDetailsMasterDto detailsMasterDto=iterator.next();
				
				if(!detailsMasterDto.getOldNetPay().equals(detailsMasterDto.getNetAmount()))
				{
					Response res = iOrderDetailsMasterDao.updateOrderDetails(detailsMasterDto);
					if(res.getStatus().equals(SUCCESS))
					{
						ordDtlsSuccessCount++;
						for(OrderDetailsDiscountDto orderDetailsDiscountDto : detailsMasterDto.getDiscountDetailsList())
						{
								orderDetailsDiscountDto.setOrderDetailsId(detailsMasterDto.getOrderDetailsId());
							Response res2=iOrderDetailsDiscountDao.saveOrderDetailsDiscount(orderDetailsDiscountDto);
							if(res2.getStatus().equals(SUCCESS))
								ordDisSuccessCount++;
						}
					}
				}
			}
			if(orderDetailsList.equals(orderDetailsList.size()))
				return new Response<>(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, null,  null);
			else
				return new Response<>(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	@Override
	public Response saveOrderDetailsMaster(OrderDetailsMasterDto orderDetailsMasterDto) throws ApplicationException {
		try {
			return iOrderDetailsMasterDao.saveOrderDetailsMaster(orderDetailsMasterDto);
	}catch (Exception e) {
		e.printStackTrace();
	}
	return new Response<>(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response cancelOrder(OrderDetailsMasterDto orderDetailsMasterDto) throws ApplicationException {
		try {
			 	//updating previous record
				iOrderDetailsMasterDao.updateOrderDetails(orderDetailsMasterDto);
			 
				//adding new record
			 	OrderDetailsMasterDto saveDetailsMasterDto = new OrderDetailsMasterDto();
			 	saveDetailsMasterDto=orderDetailsMasterDto;
				 	saveDetailsMasterDto.setOrderDetailsId(null);
				 	saveDetailsMasterDto.setOrderQty(-orderDetailsMasterDto.getOrderQty());
				 	saveDetailsMasterDto.setOldOrdDtlId(orderDetailsMasterDto.getOrderDetailsId());
				 	saveDetailsMasterDto.setCreatedDate(orderDetailsMasterDto.getUpdatedDate());
				 	saveDetailsMasterDto.setCreatedBy(orderDetailsMasterDto.getCreatedBy());
			 	iOrderDetailsMasterDao.saveOrderDetailsMaster(orderDetailsMasterDto);
			 	
		return new Response<>(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null); 	
	}catch (Exception e) {
		e.printStackTrace();
	}
	return new Response<>(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
}
