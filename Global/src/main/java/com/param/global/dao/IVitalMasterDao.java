package com.param.global.dao;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;

import com.param.global.common.Response;
import com.param.global.dto.VitalMasterDto;
import com.param.global.model.VitalMaster;

public interface IVitalMasterDao extends IGenericDao<VitalMaster, Integer>{

	public Response getVitalMasterList(VitalMasterDto vitalMasterDto)throws ApplicationException;
}
