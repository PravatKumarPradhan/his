package com.param.global.service;

import javax.transaction.Transactional;

import com.param.global.common.Response;
import com.param.global.dto.PrefixMasterDto;

import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IPrefixMasterService {

	@Transactional
	Response addPrefixMaster(PrefixMasterDto prefixMasterDto) throws ApplicationException;

	@Transactional
	Response getPrefixMasterList(PrefixMasterDto prefixMasterDto) throws ApplicationException;

	@Transactional
	Response updatePrefixMaster(PrefixMasterDto prefixMasterDto) throws ApplicationException;

	@Transactional
	Response getPrefixById(PrefixMasterDto prefixMasterDto) throws ApplicationException;

	@Transactional
	Response updateStatusForPrefix(PrefixMasterDto prefixMasterDto) throws ApplicationException;

	@Transactional
	Response getActivePrefixList() throws ApplicationException;

	@Transactional
	Response getPrefixCount(PrefixMasterDto prefixMasterDto) throws ApplicationException;

}
