package com.param.adt.master.unit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.param.adt.common.ICommonConstants;
import com.param.adt.common.Response;
import com.param.adt.master.unit.dto.MortuaryBedMasterDto;
import com.param.adt.master.unit.service.IMortuaryBedMasterService;

@Controller
@RequestMapping("/adt")
@SuppressWarnings({"rawtypes","unchecked"})
public class MortuaryBedMasterController implements ICommonConstants
{
	@Autowired
	IMortuaryBedMasterService iMortuaryBedMasterService;

	@RequestMapping(value="/saveMortuaryBeds" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response saveMortuaryBeds(@RequestBody MortuaryBedMasterDto mortuaryBedMasterDto){
		try{
			mortuaryBedMasterDto.setBedStatusId(6);
			Response res= iMortuaryBedMasterService.saveMortuaryBeds(mortuaryBedMasterDto);
			if(res.getStatus().equals(SUCCESS))
			{
				mortuaryBedMasterDto.setBedStatusId(6);
				return iMortuaryBedMasterService.saveMortuaryBedLogStatus((MortuaryBedMasterDto)res.getObject());
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getMortuaryBedList", method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getMortuaryBedList(@RequestBody MortuaryBedMasterDto mortuaryBedMasterDto){
		try{
			return iMortuaryBedMasterService.getMortuaryBedList(mortuaryBedMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getMortuaryBedListById", method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getMortuaryBedListById(@RequestBody MortuaryBedMasterDto mortuaryBedMasterDto){
		try{
			return iMortuaryBedMasterService.getMortuaryBedListById(mortuaryBedMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	 
	@RequestMapping(value="/updateMortuaryBedMaster" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateMortuaryBedMaster(@RequestBody MortuaryBedMasterDto mortuaryBedMasterDto){
		try{
			return iMortuaryBedMasterService.updateMortuaryBedMaster(mortuaryBedMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	
	@RequestMapping(value="/updateStatusForMortuaryBed" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateStatusForMortuaryBed(@RequestBody MortuaryBedMasterDto mortuaryBedMasterDto){
		try{
			
			return iMortuaryBedMasterService.updateStatusForMortuaryBed(mortuaryBedMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getActiveMortuaryBeds" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getActiveMortuaryBeds(@RequestBody MortuaryBedMasterDto mortuaryBedMasterDto){
		try{
			return iMortuaryBedMasterService.getActiveMortuaryBeds(mortuaryBedMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}

	@RequestMapping(value="/getMortuaryBedCount" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getMortuaryBedCount(@RequestBody MortuaryBedMasterDto mortuaryBedMasterDto){
		try{
			
			return iMortuaryBedMasterService.getMortuaryBedCount(mortuaryBedMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getMorturyBedListByStatusId" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getMorturyBedList(@RequestBody MortuaryBedMasterDto mortuaryBedMasterDto) {
		try {
			return iMortuaryBedMasterService.getMorturyBedListByStatusId(mortuaryBedMasterDto);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR, COMMON_ERROR_MESSAGE, null, null);
	}
	
	@RequestMapping(value="/allotMortuaryBed" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response allotMortuaryBed(@RequestBody MortuaryBedMasterDto mortuaryBedMasterDto) {
		try {
			return iMortuaryBedMasterService.allotMortuaryBed(mortuaryBedMasterDto);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR, COMMON_ERROR_MESSAGE, null, null);
	}
}
