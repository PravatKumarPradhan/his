package com.param.opd.unit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dto.BlockSlotReqDto;
import com.param.global.dto.SlotBlockUnblockRequestDto;
import com.param.global.dto.SlotMasterDto;
import com.param.opd.unit.service.ISlotMasterService;

@Controller
@RequestMapping("api/opd")
@SuppressWarnings({"rawtypes","unchecked"})
public class SlotMasterController implements ICommonConstants
{
	@Autowired
	ISlotMasterService iSlotMasterService;
	

	@RequestMapping(value="/generateSlots", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response generateSlots(@RequestBody SlotMasterDto slotMasterDto) {
		try {
			if(slotMasterDto.getSlotTypeId()!=3)
				return iSlotMasterService.generateSlots(slotMasterDto);
			else
				return iSlotMasterService.generateSlotsForECH(slotMasterDto);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	@RequestMapping(value="/getSlots", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getSlots(@RequestBody SlotMasterDto slotMasterDto) {
		try {
			return iSlotMasterService.getSlots(slotMasterDto);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	@RequestMapping(value="/getSlots/blockUnblock", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getSlotsForBlockUnblock(@RequestBody SlotBlockUnblockRequestDto slotReqDto) {
		try {
			return iSlotMasterService.getSlotsForBlockUnblock(slotReqDto);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	@RequestMapping(value="/getSlotDetails", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getSlotDetails(@RequestBody SlotMasterDto slotMasterDto) {
		try {
			return iSlotMasterService.getSlotDetails(slotMasterDto);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	@RequestMapping(value="/getSlotCount", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getSlotCount(@RequestBody SlotMasterDto slotMasterDto) {
		try {
			return iSlotMasterService.getSlotCount(slotMasterDto);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	@RequestMapping(value="/updateSlotStatus", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateSlotStatus(@RequestBody SlotMasterDto slotMasterDto) {
		try {
			return iSlotMasterService.updateSlotStatus(slotMasterDto);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	@RequestMapping(value="/getYearlySlots", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getYearlySlots(@RequestBody SlotMasterDto slotMasterDto) {
		try {
				return iSlotMasterService.getYearlySlots(slotMasterDto);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@RequestMapping(value="/getDailySlots", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getDailySlots(@RequestBody SlotMasterDto slotMasterDto) {
		try {
				return iSlotMasterService.getDailySlots(slotMasterDto);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	@RequestMapping(value="/getMonthlySlots", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getMonthlySlots(@RequestBody SlotMasterDto slotMasterDto) {
		try {
			return iSlotMasterService.getMonthlySlots(slotMasterDto);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value="/getWeeklySlots", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getWeeklySlots(@RequestBody SlotMasterDto slotMasterDto) {
		try {
			return iSlotMasterService.getWeeklySlots(slotMasterDto);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value="/blockUnblockslot", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response blockUnblockSlots(@RequestHeader Integer organisationId, @RequestHeader Integer unitId, @RequestBody BlockSlotReqDto blockSlotReqDto){
		try{
			blockSlotReqDto.setOrganisationId(organisationId);
			blockSlotReqDto.setUnitId(unitId);
			return iSlotMasterService.blockUnblockDoctorSlots(blockSlotReqDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	@RequestMapping(value="/getBlockedUnblockedSlotDetails", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getBlockedUnblockedSlotDetails(@RequestBody SlotMasterDto slotMasterDto) {
		try {
			return iSlotMasterService.getBlockedUnblockedSlotDetails(slotMasterDto);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	@RequestMapping(value="/isOverBookingAllowedOrNot", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response isOverBookingAllowedOrNot(@RequestBody SlotMasterDto slotMasterDto) {
		try {
			return iSlotMasterService.isOverBookingAllowedOrNot(slotMasterDto);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
}
