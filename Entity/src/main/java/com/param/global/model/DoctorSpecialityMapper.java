package com.param.global.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
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
	
	@NamedQuery(name="GET_DOCTOR_FOR_ICU",query=
			"SELECT doc.doctorId as doctorId, "
			+ "concat('Dr. ',doc.firstName,' ',doc.middleName,' ',doc.lastName) as firstName "
			+ "FROM DoctorSpecialityMapper docMpr "
			+ "INNER JOIN docMpr.doctorMaster doc "
			+ "WHERE doc.organizationId=:orgId "
			+ "AND doc.unitId=:unitId "
			+ "AND docMpr.specialityId=:specialityId "
			+ "AND doc.status='A' "
			+ "AND docMpr.status='A' ")
})

@Entity(name = "DoctorSpecialityMapper")
@Table(name = "t_doctor_speciality_mapper", schema = "doctor")
@SequenceGenerator(name="doctor_speciality_mapper_seq", sequenceName="doctor.doctor_speciality_mapper_seq", allocationSize=1)
public class DoctorSpecialityMapper 
{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "doctor_speciality_mapper_seq")
	@Column(name="doctor_speciality_id")
	private int doctorSpecialityId;
	
	@Column(name="doctor_id")
	private Integer doctorId;
	
	@Column(name = "unit_id")
	private Integer unitId;

	@Column(name="speciality_id")
	private Integer specialityId;
	
	@Column(name="class_id")
	private Integer classId;

	@Column(name="status")
	private char status;

	@Column(name="created_by")
	private int createdBy;

	@Column(name="created_date")
	private Date createdDate;
	
	@Column(name="updated_by")
	private int updatedBy;
	
	@Column(name="updated_date")
	private Date updatedDate;
	
	/*@ManyToOne
	@JoinColumn(name = "unit_id", insertable = false, updatable = false)
	private UnitMaster unitMaster;*/
	
	@ManyToOne
	@JoinColumn(name = "speciality_id", insertable = false, updatable = false)
	private SpecialityMaster specialityMaster;
	
	@ManyToOne
	@JoinColumn(name = "doctor_id", insertable = false, updatable = false)
	private DoctorMaster doctorMaster;

	public int getDoctorSpecialityId() {
		return doctorSpecialityId;
	}

	public void setDoctorSpecialityId(int doctorSpecialityId) {
		this.doctorSpecialityId = doctorSpecialityId;
	}

	public Integer getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
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

	/*public UnitMaster getUnitMaster() {
		return unitMaster;
	}

	public void setUnitMaster(UnitMaster unitMaster) {
		this.unitMaster = unitMaster;
	}*/


	public DoctorMaster getDoctorMaster() {
		return doctorMaster;
	}

	public void setDoctorMaster(DoctorMaster doctorMaster) {
		this.doctorMaster = doctorMaster;
	}

	public Integer getClassId() {
		return classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}
	
}
