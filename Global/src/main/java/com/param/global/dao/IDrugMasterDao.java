package com.param.global.dao;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;

import com.param.global.common.Response;
import com.param.global.dto.DrugMasterDto;
import com.param.global.model.DrugMaster;

public interface IDrugMasterDao extends IGenericDao<DrugMaster, Integer>{
	public Response getDrugMasterList(DrugMasterDto drugMasterDto)throws ApplicationException;
}
