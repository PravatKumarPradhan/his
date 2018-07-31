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

	@NamedQuery(name = "GET_SPECIMAN_TYPE_BY_SPECIMAN_ID", query = "SELECT specimanMaster.specimanId as id,"
			+ " specimanMaster.code as code,"
			+ " specimanMaster.desc as desc,"
			+ " specimanMaster.status as status,"
			+ " specimanMaster.orgId as orgId,"
			+ " specimanMaster.createdBy as createdBy,"
			+ " specimanMaster.createdDate as createdDate,"
			+ " specimanMaster.specimanBlock as specimanBlock,"
			+ " specimanMaster.specimanGross as specimanGross,"
			+ " specimanMaster.updatedBy as updatedBy,"
			+ " specimanMaster.updatedDate as updatedDate"
			+ " FROM SpecimanMaster specimanMaster "
			+ " WHERE specimanMaster.specimanId = :specimanId "
			+ " AND specimanMaster.orgId= :orgId"),
			
		@NamedQuery(name = "GET_SPECIMAN_TYPE_BY_SPECIMAN_CODE", query = "SELECT specimanMaster.specimanId as id,"
			+ " specimanMaster.code as code,"
			+ " specimanMaster.desc as desc,"
			+ " specimanMaster.status as status,"
			+ " specimanMaster.createdBy as createdBy,"
			+ " specimanMaster.createdDate as createdDate,"
			+ " specimanMaster.updatedBy as updatedBy,"
			+ " specimanMaster.specimanBlock as specimanBlock,"
			+ " specimanMaster.specimanGross as specimanGross,"
			+ " specimanMaster.updatedDate as updatedDate"
			+ " FROM SpecimanMaster specimanMaster"
			+ " WHERE specimanMaster.orgId=:orgId "
			+ " AND lower(specimanMaster.code) = lower(:code)"),
			
			@NamedQuery(name = "UPDATE_GET_SPECIMAN_TYPE_BY_SPECIMAN_CODE", query = "SELECT specimanMaster.specimanId as id,"
					+ " specimanMaster.code as code,"
					+ " specimanMaster.desc as desc,"
					+ " specimanMaster.status as status,"
					+ " specimanMaster.createdBy as createdBy,"
					+ " specimanMaster.createdDate as createdDate,"
					+ " specimanMaster.specimanBlock as specimanBlock,"
					+ " specimanMaster.specimanGross as specimanGross,"
					+ " specimanMaster.updatedBy as updatedBy,"
					+ " specimanMaster.updatedDate as updatedDate"
					+ " FROM SpecimanMaster specimanMaster"
					+ " WHERE specimanMaster.orgId=:orgId "
					+ " AND lower(specimanMaster.code) = lower(:code)"
					+ " AND specimanMaster.specimanId <> :specimanId"),
	

	@NamedQuery(name = "GET_PAGINATED_SPECIMAN_MASTER_LIST", query = "SELECT specimanMaster.specimanId as id,"
			+ " specimanMaster.code as code,"
			+ " specimanMaster.desc as desc,"
			+ " specimanMaster.status as status,"
			+ " specimanMaster.createdBy as createdBy,"
			+ " specimanMaster.createdDate as createdDate,"
			+ " specimanMaster.specimanBlock as specimanBlock,"
			+ " specimanMaster.specimanGross as specimanGross,"
			+ " specimanMaster.updatedBy as updatedBy,"
			+ " specimanMaster.updatedDate as updatedDate"
			+ " FROM SpecimanMaster specimanMaster"
			+ " WHERE specimanMaster.orgId = :orgId"
			+ " ORDER BY id DESC"),
	
	
	@NamedQuery(name = "GET_SPECIMAN_TYPE_BY_ORG", query = "SELECT specimanMaster.specimanId as id,"
			+ " specimanMaster.desc as name "
			+ " FROM SpecimanMaster specimanMaster"
			+ " WHERE specimanMaster.orgId = :orgId"
			+ " ORDER BY name "),
	
			
	@NamedQuery(name="SEARCH_ACCEPTED_SPECIMEN_TYPE",query=" SELECT "
			 +"	specimanMaster.specimanId AS id, "
			 +"	specimanMaster.desc AS label "
			 +"FROM "
			 +"	SpecimanMaster specimanMaster "
			 
			 +"WHERE "
			 +"	specimanMaster.orgId =:orgId "
			 +"	AND specimanMaster.status ='A' ")
			 
			
})

@NamedNativeQueries(
{ @NamedNativeQuery(name = "GET_TOTAL_SPECIMAN_RECORD", query = "select count(*) from lab.m_speciman_master where "
	+ "org_id = :orgId ")
})
@Entity
@Table(name = "m_speciman_master", schema = "lab")
@SequenceGenerator(name = "m_seq_speciman_master", sequenceName = "lab.m_seq_speciman_master", allocationSize = 1)
public class SpecimanMaster {
	@Id
	@Column(name = "speciman_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "m_seq_speciman_master")
	private int specimanId;
	
	@Column(name = "speciman_code")
	private String code;
	
	@Column(name = "speciman_name")
	private String desc;
	
	@Column(name = "speciman_status")
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
	
	@Column(name = "speciman_block")
	private Character specimanBlock;
	
	@Column(name = "speciman_gross")
	private Character specimanGross;


	/**
	 * @return the specimanId
	 */
	public int getSpecimanId() {
		return specimanId;
	}

	/**
	 * @param specimanId the specimanId to set
	 */
	public void setSpecimanId(int specimanId) {
		this.specimanId = specimanId;
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

	public Character getSpecimanBlock() {
		return specimanBlock;
	}

	public void setSpecimanBlock(Character specimanBlock) {
		this.specimanBlock = specimanBlock;
	}

	public Character getSpecimanGross() {
		return specimanGross;
	}

	public void setSpecimanGross(Character specimanGross) {
		this.specimanGross = specimanGross;
	}

	

}
