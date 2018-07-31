package com.param.global.dao;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dto.OrderDetailsMasterDto;
import com.param.global.model.OrderDetailsMaster;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings("rawtypes")
public class OrderDetailsMasterDaoImpl extends GenericDao<OrderDetailsMaster, Integer> implements IOrderDetailsMasterDao, ICommonConstants{

	public OrderDetailsMasterDaoImpl() {
		super(OrderDetailsMaster.class);
	}

	@Autowired
	private DozerBeanMapper mapper;
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public Response saveOrderDetailsMaster(OrderDetailsMasterDto orderDetailsMasterDto) throws ApplicationException {
		try {
			OrderDetailsMaster orderDetailsMaster = mapper.map(orderDetailsMasterDto, OrderDetailsMaster.class, "OrderDetailsMasterDto_to_OrderDetailsMaster");
			Integer orderDetailsId = (Integer) sessionFactory.getCurrentSession().save(orderDetailsMaster);
			return new Response<>(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, null, orderDetailsId);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response updateIsBilledStatus(List<Integer> orderDetailsList,Integer serviceBillId) throws ApplicationException {
		try {	
			sessionFactory.getCurrentSession().getNamedQuery("UPDATE_IS_BILLED_STATUS")
				.setParameterList("listOrderDetailsId", orderDetailsList)
				.setInteger("serviceBillId",serviceBillId )
				.executeUpdate();
			/*String query="UPDATE public.t_order_details set service_is_billed="+status+" where order_details_id="+orderDetailsId;
			SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery(query);
			sqlQuery.executeUpdate();*/
			return new Response<>(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, null, null);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response updateOrderDetails(OrderDetailsMasterDto orderDetailsMasterDto) throws ApplicationException {
		try {	
			OrderDetailsMaster orderDetailsMaster = mapper.map(orderDetailsMasterDto, OrderDetailsMaster.class, "OrderDetailsMasterDto_to_OrderDetailsMaster");
			sessionFactory.getCurrentSession().update(orderDetailsMaster);
			return new Response<>(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, null, orderDetailsMaster);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

}
