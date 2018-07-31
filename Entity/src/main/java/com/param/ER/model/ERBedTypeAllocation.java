package com.param.ER.model;

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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.param.adt.master.global.model.OrganizationMaster;
import com.param.adt.master.global.model.UnitMaster;
import com.param.global.model.DoctorMaster;
import com.param.global.model.SpecialityMaster;
import com.param.global.model.UnknownPatientRegistration;

@NamedQueries({
	
/*	@NamedQuery(name="GET_ER_BED_TYPE_ALLOCATION_LIST",query="SELECT eba.erBedTypeAllocationId as erBedTypeAllocationId, "
			+ "eba.tUhid as tUhid, "
			+ "eba.tPatientId as tPatientId, "
			+ "patient.ageFormat as ageFormat, "
			+ "patient.patientName as patientName, "
			+ "patient.genderId as genderId, "
			+ "patient.unknownPatientId as tPatientId, "
			+ "patient.age as age, "
			+ "gender.code as genderDesc, "
			+ "FROM ERBedTypeAllocation eba "
			+ "INNER JOIN eba.unknownPatientRegistration patient "
			+ "INNER JOIN patient.genderMaster gender "
			+ "INNER JOIN eba.doctorMaster doc "
			+ "INNER JOIN eba.specialityMaster sp "
			+ "WHERE eba.status='A' "
			+ "AND eba.organizationId=:orgId "
			+ "AND eba.unitId=:unitId ")*/
	
})


@Entity
@Table(name="t_er_bed_type_allocation",schema="adt")
@SequenceGenerator(name="er_bed_type_allocation_seq",sequenceName="adt.er_bed_type_allocation_seq",allocationSize=1)
public class ERBedTypeAllocation 
{
	@Id
	@Column(name="er_bed_type_allocation_id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="er_bed_type_allocation_seq")
	private int erBedTypeAllocationId;
	
	@Column(name="organization_id")
	private Integer organizationId;
	
	@Column(name="unit_id")
	private Integer unitId;
	
	@Column(name="t_patient_id")
	private Integer tPatientId;
	
	@Column(name="t_uhid")
	private String tUhid;
	
	@Column(name="doctor_id")
	private Integer doctorId;
	
	@Column(name="speciality_id")
	private Integer specialtiyId;
	
	@Column(name="triage_category_id")
	private Integer triageCategoryId;
	
	@Column(name = "status")
	private char status;

	@Column(name = "created_by")
	private int createdBy;

	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "updated_by")
	private int updatedBy;

	@Column(name = "updated_date")
	private Date updatedDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organization_id", insertable = false, nullable = false, updatable = false)
	private OrganizationMaster organizationMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "unit_id", insertable = false, nullable = false, updatable = false)
	private UnitMaster unitMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "t_patient_id", insertable = false, nullable = false, updatable = false)
	private UnknownPatientRegistration unknownPatientRegistration;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "speciality_id", insertable = false, nullable = false, updatable = false)
	private SpecialityMaster specialityMaster;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "doctor_id", insertable = false, nullable = false, updatable = false)
	private DoctorMaster doctorMaster;

	public int getErBedTypeAllocationId() {
		return erBedTypeAllocationId;
	}

	public void setErBedTypeAllocationId(int erBedTypeAllocationId) {
		this.erBedTypeAllocationId = erBedTypeAllocationId;
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

	public Integer gettPatientId() {
		return tPatientId;
	}

	public void settPatientId(Integer tPatientId) {
		this.tPatientId = tPatientId;
	}

	public String gettUhid() {
		return tUhid;
	}

	public void settUhid(String tUhid) {
		this.tUhid = tUhid;
	}

	public Integer getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}

	public Integer getSpecialtiyId() {
		return specialtiyId;
	}

	public void setSpecialtiyId(Integer specialtiyId) {
		this.specialtiyId = specialtiyId;
	}

	public Integer getTriageCategoryId() {
		return triageCategoryId;
	}

	public void setTriageCategoryId(Integer triageCategoryId) {
		this.triageCategoryId = triageCategoryId;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public int getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public OrganizationMaster getOrganizationMaster() {
		return organizationMaster;
	}

	public void setOrganizationMaster(OrganizationMaster organizationMaster) {
		this.organizationMaster = organizationMaster;
	}

	public UnitMaster getUnitMaster() {
		return unitMaster;
	}

	public void setUnitMaster(UnitMaster unitMaster) {
		this.unitMaster = unitMaster;
	}

	public UnknownPatientRegistration getUnknownPatientRegistration() {
		return unknownPatientRegistration;
	}

	public void setUnknownPatientRegistration(UnknownPatientRegistration unknownPatientRegistration) {
		this.unknownPatientRegistration = unknownPatientRegistration;
	}

	public SpecialityMaster getSpecialityMaster() {
		return specialityMaster;
	}

	public void setSpecialityMaster(SpecialityMaster specialityMaster) {
		this.specialityMaster = specialityMaster;
	}

	public DoctorMaster getDoctorMaster() {
		return doctorMaster;
	}

	public void setDoctorMaster(DoctorMaster doctorMaster) {
		this.doctorMaster = doctorMaster;
	}

	
	
	
}
