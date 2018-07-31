package com.param.entity.lis.global;

import java.util.List;

import javax.persistence.CascadeType;
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.param.entity.lis.unit.TestMaster;
import com.param.entity.lis.common.LocalTimeConverter;
@NamedQueries({

	@NamedQuery(name = "GET_REPORT_TYPE_BY_REAGENT_ID", query = "SELECT reportTypeMaster.reportTypeId as id,"
			+ " reportTypeMaster.code as code,"
			+ " reportTypeMaster.desc as desc,"
			+ " reportTypeMaster.status as status,"
			+ " reportTypeMaster.orgId as orgId,"
			+ " reportTypeMaster.createdBy as createdBy,"
			+ " reportTypeMaster.createdDate as createdDate,"
			+ " reportTypeMaster.updatedBy as updatedBy,"
			+ " reportTypeMaster.updatedDate as updatedDate"
			+ " FROM ReportTypeMaster reportTypeMaster "
			+ " WHERE reportTypeMaster.reportTypeId = :reportTypeId "
			+ " AND reportTypeMaster.orgId= :orgId"),
			
		@NamedQuery(name = "GET_REPORT_TYPE_BY_REAGENT_CODE", query = "SELECT reportTypeMaster.reportTypeId as id,"
			+ " reportTypeMaster.code as code,"
			+ " reportTypeMaster.desc as desc,"
			+ " reportTypeMaster.status as status,"
			+ " reportTypeMaster.createdBy as createdBy,"
			+ " reportTypeMaster.createdDate as createdDate,"
			+ " reportTypeMaster.updatedBy as updatedBy,"
			+ " reportTypeMaster.updatedDate as updatedDate"
			+ " FROM ReportTypeMaster reportTypeMaster"
			+ " WHERE reportTypeMaster.orgId=:orgId "
			+ " AND lower(reportTypeMaster.code) = lower(:code)"),
			
			@NamedQuery(name = "UPDATE_GET_REPORT_TYPE_BY_REAGENT_CODE", query = "SELECT reportTypeMaster.reportTypeId as id,"
					+ " reportTypeMaster.code as code,"
					+ " reportTypeMaster.desc as desc,"
					+ " reportTypeMaster.status as status,"
					+ " reportTypeMaster.createdBy as createdBy,"
					+ " reportTypeMaster.createdDate as createdDate,"
					+ " reportTypeMaster.updatedBy as updatedBy,"
					+ " reportTypeMaster.updatedDate as updatedDate"
					+ " FROM ReportTypeMaster reportTypeMaster"
					+ " WHERE reportTypeMaster.orgId=:orgId "
					+ " AND lower(reportTypeMaster.code) = lower(:code)"
					+ " AND reportTypeMaster.reportTypeId <> :reportTypeId"),
	

	@NamedQuery(name = "GET_PAGINATED_REPORT_TYPE_MASTER_LIST", query = "SELECT reportTypeMaster.reportTypeId as id,"
			+ " reportTypeMaster.code as code,"
			+ " reportTypeMaster.desc as desc,"
			+ " reportTypeMaster.status as status,"
			+ " reportTypeMaster.createdBy as createdBy,"
			+ " reportTypeMaster.createdDate as createdDate,"
			+ " reportTypeMaster.updatedBy as updatedBy,"
			+ " reportTypeMaster.updatedDate as updatedDate"
			+ " FROM ReportTypeMaster reportTypeMaster"
			+ " WHERE reportTypeMaster.orgId = :orgId"
			+ " ORDER BY id DESC")

})

@NamedNativeQueries(
{ @NamedNativeQuery(name = "GET_TOTAL_REPORT_TYPE_RECORD", query = "select count(*) from lab.m_report_type_master where "
	+ "org_id = :orgId ")
})
@Entity
@Table(name = "m_report_type_master", schema = "lab")
@SequenceGenerator(name = "m_seq_report_type_master", sequenceName = "lab.m_seq_report_type_master", allocationSize = 1)
public class ReportTypeMaster {
	@Id
	@Column(name = "report_type_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "m_seq_report_type_master")
	private int reportTypeId;
	@Column(name = "report_type_code")
	private String code;
	
	@Column(name = "report_type_name")
	private String desc;
	
	@Column(name = "report_type_status")
	private Character status;
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
	
	@Column(name = "org_id")
	private Integer orgId;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "reportTypeMaster")
	private List<TestMaster> listTestMaster;
	


	/**
	 * @return the reportTypeId
	 */
	public int getReportTypeId() {
		return reportTypeId;
	}

	/**
	 * @param reportTypeId the reportTypeId to set
	 */
	public void setReportTypeId(int reportTypeId) {
		this.reportTypeId = reportTypeId;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * @param desc the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * @return the status
	 */
	public Character getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Character status) {
		this.status = status;
	}

	/**
	 * @return the createdBy
	 */
	public Integer getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the createdDate
	 */
	public Long getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Long createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the updatedBy
	 */
	public Integer getUpdatedBy() {
		return updatedBy;
	}

	/**
	 * @param updatedBy the updatedBy to set
	 */
	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}

	/**
	 * @return the updatedDate
	 */
	public Long getUpdatedDate() {
		return updatedDate;
	}

	/**
	 * @param updatedDate the updatedDate to set
	 */
	public void setUpdatedDate(Long updatedDate) {
		this.updatedDate = updatedDate;
	}

	/**
	 * @return the orgId
	 */
	public Integer getOrgId() {
		return orgId;
	}

	/**
	 * @param orgId the orgId to set
	 */
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	/**
	 * @return the listTestMaster
	 */
	public List<TestMaster> getListTestMaster() {
		return listTestMaster;
	}

	/**
	 * @param listTestMaster the listTestMaster to set
	 */
	public void setListTestMaster(List<TestMaster> listTestMaster) {
		this.listTestMaster = listTestMaster;
	}

	

	
}
