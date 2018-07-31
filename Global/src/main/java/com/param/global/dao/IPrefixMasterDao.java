package com.param.global.dao;


import com.param.global.common.Response;
import com.param.global.dto.PrefixMasterDto;
import com.param.global.model.PrefixMaster;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IPrefixMasterDao extends IGenericDao<PrefixMaster, Integer>{

	
	Response getPrefixByName(PrefixMasterDto prefixMasterDto) throws ApplicationException;

	Response addPrefixTypeMaster(PrefixMasterDto prefixMasterDto)throws ApplicationException;

	Response getPrefixByNameNotId(PrefixMasterDto prefixMasterDto) throws ApplicationException;

	Response updatePrefixMaster(PrefixMasterDto prefixMasterDto)throws ApplicationException;

	Response getPrefixByID(PrefixMasterDto prefixMasterDto) throws ApplicationException;

	Response updatePrefixStatus(PrefixMasterDto prefixMasterDto)throws ApplicationException;

	Response getActivePrefixList() throws ApplicationException;

	Response getPrefixMasterList(PrefixMasterDto prefixMasterDto) throws ApplicationException;

	Response getCount(PrefixMasterDto prefixMasterDto) throws ApplicationException;

}
