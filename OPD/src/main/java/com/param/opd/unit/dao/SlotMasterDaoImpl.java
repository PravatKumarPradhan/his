package com.param.opd.unit.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.param.global.common.GlobalCommonDateUtils;
import com.param.global.common.IAppointmentAndSlotStatusMasterConstants;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dto.BlockSlotReqDto;
import com.param.global.dto.SlotBlockUnblockRequestDto;
import com.param.global.dto.SlotMasterDto;
import com.param.opd.unit.model.SlotMaster;

import in.param.common.dao.GenericDao;
import in.param.exception.ApplicationException;

@Repository
@SuppressWarnings({ "rawtypes", "unchecked" })
public class SlotMasterDaoImpl extends GenericDao<SlotMaster, Integer>implements ISlotMasterDao,ICommonConstants,IAppointmentAndSlotStatusMasterConstants{

	public SlotMasterDaoImpl() {
		super(SlotMaster.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Response generateSlots(SlotMasterDto slotMasterDtosObj) throws ApplicationException {
		try {
			SlotMaster slotMaster = new SlotMaster();
			slotMaster.setOrganizationId(slotMasterDtosObj.getOrganizationId());
			slotMaster.setUnitId(slotMasterDtosObj.getUnitId());
			slotMaster.setAppointmentDate(GlobalCommonDateUtils.getDate(slotMasterDtosObj.getFromDate(),"yyyy-M-dd"));
			slotMaster.setDayId(slotMasterDtosObj.getDayId());
			slotMaster.setCreatedDate(GlobalCommonDateUtils.getDate(slotMasterDtosObj.getCreatedDate(),"dd-M-yyyy HH:mm:ss"));
			slotMaster.setUpdatedDate(GlobalCommonDateUtils.getDate(slotMasterDtosObj.getUpdatedDate(),"dd-M-yyyy HH:mm:ss"));
			slotMaster.setCreatedBy(slotMasterDtosObj.getCreatedBy());
			slotMaster.setUpdatedBy(slotMasterDtosObj.getUpdatedBy());
			slotMaster.setStatus(slotMasterDtosObj.getStatus());
			slotMaster.setSlotTypeId(slotMasterDtosObj.getSlotTypeId());
			slotMaster.setSlotStatusId(Vaccant);
			if(slotMasterDtosObj.getSlotTypeId()!=SlotTypeECH) // these details does not require for ECH 
			{
				slotMaster.setFromTime(slotMasterDtosObj.getFromTime());
				slotMaster.setToTime(slotMasterDtosObj.getToTime());
				slotMaster.setSpecialityId(slotMasterDtosObj.getSpecialityId());
				slotMaster.setDoctorId(slotMasterDtosObj.getDoctorId());
				slotMaster.setModalityId(slotMasterDtosObj.getModalityId());
				slotMaster.setSubSpecialityId(slotMasterDtosObj.getSubSpecialityId());
			}
			save(slotMaster);

			return new Response(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, null, null);
		}catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getSlots(SlotMasterDto slotMasterDto) throws ApplicationException {
		try {
			List<SlotMasterDto> slotMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_ACTIVE_APPOINTMENTS")
					.setFirstResult(slotMasterDto.getOffset() != null ? slotMasterDto.getOffset() : 0)
					.setMaxResults(slotMasterDto.getNoOfRecordsPerPage() != null ? slotMasterDto.getNoOfRecordsPerPage() : 10)
					.setInteger("orgId", slotMasterDto.getOrganizationId())
					.setInteger("unitId", slotMasterDto.getUnitId())
					.setInteger("slotTypeId", slotMasterDto.getSlotTypeId())
					.setResultTransformer(Transformers.aliasToBean(SlotMasterDto.class)).list();
			return new Response(SUCCESS, null, null, slotMasterDtosList, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		} 
		return new Response(ERROR, null, null, null, null);
	}

	public Response getSlotCount(SlotMasterDto slotMasterDto) throws ApplicationException {
		try {
			List<SlotMasterDto> slotMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_ACTIVE_APPOINTMENTS")
					.setInteger("orgId", slotMasterDto.getOrganizationId())
					.setInteger("unitId", slotMasterDto.getUnitId())
					.setInteger("slotTypeId", slotMasterDto.getSlotTypeId())
					.setResultTransformer(Transformers.aliasToBean(SlotMasterDto.class)).list();
			Integer count=slotMasterDtosList.size();
			return new Response(SUCCESS, null, null, null, count);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		} 
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getSlotDetails(SlotMasterDto slotMasterDto) throws ApplicationException {
		try {
			//For doctor
			if(slotMasterDto.getSlotTypeId()==SlotTypeDoctor){

				StringBuilder stringQuery = new StringBuilder(sessionFactory.getCurrentSession().getNamedQuery("GET_APPOINTMENTS_SLOT_DETAILS").getQueryString().toString());
				
				stringQuery.append(" AND sm.specialityId=:specialityId"
						+ " AND sm.doctorId=:docId "
						+ " ORDER BY sm.slotId");

				Query query = sessionFactory.getCurrentSession().createQuery(stringQuery.toString());

				List<SlotMasterDto> slotMasterDtosList = query
						.setInteger("orgId", slotMasterDto.getOrganizationId())
						.setInteger("unitId", slotMasterDto.getUnitId())
						.setInteger("slotTypeId", slotMasterDto.getSlotTypeId())
						.setInteger("specialityId", slotMasterDto.getSpecialityId())
						.setInteger("docId", slotMasterDto.getDoctorId())
						.setDate("appointmentDate", GlobalCommonDateUtils.getDate(slotMasterDto.getAppointmentDate(),"yyyy-MM-dd"))
						.setInteger("dayId", slotMasterDto.getDayId())
						.setResultTransformer(Transformers.aliasToBean(SlotMasterDto.class)).list();
				return new Response(SUCCESS, null, null, slotMasterDtosList, null);
			}
			//For modality
			if(slotMasterDto.getSlotTypeId()==SlotTypeModality){

				StringBuilder stringQuery = new StringBuilder(sessionFactory.getCurrentSession().getNamedQuery("GET_APPOINTMENTS_SLOT_DETAILS").getQueryString().toString());
				stringQuery.append(" AND sm.modalityId=:modalityId "
						+ " AND sm.subSpecialityId=:subSpecialityId "
						+ " AND sm.specialityId=:specialityId "
						+ " ORDER BY sm.slotId");
				Query query = sessionFactory.getCurrentSession().createQuery(stringQuery.toString());

				List<SlotMasterDto> slotMasterDtosList = query
						.setInteger("orgId", slotMasterDto.getOrganizationId())
						.setInteger("unitId", slotMasterDto.getUnitId())
						.setInteger("slotTypeId", slotMasterDto.getSlotTypeId())
						.setInteger("modalityId", slotMasterDto.getModalityId())
						.setInteger("specialityId",slotMasterDto.getSpecialityId())
						.setInteger("subSpecialityId", slotMasterDto.getSubSpecialityId())
						.setDate("appointmentDate", GlobalCommonDateUtils.getDate(slotMasterDto.getAppointmentDate(),"yyyy-MM-dd"))
						.setInteger("dayId", slotMasterDto.getDayId())
						.setResultTransformer(Transformers.aliasToBean(SlotMasterDto.class)).list();
				return new Response(SUCCESS, null, null, slotMasterDtosList, null);
			}
			//For ECH
			if(slotMasterDto.getSlotTypeId()==SlotTypeECH){
				StringBuilder stringQuery = new StringBuilder(sessionFactory.getCurrentSession().getNamedQuery("GET_APPOINTMENTS_SLOT_DETAILS").getQueryString().toString());
				stringQuery.append(" ORDER BY sm.slotId");
				Query query = sessionFactory.getCurrentSession().createQuery(stringQuery.toString());

				List<SlotMasterDto> slotMasterDtosList = query
						.setInteger("orgId", slotMasterDto.getOrganizationId())
						.setInteger("unitId", slotMasterDto.getUnitId())
						.setInteger("slotTypeId", slotMasterDto.getSlotTypeId())
						.setDate("appointmentDate", GlobalCommonDateUtils.getDate(slotMasterDto.getAppointmentDate(),"yyyy-MM-dd"))
						.setInteger("dayId", slotMasterDto.getDayId())
						.setResultTransformer(Transformers.aliasToBean(SlotMasterDto.class)).list();
				return new Response(SUCCESS, null, null, slotMasterDtosList, null);
			}

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		} 
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateSlotStatus(SlotMasterDto slotMasterDto) throws ApplicationException {
		try {
			List<SlotMasterDto> slotMasterDtosList = sessionFactory.getCurrentSession()
					.getNamedQuery("GET_SLOT_STATUS")
					.setInteger("orgId", slotMasterDto.getOrganizationId())
					.setInteger("unitId", slotMasterDto.getUnitId())
					.setInteger("slotId", slotMasterDto.getSlotId())
					.setResultTransformer(Transformers.aliasToBean(SlotMasterDto.class)).list();

			if(slotMasterDtosList.isEmpty()){
				Query query=sessionFactory.getCurrentSession().createQuery("update SlotMaster sm set sm.status='I' "
						+ "WHERE sm.slotId=:slotId "
						+ "AND sm.organizationId=:orgId "
						+ "AND sm.unitId=:unitId ");
				query.setParameter("orgId", slotMasterDto.getOrganizationId());
				query.setParameter("unitId", slotMasterDto.getUnitId());
				query.setParameter("slotId", slotMasterDto.getSlotId());

				query.executeUpdate();

				return new Response(SUCCESS, SUCCESS_CODE, COMMON_UPDATE, null, null);
			}else			
				return new Response(ERROR, COMMON_ERROR_CODE, "Can not inactive this slot, appointment is booked on this slot !!", null, null);

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		} 
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getYearlySlots(SlotMasterDto slotMasterDto) throws ApplicationException {
		try {

			if(slotMasterDto.getSlotTypeId()==SlotTypeDoctor){
				List<SlotMasterDto> yearlySlotsForDoctorsList = sessionFactory.getCurrentSession()
						.createQuery("SELECT DISTINCT(to_char(sm.appointmentDate, 'Mon yyyy')) as appointmentDate, "
								+ "to_char(sm.appointmentDate, 'MM-yyyy') as fromDate 	"
								+ "FROM SlotMaster sm "
								+ "WHERE sm.organizationId=:orgId "
								+ "AND sm.unitId=:unitId "
								+ "AND sm.slotTypeId=:slotTypeId "
								+ "AND sm.doctorId=:doctorId "
								+ "AND sm.specialityId=:specialityId "
								+ "AND sm.status='A' "
								+ "ORDER BY fromDate ")
						.setInteger("orgId", slotMasterDto.getOrganizationId())
						.setInteger("unitId", slotMasterDto.getUnitId())
						.setInteger("slotTypeId", slotMasterDto.getSlotTypeId())
						.setInteger("doctorId", slotMasterDto.getDoctorId())
						.setInteger("specialityId", slotMasterDto.getSpecialityId())
						.setResultTransformer(Transformers.aliasToBean(SlotMasterDto.class)).list();
				return new Response(SUCCESS, null, null, yearlySlotsForDoctorsList, null);
			}
			if(slotMasterDto.getSlotTypeId()==SlotTypeModality){
				List<SlotMasterDto> yearlySlotsForModalitysList = sessionFactory.getCurrentSession()
						.createQuery("SELECT DISTINCT(to_char(sm.appointmentDate, 'Mon yyyy')) as appointmentDate, "
								+ "to_char(sm.appointmentDate, 'MM-yyyy') as fromDate 	"
								+ "FROM SlotMaster sm "
								+ "WHERE sm.organizationId=:orgId "
								+ "AND sm.unitId=:unitId "
								+ "AND sm.slotTypeId=:slotTypeId "
								+ "AND sm.modalityId=:modalityId "
								+ "AND sm.subSpecialityId=:subSpecialityId "
								+ "AND sm.specialityId=:specialityId "
								+ "AND sm.status='A' "
								+ "ORDER BY fromDate ")
						.setInteger("orgId", slotMasterDto.getOrganizationId())
						.setInteger("unitId", slotMasterDto.getUnitId())
						.setInteger("slotTypeId", slotMasterDto.getSlotTypeId())
						.setInteger("modalityId", slotMasterDto.getModalityId())
						.setInteger("specialityId",slotMasterDto.getSpecialityId())
						.setInteger("subSpecialityId", slotMasterDto.getSubSpecialityId())
						.setResultTransformer(Transformers.aliasToBean(SlotMasterDto.class)).list();
				return new Response(SUCCESS, null, null, yearlySlotsForModalitysList, null);
			}
			if(slotMasterDto.getSlotTypeId()==SlotTypeECH){
				List<SlotMasterDto> yearlySlotsForECHsList = sessionFactory.getCurrentSession()
						.createQuery("SELECT DISTINCT(to_char(sm.appointmentDate, 'Mon yyyy')) as appointmentDate, "
								+ "to_char(sm.appointmentDate, 'MM-yyyy') as fromDate 	"
								+ "FROM SlotMaster sm "
								+ "WHERE sm.organizationId=:orgId "
								+ "AND sm.unitId=:unitId "
								+ "AND sm.slotTypeId=:slotTypeId "
								+ "AND sm.status='A' "
								+ "ORDER BY fromDate ")
						.setInteger("orgId", slotMasterDto.getOrganizationId())
						.setInteger("unitId", slotMasterDto.getUnitId())
						.setInteger("slotTypeId", slotMasterDto.getSlotTypeId())
						.setResultTransformer(Transformers.aliasToBean(SlotMasterDto.class)).list();
				return new Response(SUCCESS, null, null, yearlySlotsForECHsList, null);

			}

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		} 
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public List<SlotMasterDto> getAppointmentsForMonth(SlotMasterDto slotMasterDto) throws ApplicationException {
		try {

			if(slotMasterDto.getSlotTypeId()==SlotTypeDoctor){
				List<SlotMasterDto> slotMasterDtosList = sessionFactory.getCurrentSession()
						.createQuery("SELECT DISTINCT to_char(sm.appointmentDate,'yyyy-mm-dd') as appointmentDate "
								+ "FROM SlotMaster sm "
								+ "WHERE sm.organizationId=:orgId "
								+ "AND sm.unitId=:unitId "
								+ "AND sm.slotTypeId=:slotTypeId "
								+ "AND sm.doctorId=:doctorId "
								+ "AND sm.specialityId=:specialityId "
								+ "AND sm.status='A' "
								+ "AND sm.appointmentDate BETWEEN :fromDate AND :toDate ")
						.setInteger("orgId", slotMasterDto.getOrganizationId())
						.setInteger("unitId", slotMasterDto.getUnitId())
						.setInteger("slotTypeId", slotMasterDto.getSlotTypeId())
						.setDate("fromDate", GlobalCommonDateUtils.getDate(slotMasterDto.getFromDate(),"yyyy-MM-dd"))
						.setDate("toDate", GlobalCommonDateUtils.getDate(slotMasterDto.getToDate(),"yyyy-MM-dd"))
						.setInteger("doctorId", slotMasterDto.getDoctorId())
						.setInteger("specialityId", slotMasterDto.getSpecialityId())
						.setResultTransformer(Transformers.aliasToBean(SlotMasterDto.class)).list();

				return slotMasterDtosList;
			}
			if(slotMasterDto.getSlotTypeId()==SlotTypeModality){
				List<SlotMasterDto> slotMasterDtosList = sessionFactory.getCurrentSession()
						.createQuery("SELECT DISTINCT to_char(sm.appointmentDate,'yyyy-mm-dd') as appointmentDate "
								+ "FROM SlotMaster sm "
								+ "WHERE sm.organizationId=:orgId "
								+ "AND sm.unitId=:unitId "
								+ "AND sm.slotTypeId=:slotTypeId "
								+ "AND sm.modalityId=:modalityId "
								+ "AND sm.subSpecialityId=:subSpecialityId "
								+ "AND sm.specialityId=:specialityId "
								+ "AND sm.status='A' "
								+ "AND sm.appointmentDate BETWEEN :fromDate AND :toDate ")
						.setInteger("orgId", slotMasterDto.getOrganizationId())
						.setInteger("unitId", slotMasterDto.getUnitId())
						.setInteger("slotTypeId", slotMasterDto.getSlotTypeId())
						.setInteger("modalityId", slotMasterDto.getModalityId())
						.setInteger("specialityId",slotMasterDto.getSpecialityId())
						.setInteger("subSpecialityId", slotMasterDto.getSubSpecialityId())
						.setDate("fromDate", GlobalCommonDateUtils.getDate(slotMasterDto.getFromDate(),"yyyy-MM-dd"))
						.setDate("toDate", GlobalCommonDateUtils.getDate(slotMasterDto.getToDate(),"yyyy-MM-dd"))
						.setResultTransformer(Transformers.aliasToBean(SlotMasterDto.class)).list();

				return slotMasterDtosList;
			}
			if(slotMasterDto.getSlotTypeId()==SlotTypeECH){
				List<SlotMasterDto> slotMasterDtosList = sessionFactory.getCurrentSession()
						.createQuery("SELECT DISTINCT to_char(sm.appointmentDate,'yyyy-mm-dd') as appointmentDate "
								+ "FROM SlotMaster sm "
								+ "WHERE sm.organizationId=:orgId "
								+ "AND sm.unitId=:unitId "
								+ "AND sm.slotTypeId=:slotTypeId "
								+ "AND sm.status='A' "
								+ "AND sm.appointmentDate BETWEEN :fromDate AND :toDate ")
						.setInteger("orgId", slotMasterDto.getOrganizationId())
						.setInteger("unitId", slotMasterDto.getUnitId())
						.setInteger("slotTypeId", slotMasterDto.getSlotTypeId())
						.setDate("fromDate", GlobalCommonDateUtils.getDate(slotMasterDto.getFromDate(),"yyyy-MM-dd"))
						.setDate("toDate", GlobalCommonDateUtils.getDate(slotMasterDto.getToDate(),"yyyy-MM-dd"))
						.setResultTransformer(Transformers.aliasToBean(SlotMasterDto.class)).list();

				return slotMasterDtosList;
			}

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}


	@Override
	public Response getDailySlots(SlotMasterDto slotMasterDto) throws ApplicationException {
		try {

			// For doctor
			if (slotMasterDto.getSlotTypeId() == SlotTypeDoctor) {
				StringBuilder stringQuery = new StringBuilder(sessionFactory.getCurrentSession().getNamedQuery("GET_APPOINTMENTS_SLOTS_BY_DATE").getQueryString().toString());
				stringQuery.append(" AND sm.speciality_id=:specialityId "
						+" AND sm.doctor_id=:docId "
						+" AND sm.appointment_date=:appointmentDate " 
						+" ORDER BY sm.slot_id");
				SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(stringQuery.toString());

				List<SlotMasterDto> slotMasterDtosList = query
						.setInteger("orgId", slotMasterDto.getOrganizationId())
						.setInteger("unitId", slotMasterDto.getUnitId())
						.setInteger("slotTypeId", slotMasterDto.getSlotTypeId())
						.setInteger("specialityId", slotMasterDto.getSpecialityId())
						.setInteger("docId", slotMasterDto.getDoctorId())
						.setDate("appointmentDate",GlobalCommonDateUtils.getDate(slotMasterDto.getAppointmentDate(), "yyyy-MM-dd"))
						.setResultTransformer(Transformers.aliasToBean(SlotMasterDto.class)).list();
				return new Response(SUCCESS, null, null, slotMasterDtosList, null);
			}
			// For modality
			if (slotMasterDto.getSlotTypeId() == SlotTypeModality) {
				StringBuilder stringQuery = new StringBuilder(sessionFactory.getCurrentSession().getNamedQuery("GET_APPOINTMENTS_SLOTS_BY_DATE").getQueryString().toString());
				stringQuery.append(" AND sm.modality_id=:modalityId "
						+ "AND sm.sub_speciality_id=:subSpecialityId "
						+ "AND sm.speciality_id=:specialityId "
						+ " AND sm.appointment_date=:appointmentDate " 
						+ " ORDER BY sm.slot_id");
				SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(stringQuery.toString());

				List<SlotMasterDto> slotMasterDtosList = query.setInteger("orgId", slotMasterDto.getOrganizationId())
						.setInteger("unitId", slotMasterDto.getUnitId())
						.setInteger("slotTypeId", slotMasterDto.getSlotTypeId())
						.setInteger("modalityId", slotMasterDto.getModalityId())
						.setInteger("specialityId",slotMasterDto.getSpecialityId())
						.setInteger("subSpecialityId", slotMasterDto.getSubSpecialityId())
						.setDate("appointmentDate",	GlobalCommonDateUtils.getDate(slotMasterDto.getAppointmentDate(), "yyyy-MM-dd"))
						.setResultTransformer(Transformers.aliasToBean(SlotMasterDto.class)).list();
				return new Response(SUCCESS, null, null, slotMasterDtosList, null);
			}
			// For ECH
			if (slotMasterDto.getSlotTypeId() == SlotTypeECH) {

				StringBuilder stringQuery = new StringBuilder(sessionFactory.getCurrentSession().getNamedQuery("GET_APPOINTMENTS_SLOTS_BY_DATE").getQueryString().toString());
				stringQuery.append(" AND sm.appointment_date=:appointmentDate " 
						+ " ORDER BY sm.slot_id");
				SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(stringQuery.toString());

				List<SlotMasterDto> slotMasterDtosList = query
						.setInteger("orgId", slotMasterDto.getOrganizationId())
						.setInteger("unitId", slotMasterDto.getUnitId())
						.setInteger("slotTypeId", slotMasterDto.getSlotTypeId())
						.setDate("appointmentDate",	GlobalCommonDateUtils.getDate(slotMasterDto.getAppointmentDate(), "yyyy-MM-dd"))
						.setResultTransformer(Transformers.aliasToBean(SlotMasterDto.class)).list();
				return new Response(SUCCESS, null, null, slotMasterDtosList, null);
			}
		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getWeeklySlots(SlotMasterDto slotMasterDto) throws ApplicationException {
		try{
			// For doctor
			if (slotMasterDto.getSlotTypeId() == SlotTypeDoctor) {

				StringBuilder stringQuery = new StringBuilder(sessionFactory.getCurrentSession().getNamedQuery("GET_APPOINTMENTS_SLOTS_BY_DATE").getQueryString().toString());
				stringQuery.append(" AND sm.speciality_id=:specialityId "
						+" AND sm.doctor_id=:docId "
						+" AND sm.appointment_date BETWEEN :fromDate AND :toDate " 
						+" ORDER BY sm.slot_id");
				SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(stringQuery.toString());

				List<SlotMasterDto> slotMasterDtosList = query
						.setInteger("orgId", slotMasterDto.getOrganizationId())
						.setInteger("unitId", slotMasterDto.getUnitId())
						.setInteger("slotTypeId", slotMasterDto.getSlotTypeId())
						.setInteger("specialityId", slotMasterDto.getSpecialityId())
						.setInteger("docId", slotMasterDto.getDoctorId())
						.setDate("fromDate",GlobalCommonDateUtils.getDate(slotMasterDto.getFromDate(), "yyyy-MM-dd"))
						.setDate("toDate",GlobalCommonDateUtils.getDate(slotMasterDto.getToDate(), "yyyy-MM-dd"))
						.setResultTransformer(Transformers.aliasToBean(SlotMasterDto.class)).list();
				return new Response(SUCCESS, null, null, slotMasterDtosList, null);
			}
			// For modality
			if (slotMasterDto.getSlotTypeId() == SlotTypeModality) {

				StringBuilder stringQuery = new StringBuilder(sessionFactory.getCurrentSession().getNamedQuery("GET_APPOINTMENTS_SLOTS_BY_DATE").getQueryString().toString());
				stringQuery.append(" AND sm.modality_id=:modalityId "
						+" AND sm.speciality_id=:specialityId "
						+" AND sm.sub_speciality_id=:subSpecialityId "
						+" AND sm.appointment_date BETWEEN :fromDate AND :toDate " 
						+" ORDER BY sm.slot_id");
				SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(stringQuery.toString());

				List<SlotMasterDto> slotMasterDtosList = query
						.setInteger("orgId", slotMasterDto.getOrganizationId())
						.setInteger("unitId", slotMasterDto.getUnitId())
						.setInteger("slotTypeId", slotMasterDto.getSlotTypeId())
						.setInteger("modalityId", slotMasterDto.getModalityId())
						.setInteger("specialityId", slotMasterDto.getSpecialityId())
						.setInteger("subSpecialityId", slotMasterDto.getSubSpecialityId())
						.setDate("fromDate",GlobalCommonDateUtils.getDate(slotMasterDto.getFromDate(), "yyyy-MM-dd"))
						.setDate("toDate",GlobalCommonDateUtils.getDate(slotMasterDto.getToDate(), "yyyy-MM-dd"))
						.setResultTransformer(Transformers.aliasToBean(SlotMasterDto.class)).list();
				return new Response(SUCCESS, null, null, slotMasterDtosList, null);
			}
			// For EHC
			if (slotMasterDto.getSlotTypeId() == SlotTypeECH) {

				StringBuilder stringQuery = new StringBuilder(sessionFactory.getCurrentSession().getNamedQuery("GET_APPOINTMENTS_SLOTS_BY_DATE").getQueryString().toString());
				stringQuery.append(" AND sm.appointment_date BETWEEN :fromDate AND :toDate " 
						+ "ORDER BY sm.slot_id");
				SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(stringQuery.toString());

				List<SlotMasterDto> slotMasterDtosList = query.setInteger("orgId", slotMasterDto.getOrganizationId())
						.setInteger("unitId", slotMasterDto.getUnitId())
						.setInteger("slotTypeId", slotMasterDto.getSlotTypeId())
						.setDate("fromDate",GlobalCommonDateUtils.getDate(slotMasterDto.getFromDate(), "yyyy-MM-dd"))
						.setDate("toDate",GlobalCommonDateUtils.getDate(slotMasterDto.getToDate(), "yyyy-MM-dd"))
						.setResultTransformer(Transformers.aliasToBean(SlotMasterDto.class)).list();
				return new Response(SUCCESS, null, null, slotMasterDtosList, null);
			}
		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response blockUnblockSlots(BlockSlotReqDto blockSlotReqDto)
			throws ApplicationException {

		try{
			int updateCount = sessionFactory.getCurrentSession()
					.getNamedQuery("BLOCK_UNBLOCK_SLOT")
					.setInteger("isBlocked", blockSlotReqDto.getIsBlockUnblock())
					.setParameterList("listSlotId", blockSlotReqDto.getListSlotId())
					.executeUpdate();
			
			return new Response<>(SUCCESS, COMMON_SUCCESS, SUCCESS_MESSAGE, null, updateCount);
		}catch(HibernateException he){
			he.printStackTrace();
			throw he;
		}catch(ApplicationException ae){
			ae.printStackTrace();
			throw ae;
		}
	}

	@Override
	public Response getSlotsForBlockUnblock(
			SlotBlockUnblockRequestDto slotReqDto) throws ApplicationException {
		try {
			
			StringBuilder stringQuery = new StringBuilder(sessionFactory.getCurrentSession().getNamedQuery("GET_SLOTS_FOR_BLOCK_UNBLOCK").getQueryString().toString());
			
			if(slotReqDto.getSlotTypeId()==1)
			{
				stringQuery.append(" AND sm.doctor_id IN (:listDoctorIds) ");
				
			if((slotReqDto.getFromTime() !=  null && !slotReqDto.getFromTime().isEmpty()) && (slotReqDto.getToTime()!=  null && !slotReqDto.getToTime().isEmpty()))
				stringQuery.append(" AND sm.from_time between '"+slotReqDto.getFromTime()+"' and '"+slotReqDto.getToTime()+"' ");
				
			SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(stringQuery.toString());
					
		List<SlotMasterDto> slotMasterDtosList = query
					.setParameterList("listDoctorIds", slotReqDto.getListDoctorId())
					.setParameterList("listSpecialityIds", slotReqDto.getListSpecialityId())
					.setParameter("fromDate", (slotReqDto.getFromDate()!=null?GlobalCommonDateUtils.getDate(slotReqDto.getFromDate(), "yyyy-MM-dd"):new Date()))
					.setParameter("toDate", (slotReqDto.getToDate()!=null?GlobalCommonDateUtils.getDate(slotReqDto.getToDate(), "yyyy-MM-dd"):new Date()))
					.setInteger("orgId", slotReqDto.getOrganisationId())
					.setInteger("unitId", slotReqDto.getUnitId())
					.setInteger("slotTypeId", slotReqDto.getSlotTypeId())
					.setInteger("isBlocked", slotReqDto.getIsBlocked())
					.setResultTransformer(Transformers.aliasToBean(SlotMasterDto.class)).list();
			return new Response(SUCCESS, null, null, slotMasterDtosList, null);
			}else{
				stringQuery.append(" AND sm.sub_speciality_id IN (:listSubSpecialityIds) "
						+ " AND sm.modality_id IN (:listModalityIds) ");
				
			if((slotReqDto.getFromTime() !=  null && !slotReqDto.getFromTime().isEmpty()) && (slotReqDto.getToTime()!=  null && !slotReqDto.getToTime().isEmpty()))
				stringQuery.append(" AND sm.from_time between '"+slotReqDto.getFromTime()+"' and '"+slotReqDto.getToTime()+"' ");
				
			SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(stringQuery.toString());
					
		List<SlotMasterDto> slotMasterDtosList = query
					.setParameterList("listModalityIds", slotReqDto.getListModalityId())
					.setParameterList("listSubSpecialityIds", slotReqDto.getListSubSpecialityId())
					.setParameterList("listSpecialityIds", slotReqDto.getListSpecialityId())
					.setParameter("fromDate", (slotReqDto.getFromDate()!=null?GlobalCommonDateUtils.getDate(slotReqDto.getFromDate(), "yyyy-MM-dd"):new Date()))
					.setParameter("toDate", (slotReqDto.getToDate()!=null?GlobalCommonDateUtils.getDate(slotReqDto.getToDate(), "yyyy-MM-dd"):new Date()))
					.setInteger("orgId", slotReqDto.getOrganisationId())
					.setInteger("unitId", slotReqDto.getUnitId())
					.setInteger("slotTypeId", slotReqDto.getSlotTypeId())
					.setInteger("isBlocked", slotReqDto.getIsBlocked())
					.setResultTransformer(Transformers.aliasToBean(SlotMasterDto.class)).list();
			return new Response(SUCCESS, null, null, slotMasterDtosList, null);
			}
			
		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		} 
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getBlockedUnblockedSlotDetails(SlotMasterDto slotMasterDto) throws ApplicationException {
		try {

		//For doctor
		if(slotMasterDto.getSlotTypeId()==SlotTypeDoctor){

			StringBuilder stringQuery = new StringBuilder(sessionFactory.getCurrentSession().getNamedQuery("GET_APPOINTMENTS_SLOT_DETAILS").getQueryString().toString());
			
			if((slotMasterDto.getFromTime() !=  null ) && (slotMasterDto.getToTime()!=  null))
				stringQuery.append(" AND sm.fromTime between '"+slotMasterDto.getFromTime()+"' and '"+slotMasterDto.getToTime()+"' ");
			
			stringQuery.append(" AND sm.specialityId=:specialityId"
							 + " AND sm.doctorId=:docId "
							 + " AND sm.isBlocked=:isBlocked " 
							 + " ORDER BY sm.slotId");

			Query query = sessionFactory.getCurrentSession().createQuery(stringQuery.toString());

			List<SlotMasterDto> slotMasterDtosList = query
					.setInteger("orgId", slotMasterDto.getOrganizationId())
					.setInteger("unitId", slotMasterDto.getUnitId())
					.setInteger("slotTypeId", slotMasterDto.getSlotTypeId())
					.setInteger("specialityId", slotMasterDto.getSpecialityId())
					.setInteger("isBlocked", slotMasterDto.getIsBlockedUnblocked())
					.setInteger("docId", slotMasterDto.getDoctorId())
					.setDate("appointmentDate", GlobalCommonDateUtils.getDate(slotMasterDto.getAppointmentDate(),"yyyy-MM-dd"))
					
					.setInteger("dayId", slotMasterDto.getDayId())
					.setResultTransformer(Transformers.aliasToBean(SlotMasterDto.class)).list();
			return new Response(SUCCESS, null, null, slotMasterDtosList, null);
		}
		//For modality
		if(slotMasterDto.getSlotTypeId()==SlotTypeModality){

			StringBuilder stringQuery = new StringBuilder(sessionFactory.getCurrentSession().getNamedQuery("GET_APPOINTMENTS_SLOT_DETAILS").getQueryString().toString());
			
			if((slotMasterDto.getFromTime() !=  null ) && (slotMasterDto.getToTime()!=  null))
				stringQuery.append(" AND sm.fromTime between '"+slotMasterDto.getFromTime()+"' and '"+slotMasterDto.getToTime()+"' ");
			
			stringQuery.append(" AND sm.modalityId=:modalityId "
							 + " AND sm.subSpecialityId=:subSpecialityId "
							 + " AND sm.specialityId=:specialityId "
							 + " AND sm.isBlocked=:isBlocked " 
							 + " ORDER BY sm.slotId");
			Query query = sessionFactory.getCurrentSession().createQuery(stringQuery.toString());

			List<SlotMasterDto> slotMasterDtosList = query
					.setInteger("orgId", slotMasterDto.getOrganizationId())
					.setInteger("unitId", slotMasterDto.getUnitId())
					.setInteger("slotTypeId", slotMasterDto.getSlotTypeId())
					.setInteger("modalityId", slotMasterDto.getModalityId())
					.setInteger("specialityId",slotMasterDto.getSpecialityId())
					.setInteger("isBlocked", slotMasterDto.getIsBlockedUnblocked())
					.setInteger("subSpecialityId", slotMasterDto.getSubSpecialityId())
					.setDate("appointmentDate", GlobalCommonDateUtils.getDate(slotMasterDto.getAppointmentDate(),"yyyy-MM-dd"))
					.setInteger("dayId", slotMasterDto.getDayId())
					.setResultTransformer(Transformers.aliasToBean(SlotMasterDto.class)).list();
			return new Response(SUCCESS, null, null, slotMasterDtosList, null);
		}
		//For ECH
		if(slotMasterDto.getSlotTypeId()==SlotTypeECH){
			StringBuilder stringQuery = new StringBuilder(sessionFactory.getCurrentSession().getNamedQuery("GET_APPOINTMENTS_SLOT_DETAILS").getQueryString().toString());
			
			if((slotMasterDto.getFromTime() !=  null ) && (slotMasterDto.getToTime()!=  null))
				stringQuery.append(" AND sm.fromTime between '"+slotMasterDto.getFromTime()+"' and '"+slotMasterDto.getToTime()+"' ");
			
			stringQuery.append(" AND sm.isBlocked=:isBlocked " 
							+  " ORDER BY sm.slotId");
			Query query = sessionFactory.getCurrentSession().createQuery(stringQuery.toString());

			List<SlotMasterDto> slotMasterDtosList = query
					.setInteger("orgId", slotMasterDto.getOrganizationId())
					.setInteger("unitId", slotMasterDto.getUnitId())
					.setInteger("isBlocked", slotMasterDto.getIsBlockedUnblocked())
					.setInteger("slotTypeId", slotMasterDto.getSlotTypeId())
					.setDate("appointmentDate", GlobalCommonDateUtils.getDate(slotMasterDto.getAppointmentDate(),"yyyy-MM-dd"))
					.setInteger("dayId", slotMasterDto.getDayId())
					.setResultTransformer(Transformers.aliasToBean(SlotMasterDto.class)).list();
			return new Response(SUCCESS, null, null, slotMasterDtosList, null);
		}

	} catch (HibernateException he) {
		he.printStackTrace();

	} catch (Exception e) {
		e.printStackTrace();
	} 
	return new Response(ERROR, null, null, null, null);
		
	}

	@Override
	public Response updateStatusOfSlot(SlotMasterDto slotMasterDto) throws ApplicationException {
	try{	
		sessionFactory.getCurrentSession().createQuery("update SlotMaster sm set "
				+ "sm.slotStatusId=:slotStatusId, " //slot booked
				+ "sm.updatedBy=:updatedBy , "
				+ "sm.updatedDate=:updatedDate "
				+ "where sm.slotId=:slotId ")
			.setInteger("updatedBy", slotMasterDto.getUpdatedBy())
			.setDate("updatedDate",GlobalCommonDateUtils.getDate(slotMasterDto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss"))
			.setInteger("slotId",slotMasterDto.getSlotId())
			.setInteger("slotStatusId", slotMasterDto.getSlotStatusId())
		.executeUpdate();
		return new Response(SUCCESS, null, null, null, null);
	}catch(Exception e)
	{
		e.printStackTrace();
	}
	return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response isOverBookingAllowedOrNot(SlotMasterDto slotMasterDto) throws ApplicationException {
		try {

			StringBuilder stringQuery = new StringBuilder(sessionFactory.getCurrentSession().getNamedQuery("IS_OVERBOOKING_ALLOWED_OR_NOT").getQueryString());
			
			//For doctor
			if(slotMasterDto.getSlotTypeId()==SlotTypeDoctor){
				
				stringQuery.append(" AND sm.doctorId=:docId ");

				Query query = sessionFactory.getCurrentSession().createQuery(stringQuery.toString());

				List<SlotMasterDto> slotMasterDtosList = query
						.setInteger("orgId", slotMasterDto.getOrganizationId())
						.setInteger("unitId", slotMasterDto.getUnitId())
						.setInteger("specialityId", slotMasterDto.getSpecialityId())
						.setInteger("docId", slotMasterDto.getDoctorId())
						.setInteger("slotStatusId", slotMasterDto.getSlotStatusId())
						.setDate("appointmentDate", GlobalCommonDateUtils.getDate(slotMasterDto.getAppointmentDate(),"yyyy-MM-dd"))
						.setResultTransformer(Transformers.aliasToBean(SlotMasterDto.class)).list();
				return new Response(SUCCESS, null, null, slotMasterDtosList, null);
			}
			//For modality
			if(slotMasterDto.getSlotTypeId()==SlotTypeModality){

				stringQuery.append(" AND sm.modalityId=:modalityId "
								 + " AND sm.subSpecialityId=:subSpecialityId ");
				Query query = sessionFactory.getCurrentSession().createQuery(stringQuery.toString());

				List<SlotMasterDto> slotMasterDtosList = query
						.setInteger("orgId", slotMasterDto.getOrganizationId())
						.setInteger("unitId", slotMasterDto.getUnitId())
						.setInteger("modalityId", slotMasterDto.getModalityId())
						.setInteger("specialityId",slotMasterDto.getSpecialityId())
						.setInteger("subSpecialityId", slotMasterDto.getSubSpecialityId())
						.setInteger("slotStatusId", slotMasterDto.getSlotStatusId())
						.setDate("appointmentDate", GlobalCommonDateUtils.getDate(slotMasterDto.getAppointmentDate(),"yyyy-MM-dd"))
						.setResultTransformer(Transformers.aliasToBean(SlotMasterDto.class)).list();
				return new Response(SUCCESS, null, null, slotMasterDtosList, null);
			}
			//For ECH
			if(slotMasterDto.getSlotTypeId()==SlotTypeECH){
				
				
				stringQuery.append(" AND sm.slotTypeId=:slotTypeId ");
				Query query = sessionFactory.getCurrentSession().createQuery(stringQuery.toString());

				List<SlotMasterDto> slotMasterDtosList = query
						.setInteger("orgId", slotMasterDto.getOrganizationId())
						.setInteger("unitId", slotMasterDto.getUnitId())
						.setInteger("slotTypeId", slotMasterDto.getSlotTypeId())
						.setInteger("slotStatusId", slotMasterDto.getSlotStatusId())
						.setDate("appointmentDate", GlobalCommonDateUtils.getDate(slotMasterDto.getAppointmentDate(),"yyyy-MM-dd"))
						.setResultTransformer(Transformers.aliasToBean(SlotMasterDto.class)).list();
				return new Response(SUCCESS, null, null, slotMasterDtosList, null);
			}

		} catch (HibernateException he) {
			he.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();
		} 
		return new Response(ERROR, null, null, null, null);
	}


}
