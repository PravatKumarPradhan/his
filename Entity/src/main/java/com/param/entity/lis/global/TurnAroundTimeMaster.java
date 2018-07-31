package com.param.entity.lis.global;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.param.entity.lis.unit.TestMaster;
import com.param.entity.lis.common.LocalTimeConverter;

@Entity
@Table(name = "m_tat_time_master", schema = "lab")
@SequenceGenerator(name = "m_seq_tat_time_master", sequenceName = "lab.m_seq_tat_time_master", allocationSize = 1)
public class TurnAroundTimeMaster {
	@Id
	@Column(name = "tat_time_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "m_seq_tat_time_master")
	private int tatTimeId;
	
	@Column(name = "tat_time_name")
	private String tatTimeName;
	
	@Column(name = "tat_time_status")
	private String status;

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
	
	@Column(name = "org_id")
	private Integer orgId;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "urgTurnAroundTimeMaster")
	private List<TestMaster> listTestMasterForUrgent;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "norTurnAroundTimeMaster")
	private List<TestMaster> listTestMasterNormal;

	public int getTatTimeId() {
		return tatTimeId;
	}

	public void setTatTimeId(int tatTimeId) {
		this.tatTimeId = tatTimeId;
	}

	public String getTatTimeName() {
		return tatTimeName;
	}

	public void setTatTimeName(String tatTimeName) {
		this.tatTimeName = tatTimeName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public List<TestMaster> getListTestMasterForUrgent()
	{
		return listTestMasterForUrgent;
	}

	public void setListTestMasterForUrgent(List<TestMaster> listTestMasterForUrgent)
	{
		this.listTestMasterForUrgent = listTestMasterForUrgent;
	}

	public List<TestMaster> getListTestMasterNormal()
	{
		return listTestMasterNormal;
	}

	public void setListTestMasterNormal(List<TestMaster> listTestMasterNormal)
	{
		this.listTestMasterNormal = listTestMasterNormal;
	}
	
	
}
