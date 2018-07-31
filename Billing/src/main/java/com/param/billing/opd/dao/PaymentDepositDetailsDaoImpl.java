package com.param.billing.opd.dao;

import java.util.Date;

import org.springframework.stereotype.Repository;

import com.param.billing.exception.BillingException;
import com.param.billing.global.dto.PaymentDepositDetailsDto;
import com.param.billing.global.model.PaymentDepositDetails;
import com.param.global.common.GlobalCommonDateUtils;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;

import in.param.common.dao.GenericDao;

@Repository
@SuppressWarnings("rawtypes")
public class PaymentDepositDetailsDaoImpl extends GenericDao<PaymentDepositDetails, Integer> implements IPaymentDepositDetailsDao, ICommonConstants{

	public PaymentDepositDetailsDaoImpl() {
		super(PaymentDepositDetails.class);
	}

	@Override
	public Response savePaymentDepositDetails(PaymentDepositDetailsDto dto) throws BillingException {
		try{
			PaymentDepositDetails depositDetails = new PaymentDepositDetails();
			depositDetails.setAccountNumber(dto.getAccountNumber());
			depositDetails.setAmount(dto.getAmount());
			depositDetails.setBankId(dto.getBankId());
			depositDetails.setCardHolderName(dto.getCardHolderName());
			depositDetails.setCardNo(dto.getCardNo());
			depositDetails.setCardTypeId(dto.getCardTypeId());
			depositDetails.setChequeDate(dto.getChequeDate() != null && !dto.getChequeDate().isEmpty() ? GlobalCommonDateUtils.getDate(dto.getChequeDate(), "dd-M-yyyy HH:mm:ss") : null);
			depositDetails.setChequeNumber(dto.getChequeNumber());
			depositDetails.setCreatedBy(dto.getCreatedBy());
			depositDetails.setCreatedDate(dto.getCreatedDate() != null && !dto.getCreatedDate().isEmpty() ? GlobalCommonDateUtils.getDate(dto.getCreatedDate(), "dd-M-yyyy HH:mm:ss") : new Date());
			depositDetails.setDepositId(dto.getDepositId());
			depositDetails.setExpiryDate(dto.getExpiryDate() != null && !dto.getExpiryDate().isEmpty() ? GlobalCommonDateUtils.getDate(dto.getExpiryDate(), "dd-M-yyyy HH:mm:ss") : null);
			depositDetails.setPaymentModeId(dto.getPaymentModeId());
			depositDetails.setUpdatedBy(dto.getUpdatedBy());
			depositDetails.setUpdatedDate(dto.getUpdatedDate() != null && !dto.getUpdatedDate().isEmpty() ? GlobalCommonDateUtils.getDate(dto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss") : new Date());
			save(depositDetails);
			return new Response<>(SUCCESS, SUCCESS_CODE, null, null, null);
		}catch(Exception e){
			e.printStackTrace();
			throw new BillingException(e.getMessage());
		}
	}
	
}
