package com.param.lis.histopathology.transaction.dao;

import com.param.entity.lis.histo.TSubSpecimanMaster;
import com.param.lis.global.common.Response;
import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface ITSubSpecimemDao 
{
	public Response saveSubSpecimanDetails(TSubSpecimanMaster tSubSpecimanMaster)throws ApplicationException;
}
