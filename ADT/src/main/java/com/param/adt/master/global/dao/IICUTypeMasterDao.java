package com.param.adt.master.global.dao;

import com.param.adt.common.Response;
import com.param.adt.master.global.dto.ICUTypeMasterDto;
import com.param.adt.master.global.model.ICUTypeMaster;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IICUTypeMasterDao extends  IGenericDao<ICUTypeMaster, Integer>
{
	
	public Response addICUTypeMaster(ICUTypeMasterDto icuTypeMasterDto ) throws ApplicationException;

	public Response getICUByName(ICUTypeMasterDto icuTypeMasterDto)throws ApplicationException;

	public Response getICUMasterList(ICUTypeMasterDto icuTypeMasterDto)throws ApplicationException;

	public Response getICUByID(ICUTypeMasterDto icuTypeMasterDto)throws ApplicationException;

	public Response getICUByNameNotId(ICUTypeMasterDto icuTypeMasterDto)throws ApplicationException;

	public Response updateICUTypeMaster(ICUTypeMasterDto icuTypeMasterDto)throws ApplicationException;

	public Response updateICUStatus(ICUTypeMasterDto icuTypeMasterDto)throws ApplicationException;

	public Response getCount(ICUTypeMasterDto icuTypeMasterDto)throws ApplicationException;
}
