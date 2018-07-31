package com.param.billing.global.transaction.dao;

import com.param.billing.exception.BillingException;
import com.param.billing.global.dto.BillListReqDto;
import com.param.billing.global.transaction.dto.BillingMasterDto;
import com.param.billing.global.transaction.model.BillingMaster;
import com.param.global.common.Response;
import com.param.global.dto.ServiceMasterDto;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface IBillingMasterDao extends IGenericDao<BillingMaster, Integer>{
	public Response saveBillingMaster(BillingMasterDto billingMasterDto)throws ApplicationException;
	public Response getBillDetailsForPayment(BillingMasterDto billingMasterDto)throws ApplicationException;
	public Response updateBillSummaryAmount(BillingMasterDto billingMasterDto)throws ApplicationException;
	public Response getBillMasterById(Integer billMasterId)throws ApplicationException;
	public Response getAutoRenderedServicesByPatient(ServiceMasterDto serviceMasterDto)throws ApplicationException;
	public Response getBillsByFilterForBillList(BillListReqDto reqDto)throws BillingException;
	public Response getRenderedServicesByEncounterId(ServiceMasterDto serviceMasterDto)throws ApplicationException;
	public Response getActiveDiscountCategories(Integer orgId,Integer unitId)throws ApplicationException;
	Response getBillDetailsByBillId(Integer billId) throws ApplicationException;
}
