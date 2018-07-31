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

/*	@NamedQuery(name = "GET_REPORT_TYPE_BY_SAMPLE_TYPE_MEDIA_ID", query = "SELECT sampleTypeMediaMappingMaster.sampleMediaMpprId as sampleMediaMpprId,"
			+ " sampleTypeMediaMappingMaster.sampleId as sampleId,"
			+ " sampleTypeMediaMappingMaster.mediaId as mediaId,"
			+ " sampleTypeMediaMappingMaster.orgId as orgId,"
			+ " sampleTypeMediaMappingMaster.sampleMediaStatus as sampleMediaStatus,"
			+ " sampleTypeMediaMappingMaster.createdBy as createdBy,"
			+ " sampleTypeMediaMappingMaster.createdDate as createdDate,"
			+ " sampleTypeMediaMappingMaster.updatedBy as updatedBy,"
			+ " sampleTypeMediaMappingMaster.updatedDate as updatedDate "
			+ " FROM SampleTypeMediaMappingMaster sampleTypeMediaMappingMaster "
			+ " WHERE sampleTypeMediaMappingMaster.sampleMediaMpprId = :sampleMediaMpprId "
			+ " AND sampleTypeMediaMappingMaster.orgId= :orgId"),*/
			
	
	/*@NamedQuery(name = "GET_PAGINATED_SAMPLE_TYPE_MEDIA_LIST", query = "SELECT sampleTypeMediaMappingMaster.sampleMediaMpprId as sampleMediaMpprId,"
			+ " sampleTypeMediaMappingMaster.sampleId as sampleId,"
			+ " sampleTypeMediaMappingMaster.mediaId as mediaId,"
			+ " sampleTypeMediaMappingMaster.sampleMediaStatus as sampleMediaStatus,"
			+ " sampleTypeMediaMappingMaster.createdBy as createdBy,"
			+ " sampleTypeMediaMappingMaster.createdDate as createdDate,"
			+ " sampleTypeMediaMappingMaster.updatedBy as updatedBy,"
			+ " sampleTypeMediaMappingMaster.updatedDate as updatedDate "
			+ " FROM SampleTypeMediaMappingMaster sampleTypeMediaMappingMaster "
			+ " WHERE sampleTypeMediaMappingMaster.orgId = :orgId"
			+ " ORDER BY sampleMediaMpprId DESC")*/
	
	@NamedQuery(name = "GET_SAMPLE_TYPE_MEDIA_MAPPER_TYPE_BY_MEDIA_CODE", query = "SELECT sampleTypeMediaMappingMaster.sampleMediaMpprId as sampleMediaMpprId,"
		    + " sampleTypeMediaMappingMaster.sampleId as sampleId,"
			+ " sampleTypeMediaMappingMaster.mediaId as mediaId,"
			+ " sampleTypeMediaMappingMaster.sampleMediaStatus as sampleMediaStatus,"
			+ " sampleTypeMediaMappingMaster.orgId as orgId,"
			+ " sampleTypeMediaMappingMaster.createdBy as createdBy,"
			+ " sampleTypeMediaMappingMaster.createdDate as createdDate,"
			+ " sampleTypeMediaMappingMaster.updatedBy as updatedBy,"
			+ " sampleTypeMediaMappingMaster.updatedDate as updatedDate"
			+ " FROM SampleTypeMediaMappingMaster sampleTypeMediaMappingMaster"
			+ " WHERE sampleTypeMediaMappingMaster.orgId=:orgId "
			+ " AND sampleTypeMediaMappingMaster.sampleId=:sampleId "),
			
			
			@NamedQuery(name = "ACTIVE_INACTIVE_SAMPLE_TYPE_MEDIA_MAPPER", query = "UPDATE SampleTypeMediaMappingMaster sampleTypeMediaMappingMaster "
					+ " SET sampleTypeMediaMappingMaster.sampleMediaStatus = :status"
					+ " WHERE sampleTypeMediaMappingMaster.orgId= :orgId "
					+ " AND sampleTypeMediaMappingMaster.sampleId = :sampleId"),
		
		@NamedQuery(name = "UPDATE_SAMPLE_TYPE_MEDIA_MAPPER", query = "UPDATE SampleTypeMediaMappingMaster sampleTypeMediaMappingMaster "
				+ "  SET sampleTypeMediaMappingMaster.isDeleted = :isDeleted"
				+ " WHERE sampleTypeMediaMappingMaster.orgId=:orgId "
				+ " AND sampleTypeMediaMappingMaster.sampleId = :sampleId")	

})

@NamedNativeQueries(
{ @NamedNativeQuery(name = "GET_TOTAL_SAMPLE_TYPE_MEDIA_RECORD", query = 
		  " select count(*) from lab.t_sample_media_mppr sample_media_mppr where"
				  + " sample_media_mppr.org_id =:orgId "
				  + " AND sample_media_mppr.is_deleted = 'N' "),
	
	 @NamedNativeQuery(name = "GET_REPORT_TYPE_BY_SAMPLE_TYPE_MEDIA_CODE",  query = "SELECT samplemediamppr.sample_media_mppr_id FROM lab.t_sample_media_mppr samplemediamppr "
	 		 + "WHERE samplemediamppr.sample_id = :sampleId "
			 + "AND samplemediamppr.media_id = :mediaId "
             + "AND samplemediamppr.org_id = :orgId"),


 @NamedNativeQuery(name = "GET_PAGINATED_SAMPLE_TYPE_MEDIA_LIST",  query = " select DISTINCT  mppr.sample_id as \"sampleId\",  "
		 + "  mppr.org_id as \"orgId\", "
		/* + "  mppr.sample_media_mppr_id as \"sampleMediaMpprId\", "*/
		 + "  samplemst.sample_code as \"sampleCode\",  "
		 + "  samplemst.sample_desc  as \"sampleDesc\",  "
		 + "  mppr.sample_media_status as \"sampleMediaStatus\"  "
		 + " from lab.t_sample_media_mppr mppr   "
		 + " inner join lab.m_sample_master samplemst   "
		 + " on samplemst.sample_id = mppr.sample_id   "
		 + " where mppr.org_id =:orgId "
		 + "  AND mppr.is_deleted = 'N'  "
		 + " ORDER BY  mppr.sample_id desc  "
) ,






@NamedNativeQuery(name = "GET_SAMPLE_TYPE_BY_MEDIA_ID",
query =   "select media_code  as \"sampleCode\" , media_description  as \"mediaDescription\" from lab.t_sample_media_mppr mppr  "
		+ " inner join lab.m_media_master mediaMst on mediaMst.media_id = mppr.media_id   "
		+ " where mppr.sample_id =:sampleId"
		+ " AND sample_media_status='A'"
		+ " AND  mppr.is_deleted = 'N' "),
		
		@NamedNativeQuery(name = "GET_SAMPLE_TYPE_MAPPER_BY_MEDIA_ID",
		query = " select mppr.sample_media_mppr_id as \"sampleMediaMpprId\" , "
				+ " mppr.sample_id as \"sampleId\", "
				+ " mppr.org_id as \"orgId\",  "
				+ " sampleMst.sample_desc as \"sampleDesc\", "
				+ " mppr.sample_media_status as \"sampleMediaStatus\", "
				+ " mediaMst.media_description as \"mediaDescription\", "
				+ " mediaMst.media_code as \"sampleCode\", "
				+ " mediaMst.media_id as \"mediaId\", "
				+ " mppr.is_deleted as \"isDeleted\" "
				+ " from lab.t_sample_media_mppr  mppr  "
				+ " inner join lab.m_sample_master  sampleMst  "
				+ " on sampleMst.sample_id = mppr.sample_id  "
				+ " inner join lab.m_media_master  mediaMst  "
				+ " on mediaMst.media_id = mppr.media_id  "
				+ " where mppr.org_id =:orgId "
				+ "  AND  mppr.is_deleted = 'N'  "
				+ " and mppr.sample_id =:sampleId "),



@NamedNativeQuery(name = "GET_MEDIA_LIST_BY_SAMPLE_ID", query =  "  SELECT "
		 +"	media_mst.media_id AS id, "
		 +"	media_mst.media_description AS name "
		 +"FROM "
		 +"	lab.t_sample_media_mppr sample_media_mppr "
		 +"INNER JOIN lab.m_media_master media_mst ON "
		 +"	media_mst.media_id = sample_media_mppr.media_id "
		 +"WHERE "
		 +"	sample_media_mppr.is_deleted = 'N' "
		 +"	AND sample_media_mppr.org_id =:orgId "
		 +"	AND sample_media_mppr.sample_media_status = 'A' "
		 +"	AND sample_media_mppr.sample_id =:sampleId ")


})


@Entity
@Table(name = "t_sample_media_mppr", schema = "lab")
@SequenceGenerator(name = "t_seq_sample_media_mppr", sequenceName = "lab.t_seq_sample_media_mppr", allocationSize = 1)
public class SampleTypeMediaMappingMaster {
	

	@Id
	@Column(name = "sample_media_mppr_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_seq_sample_media_mppr")
	private Integer sampleMediaMpprId;
	
	@Column(name = "sample_id")
	private Integer sampleId;
	
	@Column(name = "media_id")
	private Integer mediaId;
	
	@Column(name = "sample_media_status")
	private Character sampleMediaStatus;
	
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
	
	@Column(name = "is_deleted")
	private Character isDeleted;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "media_id", insertable = false, nullable = false, updatable = false)
	private MediaMaster mediaMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sample_id", insertable = false, nullable = false, updatable = false)
	private SampleMaster sampleMaster;

	/**
	 * @return the sampleMediaMpprId
	 */
	public Integer getSampleMediaMpprId() {
		return sampleMediaMpprId;
	}

	/**
	 * @param sampleMediaMpprId the sampleMediaMpprId to set
	 */
	public void setSampleMediaMpprId(Integer sampleMediaMpprId) {
		this.sampleMediaMpprId = sampleMediaMpprId;
	}

	/**
	 * @return the sampleId
	 */
	public Integer getSampleId() {
		return sampleId;
	}

	/**
	 * @param sampleId the sampleId to set
	 */
	public void setSampleId(Integer sampleId) {
		this.sampleId = sampleId;
	}

	/**
	 * @return the mediaId
	 */
	public Integer getMediaId() {
		return mediaId;
	}

	/**
	 * @param mediaId the mediaId to set
	 */
	public void setMediaId(Integer mediaId) {
		this.mediaId = mediaId;
	}

	/**
	 * @return the sampleMediaStatus
	 */
	public Character getSampleMediaStatus() {
		return sampleMediaStatus;
	}

	/**
	 * @param sampleMediaStatus the sampleMediaStatus to set
	 */
	public void setSampleMediaStatus(Character sampleMediaStatus) {
		this.sampleMediaStatus = sampleMediaStatus;
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
	 * @return the mediaMaster
	 */
	public MediaMaster getMediaMaster() {
		return mediaMaster;
	}

	/**
	 * @param mediaMaster the mediaMaster to set
	 */
	public void setMediaMaster(MediaMaster mediaMaster) {
		this.mediaMaster = mediaMaster;
	}

	/**
	 * @return the sampleMaster
	 */
	public SampleMaster getSampleMaster() {
		return sampleMaster;
	}

	/**
	 * @param sampleMaster the sampleMaster to set
	 */
	public void setSampleMaster(SampleMaster sampleMaster) {
		this.sampleMaster = sampleMaster;
	}

	public Character getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Character isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	
	
}
