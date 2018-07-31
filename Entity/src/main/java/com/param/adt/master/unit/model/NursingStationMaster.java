package com.param.adt.master.unit.model;


import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.param.adt.master.global.model.OrganizationMaster;
import com.param.adt.master.global.model.UnitMaster;


@NamedQueries({
	
	@NamedQuery(name="GET_NURSING_STATION_BY_WARD", query="SELECT nsm.nursingStationId as nursingStationId, "
			+ "nsm.nursingStationDesc as nursingStationDesc "
			+ "FROM NursingStationMaster as nsm "
			+ "INNER JOIN nsm.nursingStationWardMappersList nswm "
			+ "WHERE nswm.wardId=:wardId "
			+ "AND nsm.organizationId=:organizationId "
			+ "AND nsm.unitId=:unitId "
			+ "AND nswm.status='A' "
			+ "AND nsm.status='A'"),
			
			@NamedQuery(name="GET_LIST_NURSING_STATION", 
				query=" SELECT nsm.nursingStationId as nursingStationId, "
					+ " nsm.nursingStationCode as nursingStationCode,"
					+ " nsm.nursingStationDesc as nursingStationDesc "
					+ " FROM NursingStationMaster as nsm "
					+ " WHERE nsm.organizationId=:organizationId "
					+ " AND nsm.unitId=:unitId "
					+ " AND nsm.status='A'"),	
})

@Entity
@Table(name = "m_nursing_station_master", schema = "adt")
@SequenceGenerator(name = "nursing_station_master_seq", sequenceName = "adt.nursing_station_master_seq", allocationSize = 1)
public class NursingStationMaster
{
		@Id
		@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "nursing_station_master_seq")
		@Column(name = "nursing_station_id")
		private int nursingStationId;
		
		@Column(name="nursing_station_code")
		private String nursingStationCode;
		
		@Column(name="nursing_station_desc")
		private String nursingStationDesc;
		
		@Column(name = "created_date")
		private Date createdDate;

		@Column(name = "created_by")
		private int createdBy;

		@Column(name = "user_name")
		private String userName;
		
		@Column(name="updated_by")
		private int updatedBy;
		
		@Column(name="updated_date")
		private Date updatedDate;
		
		@Column(name="status")
		private char status;
		
		@Column(name="organization_id")
		private Integer organizationId;
		
		@Column(name="unit_id")
		private Integer unitId;
		
		@ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name = "organization_id", insertable = false, nullable = false, updatable = false)
		private OrganizationMaster organizationMaster;
		
		@ManyToOne(fetch = FetchType.LAZY)
		@JoinColumn(name = "unit_id", insertable = false, nullable = false, updatable = false)
		private UnitMaster unitMaster;
		
		@OneToMany(fetch = FetchType.LAZY, mappedBy = "nursingStationMaster", cascade = CascadeType.ALL, orphanRemoval = true)
		private List<NursingStationWardMapper> nursingStationWardMappersList;
		

		public int getNursingStationId() {
			return nursingStationId;
		}

		public void setNursingStationId(int nursingStationId) {
			this.nursingStationId = nursingStationId;
		}

		public String getNursingStationCode() {
			return nursingStationCode;
		}

		public void setNursingStationCode(String nursingStationCode) {
			this.nursingStationCode = nursingStationCode;
		}

		public String getNursingStationDesc() {
			return nursingStationDesc;
		}

		public void setNursingStationDesc(String nursingStationDesc) {
			this.nursingStationDesc = nursingStationDesc;
		}

		public Date getCreatedDate() {
			return createdDate;
		}

		public void setCreatedDate(Date createdDate) {
			this.createdDate = createdDate;
		}

		public int getCreatedBy() {
			return createdBy;
		}

		public void setCreatedBy(int createdBy) {
			this.createdBy = createdBy;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public int getUpdatedBy() {
			return updatedBy;
		}

		public void setUpdatedBy(int updatedBy) {
			this.updatedBy = updatedBy;
		}

		public Date getUpdatedDate() {
			return updatedDate;
		}

		public void setUpdatedDate(Date updatedDate) {
			this.updatedDate = updatedDate;
		}

		public char getStatus() {
			return status;
		}

		public void setStatus(char status) {
			this.status = status;
		}

		public Integer getOrganizationId() {
			return organizationId;
		}

		public void setOrganizationId(Integer organizationId) {
			this.organizationId = organizationId;
		}

		public Integer getUnitId() {
			return unitId;
		}

		public void setUnitId(Integer unitId) {
			this.unitId = unitId;
		}

		public OrganizationMaster getOrganizationMaster() {
			return organizationMaster;
		}

		public void setOrganizationMaster(OrganizationMaster organizationMaster) {
			this.organizationMaster = organizationMaster;
		}

		public UnitMaster getUnitMaster() {
			return unitMaster;
		}

		public void setUnitMaster(UnitMaster unitMaster) {
			this.unitMaster = unitMaster;
		}
		
		
		
		
		
}
