package com.param.entity.lis.global;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@NamedQueries({

	@NamedQuery(name = "GET_AGE_TYPE_GROUP_MASTER", 
			query = "SELECT ageTypeGroupMaster.ageTypeGrpId as ageTypeGrpId,"
			+ " ageTypeGroupMaster.ageTypeGrpName as ageTypeGrpName,"
			+ " ageTypeGroupMaster.ageTypeGrpStatus as ageTypeGrpStatus "
			+ " FROM AgeTypeGroupMaster ageTypeGroupMaster "
			+ " ORDER BY ageTypeGroupMaster.ageTypeGrpId ")
		

})
@Entity
@Table(name = "m_age_type_group", schema = "lab")
@SequenceGenerator(name = "m_seq_age_group", sequenceName = "lab.m_seq_age_group", allocationSize = 1)
public class AgeTypeGroupMaster 
{
	@Id
	@Column(name = "age_type_grp_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "m_seq_age_group")
	private int ageTypeGrpId;

	@Column(name = "age_type_grp_name")
	private String ageTypeGrpName;
	
	@Column(name = "age_type_grp_status")
	private Character ageTypeGrpStatus;
	
	public int getAgeTypeGrpId()
	{
		return ageTypeGrpId;
	}

	/**
	 * @param ageTypeGrpId the ageTypeGrpId to set
	 */
	public void setAgeTypeGrpId(int ageTypeGrpId) {
		this.ageTypeGrpId = ageTypeGrpId;
	}


	/**
	 * @return the ageTypeGrpName
	 */
	public String getAgeTypeGrpName() {
		return ageTypeGrpName;
	}


	/**
	 * @param ageTypeGrpName the ageTypeGrpName to set
	 */
	public void setAgeTypeGrpName(String ageTypeGrpName) {
		this.ageTypeGrpName = ageTypeGrpName;
	}


	/**
	 * @return the ageTypeGrpStatus
	 */
	public Character getAgeTypeGrpStatus() {
		return ageTypeGrpStatus;
	}


	/**
	 * @param ageTypeGrpStatus the ageTypeGrpStatus to set
	 */
	public void setAgeTypeGrpStatus(Character ageTypeGrpStatus) {
		this.ageTypeGrpStatus = ageTypeGrpStatus;
	}

}
