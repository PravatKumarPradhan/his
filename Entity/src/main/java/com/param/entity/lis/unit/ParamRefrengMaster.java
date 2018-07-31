package com.param.entity.lis.unit;

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

import com.param.global.model.GenderMaster;
import com.param.entity.lis.common.LocalTimeConverter;
import com.param.entity.lis.global.AgeGroupMaster;


@NamedQueries({



  @NamedQuery(name = "GET_PARAM_REF_RANGE_VALUE_BY_PARAMETER",
      query = " SELECT  "
          +"  paramRefrengMaster.refrengId AS refrengId,  "
          +"  paramRefrengMaster.parameterId AS parameterId,  "
          +"  paramRefrengMaster.genderId AS genderId,  "
          +"  ageGrpMst.ageTypeGrpName AS ageGroupName,  "
          +"  ageGrpMst.ageFromDay AS ageFromDay,  "
          +"  ageGrpMst.ageToday AS ageToDay,  "
          +"  genderMst.desc AS genderName,  "
          +"  paramRefrengMaster.minValue AS minValue,  "
          +"  paramRefrengMaster.maxValue AS maxValue,  "
          +"  paramRefrengMaster.lessThan AS lessThan,  "
          +"  paramRefrengMaster.moreThan AS moreThan,  "
          +"  paramRefrengMaster.ageGroupId AS ageGroupId,  "
          +"  paramRefrengMaster.remark AS remark,  "
          +"  paramRefrengMaster.refrengStatus AS refrengStatus,  "
          +"  paramRefrengMaster.createdBy AS createdBy,  "
          +"  paramRefrengMaster.createdDate AS createdDate,  "
          +"  paramRefrengMaster.createdDate AS createdDate,  "
          +"  paramRefrengMaster.updatedBy AS updatedBy,  "
          +"  paramRefrengMaster.updatedDate AS updatedDate,  "
          +"  paramRefrengMaster.orgId AS orgId,  "
          +"  paramRefrengMaster.orgUnitId AS orgUnitId,  "
          +"  paramRefrengMaster.isDeleted AS isDeleted  "
          +"FROM  "
          +"  ParamRefrengMaster paramRefrengMaster  "
          +"  LEFT JOIN paramRefrengMaster.ageGroupMaster ageGrpMst  "
          +"  LEFT JOIN paramRefrengMaster.genderMaster genderMst  "
          +"WHERE  "
          +"  paramRefrengMaster.parameterId = :parameterId  "
          +"  AND paramRefrengMaster.orgId = :orgId  "
          +"  AND paramRefrengMaster.orgUnitId = :orgUnitId  "
          +"  AND paramRefrengMaster.isDeleted = 'N'  "),

  @NamedQuery(name = "UPDATE_REF_RANGE_MASTER", query ="UPDATE ParamRefrengMaster SET refrengStatus =:status  "
      +"WHERE parameterId=:parameterId  " )

})






@Entity
@Table(name = "m_param_refreng_master", schema = "lab")
@SequenceGenerator(name = "m_seq_param_refreng_master", sequenceName = "lab.m_seq_param_refreng_master", allocationSize = 1)
public class ParamRefrengMaster
{
	@Id
	@Column(name = "refreng_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "m_seq_param_refreng_master")
	private int refrengId;

	@Column(name = "parameter_id")
	private Integer parameterId;

	@Column(name = "gender_id")
	private Integer genderId;

	@Column(name = "min_value")
	private Double minValue;

	@Column(name = "max_value")
	private Double maxValue;

	@Column(name = "less_than")
	private Double lessThan;

	@Column(name = "more_than")
	private Double moreThan;

	@Column(name = "age_group_id")
	private Integer ageGroupId;
	
	@Column(name = "remark")
	private String remark;

	@Column(name = "refreng_status")
	private Character refrengStatus;

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

	@Column(name = "org_unit_id")
	private Integer orgUnitId;
	
	@Column(name = "is_deleted")
	private Character isDeleted;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parameter_id", insertable = false, nullable = false, updatable = false)
	private ParameterMaster parameterMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "age_group_id", insertable = false, nullable = false, updatable = false)
	private AgeGroupMaster ageGroupMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "gender_id", insertable = false, nullable = false, updatable = false)
	private GenderMaster genderMaster;
	
	/**
	 * @return the refrengId
	 */
	public int getRefrengId()
	{
		return refrengId;
	}

	/**
	 * @param refrengId
	 *            the refrengId to set
	 */
	public void setRefrengId(int refrengId)
	{
		this.refrengId = refrengId;
	}


	/**
	 * @return the parameterId
	 */
	public Integer getParameterId()
	{
		return parameterId;
	}

	/**
	 * @param parameterId
	 *            the parameterId to set
	 */
	public void setParameterId(Integer parameterId)
	{
		this.parameterId = parameterId;
	}

	/**
	 * @return the genderId
	 */
	public Integer getGenderId()
	{
		return genderId;
	}

	/**
	 * @param genderId
	 *            the genderId to set
	 */
	public void setGenderId(Integer genderId)
	{
		this.genderId = genderId;
	}

	/**
	 * @return the minValue
	 */
	public Double getMinValue()
	{
		return minValue;
	}

	/**
	 * @param minValue
	 *            the minValue to set
	 */
	public void setMinValue(Double minValue)
	{
		this.minValue = minValue;
	}

	/**
	 * @return the maxValue
	 */
	public Double getMaxValue()
	{
		return maxValue;
	}

	/**
	 * @param maxValue
	 *            the maxValue to set
	 */
	public void setMaxValue(Double maxValue)
	{
		this.maxValue = maxValue;
	}

	/**
	 * @return the lessThan
	 */
	public Double getLessThan()
	{
		return lessThan;
	}

	/**
	 * @param lessThan
	 *            the lessThan to set
	 */
	public void setLessThan(Double lessThan)
	{
		this.lessThan = lessThan;
	}

	/**
	 * @return the moreThan
	 */
	public Double getMoreThan()
	{
		return moreThan;
	}

	/**
	 * @param moreThan
	 *            the moreThan to set
	 */
	public void setMoreThan(Double moreThan)
	{
		this.moreThan = moreThan;
	}

	/**
	 * @return the ageGroupId
	 */
	public Integer getAgeGroupId()
	{
		return ageGroupId;
	}

	/**
	 * @param ageGroupId
	 *            the ageGroupId to set
	 */
	public void setAgeGroupId(Integer ageGroupId)
	{
		this.ageGroupId = ageGroupId;
	}

	/**
	 * @return the remark
	 */
	public String getRemark()
	{
		return remark;
	}

	/**
	 * @param remark
	 *            the remark to set
	 */
	public void setRemark(String remark)
	{
		this.remark = remark;
	}

	/**
	 * @return the refrengStatus
	 */
	public Character getRefrengStatus()
	{
		return refrengStatus;
	}

	/**
	 * @param refrengStatus
	 *            the refrengStatus to set
	 */
	public void setRefrengStatus(Character refrengStatus)
	{
		this.refrengStatus = refrengStatus;
	}

	/**
	 * @return the createdBy
	 */
	public Integer getCreatedBy()
	{
		return createdBy;
	}

	/**
	 * @param createdBy
	 *            the createdBy to set
	 */
	public void setCreatedBy(Integer createdBy)
	{
		this.createdBy = createdBy;
	}

	/**
	 * @return the createdDate
	 */
	public Long getCreatedDate()
	{
		return createdDate;
	}

	/**
	 * @param createdDate
	 *            the createdDate to set
	 */
	public void setCreatedDate(Long createdDate)
	{
		this.createdDate = createdDate;
	}

	/**
	 * @return the updatedBy
	 */
	public Integer getUpdatedBy()
	{
		return updatedBy;
	}

	/**
	 * @param updatedBy
	 *            the updatedBy to set
	 */
	public void setUpdatedBy(Integer updatedBy)
	{
		this.updatedBy = updatedBy;
	}

	/**
	 * @return the updatedDate
	 */
	public Long getUpdatedDate()
	{
		return updatedDate;
	}

	/**
	 * @param updatedDate
	 *            the updatedDate to set
	 */
	public void setUpdatedDate(Long updatedDate)
	{
		this.updatedDate = updatedDate;
	}

	public Integer getOrgId()
	{
		return orgId;
	}

	public void setOrgId(Integer orgId)
	{
		this.orgId = orgId;
	}

	public Integer getOrgUnitId()
	{
		return orgUnitId;
	}

	public void setOrgUnitId(Integer orgUnitId)
	{
		this.orgUnitId = orgUnitId;
	}


	public ParameterMaster getParameterMaster()
	{
		return parameterMaster;
	}

	public void setParameterMaster(ParameterMaster parameterMaster)
	{
		this.parameterMaster = parameterMaster;
	}

	public Character getIsDeleted()
	{
		return isDeleted;
	}

	public void setIsDeleted(Character isDeleted)
	{
		this.isDeleted = isDeleted;
	}

	public AgeGroupMaster getAgeGroupMaster()
	{
		return ageGroupMaster;
	}

	public void setAgeGroupMaster(AgeGroupMaster ageGroupMaster)
	{
		this.ageGroupMaster = ageGroupMaster;
	}

	public GenderMaster getGenderMaster() {
		return genderMaster;
	}

	public void setGenderMaster(GenderMaster genderMaster) {
		this.genderMaster = genderMaster;
	}

	
}
