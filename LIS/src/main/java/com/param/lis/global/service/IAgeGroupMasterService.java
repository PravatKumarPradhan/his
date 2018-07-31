package com.param.lis.global.service;

import in.param.exception.ApplicationException;

import com.param.lis.global.common.Response;
import com.param.lis.global.dto.AgeGroupMasterDto;

@SuppressWarnings("rawtypes")
public interface IAgeGroupMasterService {
	
	public Response addAgeGroupMaster(AgeGroupMasterDto ageGroupMasterDto)throws ApplicationException;
	public Response getAgeGroupMasterById(Integer orgId, Integer ageGroupId) throws ApplicationException;
	public Response updateAgeGroupMaster(AgeGroupMasterDto ageGroupMasterDto) throws ApplicationException;
	public Response ActivateInactivateAgeGroupMaster(Integer orgId, Integer ageGroupId, Character ageGroupStatus) throws ApplicationException;
	public Response listAgeGroupMaster(Integer orgId, Integer offset, Integer recordPerPage) throws ApplicationException;
	public Response getToTalAgeGroupMasterRecord(Integer orgId) throws ApplicationException;
}
