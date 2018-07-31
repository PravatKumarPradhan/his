package com.param.global.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.param.billing.global.transaction.model.TariffPaymentEntitlementMapper;
import com.param.service.global.model.MCompanyContractMaster;
import com.param.service.global.model.MPackageMaster;

@NamedQueries({
	
	@NamedQuery(name="GET_ACTIVE_PAYMENT_ENTITLEMENT_LIST", query="SELECT pem.paymentEntitlementId as paymentEntitlementId, "
			+ "pem.paymentEntitlementDesc as paymentEntitlementDesc "
			+ "FROM PaymentEntitlementMaster as pem "
			+ "WHERE pem.status='A'"),
	
	@NamedQuery(name="GET_PAYMENT_ENTITLEMENT_LIST_BY_ORG", 
		query="SELECT pem.paymentEntitlementId as id, "
			+ "pem.paymentEntitlementDesc as name "
			+ "FROM PaymentEntitlementMaster as pem "
			+ "WHERE pem.status='A' "
			+ "AND pem.organizationId =:orgId ")
})

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@Table(name = "m_payment_entitlement_master", schema = "public")
@SequenceGenerator(name="payment_entitlement_master_seq" , sequenceName ="public.payment_entitlement_master_seq", allocationSize = 1)
public class PaymentEntitlementMaster 
{
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="payment_entitlement_master_seq")
	@Column(name="payment_entitlement_id")
	private int paymentEntitlementId;

	@Column(name="payment_entitlement_desc")
	private String paymentEntitlementDesc;

	@Column(name="payment_entitlement_code")
	private String paymentEntitlementCode;

	@Column(name="created_by")
	private int createdBy;
	
	@Column(name="created_date")
	private String createdDate;

	@Column(name="status")
	private char status;
	
	@Column(name="updated_by")
	private int updatedBy;
	
	@Column(name="updated_date")
	private Date updatedDate;
	
	@Column(name="organization_id")
	private Integer organizationId;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "paymentEntitlementMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<TariffPaymentEntitlementMapper> tariffPaymentEntitlementMappersList;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "paymentEntitlementMaster", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<MPackageMaster> listMPackageMaster;
	
	@OneToMany(fetch = FetchType.LAZY , cascade = CascadeType.ALL , mappedBy = "associatedCompanyMaster")
	private List<MCompanyContractMaster> listMCompanyContractMaster;

	public List<MCompanyContractMaster> getListMCompanyContractMaster() {
		return listMCompanyContractMaster;
	}

	public void setListMCompanyContractMaster(List<MCompanyContractMaster> listMCompanyContractMaster) {
		this.listMCompanyContractMaster = listMCompanyContractMaster;
	}
	
	public List<MPackageMaster> getListMPackageMaster() {
		return listMPackageMaster;
	}

	public void setListMPackageMaster(List<MPackageMaster> listMPackageMaster) {
		this.listMPackageMaster = listMPackageMaster;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
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

	public int getPaymentEntitlementId() {
		return paymentEntitlementId;
	}

	public void setPaymentEntitlementId(int paymentEntitlementId) {
		this.paymentEntitlementId = paymentEntitlementId;
	}

	public String getPaymentEntitlementDesc() {
		return paymentEntitlementDesc;
	}

	public void setPaymentEntitlementDesc(String paymentEntitlementDesc) {
		this.paymentEntitlementDesc = paymentEntitlementDesc;
	}

	public String getPaymentEntitlementCode() {
		return paymentEntitlementCode;
	}

	public void setPaymentEntitlementCode(String paymentEntitlementCode) {
		this.paymentEntitlementCode = paymentEntitlementCode;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}
	
	
}
