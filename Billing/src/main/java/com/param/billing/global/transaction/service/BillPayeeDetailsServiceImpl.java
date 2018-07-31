package com.param.billing.global.transaction.service;

import java.util.ListIterator;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.billing.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.billing.exception.BillingException;
import com.param.billing.global.transaction.dao.IBillPayeeDetailsDao;
import com.param.billing.global.transaction.dao.IBillingMasterDao;
import com.param.billing.global.transaction.dto.BillDetailsByBillIdDto;
import com.param.billing.global.transaction.dto.BillPayeeDetailsDto;

import in.param.exception.ApplicationException;

@Service
@SuppressWarnings({"rawtypes","unchecked"})
public class BillPayeeDetailsServiceImpl implements IBillPayeeDetailsService,ICommonConstants{

	@Autowired
	private IBillPayeeDetailsDao iBillPayeeDetailsDao;
	
	@Autowired
	IBillingMasterDao iBillingMasterDao;
	
	@Override
	@Transactional
	public Response saveBillPayeeDetails(BillPayeeDetailsDto billPayeeDetailsDto) throws ApplicationException {
		try {
			return iBillPayeeDetailsDao.saveBillPayeeDetails(billPayeeDetailsDto);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	@Transactional
	public Response getBillPayeeDetailsByBillId(int billId) throws BillingException {
		try {
			
			
			Response res = iBillingMasterDao.getBillDetailsByBillId(billId);
			
			ListIterator<BillDetailsByBillIdDto> iterator = res.getListObject().listIterator();
			while(iterator.hasNext())
			{
				BillDetailsByBillIdDto billDetailsByBillIdDto = iterator.next();
				Double disPer = (billDetailsByBillIdDto.getDiscount()/(billDetailsByBillIdDto.getGrossAmount()-billDetailsByBillIdDto.getConcession()))*100;
				billDetailsByBillIdDto.setDiscountPercentage(disPer);
			}
			
			Response res2 = iBillPayeeDetailsDao.getBillPayeeDetailsByBillId(billId);
			
			return new Response(SUCCESS, null, null, res2.getListObject(),res.getListObject());
			
		}catch (BillingException be) {
			be.printStackTrace();
			throw be;
		}catch(Exception e){
			e.printStackTrace();
			throw new BillingException(e.getMessage());
		}
	}

}

