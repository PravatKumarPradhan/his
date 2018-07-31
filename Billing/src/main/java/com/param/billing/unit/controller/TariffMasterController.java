package com.param.billing.unit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.param.billing.unit.service.ITariffMasterService;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dto.TariffMasterDto;

@Controller
@RequestMapping(value="api/unit")
@SuppressWarnings({"rawtypes","unchecked"})
public class TariffMasterController implements ICommonConstants{

	@Autowired
	private ITariffMasterService iTariffMasterService;
	
	@RequestMapping(value="/saveTariffMaster" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response saveTariffMaster(@RequestBody TariffMasterDto tariffMasterDto){
		try{
			return iTariffMasterService.saveTariffMaster(tariffMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getTariffMasterList", method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getTariffMasterList(@RequestBody TariffMasterDto tariffMasterDto){
		try{
			return iTariffMasterService.getTariffMasterList(tariffMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getPaymentEntitlementListByTariffid", method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getPaymentEntitlementListByTariffid(@RequestBody TariffMasterDto tariffMasterDto){
		try{
			return iTariffMasterService.getPaymentEntitlementListByTariffid(tariffMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	
	@RequestMapping(value="/getTariffListById" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getTariffListById(@RequestBody TariffMasterDto tariffMasterDto){
		try{
			return iTariffMasterService.getTariffListById(tariffMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	 
	@RequestMapping(value="/updateTariffMaster" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateTariffMaster(@RequestBody TariffMasterDto tariffMasterDto){
		try{
			
			return iTariffMasterService.updateTariffMaster(tariffMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/updateTariffMasterStatus" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateStatusForTariff(@RequestBody TariffMasterDto tariffMasterDto){
		try{
			return iTariffMasterService.updateStatusForTariff(tariffMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/updateStatusForTariffPaymentMapper" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateStatusForTariffPaymentMapper(@RequestBody TariffMasterDto tariffMasterDto){
		try{
			return iTariffMasterService.updateStatusForTariffPaymentMapper(tariffMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getActiveTariffMasterList" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getActiveTariffMasterList(@RequestBody TariffMasterDto tariffMasterDto){
		try{
			
			return iTariffMasterService.getActiveTariffMasterList(tariffMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getPaymentEntitlementListByTariffIdList", method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getPaymentEntitlementListByTariffIdList(@RequestBody TariffMasterDto tariffMasterDto){
		try{
			return iTariffMasterService.getPaymentEntitlementListByTariffIdList(tariffMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
}
