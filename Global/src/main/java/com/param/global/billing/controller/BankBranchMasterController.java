package com.param.global.billing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.param.global.billing.dto.BankBranchMasterDto;
import com.param.global.billing.service.IBankBranchMasterService;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;

@RestController
@RequestMapping("api/billing")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class BankBranchMasterController implements ICommonConstants{

	@Autowired
	private IBankBranchMasterService iBankBranchMasterService;

	
	@RequestMapping(value = "/saveBankBranchMaster", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response saveBankBranchMaster(
			@RequestBody BankBranchMasterDto bankMasterDto) {
		try {
			return iBankBranchMasterService.saveBankBranchMaster(bankMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/getBankBranchMasterById/{branchId}/{orgId}", method = RequestMethod.GET)
	public @ResponseBody Response getBankBranchMasterById(@PathVariable("branchId") int branchId,
			@PathVariable("orgId") int orgId) {
		try {
			return iBankBranchMasterService.getBankBranchMasterById(branchId, orgId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/getBankBranchMasterList", method = RequestMethod.POST)
	public @ResponseBody Response getBankBranchMasterList(@RequestBody BankBranchMasterDto bankMasterDto) {
		try {
			return iBankBranchMasterService.getBankBranchMasterList(bankMasterDto);
		} catch (Exception e) {
			e.printStackTrace();					
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/updateBankBranchMaster", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateBankBranchMaster(
			@RequestBody BankBranchMasterDto bankMasterDto) {
		try {
			return iBankBranchMasterService.updateBankBranchMaster(bankMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/updateBankBranchMasterStatus", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateBankBranchMasterStatus(
			@RequestBody BankBranchMasterDto bankMasterDto) {
		try {
			return iBankBranchMasterService.updateBankBranchMasterStatus(bankMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}
	@RequestMapping(value = "/getBankBranchMasterCount", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getBankBranchMasterCount(
			@RequestBody BankBranchMasterDto bankMasterDto) {
		try {
			return iBankBranchMasterService.getBankBranchMasterCount(bankMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

}
