package com.param.entity.model.procurement;

import java.io.Serializable;
import javax.persistence.*;

import com.param.entity.model.base.BaseEntity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the t_vendor_negociation_detail database table.
 * 
 */
@Entity
@Table(name="t_vendor_negociation_detail", schema = "procurement")
public class VendorNegociationDetail extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "qr_detail_id")
	private QuotationRequestDetail quotationRequestDetail;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "qv_detail_id")
	private QuotationVendorDetail quotationVendorDetail;

	//bi-directional many-to-one association to TVendorNegociation
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="vendor_negociation_id")
	private VendorNegociation vendorNegociation;

	//bi-directional many-to-one association to TVendorNegociationMapper
	@OneToMany(fetch = FetchType.LAZY, mappedBy="vendorNegociationDetail", cascade = CascadeType.ALL)
	private List<VendorNegociationMapper> VendorNegociationMappersList;

	public VendorNegociationDetail() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VendorNegociationDetail(QuotationRequestDetail quotationRequestDetail,
			QuotationVendorDetail quotationVendorDetail) {
		super();
		this.quotationRequestDetail = quotationRequestDetail;
		this.quotationVendorDetail = quotationVendorDetail;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public QuotationRequestDetail getQuotationRequestDetail() {
		return quotationRequestDetail;
	}

	public void setQuotationRequestDetail(QuotationRequestDetail quotationRequestDetail) {
		this.quotationRequestDetail = quotationRequestDetail;
	}

	public QuotationVendorDetail getQuotationVendorDetail() {
		return quotationVendorDetail;
	}

	public void setQuotationVendorDetail(QuotationVendorDetail quotationVendorDetail) {
		this.quotationVendorDetail = quotationVendorDetail;
	}

	public VendorNegociation getVendorNegociation() {
		return vendorNegociation;
	}

	public void setVendorNegociation(VendorNegociation vendorNegociation) {
		this.vendorNegociation = vendorNegociation;
	}

	public List<VendorNegociationMapper> getVendorNegociationMappersList() {
		return VendorNegociationMappersList;
	}

	public void setVendorNegociationMappersList(List<VendorNegociationMapper> vendorNegociationMappersList) {
		VendorNegociationMappersList = vendorNegociationMappersList;
	}
	
	
	public VendorNegociationMapper addVendorNegociationMapperList(VendorNegociationMapper vendorNegociationMapper) {
		if (getVendorNegociationMappersList() == null)
			this.VendorNegociationMappersList = new ArrayList<VendorNegociationMapper>();

		getVendorNegociationMappersList().add(vendorNegociationMapper);
		vendorNegociationMapper.setVendorNegociationDetail(this);
		return vendorNegociationMapper;
	}
	
}