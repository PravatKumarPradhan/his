package com.param.global.org.service;

import javax.transaction.Transactional;

import com.param.global.common.Response;
import com.param.global.org.dto.LiceneceTypeMasterDto;

import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
@Transactional
public interface ILiceneceTypeMasterService {

	Response saveLiceneceTypeMaster(LiceneceTypeMasterDto liceneceTypeMasterDto) throws ApplicationException;

	Response getLiceneceTypeMasterById(int licenceId,int orgId) throws ApplicationException;

	Response getLiceneceTypeMasterList(int orgId) throws ApplicationException;

	Response updateLiceneceTypeMaster(LiceneceTypeMasterDto liceneceTypeMasterDto) throws ApplicationException;

	Response updateLiceneceTypeMasterStatus(LiceneceTypeMasterDto liceneceTypeMasterDto) throws ApplicationException;
}
