
package com.param.billing.global.transaction.dao;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.hibernate.HibernateException;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.param.billing.common.ICommonConstants;
import com.param.billing.common.IError;
import com.param.global.common.Response;
import com.param.billing.exception.BillingException;
import com.param.billing.global.dto.BillPayeeDetailsResDto;
import com.param.billing.global.transaction.dto.BillPayeeDetailsDto;
import com.param.billing.global.transaction.model.BillPayeeDetails;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings({ "unchecked", "rawtypes" })
public class BillPayeeDetailsDaoImpl extends GenericDao<BillPayeeDetails, Integer> implements IBillPayeeDetailsDao, ICommonConstants,IError{

	public BillPayeeDetailsDaoImpl() {
		super(BillPayeeDetails.class);
	}

	@Autowired
	private DozerBeanMapper mapper;
	
	@Override
	public Response saveBillPayeeDetails(BillPayeeDetailsDto billPayeeDetailsDto) throws ApplicationException {
		try {
			BillPayeeDetails billPayeeDetails = mapper.map(billPayeeDetailsDto, BillPayeeDetails.class, "BillPayeeDetailsDto_to_BillPayeeDetails");
			billPayeeDetails = save(billPayeeDetails);
			return new Response<>(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, null, billPayeeDetails);
		}catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	}

	@Override
	public Response getBillPayeeDetailsByBillId(int billId) throws BillingException {
		try {
			List<BillPayeeDetailsResDto> listBillPayeeDetailsResDto = sessionFactory.getCurrentSession().getNamedQuery("GET_PAYEE_DETAILS_BY_BILL_ID").setInteger("billId", billId).setResultTransformer(Transformers.aliasToBean(BillPayeeDetailsResDto.class)).list();
			return new Response<>(SUCCESS, SUCCESS_CODE, null, listBillPayeeDetailsResDto, null);
		} catch (Exception e) { 
			e.printStackTrace();
			throw new BillingException(e.getMessage());
		}
	}

	@Override
	public Response updateDueAmtByPayeeAndBillId(int billId, int payeeId, double dueAmount) throws BillingException {
		try {
			sessionFactory.getCurrentSession().getNamedQuery("UPDATE_DUE_AMOUNT_BY_BILL_AND_PAYEE").setDouble("dueAmount", dueAmount).setInteger("billId", billId).setInteger("payeeId", payeeId).executeUpdate();
			return new Response<>(SUCCESS, SUCCESS_CODE, null, null, null);
		} catch (Exception e) { 
			e.printStackTrace();
			throw new BillingException(e.getMessage());
		}
	}

}

