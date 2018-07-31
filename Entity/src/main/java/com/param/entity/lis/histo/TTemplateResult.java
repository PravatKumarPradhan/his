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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.param.entity.lis.global.StainigMaster;
import com.param.entity.lis.transaction.LabSampleDetailsMaster;
import com.param.entity.lis.unit.LabTemplateMaster;
import com.param.entity.lis.common.LocalTimeConverter;

@Entity
@Table(name = "t_template_result", schema = "lab")
@SequenceGenerator(name = "t_seq_template_result", sequenceName = "lab.t_seq_template_result", allocationSize = 1)
public class TTemplateResult {
	
	@Id
	@Column(name = "template_res_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_seq_template_result")
	private int templateResId;
	
	@Column(name = "org_id")
	private Integer orgId;

	@Column(name = "org_unit_id")
	private Integer orgUnitId;
	
	@Column(name = "t_sub_speciman_id")
	private Integer tSubSpecimanId;
	
	@Column(name = "lab_template_id")
	private Integer labTemplateId;
	
	@Column(name = "report_no")
	private String reportNo;
	
	@Column(name = "staining_id")
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
	
	@Column(name = "template_result")
	private String templateResult;


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "lab_sample_dtls_id", insertable = false, nullable = false, updatable = false)
	private LabSampleDetailsMaster labSampleDetailsMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "staining_id", insertable = false, nullable = false, updatable = false)
	private StainigMaster stainigMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "t_sub_speciman_id", insertable = false, nullable = false, updatable = false)
	private TSubSpecimanMaster tSubSpecimanMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "lab_template_id", insertable = false, nullable = false, updatable = false)
	private LabTemplateMaster labTemplateMaster;

	public int getTemplateResId() {
		return templateResId;
	}

	public void setTemplateResId(int templateResId) {
		this.templateResId = templateResId;
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


	public Integer getLabTemplateId() {
		return labTemplateId;
	}

	public void setLabTemplateId(Integer labTemplateId) {
		this.labTemplateId = labTemplateId;
	}

	public String getReportNo() {
		return reportNo;
	}

	public void setReportNo(String reportNo) {
		this.reportNo = reportNo;
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

	public StainigMaster getStainigMaster() {
		return stainigMaster;
	}

	public void setStainigMaster(StainigMaster stainigMaster) {
		this.stainigMaster = stainigMaster;
	}

	public LabTemplateMaster getLabTemplateMaster() {
		return labTemplateMaster;
	}

	public void setLabTemplateMaster(LabTemplateMaster labTemplateMaster) {
		this.labTemplateMaster = labTemplateMaster;
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

	public String getTemplateResult() {
		return templateResult;
	}

	public void setTemplateResult(String templateResult) {
		this.templateResult = templateResult;
	}
	
	
}
