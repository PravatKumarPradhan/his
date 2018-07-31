package com.param.billing.global.transaction.config.controller;

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
import com.param.billing.global.transaction.config.dto.ServiceChargesReqDto;
import com.param.billing.global.transaction.config.dto.VariablePricingDto;
import com.param.billing.global.transaction.config.dto.VariablePricingSequenceDto;
import com.param.billing.global.transaction.config.service.IServiceTarrifMasterService;
import com.param.billing.global.transaction.config.service.IVariablePricingSequenceService;

import in.param.exception.ApplicationException;

@SuppressWarnings({ "unchecked", "rawtypes" })
@RestController
@RequestMapping(value="/billing/variable-pricing")
public class VariablePricingController implements ICommonConstants, IError{
	
	@Autowired
	IVariablePricingSequenceService iVariablePricingSequenceService;
	
	@Autowired
	IServiceTarrifMasterService iServiceTarrifMasterService; 
	
	@RequestMapping(value="/getVariablePricingSequence" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public Response getVariablePricingSequence(@RequestBody VariablePricingSequenceDto variablePricingSequenceDto){
		try{
			return iVariablePricingSequenceService.getVariablePricingSequenceByOrganisationId(variablePricingSequenceDto);
		}catch(ApplicationException ex){
			ex.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR, null, null);
	}
	
	
	@RequestMapping(value="/saveVariablePricingSequence" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public Response saveVariablePricingSequence(@RequestBody VariablePricingSequenceDto variablePricingSequenceDto){
		try{
			return iVariablePricingSequenceService.saveVariablePricingSequenceMaster(variablePricingSequenceDto);
		}catch(ApplicationException ex){
			ex.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR, null, null);
	}
	
	@RequestMapping(value="/saveVariablePricing" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public Response saveTarrifMaster(@RequestBody VariablePricingDto variablePricingDto){
		try{
			return iServiceTarrifMasterService.saveServiceTarrifMaster(variablePricingDto);
		}catch(ApplicationException ex){
			ex.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR, null, null);
	}
	
	@RequestMapping(value="/getVariablePricingFactorByVisitId" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public Response getServiceTarrifMaster(@RequestBody VariablePricingDto variablePricingDto){
		try{
			return iServiceTarrifMasterService.getServiceTarrifMaster(variablePricingDto);
		}catch(ApplicationException ex){
			ex.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR, null, null);
	}
	
	@RequestMapping(value = "/listServiceMasterWithBasePriceByVisitType/{orgId}/{unitId}/{visitTypeId}/{offset}/{recordPerPage}", method = RequestMethod.GET)
	public Response listServiceMasterWithBasePriceByVisitType(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value="unitId") Integer unitId,
			@PathVariable(value="visitTypeId") Integer visitTypeId,
			@PathVariable(value = "offset") Integer offset,
			@PathVariable(value = "recordPerPage") Integer recordPerPage){
		try{
			return iServiceTarrifMasterService.listServiceMasterWithBasePriceByVisitType(orgId, unitId, visitTypeId, offset, recordPerPage);
		
		} catch (Exception e){
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	@RequestMapping( value="getServiceCharges", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Response getServiceCharges(@RequestBody ServiceChargesReqDto serviceChargesReqDto){
		try{
			return iServiceTarrifMasterService.getServiceCharges(serviceChargesReqDto);
		
		} catch (Exception e){
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
}
