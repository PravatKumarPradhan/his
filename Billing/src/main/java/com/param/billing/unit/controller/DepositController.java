package com.param.billing.unit.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.param.billing.exception.BillingException;
import com.param.billing.global.dto.DepositCollectionReqDto;
import com.param.billing.global.dto.DepositSearchReqDto;
import com.param.billing.global.dto.VisitTypeEncounterReqDto;
import com.param.billing.opd.service.IDepositMasterService;
import com.param.global.common.Response;

@RestController
@RequestMapping("/api/billing/unit")
@SuppressWarnings("rawtypes")
public class DepositController {

	@Autowired
	private IDepositMasterService iDepositMasterService;
	
	@RequestMapping(value = "/deposit" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public Response addDeposit(@RequestBody DepositCollectionReqDto reqDto){
		try{
			return iDepositMasterService.addDeposit(reqDto);
		}catch(Exception e){
			e.printStackTrace();
			throw new BillingException(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/deposit/search" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public Response addDeposit(@RequestBody DepositSearchReqDto reqDto){
		try{
			return iDepositMasterService.searchDeposit(reqDto);
		}catch(Exception e){
			e.printStackTrace();
			throw new BillingException(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/deposit/encounters" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public Response getEncountersForDeposit(@RequestBody VisitTypeEncounterReqDto reqDto){
		try{
			return iDepositMasterService.getEncountersForDeposit(reqDto);
		}catch(Exception e){
			e.printStackTrace();
			throw new BillingException(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/deposit/patient/{orgId}/{unitId}/{patientId}" , method = RequestMethod.GET)
	public Response getAvailableDepositByPatient(@PathVariable Map<String,String> params){
		try{
			int orgId = Integer.parseInt(params.get("orgId").trim());
			int unitId = Integer.parseInt(params.get("unitId").trim());
			int patientId = Integer.parseInt(params.get("patientId").trim());
			return iDepositMasterService.getAvailableDepositByPatient(orgId, unitId, patientId);
		}catch(Exception e){
			e.printStackTrace();
			throw new BillingException(e.getMessage());
		}
	}
}