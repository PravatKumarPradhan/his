package com.param.billing.global.transaction.service;

import com.param.billing.common.Response;
import com.param.billing.global.transaction.dto.BillingGroupSpecialityMasterDto;

import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IBillingGroupSpecialityMapperService {
	public Response saveBillingGroupSpecialityMapper(BillingGroupSpecialityMasterDto billingGroupSpecialityMasterDto)throws ApplicationException;
}
