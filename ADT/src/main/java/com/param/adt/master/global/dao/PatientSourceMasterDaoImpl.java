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
import com.param.adt.master.global.dto.PatientSourceMasterDto;
import com.param.adt.master.global.model.PatientSourceMaster;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;
@SuppressWarnings({"rawtypes","unchecked"})
@Repository
public class PatientSourceMasterDaoImpl extends GenericDao<PatientSourceMaster, Integer> implements ICommonConstants,IPatientServiceMasterDao{

	public PatientSourceMasterDaoImpl( ) {
		super(PatientSourceMaster.class);
		// TODO Auto-generated constructor stub
	}

	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Autowired
	private DozerBeanMapper mapper;
	
	@Override
	public Response getPatientSourceByName(PatientSourceMasterDto patientSourceMasterDto) throws ApplicationException {
		try {
			List<PatientSourceMasterDto> patientSourceMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_PATIENT_SOURCE_LIST_BY_NAME")
					.setString("desc", patientSourceMasterDto.getDesc().toLowerCase())
					.setResultTransformer(Transformers.aliasToBean(PatientSourceMasterDto.class)).list();
			return new Response(SUCCESS, null, null, patientSourceMasterDtoList, null);

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
	public Response addPatientSourceTypeMaster(PatientSourceMasterDto patientSourceMasterDto)
			throws ApplicationException {
		try {
			PatientSourceMaster patientSourceMaster = mapper.map(patientSourceMasterDto, PatientSourceMaster.class, "PatientSourceMasterDto_to_PatientSourceMaster");
			patientSourceMaster = save(patientSourceMaster);
			return new Response(SUCCESS, null, null, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getPatientSourceMasterList(PatientSourceMasterDto patientSourceMasterDto) throws ApplicationException {
		try {
			List<PatientSourceMasterDto> patientSourceMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_PATIENT_SOURCE_LIST")
					.setInteger("orgId", patientSourceMasterDto.getOrganizationId())
					.setFirstResult(patientSourceMasterDto.getOffset() != null ? patientSourceMasterDto.getOffset() : 0)
					.setMaxResults(patientSourceMasterDto.getNoOfRecordsPerPage() != null ? patientSourceMasterDto.getNoOfRecordsPerPage() : 10)
					.setResultTransformer(Transformers.aliasToBean(PatientSourceMasterDto.class)).list();
			return new Response(SUCCESS, null, null, patientSourceMasterDtoList, null);

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
	public Response getPatientSourceById(PatientSourceMasterDto patientSourceMasterDto) throws ApplicationException {
		try {
			List<PatientSourceMasterDto> patientSourceMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_PATIENT_SOURCE_LIST_BY_ID")
					.setInteger("id", patientSourceMasterDto.getId())
					.setResultTransformer(Transformers.aliasToBean(PatientSourceMasterDto.class)).list();
			return new Response(SUCCESS, null, null, patientSourceMasterDtoList, null);

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
	public Response getActivePatientSourceList() throws ApplicationException {
		try {
			List<PatientSourceMasterDto> patientSourceMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_ACTIVE_PATIENT_SOURCE_LIST")
					.setResultTransformer(Transformers.aliasToBean(PatientSourceMasterDto.class)).list();
			return new Response(SUCCESS, null, null, patientSourceMasterDtoList, null);

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
	public Response getPatientSourceByNameNotId(PatientSourceMasterDto patientSourceMasterDto)
			throws ApplicationException {
		try {
			List<PatientSourceMasterDto> patientSourceMasterDtoList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_PATIENT_SOURCE_LIST_BY_NAME_NOT_ID")
					.setInteger("id", patientSourceMasterDto.getId())
					.setString("desc", patientSourceMasterDto.getDesc().toLowerCase())
					.setResultTransformer(Transformers.aliasToBean(PatientSourceMasterDto.class)).list();
			return new Response(SUCCESS, null, null, patientSourceMasterDtoList, null);

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
	public Response updatePatientSource(PatientSourceMasterDto patientSourceMasterDto) throws ApplicationException {
		try {
			PatientSourceMaster patientSourceMaster = findById(patientSourceMasterDto.getId());
			patientSourceMaster.setDesc(patientSourceMasterDto.getDesc());
			patientSourceMaster.setCode(patientSourceMasterDto.getCode());
			patientSourceMaster.setUpdatedBy(patientSourceMasterDto.getUpdatedBy());
			patientSourceMaster.setUpdatedDate(ADTCommonDateUtils.getDate(patientSourceMasterDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss"));
			update(patientSourceMaster);
			return new Response<PatientSourceMasterDto, Integer>(SUCCESS, null, COMMON_SUCCESS, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();
			return new Response<PatientSourceMasterDto, Integer>(ERROR, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<PatientSourceMasterDto, Integer>(ERROR, null, null, null, null);
		} finally {
			 ;
		}
	}

	@Override
	public Response updatePatientSourceStatus(PatientSourceMasterDto patientSourceMasterDto)
			throws ApplicationException {
		try {
			PatientSourceMaster patientSourceMaster = findById(patientSourceMasterDto.getId());
			patientSourceMaster.setUpdatedBy(patientSourceMasterDto.getUpdatedBy());
			patientSourceMaster.setUpdatedDate(ADTCommonDateUtils.getDate(patientSourceMasterDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss"));
			patientSourceMaster.setStatus(patientSourceMasterDto.getStatus() == ACTIVE ? ACTIVE : INACTIVE);
			update(patientSourceMaster);
			return new Response<PatientSourceMasterDto, Integer>(SUCCESS, null, COMMON_SUCCESS, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();
			return new Response<PatientSourceMasterDto, Integer>(ERROR, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<PatientSourceMasterDto, Integer>(ERROR, null, null, null, null);
		} finally {
			 ;
		}
	}
	
	@Override
	public Response getCount(PatientSourceMasterDto patientSourceMasterDto)
	{
		try{
			Session session=sessionFactory.openSession();
			Criteria c=session.createCriteria(PatientSourceMaster.class); 
			c.add(Restrictions.eq("organizationId", patientSourceMasterDto.getOrganizationId()));
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
