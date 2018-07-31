package com.param.entity.model.base;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import com.param.common.constants.Constants;
import com.param.entity.model.master.User;

import io.swagger.annotations.ApiModelProperty;

@MappedSuperclass
public abstract class CommonEntity {

	@Column(name = "is_active")
	@ApiModelProperty(value = "isActive")
	protected Boolean isActive = true;

	@Column(name = "is_deleted")
	@ApiModelProperty(value = "IsDeleted")
	protected Boolean isDeleted = false;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "added_by")
	@ApiModelProperty(value = "Added By")
	protected User addedBy = new User();

	@Column(name = "added_date")
	@ApiModelProperty(value = "Added Date")
	protected Date addedDate = new Date();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "updated_by")
	@ApiModelProperty(value = "Updated By")
	protected User updatedBy = new User();

	@Column(name = "updated_date")
	@ApiModelProperty(value = "Update Date")
	protected Date updatedDate = new Date();

	public CommonEntity() {
		this.addedBy.setUserId(Constants.UserId);
		this.updatedBy.setUserId(Constants.UserId);
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public User getAddedBy() {
		return addedBy;
	}

	public void setAddedBy(User addedBy) {
		this.addedBy = addedBy;
	}

	public Date getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}

	public User getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(User updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public User getUser() {
		User user = new User(Constants.UserId);
		return user;
	}
}
