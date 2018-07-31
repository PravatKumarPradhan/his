package com.param.lis.global.dao;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;
import com.param.entity.lis.global.AgeTypeGroupMaster;
import com.param.lis.global.common.Response;

@SuppressWarnings("rawtypes")
public interface IAgeTypeGroupMasterDao extends IGenericDao<AgeTypeGroupMaster, Integer>{
	
	public Response getListAgeTypeGroupMaster() throws ApplicationException;
}
