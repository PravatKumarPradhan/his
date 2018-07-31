package com.param.entity.model.pharmacy;

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
import javax.persistence.Table;

import com.param.entity.model.base.BaseEntity;
import com.param.entity.model.inventory.Batch;
import com.param.entity.model.inventory.MaterialIssueBatchMapper;
import com.param.entity.model.master.RejectReason;
import com.param.entity.model.master.Status;
import com.param.entity.model.master.UomType;
import com.param.entity.model.master.UomUnit;

@Entity(name="IssueDetail")
@Table(name="t_issue_detail",schema="pharmacy")
public class IssueDetail extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="sale_detail_id")
	private SaleDetail saleDetail;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="prescription_detail_id")
	private PrescriptionDetail prescriptionDetail;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="issue_id" )
	private Issue issue;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="status_id" )
	private Status status;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="patient_indent_detail_id" )
	private PatientIndentDetail patientIndentDetail;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "issueDetail", cascade = CascadeType.ALL)
	private List<IssueDetailBatchMapper> issueDetailBatchMapper;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public SaleDetail getSaleDetail() {
		return saleDetail;
	}

	public void setSaleDetail(SaleDetail saleDetail) {
		this.saleDetail = saleDetail;
	}

	public PrescriptionDetail getPrescriptionDetail() {
		return prescriptionDetail;
	}

	public void setPrescriptionDetail(PrescriptionDetail prescriptionDetail) {
		this.prescriptionDetail = prescriptionDetail;
	}

	public Issue getIssue() {
		return issue;
	}

	public void setIssue(Issue issue) {
		this.issue = issue;
	}


	public PatientIndentDetail getPatientIndentDetail() {
		return patientIndentDetail;
	}

	public void setPatientIndentDetail(PatientIndentDetail patientIndentDetail) {
		this.patientIndentDetail = patientIndentDetail;
	}
	
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	public List<IssueDetailBatchMapper> getIssueDetailBatchMapper() {
		return issueDetailBatchMapper;
	}

	public void setIssueDetailBatchMapper(List<IssueDetailBatchMapper> issueDetailBatchMapper) {
		this.issueDetailBatchMapper = issueDetailBatchMapper;
	}

	public void addBatch(IssueDetailBatchMapper batchMapper) {
		if (this.issueDetailBatchMapper == null)
			this.issueDetailBatchMapper = new ArrayList<IssueDetailBatchMapper>();

		this.issueDetailBatchMapper.add(batchMapper);
		batchMapper.setIssueDetail(this);
	}
	
	public IssueDetail() {
		super();
	}
	
	public IssueDetail(Integer id) {
		super();
		this.id = id;
	}

	public IssueDetail(Integer id, SaleDetail saleDetail, PrescriptionDetail prescriptionDetail,
			Issue issue) {
		super();
		this.id = id;
		this.saleDetail = saleDetail;
		this.prescriptionDetail = prescriptionDetail;
		this.issue = issue;
	}

	public IssueDetail(PatientIndentDetail patientIndentDetail, Status status) {
		super();
		this.patientIndentDetail = patientIndentDetail;
		this.status = status;
	}

	public IssueDetail(SaleDetail saleDetail, PrescriptionDetail prescriptionDetail, Status status) {
		super();
		this.saleDetail = saleDetail;
		this.prescriptionDetail = prescriptionDetail;
		this.status = status;
	}
	
}