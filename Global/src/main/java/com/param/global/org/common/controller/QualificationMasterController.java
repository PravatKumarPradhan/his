package com.param.global.org.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.org.common.dto.QualificationMasterDto;
import com.param.global.org.common.service.IQualificationMasterService;

@RestController
@RequestMapping("api/common")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class QualificationMasterController implements ICommonConstants {

	@Autowired
	private IQualificationMasterService iQualificationMasterService;

	@RequestMapping(value = "/saveQualificationMaster", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response saveQualificationMaster(@RequestBody QualificationMasterDto qualificationMasterDto) {
		try {
			return iQualificationMasterService.saveQualificationMaster(qualificationMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/getQualificationMasterById/{qualificationId}/{orgId}", method = RequestMethod.GET)
	public @ResponseBody Response getQualificationMasterById(@PathVariable("qualificationId") int qualificationId,
			@PathVariable("orgId") int orgId) {
		try {
			return iQualificationMasterService.getQualificationMasterById(qualificationId, orgId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/getQualificationMasterList", method = RequestMethod.POST)
	public @ResponseBody Response getQualificationMasterList(@RequestBody QualificationMasterDto qualificationMasterDto) {
		try {
			return iQualificationMasterService.getQualificationMasterList(qualificationMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/updateQualificationMaster", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateQualificationMaster(@RequestBody QualificationMasterDto qualificationMasterDto) {
		try {
			return iQualificationMasterService.updateQualificationMaster(qualificationMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/updateQualificationMasterStatus", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateQualificationMasterStatus(@RequestBody QualificationMasterDto qualificationMasterDto) {
		try {
			return iQualificationMasterService.updateQualificationMasterStatus(qualificationMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/getQualificationMasterCount", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getQualificationMasterCount(@RequestBody QualificationMasterDto qualificationMasterDto) {
		try {
			return iQualificationMasterService.getQualificationMasterCount(qualificationMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}
}
