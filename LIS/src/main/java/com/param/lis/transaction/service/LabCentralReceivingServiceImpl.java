package com.param.lis.transaction.service;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.global.dto.OrderDetailsMasterDto;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.Response;
import com.param.lis.global.common.SearchDto;
import com.param.lis.transaction.dao.ILabCentralReceivingDao;
import com.param.lis.transaction.dto.LabSampleDetailsMasterDto;

import in.param.exception.ApplicationException;

@Service
@SuppressWarnings(
{ "rawtypes", "unchecked" })
public class LabCentralReceivingServiceImpl implements ILabCentralReceivingService, ICommonConstants, IError
{
	@Autowired
	ILabCentralReceivingDao iLabCentralReceivingDao;

	@Autowired
	private SessionFactory sessionFactory;
	
	final static Logger logger = Logger.getLogger(LabTransactionServiceImpl.class);

	@Transactional
	@Override
	public Response getSampleListForInOutPatient(Integer orgId, Integer orgUnitId, Integer visitTypeId,Integer deptId,Integer offset, Integer recordPerPage)
			throws ApplicationException
	{
		try
		{
			return iLabCentralReceivingDao.getSampleListForInOutPatient(orgId, orgUnitId, visitTypeId, deptId,offset,recordPerPage);
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response acceptOrRejectSample(List<LabSampleDetailsMasterDto> listLabSampleDetailsMasterDto)
			throws ApplicationException
	{
		try
		{
			return iLabCentralReceivingDao.acceptOrRejectSample(listLabSampleDetailsMasterDto);
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response getCentrifugationWorklist(Integer orgId, Integer orgUnitId,Integer deptId,Integer offset, Integer recordPerPage) throws ApplicationException
	{
		try
		{
			return iLabCentralReceivingDao.getCentrifugationWorklist(orgId, orgUnitId,deptId,offset,recordPerPage);
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response getTotalRecordOutPatient(Integer orgId,
			Integer orgUnitId,Integer deptId) throws ApplicationException {
		try
		{
			return iLabCentralReceivingDao.getTotalRecordOutPatient(orgId,orgUnitId,deptId);
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response getTotalcentrifugationWorkList(
			Integer orgId, Integer orgUnitId,Integer deptId) throws ApplicationException {
		try
		{
			return iLabCentralReceivingDao.getTotalcentrifugationWorkList(orgId,orgUnitId,deptId);
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response getTotalRecordINPatient(Integer orgId, Integer orgUnitId,Integer deptId)
			throws ApplicationException {
		try
		{
			return iLabCentralReceivingDao.getTotalRecordINPatient(orgId,orgUnitId,deptId);
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response getRejectedSampleList(Integer orgId, Integer orgUnitId, Integer deptId,Integer offset, Integer recordPerPage) throws ApplicationException
	{
		try
		{
			return iLabCentralReceivingDao.getRejectedSampleList(orgId,orgUnitId,deptId,offset,recordPerPage);
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response getTotalRecordRejectedSample(Integer orgId, Integer orgUnitId,Integer deptId) throws ApplicationException
	{
		try
		{
			return iLabCentralReceivingDao.getTotalRecordRejectedSample(orgId, orgUnitId, deptId);
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response sampleRecollect(List<LabSampleDetailsMasterDto> listlabLabSampleDetailsMasterDto) throws ApplicationException
	{
		try
		{
			return iLabCentralReceivingDao.sampleRecollect(listlabLabSampleDetailsMasterDto);
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response sendToDepartMent(List<LabSampleDetailsMasterDto> listlabLabSampleDetailsMasterDto)
			throws ApplicationException
	{
		try
		{
			return iLabCentralReceivingDao.sendToDepartMent(listlabLabSampleDetailsMasterDto);
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response getFilteredOutPatientList(SearchDto searchDto) throws ApplicationException {
		try
		{
			return iLabCentralReceivingDao.getFilteredOutPatientList(searchDto);
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response getFilteredCentrifugationWorkList(SearchDto searchDto) throws ApplicationException {
		try
		{
			return iLabCentralReceivingDao.getFilteredCentrifugationWorkList(searchDto);
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

}
