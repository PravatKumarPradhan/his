package com.param.billing.global.transaction.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.billing.common.ICommonConstants;
import com.param.billing.common.Response;
import com.param.billing.global.transaction.dao.IBillingDetailsDao;
import com.param.billing.global.transaction.dto.BillingDetailsDto;

import in.param.exception.ApplicationException;

@Service
@SuppressWarnings("rawtypes")
public class BillingDetailsServiceImpl implements IBillingDetailsService, ICommonConstants{

	@Autowired
	private IBillingDetailsDao iBillingDetailsDao;
	
	@Override
	@Transactional
	public Response saveBillingDetails(BillingDetailsDto billingDetailsDto) throws ApplicationException {
		try {
				return iBillingDetailsDao.saveBillingDetails(billingDetailsDto);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

}
