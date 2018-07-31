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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.param.entity.lis.common.LocalTimeConverter;
@NamedQueries({
	@NamedQuery(name = "GET_MEDIA_BY_MEDIA_ID", query = "SELECT mediaMaster.mediaId as id,"
			+ " mediaMaster.code as code,"
			+ " mediaMaster.desc as desc,"
			+ " mediaMaster.status as status,"
			+ " mediaMaster.orgId as orgId,"
			+ " mediaMaster.createdBy as createdBy,"
			+ " mediaMaster.createdDate as createdDate,"
			+ " mediaMaster.updatedBy as updatedBy,"
			+ " mediaMaster.updatedDate as updatedDate"
			+ " FROM MediaMaster mediaMaster "
			+ " WHERE mediaMaster.mediaId = :mediaId "
			+ " AND mediaMaster.orgId= :orgId"),
  @NamedQuery(name = "GET_MEDIA_BY_CODE", query = "SELECT mediaMaster.mediaId as id,"
		+ " mediaMaster.code as code,"
		+ " mediaMaster.desc as desc,"
		+ " mediaMaster.status as status,"
		+ " mediaMaster.orgId as orgId,"
		+ " mediaMaster.createdBy as createdBy,"
		+ " mediaMaster.createdDate as createdDate,"
		+ " mediaMaster.updatedBy as updatedBy,"
		+ " mediaMaster.updatedDate as updatedDate"
		+ " FROM MediaMaster mediaMaster"
		+ " WHERE mediaMaster.orgId=:orgId "
		+ " AND lower(mediaMaster.code) = lower(:code)"),
		
		@NamedQuery(name = "UPDATE_GET_MEDIA_BY_CODE", query = "SELECT mediaMaster.mediaId as id,"
				+ " mediaMaster.code as code,"
				+ " mediaMaster.desc as desc,"
				+ " mediaMaster.status as status,"
				+ " mediaMaster.orgId as orgId,"
				+ " mediaMaster.createdBy as createdBy,"
				+ " mediaMaster.createdDate as createdDate,"
				+ " mediaMaster.updatedBy as updatedBy,"
				+ " mediaMaster.updatedDate as updatedDate"
				+ " FROM MediaMaster mediaMaster"
				+ " WHERE mediaMaster.orgId=:orgId "
				+ " AND lower(mediaMaster.code) = lower(:code)"
				+ " AND mediaMaster.mediaId <> :mediaId"),
		
	
  @NamedQuery(name = "GET_PAGINATED_MEDIA_MASTER_LIST", query = "SELECT mediaMaster.mediaId as id,"
				+ " mediaMaster.code as code,"
				+ " mediaMaster.desc as desc,"
				+ " mediaMaster.status as status,"
				+ " mediaMaster.orgId as orgId,"
				+ " mediaMaster.createdBy as createdBy,"
				+ " mediaMaster.createdDate as createdDate,"
				+ " mediaMaster.updatedBy as updatedBy,"
				+ " mediaMaster.updatedDate as updatedDate"
				+ " FROM MediaMaster mediaMaster"
				+ " WHERE mediaMaster.orgId = :orgId"
				+ " ORDER BY id DESC")
})
@NamedNativeQueries(
{ @NamedNativeQuery(name = "GET_TOTAL_MEDIA_RECORD", query = "select count(*) from lab.m_media_master where "
		+ "org_id = :orgId ")
})
@Entity
@Table(name = "m_media_master", schema = "lab")
@SequenceGenerator(name = "m_seq_media_master", sequenceName = "lab.m_seq_media_master", allocationSize = 1)
public class MediaMaster {
	@Id
	@Column(name = "media_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "m_seq_media_master")
	private int mediaId;
	
	@Column(name = "media_code")
	private String code;
	
	@Column(name = "media_description")
	private String desc;
	
	
	@Column(name = "media_status")
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
	


	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "mediaMaster")
	private List<SampleTypeMediaMappingMaster> listSampleTypeMediaMappingMaster;
	
	

	/**
	 * @return the mediaId
	 */
	public int getMediaId() {
		return mediaId;
	}

	/**
	 * @param mediaId the mediaId to set
	 */
	public void setMediaId(int mediaId) {
		this.mediaId = mediaId;
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

	
	/**
	 * @return the listSampleTypeMediaMappingMaster
	 */
	public List<SampleTypeMediaMappingMaster> getListSampleTypeMediaMappingMaster() {
		return listSampleTypeMediaMappingMaster;
	}

	/**
	 * @param listSampleTypeMediaMappingMaster the listSampleTypeMediaMappingMaster to set
	 */
	public void setListSampleTypeMediaMappingMaster(List<SampleTypeMediaMappingMaster> listSampleTypeMediaMappingMaster) {
		this.listSampleTypeMediaMappingMaster = listSampleTypeMediaMappingMaster;
	}

	
	
}
