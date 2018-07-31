package com.param.lis.transaction.service;

import java.util.List;
import com.param.lis.global.common.Response;
import com.param.lis.transaction.dto.BioChemParamDto;
import com.param.lis.transaction.dto.LabResultMasterDto;

import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface IBiochemValidationWorklistService {

	public Response getValidationWorkList(BioChemParamDto bioChemParamDto) throws ApplicationException;

	public Response getValidationWorkListCount(BioChemParamDto bioChemParamDto) throws ApplicationException;

	public Response getValidationWorkListDetails(List<BioChemParamDto> bioChemParamDtoList) throws ApplicationException;

	public Response saveValidationWorkList(List<LabResultMasterDto> listLabResultMasterDto) throws ApplicationException;
	
	public Response getPrivousResultBySample(BioChemParamDto bioChemParamDto) throws ApplicationException;
}
