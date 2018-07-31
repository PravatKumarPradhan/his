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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import com.param.entity.lis.common.LocalTimeConverter;



@NamedQueries({

	@NamedQuery(name = "GET_HELP_VALUE_BY_PARAMETER", query = "SELECT helpValueMaster.helpValueId AS helpValueId, "
			+ " helpValueMaster.parameterId AS parameterId, "
			+ " helpValueMaster.helpValue AS helpValue "
			+ " FROM HelpValueMaster helpValueMaster "
			+ " WHERE helpValueMaster.parameterId = :parameterId "
			+ " AND helpValueMaster.orgId= :orgId"
			+ " AND helpValueMaster.orgUnitId= :orgUnitId"
			+ " AND helpValueMaster.isDeleted= 'N'"),
	
	@NamedQuery(name = "GET_HELP_VALUE_BY_PARAMETER_ID", query = "SELECT helpValueMaster.helpValueId AS helpValueId, "
        + " helpValueMaster.parameterId AS parameterId, "
        + " helpValueMaster.helpValue AS helpValue ,"
        + " helpValueMaster.status AS status ,"
        + " helpValueMaster.createdBy AS createdBy ,"
        + " helpValueMaster.createdDate AS createdDate ,"
        + " helpValueMaster.updatedBy AS updatedBy ,"
        + " helpValueMaster.updatedDate AS updatedDate ,"
        + " helpValueMaster.orgId AS orgId ,"
        + " helpValueMaster.orgId AS orgUnitId ,"
        + " helpValueMaster.isDeleted AS isDeleted "
        + " FROM HelpValueMaster helpValueMaster "
        + " WHERE helpValueMaster.parameterId = :parameterId "
        + " AND helpValueMaster.orgId= :orgId"
        + " AND helpValueMaster.orgUnitId= :orgUnitId"
        + " AND helpValueMaster.isDeleted= 'N'"),
	
	 @NamedQuery(name = "UPDATE_HELP_MASTER_STATUS", query ="UPDATE HelpValueMaster SET status =:status  "
	      +"WHERE parameterId =:parameterId  " )
	  

})
	

@Entity
@Table(name = "m_help_value_master", schema = "lab")
@SequenceGenerator(name = "m_seq_help_value_master", sequenceName = "lab.m_seq_help_value_master", allocationSize = 1)
public class HelpValueMaster
{

		@Id
		@Column(name = "help_value_id")
		@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "m_seq_help_value_master")
		private int helpValueId;

		@Column(name = "parameter_id")
		private Integer parameterId;

		@Column(name = "help_value")
		private String helpValue;

		@Column(name = "status")
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

		@Column(name = "org_unit_id")
		private Integer orgUnitId;
		
		@Column(name = "is_deleted")
		private Character isDeleted;
		
		@ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name = "parameter_id", insertable = false, nullable = false, updatable = false)
		private ParameterMaster parameterMaster;

		public int getHelpValueId()
		{
			return helpValueId;
		}
		
		public void setHelpValueId(int helpValueId)
		{
			this.helpValueId = helpValueId;
		}

		public Integer getParameterId()
		{
			return parameterId;
		}

		public void setParameterId(Integer parameterId)
		{
			this.parameterId = parameterId;
		}

		public String getHelpValue()
		{
			return helpValue;
		}

		public void setHelpValue(String helpValue)
		{
			this.helpValue = helpValue;
		}

		public Character getStatus()
		{
			return status;
		}

		public void setStatus(Character status)
		{
			this.status = status;
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
