package com.param.lis.global.service;

import in.param.exception.ApplicationException;

import com.param.lis.global.common.Response;
import com.param.lis.global.dto.MediaMasterDto;

@SuppressWarnings("rawtypes")
public interface IMediaMasterService {
	public Response saveMediaMaster(MediaMasterDto mediaMasterDto)throws ApplicationException;
	
	public Response getMediaMasterById(Integer orgId, Integer prerequisitesId) throws ApplicationException;

	public Response updateMediaMaster(MediaMasterDto mediaMasterDto) throws ApplicationException;
	
	public Response ActivateInactivateMediaMaster(Integer orgId, Integer mediaId, Character mediaStatus) throws ApplicationException;

	public Response listMediaMaster(Integer orgId, Integer offset, Integer recordPerPage) throws ApplicationException;

	public Response getToTalMediaMasterRecord(Integer orgId) throws ApplicationException;
}
