package com.param.lis.global.dao;

import com.param.entity.lis.global.OrganismMaster;
import com.param.lis.global.common.Response;
import com.param.lis.global.dto.OrganismMasterDto;
import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface IOrganismMasterDao extends IGenericDao<OrganismMaster, Integer>{
	
	
	public Response addOrganismMaster(OrganismMasterDto organismMasterDto)throws ApplicationException;
	public Response checkOrganismMaster(OrganismMasterDto organismMasterDto) throws ApplicationException;
	public Response updateOrganismMaster(OrganismMasterDto organismMasterDto) throws ApplicationException;
	public Response updateCheckOrganismMasterCodeAvaiable(OrganismMasterDto organismMasterDto) throws ApplicationException;
	public Response ActivateInactivateOrganismMaster(Integer orgId, Integer  organismMasterDto, Character  organismStatus) throws ApplicationException;
	public Response listOrganismMaster(Integer orgId, Integer offset, Integer recordPerPage) throws ApplicationException;
	public Response getToTalOrganismMaster(Integer orgId) throws ApplicationException;
	public Response getOrganismMasterById(Integer orgId, Integer  organismId)
			throws ApplicationException;
}
