package com.param.entity.model.base;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import com.param.common.constants.Constants;
import com.param.entity.model.master.Organization;
import com.param.entity.model.master.Unit;

@MappedSuperclass
public abstract class BaseEntity extends CommonEntity {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organization_id")
	protected Organization organization = new Organization();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "unit_id")
	protected Unit unit = new Unit();

	public BaseEntity() {
		this.organization.setOrganizationId(Constants.OrgId);
		this.unit.setUnitId(Constants.UnitId);
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}
}