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
import com.param.entity.lis.unit.ParameterMaster;
import com.param.entity.lis.unit.TestParamMppr;
import com.param.entity.lis.common.LocalTimeConverter;
@NamedQueries({

	@NamedQuery(name = "GET_UNIT_BY_UNIT_ID", query = "SELECT unitMaster.unitId as id,"
			+ " unitMaster.code as code,"
			+ " unitMaster.desc as desc,"
			+ " unitMaster.status as status,"
			+ " unitMaster.orgId as orgId,"
			+ " unitMaster.createdBy as createdBy,"
			+ " unitMaster.createdDate as createdDate,"
			+ " unitMaster.updatedBy as updatedBy,"
			+ " unitMaster.updatedDate as updatedDate"
			+ " FROM LabUnitMaster unitMaster "
			+ " WHERE unitMaster.unitId = :unitId "
			+ " AND unitMaster.orgId= :orgId"),
			
		@NamedQuery(name = "GET_UNIT_BY_UNIT_CODE", query = "SELECT unitMaster.unitId as id,"
			+ " unitMaster.code as code,"
			+ " unitMaster.desc as desc,"
			+ " unitMaster.status as status,"
			+ " unitMaster.createdBy as createdBy,"
			+ " unitMaster.createdDate as createdDate,"
			+ " unitMaster.updatedBy as updatedBy,"
			+ " unitMaster.updatedDate as updatedDate"
			+ " FROM LabUnitMaster unitMaster"
			+ " WHERE unitMaster.orgId=:orgId "
			+ " AND lower(unitMaster.code) = lower(:code)"),
			
			@NamedQuery(name = "UPDATE_GET_UNIT_BY_UNIT_CODE", query = "SELECT unitMaster.unitId as id,"
					+ " unitMaster.code as code,"
					+ " unitMaster.desc as desc,"
					+ " unitMaster.status as status,"
					+ " unitMaster.createdBy as createdBy,"
					+ " unitMaster.createdDate as createdDate,"
					+ " unitMaster.updatedBy as updatedBy,"
					+ " unitMaster.updatedDate as updatedDate"
					+ " FROM LabUnitMaster unitMaster"
					+ " WHERE unitMaster.orgId=:orgId "
					+ " AND lower(unitMaster.code) = lower(:code)"
					+ " AND unitMaster.unitId <> :unitId"),
	

	@NamedQuery(name = "GET_PAGINATED_UNIT_MASTER_LIST", query = "SELECT unitMaster.unitId as id,"
			+ " unitMaster.code as code,"
			+ " unitMaster.desc as desc,"
			+ " unitMaster.status as status,"
			+ " unitMaster.createdBy as createdBy,"
			+ " unitMaster.createdDate as createdDate,"
			+ " unitMaster.updatedBy as updatedBy,"
			+ " unitMaster.updatedDate as updatedDate"
			+ " FROM LabUnitMaster unitMaster"
			+ " WHERE unitMaster.orgId = :orgId"
			+ " ORDER BY id DESC")

})

@NamedNativeQueries(
{ @NamedNativeQuery(name = "GET_TOTAL_UNIT_RECORD", query = "select count(*) from lab.m_unit_master where "
	+ "org_id = :orgId ")
})
@Entity
@Table(name = "m_unit_master", schema = "lab")
@SequenceGenerator(name = "m_seq_unit_master", sequenceName = "lab.m_seq_unit_master", allocationSize = 1)
public class LabUnitMaster {
	@Id
	@Column(name = "unit_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "m_seq_unit_master")
	private int unitId;
	
	@Column(name = "unit_code")
	private String code;
	
	@Column(name = "unit_name")
	private String desc;
	
	@Column(name = "unit_status")
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
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "labUnitMaster")
	private List<ParameterMaster> listParameterMaster;
	
	/**
	 * @return the unitId
	 */
	public int getUnitId() {
		return unitId;
	}

	/**
	 * @param unitId the unitId to set
	 */
	public void setUnitId(int unitId) {
		this.unitId = unitId;
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

	public List<ParameterMaster> getListParameterMaster()
	{
		return listParameterMaster;
	}

	public void setListParameterMaster(List<ParameterMaster> listParameterMaster)
	{
		this.listParameterMaster = listParameterMaster;
	}

	
}
