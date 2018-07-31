package com.param.billing.global.transaction.service;

import com.param.billing.exception.BillingException;
import com.param.billing.global.dto.BillListReqDto;
import com.param.billing.global.transaction.dto.BillingMasterDto;
import com.param.billing.global.transaction.dto.PaymentRecieptMasterDto;
import com.param.global.common.Response;
import com.param.global.dto.ServiceMasterDto;

import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IBillingMasterService {
	public Response collectPaymentAgainstBill(PaymentRecieptMasterDto paymentRecieptMasterDto)throws ApplicationException;
	
	public Response updateBillSummaryAmount(BillingMasterDto billingMasterDto)throws ApplicationException;
	
	public Response getAutoRenderedServicesByPatient(ServiceMasterDto serviceMasterDto)throws ApplicationException;
	
	public Response getBillsByFilterForBillList(BillListReqDto reqDto)throws BillingException;
	
	public Response getRenderedServicesByEncounterId(ServiceMasterDto serviceMasterDto)throws ApplicationException;
	
	public Response saveBill(BillingMasterDto billingMasterDto)throws ApplicationException;
	
	public Response getActiveDiscountCategories(Integer orgId, Integer unitId)throws ApplicationException;
	
}
