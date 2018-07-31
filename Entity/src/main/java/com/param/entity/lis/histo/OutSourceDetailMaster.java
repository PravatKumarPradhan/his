package com.param.entity.lis.histo;



import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.param.entity.lis.common.LocalTimeConverter;
@NamedNativeQueries(
{ 

@NamedNativeQuery(name = "GET_OUT_SOURCE_DETAILS_BY_OUT_SOURCE_ID", query = "SELECT out_source_detail_id as \"outSourceDetailId\", "
		+ "   resource as \"resource\" , "
		+ "   sample_send_through_id as \"sampleSendThroughId\", "
		+ "   courier_number as \"courierNumber\","
		/*+ "   expected_date as \"expectedDate\", "*/
		+ "   status as status,remarks as \"remarks\" "
		+ "   FROM lab.t_out_source_detail "
		+ "   where out_sourced_id=:outSourcedId "
		+ "   AND org_id=:orgId "
		+ "   AND org_unit_id=:orgUnitId "
		+ "   AND status='A' "),//2
		
})
@Entity
@Table(name = "t_out_source_detail", schema = "lab")
@SequenceGenerator(name = "seq_t_out_source_detail", sequenceName = "lab.seq_t_out_source_detail", allocationSize = 1)
public class OutSourceDetailMaster {
	
	@Id
	@Column(name = "out_source_detail_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_t_out_source_detail")
	private int outSourceDetailId;
	
	@Column(name = "resource")
	private String resource;
	
	@Column(name = "sample_send_through_id")
	private Integer sampleSendThroughId;
	
	@Column(name = "courier_number")
	private String courierNumber;
	
	@Column(name = "remarks")
	private String remarks;
	
	@Column(name = "expected_date")
	@Convert(converter = LocalTimeConverter.class)
	private Long expectedDate;
	
	@Column(name = "created_by")
	private Integer createdBy;

	@Column(name = "created_date")
	@Convert(converter = LocalTimeConverter.class)
	private Long createdDate;

	@Column(name = "updated_by")
	private Integer updatedBy;

	@Column(name = "updated_date")
	@Convert(converter = LocalTimeConverter.class)
	private Long updatedDate;
	
	@Column(name = "status")
	private Character status;
	
	@Column(name = "org_id")
	private Integer orgId;
	
	@Column(name = "org_unit_id")
	private Integer orgUnitId;

	@Column(name = "out_sourced_id")
	private Integer outSourcedId;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "out_sourced_id", insertable = false, nullable = false, updatable = false)
	private OutSourceMaster outSourceMaster;

	public int getOutSourceDetailId() {
		return outSourceDetailId;
	}

	public void setOutSourceDetailId(int outSourceDetailId) {
		this.outSourceDetailId = outSourceDetailId;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public Integer getSampleSendThroughId() {
		return sampleSendThroughId;
	}

	public void setSampleSendThroughId(Integer sampleSendThroughId) {
		this.sampleSendThroughId = sampleSendThroughId;
	}

	public String getCourierNumber() {
		return courierNumber;
	}

	public void setCourierNumber(String courierNumber) {
		this.courierNumber = courierNumber;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Long getExpectedDate() {
		return expectedDate;
	}

	public void setExpectedDate(Long expectedDate) {
		this.expectedDate = expectedDate;
	}
	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Long getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Long createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Long getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Long updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Character getStatus() {
		return status;
	}

	public void setStatus(Character status) {
		this.status = status;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public Integer getOrgUnitId() {
		return orgUnitId;
	}

	public void setOrgUnitId(Integer orgUnitId) {
		this.orgUnitId = orgUnitId;
	}

	public Integer getOutSourcedId() {
		return outSourcedId;
	}

	public void setOutSourcedId(Integer outSourcedId) {
		this.outSourcedId = outSourcedId;
	}

	public OutSourceMaster getOutSourceMaster() {
		return outSourceMaster;
	}

	public void setOutSourceMaster(OutSourceMaster outSourceMaster) {
		this.outSourceMaster = outSourceMaster;
	}

	
}
