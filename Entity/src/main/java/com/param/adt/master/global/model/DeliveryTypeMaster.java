package com.param.adt.master.global.model;

import java.util.Date;

import javax.persistence.Column;
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

@NamedQueries({
	
	@NamedQuery(name="GET_DELIVERY_MASTER_TYPE_LIST", query="SELECT delivery.deliveryTypeId as deliveryTypeId, "
			+ "delivery.deliveryTypeName as deliveryTypeName, "
			+ "delivery.deliveryTypeCode as deliveryTypeCode, "
			+ "delivery.status as status "
			+ "FROM DeliveryTypeMaster delivery "
			+ "WHERE delivery.organizationId=:orgId "
			+ "ORDER BY delivery.deliveryTypeId DESC"),
	
	@NamedQuery(name="GET_ACTIVE_DELIVERY_TYPE_LIST", query="SELECT delivery.deliveryTypeId as deliveryTypeId, "
			+ "delivery.deliveryTypeName as deliveryTypeName "
			+ "FROM DeliveryTypeMaster delivery WHERE delivery.status='A'"),
	
	@NamedQuery(name="GET_DELIVERY_TYPE_LIST_BY_ID", query="SELECT delivery.deliveryTypeId as deliveryTypeId, "
			+ "delivery.deliveryTypeName as deliveryTypeName, "
			+ "delivery.deliveryTypeCode as deliveryTypeCode, "
			+ "delivery.status as status "
			+ "FROM DeliveryTypeMaster delivery "
			+ "WHERE delivery.deliveryTypeId=:deliveryId"),
	
	@NamedQuery(name="GET_DELIVERY_TYPE_LIST_BY_NAME", query="SELECT delivery.deliveryTypeId as deliveryTypeId, "
			+ "delivery.deliveryTypeName as deliveryTypeName "
			+ "FROM DeliveryTypeMaster as delivery "
			+ "WHERE delivery.deliveryTypeName=:deliveryName OR LOWER(delivery.deliveryTypeName)=:deliveryName"),
	
	@NamedQuery(name="GET_DELIVERY_TYPE_LIST_BY_NAME_NOT_ID", query="SELECT delivery.deliveryTypeId as deliveryTypeId, "
			+ "delivery.deliveryTypeName as deliveryTypeName "
			+ "FROM DeliveryTypeMaster as delivery "
			+ "WHERE (delivery.deliveryTypeName=:deliveryName OR LOWER(delivery.deliveryTypeName)=:deliveryName) "
			+ "AND delivery.deliveryTypeId=:deliveryId")

})

@Entity
@Table(name = "m_delivery_type_master", schema = "adt")
@SequenceGenerator(name="delivery_type_master_seq", sequenceName="adt.delivery_type_master_seq", allocationSize=1)
public class DeliveryTypeMaster 
{
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="delivery_type_master_seq")
	@Column(name="delivery_type_id")
	private int deliveryTypeId;
	
	@Column(name="delivery_type_name")
	private String deliveryTypeName;
	
	@Column(name="delivery_type_code")
	private String deliveryTypeCode;

	@Column(name="status")
	private char status;
	
	@Column(name="created_by")
	private int createdBy;
	
	@Column(name="created_date")
	private Date createdDate;
	
	@Column(name="updated_by")
	private int updatedBy;
	
	@Column(name="updated_date")
	private Date updatedDate;
	
	@Column(name="organization_id")
	private Integer organizationId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organization_id", insertable = false, updatable = false)
	private OrganizationMaster organizationMaster;
	
	

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public OrganizationMaster getOrganizationMaster() {
		return organizationMaster;
	}

	public void setOrganizationMaster(OrganizationMaster organizationMaster) {
		this.organizationMaster = organizationMaster;
	}

	public int getDeliveryTypeId() {
		return deliveryTypeId;
	}

	public void setDeliveryTypeId(int deliveryTypeId) {
		this.deliveryTypeId = deliveryTypeId;
	}

	public String getDeliveryTypeName() {
		return deliveryTypeName;
	}

	public void setDeliveryTypeName(String deliveryTypeName) {
		this.deliveryTypeName = deliveryTypeName;
	}

	public String getDeliveryTypeCode() {
		return deliveryTypeCode;
	}

	public void setDeliveryTypeCode(String deliveryTypeCode) {
		this.deliveryTypeCode = deliveryTypeCode;
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
	
	
}
