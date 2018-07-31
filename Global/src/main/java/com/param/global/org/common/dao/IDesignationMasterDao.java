package com.param.global.org.common.dao;

import com.param.global.common.Response;
import com.param.global.org.common.dto.DesignationMasterDto;

import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface IDesignationMasterDao {

	Response saveDesignationMaster(DesignationMasterDto designationMasterDto) throws ApplicationException;

	Response getDesignationMasterById(int designationId, int orgId) throws ApplicationException;

	Response getDesignationMasterList(DesignationMasterDto designationMasterDto) throws ApplicationException;

	Response updateDesignationMaster(DesignationMasterDto designationMasterDto) throws ApplicationException;

	Response updateDesignationMasterStatus(DesignationMasterDto designationMasterDto) throws ApplicationException;

	Response getDesignationMasterCount(DesignationMasterDto designationMasterDto) throws ApplicationException;

	Response getDesignationMasterByName(DesignationMasterDto designationMasterDto) throws ApplicationException;

	Response getDesignationMasterByNameNotById(DesignationMasterDto designationMasterDto) throws ApplicationException;
}
