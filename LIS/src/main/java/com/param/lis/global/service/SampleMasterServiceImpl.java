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
import com.param.lis.global.dao.ISampleMasterDao;
import com.param.lis.global.dto.GlobalCommonDto;
import com.param.lis.global.dto.SampleMasterDto;

import in.param.exception.ApplicationException;

@Service
@SuppressWarnings(
{ "rawtypes", "unchecked" })
public class SampleMasterServiceImpl implements ISampleMasterService, ICommonConstants, IError
{

	@Autowired
	ISampleMasterDao iSampleMasterDao;

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public Response addSampleMaster(SampleMasterDto sampleMasterDto) throws ApplicationException
	{
		try
		{
			GlobalCommonDto sampleMstDto = (GlobalCommonDto) sessionFactory.getCurrentSession()
					.getNamedQuery("GET_SAMPLE_BY_CODE")
					.setString("code", sampleMasterDto.getCode().trim().toLowerCase())
					.setInteger("orgId", sampleMasterDto.getOrgId())
					.setResultTransformer(Transformers.aliasToBean(GlobalCommonDto.class)).uniqueResult();
			if (sampleMstDto == null)
			{
				sampleMasterDto.setCreatedDate(new Date().getTime());
				Response response = iSampleMasterDao.addSampleMaster(sampleMasterDto);
				return response;
			} else
			{
				return new Response(ERROR, ERR_500, SAMPLE_CODE_EXISIS, null, null);
			}

		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response getSampleMasterById(Integer orgId, Integer sampleId) throws ApplicationException
	{
		try
		{
			return iSampleMasterDao.getSampleMasterById(orgId, sampleId);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response updateSampleMaster(SampleMasterDto sampleMasterDto) throws ApplicationException
	{
		try
		{
			GlobalCommonDto departmentMat = (GlobalCommonDto) sessionFactory.getCurrentSession()
					.getNamedQuery("UPDATE_GET_SAMPLE_BY_CODE")
					.setString("code", sampleMasterDto.getCode().trim().toLowerCase())
					.setInteger("orgId", sampleMasterDto.getOrgId())
					.setInteger("sampleId", sampleMasterDto.getSampleId())
					.setResultTransformer(Transformers.aliasToBean(GlobalCommonDto.class)).uniqueResult();
			if (departmentMat == null)
			{
				sampleMasterDto.setUpdatedDate(new Date().getTime());
				return iSampleMasterDao.updateSampleMaster(sampleMasterDto);
			} else
			{
				return new Response(ERROR, ERR_500, SAMPLE_CODE_EXISIS, null, null);
			}

		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		
	}

	@Override
	@Transactional
	public Response ActivateInactivateSampleMaster(Integer orgId, Integer sampleId, Character sampleStatus)
			throws ApplicationException
	{
		try
		{
			return iSampleMasterDao.ActivateInactivateSampleMaster(orgId, sampleId, sampleStatus);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response listSampleMaster(Integer orgId, Integer offset, Integer recordPerPage) throws ApplicationException
	{
		try
		{
			return iSampleMasterDao.listSampleMaster(orgId, offset, recordPerPage);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response getToTalSampleMasterRecord(Integer orgId) throws ApplicationException
	{
		try
		{
			return iSampleMasterDao.getToTalSampleMasterRecord(orgId);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

}
