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
import com.param.adt.master.global.dto.OccupationMasterDto;
import com.param.adt.master.global.service.IOccupationMasterService;

@Controller
@RequestMapping("/adt")
@SuppressWarnings({"rawtypes","unchecked"})
public class OccupationMasterController implements ICommonConstants{
	
	@Autowired
	  private IOccupationMasterService iOccupationMasterService;
	 
	 @RequestMapping(value="/getOccupationMasterList", method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
		public @ResponseBody Response getOccupationMasterList(@RequestBody OccupationMasterDto occupationMasterDto){
			try{
				return iOccupationMasterService.getOccupationMasterList(occupationMasterDto);
			}catch(Exception e){
				e.printStackTrace();
			}
			return new Response(ERROR, null, null, null,null);
		}
	 
	 @RequestMapping(value="/saveOccupationMaster" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
		public @ResponseBody Response saveOccuaprtionMaster(@RequestBody OccupationMasterDto occupationMasterDto){
			try{
				
				return iOccupationMasterService.addOccupationMaster(occupationMasterDto);
			}catch(Exception e){
				e.printStackTrace();
			}
			return new Response(ERROR, null, null, null,null);
		}
		
	 @RequestMapping(value="/getOccupationMasterById" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
		public @ResponseBody Response getOccupationMasterById(@RequestBody OccupationMasterDto occupationMasterDto){
			try{
				
				return iOccupationMasterService.getOccupationById(occupationMasterDto);
			}catch(Exception e){
				e.printStackTrace();
			}
			return new Response(ERROR, null, null, null,null);
		}
	 @RequestMapping(value="/updateOccupationMaster" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
		public @ResponseBody Response updateOccupationMaster(@RequestBody OccupationMasterDto occupationMasterDto){
			try{
				return iOccupationMasterService.updateOccupationMaster(occupationMasterDto);
			}catch(Exception e){
				e.printStackTrace();
			}
			return new Response(ERROR, null, null, null,null);
		}
		@RequestMapping(value="/updateStatusForOccupation" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
		public @ResponseBody Response updateStatusForOccupation(@RequestBody OccupationMasterDto occupationMasterDto){
			try{
				
				return iOccupationMasterService.updateStatusForOccupation(occupationMasterDto);
			}catch(Exception e){
				e.printStackTrace();
			 
			}
			return new Response(ERROR, null, null, null,null);
		}
		@RequestMapping(value="/getActiveOccupationList", method = RequestMethod.GET)
		public @ResponseBody Response getActiveOccupationList(){
			try{
				return iOccupationMasterService.getActiveMaritalStatusList();
			}catch(Exception e){
				e.printStackTrace();
			}
			return new Response(ERROR, null, null, null,null);
		}
		@RequestMapping(value="/getOccupationCount" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
		public @ResponseBody Response getOccupationCount(@RequestBody OccupationMasterDto occupationMasterDto){
			try{
				
				return iOccupationMasterService.getOccupationCount(occupationMasterDto);
			}catch(Exception e){
				e.printStackTrace();
			}
			return new Response(ERROR, null, null, null,null);
		}

}
