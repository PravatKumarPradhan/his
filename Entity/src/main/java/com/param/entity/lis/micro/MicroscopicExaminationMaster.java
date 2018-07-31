package com.param.entity.lis.micro;

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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.param.entity.lis.global.MicrobiologyStainMaster;
import com.param.entity.lis.global.OrganismGroupMaster;
import com.param.entity.lis.global.StainigMaster;
import com.param.entity.lis.transaction.LabSampleDetailsMaster;
import com.param.entity.lis.common.LocalTimeConverter;

@NamedQueries({

    @NamedQuery(name = "GET_MICRO_SCOPIC_EXAMINATION_MASTER_BY_RESULT_ID",
        query = " SELECT " + " microscopicExaminationMaster.microscopicExaId AS microscopicExaId, "
            + " microscopicExaminationMaster.orgId  AS orgId, "
            + " microscopicExaminationMaster.orgUnitId  AS orgUnitId, "
            + " microscopicExaminationMaster.stainingId  AS stainingId, "
            + " microscopicExaminationMaster.microbiologyStainMaster.desc AS stainingName, "
            + " microscopicExaminationMaster.labSampleDtlsId  AS labSampleDtlsId, "
            + " microscopicExaminationMaster.createdBy  AS createdBy, "
            + " microscopicExaminationMaster.createdDate  AS createdDate, "
            + " microscopicExaminationMaster.updatedBy  AS updatedBy, "
            + " microscopicExaminationMaster.updatedDate  AS updatedDate, "
            + " microscopicExaminationMaster.isDeleted  AS isDeleted  "
            + "	FROM MicroscopicExaminationMaster microscopicExaminationMaster "
            + " WHERE microscopicExaminationMaster.labSampleDtlsId = :labSampleDtlsId"
            + "	AND microscopicExaminationMaster.orgId =:orgId "
            + " AND microscopicExaminationMaster.orgUnitId =:orgUnitId "
            + " AND microscopicExaminationMaster.isDeleted = 'N' "),

    @NamedQuery(name = "GET_MICRO_SCOPIC_EXAMINATION_DETAILS",
        query = " SELECT "
            + " microscopicExaminationDetailsMaster.examinationDetailsId AS examinationDetailsId, "
            + " microscopicExaminationDetailsMaster.orgId AS orgId, "
            + " microscopicExaminationDetailsMaster.orgUnitId AS orgUnitId, "
            + " microscopicExaminationDetailsMaster.microscopicExaId AS microscopicExaId, "
            + " microscopicExaminationDetailsMaster.organismGroupId AS organismGroupId, "
            + " microscopicExaminationDetailsMaster.organismGroupMaster.desc AS microOrgGroupName, "
            + " microscopicExaminationDetailsMaster.createdBy AS createdBy, "
            + " microscopicExaminationDetailsMaster.createdDate AS createdDate , "
            + " microscopicExaminationDetailsMaster.updatedBy AS updatedBy, "
            + " microscopicExaminationDetailsMaster.updatedDate AS updatedDate, "
            + " microscopicExaminationDetailsMaster.isDeleted AS isDeleted"
            + "	FROM MicroscopicExaminationDetailsMaster microscopicExaminationDetailsMaster "
            + " WHERE microscopicExaminationDetailsMaster.microscopicExaId = :microscopicExaId"
            + "	AND microscopicExaminationDetailsMaster.orgId =:orgId "
            + " AND microscopicExaminationDetailsMaster.orgUnitId =:orgUnitId "
            + " AND microscopicExaminationDetailsMaster.isDeleted = 'N' "),


    @NamedQuery(name = "GET_MICRO_SCOPIC_EXAMINATION_SUB_DETAILS", query = " SELECT "
        + " microscopicExaminationSubDetailsMaster.microexaSubDetailsId AS microexaSubDetailsId , "
        + " microscopicExaminationSubDetailsMaster.orgId AS orgId, "
        + " microscopicExaminationSubDetailsMaster.orgUnitId AS orgUnitId, "
        + " microscopicExaminationSubDetailsMaster.microRemark AS microRemark, "
        + " microscopicExaminationSubDetailsMaster.examinationDetailsId AS examinationDetailsId, "
        + " microscopicExaminationSubDetailsMaster.organismId AS organismId, "
        + " microscopicExaminationSubDetailsMaster.createdBy AS createdBy, "
        + " microscopicExaminationSubDetailsMaster.createdDate AS createdDate, "
        + " microscopicExaminationSubDetailsMaster.updatedBy AS updatedBy, "
        + " microscopicExaminationSubDetailsMaster.updatedDate AS updatedDate, "
        + " microscopicExaminationSubDetailsMaster.isDeleted AS isDeleted "
        + "	FROM MicroscopicExaminationSubDetailsMaster microscopicExaminationSubDetailsMaster "
        + " WHERE microscopicExaminationSubDetailsMaster.examinationDetailsId = :examinationDetailsId"
        + "	AND microscopicExaminationSubDetailsMaster.orgId =:orgId "
        + " AND microscopicExaminationSubDetailsMaster.orgUnitId =:orgUnitId "
        + " AND microscopicExaminationSubDetailsMaster.isDeleted = 'N' ")

})



@Entity
@Table(name = "t_microscopic_examination", schema = "lab")
@SequenceGenerator(name = "t_seq_microscopic_examination",
    sequenceName = "lab.t_seq_microscopic_examination", allocationSize = 1)
public class MicroscopicExaminationMaster {

  @Id
  @Column(name = "microscopic_exa_id")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_seq_microscopic_examination")
  private int microscopicExaId;

  @Column(name = "org_id")
  private Integer orgId;

  @Column(name = "org_unit_id")
  private Integer orgUnitId;

  @Column(name = "micro_stain_id")
  private Integer stainingId;

  @Column(name = "lab_sample_dtls_id")
  private Integer labSampleDtlsId;

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
  
  @Column(name = "is_completed")
  private Character isCompleted;

  @OneToMany(fetch = FetchType.LAZY,
      mappedBy = "microscopicExaminationMaster")
  private List<MicroscopicExaminationDetailsMaster> listMicroscopicExaminationDetailsMasters;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "lab_sample_dtls_id", insertable = false, nullable = false, updatable = false)
  private LabSampleDetailsMaster labSampleDetailsMaster;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "micro_stain_id", insertable = false, nullable = false, updatable = false)
  private MicrobiologyStainMaster microbiologyStainMaster;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "organism_group_id", insertable = false, nullable = false, updatable = false)
  private OrganismGroupMaster organismGroupMaster;

  public int getMicroscopicExaId() {
    return microscopicExaId;
  }

  public void setMicroscopicExaId(int microscopicExaId) {
    this.microscopicExaId = microscopicExaId;
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

  public Integer getStainingId() {
    return stainingId;
  }

  public void setStainingId(Integer stainingId) {
    this.stainingId = stainingId;
  }

  public Integer getLabSampleDtlsId() {
    return labSampleDtlsId;
  }

  public void setLabSampleDtlsId(Integer labSampleDtlsId) {
    this.labSampleDtlsId = labSampleDtlsId;
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
    this.isDeleted = (isDeleted == '\u0000') ? 'N' : isDeleted;
  }

  public List<MicroscopicExaminationDetailsMaster> getListMicroscopicExaminationDetailsMasters() {
    return listMicroscopicExaminationDetailsMasters;
  }

  public void setListMicroscopicExaminationDetailsMasters(
      List<MicroscopicExaminationDetailsMaster> listMicroscopicExaminationDetailsMasters) {
    this.listMicroscopicExaminationDetailsMasters = listMicroscopicExaminationDetailsMasters;
  }


  public MicrobiologyStainMaster getMicrobiologyStainMaster() {
    return microbiologyStainMaster;
  }

  public void setMicrobiologyStainMaster(MicrobiologyStainMaster microbiologyStainMaster) {
    this.microbiologyStainMaster = microbiologyStainMaster;
  }

  public OrganismGroupMaster getOrganismGroupMaster() {
    return organismGroupMaster;
  }

  public void setOrganismGroupMaster(OrganismGroupMaster organismGroupMaster) {
    this.organismGroupMaster = organismGroupMaster;
  }


  public LabSampleDetailsMaster getLabSampleDetailsMaster() {
    return labSampleDetailsMaster;
  }

  public void setLabSampleDetailsMaster(LabSampleDetailsMaster labSampleDetailsMaster) {
    this.labSampleDetailsMaster = labSampleDetailsMaster;
  }

  public Character getIsCompleted() {
    return isCompleted;
  }

  public void setIsCompleted(Character isCompleted) {
    this.isCompleted = (isCompleted == '\u0000') ? 'N' : isCompleted;
  }


}
