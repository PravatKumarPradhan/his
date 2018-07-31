package com.param.global.opd.dao;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.param.global.common.GlobalCommonDateUtils;
import com.param.global.common.IAppointmentAndSlotStatusMasterConstants;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dto.PatientRegistrationDto;
import com.param.global.dto.SlotMasterDto;
import com.param.opd.appointment.dto.CountDto;
import com.param.opd.appointment.model.AppointmentMaster;

import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings({"rawtypes","unchecked"})
public class AppointmentSchedulingDaoImpl implements IAppointmentSchedulingDao,ICommonConstants,IAppointmentAndSlotStatusMasterConstants {

	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
	DozerBeanMapper mapper;
	

	@Override
	public Response scheduleAppointment(SlotMasterDto slotMasterDto) throws ApplicationException {
		try {
			
			AppointmentMaster appointmentMaster = mapper.map(slotMasterDto, AppointmentMaster.class,
					"SlotMasterDto_to_AppointmentMaster");
			appointmentMaster.setAppointmentStatusId(Pending);
			sessionFactory.getCurrentSession().save(appointmentMaster);
			
			sessionFactory.getCurrentSession().createQuery("update SlotMaster sm set "
					+ "sm.slotStatusId=:slotStatusId, "
					+ "sm.updatedBy=:updatedBy , "
					+ "sm.updatedDate=:updatedDate "
					+ "where sm.slotId=:slotId ")
				.setInteger("updatedBy", slotMasterDto.getUpdatedBy())
				.setDate("updatedDate",GlobalCommonDateUtils.getDate(slotMasterDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss"))
				.setInteger("slotId",slotMasterDto.getSlotId())
				.setInteger("slotStatusId",slotMasterDto.getSlotStatusId())
				
			.executeUpdate();
			
			return new Response(SUCCESS, SUCCESS_CODE, COMMON_SUCCESS, null, appointmentMaster);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	

	@Override
	public Response cancelScheduledAppointment(SlotMasterDto slotMasterDto) throws ApplicationException {
		try {
			//updating slotStatusId to 2(Cancelled) after canceling appointment. 
			sessionFactory.getCurrentSession().createQuery("update AppointmentMaster am set "
						+ "am.appointmentStatusId="+Cancelled+", "
						+ "am.cancelReasonId=:cancelReasonId, "
						+ "am.cancelledBy=:cancelledBy, "
						+ "am.updatedBy=:updatedBy, "
						+ "am.updatedDate=:updatedDate, "
						+ "am.status='I' "
						+ "WHERE am.appointmentId=:appointmentId")
					.setInteger("cancelReasonId", slotMasterDto.getCancelReasonId())
					.setCharacter("cancelledBy", slotMasterDto.getCancelledBy())
					.setInteger("updatedBy",slotMasterDto.getUpdatedBy())
					.setDate("updatedDate",GlobalCommonDateUtils.getDate(slotMasterDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss"))
					.setInteger("appointmentId", slotMasterDto.getAppointmentId())
			.executeUpdate();
			
			
			//updating slotStatusId to 1(vacant) after canceling appointment. 
			sessionFactory.getCurrentSession().createQuery("update SlotMaster sm set "
						+ "sm.slotStatusId="+Vaccant+", " //slot vacant
						+ "sm.updatedBy=:updatedBy, "
						+ "sm.updatedDate=:updatedDate "
						+ "where sm.slotId=:slotId ")
					.setInteger("updatedBy", slotMasterDto.getUpdatedBy())
					.setDate("updatedDate",GlobalCommonDateUtils.getDate(slotMasterDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss"))
					.setInteger("slotId",slotMasterDto.getSlotId())
			.executeUpdate();
			
			
			return new Response(SUCCESS, null, "Appointment Cancelled Successfully", null, null);
			
		} catch (HibernateException he) {
			he.printStackTrace();

		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response rescheduleAppointment(SlotMasterDto slotMasterDto) throws ApplicationException {
		try {
			Session session= sessionFactory.getCurrentSession();
			
			// Rescheduling the appointment by deactivating previous appointment by changing the status to I and appointmentStatusId to 3 
			session.createQuery("update AppointmentMaster am set "
						+ "am.appointmentStatusId="+Rescheduled+", "	//appointmentStatusId=3 ==> For rescheduling 
						+ "am.rescheduleReasonId=:rescheduleReasonId, "
						+ "am.rescheduledBy=:rescheduledBy, "
						+ "am.updatedBy=:updatedBy, "
						+ "am.updatedDate=:updatedDate, "
						+ "am.status='I' "
						+ "WHERE am.appointmentId=:appointmentId")
					.setInteger("rescheduleReasonId", slotMasterDto.getRescheduleReasonId())
					.setCharacter("rescheduledBy", slotMasterDto.getRescheduledBy())
					.setInteger("updatedBy",slotMasterDto.getUpdatedBy())
					.setDate("updatedDate",GlobalCommonDateUtils.getDate(slotMasterDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss"))
					.setInteger("appointmentId", slotMasterDto.getAppointmentId())
			.executeUpdate();
			
			
			//updating slotStatusId to 1(vacant) for previous slot after rescheduling appointment. 
			session.createQuery("update SlotMaster sm set "
						+ "sm.slotStatusId="+Vaccant+", " //slot vacant
						+ "sm.updatedBy=:updatedBy, "
						+ "sm.updatedDate=:updatedDate "
						+ "where sm.slotId=:slotId")
					.setInteger("updatedBy", slotMasterDto.getUpdatedBy())
					.setDate("updatedDate",GlobalCommonDateUtils.getDate(slotMasterDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss"))
					.setInteger("slotId",slotMasterDto.getPreviousSlotId())
			.executeUpdate();
			
			//Adding new entry in appointmentMaster with new slot id 
			AppointmentMaster appointmentMaster = mapper.map(slotMasterDto, AppointmentMaster.class,
					"SlotMasterDto_to_AppointmentMaster");
				appointmentMaster.setAppointmentStatusId(Vaccant);
				appointmentMaster.setCancelledBy('\u0000');
				appointmentMaster.setRescheduledBy('\u0000');
				appointmentMaster.setRescheduleReasonId(null);
				appointmentMaster.setCancelReasonId(null);
			session.save(appointmentMaster);
			
			//updating slotStatusId to 4(appointment booked) after saving appointment. 
			session.createQuery("update SlotMaster sm set "
						+ "sm.slotStatusId="+Booked+", " //slot booked
						+ "sm.updatedBy=:updatedBy, "
						+ "sm.updatedDate=:updatedDate "
						+ "where sm.slotId=:slotId ")
					.setInteger("updatedBy", slotMasterDto.getUpdatedBy())
					.setDate("updatedDate",GlobalCommonDateUtils.getDate(slotMasterDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss"))
					.setInteger("slotId",slotMasterDto.getPreviousSlotId())
			.executeUpdate();
			
			return new Response(SUCCESS, null, "Appointment Rescheduled Successfully", null, null);
			
		} catch (HibernateException he) {
			he.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}
	
	@Override
	public Response updateAppointmentStatus(SlotMasterDto slotMasterDto) throws ApplicationException {
		try {
			System.out.println("appointmentId:"+slotMasterDto.getAppointmentId());
			sessionFactory.getCurrentSession().createQuery("update AppointmentMaster set "
					+ "appointmentStatusId=:appointmentStatusId, "
					+ "updatedBy=:updatedBy, "
					+ "updatedDate=:updatedDate "
					+ "WHERE appointmentId=:appointmentId")
				.setInteger("appointmentStatusId", slotMasterDto.getAppointmentStatusId())
				.setInteger("updatedBy",slotMasterDto.getUpdatedBy())
				.setDate("updatedDate",GlobalCommonDateUtils.getDate(slotMasterDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss"))
				.setInteger("appointmentId", slotMasterDto.getAppointmentId())
			.executeUpdate();
			return new Response(SUCCESS, null, null, null, null);
			
		} catch (HibernateException he) {
			he.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response searchAppointmentsByCriteria(SlotMasterDto slotMasterDto) throws ApplicationException {
		try {
			StringBuilder stringQuery=new StringBuilder(sessionFactory.getCurrentSession().getNamedQuery("GET_SCHEDULED_APPOINTMENTS_SLOTS_LIST").getQueryString().toString());

			SQLQuery query=sessionFactory.getCurrentSession().createSQLQuery((this.getFinalQueryAfterAppendConditions(stringQuery,slotMasterDto)).toString());
				
			List<SlotMasterDto> slotMasterDtosList = query
				.setInteger("orgId", slotMasterDto.getOrganizationId())
				.setInteger("unitId", slotMasterDto.getUnitId())
				.setInteger("appointmentTypeId", slotMasterDto.getAppointmentTypeId())
				.setResultTransformer(Transformers.aliasToBean(SlotMasterDto.class)).list();
			Integer count=slotMasterDtosList.size();
			return new Response(SUCCESS, null, null, slotMasterDtosList, count);
			
		} catch (HibernateException he) {
			he.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response scheduledAppointmentList(SlotMasterDto slotMasterDto) throws ApplicationException {
		try {
			List<SlotMasterDto> slotMasterDtosList=null;
			List<CountDto> countDtoList=null;
			StringBuilder stringQuery=null;
			SQLQuery query=null;
			
			stringQuery=new StringBuilder(sessionFactory.getCurrentSession().getNamedQuery("GET_SCHEDULED_APPOINTMENTS_SLOTS_LIST").getQueryString().toString());
			//For All Appointments
			if(slotMasterDto.getAppointmentStatusId()==All){
				query=sessionFactory.getCurrentSession().createSQLQuery((this.getFinalQueryAfterAppendConditions(stringQuery,slotMasterDto)).toString());
			}
			//For pending appointments
			if(slotMasterDto.getAppointmentStatusId()==Pending){
				stringQuery.append(" AND am.status='A' AND am.appointment_status_id="+Pending+" ");
				query=sessionFactory.getCurrentSession().createSQLQuery((this.getFinalQueryAfterAppendConditions(stringQuery,slotMasterDto)).toString());
			}
			//For rescheduled and cancelled appointments
			if(slotMasterDto.getAppointmentStatusId()==Rescheduled){
				stringQuery.append(" AND am.status='I' AND (am.appointment_status_id="+Cancelled+" OR am.appointment_status_id="+Rescheduled+") ");
				query=sessionFactory.getCurrentSession().createSQLQuery((this.getFinalQueryAfterAppendConditions(stringQuery,slotMasterDto)).toString());
			}
			//For CheckIn
			if(slotMasterDto.getAppointmentStatusId()==CheckedIn){
				stringQuery.append(" AND am.status='A' AND am.appointment_status_id="+CheckedIn+" ");
				query=sessionFactory.getCurrentSession().createSQLQuery((this.getFinalQueryAfterAppendConditions(stringQuery,slotMasterDto)).toString());
			}
			
			//For Admitted Patient List who are having appointments
			if(slotMasterDto.getAppointmentStatusId()==Admitted){
				stringQuery.append(" AND am.status='A' AND adm.status='A' ");
				query=sessionFactory.getCurrentSession().createSQLQuery((this.getFinalQueryAfterAppendConditions(stringQuery,slotMasterDto)).toString());
			}
			
			slotMasterDtosList = query
					.setInteger("orgId", slotMasterDto.getOrganizationId())
					.setInteger("unitId", slotMasterDto.getUnitId())
					.setInteger("appointmentTypeId", slotMasterDto.getAppointmentTypeId())
					.setResultTransformer(Transformers.aliasToBean(SlotMasterDto.class)).list();
			
			return new Response(SUCCESS, null, null, slotMasterDtosList, countDtoList);
			
		} catch (HibernateException he) {
			he.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	private StringBuilder getFinalQueryAfterAppendConditions(StringBuilder namedQuery, SlotMasterDto slotMasterDto) {
		try {
				
			if (slotMasterDto.getAppointmentDate() != null)
				namedQuery.append(" AND sm.appointment_date='" + GlobalCommonDateUtils.getDate(slotMasterDto.getAppointmentDate(), "yyyy-M-dd") + "' ");

			if (slotMasterDto.getPatientId() > 0)
				namedQuery.append(" AND am.patient_id = " + slotMasterDto.getPatientId());

			if (slotMasterDto.getFromDate() != null)
				namedQuery.append(" AND sm.appointment_date >='" + GlobalCommonDateUtils.getDate(slotMasterDto.getFromDate(), "yyyy-MM-dd") + "' ");

			if (slotMasterDto.getToDate() != null)
				namedQuery.append(" AND sm.appointment_date <='"	+ GlobalCommonDateUtils.getDate(slotMasterDto.getToDate(), "yyyy-MM-dd") + "' ");

			if (slotMasterDto.getSpecialityId() > 0)
				namedQuery.append(" AND sm.speciality_id =" + slotMasterDto.getSpecialityId());

			if (slotMasterDto.getDoctorId() > 0)
				namedQuery.append(" AND sm.doctor_id =" + slotMasterDto.getDoctorId());

			if (slotMasterDto.getSubSpecialityId() > 0)
				namedQuery.append(" AND sm.sub_speciality_id = " + slotMasterDto.getSubSpecialityId());

			if (slotMasterDto.getModalityId() > 0)
				namedQuery.append(" AND sm.modality_id = " + slotMasterDto.getModalityId());

			if (slotMasterDto.getVisitTypeId() > 0)
				namedQuery.append(" AND am.visit_type_id =" + slotMasterDto.getVisitTypeId());

			namedQuery.append(" ORDER BY am.appointment_id ");

			return namedQuery;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return namedQuery;
	}
	
	@Override
	public Response patientAutofillSearch(SlotMasterDto slotMasterDto) throws ApplicationException {
		try {
			
			List<PatientRegistrationDto> patientRegistrationDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("PATIENT_AUTO_FILL_SEARCH")
					.setParameter("patientName", slotMasterDto.getPatientName().toLowerCase() + "%")
					.setInteger("organizationId", slotMasterDto.getOrganizationId())
					.setInteger("unitId", slotMasterDto.getUnitId())
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
	public Response blockedScheduleAppointmentList(SlotMasterDto slotMasterDto) throws ApplicationException {
		try {
			
			StringBuilder rawQuery = new StringBuilder(sessionFactory.getCurrentSession()
					.getNamedQuery("GET_BLOCKED_SCHEDULED_APPOINTMENTS_SLOTS_LIST").getQueryString());
			SQLQuery query=sessionFactory.getCurrentSession().createSQLQuery((this.getFinalQueryAfterAppendConditions(rawQuery,slotMasterDto)).toString());
			
			List<SlotMasterDto> patientRegistrationDtosList = query
					.setInteger("orgId", slotMasterDto.getOrganizationId())
					.setInteger("unitId", slotMasterDto.getUnitId())
					.setInteger("appointmentTypeId", slotMasterDto.getAppointmentTypeId())
					.setResultTransformer(Transformers.aliasToBean(SlotMasterDto.class)).list();
			return new Response(SUCCESS, null, null, patientRegistrationDtosList, null);
		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response isAppointmentValid(SlotMasterDto slotMasterDto) throws ApplicationException {
		try {
			
			Query query = sessionFactory.getCurrentSession().createQuery("SELECT sm.slotId as slotId FROM SlotMaster sm "
					+ "WHERE sm.slotId=:slotId "
					+ "AND sm.slotStatusId=:slotStatusId ");
			
			List<SlotMasterDto> slotMasterDtosList = query
					.setInteger("slotId", slotMasterDto.getSlotId())
					.setInteger("slotStatusId", slotMasterDto.getSlotStatusId())
					.setResultTransformer(Transformers.aliasToBean(SlotMasterDto.class)).list();
			
			return new Response(SUCCESS, null, null, slotMasterDtosList, null);
		}catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		} 
		return new Response(ERROR, null, null, null, null);
	}

}
