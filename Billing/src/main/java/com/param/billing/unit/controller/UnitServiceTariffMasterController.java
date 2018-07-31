package com.param.billing.unit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.param.billing.unit.service.IUnitServiceTariffMasterService;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dto.UnitServiceTariffMasterDto;

@Controller
@RequestMapping(value="api/unit")
@SuppressWarnings({"rawtypes","unchecked"})
public class UnitServiceTariffMasterController implements ICommonConstants{

	@Autowired
	IUnitServiceTariffMasterService iUnitServiceTariffMasterService; 
	
	@RequestMapping(value="/getMasterServiceListByMultipleParameters", method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getMasterServiceListByMultipleParameters(@RequestBody UnitServiceTariffMasterDto unitServiceTariffMasterDto){
		try{
			return iUnitServiceTariffMasterService.getMasterServiceListByMultipleParameters(unitServiceTariffMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/saveUnitServiceTariffMaster", method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response saveUnitServiceTariffMaster(@RequestBody UnitServiceTariffMasterDto unitServiceTariffMasterDto){
		try{
			return iUnitServiceTariffMasterService.saveUnitServiceTariffMaster(unitServiceTariffMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getBasePriceOfServicesFromTariffMasterByServiceList", method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getBasePriceOfServicesFromTariffMasterByServiceList(@RequestBody UnitServiceTariffMasterDto unitServiceTariffMasterDto){
		try{
			return iUnitServiceTariffMasterService.getBasePriceOfServicesFromTariffMasterByServiceList(unitServiceTariffMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
}
