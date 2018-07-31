package com.param.global.billing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.param.global.billing.dto.EmployeeCategoryMasterDto;
import com.param.global.billing.service.IEmployeeCategoryMasterService;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;

@RestController
@RequestMapping("/billing")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class EmployeeCategoryMasterController implements ICommonConstants {

	@Autowired
	IEmployeeCategoryMasterService iEmployeeCategoryMasterService;

	@RequestMapping(value = "/saveEmployeeCategoryMaster", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response saveEmployeeCategoryMaster(
			@RequestBody EmployeeCategoryMasterDto employeeCategoryMasterDto) {
		try {
			return iEmployeeCategoryMasterService.saveEmployeeCategoryMaster(employeeCategoryMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/getEmployeeCategoryMasterById/{employeeId}/{orgId}", method = RequestMethod.GET)
	public @ResponseBody Response getEmployeeCategoryMasterById(@PathVariable("employeeId") int employeeId,
			@PathVariable("orgId") int orgId) {
		try {
			return iEmployeeCategoryMasterService.getEmployeeCategoryMasterById(employeeId, orgId);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null, e.getMessage());

		}
	}

	@RequestMapping(value = "/getEmployeeCategoryMasterList", method = RequestMethod.POST)
	public @ResponseBody Response getEmployeeCategoryMasterList(@RequestBody EmployeeCategoryMasterDto employeeCategoryMasterDto) {
		try {
			return iEmployeeCategoryMasterService.getEmployeeCategoryMasterList(employeeCategoryMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null, e.getMessage());
		}
	}

	@RequestMapping(value = "/updateEmployeeCategoryMaster", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateEmployeeCategoryMaster(
			@RequestBody EmployeeCategoryMasterDto employeeCategoryMasterDto) {
		try {
			return iEmployeeCategoryMasterService.updateEmployeeCategoryMaster(employeeCategoryMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/updateEmployeeCategoryMasterStatus", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateEmployeeCategoryMasterStatus(
			@RequestBody EmployeeCategoryMasterDto employeeCategoryMasterDto) {
		try {
			return iEmployeeCategoryMasterService.updateEmployeeCategoryMasterStatus(employeeCategoryMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}
	@RequestMapping(value = "/getEmployeeCategoryCount", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getEmployeeCategoryCount(
			@RequestBody EmployeeCategoryMasterDto employeeCategoryMasterDto) {
		try {
			return iEmployeeCategoryMasterService.getEmployeeCategoryCount(employeeCategoryMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}
	
}
