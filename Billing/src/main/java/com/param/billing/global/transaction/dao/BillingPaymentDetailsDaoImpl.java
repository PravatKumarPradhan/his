package com.param.billing.global.transaction.dao;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.param.global.common.GlobalCommonDateUtils;
import com.param.global.common.ICommonConstants;
import com.param.billing.common.Response;
import com.param.billing.exception.BillingException;
import com.param.billing.global.dto.BillingPaymentDetailsReqDto;
import com.param.billing.global.transaction.dto.BillingPaymentDetailsDto;
import com.param.billing.global.transaction.model.BillingPaymentDetails;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings("rawtypes")
public class BillingPaymentDetailsDaoImpl extends GenericDao<BillingPaymentDetails, Integer> implements IBillingPaymentDetailsDao,ICommonConstants{

	public BillingPaymentDetailsDaoImpl() {
		super(BillingPaymentDetails.class);
	}
	
	@Autowired
	private DozerBeanMapper mapper;
	
	@Override
	public Response saveBillingPaymentDetails(BillingPaymentDetailsDto billingPaymentDetailsDto)
			throws ApplicationException {
		try {
			BillingPaymentDetails billingPaymentDetails = mapper.map(billingPaymentDetailsDto, BillingPaymentDetails.class,"BillingPaymentDetailsDto_to_BillingPaymentDetails");
			billingPaymentDetails=save(billingPaymentDetails);
			return new Response<>(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, null, billingPaymentDetails);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, null, null);
	}

	@Override
	public com.param.global.common.Response savePayment(BillingPaymentDetailsReqDto reqDto) throws BillingException {
		try {
			BillingPaymentDetails paymentDetails = new BillingPaymentDetails();
			paymentDetails.setAccountNumber(reqDto.getAccountNumber());
			paymentDetails.setAmount(reqDto.getAmount());
			paymentDetails.setBankId(reqDto.getBankId());
			paymentDetails.setBillingMasterId(reqDto.getBillingMasterId());
			paymentDetails.setCardHolderName(reqDto.getCardHolderName());
			paymentDetails.setCardNo(reqDto.getCardNo());
			paymentDetails.setCardTypeId(reqDto.getCardTypeId());
			paymentDetails.setCheckNumber(reqDto.getChequeNumber());
			paymentDetails.setChequeDate((reqDto.getChequeDate() != null && !reqDto.getChequeDate().isEmpty()) ? GlobalCommonDateUtils.getDate(reqDto.getChequeDate(), "dd-M-yyyy").getTime() : null);
			paymentDetails.setCreatedBy(reqDto.getCreatedBy());
			paymentDetails.setCreatedDate((reqDto.getCreatedDate() != null && !reqDto.getCreatedDate().isEmpty()) ? GlobalCommonDateUtils.getDate(reqDto.getCreatedDate(), "dd-M-yyyy HH:mm:ss").getTime() : null);
			paymentDetails.setCvv(reqDto.getCvv());
			paymentDetails.setDateOfTransaction((reqDto.getDateOfTransaction() != null && !reqDto.getDateOfTransaction().isEmpty()) ? GlobalCommonDateUtils.getDate(reqDto.getDateOfTransaction(), "dd-M-yyyy HH:mm:ss").getTime() : null);
			paymentDetails.setExpiryDate((reqDto.getExpiryDate() != null && !reqDto.getExpiryDate().isEmpty()) ? GlobalCommonDateUtils.getDate(reqDto.getExpiryDate(), "dd-M-yyyy").getTime() : null);
			paymentDetails.setOrgnisationId(reqDto.getOrgnisationId());
			paymentDetails.setPaymentModeId(reqDto.getPaymentModeId());
			paymentDetails.setPaymentVoucherNo(reqDto.getPaymentVoucherNo());
			paymentDetails.setStatus(ACTIVE);
			paymentDetails.setTariffId(reqDto.getTariffId());
			paymentDetails.setUnitId(reqDto.getUnitId());
			paymentDetails.setUpdatedBy(reqDto.getUpdatedBy());
			paymentDetails.setUpdatedDate(null);
			paymentDetails = save(paymentDetails);
			return new com.param.global.common.Response<>(SUCCESS, SUCCESS_CODE, null, null, null);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new com.param.global.common.Response<>(ERROR, ERROR_CODE, null, null, null);
	}

	
}

