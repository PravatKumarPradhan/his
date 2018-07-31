package com.param.adt.master.global.model;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
@NamedQueries({
	/*@NamedQuery(name = "GET_ZONE_BY_UNIT_ID", 
			query="SELECT zoneMaster.zoneMasterId as zoneMasterId, zoneMaster.zoneDesc as zoneDesc"
					+ " from UnitMaster unitMaster"
					+ " INNER JOIN unitMaster.zoneMaster as zoneMaster"
					+ " WHERE unitMaster.unitId =:unitId AND zoneMaster.status ='A'"),*/
	
	@NamedQuery(name = "GET_ZONE_MASTER_LIST",
				query = "SELECT		zoneMst.zoneMasterId as zoneMasterId, zoneMst.zoneDesc as zoneDesc,"
						+ "			zoneMst.status as status, zoneMst.createdBy as createdBy, to_char(zoneMst.createdDate,'DD-MM-YYYY')as createdDate "
						+ "FROM		ZoneMaster zoneMst "
						+ "ORDER BY zoneMst.zoneMasterId"),				
})
@Entity
@Table(name = "m_zone_master", schema = "adt")
public class ZoneMaster {
	@Id
	@Column(name = "zone_master_id")
	private int zoneMasterId;
	
	@Column(name = "zone_desc")
	private String zoneDesc;

	@Column(name = "status")
	private char status;
	
	@Column(name = "created_by")
	private int createdBy;
	
	@Column(name = "created_date")
	private Date createdDate;

	@Column(name="updated_by")
	private int updatedBy;
	
	@Column(name="updated_date")
	private Date updatedDate;
	
	
	 
	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public int getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public int getZoneMasterId() {
		return zoneMasterId;
	}

	public void setZoneMasterId(int zoneMasterId) {
		this.zoneMasterId = zoneMasterId;
	}

	public String getZoneDesc() {
		return zoneDesc;
	}

	public void setZoneDesc(String zoneDesc) {
		this.zoneDesc = zoneDesc;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = (status == '\u0000') ? 'I' : status;
	}

	public int getCreatdBy() {
		return createdBy;
	}

	public void setCreatdBy(int creatdBy) {
		this.createdBy = creatdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

}
