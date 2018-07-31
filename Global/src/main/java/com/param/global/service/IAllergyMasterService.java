package com.param.global.service;

import in.param.exception.ApplicationException;

import com.param.global.common.Response;
import com.param.global.dto.AllergyMasterDto;

public interface IAllergyMasterService {

	public Response getListOfAllergyMaster(AllergyMasterDto allergyMasterDto)throws ApplicationException;
}
