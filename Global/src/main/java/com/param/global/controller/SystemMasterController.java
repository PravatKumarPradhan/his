package com.param.global.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dto.MasterOfSytemDto;
import com.param.global.dto.SystemMasterDto;
import com.param.global.service.ISystemMasterService;

/*FUNCTIONALITY : SYSTEM MASTER
DATE:18/04/2018
CREATED BY: JYOTI*/
@RestController
@RequestMapping("api/global")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class SystemMasterController implements ICommonConstants {

	@Autowired
	private ISystemMasterService iSystemMasterService;

	@RequestMapping(value = "/getSystemMaster", method = RequestMethod.GET)
	public @ResponseBody Response listOfSystemMaster(@RequestHeader(value = "unit_id") int unitId,
			@RequestHeader(value = "organization_id") int organizationId, @RequestHeader(value = "type_id") int typeId,
			@RequestHeader(value = "gender_id") int genderId) {
		try {
			SystemMasterDto systemMasterDto = new SystemMasterDto();
			systemMasterDto.setUnitId(unitId);
			systemMasterDto.setTypeId(typeId);
			systemMasterDto.setOrganizationId(organizationId);
			systemMasterDto.setGenderId(genderId);
			return iSystemMasterService.getListOfSystemMaster(systemMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@RequestMapping(value = "/saveSystemMaster", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response saveSystemMaster(@RequestHeader(value = "unit_id") int unitId,
			@RequestHeader(value = "organization_id") int organizationId,
			@RequestBody MasterOfSytemDto masterOfSytemDto) {
		try {
			masterOfSytemDto.setUnitId(unitId);
			masterOfSytemDto.setOrganizationId(organizationId);
			return iSystemMasterService.saveSystemMaster(masterOfSytemDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@RequestMapping(value = "/getListOfSystemMaster", method = RequestMethod.GET)
	public @ResponseBody Response getListOfSystemMaster(@RequestHeader(value = "unit_id") int unitId,
			@RequestHeader(value = "organization_id") int organizationId) {
		try {
			MasterOfSytemDto masterOfSytemDto = new MasterOfSytemDto();
			masterOfSytemDto.setUnitId(unitId);
			masterOfSytemDto.setOrganizationId(organizationId);
			return iSystemMasterService.getSystemMaster(masterOfSytemDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@RequestMapping(value = "/updateSystemMasterStatus", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateSystemMasterStatus(@RequestBody MasterOfSytemDto masterOfSytemDto) {
		try {
			return iSystemMasterService.updateSystemMasterStatus(masterOfSytemDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@RequestMapping(value = "/getListOfSystemMasterById", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getListOfSystemMasterById(@RequestBody MasterOfSytemDto masterOfSytemDto) {
		try {
			return iSystemMasterService.getListOfSystemMasterById(masterOfSytemDto);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@RequestMapping(value = "/updateSystemMaster", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateSystemMaster(@RequestHeader(value = "unit_id") int unitId,
			@RequestHeader(value = "organization_id") int organizationId,
			@RequestBody MasterOfSytemDto masterOfSytemDto) {
		try {
			masterOfSytemDto.setUnitId(unitId);
			masterOfSytemDto.setOrganizationId(organizationId);
			return iSystemMasterService.updateSystemMaster(masterOfSytemDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@RequestMapping(value = "/getListOfSystemMasterByType", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getListOfSystemMasterTyp(@RequestHeader(value = "unit_id") int unitId,
			@RequestHeader(value = "organization_id") int organizationId, @RequestHeader(value = "type_id") int typeId) {
		try {
			MasterOfSytemDto masterOfSytemDto = new MasterOfSytemDto();
			masterOfSytemDto.setUnitId(unitId);
			masterOfSytemDto.setOrganizationId(organizationId);
			masterOfSytemDto.setTypeId(typeId);
			return iSystemMasterService.getListOfSystemMasterByType(masterOfSytemDto);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
}
