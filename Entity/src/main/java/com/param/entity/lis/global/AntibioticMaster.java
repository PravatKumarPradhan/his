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

	@NamedQuery(name = "GET_ANTIBIOTIC_MASTER_ID", query = "SELECT antibioticMaster.antibioticId as id,"
			+ " antibioticMaster.code as code,"
			+ " antibioticMaster.desc as desc,"
			+ " antibioticMaster.status as status,"
			+ " antibioticMaster.orgId as orgId,"
			+ " antibioticMaster.createdBy as createdBy,"
			+ " antibioticMaster.createdDate as createdDate,"
			+ " antibioticMaster.updatedBy as updatedBy,"
			+ " antibioticMaster.updatedDate as updatedDate"
			+ " FROM AntibioticMaster antibioticMaster "
			+ " WHERE antibioticMaster.antibioticId = :antibioticId "
			+ " AND antibioticMaster.orgId= :orgId"),
			
		@NamedQuery(name = "GET_ANTIBIOTIC_MASTER_CODE", query = "SELECT antibioticMaster.antibioticId as id,"
			+ " antibioticMaster.code as code,"
			+ " antibioticMaster.desc as desc,"
			+ " antibioticMaster.status as status,"
			+ " antibioticMaster.orgId as orgId,"
			+ " antibioticMaster.createdBy as createdBy,"
			+ " antibioticMaster.createdDate as createdDate,"
			+ " antibioticMaster.updatedBy as updatedBy,"
			+ " antibioticMaster.updatedDate as updatedDate"
			+ " FROM AntibioticMaster antibioticMaster"
			+ " WHERE antibioticMaster.orgId=:orgId "
			+ " AND lower(antibioticMaster.code) = lower(:code)"),
	
			@NamedQuery(name = "UPDATE_GET_ANTIBIOTIC_MASTER_CODE", query = "SELECT antibioticMaster.antibioticId as id,"
					+ " antibioticMaster.code as code,"
					+ " antibioticMaster.desc as desc,"
					+ " antibioticMaster.status as status,"
					+ " antibioticMaster.orgId as orgId,"
					+ " antibioticMaster.createdBy as createdBy,"
					+ " antibioticMaster.createdDate as createdDate,"
					+ " antibioticMaster.updatedBy as updatedBy,"
					+ " antibioticMaster.updatedDate as updatedDate"
					+ " FROM AntibioticMaster antibioticMaster"
					+ " WHERE antibioticMaster.orgId=:orgId "
					+ " AND lower(antibioticMaster.code) = lower(:code)"
					+ " AND antibioticMaster.antibioticId <> :antibioticId"),
					
					
	@NamedQuery(name = "GET_PAGINATED_ANTIBIOTIC_MASTER_LIST", query = "SELECT antibioticMaster.antibioticId as id,"
			+ " antibioticMaster.code as code,"
			+ " antibioticMaster.desc as desc,"
			+ " antibioticMaster.status as status,"
			+ " antibioticMaster.createdBy as createdBy,"
			+ " antibioticMaster.createdDate as createdDate,"
			+ " antibioticMaster.updatedBy as updatedBy,"
			+ " antibioticMaster.updatedDate as updatedDate"
			+ " FROM AntibioticMaster antibioticMaster"
			+ " WHERE antibioticMaster.orgId = :orgId"
			+ " ORDER BY id DESC"),

	@NamedQuery(name = "GET_LIST_OF_ANTIBIOTIC_MASTER", query = "SELECT antibioticMaster.antibioticId as antibioticId,"
			+ " antibioticMaster.code as code,"
			+ " antibioticMaster.desc as desc "
			+ " FROM AntibioticMaster antibioticMaster "
			+ " ORDER BY antibioticMaster.antibioticId ")
					

			

})

@NamedNativeQueries(
{ @NamedNativeQuery(name = "GET_TOTAL_ANTIBIOTIC__MASTER_RECORD", query = "select count(*) from lab.m_antibiotic_master where "
	+ "org_id = :orgId "),
	
	@NamedNativeQuery(name = "GET_ANTIBIOTIC_BY_ORGANISM_ID_IN_MICRO",
	query =   " SELECT "
			 +"	antibiotic_mst.antibiotic_id AS id, "
			 +"	antibiotic_mst.antibiotic_name AS NAME "
			 +"FROM "
			 +"	lab.t_antibiotic_organism_mppr antibiotic_organism_mppr  "
			 +" INNER JOIN lab.m_antibiotic_master antibiotic_mst ON "
			 +"	antibiotic_mst.antibiotic_id = antibiotic_organism_mppr.antibiotic_id "
			 +"WHERE "
			 +"	antibiotic_organism_mppr.status = 'A' "
			 +"	AND antibiotic_organism_mppr.org_id =:orgId "
			 +"	AND antibiotic_organism_mppr.organism_id =:organismId "
			 +"	AND antibiotic_organism_mppr.is_deleted = 'N' "
			 +"ORDER BY antibiotic_mst.antibiotic_name  ")
})
@Entity
@Table(name = "m_antibiotic_master", schema = "lab")
@SequenceGenerator(name = "seq_antibiotic_master", sequenceName = "lab.seq_antibiotic_master", allocationSize = 1)
public class AntibioticMaster {
	

	@Id
	@Column(name = "antibiotic_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_antibiotic_master")
	private int antibioticId;
	
	@Column(name = "antibiotic_code")
	private String code;
	
	@Column(name = "antibiotic_name")
	private String desc;
	
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
	
	@Column(name = "antibiotic_status")
	private Character status;
	
	@Column(name = "org_id")
	private Integer orgId;

	public int getAntibioticId() {
		return antibioticId;
	}

	public void setAntibioticId(int antibioticId) {
		this.antibioticId = antibioticId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
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

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	



	
}
