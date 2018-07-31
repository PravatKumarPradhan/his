package com.param.lis.unit.service;

import in.param.exception.ApplicationException;

import java.util.Date;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.Response;
import com.param.lis.transaction.dao.BiochemValidationWorklistDaoImpl;
import com.param.lis.unit.dao.IParameterMasterDao;
import com.param.lis.unit.dto.ParameterMasterDto;


@Service
@SuppressWarnings(
{ "rawtypes", "unchecked" })
public class ParameterMasterServiceImpl implements IParameterMasterService, ICommonConstants, IError
{

	@Autowired
	IParameterMasterDao iParameterMasterDao;

	@Autowired
	private SessionFactory sessionFactory;
	
	final static Logger logger = Logger.getLogger(ParameterMasterServiceImpl.class);

	@Override
	@Transactional
	public Response addParameter(ParameterMasterDto parameterMasterDto) throws ApplicationException
	{
		try
		{
			ParameterMasterDto parameterMat = (ParameterMasterDto) sessionFactory.getCurrentSession()
					.getNamedQuery("GET_PARAMETER_BY_CODE")
				    .setString("parameterCode", parameterMasterDto.getParameterCode().trim().toLowerCase())
					.setInteger("orgId", parameterMasterDto.getOrgId())
					.setInteger("orgUnitId", parameterMasterDto.getOrgUnitId())
					.setResultTransformer(Transformers.aliasToBean(ParameterMasterDto.class)).uniqueResult();
			if (parameterMat == null)
			{
				parameterMasterDto.setCreatedDate(new Date().getTime());
				return iParameterMasterDao.addParameter(parameterMasterDto);
				
			} else
			{
				return new Response(ERROR, ERR_500,PARAMATER_CODE_EXISIS, null, null);
			}

		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response getParameterById(Integer orgId, Integer orgUnitId,Integer parameterId) throws ApplicationException
	{
		try
		{
			return iParameterMasterDao.getParameterById(orgId,orgUnitId,parameterId);
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response updateParameter(ParameterMasterDto parameterMasterDto) throws ApplicationException
	{
		
		try
		{
			ParameterMasterDto parameterMat = (ParameterMasterDto) sessionFactory.getCurrentSession()
					.getNamedQuery("UPDATE_GET_PARAMETER_BY_CODE")
					.setString("parameterCode", parameterMasterDto.getParameterCode().trim().toLowerCase())
					.setInteger("orgId", parameterMasterDto.getOrgId())
					.setInteger("orgUnitId", parameterMasterDto.getOrgUnitId())
					.setInteger("parameterId", parameterMasterDto.getParameterId())
					.setResultTransformer(Transformers.aliasToBean(ParameterMasterDto.class)).uniqueResult();
			if (parameterMat == null)
			{
				parameterMasterDto.setUpdatedDate(new Date().getTime());
				return iParameterMasterDao.updateParameter(parameterMasterDto);
			} else
			{
				return new Response(ERROR, ERR_500,PARAMATER_CODE_EXISIS, null, null);
			}

		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	
	}

	@Override
	@Transactional
	public Response activeInactiveParameter(Integer orgId, Integer headerId, Character headerStatus)
			throws ApplicationException
	{
		try
		{
			return iParameterMasterDao.activeInactiveParameter(orgId, headerId, headerStatus);
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response listParameterMaster(Integer orgId, Integer orgUnitId, Integer offset, Integer recordPerPage) throws ApplicationException
	{
		try
		{
			return iParameterMasterDao.listParameterMaster(orgId,  orgUnitId, offset, recordPerPage);
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response getTotalParameterMasterRecord(Integer orgId,Integer orgUnitId) throws ApplicationException
	{
		try
		{
			return iParameterMasterDao.getTotalParameterMasterRecord(orgId,orgUnitId);
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response getHelpValueByParameter(Integer orgId, Integer orgUnitId, Integer parameterId)
			throws ApplicationException {
		try
		{
			return iParameterMasterDao.getHelpValueByParameter(orgId, orgUnitId, parameterId);
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

  @Override
  @Transactional
  public Response getRefReangeByParameter(Integer parameterId, Integer refrangeTypeId,
      Integer orgId, Integer orgUnitId) throws ApplicationException {
    try
    {
        return iParameterMasterDao.getRefReangeByParameter(parameterId, refrangeTypeId, orgId, orgUnitId);
    } catch (Exception e)
    {
        logger.error("Exection", e);
        return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

}