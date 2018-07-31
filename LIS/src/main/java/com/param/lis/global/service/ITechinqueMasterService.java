package com.param.lis.global.service;

import com.param.lis.global.common.Response;
import com.param.lis.global.controller.TechinqueMasterController;
import com.param.lis.global.dto.SampleMasterDto;
import com.param.lis.global.dto.TechniqueMasterDto;

import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface ITechinqueMasterService
{
	public Response addTechinqueMaster(TechniqueMasterDto techniqueMasterDto) throws ApplicationException;

	public Response getTechinqueMasterById(Integer orgId, Integer TechinqueId) throws ApplicationException;

	public Response updateTechinqueMaster(TechniqueMasterDto techniqueMasterDto) throws ApplicationException;
	
	public Response ActivateInactivateTechinqueMaster(Integer orgId, Integer TechinqueId, Character TechinqueStatus) throws ApplicationException;

	public Response listTechinqueMaster(Integer orgId, Integer offset, Integer recordPerPage) throws ApplicationException;

	public Response getToTalTechinqueMasterRecord(Integer orgId) throws ApplicationException;

}
