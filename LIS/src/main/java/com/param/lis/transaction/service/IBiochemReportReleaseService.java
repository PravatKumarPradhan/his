package com.param.lis.transaction.service;

import java.util.List;
import com.param.lis.global.common.Response;
import com.param.lis.transaction.dto.BioChemParamDto;
import com.param.lis.transaction.dto.LabResultMasterDto;

import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface IBiochemReportReleaseService {

	public Response getReportReleaseWorkList(BioChemParamDto bioChemParamDto) throws ApplicationException;

	public Response getReportReleaseWorkListCount(BioChemParamDto bioChemParamDto) throws ApplicationException;

	public Response getReportReleaseWorkListDetails(List<BioChemParamDto> bioChemParamDtoList) throws ApplicationException;

	public Response saveReportReleaseWorkList(List<LabResultMasterDto> listLabResultMasterDto) throws ApplicationException;
}
