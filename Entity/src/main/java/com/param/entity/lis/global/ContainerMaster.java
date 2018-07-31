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
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.param.entity.lis.common.LocalTimeConverter;
import com.param.entity.lis.unit.TestMaster;

@NamedQueries({
		// Container
		@NamedQuery(name = "GET_CONTAINER_BY_CONTAINER_ID", query = "SELECT  containerMaster.containerId as id, "
				+ " containerMaster.code as code,"
				+ " containerMaster.desc as desc,"
				+ " containerMaster.status as status,"
				+ " containerMaster.orgId as orgId,"
				+ " containerMaster.createdBy as createdBy,"
				+ " containerMaster.createdDate as createdDate,"
				+ " containerMaster.updatedBy as updatedBy,"
				+ " containerMaster.updatedDate as updatedDate"
				+ " FROM ContainerMaster containerMaster"
				+ " WHERE containerMaster.containerId = :containerId "
				+ " AND containerMaster.orgId= :orgId"),
				
		@NamedQuery(name = "GET_CONTAINER_BY_CODE", query = "SELECT containerMaster.containerId as id,"
				+ " containerMaster.code as code,"
				+ " containerMaster.desc as desc,"
				+ " containerMaster.status as status,"
				+ " containerMaster.createdBy as createdBy,"
				+ " containerMaster.createdDate as createdDate,"
				+ " containerMaster.updatedBy as updatedBy,"
				+ " containerMaster.updatedDate as updatedDate"
				+ " FROM ContainerMaster containerMaster"
				+ " WHERE containerMaster.orgId=:orgId "
				+ " AND lower(containerMaster.code) = lower(:code)"),
				
				@NamedQuery(name = "UPDATE_GET_CONTAINER_BY_CODE", query = "SELECT containerMaster.containerId as id,"
						+ " containerMaster.code as code,"
						+ " containerMaster.desc as desc,"
						+ " containerMaster.status as status,"
						+ " containerMaster.createdBy as createdBy,"
						+ " containerMaster.createdDate as createdDate,"
						+ " containerMaster.updatedBy as updatedBy,"
						+ " containerMaster.updatedDate as updatedDate"
						+ " FROM ContainerMaster containerMaster"
						+ " WHERE containerMaster.orgId=:orgId "
						+ " AND lower(containerMaster.code) = lower(:code)"
						+ " AND containerMaster.containerId <> :containerId"),


		@NamedQuery(name = "GET_PAGINATED_CONTAINER_MASTER_LIST", query = "SELECT containerMaster.containerId as id,"
				+ " containerMaster.code as code,"
				+ " containerMaster.desc as desc,"
				+ " containerMaster.status as status,"
				+ " containerMaster.createdBy as createdBy,"
				+ " containerMaster.createdDate as createdDate,"
				+ " containerMaster.updatedBy as updatedBy,"
				+ " containerMaster.updatedDate as updatedDate"
				+ " FROM ContainerMaster containerMaster"
				+ " WHERE containerMaster.orgId = :orgId"
				+ " ORDER BY id DESC")

})
@NamedNativeQueries(
{ @NamedNativeQuery(name = "GET_TOTAL_CONTAINER_RECORD", query = "select count(*) from lab.m_container_master where "
		+ "org_id = :orgId")
})
@Entity
@Table(name = "m_container_master", schema = "lab")
@SequenceGenerator(name = "m_seq_container_master", sequenceName = "lab.m_seq_container_master", allocationSize = 1)
public class ContainerMaster {

	@Id
	@Column(name = "container_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "m_seq_container_master")
	private int containerId;

	@Column(name = "container_code")
	private String code;

	@Column(name = "container_name")
	private String desc;

	@Column(name = "created_by")
	private Integer createdBy;

	@Column(name = "container_status")
	private Character status;

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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sampleMaster")
	private List<TestMaster> listTestMaster;


	/**
	 * @return the containerId
	 */
	public int getContainerId() {
		return containerId;
	}

	/**
	 * @param containerId the containerId to set
	 */
	public void setContainerId(int containerId) {
		this.containerId = containerId;
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

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public List<TestMaster> getListTestMaster() {
		return listTestMaster;
	}

	public void setListTestMaster(List<TestMaster> listTestMaster) {
		this.listTestMaster = listTestMaster;
	}


	/**
	 * @return the createdBy
	 */
	public Integer getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy
	 *            the createdBy to set
	 */
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

}
