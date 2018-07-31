package com.param.lis.unit.dao;

import java.math.BigInteger;
import java.util.List;

import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.hibernate.HibernateException;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.param.entity.lis.unit.HeaderMaster;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.Response;
import com.param.lis.global.dto.GlobalCommonDto;
import com.param.lis.unit.dto.HeaderMasterDto;
import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings(
{ "rawtypes", "unchecked" })
public class HeaderMasterDaoImpl extends GenericDao<HeaderMaster, Integer>
		implements IHeaderMasterDao, ICommonConstants, IError
{

	public HeaderMasterDaoImpl()
	{
		super(HeaderMaster.class);
	}

	/*
	 * @Autowired private SessionFactory sessionFactory;
	 */

	@Autowired
	private DozerBeanMapper mapper;

	final static Logger logger = Logger.getLogger(HeaderMaster.class);

	@Override
	public Response addHeaderMaster(HeaderMasterDto departmentMasterDto) throws ApplicationException
	{
		try
		{
			HeaderMaster headerMaster = mapper.map(departmentMasterDto, HeaderMaster.class,
					"HeaderMasterDtoTOHeaderMaster");
			HeaderMaster headerMst = save(headerMaster);
			if (headerMst != null)
			{
				return new Response(SUCCESS, SUCCESS_200, HEADER_ADD_SUCC, null, null);
			} else
			{
				return new Response(SUCCESS, SUCCESS_200, HEADER_ADD_FAIL, null, null);
			}
		}catch(HibernateException exception){
			logger.error("Exection", exception);
		} catch (Exception e)
		{
			logger.error("Exection", e);
		}
		return new Response(ERROR, ERR_500,HEADER_ADD_FAIL, null, null);
	}

	@Override
	public Response getHeaderMasterById(Integer orgId,Integer unitId,Integer headerId) throws ApplicationException
	{
		
		try
		{
			GlobalCommonDto departmentMaster = (GlobalCommonDto) sessionFactory.getCurrentSession()
					.getNamedQuery("GET_HEADER_BY_HEADR_ID").setInteger("orgId", orgId)
					.setInteger("headerId", headerId)
					.setInteger("unitId", unitId)
                    .setResultTransformer(Transformers.aliasToBean(GlobalCommonDto.class))					
					.uniqueResult();
			
			return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, departmentMaster);

		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, HEADER_NOT_FOUND, null, null);
		}
		
	}

	@Override
	public Response updateHeaderMaster(HeaderMasterDto departmentMasterDto) throws ApplicationException
	{
		try
		{
			HeaderMaster departmentMaster = mapper.map(departmentMasterDto, HeaderMaster.class,
					"HeaderMasterDtoTOHeaderMaster");
			HeaderMaster techniqueMat = update(departmentMaster);
			if (techniqueMat != null)
			{
				return new Response(SUCCESS, SUCCESS_200, HEADER_UPDATE_SUCC, null, null);
			} else
			{
				return new Response(SUCCESS, SUCCESS_200, HEADER_UPDATE_FAIL, null, null);
			}
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, HEADER_ADD_FAIL, null, null);
		}
	}


	
	@Override
	public Response ActivateInactivateHeaderMaster(Integer orgId, Integer headerId, Character headerStatus)
			throws ApplicationException
	{
		try
		{
			HeaderMaster headerMaster = findById(headerId);
			if (headerMaster.getHeaderId() != 0)
			{
				headerMaster.setStatus(headerStatus);
				HeaderMaster departmentMst = update(headerMaster);
				if (departmentMst.getStatus() == ACTIVE)
				{
					return new Response(SUCCESS, SUCCESS_200, HEADER_ACTIVATE_SUCC, null, null);
				} else if (departmentMst.getStatus() == INACTIVE)
				{
					return new Response(SUCCESS, SUCCESS_200, HEADER_INACTIVATE_SUCC, null, null);
				} else
				{
					if (headerStatus == ACTIVE)
					{
						return new Response(SUCCESS, SUCCESS_200, HEADER_ACTIVATE_FAIL, null, null);
					} else
					{
						return new Response(SUCCESS, SUCCESS_200, HEADER_INACTIVATE_FAIL, null, null);
					}
				}
			} else
			{
				return new Response(SUCCESS, SUCCESS_200, HEADER_NOT_FOUND, null, null);
			}

		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, HEADER_NOT_FOUND, null, null);
		}
	}

	@Override
	public Response listHeaderMaster(Integer orgId, Integer offset, Integer recordPerPage) throws ApplicationException
	{
		try
		{
			List<GlobalCommonDto> listTechniqueMasterDto = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_PAGINATED_HEADER_MASTER_LIST").setInteger("orgId", orgId)
					.setFirstResult(offset != null ? offset : 0)
					.setMaxResults(recordPerPage != null ? recordPerPage : 10)
					.setResultTransformer(Transformers.aliasToBean(GlobalCommonDto.class)).list();
			
			if(listTechniqueMasterDto.isEmpty())
			{
				return new Response(ERROR, ERR_500, HEADER_RECORDS_NOT_FOUND, null, null);
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
	public Response getToTalHeaderMasterRecord(Integer orgId) throws ApplicationException
	{
		try
		{
			BigInteger HeaderMasterTotalRecordCount = (BigInteger) sessionFactory.getCurrentSession()
					.getNamedQuery("GET_TOTAL_HEADER_RECORD").setInteger("orgId", orgId).uniqueResult();
			if (HeaderMasterTotalRecordCount.compareTo(BigInteger.ZERO) == 1)
			{
				return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, HeaderMasterTotalRecordCount);
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
