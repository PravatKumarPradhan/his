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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.param.entity.lis.global.OrganismMaster;
import com.param.entity.lis.common.LocalTimeConverter;



@Entity
@Table(name = "t_microscopic_exa_sub_details", schema = "lab")
@SequenceGenerator(name = "t_seq_microscopic_exa_sub_details", sequenceName = "lab.t_seq_microscopic_exa_sub_details", allocationSize = 1)
public class MicroscopicExaminationSubDetailsMaster {
	
	@Id
	@Column(name = "microexa_sub_details_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_seq_microscopic_exa_sub_details")
	private int microexaSubDetailsId;
	
	@Column(name = "org_id")
	private Integer orgId;

	@Column(name = "org_unit_id")
	private Integer orgUnitId;

	@Column(name = "remark")
	private String microRemark;

	@Column(name = "examination_details_id")
	private Integer examinationDetailsId;
	
	@Column(name = "organism_id")
	private Integer organismId;
	
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
	@JoinColumn(name = "examination_details_id", insertable = false, nullable = false, updatable = false)
	private MicroscopicExaminationDetailsMaster microscopicExaminationDetailsMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organism_id", insertable = false, nullable = false, updatable = false)
	private OrganismMaster organismMaster;

	public int getMicroexaSubDetailsId() {
		return microexaSubDetailsId;
	}

	public void setMicroexaSubDetailsId(int microexaSubDetailsId) {
		this.microexaSubDetailsId = microexaSubDetailsId;
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


	public String getMicroRemark() {
		return microRemark;
	}

	public void setMicroRemark(String microRemark) {
		this.microRemark = microRemark;
	}

	public Integer getExaminationDetailsId() {
		return examinationDetailsId;
	}

	public void setExaminationDetailsId(Integer examinationDetailsId) {
		this.examinationDetailsId = examinationDetailsId;
	}

	public Integer getOrganismId() {
		return organismId;
	}

	public void setOrganismId(Integer organismId) {
		this.organismId = organismId;
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

	public MicroscopicExaminationDetailsMaster getMicroscopicExaminationDetailsMaster() {
		return microscopicExaminationDetailsMaster;
	}

	public void setMicroscopicExaminationDetailsMaster(
			MicroscopicExaminationDetailsMaster microscopicExaminationDetailsMaster) {
		this.microscopicExaminationDetailsMaster = microscopicExaminationDetailsMaster;
	}

	public OrganismMaster getOrganismMaster() {
		return organismMaster;
	}

	public void setOrganismMaster(OrganismMaster organismMaster) {
		this.organismMaster = organismMaster;
	}
	
	

	
	
	

}
