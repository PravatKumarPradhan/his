package com.param.lis.global.dao;

import in.param.exception.ApplicationException;

import java.util.List;

import com.param.lis.global.common.Response;
import com.param.lis.global.dto.AntibioticOrganismMpprMasterDto;

@SuppressWarnings("rawtypes")
public interface IAntibioticOrganismMpprMasterDao {
	
	public Response addAntibioticOrganismMpprMaster(AntibioticOrganismMpprMasterDto antibioticOrganismMpprMasterDto)throws ApplicationException;
	public Response listAntibioticOrganismMpprMaster(Integer orgId, Integer offset, Integer recordPerPage) throws ApplicationException;
	public Response getToTalAntibioticOrganismMpprMaster(Integer orgId) throws ApplicationException;
	public Response updateAntibioticOrganismMpprMaster(List<AntibioticOrganismMpprMasterDto> listAntibioticOrganismMpprMasterDto) throws ApplicationException;
	public Response activateInactivateAntibioticOrganismMpprMaster(Integer orgId,
			Integer antiboiticId, Character status) throws ApplicationException;
	public Response checkAntibioticOrganismMpprMasterAvaiable(AntibioticOrganismMpprMasterDto antibioticOrganismMpprMasterDto) throws ApplicationException;
	
	public Response getAntibioticOrganismMpprMasterById(Integer orgId, Integer antibioticOrganismMpprId) throws ApplicationException;
	public Response getAntibioticByOrganismId(Integer antibioticId) throws ApplicationException;
	
}
