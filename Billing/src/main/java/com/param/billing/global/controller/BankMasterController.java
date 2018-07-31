package com.param.billing.global.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.param.billing.common.ICommonConstants;
import com.param.billing.common.Response;
import com.param.billing.global.dto.BankMasterDto;
import com.param.billing.global.service.IBankMasterService;

@RestController
@RequestMapping("api/billing")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class BankMasterController implements ICommonConstants{

	@Autowired
	private IBankMasterService iBankMasterService;
	
	@RequestMapping(value="bankMaster",method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	public Response getActiveBankMasterList(@RequestBody BankMasterDto bankMasterDto) {
		try {
			return iBankMasterService.getActiveBankMaster(bankMasterDto);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	@RequestMapping(value = "/saveBankMaster", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response saveBankMaster(
			@RequestBody BankMasterDto bankMasterDto) {
		try {
			return iBankMasterService.saveBankMaster(bankMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/getBankMasterById/{bankId}/{orgId}", method = RequestMethod.GET)
	public @ResponseBody Response getBankMasterById(@PathVariable("bankId") int bankId,
			@PathVariable("orgId") int orgId) {
		try {
			return iBankMasterService.getBankMasterById(bankId, orgId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/getBankMasterList", method = RequestMethod.POST)
	public @ResponseBody Response getBankMasterList(@RequestBody BankMasterDto bankMasterDto) {
		try {
			return iBankMasterService.getBankMasterList(bankMasterDto);
		} catch (Exception e) {
			e.printStackTrace();					
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/updateBankMaster", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateBankMaster(
			@RequestBody BankMasterDto bankMasterDto) {
		try {
			return iBankMasterService.updateBankMaster(bankMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/updateBankMasterStatus", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateBankMasterStatus(
			@RequestBody BankMasterDto bankMasterDto) {
		try {
			return iBankMasterService.updateBankMasterStatus(bankMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}
	@RequestMapping(value = "/getBankMasterCount", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getBankMasterCount(
			@RequestBody BankMasterDto bankMasterDto) {
		try {
			return iBankMasterService.getBankMasterCount(bankMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}
}
