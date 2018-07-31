package com.param.global.dao;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dto.OrderDetailsDiscountDto;
import com.param.global.model.OrderDetailsDiscount;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings({"rawtypes","unchecked"})
public class OrderDetailsDiscountDaoImpl extends GenericDao<OrderDetailsDiscount, Integer>implements IOrderDetailsDiscountDao, ICommonConstants{

	public OrderDetailsDiscountDaoImpl() {
		super(OrderDetailsDiscount.class);
	}

	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
	private DozerBeanMapper mapper;
	
	@Override
	public Response saveOrderDetailsDiscount(OrderDetailsDiscountDto orderDetailsDiscountDto)
			throws ApplicationException {
		try {
				OrderDetailsDiscount orderDetailsDiscount=mapper.map(orderDetailsDiscountDto, OrderDetailsDiscount.class, "OrderDetailsDiscountDto_to_OrderDetailsDiscount");
				save(orderDetailsDiscount);
				return new Response<>(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, null, null);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
		
	}
	
	@Override
	public Response getOrderDetailsDiscountByOrderDetailsId(Integer orderDetailsId)
			throws ApplicationException {
			try {
				List<OrderDetailsDiscountDto> orderDetailsDiscountDtosList = sessionFactory.getCurrentSession()
						.getNamedQuery("GET_ORDERDETAILS_DISCOUNT_BY_ORDER_DETAILS_ID")
						.setInteger("orderDetailsId", orderDetailsId)
						.setResultTransformer(Transformers.aliasToBean(OrderDetailsDiscountDto.class)).list();
				
				return new Response(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, orderDetailsDiscountDtosList, null);
			}catch (HibernateException he) {
				he.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return new Response(ERROR, SUCCESS_CODE, SUCCESS_MESSAGE, null, null);
	}

	@Override
	public Response inactiveCancelledDiscount(Integer orderDetailsDiscountId) throws ApplicationException {
		try {
			
			sessionFactory.getCurrentSession().createQuery(
							"UPDATE OrderDetailsDiscount set status='I',isCancel='Y' WHERE orderDetailsDiscountId=" + orderDetailsDiscountId)
					.executeUpdate();
			
			
			return new Response(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, null, null);
		}catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new Response(ERROR, SUCCESS_CODE, SUCCESS_MESSAGE, null, null);
	}

	
	
	
}
