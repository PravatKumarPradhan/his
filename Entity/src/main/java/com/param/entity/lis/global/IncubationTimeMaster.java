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
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.param.entity.lis.common.LocalTimeConverter;

@NamedNativeQueries({

	@NamedNativeQuery(name = "GET_INCUBATION_TIME_LIST", query = "SELECT incubationtimemst.incubation_time_id AS id,"
			+ " incubationtimemst.incubation_desc AS name "
			+ " FROM lab.m_incubation_time_master incubationtimemst "
			+ " WHERE incubationtimemst.status = 'A'  "
			+ " AND incubationtimemst.org_id =:orgId "),
			

})


@Entity
@Table(name = "m_incubation_test_master", schema = "lab")
public class IncubationTimeMaster {
	

	@Id
	@Column(name = "incubation_time_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int incubationTimeId;
	
	@Column(name = "incubation_desc")
	private String incubationDesc;
	
	@Column(name = "status")
	private Character status;
	
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "incubationTimeMaster")
	private List<IncubationPeriodMaster> listIncubationPeriodMaster;

	public int getIncubationTimeId() {
		return incubationTimeId;
	}

	public void setIncubationTimeId(int incubationTimeId) {
		this.incubationTimeId = incubationTimeId;
	}

	public String getIncubationDesc() {
		return incubationDesc;
	}

	public void setIncubationDesc(String incubationDesc) {
		this.incubationDesc = incubationDesc;
	}

	public Character getStatus() {
		return status;
	}

	public void setStatus(Character status) {
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

	public List<IncubationPeriodMaster> getListIncubationPeriodMaster() {
		return listIncubationPeriodMaster;
	}

	public void setListIncubationPeriodMaster(List<IncubationPeriodMaster> listIncubationPeriodMaster) {
		this.listIncubationPeriodMaster = listIncubationPeriodMaster;
	}
	
	
	

	
	

}
