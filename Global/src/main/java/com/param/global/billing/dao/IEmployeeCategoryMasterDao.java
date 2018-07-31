package com.param.global.billing.dao;

import com.param.global.billing.dto.EmployeeCategoryMasterDto;
import com.param.global.common.Response;

import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface IEmployeeCategoryMasterDao {


	Response saveEmployeeCategoryMaster(EmployeeCategoryMasterDto employeeCategoryMasterDto) throws ApplicationException;

	Response getEmployeeCategoryMasterById(int employeeId, int orgId) throws ApplicationException;

	Response getEmployeeCategoryMasterList(EmployeeCategoryMasterDto employeeCategoryMasterDto) throws ApplicationException;

	Response updateEmployeeCategoryMaster(EmployeeCategoryMasterDto employeeCategoryMasterDto) throws ApplicationException;

	Response updateEmployeeCategoryMasterStatus(EmployeeCategoryMasterDto employeeCategoryMasterDto) throws ApplicationException;

	Response getEmployeeCategoryMasterByNameNotId(EmployeeCategoryMasterDto doctorCategoryemployeeCategoryMasterDtoMasterDto) throws ApplicationException;
	
	Response getEmployeeCategoryMasterByName(EmployeeCategoryMasterDto employeeCategoryMasterDto) throws ApplicationException;

	Response getEmployeeCategoryCount(EmployeeCategoryMasterDto employeeCategoryMasterDto) throws ApplicationException;
}
