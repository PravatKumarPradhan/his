package com.param.lis.global.service;

import in.param.exception.ApplicationException;

import com.param.lis.global.common.Response;
import com.param.lis.global.dto.IncubationTestMasterDto;
@SuppressWarnings("rawtypes")
public interface IIncubationTestMasterService {
	public Response addIncubationTest(IncubationTestMasterDto IncubationTestDto)throws ApplicationException;
	public Response getIncubationTestById(Integer orgId, Integer incubationTestId) throws ApplicationException;
	public Response updateIncubationTest(IncubationTestMasterDto IncubationTestDto) throws ApplicationException;
	public Response ActivateInactivateIncubationTest(Integer orgId, Integer incubationTestId, Character incubationTestStatus) throws ApplicationException;
	public Response listIncubationTest(Integer orgId, Integer offset, Integer recordPerPage) throws ApplicationException;
	public Response getToTalIncubationTest(Integer orgId) throws ApplicationException;
}
