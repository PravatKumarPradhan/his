package com.param.service.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.param.global.common.ICommonConstants;
import com.param.global.common.IError;
import com.param.global.common.Response;
import com.param.service.dto.CommonDto;
import com.param.service.dto.EhcPackageSearchResDto;
import com.param.service.dto.ServiceForPackageReqDto;
import com.param.service.services.IEHCpackageService;
@RestController
@SuppressWarnings(
{ "rawtypes", "unchecked" })
@RequestMapping(value = "/Unit/Service/EHCpackage")
public class EHCpackageController  implements ICommonConstants, IError {
	
	@Autowired
	private IEHCpackageService iEHCpackageService;


	
	final static Logger logger = Logger.getLogger(EHCpackageController.class);

	@RequestMapping(value = "/getPackgeType/{orgId}/{orgUnitId}", method = RequestMethod.GET)
	public @ResponseBody Response getPackgeType(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "orgUnitId") Integer orgUnitId)
	{
		Response<CommonDto, Integer> response = null;
		try
		{
			response = iEHCpackageService.getPackgeType(orgId,orgUnitId);
			return response;
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	
	@RequestMapping(value = "/autoCompleteServices/{orgId}/{orgUnitId}/{serviceName}", method = RequestMethod.GET)
	public @ResponseBody Response getAutoCompleteServices(
			@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "orgUnitId") Integer orgUnitId,
			@PathVariable(value = "serviceName") String serviceName)
	{
		Response<CommonDto, Integer> response = null;
		try
		{
			response = iEHCpackageService.getAutoCompleteServices(orgId,orgUnitId,serviceName);
			return response;
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	
	@RequestMapping(value = "/getTariff/{orgId}/{orgUnitId}", method = RequestMethod.GET)
	public @ResponseBody Response getTariff(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "orgUnitId") Integer orgUnitId)
	{
		Response<CommonDto, Integer> response = null;
		try
		{
			response = iEHCpackageService.getTariff(orgId, orgUnitId);
			return response;
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	
	@RequestMapping(value = "/getPaymentEntitlementByTariffId/{orgId}/{orgUnitId}/{tariffId}", method = RequestMethod.GET)
	public @ResponseBody Response getPaymentEntitlementByTariffId(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "orgUnitId") Integer orgUnitId,	@PathVariable(value = "tariffId") Integer tariffId)
	{
		Response<CommonDto, Integer> response = null;
		try
		{
			response = iEHCpackageService.getPaymentEntitlementByTariffId(orgId, orgUnitId, tariffId);
			return response;
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	@RequestMapping(value = "/getPatientTypeByTariffId/{orgId}/{orgUnitId}/{tariffId}", method = RequestMethod.GET)
	public @ResponseBody Response getPatientTypeByTariffId(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "orgUnitId") Integer orgUnitId,	@PathVariable(value = "tariffId") Integer tariffId)
	{
		Response<CommonDto, Integer> response = null;
		try
		{
			response = iEHCpackageService.getPatientTypeByTariffId(orgId, orgUnitId, tariffId);
			return response;
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	/*@RequestMapping(value = "/autoCompletePackageCode/{orgId}/{orgUnitId}/{serviceName}", method = RequestMethod.GET)
	public @ResponseBody Response getAutoCompletePackageCode(
			@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "orgUnitId") Integer orgUnitId,
			@PathVariable(value = "serviceName") String serviceName)
	{
		Response<CommonDto, Integer> response = null;
		try
		{
			response = iEHCpackageService.getAutoCompletePackageName(orgId, orgUnitId, serviceName);
			return response;
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	*/
	
	
	
	@RequestMapping(value = "/autoCompletePackageName", method = RequestMethod.POST)
	public @ResponseBody Response getAutoCompletePackageName(@RequestBody ServiceForPackageReqDto reqDto)
	{
		Response<EhcPackageSearchResDto, Integer> response = null;
		try
		{
			response = iEHCpackageService.getAutoCompletePackageCode(reqDto);
			return response;
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
}
