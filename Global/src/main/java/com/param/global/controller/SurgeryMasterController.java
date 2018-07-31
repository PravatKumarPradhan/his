package com.param.global.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dto.SurgeryMasterDto;
import com.param.global.service.ISurgeryMasterService;

/*FUNCTIONALITY : SURGERY MASTER
DATE:06/04/2018
CREATED BY: JYOTI*/
@RestController
@RequestMapping("api/global")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class SurgeryMasterController implements ICommonConstants {

	@Autowired
	private ISurgeryMasterService iSurgeryMasterService;

	@RequestMapping(value = "/getSurgeryMasterListAutoFillSerach", method = RequestMethod.GET)
	public @ResponseBody Response listOfSurgeryMasterList(@RequestHeader(value = "unit_id") int unitId,
			@RequestHeader(value = "organization_id") int organizationId,
			@RequestParam(value = "surgery_name") String surgeryName) {
		try {
			SurgeryMasterDto surgeryMasterDto = new SurgeryMasterDto();
			surgeryMasterDto.setUnitId(unitId);
			surgeryMasterDto.setOrganizationId(organizationId);
			surgeryMasterDto.setSurgeryName(surgeryName);
			return iSurgeryMasterService.getListOfSurgeryMaster(surgeryMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@RequestMapping(value = "/getSurgeryMasterList", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getSurgeryMasterList(@RequestBody SurgeryMasterDto surgeryMasterDto) {
		try {
			return iSurgeryMasterService.getSurgeryMasterList(surgeryMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@RequestMapping(value = "/saveSurgeryMaster", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response saveSurgeryMaster(@RequestBody SurgeryMasterDto surgeryMasterDto) {
		try {
			return iSurgeryMasterService.saveSurgeryMaster(surgeryMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@RequestMapping(value = "/getSurgeryMasterById", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getSurgeryMasterById(@RequestBody SurgeryMasterDto surgeryMasterDto) {
		try {
			return iSurgeryMasterService.getSurgeryMasterById(surgeryMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@RequestMapping(value = "/updateSurgeryMaster", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateSurgeryGradeMaster(@RequestBody SurgeryMasterDto surgeryMasterDto) {
		try {
			return iSurgeryMasterService.updateSurgeryMaster(surgeryMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/updateStatusForSurgeryMaster", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateStatusForSurgeryMaster(@RequestBody SurgeryMasterDto surgeryMasterDto) {
		try {

			return iSurgeryMasterService.updateStatusForSurgeryMaster(surgeryMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

}
