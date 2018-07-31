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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.param.entity.lis.unit.ParameterMaster;
import com.param.entity.lis.common.LocalTimeConverter;

@Entity
@Table(name = "t_test_parameter_mppr", schema = "lab")
@SequenceGenerator(name = "t_test_par_mppr", sequenceName = "lab.t_test_par_mppr", allocationSize = 1)
public class TestParamMppr
{
	
	@Id
	@Column(name = "test_per_mppr_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_test_par_mppr")
	private int testPerMpprId;

	@Column(name = "test_id")
	private Integer testId;

	@Column(name = "parameter_id")
	private Integer parameterId;

	@Column(name = "para_sequence")
	private Integer paraSequence;

	@Column(name = "header_id")
	private Integer headerId;

	@Column(name = "test_para_status")
	private Character testParaStatus;
	
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
	@JoinColumn(name = "test_id", insertable = false, updatable = false, nullable = false)
	private TestMaster testMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "header_id", insertable = false, updatable = false, nullable = false)
	private HeaderMaster headerMaster;
	
	@ManyToOne
	@JoinColumn(name = "parameter_id", insertable = false, updatable = false, nullable = false)
	private ParameterMaster parameterMaster;
	

	public int getTestPerMpprId()
	{
		return testPerMpprId;
	}

	public void setTestPerMpprId(int testPerMpprId)
	{
		this.testPerMpprId = testPerMpprId;
	}

	public Integer getTestId()
	{
		return testId;
	}

	public void setTestId(Integer testId)
	{
		this.testId = testId;
	}

	public Integer getParameterId()
	{
		return parameterId;
	}

	public void setParameterId(Integer parameterId)
	{
		this.parameterId = parameterId;
	}

	public Integer getParaSequence()
	{
		return paraSequence;
	}

	public void setParaSequence(Integer paraSequence)
	{
		this.paraSequence = paraSequence;
	}

	public Integer getHeaderId()
	{
		return headerId;
	}

	public void setHeaderId(Integer headerId)
	{
		this.headerId = headerId;
	}

	public Character getTestParaStatus()
	{
		return testParaStatus;
	}

	public void setTestParaStatus(Character testParaStatus)
	{
		this.testParaStatus = testParaStatus;
	}

	public Integer getCreatedBy()
	{
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy)
	{
		this.createdBy = createdBy;
	}

	public Long getCreatedDate()
	{
		return createdDate;
	}

	public void setCreatedDate(Long createdDate)
	{
		this.createdDate = createdDate;
	}

	public Integer getUpdatedBy()
	{
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy)
	{
		this.updatedBy = updatedBy;
	}

	public Long getUpdatedDate()
	{
		return updatedDate;
	}

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

	public TestMaster getTestMaster()
	{
		return testMaster;
	}

	public void setTestMaster(TestMaster testMaster)
	{
		this.testMaster = testMaster;
	}

	public HeaderMaster getHeaderMaster()
	{
		return headerMaster;
	}

	public void setHeaderMaster(HeaderMaster headerMaster)
	{
		this.headerMaster = headerMaster;
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

	

}
