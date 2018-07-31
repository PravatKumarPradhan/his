package com.param.global.dao;

import com.param.global.common.Response;
import com.param.global.dto.EmployeeRegistrationDto;
import com.param.global.model.EmployeeMaster;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;


@SuppressWarnings("rawtypes")
public interface IEmployeeRegistrationDao extends IGenericDao<EmployeeMaster, Integer>{

	Response saveEmployeeRegistration(EmployeeRegistrationDto employeeRegistrationDto)throws ApplicationException;

	Response checkUniqueEmployee(EmployeeRegistrationDto employeeRegistrationDto)throws ApplicationException;

	Response saveEmployeeDetails(EmployeeRegistrationDto employeeRegistrationDto)throws ApplicationException;

	Response getEmployeeDetails(EmployeeRegistrationDto employeeRegistrationDto)throws ApplicationException;

	Response updateEmployeeDetails(EmployeeRegistrationDto employeeRegistrationDto)throws ApplicationException;

	Response updateEmployeeStatus(EmployeeRegistrationDto employeeRegistrationDto)throws ApplicationException;

	Response searchEmployeeByCriteria(EmployeeRegistrationDto employeeRegistrationDto)throws ApplicationException;
}
