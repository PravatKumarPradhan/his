package com.param.global.dao;

import com.param.global.common.Response;
import com.param.global.dto.GenderMasterDto;
import com.param.global.model.GenderMaster;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface IGenderMasterDao extends IGenericDao<GenderMaster, Integer> {

	
	Response getGenderMasterList(GenderMasterDto genderMasterDto) throws ApplicationException;

	Response getGenderByName(GenderMasterDto genderMasterDto) throws ApplicationException;

	Response addGenderMaster(GenderMasterDto genderMasterDto)  throws ApplicationException;

	Response getGenderByID(GenderMasterDto genderMasterDto) throws ApplicationException;

	Response getGenderByNameNotId(GenderMasterDto genderMasterDto) throws ApplicationException;

	Response updateGenderMaster(GenderMasterDto genderMasterDto) throws ApplicationException;

	Response updateGenderStatus(GenderMasterDto genderMasterDto) throws ApplicationException;

	Response getActiveGenderList() throws ApplicationException;
	
	Response getCount(GenderMasterDto genderMasterDto)throws ApplicationException;

}
