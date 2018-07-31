package com.param.lis.global.dao;

import java.math.BigInteger;
import java.util.List;

import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.hibernate.HibernateError;
import org.hibernate.HibernateException;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.param.entity.lis.global.PrerequisitesMaster;
import com.param.entity.lis.global.SampleMaster;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.Response;
import com.param.lis.global.dto.GlobalCommonDto;
import com.param.lis.global.dto.PrerequisitesMasterDto;
import com.param.lis.global.dto.SampleMasterDto;
import in.param.common.dao.GenericDao;
import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings(
{ "rawtypes", "unchecked" })
public class PrerequisitesMasterDaoImpl extends GenericDao<PrerequisitesMaster, Integer>
		implements  IPrerequisitesDao, ICommonConstants, IError
{

	public PrerequisitesMasterDaoImpl()
	{
		super(PrerequisitesMaster.class);
	}

	/*
	 * @Autowired private SessionFactory sessionFactory;
	 */

	@Autowired
	private DozerBeanMapper mapper;

	final static Logger logger = Logger.getLogger(PrerequisitesMaster.class);

	@Override
	public Response addPrerequisitesMaster(PrerequisitesMasterDto prerequisitesMasterDto) throws ApplicationException
	{
		try
		{
			PrerequisitesMaster prerequisitesMst = mapper.map(prerequisitesMasterDto, PrerequisitesMaster.class,
					"PrerequisitesMasterDtoTOPrerequisitesMaster");
			PrerequisitesMaster PrerequisitesMst = save(prerequisitesMst);
			if (PrerequisitesMst != null)
			{
				return new Response(SUCCESS, SUCCESS_200, PREREQUISITES_ADD_SUCC, null, null);
			} else
			{
				return new Response(SUCCESS, SUCCESS_200, PREREQUISITES_ADD_FAIL, null, null);
			}
		}catch(HibernateException exception){
			logger.error("Exection", exception);
		} catch (Exception e)
		{
			logger.error("Exection", e);
		}
		return new Response(ERROR, ERR_500, PREREQUISITES_ADD_FAIL, null, null);
	}

	@Override
	public Response getPrerequisitesMasterById(Integer orgId, Integer prerequisitesId) throws ApplicationException
	{

		try
		{
			GlobalCommonDto containerMaster = (GlobalCommonDto) sessionFactory.getCurrentSession()
					.getNamedQuery("GET_PREREQUISITES_BY_PREREQUISITES_ID").setInteger("orgId", orgId)
					.setInteger("prerequisitesId", prerequisitesId)
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
	public Response updatePrerequisitesMaster(PrerequisitesMasterDto prerequisitesMasterDto) throws ApplicationException
	{
		try
		{
			PrerequisitesMaster prerequisitesMaster = mapper.map(prerequisitesMasterDto, PrerequisitesMaster.class,
					"PrerequisitesMasterDtoTOPrerequisitesMaster");
			PrerequisitesMaster prerequisitesMst = update(prerequisitesMaster);
			if (prerequisitesMst != null)
			{
				return new Response(SUCCESS, SUCCESS_200, PREREQUISITES_UPDATE_SUCC, null, null);
			} else
			{
				return new Response(SUCCESS, SUCCESS_200, PREREQUISITES_UPDATE_FAIL, null, null);
			}
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, PREREQUISITES_ADD_FAIL, null, null);
		}
	}


	
	@Override
	public Response ActivateInactivatePrerequisitesMaster(Integer orgId, Integer prerequisitesId, Character prerequisitesStatus)
			throws ApplicationException
	{
		try
		{
			PrerequisitesMaster prerequisitesMaster = findById(prerequisitesId);
			if (prerequisitesMaster.getPrerequisitesId()!= 0)
			{
				prerequisitesMaster.setStatus(prerequisitesStatus);
				PrerequisitesMaster prerequisitesMst = update(prerequisitesMaster);
				if (prerequisitesMst.getStatus() == ACTIVE)
				{
					return new Response(SUCCESS, SUCCESS_200, PREREQUISITES_ACTIVATE_SUCC, null, null);
				} else if (prerequisitesMst.getStatus()== INACTIVE)
				{
					return new Response(SUCCESS, SUCCESS_200, PREREQUISITES_INACTIVATE_SUCC, null, null);
				} else
				{
					if (prerequisitesStatus == ACTIVE)
					{
						return new Response(SUCCESS, SUCCESS_200, PREREQUISITES_ACTIVATE_FAIL, null, null);
					} else
					{
						return new Response(SUCCESS, SUCCESS_200, PREREQUISITES_INACTIVATE_FAIL, null, null);
					}
				}
			} else
			{
				return new Response(SUCCESS, SUCCESS_200, PREREQUISITES_NOT_FOUND, null, null);
			}

		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, PREREQUISITES_NOT_FOUND, null, null);
		}
	}

	@Override
	public Response listPrerequisitesMaster(Integer orgId, Integer offset, Integer recordPerPage) throws ApplicationException
	{
		try
		{
			List<GlobalCommonDto> listPrerequisitesMasterDto = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_PAGINATED_PREREQUISITES_MASTER_LIST").setInteger("orgId", orgId)
					.setFirstResult(offset != null ? offset : 0)
					.setMaxResults(recordPerPage != null ? recordPerPage : 10)
					.setResultTransformer(Transformers.aliasToBean(GlobalCommonDto.class)).list();
			
			if(listPrerequisitesMasterDto.isEmpty())
			{
				return new Response(ERROR, ERR_500, PREREQUISITES_RECORDS_NOT_FOUND, null, null);
			}
			else{
				return new Response(SUCCESS, SUCCESS_200, null, listPrerequisitesMasterDto, null);
			}
			
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	public Response getToTalPrerequisitesMasterRecord(Integer orgId) throws ApplicationException
	{
		try
		{
			BigInteger prerequisitesMasterTotalRecordCount = (BigInteger) sessionFactory.getCurrentSession()
					.getNamedQuery("GET_TOTAL_PREREQUISITES_RECORD").setInteger("orgId", orgId).uniqueResult();
			if (prerequisitesMasterTotalRecordCount.compareTo(BigInteger.ZERO) == 1)
			{
				return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, prerequisitesMasterTotalRecordCount);
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
