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
import com.param.adt.master.global.dto.ReligionMasterDto;
import com.param.adt.master.global.service.IReligionMasterService;

@Controller
@RequestMapping("/adt")
@SuppressWarnings({"rawtypes","unchecked"})
public class ReligionMasterController implements ICommonConstants
{
	@Autowired
	private IReligionMasterService iReligionMasterService;

	@RequestMapping(value = "/saveReligionMaster", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response saveReligionMaster(@RequestBody ReligionMasterDto religionMasterDto) {
		try {

			return iReligionMasterService.addReligionMaster(religionMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/getReligionMasterList", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getReligionMasterList(@RequestBody ReligionMasterDto religionMasterDto) {
		try {
			return iReligionMasterService.getReligionMasterList(religionMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/updateReligionMaster", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateReligionMaster(@RequestBody ReligionMasterDto religionMasterDto) {
		try {
			return iReligionMasterService.updateReligionMaster(religionMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/getReligionById", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getReligionById(@RequestBody ReligionMasterDto religionMasterDto) {
		try {

			return iReligionMasterService.getReligionById(religionMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/updateStatusForReligion", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateStatusForReligion(@RequestBody ReligionMasterDto religionMasterDto) {
		try {

			return iReligionMasterService.updateStatusForReligion(religionMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/getActiveReligionList", method = RequestMethod.GET)
	public @ResponseBody Response getActiveReligionList() {
		try {
			return iReligionMasterService.getActiveReligionList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}
	
	@RequestMapping(value = "/getReligionCount", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getReligionCount(@RequestBody ReligionMasterDto religionMasterDto) {
		try {

			return iReligionMasterService.getReligionCount(religionMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}
}
