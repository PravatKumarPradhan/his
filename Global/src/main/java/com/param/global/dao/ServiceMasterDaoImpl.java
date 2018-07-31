package com.param.global.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.param.global.common.ICommonConstants;
import com.param.global.common.IError;
import com.param.global.common.Response;
import com.param.global.dto.ServiceMasterDto;
import com.param.global.dto.ServicePriceDetailsDto;
import com.param.global.dto.UnitServiceMapperDto;
import com.param.global.model.ServiceMaster;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ServiceMasterDaoImpl extends GenericDao<ServiceMaster, Integer>
		implements IServiceMasterDao, ICommonConstants, IError {

	public ServiceMasterDaoImpl() {
		super(ServiceMaster.class);
	}

	@Autowired
	private DozerBeanMapper mapper;

	final static Logger logger = Logger.getLogger(ServiceMasterDaoImpl.class);

	@Override
	public Response saveOrganizationServiceMaster(ServiceMasterDto xServiceMasterDto) throws ApplicationException {
		try {
			// ----------- check if Service Name alreay exist ---------------//
			Long count = (Long) sessionFactory.getCurrentSession()
					.createQuery(
							"select count(*) from ServiceMaster mst where lower(mst.serviceStandardName) LIKE lower(CONCAT('%', :serviceStandardName, '%'))")
					.setString("serviceStandardName", xServiceMasterDto.getServiceStandardName()).uniqueResult();
			boolean exists = count > 0;
			if (exists)
				return new Response(ERROR, ALREADY_EXIST_CODE, SERVICE_NAME_ALREADY_EXIST, null, null);
			else {
				ServiceMaster xServiceMaster = mapper.map(xServiceMasterDto, ServiceMaster.class,
						"billing_XServiceMasterDto_to_XServiceMaster");
				xServiceMaster = save(xServiceMaster);
				return new Response(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, null, xServiceMaster);
			}
		} catch (HibernateException he) {
			logger.error("Exection", he);
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	}

	@Override
	public Response changeOrganizationServiceMasterStatus(ServiceMasterDto xServiceMasterDto)
			throws ApplicationException {
		try {
			sessionFactory.getCurrentSession().getNamedQuery("CHANGE_GLOBAL_SERVICE_MASTER_STATUS")
					.setInteger("serviceMasterId", xServiceMasterDto.getServiceMasterId())
					.setInteger("organizationId", xServiceMasterDto.getOrganizationId())
					.setCharacter("status", xServiceMasterDto.getStatus()).executeUpdate();
			return new Response(SUCCESS, SUCCESS_CODE, COMMON_UPDATE, null, null);
		} catch (HibernateException he) {
			logger.error("Exection", he);
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	}

	@Override
	public Response listOrganizationServiceMasterMaster(Integer orgId, Integer offset, Integer recordPerPage)
			throws ApplicationException {
		try {
			List<ServiceMasterDto> listXServiceMasterDto = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_PAGINATED_SERVICE_MASTER_LIST").setInteger("orgId", orgId)
					.setFirstResult(offset != null ? offset : 0)
					.setMaxResults(recordPerPage != null ? recordPerPage : 10)
					.setResultTransformer(Transformers.aliasToBean(ServiceMasterDto.class)).list();
			return new Response<>(SUCCESS, SUCCESS_CODE, null, listXServiceMasterDto, null);
		} catch (HibernateException he) {
			logger.error("Exection", he);
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	}

	@Override
	public Response getOrganizationServiceMasterTotalCount(Integer orgId) throws ApplicationException {
		try {
			BigInteger serviceMasterCount = (BigInteger) sessionFactory.getCurrentSession()
					.getNamedQuery("GET_SERVICE_MASTER_COUNT").setInteger("orgId", orgId).uniqueResult();
			if (serviceMasterCount.compareTo(BigInteger.ZERO) == 1) {
				return new Response(SUCCESS, SUCCESS_CODE, null, null, serviceMasterCount);
			} else {
				return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, BigInteger.ZERO);
			}
		} catch (HibernateException he) {
			logger.error("Exection", he);
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	}

	@Override
	public Response getOrganizationServiceMasterById(ServiceMasterDto xServiceMasterDto) throws ApplicationException {
		try {
			xServiceMasterDto = (ServiceMasterDto) sessionFactory.getCurrentSession()
					.getNamedQuery("GET_SERVICE_MASTER_BY_ID")
					.setInteger("serviceMasterId", xServiceMasterDto.getServiceMasterId())
					.setInteger("orgId", xServiceMasterDto.getOrganizationId())
					.setResultTransformer(Transformers.aliasToBean(ServiceMasterDto.class)).uniqueResult();
			return new Response<>(SUCCESS, SUCCESS_CODE, null, null, xServiceMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	}

	@Override
	public Response updateOrganizationServiceMaster(ServiceMasterDto xServiceMasterDto) throws ApplicationException {
		try {
			// ----------- check if Service Name alreay exist ---------------//
			Long count = (Long) sessionFactory.getCurrentSession()
					.createQuery(
							"select count(*) from ServiceMaster mst where lower(mst.serviceStandardName) LIKE lower(CONCAT('%', :serviceStandardName, '%'))")
					.setString("serviceStandardName", xServiceMasterDto.getServiceStandardName()).uniqueResult();
			boolean exists = count > 0;
			if (exists)
				return new Response(ERROR, ALREADY_EXIST_CODE, SERVICE_NAME_ALREADY_EXIST, null, null);
			else {
				ServiceMaster xServiceMaster = mapper.map(xServiceMasterDto, ServiceMaster.class,
						"billing_XServiceMasterDto_to_XServiceMaster");
				xServiceMaster = update(xServiceMaster);
				return new Response(SUCCESS, SUCCESS_CODE, COMMON_UPDATE, null, xServiceMaster);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	}

	@Override
	public Response listServiceMasterWithBasePriceByVisitType(Integer orgId, Integer unitId, Integer visitTypeId,
			Integer offset, Integer recordPerPage) throws ApplicationException {
		try {
			List<ServicePriceDetailsDto> listXServiceMasterDto = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_PAGINATED_SERVICE_MASTER_LIST_WITH_BASEPRICE_BYVISITTYPE")
					.setInteger("orgId", orgId).setInteger("visitTypeId", visitTypeId)
					.setFirstResult(offset != null ? offset : 0)
					.setMaxResults(recordPerPage != null ? recordPerPage : 10)
					.setResultTransformer(Transformers.aliasToBean(ServicePriceDetailsDto.class)).list();
			return new Response<>(SUCCESS, SUCCESS_CODE, null, listXServiceMasterDto, null);
		} catch (HibernateException he) {
			logger.error("Exection", he);
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	}

	@Override
	public Response getOrganizationServiceMasterBySpecialityAndSubSpeciality(ServiceMasterDto xServiceMasterDto)
			throws ApplicationException {
		try {
			List<ServiceMasterDto> listXServiceMasterDto = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_SERVICE_MASTER_BY_SPECIALITY_AND_SUB_SPECIALITY")
					.setInteger("orgId", xServiceMasterDto.getOrganizationId())
					.setInteger("specialityId", xServiceMasterDto.getSpecialityId())
					.setInteger("subSpecialityId", xServiceMasterDto.getSubSpecialityId())
					.setResultTransformer(Transformers.aliasToBean(ServiceMasterDto.class)).list();
			List al = new ArrayList<>();
			for (Iterator itr = listXServiceMasterDto.iterator(); itr.hasNext();) 
			{
				ServiceMasterDto serviceMasterDto2 = (ServiceMasterDto) itr.next();
				al.add(serviceMasterDto2.getServiceMasterId());
			}
			Response res = checkServiceAvailableInUnitServiceMapper(al);
			List<Integer> al1 = new LinkedList();
			List<Integer> al2 = new LinkedList<>();
			if (res.getListObject().size() > 0) {
				al1 = res.getListObject();

				for (Iterator itr = al1.iterator(); itr.hasNext();) 
				{
					UnitServiceMapperDto unitServiceMapperDto = (UnitServiceMapperDto) itr.next();
					al2.add(unitServiceMapperDto.getServiceId());
				}
			}
			List<ServiceMasterDto> listXServiceMasterDto2 ;
			String getOutPatientQuery = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_SERVICE_MASTER_BY_SPECIALITY_AND_SUB_SPECIALITY_NOT_MAPPED").getQueryString()
					.toString();
			StringBuilder getOutPatientSearchQueryPart1 = new StringBuilder(getOutPatientQuery);

			if (al2.size() > 0) {
				
				String text =  al2.toString().replace("[", "").replace("]", "");
				
				getOutPatientSearchQueryPart1.append(" AND service.service_master_id NOT IN ("+text+")");
				  Query createdQuery=sessionFactory.getCurrentSession().createSQLQuery(getOutPatientSearchQueryPart1.toString());
				  
					listXServiceMasterDto2 = createdQuery
							.setInteger("orgId", xServiceMasterDto.getOrganizationId())
							.setInteger("specialityId", xServiceMasterDto.getSpecialityId())
							.setInteger("subSpecialityId", xServiceMasterDto.getSubSpecialityId())
							.setResultTransformer(Transformers.aliasToBean(ServiceMasterDto.class)).list();
			}
			else{
				Query createdQuery = sessionFactory.getCurrentSession()
						.createSQLQuery(getOutPatientSearchQueryPart1.toString());
				  
				 listXServiceMasterDto2 = createdQuery
						.setInteger("orgId", xServiceMasterDto.getOrganizationId())
						.setInteger("specialityId", xServiceMasterDto.getSpecialityId())
						.setInteger("subSpecialityId", xServiceMasterDto.getSubSpecialityId())
						.setResultTransformer(Transformers.aliasToBean(ServiceMasterDto.class)).list();
			}
			
			return new Response<>(SUCCESS, SUCCESS_CODE, null, listXServiceMasterDto2, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	}

	public Response checkServiceAvailableInUnitServiceMapper(List al) throws ApplicationException {
		try {
			List<UnitServiceMapperDto> serviceMasterDtolist = sessionFactory.getCurrentSession()
					.getNamedQuery("CHECK_SERVICE_AVAILABLE_IN_UNIT_SERVICE_MAPPER").setParameterList("serviceId", al)
					.setResultTransformer(Transformers.aliasToBean(UnitServiceMapperDto.class)).list();

			return new Response<>(SUCCESS, SUCCESS_CODE, null, serviceMasterDtolist, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	}

	

	@Override
	public Response listServiceMasterByServiceId(Integer orgId, Integer unitId, List<Integer> listServiceId)
			throws ApplicationException {
		// return list of services details to calling method 17-01-2018

		try {

			List<ServicePriceDetailsDto> listServiceMasterDto = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_SERVICE_DETAILS_WITH_BASEPRICE_BYSERVICELIST")
					.setParameterList("listServiceId", listServiceId).setParameter("orgId", orgId)
					.setResultTransformer(Transformers.aliasToBean(ServicePriceDetailsDto.class)).list();
			return new Response<>(SUCCESS, SUCCESS_CODE, null, listServiceMasterDto, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	}

	@Override
	public Response getServiceDetailsByServiceId(ServiceMasterDto serviceMasterDto) throws ApplicationException {
		try {
			serviceMasterDto = (ServiceMasterDto) sessionFactory.getCurrentSession()
					.getNamedQuery("GET_SERVICE_DETAILS_BY_ID")
					.setInteger("serviceId", serviceMasterDto.getServiceMasterId())
					.setInteger("unitId", serviceMasterDto.getUnitId())
					.setInteger("orgId", serviceMasterDto.getOrganizationId())
					.setResultTransformer(Transformers.aliasToBean(ServiceMasterDto.class)).uniqueResult();
			return new Response<>(SUCCESS, SUCCESS_CODE, null, null, serviceMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	}

}
