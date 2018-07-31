package com.param.global.billing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.param.global.billing.dto.CurrencyMasterDto;
import com.param.global.billing.service.ICurrencyMasterService;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;

@RestController
@RequestMapping("/billing")
@SuppressWarnings({ "rawtypes", "unchecked" })

public class CurrencyMasterController implements ICommonConstants {

	@Autowired
	ICurrencyMasterService iCurrencyMasterService;

	@RequestMapping(value = "/saveCurrencyMaster", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response saveCurrencyMaster(@RequestBody CurrencyMasterDto currencyMasterDto) {
		try {
			return iCurrencyMasterService.saveCurrencyMaster(currencyMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/getCurrencyMasterById/{currencyId}/{orgId}", method = RequestMethod.GET)
	public @ResponseBody Response getCurrencyMasterById(@PathVariable("currencyId") int currencyId,@PathVariable("orgId") int orgId) {
		try {
			return iCurrencyMasterService.getCurrencyMasterById(currencyId,orgId);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null, e.getMessage());

		}
	}
	@RequestMapping(value = "/getCurrencyMasterList", method = RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getCurrencyMasterList(@RequestBody CurrencyMasterDto currencyMasterDto) {
		try {
			return iCurrencyMasterService.getCurrencyMasterList(currencyMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null, e.getMessage());
		}
	}
	@RequestMapping(value = "/updateCurrencyMaster", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateCurrencyMaster(@RequestBody CurrencyMasterDto currencyMasterDto) {
		try {
			return iCurrencyMasterService.updateCurrencyMaster(currencyMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}
	@RequestMapping(value = "/updateCurrencyMasterStatus", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateCurrencyMasterStatus(@RequestBody CurrencyMasterDto currencyMasterDto) {
		try {
			return iCurrencyMasterService.updateCurrencyMasterStatus(currencyMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}
	@RequestMapping(value = "/getCurrencyCount", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getCurrencyCount(@RequestBody CurrencyMasterDto currencyMasterDto) {
		try {
			return iCurrencyMasterService.getCurrencyCount(currencyMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}
}
