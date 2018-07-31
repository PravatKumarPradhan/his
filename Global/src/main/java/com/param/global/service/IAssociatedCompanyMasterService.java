package com.param.global.service;

import com.param.global.common.Response;
import com.param.global.dto.AssociatedCompanyMasterDto;

import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IAssociatedCompanyMasterService {
	public Response getAssociatedCompanyMaster(AssociatedCompanyMasterDto associatedCompanyMasterDto)throws ApplicationException;
}
