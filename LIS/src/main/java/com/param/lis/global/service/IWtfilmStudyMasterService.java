package com.param.lis.global.service;

import in.param.exception.ApplicationException;

import com.param.lis.global.common.Response;
import com.param.lis.global.dto.WtfilmStudyMasterDto;

@SuppressWarnings("rawtypes")
public interface IWtfilmStudyMasterService {
	public Response addWtfilmStudyMaster(WtfilmStudyMasterDto wtfilmStudyMasterDto)throws ApplicationException;
	public Response updateWtfilmStudyMaster(WtfilmStudyMasterDto wtfilmStudyMasterDto) throws ApplicationException;
	public Response ActivateInactivateWtfilmStudyMaster(Integer orgId, Integer wtfilmStudyId, Character wtfilmStudyStatus) throws ApplicationException;
	public Response listWtfilmStudyMaster(Integer orgId, Integer offset, Integer recordPerPage) throws ApplicationException;
	public Response getToTalWtfilmStudyMaster(Integer orgId) throws ApplicationException;
	public Response getWtfilmStudyMasterById(Integer orgId, Integer wtfilmStudyId)
			throws ApplicationException;
}
