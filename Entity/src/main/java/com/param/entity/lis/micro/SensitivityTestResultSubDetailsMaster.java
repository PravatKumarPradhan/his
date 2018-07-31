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
import com.param.entity.lis.global.AntibioticMaster;
import com.param.entity.lis.global.MicroLabPriorityMaster;
import com.param.entity.lis.global.SensitivityMaster;
import com.param.entity.lis.common.LocalTimeConverter;


@Entity
@Table(name = "t_sesitivity_test_result_sub_details", schema = "lab")
@SequenceGenerator(name = "t_seq_sesitivity_test_result_sub_details", sequenceName = "lab.t_seq_sesitivity_test_result_sub_details", allocationSize = 1)
public class SensitivityTestResultSubDetailsMaster {
	
	@Id
	@Column(name = "sesitivity_result_sub_details_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_seq_sesitivity_test_result_sub_details")
	private int sesitivityResultSubDetailsId;
	
	@Column(name = "org_id")
	private Integer orgId;

	@Column(name = "org_unit_id")
	private Integer orgUnitId;
	
	@Column(name = "antibiotic_id")
	private Integer antibioticId;

	@Column(name = "sensitivity_id")
	private Integer sensitivityId;
	
	@Column(name = "micro_lab_priority_id")
	private Integer microLabPriorityId;
	
	@Column(name = "mic")
	private Double mic;
	
	@Column(name = "remark")
	private String remark;
	
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
	
	@Column(name = "sesitivity_result_details_id")
	private Integer sesitivityResultDetailsId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "antibiotic_id", insertable = false, nullable = false, updatable = false)
	private AntibioticMaster antibioticMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sesitivity_result_details_id", insertable = false, nullable = false, updatable = false)
	private SensitivityTestResultDetailsMaster sensitivityTestResultDetailsMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "micro_lab_priority_id", insertable = false, nullable = false, updatable = false)
	private MicroLabPriorityMaster microLabPriorityMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sensitivity_id", insertable = false, nullable = false, updatable = false)
	private SensitivityMaster sensitivityMaster;


	public int getSesitivityResultSubDetailsId() {
		return sesitivityResultSubDetailsId;
	}

	public void setSesitivityResultSubDetailsId(int sesitivityResultSubDetailsId) {
		this.sesitivityResultSubDetailsId = sesitivityResultSubDetailsId;
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

	public Integer getAntibioticId() {
		return antibioticId;
	}

	public void setAntibioticId(Integer antibioticId) {
		this.antibioticId = antibioticId;
	}

	public Integer getSensitivityId() {
		return sensitivityId;
	}

	public void setSensitivityId(Integer sensitivityId) {
		this.sensitivityId = sensitivityId;
	}

	public Integer getMicroLabPriorityId() {
		return microLabPriorityId;
	}

	public void setMicroLabPriorityId(Integer microLabPriorityId) {
		this.microLabPriorityId = microLabPriorityId;
	}

	public Double getMic() {
		return mic;
	}

	public void setMic(Double mic) {
		this.mic = mic;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public Integer getSesitivityResultDetailsId() {
		return sesitivityResultDetailsId;
	}

	public void setSesitivityResultDetailsId(Integer sesitivityResultDetailsId) {
		this.sesitivityResultDetailsId = sesitivityResultDetailsId;
	}

	public AntibioticMaster getAntibioticMaster() {
		return antibioticMaster;
	}

	public void setAntibioticMaster(AntibioticMaster antibioticMaster) {
		this.antibioticMaster = antibioticMaster;
	}

	public SensitivityTestResultDetailsMaster getSensitivityTestResultDetailsMaster() {
		return sensitivityTestResultDetailsMaster;
	}

	public void setSensitivityTestResultDetailsMaster(
			SensitivityTestResultDetailsMaster sensitivityTestResultDetailsMaster) {
		this.sensitivityTestResultDetailsMaster = sensitivityTestResultDetailsMaster;
	}

	public MicroLabPriorityMaster getMicroLabPriorityMaster() {
		return microLabPriorityMaster;
	}

	public void setMicroLabPriorityMaster(
			MicroLabPriorityMaster microLabPriorityMaster) {
		this.microLabPriorityMaster = microLabPriorityMaster;
	}

	public SensitivityMaster getSensitivityMaster() {
		return sensitivityMaster;
	}

	public void setSensitivityMaster(SensitivityMaster sensitivityMaster) {
		this.sensitivityMaster = sensitivityMaster;
	}

	
	
	
 
	
}
