package com.param.entity.lis.histo;

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

import com.param.entity.lis.global.SpecimanMaster;
import com.param.entity.model.adt.Admission;
import com.param.global.model.DoctorMaster;
import com.param.global.model.PatientRegistration;
import com.param.global.model.SurgeryMaster;
import com.param.global.model.VisitTypeMaster;
import com.param.entity.lis.common.LocalTimeConverter;


@NamedQueries({
@NamedQuery(name = "GET_FROZEN_SECTION_REQ_LIST", query = " SELECT  "
    +" tFrozenSectionReqMaster.requestId AS requestId,  "
    +" tFrozenSectionReqMaster.patientId AS patientId,  "
    +" tFrozenSectionReqMaster.doctorId AS doctorId,  "
    +" tFrozenSectionReqMaster.surgeryId AS surgeryId,  "
    +" tFrozenSectionReqMaster.visitTypeId AS visitTypeId,  "
    +" tFrozenSectionReqMaster.specimenTypeId AS specimenTypeId,  "
    +" tFrozenSectionReqMaster.surgeryStartTime AS surgeryStartTime,  "
    +" tFrozenSectionReqMaster.reqRemark AS reqRemark,  "
    +" tFrozenSectionReqMaster.ackRemark AS ackRemark,  "
    +" tFrozenSectionReqMaster.admissionId AS admissionId,  "
    +" tFrozenSectionReqMaster.isDeleted AS isDeleted,  "
    +" tFrozenSectionReqMaster.isAccepted AS isAccepted,  "
    +" visitMst.visitTypeCode AS visitType,  "
    +" tSpecimanMst.desc AS specimanType,  "
    +" patientReg.uhidNumber AS uhid,  "
    +" genderMst.desc AS genderName,  "
    +" patientReg.dob AS dob,  "
    +" prefixMst.prefixCode || ' ' || patientReg.firstName || ' ' || patientReg.lastName AS patientDetails,  "
    +" 'Dr.' || ' ' || doctorMst.firstName || ' ' || doctorMst.lastName AS doctorDetails  "
    +"FROM  "
    +" TFrozenSectionReqMaster tFrozenSectionReqMaster  "
    +"INNER JOIN tFrozenSectionReqMaster.specimanMaster tSpecimanMst  "
    +"INNER JOIN tFrozenSectionReqMaster.visitTypeMaster visitMst  "
    +"INNER JOIN tFrozenSectionReqMaster.patientRegistration patientReg  "
    +"INNER JOIN tFrozenSectionReqMaster.doctorMaster doctorMst  "
    +"INNER JOIN patientReg.prefixMaster prefixMst  "
    +"INNER JOIN patientReg.genderMaster genderMst  "
    +"INNER JOIN tFrozenSectionReqMaster.admission admsion  "
    +"WHERE  "
    +" tFrozenSectionReqMaster.orgId =:orgId  "
    +" AND tFrozenSectionReqMaster.orgUnitId =:orgUnitId  "
    +" AND tFrozenSectionReqMaster.isDeleted = 'N'  "
    +" AND admsion.status = 'A'  "
    +" AND tSpecimanMst.status = 'A'  "
    +" AND visitMst.status = 'A'  "
    +" AND patientReg.status = 'A'  "
    +" AND doctorMst.status = 'A'  "
    +" AND prefixMst.status = 'A'  "
    +" AND genderMst.status = 'A'  "
    +" ORDER BY tFrozenSectionReqMaster.createdBy  "),

@NamedQuery(name = "GET_FROZEN_SECTION_REQ_LIST_COUNT", query = " SELECT  "
    +" COUNT(*)  "
    +"FROM  "
    +" TFrozenSectionReqMaster tFrozenSectionReqMaster  "
    +"INNER JOIN tFrozenSectionReqMaster.specimanMaster tSpecimanMst  "
    +"INNER JOIN tFrozenSectionReqMaster.visitTypeMaster visitMst  "
    +"INNER JOIN tFrozenSectionReqMaster.patientRegistration patientReg  "
    +"INNER JOIN tFrozenSectionReqMaster.doctorMaster doctorMst  "
    +"INNER JOIN patientReg.prefixMaster prefixMst  "
    +"INNER JOIN patientReg.genderMaster genderMst  "
    +"INNER JOIN tFrozenSectionReqMaster.admission admsion  "
    +"WHERE  "
    +" tFrozenSectionReqMaster.orgId =:orgId  "
    +" AND tFrozenSectionReqMaster.orgUnitId =:orgUnitId  "
    +" AND tFrozenSectionReqMaster.isDeleted = 'N'  "
    +" AND admsion.status = 'A'  "
    +" AND tSpecimanMst.status = 'A'  "
    +" AND visitMst.status = 'A'  "
    +" AND patientReg.status = 'A'  "
    +" AND doctorMst.status = 'A'  "
    +" AND prefixMst.status = 'A'  "
    +" AND genderMst.status = 'A'  ")

})

@Entity
@Table(name = "t_frozen_section_req", schema = "lab")
@SequenceGenerator(name = "t_seq_frozen_section_req", sequenceName = "lab.t_seq_frozen_section_req", allocationSize = 1)
public class TFrozenSectionReqMaster {
  
  
  @Id
  @Column(name = "request_id")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_seq_frozen_section_req")
  private int requestId;
  
  @Column(name = "org_id")
  private Integer orgId;

  @Column(name = "org_unit_id")
  private Integer orgUnitId;

  @Column(name = "patient_id")
  private Integer patientId;
  
  @Column(name = "doctor_id")
  private Integer doctorId;
  
  @Column(name = "surgery_id")
  private Integer surgeryId;
  
  @Column(name = "visit_type_id")
  private Integer visitTypeId;

  @Column(name = "specimen_type_id")
  private Integer specimenTypeId;
  
  @Column(name = "surgery_start_time")
  @Convert(converter = LocalTimeConverter.class)
  private Long surgeryStartTime;
  
  @Column(name = "req_remark")
  private String reqRemark;
  
  @Column(name = "ack_remark")
  private String ackRemark;
  
  @Column(name = "admission_id")
  private Integer admissionId;
  
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
  
  @Column(name = "is_accepted")
  private Character isAccepted;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "patient_id", insertable = false, nullable = false, updatable = false)
  private PatientRegistration patientRegistration;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "doctor_id", insertable = false, nullable = false, updatable = false)
  private DoctorMaster doctorMaster;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "visit_type_id", insertable = false, nullable = false, updatable = false)
  private VisitTypeMaster visitTypeMaster;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "surgery_id", insertable = false, nullable = false, updatable = false)
  private SurgeryMaster surgeryMaster;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "specimen_type_id", insertable = false, nullable = false, updatable = false)
  private SpecimanMaster specimanMaster;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "admission_id", insertable = false, nullable = false, updatable = false)
  private Admission admission;

  public int getRequestId() {
    return requestId;
  }

  public void setRequestId(int requestId) {
    this.requestId = requestId;
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

  public Integer getPatientId() {
    return patientId;
  }

  public void setPatientId(Integer patientId) {
    this.patientId = patientId;
  }

  public Integer getDoctorId() {
    return doctorId;
  }

  public void setDoctorId(Integer doctorId) {
    this.doctorId = doctorId;
  }

  public Integer getSurgeryId() {
    return surgeryId;
  }

  public void setSurgeryId(Integer surgeryId) {
    this.surgeryId = surgeryId;
  }

  public Integer getVisitTypeId() {
    return visitTypeId;
  }

  public void setVisitTypeId(Integer visitTypeId) {
    this.visitTypeId = visitTypeId;
  }

  public Integer getSpecimenTypeId() {
    return specimenTypeId;
  }

  public void setSpecimenTypeId(Integer specimenTypeId) {
    this.specimenTypeId = specimenTypeId;
  }

  public Long getSurgeryStartTime() {
    return surgeryStartTime;
  }

  public void setSurgeryStartTime(Long surgeryStartTime) {
    this.surgeryStartTime = surgeryStartTime;
  }

  public String getReqRemark() {
    return reqRemark;
  }

  public void setReqRemark(String reqRemark) {
    this.reqRemark = reqRemark;
  }

  public String getAckRemark() {
    return ackRemark;
  }

  public void setAckRemark(String ackRemark) {
    this.ackRemark = ackRemark;
  }

  public Integer getAdmissionId() {
    return admissionId;
  }

  public void setAdmissionId(Integer admissionId) {
    this.admissionId = admissionId;
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

  public PatientRegistration getPatientRegistration() {
    return patientRegistration;
  }

  public void setPatientRegistration(PatientRegistration patientRegistration) {
    this.patientRegistration = patientRegistration;
  }

  public DoctorMaster getDoctorMaster() {
    return doctorMaster;
  }

  public void setDoctorMaster(DoctorMaster doctorMaster) {
    this.doctorMaster = doctorMaster;
  }

  public VisitTypeMaster getVisitTypeMaster() {
    return visitTypeMaster;
  }

  public void setVisitTypeMaster(VisitTypeMaster visitTypeMaster) {
    this.visitTypeMaster = visitTypeMaster;
  }

  public SurgeryMaster getSurgeryMaster() {
    return surgeryMaster;
  }

  public void setSurgeryMaster(SurgeryMaster surgeryMaster) {
    this.surgeryMaster = surgeryMaster;
  }

  public SpecimanMaster getSpecimanMaster() {
    return specimanMaster;
  }

  public void setSpecimanMaster(SpecimanMaster specimanMaster) {
    this.specimanMaster = specimanMaster;
  }

  public Admission getAdmission() {
    return admission;
  }

  public void setAdmission(Admission admission) {
    this.admission = admission;
  }
  
  
  public Character getIsAccepted() {
    return isAccepted;
  }

  public void setIsAccepted(Character isAccepted) {
    this.isAccepted = isAccepted;
  }
  

}
