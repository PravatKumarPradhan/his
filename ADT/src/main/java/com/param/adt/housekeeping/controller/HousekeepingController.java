package com.param.adt.housekeeping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.param.adt.common.ICommonConstants;
import com.param.adt.common.Response;
import com.param.adt.housekeeping.service.IHousekeepingService;
import com.param.adt.master.global.dto.BedMasterDto;

@Controller
@RequestMapping("/adt")
@SuppressWarnings({"rawtypes","unchecked"})
public class HousekeepingController implements ICommonConstants{

	@Autowired
	IHousekeepingService iHousekeepingService;
	
	@RequestMapping(value = "/getBedsForHousekeepingList", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getBedsForHousekeepingList(@RequestBody BedMasterDto bedMasterDto) {
		try {
			return iHousekeepingService.getBedsForHousekeepingList(bedMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new Response(ERROR, null, null, null, null);
	}
	
	@RequestMapping(value = "/getActiveUserList", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getActiveUserList(@RequestBody BedMasterDto bedMasterDto) {
		try {
			return iHousekeepingService.getActiveUserList(bedMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new Response(ERROR, null, null, null, null);
	}
	
	@RequestMapping(value = "/assignUserForCleaning", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response assignUserForCleaning(@RequestBody BedMasterDto bedMasterDto) {
		try {
			return iHousekeepingService.assignUserForCleaning(bedMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new Response(ERROR, null, null, null, null);
	}
	
	@RequestMapping(value = "/markAsCleaned", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response markAsCleaned(@RequestBody BedMasterDto bedMasterDto) {
		try {
			return iHousekeepingService.markAsCleaned(bedMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new Response(ERROR, null, null, null, null);
	}
	
	@RequestMapping(value = "/getListOfVacantBedsForNursing", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getListOfVacantBedsForNursing(@RequestBody BedMasterDto bedMasterDto) {
		try {
			return iHousekeepingService.getListOfVacantBedsForNursing(bedMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new Response(ERROR, null, null, null, null);
	}
	
	@RequestMapping(value = "/acceptRequestForNursing", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response acceptRequestForNursing(@RequestBody BedMasterDto bedMasterDto) {
		try {
			return iHousekeepingService.acceptRequestForNursing(bedMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new Response(ERROR, null, null, null, null);
	}
	
	@RequestMapping(value = "/rejectRequestForNursing", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response rejectRequestForNursing(@RequestBody BedMasterDto bedMasterDto) {
		try {
			return iHousekeepingService.rejectRequestForNursing(bedMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new Response(ERROR, null, null, null, null);
	}
	
	@RequestMapping(value = "/searchBedByMultipleCriteriaForHousekeeping", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response searchBedByMultipleCriteriaForHousekeeping(@RequestBody BedMasterDto bedMasterDto) {
		try {
			return iHousekeepingService.searchBedByMultipleCriteriaForHousekeeping(bedMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new Response(ERROR, null, null, null, null);
	}
	
	@RequestMapping(value = "/searchBedByMultipleCriteriaForNursing", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response searchBedByMultipleCriteriaForNursing(@RequestBody BedMasterDto bedMasterDto) {
		try {
			return iHousekeepingService.searchBedByMultipleCriteriaForNursing(bedMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new Response(ERROR, null, null, null, null);
	}

}
