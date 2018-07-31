package com.param.lis.global.service;


import in.param.exception.ApplicationException;

import com.param.lis.common.dto.CheckTranSactionExistsDto;
import com.param.lis.global.common.Response;
import com.param.lis.global.dto.MediaColonyMapperMasterDto;

@SuppressWarnings("rawtypes")
public interface IMediaColonyMapperMasterService {
	
	public Response addMediaColonyMapperMaster(MediaColonyMapperMasterDto mediaColonyMapperMasterDto)throws ApplicationException;
	public Response listMediaColonyMapperMaster(Integer orgId, Integer offset, Integer recordPerPage) throws ApplicationException;
	public Response getToTalMediaColonyMapperMaster(Integer orgId) throws ApplicationException;
	public Response updateMediaColonyMapperMaster(MediaColonyMapperMasterDto mediaColonyMapperMasterDto) throws ApplicationException;
	public Response activateInactivateMediaColonyMapperMaster(Integer orgId,Integer mediaColonyMpprId, Character status) throws ApplicationException;
	public Response getMediaColonyMapperMasterById(Integer orgId, Integer mediaColonyMpprId) throws ApplicationException;
	public Response getMediaMasterClassById(Integer antiboiticClassId) throws ApplicationException;
	public Response getToTalMediaRecordCount(CheckTranSactionExistsDto sheckTranSactionExistsDto) throws ApplicationException;
	
}
