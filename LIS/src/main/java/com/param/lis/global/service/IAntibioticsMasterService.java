package com.param.lis.global.service;

import in.param.exception.ApplicationException;

import com.param.lis.global.common.Response;
import com.param.lis.global.dto.AntibioticClassMasterDto;
import com.param.lis.global.dto.BacteriaMasterDto;

@SuppressWarnings("rawtypes")
public interface IAntibioticsMasterService {
	public Response addAntibioticsMaster(AntibioticClassMasterDto antibioticClassMasterDto)throws ApplicationException;
	public Response getListAntibioticsMaster() throws ApplicationException;
	public Response updateAntibioticsMaster(AntibioticClassMasterDto antibioticClassMasterDto) throws ApplicationException;
	public Response ActivateInactivateAntibioticsMaster(Integer orgId, Integer antibioticId, Character antibioticStatus) throws ApplicationException;
	public Response listAntibioticsMaster(Integer orgId, Integer offset, Integer recordPerPage) throws ApplicationException;
	public Response getToTalAntibioticsMaster(Integer orgId) throws ApplicationException;
	public Response getAntibioticsMasterById(Integer orgId, Integer reagentId)
			throws ApplicationException;
}
