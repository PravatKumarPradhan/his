package com.param.global.org.common.service;

import javax.transaction.Transactional;

import com.param.global.common.Response;
import com.param.global.org.common.dto.DesignationMasterDto;

import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
@Transactional
public interface IDesignationMasterService {


	Response saveDesignationMaster(DesignationMasterDto designationMasterDto) throws ApplicationException;

	Response getDesignationMasterById(int designationId, int orgId) throws ApplicationException;

	Response getDesignationMasterList(DesignationMasterDto designationMasterDto) throws ApplicationException;

	Response updateDesignationMaster(DesignationMasterDto designationMasterDto) throws ApplicationException;

	Response updateDesignationMasterStatus(DesignationMasterDto designationMasterDto) throws ApplicationException;

	Response getDesignationMasterCount(DesignationMasterDto designationMasterDto) throws ApplicationException;

}
