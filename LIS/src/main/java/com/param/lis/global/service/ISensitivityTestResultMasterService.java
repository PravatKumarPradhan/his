package com.param.lis.global.service;

import in.param.exception.ApplicationException;

import com.param.lis.global.common.Response;
import com.param.lis.global.dto.SensitivityMasterDto;

@SuppressWarnings("rawtypes")
public interface ISensitivityTestResultMasterService {
	public Response addSensitivityTestResultMaster(SensitivityMasterDto sensitivityTestResultMasterDto)throws ApplicationException;
	public Response updateSensitivityTestResultMaster(SensitivityMasterDto sensitivityTestResultMasterDto) throws ApplicationException;
	public Response ActivateInactivateSensitivityTestResultMaster(Integer orgId, Integer sensitivityTestResulId, Character sensitivityTestResulStatus) throws ApplicationException;
	public Response listSensitivityTestResultMaster(Integer orgId, Integer offset, Integer recordPerPage) throws ApplicationException;
	public Response getToTalSensitivityTestResultMaster(Integer orgId) throws ApplicationException;
	public Response getSensitivityTestResultMasterById(Integer orgId, Integer sensitivityTestResulId)
			throws ApplicationException;
}
