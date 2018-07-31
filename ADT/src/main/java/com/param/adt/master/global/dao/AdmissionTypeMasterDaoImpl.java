package com.param.adt.master.global.dao;

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

import com.param.adt.common.ADTCommonDateUtils;
import com.param.adt.common.ICommonConstants;
import com.param.adt.common.Response;
import com.param.adt.master.global.dto.AdmissionTypeMasterDto;
import com.param.adt.master.global.model.AdmissionTypeMaster;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings({ "rawtypes", "unchecked" })
public class AdmissionTypeMasterDaoImpl   extends GenericDao<AdmissionTypeMaster, Integer> implements IAdmissionTypeMasterDao,ICommonConstants{

	public AdmissionTypeMasterDaoImpl() {
		super(AdmissionTypeMaster.class);
		// TODO Auto-generated constructor stub
	}
	
	
	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	DozerBeanMapper mapper;

	@Override
	public Response getAdmissionTypeByName(AdmissionTypeMasterDto admissionTypeMasterDto) throws ApplicationException {
		// TODO Auto-generated method stub
		try {
			List<AdmissionTypeMasterDto> admissionTypeMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_ADMISSION_TYPE_LIST_BY_NAME")
					.setString("admissionTypeDesc", admissionTypeMasterDto.getAdmissionTypeDesc().toLowerCase())
					.setResultTransformer(Transformers.aliasToBean(AdmissionTypeMasterDto.class)).list();
			if (admissionTypeMasterDtosList != null) {
				return new Response(SUCCESS, null, null, admissionTypeMasterDtosList, null);
			}
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response addAdmissionTypeMaster(AdmissionTypeMasterDto admissionTypeMasterDto) throws ApplicationException {
		// TODO Auto-generated method stub
		try {
			AdmissionTypeMaster admissionTypeMaster = mapper.map(admissionTypeMasterDto, AdmissionTypeMaster.class,
					"AdmissionTypeMasterDto_to_AdmissionTypeMaster");
			admissionTypeMaster = save(admissionTypeMaster);
			return new Response(SUCCESS, null, null, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getAdmissionTypeMasterList(AdmissionTypeMasterDto admissionTypeMasterDto) throws ApplicationException {
		// TODO Auto-generated method stub
		try {
			List<AdmissionTypeMasterDto> admissionTyperDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_ADMISSION_TYPE_LIST")
					.setFirstResult(admissionTypeMasterDto.getOffset() != null ? admissionTypeMasterDto.getOffset() : 0)
					.setMaxResults(admissionTypeMasterDto.getNoOfRecordsPerPage() != null ? admissionTypeMasterDto.getNoOfRecordsPerPage() : 10)
					.setInteger("orgId", admissionTypeMasterDto.getOrganizationId())
					.setResultTransformer(Transformers.aliasToBean(AdmissionTypeMasterDto.class)).list();
			if (admissionTyperDtosList != null) {
				return new Response(SUCCESS, null, null, admissionTyperDtosList, null);
			}
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getAdmissionTypeByNameNotId(AdmissionTypeMasterDto admissionTypeMasterDto)
			throws ApplicationException {
		try {
			List<AdmissionTypeMasterDto> admissionTypeMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_ADMISSION_TYPE_LIST_BY_NAME_NOT_ID")
					.setInteger("admissionTypeId",admissionTypeMasterDto.getAdmissionTypeId())
					.setString("admissionTypeDesc",admissionTypeMasterDto.getAdmissionTypeDesc().toLowerCase())
					.setResultTransformer(Transformers.aliasToBean(AdmissionTypeMasterDto.class)).list();
			if (admissionTypeMasterDtosList != null) {
				return new Response(SUCCESS, null, null, admissionTypeMasterDtosList, null);
			}
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateAdmissionTypeMaster(AdmissionTypeMasterDto admissionTypeMasterDto)
			throws ApplicationException {
		try {
			AdmissionTypeMaster admissionTypeMaster = findById(admissionTypeMasterDto.getAdmissionTypeId());
			admissionTypeMaster.setAdmissionTypeDesc(admissionTypeMasterDto.getAdmissionTypeDesc());
			admissionTypeMaster.setAdmissionTypeCode(admissionTypeMasterDto.getAdmissionTypeCode());
			admissionTypeMaster.setUpdatedBy(admissionTypeMasterDto.getUpdatedBy());
			admissionTypeMaster.setUpdatedDate(ADTCommonDateUtils.getDate(admissionTypeMasterDto.getUpdatedDate(),"dd-M-yyy HH:mm:ss"));
			update(admissionTypeMaster);
			return new Response<AdmissionTypeMasterDto, Integer>(SUCCESS, null, COMMON_SUCCESS, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();
			return new Response<AdmissionTypeMasterDto, Integer>(ERROR, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<AdmissionTypeMasterDto, Integer>(ERROR, null, null, null, null);
		} finally {
			 ;
		}
	}

	@Override
	public Response getAdmissionTypeById(AdmissionTypeMasterDto admissionTypeMasterDto) throws ApplicationException {
		// TODO Auto-generated method stub
		try {
			List<AdmissionTypeMasterDto> admissionTypeDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_ADMISSION_TYPE_LIST_BY_ID")
					.setInteger("admissionTypeId",admissionTypeMasterDto.getAdmissionTypeId())
					.setResultTransformer(Transformers.aliasToBean(AdmissionTypeMasterDto.class)).list();
			if (admissionTypeDtosList != null) {
				return new Response(SUCCESS, null, null, admissionTypeDtosList, null);
			}
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateAdmissionTypeStatus(AdmissionTypeMasterDto admissionTypeMasterDto) throws ApplicationException {
		try {
			AdmissionTypeMaster admissionTypeMaster = findById(admissionTypeMasterDto.getAdmissionTypeId());

			admissionTypeMaster.setStatus(admissionTypeMasterDto.getStatus() == ACTIVE ? ACTIVE : INACTIVE);
			admissionTypeMaster.setUpdatedBy(admissionTypeMasterDto.getUpdatedBy());
			admissionTypeMaster.setUpdatedDate(ADTCommonDateUtils.getDate(admissionTypeMasterDto.getUpdatedDate(),"dd-M-yyy HH:mm:ss"));
			update(admissionTypeMaster);
			return new Response<AdmissionTypeMasterDto, Integer>(SUCCESS, null, COMMON_SUCCESS, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();
			return new Response<AdmissionTypeMasterDto, Integer>(ERROR, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<AdmissionTypeMasterDto, Integer>(ERROR, null, null, null, null);
		} finally {
			 ;
		}
	}

	@Override
	public Response getActiveAdmissionTypeMasterList() throws ApplicationException {
		try {
			List<AdmissionTypeMasterDto> admissionTypeDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_ACTIVE_ADMISSION_TYPE_LIST")
					.setResultTransformer(Transformers.aliasToBean(AdmissionTypeMasterDto.class)).list();
			if (admissionTypeDtosList != null) {
				return new Response(SUCCESS, null, null, admissionTypeDtosList, null);
			}
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}
	
	public Response getCount(AdmissionTypeMasterDto admissionTypeMasterDto) throws ApplicationException {
		try{
			Session session=sessionFactory.openSession();
			Criteria c=session.createCriteria(AdmissionTypeMaster.class); 
			c.add(Restrictions.eq("organizationId", admissionTypeMasterDto.getOrganizationId()));
			c.setProjection(Projections.rowCount());  
			Long count = (Long)c.uniqueResult();
			return new Response(SUCCESS, null, null, null, count);
		}catch(Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, null, null, null, null);
		}
	}

}
