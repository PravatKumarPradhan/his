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
import com.param.adt.master.global.dto.RaceMasterDto;
import com.param.adt.master.global.service.IRaceMasterService;

@Controller
@RequestMapping("/adt")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class RaceMasterController implements ICommonConstants {

	@Autowired
	IRaceMasterService iRaceMasterService;

	@RequestMapping(value = "/saveRaceMaster", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response saveRaceMaster(@RequestBody RaceMasterDto raceMasterDto) {
		try {

			return iRaceMasterService.addRaceMaster(raceMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/getRaceMasterList", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getRaceMasterList(@RequestBody RaceMasterDto raceMasterDto) {
		try {
			return iRaceMasterService.getRaceMasterList(raceMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/updateRaceMaster", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateRaceMaster(@RequestBody RaceMasterDto raceMasterDto) {
		try {
			return iRaceMasterService.updateRaceMaster(raceMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/getRaceById", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getRaceById(@RequestBody RaceMasterDto raceMasterDto) {
		try {

			return iRaceMasterService.getRaceById(raceMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/updateStatusForRace", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateStatusForRace(@RequestBody RaceMasterDto raceMasterDto) {
		try {

			return iRaceMasterService.updateStatusForRace(raceMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/getActiveRaceList", method = RequestMethod.GET)
	public @ResponseBody Response getActiveRaceList() {
		try {
			return iRaceMasterService.getActiveRaceList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}
	
	@RequestMapping(value = "/getRaceCount", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getRaceCount(@RequestBody RaceMasterDto raceMasterDto) {
		try {
			return iRaceMasterService.getRaceCount(raceMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

}
