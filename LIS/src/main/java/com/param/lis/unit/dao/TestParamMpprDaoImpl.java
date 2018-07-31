package com.param.lis.unit.dao;

import java.util.Date;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.param.entity.lis.unit.TestMaster;
import com.param.entity.lis.unit.TestParamMppr;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.ITransactionConstants;
import com.param.lis.global.common.Response;
import com.param.lis.unit.dto.TestMasterDto;
import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
public class TestParamMpprDaoImpl extends GenericDao<TestParamMppr, Integer>
implements ITestParamMpprDao, ICommonConstants, IError, ITransactionConstants
{

	public TestParamMpprDaoImpl() {
		super(TestParamMppr.class);
	}
	

	final static Logger logger = Logger.getLogger(TestParamMpprDaoImpl.class);

	@Override
	public TestParamMppr addTestParamMppr(TestParamMppr testParamMppr) throws ApplicationException
	{
		try
		{
				return save(testParamMppr);
		} catch (HibernateException exception)
		{
			logger.error("Exception", exception);
		} catch (Exception e)
		{
			logger.error("Exception", e);
		}
		return null;
	}

	@Override
	public TestParamMppr updateTestParamMppr(TestParamMppr testParamMppr) throws ApplicationException
	{
		try
		{
				return update(testParamMppr);
		} catch (HibernateException exception)
		{
			logger.error("Exception", exception);
		} catch (Exception e)
		{
			logger.error("Exception", e);
		}
		return null;
	}
	
}
