package com.param.adt.master.global.service;

import com.param.adt.common.Response;
import com.param.adt.master.global.dto.ICUTypeMasterDto;

import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IICUTypeService 
{
	
	public Response addICUTypeMaster(ICUTypeMasterDto icuTypeMasterDto) throws ApplicationException;

	public Response getICUTypeMasterList(ICUTypeMasterDto icuTypeMasterDto)throws ApplicationException;

	public Response getSpecialityById(ICUTypeMasterDto icuTypeMasterDto)throws ApplicationException;

	public Response updateICUTypeMaster(ICUTypeMasterDto icuTypeMasterDto)throws ApplicationException;

	public Response updateStatusForICU(ICUTypeMasterDto icuTypeMasterDto)throws ApplicationException;

	public Response getICUCount(ICUTypeMasterDto icuTypeMasterDto) throws ApplicationException;

	
}
