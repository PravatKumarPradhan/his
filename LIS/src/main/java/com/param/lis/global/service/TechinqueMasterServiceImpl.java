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
import com.param.lis.global.dao.ITechinqueMasterDao;
import com.param.lis.global.dto.GlobalCommonDto;
import com.param.lis.global.dto.SampleMasterDto;
import com.param.lis.global.dto.TechniqueMasterDto;

import in.param.exception.ApplicationException;

@Service
@SuppressWarnings(
{ "rawtypes", "unchecked" })
public class TechinqueMasterServiceImpl implements ITechinqueMasterService, ICommonConstants, IError
{

	@Autowired
	ITechinqueMasterDao iTechinqueMasterDao;

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unused")
	@Override
	@Transactional
	public Response addTechinqueMaster(TechniqueMasterDto techniqueMasterDto) throws ApplicationException
	{
		try
		{
			GlobalCommonDto techniqueMat = (GlobalCommonDto) sessionFactory.getCurrentSession()
					.getNamedQuery("GET_TECHNIQUE_BY_CODE")
					.setString("code", techniqueMasterDto.getCode().trim().toLowerCase())
					.setInteger("orgId", techniqueMasterDto.getOrgId())
					.setResultTransformer(Transformers.aliasToBean(GlobalCommonDto.class)).uniqueResult();
			if (techniqueMat == null)
			{
				techniqueMasterDto.setCreatedDate(new Date().getTime());
				Response response = iTechinqueMasterDao.addTechinqueMaster(techniqueMasterDto);
				return response;
			} else
			{
				return new Response(ERROR, ERR_500, TECHINQUE_CODE_EXISIS, null, null);
			}

		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response getTechinqueMasterById(Integer orgId, Integer techniqueId) throws ApplicationException
	{
		try
		{
			return iTechinqueMasterDao.getTechinqueMasterById(orgId, techniqueId);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response updateTechinqueMaster(TechniqueMasterDto techniqueMasterDto) throws ApplicationException
	{
		try
		{
			GlobalCommonDto departmentMat = (GlobalCommonDto) sessionFactory.getCurrentSession()
					.getNamedQuery("UPDATE_GET_TECHNIQUE_BY_CODE")
					.setString("code", techniqueMasterDto.getCode().trim().toLowerCase())
					.setInteger("orgId", techniqueMasterDto.getOrgId())
					.setInteger("techniqueId", techniqueMasterDto.getTechniqueId())
					.setResultTransformer(Transformers.aliasToBean(GlobalCommonDto.class)).uniqueResult();
			if (departmentMat == null)
			{
				techniqueMasterDto.setUpdatedDate(new Date().getTime());
				return iTechinqueMasterDao.updateTechinqueMaster(techniqueMasterDto);
			} else
			{
				return new Response(ERROR, ERR_500, TECHINQUE_CODE_EXISIS, null, null);
			}

		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		
	}

	@Override
	@Transactional
	public Response ActivateInactivateTechinqueMaster(Integer orgId, Integer sampleId, Character sampleStatus)
			throws ApplicationException
	{
		try
		{
			return iTechinqueMasterDao.ActivateInactivateTechinqueMaster(orgId, sampleId, sampleStatus);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response listTechinqueMaster(Integer orgId, Integer offset, Integer recordPerPage) throws ApplicationException
	{
		try
		{
			return iTechinqueMasterDao.listTechinqueMaster(orgId, offset, recordPerPage);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response getToTalTechinqueMasterRecord(Integer orgId) throws ApplicationException
	{
		try
		{
			return iTechinqueMasterDao.getToTalTechinqueMasterRecord(orgId);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

}
