package com.param.billing.global.transaction.dao;

import com.param.billing.exception.BillingException;
import com.param.billing.global.dto.BillingStatusMapperDto;
import com.param.billing.global.transaction.model.BillingStatusMapper;
import com.param.billing.global.transaction.model.BillingStatusMapperId;
import com.param.global.common.Response;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface IBillStatusMapperDao extends IGenericDao<BillingStatusMapper, BillingStatusMapperId>{
	public Response saveBillStatus(BillingStatusMapperDto mapperDto)throws BillingException;
}
