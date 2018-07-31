package com.param.billing.global.transaction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.param.billing.common.ICommonConstants;
import com.param.billing.common.IError;
import com.param.billing.common.Response;
import com.param.billing.global.dto.BillingGroupMasterDto;
import com.param.billing.global.model.BillingGroupMaster;
import com.param.billing.global.transaction.dao.IBillingGroupMasterService;
import com.param.billing.global.transaction.dto.BillingGroupSpecialityMasterDto;
import com.param.billing.global.transaction.service.IBillingGroupSpecialityMapperService;
/**
 * 
 * @author Nikhil
 * Controller to Handle request for BillingGroupMaster
 */
@RestController
@RequestMapping(value="billing")
@SuppressWarnings({"rawtypes","unchecked"})
public class BillingGroupMasterController implements ICommonConstants,IError {
	@Autowired
	private IBillingGroupMasterService iBillingGroupMasterService;
	
	@Autowired
	private IBillingGroupSpecialityMapperService iBillingGroupSpecialityMapperService;

	@RequestMapping(value="createBillingGroupMaster",method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Response createBillGroupMaster(@RequestBody BillingGroupSpecialityMasterDto billingGroupSpecialityMasterDto) {
		try {
			//billingGroupSpecialityMasterDto.setStatus(ACTIVE);
			Response billingGroupMasterResopnse = iBillingGroupMasterService.saveBillingGroupMaster(billingGroupSpecialityMasterDto);
			if(billingGroupMasterResopnse.getStatus().equalsIgnoreCase(SUCCESS)) {
				BillingGroupMaster billingGroupId = (BillingGroupMaster) billingGroupMasterResopnse.getObject();
				billingGroupSpecialityMasterDto.setBillingGroupId(billingGroupId.getBillingGroupId());
				return iBillingGroupSpecialityMapperService.saveBillingGroupSpecialityMapper(billingGroupSpecialityMasterDto);
			}else {
				return billingGroupMasterResopnse;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	}
	
	@RequestMapping(value="getBillingGroupMasterList", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Response getBillingGroupMasterList(@RequestBody BillingGroupMasterDto billingGroupMasterDto) {
		try {
				return iBillingGroupMasterService.getBillingGroupMasterList(billingGroupMasterDto);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	}
	
	@RequestMapping(value="getBillingGroupMasterById", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Response getBillingGroupMasterById(@RequestBody BillingGroupMasterDto billingGroupMasterDto) {
		try {
				return iBillingGroupMasterService.getBillingGroupMasterById(billingGroupMasterDto);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	}
	
	@RequestMapping(value="changeBillingGroupMasterStatus", method=RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Response changeBillingGroupMasterStatus(@RequestBody BillingGroupMasterDto billingGroupMasterDto) {
		try {
				return iBillingGroupMasterService.changeBillingGroupMasterStatus(billingGroupMasterDto);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	}
	
	@RequestMapping(value="updateBillingGroupMaster", method=RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Response updateBillingGroupMaster(@RequestBody BillingGroupSpecialityMasterDto billingGroupSpecialityMasterDto) {
		try {
				return iBillingGroupMasterService.updateBillingGroupMaster(billingGroupSpecialityMasterDto);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	}
	
	@RequestMapping(value="getBillingGroupMasterCount", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Response getBillingGroupMasterCount(@RequestBody BillingGroupMasterDto billingGroupMasterDto) {
		try {
				return iBillingGroupMasterService.getBillingGroupMasterTotalCount(billingGroupMasterDto);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	}

}
