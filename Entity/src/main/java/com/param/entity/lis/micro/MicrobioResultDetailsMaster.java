package com.param.entity.lis.micro;



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
import com.param.entity.lis.global.IncubationPeriodMaster;
import com.param.entity.lis.global.MediaMaster;
import com.param.entity.lis.common.LocalTimeConverter;

@NamedQueries({

	@NamedQuery(name = "GET_INCUBATION_DETAILS", query = "SELECT "
			 +"	microbioResultDetailsMaster "
			 +" FROM "
			 +"	MicrobioResultDetailsMaster microbioResultDetailsMaster "
			 +" WHERE "
			 +"	microbioResultDetailsMaster.microbioResultEntryId =:microbioResultEntryId "
			 +"	AND microbioResultDetailsMaster.isDeleted = 'N' "
			 +"	AND microbioResultDetailsMaster.orgId = :orgId "
			 +"	AND microbioResultDetailsMaster.orgUnitId = :orgUnitId "),
	
	
	@NamedQuery(name="GET_MICRO_RES_DETAILS_BY_RESULT_ID", query=" SELECT "
			+ " microbioResultDetailsMaster.microbioResultDetailsId AS microbioResultDetailsId, "
			+ " microbioResultDetailsMaster.orgId AS orgId, "
			+ " microbioResultDetailsMaster.orgUnitId AS orgUnitId, "
			+ " microbioResultDetailsMaster.incuRemark AS incuRemark, "
			+ " microbioResultDetailsMaster.microbioResultEntryId AS microbioResultEntryId, "
			+ " microbioResultDetailsMaster.mediaId AS mediaId, "
			+ " microbioResultDetailsMaster.incubationPeriodId AS incubationPeriodId, "
			+ " microbioResultDetailsMaster.isGrowthFound AS isGrowthFound, "
			+ " microbioResultDetailsMaster.isDeleted AS isDeleted "
			+"	FROM MicrobioResultDetailsMaster microbioResultDetailsMaster "
			+"  WHERE microbioResultDetailsMaster.microbioResultEntryId = :microbioResultEntryId"
			+"	AND microbioResultDetailsMaster.orgId =:orgId "
			+ " AND microbioResultDetailsMaster.orgUnitId =:orgUnitId "
			+ " AND microbioResultDetailsMaster.isDeleted = 'N' ")
})

@Entity
@Table(name = "t_microbio_result_details", schema = "lab")
@SequenceGenerator(name = "t_seq_microbio_result_details", sequenceName = "lab.t_seq_microbio_result_details", allocationSize = 1)
public class MicrobioResultDetailsMaster {
	
	@Id
	@Column(name = "microbio_result_details_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_seq_microbio_result_details")
	private int microbioResultDetailsId;
	
	@Column(name = "org_id")
	private Integer orgId;

	@Column(name = "org_unit_id")
	private Integer orgUnitId;

	@Column(name = "remark")
	private String incuRemark;

	@Column(name = "microbio_result_entry_id")
	private Integer microbioResultEntryId;
	
	@Column(name = "media_id")
	private Integer mediaId;
	
	@Column(name = "incubation_period_id")
	private Integer incubationPeriodId;
	
	@Column(name = "is_growth_found")
	private Character isGrowthFound;
	
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
	@JoinColumn(name = "media_id", insertable = false, nullable = false, updatable = false)
	private MediaMaster mediaMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "incubation_period_id", insertable = false, nullable = false, updatable = false)
	private IncubationPeriodMaster incubationPeriodMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "microbio_result_entry_id", insertable = false, nullable = false, updatable = false)
	private MicrobioResultEntryMaster microbioResultEntryMaster;

	public int getMicrobioResultDetailsId() {
		return microbioResultDetailsId;
	}

	public void setMicrobioResultDetailsId(int microbioResultDetailsId) {
		this.microbioResultDetailsId = microbioResultDetailsId;
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
	

	public String getIncuRemark() {
		return incuRemark;
	}

	public void setIncuRemark(String incuRemark) {
		this.incuRemark = incuRemark;
	}

	public Integer getMicrobioResultEntryId() {
		return microbioResultEntryId;
	}

	public void setMicrobioResultEntryId(Integer microbioResultEntryId) {
		this.microbioResultEntryId = microbioResultEntryId;
	}

	public Integer getMediaId() {
		return mediaId;
	}

	public void setMediaId(Integer mediaId) {
		this.mediaId = mediaId;
	}

	public Integer getIncubationPeriodId() {
		return incubationPeriodId;
	}

	public void setIncubationPeriodId(Integer incubationPeriodId) {
		this.incubationPeriodId = incubationPeriodId;
	}

	public Character getIsGrowthFound() {
		return isGrowthFound;
	}

	public void setIsGrowthFound(Character isGrowthFound) {
		this.isGrowthFound = isGrowthFound;
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

	public MediaMaster getMediaMaster() {
		return mediaMaster;
	}

	public void setMediaMaster(MediaMaster mediaMaster) {
		this.mediaMaster = mediaMaster;
	}

	public IncubationPeriodMaster getIncubationPeriodMaster() {
		return incubationPeriodMaster;
	}

	public void setIncubationPeriodMaster(IncubationPeriodMaster incubationPeriodMaster) {
		this.incubationPeriodMaster = incubationPeriodMaster;
	}

	public MicrobioResultEntryMaster getMicrobioResultEntryMaster() {
		return microbioResultEntryMaster;
	}

	public void setMicrobioResultEntryMaster(MicrobioResultEntryMaster microbioResultEntryMaster) {
		this.microbioResultEntryMaster = microbioResultEntryMaster;
	}
	
	
	
	

}
