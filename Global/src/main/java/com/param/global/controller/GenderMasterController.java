package com.param.global.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dto.GenderMasterDto;
import com.param.global.service.IGenderMasterService;


@Controller
@RequestMapping("/adt")
@SuppressWarnings({"rawtypes","unchecked"})
public class GenderMasterController implements ICommonConstants{
	
	 @Autowired
	  private IGenderMasterService iGenderMasterService;
	 
	 @RequestMapping(value="/getGenderMasterList", method = RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
		public @ResponseBody Response getGenderMasterList(@RequestBody GenderMasterDto genderMasterDto){
			try{
				return iGenderMasterService.getGenderMasterList(genderMasterDto);
			}catch(Exception e){
				e.printStackTrace();
			}
			return new Response(ERROR, null, null, null,null);
		}
	 
	 @RequestMapping(value="/saveGenderMaster" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
		public @ResponseBody Response saveGenderMaster(@RequestBody GenderMasterDto genderMasterDto){
			try{
				
				return iGenderMasterService.addGenderMaster(genderMasterDto);
			}catch(Exception e){
				e.printStackTrace();
			}
			return new Response(ERROR, null, null, null,null);
		}
		
	 @RequestMapping(value="/getGenderById" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
		public @ResponseBody Response getGenderById(@RequestBody GenderMasterDto genderMasterDto){
			try{
				
				return iGenderMasterService.getGenderById(genderMasterDto);
			}catch(Exception e){
				e.printStackTrace();
			}
			return new Response(ERROR, null, null, null,null);
		}
	 @RequestMapping(value="/updateGenderMaster" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
		public @ResponseBody Response updateGenderMaster(@RequestBody GenderMasterDto genderMasterDto){
			try{
				return iGenderMasterService.updateGenderMaster(genderMasterDto);
			}catch(Exception e){
				e.printStackTrace();
			}
			return new Response(ERROR, null, null, null,null);
		}
		@RequestMapping(value="/updateStatusForGender" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
		public @ResponseBody Response updateStatusForGender(@RequestBody GenderMasterDto genderMasterDto){
			try{
				
				return iGenderMasterService.updateStatusForGender(genderMasterDto);
			}catch(Exception e){
				e.printStackTrace();
			}
			return new Response(ERROR, null, null, null,null);
		}
		@RequestMapping(value="/getActiveGenderList", method = RequestMethod.GET)
		public @ResponseBody Response getActiveGenderList(){
			try{
				return iGenderMasterService.getActiveGenderList();
			}catch(Exception e){
				e.printStackTrace();
			}
			return new Response(ERROR, null, null, null,null);
		}
		
		@RequestMapping(value="/getGenderCount", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
		public @ResponseBody Response getGenderCount(@RequestBody GenderMasterDto genderMasterDto){
			try{
				return iGenderMasterService.getActiveGenderList(genderMasterDto);
			}catch(Exception e){
				e.printStackTrace();
			}
			return new Response(ERROR, null, null, null,null);
		}

}
