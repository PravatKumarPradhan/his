package com.param.entity.lis.global;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.param.entity.lis.common.LocalTimeConverter;
@NamedQueries({

	@NamedQuery(name = "GET_REAGENT_BY_REAGENT_ID", query = "SELECT reagentMaster.reagentId as id,"
			+ " reagentMaster.code as code,"
			+ " reagentMaster.desc as desc,"
			+ " reagentMaster.status as status,"
			+ " reagentMaster.orgId as orgId,"
			+ " reagentMaster.createdBy as createdBy,"
			+ " reagentMaster.createdDate as createdDate,"
			+ " reagentMaster.updatedBy as updatedBy,"
			+ " reagentMaster.updatedDate as updatedDate"
			+ " FROM ReagentMaster reagentMaster "
			+ " WHERE reagentMaster.reagentId = :reagentId "
			+ " AND reagentMaster.orgId= :orgId"),
			
		@NamedQuery(name = "GET_REAGENT_BY_REAGENT_CODE", query = "SELECT reagentMaster.reagentId as id,"
			+ " reagentMaster.code as code,"
			+ " reagentMaster.desc as desc,"
			+ " reagentMaster.status as status,"
			+ " reagentMaster.createdBy as createdBy,"
			+ " reagentMaster.createdDate as createdDate,"
			+ " reagentMaster.updatedBy as updatedBy,"
			+ " reagentMaster.updatedDate as updatedDate"
			+ " FROM ReagentMaster reagentMaster"
			+ " WHERE reagentMaster.orgId=:orgId "
			+ " AND lower(reagentMaster.code) = lower(:code)"),
			
			@NamedQuery(name = "UPDATE_GET_REAGENT_BY_REAGENT_CODE", query = "SELECT reagentMaster.reagentId as id,"
					+ " reagentMaster.code as code,"
					+ " reagentMaster.desc as desc,"
					+ " reagentMaster.status as status,"
					+ " reagentMaster.createdBy as createdBy,"
					+ " reagentMaster.createdDate as createdDate,"
					+ " reagentMaster.updatedBy as updatedBy,"
					+ " reagentMaster.updatedDate as updatedDate"
					+ " FROM ReagentMaster reagentMaster"
					+ " WHERE reagentMaster.orgId=:orgId "
					+ " AND lower(reagentMaster.code) = lower(:code)"
					+ " AND reagentMaster.reagentId <> :reagentId"),

	@NamedQuery(name = "GET_PAGINATED_REAGENT_MASTER_LIST", query = "SELECT reagentMaster.reagentId as id,"
			+ " reagentMaster.code as code,"
			+ " reagentMaster.desc as desc,"
			+ " reagentMaster.status as status,"
			+ " reagentMaster.createdBy as createdBy,"
			+ " reagentMaster.createdDate as createdDate,"
			+ " reagentMaster.updatedBy as updatedBy,"
			+ " reagentMaster.updatedDate as updatedDate"
			+ " FROM ReagentMaster reagentMaster"
			+ " WHERE reagentMaster.orgId = :orgId"
			+ " ORDER BY id DESC")

})

@NamedNativeQueries(
{ @NamedNativeQuery(name = "GET_TOTAL_REAGENT_RECORD", query = "select count(*) from lab.m_reagent_master where "
	+ "org_id = :orgId ")
})
@Entity
@Table(name = "m_reagent_master", schema = "lab")
@SequenceGenerator(name = "m_seq_reagent_master", sequenceName = "lab.m_seq_reagent_master", allocationSize = 1)
public class ReagentMaster {
	@Id
	@Column(name = "reagent_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "m_seq_reagent_master")
	private int reagentId;
	
	@Column(name = "reagent_code")
	private String code;
	
	@Column(name = "reagent_name")
	private String desc;
	

	@Column(name = "reagent_status")
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
	


	/**
	 * @return the reagentId
	 */
	public int getReagentId() {
		return reagentId;
	}

	/**
	 * @param reagentId the reagentId to set
	 */
	public void setReagentId(int reagentId) {
		this.reagentId = reagentId;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * @param desc the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * @return the status
	 */
	public Character getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Character status) {
		this.status = status;
	}

	/**
	 * @return the createdBy
	 */
	public Integer getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the createdDate
	 */
	public Long getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Long createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the updatedBy
	 */
	public Integer getUpdatedBy() {
		return updatedBy;
	}

	/**
	 * @param updatedBy the updatedBy to set
	 */
	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	/**
	 * @return the updatedDate
	 */
	public Long getUpdatedDate() {
		return updatedDate;
	}

	/**
	 * @param updatedDate the updatedDate to set
	 */
	public void setUpdatedDate(Long updatedDate) {
		this.updatedDate = updatedDate;
	}

	/**
	 * @return the orgId
	 */
	public Integer getOrgId() {
		return orgId;
	}

	/**
	 * @param orgId the orgId to set
	 */
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	

	
}
