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
import com.param.adt.master.global.dto.MaritalStatusDto;
import com.param.adt.master.global.service.IMaritalStatusMasterService;


@Controller
@RequestMapping("/adt")
@SuppressWarnings({"rawtypes","unchecked"})
public class MaritalStatusMasterController implements ICommonConstants {
	
	 @Autowired
	  private IMaritalStatusMasterService iMaritalStatusMasterService;
	 
	 @RequestMapping(value="/getMaritalStatusMasterList", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
		public @ResponseBody Response getMaritalStatusMasterList(@RequestBody MaritalStatusDto maritalStatusDto){
			try{
				return iMaritalStatusMasterService.getMaritalStatusMasterList(maritalStatusDto);
			}catch(Exception e){
				e.printStackTrace();
			}
			return new Response(ERROR, null, null, null,null);
		}
	 
	 @RequestMapping(value="/saveMaritalStatusMaster" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
		public @ResponseBody Response saveMaritalStatusMaster(@RequestBody MaritalStatusDto maritalStatusDto){
			try{
				
				return iMaritalStatusMasterService.addMaritalStatusMaster(maritalStatusDto);
			}catch(Exception e){
				e.printStackTrace();
			}
			return new Response(ERROR, null, null, null,null);
		}
		
	 @RequestMapping(value="/getMaritalById" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
		public @ResponseBody Response getMaritalById(@RequestBody MaritalStatusDto maritalStatusDto){
			try{
				
				return iMaritalStatusMasterService.getMaritalById(maritalStatusDto);
			}catch(Exception e){
				e.printStackTrace();
			}
			return new Response(ERROR, null, null, null,null);
		}
	 @RequestMapping(value="/updateMaritalStatusMaster" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
		public @ResponseBody Response updateMaritalMaster(@RequestBody MaritalStatusDto maritalStatusDto){
			try{
				return iMaritalStatusMasterService.updateMaritalStatusMaster(maritalStatusDto);
			}catch(Exception e){
				e.printStackTrace();
			}
			return new Response(ERROR, null, null, null,null);
		}
		@RequestMapping(value="/updateStatusForMaritalStatus" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
		public @ResponseBody Response updateStatusForMarital(@RequestBody MaritalStatusDto maritalStatusDto){
			try{
				
				return iMaritalStatusMasterService.updateStatusForMarital(maritalStatusDto);
			}catch(Exception e){
				e.printStackTrace();
			}
			return new Response(ERROR, null, null, null,null);
		}
		@RequestMapping(value="/getActiveMaritalStatusList", method = RequestMethod.GET)
		public @ResponseBody Response getActiveMaritalStatusList(){
			try{
				return iMaritalStatusMasterService.getActiveMaritalStatusList();
			}catch(Exception e){
				e.printStackTrace();
			}
			return new Response(ERROR, null, null, null,null);
		}
		
		@RequestMapping(value="/getMaritalStatusCount" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
		public @ResponseBody Response getMaritalStatusCount(@RequestBody MaritalStatusDto maritalStatusDto){
			try{
				
				return iMaritalStatusMasterService.getMaritalStatusCount(maritalStatusDto);
			}catch(Exception e){
				e.printStackTrace();
			}
			return new Response(ERROR, null, null, null,null);
		}
}
