package com.param.lis.global.dao;

import java.util.List;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;
import com.param.entity.lis.global.SampleTypeMediaMappingMaster;
import com.param.lis.global.common.Response;
import com.param.lis.global.dto.SampleTypeMediaMappingMasterDto;

@SuppressWarnings("rawtypes")
public interface ISampleTypeMediaMappingMasterDao extends IGenericDao<SampleTypeMediaMappingMaster, Integer>{
	
	public Response addSampleTypeMediaMappingMaster(SampleTypeMediaMappingMasterDto sampleTypeMediaMappingMasterDto)throws ApplicationException;
	public Response getSampleTypeMediaMappingMasterById(Integer orgId, Integer sampleTypeId) throws ApplicationException;
	public Response updateSampleTypeMediaMappingMaster(List<SampleTypeMediaMappingMasterDto> listsampleTypeMediaMappingMasterDto) throws ApplicationException;
	public Response ActivateInactivateSampleTypeMediaMappingMaster(Integer orgId, Integer sampleTypeId, Character sampleTypeStatus) throws ApplicationException;
	public Response listSampleTypeMediaMappingMaster(Integer orgId, Integer offset, Integer recordPerPage) throws ApplicationException;
	public Response checkSampleTypeMediaMappingMaster(SampleTypeMediaMappingMasterDto sampleTypeMediaMappingMasterDto) throws ApplicationException;
	public Response getToTalSampleTypeMediaMappingMaster(Integer orgId) throws ApplicationException;
	public Response getSampleTypeAddtionClassMasterById(Integer orgId, Integer sampleTypeMediaMappingMpprId) throws ApplicationException;
	public Response getMediaMasterClassById(Integer mediaId) throws ApplicationException;
}
