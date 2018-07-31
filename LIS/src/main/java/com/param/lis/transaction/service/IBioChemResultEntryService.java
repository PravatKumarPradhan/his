package com.param.lis.transaction.service;

import java.util.List;
import com.param.lis.global.common.Response;
import com.param.lis.transaction.dto.BioChemParamDto;
import com.param.lis.transaction.dto.LabResultMasterDto;

import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface IBioChemResultEntryService
{
	public Response getResultEntryList(BioChemParamDto bioChemParamDto) throws ApplicationException;

	public Response getResultEntryListCount(BioChemParamDto bioChemParamDto) throws ApplicationException;

	public Response getResultEntryDetails(List<BioChemParamDto> bioChemParamDtoList) throws ApplicationException;

	public Response saveEntryDetails(List<LabResultMasterDto> listLabResultMasterDto) throws ApplicationException;
	
	public Response getSingleParamTestResult(BioChemParamDto bioChemParamDto) throws ApplicationException;

	public Response getMultyParamTestResult(BioChemParamDto bioChemParamDto) throws ApplicationException;
}
