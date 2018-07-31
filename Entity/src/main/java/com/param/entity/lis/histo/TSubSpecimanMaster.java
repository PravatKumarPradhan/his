package com.param.entity.lis.histo;

import java.math.BigInteger;
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

/*	@NamedQuery(name = "GET_SPCIMEN_LIST", query = "SELECT tSpecimanMaster.tSpecimanId AS tSpecimanId,"
			+ " tSpecimanMaster.orgId AS orgId,"
			+ " tSpecimanMaster.orgUnitId AS orgUnitId,"
			+ " tSpecimanMaster.specimanExaminination AS specimanExaminination,"
			+ " tSpecimanMaster.labSampleDtlsId AS labSampleDtlsId,"
			+ " tSpecimanMaster.histopathlogyNumber AS histopathlogyNumber,"
			+ " tSpecimanMaster.barcodeNo AS barcodeNo,"
			+ " tSpecimanMaster.specimanTypeId AS specimanTypeId,"
			+ " tSpecimanMaster.specimanId AS specimanId,"
			+ " tSpecimanMaster.specimanName AS specimanName "
			+ " FROM TSpecimanMaster tSpecimanMaster "
			+ " WHERE tSpecimanMaster.orgId = :orgId "
			+ " AND tSpecimanMaster.orgUnitId = :orgUnitId"
			+ " AND tSpecimanMaster.isDeleted ='N'"),*/
  
  
  @NamedQuery(name = "GET_SUB_SPCIMEN_LIST", query = " SELECT "
          +" tSubSpecimanMaster.tSubSpecimanId AS tSubSpecimanId, "
          +" visitMst.visitTypeCode AS visitType, "
          +" testMst.testDesc AS testDesc, "
          +" speciMst.desc AS specimanType, "
          +" labSampleMst.visitTypeId AS visitTypeId, "
          +" testMst.testDesc AS testDesc, "
          +" speciMst.specimanId AS specimanId, "
          +" tSubSpecimanMaster.orgId AS orgId, "
          +" tSubSpecimanMaster.orgUnitId AS orgUnitId, "
          +" tSubSpecimanMaster.subSpecimanExaminination AS subSpecimanExaminination, "
          +" tSubSpecimanMaster.labSampleDtlsId AS labSampleDtlsId, "
          +" tSpecimanMst.histopathlogyNumber AS histopathlogyNumber, "
          +" tSubSpecimanMaster.subSpecimanBarcode AS subSpecimanBarcode, "
          +" tSpecimanMst.specimanTypeId AS specimanTypeId, "
          +" tSpecimanMst.specimanId AS specimanId, "
          +" tSpecimanMst.specimanName AS specimanName, "
          +" patientReg.uhidNumber AS uhid, "
          +" genderMst.desc AS genderName, "
          +" patientReg.dob AS dob, "
          +" prefixMst.prefixCode || ' ' || patientReg.firstName || ' ' || patientReg.lastName AS patientDetails, "
          +" 'Dr.' || ' ' || doctorMst.firstName || ' ' || doctorMst.lastName AS doctorDetails, "
          + "lstTTemplateRes.templateResId AS templateResId "
          +"FROM "
          +" TSubSpecimanMaster tSubSpecimanMaster "
          +"INNER JOIN tSubSpecimanMaster.tSpecimanMaster tSpecimanMst "
          +"INNER JOIN tSubSpecimanMaster.labSampleDetailsMaster labSampleDetailsMst "
          +"INNER JOIN labSampleDetailsMst.labSampleMaster labSampleMst "
          +"INNER JOIN labSampleDetailsMst.testMaster testMst "
          +"INNER JOIN testMst.specimanMaster speciMst "
          +"INNER JOIN labSampleDetailsMst.labSampleMaster labSampleMst "
          +"INNER JOIN labSampleMst.visitTypeMaster visitMst "
          +"INNER JOIN labSampleDetailsMst.patientRegistration patientReg "
          +"INNER JOIN labSampleDetailsMst.doctorMaster doctorMst "
          +"INNER JOIN patientReg.prefixMaster prefixMst "
          +"INNER JOIN labSampleDetailsMst.genderMaster genderMst "
          +" LEFT JOIN labSampleDetailsMst.listTTemplateResult lstTTemplateRes"
          +" WHERE "
          +" tSubSpecimanMaster.orgId = :orgId "
          +" AND tSubSpecimanMaster.orgUnitId = :orgUnitId "
          +" AND tSubSpecimanMaster.isDeleted = 'N' "
          +" AND labSampleDetailsMst.sampleStatusId IN (:sampleStatusIds)"
          +" AND testMst.deptId = :deptId"),

@NamedQuery(name = "GET_SUB_SPCIMEN_LIST_COUNT", query = "SELECT COUNT(*)"
        + " FROM TSubSpecimanMaster tSubSpecimanMaster "
        +" INNER JOIN tSubSpecimanMaster.labSampleDetailsMaster labSampleDetailsMst "
        +" INNER JOIN labSampleDetailsMst.testMaster testMst "
        + " WHERE tSubSpecimanMaster.orgId = :orgId "
        + " AND tSubSpecimanMaster.orgUnitId = :orgUnitId"
        + " AND tSubSpecimanMaster.isDeleted ='N'"
        + " AND labSampleDetailsMst.sampleStatusId IN (:sampleStatusIds)"
        + " AND testMst.deptId = :deptId"),
  
  
	
	@NamedQuery(name = "GET_GROSS_SPCIMEN_LIST", query = " SELECT "
			 +"	tSubSpecimanMaster.tSubSpecimanId AS tSubSpecimanId, "
			 +"	visitMst.visitTypeCode AS visitType, "
			 +"	tSpecimanMaster.histopathlogyNumber AS histopathlogyNumber, "
		     +"	speciMst.desc AS specimanType, "
			 +"	tSubSpecimanMaster.orgId AS orgId, "
			 +"	tSubSpecimanMaster.tSpecimanId AS tSpecimanId, "
			 +"	tSubSpecimanMaster.subSpecimanExaminination AS subSpecimanExaminination, "
			 +"	tSubSpecimanMaster.subSpecimanName AS subSpecimanName, "
			 +"	tSubSpecimanMaster.labSampleDtlsId AS labSampleDtlsId, "
			 +"	tSubSpecimanMaster.subSpcimanNo AS subSpcimanNo, "
			 +"	tSubSpecimanMaster.subSpecimanBarcode AS subSpecimanBarcode, "
			 +"	tSubSpecimanMaster.captureNote AS captureNote, "
			 +"	tSpecimanMaster.specimanTypeId AS specimanTypeId, "
			 +"	tSpecimanMaster.specimanId AS specimanId, "
			 +"	tSpecimanMaster.specimanName AS specimanName, "
			 +"	patientReg.uhidNumber AS uhid, "
			 +"	genderMst.desc AS genderName, "
			 +"	patientReg.dob AS dob, "
			 +"	prefixMst.prefixCode || ' ' || patientReg.firstName || ' ' || patientReg.lastName AS patientDetails, "
			 +"	'Dr.' || ' ' || doctorMst.firstName || ' ' || doctorMst.lastName AS doctorDetails "
			 +"FROM "
			 +"	TSubSpecimanMaster tSubSpecimanMaster "
			 +"INNER JOIN tSubSpecimanMaster.tSpecimanMaster tSpecimanMaster "
			 +"INNER JOIN tSpecimanMaster.labSampleDetailsMaster labSampleDetailsMst "
			 +"INNER JOIN labSampleDetailsMst.labSampleMaster labSampleMst "
			 +"INNER JOIN labSampleDetailsMst.testMaster testMst "
			 +"INNER JOIN testMst.specimanMaster speciMst "
			 +"INNER JOIN labSampleMst.visitTypeMaster visitMst "
			 +"INNER JOIN labSampleDetailsMst.patientRegistration patientReg "
			 +"INNER JOIN labSampleDetailsMst.doctorMaster doctorMst "
			 +"INNER JOIN patientReg.prefixMaster prefixMst "
			 +"INNER JOIN labSampleDetailsMst.genderMaster genderMst "
			 +"WHERE "
			 +"	tSpecimanMaster.orgId = :orgId "
			 +"	AND tSpecimanMaster.orgUnitId = :orgUnitId "
			 +"	AND tSpecimanMaster.isDeleted = 'N' "
			 +"	AND labSampleDetailsMst.sampleStatusId = :sampleStatusId "),
	
	@NamedQuery(name = "GET_GROSS_SPCIMEN_LIST_COUNT", query = "SELECT COUNT(*)"
			+ " FROM TSpecimanMaster tSpecimanMaster "
			+" INNER JOIN tSpecimanMaster.labSampleDetailsMaster labSampleDetailsMst "
			+ " WHERE tSpecimanMaster.orgId = :orgId "
			+ " AND tSpecimanMaster.orgUnitId = :orgUnitId"
			+ " AND tSpecimanMaster.isDeleted ='N'"
			+ " AND labSampleDetailsMst.sampleStatusId = :sampleStatusId"),
	
	
	@NamedQuery(name = "GET_MICROSCOPIC_EXAM_DATA_BY_SUB_SPCIMEN_ID", query = "SELECT tSubSpecimanMaster "
	        + " FROM TSubSpecimanMaster tSubSpecimanMaster "
	        + " WHERE tSubSpecimanMaster.orgId = :orgId "
	        + " AND tSubSpecimanMaster.orgUnitId = :orgUnitId"
	        + " AND tSubSpecimanMaster.tSubSpecimanId = :subSpecimanId"),
	
	
	/*@NamedQuery(name = "GET_PATIENT_SEARCH_BY_SPCIMAN", query = ""),*/
	
})


@Entity
@Table(name = "t_sub_speciman_master", schema = "lab")
@SequenceGenerator(name = "t_seq_sub_speciaman_master", sequenceName = "lab.t_seq_sub_speciaman_master", allocationSize = 1)
public class TSubSpecimanMaster {
	

	@Id
	@Column(name = "t_sub_speciman_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_seq_sub_speciaman_master")
	private int tSubSpecimanId;
	
	@Column(name = "org_id")
	private Integer orgId;

	@Column(name = "org_unit_id")
	private Integer orgUnitId;
	
	@Column(name = "t_speciman_id")
	private Integer tSpecimanId;
	
	@Column(name = "sub_speciman_name")
	private String subSpecimanName;
	
	@Column(name = "sub_speciman_examination")
	private String subSpecimanExaminination;

	@Column(name = "lab_sample_dtls_id")
	private Integer labSampleDtlsId;
	
	@Column(name = "sub_spciman_no")
	private String subSpcimanNo;
	
	@Column(name = "sub_speciman_barcode")
	private String subSpecimanBarcode;
	
	@Column(name = "capture_note")
	private String captureNote;
	
	@Column(name = "created_by")
	private Integer createdBy;
	
	@Column(name = "sub_speciman_num")
	private BigInteger subSpecimanNum;
	
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
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "lab_sample_dtls_id", insertable = false, nullable = false, updatable = false)
	private LabSampleDetailsMaster labSampleDetailsMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "t_speciman_id", insertable = false, nullable = false, updatable = false)
	private TSpecimanMaster tSpecimanMaster;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tSubSpecimanMaster")
	private List<TGrossMaster> listTGrossMaster;

	public int gettSubSpecimanId() {
		return tSubSpecimanId;
	}

	public void settSubSpecimanId(int tSubSpecimanId) {
		this.tSubSpecimanId = tSubSpecimanId;
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

	public Integer gettSpecimanId() {
		return tSpecimanId;
	}

	public void settSpecimanId(Integer tSpecimanId) {
		this.tSpecimanId = tSpecimanId;
	}

	public String getSubSpecimanExaminination() {
		return subSpecimanExaminination;
	}

	public void setSubSpecimanExaminination(String subSpecimanExaminination) {
		this.subSpecimanExaminination = subSpecimanExaminination;
	}

	public Integer getLabSampleDtlsId() {
		return labSampleDtlsId;
	}

	public void setLabSampleDtlsId(Integer labSampleDtlsId) {
		this.labSampleDtlsId = labSampleDtlsId;
	}

	public String getSubSpcimanNo() {
		return subSpcimanNo;
	}

	public void setSubSpcimanNo(String subSpcimanNo) {
		this.subSpcimanNo = subSpcimanNo;
	}

	public String getSubSpecimanBarcode() {
		return subSpecimanBarcode;
	}

	public void setSubSpecimanBarcode(String subSpecimanBarcode) {
		this.subSpecimanBarcode = subSpecimanBarcode;
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

	public LabSampleDetailsMaster getLabSampleDetailsMaster() {
		return labSampleDetailsMaster;
	}

	public void setLabSampleDetailsMaster(LabSampleDetailsMaster labSampleDetailsMaster) {
		this.labSampleDetailsMaster = labSampleDetailsMaster;
	}

	public TSpecimanMaster gettSpecimanMaster() {
		return tSpecimanMaster;
	}

	public void settSpecimanMaster(TSpecimanMaster tSpecimanMaster) {
		this.tSpecimanMaster = tSpecimanMaster;
	}

	public List<TGrossMaster> getListTGrossMaster() {
		return listTGrossMaster;
	}

	public void setListTGrossMaster(List<TGrossMaster> listTGrossMaster) {
		this.listTGrossMaster = listTGrossMaster;
	}

	public String getCaptureNote() {
		return captureNote;
	}

	public void setCaptureNote(String captureNote) {
		this.captureNote = captureNote;
	}

	public String getSubSpecimanName() {
		return subSpecimanName;
	}

	public void setSubSpecimanName(String subSpecimanName) {
		this.subSpecimanName = subSpecimanName;
	}

	public BigInteger getSubSpecimanNum() {
		return subSpecimanNum;
	}

	public void setSubSpecimanNum(BigInteger subSpecimanNum) {
		this.subSpecimanNum = subSpecimanNum;
	}
	
}
