package com.param.lis.unit.dao;

import com.param.entity.lis.unit.TestParamMppr;
import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;

public interface ITestParamMpprDao  extends IGenericDao<TestParamMppr, Integer>
{
	public TestParamMppr addTestParamMppr(TestParamMppr testParamMppr) throws ApplicationException;
	public TestParamMppr updateTestParamMppr(TestParamMppr testParamMppr) throws ApplicationException;
}
