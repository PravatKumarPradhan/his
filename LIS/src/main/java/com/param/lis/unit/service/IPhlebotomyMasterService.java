package com.param.lis.unit.service;

import in.param.exception.ApplicationException;

import com.param.lis.global.common.Response;
import com.param.lis.unit.dto.PhlebotomyMasterDto;

@SuppressWarnings("rawtypes")
public interface IPhlebotomyMasterService {
	public Response addPhlebotomyMaster(PhlebotomyMasterDto phlebotomyMasterDto)throws ApplicationException;
	
	public Response getPhlebotomyMasterById(Integer orgId,Integer unitId, Integer phlebotomyId) throws ApplicationException;

	public Response updatePhlebotomyMaster(PhlebotomyMasterDto phlebotomyMasterDto) throws ApplicationException;
	
	public Response ActivateInactivatePhlebotomyMaster(Integer orgId, Integer phlebotomyId, Character phlebotomyStatus) throws ApplicationException;

	public Response listPhlebotomyMaster(Integer orgId, Integer offset, Integer recordPerPage) throws ApplicationException;

	public Response getToTalPhlebotomyMasterRecord(Integer orgId) throws ApplicationException;
}
