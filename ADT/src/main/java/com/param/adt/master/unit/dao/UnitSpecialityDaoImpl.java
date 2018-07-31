package com.param.adt.master.unit.dao;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.param.adt.common.ADTCommonDateUtils;
import com.param.adt.common.ICommonConstants;
import com.param.adt.common.Response;
import com.param.adt.master.unit.dto.UnitSpecialtyMapperDto;
import com.param.adt.master.unit.model.UnitSpecialityMapper;
import com.param.global.dto.SpecialityMasterDto;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings({ "rawtypes", "unchecked" })
public class UnitSpecialityDaoImpl extends GenericDao<UnitSpecialityMapper, Integer>
		implements ICommonConstants, IUnitSpecialityDao {

	public UnitSpecialityDaoImpl() {
		super(UnitSpecialityMapper.class);
		// TODO Auto-generated constructor stub
	}

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	DozerBeanMapper mapper;

	@Override
	public Response saveUnitSpecialtiy(UnitSpecialtyMapperDto unitSpecialtyMapperDto) throws ApplicationException {
		try {
			UnitSpecialityMapper unitSpecialityMapper = mapper.map(unitSpecialtyMapperDto, UnitSpecialityMapper.class,
					"UnitSpecialtyMapperDto_to_UnitSpecialtyMapper");
			save(unitSpecialityMapper);
			return new Response(SUCCESS, null, COMMON_SUCCESS, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getUnitSpecialtiyList(UnitSpecialtyMapperDto unitSpecialtyMapperDto) throws ApplicationException {
		try {
			List<UnitSpecialtyMapperDto> unitSpecialtyMapperDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_UNIT_SPECIALITY_LIST")
					.setInteger("orgId", unitSpecialtyMapperDto.getOrganizationId())
					.setInteger("unitId", unitSpecialtyMapperDto.getUnitId())
					.setFirstResult(unitSpecialtyMapperDto.getOffset() != null ? unitSpecialtyMapperDto.getOffset() : 0)
					.setMaxResults(unitSpecialtyMapperDto.getNoOfRecordsPerPage() != null ? unitSpecialtyMapperDto.getNoOfRecordsPerPage() : 10)
					.setResultTransformer(Transformers.aliasToBean(UnitSpecialtyMapperDto.class)).list();
			return new Response(SUCCESS, null, null, unitSpecialtyMapperDtosList, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		} 
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getUnitSpecialityById(UnitSpecialtyMapperDto unitSpecialtyMapperDto) throws ApplicationException {
		/*try
		{
			UnitSpecialityMapper unitSpecialityMapper=findById(unitSpecialtyMapperDto.getUnitSpecialityMapperId());
		if(unitSpecialityMapper.getUnitSpecialityMapperId()==unitSpecialtyMapperDto.getUnitSpecialityMapperId())
			return new Response(SUCCESS, null, null, null, null);
		else 
			return new Response(ERROR, null, null, null, null);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);*/
		try {
			List<UnitSpecialtyMapperDto> unitSpecialtyMapperDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_UNIT_SPECIALITY_BY_ID")
					.setInteger("unitSpecialityMapperId", unitSpecialtyMapperDto.getUnitSpecialityMapperId())
					.setInteger("orgId", unitSpecialtyMapperDto.getOrganizationId())
					.setInteger("unitId", unitSpecialtyMapperDto.getUnitId())
					.setResultTransformer(Transformers.aliasToBean(UnitSpecialtyMapperDto.class)).list();
			return new Response(SUCCESS, null, null, unitSpecialtyMapperDtosList, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		} 
		return new Response(ERROR, null, null, null, null);
	
	}

	@Override
	public Response updateStatusForUnitSpecialityList(UnitSpecialtyMapperDto unitSpecialtyMapperDto)
			throws ApplicationException {
		try {
			UnitSpecialityMapper unitSpecialityMapper = findById(unitSpecialtyMapperDto.getUnitSpecialityMapperId());

			unitSpecialityMapper.setStatus(unitSpecialtyMapperDto.getStatus() == ACTIVE ? ACTIVE : INACTIVE);
			unitSpecialityMapper.setUpdatedBy(unitSpecialtyMapperDto.getUpdatedBy());
			unitSpecialityMapper.setUpdatedDate(
					ADTCommonDateUtils.getDate(unitSpecialtyMapperDto.getUpdatedDate(), "dd-M-yyy HH:mm:ss"));
			update(unitSpecialityMapper);
			return new Response<SpecialityMasterDto, Integer>(SUCCESS, null, COMMON_SUCCESS, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();
			return new Response<SpecialityMasterDto, Integer>(ERROR, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<SpecialityMasterDto, Integer>(ERROR, null, null, null, null);
		} 
	}

	@Override
	public Response truncateUnitSpecialtiy(UnitSpecialtyMapperDto obj) {
		try {
			// Updating status from A(active) to T(Truncated)
			Session session = sessionFactory.openSession();
			session.createQuery("update UnitSpecialityMapper ussm set ussm.status='T', ussm.updatedBy='"
					+ obj.getUpdatedBy() + "', ussm.updatedDate='"
					+ ADTCommonDateUtils.getDate(obj.getUpdatedDate(), "dd-M-yyyy HH:mm:ss") + "' WHERE ussm.status='A'")
					.executeUpdate();
			session.close();
			return new Response(SUCCESS, null, COMMON_SUCCESS, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getCount(UnitSpecialtyMapperDto unitSpecialtyMapperDto) throws ApplicationException {
		try{
			Session session=sessionFactory.openSession();
			Criteria c=session.createCriteria(UnitSpecialityMapper.class); 
			Criterion unit=Restrictions.eq("unitId", unitSpecialtyMapperDto.getUnitId());
			Criterion org=Restrictions.eq("organizationId", unitSpecialtyMapperDto.getOrganizationId());
			LogicalExpression andExp = Restrictions.and(unit, org);
			c.add(andExp);
			c.setProjection(Projections.rowCount());  
			Long count = (Long)c.uniqueResult();
			return new Response(SUCCESS, null, null, null, count);
		}catch(Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, null, null, null, null);
		}
	}

	@Override
	public Response getSpecialityListByDoctorId(UnitSpecialtyMapperDto unitSpecialtyMapperDto)
			throws ApplicationException {
		try {
			List<UnitSpecialtyMapperDto> unitSpecialtyMapperDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_SPECIALITY_LIST_BY_DOCTOR_ID")
					.setInteger("orgId", unitSpecialtyMapperDto.getOrganizationId())
					.setInteger("unitId", unitSpecialtyMapperDto.getUnitId())
					.setInteger("doctorId", unitSpecialtyMapperDto.getDoctorId())
					.setResultTransformer(Transformers.aliasToBean(UnitSpecialtyMapperDto.class)).list();
			return new Response(SUCCESS, null, null, unitSpecialtyMapperDtosList, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getActiveUnitSpecialityList(UnitSpecialtyMapperDto unitSpecialtyMapperDto)
			throws ApplicationException {
		try {
			List<UnitSpecialtyMapperDto> unitSpecialtyMapperDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_ACTIVE_UNIT_SPECIALITY_LIST")
					.setInteger("orgId", unitSpecialtyMapperDto.getOrganizationId())
					.setInteger("unitId", unitSpecialtyMapperDto.getUnitId())					
					.setResultTransformer(Transformers.aliasToBean(UnitSpecialtyMapperDto.class)).list();
			return new Response(SUCCESS, null, null, unitSpecialtyMapperDtosList, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		} 
		return new Response(ERROR, null, null, null, null);
	}


}
