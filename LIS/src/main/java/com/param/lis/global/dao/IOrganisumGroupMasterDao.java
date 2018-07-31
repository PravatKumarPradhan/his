package com.param.lis.global.dao;

import com.param.entity.lis.global.OrganismGroupMaster;
import com.param.lis.global.common.Response;
import com.param.lis.global.dto.OrganisumGroupMasterDto;
import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface IOrganisumGroupMasterDao extends IGenericDao<OrganismGroupMaster, Integer>{
	
	
	public Response addOrganisumGroupMaster(OrganisumGroupMasterDto organisumGroupMasterDto)throws ApplicationException;
	public Response checkOrganisumGroupMaster(OrganisumGroupMasterDto organisumGroupMasterDto) throws ApplicationException;
	public Response updateOrganisumGroupMaster(OrganisumGroupMasterDto organisumGroupMasterDto) throws ApplicationException;
	public Response updateCheckOrganisumGroupMasterCodeAvaiable(OrganisumGroupMasterDto organisumGroupMasterDto) throws ApplicationException;
	public Response ActivateInactivateOrganisumGroupMaster(Integer orgId, Integer  organisumGroupId, Character  organisumGroupStatus) throws ApplicationException;
	public Response listOrganisumGroupMaster(Integer orgId, Integer offset, Integer recordPerPage) throws ApplicationException;
	public Response getToTalOrganisumGroupMaster(Integer orgId) throws ApplicationException;
	public Response getOrganisumGroupMasterById(Integer orgId, Integer  organisumGroupId)
			throws ApplicationException;
}
