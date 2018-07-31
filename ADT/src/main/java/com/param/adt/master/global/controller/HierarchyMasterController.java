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
import com.param.adt.master.global.dto.HierarchyMasterDto;
import com.param.adt.master.global.service.IHierarchyMasterService;

@Controller
@RequestMapping("/adt")
@SuppressWarnings({"rawtypes","unchecked"})
public class HierarchyMasterController implements ICommonConstants
{
	@Autowired
	IHierarchyMasterService iHierarchyMaster;
	
	@RequestMapping(value="/saveHierarchyMaster" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response saveHierarchyMaster(@RequestBody HierarchyMasterDto hierarchyMasterDto){
		try{
			
			return iHierarchyMaster.addHierarchyMaster(hierarchyMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getHierarchyMasterList", method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getHierarchyMasterList(@RequestBody HierarchyMasterDto hierarchyMasterDto){
		try{
			return iHierarchyMaster.getHierarchyMasterList(hierarchyMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	 
	@RequestMapping(value="/updateHierarchyMaster" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateHierarchyMaster(@RequestBody HierarchyMasterDto hierarchyMasterDto){
		try{
			return iHierarchyMaster.updateHierarchyMaster(hierarchyMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getHierarchyById" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getHierarchyById(@RequestBody HierarchyMasterDto hierarchyMasterDto){
		try{
			
			return iHierarchyMaster.getHierarchyById(hierarchyMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/updateStatusForHierarchy" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateStatusForHierarchy(@RequestBody HierarchyMasterDto hierarchyMasterDto){
		try{
			
			return iHierarchyMaster.updateStatusForHierarchy(hierarchyMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getActiveHierarchyList", method = RequestMethod.GET)
	public @ResponseBody Response getActiveCityList(){
		try{
			return iHierarchyMaster.getActiveHierarchyList();
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getHierarchyCount", method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getHierarchyCount(@RequestBody HierarchyMasterDto hierarchyMasterDto){
		try{
			return iHierarchyMaster.getHierarchyCount(hierarchyMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}

}
