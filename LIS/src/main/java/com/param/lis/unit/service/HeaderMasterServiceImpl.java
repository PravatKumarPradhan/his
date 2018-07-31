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
import com.param.lis.global.dto.GlobalCommonDto;
import com.param.lis.unit.dao.IHeaderMasterDao;
import com.param.lis.unit.dto.HeaderMasterDto;

import in.param.exception.ApplicationException;

@Service
@SuppressWarnings(
{ "rawtypes", "unchecked" })
public class HeaderMasterServiceImpl implements IHeaderMasterService, ICommonConstants, IError
{

	@Autowired
	IHeaderMasterDao iHeaderMasterDao;

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public Response addHeaderMaster(HeaderMasterDto departmentMasterDto) throws ApplicationException
	{
		try
		{
			GlobalCommonDto departmentMat = (GlobalCommonDto) sessionFactory.getCurrentSession()
					.getNamedQuery("GET_HEADER_BY_CODE")
					.setString("code", departmentMasterDto.getCode().trim().toLowerCase())
					.setInteger("orgId", departmentMasterDto.getOrgId())
					.setInteger("unitId", departmentMasterDto.getUnitId())
					.setResultTransformer(Transformers.aliasToBean(GlobalCommonDto.class)).uniqueResult();
			if (departmentMat == null)
			{
				departmentMasterDto.setCreatedDate(new Date().getTime());
				Response response = iHeaderMasterDao.addHeaderMaster(departmentMasterDto);
				return response;
			} else
			{
				return new Response(ERROR, ERR_500, HEADER_CODE_EXISIS, null, null);
			}

		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response getHeaderMasterById(Integer orgId,Integer unitId, Integer headerId) throws ApplicationException
	{
		try
		{
			return iHeaderMasterDao.getHeaderMasterById(orgId,unitId,headerId);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response updateHeaderMaster(HeaderMasterDto headerMasterDto) throws ApplicationException
	{
		
		try
		{
			GlobalCommonDto departmentMat = (GlobalCommonDto) sessionFactory.getCurrentSession()
					.getNamedQuery("UPDATE_GET_HEADER_BY_CODE")
					.setString("code", headerMasterDto.getCode().trim().toLowerCase())
					.setInteger("orgId", headerMasterDto.getOrgId())
					.setInteger("unitId", headerMasterDto.getUnitId())
					.setInteger("headerId", headerMasterDto.getHeaderId())
					.setResultTransformer(Transformers.aliasToBean(GlobalCommonDto.class)).uniqueResult();
			if (departmentMat == null)
			{
				headerMasterDto.setUpdatedDate(new Date().getTime());
				return iHeaderMasterDao.updateHeaderMaster(headerMasterDto);
			} else
			{
				return new Response(ERROR, ERR_500, HEADER_CODE_EXISIS, null, null);
			}

		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	
	}

	@Override
	@Transactional
	public Response ActivateInactivateHeaderMaster(Integer orgId, Integer headerId, Character headerStatus)
			throws ApplicationException
	{
		try
		{
			return iHeaderMasterDao.ActivateInactivateHeaderMaster(orgId, headerId, headerStatus);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response listHeaderMaster(Integer orgId, Integer offset, Integer recordPerPage) throws ApplicationException
	{
		try
		{
			return iHeaderMasterDao.listHeaderMaster(orgId, offset, recordPerPage);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response getToTalHeaderMasterRecord(Integer orgId) throws ApplicationException
	{
		try
		{
			return iHeaderMasterDao.getToTalHeaderMasterRecord(orgId);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

}
