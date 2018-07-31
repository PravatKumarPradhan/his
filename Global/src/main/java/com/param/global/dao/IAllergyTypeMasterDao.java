package com.param.global.dao;

import com.param.global.common.Response;
import com.param.global.dto.AllergyTypeMasterDto;
import com.param.global.model.AllergyTypeMaster;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;

public interface IAllergyTypeMasterDao extends IGenericDao<AllergyTypeMaster, Integer>{

	public Response getListOfAllergyTypeMaster(AllergyTypeMasterDto allergyTypeMasterDto)throws ApplicationException;
}
