package com.param.adt.master.unit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.param.adt.common.ICommonConstants;
import com.param.adt.common.Response;
import com.param.adt.master.unit.dto.BillingBedCategoryMasterDto;
import com.param.adt.master.unit.service.IBillingBedCategoryMasterService;


@Controller
@RequestMapping("/adt")
@SuppressWarnings({"rawtypes","unchecked"})
public class BillingBedCategoryMasterController implements ICommonConstants{

	
	@Autowired
	IBillingBedCategoryMasterService iBillingBedCategoryMasterService;
	
	@RequestMapping(value="/getActiveBillingBedCategoryList/orgId/{orgId}/unitId/{unitId}", method = RequestMethod.GET)
	public @ResponseBody Response getActiveBillingBedCategoryList(@PathVariable("unitId") Integer unitId,
			@PathVariable("orgId") Integer orgId) {
		try{
			BillingBedCategoryMasterDto bedCategoryMasterDto=new BillingBedCategoryMasterDto();
				bedCategoryMasterDto.setOrganizationId(orgId);
				bedCategoryMasterDto.setUnitId(unitId);
			return iBillingBedCategoryMasterService.getActiveBillingBedCategoryList(bedCategoryMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value = "/getBillingBedCategoryListByBedCategory", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getBillingBedCategoryListByBedCategory(
			@RequestBody BillingBedCategoryMasterDto billingBedCategoryMasterDto) {
		try {
			return iBillingBedCategoryMasterService.getActiveBillingBedCategoryByBedCat(billingBedCategoryMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}
	@RequestMapping(value = "/saveBillingBedCategoryMaster", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response saveBillingBedCategoryMaster(@RequestBody BillingBedCategoryMasterDto billingBedCategoryMasterDto) {
		try {
			return iBillingBedCategoryMasterService.saveBillingBedCategoryMaster(billingBedCategoryMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/getBillingBedCategoryById/{billingBedId}/{orgId}/{unitId}", method = RequestMethod.GET)
	public @ResponseBody Response getBillingBedCategoryById(@PathVariable("billingBedId") int billingBedId,
			@PathVariable("orgId") int orgId, @PathVariable("unitId") int unitId) {
		try {
			return iBillingBedCategoryMasterService.getBillingBedCategoryById(billingBedId, orgId, unitId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/getBillingBedCategoryList", method = RequestMethod.POST)
	public @ResponseBody Response getBillingBedCategoryList(@RequestBody BillingBedCategoryMasterDto billingBedCategoryMasterDto) {
		try {
			return iBillingBedCategoryMasterService.getBillingBedCategoryList(billingBedCategoryMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/updateBillingBedCategoryMaster", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateBillingBedCategoryMaster(@RequestBody BillingBedCategoryMasterDto billingBedCategoryMasterDto) {
		try {
			return iBillingBedCategoryMasterService.updateBillingBedCategoryMaster(billingBedCategoryMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/updateBillingBedCategoryMasterStatus", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateBillingBedCategoryMasterStatus(@RequestBody BillingBedCategoryMasterDto billingBedCategoryMasterDto) {
		try {
			return iBillingBedCategoryMasterService.updateBillingBedCategoryMasterStatus(billingBedCategoryMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/getBillingBedCategoryMasterCount", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getBillingBedCategoryMasterCount(@RequestBody BillingBedCategoryMasterDto billingBedCategoryMasterDto) {
		try {
			return iBillingBedCategoryMasterService.getBillingBedCategoryMasterCount(billingBedCategoryMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}
	
}
