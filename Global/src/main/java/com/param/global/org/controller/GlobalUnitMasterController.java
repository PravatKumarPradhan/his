package com.param.global.org.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.org.dto.UnitMasterDto;
import com.param.global.org.service.IGlobalUnitMasterService;

@Controller
@SuppressWarnings("rawtypes")
public class GlobalUnitMasterController  implements ICommonConstants{

	@Autowired
	private IGlobalUnitMasterService iGlobalUnitMasterService;
	
	
	@RequestMapping(value="/unit/save" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response saveUnitMaster(@RequestBody UnitMasterDto unitMasterDto){
		try{
			return iGlobalUnitMasterService.saveUnitMaster(unitMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, null, null, null, null);
	}
	
	
	@RequestMapping(value="/unit/update" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateUnitMaster(@RequestBody UnitMasterDto unitMasterDto){
		try{
			return iGlobalUnitMasterService.updateUnitMaster(unitMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, null, null, null, null);
	}
	
	@RequestMapping(value="/unit/{unitId}" , method = RequestMethod.GET)
	public @ResponseBody Response getUnitMasterById(@PathVariable("unitId") Integer unitId){
		try{
			return iGlobalUnitMasterService.getUnitById(unitId);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, null, null, null, null);
	}
	
	
	@RequestMapping(value="/unit/count" , method = RequestMethod.GET)
	public @ResponseBody Response getUnitMasterCount(){
		try{
			return iGlobalUnitMasterService.getUnitTotalCount();
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, null, null, null, null);
	}
	
	@RequestMapping(value="/unit/{offSet}/{noOfRecordsPerPage}" , method = RequestMethod.GET)
	public @ResponseBody Response getAllUnitList(@PathVariable("offSet") Integer offSet , @PathVariable("noOfRecordsPerPage") Integer noOfRecordsPerPage){
		try{
			UnitMasterDto unitMasterDto = new UnitMasterDto();
			unitMasterDto.setOffSet(offSet);
			unitMasterDto.setNoOfRecordsPerPage(noOfRecordsPerPage);
			return iGlobalUnitMasterService.getAllUnitList(unitMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, null, null, null, null);
	}
	
	@RequestMapping(value="/unit/status/{unitId}/{status}" , method = RequestMethod.GET)
	public @ResponseBody Response updateUnitStatus(@PathVariable("unitId") Integer unitId , @PathVariable("status") char status){
		try{
			UnitMasterDto unitMasterDto = new UnitMasterDto();
			unitMasterDto.setUnitId(unitId);
			unitMasterDto.setStatus(status);
			return iGlobalUnitMasterService.updateUnitStatusById(unitMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, null, null, null, null);
	}
}
