package com.param.opd.unit.model;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.param.global.common.LocalTimeConverter;
import com.param.global.model.DayMaster;
import com.param.global.model.DoctorMaster;
import com.param.global.model.ModalityMaster;
import com.param.global.model.SpecialityMaster;
import com.param.global.model.SubSpecialityMaster;
import com.param.global.model.VisitTypeMaster;
import com.param.opd.appointment.model.AppointmentMaster;

@NamedQueries({
	
	@NamedQuery(name="GET_ACTIVE_APPOINTMENTS",query=""
			+ "SELECT DISTINCT(to_char(sm.appointmentDate,'dd/MM/yyyy')) as appointmentDate, "
			+ "sm.dayId as dayId, "
			+ "dm.day as day, "
			+ "sm.doctorId as doctorId, "
			+ "concat(coalesce(docMaster.firstName),' ',coalesce(docMaster.lastName)) as doctorName, "
			+ "sm.specialityId as specialityId, "
			+ "speciaityMaster.specialityName as specialityName, "
			+ "sm.modalityId as modalityId, "
			+ "mm.modalityDesc as modalityDesc, "
			+ "mm.subSpecialityId as subSpecialityId, "
			+ "ssm.subSpecialityMasterName as subSpecialityName "
			+ "FROM SlotMaster sm "
			+ "LEFT JOIN sm.specialityMaster speciaityMaster "
			+ "LEFT JOIN sm.doctorMaster docMaster "
			+ "INNER JOIN sm.dayMaster dm "
			+ "LEFT JOIN sm.modalityMaster mm "
			+ "LEFT JOIN sm.subSpecialityMaster ssm "
			+ "WHERE sm.organizationId=:orgId "
			+ "AND sm.unitId=:unitId "
			+ "AND sm.slotTypeId=:slotTypeId "
			+ "ORDER BY appointmentDate"),
			
	


	@NamedQuery(name="GET_APPOINTMENTS_SLOT_DETAILS",query=
	"SELECT sm.slotId as slotId, "
			+ "to_char(sm.appointmentDate,'dd/MM/yyyy') as appointmentDate, "
			+ "concat(to_char(sm.fromTime,'HH:MI')) as fromTimeString, "	//HH12:MI:SS -->for 12 hrs and HH24 for 24 hrs
			+ "concat(to_char(sm.toTime,'HH:MI')) as toTimeString,"
			+ "sm.status as status "
			+ "FROM SlotMaster sm "
			+ "WHERE sm.organizationId=:orgId "
			+ "AND sm.unitId=:unitId "
			+ "AND sm.slotTypeId=:slotTypeId "
			+ "AND sm.appointmentDate=:appointmentDate "
			+ "AND sm.dayId=:dayId "
			),

	@NamedQuery(name="GET_SLOT_STATUS",query=
	"SELECT sm.appointmentId as appointmentId "
			+ "FROM AppointmentMaster sm "
			+ "WHERE sm.organizationId=:orgId "
			+ "AND sm.unitId=:unitId "
			+ "AND sm.slotId=:slotId "
			+ "AND sm.status='A' "),
	
	@NamedQuery(name="IS_OVERBOOKING_ALLOWED_OR_NOT",query="SELECT slotId as slotId "
			+ "FROM SlotMaster sm "
			+ "WHERE sm.organizationId=:orgId "
			+ "AND sm.unitId=:unitId "
			+ "AND sm.appointmentDate=:appointmentDate "
			+ "AND sm.slotStatusId=:slotStatusId "
			+ "AND sm.specialityId=:specialityId "
			+ "AND sm.status='A' ")
	

})

@NamedNativeQueries({


	@NamedNativeQuery(name="GET_APPOINTMENTS_SLOTS_BY_DATE",query=
			"SELECT sm.slot_id as \"slotId\", "
					+ "to_char(sm.appointment_date,'dd/MM/yyyy') as \"appointmentDate\", "
					+ "to_char(sm.appointment_date,'yyyy-MM-dd') as \"appointmentDate2\", "
					+ "concat(to_char(sm.from_time,'HH:MI')) as \"fromTimeString\", "	//HH12:MI:SS -->for 12 hrs and HH24 for 24 hrs
					+ "concat(to_char(sm.to_time,'HH:MI')) as \"toTimeString\","
					+ "concat(to_char(sm.from_time,'HH:MI AM')) as \"fromTimeString2\", "	//HH12:MI:SS -->for 12 hrs and HH24 for 24 hrs
					+ "concat(to_char(sm.to_time,'HH:MI AM')) as \"toTimeString2\","
					+ "sm.doctor_id as \"doctorId\", "
					+ "sm.slot_status_id as \"slotStatusId\", "
					+ "concat('Dr. ',doc.first_name,' ',doc.middle_name,' ',doc.last_name) as \"doctorName\", "
					+ "sm.speciality_id as \"specialityId\", "
					+ "spl.speciality_name as \"specialityName\", "
					+ "sm.modality_id as \"modalityId\", "
					+ "modality.modality_desc as \"modalityDesc\", "
					+ "sm.sub_speciality_id as \"subSpecialityId\", "
					+ "ssm.sub_speciality_name as \"subSpecialityName\", "
					+ "aml.appointment_id as \"appointmentId\", "
					+ "aml.patient_id as \"patientId\", "
					+ "patient.uhid_number as \"uhidNumber\", "
					+ "concat(coalesce(patient.first_name) ,' ', coalesce(patient.middle_name) ,' ', coalesce(patient.last_name),',',concat(to_char(EXTRACT(year from AGE(CURRENT_TIMESTAMP, patient.dob)),'999'), 'Y'),' / ',gender.gender_code) as \"patientName\", "
					+ "concat(coalesce(area.area_name),', ',coalesce(city.city_name),', ',coalesce(district.district_name),', ',coalesce(state.state_name),', ',coalesce(country.country_name),'. ',coalesce(patientDetails.zip_code)) as \"address\" ,"
					+ "patient.mobile_number as \"mobileNumber\", "
					+ "patient.email as \"email\", "
					+ "sm.is_blocked as \"isBlockedUnblocked\" "
					+ "FROM opd.m_slot_master sm "
					+ "LEFT JOIN doctor.m_doctor_master doc ON doc.doctor_id = sm.doctor_id "
					+ "LEFT JOIN doctor.m_speciality_master spl ON spl.speciality_id=sm.speciality_id "
					+ "LEFT JOIN opd.m_appointment_master aml ON aml.slot_id = sm.slot_id AND aml.status='A' "
					+ "LEFT JOIN patient.t_patient_registration patient ON patient.patient_id = aml.patient_id "
					+ "LEFT JOIN public.m_gender_master gender ON gender.gender_id = patient.gender_id "
					+ "LEFT JOIN patient.t_patient_details patientDetails ON patientDetails.patient_id = patient.patient_id "
					+ "LEFT JOIN public.m_country_master country ON country.country_id = patientDetails.country_id "
					+ "LEFT JOIN public.m_state_master state ON state.state_id = patientDetails.state_id "
					+ "LEFT JOIN public.m_district_master district ON district.district_id = patientDetails.district_id "
					+ "LEFT JOIN public.m_city_master city ON city.district_id = patientDetails.city_id "
					+ "LEFT JOIN public.m_area_master area ON area.area_id = patientDetails.area_id "
					+ "LEFT JOIN public.m_modality_master modality ON sm.modality_id=modality.modality_id "
					+ "LEFT JOIN doctor.m_sub_speciality_master ssm ON ssm.sub_speciality_id=sm.sub_speciality_id "
					+ "WHERE sm.organization_id=:orgId "
					+ "AND sm.unit_id=:unitId "
					+ "AND sm.slot_type_id=:slotTypeId "
					+ "AND sm.status='A' "
			),
	@NamedNativeQuery(
		name="BLOCK_UNBLOCK_SLOT",
		query="UPDATE 	opd.m_slot_master"+
				" set is_blocked =:isBlocked"+
				" WHERE slot_id IN (:listSlotId)"
	),
	
	@NamedNativeQuery(name="GET_SLOTS_FOR_BLOCK_UNBLOCK",query="select distinct to_char(sm.appointment_date, 'dd/MM/yyyy') as \"appointmentDate\", "
			+" sm.day_id as \"dayId\", "
			+"dm.day as \"day\", "
			+"sm.doctor_id as \"doctorId\", "
			+"concat(coalesce(docMaster.first_name),' ',coalesce(docMaster.last_name)) as \"doctorName\", "
			+"sm.speciality_id as \"specialityId\", "
			+"speciaityMaster.speciality_name as \"specialityName\", "
			+"sm.modality_id as \"modalityId\", "
			+"mm.modality_desc as \"modalityDesc\", "
			+"mm.sub_speciality_id as \"subSpecialityId\", "
			+"ssm.sub_speciality_name as \"subSpecialityName\", "
			+"budl.remark as \"remark\","
			+ "reason.reasonDesc as \"reasonDesc\" "
			+"FROM opd.m_slot_master sm "
			+"left outer join doctor.m_speciality_master speciaityMaster on sm.speciality_id=speciaityMaster.speciality_id "
			+"left outer join doctor.m_doctor_master docMaster on sm.doctor_id=docMaster.doctor_id "
			+"inner join public.m_day_master dm on sm.day_id=dm.day_id "
			+"left outer join public.m_modality_master mm on sm.modality_id=mm.modality_id "
			+"left outer join doctor.m_sub_speciality_master ssm on sm.sub_speciality_id=ssm.sub_speciality_id "
			+"left outer join opd.t_slot_block_unblock_detail budl on sm.slot_id=budl.slot_id and budl.status='A' "
			+"left outer join adt.m_reason_master reason on budl.reason_id=reason.reason_master_id "
			+"where sm.organization_id=:orgId "
			+"and sm.unit_id=:unitId "
			+"and sm.slot_type_id=:slotTypeId "
			+"and (sm.speciality_id in (:listSpecialityIds)) "
			+"and sm.is_blocked=:isBlocked "
			+"and (sm.appointment_date between :fromDate and :toDate) ")
})


@Entity
@Table(name="m_slot_master",schema="opd")
@SequenceGenerator(name="slot_master_seq",sequenceName="opd.slot_master_seq",allocationSize=1)
public class SlotMaster {

	@Id
	@Column(name="slot_id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="slot_master_seq")
	private Integer slotId;
	
	@Column(name="organization_id")
	private Integer organizationId;
	
	@Column(name="unit_id")
	private Integer unitId;
	
	@Column(name="speciality_id")
	private Integer specialityId;
	
	@Column(name="sub_speciality_id")
	private Integer subSpecialityId;
	
	@Column(name="doctor_id")
	private Integer doctorId;
	
	@Column(name="modality_id")
	private Integer modalityId;
	
	@Column(name="appointment_date")
	private Date appointmentDate;
	
	@Column(name="day_id")
	private Integer dayId;
	
	@Column(name="from_time")
	@Convert(converter = LocalTimeConverter.class)
	private LocalTime fromTime;
	
	@Column(name="to_time")
	@Convert(converter = LocalTimeConverter.class)
	private LocalTime toTime;
	
	@Column(name="slot_type_id")
	private Integer slotTypeId;
	
	@Column(name="visit_type_id")
	private Integer visitTypeId;
	
	@Column(name="slot_status_id")
	private Integer slotStatusId;
	
	@Column(name="created_by")
	private Integer createdBy;
	
	@Column(name="created_date")
	private Date createdDate;
	
	@Column(name="updated_by")
	private Integer updatedBy;
	
	@Column(name="updated_date")
	private Date updatedDate;
	
	@Column(name="status")
	private char status;
	
	//0-DEFAULT, 1-BLOCKED, 2-UNBLOCKED
	@Column(name="is_blocked")
	private Integer isBlocked;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "speciality_id", insertable = false, nullable = false, updatable = false)
	private SpecialityMaster specialityMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sub_speciality_id", insertable = false, nullable = false, updatable = false)
	private SubSpecialityMaster subSpecialityMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "doctor_id", insertable = false, nullable = false, updatable = false)
	private  DoctorMaster doctorMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "visit_type_id", insertable = false, nullable = false, updatable = false)
	private  VisitTypeMaster visitTypeMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "day_id", insertable = false, nullable = false, updatable = false)
	private  DayMaster dayMaster;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "modality_id", insertable = false, nullable = false, updatable = false)
	private  ModalityMaster modalityMaster;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "slotMaster", cascade = CascadeType.ALL, orphanRemoval = true) 
	private List<AppointmentMaster> appointmentMastersList;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "slotMaster", cascade = CascadeType.ALL, orphanRemoval = true) 
	private List<SlotBlockUnblockDetail> blockUnblockDetailsList;

	public Integer getSubSpecialityId() {
		return subSpecialityId;
	}

	public void setSubSpecialityId(Integer subSpecialityId) {
		this.subSpecialityId = subSpecialityId;
	}

	public Integer getSlotStatusId() {
		return slotStatusId;
	}

	public void setSlotStatusId(Integer slotStatusId) {
		this.slotStatusId = slotStatusId;
	}

	public Integer getModalityId() {
		return modalityId;
	}

	public void setModalityId(Integer modalityId) {
		this.modalityId = modalityId;
	}

	public Date getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public Integer getSlotId() {
		return slotId;
	}

	public void setSlotId(Integer slotId) {
		this.slotId = slotId;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public Integer getSpecialityId() {
		return specialityId;
	}

	public void setSpecialityId(Integer specialityId) {
		this.specialityId = specialityId;
	}

	public Integer getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}

	public Integer getDayId() {
		return dayId;
	}

	public void setDayId(Integer dayId) {
		this.dayId = dayId;
	}

	public LocalTime getFromTime() {
		return fromTime;
	}

	public void setFromTime(LocalTime fromTime) {
		this.fromTime = fromTime;
	}

	public LocalTime getToTime() {
		return toTime;
	}

	public void setToTime(LocalTime toTime) {
		this.toTime = toTime;
	}

	public Integer getSlotTypeId() {
		return slotTypeId;
	}

	public void setSlotTypeId(Integer slotTypeId) {
		this.slotTypeId = slotTypeId;
	}

	public Integer getVisitTypeId() {
		return visitTypeId;
	}

	public void setVisitTypeId(Integer visitTypeId) {
		this.visitTypeId = visitTypeId;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = (status == '\u0000') ? 'A' : status;
	}

	public Integer getIsBlocked() {
		return isBlocked;
	}

	public void setIsBlocked(Integer isBlocked) {
		this.isBlocked = isBlocked;
	}
	
}