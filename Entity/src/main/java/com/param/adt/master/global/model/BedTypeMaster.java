package com.param.adt.master.global.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@NamedQueries({
	
	@NamedQuery(name="GET_BED_TYPE_LIST", query="SELECT bm.bedTypeId as bedTypeId, "
			+ "bm.bedTypeDesc as bedTypeDesc, "
			+ "bm.bedTypeCode as bedTypeCode, "
			+ "bm.status as status "
			+ "FROM BedTypeMaster bm "
			+ "WHERE bm.organizationId=:orgId "
			+ "ORDER BY bm.bedTypeId DESC"),
	
	@NamedQuery(name="GET_BED_TYPE_LIST_BY_ID", query="SELECT bm.bedTypeId as bedTypeId, "
			+ "bm.bedTypeDesc as bedTypeDesc, "
			+ "bm.bedTypeCode as bedTypeCode, "
			+ "bm.status as status "
			+ "FROM BedTypeMaster bm "
			+ "WHERE bm.bedTypeId=:bedTypeId "),
	
	@NamedQuery(name="GET_BED_TYPE_LIST_BY_NAME", query="SELECT bm.bedTypeId as bedTypeId, "
			+ "bm.bedTypeDesc as bedTypeDesc "
			+ "FROM BedTypeMaster bm "
			+ "WHERE bm.bedTypeDesc=:bedTypeDesc OR LOWER(bm.bedTypeDesc)=:bedTypeDesc"),
	
	@NamedQuery(name="GET_BED_TYPE_LIST_BY_NAME_NOT_ID", query="SELECT bm.bedTypeId as bedTypeId, "
			+ "bm.bedTypeDesc as bedTypeDesc "
			+ "FROM BedTypeMaster bm "
			+ "WHERE (bm.bedTypeDesc=:bedTypeDesc OR LOWER(bm.bedTypeDesc)=:bedTypeDesc) "
			+ "AND bm.bedTypeId!=:bedTypeId"),
	
	@NamedQuery(name="GET_ACTIVE_BED_TYPE_LIST", query="SELECT bm.bedTypeId as bedTypeId, "
			+ "bm.bedTypeDesc as bedTypeDesc "
			+ "FROM BedTypeMaster bm "
			+ "WHERE bm.status='A'")
	
	
	
})

@Entity
@Table(name = "m_bed_type_master", schema = "adt")
@SequenceGenerator(name="bed_type_master_seq", sequenceName="adt.bed_type_master_seq", allocationSize=1)
public class BedTypeMaster {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bed_type_master_seq")
	@Column(name = "bed_type_id")
	private int bedTypeId;
	
	@Column(name = "bed_type_code")
	private String bedTypeCode;
	
	@Column(name = "bed_type_desc")
	private String bedTypeDesc;
	
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
	
	@Column(name="organization_id")
	private Integer organizationId;
	
/*	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organization_id", insertable = false, updatable = false)
	private OrganizationMaster organizationMaster;*/
	
	
	
	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}


/*	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bedTypeMaster", cascade = CascadeType.ALL, orphanRemoval = true) 
	private List<RoomMaster> listRoomMaster;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bedTypeMaster", cascade = CascadeType.ALL, orphanRemoval = true) 
	private List<BedMaster> listBedMaster;*/

	

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

	public int getBedTypeId() {
		return bedTypeId;
	}

	public void setBedTypeId(int bedTypeId) {
		this.bedTypeId = bedTypeId;
	}



	public String getBedTypeCode() {
		return bedTypeCode;
	}

	public void setBedTypeCode(String bedTypeCode) {
		this.bedTypeCode = bedTypeCode;
	}

	public String getBedTypeDesc() {
		return bedTypeDesc;
	}

	public void setBedTypeDesc(String bedTypeDesc) {
		this.bedTypeDesc = bedTypeDesc;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	

}
