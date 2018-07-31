package com.param.lis.global.dao;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;
import com.param.entity.lis.global.BactClassificationMaster;
import com.param.lis.global.common.Response;
import com.param.lis.global.dto.BactClassificationMasterDto;
import com.param.lis.global.dto.ReportTypeMasterDto;

@SuppressWarnings("rawtypes")
public interface IBacteriaClassficationMasterDao extends IGenericDao<BactClassificationMaster, Integer>{
	
	public Response addBacteriaClassficationMaster(BactClassificationMasterDto bacteriaClassficationMasterDto)throws ApplicationException;
	public Response getBacteriaClassficationMasterById(Integer orgId, Integer bacteriaId) throws ApplicationException;
	public Response updateBacteriaClassficationMaster(BactClassificationMasterDto bacteriaClassficationMasterDto) throws ApplicationException;
	public Response updateCheckBacteriaClassficationCodeAvaiable(BactClassificationMasterDto bactClassificationMasterDto) throws ApplicationException;
	public Response ActivateInactivateBacteriaClassficationMaster(Integer orgId, Integer bacteriaId, Character bacteriaStatus) throws ApplicationException;
	public Response listBacteriaClassficationMaster(Integer orgId, Integer offset, Integer recordPerPage) throws ApplicationException;
	public Response checkBacteriaClassficationMaster(BactClassificationMasterDto bacteriaClassficationMasterDto) throws ApplicationException;
	public Response getToTalBacteriaClassficationMaster(Integer orgId) throws ApplicationException;
}
