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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.param.entity.lis.global.AntibioticMaster;
import com.param.entity.lis.global.OrganismGroupMaster;
import com.param.entity.lis.common.LocalTimeConverter;

@NamedQueries({
	
	@NamedQuery(name = "GET_SENSITIVITY_EXAMINATION_MASTER_BY_RESULT_ID", query = " SELECT " 
	        + " sensitivityTestResultMaster.sensitivityResultId AS sensitivityResultId, "
	        + " sensitivityTestResultMaster.orgId  AS orgId, "
	        + " sensitivityTestResultMaster.orgUnitId  AS orgUnitId, "
	        + " sensitivityTestResultMaster.checkTest  AS checkTest, "
	        + " sensitivityTestResultMaster.createdBy  AS createdBy, "
	        + " sensitivityTestResultMaster.createdDate  AS createdDate, "
	        + " sensitivityTestResultMaster.updatedBy  AS updatedBy, "
	        + " sensitivityTestResultMaster.updatedDate  AS updatedDate, "
	        + " sensitivityTestResultMaster.isDeleted  AS isDeleted  "
			+ "	FROM SensitivityTestResultMaster sensitivityTestResultMaster "
			+ " WHERE sensitivityTestResultMaster.labSampleDtlsId = :labSampleDtlsId"
			+ "	AND sensitivityTestResultMaster.orgId =:orgId"
			+ " AND sensitivityTestResultMaster.orgUnitId =:orgUnitId"
			+ " AND sensitivityTestResultMaster.isDeleted = 'N' "),
	
	@NamedQuery(name = "GET_SENSITIVITY_EXAMINATION_DETAILS", query = " SELECT " 
	        + " SensitivityTestResultDetailsMaster.sensitivityResultId AS sensitivityResultId, "
	        + " SensitivityTestResultDetailsMaster.orgId AS orgId, "
	        + " SensitivityTestResultDetailsMaster.sesitivityResultDetailsId AS sesitivityResultDetailsId, "
	        + " SensitivityTestResultDetailsMaster.orgUnitId AS orgUnitId, "
	        + " SensitivityTestResultDetailsMaster.organismId AS organismId, "
	        + " SensitivityTestResultDetailsMaster.organismMaster.desc AS organismName, "
	        + " SensitivityTestResultDetailsMaster.organismGroupMaster.desc AS organismGroupName, "
	        + " SensitivityTestResultDetailsMaster.organismGroupId AS organismGroupId, "
	        + " SensitivityTestResultDetailsMaster.createdBy AS createdBy, "
	        + " SensitivityTestResultDetailsMaster.createdDate AS createdDate , "
	        + " SensitivityTestResultDetailsMaster.updatedBy AS updatedBy, "
	        + " SensitivityTestResultDetailsMaster.updatedDate AS updatedDate, "
	        + " SensitivityTestResultDetailsMaster.isDeleted AS isDeleted"
			+ "	FROM SensitivityTestResultDetailsMaster SensitivityTestResultDetailsMaster "
			+ " WHERE SensitivityTestResultDetailsMaster.sensitivityResultId = :sensitivityResultId"
			+ "	AND SensitivityTestResultDetailsMaster.orgId =:orgId "
			+ " AND SensitivityTestResultDetailsMaster.orgUnitId =:orgUnitId "
			+ " AND SensitivityTestResultDetailsMaster.isDeleted = 'N' "),
			
			@NamedQuery(name = "GET_SENSITIVITY_EXAMINATION_SUB_DETAILS", query = " SELECT " 
			        + " sensitivityTestResultSubDetailsMaster.sesitivityResultSubDetailsId AS sesitivityResultSubDetailsId , "
			        + " sensitivityTestResultSubDetailsMaster.orgId AS orgId, "
			        + " sensitivityTestResultSubDetailsMaster.orgUnitId AS orgUnitId, "
			        + " sensitivityTestResultSubDetailsMaster.remark AS remark, "
			        + " sensitivityTestResultSubDetailsMaster.antibioticId AS antibioticId, "
			        + " sensitivityTestResultSubDetailsMaster.antibioticMaster.desc AS antibioticName, "
			        + " sensitivityTestResultSubDetailsMaster.sensitivityId AS sensitivityId, "
			        + " sensitivityTestResultSubDetailsMaster.microLabPriorityId AS microLabPriorityId, "
			        + " sensitivityTestResultSubDetailsMaster.mic AS mic, "
                    + " sensitivityTestResultSubDetailsMaster.sesitivityResultDetailsId AS sesitivityResultDetailsId, "
			        + " sensitivityTestResultSubDetailsMaster.createdBy AS createdBy, "
			        + " sensitivityTestResultSubDetailsMaster.createdDate AS createdDate, "
			        + " sensitivityTestResultSubDetailsMaster.updatedBy AS updatedBy, "
			        + " sensitivityTestResultSubDetailsMaster.updatedDate AS updatedDate, "
			        + " sensitivityTestResultSubDetailsMaster.isDeleted AS isDeleted "
					+ "	FROM SensitivityTestResultSubDetailsMaster sensitivityTestResultSubDetailsMaster "
					+ " WHERE sensitivityTestResultSubDetailsMaster.sesitivityResultDetailsId = :sesitivityResultDetailsId"
					+ "	AND sensitivityTestResultSubDetailsMaster.orgId =:orgId "
					+ " AND sensitivityTestResultSubDetailsMaster.orgUnitId =:orgUnitId "
					+ " AND sensitivityTestResultSubDetailsMaster.isDeleted = 'N' ") 
})

@Entity
@Table(name = "t_sesitivity_test_result", schema = "lab")
@SequenceGenerator(name = "t_seq_sesitivity_test_result", sequenceName = "lab.t_seq_sesitivity_test_result", allocationSize = 1)
public class SensitivityTestResultMaster {
	
	@Id
	@Column(name = "sensitivity_result_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_seq_sesitivity_test_result")
	private int sensitivityResultId;
	
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
	
	@Column(name = "lab_sample_dtls_id")
	private Integer labSampleDtlsId;
	
	@Column(name = "check_test")
	private Character checkTest;
	
	
	@OneToMany( fetch = FetchType.LAZY, mappedBy = "sensitivityTestResultMaster")
	private List<SensitivityTestResultDetailsMaster> listSensitivityTestResultDetailsMaster;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "microbio_result_entry_id", insertable = false, nullable = false, updatable = false)
	private MicrobioResultEntryMaster microbioResultEntryMaster;

	public int getSensitivityResultId() {
		return sensitivityResultId;
	}

	public void setSensitivityResultId(int sensitivityResultId) {
		this.sensitivityResultId = sensitivityResultId;
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


	public Character getCheckTest() {
		return checkTest;
	}

	public void setCheckTest(Character checkTest) {
		this.checkTest = checkTest;
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

	public Integer getLabSampleDtlsId() {
		return labSampleDtlsId;
	}

	public void setLabSampleDtlsId(Integer labSampleDtlsId) {
		this.labSampleDtlsId = labSampleDtlsId;
	}


	public List<SensitivityTestResultDetailsMaster> getListSensitivityTestResultDetailsMaster() {
		return listSensitivityTestResultDetailsMaster;
	}

	public void setListSensitivityTestResultDetailsMaster(
			List<SensitivityTestResultDetailsMaster> listSensitivityTestResultDetailsMaster) {
		this.listSensitivityTestResultDetailsMaster = listSensitivityTestResultDetailsMaster;
	}

	public MicrobioResultEntryMaster getMicrobioResultEntryMaster() {
		return microbioResultEntryMaster;
	}

	public void setMicrobioResultEntryMaster(MicrobioResultEntryMaster microbioResultEntryMaster) {
		this.microbioResultEntryMaster = microbioResultEntryMaster;
	}
	
	


}
