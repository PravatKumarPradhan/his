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


	
	@NamedQuery(name = "GET_GROSS_LIST_BY_SUBSPECIAMAN", query = " SELECT "
			 +"	tGrossMaster.tGrossId AS tGrossId, "
			 +"	tGrossMaster.orgId AS orgId, "
			 +"	tGrossMaster.orgUnitId AS orgUnitId, "
			 +"	tGrossMaster.tSubSpecimanId AS tSubSpecimanId, "
			 +"	tGrossMaster.labSampleDtlsId AS labSampleDtlsId, "
			 +"	tGrossMaster.grossBarcode AS grossBarcode, "
			 +"	tGrossMaster.grossNo AS grossNo, "
			 +"	tGrossMaster.grossDesc AS grossDesc, "
			 +"	tGrossMaster.captureNote AS captureNote "
			 +"FROM "
			 +"	TGrossMaster tGrossMaster "
			 +"WHERE "
			 +"	tGrossMaster.orgId = :orgId "
			 +"	AND tGrossMaster.orgUnitId =:orgUnitId "
			 +"	AND tGrossMaster.isDeleted = 'N' "
			 +"	AND tGrossMaster.tSubSpecimanId =:tSubSpecimanId "),
	
	   @NamedQuery(name = "GET_GROSS_LIST_BY_SUBSPECIAMAN_SLIDE", query = " SELECT "
           +" tGrossMaster "
           +"FROM "
           +" TGrossMaster tGrossMaster "
           +"WHERE "
           +" tGrossMaster.orgId = :orgId "
           +" AND tGrossMaster.orgUnitId = :orgUnitId "
           +" AND tGrossMaster.isDeleted = 'N' "
           +" AND tGrossMaster.tSubSpecimanId = :tSubSpecimanId "),
	
	  @NamedQuery(name = "GET_GROSS_LIST_FOR_SLIDE_CREATION", query = " SELECT "
          +" tGrossMaster.tGrossId AS tGrossId, "
          +" tGrossMaster.orgId AS orgId, "
          +" tGrossMaster.orgUnitId AS orgUnitId, "
          +" tGrossMaster.tSubSpecimanId AS tSubSpecimanId, "
          +" tGrossMaster.labSampleDtlsId AS labSampleDtlsId, "
          +" tGrossMaster.grossBarcode AS grossBarcode, "
          +" tGrossMaster.grossNo AS grossNo, "
          +" tGrossMaster.grossDesc AS grossDesc, "
          +" tGrossMaster.captureNote AS captureNote, "
          +" tGrossMaster.isDeleted AS isDeleted "
          +"FROM "
          +" TGrossMaster tGrossMaster "
          +"WHERE "
          +" tGrossMaster.orgId = :orgId "
          +" AND tGrossMaster.orgUnitId = :orgUnitId "
          +" AND tGrossMaster.isDeleted = 'N' "
          +" AND tGrossMaster.tSubSpecimanId = :tSubSpecimanId "),
	  
	  @NamedQuery(name = "GET_GROSS_LIST_FOR_RE_SLIDE_CREATION", query = " SELECT "
          +" tGrossMst.tGrossId AS tGrossId, "
          +" tGrossMst.orgId AS orgId, "
          +" tGrossMst.orgUnitId AS orgUnitId, "
          +" tGrossMst.tSubSpecimanId AS tSubSpecimanId, "
          +" tGrossMst.labSampleDtlsId AS labSampleDtlsId, "
          +" tGrossMst.grossBarcode AS grossBarcode, "
          +" tGrossMst.grossNo AS grossNo, "
          +" tGrossMst.grossDesc AS grossDesc, "
          +" tGrossMst.captureNote AS captureNote, "
          +" tGrossMst.isDeleted AS isDeleted "
          +" FROM "
          + " TRestainingRequestDetailsMaster tRestainingRequestDetailsMaster"
          +" INNER JOIN tRestainingRequestDetailsMaster.tGrossMaster tGrossMst"
          +" WHERE "
          +" tGrossMst.orgId = :orgId "
          +" AND tGrossMst.orgUnitId = :orgUnitId "
          +" AND tGrossMst.isDeleted = 'N' "
          +" AND tRestainingRequestDetailsMaster.isDeleted = 'N' "
          +" AND tRestainingRequestDetailsMaster.orgId = :orgId "
          +" AND tRestainingRequestDetailsMaster.orgUnitId = :orgUnitId "
          + "AND tRestainingRequestDetailsMaster.tRestainingDetailId = :tRestainingDetailId"),
	
	 @NamedQuery(name = "GET_GROSS_LIST_FOR_RESTAINING", query = " SELECT "
	     +" tGrossMaster.tGrossId AS tGrossId, "
         +" tGrossMaster.orgId AS orgId, "
         +" tGrossMaster.orgUnitId AS orgUnitId, "
         +" tGrossMaster.tSubSpecimanId AS tSubSpecimanId, "
         +" tGrossMaster.labSampleDtlsId AS labSampleDtlsId, "
         +" tGrossMaster.grossBarcode AS grossBarcode, "
         +" tGrossMaster.grossNo AS grossNo, "
         +" tGrossMaster.grossDesc AS grossDesc, "
         +" tGrossMaster.captureNote AS captureNote, "
         +" tGrossMaster.isDeleted AS isDeleted "
         +"FROM "
         +" TGrossMaster tGrossMaster "
         +"WHERE "
         +" tGrossMaster.orgId = :orgId "
         +" AND tGrossMaster.orgUnitId = :orgUnitId "
         +" AND tGrossMaster.isDeleted = 'N' "
         +" AND tGrossMaster.tSubSpecimanId = :tSubSpecimanId ")
  
	
	
	
})


@Entity
@Table(name = "t_gross_master", schema = "lab")
@SequenceGenerator(name = "t_seq_gross_master", sequenceName = "lab.t_seq_gross_master", allocationSize = 1)
public class TGrossMaster {
	
	@Id
	@Column(name = "t_gross_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_seq_gross_master")
	private int tGrossId;
	
	@Column(name = "org_id")
	private Integer orgId;

	@Column(name = "org_unit_id")
	private Integer orgUnitId;

	@Column(name = "t_sub_speciman_id")
	private Integer tSubSpecimanId;

	@Column(name = "lab_sample_dtls_id")
	private Integer labSampleDtlsId;
	
	@Column(name = "gross_barcode")
	private String grossBarcode;
	
	@Column(name = "gross_no")
	private String grossNo;
	
	@Column(name = "gross_desc")
	private String grossDesc;
	
	@Column(name = "capture_note")
	private String captureNote;
	
	@Column(name = "gross_num")
	private BigInteger grossNum;
	
	
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
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "t_sub_speciman_id", insertable = false, nullable = false, updatable = false)
	private TSubSpecimanMaster tSubSpecimanMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "lab_sample_dtls_id", insertable = false, nullable = false, updatable = false)
	private LabSampleDetailsMaster labSampleDetailsMaster;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tGrossMaster")
	private List<TBlockMaster> listTBlockMaster;

	public int gettGrossId() {
		return tGrossId;
	}

	public void settGrossId(int tGrossId) {
		this.tGrossId = tGrossId;
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

	public String getGrossBarcode() {
		return grossBarcode;
	}

	public void setGrossBarcode(String grossBarcode) {
		this.grossBarcode = grossBarcode;
	}

	public String getGrossNo() {
		return grossNo;
	}

	public void setGrossNo(String grossNo) {
		this.grossNo = grossNo;
	}

	public String getGrossDesc() {
		return grossDesc;
	}

	public void setGrossDesc(String grossDesc) {
		this.grossDesc = grossDesc;
	}

	public String getCaptureNote() {
		return captureNote;
	}

	public void setCaptureNote(String captureNote) {
		this.captureNote = captureNote;
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

	public List<TBlockMaster> getListTBlockMaster() {
		return listTBlockMaster;
	}

	public void setListTBlockMaster(List<TBlockMaster> listTBlockMaster) {
		this.listTBlockMaster = listTBlockMaster;
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

	public BigInteger getGrossNum() {
		return grossNum;
	}

	public void setGrossNum(BigInteger grossNum) {
		this.grossNum = grossNum;
	}
	
	
}
