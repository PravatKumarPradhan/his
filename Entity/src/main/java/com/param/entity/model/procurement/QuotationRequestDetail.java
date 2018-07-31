package com.param.entity.model.procurement;

import java.io.Serializable;
import javax.persistence.*;

import com.param.entity.model.base.BaseEntity;
import com.param.entity.model.master.Item;
import com.param.entity.model.master.RejectReason;
import com.param.entity.model.master.Status;
import com.param.entity.model.master.UomType;
import com.param.entity.model.master.UomUnit;
import com.param.entity.model.master.User;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the t_quotation_request_detail database table.
 * 
 */
@Entity
@Table(name="t_quotation_request_detail",schema="procurement")
public class QuotationRequestDetail extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "item_id", nullable = false)
	private Item item;
	
	private Integer quantity;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "status_id")
	private Status status;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="uom_type_id")
	private UomType uomType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="uom_unit_id")
	private UomUnit uomUnit;

	@Column(name="warranty_period")
	private String warrantyPeriod;
	
	@Column(name="expected_payment")
	private String expectedPayment;

	@Column(name="expected_support_term")
	private String expectedSupportTerm;

	@Column(name="expected_technical_specification")
	private String expectedTechnicalSpecification;


	//bi-directional many-to-one association to QuotationRequest
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="qr_id")
	private QuotationRequest quotationRequest;

	//bi-directional many-to-one association to PurchaseRequestDetail
	@OneToMany(fetch = FetchType.LAZY,mappedBy="quotationRequestDetail", cascade = CascadeType.ALL)
	private List<PurchaseRequestDetail> purchaseRequestDetailsList;
	
	//bi-directional many-to-one association to PurchaseRequestDetail
	@OneToMany(fetch = FetchType.LAZY,mappedBy="quotationRequestDetail", cascade = CascadeType.ALL)
	private List<QuotationRequestAccessoriesMapper> quotationRequestAccessoriesMapperList;
	
	//bi-directional many-to-one association to PurchaseRequestDetail
	@OneToMany(fetch = FetchType.LAZY,mappedBy="qrDetail", cascade = CascadeType.ALL)
	private List<QuotationVendorDetail> quotationVendorDetail;
	
	public QuotationRequestDetail() {
		super();
	}
	
	public QuotationRequestDetail(Integer id) {
		this.id = id;
	}
	
	
	public QuotationRequestDetail(Item item, Integer quantity, Status status, UomType uomType, UomUnit uomUnit, String warrantyPeriod,
			String expectedPayment, String expectedSupportTerm, String expectedTechnicalSpecification) {
		super();
		this.item = item;
		this.quantity = quantity;
		this.uomType = uomType;
		this.uomUnit = uomUnit;
		this.status = status;
		this.warrantyPeriod = warrantyPeriod;
		this.expectedPayment = expectedPayment;
		this.expectedSupportTerm = expectedSupportTerm;
		this.expectedTechnicalSpecification = expectedTechnicalSpecification;
	}


	public void updateQuotationRequestDetail( Integer quantity, UomType uomType, UomUnit uomUnit,
			String warrantyPeriod, String expectedPayment, String expectedSupportTerm,
			String expectedTechnicalSpecification) {
		this.quantity = quantity;
		this.uomType = uomType;
		this.uomUnit = uomUnit;
		this.warrantyPeriod = warrantyPeriod;
		this.expectedPayment = expectedPayment;
		this.expectedSupportTerm = expectedSupportTerm;
		this.expectedTechnicalSpecification = expectedTechnicalSpecification;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getWarrantyPeriod() {
		return warrantyPeriod;
	}

	public void setWarrantyPeriod(String warrantyPeriod) {
		this.warrantyPeriod = warrantyPeriod;
	}

	public String getExpectedPayment() {
		return expectedPayment;
	}

	public void setExpectedPayment(String expectedPayment) {
		this.expectedPayment = expectedPayment;
	}

	public String getExpectedSupportTerm() {
		return expectedSupportTerm;
	}

	public void setExpectedSupportTerm(String expectedSupportTerm) {
		this.expectedSupportTerm = expectedSupportTerm;
	}

	public String getExpectedTechnicalSpecification() {
		return expectedTechnicalSpecification;
	}

	public void setExpectedTechnicalSpecification(String expectedTechnicalSpecification) {
		this.expectedTechnicalSpecification = expectedTechnicalSpecification;
	}
	
	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
	public UomType getUomType() {
		return uomType;
	}

	public void setUomType(UomType uomType) {
		this.uomType = uomType;
	}

	public UomUnit getUomUnit() {
		return uomUnit;
	}

	public void setUomUnit(UomUnit uomUnit) {
		this.uomUnit = uomUnit;
	}
	
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public QuotationRequest getQuotationRequest() {
		return quotationRequest;
	}

	public void setQuotationRequest(QuotationRequest quotationRequest) {
		this.quotationRequest = quotationRequest;
	}

	public List<PurchaseRequestDetail> getPurchaseRequestDetailsList() {
		return purchaseRequestDetailsList;
	}

	public void setPurchaseRequestDetailsList(List<PurchaseRequestDetail> purchaseRequestDetailsList) {
		this.purchaseRequestDetailsList = purchaseRequestDetailsList;
	}

	public List<QuotationRequestAccessoriesMapper> getQuotationRequestAccessoriesMapperList() {
		return quotationRequestAccessoriesMapperList;
	}

	public void setQuotationRequestAccessoriesMapperList(
			List<QuotationRequestAccessoriesMapper> quotationRequestAccessoriesMapperList) {
		this.quotationRequestAccessoriesMapperList = quotationRequestAccessoriesMapperList;
	}
	
	public List<QuotationVendorDetail> getQuotationVendorDetail() {
		return quotationVendorDetail;
	}

	public void setQuotationVendorDetail(List<QuotationVendorDetail> quotationVendorDetail) {
		this.quotationVendorDetail = quotationVendorDetail;
	}

	public QuotationRequestAccessoriesMapper addQuotationRequestAccessoriesMapperList(QuotationRequestAccessoriesMapper qRAccessoriesMapper) {
		if (getQuotationRequestAccessoriesMapperList() == null)
			this.quotationRequestAccessoriesMapperList = new ArrayList<QuotationRequestAccessoriesMapper>();
		
		getQuotationRequestAccessoriesMapperList().add(qRAccessoriesMapper);
		qRAccessoriesMapper.setQuotationRequestDetail(this);
		
		return qRAccessoriesMapper;
	}
	
	public PurchaseRequestDetail addPurchaseRequestDetailList(PurchaseRequestDetail purchaseRequestDetail) {
		if (getPurchaseRequestDetailsList() == null)
			this.purchaseRequestDetailsList = new ArrayList<PurchaseRequestDetail>();

		getPurchaseRequestDetailsList().add(purchaseRequestDetail);
		purchaseRequestDetail.setQuotationRequestDetail(this);

		return purchaseRequestDetail;
	}
}