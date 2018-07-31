package com.param.global.service;

import in.param.exception.ApplicationException;

import com.param.global.common.Response;
import com.param.global.dto.AllergyTypeMasterDto;

public interface IAllergyTypeMasterService {
	public Response getListOfAllergyTypeMaster(AllergyTypeMasterDto allergyTypeMasterDto)throws ApplicationException;
}
