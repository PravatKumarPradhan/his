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

	

			
		@NamedQuery(name = "GET_ORGANIGROUP_ORGANI_CLASS_MAPPER_TYPE_BY_ORGANIGROUP_ORGANI_CODE", query = "SELECT organismGroupOrganismMapperMaster.orgGroupMpprId as orgGroupMpprId,"
		    + " organismGroupOrganismMapperMaster.organismId as organismId,"
			+ " organismGroupOrganismMapperMaster.organismGroupId as organismGroupId,"
			+ " organismGroupOrganismMapperMaster.status as status,"
			+ " organismGroupOrganismMapperMaster.orgId as orgId,"
			+ " organismGroupOrganismMapperMaster.createdBy as createdBy,"
			+ " organismGroupOrganismMapperMaster.createdDate as createdDate,"
			+ " organismGroupOrganismMapperMaster.updatedBy as updatedBy,"
			+ " organismGroupOrganismMapperMaster.updatedDate as updatedDate"
			+ " FROM OrganismGroupOrganismMapperMaster organismGroupOrganismMapperMaster"
			+ " WHERE organismGroupOrganismMapperMaster.orgId=:orgId "
			+ " AND organismGroupOrganismMapperMaster.organismGroupId=:organismGroupId "),
		
		
		
		@NamedQuery(name = "ACTIVE_INACTIVE_ORGANIGROUP_ORGANI_CLASS_MAPPER", query = "UPDATE OrganismGroupOrganismMapperMaster organismGroupOrganismMapperMaster "
					+ " SET organismGroupOrganismMapperMaster.status = :status"
					+ " WHERE organismGroupOrganismMapperMaster.orgId= :orgId "
					+ " AND organismGroupOrganismMapperMaster.organismGroupId = :organismGroupId"),
		
		@NamedQuery(name = "UPDATE_ORGANIGROUP_ORGANI_CLASS_MAPPER", query = "UPDATE OrganismGroupOrganismMapperMaster organismGroupOrganismMapperMaster "
				+ "  SET organismGroupOrganismMapperMaster.isDeleted = :isDeleted"
				+ " WHERE organismGroupOrganismMapperMaster.orgId=:orgId "
				+ " AND organismGroupOrganismMapperMaster.organismGroupId = :organismGroupId")	
			
			
	
		

})

@NamedNativeQueries(
{ @NamedNativeQuery(name = "GET_TOTAL_ORGANIGROUP_ORGANI_CLASS_MAPPER_RECORD", query = "select count(*) from lab.t_organism_group_mppr t_organism_group_mppr  where "
		+ " t_organism_group_mppr.org_id =:orgId "
		+ " AND t_organism_group_mppr.is_deleted = 'N' "),
	
	
	@NamedNativeQuery(name = "GET_PAGINATED_ORGANIGROUP_ORGANI_CLASS_MAPPER_MASTER_LIST",
	   query = " select DISTINCT  mppr.organism_group_id as \"organismGroupId\",  "
			   + " mppr.org_id as \"orgId\", "
			   + " organismGroupMstr.organism_group_code as \"organismGroupCode\",  "
			   + " organismGroupMstr.organism_group_name  as \"organismGroupName\",  "
			   + " mppr.status as \"status\"  "
			   + " from lab.t_organism_group_mppr  mppr   "
			   + " inner join lab.m_organism_group_master organismGroupMstr   "
			   + " on organismGroupMstr.organism_group_id = mppr.organism_group_id   "
			   + " where mppr.org_id =:orgId "
			   + "  AND mppr.is_deleted = 'N'  "
			   + " ORDER BY  mppr.organism_group_id desc  "),
			
			@NamedNativeQuery(name = "GET_ORGANIGROUP_ORGANI_CLASS_MAPPER_BY_ORGANIGROUP_ORGANI_ID",
			query =  " select mppr.org_group_mppr_id as \"orgGroupMpprId\" , "
					+ "  mppr.organism_id as \"organismId\" , "
					+ "  mppr.org_id as \"orgId\" ,  "
					+ "  organismGroupMaster.organism_group_name as \"organismGroupName\", "
					+ "  mppr.status as \"status\", "
					+ "  organismMst.organism_name as \"organismName\", "
					/*+ "  organismMst.organism_id as \"organismId\", "*/
					+ "  mppr.is_deleted as \"isDeleted\""
					+ "  from lab.t_organism_group_mppr  mppr  "
					+ "  inner join lab.m_organism_group_master organismGroupMaster  "
					+ "  on organismGroupMaster.organism_group_id = mppr.organism_group_id  "
					+ "  inner join lab.m_organism_master organismMst  "
					+ "  on organismMst.organism_id = mppr.organism_id  "
					+ "  where mppr.org_id =:orgId "
					+ "  AND  mppr.is_deleted = 'N'  "
					+ "  and mppr.organism_group_id = :organismGroupId "),
			
			
			 @NamedNativeQuery(name = "GET_ORGANIGROUP_ORGANI_BY_ORGANIGROUP_ORGANI_CLASS_ID",
		     query =  " select organism_code as \"organismCode\", organism_name as \"organismDesc\" from lab.t_organism_group_mppr mppr  "
		    		 + "  inner join lab.m_organism_master organismMst on organismMst.organism_id=mppr.organism_id  "
		    		 + "   where mppr.organism_group_id=:organismGroupId "
		    		 + "   AND status='A'  "
		    		 + "   AND  mppr.is_deleted = 'N'"),


@NamedNativeQuery(name = "GET_ORGANISM_BY_GROUP_ID",
query =   " SELECT "
		 +"	organism_mst.organism_id AS id, "
		 +"	organism_mst.organism_name AS NAME "
		 +"FROM "
		 +"	lab.t_organism_group_mppr organism_group_mppr "
		 +"INNER JOIN lab.m_organism_master organism_mst ON "
		 +"	organism_mst.organism_id = organism_group_mppr.organism_id "
		 +"WHERE "
		 +"	organism_group_mppr.status = 'A' "
		 +"	AND organism_group_mppr.org_id =:orgId "
		 +"	AND organism_group_mppr.organism_group_id =:organismGroupId "
		 +"ORDER BY organism_mst.organism_name ")


})
@Entity
@Table(name = "t_organism_group_mppr", schema = "lab")
@SequenceGenerator(name = "t_seq_organism_group_mppr", sequenceName = "lab.t_seq_organism_group_mppr", allocationSize = 1)
public class OrganismGroupOrganismMapperMaster {
	

	@Id
	@Column(name = "org_group_mppr_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_seq_organism_group_mppr")
	private int orgGroupMpprId;
	
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
	
	@Column(name = "organism_group_id")
	private Integer organismGroupId;
	
	@Column(name = "organism_id")
	private Integer organismId;
	
	@Column(name = "org_id")
	private Integer orgId;
	
	@Column(name = "is_deleted")
	private Character isDeleted;

	public int getOrgGroupMpprId() {
		return orgGroupMpprId;
	}

	public void setOrgGroupMpprId(int orgGroupMpprId) {
		this.orgGroupMpprId = orgGroupMpprId;
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

	public Integer getOrganismGroupId() {
		return organismGroupId;
	}

	public void setOrganismGroupId(Integer organismGroupId) {
		this.organismGroupId = organismGroupId;
	}

	public Integer getOrganismId() {
		return organismId;
	}

	public void setOrganismId(Integer organismId) {
		this.organismId = organismId;
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
