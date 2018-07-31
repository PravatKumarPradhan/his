package com.param.global.billing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.param.global.billing.dto.CurrencyDenominationMasterDto;
import com.param.global.billing.service.ICurrencyDenominationMasterService;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;

@RestController
@RequestMapping("api/billing")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class CurrencyDenominationMasterController implements ICommonConstants {

	@Autowired
	ICurrencyDenominationMasterService iCurrencyDenominationMasterService;

	@RequestMapping(value = "/saveCurrencyDenominationMaster", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response saveCurrencyDenominationMaster(@RequestBody CurrencyDenominationMasterDto currencyDenominationMasterDto) {
		try {
			return iCurrencyDenominationMasterService.saveCurrencyDenominationMaster(currencyDenominationMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/getCurrencyDenominationMasterById/{currencyId}/{orgId}", method = RequestMethod.GET)
	public @ResponseBody Response getCurrencyDenominationMasterById(@PathVariable("currencyId") int currencyId,@PathVariable("orgId") int orgId) {
		try {
			return iCurrencyDenominationMasterService.getCurrencyDenominationMasterById(currencyId,orgId);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null, e.getMessage());

		}
	}
	@RequestMapping(value = "/getCurrencyDenominationMasterList", method = RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getCurrencyDenominationMasterList(@RequestBody CurrencyDenominationMasterDto   currencyDenominationMasterDto) {
		try {
			return iCurrencyDenominationMasterService.getCurrencyDenominationMasterList(currencyDenominationMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null, e.getMessage());
		}
	}
	@RequestMapping(value = "/updateCurrencyDenominationMaster", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateCurrencyDenominationMaster(@RequestBody CurrencyDenominationMasterDto currencyDenominationMasterDto) {
		try {
			return iCurrencyDenominationMasterService.updateCurrencyDenominationMaster(currencyDenominationMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}
	@RequestMapping(value = "/updateCurrencyDenominationMasterStatus", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateCurrencyDenominationMasterStatus(@RequestBody CurrencyDenominationMasterDto currencyDenominationMasterDto) {
		try {
			return iCurrencyDenominationMasterService.updateCurrencyDenominationMasterStatus(currencyDenominationMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}
	@RequestMapping(value = "/getCurrencyDenominationCount", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getCurrencyDenominationCount(@RequestBody CurrencyDenominationMasterDto currencyDenominationMasterDto) {
		try {
			return iCurrencyDenominationMasterService.getCurrencyDenominationCount(currencyDenominationMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}
}
