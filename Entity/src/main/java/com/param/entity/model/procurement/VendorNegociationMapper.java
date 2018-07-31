package com.param.entity.model.procurement;

import java.io.Serializable;
import javax.persistence.*;

import com.param.entity.model.base.BaseEntity;

import java.sql.Timestamp;

/**
 * The persistent class for the t_vendor_negociation_mapper database table.
 * 
 */
@Entity
@Table(name="t_vendor_negociation_mapper", schema = "procurement")
public class VendorNegociationMapper extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	private double cop;

	private String remarks;

	@Column(name="status_id")
	private Integer statusId;

	@Column(name="vendor_remarks")
	private String vendorRemarks;
	
	@Column(name="vendor_expected_payment")
	private String vendorExpectedPayment;

	@Column(name="vendor_support_terms")
	private String vendorSupportTerms;

	@Column(name="vendor_technical_specification")
	private String vendorTechnicalSpecification;

	@Column(name="vendor_warrenty_period")
	private String vendorWarrentyPeriod;

	//bi-directional many-to-one association to TVendorNegociationDetail
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="vendor_negociation_detail_id")
	private VendorNegociationDetail vendorNegociationDetail;

	public VendorNegociationMapper() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VendorNegociationMapper(double cop, String remarks, Integer statusId, String vendorRemarks,
			String vendorExpectedPayment, String vendorSupportTerms, String vendorTechnicalSpecification,
			String vendorWarrentyPeriod) {
		super();
		this.cop = cop;
		this.remarks = remarks;
		this.statusId = statusId;
		this.vendorRemarks = vendorRemarks;
		this.vendorExpectedPayment = vendorExpectedPayment;
		this.vendorSupportTerms = vendorSupportTerms;
		this.vendorTechnicalSpecification = vendorTechnicalSpecification;
		this.vendorWarrentyPeriod = vendorWarrentyPeriod;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public double getCop() {
		return cop;
	}

	public void setCop(double cop) {
		this.cop = cop;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	public String getVendorRemarks() {
		return vendorRemarks;
	}

	public void setVendorRemarks(String vendorRemarks) {
		this.vendorRemarks = vendorRemarks;
	}

	public String getVendorExpectedPayment() {
		return vendorExpectedPayment;
	}

	public void setVendorExpectedPayment(String vendorExpectedPayment) {
		this.vendorExpectedPayment = vendorExpectedPayment;
	}

	public String getVendorSupportTerms() {
		return vendorSupportTerms;
	}

	public void setVendorSupportTerms(String vendorSupportTerms) {
		this.vendorSupportTerms = vendorSupportTerms;
	}

	public String getVendorTechnicalSpecification() {
		return vendorTechnicalSpecification;
	}

	public void setVendorTechnicalSpecification(String vendorTechnicalSpecification) {
		this.vendorTechnicalSpecification = vendorTechnicalSpecification;
	}

	public String getVendorWarrentyPeriod() {
		return vendorWarrentyPeriod;
	}

	public void setVendorWarrentyPeriod(String vendorWarrentyPeriod) {
		this.vendorWarrentyPeriod = vendorWarrentyPeriod;
	}

	public VendorNegociationDetail getVendorNegociationDetail() {
		return vendorNegociationDetail;
	}

	public void setVendorNegociationDetail(VendorNegociationDetail vendorNegociationDetail) {
		this.vendorNegociationDetail = vendorNegociationDetail;
	}
}