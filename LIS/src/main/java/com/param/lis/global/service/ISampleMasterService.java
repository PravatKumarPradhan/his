package com.param.lis.global.service;

import com.param.lis.global.common.Response;
import com.param.lis.global.dto.SampleMasterDto;

import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface ISampleMasterService
{
	public Response addSampleMaster(SampleMasterDto sampleMasterDto) throws ApplicationException;

	public Response getSampleMasterById(Integer orgId, Integer sampleId) throws ApplicationException;

	public Response updateSampleMaster(SampleMasterDto sampleMasterDto) throws ApplicationException;
	
	public Response ActivateInactivateSampleMaster(Integer orgId, Integer sampleId, Character sampleStatus) throws ApplicationException;

	public Response listSampleMaster(Integer orgId, Integer offset, Integer recordPerPage) throws ApplicationException;

	public Response getToTalSampleMasterRecord(Integer orgId) throws ApplicationException;

}
