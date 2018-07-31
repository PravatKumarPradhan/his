package com.param.global.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dto.EmployeeRegistrationDto;
import com.param.global.service.IEmployeeRegistrationService;

@RestController
@RequestMapping(value="api/global")
@SuppressWarnings({ "unchecked", "rawtypes"})
public class EmployeeRegistrationController implements ICommonConstants{

	@Autowired
	IEmployeeRegistrationService iEmployeeRegistrationService;
	
	@RequestMapping(value="/employeeRegistration", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public synchronized @ResponseBody Response saveEmployeeRegistration(@RequestBody  EmployeeRegistrationDto employeeRegistrationDto) {
		try {
			return iEmployeeRegistrationService.saveEmployeeRegistration(employeeRegistrationDto);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR, COMMON_ERROR_MESSAGE, null, null);
	}
	
	@RequestMapping(value="/getEmployeeDetails", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getEmployeeDetails(@RequestBody  EmployeeRegistrationDto employeeRegistrationDto) {
		try {
			return iEmployeeRegistrationService.getEmployeeDetails(employeeRegistrationDto);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR, COMMON_ERROR_MESSAGE, null, null);
	}
	
	@RequestMapping(value="/updateEmployeeDetails", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateEmployeeDetails(@RequestBody  EmployeeRegistrationDto employeeRegistrationDto) {
		try {
			return iEmployeeRegistrationService.updateEmployeeDetails(employeeRegistrationDto);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR, COMMON_ERROR_MESSAGE, null, null);
	}
	
	@RequestMapping(value="/updateEmployeeStatus", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateEmployeeStatus(@RequestBody  EmployeeRegistrationDto employeeRegistrationDto) {
		try {
			return iEmployeeRegistrationService.updateEmployeeStatus(employeeRegistrationDto);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR, COMMON_ERROR_MESSAGE, null, null);
	}
	
	@RequestMapping(value="/searchEmployeeByCriteria", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response searchEmployeeByCriteria(@RequestBody  EmployeeRegistrationDto employeeRegistrationDto) {
		try {
			return iEmployeeRegistrationService.searchEmployeeByCriteria(employeeRegistrationDto);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR, COMMON_ERROR_MESSAGE, null, null);
	}
}
