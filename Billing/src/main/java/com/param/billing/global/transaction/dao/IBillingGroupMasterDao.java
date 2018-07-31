package com.param.billing.global.transaction.dao;

import com.param.billing.common.Response;
import com.param.billing.global.dto.BillingGroupMasterDto;
import com.param.billing.global.model.BillingGroupMaster;
import com.param.billing.global.transaction.dto.BillingGroupSpecialityMasterDto;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IBillingGroupMasterDao extends IGenericDao<BillingGroupMaster, Integer>{
	public Response saveBillingGroupMaster(BillingGroupSpecialityMasterDto billingGroupSpecialityMasterDto)throws ApplicationException;
	public Response getBillingGroupMasterList(BillingGroupMasterDto billingGroupMasterDto)throws ApplicationException;
	public Response getBillingGroupMasterById(BillingGroupMasterDto billingGroupMasterDto)throws ApplicationException;
	public Response changeBillingGroupMasterStatus(BillingGroupMasterDto billingGroupMasterDto)throws ApplicationException;
	public Response updateBillingGroupMaster(BillingGroupSpecialityMasterDto billingGroupSpecialityMasterDto)throws ApplicationException;
	public Response getBillingGroupMasterTotalCount(BillingGroupMasterDto billingGroupMasterDto)throws ApplicationException;
}
