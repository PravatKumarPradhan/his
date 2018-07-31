package com.param.entity.lis.unit;


import javax.persistence.Column;
import javax.persistence.Convert;
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

import com.param.global.model.DoctorMaster;
import com.param.global.model.SpecialityMaster;
import com.param.entity.lis.common.LocalTimeConverter;

@NamedQueries({


		@NamedQuery(name = "GET_SPECIALTY_TEMPLATE_LIST", query = "SELECT specialtyTemplateMapper.specialtyTempId AS specialtyTempId,"
				+ " specialtyTemplateMapper.orgId as orgId,"
				+ " specialtyTemplateMapper.orgUnitId as orgUnitId,"
				+ " specialtyTemplateMapper.templateTypeId as templateTypeId,"
				+ " specialtyTemplateMapper.doctorId as doctorId,"
				+ " specialtyTemplateMapper.labTemplateId as labTemplateId,"
	            + " specialtyTemplateMapper.specialityId as specialityId,"
				+ " specialtyTemplateMapper.status as status,"
				+ " specialtyTemplateMapper.createdBy as createdBy,"
				+ " specialtyTemplateMapper.createdDate as createdDate,"
				+ " specialtyTemplateMapper.updatedBy as updatedBy,"
				+ " specialtyTemplateMapper.updatedDate as updatedDate "
				+ " FROM SpecialtyTemplateMapper specialtyTemplateMapper "
				+ " WHERE specialtyTemplateMapper.labTemplateId= :labTemplateId "
		        + " AND specialtyTemplateMapper.isDeleted= 'N'"),
		

		@NamedQuery(name = "GET_TEMPLATE_TYPE_EXIST_OR_NOT", query = "select count(*) FROM "
				+ "SpecialtyTemplateMapper specialtyTemplateMapper "
				+ " WHERE specialtyTemplateMapper.orgId = :orgId " 
				+ " AND specialtyTemplateMapper.orgUnitId = :orgUnitId " 
				+ " AND specialtyTemplateMapper.templateTypeId = :templateTypeId " )

		
		

})


@Entity
@Table(name = "m_specialty_template_mapper", schema = "lab")
@SequenceGenerator(name = "m_seq_specialty_template_mapper",
    sequenceName = "lab.m_seq_specialty_template_mapper", allocationSize = 1)
public class SpecialtyTemplateMapper {

	


  @Id
  @Column(name = "specialty_temp_id")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "m_seq_specialty_template_mapper")
  private int specialtyTempId;

  @Column(name = "org_id")
  private Integer orgId;

  @Column(name = "org_unit_id")
  private Integer orgUnitId;
  
  @Column(name = "doctor_id")
  private Integer doctorId;
  
  @Column(name = "lab_template_id")
  private Integer labTemplateId;

  @Column(name = "template_type_id")
  private Integer templateTypeId;

  @Column(name = "status")
  private Character status;

  @Column(name = "created_by")
  private Integer createdBy;

  @Column(name = "created_date")
  @Convert(converter = LocalTimeConverter.class)
  private Long createdDate;

  @Column(name = "updated_by")
  private Integer updatedBy;

  @Column(name = "updated_date")
  @Convert(converter = LocalTimeConverter.class)
  private Long updatedDate;

  @Column(name = "is_deleted")
  private Character isDeleted;

  @Column(name = "speciality_id")
  private Integer specialityId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "speciality_id", insertable = false, nullable = false, updatable = false)
  private SpecialityMaster specialityMaster;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "doctor_id", insertable = false, nullable = false, updatable = false)
  private DoctorMaster doctorMaster;
  

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "lab_template_id", insertable = false, nullable = false, updatable = false)
  private LabTemplateMaster labTemplateMaster;
  


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

public LabTemplateMaster getLabTemplateMaster() {
	return labTemplateMaster;
}

public void setLabTemplateMaster(LabTemplateMaster labTemplateMaster) {
	this.labTemplateMaster = labTemplateMaster;
}

public int getSpecialtyTempId() {
	return specialtyTempId;
}

public void setSpecialtyTempId(int specialtyTempId) {
	this.specialtyTempId = specialtyTempId;
}

public Integer getOrgId() {
	return orgId;
}

public void setOrgId(Integer orgId) {
	this.orgId = orgId;
}

public Integer getOrgUnitId() {
	return orgUnitId;
}

public void setOrgUnitId(Integer orgUnitId) {
	this.orgUnitId = orgUnitId;
}

public Integer getDoctorId() {
	return doctorId;
}

public void setDoctorId(Integer doctorId) {
	this.doctorId = doctorId;
}

public Integer getLabTemplateId() {
	return labTemplateId;
}

public void setLabTemplateId(Integer labTemplateId) {
	this.labTemplateId = labTemplateId;
}

public Integer getTemplateTypeId() {
	return templateTypeId;
}

public void setTemplateTypeId(Integer templateTypeId) {
	this.templateTypeId = templateTypeId;
}

public Character getStatus() {
	return status;
}

public void setStatus(Character status) {
	this.status = status;
}

public Integer getCreatedBy() {
	return createdBy;
}

public void setCreatedBy(Integer createdBy) {
	this.createdBy = createdBy;
}

public Long getCreatedDate() {
	return createdDate;
}

public void setCreatedDate(Long createdDate) {
	this.createdDate = createdDate;
}

public Integer getUpdatedBy() {
	return updatedBy;
}

public void setUpdatedBy(Integer updatedBy) {
	this.updatedBy = updatedBy;
}

public Long getUpdatedDate() {
	return updatedDate;
}

public void setUpdatedDate(Long updatedDate) {
	this.updatedDate = updatedDate;
}

public Character getIsDeleted() {
	return isDeleted;
}

public void setIsDeleted(Character isDeleted) {
	this.isDeleted = isDeleted;
}

public Integer getSpecialityId() {
	return specialityId;
}

public void setSpecialityId(Integer specialityId) {
	this.specialityId = specialityId;
}


}
	



