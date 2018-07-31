package com.param.billing.global.transaction.dao;

import org.dozer.DozerBeanMapper;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.param.billing.common.ICommonConstants;
import com.param.billing.common.IError;
import com.param.billing.common.Response;
import com.param.billing.global.transaction.dto.BillingDetailsDto;
import com.param.billing.global.transaction.model.BillingDetails;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings({ "unchecked", "rawtypes" })
public class BillingDetailsDaoImpl extends GenericDao<BillingDetails, Integer> implements IBillingDetailsDao, ICommonConstants, IError{

	public BillingDetailsDaoImpl() {
		super(BillingDetails.class);
	}

	@Autowired
	private DozerBeanMapper mapper;
	
	@Override
	public Response saveBillingDetails(BillingDetailsDto billingDetailsDto) throws ApplicationException {
		try {
			BillingDetails billingDetails = mapper.map(billingDetailsDto, BillingDetails.class,"BillingDetailsDto_to_BillingDetails");
				billingDetails = save(billingDetails);
			return new Response<>(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, null, billingDetails);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	}
	
	
	
}
