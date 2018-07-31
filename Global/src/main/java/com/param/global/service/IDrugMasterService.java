package com.param.global.service;

import in.param.exception.ApplicationException;

import com.param.global.common.Response;
import com.param.global.dto.DrugMasterDto;

public interface IDrugMasterService {

	public Response getDrugMasterList(DrugMasterDto drugMasterDto)throws ApplicationException;
}
