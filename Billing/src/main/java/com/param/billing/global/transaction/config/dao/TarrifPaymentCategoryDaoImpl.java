package com.param.billing.global.transaction.config.dao;

import java.util.List;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

import org.dozer.DozerBeanMapper;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.param.billing.common.ICommonConstants;
import com.param.billing.common.IError;
import com.param.billing.common.Response;
import com.param.billing.global.transaction.config.dto.TarrifPatientCategoryMpprDto;
import com.param.billing.global.transaction.config.dto.TarrifPaymentEntitlementMpprDto;
import com.param.billing.global.transaction.model.ServiceTarrifPaymentEntitlementManager;

@Repository
public class TarrifPaymentCategoryDaoImpl extends GenericDao<ServiceTarrifPaymentEntitlementManager, Integer> implements ITarrifPaymentCategoryDao, ICommonConstants, IError{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private DozerBeanMapper mapper;
	
	public TarrifPaymentCategoryDaoImpl() {
		super(ServiceTarrifPaymentEntitlementManager.class);
	}

	@Override
	public Response savePaymentEntitlementFactor(TarrifPaymentEntitlementMpprDto paymentEntitlementMpprDto)
			throws ApplicationException {
		try {
			paymentEntitlementMpprDto.setServiceTarrifPaymentEntitlementManagerId(null);
			ServiceTarrifPaymentEntitlementManager paymentEntitlementManager = mapper.map(paymentEntitlementMpprDto, ServiceTarrifPaymentEntitlementManager.class,
					"TarrifPaymentEntitlementMpprDto_to_ServiceTarrifPaymentEntitlementManager");
			paymentEntitlementManager = save(paymentEntitlementManager);

			return new Response(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, null, paymentEntitlementManager);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR, COMMON_ERROR_MESSAGE, null, null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response getPaymentCategoryMultiplicationFactorByTarrifId(Integer tarrifMasterId) throws ApplicationException {
		try{
			List<TarrifPaymentEntitlementMpprDto> listPaymentCategoryMpprDto = sessionFactory.getCurrentSession()
				.getNamedQuery("GET_ACTIVE_PAYMENT_TYPE_FACTOR_BY_TARRIF_ID")
				.setInteger("serviceTarrifId", tarrifMasterId)
				.setResultTransformer(Transformers.aliasToBean(TarrifPaymentEntitlementMpprDto.class)).list();
		
			return new Response(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, listPaymentCategoryMpprDto, null);
		
		}catch(HibernateException he){
			throw he;
		}catch (Exception e) {
			throw e;
		}
	}

}
