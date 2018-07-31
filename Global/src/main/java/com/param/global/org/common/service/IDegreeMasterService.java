package com.param.global.org.common.service;

import javax.transaction.Transactional;

import com.param.global.common.Response;
import com.param.global.org.common.dto.DegreeMasterDto;
import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
@Transactional
public interface IDegreeMasterService {

	
	Response saveDegreeMaster(DegreeMasterDto degreeMasterDto) throws ApplicationException;

	Response getDegreeMasterById(int degreeId, int orgId) throws ApplicationException;

	Response getDegreeMasterList(DegreeMasterDto degreeMasterDto) throws ApplicationException;

	Response updateDegreeMaster(DegreeMasterDto degreeMasterDto) throws ApplicationException;

	Response updateDegreeMasterStatus(DegreeMasterDto degreeMasterDto) throws ApplicationException;

	Response getDegreeMasterCount(DegreeMasterDto degreeMasterDto) throws ApplicationException; 

}
