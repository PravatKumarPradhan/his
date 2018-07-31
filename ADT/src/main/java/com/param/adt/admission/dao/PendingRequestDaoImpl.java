package com.param.adt.admission.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.param.adt.admission.dto.AdmissionNoteDto;
import com.param.adt.admission.dto.AdmissionPatientMapperDto;
import com.param.adt.admission.model.AdmissionNote;
import com.param.adt.admission.model.AdmissionPatientDocuments;
import com.param.adt.admission.model.AdmissionPatientMapper;
import com.param.adt.common.ADTCommonDateUtils;
import com.param.adt.common.ICommonConstants;
import com.param.adt.common.Response;
import com.param.adt.master.global.dto.PatientCategoryMasterDto;
import com.param.adt.master.global.dto.PaymentEntitlementMasterDto;
import com.param.global.dto.KinDetailsDto;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings({ "rawtypes", "unchecked" })
public class PendingRequestDaoImpl extends GenericDao<AdmissionNote, Integer>
		implements IPendingRequestDao, ICommonConstants {

	public PendingRequestDaoImpl() {
		super(AdmissionNote.class);
		// TODO Auto-generated constructor stub
	}

	@Autowired
	private SessionFactory sessionFactory;


	@Override
	public Response getPendingAdmissionRequestDetails(AdmissionNoteDto admissionNoteDto) throws ApplicationException {
		try {
			List<AdmissionNoteDto> admissionNoteDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_PENDING_ADMISSION_REQUEST_DETAILS")
					.setFirstResult(admissionNoteDto.getOffset() != null ? admissionNoteDto.getOffset() : 0)
					.setMaxResults(admissionNoteDto.getNoOfRecordsPerPage() != null ? admissionNoteDto.getNoOfRecordsPerPage() : 10)
					.setInteger("organizationId", admissionNoteDto.getOrganizationId())
					.setInteger("unitId", admissionNoteDto.getUnitId())
					.setResultTransformer(Transformers.aliasToBean(AdmissionNoteDto.class)).list();
			return new Response(SUCCESS, null, null, admissionNoteDtosList, null);

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
	public Response getAvtivePatientCategoryList() throws ApplicationException {
		try {
			List<PatientCategoryMasterDto> categoryMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_ACTIVE_PATIENT_CATEGORY_LIST")
					.setResultTransformer(Transformers.aliasToBean(PatientCategoryMasterDto.class)).list();
			return new Response(SUCCESS, null, null, categoryMasterDtosList, null);

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
	public Response getAvtivePaymentEntitlementList() throws ApplicationException {
		try {
			List<PaymentEntitlementMasterDto> entitlementMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_ACTIVE_PAYMENT_ENTITLEMENT_LIST")
					.setResultTransformer(Transformers.aliasToBean(PaymentEntitlementMasterDto.class)).list();
			return new Response(SUCCESS, null, null, entitlementMasterDtosList, null);

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
	public Response updatePendingAdmissionRequestDetails(AdmissionNoteDto admissionNoteDto)
			throws ApplicationException {
		try {

			AdmissionNote admissionNote = findById(admissionNoteDto.getAdmissionNoteId());
			admissionNote.setUpdatedBy(admissionNoteDto.getUpdatedBy());
			admissionNote.setUpdatedDate(ADTCommonDateUtils.getDate(admissionNoteDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss"));
			update(admissionNote);
			return new Response<AdmissionPatientMapperDto, Integer>(SUCCESS, null, COMMON_SUCCESS, null, admissionNote);

		} catch (HibernateException he) {
			he.printStackTrace();
			return new Response<AdmissionPatientMapperDto, Integer>(ERROR, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<AdmissionPatientMapperDto, Integer>(ERROR, null, null, null, null);
		} 
	}

	@Override
	public Response updateAdmissionRequestMapperDetails(AdmissionNoteDto admissionPatientMapperDto)
			throws ApplicationException {
		try {
			int admissionPatientId2 = 0;
			Response res = getPreviousData(admissionPatientMapperDto);
			List<AdmissionPatientMapperDto> admissionPatientMapperDtosList = res.getListObject();
			for (AdmissionPatientMapperDto a : admissionPatientMapperDtosList)
				admissionPatientId2 = a.getAdmissionPatientId();

			Session session = sessionFactory.openSession();
			Transaction tr = session.beginTransaction();

			Query qry = session.createQuery("update AdmissionPatientMapper m set m.status='I', " + "m.updatedBy="
					+ admissionPatientMapperDto.getUpdatedBy() + ", m.updatedDate='"+ADTCommonDateUtils.getDate(admissionPatientMapperDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss")
					+ "' where m.admissionPatientId=" + admissionPatientId2 + " AND m.status='A'");

			qry.executeUpdate();
			tr.commit();
			
			Transaction tr2=session.beginTransaction();
			
			AdmissionPatientMapper admissionPatientMapper = new AdmissionPatientMapper();
			admissionPatientMapper.setAdmissionNoteId(admissionPatientMapperDto.getAdmissionNoteId());
			admissionPatientMapper.setBedCategoryId(admissionPatientMapperDto.getBedCategoryId());
			admissionPatientMapper.setPatientCategoryId(admissionPatientMapperDto.getPatientCategoryId());
			admissionPatientMapper.setPaymentEntitlementId(admissionPatientMapperDto.getPaymentEntitlementId());
			admissionPatientMapper
					.setPdd(ADTCommonDateUtils.getDate(admissionPatientMapperDto.getPdd(), "dd-M-yyyy HH:mm:ss"));
			admissionPatientMapper
					.setDoa(ADTCommonDateUtils.getDate(admissionPatientMapperDto.getDoa(), "dd-M-yyyy HH:mm:ss"));
			admissionPatientMapper.setUpdatedBy(admissionPatientMapperDto.getUpdatedBy());
			admissionPatientMapper.setUpdatedDate(
					ADTCommonDateUtils.getDate(admissionPatientMapperDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss"));
			admissionPatientMapper.setCreatedDate(
					ADTCommonDateUtils.getDate(admissionPatientMapperDto.getCreatedDate(), "dd-M-yyyy HH:mm:ss"));
			admissionPatientMapper.setStatus('A');
			admissionPatientMapper.setActiveStatus('P'); // P -----> Pending
			session.save(admissionPatientMapper);
			tr2.commit();
			session.close();
			return new Response<AdmissionNoteDto, Integer>(SUCCESS, null, COMMON_SUCCESS, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();
			return new Response<AdmissionNoteDto, Integer>(ERROR, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<AdmissionNoteDto, Integer>(ERROR, null, null, null, null);
		} finally {
			 ;
		}
	}

	@Override
	public Response getPreviousData(AdmissionNoteDto admissionNoteDto) throws ApplicationException {
		try {
			List<AdmissionPatientMapperDto> admissionPatientMapperDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_PREVIOUS_DATA")
					.setInteger("admissionNoteId", admissionNoteDto.getAdmissionNoteId())
					.setResultTransformer(Transformers.aliasToBean(AdmissionPatientMapperDto.class)).list();
			return new Response(SUCCESS, null, null, admissionPatientMapperDtosList, null);

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
	public Response updateReserveBedDetails(AdmissionNoteDto admissionNoteDto) throws ApplicationException {
/*		int admissionPatientId2 = 0;
		Response res = getPreviousData(admissionNoteDto);
		List<AdmissionPatientMapperDto> admissionNoteDtosList = res.getListObject();
		for (AdmissionPatientMapperDto a : admissionNoteDtosList)
			admissionPatientId2 = a.getAdmissionPatientId();*/
		Session session = sessionFactory.openSession();
		//<-----
		Transaction tr1 = session.beginTransaction();

		Query qry1 = session.createQuery("update AdmissionNote an set an.admissionStatus='R', " + "an.updatedBy="
				+ admissionNoteDto.getUpdatedBy() + ", an.updatedDate='"+ADTCommonDateUtils.getDate(admissionNoteDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss")+ "' where an.admissionNoteId="
				+ admissionNoteDto.getAdmissionNoteId() + " AND an.status='A'");

		qry1.executeUpdate();
		tr1.commit();
		//----->
		
		//<-----
		Transaction tr2 = session.beginTransaction();

		Query qry = session.createQuery("update AdmissionPatientMapper m set m.status='I', " + "m.updatedBy="
				+ admissionNoteDto.getUpdatedBy() + ", m.updatedDate='"+ADTCommonDateUtils.getDate(admissionNoteDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss")+"' where m.admissionNoteId="
				+ admissionNoteDto.getAdmissionNoteId() + " AND m.status='A'");

		qry.executeUpdate();
		tr2.commit();
		//----->
		
		//<-----
		Transaction tr3=session.beginTransaction();
		AdmissionPatientMapper admissionPatientMapper = new AdmissionPatientMapper();
		admissionPatientMapper.setAdmissionNoteId(admissionNoteDto.getAdmissionNoteId());
		admissionPatientMapper.setBedCategoryId(admissionNoteDto.getBedCategoryId());
		admissionPatientMapper.setPatientCategoryId(admissionNoteDto.getPatientCategoryId());
		admissionPatientMapper.setPaymentEntitlementId(admissionNoteDto.getPaymentEntitlementId());
		admissionPatientMapper
				.setPdd(ADTCommonDateUtils.getDate(admissionNoteDto.getPdd(), "dd-M-yyyy HH:mm:ss"));
		admissionPatientMapper
				.setDoa(ADTCommonDateUtils.getDate(admissionNoteDto.getDoa(), "dd-M-yyyy HH:mm:ss"));
		admissionPatientMapper.setUpdatedBy(admissionNoteDto.getUpdatedBy());
		admissionPatientMapper.setUpdatedDate(
				ADTCommonDateUtils.getDate(admissionNoteDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss"));
		admissionPatientMapper.setCreatedDate(
				ADTCommonDateUtils.getDate(admissionNoteDto.getCreatedDate(), "dd-M-yyyy HH:mm:ss"));
		admissionPatientMapper.setStatus('A');
		admissionPatientMapper.setActiveStatus('R'); // R -------> Reserved
		session.save(admissionPatientMapper);
	
		
		tr3.commit();
		//----->
		session.close();
		return new Response<AdmissionNoteDto, Integer>(SUCCESS, null, COMMON_SUCCESS, null, null);
	}

	@Override
	public Response uploadAdmissionDocuments(AdmissionPatientDocuments admissionPatientDocuments)
			throws ApplicationException {
		try {

			/*AdmissionPatientDocuments admissionPatientDocuments = mapper.map(admissionPatientDocumentsDto,
					AdmissionPatientDocuments.class, "AdmissionPatientDocumentsDto_to_AdmissionPatientDocuments");*/
			Session session = sessionFactory.openSession();
			Transaction tr = session.beginTransaction();
			session.save(admissionPatientDocuments);
			tr.commit();
			session.close();
			return new Response<KinDetailsDto, Integer>(SUCCESS, null, COMMON_SUCCESS, null, null);
		} catch (HibernateException he) {
			he.printStackTrace();
			return new Response<KinDetailsDto, Integer>(ERROR, null, null, null, null);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<KinDetailsDto, Integer>(ERROR, null, null, null, null);
		} finally {
			 ;
		}
	}
	
	public Response getCount(AdmissionNoteDto admissionNoteDto) throws ApplicationException {
		try {
			List<AdmissionNoteDto> admissionNoteDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_PENDING_ADMISSION_REQUEST_DETAILS")
					.setInteger("organizationId", admissionNoteDto.getOrganizationId())
					.setInteger("unitId", admissionNoteDto.getUnitId())
					.setResultTransformer(Transformers.aliasToBean(AdmissionNoteDto.class)).list();
			
			Integer count=admissionNoteDtosList.size();
			
			return new Response(SUCCESS, null, null, null, count);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			 ;
		}
		return new Response(ERROR, null, null, null, null);
	}

}
