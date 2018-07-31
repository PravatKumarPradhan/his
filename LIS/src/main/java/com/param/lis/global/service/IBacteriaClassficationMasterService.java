package com.param.lis.global.service;

import in.param.exception.ApplicationException;

import com.param.lis.global.common.Response;
import com.param.lis.global.dto.BactClassificationMasterDto;

@SuppressWarnings("rawtypes")
public interface IBacteriaClassficationMasterService {
	public Response addBacteriaClassficationMaster(BactClassificationMasterDto bacteriaClassficationMasterDto)throws ApplicationException;
	public Response getBacteriaClassficationMasterById(Integer orgId, Integer bacteriaId) throws ApplicationException;
	public Response updateBacteriaClassficationMaster(BactClassificationMasterDto bacteriaClassficationMasterDto) throws ApplicationException;
	public Response ActivateInactivateBacteriaClassficationMaster(Integer orgId, Integer bacteriaId, Character bacteriaStatus) throws ApplicationException;
	public Response listBacteriaClassficationMaster(Integer orgId, Integer offset, Integer recordPerPage) throws ApplicationException;
	public Response getToTalBacteriaClassficationMaster(Integer orgId) throws ApplicationException;
}
