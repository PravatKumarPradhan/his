package com.param.lis.microbiology.transaction.service;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.Response;
import com.param.lis.microbiology.transaction.dao.IMicrobiologyDao;
import com.param.lis.microbiology.transaction.dto.TMicroSampleMediaDto;
import com.param.lis.transaction.dto.BioChemParamDto;
import com.param.lis.transaction.dto.LabSampleDetailsMasterDto;

import in.param.exception.ApplicationException;

@Service
@SuppressWarnings({"rawtypes", "unchecked" })
public class MicrobiologyServiceImpl  implements IMicrobiologyService, ICommonConstants, IError
{
	@Autowired
	IMicrobiologyDao iMicrobiologyDao;

	
	final static Logger logger = Logger.getLogger(MicrobiologyServiceImpl.class);

	@Override
	@Transactional
	public Response microAcceptancePending(BioChemParamDto bioChemParamDto) throws ApplicationException
	{
		try
		{
			return iMicrobiologyDao.microAcceptancePending(bioChemParamDto);
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response microAcceptancePendingCount(BioChemParamDto bioChemParamDto) throws ApplicationException
	{
		try
		{
			return iMicrobiologyDao.microAcceptancePendingCount(bioChemParamDto);
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response microChemistryAcceptRejectSample(List<LabSampleDetailsMasterDto> listlabSampleDetailsMasterDto)
			throws ApplicationException
	{
		try
		{
			return iMicrobiologyDao.microChemistryAcceptRejectSample(listlabSampleDetailsMasterDto);
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response getMicroChemistryWorklist(BioChemParamDto bioChemParamDto) throws ApplicationException
	{
		try
		{
			return iMicrobiologyDao.getMicroChemistryWorklist(bioChemParamDto);
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response getMicroChemistryWorklistCount(BioChemParamDto bioChemParamDto) throws ApplicationException
	{
		try
		{
			return iMicrobiologyDao.getMicroChemistryWorklistCount(bioChemParamDto);
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	@Override
	@Transactional
	public Response sendForIncubationObjservation(List<LabSampleDetailsMasterDto> listlabSampleDetailsMasterDto)
			throws ApplicationException
	{
		try
		{
			return iMicrobiologyDao.sendForIncubationObjservation(listlabSampleDetailsMasterDto);
		} catch (Exception e)
		{
			logger.error("Exception", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

  @Override
  @Transactional
  public Response updateLabSampleDetailsMaster(LabSampleDetailsMasterDto labSampleDetailsMasterDto)
      throws ApplicationException {
    try
    {
        return iMicrobiologyDao.updateLabSampleDetailsMaster(labSampleDetailsMasterDto);
    } catch (Exception e)
    {
        logger.error("Exception", e);
        return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  @Transactional
  public Response getAddedMediaForIncuabation(Integer labSampleDtlsId, Integer orgId,
      Integer orgUniId) throws ApplicationException {
    try
    {
        return iMicrobiologyDao.getAddedMediaForIncuabation(labSampleDtlsId, orgId, orgUniId);
    } catch (Exception e)
    {
        logger.error("Exception", e);
        return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

  @Override
  @Transactional
  public Response addMediaForIncuabation(List<TMicroSampleMediaDto> listTMicroSampleMediaDto)
      throws ApplicationException {
    try
    {
        return iMicrobiologyDao.addMediaForIncuabation(listTMicroSampleMediaDto);
    } catch (Exception e)
    {
        logger.error("Exception", e);
        return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
    }
  }

	

}
