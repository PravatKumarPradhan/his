package com.param.lis.global.dao;

import com.param.entity.lis.global.PrerequisitesMaster;
import com.param.lis.global.common.Response;
import com.param.lis.global.dto.PrerequisitesMasterDto;
import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface IPrerequisitesDao extends IGenericDao<PrerequisitesMaster, Integer>
{
	public Response addPrerequisitesMaster(PrerequisitesMasterDto prerequisitesMasterDto) throws ApplicationException;

	public Response getPrerequisitesMasterById(Integer orgId, Integer prerequisitesId) throws ApplicationException;

	public Response updatePrerequisitesMaster(PrerequisitesMasterDto prerequisitesMasterDto) throws ApplicationException;

	public Response ActivateInactivatePrerequisitesMaster(Integer orgId, Integer prerequisitesId, Character prerequisitesStatus)
			throws ApplicationException;

	public Response listPrerequisitesMaster(Integer orgId, Integer offset, Integer recordPerPage) throws ApplicationException;

	public Response getToTalPrerequisitesMasterRecord(Integer orgId) throws ApplicationException;

}
