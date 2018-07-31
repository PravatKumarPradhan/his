package com.param.lis.global.service;

import in.param.exception.ApplicationException;

import java.util.Date;

import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.Response;
import com.param.lis.global.dao.IContainerMasterDao;
import com.param.lis.global.dto.ContainerMasterDto;
import com.param.lis.global.dto.GlobalCommonDto;

@Service
@SuppressWarnings(
{ "rawtypes", "unchecked" })
public class ContainerMasterServiceImpl implements IContainerMasterService, ICommonConstants, IError
{

	@Autowired
	IContainerMasterDao iContainerMasterDao;

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public Response addContainerMaster(ContainerMasterDto containerMasterDto) throws ApplicationException
	{
		try
		{
			GlobalCommonDto containerMstDto = (GlobalCommonDto) sessionFactory.getCurrentSession()
					.getNamedQuery("GET_CONTAINER_BY_CODE")
					.setString("code", containerMasterDto.getCode().trim().toLowerCase())
					.setInteger("orgId", containerMasterDto.getOrgId())
					.setResultTransformer(Transformers.aliasToBean(GlobalCommonDto.class)).uniqueResult();
			if (containerMstDto == null)
			{
				containerMasterDto.setCreatedDate(new Date().getTime());
				Response response = iContainerMasterDao.addContainerMaster(containerMasterDto);
				return response;
			} else
			{
				return new Response(ERROR, ERR_500, CONTAINER_CODE_EXISIS, null, null);
			}

		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response getContainerMasterById(Integer orgId, Integer containerId) throws ApplicationException
	{
		try
		{
			return iContainerMasterDao.getContainerMasterById(orgId, containerId);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response updateContainerMaster(ContainerMasterDto containerMasterDto) throws ApplicationException
	{
		try
		{
			GlobalCommonDto containerMat = (GlobalCommonDto) sessionFactory.getCurrentSession()
					.getNamedQuery("UPDATE_GET_CONTAINER_BY_CODE")
					.setString("code", containerMasterDto.getCode().trim().toLowerCase())
					.setInteger("orgId", containerMasterDto.getOrgId())
					.setInteger("containerId", containerMasterDto.getContainerId())
					.setResultTransformer(Transformers.aliasToBean(GlobalCommonDto.class)).uniqueResult();
			if (containerMat == null)
			{
				containerMasterDto.setUpdatedDate(new Date().getTime());
				return iContainerMasterDao.updateContainerMaster(containerMasterDto);
			} else
			{
				return new Response(ERROR, ERR_500, CONTAINER_CODE_EXISIS, null, null);
			}

		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		
	}

	@Override
	@Transactional
	public Response  ActivateInactivateContainerMaster(Integer orgId, Integer sampleId, Character containerStatus)
			throws ApplicationException
	{
		try
		{
			return iContainerMasterDao.ActivateInactivateContainerMaster(orgId, sampleId, containerStatus);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response listContainerMaster(Integer orgId, Integer offset, Integer recordPerPage) throws ApplicationException
	{
		try
		{
			return iContainerMasterDao.listContainerMaster(orgId, offset, recordPerPage);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response getToTalContainerMasterRecord(Integer orgId) throws ApplicationException
	{
		try
		{
			return iContainerMasterDao.getToTalContainerMasterRecord(orgId);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

}
