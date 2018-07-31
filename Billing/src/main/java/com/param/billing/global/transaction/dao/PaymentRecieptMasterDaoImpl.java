package com.param.billing.global.transaction.dao;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.param.billing.common.ICommonConstants;
import com.param.billing.common.Response;
import com.param.billing.global.transaction.dto.PaymentRecieptMasterDto;
import com.param.billing.global.transaction.model.PaymentRecieptMaster;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;
@Repository
@SuppressWarnings("rawtypes")
public class PaymentRecieptMasterDaoImpl extends GenericDao<PaymentRecieptMaster, Integer> implements IPaymentRecieptMasterDao,ICommonConstants{

	public PaymentRecieptMasterDaoImpl() {
		super(PaymentRecieptMaster.class);
	}

	@Autowired
	private DozerBeanMapper mapper;
	
	@Override
	public Response savePaymentRecieptMaster(PaymentRecieptMasterDto paymentRecieptMasterDto)
			throws ApplicationException {
		try {
			PaymentRecieptMaster paymentRecieptMaster = mapper.map(paymentRecieptMasterDto, PaymentRecieptMaster.class, "PaymentRecieptMasterDto_to_PaymentRecieptMaster"); 
			paymentRecieptMaster = save(paymentRecieptMaster);
			return new Response<>(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, null, paymentRecieptMaster);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	
}

