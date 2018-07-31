package com.param.lis.unit.dao;

import com.param.entity.lis.unit.TestMaster;
import com.param.lis.global.common.Response;
import com.param.lis.unit.dto.TestMasterDto;
import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface ISingleParamTestMasterDao extends IGenericDao<TestMaster, Integer>
{

	public Response addSingleParameterTest(TestMasterDto testMasterDto) throws ApplicationException;

	public Response getSingleParameterTest(Integer orgId, Integer orgUnitId, Integer testId)
			throws ApplicationException;

	public Response updateSingleParameterTest(TestMasterDto testMasterDto) throws ApplicationException;

	public Response activeInactiveTest(Integer orgId, Integer orgUnitId, Integer testId,Integer parameterId, Character testStatus)
			throws ApplicationException;

	public Response listTestMaster(Integer orgId,Character isHistoCyto, Integer orgUnitId, Integer offset, Integer recordPerPage)
			throws ApplicationException;

	public Response getTotalTestRecord(Integer orgId,Character isHistoCyto, Integer orgUnitId) throws ApplicationException;
	
	public Response checkServiceAvaiable(TestMasterDto testMasterDto) throws ApplicationException;

}
