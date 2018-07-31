package com.param.entity.lis.micro;

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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.param.entity.lis.global.AntibioticMaster;
import com.param.entity.lis.global.MicroLabPriorityMaster;
import com.param.entity.lis.global.OrganismGroupMaster;
import com.param.entity.lis.global.OrganismMaster;
import com.param.entity.lis.global.SensitivityMaster;
import com.param.entity.lis.common.LocalTimeConverter;


@Entity
@Table(name = "t_sesitivity_test_result_details", schema = "lab")
@SequenceGenerator(name = "t_seq_sesitivity_test_result_details", sequenceName = "lab.t_seq_sesitivity_test_result_details", allocationSize = 1)
public class SensitivityTestResultDetailsMaster {
	
	@Id
	@Column(name = "sesitivity_result_details_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_seq_sesitivity_test_result_details")
	private int sesitivityResultDetailsId;
	
	@Column(name = "sensitivity_result_id")
	private Integer sensitivityResultId;
	
	@Column(name = "org_id")
	private Integer orgId;
	
	@Column(name = "organism_id")
	private Integer organismId;
	
	@Column(name = "organism_group_id")
	private Integer organismGroupId;
	
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
	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organism_id", insertable = false, nullable = false, updatable = false)
	private OrganismMaster organismMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organism_group_id", insertable = false, nullable = false, updatable = false)
	private OrganismGroupMaster organismGroupMaster;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sensitivity_result_id", insertable = false, nullable = false, updatable = false)
	private SensitivityTestResultMaster sensitivityTestResultMaster;
	
	@OneToMany( fetch = FetchType.LAZY, mappedBy = "sensitivityTestResultDetailsMaster")
	private List<SensitivityTestResultSubDetailsMaster> listSensitivityTestResultSubDetailsMaster;

	

	public List<SensitivityTestResultSubDetailsMaster> getListSensitivityTestResultSubDetailsMaster() {
		return listSensitivityTestResultSubDetailsMaster;
	}

	public void setListSensitivityTestResultSubDetailsMaster(
			List<SensitivityTestResultSubDetailsMaster> listSensitivityTestResultSubDetailsMaster) {
		this.listSensitivityTestResultSubDetailsMaster = listSensitivityTestResultSubDetailsMaster;
	}

	public int getSesitivityResultDetailsId() {
		return sesitivityResultDetailsId;
	}

	public void setSesitivityResultDetailsId(int sesitivityResultDetailsId) {
		this.sesitivityResultDetailsId = sesitivityResultDetailsId;
	}

	public Integer getSensitivityResultId() {
		return sensitivityResultId;
	}

	public void setSensitivityResultId(Integer sensitivityResultId) {
		this.sensitivityResultId = sensitivityResultId;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public Integer getOrganismId() {
		return organismId;
	}

	public void setOrganismId(Integer organismId) {
		this.organismId = organismId;
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

	public OrganismMaster getOrganismMaster() {
		return organismMaster;
	}

	public void setOrganismMaster(OrganismMaster organismMaster) {
		this.organismMaster = organismMaster;
	}

	public SensitivityTestResultMaster getSensitivityTestResultMaster() {
		return sensitivityTestResultMaster;
	}

	public void setSensitivityTestResultMaster(
			SensitivityTestResultMaster sensitivityTestResultMaster) {
		this.sensitivityTestResultMaster = sensitivityTestResultMaster;
	}

	public Integer getOrganismGroupId() {
		return organismGroupId;
	}

	public void setOrganismGroupId(Integer organismGroupId) {
		this.organismGroupId = organismGroupId;
	}

	public OrganismGroupMaster getOrganismGroupMaster() {
		return organismGroupMaster;
	}

	public void setOrganismGroupMaster(OrganismGroupMaster organismGroupMaster) {
		this.organismGroupMaster = organismGroupMaster;
	}

	
	
	
 
	
}
