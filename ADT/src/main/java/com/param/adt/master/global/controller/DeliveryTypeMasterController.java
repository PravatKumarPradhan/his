package com.param.adt.master.global.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.param.adt.common.ICommonConstants;
import com.param.adt.common.Response;
import com.param.adt.master.global.dto.DeliveryTypeMasterDto;
import com.param.adt.master.global.service.IDeliveryTypeMasterService;

@Controller
@RequestMapping("/adt")
@SuppressWarnings({"rawtypes","unchecked"})
public class DeliveryTypeMasterController implements ICommonConstants{

	@Autowired
	private IDeliveryTypeMasterService iDeliveryTypeSercive;
	
	@RequestMapping(value="/saveDeliveryTypeMaster" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response saveDeliveryTypeMaster(@RequestBody DeliveryTypeMasterDto deliveryTypeMasterDto){
		try{
			
			return iDeliveryTypeSercive.addDeliveryTypeMaster(deliveryTypeMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getDeliveryTypeMaster", method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getDeliveryTypeMasterList(@RequestBody DeliveryTypeMasterDto deliveryTypeMasterDto){
		try{
			return iDeliveryTypeSercive.getDeliveryTypeMasterList(deliveryTypeMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	 
	@RequestMapping(value="/updateDeliveryTypeMaster" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateDeliveryTypeMaster(@RequestBody DeliveryTypeMasterDto deliveryTypeMasterDto){
		try{
			return iDeliveryTypeSercive.updateDeliveryTypeMaster(deliveryTypeMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			 ;
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getDeliveryTypeById" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getDeliveryTypeById(@RequestBody DeliveryTypeMasterDto deliveryTypeMasterDto){
		try{
			
			return iDeliveryTypeSercive.getDeliveryTypeById(deliveryTypeMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/updateStatusForDeliveryType" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateStatusForDeliveryType(@RequestBody DeliveryTypeMasterDto deliveryTypeMasterDto){
		try{
			
			return iDeliveryTypeSercive.updateStatusForDeliveryType(deliveryTypeMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getActiveDeliveryTypeList", method = RequestMethod.GET)
	public @ResponseBody Response getActiveDeliveryTypeList(){
		try{
			return iDeliveryTypeSercive.getActiveDeliveryTypeList();
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getDeliveryTypeCount" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getDeliveryTypeCount(@RequestBody DeliveryTypeMasterDto deliveryTypeMasterDto){
		try{
			
			return iDeliveryTypeSercive.getDeliveryTypeCount(deliveryTypeMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}

}
