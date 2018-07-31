package com.param.billing.global.transaction.dao;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.param.billing.common.ICommonConstants;
import com.param.billing.common.Response;
import com.param.billing.global.transaction.dto.PaymentReceiptDetailsDto;
import com.param.billing.global.transaction.model.PaymentReceiptDetails;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings("rawtypes")
public class PaymentRecieptDetailsDaoImpl extends GenericDao<PaymentReceiptDetails, Integer> implements IPaymentRecieptDetailsDao,ICommonConstants{

	public PaymentRecieptDetailsDaoImpl() {
		super(PaymentReceiptDetails.class);
	}

	@Autowired
	private DozerBeanMapper mapper;
	
	@Override
	public Response savePaymentRecieptDetails(PaymentReceiptDetailsDto paymentReceiptDetailsDto)
			throws ApplicationException {
		try {
			PaymentReceiptDetails paymentRecieptDetails = mapper.map(paymentReceiptDetailsDto, PaymentReceiptDetails.class,"PaymentReceiptDetailsDto_to_PaymentRecieptDetails");
			paymentRecieptDetails=save(paymentRecieptDetails);
			return new Response<>(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, null, paymentRecieptDetails);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

}
