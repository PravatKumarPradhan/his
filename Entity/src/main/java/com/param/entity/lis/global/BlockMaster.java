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

	@NamedQuery(name = "GET_REPORT_TYPE_BY_BLOCK_ID", query = "SELECT blockMaster.blockId as id,"
			+ " blockMaster.code as code,"
			+ " blockMaster.desc as desc,"
			+ " blockMaster.status as status,"
			+ " blockMaster.orgId as orgId,"
			+ " blockMaster.isBlockFrozensec as isBlockFrozensec,"
			+ " blockMaster.createdBy as createdBy,"
			+ " blockMaster.createdDate as createdDate,"
			+ " blockMaster.updatedBy as updatedBy,"
			+ " blockMaster.updatedDate as updatedDate "
			+ " FROM BlockMaster blockMaster "
			+ " WHERE blockMaster.blockId = :blockId "
			+ " AND blockMaster.orgId= :orgId"),
			
		@NamedQuery(name = "GET_REPORT_TYPE_BY_BLOCK_CODE", query = "SELECT blockMaster.blockId as id,"
			+ " blockMaster.code as code,"
			+ " blockMaster.desc as desc,"
			+ " blockMaster.status as status,"
			+ " blockMaster.isBlockFrozensec as isBlockFrozensec,"
			+ " blockMaster.createdBy as createdBy,"
			+ " blockMaster.createdDate as createdDate,"
			+ " blockMaster.updatedBy as updatedBy,"
			+ " blockMaster.updatedDate as updatedDate "
			+ " FROM BlockMaster blockMaster "
			+ " WHERE blockMaster.orgId=:orgId "
			+ " AND lower(blockMaster.code) = lower(:code)"),
			
			
			@NamedQuery(name = "UPDATE_GET_REPORT_TYPE_BY_BLOCK_CODE", query = "SELECT blockMaster.blockId as id,"
					+ " blockMaster.code as code,"
					+ " blockMaster.desc as desc,"
					+ " blockMaster.status as status,"
					+ " blockMaster.isBlockFrozensec as isBlockFrozensec,"
					+ " blockMaster.createdBy as createdBy,"
					+ " blockMaster.createdDate as createdDate,"
					+ " blockMaster.updatedBy as updatedBy,"
					+ " blockMaster.updatedDate as updatedDate "
					+ " FROM BlockMaster blockMaster "
					+ " WHERE blockMaster.orgId=:orgId "
					+ " AND lower(blockMaster.code) = lower(:code)"
					+ " AND blockMaster.blockId <> :blockId"),

	@NamedQuery(name = "GET_PAGINATED_BLOCK_MASTER_LIST", query = "SELECT blockMaster.blockId as id,"
			+ " blockMaster.code as code,"
			+ " blockMaster.desc as desc,"
			+ " blockMaster.status as status,"
			+ " blockMaster.isBlockFrozensec as isBlockFrozensec,"
			+ " blockMaster.createdBy as createdBy,"
			+ " blockMaster.createdDate as createdDate,"
			+ " blockMaster.updatedBy as updatedBy,"
			+ " blockMaster.updatedDate as updatedDate "
			+ " FROM BlockMaster blockMaster "
			+ " WHERE blockMaster.orgId = :orgId"
			+ " ORDER BY id DESC")

})

@NamedNativeQueries(
{ @NamedNativeQuery(name = "GET_TOTAL_BLOCK_RECORD", query = "select count(*) from lab.m_block_master where "
	+ "org_id = :orgId ")
})
@Entity
@Table(name = "m_block_master", schema = "lab") 
@SequenceGenerator(name = "m_seq_block_master", sequenceName = "lab.m_seq_block_master", allocationSize = 1)
public class BlockMaster {
	@Id
	@Column(name = "block_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "m_seq_block_master")
	private int blockId;

	@Column(name = "block_code")
	private String code;
	@Column(name = "block_name")
	private String desc;
	@Column(name = "block_status")
	private Character status;
	@Column(name = "is_block_frozensec")
	private Character isBlockFrozensec;
	
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
	 * @return the blockId
	 */
	public int getBlockId() {
		return blockId;
	}

	/**
	 * @param blockId the blockId to set
	 */
	public void setBlockId(int blockId) {
		this.blockId = blockId;
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
	 * @return the isBlockFrozensec
	 */
	public Character getIsBlockFrozensec() {
		return isBlockFrozensec;
	}

	/**
	 * @param isBlockFrozensec the isBlockFrozensec to set
	 */
	public void setIsBlockFrozensec(Character isBlockFrozensec) {
		this.isBlockFrozensec = isBlockFrozensec;
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
