package com.param.adt.master.global.dao;

import com.param.adt.common.Response;
import com.param.adt.master.global.dto.IdentificationMasterDto;
import com.param.global.model.IdentificationMaster;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IIdentificationMasterDao extends IGenericDao<IdentificationMaster, Integer>{

	
	Response getIdentificationByName(IdentificationMasterDto identificationMasterDto)throws ApplicationException;

	Response addIdentificationMaster(IdentificationMasterDto identificationMasterDto)throws ApplicationException;

	Response getIdentificationMasterList(IdentificationMasterDto identificationMasterDto) throws ApplicationException;

	Response getIdentificationByNameNotId(IdentificationMasterDto identificationMasterDto)throws ApplicationException;

	Response updateIdentificationMaster(IdentificationMasterDto identificationMasterDto) throws ApplicationException;

	Response getIdentificationById(IdentificationMasterDto identificationMasterDto)throws ApplicationException;

	Response updateIdentificationStatus(IdentificationMasterDto identificationMasterDto)throws ApplicationException;

	Response getActiveIdentificationList()throws ApplicationException;

	Response getCount(IdentificationMasterDto identificationMasterDto);

}
