package com.param.lis.global.dao;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;
import com.param.entity.lis.global.IncubationTestMaster;
import com.param.lis.global.common.Response;
import com.param.lis.global.dto.IncubationTestMasterDto;
import com.param.lis.global.dto.ReportTypeMasterDto;

@SuppressWarnings("rawtypes")
public interface IIncubationTestMasterDao extends IGenericDao<IncubationTestMaster, Integer>{
	
	public Response addIncubationTest(IncubationTestMasterDto IncubationTestDto)throws ApplicationException;
	public Response getIncubationTestById(Integer orgId, Integer bacteriaId) throws ApplicationException;
	public Response updateIncubationTest(IncubationTestMasterDto IncubationTestDto) throws ApplicationException;
	public Response updateCheckIncubationTestCodeAvaiable(IncubationTestMasterDto incubationTestMasterDto) throws ApplicationException;
	
	public Response ActivateInactivateIncubationTest(Integer orgId, Integer bacteriaId, Character bacteriaStatus) throws ApplicationException;
	public Response listIncubationTest(Integer orgId, Integer offset, Integer recordPerPage) throws ApplicationException;
	public Response checkIncubationTest(IncubationTestMasterDto IncubationTestDto) throws ApplicationException;
	public Response getToTalIncubationTest(Integer orgId) throws ApplicationException;
}
