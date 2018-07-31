package com.param.global.dao;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dto.OrderMasterDto;
import com.param.global.model.OrderMaster;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings({ "rawtypes", "unchecked" })
public class OrderMasterDaoImpl extends GenericDao<OrderMaster, Integer> implements IOrderMasterDao, ICommonConstants{

	public OrderMasterDaoImpl() {
		super(OrderMaster.class);
	}

	@Autowired
	private DozerBeanMapper mapper;
	
	@Override
	public Response saveOrderMaster(OrderMasterDto orderMasterDto) throws ApplicationException {
		try {
			OrderMaster orderMaster = mapper.map(orderMasterDto, OrderMaster.class, "OrderMasterDto_to_OrderMaster");
			Integer orderId= (Integer) sessionFactory.getCurrentSession().save(orderMaster);
			return new Response<>(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, null, orderId);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE,COMMON_ERROR_MESSAGE, null, null);
	}

}
