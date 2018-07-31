package com.param.billing.global.transaction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.param.billing.common.ICommonConstants;
import com.param.billing.exception.BillingException;
import com.param.billing.global.dto.BillListReqDto;
import com.param.billing.global.dto.BillPaymentCollectionReqDto;
import com.param.billing.global.transaction.dto.BillPayeeDetailsDto;
import com.param.billing.global.transaction.dto.BillingMasterDto;
import com.param.billing.global.transaction.dto.PaymentRecieptMasterDto;
import com.param.billing.global.transaction.service.IBillPayeeDetailsService;
import com.param.billing.global.transaction.service.IBillingMasterService;
import com.param.billing.unit.service.IBillingPaymentDetailsService;
import com.param.global.common.IBillingStatusMasterConstant;
import com.param.global.common.Response;
import com.param.global.dto.ServiceMasterDto;
import com.param.global.service.IOrderDetailsDiscountService;

@RestController
@RequestMapping("api/billing")
@SuppressWarnings({"rawtypes"})
public class BillingMasterController implements ICommonConstants,IBillingStatusMasterConstant{

	@Autowired
	private IBillingMasterService iBillingMasterService;

	@Autowired
	private IBillPayeeDetailsService iBillPayeeDetailsService;
	
	@Autowired
	private IBillingPaymentDetailsService iBillingPaymentDetailsService;
	
	@Autowired
	private IOrderDetailsDiscountService iOrderDetailsDiscountService; 
	
	
	@RequestMapping(value="saveBill", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public Response saveBill(@RequestBody BillingMasterDto billingMasterDto) {
		try {
			return iBillingMasterService.saveBill(billingMasterDto);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	@RequestMapping(value="billPayeeDetails", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public Response saveBillPayeeDetails(@RequestBody BillPayeeDetailsDto billPayeeDetailsDto) {
		try {
			return iBillPayeeDetailsService.saveBillPayeeDetails(billPayeeDetailsDto);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	@RequestMapping(value="collectPayment", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public Response collectpayment(@RequestBody PaymentRecieptMasterDto paymentRecieptMasterDto) {
		try {
			return iBillingMasterService.collectPaymentAgainstBill(paymentRecieptMasterDto);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	@RequestMapping(value = "/serviceMaster/autoRendered", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public Response getAutoRenderedServicesByPatient(@RequestBody ServiceMasterDto serviceMasterDto){
			try{
				return iBillingMasterService.getAutoRenderedServicesByPatient(serviceMasterDto);
			}
			catch (Exception e){
					e.printStackTrace();
			}
			return new Response<>(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	@RequestMapping(value = "/serviceMaster/renderedServices", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public Response getRenderedServicesByEncounterId(@RequestBody ServiceMasterDto serviceMasterDto){
			try{
				return iBillingMasterService.getRenderedServicesByEncounterId(serviceMasterDto);
			}
			catch (Exception e){
					e.printStackTrace();
			}
			return new Response<>(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	@RequestMapping(value = "/billlist/filter" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public Response getBillsByFilterForBillList(@RequestBody BillListReqDto reqDto){
		try{
			return iBillingMasterService.getBillsByFilterForBillList(reqDto);
		}catch(BillingException be){
			throw be;
		}
		catch (Exception e){
				e.printStackTrace();
				throw new BillingException(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/payee/bill/{billId}" , method = RequestMethod.GET)
	public Response getPayeeDetailsByBillId(@PathVariable("billId")int billId){
		try{
			return iBillPayeeDetailsService.getBillPayeeDetailsByBillId(billId);
		}catch(BillingException be){
			throw be;
		}
		catch (Exception e){
				e.printStackTrace();
				throw new BillingException(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/payments" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public Response collectPayment(@RequestBody BillPaymentCollectionReqDto reqDto){
		try{
			return iBillingPaymentDetailsService.collectPayment(reqDto);
		}catch(BillingException be){
			throw be;
		}
		catch (Exception e){
				e.printStackTrace();
				throw new BillingException(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/discountCategories/orgId/{orgId}/unitId/{unitId}" , method = RequestMethod.GET )
	public Response getActiveDiscountCategories(@PathVariable("orgId")Integer orgId,@PathVariable("unitId")Integer unitId){
		try{
			return iBillingMasterService.getActiveDiscountCategories(orgId,unitId);
		}catch(BillingException be){
			throw be;
		}
		catch (Exception e){
				e.printStackTrace();
				throw new BillingException(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/cancelDiscount" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE )
	public Response cancelDiscount(@RequestBody BillingMasterDto billingMasterDto){
		try{
			return iOrderDetailsDiscountService.saveCancelledDiscounts(billingMasterDto.getCancelledDiscountsList());
		}catch(BillingException be){
			throw be;
		}
		catch (Exception e){
				e.printStackTrace();
				throw new BillingException(e.getMessage());
		}
	}
	
}
