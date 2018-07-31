package com.param.entity.lis.unit;


import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.param.entity.lis.common.LocalTimeConverter;



@NamedQueries({


		@NamedQuery(name = "GET_RACK_SHELF_MASTER_LIST", query = "SELECT rackShelfMaster.shelfId AS shelfId,"
				+ " rackShelfMaster.orgId as orgId,"
				+ " rackShelfMaster.orgUnitId as orgUnitId,"
				+ " rackShelfMaster.rackId as rackId,"
				+ " rackShelfMaster.shelfCode as shelfCode,"
				+ " rackShelfMaster.shelfName as shelfName,"
	            + " rackShelfMaster.isDeleted as isDeleted,"
				+ " rackShelfMaster.status as status,"
				+ " rackShelfMaster.createdBy as createdBy,"
				+ " rackShelfMaster.createdDate as createdDate,"
				+ " rackShelfMaster.updatedBy as updatedBy,"
				+ " rackShelfMaster.updatedDate as updatedDate "
				+ " FROM RackShelfMaster rackShelfMaster "
				+ " WHERE rackShelfMaster.rackId= :rackId "
		        + " AND rackShelfMaster.isDeleted= 'N'"),
		

		/*@NamedQuery(name = "GET_RACK_SHELF_TYPE_EXIST_OR_NOT", query = "select count(*) FROM "
				+ "RackShelfMaster rackShelfMaster "
				+ " WHERE rackShelfMaster.orgId = :orgId " 
				+ " AND rackShelfMaster.orgUnitId = :orgUnitId " 
				+ " AND rackShelfMaster.templateTypeId = :templateTypeId " )*/

		
		

})


@Entity
@Table(name = "m_rack_shelf_master", schema = "lab")
@SequenceGenerator(name = "m_seq_m_rack_shelf_master",
    sequenceName = "lab.m_seq_m_rack_shelf_master", allocationSize = 1)
public class RackShelfMaster {


  @Id
  @Column(name = "shelf_id")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "m_seq_m_rack_shelf_master")
  private int shelfId;

  @Column(name = "rack_id")
  private Integer rackId;
  
  @Column(name = "org_id")
  private Integer orgId;

  @Column(name = "org_unit_id")
  private Integer orgUnitId;

  @Column(name = "shelf_code")
  private String shelfCode;

  @Column(name = "shelf_name")
  private String shelfName;

  @Column(name = "status")
  private Character status;
  
  @Column(name = "is_deleted")
  private Character isDeleted;
  
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
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "rack_id", insertable = false, nullable = false, updatable = false)
  private RackMaster rackMaster;

public int getShelfId() {
	return shelfId;
}

public void setShelfId(int shelfId) {
	this.shelfId = shelfId;
}

public Integer getOrgId() {
	return orgId;
}

public void setOrgId(Integer orgId) {
	this.orgId = orgId;
}

public Integer getRackId() {
	return rackId;
}

public void setRackId(Integer rackId) {
	this.rackId = rackId;
}

public Integer getOrgUnitId() {
	return orgUnitId;
}

public void setOrgUnitId(Integer orgUnitId) {
	this.orgUnitId = orgUnitId;
}

public String getShelfCode() {
	return shelfCode;
}

public void setShelfCode(String shelfCode) {
	this.shelfCode = shelfCode;
}

public String getShelfName() {
	return shelfName;
}

public void setShelfName(String shelfName) {
	this.shelfName = shelfName;
}

public Character getStatus() {
	return status;
}

public void setStatus(Character status) {
	this.status = status;
}

public Character getIsDeleted() {
	return isDeleted;
}

public void setIsDeleted(Character isDeleted) {
	this.isDeleted = isDeleted;
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

public RackMaster getRackMaster() {
	return rackMaster;
}

public void setRackMaster(RackMaster rackMaster) {
	this.rackMaster = rackMaster;
}


}
	



