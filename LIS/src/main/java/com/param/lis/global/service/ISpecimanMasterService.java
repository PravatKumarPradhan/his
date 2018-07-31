package com.param.lis.global.service;

import in.param.exception.ApplicationException;

import com.param.lis.global.common.Response;
import com.param.lis.global.dto.SpecimanMasterDto;

@SuppressWarnings("rawtypes")
public interface ISpecimanMasterService {
	public Response addSpecimanMaster(SpecimanMasterDto specimanMasterDto)throws ApplicationException;
	public Response getSpecimanMasteById(Integer orgId, Integer specimanId) throws ApplicationException;
	public Response updateSpecimanMaster(SpecimanMasterDto specimanMasterDto) throws ApplicationException;
	public Response ActivateInactivateSpecimanMaster(Integer orgId, Integer specimanId, Character specimanStatus) throws ApplicationException;
	public Response listSpecimanMaster(Integer orgId, Integer offset, Integer recordPerPage) throws ApplicationException;
	public Response getToTalSpecimanMaster(Integer orgId) throws ApplicationException;
	public Response specimanGrossRequriedInSpecimanMaster(Integer orgId, Integer specimanId, Character specimanGross) throws ApplicationException;
	public Response specimanBlockRequriedInSpecimanMaster(Integer orgId, Integer specimanId, Character specimanBlock) throws ApplicationException;
}
