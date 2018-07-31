package com.param.lis.microbiology.transaction.service;

import java.util.List;

import com.param.lis.global.common.Response;
import com.param.lis.microbiology.transaction.dto.TMicroSampleMediaDto;
import com.param.lis.transaction.dto.BioChemParamDto;
import com.param.lis.transaction.dto.LabSampleDetailsMasterDto;
import com.param.lis.transaction.dto.ResultEntryDataDto;

import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface IMicrobiologyService {
	
	public Response microAcceptancePending(BioChemParamDto bioChemParamDto) throws ApplicationException;

	public Response microAcceptancePendingCount(BioChemParamDto bioChemParamDto) throws ApplicationException;

	public Response microChemistryAcceptRejectSample(List<LabSampleDetailsMasterDto> listlabSampleDetailsMasterDto)
			throws ApplicationException;

	public Response getMicroChemistryWorklist(BioChemParamDto bioChemParamDto) throws ApplicationException;

	public Response getMicroChemistryWorklistCount(BioChemParamDto bioChemParamDto) throws ApplicationException;
	
	public Response sendForIncubationObjservation(List<LabSampleDetailsMasterDto> listlabSampleDetailsMasterDto)
			throws ApplicationException;

	public Response updateLabSampleDetailsMaster (LabSampleDetailsMasterDto labSampleDetailsMasterDto) throws ApplicationException;
	
	public Response getAddedMediaForIncuabation (Integer labSampleDtlsId,Integer orgId,Integer orgUniId) throws ApplicationException;
	
	public Response addMediaForIncuabation ( List<TMicroSampleMediaDto> listTMicroSampleMediaDto) throws ApplicationException;
}
