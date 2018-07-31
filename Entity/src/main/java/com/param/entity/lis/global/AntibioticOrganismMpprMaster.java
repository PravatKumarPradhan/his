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


			
		@NamedQuery(name = "GET_ANTIBIOTIC_MAPPER_ORGANISM_CODE", query = "SELECT antibioticOrganismMpprMaster.antiboiticOrganismMpprId as antiboiticOrganismMpprId,"
		    + " antibioticOrganismMpprMaster.antiboiticId as antiboiticId,"
			+ " antibioticOrganismMpprMaster.organismId as organismId,"
			+ " antibioticOrganismMpprMaster.status as status,"
			+ " antibioticOrganismMpprMaster.orgId as orgId,"
			+ " antibioticOrganismMpprMaster.createdBy as createdBy,"
			+ " antibioticOrganismMpprMaster.createdDate as createdDate,"
			+ " antibioticOrganismMpprMaster.updatedBy as updatedBy,"
			+ " antibioticOrganismMpprMaster.updatedDate as updatedDate"
			+ " FROM AntibioticOrganismMpprMaster antibioticOrganismMpprMaster"
			+ " WHERE antibioticOrganismMpprMaster.orgId=:orgId "
			+ " AND antibioticOrganismMpprMaster.organismId=:organismId "),
		
		
		
		@NamedQuery(name = "ACTIVE_INACTIVE_ANTIBIOTIC_MAPPER_ORGANISM", query = "UPDATE AntibioticOrganismMpprMaster antibioticOrganismMpprMaster "
					+ " SET antibioticOrganismMpprMaster.status = :status"
					+ " WHERE antibioticOrganismMpprMaster.orgId= :orgId "
					+ " AND antibioticOrganismMpprMaster.organismId = :organismId"),
		
		@NamedQuery(name = "UPDATE_ANTIBIOTIC_MAPPER_ORGANISM", query = "UPDATE AntibioticOrganismMpprMaster antibioticOrganismMpprMaster "
				+ "  SET antibioticOrganismMpprMaster.isDeleted = :isDeleted"
				+ " WHERE antibioticOrganismMpprMaster.orgId=:orgId "
				+ " AND antibioticOrganismMpprMaster.organismId = :organismId")	
		
		

})

@NamedNativeQueries(
{ @NamedNativeQuery(name = "GET_TOTAL_ANTIBIOTIC_MAPPER_ORGANISM_RECORD", query = "select count(*) from lab.t_antibiotic_organism_mppr t_antibiotic_organism_mppr where "
	+ " t_antibiotic_organism_mppr.org_id =:orgId "
	+ " AND t_antibiotic_organism_mppr.is_deleted = 'N' "),
	
	
	@NamedNativeQuery(name = "GET_PAGINATED_ANTIBIOTIC_MAPPER_ORGANISM_MASTER_LIST",
	   query ="select DISTINCT  mppr.organism_id as \"organismId\",  "
			   + " mppr.org_id as \"orgId\", "
			   + " organismst.organism_code as \"organismCode\", "
			   + " organismst.organism_name  as \"organismDesc\", "
			   + " mppr.status as status  "
			   + " from lab.t_antibiotic_organism_mppr mppr  "
			   + " inner join lab.m_organism_master organismst "
			   + " on organismst.organism_id = mppr.organism_id  "
			   + " where mppr.org_id =:orgId "
			   + " AND mppr.is_deleted = 'N'  "
			   + " ORDER BY  mppr.organism_id desc "),
			
			@NamedNativeQuery(name = "GET_ANTIBIOTIC_MAPPER_BY_ORGANISM_ID",
			query = "select mppr.antibiotic_organism_mpper_id \"antiboiticOrganismMpprId\" , "
					+ " mppr.organism_id as \"organismId\", "
					+ " mppr.org_id as \"orgId\", "
					+ " organismMst.organism_name as \"organismDesc\", "
					+ " mppr.status as \"status\", "
					+ " antiMst.antibiotic_name as \"antibioticName\", "
					+ " antiMst.antibiotic_id as \"antibioticId\", "
					+ " mppr.is_deleted as \"isDeleted\" "
					+ " from lab.t_antibiotic_organism_mppr mppr "
					+ " inner join lab.m_organism_master organismMst "
					+ " on organismMst.organism_id = mppr.organism_id "
					+ " inner join lab.m_antibiotic_master antiMst  "
					+ " on antiMst.antibiotic_id = mppr.antibiotic_id "
					+ " where mppr.org_id =:orgId "
					+ " AND  mppr.is_deleted = 'N' "
					+ " and mppr.organism_id =:organismId "),
			
			
			 @NamedNativeQuery(name = "GET_ANTIBIOTIC_BY_ORGANISM_ID",
		     query =  " select antibiotic_code  as \"organismCode\" , antibiotic_name  as \"organismDesc\" from lab.t_antibiotic_organism_mppr mppr "
		        + " inner join lab.m_antibiotic_master antiMst on antiMst.antibiotic_id = mppr.antibiotic_id  "
		        + " where mppr.organism_id =:organismId "
		        + " and status='A'"
		        + " AND  mppr.is_deleted = 'N' "),
		        
		        @NamedNativeQuery(name = "GET_ANTIBIOTIC_BY_ANTIBIOTIC_GROUP_ID_IN_ORGANISM",
		        query =   "SELECT "
		        		+ "  antibiotic_mst.antibiotic_id AS id, "
		        		+ "  antibiotic_mst.antibiotic_name AS NAME "
		        		+ " FROM "
		        		+ "  lab.t_antibiotic_group_mppr antibiotic_group_mppr "
		        		+ " INNER JOIN lab.m_antibiotic_master antibiotic_mst ON "
		        		+ "  antibiotic_mst.antibiotic_id = antibiotic_group_mppr.antibiotic_id "
		        		+ " WHERE "
		        		+ "  antibiotic_group_mppr.status = 'A' "
		        		+ "  AND antibiotic_group_mppr.org_id =:orgId "
		        		+ "  AND antibiotic_group_mppr.antibiotic_group_id IN (:antibioticGroupId) "
		        		+ " ORDER BY "
		        		+ "  antibiotic_mst.antibiotic_name ")
		        
		        
})
@Entity
@Table(name = "t_antibiotic_organism_mppr", schema = "lab")
@SequenceGenerator(name = "t_seq_antibiotic_organism_mppr", sequenceName = "lab.t_seq_antibiotic_organism_mppr", allocationSize = 1)
public class AntibioticOrganismMpprMaster {
	

	@Id
	@Column(name = "antibiotic_organism_mpper_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_seq_antibiotic_organism_mppr")
	private int antiboiticOrganismMpprId;
	
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
	
	@Column(name = "organism_id")
	private Integer organismId;
	
	@Column(name = "antibiotic_id")
	private Integer antiboiticId;
	
	@Column(name = "org_id")
	private Integer orgId;
	
	@Column(name = "is_deleted")
	private Character isDeleted;

	public int getAntiboiticOrganismMpprId() {
		return antiboiticOrganismMpprId;
	}

	public void setAntiboiticOrganismMpprId(int antiboiticOrganismMpprId) {
		this.antiboiticOrganismMpprId = antiboiticOrganismMpprId;
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

	public Integer getOrganismId() {
		return organismId;
	}

	public void setOrganismId(Integer organismId) {
		this.organismId = organismId;
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
