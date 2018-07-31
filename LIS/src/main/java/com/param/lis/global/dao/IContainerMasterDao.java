package com.param.lis.global.dao;

import com.param.entity.lis.global.ContainerMaster;
import com.param.lis.global.common.Response;
import com.param.lis.global.dto.ContainerMasterDto;
import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface IContainerMasterDao extends IGenericDao<ContainerMaster, Integer>
{
	public Response addContainerMaster(ContainerMasterDto containerMasterDto) throws ApplicationException;

	public Response getContainerMasterById(Integer orgId, Integer sampleId) throws ApplicationException;

	public Response updateContainerMaster(ContainerMasterDto containerMasterDto) throws ApplicationException;

	public Response ActivateInactivateContainerMaster(Integer orgId, Integer sampleId, Character sampleStatus)
			throws ApplicationException;

	public Response listContainerMaster(Integer orgId, Integer offset, Integer recordPerPage) throws ApplicationException;

	public Response getToTalContainerMasterRecord(Integer orgId) throws ApplicationException;

}
