package com.param.global.dao;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.global.common.GlobalCommonDateUtils;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dto.DependentDetailsDto;
import com.param.global.dto.PatientRegistrationDto;
import com.param.global.model.DependentDetails;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Service
@SuppressWarnings({ "rawtypes", "unchecked" })
public class DependentDetailsDaoImpl extends GenericDao<DependentDetails, Integer>
		implements IDependentDetailsDao, ICommonConstants {

	public DependentDetailsDaoImpl() {
		super(DependentDetails.class);
	}

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	DozerBeanMapper mapper;

	@Override
	public Response saveDependentDetails(DependentDetailsDto detailsDto) throws ApplicationException {
		try {
			DependentDetails dependentDetails = mapper.map(detailsDto, DependentDetails.class,
					"DependentDetailsDto_to_DependentDetails");
			sessionFactory.getCurrentSession().save(dependentDetails);
			return new Response(SUCCESS, SUCCESS_CODE, COMMON_SUCCESS, null, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getDependentDetails(DependentDetailsDto dependentDetailsDto) throws ApplicationException {
		try {
			List<DependentDetailsDto> dependentDetailsDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_DEPENDENT_DETAILS")
					.setInteger("empDocId", dependentDetailsDto.getEmpDocId())
					.setInteger("typeId",dependentDetailsDto.getTypeId())
					.setResultTransformer(Transformers.aliasToBean(DependentDetailsDto.class)).list();
			return new Response(SUCCESS, null, null, dependentDetailsDtosList, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateDependentDetails(DependentDetailsDto dependentDetailsDtoNew) throws ApplicationException {
		try {
			DependentDetails dependentDetails = mapper.map(dependentDetailsDtoNew, DependentDetails.class,
					"DependentDetailsDto_to_DependentDetails");
			sessionFactory.getCurrentSession().update(dependentDetails);
			return new Response(SUCCESS, SUCCESS_CODE, COMMON_SUCCESS, null, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response inactivePreviousDependents(DependentDetailsDto dependentDetailsDto) throws ApplicationException {
		try {
			
			sessionFactory.getCurrentSession().createQuery("UPDATE DependentDetails depDet set depDet.status='I', "
					+ "depDet.updatedBy=:updatedBy, "
					+ "depDet.updatedDate=:updatedDate "
					+ "WHERE depDet.typeId=:typeId "
					+ "AND depDet.empDocId=:empDocId "
					+ "AND depDet.status='A' ")
			.setInteger("updatedBy", dependentDetailsDto.getUpdatedBy())
			.setTimestamp("updatedDate", GlobalCommonDateUtils.getDate(dependentDetailsDto.getUpdatedDate(), "yyyy-M-dd"))
			.setInteger("typeId", dependentDetailsDto.getTypeId())
			.setInteger("empDocId",dependentDetailsDto.getEmpDocId())
			.executeUpdate();
			
			return new Response(SUCCESS, null, null, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response searchDependentDetailsOfDoctor(DependentDetailsDto dependentDetailsDto)
			throws ApplicationException {
		try {
			StringBuilder query = new StringBuilder(sessionFactory.getCurrentSession()
					.getNamedQuery("GET_DOCTORS_DEPENDENT_DETAILS").getQueryString());
			
			
			if(dependentDetailsDto.getEmployeeName()!=null && !dependentDetailsDto.getEmployeeName().isEmpty())
			{
				query.append(" AND lower(concat(coalesce(doctor.first_name),coalesce(doctor.last_name))) LIKE concat('%',LOWER('"+dependentDetailsDto.getEmployeeName()+"'),'%') ");
			}if(dependentDetailsDto.getEmpCode()!=null && !dependentDetailsDto.getEmpCode().isEmpty())
			{
				query.append(" AND doctor.doc_code='"+dependentDetailsDto.getEmpCode()+"' ");
			}if(dependentDetailsDto.getIdentificationTypeId()>0)
			{
				query.append(" AND depandents.identification_type_id="+dependentDetailsDto.getIdentificationTypeId());
			}if(dependentDetailsDto.getIdentificationNumber()!=null && !dependentDetailsDto.getIdentificationNumber().isEmpty())
			{
				query.append(" AND depandents.identification_number='"+dependentDetailsDto.getIdentificationNumber()+"' ");
			}if(dependentDetailsDto.getDependentName()!=null && !dependentDetailsDto.getDependentName().isEmpty())
			{
				query.append(" AND lower(concat(coalesce(depandents.first_name),coalesce(depandents.last_name))) LIKE concat('%',LOWER('"+dependentDetailsDto.getDependentName()+"'),'%') ");
			}if(dependentDetailsDto.getTypeId()!=null)
			{
				query.append(" AND depandents.type_id="+dependentDetailsDto.getTypeId()+" ");
			}
			
			
			List<DependentDetailsDto> dependentDetailsDtosList = sessionFactory.getCurrentSession()
					.createSQLQuery(query.toString())
					.setInteger("organizationId", dependentDetailsDto.getOrganizationId())
					.setInteger("unitId",dependentDetailsDto.getUnitId())
					.setResultTransformer(Transformers.aliasToBean(PatientRegistrationDto.class)).list();
			return new Response(SUCCESS, null, null, dependentDetailsDtosList, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response searchDependentDetailsOfEmployee(DependentDetailsDto dependentDetailsDto)
			throws ApplicationException {
		try {
			StringBuilder query = new StringBuilder(sessionFactory.getCurrentSession()
					.getNamedQuery("GET_EMPLOYEES_DEPENDENT_DETAILS").getQueryString());
			
			
			if(dependentDetailsDto.getEmployeeName()!=null && !dependentDetailsDto.getEmployeeName().isEmpty())
			{
				query.append(" AND lower(concat(coalesce(employee.first_name),coalesce(employee.last_name))) LIKE concat('%',LOWER('"+dependentDetailsDto.getEmployeeName()+"'),'%') ");
			}if(dependentDetailsDto.getEmpCode()!=null && !dependentDetailsDto.getEmpCode().isEmpty())
			{
				query.append(" AND employee.doc_code='"+dependentDetailsDto.getEmpCode()+"' ");
			}if(dependentDetailsDto.getIdentificationTypeId()>0)
			{
				query.append(" AND depandents.identification_type_id="+dependentDetailsDto.getIdentificationTypeId());
			}if(dependentDetailsDto.getIdentificationNumber()!=null && !dependentDetailsDto.getIdentificationNumber().isEmpty())
			{
				query.append(" AND depandents.identification_number='"+dependentDetailsDto.getIdentificationNumber()+"' ");
			}if(dependentDetailsDto.getDependentName()!=null && !dependentDetailsDto.getDependentName().isEmpty())
			{
				query.append(" AND lower(concat(coalesce(depandents.first_name),coalesce(depandents.last_name))) LIKE concat('%',LOWER('"+dependentDetailsDto.getDependentName()+"'),'%') ");
			}if(dependentDetailsDto.getTypeId()!=null)
			{
				query.append(" AND depandents.type_id="+dependentDetailsDto.getTypeId()+" ");
			}
			
			
			List<DependentDetailsDto> dependentDetailsDtosList = sessionFactory.getCurrentSession()
					.createSQLQuery(query.toString())
					.setInteger("organizationId", dependentDetailsDto.getOrganizationId())
					.setInteger("unitId",dependentDetailsDto.getUnitId())
					.setResultTransformer(Transformers.aliasToBean(PatientRegistrationDto.class)).list();
			return new Response(SUCCESS, null, null, dependentDetailsDtosList, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}
}
 