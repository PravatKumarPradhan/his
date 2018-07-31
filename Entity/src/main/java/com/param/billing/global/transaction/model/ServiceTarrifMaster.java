package com.param.billing.global.transaction.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@NamedQueries({
	@NamedQuery(name="GET_VARIABLE_PRICING_FACTOR_BY_VISITTYPE",
			query="SELECT  servTarrifMst.serviceTarrifMasterId as serviceTarrifMasterId, servTarrifMst.serviceMasterId as serviceMasterId, servTarrifMst.fromDate as fromDate, servTarrifMst.toDate as toDate,"
					+ " servTarrifMst.visitTypeId as visitTypeId, servTarrifMst.unitId as unitId, servTarrifMst.organisationId as organisationId, servTarrifMst.status as status,"
					+ " servTarrifMst.createdDate as createdDate, servTarrifMst.createdBy as createdBy, servTarrifMst.updatedDate as updatedDate, servTarrifMst.updatedBy as updatedBy "
					+ " FROM ServiceTarrifMaster servTarrifMst"
					/*+ " LEFT JOIN servTarrifMst.listServiceTarrifBedtypeManager ServTarrifBedType"
					+ " LEFT JOIN servTarrifMst.listServiceTarrifPatinettypeManager servTarrifPatientType"
					+ " LEFT JOIN servTarrifMst.listserviceTarrifPaymentEntitlementManager servTarrifPaymentEnt"*/
					+ " WHERE servTarrifMst.visitTypeId = :visitTypeId"
					+ " AND servTarrifMst.unitId = :unitId"
					+ " AND servTarrifMst.organisationId = :organisationId"
					+ " AND servTarrifMst.status = 'A'"),
					
	@NamedQuery(name="UPDATE_VARIABLE_PRICING_FACTOR_STATUS_BY_VISITTYPE",
			query="UPDATE ServiceTarrifMaster servTarrifMst "
					+ " SET servTarrifMst.status = 'I'"
					+ " WHERE servTarrifMst.organisationId =:organisationId"
					+ " AND servTarrifMst.unitId =:unitId"
					+ " AND servTarrifMst.visitTypeId =:visitTypeId")
})

@NamedNativeQueries({
	/** Get Variable pricing factor from MATERIALIZED view
	 * @author SantoshK.
	 */
	@NamedNativeQuery(name="GET_VIEW_VARIABLE_FACTOR_FOR_ALL_TYPES",
			query="SELECT bed_type_id as \"bedTypeId\", multiplication_factor as \"multiplicationFactor\", type_diff as \"typeDiff\" FROM m_view_bed_variable_factor_nikhil "
					+ " WHERE unit_id= :unitId AND organisation_id= :organisationId AND visit_type_id= :visitTypeId")
})
//m_view_bed_variable_factor


@Entity
@Table(name="m_service_tarrif_master",schema="service")
@SequenceGenerator(name = "service_tarrif_master_seq", sequenceName = "service.service_tarrif_master_seq", allocationSize = 1)
public class ServiceTarrifMaster {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "service_tarrif_master_seq")
	@Column(name = "service_tarrif_master_id")
	private int serviceTarrifMasterId;
	  
	@Column(name = "service_master_id")
	private Integer serviceMasterId;
	
	  
	@Column(name = "from_date")
	private Date fromDate;
	  
	@Column(name = "to_date")
	private Date toDate;
	  
	@Column(name = "visit_type_id")
	private Integer visitTypeId;
	  
	@Column(name = "unit_id")
	private Integer unitId;
	
	@Column(name = "organisation_id")
	private Integer organisationId;
	
	@Column(name = "status")
	private char status;
	
	@Column(name = "created_date")
	private Date createdDate;
	
	@Column(name = "created_by")
	private Integer createdBy;
	
	@Column(name = "updated_date")
	private Date updatedDate;
	
	@Column(name = "updated_by")
	private Integer updatedBy;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="serviceTarrifMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ServiceTarrifBedtypeManager> listServiceTarrifBedtypeManager;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="serviceTarrifMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ServiceTarrifPatientCategoryManager> listServiceTarrifPatinettypeManager;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="serviceTarrifMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ServiceTarrifPaymentEntitlementManager> listserviceTarrifPaymentEntitlementManager;

	public int getServiceTarrifMasterId() {
		return serviceTarrifMasterId;
	}

	public void setServiceTarrifMasterId(int serviceTarrifMasterId) {
		this.serviceTarrifMasterId = serviceTarrifMasterId;
	}

	public Integer getServiceMasterId() {
		return serviceMasterId;
	}

	public void setServiceMasterId(Integer serviceMasterId) {
		this.serviceMasterId = serviceMasterId;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}


	public Integer getVisitTypeId() {
		return visitTypeId;
	}

	public void setVisitTypeId(Integer visitTypeId) {
		this.visitTypeId = visitTypeId;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}


	public Integer getOrganisationId() {
		return organisationId;
	}

	public void setOrganisationId(Integer organisationId) {
		this.organisationId = organisationId;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public List<ServiceTarrifBedtypeManager> getListServiceTarrifBedtypeManager() {
		return listServiceTarrifBedtypeManager;
	}

	public void setListServiceTarrifBedtypeManager(
			List<ServiceTarrifBedtypeManager> listServiceTarrifBedtypeManager) {
		this.listServiceTarrifBedtypeManager = listServiceTarrifBedtypeManager;
	}

	public List<ServiceTarrifPatientCategoryManager> getListServiceTarrifPatinettypeManager() {
		return listServiceTarrifPatinettypeManager;
	}

	public void setListServiceTarrifPatinettypeManager(
			List<ServiceTarrifPatientCategoryManager> listServiceTarrifPatinettypeManager) {
		this.listServiceTarrifPatinettypeManager = listServiceTarrifPatinettypeManager;
	}

	public List<ServiceTarrifPaymentEntitlementManager> getListserviceTarrifPaymentEntitlementManager() {
		return listserviceTarrifPaymentEntitlementManager;
	}

	public void setListserviceTarrifPaymentEntitlementManager(
			List<ServiceTarrifPaymentEntitlementManager> listserviceTarrifPaymentEntitlementManager) {
		this.listserviceTarrifPaymentEntitlementManager = listserviceTarrifPaymentEntitlementManager;
	}
	
	
	
}
