package com.param.global.org.dao;

import com.param.global.common.Response;
import com.param.global.org.dto.LiceneceTypeMasterDto;

import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface ILiceneceTypeMasterDao {

	Response saveLiceneceTypeMaster(LiceneceTypeMasterDto liceneceTypeMasterDto) throws ApplicationException;

	Response getLiceneceTypeMasterByType(LiceneceTypeMasterDto liceneceTypeMasterDto) throws ApplicationException;

	Response getLiceneceTypeMasterById(int licenceId,int orgId) throws ApplicationException;

	Response getLiceneceTypeMasterList(int orgId) throws ApplicationException;

	Response updateLiceneceTypeMaster(LiceneceTypeMasterDto liceneceTypeMasterDto) throws ApplicationException;

	Response updateLiceneceTypeMasterStatus(LiceneceTypeMasterDto liceneceTypeMasterDto) throws ApplicationException;
	
	 Response getLicenceByNameNotId(LiceneceTypeMasterDto liceneceTypeMasterDto) throws ApplicationException;
}
