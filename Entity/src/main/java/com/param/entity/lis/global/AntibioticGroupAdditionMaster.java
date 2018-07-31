package com.param.entity.lis.global;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.param.entity.lis.common.LocalTimeConverter;
@NamedQueries({

	

			
		@NamedQuery(name = "GET_ANTIBIOTIC_GROUP_MAPPER_TYPE_BY_ANTIBIOTIC_CODE", query = "SELECT antibioticGroupAdditionMaster.antiboiticGroupMpprId as antiboiticGroupMpprId,"
		    + " antibioticGroupAdditionMaster.antiboiticId as antiboiticId,"
			+ " antibioticGroupAdditionMaster.antiboiticGroupId as antiboiticGroupId,"
			+ " antibioticGroupAdditionMaster.status as status,"
			+ " antibioticGroupAdditionMaster.orgId as orgId,"
			+ " antibioticGroupAdditionMaster.createdBy as createdBy,"
			+ " antibioticGroupAdditionMaster.createdDate as createdDate,"
			+ " antibioticGroupAdditionMaster.updatedBy as updatedBy,"
			+ " antibioticGroupAdditionMaster.updatedDate as updatedDate"
			+ " FROM AntibioticGroupAdditionMaster antibioticGroupAdditionMaster"
			+ " WHERE antibioticGroupAdditionMaster.orgId=:orgId "
			+ " AND antibioticGroupAdditionMaster.antiboiticGroupId=:antiboiticGroupId "),
			
			
		
		
		
		
		@NamedQuery(name = "ACTIVE_INACTIVE_ANTIBIOTIC_GROUP_MAPPER", query = "UPDATE AntibioticGroupAdditionMaster antibioticGroupAdditionMaster "
					+ " SET antibioticGroupAdditionMaster.status = :status"
					+ " WHERE antibioticGroupAdditionMaster.orgId= :orgId "
					+ " AND antibioticGroupAdditionMaster.antiboiticGroupId = :antiboiticGroupId"),
		
		@NamedQuery(name = "UPDATE_ANTIBIOTIC_GROUP_MAPPER", query = "UPDATE AntibioticGroupAdditionMaster antibioticGroupAdditionMaster "
				+ "  SET antibioticGroupAdditionMaster.isDeleted = :isDeleted"
				+ " WHERE antibioticGroupAdditionMaster.orgId=:orgId "
				+ " AND antibioticGroupAdditionMaster.antiboiticGroupId = :antiboiticGroupId")	
			
		
			
	
	
		

})

@NamedNativeQueries(
{ @NamedNativeQuery(name = "GET_TOTAL_ANTIBIOTIC_GROUP_MAPPER_RECORD", query = "select count(*) from lab.t_antibiotic_group_mppr anibiotic_group_mppr where "
	+ " anibiotic_group_mppr.org_id =:orgId "
	+ " AND anibiotic_group_mppr.is_deleted = 'N' "),
	
	
	@NamedNativeQuery(name = "GET_PAGINATED_ANTIBIOTIC_GROUP_MAPPER_MASTER_LIST",
	   query = "select DISTINCT  mppr.antibiotic_group_id as \"antiboiticGroupId\", "
	   + "  mppr.org_id as \"orgId\","
	            + "  antigroupmst.antibiotic_group_code as \"antibioticGorupCode\", "
	            + "  antigroupmst.antibiotic_group_desc  as \"antibioticGroupDesc\", "
	            + "  mppr.status as status "
	            + "  from lab.t_antibiotic_group_mppr mppr  "
	            + "  inner join lab.m_antibiotic_group_master antigroupmst  "
	            + "  on antigroupmst.antibiotic_group_id = mppr.antibiotic_group_id  "
	            + "  where mppr.org_id =:orgId  "
	            + " AND mppr.is_deleted = 'N' "
	            + "  ORDER BY  mppr.antibiotic_group_id desc "),
			
			@NamedNativeQuery(name = "GET_ANTIBIOTIC_GROUP_MAPPER_BY_ANTIBIOTIC_ID",
			query = "select mppr.antibiotic_group_mppr_id as \"antiboiticGroupMpprId\" ,"
					+ " mppr.antibiotic_group_id as \"antiboiticGroupId\" ,"
					+ " mppr.org_id as \"orgId\" , "
					+ " antiGroupMst.antibiotic_group_desc as \"antibioticGroupDesc\","
					+ " mppr.status as status,"
					+ " antiMst.antibiotic_name as \"antibioticName\","
					+ " antiMst.antibiotic_id as \"antiboiticId\","
					+ " mppr.is_deleted as \"isDeleted\""
					+ " from lab.t_antibiotic_group_mppr mppr "
					+ " inner join lab.m_antibiotic_group_master antiGroupMst "
					+ " 	on antiGroupMst.antibiotic_group_id = mppr.antibiotic_group_id "
					+ " inner join lab.m_antibiotic_master antiMst "
					+ " 	on antiMst.antibiotic_id = mppr.antibiotic_id "
					+ " where mppr.org_id =:orgId "
					+ " AND  mppr.is_deleted = 'N' "
					+ " and mppr.antibiotic_group_id =:antiboiticGroupId"),
			
			
			 @NamedNativeQuery(name = "GET_ANTIBIOTIC_BY_ANTIBIOTIC_GROUP_ID",
		     query =  " select antibiotic_code  as \"antibioticGorupCode\" , antibiotic_name  as \"antibioticName\" from lab.t_antibiotic_group_mppr mppr "
		        + " inner join lab.m_antibiotic_master antiMst on antiMst.antibiotic_id = mppr.antibiotic_id  "
		        + " where mppr.antibiotic_group_id =:antiboiticGroupId "
		        + " and status='A'"
		        + " AND  mppr.is_deleted = 'N' ")
})
@Entity
@Table(name = "t_antibiotic_group_mppr", schema = "lab")
@SequenceGenerator(name = "t_seq_antiboitic_group_mppr", sequenceName = "lab.t_seq_antiboitic_group_mppr", allocationSize = 1)
public class AntibioticGroupAdditionMaster {
	

	@Id
	@Column(name = "antibiotic_group_mppr_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_seq_antiboitic_group_mppr")
	private int antiboiticGroupMpprId;
	
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
	
	@Column(name = "status")
	private Character status;
	
	@Column(name = "antibiotic_group_id")
	private Integer antiboiticGroupId;
	
	@Column(name = "antibiotic_id")
	private Integer antiboiticId;
	
	@Column(name = "org_id")
	private Integer orgId;
	
	@Column(name = "is_deleted")
	private Character isDeleted;

	public int getAntiboiticGroupMpprId() {
		return antiboiticGroupMpprId;
	}

	public void setAntiboiticGroupMpprId(int antiboiticGroupMpprId) {
		this.antiboiticGroupMpprId = antiboiticGroupMpprId;
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

	public Character getStatus() {
		return status;
	}

	public void setStatus(Character status) {
		this.status = status;
	}

	public Integer getAntiboiticGroupId() {
		return antiboiticGroupId;
	}

	public void setAntiboiticGroupId(Integer antiboiticGroupId) {
		this.antiboiticGroupId = antiboiticGroupId;
	}

	public Integer getAntiboiticId() {
		return antiboiticId;
	}

	public void setAntiboiticId(Integer antiboiticId) {
		this.antiboiticId = antiboiticId;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public Character getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Character isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	

	
}
