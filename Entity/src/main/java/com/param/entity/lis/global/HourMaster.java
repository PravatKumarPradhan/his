package com.param.entity.lis.global;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@NamedQueries({

	@NamedQuery(name = "GET_HOURE_MASTER", 
			query = "SELECT hourMaster.hourId as hourId,"
			+ " hourMaster.hours as hours "
			+ " FROM HourMaster hourMaster "
			+ " ORDER BY hourMaster.hourId ")
		

})

/*@NamedNativeQueries(
{ @NamedNativeQuery(name = "GET_HOURE_MASTER_NATIVE", 
		query = "select hourMaster.hour_id as hourId, hourMaster.hours as hours from lab.m_hour_master as hourMaster ")
})*/
@Entity
@Table(name = "m_hour_master", schema = "public")
@SequenceGenerator(name = "m_seq_hour_master", sequenceName = "public.m_seq_hour_master", allocationSize = 1)
public class HourMaster {
	@Id
	@Column(name = "hour_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "m_seq_hour_master")
	private Integer hourId;
	
	@Column(name="hours")
	private Integer hours;
	


	/**
	 * @return the hourId
	 */
	public int getHourId() {
		return hourId;
	}

	/**
	 * @param hourId the hourId to set
	 */
	public void setHourId(Integer hourId) {
		this.hourId = hourId;
	}

	/**
	 * @return the hours
	 */
	public Integer getHours() {
		return hours;
	}

	/**
	 * @param hours the hours to set
	 */
	public void setHours(Integer hours) {
		this.hours = hours;
	}


	
}
