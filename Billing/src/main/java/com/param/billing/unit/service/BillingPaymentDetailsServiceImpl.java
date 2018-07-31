package com.param.billing.unit.service;

import java.util.Date;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.param.billing.exception.BillingException;
import com.param.billing.global.dto.BillPaymentCollectionReqDto;
import com.param.billing.global.dto.BillingPaymentDetailsReqDto;
import com.param.billing.global.dto.BillingStatusMapperDto;
import com.param.billing.global.transaction.dao.IBillPayeeDetailsDao;
import com.param.billing.global.transaction.dao.IBillStatusMapperDao;
import com.param.billing.global.transaction.dao.IBillingMasterDao;
import com.param.billing.global.transaction.dao.IBillingPaymentDetailsDao;
import com.param.billing.global.transaction.dto.BillingMasterDto;
import com.param.billing.global.transaction.dto.BillingPaymentDetailsDto;
import com.param.billing.global.transaction.model.BillingStatusMapperId;
import com.param.global.common.GlobalCommonDateUtils;
import com.param.global.common.IBillingStatusMasterConstant;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;

@Service
@SuppressWarnings("rawtypes")
public class BillingPaymentDetailsServiceImpl implements IBillingPaymentDetailsService, ICommonConstants{

	@Autowired
	private IBillingMasterDao iBillingMasterDao;
	@Autowired
	private IBillingPaymentDetailsDao iBillingPaymentDetailsDao;
	@Autowired
	private IBillStatusMapperDao iBillStatusMapperDao;
	@Autowired
	private IBillPayeeDetailsDao iBillPayeeDetailsDao;
	
	@Override
	@Transactional
	public Response collectPayment(BillPaymentCollectionReqDto reqDto) throws BillingException {
		try{
			double totalBillAmount = 0;
			double dueAmount = 0;
			double netAmount = 0;
			double amountCollected = 0;		
			double concessionAmount = 0;
			double totalDueAmount = 0;
			String dateTime = GlobalCommonDateUtils.getStringDate(new Date(), "dd-M-yyyy HH:mm:ss");
			
			//get bill details by id
			Response billRes = iBillingMasterDao.getBillMasterById(reqDto.getBillingMasterId());
			if(billRes.getStatus().equals(SUCCESS) && billRes.getObject() != null) {
			BillingMasterDto billingMasterDto=(BillingMasterDto) billRes.getObject();
			totalBillAmount = billingMasterDto.getTotalBillAmount();
			dueAmount = billingMasterDto.getOutstanding();
			totalDueAmount = billingMasterDto.getOutstanding();
			netAmount = billingMasterDto.getNetAmount();
			concessionAmount = billingMasterDto.getConcessionAmount();
			
			Iterator<BillingPaymentDetailsReqDto> paymentItr = reqDto.getListBillingPaymentDetailsReqDto().listIterator();
			BillingPaymentDetailsReqDto paymentDetails = null;
			while(paymentItr.hasNext()){
				paymentDetails = paymentItr.next();
				paymentDetails.setBillingMasterId(reqDto.getBillingMasterId());
				paymentDetails.setDateOfTransaction(dateTime);
				paymentDetails.setCreatedDate(dateTime);
				paymentDetails.setOrgnisationId(reqDto.getOrgId());
				paymentDetails.setUnitId(reqDto.getUnitId());
				paymentDetails.setAmount(paymentDetails.getAmount() > dueAmount ? dueAmount : paymentDetails.getAmount());
				Response paymRes = iBillingPaymentDetailsDao.savePayment(paymentDetails);
				if(paymRes.getCode().equalsIgnoreCase(SUCCESS_CODE)){
					amountCollected += paymentDetails.getAmount();
					dueAmount = totalDueAmount - amountCollected;
				}
			}
			
			//update bill
			BillingMasterDto billUpdateDto = new BillingMasterDto();
			billUpdateDto.setBillingMasterId(reqDto.getBillingMasterId());
			billUpdateDto.setNetAmount(netAmount);
			billUpdateDto.setConcessionAmount(concessionAmount);
			billUpdateDto.setTotalBillAmount(totalBillAmount);
			billUpdateDto.setOutstanding(dueAmount > 0 ? dueAmount : 0 );
			billUpdateDto.setBillStatusId(dueAmount > 0 ? IBillingStatusMasterConstant.ONHOLD : IBillingStatusMasterConstant.SETTLED);
			iBillingMasterDao.updateBillSummaryAmount(billUpdateDto);
			
			//update bill status mapper 
			BillingStatusMapperDto mapperDto = new BillingStatusMapperDto();
			BillingStatusMapperId mapperId = new BillingStatusMapperId();
			mapperId.setBillId(reqDto.getBillingMasterId());
			mapperId.setBillStatusId(billUpdateDto.getBillStatusId());
			mapperDto.setBillingStatusMapperId(mapperId);
			mapperDto.setCreatedBy(billingMasterDto.getCreatedBy());
			mapperDto.setOrgnisationId(billingMasterDto.getOrganisationId());
			mapperDto.setUnitId(billingMasterDto.getUnitId());
			mapperDto.setStatus(ACTIVE);
			iBillStatusMapperDao.saveBillStatus(mapperDto);
			
			//update bill payee details
			iBillPayeeDetailsDao.updateDueAmtByPayeeAndBillId(reqDto.getBillingMasterId(), reqDto.getPayeeId(), dueAmount > 0 ? dueAmount : 0);
			return new Response<>(SUCCESS, SUCCESS_CODE, PAYMENT_COLLECTED, null, null);
			}
			return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
		}catch(BillingException be){
			be.printStackTrace();
			throw be;
		}catch(Exception e){
			e.printStackTrace();
			throw new BillingException(e.getMessage());
		}
	}

}
