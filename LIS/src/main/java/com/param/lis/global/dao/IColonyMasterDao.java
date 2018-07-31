package com.param.lis.global.dao;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;
import com.param.entity.lis.global.ColonyMaster;
import com.param.lis.global.common.Response;
import com.param.lis.global.dto.ColonyMasterDto;
import com.param.lis.global.dto.ReportTypeMasterDto;

@SuppressWarnings("rawtypes")
public interface IColonyMasterDao extends IGenericDao<ColonyMaster, Integer>{
	
	
	public Response addColonyMaster(ColonyMasterDto colonyMasterDto)throws ApplicationException;
	public Response checkColonyMaster(ColonyMasterDto colonyMasterDto) throws ApplicationException;
	public Response updateColonyMaster(ColonyMasterDto colonyMasterDto) throws ApplicationException;
	public Response updateCheckColonyCodeAvaiable(ColonyMasterDto colonyMasterDto) throws ApplicationException;
	public Response ActivateInactivateColonyMaster(Integer orgId, Integer colonyId, Character colonyStatus) throws ApplicationException;
	public Response listColonyMaster(Integer orgId, Integer offset, Integer recordPerPage) throws ApplicationException;
	public Response getToTalColonyMaster(Integer orgId) throws ApplicationException;
	public Response getColonyMasterById(Integer orgId, Integer colonyId)
			throws ApplicationException;
}
