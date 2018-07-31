package com.param.lis.global.dao;

import in.param.exception.ApplicationException;

import java.util.List;

import com.param.lis.global.common.Response;
import com.param.lis.global.dto.AntibioticGroupAdditionMasterDto;

@SuppressWarnings("rawtypes")
public interface IAntibioticGroupAddtionMasterDao {
	
	public Response addAntibioticGroupAddtionMaster(AntibioticGroupAdditionMasterDto antibioticGroupAdditionMasterDto)throws ApplicationException;
	public Response listAntibioticGroupAddtionMaster(Integer orgId, Integer offset, Integer recordPerPage) throws ApplicationException;
	public Response getToTalAntibioticGroupAddtionMaster(Integer orgId) throws ApplicationException;
	public Response updateAntibioticGroupAddtionMaster(List<AntibioticGroupAdditionMasterDto> listantibioticGroupAdditionMasterDto) throws ApplicationException;
	public Response activateInactivateAntibioticGroupAddtionMaster(Integer orgId,
			Integer antibioticGroupMpprId, Character status) throws ApplicationException;
	public Response checkAntibioticGroupAvaiable(AntibioticGroupAdditionMasterDto antibioticGroupAdditionMasterDto) throws ApplicationException;
	
	public Response getAntibioticGroupAddtionMasterById(Integer orgId, Integer antibioticGroupMpprId) throws ApplicationException;
	public Response getAntibioticGroupClassById(Integer antibioticGroupId) throws ApplicationException;
	
}
