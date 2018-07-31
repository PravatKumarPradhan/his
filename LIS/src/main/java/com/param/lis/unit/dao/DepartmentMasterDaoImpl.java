package com.param.lis.unit.dao;

import java.math.BigInteger;
import java.util.List;

import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.hibernate.HibernateException;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.param.entity.lis.unit.DepartmentMaster;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.Response;
import com.param.lis.global.dto.GlobalCommonDto;
import com.param.lis.unit.dto.DepartmentMasterDto;
import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings(
{ "rawtypes", "unchecked" })
public class DepartmentMasterDaoImpl extends GenericDao<DepartmentMaster, Integer>
		implements IDepartmentMasterDao, ICommonConstants, IError
{

	public DepartmentMasterDaoImpl()
	{
		super(DepartmentMaster.class);
	}

	/*
	 * @Autowired private SessionFactory sessionFactory;
	 */

	@Autowired
	private DozerBeanMapper mapper;

	final static Logger logger = Logger.getLogger(DepartmentMaster.class);

	@Override
	public Response addDepartmentMaster(DepartmentMasterDto departmentMasterDto) throws ApplicationException
	{
		try
		{
			DepartmentMaster techniqueMaster = mapper.map(departmentMasterDto, DepartmentMaster.class,
					"DepartmentMasterDtoTODepartmentMaster");
			DepartmentMaster departmentMst = save(techniqueMaster);
			if (departmentMst != null)
			{
				return new Response(SUCCESS, SUCCESS_200, DEPARTMENT_ADD_SUCC, null, null);
			} else
			{
				return new Response(SUCCESS, SUCCESS_200, DEPARTMENT_ADD_FAIL, null, null);
			}
		}catch(HibernateException exception){
			logger.error("Exection", exception);
		} catch (Exception e)
		{
			logger.error("Exection", e);
		}
		return new Response(ERROR, ERR_500,DEPARTMENT_ADD_FAIL, null, null);
	}

	@Override
	public Response getDepartmentMasterById(Integer orgId,Integer unitId,Integer departmentId) throws ApplicationException
	{
		
		try
		{
			GlobalCommonDto departmentMaster = (GlobalCommonDto) sessionFactory.getCurrentSession()
					.getNamedQuery("GET_DEPARTMENT_BY_DEPT_ID").setInteger("orgId", orgId)
					.setInteger("departmentId", departmentId)
					.setInteger("unitId", unitId)
                    .setResultTransformer(Transformers.aliasToBean(GlobalCommonDto.class))					
					.uniqueResult();
			
			return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, departmentMaster);

		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, DEPARTMENT_NOT_FOUND, null, null);
		}
		
	}

	@Override
	public Response updateDepartmentMaster(DepartmentMasterDto departmentMasterDto) throws ApplicationException
	{
		try
		{
			DepartmentMaster departmentMaster = mapper.map(departmentMasterDto, DepartmentMaster.class,
					"DepartmentMasterDtoTODepartmentMaster");
			DepartmentMaster techniqueMat = update(departmentMaster);
			if (techniqueMat != null)
			{
				return new Response(SUCCESS, SUCCESS_200, DEPARTMENT_UPDATE_SUCC, null, null);
			} else
			{
				return new Response(SUCCESS, SUCCESS_200, DEPARTMENT_UPDATE_FAIL, null, null);
			}
		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, DEPARTMENT_ADD_FAIL, null, null);
		}
	}


	
	@Override
	public Response ActivateInactivateDepartmentMaster(Integer orgId, Integer departmentId, Character departmentStatus)
			throws ApplicationException
	{
		try
		{
			DepartmentMaster departmentMaster = findById(departmentId);
			if (departmentMaster.getDepartmentId() != 0)
			{
				departmentMaster.setStatus(departmentStatus);
				DepartmentMaster departmentMst = update(departmentMaster);
				if (departmentMst.getStatus() == ACTIVE)
				{
					return new Response(SUCCESS, SUCCESS_200, DEPARTMENT_ACTIVATE_SUCC, null, null);
				} else if (departmentMst.getStatus() == INACTIVE)
				{
					return new Response(SUCCESS, SUCCESS_200, DEPARTMENT_INACTIVATE_SUCC, null, null);
				} else
				{
					if (departmentStatus == ACTIVE)
					{
						return new Response(SUCCESS, SUCCESS_200, DEPARTMENT_ACTIVATE_FAIL, null, null);
					} else
					{
						return new Response(SUCCESS, SUCCESS_200, DEPARTMENT_INACTIVATE_FAIL, null, null);
					}
				}
			} else
			{
				return new Response(SUCCESS, SUCCESS_200, DEPARTMENT_NOT_FOUND, null, null);
			}

		} catch (Exception e)
		{
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, DEPARTMENT_NOT_FOUND, null, null);
		}
	}

	@Override
	public Response listDepartmentMaster(Integer orgId, Integer offset, Integer recordPerPage) throws ApplicationException
	{
		try
		{
			List<GlobalCommonDto> listTechniqueMasterDto = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_PAGINATED_DEPARTMENT_MASTER_LIST").setInteger("orgId", orgId)
					.setFirstResult(offset != null ? offset : 0)
					.setMaxResults(recordPerPage != null ? recordPerPage : 10)
					.setResultTransformer(Transformers.aliasToBean(GlobalCommonDto.class)).list();
			
			if(listTechniqueMasterDto.isEmpty())
			{
				return new Response(ERROR, ERR_500, DEPARTMENT_RECORDS_NOT_FOUND, null, null);
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
	public Response getToTalDepartmentMasterRecord(Integer orgId) throws ApplicationException
	{
		try
		{
			BigInteger DepartmentMasterTotalRecordCount = (BigInteger) sessionFactory.getCurrentSession()
					.getNamedQuery("GET_TOTAL_DEPARTMENT_RECORD").setInteger("orgId", orgId).uniqueResult();
			if (DepartmentMasterTotalRecordCount.compareTo(BigInteger.ZERO) == 1)
			{
				return new Response(SUCCESS, SUCCESS_200, SUCCESS_200_MESSAGE, null, DepartmentMasterTotalRecordCount);
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
