package com.param.adt.admission.dao;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.param.adt.admission.dto.AdmissionNoteDto;
import com.param.adt.admission.dto.UnreservedPatientDto;
import com.param.adt.admission.model.AdmissionPatientMapper;
import com.param.adt.admission.model.UnreservedPatient;
import com.param.adt.common.ADTCommonDateUtils;
import com.param.adt.common.ICommonConstants;
import com.param.adt.common.Response;
import com.param.global.dto.AdmissionDto;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@SuppressWarnings({ "rawtypes", "unchecked" })
@Repository
public class BedReservationListDaoImpl extends GenericDao<UnreservedPatient, Integer>
		implements IBedReservationListDao, ICommonConstants {

	public BedReservationListDaoImpl() {
		super(UnreservedPatient.class);
		// TODO Auto-generated constructor stub
	}

	@Autowired
	DozerBeanMapper mapper;

	@Autowired
	SessionFactory sessoinFactory;

	@Override
	public Response getBedReservationList(AdmissionDto admissionDto) {
		try {

			List<AdmissionNoteDto> admissionNoteDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_BED_RESERVATION_LIST_DETAILS")
					.setFirstResult(admissionDto.getOffset() != null ? admissionDto.getOffset() : 0)
					.setMaxResults(admissionDto.getNoOfRecordsPerPage() != null ? admissionDto.getNoOfRecordsPerPage() : 10)
					.setInteger("organizationId", admissionDto.getOrganizationId())
					.setInteger("unitId", admissionDto.getUnitId())
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
	public Response cancelReservation(UnreservedPatientDto unreservedPatientDto) throws ApplicationException {
		try {

			Session session = sessionFactory.openSession();
			// <-----Updating status in AdmissionNote to I from A, activating is
			// cancledBy Flag to Y
			Transaction tr1 = session.beginTransaction();

			Query qr1 = session.createQuery(
					"update AdmissionNote an set an.status='I', an.isCancel='Y', an.canceledDate=now(), an.updatedBy="
							+ unreservedPatientDto.getUpdatedBy() + ", an.updatedDate='"+ADTCommonDateUtils.getDate(unreservedPatientDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss")+"' where an.admissionNoteId="
							+ unreservedPatientDto.getAdmissionNoteId());
			qr1.executeUpdate();
			tr1.commit();
			// ----->

			// <-----Updating status to I and active Status to C(Cancelled) in
			// Admission Patient Mapping
			Transaction tr2 = session.beginTransaction();

			Query qr2 = session.createQuery(
					"update AdmissionPatientMapper apm set apm.status='I',apm.activeStatus='I', apm.updatedBy="
							+ unreservedPatientDto.getUpdatedBy() + ", apm.updatedDate='"+ADTCommonDateUtils.getDate(unreservedPatientDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss")+"' WHERE apm.admissionNoteId="
							+ unreservedPatientDto.getAdmissionNoteId());
			qr2.executeUpdate();
			tr2.commit();
			// ----->

			// <----- Adding a entry for cancellation in Unreserve Patient
			// transaction
			Transaction tr = session.beginTransaction();

			UnreservedPatient unreservedPatient = new UnreservedPatient();
			unreservedPatient.setAdmissionNoteId(unreservedPatientDto.getAdmissionNoteId());
			unreservedPatient.setIsCancelReservation(unreservedPatientDto.getIsCancelReservation());
			unreservedPatient.setReasonForCancelation(unreservedPatientDto.getReasonForCancelation());
			unreservedPatient.setDoa(ADTCommonDateUtils.getDate(unreservedPatientDto.getDoa(), "dd-M-yyyy HH:mm:ss"));
			// unreservedPatient.setPatientId(unreservedPatientDto.getPatientId());
			unreservedPatient.setCreatedDate(
					ADTCommonDateUtils.getDate(unreservedPatientDto.getCreatedDate(), "dd-M-yyyy HH:mm:ss"));
			unreservedPatient.setUpdatedDate(
					ADTCommonDateUtils.getDate(unreservedPatientDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss"));
			unreservedPatient.setUpdatedBy(unreservedPatientDto.getUpdatedBy());
			unreservedPatient.setCreatedBy(unreservedPatientDto.getCreatedBy());
			unreservedPatient.setReasonForAdmission(unreservedPatientDto.getReasonForAdmission());
			unreservedPatient.setStatus(unreservedPatientDto.getStatus());
			unreservedPatient.setIsCancelReservation(unreservedPatient.getIsCancelReservation());
			unreservedPatient.setIsRescheduleReservation(unreservedPatientDto.getIsRescheduleReservation());
			unreservedPatient.setIsFlexible(unreservedPatientDto.getIsFlexible());
			session.save(unreservedPatient);
			tr.commit();
			// ----->

			session.close();
			return new Response(SUCCESS, null, null, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);

	}

	@Override
	public Response rescheduleReservation(UnreservedPatientDto unreservedPatientDto) throws ApplicationException {
		try {
			Session session = sessionFactory.openSession();
			// <-----Updating status in AdmissionNote to I from A
			Transaction tr1 = session.beginTransaction();

			Query qr1 = session.createQuery("update AdmissionNote an set an.admissionStatus='P'," + " an.updatedBy="
					+ unreservedPatientDto.getUpdatedBy() + ", an.updatedDate='"+ADTCommonDateUtils.getDate(unreservedPatientDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss")+"' where an.admissionNoteId="
					+ unreservedPatientDto.getAdmissionNoteId());
			qr1.executeUpdate();
			tr1.commit();
			// ----->

			// <-----Updating status to I and active Status to RS(Reschedule) in
			// Admission Patient Mapping
			Transaction tr2 = session.beginTransaction();

			Query qr2 = session.createQuery(
					"update AdmissionPatientMapper apm set apm.status='I',apm.activeStatus='I', apm.updatedBy="
							+ unreservedPatientDto.getUpdatedBy() + ", apm.updatedDate='"+ADTCommonDateUtils.getDate(unreservedPatientDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss")+"' WHERE apm.admissionNoteId="
							+ unreservedPatientDto.getAdmissionNoteId());
			qr2.executeUpdate();
			tr2.commit();
			// ----->
			// <----- Adding new entry in Admission Patient Mapper
			AdmissionPatientMapper admissionPatientMapper = mapper.map(unreservedPatientDto,
					AdmissionPatientMapper.class, "UnreservedPatientDto_to_AdmissionPatientMapper");
			Transaction tr3 = session.beginTransaction();

			session.save(admissionPatientMapper);

			tr3.commit();
			// ----->
			// <----- Adding a entry for cancellation in Unreserve Patient
			// transaction
			Transaction tr = session.beginTransaction();

			UnreservedPatient unreservedPatient = new UnreservedPatient();
			unreservedPatient.setAdmissionNoteId(unreservedPatientDto.getAdmissionNoteId());
			unreservedPatient
					.setNewDoa(ADTCommonDateUtils.getDate(unreservedPatientDto.getNewDoa(), "dd-M-yyyy HH:mm:ss"));
			unreservedPatient.setDoa(ADTCommonDateUtils.getDate(unreservedPatientDto.getDoa(), "dd-M-yyyy HH:mm:ss"));
			unreservedPatient.setIsFlexible(unreservedPatientDto.getIsFlexible());
			unreservedPatient.setBedCategoryId(unreservedPatientDto.getBedCategoryId());
			unreservedPatient.setIsRescheduleReservation(unreservedPatient.getIsRescheduleReservation());
			unreservedPatient.setPatientId(unreservedPatientDto.getPatientId());
			unreservedPatient.setCreatedDate(
					ADTCommonDateUtils.getDate(unreservedPatientDto.getCreatedDate(), "dd-M-yyyy HH:mm:ss"));
			unreservedPatient.setUpdatedDate(
					ADTCommonDateUtils.getDate(unreservedPatientDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss"));
			unreservedPatient.setUpdatedBy(unreservedPatientDto.getUpdatedBy());
			unreservedPatient.setCreatedBy(unreservedPatientDto.getCreatedBy());
			unreservedPatient.setStatus(unreservedPatientDto.getStatus());
			unreservedPatient.setReasonForUnreserve(unreservedPatientDto.getReasonForUnreserve());
			unreservedPatient.setReasonForAdmission(unreservedPatientDto.getReasonForAdmission());
			unreservedPatient.setIsCancelReservation(unreservedPatient.getIsCancelReservation());
			unreservedPatient.setOrganizatoinId(unreservedPatientDto.getOrganizationId());
			unreservedPatient.setUnitId(unreservedPatientDto.getUnitId());
			session.save(unreservedPatient);
			tr.commit();
			// ----->

			session.close();
			return new Response(SUCCESS, null, null, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);

	}
	
	public Response getCount(AdmissionDto admissionDto) throws ApplicationException {
		try{
			List<AdmissionNoteDto> admissionNoteDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_BED_RESERVATION_LIST_DETAILS")
					.setInteger("organizationId", admissionDto.getOrganizationId())
					.setInteger("unitId", admissionDto.getUnitId())
					.setResultTransformer(Transformers.aliasToBean(AdmissionNoteDto.class)).list();	
			
			Integer count=admissionNoteDtosList.size();
			return new Response(SUCCESS, null, null, null, count);
		}catch(Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, null, null, null, null);
		}
	}

	
}
