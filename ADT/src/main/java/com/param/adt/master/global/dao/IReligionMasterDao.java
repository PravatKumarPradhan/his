package com.param.adt.master.global.dao;

import com.param.adt.common.Response;
import com.param.adt.master.global.dto.ReligionMasterDto;
import com.param.adt.master.global.model.ReligionMaster;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IReligionMasterDao extends IGenericDao<ReligionMaster, Integer>{


	Response getReligionByName(ReligionMasterDto religionMasterDto) throws ApplicationException;

	Response addReligionMaster(ReligionMasterDto religionMasterDto)throws ApplicationException;

	Response getReligionMasterList(ReligionMasterDto religionMasterDto) throws ApplicationException;

	Response getReligionById(ReligionMasterDto religionMasterDto)throws ApplicationException;

	Response getActiveReligionList() throws ApplicationException;

	Response getReligionByNameNotId(ReligionMasterDto religionMasterDto) throws ApplicationException;

	Response updateReligionMaster(ReligionMasterDto religionMasterDto)throws ApplicationException;

	Response updateReligionStatus(ReligionMasterDto religionMasterDto)throws ApplicationException;

	Response getCount(ReligionMasterDto religionMasterDto) throws ApplicationException;


}
