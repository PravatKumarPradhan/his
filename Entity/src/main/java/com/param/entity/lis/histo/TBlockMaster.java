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
  
  @NamedQuery(name = "GET_BLOCK_LIST_FOR_SLIDE_CREATION", query = " SELECT "
      +" tBlockMaster.tBlockId AS tBlockId, "
      +" tBlockMaster.orgId AS orgId, "
      +" tBlockMaster.orgUnitId AS orgUnitId, "
      +" tBlockMaster.tGrossId AS tGrossId, "
      +" tBlockMaster.labSampleDtlsId AS labSampleDtlsId, "
      +" tBlockMaster.blockBarcode AS blockBarcode, "
      +" tBlockMaster.blockNo AS blockNo, "
      +" tBlockMaster.captureNote AS captureNote, "
      +" tBlockMaster.isDeleted AS isDeleted "
      +"FROM "
      +" TBlockMaster tBlockMaster "
      +"WHERE "
      +" tBlockMaster.orgId =:orgId "
      +" AND tBlockMaster.orgUnitId =:orgUnitId "
      +" AND tBlockMaster.isDeleted ='N' "
      +" AND tBlockMaster.tGrossId =:tGrossId "),
  
  @NamedQuery(name = "GET_BLOCK_LIST_FOR_RE_SLIDE_CREATION", query = " SELECT "
      +" tBlockMst.tBlockId AS tBlockId, "
      +" tBlockMst.orgId AS orgId, "
      +" tBlockMst.orgUnitId AS orgUnitId, "
      +" tBlockMst.tGrossId AS tGrossId, "
      +" tBlockMst.labSampleDtlsId AS labSampleDtlsId, "
      +" tBlockMst.blockBarcode AS blockBarcode, "
      +" tBlockMst.blockNo AS blockNo, "
      +" tBlockMst.captureNote AS captureNote, "
      +" tBlockMst.isDeleted AS isDeleted "
      +" FROM "
      +" TRestainingRequestDetailsMaster tRestainingRequestDetailsMaster"
      +" INNER JOIN tRestainingRequestDetailsMaster.tBlockMaster tBlockMst"
      +" WHERE "
      +" tBlockMst.orgId =:orgId "
      +" AND tBlockMst.orgUnitId =:orgUnitId "
      +" AND tBlockMst.isDeleted ='N' "
      +" AND tRestainingRequestDetailsMaster.orgId =:orgId"
      +" AND tRestainingRequestDetailsMaster.orgUnitId =:orgUnitId"
      +" AND tRestainingRequestDetailsMaster.tRestainingDetailId =:tRestainingDetailId"),

  @NamedQuery(name = "GET_BLOCK_LIST_FOR_RESTAINING", query = " SELECT "
      +" tBlockMaster.tBlockId AS tBlockId, "
      +" tBlockMaster.tGrossId AS tGrossId, "
      +" tBlockMaster.orgId AS orgId, "
      +" tBlockMaster.orgUnitId AS orgUnitId, "
      +" tBlockMaster.labSampleDtlsId AS labSampleDtlsId, "
      +" tBlockMaster.blockNo AS blockNo "
      +"FROM "
      +" TBlockMaster tBlockMaster "
      +"WHERE "
      +" tBlockMaster.orgId =:orgId "
      +" AND tBlockMaster.orgUnitId =:orgUnitId "
      +" AND tBlockMaster.isDeleted ='N' "
      +" AND tBlockMaster.tGrossId =:tGrossId ")
})


@Entity
@Table(name = "t_block_master", schema = "lab")
@SequenceGenerator(name = "t_seq_block_master", sequenceName = "lab.t_seq_block_master", allocationSize = 1)
public class TBlockMaster 
{
	@Id
	@Column(name = "t_block_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_seq_block_master")
	private int tBlockId;
	
	@Column(name = "org_id")
	private Integer orgId;

	@Column(name = "org_unit_id")
	private Integer orgUnitId;

	@Column(name = "t_gross_id")
	private Integer tGrossId;

	@Column(name = "lab_sample_dtls_id")
	private Integer labSampleDtlsId;
	
	@Column(name = "block_barcode")
	private String blockBarcode;
	
	@Column(name = "block_no")
	private String blockNo;
	
	@Column(name = "block_num")
	private BigInteger blockNum;
	
	@Column(name = "capture_note")
	private String captureNote;
	
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
	@JoinColumn(name = "lab_sample_dtls_id", insertable = false, nullable = false, updatable = false)
	private LabSampleDetailsMaster labSampleDetailsMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "t_gross_id", insertable = false, nullable = false, updatable = false)
	private TGrossMaster tGrossMaster;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tBlockMaster")
	private List<TSlideMaster> listTSlideMaster;

	public int gettBlockId() {
		return tBlockId;
	}

	public void settBlockId(int tBlockId) {
		this.tBlockId = tBlockId;
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

	public Integer gettGrossId() {
		return tGrossId;
	}

	public void settGrossId(Integer tGrossId) {
		this.tGrossId = tGrossId;
	}

	public Integer getLabSampleDtlsId() {
		return labSampleDtlsId;
	}

	public void setLabSampleDtlsId(Integer labSampleDtlsId) {
		this.labSampleDtlsId = labSampleDtlsId;
	}

	public String getBlockBarcode() {
		return blockBarcode;
	}

	public void setBlockBarcode(String blockBarcode) {
		this.blockBarcode = blockBarcode;
	}

	public String getBlockNo() {
		return blockNo;
	}

	public void setBlockNo(String blockNo) {
		this.blockNo = blockNo;
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

	public TGrossMaster gettGrossMaster() {
		return tGrossMaster;
	}

	public void settGrossMaster(TGrossMaster tGrossMaster) {
		this.tGrossMaster = tGrossMaster;
	}

	public List<TSlideMaster> getListTSlideMaster() {
		return listTSlideMaster;
	}

	public void setListTSlideMaster(List<TSlideMaster> listTSlideMaster) {
		this.listTSlideMaster = listTSlideMaster;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

  public BigInteger getBlockNum() {
    return blockNum;
  }

  public void setBlockNum(BigInteger blockNum) {
    this.blockNum = blockNum;
  }

	
}
