package com.param.global.billing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.global.billing.dao.IEmployeeCategoryMasterDao;
import com.param.global.billing.dto.EmployeeCategoryMasterDto;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;

import in.param.exception.ApplicationException;

@Service
@SuppressWarnings({"rawtypes","unchecked"})
public class EmployeeCategoryMasterServiceImpl implements IEmployeeCategoryMasterService, ICommonConstants {

	@Autowired
	IEmployeeCategoryMasterDao iEmployeeCategoryMasterDao;


	@Override
	public Response saveEmployeeCategoryMaster(EmployeeCategoryMasterDto employeeCategoryMasterDto)
			throws ApplicationException {
		Response response = iEmployeeCategoryMasterDao.getEmployeeCategoryMasterByName(employeeCategoryMasterDto);

		try {
			if (response.getListObject().size() > 0 && response.getListObject() != null) {
				return new Response(ERROR, null, ALREADY_EXIST, null, null);
			} else {
				return iEmployeeCategoryMasterDao.saveEmployeeCategoryMaster(employeeCategoryMasterDto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new Response<>(ERROR, ERROR_CODE, null, null, null);
	}

	@Override
	public Response getEmployeeCategoryMasterById(int employeeId, int orgId) throws ApplicationException  {
		try {
			return iEmployeeCategoryMasterDao.getEmployeeCategoryMasterById(employeeId, orgId);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new Response<>(ERROR, ERROR_CODE, null, null, null);
	}

	
	@Override
	public Response getEmployeeCategoryMasterList(EmployeeCategoryMasterDto employeeCategoryMasterDto) throws ApplicationException {
		try {
			return iEmployeeCategoryMasterDao.getEmployeeCategoryMasterList(employeeCategoryMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new Response<>(ERROR, ERROR_CODE, null, null, null);
	}

	@Override
	public Response updateEmployeeCategoryMaster(EmployeeCategoryMasterDto employeeCategoryMasterDto)
			throws ApplicationException{
		Response response = iEmployeeCategoryMasterDao.getEmployeeCategoryMasterByNameNotId(employeeCategoryMasterDto);

		try {
			if (response.getListObject().size() > 0 && response.getListObject() != null) {
				return new Response(ERROR, null, ALREADY_EXIST, null, null);
			} else {
				return iEmployeeCategoryMasterDao.updateEmployeeCategoryMaster(employeeCategoryMasterDto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new Response<>(ERROR, ERROR_CODE, null, null, null);
	}

	@Override
	public Response updateEmployeeCategoryMasterStatus(EmployeeCategoryMasterDto employeeCategoryMasterDto)
			throws ApplicationException {
		try {
			return iEmployeeCategoryMasterDao.updateEmployeeCategoryMasterStatus(employeeCategoryMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new Response<>(ERROR, ERROR_CODE, null, null, null);
	}

	@Override
	public Response getEmployeeCategoryCount(EmployeeCategoryMasterDto employeeCategoryMasterDto)
			throws ApplicationException {
		try{
			return iEmployeeCategoryMasterDao.getEmployeeCategoryCount(employeeCategoryMasterDto);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, null, null, null);
	}

}
