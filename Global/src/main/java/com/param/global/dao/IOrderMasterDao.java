package com.param.global.dao;

import com.param.global.common.Response;
import com.param.global.dto.OrderMasterDto;
import com.param.global.model.OrderMaster;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IOrderMasterDao extends IGenericDao<OrderMaster, Integer>{
	public Response saveOrderMaster(OrderMasterDto orderMasterDto)throws ApplicationException;
}
