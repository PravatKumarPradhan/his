package com.param.global.org.common.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.param.global.common.GlobalCommonDateUtils;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.model.EmployeeDesignationMaster;
import com.param.global.org.common.dto.DesignationMasterDto;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;
@Repository
@SuppressWarnings({ "unchecked", "rawtypes" })

public class DesignationMasterDaoImpl extends GenericDao<EmployeeDesignationMaster, Integer>
		implements IDesignationMasterDao, ICommonConstants {

	public DesignationMasterDaoImpl() {
		super(EmployeeDesignationMaster.class);
	}

	@Override
	public Response saveDesignationMaster(DesignationMasterDto designationMasterDto) throws ApplicationException {
		try {
			EmployeeDesignationMaster employeeDesignationMaster=new EmployeeDesignationMaster();
			employeeDesignationMaster.setCreatedBy(designationMasterDto.getCreatedBy());
			employeeDesignationMaster.setCreatedDate(GlobalCommonDateUtils.getDate(designationMasterDto.getCreatedDate(),"dd-M-yyyy HH:mm:ss" ));
			employeeDesignationMaster.setEmployeeDesignationCode(designationMasterDto.getEmployeeDesignationDescription());
			employeeDesignationMaster.setEmployeeDesignationDescription(designationMasterDto.getEmployeeDesignationDescription());
			employeeDesignationMaster.setOrganizationId(designationMasterDto.getOrganizationId());
			employeeDesignationMaster.setStatus(designationMasterDto.getStatus());
			employeeDesignationMaster.setUpdatedBy(designationMasterDto.getUpdatedBy());
			employeeDesignationMaster.setUpdatedDate(GlobalCommonDateUtils.getDate(designationMasterDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss"));
			save(employeeDesignationMaster);
			return new Response<>(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	
	@Override
	public Response getDesignationMasterById(int designationId, int orgId) throws ApplicationException {
		try {
			List<DesignationMasterDto> designationMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_DESIGNATION_MASTER_BY_ID").setInteger("designationId", designationId)
					.setInteger("orgId", orgId)
					.setResultTransformer(Transformers.aliasToBean(DesignationMasterDto.class)).list();
			return new Response<>(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, designationMasterDtoList, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getDesignationMasterList(DesignationMasterDto designationMasterDto) throws ApplicationException {
		try {
			List<DesignationMasterDto> designationMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_DESIGNATION_MASTER_LIST")
					.setInteger("orgId", designationMasterDto.getOrganizationId())
					.setFirstResult(designationMasterDto.getOffset() != null ? designationMasterDto.getOffset() : 0)
					.setMaxResults(designationMasterDto.getNoOfRecordsPerPage() != null ? designationMasterDto.getNoOfRecordsPerPage() : 10)
					.setResultTransformer(Transformers.aliasToBean(DesignationMasterDto.class)).list();
			return new Response<>(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, designationMasterDtoList, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response updateDesignationMaster(DesignationMasterDto designationMasterDto) throws ApplicationException {
		try {
			EmployeeDesignationMaster employeeDesignationMaster=findById(designationMasterDto.getEmployeeDesignationId());
			employeeDesignationMaster.setEmployeeDesignationCode(designationMasterDto.getEmployeeDesignationCode());
			employeeDesignationMaster.setEmployeeDesignationDescription(designationMasterDto.getEmployeeDesignationDescription());
			employeeDesignationMaster.setUpdatedBy(designationMasterDto.getUpdatedBy());
			employeeDesignationMaster.setUpdatedDate(GlobalCommonDateUtils.getDate(designationMasterDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss"));
			update(employeeDesignationMaster);
			return new Response<>(SUCCESS, SUCCESS_CODE, COMMON_UPDATE, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response updateDesignationMasterStatus(DesignationMasterDto designationMasterDto)
			throws ApplicationException {
		try {
			EmployeeDesignationMaster employeeDesignationMaster=findById(designationMasterDto.getEmployeeDesignationId());
			employeeDesignationMaster.setStatus(designationMasterDto.getStatus() == ACTIVE ? ACTIVE : INACTIVE);
			employeeDesignationMaster.setUpdatedBy(designationMasterDto.getUpdatedBy());
			employeeDesignationMaster.setUpdatedDate(
					GlobalCommonDateUtils.getDate(designationMasterDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss"));
			update(employeeDesignationMaster);
			return new Response<>(SUCCESS, SUCCESS_CODE, COMMON_UPDATE, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getDesignationMasterCount(DesignationMasterDto designationMasterDto) throws ApplicationException {
		try {
			Session session=sessionFactory.openSession();
			Criteria c=session.createCriteria(EmployeeDesignationMaster.class); 
			c.add(Restrictions.eq("organizationId", designationMasterDto.getOrganizationId()));
			c.setProjection(Projections.rowCount());  
			Long count = (Long)c.uniqueResult();
			return new Response(SUCCESS, null, null, null, count);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getDesignationMasterByName(DesignationMasterDto designationMasterDto) throws ApplicationException {
		try {
			List<DesignationMasterDto> designationMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_DESIGNATION_MASTER_BY_NAME")
					.setInteger("orgId", designationMasterDto.getOrganizationId())
					.setString("designationDesc", designationMasterDto.getEmployeeDesignationDescription().toLowerCase())
					.setResultTransformer(Transformers.aliasToBean(DesignationMasterDto.class)).list();
			return new Response<>(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, designationMasterDtoList, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getDesignationMasterByNameNotById(DesignationMasterDto designationMasterDto)
			throws ApplicationException {
		try {
			List<DesignationMasterDto> designationMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_DESIGNATION_MASTER_BY_NAME_NOT_BY_ID")
					.setInteger("designationId", designationMasterDto.getEmployeeDesignationId())
					.setString("designationDesc", designationMasterDto.getEmployeeDesignationDescription())
					.setResultTransformer(Transformers.aliasToBean(DesignationMasterDto.class)).list();
			return new Response<>(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, designationMasterDtoList, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

}
