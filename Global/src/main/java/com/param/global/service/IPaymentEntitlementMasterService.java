package com.param.global.service;

import in.param.exception.ApplicationException;

import com.param.global.common.Response;
import com.param.global.dto.PaymentEntitlementMasterDto;

public interface IPaymentEntitlementMasterService {

	Response getPaymentEntitlementMaster(PaymentEntitlementMasterDto paymentEntitlementMasterDto) throws ApplicationException;
	public Response getPaymentEntitlementListByOrgId(int orgId)throws ApplicationException;
}
