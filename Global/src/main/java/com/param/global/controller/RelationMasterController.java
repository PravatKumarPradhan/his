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
import com.param.global.dto.RelationMasterDto;
import com.param.global.service.IRelationMasterService;

@Controller
@RequestMapping("/adt")
@SuppressWarnings({"rawtypes","unchecked"})
public class RelationMasterController implements ICommonConstants{
	 
	@Autowired
	  private IRelationMasterService iRelationService;
	 
	 @RequestMapping(value="/getRelationMasterList", method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
		public @ResponseBody Response getRelationMasterList(@RequestBody RelationMasterDto relationMasterDto){
			try{
				return iRelationService.getRelationMasterList(relationMasterDto);
			}catch(Exception e){
				e.printStackTrace();
			}
			return new Response(ERROR, null, null, null,null);
		}
	 
	 @RequestMapping(value="/saveRelationMaster" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
		public @ResponseBody Response saveRelationMaster(@RequestBody RelationMasterDto relationMasterDto){
			try{
				
				return iRelationService.addRelationMaster(relationMasterDto);
			}catch(Exception e){
				e.printStackTrace();
			}
			return new Response(ERROR, null, null, null,null);
		}
		
	 @RequestMapping(value="/getRelationById" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
		public @ResponseBody Response getRelationById(@RequestBody RelationMasterDto relationMasterDto){
			try{
				
				return iRelationService.getRelationById(relationMasterDto);
			}catch(Exception e){
				e.printStackTrace();
			}
			return new Response(ERROR, null, null, null,null);
		}
	 @RequestMapping(value="/updateRelationMaster" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
		public @ResponseBody Response updateGenderMaster(@RequestBody RelationMasterDto relationMasterDto){
			try{
				return iRelationService.updateRelationMaster(relationMasterDto);
			}catch(Exception e){
				e.printStackTrace();
			}
			return new Response(ERROR, null, null, null,null);
		}
		@RequestMapping(value="/updateStatusForRelation" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
		public @ResponseBody Response updateStatusForRelation(@RequestBody RelationMasterDto relationMasterDto){
			try{
				
				return iRelationService.updateStatusForRelation(relationMasterDto);
			}catch(Exception e){
				e.printStackTrace();
			}
			return new Response(ERROR, null, null, null,null);
		}
		@RequestMapping(value="/getActiveRelationList", method = RequestMethod.GET)
		public @ResponseBody Response getActiveRelationList(){
			try{
				return iRelationService.getActiveRelationList();
			}catch(Exception e){
				e.printStackTrace();
			}
			return new Response(ERROR, null, null, null,null);
		}
		
		@RequestMapping(value="/getRelationCount" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
		public @ResponseBody Response getRelationCount(@RequestBody RelationMasterDto relationMasterDto){
			try{
				
				return iRelationService.getRelationCount(relationMasterDto);
			}catch(Exception e){
				e.printStackTrace();
			}
			return new Response(ERROR, null, null, null,null);
		}
}
