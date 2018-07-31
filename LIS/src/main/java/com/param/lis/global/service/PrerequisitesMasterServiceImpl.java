package com.param.lis.global.service;

import java.util.Date;

import org.springframework.transaction.annotation.Transactional;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.Response;
import com.param.lis.global.dao.IPrerequisitesDao;
import com.param.lis.global.dto.GlobalCommonDto;
import com.param.lis.global.dto.PrerequisitesMasterDto;

import in.param.exception.ApplicationException;

@Service
@SuppressWarnings(
{ "rawtypes", "unchecked" })
public class PrerequisitesMasterServiceImpl implements IPrerequisitesService, ICommonConstants, IError
{

	@Autowired
	IPrerequisitesDao iPrerequisitesDao;

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public Response addPrerequisitesMaster(PrerequisitesMasterDto prerequisitesMasterDto) throws ApplicationException
	{
		try
		{
			GlobalCommonDto prerequisitesMst = (GlobalCommonDto) sessionFactory.getCurrentSession()
					.getNamedQuery("GET_PREREQUISITES_BY_CODE")
					.setString("code", prerequisitesMasterDto.getCode().trim().toLowerCase())
					.setInteger("orgId", prerequisitesMasterDto.getOrgId())
					.setResultTransformer(Transformers.aliasToBean(GlobalCommonDto.class)).uniqueResult();
			if (prerequisitesMst == null)
			{
				prerequisitesMasterDto.setCreatedDate(new Date().getTime());
				Response response = iPrerequisitesDao.addPrerequisitesMaster(prerequisitesMasterDto);
				return response;
			} else
			{
				return new Response(ERROR, ERR_500, PREREQUISITES_CODE_EXISIS, null, null);
			}

		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response getPrerequisitesMasterById(Integer orgId, Integer prerequisitesId) throws ApplicationException
	{
		try
		{
			return iPrerequisitesDao.getPrerequisitesMasterById(orgId, prerequisitesId);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response updatePrerequisitesMaster(PrerequisitesMasterDto prerequisitesMasterDto) throws ApplicationException
	{
		try
		{
			GlobalCommonDto prerequisitesMat = (GlobalCommonDto) sessionFactory.getCurrentSession()
					.getNamedQuery("UPDATE_GET_PREREQUISITES_BY_CODE")
					.setString("code", prerequisitesMasterDto.getCode().trim().toLowerCase())
					.setInteger("orgId", prerequisitesMasterDto.getOrgId())
					.setInteger("prerequisitesId", prerequisitesMasterDto.getPrerequisitesId())
					.setResultTransformer(Transformers.aliasToBean(GlobalCommonDto.class)).uniqueResult();
			if (prerequisitesMat == null)
			{
				prerequisitesMasterDto.setUpdatedDate(new Date().getTime());
				return iPrerequisitesDao.updatePrerequisitesMaster(prerequisitesMasterDto);
			} else
			{
				return new Response(ERROR, ERR_500, SAMPLE_CODE_EXISIS, null, null);
			}

		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		/*try
		{
			prerequisitesMasterDto.setUpdatedDate(new Date().getTime());
			return iPrerequisitesDao.updatePrerequisitesMaster(prerequisitesMasterDto);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}*/
	}

	@Override
	@Transactional
	public Response ActivateInactivatePrerequisitesMaster(Integer orgId, Integer prerequisitesId, Character prerequisitesStatus)
			throws ApplicationException
	{
		try
		{
			return iPrerequisitesDao.ActivateInactivatePrerequisitesMaster(orgId, prerequisitesId, prerequisitesStatus);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response listPrerequisitesMaster(Integer orgId, Integer offset, Integer recordPerPage) throws ApplicationException
	{
		try
		{
			return iPrerequisitesDao.listPrerequisitesMaster(orgId, offset, recordPerPage);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response getToTalPrerequisitesMasterRecord(Integer orgId) throws ApplicationException
	{
		try
		{
			return iPrerequisitesDao.getToTalPrerequisitesMasterRecord(orgId);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

}
