package com.param.global.dao;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.param.global.common.GlobalCommonDateUtils;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dto.ServiceMasterDto;
import com.param.global.dto.ServiceSearchReqDto;
import com.param.global.dto.ServiceSearchResDto;
import com.param.global.dto.UnitServiceMapperDto;
import com.param.global.model.UnitServiceMapper;
import com.param.global.model.UnitServiceMasterDetails;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings({ "unchecked", "rawtypes" })
public class UnitServiceMapperDaoImpl extends GenericDao<UnitServiceMapper, Integer> implements IUnitServiceMapperDao, ICommonConstants{

	public UnitServiceMapperDaoImpl() {
		super(UnitServiceMapper.class);
	}

	@Autowired
	private DozerBeanMapper mapper;
	
	@Autowired
	private SessionFactory sessionFactory; 
	
	@Override
	public Response saveUnitServiceMapper(UnitServiceMapperDto unitServiceMapperDto) throws ApplicationException {
		try {
			UnitServiceMapper unitServiceMapper = mapper.map(unitServiceMapperDto, UnitServiceMapper.class, "global_UnitServiceMapperDto_to_UnitServiceMapper");
			save(unitServiceMapper);
			UnitServiceMasterDetails unitServiceMasterDetails=new UnitServiceMasterDetails();
			unitServiceMasterDetails.setServiceMasterId(unitServiceMapperDto.getServiceId());
			unitServiceMasterDetails.setIsDiscount('N');
			unitServiceMasterDetails.setIsOutsource('N');
			unitServiceMasterDetails.setIsPackage('N');
			unitServiceMasterDetails.setIsPanel('N');
			unitServiceMasterDetails.setIsQuantityEditable('N');
			unitServiceMasterDetails.setIsAutoRender('N');
			unitServiceMasterDetails.setIsRateEditable('N');
			unitServiceMasterDetails.setIsRefDoctorShare('N');
			unitServiceMasterDetails.setIsTaxApplicable('N');
			unitServiceMasterDetails.setIsBedSide('N');
			unitServiceMasterDetails.setIsDocReq('N');
			unitServiceMasterDetails.setIsPeriodicity('N');
			unitServiceMasterDetails.setIsPoc('N');
			unitServiceMasterDetails.setIsProcedure('N');
			unitServiceMasterDetails.setStatus('A');
			unitServiceMasterDetails.setCreatedBy(unitServiceMapperDto.getCreatedBy());
			unitServiceMasterDetails.setUpdatedBy(unitServiceMapperDto.getUpdatedBy());
			unitServiceMasterDetails.setCreatedDate(unitServiceMapperDto.getCreatedDate());
			unitServiceMasterDetails.setUpdatedDate(unitServiceMapperDto.getUpdatedDate());
			unitServiceMasterDetails.setUnitId(unitServiceMapperDto.getUnitId());
			unitServiceMasterDetails.setOrganizationId(unitServiceMapperDto.getOrgnisationId());
			sessionFactory.getCurrentSession().save(unitServiceMasterDetails);
			return new Response(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, null, null);
		}catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR, COMMON_ERROR_MESSAGE, null, null);
	}
	@Override
	public Response searchServiceMasterBySpecialitySubSplecialityUnitAndOrg(String searchKeyword, Integer specialityId, Integer subSpecialityId, Integer unitId, Integer orgId)
			throws ApplicationException {
		try {
				List<UnitServiceMapperDto> listUnitServiceMapperDto = sessionFactory.getCurrentSession()
						.getNamedQuery("SEARCH_SERVICE_MASTER_BY_UNIT_AND_ORG")
						.setInteger("unitId", unitId)
						.setInteger("orgId", orgId)
						.setInteger("specialityId", specialityId)
						.setInteger("subSpecialityId", subSpecialityId)
						.setString("searchKeyword", "%"+ searchKeyword.trim().toLowerCase()+"%")
						.setResultTransformer(Transformers.aliasToBean(ServiceMasterDto.class))
						.list();
				return new Response<>(SUCCESS, SUCCESS_CODE, null, listUnitServiceMapperDto, null);
		}catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR, COMMON_ERROR_MESSAGE, null, null);
	}
	@Override
	public Response getMappedServicesListByUnit(UnitServiceMapperDto unitServiceMapperDto) throws ApplicationException {
		try {
				List<UnitServiceMapperDto> listUnitServiceMapperDto = sessionFactory.getCurrentSession()
						.getNamedQuery("GET_MAPPED_SERVICES_BY_UNIT")
						.setInteger("unitId", unitServiceMapperDto.getUnitId())
						.setInteger("orgId", unitServiceMapperDto.getOrgnisationId())
						.setFirstResult(unitServiceMapperDto.getOffset() != null ? unitServiceMapperDto.getOffset() : 0)
						.setMaxResults(unitServiceMapperDto.getRecordPerPage() != null ? unitServiceMapperDto.getRecordPerPage() : 10)
						.setResultTransformer(Transformers.aliasToBean(UnitServiceMapperDto.class))
						.list();
				return new Response<>(SUCCESS, SUCCESS_CODE, null, listUnitServiceMapperDto, null);
		}catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR, COMMON_ERROR_MESSAGE, null, null);
	}
	@Override
	public Response updateUnitServiceMapperStatus(UnitServiceMapperDto unitServiceMapperDto)
			throws ApplicationException {
		try {
				sessionFactory.getCurrentSession().getNamedQuery("UPDATE_UNIT_SERVICE_MAPPER_STATUS")
					.setCharacter("status", unitServiceMapperDto.getStatus())
					.setInteger("unitId", unitServiceMapperDto.getUnitId())
					.setInteger("serviceId", unitServiceMapperDto.getServiceId())
					.setInteger("orgId", unitServiceMapperDto.getOrgnisationId())
					.executeUpdate();
				return new Response<>(SUCCESS, SUCCESS_CODE, COMMON_UPDATE, null, null);
		}catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR, COMMON_ERROR_MESSAGE, null, null);
	}
	@Override
	public Response getCountOfMappedServicesListByUnit(UnitServiceMapperDto unitServiceMapperDto)
			throws ApplicationException {
		try {
			BigInteger count = (BigInteger) sessionFactory.getCurrentSession().getNamedQuery("GET_COUNT OF_MAPPED_SERVICES_BY_UNIT")
						.setInteger("unitId", unitServiceMapperDto.getUnitId())
						.setInteger("orgId", unitServiceMapperDto.getOrgnisationId())
						.uniqueResult();
			if (count.compareTo(BigInteger.ZERO) == 1)
			{
				return new Response(SUCCESS, SUCCESS_CODE, null, null, count);
			} else
			{
				return new Response(ERROR, null, null, null, BigInteger.ZERO);
			}
		}catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR, COMMON_ERROR_MESSAGE, null, null);
	}
	@Override
	public Response searchServicesByNameAndCode(ServiceMasterDto serviceMasterDto) throws ApplicationException {
		try {
			List<ServiceSearchResDto> listServiceMasterDto = sessionFactory.getCurrentSession()
					.getNamedQuery("SEARCH_SERVICES_BY_NAME_AND_CODE")
					.setString("searchKeyword", "%"+serviceMasterDto.getSearchKeyword().toLowerCase()+"%")
					//.setString("serviceCode", serviceMasterDto.getServiceStandardCode())
					.setInteger("unitId", serviceMasterDto.getUnitId())
					.setInteger("orgId", serviceMasterDto.getOrganizationId())
					.setResultTransformer(Transformers.aliasToBean(ServiceSearchResDto.class)).list();
			return new Response(SUCCESS, SUCCESS_CODE, null, listServiceMasterDto, null);
		}catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR, COMMON_ERROR_MESSAGE, null, null);
	}
	@Override
	public Response searchUnitServiceByMultipleSpecialityAndSubSpeciality(UnitServiceMapperDto unitServiceMapperDto)
			throws ApplicationException {
		try {
			List<UnitServiceMapperDto> listUnitServiceMapperDtoList = null;
			String roughQuery= sessionFactory.getCurrentSession().getNamedQuery("SEARCH_SERVICE_MASTER_BY_UNIT_AND_ORG_SPECIALITY_SUBSPECILAITY_LIST").getQueryString();
			StringBuilder stringBuilderQuery = new StringBuilder(roughQuery);
			
			
			if(unitServiceMapperDto.getServiceIdList()!=null && unitServiceMapperDto.getServiceIdList().size()>0)
			{
				stringBuilderQuery.append(" AND service.service_master_id IN (:serviceIdList) ");
				listUnitServiceMapperDtoList = sessionFactory.getCurrentSession().createSQLQuery(stringBuilderQuery.toString())
						.setInteger("unitId", unitServiceMapperDto.getUnitId())
						.setInteger("orgId", unitServiceMapperDto.getOrgnisationId())
						.setParameterList("serviceIdList", unitServiceMapperDto.getServiceIdList())
						.setResultTransformer(Transformers.aliasToBean(UnitServiceMapperDto.class))
						.list();
				
			}else if(unitServiceMapperDto.getSubSpecialityIdList()!=null && unitServiceMapperDto.getSubSpecialityIdList().size()>0)
			{
				stringBuilderQuery.append(" AND service.sub_speciality_id IN (:subSpecialityIdList) ");
				listUnitServiceMapperDtoList = sessionFactory.getCurrentSession().createSQLQuery(stringBuilderQuery.toString())
						.setInteger("unitId", unitServiceMapperDto.getUnitId())
						.setInteger("orgId", unitServiceMapperDto.getOrgnisationId())
						.setParameterList("subSpecialityIdList", unitServiceMapperDto.getSubSpecialityIdList())
						.setResultTransformer(Transformers.aliasToBean(UnitServiceMapperDto.class))
						.list();
			}else if(unitServiceMapperDto.getSpecialityIdList()!=null && unitServiceMapperDto.getSpecialityIdList().size()>0 )
			{
				stringBuilderQuery.append(" AND service.speciality_id IN (:specialityIdList) ");
				
				listUnitServiceMapperDtoList = sessionFactory.getCurrentSession().createSQLQuery(stringBuilderQuery.toString())
						.setInteger("unitId", unitServiceMapperDto.getUnitId())
						.setInteger("orgId", unitServiceMapperDto.getOrgnisationId())
						.setParameterList("specialityIdList", unitServiceMapperDto.getSpecialityIdList())
						.setResultTransformer(Transformers.aliasToBean(UnitServiceMapperDto.class))
						.list();
			}else{
				return new Response<>(SUCCESS, SUCCESS_CODE, "Select atleast Department OR Sub-department OR Services...!!", null, null);
			}
			
			
			return new Response<>(SUCCESS, SUCCESS_CODE, null, listUnitServiceMapperDtoList, null);
	}catch (HibernateException he) {
		he.printStackTrace();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return new Response(ERROR, COMMON_ERROR, COMMON_ERROR_MESSAGE, null, null);
	}
	@Override
	public Response searchActiveServices(ServiceSearchReqDto serviceSearchReqDto)
			throws ApplicationException {
		try {
			
			List<ServiceSearchResDto> listServiceMasterDto = null;
			String str = sessionFactory.getCurrentSession()
					.getNamedQuery("SEARCH_SERVICES_BY_NAME_AND_CODE").getQueryString();
			StringBuffer query = new StringBuffer(str);
			
			
			if(serviceSearchReqDto.getSubGroupId() !=null && serviceSearchReqDto.getGroupId() != null){
				query.append(" AND  service.speciality_id =:specialityId ");
				query.append(" AND  service.sub_speciality_id =:subSpecialityId ");
				listServiceMasterDto = sessionFactory.getCurrentSession().createSQLQuery(query.toString())
						.setString("searchKeyword", "%"+serviceSearchReqDto.getServiceName().toLowerCase()+"%")
						//.setString("serviceCode", serviceMasterDto.getServiceStandardCode())
						.setInteger("unitId", serviceSearchReqDto.getUnitId())
						.setInteger("orgId", serviceSearchReqDto.getOrganisationId())
						.setInteger("specialityId", serviceSearchReqDto.getSubGroupId())
						.setInteger("subSpecialityId", serviceSearchReqDto.getSubGroupId())
						.setResultTransformer(Transformers.aliasToBean(ServiceSearchResDto.class))
						.list();;
			}else if(serviceSearchReqDto.getGroupId() != null){
				query.append(" AND  service.speciality_id =:specialityId ");
				listServiceMasterDto = sessionFactory.getCurrentSession().createSQLQuery(query.toString())
						.setString("searchKeyword", "%"+serviceSearchReqDto.getServiceName().toLowerCase()+"%")
						//.setString("serviceCode", serviceMasterDto.getServiceStandardCode())
						.setInteger("unitId", serviceSearchReqDto.getUnitId())
						.setInteger("orgId", serviceSearchReqDto.getOrganisationId())
						.setInteger("specialityId", serviceSearchReqDto.getGroupId())
						.setResultTransformer(Transformers.aliasToBean(ServiceSearchResDto.class))
						.list();
			}else if(serviceSearchReqDto.getSubGroupId() !=null ){
				query.append(" AND  service.sub_speciality_id =:subSpecialityId ");
				listServiceMasterDto = sessionFactory.getCurrentSession().createSQLQuery(query.toString())
						.setString("searchKeyword", "%"+serviceSearchReqDto.getServiceName().toLowerCase()+"%")
						//.setString("serviceCode", serviceMasterDto.getServiceStandardCode())
						.setInteger("unitId", serviceSearchReqDto.getUnitId())
						.setInteger("orgId", serviceSearchReqDto.getOrganisationId())
						.setInteger("subSpecialityId", serviceSearchReqDto.getSubGroupId())
						.setResultTransformer(Transformers.aliasToBean(ServiceSearchResDto.class))
						.list();;
			}else{
				listServiceMasterDto = sessionFactory.getCurrentSession().createSQLQuery(query.toString())
						.setString("searchKeyword", "%"+serviceSearchReqDto.getServiceName().toLowerCase()+"%")
						//.setString("serviceCode", serviceMasterDto.getServiceStandardCode())
						.setInteger("unitId", serviceSearchReqDto.getUnitId())
						.setInteger("orgId", serviceSearchReqDto.getOrganisationId())
						.setResultTransformer(Transformers.aliasToBean(ServiceSearchResDto.class))
						.list();
			}
			
			return new Response(SUCCESS, SUCCESS_CODE, null, listServiceMasterDto, null);
		}catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR, COMMON_ERROR_MESSAGE, null, null);
	}

}
