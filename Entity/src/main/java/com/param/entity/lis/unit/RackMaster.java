package com.param.entity.lis.unit;

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

import com.param.global.model.GenderMaster;
import com.param.entity.lis.common.LocalTimeConverter;



@NamedQueries({



    /*@NamedQuery(name = "GET_RACK_MASTER_BY_ID",
        query = "SELECT rackMaster.rackId AS rackId,"
            + " rackMaster.rackCode as rackCode,"
            + " rackMaster.rackName as rackName,"
            + " FROM RackMaster rackMaster "
            + " WHERE rackMaster.orgId = :orgId "
            + " AND rackMaster.orgUnitId= :orgUnitId "
        	+ " AND rackMaster.status= 'A' " ),
*/
		@NamedQuery(name = "GET_RACK_MASTER_LIST", query = "SELECT rackMaster.rackId AS rackId,"
				+ " rackMaster.orgId as orgId,"
				+ " rackMaster.orgUnitId as orgUnitId,"
				+ " rackMaster.rackCode as rackCode,"
				+ " rackMaster.rackName as rackName,"
				+ " rackMaster.status as status,"
				+ " rackMaster.createdBy as createdBy,"
				+ " rackMaster.createdDate as createdDate,"
				+ " rackMaster.updatedBy as updatedBy,"
				+ " rackMaster.updatedDate as updatedDate "
				+ " FROM RackMaster rackMaster "
				+ " WHERE rackMaster.orgId = :orgId "
				+ " AND rackMaster.orgUnitId= :orgUnitId "),
		
		
		@NamedQuery(name = "GET_RACK_MASTER_BY_RACK_CODE", query = "SELECT rackMaster.rackId AS rackId,"
				+ " rackMaster.orgId as orgId,"
				+ " rackMaster.orgUnitId as orgUnitId,"
				+ " rackMaster.rackCode as rackCode,"
				+ " rackMaster.rackName as rackName,"
				+ " rackMaster.status as status,"
				+ " rackMaster.createdBy as createdBy,"
				+ " rackMaster.createdDate as createdDate,"
				+ " rackMaster.updatedBy as updatedBy,"
				+ " rackMaster.updatedDate as updatedDate "
				+ " FROM RackMaster rackMaster "
				+ " WHERE rackMaster.orgId = :orgId "
				+ " AND rackMaster.orgUnitId= :orgUnitId "
				+ " AND lower(rackMaster.rackCode) = lower(:rackCode)"),
		
		
/*		
		@NamedQuery(name = "GET_RACK_MASTER_BY_RACK_TYPE", query = "SELECT DISTINCT(rackMaster.rackId) AS rackId,"
            + " rackMaster.templateCode as description,"
            + " rackMaster.htmlData as html,"
            + " rackMaster.templateDesc as title "
            + " FROM LabTemplateMaster rackMaster "
            + " INNER JOIN rackMaster.ListSpecialtyTemplateMapper lstSpecialtyTemplateMapper "
            + " WHERE rackMaster.orgId = :orgId "
            + " AND rackMaster.orgUnitId = :orgUnitId "
            + " AND lstSpecialtyTemplateMapper.status = 'A' "
            + " AND lstSpecialtyTemplateMapper.isDeleted = 'N' "
            + " AND lstSpecialtyTemplateMapper.templateTypeId = :templateTypeId")*/

		

})



@NamedNativeQueries(
{ @NamedNativeQuery(name = "GET_TOTAL_RACK_MASTER_RECORD", query = "select count(*) FROM lab.m_rack_master rack_master where "
		+ "rack_master.org_id = :orgId AND "+ "rack_master.org_unit_id = :orgUnitId"),
	
	@NamedNativeQuery(name = "UPDATE_BY_RANK_SHELF_ID", query = "UPDATE lab.m_rack_shelf_master rack_shelf_master "
			+ "SET is_deleted = 'Y' "
			+ " WHERE rack_shelf_master.rack_id= :rackId"
			+"  And rack_shelf_master.org_id= :orgId"
			+"  And rack_shelf_master.org_unit_id= :orgUnitId"),
	
	@NamedNativeQuery(name = "UPDATE_STATUS_RANK_SHELF_MASTER", query = "UPDATE lab.m_rack_shelf_master rack_shelf_master "
			+ "SET status =:rackStatus"
			+ " WHERE rack_shelf_master.rack_id=:rackId"
			+ " AND is_deleted = 'N' ")





})

@Entity
@Table(name = "m_rack_master", schema = "lab")
@SequenceGenerator(name = "m_seq_rack_master",
    sequenceName = "lab.m_seq_rack_master", allocationSize = 1)
public class RackMaster {
	


  @Id
  @Column(name = "rack_id")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "m_seq_rack_master")
  private int rackId;

  @Column(name = "org_id")
  private Integer orgId;

  @Column(name = "org_unit_id")
  private Integer orgUnitId;

  @Column(name = "rack_code")
  private String rackCode;

  @Column(name = "rack_name")
  private String rackName;

  @Column(name = "status")
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
  
  @OneToMany(fetch = FetchType.LAZY,mappedBy = "rackMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<RackShelfMaster> ListRackShelfMaster;
	

public int getRackId() {
	return rackId;
}

public void setRackId(int rackId) {
	this.rackId = rackId;
}

public Integer getOrgId() {
	return orgId;
}

public void setOrgId(Integer orgId) {
	this.orgId = orgId;
}

public Integer getOrgUnitId() {
	return orgUnitId;
}

public void setOrgUnitId(Integer orgUnitId) {
	this.orgUnitId = orgUnitId;
}

public String getRackCode() {
	return rackCode;
}

public void setRackCode(String rackCode) {
	this.rackCode = rackCode;
}

public String getRackName() {
	return rackName;
}

public void setRackName(String rackName) {
	this.rackName = rackName;
}

public Character getStatus() {
	return status;
}

public void setStatus(Character status) {
	this.status = status;
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

public Integer getUpdatedBy() {
	return updatedBy;
}

public void setUpdatedBy(Integer updatedBy) {
	this.updatedBy = updatedBy;
}

public Long getUpdatedDate() {
	return updatedDate;
}

public void setUpdatedDate(Long updatedDate) {
	this.updatedDate = updatedDate;
}

public List<RackShelfMaster> getListRackShelfMaster() {
	return ListRackShelfMaster;
}

public void setListRackShelfMaster(List<RackShelfMaster> listRackShelfMaster) {
	ListRackShelfMaster = listRackShelfMaster;
}


}
	



