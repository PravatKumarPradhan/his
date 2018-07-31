package com.param.global.dao;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;

import com.param.global.common.Response;
import com.param.global.dto.RelationMasterDto;
import com.param.global.model.RelationMaster;
@SuppressWarnings("rawtypes")
public interface IRelationMasterDao extends IGenericDao<RelationMaster, Integer>{

	
	Response getCountryByName(RelationMasterDto relationMasterDto)throws ApplicationException;

	Response addCountryMaster(RelationMasterDto relationMasterDto)throws ApplicationException;

	Response getRelationMasterList(RelationMasterDto relationMasterDto)throws ApplicationException;

	Response getRelationById(RelationMasterDto relationMasterDto)throws ApplicationException;

	Response getActiveRelationList()throws ApplicationException;

	Response getRelationByNameNotId(RelationMasterDto relationMasterDto)throws ApplicationException;

	Response updateRelationMaster(RelationMasterDto relationMasterDto)throws ApplicationException;

	Response updateRelationStatus(RelationMasterDto relationMasterDto)throws ApplicationException;
	
	Response getCount(RelationMasterDto relationMasterDto)throws ApplicationException;

}
