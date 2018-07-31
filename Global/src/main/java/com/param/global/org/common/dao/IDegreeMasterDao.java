package com.param.global.org.common.dao;

import com.param.global.common.Response;
import com.param.global.org.common.dto.DegreeMasterDto;

import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")

public interface IDegreeMasterDao {

	Response saveDegreeMaster(DegreeMasterDto degreeMasterDto) throws ApplicationException;

	Response getDegreeMasterById(int degreeId, int orgId) throws ApplicationException;

	Response getDegreeMasterList(DegreeMasterDto degreeMasterDto) throws ApplicationException;

	Response updateDegreeMaster(DegreeMasterDto degreeMasterDto) throws ApplicationException;

	Response updateDegreeMasterStatus(DegreeMasterDto degreeMasterDto) throws ApplicationException;

	Response getDegreeMasterCount(DegreeMasterDto degreeMasterDto) throws ApplicationException;
	
	Response getDegreeMasterByName(DegreeMasterDto degreeMasterDto) throws ApplicationException;

	Response getDegreeMasterByNameNotById(DegreeMasterDto degreeMasterDto) throws ApplicationException;
}
