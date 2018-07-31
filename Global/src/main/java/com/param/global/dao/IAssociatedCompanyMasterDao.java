package com.param.global.dao;

import com.param.global.common.Response;
import com.param.global.dto.AssociatedCompanyMasterDto;
import com.param.global.model.AssociatedCompanyMaster;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IAssociatedCompanyMasterDao extends IGenericDao<AssociatedCompanyMaster, Integer>{
	public Response getAssociatedCompanyMaster(AssociatedCompanyMasterDto associatedCompanyMasterDto)throws ApplicationException;
}
