package com.param.global.dao;

import com.param.global.common.Response;
import com.param.global.dto.CompanyMasterDto;
import com.param.global.model.CompanyMaster;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface ICompanyMasterDao extends IGenericDao<CompanyMaster, Integer>{
	public Response getActiveCompanyMasterList(CompanyMasterDto companyMasterDto)throws ApplicationException;
}
