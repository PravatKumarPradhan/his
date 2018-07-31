package com.param.global.dao;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;

import com.param.global.common.Response;
import com.param.global.dto.SeverityMasterDto;
import com.param.global.model.SeverityMaster;

public interface ISeverityMasterDao extends IGenericDao<SeverityMaster, Integer>{

	public Response getListOfSeverityMaster(SeverityMasterDto severityMasterDto)throws ApplicationException;
}
