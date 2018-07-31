package com.param.lis.unit.dao;

import java.math.BigInteger;
import java.util.List;

import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.hibernate.HibernateException;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.param.entity.lis.unit.StorageLocationMaster;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.Response;
import com.param.lis.global.dto.GlobalCommonDto;
import com.param.lis.unit.dto.StorageLocationMasterDto;
import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings(
{ "rawtypes", "unchecked" })
public class StorageLocationMasterDaoImpl extends GenericDao<StorageLocationMaster, Integer>
		implements IStorageLocationMasterDao, ICommonConstants, IError
{

	public StorageLocationMasterDaoImpl()
	{
		super(StorageLocationMaster.class);
	}

	/*
	 * @Autowired private SessionFactory sessionFactory;
	 */

	@Autowired
	private DozerBeanMapper mapper;

	final static Logger logger = Logger.getLogger(StorageLocationMaster.class);

	@Override
	public Response addStorageLocationMaster(StorageLocationMasterDto storageLocationMasterDto) throws ApplicationException
	{
		try
		{
			StorageLocationMaster techniqueMaster = mapper.map(storageLocationMasterDto, StorageLocationMaster.class,
					"StorageLocationMasterDtoTOStorageLocationMaster");
			StorageLocationMaster departmentMst = save(techniqueMaster);
			if (departmentMst != null)
			{
				return new Response(SUCCESS, SUCCESS_200, STORAGE_LOCATION_ADD_SUCC, null, null);
			} else
			{
				return new Response(SUCCESS, SUCCESS_200, STORAGE_LOCATION_ADD_FAIL, null, null);
			}
		}catch(HibernateException exception){
			logger.error("Exection", exception);
		} catch (Exception e)
		{
			logger.error("Exection", e);
		}
		return new Response(ERROR, ERR_500,STORAGE_LOCATION_ADD_FAIL, null, null);
	}

	@Override
	public Response getStorageLocationMasterById(Integer orgId,Integer unitId,Integer storageLocationId) throws ApplicationException
	{
		
		try
		{
			GlobalCommonDto departmentMaster = (GlobalCommonDto) sessionFactory.getCurrentSession()
					.getNamedQuery("GET_STORAGE_LOCATION_MASTER_BY_DEPT_ID").setInteger("orgId", orgId)
					.setInteger("storageLocationId", storageLocationId)
					.setInteger("unitId", unitId)
                    .setResultTransformer(Transformers.aliasToBean(GlobalCommonDto.class))					
					.uniqueResult();
			
			return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, departmentMaster);

		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, STORAGE_LOCATION_NOT_FOUND, null, null);
		}
		
	}

	@Override
	public Response updateStorageLocationMaster(StorageLocationMasterDto storageLocationMasterDto) throws ApplicationException
	{
		try
		{
			StorageLocationMaster departmentMaster = mapper.map(storageLocationMasterDto, StorageLocationMaster.class,
					"StorageLocationMasterDtoTOStorageLocationMaster");
			StorageLocationMaster techniqueMat = update(departmentMaster);
			if (techniqueMat != null)
			{
				return new Response(SUCCESS, SUCCESS_200, STORAGE_LOCATION_UPDATE_SUCC, null, null);
			} else
			{
				return new Response(SUCCESS, SUCCESS_200, STORAGE_LOCATION_UPDATE_FAIL, null, null);
			}
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, STORAGE_LOCATION_ADD_FAIL, null, null);
		}
	}


	
	@Override
	public Response ActivateInactivateStorageLocationMaster(Integer orgId, Integer storageLocationId, Character storageLocationStatus)
			throws ApplicationException
	{
		try
		{
			StorageLocationMaster storageLocationMaster = findById(storageLocationId);
			if (storageLocationMaster.getStorageLocationId() != 0)
			{
				storageLocationMaster.setStatus(storageLocationStatus);
				StorageLocationMaster departmentMst = update(storageLocationMaster);
				if (departmentMst.getStatus() == ACTIVE)
				{
					return new Response(SUCCESS, SUCCESS_200, STORAGE_LOCATION_ACTIVATE_SUCC, null, null);
				} else if (departmentMst.getStatus() == INACTIVE)
				{
					return new Response(SUCCESS, SUCCESS_200, STORAGE_LOCATION_INACTIVATE_SUCC, null, null);
				} else
				{
					if (storageLocationStatus == ACTIVE)
					{
						return new Response(SUCCESS, SUCCESS_200, STORAGE_LOCATION_ACTIVATE_FAIL, null, null);
					} else
					{
						return new Response(SUCCESS, SUCCESS_200, STORAGE_LOCATION_INACTIVATE_FAIL, null, null);
					}
				}
			} else
			{
				return new Response(SUCCESS, SUCCESS_200, STORAGE_LOCATION_NOT_FOUND, null, null);
			}

		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, STORAGE_LOCATION_NOT_FOUND, null, null);
		}
	}

	@Override
	public Response listStorageLocationMaster(Integer orgId, Integer offset, Integer recordPerPage) throws ApplicationException
	{
		try
		{
			List<GlobalCommonDto> listTechniqueMasterDto = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_PAGINATED_STORAGE_LOCATION_MASTER_MASTER_LIST").setInteger("orgId", orgId)
					.setFirstResult(offset != null ? offset : 0)
					.setMaxResults(recordPerPage != null ? recordPerPage : 10)
					.setResultTransformer(Transformers.aliasToBean(GlobalCommonDto.class)).list();
			
			if(listTechniqueMasterDto.isEmpty())
			{
				return new Response(ERROR, ERR_500, STORAGE_LOCATION_RECORDS_NOT_FOUND, null, null);
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
	public Response getToTalStorageLocationMasterRecord(Integer orgId) throws ApplicationException
	{
		try
		{
			BigInteger StorageLocationMasterTotalRecordCount = (BigInteger) sessionFactory.getCurrentSession()
					.getNamedQuery("GET_TOTAL_STORAGE_LOCATION_MASTER_RECORD").setInteger("orgId", orgId).uniqueResult();
			if (StorageLocationMasterTotalRecordCount.compareTo(BigInteger.ZERO) == 1)
			{
				return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, StorageLocationMasterTotalRecordCount);
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
