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
import com.param.entity.lis.global.ContainerMaster;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.Response;
import com.param.lis.global.dto.ContainerMasterDto;
import com.param.lis.global.dto.GlobalCommonDto;
import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings(
{ "rawtypes", "unchecked" })
public class ContainerMasterDaoImpl extends GenericDao<ContainerMaster, Integer>
		implements IContainerMasterDao, ICommonConstants, IError
{

	public ContainerMasterDaoImpl()
	{
		super(ContainerMaster.class);
	}
	/*
	 * @Autowired private SessionFactory sessionFactory;
	 */

	@Autowired
	private DozerBeanMapper mapper;

	final static Logger logger = Logger.getLogger(ContainerMaster.class);

	@Override
	public Response addContainerMaster(ContainerMasterDto containerMasterDto) throws ApplicationException
	{
		try
		{
			ContainerMaster containerMaster = mapper.map(containerMasterDto, ContainerMaster.class,
					"ContainerMasterDtoTOContainerMaster");
			ContainerMaster containerMst = save(containerMaster);
			if (containerMst != null)
			{
				return new Response(SUCCESS, SUCCESS_200, CONTAINER_ADD_SUCC, null, null);
			} else
			{
				return new Response(SUCCESS, SUCCESS_200, CONTAINER_ADD_FAIL, null, null);
			}
		}catch(HibernateException exception){
			logger.error("Exection", exception);
		} catch (Exception e)
		{
			logger.error("Exection", e);
		}
		return new Response(ERROR, ERR_500, CONTAINER_ADD_FAIL, null, null);
	}

	@Override
	public Response getContainerMasterById(Integer orgId, Integer containerId) throws ApplicationException
	{
		try
		{
			GlobalCommonDto containerMaster = (GlobalCommonDto) sessionFactory.getCurrentSession()
					.getNamedQuery("GET_CONTAINER_BY_CONTAINER_ID").setInteger("orgId", orgId)
					.setInteger("containerId", containerId)
                    .setResultTransformer(Transformers.aliasToBean(GlobalCommonDto.class))					
					.uniqueResult();
			
			return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, containerMaster);

		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, CONTAINER_NOT_FOUND, null, null);
		}
		
	}

	@Override
	public Response updateContainerMaster(ContainerMasterDto containerMasterDto) throws ApplicationException
	{
		try
		{
			ContainerMaster containerMaster = mapper.map(containerMasterDto, ContainerMaster.class,
					"ContainerMasterDtoTOContainerMaster");
			ContainerMaster ContainerMst = update(containerMaster);
			if (ContainerMst != null)
			{
				return new Response(SUCCESS, SUCCESS_200, CONTAINER_UPDATE_SUCC, null, null);
			} else
			{
				return new Response(SUCCESS, SUCCESS_200, CONTAINER_UPDATE_FAIL, null, null);
			}
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, CONTAINER_UPDATE_FAIL, null, null);
		}
	}


	
	@Override
	public Response ActivateInactivateContainerMaster(Integer orgId, Integer sampleId, Character containerStatus)
			throws ApplicationException
	{
		try
		{
			ContainerMaster containerMaster = findById(sampleId);
			if (containerMaster.getContainerId() != 0)
			{
				containerMaster.setStatus(containerStatus);
				ContainerMaster containerMst = update(containerMaster);
				if (containerMst.getStatus() == ACTIVE)
				{
					return new Response(SUCCESS, SUCCESS_200, CONTAINER_ACTIVATE_SUCC, null, null);
				} else if (containerMst.getStatus() == INACTIVE)
				{
					return new Response(SUCCESS, SUCCESS_200, CONTAINER_INACTIVATE_SUCC, null, null);
				} else
				{
					if (containerStatus == ACTIVE)
					{
						return new Response(SUCCESS, SUCCESS_200, CONTAINER_ACTIVATE_FAIL, null, null);
					} else
					{
						return new Response(SUCCESS, SUCCESS_200, CONTAINER_INACTIVATE_FAIL, null, null);
					}
				}
			} else
			{
				return new Response(SUCCESS, SUCCESS_200, CONTAINER_NOT_FOUND, null, null);
			}

		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, CONTAINER_NOT_FOUND, null, null);
		}
	}

	@Override
	public Response listContainerMaster(Integer orgId, Integer offset, Integer recordPerPage) throws ApplicationException
	{
		try
		{
			List<GlobalCommonDto> listContainerMasterDto = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_PAGINATED_CONTAINER_MASTER_LIST").setInteger("orgId", orgId)
					.setFirstResult(offset != null ? offset : 0)
					.setMaxResults(recordPerPage != null ? recordPerPage : 10)
					.setResultTransformer(Transformers.aliasToBean(GlobalCommonDto.class)).list();
			
			if(listContainerMasterDto.isEmpty())
			{
				return new Response(ERROR, ERR_500, CONTAINER_RECORDS_NOT_FOUND, null, null);
			}
			else{
				return new Response(SUCCESS, SUCCESS_200, null, listContainerMasterDto, null);
			}
			
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	public Response getToTalContainerMasterRecord(Integer orgId) throws ApplicationException
	{
		try
		{
			BigInteger containerMasterTotalRecordCount = (BigInteger) sessionFactory.getCurrentSession()
					.getNamedQuery("GET_TOTAL_CONTAINER_RECORD").setInteger("orgId", orgId).uniqueResult();
			if (containerMasterTotalRecordCount.compareTo(BigInteger.ZERO) == 1)
			{
				return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, containerMasterTotalRecordCount);
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
