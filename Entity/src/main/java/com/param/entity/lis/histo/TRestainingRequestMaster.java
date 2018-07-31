
package com.param.entity.lis.histo;

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
import com.param.entity.lis.transaction.LabSampleDetailsMaster;
import com.param.entity.lis.common.LocalTimeConverter;

@NamedQueries({
 @NamedQuery(name = "GET_RESTAIN_WORKLIST", query = " SELECT "
    + " DISTINCT(tRestainingRequestMaster.tRestainingReqId) AS tRestainingReqId, "
    + " tRestainingRequestMaster.orgId AS orgId, "
    + " tRestainingRequestMaster.orgUnitId AS orgUnitId, "
    + " tRestainingRequestMaster.labSampleDtlsId AS labSampleDtlsId, "
    + " visitMst.visitTypeCode AS visitType, "
    + " tSpecimanMst.histopathlogyNumber AS histopathlogyNumber, "
    + " speciMst.desc AS specimanType, " 
    + " tSubSpecimanMst.subSpcimanNo AS subSpcimanNo, "
    + " tSubSpecimanMst.subSpecimanBarcode AS subSpecimanBarcode, "
    + " tSpecimanMst.specimanTypeId AS specimanTypeId, "
    + " tSpecimanMst.specimanId AS specimanId, "
    + " tSpecimanMst.specimanName AS specimanName, "
    + " patientReg.uhidNumber AS uhid, "
    + " genderMst.desc AS genderName, " 
    + " patientReg.dob AS dob, "
    + " prefixMst.prefixCode || ' ' || patientReg.firstName || ' ' || patientReg.lastName AS patientDetails, "
    + " 'Dr.' || ' ' || doctorMst.firstName || ' ' || doctorMst.lastName AS doctorDetails "
    + " FROM "
    + " TRestainingRequestMaster tRestainingRequestMaster "
    + " INNER JOIN tRestainingRequestMaster.listTRestainingRequestDetailsMaster lstTRestainingRequestDtlsMst "
    + " INNER  JOIN lstTRestainingRequestDtlsMst.listTRestainingReqSubDetailsMaster lstTRestainingReqSubDtlsMst "
    + " INNER JOIN tRestainingRequestMaster.tSubSpecimanMaster tSubSpecimanMst "
    + " INNER JOIN tSubSpecimanMst.tSpecimanMaster tSpecimanMst "
    + " INNER JOIN tRestainingRequestMaster.labSampleDetailsMaster labSampleDetailsMst "
    + " INNER JOIN labSampleDetailsMst.labSampleMaster labSampleMst "
    + " INNER JOIN labSampleDetailsMst.testMaster testMst "
    + " INNER JOIN testMst.specimanMaster speciMst "
    + " INNER JOIN labSampleMst.visitTypeMaster visitMst "
    + " INNER JOIN labSampleDetailsMst.patientRegistration patientReg "
    + " INNER JOIN labSampleDetailsMst.doctorMaster doctorMst "
    + " INNER JOIN patientReg.prefixMaster prefixMst "
    + " INNER JOIN labSampleDetailsMst.genderMaster genderMst "
    + " WHERE "
    + " tRestainingRequestMaster.orgId = :orgId " 
    + " AND tRestainingRequestMaster.orgUnitId = :orgUnitId "
    + " AND tRestainingRequestMaster.isDeleted = 'N' "
    + " AND lstTRestainingRequestDtlsMst.isDeleted = 'N' "
    + " AND lstTRestainingReqSubDtlsMst.isDeleted = 'N' "
    + " AND lstTRestainingReqSubDtlsMst.isSlideCreated = 'N' "),
   

    @NamedQuery(name = "GET_RESTAIN_WORKLIST_COUNT",
        query = " SELECT COUNT(DISTINCT tRestainingRequestMaster.tRestainingReqId) " 
            + " FROM "
            + " TRestainingRequestMaster tRestainingRequestMaster "
            + " INNER JOIN tRestainingRequestMaster.listTRestainingRequestDetailsMaster lstTRestainingRequestDtlsMst "
            + " INNER  JOIN lstTRestainingRequestDtlsMst.listTRestainingReqSubDetailsMaster lstTRestainingReqSubDtlsMst " 
            + " WHERE "
            + " tRestainingRequestMaster.orgId =:orgId "
            + " AND tRestainingRequestMaster.orgUnitId =:orgUnitId "
            + " AND tRestainingRequestMaster.isDeleted = 'N' "
            + " AND lstTRestainingRequestDtlsMst.isDeleted = 'N' "
            + " AND lstTRestainingReqSubDtlsMst.isDeleted = 'N' "
            + " AND lstTRestainingReqSubDtlsMst.isSlideCreated = 'N' "),


})



@Entity
@Table(name = "t_restaining_req_master", schema = "lab")
@SequenceGenerator(name = "t_seq_restaining_req_master",
    sequenceName = "lab.t_seq_restaining_req_master", allocationSize = 1)
public class TRestainingRequestMaster {

  @Id
  @Column(name = "t_restaining_req_id")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_seq_restaining_req_master")
  private int tRestainingReqId;

  @Column(name = "org_id")
  private Integer orgId;

  @Column(name = "org_unit_id")
  private Integer orgUnitId;

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

  @Column(name = "t_sub_speciman_id")
  private Integer tSubSpecimanId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "lab_sample_dtls_id", insertable = false, nullable = false, updatable = false)
  private LabSampleDetailsMaster labSampleDetailsMaster;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "t_sub_speciman_id", insertable = false, nullable = false, updatable = false)
  private TSubSpecimanMaster tSubSpecimanMaster;
  

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,
      mappedBy = "tRestainingRequestMaster")
  private List<TRestainingRequestDetailsMaster> listTRestainingRequestDetailsMaster;

  public int gettRestainingReqId() {
    return tRestainingReqId;
  }

  public void settRestainingReqId(int tRestainingReqId) {
    this.tRestainingReqId = tRestainingReqId;
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

  public Integer getLabSampleDtlsId() {
    return labSampleDtlsId;
  }

  public void setLabSampleDtlsId(Integer labSampleDtlsId) {
    this.labSampleDtlsId = labSampleDtlsId;
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

  public LabSampleDetailsMaster getLabSampleDetailsMaster() {
    return labSampleDetailsMaster;
  }

  public void setLabSampleDetailsMaster(LabSampleDetailsMaster labSampleDetailsMaster) {
    this.labSampleDetailsMaster = labSampleDetailsMaster;
  }

  public List<TRestainingRequestDetailsMaster> getListTRestainingRequestDetailsMaster() {
    return listTRestainingRequestDetailsMaster;
  }

  public void setListTRestainingRequestDetailsMaster(
      List<TRestainingRequestDetailsMaster> listTRestainingRequestDetailsMaster) {
    this.listTRestainingRequestDetailsMaster = listTRestainingRequestDetailsMaster;
  }

  public Integer getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(Integer createdBy) {
    this.createdBy = createdBy;
  }

  public Integer gettSubSpecimanId() {
    return tSubSpecimanId;
  }

  public void settSubSpecimanId(Integer tSubSpecimanId) {
    this.tSubSpecimanId = tSubSpecimanId;
  }

  public TSubSpecimanMaster gettSubSpecimanMaster() {
    return tSubSpecimanMaster;
  }

  public void settSubSpecimanMaster(TSubSpecimanMaster tSubSpecimanMaster) {
    this.tSubSpecimanMaster = tSubSpecimanMaster;
  }



}
