package com.param.billing.global.transaction.dao;

import com.param.global.common.Response;
import com.param.billing.exception.BillingException;
import com.param.billing.global.transaction.dto.BillPayeeDetailsDto;
import com.param.billing.global.transaction.model.BillPayeeDetails;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IBillPayeeDetailsDao extends IGenericDao<BillPayeeDetails, Integer>{
	public Response saveBillPayeeDetails(BillPayeeDetailsDto billPayeeDetailsDto)throws ApplicationException;
	public Response getBillPayeeDetailsByBillId(int billId)throws BillingException;
	public Response updateDueAmtByPayeeAndBillId(int billId, int payeeId, double dueAmount)throws BillingException;
}

