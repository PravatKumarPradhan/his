package com.param.lis.global.service;

import in.param.exception.ApplicationException;

import com.param.lis.global.common.Response;
import com.param.lis.global.dto.BacteriaMasterDto;

@SuppressWarnings("rawtypes")
public interface IBacteriaMasterService {
	public Response addBacteriaMaster(BacteriaMasterDto bacteriaMasterDto)throws ApplicationException;
	public Response updateBacteriaMaster(BacteriaMasterDto bacteriaMasterDto) throws ApplicationException;
	public Response ActivateInactivateBacteriaMaster(Integer orgId, Integer reagentId, Character reagentStatus) throws ApplicationException;
	public Response listBacteriaMaster(Integer orgId, Integer offset, Integer recordPerPage) throws ApplicationException;
	public Response getToTalBacteriaMaster(Integer orgId) throws ApplicationException;
	public Response getBacteriaMasterById(Integer orgId, Integer reagentId)
			throws ApplicationException;
}
