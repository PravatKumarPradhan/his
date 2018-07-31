package com.param.lis.global.dao;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;
import com.param.entity.lis.global.HourMaster;
import com.param.lis.global.common.Response;

@SuppressWarnings("rawtypes")
public interface IHourMasterDao extends IGenericDao<HourMaster, Integer>{
	
	public Response getListHourMaster() throws ApplicationException;
}
