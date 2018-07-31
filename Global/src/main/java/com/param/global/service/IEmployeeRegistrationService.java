package com.param.global.service;

import javax.transaction.Transactional;

import com.param.global.common.Response;
import com.param.global.dto.EmployeeRegistrationDto;

import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IEmployeeRegistrationService {

	@Transactional
	Response saveEmployeeRegistration(EmployeeRegistrationDto employeeRegistrationDto)throws ApplicationException;

	@Transactional
	Response getEmployeeDetails(EmployeeRegistrationDto employeeRegistrationDto)throws ApplicationException;

	@Transactional
	Response updateEmployeeDetails(EmployeeRegistrationDto employeeRegistrationDto)throws ApplicationException;

	@Transactional
	Response updateEmployeeStatus(EmployeeRegistrationDto employeeRegistrationDto)throws ApplicationException;

	@Transactional
	Response searchEmployeeByCriteria(EmployeeRegistrationDto employeeRegistrationDto)throws ApplicationException;

}
