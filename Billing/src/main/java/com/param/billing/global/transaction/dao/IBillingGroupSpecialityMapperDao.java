package com.param.billing.global.transaction.dao;

import com.param.billing.common.Response;
import com.param.billing.global.transaction.dto.BillingGroupSpecialityMapperDto;
import com.param.billing.global.transaction.model.BillingGroupSpecialityMapper;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IBillingGroupSpecialityMapperDao extends IGenericDao<BillingGroupSpecialityMapper, Integer>{
	public Response saveBillingGroupSpecialityMapper(BillingGroupSpecialityMapperDto billingGroupSpecialityMapperDto)throws ApplicationException;
	public Response deleteBillingGroupSpecialityMapper(BillingGroupSpecialityMapperDto billingGroupSpecialityMapperDto)throws ApplicationException;
}
