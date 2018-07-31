package com.param.lis.unit.service;

import java.util.Date;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.ITransactionConstants;
import com.param.lis.global.common.Response;
import com.param.lis.unit.dao.ISingleParamTestMasterDao;
import com.param.lis.unit.dto.TestMasterDto;

import in.param.exception.ApplicationException;

@Service
@SuppressWarnings(
{ "rawtypes", "unchecked" })
public class SingleParamTestMasterServiceImpl implements ISingleParamTestMasterService,ITransactionConstants, ICommonConstants, IError
{

	@Autowired
	ISingleParamTestMasterDao iTestMasterDao;

	@Autowired
	private SessionFactory sessionFactory;

	final static Logger logger = Logger.getLogger(SingleParamTestMasterServiceImpl.class);

	@Override
	@Transactional
	public Response addSingleParameterTest(TestMasterDto testMasterDto) throws ApplicationException
	{
		try
		{
			TestMasterDto testMstDto = (TestMasterDto) sessionFactory.getCurrentSession()
					.getNamedQuery("GET_TEST_BY_TEST_CODE")
					.setString("testCode", testMasterDto.getTestCode().trim().toLowerCase())
					.setInteger("testType", SINGLE_PARAM_TEST)
					.setInteger("orgId", testMasterDto.getOrgId()).setInteger("orgUnitId", testMasterDto.getOrgUnitId())
					.setResultTransformer(Transformers.aliasToBean(TestMasterDto.class)).uniqueResult();
			if (testMstDto == null)
			{
				testMasterDto.setCreatedDate(new Date().getTime());
				return iTestMasterDao.addSingleParameterTest(testMasterDto);
			} else
			{
				return new Response(ERROR, ERR_500, TEST_CODE_EXISIS, null, null);
			}

		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response getSingleParameterTest(Integer orgId, Integer orgUnitId, Integer testId) throws ApplicationException
	{
		try
		{
			return iTestMasterDao.getSingleParameterTest(orgId, orgUnitId, testId);
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response updateSingleParameterTest(TestMasterDto testMasterDto) throws ApplicationException
	{
		try
		{
			TestMasterDto testMstDto = (TestMasterDto) sessionFactory.getCurrentSession()
					.getNamedQuery("GET_TEST_BY_TEST_CODE_UPDATE")
					.setString("testCode", testMasterDto.getTestCode().trim().toLowerCase())
					.setInteger("orgId", testMasterDto.getOrgId())
					.setInteger("orgUnitId", testMasterDto.getOrgUnitId())
					.setInteger("testType", SINGLE_PARAM_TEST)
					.setInteger("testId", testMasterDto.getTestId())
					.setResultTransformer(Transformers.aliasToBean(TestMasterDto.class)).uniqueResult();
			if (testMstDto == null)
			{
				return iTestMasterDao.updateSingleParameterTest(testMasterDto);
			} else
			{
				return new Response(ERROR, ERR_500, TEST_CODE_EXISIS, null, null);
			}
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response activeInactiveTest(Integer orgId, Integer orgUnitId, Integer testId, Integer parameterId,
			Character testStatus) throws ApplicationException
	{
		try
		{
			return iTestMasterDao.activeInactiveTest(orgId, orgUnitId, testId, parameterId, testStatus);
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response listTestMaster(Integer orgId, Character isHistoCyto, Integer orgUnitId, Integer offset, Integer recordPerPage)
			throws ApplicationException
	{
		try
		{
			return iTestMasterDao.listTestMaster(orgId,isHistoCyto,orgUnitId, offset, recordPerPage);
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response getTotalTestRecord(Integer orgId, Character isHistoCyto,Integer orgUnitId) throws ApplicationException
	{
		try
		{
			return iTestMasterDao.getTotalTestRecord(orgId,isHistoCyto, orgUnitId);
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response checkServiceAvaiable(TestMasterDto testMasterDto)
			throws ApplicationException {
		try
		{
			return iTestMasterDao.checkServiceAvaiable(testMasterDto);
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

}
