package com.param.global.billing.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.param.global.billing.dto.EmployeeCategoryMasterDto;
import com.param.global.common.GlobalCommonDateUtils;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.model.EmployeeCategoryMaster;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings({ "rawtypes", "unchecked" })
public class EmployeeCategoryMasterDaoImpl extends GenericDao<EmployeeCategoryMaster, Integer>
		implements IEmployeeCategoryMasterDao, ICommonConstants {

	@Autowired
	SessionFactory sessionFactory;

	public EmployeeCategoryMasterDaoImpl() {
		super(EmployeeCategoryMaster.class);
	}

	@Override
	public Response saveEmployeeCategoryMaster(EmployeeCategoryMasterDto employeeCategoryMasterDto)
			throws ApplicationException {
		try {
			EmployeeCategoryMaster employeeCategoryMaster = new EmployeeCategoryMaster();
			employeeCategoryMaster.setCreatedBy(employeeCategoryMasterDto.getCreatedBy());
			employeeCategoryMaster.setCreatedDate(
					GlobalCommonDateUtils.getDate(employeeCategoryMasterDto.getCreatedDate(), "dd-M-yyyy HH:mm:ss"));
			employeeCategoryMaster.setEmployeeCategoryCode(employeeCategoryMasterDto.getEmployeeCategoryCode());
			employeeCategoryMaster
					.setEmployeeCategoryDescription(employeeCategoryMasterDto.getEmployeeCategoryDescription());
			employeeCategoryMaster.setOrganizationId(employeeCategoryMasterDto.getOrganizationId());
			employeeCategoryMaster.setStatus(employeeCategoryMasterDto.getStatus());
			employeeCategoryMaster.setUpdatedBy(employeeCategoryMasterDto.getUpdatedBy());
			employeeCategoryMaster.setUpdatedDate(
					GlobalCommonDateUtils.getDate(employeeCategoryMasterDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss"));
			save(employeeCategoryMaster);
			return new Response<>(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, null, null, null);
	}

	@Override
	public Response getEmployeeCategoryMasterById(int employeeId, int orgId) throws ApplicationException {
		try {
			List<EmployeeCategoryMasterDto> employeeCategoryMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_EMPLOYEE_CATEGORY_MASTER_BY_ID").setInteger("employeeId", employeeId)
					.setInteger("orgId", orgId)
					.setResultTransformer(Transformers.aliasToBean(EmployeeCategoryMasterDto.class)).list();
			return new Response<>(SUCCESS, SUCCESS_CODE, null, employeeCategoryMasterDtoList, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, null, null, null);
	}

	@Override
	public Response getEmployeeCategoryMasterList(EmployeeCategoryMasterDto employeeCategoryMasterDto) throws ApplicationException {
		try {
			List<EmployeeCategoryMasterDto> employeeCategoryMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_EMPLOYEE_CATEGORY_MASTER_LIST")
					.setInteger("orgId", employeeCategoryMasterDto.getOrganizationId())
					.setFirstResult(employeeCategoryMasterDto.getOffset() != null ? employeeCategoryMasterDto.getOffset() : 0)
					.setMaxResults(employeeCategoryMasterDto.getNoOfRecordsPerPage() != null ? employeeCategoryMasterDto.getNoOfRecordsPerPage() : 10)
					.setResultTransformer(Transformers.aliasToBean(EmployeeCategoryMasterDto.class)).list();
			return new Response<>(SUCCESS, SUCCESS_CODE, null, employeeCategoryMasterDtoList, null);

		} catch (HibernateException he) {
			he.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, null, null, null);
	}

	@Override
	public Response updateEmployeeCategoryMaster(EmployeeCategoryMasterDto employeeCategoryMasterDto)
			throws ApplicationException {
		try {
			EmployeeCategoryMaster employeeCategoryMaster = findById(employeeCategoryMasterDto.getEmployeeCategoryId());
			employeeCategoryMaster.setEmployeeCategoryCode(employeeCategoryMasterDto.getEmployeeCategoryCode());
			employeeCategoryMaster
					.setEmployeeCategoryDescription(employeeCategoryMasterDto.getEmployeeCategoryDescription());
			employeeCategoryMaster.setUpdatedBy(employeeCategoryMasterDto.getUpdatedBy());
			employeeCategoryMaster.setUpdatedDate(
					GlobalCommonDateUtils.getDate(employeeCategoryMasterDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss"));
			update(employeeCategoryMaster);
			return new Response<>(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, null, null, null);
	}

	@Override
	public Response updateEmployeeCategoryMasterStatus(EmployeeCategoryMasterDto employeeCategoryMasterDto)
			throws ApplicationException {
		try {
			EmployeeCategoryMaster employeeCategoryMaster = findById(employeeCategoryMasterDto.getEmployeeCategoryId());
			employeeCategoryMaster.setStatus(employeeCategoryMasterDto.getStatus() == ACTIVE ? ACTIVE : INACTIVE);
			employeeCategoryMaster.setUpdatedBy(employeeCategoryMasterDto.getUpdatedBy());
			employeeCategoryMaster.setUpdatedDate(
					GlobalCommonDateUtils.getDate(employeeCategoryMasterDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss"));
			update(employeeCategoryMaster);
			return new Response<>(SUCCESS, SUCCESS_CODE, COMMON_UPDATE, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, null, null, null);
	}

	@Override
	public Response getEmployeeCategoryMasterByNameNotId(EmployeeCategoryMasterDto employeeCategoryMasterDto)
			throws ApplicationException {
		try {

			List<EmployeeCategoryMasterDto> employeeCategoryMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_EMPLOYEE_CATEGORY_MASTER_BY_NAME_NOT_ID")
					.setInteger("employeeId", employeeCategoryMasterDto.getEmployeeCategoryId())
					.setString("empName", employeeCategoryMasterDto.getEmployeeCategoryDescription().toLowerCase())
					.setResultTransformer(Transformers.aliasToBean(EmployeeCategoryMasterDto.class)).list();
			return new Response<>(SUCCESS, SUCCESS_CODE, null, employeeCategoryMasterDtoList, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, null, null, null);
	}

	@Override
	public Response getEmployeeCategoryMasterByName(EmployeeCategoryMasterDto employeeCategoryMasterDto)
			throws ApplicationException {
		try {
			List<EmployeeCategoryMasterDto> employeeCategoryMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_EMPLOYEE_CATEGORY_MASTER_BY_NAME")
					.setInteger("orgId", employeeCategoryMasterDto.getOrganizationId())
					.setString("empName", employeeCategoryMasterDto.getEmployeeCategoryDescription().toLowerCase())
					.setResultTransformer(Transformers.aliasToBean(EmployeeCategoryMasterDto.class)).list();
			return new Response<>(SUCCESS, SUCCESS_CODE, null, employeeCategoryMasterDtoList, null);

		} catch (HibernateException he) {
			he.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, null, null, null);
	}

	@Override
	public Response getEmployeeCategoryCount(EmployeeCategoryMasterDto employeeCategoryMasterDto)
			throws ApplicationException {
		try{
			Session session=sessionFactory.openSession();
			Criteria c=session.createCriteria(EmployeeCategoryMaster.class); 
			c.add(Restrictions.eq("organizationId", employeeCategoryMasterDto.getOrganizationId()));
			c.setProjection(Projections.rowCount());  
			Long count = (Long)c.uniqueResult();
			return new Response(SUCCESS, null, null, null, count);
		}catch(HibernateException he){
			he.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, null, null, null);
	}

}
