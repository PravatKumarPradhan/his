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
import com.param.global.dto.BedCategoryMasterDto;
import com.param.global.service.IBedCategoryMasterService;

@Controller
@RequestMapping("/adt")
@SuppressWarnings({"rawtypes","unchecked"})
public class BedCategoryMasterController implements ICommonConstants{

	@Autowired
	IBedCategoryMasterService iBedCategoryMaster;
	
	@RequestMapping(value="/saveBedCategoryMaster" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response saveBedCategoryMaster(@RequestBody BedCategoryMasterDto BedCategoryMasterDto){
		try{
			
			return iBedCategoryMaster.addBedCategoryMaster(BedCategoryMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getBedCategoryMasterList", method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getBedCategoryMasterList(@RequestBody BedCategoryMasterDto BedCategoryMasterDto){
		try{
			return iBedCategoryMaster.getBedCategoryMasterList(BedCategoryMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	 
	@RequestMapping(value="/updateBedCategoryMaster" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateBedCategoryMaster(@RequestBody BedCategoryMasterDto BedCategoryMasterDto){
		try{
			return iBedCategoryMaster.updateBedCategoryMaster(BedCategoryMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getBedCategoryById" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getBedCategoryById(@RequestBody BedCategoryMasterDto BedCategoryMasterDto){
		try{
			
			return iBedCategoryMaster.getBedCategoryById(BedCategoryMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/updateStatusForBedCategory" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateStatusForBedCategory(@RequestBody BedCategoryMasterDto BedCategoryMasterDto){
		try{
			
			return iBedCategoryMaster.updateStatusForBedCategory(BedCategoryMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getActiveBedCategoryList", method = RequestMethod.GET)
	public @ResponseBody Response getActiveCityList(){
		try{
			return iBedCategoryMaster.getActiveBedCategoryList();
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getBedCategoryCount" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getBedCategoryCount(@RequestBody BedCategoryMasterDto BedCategoryMasterDto){
		try{
			
			return iBedCategoryMaster.getBedCategoryCount(BedCategoryMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
}
