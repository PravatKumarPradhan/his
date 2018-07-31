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

	

			
		@NamedQuery(name = "GET_MEDIACOLONY_CLASS_MAPPER_TYPE_BY_MEDIACOLONY_CODE", query = "SELECT mediaColonyMapperMaster.mediaColonyMpprId as mediaColonyMpprId,"
		    + " mediaColonyMapperMaster.colonyId as colonyId,"
			+ " mediaColonyMapperMaster.mediaId as mediaId,"
			+ " mediaColonyMapperMaster.status as status,"
			+ " mediaColonyMapperMaster.orgId as orgId,"
			+ " mediaColonyMapperMaster.createdBy as createdBy,"
			+ " mediaColonyMapperMaster.createdDate as createdDate,"
			+ " mediaColonyMapperMaster.updatedBy as updatedBy,"
			+ " mediaColonyMapperMaster.updatedDate as updatedDate"
			+ " FROM MediaColonyMapperMaster mediaColonyMapperMaster"
			+ " WHERE mediaColonyMapperMaster.orgId=:orgId "
			+ " AND mediaColonyMapperMaster.mediaId=:mediaId "),
		
		
		
		@NamedQuery(name = "ACTIVE_INACTIVE_MEDIACOLONY_CLASS_MAPPER", query = "UPDATE MediaColonyMapperMaster mediaColonyMapperMaster "
					+ " SET mediaColonyMapperMaster.status = :status"
					+ " WHERE mediaColonyMapperMaster.orgId= :orgId "
					+ " AND mediaColonyMapperMaster.mediaId = :mediaId"),
		
		@NamedQuery(name = "UPDATE_MEDIACOLONY_CLASS_MAPPER", query = "UPDATE MediaColonyMapperMaster mediaColonyMapperMaster "
				+ "  SET mediaColonyMapperMaster.isDeleted = :isDeleted"
				+ " WHERE mediaColonyMapperMaster.orgId=:orgId "
				+ " AND mediaColonyMapperMaster.mediaId = :mediaId")	
			
			
	
		

})

@NamedNativeQueries(
{ @NamedNativeQuery(name = "GET_TOTAL_MEDIACOLONY_CLASS_MAPPER_RECORD", query = "select count(*) from lab.t_media_colony_mppr media_colony_mppr  where "
		+ " media_colony_mppr.org_id =:orgId "
		+ " AND media_colony_mppr.is_deleted = 'N' "),
	
	
	@NamedNativeQuery(name = "GET_PAGINATED_MEDIACOLONY_CLASS_MAPPER_MASTER_LIST",
	   query = "select DISTINCT  mppr.media_id as \"mediaId\", "
			   + "  mppr.org_id as \"orgId\","
	            + "  mediamst.media_code as \"mediaCode\", "
	            + "  mediamst.media_description  as \"mediaDesc\", "
	            + "  mppr.status as status "
	            + "  from lab.t_media_colony_mppr mppr  "
	            + "  inner join lab.m_media_master mediamst  "
	            + "  on mediamst.media_id = mppr.media_id  "
	            + "  where mppr.org_id =:orgId  "
	            + "  AND mppr.is_deleted = 'N' "
	            + "  ORDER BY  mppr.media_id desc "),
			
			@NamedNativeQuery(name = "GET_MEDIACOLONY_CLASS_MAPPER_BY_MEDIACOLONY_ID",
			query = "select mppr.media_colony_mppr_id as \"mediaColonyMpprId\" ,"
					+ " mppr.media_id as \"mediaId\" ,"
					+ " mppr.org_id as \"orgId\" , "
					+ " mediaMst.media_description as \"mediaDesc\","
					+ " mppr.status as status,"
					+ " colonymst.colony_name as \"colonyName\","
					+ " colonymst.colony_id as \"colonyId\","
					+ " mppr.is_deleted as \"isDeleted\""
					+ " from lab.t_media_colony_mppr mppr "
					+ " inner join lab.m_media_master mediaMst "
					+ " on mediaMst.media_id = mppr.media_id "
					+ " inner join lab.m_colony_master colonymst "
					+ " on colonymst.colony_id = mppr.colony_id "
					+ " where mppr.org_id =:orgId "
					+ " AND  mppr.is_deleted = 'N' "
					+ " and mppr.media_id =:mediaId"),
			
			
			 @NamedNativeQuery(name = "GET_MEDIACOLONY_BY_MEDIACOLONY_CLASS_ID",
		     query =  "select  colony_code as \"mediaCode\", colony_name as \"mediaDesc\" from lab.t_media_colony_mppr mppr "
		    		    + " inner join lab.m_colony_master mediMst on mediMst.colony_id=mppr.colony_id "
		    		    + "where mppr.media_id=:mediaId "
		    		    + " AND status='A' "
		    		   + " AND  mppr.is_deleted = 'N' "),


@NamedNativeQuery(name = "GET_TOTAL_MEDIACOLONYS_MAPPER_COUNT", query = "select count(*) from lab.t_media_colony_mppr media_colony_mppr  where "
		+"  media_colony_mppr.media_id IN (:mediaId) "
		+ " AND media_colony_mppr.org_id =:orgId "
		+ " AND media_colony_mppr.is_deleted = 'N' ")
})
@Entity
@Table(name = "t_media_colony_mppr", schema = "lab")
@SequenceGenerator(name = "t_seq_media_colony_mppr", sequenceName = "lab.t_seq_media_colony_mppr", allocationSize = 1)
public class MediaColonyMapperMaster {
	

	@Id
	@Column(name = "media_colony_mppr_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_seq_media_colony_mppr")
	private int mediaColonyMpprId;
	
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
	
	@Column(name = "media_id")
	private Integer mediaId;
	
	@Column(name = "colony_id")
	private Integer colonyId;
	
	@Column(name = "org_id")
	private Integer orgId;
	
	@Column(name = "is_deleted")
	private Character isDeleted;

	public int getMediaColonyMpprId() {
		return mediaColonyMpprId;
	}

	public void setMediaColonyMpprId(int mediaColonyMpprId) {
		this.mediaColonyMpprId = mediaColonyMpprId;
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

	public Integer getMediaId() {
		return mediaId;
	}

	public void setMediaId(Integer mediaId) {
		this.mediaId = mediaId;
	}

	public Integer getColonyId() {
		return colonyId;
	}

	public void setColonyId(Integer colonyId) {
		this.colonyId = colonyId;
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
