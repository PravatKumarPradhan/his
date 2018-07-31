package com.param.entity.model.procurement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.param.entity.model.base.BaseEntity;
import com.param.entity.model.master.Status;


/**
 * The persistent class for the t_technical_approval_detail database table.
 * 
 */
@Entity
@Table(name="t_technical_approval_detail", schema = "procurement")
public class TechnicalApprovalDetail extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="quotation_request_detail_id")
	private QuotationRequestDetail quotationRequestDetail;
	
	//bi-directional many-to-one association to TTechnicalApproval
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="technical_approval_id")
	private TechnicalApproval technicalApproval;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "status_id")
	private Status status;
	
	//bi-directional many-to-one association to TTechnicalApprovalDetail
	@OneToMany(fetch = FetchType.LAZY, mappedBy="technicalApprovalDetail", cascade = CascadeType.ALL)
	private List<TechnicalApprovalDetailMapper> technicalApprovalDetailMapperList;
	
	public TechnicalApprovalDetail() {
		super();
	}

	public TechnicalApprovalDetail(QuotationRequestDetail quotationRequestDetail, Status status) {
		super();
		this.quotationRequestDetail = quotationRequestDetail;
		this.status = status;
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

	public TechnicalApproval getTechnicalApproval() {
		return technicalApproval;
	}

	public void setTechnicalApproval(TechnicalApproval technicalApproval) {
		this.technicalApproval = technicalApproval;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<TechnicalApprovalDetailMapper> getTechnicalApprovalDetailMapperList() {
		return technicalApprovalDetailMapperList;
	}

	public void setTechnicalApprovalDetailMapperList(
			List<TechnicalApprovalDetailMapper> technicalApprovalDetailMapperList) {
		this.technicalApprovalDetailMapperList = technicalApprovalDetailMapperList;
	}

	public TechnicalApprovalDetailMapper addTechnicalApprovalDetailMapperList(TechnicalApprovalDetailMapper techApprovalDetailMapper) {
		if (getTechnicalApprovalDetailMapperList() == null)
			this.technicalApprovalDetailMapperList = new ArrayList<TechnicalApprovalDetailMapper>();

		getTechnicalApprovalDetailMapperList().add(techApprovalDetailMapper);
		techApprovalDetailMapper.setTechnicalApprovalDetail(this);

		return techApprovalDetailMapper;
	}
	
}