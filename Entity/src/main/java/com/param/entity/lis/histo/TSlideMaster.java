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
import com.param.entity.lis.global.StainigMaster;
import com.param.entity.lis.transaction.LabSampleDetailsMaster;
import com.param.entity.lis.common.LocalTimeConverter;

@NamedQueries({
@NamedQuery(name = "GET_TOTAL_SLIDES_BY_SPECIMEN", query = " SELECT tSlideMaster.tSlideId AS tSlideId, "
    + " tSlideMaster.slideNo AS slideNo, "
    + " tSlideMaster.isRestained AS isRestained, "
    + " subSpecimenMst.subSpecimanName AS subSpecimanName, "
    + " tGrossMst.grossNo AS grossNo, "
    + " tBlockMst.blockNo AS blockNo, "
    + " stainigMst.desc AS stainName, "
    + " tMicroExaDtls.isComplete AS isComplete, "
    + " tMicroExaDtls.captureNote AS captureNote, "
    + " tMicroExaDtls.sendForStorage AS sendForStorage , "
    + " labSampleDetailsMst.labSampleDtlsId AS labSampleDtlsId "
    +" FROM "
    +" TSlideMaster tSlideMaster "
    +" INNER JOIN tSlideMaster.tSubSpecimanMaster subSpecimenMst "
    +" INNER JOIN subSpecimenMst.tSpecimanMaster tSpecimanMst "
    +" INNER JOIN tSlideMaster.tGrossMaster tGrossMst "
    +" INNER JOIN tSlideMaster.tBlockMaster tBlockMst "
    +" INNER JOIN tSlideMaster.stainigMaster stainigMst "
    +" INNER JOIN tSlideMaster.labSampleDetailsMaster labSampleDetailsMst "
    +" LEFT JOIN  tSlideMaster.listTMicroExaDetails tMicroExaDtls "
    +"WHERE "
    +" tSlideMaster.orgId =:orgId "
    +" AND tSlideMaster.orgUnitId =:orgUnitId "
    +" AND tSlideMaster.isDeleted = 'N' "
    +" AND subSpecimenMst.tSubSpecimanId =:tSubSpecimanId "),

@NamedQuery(name = "GET_SLIDE_LIST_FOR_SLIDE_CREATION", query = " SELECT "
    +" tSlideMaster.tSlideId AS tSlideId, "
    +" tSlideMaster.orgId AS orgId, "
    +" tSlideMaster.orgUnitId AS orgUnitId, "
    +" tSlideMaster.tSubSpecimanId AS tSubSpecimanId, "
    +" tSlideMaster.tGrossId AS tGrossId, "
    +" tSlideMaster.tBlockId AS tBlockId, "
    +" tSlideMaster.labSampleDtlsId AS labSampleDtlsId, "
    +" tSlideMaster.stainingId AS stainingId, "
    +" tSlideMaster.slideBarcode AS slideBarcode, "
    +" tSlideMaster.slideNo AS slideNo, "
    +" tSlideMaster.captureNote AS captureNote, "
    +" tSlideMaster.isDeleted AS isDeleted "
    +"FROM "
    +" TSlideMaster tSlideMaster "
    +"WHERE "
    +" tSlideMaster.orgId = :orgId "
    +" AND tSlideMaster.orgUnitId =:orgUnitId "
    +" AND tSlideMaster.isDeleted = 'N' "
    +" AND tSlideMaster.tBlockId =:tBlockId "),

@NamedQuery(name = "GET_SLIDE_LIST_FOR_RE_SLIDE_CREATION", query = " SELECT "
    +" tRestainingReqSubDetailsMaster.orgId AS orgId, "
    +" tRestainingReqSubDetailsMaster.tRestainingSubDetailId AS tRestainingSubDetailId, "
    +" tRestainingReqSubDetailsMaster.orgUnitId AS orgUnitId, "
    +" tSlideMst.tSubSpecimanId AS tSubSpecimanId, "
    +" tSlideMst.tGrossId AS tGrossId, "
    +" tSlideMst.tBlockId AS tBlockId, "
    +" tSlideMst.slideNum AS slideNum, "
    +" tSlideMst.labSampleDtlsId AS labSampleDtlsId, "
    +" tRestainingReqSubDetailsMaster.stainingId AS stainingId, "
    +" tSlideMst.slideNo AS slideNo, "
    +" tSlideMst.isDeleted AS isDeleted "
    +"FROM "
    +" TRestainingReqSubDetailsMaster tRestainingReqSubDetailsMaster"
    +" LEFT JOIN tRestainingReqSubDetailsMaster.tSlideMaster tSlideMst"
    +" WHERE "
  //  +" tSlideMst.orgId = :orgId "
 //   +" AND tSlideMst.orgUnitId =:orgUnitId "
  //  +" AND tSlideMst.isDeleted = 'N' "
    +"  tRestainingReqSubDetailsMaster.isDeleted ='N' "
    +" AND tRestainingReqSubDetailsMaster.orgId =:orgId "
    +" AND tRestainingReqSubDetailsMaster.orgUnitId =:orgUnitId "
    +" AND tRestainingReqSubDetailsMaster.tRestainingDetailId =:tRestainingDetailId "
    +" AND tRestainingReqSubDetailsMaster.tRestainingSubDetailId =:tRestainingSubDetailId "
    +" AND tRestainingReqSubDetailsMaster.isNew =:isNew "),

@NamedQuery(name = "GET_MAX_SLIDE_NUMBER", query = " SELECT MAX(tSlideMaster.slideNum)"
    +"  FROM TSlideMaster tSlideMaster "
    +" WHERE tSlideMaster.orgId =:orgId "
    +" AND tSlideMaster.orgUnitId =:orgUnitId "
    +" AND tSlideMaster.isDeleted = 'N' "
    +" AND tSlideMaster.tBlockId =:tBlockId "  ),


@NamedQuery(name = "GET_SLIDE_LIST_FOR_RESTAINING", query = " SELECT "
    +" tSlideMaster.tSlideId AS id, "
    +" tSlideMaster.slideNo AS name "
    +"FROM "
    +" TSlideMaster tSlideMaster "
    +"WHERE "
    +" tSlideMaster.orgId = :orgId "
    +" AND tSlideMaster.orgUnitId =:orgUnitId "
    +" AND tSlideMaster.isDeleted = 'N' "
    +" AND tSlideMaster.tBlockId =:tBlockId ")
})

@Entity
@Table(name = "t_slide_master", schema = "lab")
@SequenceGenerator(name = "t_seq_slide_master", sequenceName = "lab.t_seq_slide_master", allocationSize = 1)
public class TSlideMaster 
{
	@Id
	@Column(name = "t_slide_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_seq_slide_master")
	private int tSlideId;
	
	@Column(name = "org_id")
	private Integer orgId;

	@Column(name = "org_unit_id")
	private Integer orgUnitId;

	@Column(name = "t_sub_speciman_id")
	private Integer tSubSpecimanId;
	
	@Column(name = "t_gross_id")
	private Integer tGrossId;
	
	@Column(name = "t_block_id")
	private Integer tBlockId;

	@Column(name = "lab_sample_dtls_id")
	private Integer labSampleDtlsId;
	
	@Column(name = "staining_id")
	private Integer stainingId;
	
	@Column(name = "slide_barcode")
	private String slideBarcode;
	
	@Column(name = "slide_no")
	private String slideNo;
	
	@Column(name = "slide_num")
	private BigInteger slideNum;
	
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
	
	@Column(name = "is_restained")
    private Character isRestained;
	
	@Column(name = "restain_against_tslideid")
	private Integer restainAgainstTslideid;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "t_sub_speciman_id", insertable = false, nullable = false, updatable = false)
	private TSubSpecimanMaster tSubSpecimanMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "t_gross_id", insertable = false, nullable = false, updatable = false)
	private TGrossMaster tGrossMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "t_block_id", insertable = false, nullable = false, updatable = false)
	private TBlockMaster tBlockMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "lab_sample_dtls_id", insertable = false, nullable = false, updatable = false)
	private LabSampleDetailsMaster labSampleDetailsMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "staining_id", insertable = false, nullable = false, updatable = false)
	private StainigMaster stainigMaster;
	
	/*fetch = FetchType.LAZY*/
	@OneToMany(cascade = CascadeType.ALL , mappedBy = "tSlideMaster")
	private List<TMicroExaDetails> listTMicroExaDetails;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "tSlideMaster")
	private List<TRestainingReqSubDetailsMaster> listTRestainingReqSubDetailsMaster;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "tSlideMaster")
	private List<TSlideStorageMaster> listTSlideStorageMaster;
	
	public int gettSlideId() {
		return tSlideId;
	}

	public void settSlideId(int tSlideId) {
		this.tSlideId = tSlideId;
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

	public Integer gettBlockId() {
		return tBlockId;
	}

	public void settBlockId(Integer tBlockId) {
		this.tBlockId = tBlockId;
	}

	public Integer getLabSampleDtlsId() {
		return labSampleDtlsId;
	}

	public void setLabSampleDtlsId(Integer labSampleDtlsId) {
		this.labSampleDtlsId = labSampleDtlsId;
	}

	public Integer getStainingId() {
		return stainingId;
	}

	public void setStainingId(Integer stainingId) {
		this.stainingId = stainingId;
	}

	public String getSlideBarcode() {
		return slideBarcode;
	}

	public void setSlideBarcode(String slideBarcode) {
		this.slideBarcode = slideBarcode;
	}

	public String getSlideNo() {
		return slideNo;
	}

	public void setSlideNo(String slideNo) {
		this.slideNo = slideNo;
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

	public TGrossMaster gettGrossMaster() {
		return tGrossMaster;
	}

	public void settGrossMaster(TGrossMaster tGrossMaster) {
		this.tGrossMaster = tGrossMaster;
	}

	public TBlockMaster gettBlockMaster() {
		return tBlockMaster;
	}

	public void settBlockMaster(TBlockMaster tBlockMaster) {
		this.tBlockMaster = tBlockMaster;
	}

	public LabSampleDetailsMaster getLabSampleDetailsMaster() {
		return labSampleDetailsMaster;
	}

	public void setLabSampleDetailsMaster(LabSampleDetailsMaster labSampleDetailsMaster) {
		this.labSampleDetailsMaster = labSampleDetailsMaster;
	}

	public StainigMaster getStainigMaster() {
		return stainigMaster;
	}

	public void setStainigMaster(StainigMaster stainigMaster) {
		this.stainigMaster = stainigMaster;
	}

	public List<TMicroExaDetails> getListTMicroExaDetails() {
		return listTMicroExaDetails;
	}

	public void setListTMicroExaDetails(List<TMicroExaDetails> listTMicroExaDetails) {
		this.listTMicroExaDetails = listTMicroExaDetails;
	}

	public List<TRestainingReqSubDetailsMaster> getListTRestainingReqSubDetailsMaster() {
		return listTRestainingReqSubDetailsMaster;
	}

	public void setListTRestainingReqSubDetailsMaster(
			List<TRestainingReqSubDetailsMaster> listTRestainingReqSubDetailsMaster) {
		this.listTRestainingReqSubDetailsMaster = listTRestainingReqSubDetailsMaster;
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

  public Character getIsRestained() {
    return isRestained;
  }

  public void setIsRestained(Character isRestained) {
    this.isRestained = isRestained;
  }

  public Integer getRestainAgainstTslideid() {
    return restainAgainstTslideid;
  }

  public void setRestainAgainstTslideid(Integer restainAgainstTslideid) {
    this.restainAgainstTslideid = restainAgainstTslideid;
  }

public BigInteger getSlideNum() {
	return slideNum;
}

public void setSlideNum(BigInteger slideNum) {
	this.slideNum = slideNum;
}

public List<TSlideStorageMaster> getListTSlideStorageMaster() {
  return listTSlideStorageMaster;
}

public void setListTSlideStorageMaster(List<TSlideStorageMaster> listTSlideStorageMaster) {
  this.listTSlideStorageMaster = listTSlideStorageMaster;
}
	
	
	
}
