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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.param.entity.lis.common.LocalTimeConverter;
import com.param.entity.lis.unit.ParamRefrengMaster;
@NamedQueries({

	@NamedQuery(name = "GET_AGE_GROUP_BY_ID", query = "SELECT ageGroupMaster.ageGroupId as ageGroupId,"
			+ " ageGroupMaster.ageGroupCode as ageGroupCode,"
			+ " ageGroupMaster.ageGroupTo as ageGroupTo,"
			+ " ageGroupMaster.ageTypeGrpfromId as ageTypeGrpfromId,"
			+ " ageGroupMaster.ageTypeGrptoId as ageTypeGrptoId,"
			+ " ageGroupMaster.orgId as orgId,"
			+ " ageGroupMaster.ageTypeGrpName as ageTypeGrpName,"
			+ " ageGroupMaster.ageFromDay as ageFromDay,"
			+ " ageGroupMaster.ageToday as ageToday,"
			+ " ageGroupMaster.createdBy as createdBy,"
			+ " ageGroupMaster.createdDate as createdDate,"
			+ " ageGroupMaster.updatedBy as updatedBy,"
			+ " ageGroupMaster.updatedDate as updatedDate,"
			+ " ageGroupMaster.ageGroupStatus as ageGroupStatus,"
			+ " ageGroupMaster.ageGroupFrom as ageGroupFrom "
			+ " FROM AgeGroupMaster ageGroupMaster "
			+ " WHERE ageGroupMaster.ageGroupId = :ageGroupId "
			+ " AND ageGroupMaster.orgId= :orgId"),
			
		@NamedQuery(name = "GET_AGE_GROUP_BY_CODE", query = "SELECT ageGroupMaster.ageGroupId as ageGroupId,"
			+ " ageGroupMaster.ageGroupCode as ageGroupCode,"
			+ " ageGroupMaster.ageGroupTo as ageGroupTo,"
			+ " ageGroupMaster.ageTypeGrpfromId as ageTypeGrpfromId,"
			+ " ageGroupMaster.ageTypeGrptoId as ageTypeGrptoId," 
			+ " ageGroupMaster.ageGroupStatus as ageGroupStatus,"
			+ " ageGroupMaster.ageGroupFrom as ageGroupFrom,"
			+ " ageGroupMaster.ageTypeGrpName as ageTypeGrpName,"
			+ " ageGroupMaster.ageFromDay as ageFromDay,"
			+ " ageGroupMaster.ageToday as ageToday,"
			+ " ageGroupMaster.createdBy as createdBy,"
			+ " ageGroupMaster.createdDate as createdDate,"
			+ " ageGroupMaster.updatedBy as updatedBy,"
			+ " ageGroupMaster.updatedDate as updatedDate "
			+ " FROM AgeGroupMaster ageGroupMaster"
			+ " WHERE ageGroupMaster.orgId=:orgId "
			+ " AND lower(ageGroupMaster.ageGroupCode) = lower(:ageGroupCode)"),
			
			@NamedQuery(name = "UPDATE_GET_AGE_GROUP_BY_CODE", query = "SELECT ageGroupMaster.ageGroupId as ageGroupId,"
					+ " ageGroupMaster.ageGroupCode as ageGroupCode,"
					+ " ageGroupMaster.ageGroupTo as ageGroupTo,"
					+ " ageGroupMaster.ageTypeGrpfromId as ageTypeGrpfromId,"
					+ " ageGroupMaster.ageTypeGrptoId as ageTypeGrptoId," 
					+ " ageGroupMaster.ageGroupStatus as ageGroupStatus,"
					+ " ageGroupMaster.ageGroupFrom as ageGroupFrom,"
					+ " ageGroupMaster.ageFromDay as ageFromDay,"
					+ " ageGroupMaster.ageToday as ageToday,"
					+ " ageGroupMaster.createdBy as createdBy,"
					+ " ageGroupMaster.ageTypeGrpName as ageTypeGrpName,"
					+ " ageGroupMaster.createdDate as createdDate,"
					+ " ageGroupMaster.updatedBy as updatedBy,"
					+ " ageGroupMaster.updatedDate as updatedDate "
					+ " FROM AgeGroupMaster ageGroupMaster"
					+ " WHERE ageGroupMaster.orgId=:orgId "
					+ " AND lower(ageGroupMaster.ageGroupCode) = lower(:ageGroupCode)"
					+ " AND ageGroupMaster.ageGroupId <> :ageGroupId"),
	

	@NamedQuery(name = "GET_PAGINATED_AGE_GROUP_MASTER_LIST", query = "SELECT ageGroupMaster.ageGroupId as ageGroupId,"
			+ " ageGroupMaster.ageGroupCode as ageGroupCode,"
			+ " ageGroupMaster.ageGroupTo as ageGroupTo,"
			+ " ageGroupMaster.ageTypeGrpfromId as ageTypeGrpfromId,"
			+ " ageGroupMaster.ageTypeGrptoId as ageTypeGrptoId," 
			+ " ageGroupMaster.ageGroupStatus as ageGroupStatus,"
			+ " ageGroupMaster.ageGroupFrom as ageGroupFrom,"
			+ " ageGroupMaster.ageTypeGrpName as ageTypeGrpName,"
			+ " ageGroupMaster.ageFromDay as ageFromDay,"
			+ " ageGroupMaster.ageToday as ageToday,"
			+ " ageGroupMaster.createdBy as createdBy,"
			+ " ageGroupMaster.createdDate as createdDate,"
			+ " ageGroupMaster.updatedBy as updatedBy,"
			+ " ageGroupMaster.updatedDate as updatedDate "
			+ " FROM AgeGroupMaster ageGroupMaster"
			+ " WHERE ageGroupMaster.orgId = :orgId"
			+ " ORDER BY ageGroupId DESC")

})

@NamedNativeQueries(
{ @NamedNativeQuery(name = "GET_TOTAL_AGE_GROUP_RECORD", query = "select count(*) from lab.m_age_group_master where "
	+ "org_id = :orgId ")
})


@Entity
@Table(name = "m_age_group_master", schema = "lab")
@SequenceGenerator(name = "m_seq_age_group_master", sequenceName = "lab.m_seq_age_group_master", allocationSize = 1)
public class AgeGroupMaster 
{
	@Id
	@Column(name = "age_group_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "m_seq_age_group_master")
	private Integer ageGroupId;

	@Column(name = "age_group_code")
	private String ageGroupCode;

	@Column(name = "age_group_to")
	private Integer ageGroupTo;

	@Column(name = "age_type_grpfrom_id")
	private Integer ageTypeGrpfromId;
	
	@Column(name = "age_type_grpto_id")
	private Integer ageTypeGrptoId;
	
	@Column(name = "age_type_grp_name")
	private String ageTypeGrpName;
	
	@Column(name = "age_from_day")
	private Integer ageFromDay;
	
	@Column(name = "age_to_day")
	private Integer ageToday;
	
	@Column(name = "created_by")
	private Integer createdBy;
	
	@Column(name = "created_date")
	@Convert(converter = LocalTimeConverter.class)
	private Long createdDate;

	@Column(name = "updated_date")
	@Convert(converter = LocalTimeConverter.class)
	private Long updatedDate;
	
	@Column(name = "updated_by")
	private Integer updatedBy;
	
	@Column(name = "age_group_status")
	private Character ageGroupStatus;

	@Column(name = "age_group_from")
	private Integer ageGroupFrom;
	
	@Column(name = "org_id")
	private Integer orgId;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ageGroupMaster")
	private List<ParamRefrengMaster> listParamRefrengMaster;

	public Integer getAgeGroupId() {
		return ageGroupId;
	}

	public void setAgeGroupId(Integer ageGroupId) {
		this.ageGroupId = ageGroupId;
	}

	public String getAgeGroupCode() {
		return ageGroupCode;
	}

	public void setAgeGroupCode(String ageGroupCode) {
		this.ageGroupCode = ageGroupCode;
	}

	public Integer getAgeGroupTo() {
		return ageGroupTo;
	}

	public void setAgeGroupTo(Integer ageGroupTo) {
		this.ageGroupTo = ageGroupTo;
	}

	public Integer getAgeTypeGrpfromId() {
		return ageTypeGrpfromId;
	}

	public void setAgeTypeGrpfromId(Integer ageTypeGrpfromId) {
		this.ageTypeGrpfromId = ageTypeGrpfromId;
	}

	public Integer getAgeTypeGrptoId() {
		return ageTypeGrptoId;
	}

	public void setAgeTypeGrptoId(Integer ageTypeGrptoId) {
		this.ageTypeGrptoId = ageTypeGrptoId;
	}

	public String getAgeTypeGrpName() {
		return ageTypeGrpName;
	}

	public void setAgeTypeGrpName(String ageTypeGrpName) {
		this.ageTypeGrpName = ageTypeGrpName;
	}

	public Integer getAgeFromDay() {
		return ageFromDay;
	}

	public void setAgeFromDay(Integer ageFromDay) {
		this.ageFromDay = ageFromDay;
	}

	public Integer getAgeToday() {
		return ageToday;
	}

	public void setAgeToday(Integer ageToday) {
		this.ageToday = ageToday;
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

	public Long getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Long updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Character getAgeGroupStatus() {
		return ageGroupStatus;
	}

	public void setAgeGroupStatus(Character ageGroupStatus) {
		this.ageGroupStatus = ageGroupStatus;
	}

	public Integer getAgeGroupFrom() {
		return ageGroupFrom;
	}

	public void setAgeGroupFrom(Integer ageGroupFrom) {
		this.ageGroupFrom = ageGroupFrom;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public List<ParamRefrengMaster> getListParamRefrengMaster()
	{
		return listParamRefrengMaster;
	}

	public void setListParamRefrengMaster(List<ParamRefrengMaster> listParamRefrengMaster)
	{
		this.listParamRefrengMaster = listParamRefrengMaster;
	}

}
