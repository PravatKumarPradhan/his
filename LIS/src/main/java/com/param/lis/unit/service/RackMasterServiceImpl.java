package com.param.lis.unit.service;

import java.util.Date;

import org.springframework.transaction.annotation.Transactional;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.Response;
import com.param.lis.unit.dao.IRackMasterDao;
import com.param.lis.unit.dto.RackMasterDto;

import in.param.exception.ApplicationException;

@Service
@SuppressWarnings(
{ "rawtypes", "unchecked" })
public class RackMasterServiceImpl implements IRackMasterService, ICommonConstants, IError
{

	@Autowired
	IRackMasterDao iRackMasterDao;

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unused")
	@Override
	@Transactional
	public Response addRackMaster(RackMasterDto rackMasterDto) throws ApplicationException
	{
		try
		{
			RackMasterDto  rackMasterMat = (RackMasterDto) sessionFactory.getCurrentSession()
					.getNamedQuery("GET_RACK_MASTER_BY_RACK_CODE")
					.setString("rackCode", rackMasterDto.getRackCode().trim().toLowerCase())
					.setInteger("orgId", rackMasterDto.getOrgId())
					.setInteger("orgUnitId", rackMasterDto.getOrgUnitId())
					.setResultTransformer(Transformers.aliasToBean(RackMasterDto.class)).uniqueResult();
			if (rackMasterMat == null)
			{
				rackMasterDto.setCreatedDate(new Date().getTime());
				Response response = iRackMasterDao.addRackMaster(rackMasterDto);
				return response;
			} else
			{
				return new Response(ERROR, ERR_500, RACK_CODE_EXISIS, null, null);
			}

		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response getRackMasterById(Integer orgId,Integer unitId, Integer departmentId) throws ApplicationException
	{
		try
		{
			return iRackMasterDao.getRackMasterById(orgId,unitId,departmentId);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response updateRackMaster(RackMasterDto rackMasterDto) throws ApplicationException
	{
		
		try
		{
			/*RackMasterDto rackMat = (RackMasterDto) sessionFactory.getCurrentSession();
					.getNamedQuery("UPDATE_GET_RACK_BY_CODE")
					.setString("rackCode", rackMasterDto.getRackCode().trim().toLowerCase())
					.setInteger("orgId", rackMasterDto.getOrgId())
					.setInteger("orgUnitId", rackMasterDto.getOrgUnitId())
					.setInteger("rackId", rackMasterDto.getRackId())
					.setResultTransformer(Transformers.aliasToBean(RackMasterDto.class)).uniqueResult();*/
			return iRackMasterDao.updateRackMaster(rackMasterDto);
		/*	if (rackMat == null)
			{
				rackMasterDto.setUpdatedDate(new Date().getTime());
				return iRackMasterDao.updateRackMaster(rackMasterDto);
			} else
			{
				return new Response(ERROR, ERR_500, RACK_CODE_EXISIS, null, null);
			}*/

		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	
	}

	@Override
	@Transactional
	public Response ActivateInactivateRackMaster(Integer orgId, Integer rackId, Character rackStatus)
			throws ApplicationException
	{
		try
		{
			return iRackMasterDao.ActivateInactivateRackMaster(orgId, rackId,  rackStatus);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response listRackMaster(Integer orgId, Integer orgUnitId,Integer offset, Integer recordPerPage) throws ApplicationException
	{
		try
		{
			return iRackMasterDao.listRackMaster(orgId,orgUnitId, offset, recordPerPage);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response getToTalRackMasterRecord(Integer orgId,Integer orgUnitId) throws ApplicationException
	{
		try
		{
			return iRackMasterDao.getToTalRackMasterRecord(orgId,orgUnitId);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

}
