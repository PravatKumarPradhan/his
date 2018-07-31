package com.param.lis.global.service;

import in.param.exception.ApplicationException;

import com.param.lis.global.common.Response;
import com.param.lis.global.dto.PrerequisitesMasterDto;

@SuppressWarnings("rawtypes")
public interface IPrerequisitesService
{
	public Response addPrerequisitesMaster(PrerequisitesMasterDto prerequisitesMasterDto) throws ApplicationException;

	public Response getPrerequisitesMasterById(Integer orgId, Integer prerequisitesId) throws ApplicationException;

	public Response updatePrerequisitesMaster(PrerequisitesMasterDto prerequisitesMasterDto) throws ApplicationException;
	
	public Response ActivateInactivatePrerequisitesMaster(Integer orgId, Integer prerequisitesId, Character prerequisitesStatus) throws ApplicationException;

	public Response listPrerequisitesMaster(Integer orgId, Integer offset, Integer recordPerPage) throws ApplicationException;

	public Response getToTalPrerequisitesMasterRecord(Integer orgId) throws ApplicationException;

}
