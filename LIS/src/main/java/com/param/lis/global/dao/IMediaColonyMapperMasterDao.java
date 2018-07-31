package com.param.lis.global.dao;

import in.param.exception.ApplicationException;

import java.util.List;

import com.param.lis.common.dto.CheckTranSactionExistsDto;
import com.param.lis.common.dto.CommonDto;
import com.param.lis.global.common.Response;
import com.param.lis.global.dto.MediaColonyMapperMasterDto;

@SuppressWarnings("rawtypes")
public interface IMediaColonyMapperMasterDao {
	
	public Response addMediaColonyMapperMaster(MediaColonyMapperMasterDto mediaColonyMapperMasterDto)throws ApplicationException;
	public Response listMediaColonyMapperMaster(Integer orgId, Integer offset, Integer recordPerPage) throws ApplicationException;
	public Response getToTalMediaColonyMapperMaster(Integer orgId) throws ApplicationException;
	public Response updateMediaColonyMapperMaster(List<MediaColonyMapperMasterDto> listmediaColonyMapperMasterDto) throws ApplicationException;
	public Response activateInactivateMediaColonyMapperMaster(Integer orgId,
			Integer mediaId, Character status) throws ApplicationException;
	public Response checkMediaColonyMapperAvaiable(MediaColonyMapperMasterDto mediaColonyMapperMasterDto) throws ApplicationException;
	
	public Response getMediaColonyMapperMasterById(Integer orgId, Integer mediaColonyMpprId) throws ApplicationException;
	public Response getMediaMasterClassById(Integer mediaId) throws ApplicationException;
	
	public Response getToTalMediaRecordCount(CheckTranSactionExistsDto checkTranSactionExistsDto) throws ApplicationException;
	
}
