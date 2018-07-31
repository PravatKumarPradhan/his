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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.param.service.global.model.MCompanyContractMaster;
import com.param.service.global.model.MPackageMaster;

@NamedQueries({
					
	@NamedQuery(name="GET_TARIFF_MASTER_LIST_BY_NAME",query="SELECT tariff.tariffId as tariffId "
			+ "FROM TariffMaster tariff "
			+ "WHERE tariff.organizationId=:orgId "
			+ "AND tariff.unitId=:unitId "
			+ "AND tariff.tariffDesc=:tariffDesc "),
	
	@NamedQuery(name="GET_TARIFF_MASTER_LIST",query="SELECT tariff.tariffDesc as tariffDesc,"
			+ "tariff.tariffId as tariffId, "
			+ "tariff.tariffCode as tariffCode, "
			+ "to_char(tariff.validFrom,'dd/MM/yyyy') as validFrom, "
			+ "to_char(tariff.validTo,'dd/MM/yyyy') as validTo, "
			+ "tariff.status as status "
			+ "FROM TariffMaster tariff "
			+ "WHERE tariff.organizationId=:orgId "
			+ "AND tariff.unitId=:unitId "),
	
	@NamedQuery(name="GET_PAYMENT_ENTITLEMENT_LIST_BY_TARIFF_ID",query="SELECT tariffPayment.tariffPaymentEntitlementMapperId as tariffPaymentEntitlementMapperId, "
			+ "tariffPayment.tariffId as tariffId, "
			+ "tariffPayment.paymentEntitlementId as applicablePaymentEntitlementId, "
			+ "paymentEntitlement.paymentEntitlementDesc as paymentEntitlementDesc, "
			+ "tariffPayment.status as status "
			+ "FROM TariffPaymentEntitlementMapper tariffPayment "
			+ "INNER JOIN tariffPayment.paymentEntitlementMaster paymentEntitlement "
			+ "WHERE tariffPayment.tariffId=:tariffId "
			+ "AND tariffPayment.status='A' "),
	
	@NamedQuery(name="GET_PAYMENT_ENTITLEMENT_LIST_BY_TARIFF_ID_LIST",query="SELECT tariffPayment.tariffPaymentEntitlementMapperId as tariffPaymentEntitlementMapperId, "
			+ "tariffPayment.tariffId as tariffId, "
			+ "tariffPayment.paymentEntitlementId as applicablePaymentEntitlementId, "
			+ "paymentEntitlement.paymentEntitlementDesc as paymentEntitlementDesc, "
			+ "tariffPayment.status as status "
			+ "FROM TariffPaymentEntitlementMapper tariffPayment "
			+ "INNER JOIN tariffPayment.paymentEntitlementMaster paymentEntitlement "
			+ "WHERE tariffPayment.tariffId IN (:tariffIdList) "
			+ "AND tariffPayment.status='A' "),
	
	@NamedQuery(name="GET_TARIFF_MASTER_LIST_BY_ID",query="SELECT tariff.tariffDesc as tariffDesc,"
			+ "tariff.tariffId as tariffId, "
			+ "tariff.tariffCode as tariffCode, "
			+ "to_char(tariff.validFrom,'MM/dd/yyyy') as validFrom, "
			+ "to_char(tariff.validTo,'MM/dd/yyyy') as validTo, "
			+ "tariff.status as status "
			+ "FROM TariffMaster tariff "
			+ "WHERE tariff.tariffId=:tariffId "),
	
	@NamedQuery(name="GET_TARIFF_MASTER_LIST_BY_NAME_NOT_ID",query="SELECT tariff.tariffDesc as tariffDesc,"
			+ "tariff.tariffId as tariffId, "
			+ "tariff.tariffCode as tariffCode, "
			+ "to_char(tariff.validFrom,'MM/dd/yyyy') as validFrom, "
			+ "to_char(tariff.validTo,'MM/dd/yyyy') as validTo, "
			+ "tariff.status as status "
			+ "FROM TariffMaster tariff "
			+ "WHERE tariff.tariffId!=:tariffId "
			+ "AND tariff.tariffDesc=:tariffDesc "
			+ "AND tariff.organizationId=:orgId "
			+ "AND tariff.unitId=:unitId "),
	
	@NamedQuery(name="GET_PAYMENT_ENTITLEMENT_LIST_BY_MAPPER_ID",query="SELECT tariffPayment.tariffPaymentEntitlementMapperId as tariffPaymentEntitlementMapperId "
			+ "FROM TariffPaymentEntitlementMapper tariffPayment "
			+ "WHERE tariffPayment.tariffPaymentEntitlementMapperId=:tariffPaymentEntitlementMapperId "),
	
	@NamedQuery(name="GET_ACTIVE_TARIFF_MASTER_LIST",query="SELECT tariff.tariffId as tariffId, "
			+ "tariff.tariffDesc as tariffDesc "
			+ "FROM TariffMaster tariff "
			+ "WHERE tariff.organizationId=:orgId "
			+ "AND tariff.unitId=:unitId "
			+ "AND tariff.status='A' ")
	
	
}) 


@Entity
@Table(name="m_tariff_master",schema="service")
@SequenceGenerator(name="tariff_master_seq",sequenceName="service.tariff_master_seq",allocationSize=1)
public class TariffMaster {

	@Id
	@Column(name="tariff_id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="tariff_master_seq")
	private Integer tariffId;
	
	@Column(name="organization_id")
	private Integer organizationId;
	
	@Column(name="unit_id")
	private Integer unitId;
	
	@Column(name="tariff_code")
	private String tariffCode;
	
	@Column(name="tariff_desc")
	private String tariffDesc;
	
	@Column(name="valid_from")
	private Date validFrom;
	
	@Column(name="valid_to")
	private Date validTo;
	
	@Column(name="created_date")
	private Date createdDate;
	
	@Column(name="created_by")
	private Integer createdBy;
	
	@Column(name="updated_date")
	private Date updatedDate;
	
	@Column(name="updated_by")
	private Integer updatedBy;
	
	@Column(name="status")
	private Character status;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tariffMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<TariffPaymentEntitlementMapper> tariffPaymentEntitlementMappersList;
	
	@OneToMany(fetch = FetchType.LAZY , cascade = CascadeType.ALL , mappedBy = "tariffMaster")
	private List<MCompanyContractMaster> listMCompanyContractMaster;
	
	@OneToMany(fetch = FetchType.LAZY , cascade = CascadeType.ALL , mappedBy = "tariffMaster")
	private List<MPackageMaster> listMPackageMaster;

	public List<TariffPaymentEntitlementMapper> getTariffPaymentEntitlementMappersList() {
		return tariffPaymentEntitlementMappersList;
	}

	public void setTariffPaymentEntitlementMappersList(
			List<TariffPaymentEntitlementMapper> tariffPaymentEntitlementMappersList) {
		this.tariffPaymentEntitlementMappersList = tariffPaymentEntitlementMappersList;
	}

	public List<MPackageMaster> getListMPackageMaster() {
		return listMPackageMaster;
	}

	public void setListMPackageMaster(List<MPackageMaster> listMPackageMaster) {
		this.listMPackageMaster = listMPackageMaster;
	}

	public List<MCompanyContractMaster> getListMCompanyContractMaster() {
		return listMCompanyContractMaster;
	}

	public void setListMCompanyContractMaster(List<MCompanyContractMaster> listMCompanyContractMaster) {
		this.listMCompanyContractMaster = listMCompanyContractMaster;
	}
	
	public Integer getTariffId() {
		return tariffId;
	}

	public void setTariffId(Integer tariffId) {
		this.tariffId = tariffId;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public String getTariffCode() {
		return tariffCode;
	}

	public void setTariffCode(String tariffCode) {
		this.tariffCode = tariffCode;
	}

	public String getTariffDesc() {
		return tariffDesc;
	}

	public void setTariffDesc(String tariffDesc) {
		this.tariffDesc = tariffDesc;
	}

	public Date getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}

	public Date getValidTo() {
		return validTo;
	}

	public void setValidTo(Date validTo) {
		this.validTo = validTo;
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

	public Character getStatus() {
		return status;
	}

	public void setStatus(Character status) {
		this.status = status;
	}
	
}


/*
 * "applicablePaymentEntitlementIdList":  
 * "createdBy" : 1 
 * "createdDate" : "08-05-2018 13:51:57"
 * "organizationId" : 1 
 * "status" : "A" 
 * "tariffCode" : "test" 
 * "tariffDesc" : "yesy"
 * "unitId" : 1 
 * "updatedBy" : 1 
 * "updatedDate" : "08-05-2018 13:51:57" 
 * "validFrom" : "06-05-2018 00:00:00" 
 * "validTo" : "08-05-2018 00:00:00"
 */