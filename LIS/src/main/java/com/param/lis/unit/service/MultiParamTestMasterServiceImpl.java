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
import com.param.lis.unit.dao.IMultiParamTestMasterDao;
import com.param.lis.unit.dto.TestMasterDto;

import in.param.exception.ApplicationException;

@Service
@SuppressWarnings(
{ "rawtypes", "unchecked" })
public class MultiParamTestMasterServiceImpl
		implements IMultiParamTestMasterService, ICommonConstants, IError, ITransactionConstants
{

	@Autowired
	IMultiParamTestMasterDao iMultiParamTestMasterDao;

	@Autowired
	private SessionFactory sessionFactory;

	final static Logger logger = Logger.getLogger(MultiParamTestMasterServiceImpl.class);

	@Override
	@Transactional
	public Response addMultiParameterTest(TestMasterDto testMasterDto) throws ApplicationException
	{
		try
		{
			TestMasterDto testMstDto = (TestMasterDto) sessionFactory.getCurrentSession()
					.getNamedQuery("GET_TEST_BY_TEST_CODE")
					.setString("testCode", testMasterDto.getTestCode().trim().toLowerCase())
					.setInteger("orgId", testMasterDto.getOrgId())
					.setInteger("orgUnitId", testMasterDto.getOrgUnitId())
					.setInteger("testType", MULTY_PARAM_TEST)
					.setResultTransformer(Transformers.aliasToBean(TestMasterDto.class)).uniqueResult();
			if (testMstDto == null)
			{
				testMasterDto.setCreatedDate(new Date().getTime());
				return iMultiParamTestMasterDao.addMultiParameterTest(testMasterDto);
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
	public Response getMultyParameterTest(Integer orgId, Integer orgUnitId, Integer testId) throws ApplicationException
	{
		try
		{
			return iMultiParamTestMasterDao.getMultyParameterTest(orgId, orgUnitId, testId);
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response updateMultiParameterTest(TestMasterDto testMasterDto) throws ApplicationException
	{
		try
		{
			TestMasterDto testMstDto = (TestMasterDto) sessionFactory.getCurrentSession()
					.getNamedQuery("GET_TEST_BY_TEST_CODE_UPDATE")
					.setString("testCode", testMasterDto.getTestCode().trim().toLowerCase())
					.setInteger("orgId", testMasterDto.getOrgId())
					.setInteger("orgUnitId", testMasterDto.getOrgUnitId())
					.setInteger("testType", MULTY_PARAM_TEST)
					.setInteger("testId", testMasterDto.getTestId())
					.setResultTransformer(Transformers.aliasToBean(TestMasterDto.class)).uniqueResult();
			if (testMstDto == null)
			{
				return iMultiParamTestMasterDao.updateMultiParameterTest(testMasterDto);
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
	public Response activeInactiveMultiParamTest(Integer orgId, Integer orgUnitId, Integer testId,
			Character testStatus) throws ApplicationException
	{
		try
		{
			return iMultiParamTestMasterDao.activeInactiveMultiParamTest(orgId, orgUnitId, testId,
					testStatus);
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response listMultiParamTestMaster(Integer orgId,Character isHistoCyto, Integer orgUnitId, Integer offset, Integer recordPerPage)
			throws ApplicationException
	{
		try
		{
			return iMultiParamTestMasterDao.listMultiParamTestMaster(orgId, isHistoCyto, orgUnitId, offset, recordPerPage);
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response getTotalMultiTestRecord(Integer orgId, Character isHistoCyto, Integer orgUnitId) throws ApplicationException
	{
		try
		{
			return iMultiParamTestMasterDao.getTotalMultiTestRecord(orgId,isHistoCyto ,orgUnitId);
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

}
