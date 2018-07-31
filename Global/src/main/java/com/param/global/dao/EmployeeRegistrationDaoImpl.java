package com.param.global.dao;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.param.global.common.GlobalCommonDateUtils;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dto.EmployeeRegistrationDto;
import com.param.global.model.EmployeeDetails;
import com.param.global.model.EmployeeMaster;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;
@Repository
@SuppressWarnings({ "unchecked", "rawtypes"})
public class EmployeeRegistrationDaoImpl extends GenericDao<EmployeeMaster, Integer>implements IEmployeeRegistrationDao,ICommonConstants{

	
	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
	DozerBeanMapper mapper;
	
	public EmployeeRegistrationDaoImpl() {
		super(EmployeeMaster.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Response saveEmployeeRegistration(EmployeeRegistrationDto employeeRegistrationDto) {
		try {
			EmployeeMaster employeeMaster = mapper.map(employeeRegistrationDto,EmployeeMaster.class,
					"EmployeeMasterDto_to_EmployeeMaster");
			employeeMaster=save(employeeMaster);
			Integer eId=employeeMaster.getEmployeeId();
			return new Response(SUCCESS, SUCCESS_CODE, null, null, eId);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response checkUniqueEmployee(EmployeeRegistrationDto employeeRegistrationDto) throws ApplicationException {
		try{
			List<EmployeeRegistrationDto> employeeRegistrationDtosList = null;
		if(employeeRegistrationDto.getEmployeeId()!=null)
			{
			StringBuilder finalQuery=new StringBuilder(sessionFactory.getCurrentSession()
						.getNamedQuery("CHECK_UNIQUE_EMPLOYEE").getQueryString());
			finalQuery.append(" AND et.employeeId!="+employeeRegistrationDto.getEmployeeId());	
				
			employeeRegistrationDtosList=sessionFactory.openSession().createQuery(finalQuery.toString())
					.setInteger("orgId", employeeRegistrationDto.getOrganizationId())
					.setInteger("unitId", employeeRegistrationDto.getUnitId())
					.setString("empCode", employeeRegistrationDto.getEmpCode())
					.setString("mobileNumber", employeeRegistrationDto.getMobileNumber())
					.setResultTransformer(Transformers.aliasToBean(EmployeeRegistrationDto.class)).list();
			}
		else{
			employeeRegistrationDtosList=sessionFactory.openSession().getNamedQuery("CHECK_UNIQUE_EMPLOYEE")
					.setInteger("orgId", employeeRegistrationDto.getOrganizationId())
					.setInteger("unitId", employeeRegistrationDto.getUnitId())
					.setString("empCode", employeeRegistrationDto.getEmpCode())
					.setString("mobileNumber", employeeRegistrationDto.getMobileNumber())
					.setResultTransformer(Transformers.aliasToBean(EmployeeRegistrationDto.class)).list();
		}
		return new Response(SUCCESS, SUCCESS_CODE, null, employeeRegistrationDtosList, null);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR, COMMON_ERROR_MESSAGE, null, null);
		
	}

	@Override
	public Response saveEmployeeDetails(EmployeeRegistrationDto employeeRegistrationDto) throws ApplicationException {
		try {
			EmployeeDetails employeeDetails = mapper.map(employeeRegistrationDto,EmployeeDetails.class,
					"EmployeeMasterDto_to_EmployeeDetails");
			Integer employeeDetailsId=(Integer) sessionFactory.getCurrentSession().save(employeeDetails);
			employeeRegistrationDto.setEmployeeDetailsId(employeeDetailsId);
			return new Response(SUCCESS, SUCCESS_CODE, null, null, null);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR, COMMON_ERROR_MESSAGE, null, employeeRegistrationDto);
	}

	@Override
	public Response getEmployeeDetails(EmployeeRegistrationDto employeeRegistrationDto) throws ApplicationException {
		try{
			
			List<EmployeeRegistrationDto> employeeRegistrationDtosList=sessionFactory.openSession().getNamedQuery("GET_EMPLOYEE_DETAILS")
					.setInteger("organizationId", employeeRegistrationDto.getOrganizationId())
					.setInteger("unitId", employeeRegistrationDto.getUnitId())
					.setResultTransformer(Transformers.aliasToBean(EmployeeRegistrationDto.class)).list();
			return new Response(SUCCESS, SUCCESS_CODE, null, employeeRegistrationDtosList, null);
			}catch (Exception e) {
				e.printStackTrace();
			}
			return new Response(ERROR, COMMON_ERROR, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response updateEmployeeDetails(EmployeeRegistrationDto employeeRegistrationDto) throws ApplicationException {
		try{
			Session session=sessionFactory.getCurrentSession();
				EmployeeMaster employeeMaster = mapper.map(employeeRegistrationDto,EmployeeMaster.class,
						"EmployeeMasterDto_to_EmployeeMaster");
				session.update(employeeMaster);
				
				EmployeeDetails employeeDetails = mapper.map(employeeRegistrationDto,EmployeeDetails.class,
						"EmployeeMasterDto_to_EmployeeDetails");
				session.update(employeeDetails);
			return new Response(SUCCESS, null, COMMON_UPDATE, null, null);
			
			} catch (HibernateException he) {
				he.printStackTrace();

			} catch (Exception e) {
				e.printStackTrace();
			}
			return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response updateEmployeeStatus(EmployeeRegistrationDto employeeRegistrationDto) throws ApplicationException {
		try{
			sessionFactory.getCurrentSession().createQuery("UPDATE EmployeeMaster emp set emp.status='I', "
					+ "emp.updatedBy=:updatedBy, "
					+ "emp.updatedDate=:updatedDate "
					+ "WHERE emp.employeeId=:employeeId "
					+ "AND emp.status='A' ")
			.setInteger("updatedBy", employeeRegistrationDto.getUpdatedBy())
			.setTimestamp("updatedDate", GlobalCommonDateUtils.getDate(employeeRegistrationDto.getUpdatedDate(), "yyyy-M-dd"))
			.setInteger("employeeId", employeeRegistrationDto.getEmployeeId())
			.executeUpdate();
			
			sessionFactory.getCurrentSession().createQuery("UPDATE EmployeeDetails emp set emp.status='I', "
					+ "emp.updatedBy=:updatedBy, "
					+ "emp.updatedDate=:updatedDate "
					+ "WHERE emp.employeeId=:employeeId "
					+ "AND emp.status='A' ")
			.setInteger("updatedBy", employeeRegistrationDto.getUpdatedBy())
			.setTimestamp("updatedDate", GlobalCommonDateUtils.getDate(employeeRegistrationDto.getUpdatedDate(), "yyyy-M-dd"))
			.setInteger("employeeId", employeeRegistrationDto.getEmployeeId())
			.executeUpdate();
			
			sessionFactory.getCurrentSession().createQuery("UPDATE DependentDetails depDet set depDet.status='I', "
					+ "depDet.updatedBy=:updatedBy, "
					+ "depDet.updatedDate=:updatedDate "
					+ "WHERE depDet.empDocId=:employeeId "
					+ "AND depDet.typeId=:typeId "
					+ "AND depDet.status='A' ")
			.setInteger("updatedBy", employeeRegistrationDto.getUpdatedBy())
			.setTimestamp("updatedDate", GlobalCommonDateUtils.getDate(employeeRegistrationDto.getUpdatedDate(), "yyyy-M-dd"))
			.setInteger("employeeId", employeeRegistrationDto.getEmployeeId())
			.setInteger("typeId", employeeRegistrationDto.getTypeId())
			.executeUpdate();
			
			return new Response(SUCCESS, null, COMMON_UPDATE, null, null);
			
			} catch (HibernateException he) {
				he.printStackTrace();

			} catch (Exception e) {
				e.printStackTrace();
			}
			return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response searchEmployeeByCriteria(EmployeeRegistrationDto employeeRegistrationDto)
			throws ApplicationException {
		try {

			StringBuilder rawQuery = new StringBuilder(sessionFactory.openSession()
					.getNamedQuery("GET_EMPLOYEE_DETAILS").getQueryString());
			
			if(employeeRegistrationDto.getEmployeeName()!=null && !employeeRegistrationDto.getEmployeeName().isEmpty())
			{
				rawQuery.append(" AND lower(concat(coalesce(employee.firstName),coalesce(employee.lastName))) LIKE concat('%',LOWER('"+employeeRegistrationDto.getEmployeeName()+"'),'%') ");
			}if(employeeRegistrationDto.getEmpCategoryId()>0)
			{
				rawQuery.append(" AND employee.empCategoryId="+employeeRegistrationDto.getEmpCategoryId());
			}if(employeeRegistrationDto.getEmpDesignationId()>0)
			{
				rawQuery.append(" AND employee.empDesignationId="+employeeRegistrationDto.getEmpDesignationId());
			}if(employeeRegistrationDto.getSpecialityId()>0)
			{
				rawQuery.append(" AND employee.specialityId="+employeeRegistrationDto.getSpecialityId());
			}if(employeeRegistrationDto.getEmpCode()!=null && !employeeRegistrationDto.getEmpCode().isEmpty())
			{
				rawQuery.append(" AND employee.empCode='"+employeeRegistrationDto.getEmpCode()+"' ");
			}if(employeeRegistrationDto.getIdentificationTypeId()>0)
			{
				rawQuery.append(" AND employee.identificationTypeId="+employeeRegistrationDto.getIdentificationTypeId());
			}if(employeeRegistrationDto.getIdentificationNumber()!=null && !employeeRegistrationDto.getIdentificationNumber().isEmpty())
			{
				rawQuery.append(" AND employee.identificationNumber='"+employeeRegistrationDto.getIdentificationNumber()+"' ");
			}
			
			List<EmployeeRegistrationDto> employeeRegistrationDtosList = sessionFactory.openSession()
					.createQuery(rawQuery.toString())
					.setInteger("organizationId", employeeRegistrationDto.getOrganizationId())
					.setInteger("unitId", employeeRegistrationDto.getUnitId())
					.setResultTransformer(Transformers.aliasToBean(EmployeeRegistrationDto.class)).list();
			return new Response(SUCCESS, SUCCESS_CODE, null, employeeRegistrationDtosList, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR, COMMON_ERROR_MESSAGE, null, null);
	}

}
