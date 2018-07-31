package com.param.lis.global.service;

import in.param.exception.ApplicationException;

import com.param.lis.global.common.Response;
import com.param.lis.global.dto.SampleTypeMediaMappingMasterDto;

@SuppressWarnings("rawtypes")
public interface ISampleTypeMediaMappingMasterService {
	
	public Response addSampleTypeMediaMappingMaster(SampleTypeMediaMappingMasterDto sampleTypeMediaMappingMasterDto)throws ApplicationException;
	public Response getSampleTypeMediaMappingMasterById(Integer orgId, Integer sampleTypeId) throws ApplicationException;
	public Response updateSampleTypeMediaMappingMaster(SampleTypeMediaMappingMasterDto sampleTypeMediaMappingMasterDto) throws ApplicationException;
	public Response ActivateInactivateSampleTypeMediaMappingMaster(Integer orgId, Integer sampleTypeId, Character sampleTypeStatus) throws ApplicationException;
	public Response listSampleTypeMediaMappingMaster(Integer orgId, Integer offset, Integer recordPerPage) throws ApplicationException;
	public Response getToTalSampleTypeMediaMappingMaster(Integer orgId) throws ApplicationException;
	public Response getSampleTypeAddtionClassMasterById(Integer orgId, Integer sampleTypeMediaMappingMpprId) throws ApplicationException;
	public Response getMediaMasterClassById(Integer mediaId) throws ApplicationException;
}
