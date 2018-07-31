package com.param.adt.master.global.dao;

import com.param.adt.common.Response;
import com.param.adt.master.global.dto.EncounterTypeMasterDto;
import com.param.adt.master.global.model.EncounterTypeMaster;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IEncounterTypeMasterDao extends IGenericDao<EncounterTypeMaster, Integer>{

	
	Response getEncounterTypeByName(EncounterTypeMasterDto encounterTypeMasterDto) throws ApplicationException;

	Response addEncounterTypeMaster(EncounterTypeMasterDto encounterTypeMasterDto)throws ApplicationException;

	Response getEncounterTypeByNameNotId(EncounterTypeMasterDto encounterTypeMasterDto) throws ApplicationException;

	Response updateEncounterTypeMaster(EncounterTypeMasterDto encounterTypeMasterDto)throws ApplicationException;

	Response getEncounterTypeById(EncounterTypeMasterDto encounterTypeMasterDto) throws ApplicationException;

	Response updateEncounterTypeStatus(EncounterTypeMasterDto encounterTypeMasterDto) throws ApplicationException;

	Response getActiveEncounterTypeList() throws ApplicationException;

	Response getEncounterTypeMasterList(EncounterTypeMasterDto encounterTypeMasterDto) throws ApplicationException;

	Response getCount(EncounterTypeMasterDto encounterTypeMasterDto) throws ApplicationException;

}
