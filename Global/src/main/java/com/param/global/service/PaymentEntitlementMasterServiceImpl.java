package com.param.global.service;

import in.param.exception.ApplicationException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.global.common.Response;
import com.param.global.dao.IPaymentEntitlementMasterDao;
import com.param.global.dto.PaymentEntitlementMasterDto;

@Service
@Transactional
public class PaymentEntitlementMasterServiceImpl implements IPaymentEntitlementMasterService{

	@Autowired
	IPaymentEntitlementMasterDao iPaymentEntitlementMasterDao;
	
	@Override
	public Response getPaymentEntitlementMaster(
			PaymentEntitlementMasterDto paymentEntitlementMasterDto)
			throws ApplicationException {
		return iPaymentEntitlementMasterDao.getPaymentEntitlementMaster(paymentEntitlementMasterDto);
	}

	@Override
	public Response getPaymentEntitlementListByOrgId(int orgId) throws ApplicationException {
		return iPaymentEntitlementMasterDao.getPaymentEntitlementListByOrgId(orgId);
	}

}
