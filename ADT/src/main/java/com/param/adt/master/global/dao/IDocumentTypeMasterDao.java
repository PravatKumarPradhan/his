package com.param.adt.master.global.dao;

import com.param.adt.common.Response;
import com.param.adt.master.global.dto.DocumentTypeMasterDto;
import com.param.adt.master.global.model.DocumentTypeMaster;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IDocumentTypeMasterDao extends IGenericDao<DocumentTypeMaster, Integer> {

	
	Response getDocumentTypeByName(DocumentTypeMasterDto documentTypeMasterDto)throws ApplicationException;

	Response addDocumentTypeTypeMaster(DocumentTypeMasterDto documentTypeMasterDto) throws ApplicationException;

	Response getDocumentTypeMasterList(DocumentTypeMasterDto documentTypeMasterDto) throws ApplicationException;

	Response getDocumentTypeById(DocumentTypeMasterDto documentTypeMasterDto) throws ApplicationException;

	Response getActiveDocumentTypeList()throws ApplicationException;

	Response updateHolidayStatus(DocumentTypeMasterDto documentTypeMasterDto) throws ApplicationException;

	Response getDocumentTypeByNameNotId(DocumentTypeMasterDto documentTypeMasterDto)throws ApplicationException;

	Response updateDocumentTypeMaster(DocumentTypeMasterDto documentTypeMasterDto) throws ApplicationException;

	Response getCount(DocumentTypeMasterDto documentTypeMasterDto) throws ApplicationException;

}
