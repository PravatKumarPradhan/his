package com.param.lis.transaction.service;

import com.param.lis.global.common.Response;
import com.param.lis.transaction.dto.BioChemParamDto;

import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface IReportHandOverService{

	public Response getReportHandList(BioChemParamDto bioChemParamDto) throws ApplicationException;

	public Response getReportHandListCount(BioChemParamDto bioChemParamDto) throws ApplicationException;

	
}
