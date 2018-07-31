package com.param.lis.global.dao;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;
import com.param.entity.lis.global.MediaMaster;
import com.param.lis.global.common.Response;
import com.param.lis.global.dto.MediaMasterDto;
import com.param.lis.global.dto.PrerequisitesMasterDto;
import com.param.lis.global.dto.ReportTypeMasterDto;

@SuppressWarnings("rawtypes")
public interface IMediaMasterDao extends IGenericDao<MediaMaster, Integer>{
	public Response saveMediaMaster(MediaMasterDto mediaMasterDto)throws ApplicationException;
	public Response checkMediaCodeAvaiable(MediaMasterDto mediaMasterDto) throws ApplicationException;
	public Response getMediaMasterById(Integer orgId, Integer prerequisitesId) throws ApplicationException;

	public Response updateMediaMaster(MediaMasterDto mediaMasterDto) throws ApplicationException;
	public Response updateCheckMediaCodeAvaiable(MediaMasterDto mediaMasterDto) throws ApplicationException;
	public Response ActivateInactivateMediaMaster(Integer orgId, Integer prerequisitesId, Character prerequisitesStatus) throws ApplicationException;

	public Response listMediaMaster(Integer orgId, Integer offset, Integer recordPerPage) throws ApplicationException;

	public Response getToTalMediaMasterRecord(Integer orgId) throws ApplicationException;
}
