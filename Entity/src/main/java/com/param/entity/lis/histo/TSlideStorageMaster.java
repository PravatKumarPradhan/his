package com.param.entity.lis.histo;

import javax.persistence.Column;



import javax.persistence.Convert;
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
import com.param.entity.lis.unit.RackMaster;
import com.param.entity.lis.unit.RackShelfMaster;
import com.param.entity.lis.common.LocalTimeConverter;




@NamedQueries({
  
  @NamedQuery(name="SLIDE_LIST_FOR_STORAGE",query= " SELECT  "
      +" visitMst.visitTypeCode AS visitType,  "
      +" testMst.testDesc AS testDesc,  "
      +" speciMst.desc AS specimanType,  "
      +" labSampleMst.visitTypeId AS visitTypeId,  "
      +" testMst.testDesc AS testDesc,  "
      +" speciMst.specimanId AS specimanId,  "
      +" tSpecimanMst.histopathlogyNumber AS histopathlogyNumber,  "
      +" tSubSpecimanMst.subSpecimanBarcode AS subSpecimanBarcode,  "
      +" tSpecimanMst.specimanTypeId AS specimanTypeId,  "
      +" tSpecimanMst.specimanId AS specimanId,  "
      +" tSpecimanMst.specimanName AS specimanName,  "
      +" patientReg.uhidNumber AS uhid,  "
      +" genderMst.desc AS genderName,  "
      +" patientReg.dob AS dob,  "
      +" prefixMst.prefixCode || ' ' || patientReg.firstName || ' ' || patientReg.lastName AS patientDetails,  "
      +" 'Dr.' || ' ' || doctorMst.firstName || ' ' || doctorMst.lastName AS doctorDetails,  "
      +" tBlockMst.blockNo AS blockNo,  "
      +" tGrosssMst.grossNo AS grossNo,  "
      +" tSlideMaster.slideNo AS slideNo,  "
      +" tSlideMaster.tSlideId AS tSlideId  "
      +"FROM  "
      +" TSlideMaster tSlideMaster  "
      +"INNER JOIN tSlideMaster.listTMicroExaDetails lstTMicroExaDetails  "
      +"INNER JOIN tSlideMaster.tBlockMaster tBlockMst  "
      +"INNER JOIN tSlideMaster.tGrossMaster tGrosssMst  "
      +"INNER JOIN tSlideMaster.tSubSpecimanMaster tSubSpecimanMst  "
      +"INNER JOIN tSubSpecimanMst.tSpecimanMaster tSpecimanMst  "
      +"INNER JOIN tSlideMaster.labSampleDetailsMaster labSampleDetailsMst  "
      +"INNER JOIN labSampleDetailsMst.labSampleMaster labSampleMst  "
      +"INNER JOIN labSampleDetailsMst.testMaster testMst  "
      +"INNER JOIN testMst.specimanMaster speciMst  "
      +"INNER JOIN labSampleMst.visitTypeMaster visitMst  "
      +"INNER JOIN labSampleDetailsMst.patientRegistration patientReg  "
      +"INNER JOIN labSampleDetailsMst.doctorMaster doctorMst  "
      +"INNER JOIN patientReg.prefixMaster prefixMst  "
      +"INNER JOIN labSampleDetailsMst.genderMaster genderMst  "
      +"WHERE  "
      +" lstTMicroExaDetails.orgId = :orgId  "
      +" AND lstTMicroExaDetails.orgUnitId = :orgUnitId  "
      +" AND lstTMicroExaDetails.isDeleted = 'N'  "
      +" AND lstTMicroExaDetails.sendForStorage = 'Y'  "
      +" AND tSlideMaster.tSlideId NOT IN(  "
      +"     SELECT  "
      +"         tSlideStorageMst.tSlideId  "
      +"     FROM  "
      +"         TSlideStorageMaster tSlideStorageMst  "
      +"     WHERE  "
      +"         tSlideStorageMst.orgId = :orgId  "
      +"         AND tSlideStorageMst.orgUnitId = :orgUnitId  "
      +"         AND tSlideStorageMst.isDeleted = 'N'  "
      +" )  "),
  
  @NamedQuery(name="SLIDE_LIST_FOR_STORAGE_COUNT",query= " SELECT  "
      +" COUNT(*)  "
      +"FROM  "
      +" TSlideMaster tSlideMaster  "
      +"INNER JOIN tSlideMaster.listTMicroExaDetails lstTMicroExaDetails  "
      +"WHERE  "
      +" lstTMicroExaDetails.orgId = :orgId  "
      +" AND lstTMicroExaDetails.orgUnitId = :orgUnitId  "
      +" AND lstTMicroExaDetails.isDeleted = 'N'  "
      +" AND lstTMicroExaDetails.sendForStorage = 'Y'  "
      +" AND tSlideMaster.tSlideId NOT IN(  "
      +"     SELECT  "
      +"         tSlideStorageMst.tSlideId  "
      +"     FROM  "
      +"         TSlideStorageMaster tSlideStorageMst  "
      +"     WHERE  "
      +"         tSlideStorageMst.orgId = :orgId  "
      +"         AND tSlideStorageMst.orgUnitId = :orgUnitId  "
      +"         AND tSlideStorageMst.isDeleted = 'N'  "
      +" )  "),
  
  @NamedQuery(name="GET_STORED_SLIDE_BY_SHELF",query= " SELECT  "
      +" visitMst.visitTypeCode AS visitType,  "
      +" testMst.testDesc AS testDesc,  "
      +" speciMst.desc AS specimanType,  "
      +" labSampleMst.visitTypeId AS visitTypeId,  "
      +" testMst.testDesc AS testDesc,  "
      +" speciMst.specimanId AS specimanId,  "
      +" tSpecimanMst.histopathlogyNumber AS histopathlogyNumber,  "
      +" tSubSpecimanMst.subSpecimanBarcode AS subSpecimanBarcode,  "
      +" tSpecimanMst.specimanTypeId AS specimanTypeId,  "
      +" tSpecimanMst.specimanId AS specimanId,  "
      +" tSpecimanMst.specimanName AS specimanName,  "
      +" patientReg.uhidNumber AS uhid,  "
      +" genderMst.desc AS genderName,  "
      +" patientReg.dob AS dob,  "
      +" prefixMst.prefixCode || ' ' || patientReg.firstName || ' ' || patientReg.lastName AS patientDetails,  "
      +" 'Dr.' || ' ' || doctorMst.firstName || ' ' || doctorMst.lastName AS doctorDetails,  "
      +" tBlockMst.blockNo AS blockNo,  "
      +" tGrosssMst.grossNo AS grossNo,  "
      +" tSlideMst.slideNo AS slideNo,  "
      +" rackMst.rackCode AS rackCode,  "
      +" rackShelfMst.shelfCode AS shelfCode  "
      +"FROM  "
      +" TSlideStorageMaster tSlideStorageMst  "
      +"INNER JOIN tSlideStorageMst.rackShelfMaster rackShelfMst  "
      +"INNER JOIN rackShelfMst.rackMaster rackMst  "
      +"INNER JOIN tSlideStorageMst.tSlideMaster tSlideMst  "
      +"INNER JOIN tSlideMst.tBlockMaster tBlockMst  "
      +"INNER JOIN tSlideMst.tGrossMaster tGrosssMst  "
      +"INNER JOIN tSlideMst.tSubSpecimanMaster tSubSpecimanMst  "
      +"INNER JOIN tSubSpecimanMst.tSpecimanMaster tSpecimanMst  "
      +"INNER JOIN tSlideMst.labSampleDetailsMaster labSampleDetailsMst  "
      +"INNER JOIN labSampleDetailsMst.labSampleMaster labSampleMst  "
      +"INNER JOIN labSampleDetailsMst.testMaster testMst  "
      +"INNER JOIN testMst.specimanMaster speciMst  "
      +"INNER JOIN labSampleMst.visitTypeMaster visitMst  "
      +"INNER JOIN labSampleDetailsMst.patientRegistration patientReg  "
      +"INNER JOIN labSampleDetailsMst.doctorMaster doctorMst  "
      +"INNER JOIN patientReg.prefixMaster prefixMst  "
      +"INNER JOIN labSampleDetailsMst.genderMaster genderMst  "
      +"WHERE  "
      +" tSlideStorageMst.orgId = :orgId  "
      +" AND tSlideStorageMst.orgUnitId = :orgUnitId  "
      +" AND tSlideStorageMst.isDeleted = 'N'  "
      +" AND tSlideStorageMst.isSlideAccepted = 'A'  "
      +" AND rackShelfMst.rackId =:rackId  "
      +" AND rackShelfMst.shelfId =:shelfId  "
),
  
  @NamedQuery(name="STORED_SLIDE_LIST",query= " SELECT  "
      +" visitMst.visitTypeCode AS visitType,  "
      +" testMst.testDesc AS testDesc,  "
      +" speciMst.desc AS specimanType,  "
      +" labSampleMst.visitTypeId AS visitTypeId,  "
      +" testMst.testDesc AS testDesc,  "
      +" speciMst.specimanId AS specimanId,  "
      +" tSpecimanMst.histopathlogyNumber AS histopathlogyNumber,  "
      +" tSubSpecimanMst.subSpecimanBarcode AS subSpecimanBarcode,  "
      +" tSpecimanMst.specimanTypeId AS specimanTypeId,  "
      +" tSpecimanMst.specimanId AS specimanId,  "
      +" tSpecimanMst.specimanName AS specimanName,  "
      +" patientReg.uhidNumber AS uhid,  "
      +" genderMst.desc AS genderName,  "
      +" patientReg.dob AS dob,  "
      +" prefixMst.prefixCode || ' ' || patientReg.firstName || ' ' || patientReg.lastName AS patientDetails,  "
      +" 'Dr.' || ' ' || doctorMst.firstName || ' ' || doctorMst.lastName AS doctorDetails,  "
      +" tBlockMst.blockNo AS blockNo,  "
      +" tGrosssMst.grossNo AS grossNo,  "
      +" tSlideMst.slideNo AS slideNo,  "
      +" rackMst.rackName AS rackCode,  "
      +" rackShelfMst.shelfName AS shelfCode  "
      +"FROM  "
      +" TSlideStorageMaster tSlideStorageMst  "
      +"INNER JOIN tSlideStorageMst.rackShelfMaster rackShelfMst  "
      +"INNER JOIN rackShelfMst.rackMaster rackMst  "
      +"INNER JOIN tSlideStorageMst.tSlideMaster tSlideMst  "
      +"INNER JOIN tSlideMst.tBlockMaster tBlockMst  "
      +"INNER JOIN tSlideMst.tGrossMaster tGrosssMst  "
      +"INNER JOIN tSlideMst.tSubSpecimanMaster tSubSpecimanMst  "
      +"INNER JOIN tSubSpecimanMst.tSpecimanMaster tSpecimanMst  "
      +"INNER JOIN tSlideMst.labSampleDetailsMaster labSampleDetailsMst  "
      +"INNER JOIN labSampleDetailsMst.labSampleMaster labSampleMst  "
      +"INNER JOIN labSampleDetailsMst.testMaster testMst  "
      +"INNER JOIN testMst.specimanMaster speciMst  "
      +"INNER JOIN labSampleMst.visitTypeMaster visitMst  "
      +"INNER JOIN labSampleDetailsMst.patientRegistration patientReg  "
      +"INNER JOIN labSampleDetailsMst.doctorMaster doctorMst  "
      +"INNER JOIN patientReg.prefixMaster prefixMst  "
      +"INNER JOIN labSampleDetailsMst.genderMaster genderMst  "
      +"WHERE  "
      +" tSlideStorageMst.orgId =:orgId"
      +" AND tSlideStorageMst.orgUnitId =:orgUnitId  "
      +" AND tSlideStorageMst.isDeleted = 'N'  "
      +" AND tSlideStorageMst.isSlideAccepted = 'A'  "
),
  @NamedQuery(name="STORED_SLIDE_LIST_COUNT",query=  " SELECT  "
      +" COUNT(*)  "
      +"FROM  "
      +" TSlideStorageMaster tSlideStorageMst  "
      +"WHERE  "
      +" tSlideStorageMst.orgId =:orgId  "
      +" AND tSlideStorageMst.orgUnitId =:orgUnitId  "
      +" AND tSlideStorageMst.isDeleted = 'N'  "
      +" AND tSlideStorageMst.isSlideAccepted = 'A'  "
)
  
  
})

@Entity
@Table(name = "t_slide_storage", schema = "lab")
@SequenceGenerator(name = "t_seq_slide_storage", sequenceName = "lab.t_seq_slide_storage", allocationSize = 1)
public class TSlideStorageMaster {
  
  @Id
  @Column(name = "t_slide_storage_id")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_seq_slide_storage")
  private int tSlideStorageId;

  @Column(name = "rack_id")
  private Integer rackId;
  
  @Column(name = "shelf_id")
  private Integer shelfId;
  
  @Column(name = "t_slide_id")
  private Integer tSlideId;
  
  @Column(name = "remark")
  private String remark;

  @Column(name = "is_slide_accepted")
  private Character isSlideAccepted;
  
  @Column(name = "org_id")
  private Integer orgId;

  @Column(name = "org_unit_id")
  private Integer orgUnitId;
  
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
  
  @ManyToOne
  @JoinColumn(name = "t_slide_id", insertable = false, nullable = false, updatable = false)
  private TSlideMaster tSlideMaster;
  
  @ManyToOne
  @JoinColumn(name = "rack_id", insertable = false, nullable = false, updatable = false)
  private RackMaster rackMaster;
  
  @ManyToOne
  @JoinColumn(name = "shelf_id", insertable = false, nullable = false, updatable = false)
  private RackShelfMaster rackShelfMaster;

  public int gettSlideStorageId() {
    return tSlideStorageId;
  }

  public void settSlideStorageId(int tSlideStorageId) {
    this.tSlideStorageId = tSlideStorageId;
  }

  public Integer getRackId() {
    return rackId;
  }

  public void setRackId(Integer rackId) {
    this.rackId = rackId;
  }

  public Integer getShelfId() {
    return shelfId;
  }

  public void setShelfId(Integer shelfId) {
    this.shelfId = shelfId;
  }

  public Integer gettSlideId() {
    return tSlideId;
  }

  public void settSlideId(Integer tSlideId) {
    this.tSlideId = tSlideId;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public Character getIsSlideAccepted() {
    return isSlideAccepted;
  }

  public void setIsSlideAccepted(Character isSlideAccepted) {
    this.isSlideAccepted = isSlideAccepted;
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

  public TSlideMaster gettSlideMaster() {
    return tSlideMaster;
  }

  public void settSlideMaster(TSlideMaster tSlideMaster) {
    this.tSlideMaster = tSlideMaster;
  }

  public RackMaster getRackMaster() {
    return rackMaster;
  }

  public void setRackMaster(RackMaster rackMaster) {
    this.rackMaster = rackMaster;
  }

  public RackShelfMaster getRackShelfMaster() {
    return rackShelfMaster;
  }

  public void setRackShelfMaster(RackShelfMaster rackShelfMaster) {
    this.rackShelfMaster = rackShelfMaster;
  }
  

}
