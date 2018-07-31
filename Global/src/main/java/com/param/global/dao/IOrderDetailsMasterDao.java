package com.param.global.dao;

import java.util.List;

import com.param.global.common.Response;
import com.param.global.dto.OrderDetailsMasterDto;
import com.param.global.model.OrderDetailsMaster;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IOrderDetailsMasterDao extends IGenericDao<OrderDetailsMaster, Integer>{
	public Response saveOrderDetailsMaster(OrderDetailsMasterDto orderDetailsMasterDto)throws ApplicationException;
	public Response updateIsBilledStatus(List<Integer> orderDetailsList,Integer serviceBillId) throws ApplicationException;
	public Response updateOrderDetails(OrderDetailsMasterDto detailsMasterDto)throws ApplicationException;
}
