package com.param.lis.global.dao;

import java.math.BigInteger;
import java.util.List;

import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.hibernate.HibernateException;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.param.entity.lis.global.TechniqueMaster;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.Response;
import com.param.lis.global.dto.GlobalCommonDto;
import com.param.lis.global.dto.SampleMasterDto;
import com.param.lis.global.dto.TechniqueMasterDto;
import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings(
{ "rawtypes", "unchecked" })
public class TechinqueMasterDaoImpl extends GenericDao<TechniqueMaster, Integer>
		implements ITechinqueMasterDao, ICommonConstants, IError
{

	public TechinqueMasterDaoImpl()
	{
		super(TechniqueMaster.class);
	}

	/*
	 * @Autowired private SessionFactory sessionFactory;
	 */

	@Autowired
	private DozerBeanMapper mapper;

	final static Logger logger = Logger.getLogger(TechniqueMaster.class);

	@Override
	public Response addTechinqueMaster(TechniqueMasterDto techniqueMasterDto) throws ApplicationException
	{
		try
		{
			TechniqueMaster techniqueMaster = mapper.map(techniqueMasterDto, TechniqueMaster.class,
					"TechniqueMasterDtoTOTechniqueMaster");
			TechniqueMaster techniqueMst = save(techniqueMaster);
			if (techniqueMst != null)
			{
				return new Response(SUCCESS, SUCCESS_200, TECHINQUE_ADD_SUCC, null, null);
			} else
			{
				return new Response(SUCCESS, SUCCESS_200, TECHINQUE_ADD_FAIL, null, null);
			}
		}catch(HibernateException exception){
			logger.error("Exection", exception);
		} catch (Exception e)
		{
			logger.error("Exection", e);
		}
		return new Response(ERROR, ERR_500,TECHINQUE_ADD_FAIL, null, null);
	}

	@Override
	public Response getTechinqueMasterById(Integer orgId, Integer techniqueId) throws ApplicationException
	{
		
		try
		{
			GlobalCommonDto containerMaster = (GlobalCommonDto) sessionFactory.getCurrentSession()
					.getNamedQuery("GET_TECHNIQUE_BY_SAMPLE_ID").setInteger("orgId", orgId)
					.setInteger("techniqueId", techniqueId)
                    .setResultTransformer(Transformers.aliasToBean(GlobalCommonDto.class))					
					.uniqueResult();
			
			return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, containerMaster);

		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, TECHINQUE_NOT_FOUND, null, null);
		}
		
	}

	@Override
	public Response updateTechinqueMaster(TechniqueMasterDto techniqueMasterDto) throws ApplicationException
	{
		try
		{
			TechniqueMaster TechniqueMaster = mapper.map(techniqueMasterDto, TechniqueMaster.class,
					"TechniqueMasterDtoTOTechniqueMaster");
			TechniqueMaster techniqueMat = update(TechniqueMaster);
			if (techniqueMat != null)
			{
				return new Response(SUCCESS, SUCCESS_200, TECHINQUE_UPDATE_SUCC, null, null);
			} else
			{
				return new Response(SUCCESS, SUCCESS_200, TECHINQUE_UPDATE_FAIL, null, null);
			}
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, TECHINQUE_ADD_FAIL, null, null);
		}
	}


	
	@Override
	public Response ActivateInactivateTechinqueMaster(Integer orgId, Integer techinqueId, Character techinqueStatus)
			throws ApplicationException
	{
		try
		{
			TechniqueMaster techniqueMaster = findById(techinqueId);
			if (techniqueMaster.getTechniqueId() != 0)
			{
				techniqueMaster.setStatus(techinqueStatus);
				TechniqueMaster techniqueMst = update(techniqueMaster);
				if (techniqueMst.getStatus() == ACTIVE)
				{
					return new Response(SUCCESS, SUCCESS_200, TECHINQUE_ACTIVATE_SUCC, null, null);
				} else if (techniqueMst.getStatus() == INACTIVE)
				{
					return new Response(SUCCESS, SUCCESS_200, TECHINQUE_INACTIVATE_SUCC, null, null);
				} else
				{
					if (techinqueStatus == ACTIVE)
					{
						return new Response(SUCCESS, SUCCESS_200, TECHINQUE_ACTIVATE_FAIL, null, null);
					} else
					{
						return new Response(SUCCESS, SUCCESS_200, TECHINQUE_INACTIVATE_FAIL, null, null);
					}
				}
			} else
			{
				return new Response(SUCCESS, SUCCESS_200, TECHINQUE_NOT_FOUND, null, null);
			}

		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, TECHINQUE_NOT_FOUND, null, null);
		}
	}

	@Override
	public Response listTechinqueMaster(Integer orgId, Integer offset, Integer recordPerPage) throws ApplicationException
	{
		try
		{
			List<GlobalCommonDto> listTechniqueMasterDto = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_PAGINATED_TECHNIQUE_MASTER_LIST").setInteger("orgId", orgId)
					.setFirstResult(offset != null ? offset : 0)
					.setMaxResults(recordPerPage != null ? recordPerPage : 10)
					.setResultTransformer(Transformers.aliasToBean(GlobalCommonDto.class)).list();
			
			if(listTechniqueMasterDto.isEmpty())
			{
				return new Response(ERROR, ERR_500, TECHINQUE_RECORDS_NOT_FOUND, null, null);
			}
			else{
				return new Response(SUCCESS, SUCCESS_200, null, listTechniqueMasterDto, null);
			}
			
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	public Response getToTalTechinqueMasterRecord(Integer orgId) throws ApplicationException
	{
		try
		{
			BigInteger TechinqueMasterTotalRecordCount = (BigInteger) sessionFactory.getCurrentSession()
					.getNamedQuery("GET_TOTAL_TECHNIQUE_RECORD").setInteger("orgId", orgId).uniqueResult();
			if (TechinqueMasterTotalRecordCount.compareTo(BigInteger.ZERO) == 1)
			{
				return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, TechinqueMasterTotalRecordCount);
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
