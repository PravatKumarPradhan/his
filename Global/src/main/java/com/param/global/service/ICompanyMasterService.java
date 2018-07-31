package com.param.global.service;

import com.param.global.common.Response;
import com.param.global.dto.CompanyMasterDto;

import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface ICompanyMasterService {
	public Response getActiveCompanyMasterList(CompanyMasterDto companyMasterDto)throws ApplicationException;
}
