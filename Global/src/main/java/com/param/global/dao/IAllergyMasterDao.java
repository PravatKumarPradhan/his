package com.param.global.dao;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;

import com.param.global.common.Response;
import com.param.global.dto.AllergyMasterDto;
import com.param.global.model.AllergyMaster;

public interface IAllergyMasterDao extends IGenericDao<AllergyMaster, Integer>{
	
	public Response getListOfAllergyMaster(AllergyMasterDto allergyMasterDto)throws ApplicationException;
	
}
