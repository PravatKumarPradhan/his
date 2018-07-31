package com.param.lis.global.service;

import in.param.exception.ApplicationException;

import com.param.lis.global.common.Response;
import com.param.lis.global.dto.AntibioticAdditionMasterDto;
import com.param.lis.global.dto.AntibioticGroupAdditionMasterDto;

@SuppressWarnings("rawtypes")
public interface IAntibioticGroupAddtionMasterService {
	
	public Response addAntibioticGroupAddtionMaster(AntibioticGroupAdditionMasterDto antibioticGroupAdditionMasterDto)throws ApplicationException;
	public Response listAntibioticGroupAddtionMaster(Integer orgId, Integer offset, Integer recordPerPage) throws ApplicationException;
	public Response getToTalAntibioticGroupAddtionMaster(Integer orgId) throws ApplicationException;
	public Response updateAntibioticGroupAddtionMaster(AntibioticGroupAdditionMasterDto antibioticGroupAdditionMasterDto) throws ApplicationException;
	public Response activateInactivateAntibioticGroupAddtionMaster(Integer orgId,
			Integer antibioticGroupId, Character status) throws ApplicationException;
	public Response getAntibioticGroupAddtionMasterById(Integer orgId, Integer antiboiticGroupMpprId) throws ApplicationException;

	public Response getAntibioticGroupMasterClassById(Integer antibioticGroupId) throws ApplicationException;
	
}
