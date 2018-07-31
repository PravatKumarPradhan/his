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
import com.param.adt.master.global.dto.ShortLeaveReasonMasterDto;
import com.param.adt.master.global.service.IShortLeaveReasonService;

@Controller
@RequestMapping("/adt")
@SuppressWarnings({"rawtypes","unchecked"})
public class ShortLeaveReasonMasterController implements ICommonConstants
{
	@Autowired
	IShortLeaveReasonService iShortLeaveReasonMasterService;
	
	@RequestMapping(value="/saveShortLeaveReasonMaster" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response saveShortLeaveReasonMaster(@RequestBody ShortLeaveReasonMasterDto shortLeaveReasonMasterDto){
		try{
			
			return iShortLeaveReasonMasterService.addShortLeaveReasonMaster(shortLeaveReasonMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getShortLeaveReasonMasterList", method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getShortLeaveReasonMasterList(@RequestBody ShortLeaveReasonMasterDto shortLeaveReasonMasterDto){
		try{
			return iShortLeaveReasonMasterService.getShortLeaveReasonMasterList(shortLeaveReasonMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getShortLeaveCount", method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getShortLeaveCount(@RequestBody ShortLeaveReasonMasterDto shortLeaveReasonMasterDto){
		try{
			return iShortLeaveReasonMasterService.getShortLeaveCount(shortLeaveReasonMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	 
	@RequestMapping(value="/updateShortLeaveReasonMaster" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateShortLeaveReasonMaster(@RequestBody ShortLeaveReasonMasterDto shortLeaveReasonMasterDto){
		try{
			return iShortLeaveReasonMasterService.updateShortLeaveReasonMaster(shortLeaveReasonMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getShortLeaveReasonById" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getShortLeaveReasonById(@RequestBody ShortLeaveReasonMasterDto shortLeaveReasonMasterDto){
		try{
			
			return iShortLeaveReasonMasterService.getShortLeaveReasonById(shortLeaveReasonMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/updateStatusForShortLeaveReason" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateStatusForShortLeaveReason(@RequestBody ShortLeaveReasonMasterDto shortLeaveReasonMasterDto){
		try{
			
			return iShortLeaveReasonMasterService.updateStatusForShortLeaveReason(shortLeaveReasonMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getActiveShortLeaveReasonList" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getActiveShortLeaveReasonList(@RequestBody ShortLeaveReasonMasterDto shortLeaveReasonMasterDto){
		try{
			return iShortLeaveReasonMasterService.getActiveShortLeaveReasonList(shortLeaveReasonMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
}
