package com.param.adt.master.global.service;

import javax.transaction.Transactional;

import com.param.adt.common.Response;
import com.param.adt.master.global.dto.DocumentTypeMasterDto;

import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IDocumentTypeMasterService {

	@Transactional
	Response addDocumentTypeMaster(DocumentTypeMasterDto documentTypeMasterDto) throws ApplicationException;

	@Transactional
	Response getDocumentTypeMasterList(DocumentTypeMasterDto documentTypeMasterDto) throws ApplicationException;

	@Transactional
	Response updateDocumentTypeMaster(DocumentTypeMasterDto documentTypeMasterDto) throws ApplicationException;

	@Transactional
	Response getDocumentTypeById(DocumentTypeMasterDto documentTypeMasterDto)throws ApplicationException;

	@Transactional
	Response updateStatusForDocumentType(DocumentTypeMasterDto documentTypeMasterDto) throws ApplicationException;

	@Transactional
	Response getActiveDocumentTypeList()throws ApplicationException;

	@Transactional
	Response getDocumentTypeCount(DocumentTypeMasterDto documentTypeMasterDto) throws ApplicationException;

}
