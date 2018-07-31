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

	
/*	@NamedQuery(name = "GET_ANTIBIOTIC_CLASS_MAPPER_BY_ANTIBIOTIC_ID", query = "SELECT antibioticAdditionMaster.antiboiticMpprId as antiboiticMpprId,"
			+ " antibioticAdditionMaster.antiboiticId as antiboiticId,"
			+ " antibioticAdditionMaster.antiboiticClassId as antiboiticClassId,"
			+ " antibioticAdditionMaster.status as status,"
			+ " antibioticAdditionMaster.orgId as orgId,"
			+ " antibioticAdditionMaster.createdBy as createdBy,"
			+ " antibioticAdditionMaster.createdDate as createdDate,"
			+ " antibioticAdditionMaster.updatedBy as updatedBy,"
			+ " antibioticAdditionMaster.updatedDate as updatedDate"
			+ " FROM AntibioticAdditionMaster antibioticAdditionMaster "
			+ " WHERE antibioticAdditionMaster.antiboiticMpprId = :antiboiticMpprId "
			+ " AND antibioticAdditionMaster.orgId= :orgId"),*/
			
		@NamedQuery(name = "GET_ANTIBIOTIC_CLASS_MAPPER_TYPE_BY_ANTIBIOTIC_CODE", query = "SELECT antibioticAdditionMaster.antiboiticMpprId as antiboiticMpprId,"
		    + " antibioticAdditionMaster.antiboiticId as antiboiticId,"
			+ " antibioticAdditionMaster.antiboiticClassId as antiboiticClassId,"
			+ " antibioticAdditionMaster.status as status,"
			+ " antibioticAdditionMaster.orgId as orgId,"
			+ " antibioticAdditionMaster.createdBy as createdBy,"
			+ " antibioticAdditionMaster.createdDate as createdDate,"
			+ " antibioticAdditionMaster.updatedBy as updatedBy,"
			+ " antibioticAdditionMaster.updatedDate as updatedDate"
			+ " FROM AntibioticAdditionMaster antibioticAdditionMaster"
			+ " WHERE antibioticAdditionMaster.orgId=:orgId "
			+ " AND antibioticAdditionMaster.antiboiticClassId=:antiboiticClassId "),
		
		
		
		@NamedQuery(name = "ACTIVE_INACTIVE_ANTIBIOTIC_CLASS_MAPPER", query = "UPDATE AntibioticAdditionMaster antibioticAdditionMaster "
					+ " SET antibioticAdditionMaster.status = :status"
					+ " WHERE antibioticAdditionMaster.orgId= :orgId "
					+ " AND antibioticAdditionMaster.antiboiticClassId = :antiboiticClassId"),
		
		@NamedQuery(name = "UPDATE_ANTIBIOTIC_CLASS_MAPPER", query = "UPDATE AntibioticAdditionMaster antibioticAdditionMaster "
				+ "  SET antibioticAdditionMaster.isDeleted = :isDeleted"
				+ " WHERE antibioticAdditionMaster.orgId=:orgId "
				+ " AND antibioticAdditionMaster.antiboiticClassId = :antiboiticClassId")	
			
			/*@NamedQuery(name = "UPDATE_GET_ANTIBIOTIC_CLASS_MAPPER_TYPE_BY_ANTIBIOTIC_CODE", query = "SELECT antibioticAdditionMaster.antiboiticMpprId as antiboiticMpprId,"
					+ " antibioticAdditionMaster.antiboiticId as antiboiticId,"
					+ " antibioticAdditionMaster.antiboiticClassId as antiboiticClassId,"
					+ " antibioticAdditionMaster.status as status,"
					+ " antibioticAdditionMaster.orgId as orgId,"
					+ " antibioticAdditionMaster.createdBy as createdBy,"
					+ " antibioticAdditionMaster.createdDate as createdDate,"
					+ " antibioticAdditionMaster.updatedBy as updatedBy,"
					+ " antibioticAdditionMaster.updatedDate as updatedDate"
					+ " FROM AntibioticAdditionMaster antibioticAdditionMaster"
					+ " WHERE antibioticAdditionMaster.orgId=:orgId "
					+ " AND lower(antibioticAdditionMaster.code) = lower(:code)"
					+ " AND antibioticAdditionMaster.antiboiticMpprId <> :antiboiticMpprId"),
	

	@NamedQuery(name = "GET_PAGINATED_ANTIBIOTIC_CLASS_MAPPER_LIST", query = "SELECT antibioticAdditionMaster.antiboiticMpprId as antiboiticMpprId,"
			+ " antibioticAdditionMaster.antiboiticId as antiboiticId,"
			+ " antibioticAdditionMaster.antiboiticClassId as antiboiticClassId,"
			+ " antibioticAdditionMaster.status as status,"
			+ " antibioticAdditionMaster.createdBy as createdBy,"
			+ " antibioticAdditionMaster.createdDate as createdDate,"
			+ " antibioticAdditionMaster.updatedBy as updatedBy,"
			+ " antibioticAdditionMaster.updatedDate as updatedDate"
			+ " FROM AntibioticAdditionMaster antibioticAdditionMaster"
			+ " WHERE antibioticAdditionMaster.orgId = :orgId"
			+ " ORDER BY antiboiticMpprId DESC"),*/
			
			
	
	
		

})

@NamedNativeQueries(
{ @NamedNativeQuery(name = "GET_TOTAL_ANTIBIOTIC_CLASS_MAPPER_RECORD", query = "select count(*) from lab.t_antibiotic_class_mppr antibiotic_class_mppr where "
	+ " antibiotic_class_mppr.org_id =:orgId "
	+ " AND antibiotic_class_mppr.is_deleted = 'N' "),
	
	
	@NamedNativeQuery(name = "GET_PAGINATED_ANTIBIOTIC_CLASS_MAPPER_MASTER_LIST",
	   query = "select DISTINCT  mppr.antibiotic_class_id as \"antiboiticClassId\", "
	   /* + "  mppr.antibiotic_mppr_id \"antiboiticMpprId\","*/
	   + "  mppr.org_id as \"orgId\","
	            + "  anticlassmst.antibiotic_class_code as \"antiboiticClassCode\", "
	            + "  anticlassmst.antibiotic_class_desc  as \"antibioticClassDesc\", "
	            + "  mppr.status as status "
	            + "  from lab.t_antibiotic_class_mppr mppr  "
	            + "  inner join lab.m_antibiotic_class_master anticlassmst  "
	            + "  on anticlassmst.antibiotic_class_id = mppr.antibiotic_class_id  "
	            + "  where mppr.org_id =:orgId  "
	            + " AND mppr.is_deleted = 'N' "
	            + "  ORDER BY  mppr.antibiotic_class_id desc "),
			
			@NamedNativeQuery(name = "GET_ANTIBIOTIC_CLASS_MAPPER_BY_ANTIBIOTIC_ID",
			query = "select mppr.antibiotic_mppr_id as \"antiboiticMpprId\" ,"
					+ " mppr.antibiotic_class_id as \"antiboiticClassId\" ,"
					+ " mppr.org_id as \"orgId\" , "
					+ " antiClassMst.antibiotic_class_desc as \"antibioticClassDesc\","
					+ " mppr.status as status,"
					+ " antiMst.antibiotic_name as \"antibioticName\","
					+ " antiMst.antibiotic_id as \"antibioticId\","
					+ " mppr.is_deleted as \"isDeleted\""
					+ " from lab.t_antibiotic_class_mppr mppr "
					+ " inner join lab.m_antibiotic_class_master antiClassMst "
					+ " 	on antiClassMst.antibiotic_class_id = mppr.antibiotic_class_id "
					+ " inner join lab.m_antibiotic_master antiMst "
					+ " 	on antiMst.antibiotic_id = mppr.antibiotic_id "
					+ " where mppr.org_id =:orgId "
					+ " AND  mppr.is_deleted = 'N' "
					+ " and mppr.antibiotic_class_id =:antiboiticClassId"),
			
			
			 @NamedNativeQuery(name = "GET_ANTIBIOTIC_BY_ANTIBIOTIC_CLASS_ID",
		     query =  " select antibiotic_code  as \"antiboiticClassCode\" , antibiotic_name  as \"antibioticName\" from lab.t_antibiotic_class_mppr mppr "
		        + " inner join lab.m_antibiotic_master antiMst on antiMst.antibiotic_id = mppr.antibiotic_id  "
		        + " where mppr.antibiotic_class_id =:antiboiticClassId "
		        + " and status='A'"
		        + " AND  mppr.is_deleted = 'N' ")
})
@Entity
@Table(name = "t_antibiotic_class_mppr", schema = "lab")
@SequenceGenerator(name = "t_seq_antiboitic_class_mppr", sequenceName = "lab.t_seq_antiboitic_class_mppr", allocationSize = 1)
public class AntibioticAdditionMaster {
	

	@Id
	@Column(name = "antibiotic_mppr_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_seq_antiboitic_class_mppr")
	private int antiboiticMpprId;
	
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
	
	@Column(name = "antibiotic_class_id")
	private Integer antiboiticClassId;
	
	@Column(name = "antibiotic_id")
	private Integer antiboiticId;
	
	@Column(name = "org_id")
	private Integer orgId;
	
	@Column(name = "is_deleted")
	private Character isDeleted;

	public int getAntiboiticMpprId() {
		return antiboiticMpprId;
	}


	public void setAntiboiticMpprId(int antiboiticMpprId) {
		this.antiboiticMpprId = antiboiticMpprId;
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


	public Integer getAntiboiticClassId() {
		return antiboiticClassId;
	}


	public void setAntiboiticClassId(Integer antiboiticClassId) {
		this.antiboiticClassId = antiboiticClassId;
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
