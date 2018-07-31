package com.param.global.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@NamedQueries({
	@NamedQuery(name="GET_DOCTOR_CONSULTATION_SERVICE_MAPPER_LIST",query="SELECT dcsm.doctorConsultationServiceMapperId as doctorConsultationServiceMapperId, "
			+ "dcsm.doctorId as doctorId, "
			+ "dcsm.specialityId as specialityId, "
			+ "spl.specialityName as specialityName, "
			+ "concat(coalesce(doc.firstName),' ', coalesce(doc.middleName),' ',coalesce(doc.lastName)) as doctorName,  "
			+ "dcsm.newVisitServiceId as newVisitServiceId, "
			+ "nvs.serviceStandardName as newVisitServiceName, "
			+ "dcsm.followupVisitServiceId as followupVisitServiceId, "
			+ "fvs.serviceStandardName as followupVisitServiceName, "
			+ "dcsm.secondaryVisitServiceId as secondaryVisitServiceId, "
			+ "svs.serviceStandardName as secondaryVisitServiceName "
			+ "FROM DoctorConsultationServiceMapper dcsm "
			+ "INNER JOIN dcsm.doctorMaster doc "
			+ "INNER JOIN dcsm.specialityMaster spl "
			+ "INNER JOIN dcsm.newVisitService nvs "
			+ "INNER JOIN dcsm.followupVisitService fvs "
			+ "INNER JOIN dcsm.secondaryVisitService svs "
			+ "WHERE dcsm.organizationId=:orgId "
			+ "AND dcsm.unitId=:unitId "
			+ "AND dcsm.status='A' "),
	
	@NamedQuery(name="GET_DOCTOR_CONSULTATION_SERVICE_BY_ID",query="SELECT dcsm.doctorConsultationServiceMapperId as doctorConsultationServiceMapperId, "
			+ "dcsm.doctorId as doctorId, "
			+ "dcsm.specialityId as specialityId, "
			+ "dcsm.newVisitServiceId as newVisitServiceId, "
			+ "dcsm.followupVisitServiceId as followupVisitServiceId, "
			+ "dcsm.secondaryVisitServiceId as secondaryVisitServiceId "
			+ "FROM DoctorConsultationServiceMapper dcsm "
			+ "WHERE dcsm.doctorConsultationServiceMapperId=:doctorConsultationServiceMapperId "),
	
	@NamedQuery(name="CHECK_DOCTOR_CONSULTATION_EXIST_OR_NOT",query="SELECT dcsm.doctorConsultationServiceMapperId as doctorConsultationServiceMapperId "
			+ "FROM DoctorConsultationServiceMapper dcsm "
			+ "WHERE dcsm.organizationId=:orgId "
			+ "AND dcsm.unitId=:unitId "
			+ "AND dcsm.doctorId=:doctorId "
			+ "AND dcsm.newVisitServiceId=:newVisitServiceId "
			+ "AND dcsm.followupVisitServiceId=:followupVisitServiceId "
			+ "AND dcsm.secondaryVisitServiceId=:secondaryVisitServiceId "
			+ "AND dcsm.status='A'"),
	
	@NamedQuery(name="GET_DOCTOR_CONSULTATION_SERVICE_BY_SPECIALITY_AND_DOCTOR_ID",query="SELECT dcsm.doctorConsultationServiceMapperId as doctorConsultationServiceMapperId, "
			+ "dcsm.newVisitServiceId as newVisitServiceId, "
			+ "dcsm.followupVisitServiceId as followupVisitServiceId, "
			+ "dcsm.secondaryVisitServiceId as secondaryVisitServiceId "
			+ "FROM DoctorConsultationServiceMapper dcsm "
			+ "WHERE dcsm.organizationId=:orgId "
			+ "AND dcsm.unitId=:unitId "
			+ "AND dcsm.doctorId=:doctorId "
			+ "AND dcsm.specialityId=:specialityId ")
			}	
		)


@Entity
@Table(name="m_doctor_consultation_service_mapper",schema="doctor")
@SequenceGenerator(name="doctor_consultation_service_mapper_seq",sequenceName="doctor.doctor_consultation_service_mapper_seq")
public class DoctorConsultationServiceMapper {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="doctor_consultation_service_mapper_seq")
	@Column(name="doctor_consultation_service_mapper_id")
	private Integer doctorConsultationServiceMapperId;
	
	@Column(name="unit_id")
	private Integer unitId;
	
	@Column(name="organization_id")
	private Integer organizationId;
	
	@Column(name="speciality_id")
	private Integer specialityId;
	
	@Column(name="doctor_id")
	private Integer doctorId;
	
	@Column(name="new_visit_service_id")
	private Integer newVisitServiceId;
	
	@Column(name="followup_visit_service_id")
	private Integer followupVisitServiceId;
	
	@Column(name="secondary_visit_service_id")
	private Integer secondaryVisitServiceId;
	
	@Column(name = "status")
	private char status;
	
	@Column(name = "created_by")
	private Integer createdBy;
	
	@Column(name = "created_date")
	private Date createdDate;
	
	@Column(name="updated_by")
	private Integer updatedBy;
	
	@Column(name="updated_date")
	private Date updatedDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "doctor_id", insertable = false, updatable = false)
	private DoctorMaster doctorMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "new_visit_service_id", insertable = false, updatable = false)
	private ServiceMaster newVisitService;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "followup_visit_service_id", insertable = false, updatable = false)
	private ServiceMaster followupVisitService;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "secondary_visit_service_id", insertable = false, updatable = false)
	private ServiceMaster secondaryVisitService;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "speciality_id", insertable = false, updatable = false)
	private SpecialityMaster specialityMaster;
	
	public Integer getSpecialityId() {
		return specialityId;
	}

	public void setSpecialityId(Integer specialityId) {
		this.specialityId = specialityId;
	}

	public Integer getDoctorConsultationServiceMapperId() {
		return doctorConsultationServiceMapperId;
	}

	public void setDoctorConsultationServiceMapperId(Integer doctorConsultationServiceMapperId) {
		this.doctorConsultationServiceMapperId = doctorConsultationServiceMapperId;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public Integer getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}

	public Integer getNewVisitServiceId() {
		return newVisitServiceId;
	}

	public void setNewVisitServiceId(Integer newVisitServiceId) {
		this.newVisitServiceId = newVisitServiceId;
	}

	public Integer getFollowupVisitServiceId() {
		return followupVisitServiceId;
	}

	public void setFollowupVisitServiceId(Integer followupVisitServiceId) {
		this.followupVisitServiceId = followupVisitServiceId;
	}

	public Integer getSecondaryVisitServiceId() {
		return secondaryVisitServiceId;
	}

	public void setSecondaryVisitServiceId(Integer secondaryVisitServiceId) {
		this.secondaryVisitServiceId = secondaryVisitServiceId;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
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
	
	
}


/*CREATE TABLE doctor.m_doctor_consultation_service_mapper
(
  doctor_consultation_service_mapper_id integer NOT NULL DEFAULT nextval('doctor.doctor_consultation_service_mapper_seq'::regclass),
  unit_id integer,
  organisation_id integer,
  doctor_id integer,
  new_visit_service_id integer,
  followup_visit_service_id integer,
  secondary_visit_service_id integer,
  status character(1) DEFAULT 'A'::"char",
  created_date timestamp without time zone,
  created_by integer,
  updated_date timestamp without time zone DEFAULT now(),
  updated_by integer,
  CONSTRAINT pk_doctor_consultation_service_mapper_id PRIMARY KEY (doctor_consultation_service_mapper_id),
  CONSTRAINT m_doctor_consultation_service_m_secondary_visit_service_id_fkey FOREIGN KEY (secondary_visit_service_id)
      REFERENCES service.m_service_master (service_master_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT m_doctor_consultation_service_ma_followup_visit_service_id_fkey FOREIGN KEY (followup_visit_service_id)
      REFERENCES service.m_service_master (service_master_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT m_doctor_consultation_service_mapper_doctor_id_fkey FOREIGN KEY (doctor_id)
      REFERENCES doctor.m_doctor_master (doctor_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT m_doctor_consultation_service_mapper_new_visit_service_id_fkey FOREIGN KEY (new_visit_service_id)
      REFERENCES service.m_service_master (service_master_id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)*/