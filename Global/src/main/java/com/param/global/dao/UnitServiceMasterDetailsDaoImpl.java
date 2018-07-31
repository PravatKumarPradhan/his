package com.param.global.dao;

import java.math.BigInteger;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dto.UnitServiceMasterDetailsDto;
import com.param.global.model.UnitServiceMasterDetails;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings({ "rawtypes", "unchecked" })
public class UnitServiceMasterDetailsDaoImpl extends GenericDao<UnitServiceMasterDetails, Integer> implements IUnitServiceMasterDetailsDao,ICommonConstants{

	public UnitServiceMasterDetailsDaoImpl() {
		super(UnitServiceMasterDetails.class);
	}
	

	@Autowired
	private DozerBeanMapper mapper;
	
	@Override
	public Response saveUnitServiceMasterDetails(UnitServiceMasterDetailsDto unitServiceMasterDetailsDto)
			throws ApplicationException {
		/*try {
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT COUNT(*) FROM UnitServiceMasterDetails service WHERE service.serviceMasterId = :serviceMasterId AND service.unitId =:unitId AND service.organizationId =:orgId");
			Long count = (Long) sessionFactory.getCurrentSession().createQuery(sb.toString())
					.setInteger("serviceMasterId", unitServiceMasterDetailsDto.getServiceMasterId())
					.setInteger("unitId", unitServiceMasterDetailsDto.getUnitId())
					.setInteger("orgId", unitServiceMasterDetailsDto.getOrganizationId())
					.uniqueResult();
			boolean exist = count > 0;
			if(exist) {
				return new Response(ERROR, ALREADY_EXIST_CODE, ALREADY_EXIST, null, null);
			}else {
				UnitServiceMasterDetails unitServiceMasterDetails = mapper.map(unitServiceMasterDetailsDto, UnitServiceMasterDetails.class, "global_UnitServiceMasterDetailsDto_to_UnitServiceMasterDetails"); 
				save(unitServiceMasterDetails);
				return new Response(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, null, null);
			}
		}catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} */
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getPaginatedUnitServiceMasterDetailsList(UnitServiceMasterDetailsDto unitServiceMasterDetailsDto)
			throws ApplicationException {
		try {
			List<UnitServiceMasterDetailsDto> listUnitServiceMasterDetailsDto = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_PAGINATED_UNIT_SERVICE_MASTER_DETAILS_LIST")
					.setInteger("orgId", unitServiceMasterDetailsDto.getOrganizationId())
					.setInteger("unitId", unitServiceMasterDetailsDto.getUnitId())
					.setFirstResult(unitServiceMasterDetailsDto.getOffset() != null ? unitServiceMasterDetailsDto.getOffset() : 0)
					.setMaxResults(unitServiceMasterDetailsDto.getRecordPerPage() != null ? unitServiceMasterDetailsDto.getRecordPerPage() : 10)
					.setResultTransformer(Transformers.aliasToBean(UnitServiceMasterDetailsDto.class)).list();
			return new Response(SUCCESS, SUCCESS_CODE, null, listUnitServiceMasterDetailsDto, null);
		}catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getUnitServiceMasterDetailsCount(UnitServiceMasterDetailsDto unitServiceMasterDetailsDto)
			throws ApplicationException {
		try {
			BigInteger UnitServiceMasterDetailsCount = (BigInteger) sessionFactory.getCurrentSession()
					.getNamedQuery("GET_UNIT_SERVICE_MASTER_DETAILS_COUNT")
					.setInteger("orgId", unitServiceMasterDetailsDto.getOrganizationId())
					.setInteger("unitId", unitServiceMasterDetailsDto.getUnitId())
					.uniqueResult();
			if (UnitServiceMasterDetailsCount.compareTo(BigInteger.ZERO) == 1)
			{
				return new Response(SUCCESS, SUCCESS_CODE, null, null, UnitServiceMasterDetailsCount);
			} else
			{
				return new Response(ERROR, null, null, null, BigInteger.ZERO);
			}
		}catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getUnitServiceMasterDetailsById(UnitServiceMasterDetailsDto unitServiceMasterDetailsDto)
			throws ApplicationException {
		try {
			 unitServiceMasterDetailsDto = (UnitServiceMasterDetailsDto) sessionFactory.getCurrentSession()
					.getNamedQuery("GET_SERVICE_MASTER_DETAILS_BY_ID")
					.setInteger("unitServiceMasterDetailsId", unitServiceMasterDetailsDto.getUnitServiceMasterDetailsId())
					.setResultTransformer(Transformers.aliasToBean(UnitServiceMasterDetailsDto.class)).uniqueResult();;
			return new Response<>(SUCCESS, SUCCESS_CODE, null, null,unitServiceMasterDetailsDto);
		}catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response updateUnitServiceMasterDetails(UnitServiceMasterDetailsDto unitServiceMasterDetailsDto)
			throws ApplicationException {
		try {
			UnitServiceMasterDetails unitServiceMasterDetails = mapper.map(unitServiceMasterDetailsDto, UnitServiceMasterDetails.class, "global_UnitServiceMasterDetailsDto_to_UnitServiceMasterDetails"); 
			update(unitServiceMasterDetails);
			return new Response(SUCCESS, SUCCESS_CODE, COMMON_UPDATE, null, null);
		}catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getAllActiveTax(Integer unitId) throws ApplicationException {
		try {
			List<UnitServiceMasterDetailsDto> listUnitServiceMasterDetailsDto = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_ALL_ACTIVE_TAX")
					.setInteger("unitId", unitId)					
					.setResultTransformer(Transformers.aliasToBean(UnitServiceMasterDetailsDto.class)).list();
			return new Response(SUCCESS, SUCCESS_CODE, null, listUnitServiceMasterDetailsDto, null);
		}catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	
	
}
