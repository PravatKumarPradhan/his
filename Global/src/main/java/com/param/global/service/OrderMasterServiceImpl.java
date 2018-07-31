package com.param.global.service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dao.IOrderDetailsDiscountDao;
import com.param.global.dao.IOrderDetailsMasterDao;
import com.param.global.dao.IOrderMasterDao;
import com.param.global.dto.OrderDetailsDiscountDto;
import com.param.global.dto.OrderDetailsMasterDto;
import com.param.global.dto.OrderMasterDto;

import in.param.exception.ApplicationException;

@Service
@SuppressWarnings("rawtypes")
public class OrderMasterServiceImpl implements IOrderMasterService, ICommonConstants{

	@Autowired
	private IOrderMasterDao iOrderMasterDao; 
	
	@Autowired
	private IOrderDetailsMasterDao iOrderDetailsMasterDao; 
	
	@Autowired
	private IOrderDetailsDiscountDao iOrderDetailsDiscountDao;
	
	@Override
	@Transactional
	public Response saveOrderMasterWithDiscount(OrderMasterDto orderMasterDto) throws ApplicationException {
		try {
			Map<Integer,Integer> packageOrders = new LinkedHashMap<Integer,Integer>();
		  orderMasterDto.setOrderNo(getOrderNumber());
			Response orderMasterResponse = iOrderMasterDao.saveOrderMaster(orderMasterDto);
			if(orderMasterResponse.getStatus().equals(SUCCESS) && orderMasterResponse.getObject() != null && orderMasterDto.getListOrderDetailsMasterDto() != null) 
			{
				Integer orderId = (Integer) orderMasterResponse.getObject();
				
				for(OrderDetailsMasterDto orderDetailsMasterDto : orderMasterDto.getListOrderDetailsMasterDto()) 
				{
					orderDetailsMasterDto.setOrderId(orderId);
					Response orderDetails=iOrderDetailsMasterDao.saveOrderDetailsMaster(orderDetailsMasterDto);
					Integer orderDetailsId = (Integer) orderDetails.getObject();
					if(orderDetailsMasterDto.isPackage() && orderDetailsMasterDto.getPackageId() != null){
						packageOrders.put(orderDetailsMasterDto.getPackageId(), orderDetailsId);
					}
					if(orderDetails.getStatus().equals(SUCCESS) && orderDetailsMasterDto.getDiscountDetailsList().size()>0) 
					{
						if(orderDetailsMasterDto.getDiscountDetailsList().size()>0)
						{
							for(OrderDetailsDiscountDto orderDetailsDiscountDto : orderDetailsMasterDto.getDiscountDetailsList())
							{
									orderDetailsDiscountDto.setOrderDetailsId(orderDetailsId);
								iOrderDetailsDiscountDao.saveOrderDetailsDiscount(orderDetailsDiscountDto);
							}
						}
					}
				}
			}
			return new Response<>(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, null, packageOrders);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}


	@Override
	@Transactional
	public Response saveOrderMaster(OrderMasterDto orderMasterDto) throws ApplicationException {
		try {
		  orderMasterDto.setOrderNo(getOrderNumber());
			Response orderMasterResponse = iOrderMasterDao.saveOrderMaster(orderMasterDto);
			if(orderMasterResponse.getStatus().equals(SUCCESS) && orderMasterResponse.getObject() != null && orderMasterDto.getListOrderDetailsMasterDto() != null) {
				Integer orderId = (Integer) orderMasterResponse.getObject();
				for(OrderDetailsMasterDto orderDetailsMasterDto : orderMasterDto.getListOrderDetailsMasterDto()) {
					orderDetailsMasterDto.setOrderId(orderId);
					iOrderDetailsMasterDao.saveOrderDetailsMaster(orderDetailsMasterDto);
					
				}
			}
			return new Response<>(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, null, null);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	public static String getOrderNumber() {
      String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
      StringBuilder salt = new StringBuilder("ORD00");
      Random rnd = new Random();
      while (salt.length() < 10) {
          int index = (int) (rnd.nextFloat() * SALTCHARS.length());
          salt.append(SALTCHARS.charAt(index));
      }
      String saltStr = salt.toString();
      return saltStr;

  }
	
}
