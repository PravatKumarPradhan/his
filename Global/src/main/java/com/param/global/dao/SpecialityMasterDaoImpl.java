package com.param.global.dao;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.param.global.common.GlobalCommonDateUtils;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dto.SpecialityMasterDto;
import com.param.global.model.SpecialityMaster;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@SuppressWarnings({ "rawtypes", "unchecked" })
@Repository
public class SpecialityMasterDaoImpl extends GenericDao<SpecialityMaster, Integer>
		implements ISpecialityMasterDao, ICommonConstants {
	@Autowired
	private DozerBeanMapper mapper;

	@Autowired
	private SessionFactory sessionFactory;

	public SpecialityMasterDaoImpl() {
		super(SpecialityMaster.class);
	}

	@Override
	public Response getSpecialityMasterList(SpecialityMasterDto SpecialityMasterDto) throws ApplicationException {
		try {
			List<SpecialityMasterDto> specialityMasterDto = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_SPECIALITY_LIST")
					.setInteger("orgId", SpecialityMasterDto.getOrganizationId())
					.setFirstResult(SpecialityMasterDto.getOffset() != null ? SpecialityMasterDto.getOffset() : 0)
					.setMaxResults(SpecialityMasterDto.getNoOfRecordsPerPage() != null ? SpecialityMasterDto.getNoOfRecordsPerPage() : 10)
					.setResultTransformer(Transformers.aliasToBean(SpecialityMasterDto.class)).list();
			return new Response(SUCCESS, null, null, specialityMasterDto, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			 ;
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response addSpecialityMaster(SpecialityMasterDto specialityMasterDto) throws ApplicationException {
		try {
			SpecialityMaster SpecialityMaster = mapper.map(specialityMasterDto, SpecialityMaster.class,
					"SpecialityMasterDto_to_SpecialityMaster");
			 
			//XSpecialityMaster xSpecialityMaster=(XSpecialityMaster) specialityMaster;
			save(SpecialityMaster);
			
			
			/*Session session=sessionFactory.openSession();
			Transaction tr=session.beginTransaction();
			XSpecialityMaster xSpecialityMaster=new XSpecialityMaster();
				xSpecialityMaster.setSpecialityCode(specialityMasterDto.getSpecialityCode());
				xSpecialityMaster.setSpecialityName(specialityMasterDto.getSpecialityName());
				xSpecialityMaster.setIsSurgicalCode(specialityMasterDto.getIsSurgicalCode());
				xSpecialityMaster.setGeneralLedgerId(specialityMasterDto.getGeneralLedgerId());
				xSpecialityMaster.setCreatedBy(specialityMasterDto.getCreatedBy());
				xSpecialityMaster.setCreatedDate(ADTCommonDateUtils.getDate(specialityMasterDto.getCreatedDate(),"dd-M-yyyy HH:mm:ss"));
				xSpecialityMaster.setUpdatedBy(specialityMasterDto.getUpdatedBy());
				xSpecialityMaster.setUpdatedDate(ADTCommonDateUtils.getDate(specialityMasterDto.getCreatedDate(),"dd-M-yyyy HH:mm:ss"));
				xSpecialityMaster.setStatus(specialityMasterDto.getStatus());
				xSpecialityMaster.setOrganizationId(specialityMasterDto.getOrganizationId());
			session.save(xSpecialityMaster);
			tr.commit();
	*/		
			
			return new Response(SUCCESS, null, null, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response editSpecialityMaster(SpecialityMasterDto SpecialityMasterDto) throws ApplicationException {
		try {
			List<SpecialityMasterDto> listSpecialityMasterDto = new ArrayList<SpecialityMasterDto>();
			listSpecialityMasterDto = sessionFactory.getCurrentSession().getNamedQuery("GET_SPECIALITY_LIST_BY_ID")
					.setInteger("specialityId", SpecialityMasterDto.getSpecialityId())
					.setResultTransformer(Transformers.aliasToBean(SpecialityMasterDto.class)).list();
			return new Response<SpecialityMasterDto, Integer>(SUCCESS, null, null, listSpecialityMasterDto, null);

		} catch (HibernateException he) {
			he.printStackTrace();
			return new Response<SpecialityMasterDto, Integer>(ERROR, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<SpecialityMasterDto, Integer>(ERROR, null, null, null, null);
		} finally {
			 ;
		}
	}

	@Override
	public Response updateSpecialityMaster(SpecialityMasterDto SpecialityMasterDto) throws ApplicationException {
		try {
			
			SpecialityMaster Speciality = findById(SpecialityMasterDto.getSpecialityId());
			Speciality.setSpecialityName(SpecialityMasterDto.getSpecialityName());
			Speciality.setSpecialityCode(SpecialityMasterDto.getSpecialityCode());
			Speciality.setGeneralLedgerId(SpecialityMasterDto.getGeneralLedgerId());
			Speciality.setIsSurgicalCode(SpecialityMasterDto.getIsSurgicalCode());
			Speciality.setUpdatedBy(SpecialityMasterDto.getUpdatedBy());
			Speciality.setUpdatedDate(GlobalCommonDateUtils.getDate(SpecialityMasterDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss"));
			
			update(Speciality);
			return new Response<SpecialityMasterDto, Integer>(SUCCESS, null, COMMON_SUCCESS, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();
			return new Response<SpecialityMasterDto, Integer>(ERROR, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<SpecialityMasterDto, Integer>(ERROR, null, null, null, null);
		} finally {
			 ;
		}
	}

	@Override
	public Response getSpecialityByNameNotId(SpecialityMasterDto specialityMasterDto) throws ApplicationException {
		try {
			List<SpecialityMasterDto> listDepartmentMasterDto = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_SPECIALITY_LIST_BY_NAME_NOT_ID")
					.setString("specialityName", specialityMasterDto.getSpecialityName().toLowerCase())
					.setInteger("specialityId", specialityMasterDto.getSpecialityId())
					.setResultTransformer(Transformers.aliasToBean(SpecialityMasterDto.class)).list();
			if (listDepartmentMasterDto != null) {
				return new Response(SUCCESS, null, null, listDepartmentMasterDto, null);
			}
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getSpecialityById(SpecialityMasterDto specialityMasterDto) throws ApplicationException {
		try {
			List<SpecialityMasterDto> listSpecialityMasterDto = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_SPECIALITY_LIST_BY_ID")
					.setInteger("specialityId", specialityMasterDto.getSpecialityId())
					.setResultTransformer(Transformers.aliasToBean(SpecialityMasterDto.class)).list();
			if (listSpecialityMasterDto != null) {
				return new Response(SUCCESS, null, null, listSpecialityMasterDto, null);
			}
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getActiveSpecialityList(SpecialityMasterDto specialityMasterDto) throws ApplicationException {
		try {
			List<SpecialityMasterDto> specialityMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_ACTIVE_SPECIALITY_LIST")
					.setInteger("organizationId", specialityMasterDto.getOrganizationId())
					.setInteger("unitId", specialityMasterDto.getUnitId())
					.setResultTransformer(Transformers.aliasToBean(SpecialityMasterDto.class)).list();
			return new Response(SUCCESS, null, null, specialityMasterDtosList, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			 ;
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getSpecialityByName(SpecialityMasterDto specialityMasterDto) throws ApplicationException {
		try {
			List<SpecialityMasterDto> specialityMasterList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_SPECIALITY_LIST_BY_NAME")
					.setString("specialityName", specialityMasterDto.getSpecialityName().toLowerCase())
					.setResultTransformer(Transformers.aliasToBean(SpecialityMasterDto.class)).list();
			return new Response(SUCCESS, null, null, specialityMasterList, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			 ;
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateSpecialityStatus(SpecialityMasterDto SpecialityMasterDto) {
		try {
			SpecialityMaster Speciality = findById(SpecialityMasterDto.getSpecialityId());

			Speciality.setStatus(SpecialityMasterDto.getStatus() == ACTIVE ? ACTIVE : INACTIVE);
			Speciality.setUpdatedBy(SpecialityMasterDto.getUpdatedBy());
			Speciality.setUpdatedDate(GlobalCommonDateUtils.getDate(SpecialityMasterDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss"));
			update(Speciality);
			return new Response<SpecialityMasterDto, Integer>(SUCCESS, null, COMMON_SUCCESS, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();
			return new Response<SpecialityMasterDto, Integer>(ERROR, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<SpecialityMasterDto, Integer>(ERROR, null, null, null, null);
		} finally {
			 ;
		}

	}

	@Override
	public Response getActiveSpecialityMasterList() throws ApplicationException {
		try {
			List<SpecialityMasterDto> specialityMasterList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_ACTIVE_SPECIALITY_LIST")
					.setResultTransformer(Transformers.aliasToBean(SpecialityMasterDto.class)).list();
			return new Response(SUCCESS, null, null, specialityMasterList, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			 ;
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getSpecialityListForSubSpeciality() throws ApplicationException {
		try {
			List<SpecialityMasterDto> specialityMasterDto = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_SPECIALITY_LIST_FOR_SUB_SPE")
					.setResultTransformer(Transformers.aliasToBean(SpecialityMasterDto.class)).list();
			return new Response(SUCCESS, null, null, specialityMasterDto, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			 ;
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getCount(SpecialityMasterDto SpecialityMasterDto)
	{
		try{
			Session session=sessionFactory.openSession();
			Criteria c=session.createCriteria(SpecialityMaster.class); 
			c.add(Restrictions.eq("organizationId", SpecialityMasterDto.getOrganizationId()));
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
	public Response getOrgActiveSpecialityList(SpecialityMasterDto specialityMasterDto) throws ApplicationException {
		try {
			List<SpecialityMasterDto> specialityMasterDtolist = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_ORG_ACTIVE_SPECIALITY_LIST_NOT_MAPPED_TO_UNIT")
					.setInteger("unitId", specialityMasterDto.getUnitId())
					.setInteger("orgId", specialityMasterDto.getOrganizationId())
					.setResultTransformer(Transformers.aliasToBean(SpecialityMasterDto.class)).list();
			return new Response(SUCCESS, null, null, specialityMasterDtolist, null);
			

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		} 
		return new Response(ERROR, null, null, null, null);
	}	
	
}
