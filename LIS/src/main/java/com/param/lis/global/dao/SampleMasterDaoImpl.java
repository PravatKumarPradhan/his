package com.param.lis.global.dao;

import java.math.BigInteger;
import java.util.List;

import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.param.entity.lis.global.SampleMaster;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.Response;
import com.param.lis.global.dto.GlobalCommonDto;
import com.param.lis.global.dto.SampleMasterDto;
import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings(
{ "rawtypes", "unchecked" })
public class SampleMasterDaoImpl extends GenericDao<SampleMaster, Integer>
		implements ISampleMasterDao, ICommonConstants, IError
{

	public SampleMasterDaoImpl()
	{
		super(SampleMaster.class);
	}

	/*
	 * @Autowired private SessionFactory sessionFactory;
	 */
	
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private DozerBeanMapper mapper;

	final static Logger logger = Logger.getLogger(SampleMasterDaoImpl.class);

	@Override
	public Response addSampleMaster(SampleMasterDto sampleMasterDto) throws ApplicationException
	{
		try
		{
			SampleMaster sampleMaster = mapper.map(sampleMasterDto, SampleMaster.class,
					"SampleMasterDtoTOSampleMaster");
			SampleMaster sampleMst = save(sampleMaster);
			if (sampleMst != null)
			{
				return new Response(SUCCESS, SUCCESS_200, SAMPLE_ADD_SUCC, null, null);
			} else
			{
				return new Response(SUCCESS, SUCCESS_200, SAMPLE_ADD_FAIL, null, null);
			}
		}catch(HibernateException exception){
			logger.error("Exection", exception);
		} catch (Exception e)
		{
			logger.error("Exection", e);
		}
		return new Response(ERROR, ERR_500, SAMPLE_ADD_FAIL, null, null);
	}

	@Override
	public Response getSampleMasterById(Integer orgId, Integer sampleId) throws ApplicationException
	{
		try
		{
			GlobalCommonDto sampleMasterDto = (GlobalCommonDto) sessionFactory.getCurrentSession()
					.getNamedQuery("GET_SAMPLE_BY_SAMPLE_ID").setInteger("orgId", orgId)
					.setInteger("sampleId", sampleId)
                    .setResultTransformer(Transformers.aliasToBean(GlobalCommonDto.class))					
					.uniqueResult();
			
			return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, sampleMasterDto);

		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, SAMPLE_NOT_FOUND, null, null);
		}
	}

	@Override
	public Response updateSampleMaster(SampleMasterDto sampleMasterDto) throws ApplicationException
	{
		try
		{
			SampleMaster sampleMaster = mapper.map(sampleMasterDto, SampleMaster.class,
					"SampleMasterDtoTOSampleMaster");
			SampleMaster sampleMst = update(sampleMaster);
			if (sampleMst != null)
			{
				return new Response(SUCCESS, SUCCESS_200, SAMPLE_UPDATE_SUCC, null, null);
			} else
			{
				return new Response(SUCCESS, SUCCESS_200, SAMPLE_UPDATE_FAIL, null, null);
			}
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, SAMPLE_ADD_FAIL, null, null);
		}
	}


	
	@Override
	public Response ActivateInactivateSampleMaster(Integer orgId, Integer sampleId, Character sampleStatus)
			throws ApplicationException
	{
		try
		{
			SampleMaster sampleMaster = findById(sampleId);
			if (sampleMaster.getSampleId() != 0)
			{
				sampleMaster.setStatus(sampleStatus);
				SampleMaster sampleMst = update(sampleMaster);
				if (sampleMst.getStatus() == ACTIVE)
				{
					return new Response(SUCCESS, SUCCESS_200, SAMPLE_ACTIVATE_SUCC, null, null);
				} else if (sampleMst.getStatus() == INACTIVE)
				{
					return new Response(SUCCESS, SUCCESS_200, SAMPLE_INACTIVATE_SUCC, null, null);
				} else
				{
					if (sampleStatus == ACTIVE)
					{
						return new Response(SUCCESS, SUCCESS_200, SAMPLE_ACTIVATE_FAIL, null, null);
					} else
					{
						return new Response(SUCCESS, SUCCESS_200, SAMPLE_INACTIVATE_FAIL, null, null);
					}
				}
			} else
			{
				return new Response(SUCCESS, SUCCESS_200, SAMPLE_NOT_FOUND, null, null);
			}

		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, SAMPLE_NOT_FOUND, null, null);
		}
	}

	@Override
	public Response listSampleMaster(Integer orgId, Integer offset, Integer recordPerPage) throws ApplicationException
	{
		try
		{
			List<GlobalCommonDto> listSampleMasterDto = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_PAGINATED_SAMPLE_MASTER_LIST").setInteger("orgId", orgId)
					.setFirstResult(offset != null ? offset : 0)
					.setMaxResults(recordPerPage != null ? recordPerPage : 10)
					.setResultTransformer(Transformers.aliasToBean(GlobalCommonDto.class)).list();
			
			if(listSampleMasterDto.isEmpty())
			{
				return new Response(ERROR, ERR_500, SAMPLE_RECORDS_NOT_FOUND, null, null);
			}
			else{
				return new Response(SUCCESS, SUCCESS_200, null, listSampleMasterDto, null);
			}
			
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	public Response getToTalSampleMasterRecord(Integer orgId) throws ApplicationException
	{
		try
		{
			BigInteger sampleMasterTotalRecordCount = (BigInteger) sessionFactory.getCurrentSession()
					.getNamedQuery("GET_TOTAL_SAMPLE_RECORD").setInteger("orgId", orgId).uniqueResult();
			if (sampleMasterTotalRecordCount.compareTo(BigInteger.ZERO) == 1)
			{
				return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, sampleMasterTotalRecordCount);
			} else
			{
				return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, BigInteger.ZERO);
			}

		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

}
