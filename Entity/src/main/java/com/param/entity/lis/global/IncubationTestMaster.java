package com.param.entity.lis.global;

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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.param.entity.lis.common.LocalTimeConverter;

@NamedQueries({

	@NamedQuery(name = "GET_REPORT_TYPE_BY_INCUBATION_TEST_MASTER_ID", query = "SELECT incubationTestMaster.incubationTestId as id,"
			+ " incubationTestMaster.code as code,"
			+ " incubationTestMaster.desc as desc,"
			+ " incubationTestMaster.status as status,"
			+ " incubationTestMaster.orgId as orgId,"
			+ " incubationTestMaster.createdBy as createdBy,"
			+ " incubationTestMaster.createdDate as createdDate,"
			+ " incubationTestMaster.updatedBy as updatedBy,"
			+ " incubationTestMaster.updatedDate as updatedDate"
			+ " FROM IncubationTestMaster incubationTestMaster "
			+ " WHERE incubationTestMaster.incubationTestId = :incubationTestId "
			+ " AND incubationTestMaster.orgId= :orgId"),
			
		@NamedQuery(name = "GET_REPORT_TYPE_BY_INCUBATION_TEST_CODE", query = "SELECT incubationTestMaster.incubationTestId as id,"
			+ " incubationTestMaster.code as code,"
			+ " incubationTestMaster.desc as desc,"
			+ " incubationTestMaster.status as status,"
			+ " incubationTestMaster.createdBy as createdBy,"
			+ " incubationTestMaster.createdDate as createdDate,"
			+ " incubationTestMaster.updatedBy as updatedBy,"
			+ " incubationTestMaster.updatedDate as updatedDate"
			+ " FROM IncubationTestMaster incubationTestMaster"
			+ " WHERE incubationTestMaster.orgId=:orgId "
			+ " AND lower(incubationTestMaster.code) = lower(:code)"),
			
			@NamedQuery(name = "UPDATE_GET_REPORT_TYPE_BY_INCUBATION_TEST_CODE", query = "SELECT incubationTestMaster.incubationTestId as id,"
					+ " incubationTestMaster.code as code,"
					+ " incubationTestMaster.desc as desc,"
					+ " incubationTestMaster.status as status,"
					+ " incubationTestMaster.createdBy as createdBy,"
					+ " incubationTestMaster.createdDate as createdDate,"
					+ " incubationTestMaster.updatedBy as updatedBy,"
					+ " incubationTestMaster.updatedDate as updatedDate"
					+ " FROM IncubationTestMaster incubationTestMaster"
					+ " WHERE incubationTestMaster.orgId=:orgId "
					+ " AND lower(incubationTestMaster.code) = lower(:code)"
					+ " AND incubationTestMaster.incubationTestId <> :incubationTestId"),
			
	

	@NamedQuery(name = "GET_PAGINATED_INCUBATION_TEST_MASTER_LIST", query = "SELECT incubationTestMaster.incubationTestId as id,"
			+ " incubationTestMaster.code as code,"
			+ " incubationTestMaster.desc as desc,"
			+ " incubationTestMaster.status as status,"
			+ " incubationTestMaster.createdBy as createdBy,"
			+ " incubationTestMaster.createdDate as createdDate,"
			+ " incubationTestMaster.updatedBy as updatedBy,"
			+ " incubationTestMaster.updatedDate as updatedDate"
			+ " FROM IncubationTestMaster incubationTestMaster"
			+ " WHERE incubationTestMaster.orgId = :orgId"
			+ " ORDER BY id DESC")

})

@NamedNativeQueries(
{ @NamedNativeQuery(name = "GET_TOTAL_INCUBATION_TEST_RECORD", query = "select count(*) from lab.m_incubation_test_master where "
	+ "org_id = :orgId ")
})
@Entity
@Table(name = "m_incubation_test_master", schema = "lab")
@SequenceGenerator(name = "m_seq_incubation_test_master", sequenceName = "lab.m_seq_incubation_test_master", allocationSize = 1)
public class IncubationTestMaster {
	

	@Id
	@Column(name = "incubation_test_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "m_seq_incubation_test_master")
	private int incubationTestId;
	
	@Column(name = "incubation_testcode")
	private String code;
	
	@Column(name = "incubation_testdesc")
	private String desc;

	
	@Column(name = "incubation_test_status")
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




	/**
	 * @return the incubationTestId
	 */
	public int getIncubationTestId() {
		return incubationTestId;
	}

	/**
	 * @param incubationTestId the incubationTestId to set
	 */
	public void setIncubationTestId(int incubationTestId) {
		this.incubationTestId = incubationTestId;
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
	 * @return the organizationMaster
	 */
	
	
	

}
