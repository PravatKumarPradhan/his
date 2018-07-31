package com.param.adt.discharge.dao;

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

import com.param.adt.common.ADTCommonDateUtils;
import com.param.adt.common.ICommonConstants;
import com.param.adt.common.Response;
import com.param.adt.discharge.dto.DischargeDto;
import com.param.adt.discharge.model.Discharge;
import com.param.adt.housekeeping.model.Housekeeping;
import com.param.adt.housekeeping.model.HousekeepingLog;
import com.param.adt.master.global.model.BedLogStatus;
import com.param.global.dto.AdmissionDto;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@SuppressWarnings({ "rawtypes", "unchecked" })
@Repository
public class MarkedForDischargeDaoImpl extends GenericDao<Discharge, Integer>
		implements IMarkedForDischargeDao, ICommonConstants {

	public MarkedForDischargeDaoImpl() {
		super(Discharge.class);
		// TODO Auto-generated constructor stub
	}

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	DozerBeanMapper mapper;

	@Override
	public Response getDischagePatientListForNursing(DischargeDto dischargeDto) throws ApplicationException {
		try {
			List<AdmissionDto> admissionDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_DISCHARGE_PATIENT_LIST_FOR_NURSING")
					.setInteger("orgId", dischargeDto.getOrganizationId())
					.setInteger("unitId", dischargeDto.getUnitId())
					.setDate("date", ADTCommonDateUtils.getDate(dischargeDto.getDischargeDate(), "dd-M-yyyy"))
					.setResultTransformer(Transformers.aliasToBean(AdmissionDto.class)).list();
			return new Response(SUCCESS, null, null, admissionDtosList, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		} 
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getPatientServiceOrderList(AdmissionDto admissionDto) throws ApplicationException {
		try {
			List<AdmissionDto> admissionDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_PATIENT_SERVICE_ORDER_LIST")
					.setInteger("orgId", admissionDto.getOrganizationId())
					.setInteger("unitId", admissionDto.getUnitId())
					.setInteger("admissionId", admissionDto.getAdmissionId())
					.setResultTransformer(Transformers.aliasToBean(AdmissionDto.class)).list();
			return new Response(SUCCESS, null, null, admissionDtosList, null);

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
	public Response updatePatientServiceOrder(AdmissionDto admissionDto) throws ApplicationException {
		try {

			Session session = sessionFactory.openSession();
			Query query = session.createQuery("update PatientServicesOrder pso set pso.orderStatusId="
					+ admissionDto.getOrderStatusId() + " ,pso.cancelReasonId=" + admissionDto.getReasonId()
					+ " ,pso.updatedBy=" + admissionDto.getUpdatedBy() + " ,pso.updatedDate='"
					+ ADTCommonDateUtils.getDate(admissionDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss")
					+ "' WHERE pso.organizationId=" + admissionDto.getOrganizationId() + " AND pso.unitId="
					+ admissionDto.getUnitId() + " AND pso.patientSevicesOrderId="
					+ admissionDto.getPatientSevicesOrderId());
			query.executeUpdate();
			return new Response(SUCCESS, null, null, null, null);

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
	public Response getPatientReadyForBillingList(DischargeDto dischargeDto) throws ApplicationException {
		try {
			List<AdmissionDto> admissionDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_PATIENT_READY_FOR_BILLING")
					.setInteger("orgId", dischargeDto.getOrganizationId())
					.setInteger("unitId", dischargeDto.getUnitId())
					.setInteger("visitTypeId", dischargeDto.getVisitTypeId())
					.setResultTransformer(Transformers.aliasToBean(AdmissionDto.class)).list();
			return new Response(SUCCESS, null, null, admissionDtosList, null);

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
	public Response updatePatientReadyForBillingStatus(DischargeDto dischargeDto) throws ApplicationException {
		try {

			Session session = sessionFactory.openSession();
			Query query = session.createQuery(
					"update Discharge dis set dis.updatedBy=" + dischargeDto.getUpdatedBy() + ",dis.updatedDate='"
							+ ADTCommonDateUtils.getDate(dischargeDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss")
							+ "' ,dis.dischargeStatusId=" + dischargeDto.getDischargeStatusId()
							+ " WHERE dis.dischargeId=" + dischargeDto.getDischargeId() + " AND dis.organizationId="
							+ dischargeDto.getOrganizationId() + " AND dis.unitId=" + dischargeDto.getUnitId()+" AND dis.status='A' ");
			query.executeUpdate();
			return new Response(SUCCESS, null, null, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		} 
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getPatientFinalDischargeList(DischargeDto dischargeDto) throws ApplicationException {
		try {
			List<AdmissionDto> admissionDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_PATIENT_FINAL_DISCHARGE_LIST")
					.setInteger("orgId", dischargeDto.getOrganizationId())
					.setInteger("unitId", dischargeDto.getUnitId())
					.setInteger("visitTypeId", dischargeDto.getVisitTypeId())
					.setResultTransformer(Transformers.aliasToBean(AdmissionDto.class)).list();
			return new Response(SUCCESS, null, null, admissionDtosList, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response vacateBed(DischargeDto dischargeDto) throws ApplicationException {
		try {

			Session session = sessionFactory.openSession();
			// <-----Updating status of Admission to I
			Query query1 = session.createQuery(
					"update Admission adm set adm.updatedBy=" + dischargeDto.getUpdatedBy() + ",adm.updatedDate='"
							+ ADTCommonDateUtils.getDate(dischargeDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss")
							+ "' ,adm.status='I' WHERE adm.admissionId=" + dischargeDto.getAdmissionId()
							+ " AND adm.organizationId=" + dischargeDto.getOrganizationId() + " AND adm.unitId="
							+ dischargeDto.getUnitId());
			query1.executeUpdate();
			// ----->

			// <-----Updating status of AdmissionDetails to I AND isDischarge to D
			Query query2 = session.createQuery("update AdmissionDetails adm set adm.updatedBy="
					+ dischargeDto.getUpdatedBy() + ",adm.updatedDate='"
					+ ADTCommonDateUtils.getDate(dischargeDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss")
					+ "' ,adm.status='I',adm.isDischarge='D' WHERE adm.admissionId=" + dischargeDto.getAdmissionId()
					+ " AND adm.organizationId=" + dischargeDto.getOrganizationId() + " AND adm.unitId="
					+ dischargeDto.getUnitId());
			query2.executeUpdate();
			// ----->

			// <-----Updating status of Discharge to 5(DISCHARGED)
			Query query5 = session.createQuery(
					"update Discharge dis set dis.updatedBy=" + dischargeDto.getUpdatedBy() + ",dis.updatedDate='"
							+ ADTCommonDateUtils.getDate(dischargeDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss")
							+ "' ,dis.dischargeStatusId=5 WHERE dis.dischargeId=" + dischargeDto.getDischargeId()
							+ " AND dis.organizationId=" + dischargeDto.getOrganizationId() + " AND dis.unitId="
							+ dischargeDto.getUnitId());
			query5.executeUpdate();
			// ----->

			if (dischargeDto.getBedId() != null) 
			{
				// <-----Updating status of AdmissionDetails to I
				Query query3 = session.createQuery("update BedMaster bm set bm.updatedBy=" + dischargeDto.getUpdatedBy()
						+ ",bm.updatedDate='"
						+ ADTCommonDateUtils.getDate(dischargeDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss")
						+ "' ,bm.bedStatusId="+dischargeDto.getBedStatusId()+" WHERE bm.bedId=" + dischargeDto.getBedId() + " AND bm.organizationId="
						+ dischargeDto.getOrganizationId() + " AND bm.unitId=" + dischargeDto.getUnitId());
				query3.executeUpdate();
				// ----->

				// <-----Updating status of AdmissionDetails to I
				Query query4 = session.createQuery("update BedLogStatus bm set bm.updatedBy="
						+ dischargeDto.getUpdatedBy() + ",bm.updatedDate='"
						+ ADTCommonDateUtils.getDate(dischargeDto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss")
						+ "' ,bm.status='I' WHERE bm.bedId=" + dischargeDto.getBedId() + " AND bm.organizationId="
						+ dischargeDto.getOrganizationId() + " AND bm.unitId=" + dischargeDto.getUnitId());
				query4.executeUpdate();
				// ----->

				// <-----Adding new entry in BedLogStatus
				Transaction tr = session.beginTransaction();
				BedLogStatus bedLogStatus = mapper.map(dischargeDto, BedLogStatus.class,
						"DischargeDto_to_BedLogStatus");
				session.save(bedLogStatus);
				tr.commit();
				// ----->
				
				
				// <-----Adding new entry in Housekeeping
				Transaction tr2 = session.beginTransaction();
				Housekeeping housekeeping = new Housekeeping();
					housekeeping.setOrganizationId(dischargeDto.getOrganizationId());
					housekeeping.setUnitId(dischargeDto.getUnitId());
					housekeeping.setNursingStationId(dischargeDto.getNursingStationId());
					housekeeping.setFloorId(dischargeDto.getFloorId());
					housekeeping.setWardId(dischargeDto.getWardId());
					housekeeping.setRoomId(dischargeDto.getRoomId());
					housekeeping.setBedId(dischargeDto.getBedId());
					housekeeping.setBedCategoryId(dischargeDto.getBedCategoryId());
					
					if(dischargeDto.getIsIcu()=='Y')
						housekeeping.setPriorityId(1);
					else
						housekeeping.setPriorityId(2);
					
					housekeeping.setHousekeepingStatusId(1);
					housekeeping.setStatus('A');
					housekeeping.setCreatedBy(dischargeDto.getUpdatedBy());
					housekeeping.setUpdatedBy(dischargeDto.getUpdatedBy());
					housekeeping.setCreatedDate(ADTCommonDateUtils.getDate(dischargeDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss"));
					housekeeping.setUpdatedDate(ADTCommonDateUtils.getDate(dischargeDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss"));
				Integer id = (Integer)session.save(housekeeping);
				System.out.println(id);
				dischargeDto.setHousekeepingId(id);
				tr2.commit();
				// ----->
			}
			return new Response(SUCCESS, null, null, null, dischargeDto);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		} 
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response saveHousekeepingLog(DischargeDto dischargeDto2) throws ApplicationException {
		try {	
			Session session=sessionFactory.openSession();
			Transaction tr=session.beginTransaction();
			HousekeepingLog housekeepingLog=new HousekeepingLog();
				housekeepingLog.setHousekeepingId(dischargeDto2.getHousekeepingId());
				housekeepingLog.setOrganizationId(dischargeDto2.getOrganizationId());
				housekeepingLog.setUnitId(dischargeDto2.getUnitId());
				housekeepingLog.setPriorityId(dischargeDto2.getPriorityId());
				housekeepingLog.setHousekeepingStatusId(1);
				housekeepingLog.setCreatedBy(dischargeDto2.getUpdatedBy());
				housekeepingLog.setUpdatedBy(dischargeDto2.getUpdatedBy());
				housekeepingLog.setCreatedDate(ADTCommonDateUtils.getDate(dischargeDto2.getUpdatedDate(),"dd-M-yyyy HH:mm:ss"));
				housekeepingLog.setUpdatedDate(ADTCommonDateUtils.getDate(dischargeDto2.getUpdatedDate(),"dd-M-yyyy HH:mm:ss"));
				housekeepingLog.setStatus('A');
			session.save(housekeepingLog);
			tr.commit();
			return new Response(SUCCESS, null, null, null, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

}
