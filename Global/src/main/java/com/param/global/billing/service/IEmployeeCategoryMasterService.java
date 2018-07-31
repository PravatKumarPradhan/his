package com.param.global.billing.service;

import javax.transaction.Transactional;

import com.param.global.billing.dto.EmployeeCategoryMasterDto;
import com.param.global.common.Response;

import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
@Transactional
public interface IEmployeeCategoryMasterService {

	
	Response saveEmployeeCategoryMaster(EmployeeCategoryMasterDto employeeCategoryMasterDto) throws ApplicationException;

	Response getEmployeeCategoryMasterById(int employeeId, int orgId) throws ApplicationException;

	Response getEmployeeCategoryMasterList(EmployeeCategoryMasterDto employeeCategoryMasterDto) throws ApplicationException;

	Response updateEmployeeCategoryMaster(EmployeeCategoryMasterDto employeeCategoryMasterDto) throws ApplicationException;

	Response updateEmployeeCategoryMasterStatus(EmployeeCategoryMasterDto employeeCategoryMasterDto) throws ApplicationException;

	Response getEmployeeCategoryCount(EmployeeCategoryMasterDto employeeCategoryMasterDto) throws ApplicationException;

}
