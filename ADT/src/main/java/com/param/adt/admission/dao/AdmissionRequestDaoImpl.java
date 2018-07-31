package com.param.adt.admission.dao;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.param.adt.admission.dto.AdmissionNoteDto;
import com.param.adt.admission.model.AdmissionNote;
import com.param.adt.admission.model.AdmissionPatientMapper;
import com.param.adt.common.ADTCommonDateUtils;
import com.param.adt.common.IADTConstants;
import com.param.adt.common.ICommonConstants;
import com.param.adt.common.Response;
import com.param.adt.master.global.dto.DoctorSpecialityMapperDto;
import com.param.adt.master.global.dto.ReasonMasterDto;
import com.param.adt.master.global.dto.VisitMasterDto;
import com.param.adt.master.global.model.BedCategoryWaitingList;
import com.param.global.dto.PatientRegistrationDto;
import com.param.global.dto.SpecialityMasterDto;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings({ "rawtypes", "unchecked" })
public class AdmissionRequestDaoImpl extends GenericDao<AdmissionNote, Integer>
		implements IAdmissionRequestDao, ICommonConstants,IADTConstants {

	public AdmissionRequestDaoImpl() {
		super(AdmissionNote.class);
		// TODO Auto-generated constructor stub
	}

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private DozerBeanMapper mapper;

	@Override
	public Response getPatientListLike(PatientRegistrationDto patientRegistrationDto) {
		try {

				List<PatientRegistrationDto> patientRegistrationDtosList = sessionFactory.getCurrentSession()
						.getNamedQuery("GET_PATIENT_LIST_LIKE")
						.setParameter("firstName", "%" + patientRegistrationDto.getFirstName().toLowerCase() + "%")
						.setInteger("organizationId", patientRegistrationDto.getOrganizationId())
						.setInteger("unitId", patientRegistrationDto.getUnitId())
						.setResultTransformer(Transformers.aliasToBean(PatientRegistrationDto.class)).list();
				return new Response(SUCCESS, null, null, patientRegistrationDtosList, null);
		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		} 
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getPatientVisitDetailsById(VisitMasterDto visitMasterDto) {
		try {
			List<VisitMasterDto> visitMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_PATIENT_VIST_DETAILS_BY_ID")
					.setInteger("patientId", visitMasterDto.getPatientId())
					.setResultTransformer(Transformers.aliasToBean(VisitMasterDto.class)).list();
			return new Response(SUCCESS, null, null, visitMasterDtosList, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		} 
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getDoctorListAganistSpeciality(DoctorSpecialityMapperDto doctorSpecialityMapperDto) {
		try {
			List<SpecialityMasterDto> specialityMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_DOCTOR_LIST_BY_SPECIALITY")
					.setInteger("organizationId", doctorSpecialityMapperDto.getOrganizationId())
					.setInteger("unitId", doctorSpecialityMapperDto.getUnitId())
					.setInteger("specilaityId", doctorSpecialityMapperDto.getSpecialityId())
					.setResultTransformer(Transformers.aliasToBean(DoctorSpecialityMapperDto.class)).list();
			return new Response(SUCCESS, null, null, specialityMasterDtosList, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getReasonMasterList() {
		try {
			List<ReasonMasterDto> reasonMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_REASON_MASTER_LIST")
					.setResultTransformer(Transformers.aliasToBean(ReasonMasterDto.class)).list();
			return new Response(SUCCESS, null, null, reasonMasterDtosList, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		} 
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response saveAdmissionRequest(AdmissionNoteDto admissionNoteDto) {
		try {
			AdmissionNote admissionNote = mapper.map(admissionNoteDto, AdmissionNote.class,
					"AdmissionNoteDto_to_AdmissionNote");
			admissionNote = save(admissionNote);
			admissionNoteDto.setAdmissionNoteId(admissionNote.getAdmissionNoteId());
			return new Response(SUCCESS, null, null, null, admissionNoteDto);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response saveAdmissionPatientMapper(AdmissionNoteDto admissionNoteDto)
			throws ApplicationException {
		try {
			Session session=sessionFactory.openSession();
				AdmissionPatientMapper admissionNote = mapper.map(admissionNoteDto, AdmissionPatientMapper.class,
						"AdmissionNoteDto_to_AdmissionPatientMaper");
			Transaction tr=session.beginTransaction();
			session.save(admissionNote);
			tr.commit();
			return new Response(SUCCESS, null, NOTE_GENERATED, null, admissionNoteDto);

		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);

	}

	@Override
	public Response getMaxWaitingListNumber(AdmissionNoteDto admissionNoteDto) throws ApplicationException {
		try {
				Session session = sessionFactory.openSession();
				Criteria c = session.createCriteria(BedCategoryWaitingList.class);
					c.add(Restrictions.eq("organizationId", admissionNoteDto.getOrganizationId()));
					c.add(Restrictions.eq("unitId", admissionNoteDto.getUnitId()));
					c.add(Restrictions.eq("bedCategoryId", admissionNoteDto.getBedCategoryId()));
					c.add(Restrictions.eq("doa", ADTCommonDateUtils.getDate(admissionNoteDto.getDoa(),"dd-M-yyyy HH:mm:ss")));
					c.add(Restrictions.eq("status",'A'));
					c.setProjection(Projections.max("waitListNumber"));
				Integer maxWaitingListNo = (Integer) c.uniqueResult();
				if(maxWaitingListNo==null)
					maxWaitingListNo=0;
				return new Response(SUCCESS, null, null, null, maxWaitingListNo);
			
		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response saveWaitingListNumber(AdmissionNoteDto admissionNoteDto) throws ApplicationException {
		try {
			Session session=sessionFactory.openSession();
			Transaction tr=session.beginTransaction();
				BedCategoryWaitingList bedCategoryWaitingList = mapper.map(admissionNoteDto, BedCategoryWaitingList.class,
						"AdmissionNoteDto_to_BedCategoryWaitingList");
			Integer bedCategoryWatingListId=(Integer) session.save(bedCategoryWaitingList);
			tr.commit();
			return new Response(SUCCESS, null, NOTE_GENERATED, null, bedCategoryWatingListId);

		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
		
	}

	@Override
	public Response getDoctorsListAganistSpecialitys(DoctorSpecialityMapperDto doctorSpecialityMapper)
			throws ApplicationException {
		try {
			List<SpecialityMasterDto> specialityMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_DOCTORS_LIST_BY_SPECIALITIES")
					.setInteger("organizationId", doctorSpecialityMapper.getOrganizationId())
					.setInteger("unitId", doctorSpecialityMapper.getUnitId())
					.setParameterList("specilaityArray", doctorSpecialityMapper.getSpecialityArray())
					.setResultTransformer(Transformers.aliasToBean(DoctorSpecialityMapperDto.class)).list();
			return new Response(SUCCESS, null, null, specialityMasterDtosList, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

}
