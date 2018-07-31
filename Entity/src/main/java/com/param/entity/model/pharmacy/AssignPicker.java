package com.param.entity.model.pharmacy;

import java.io.Serializable;
import javax.persistence.*;

import com.param.entity.model.base.BaseEntity;
import com.param.entity.model.master.Status;
import com.param.entity.model.master.User;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity(name="AssignPicker")
@Table(name="t_assign_picker",schema="pharmacy")
public class AssignPicker extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer id;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="picker_id", nullable=false)
	private User picker;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="prescription_id")
	private Prescription prescription;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="status_id")
	private Status status;

	//bi-directional many-to-one association to AssignPickerDetail
	@OneToMany(mappedBy="assignPicker",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<AssignPickerDetail> assignPickerDetails;

	public AssignPicker() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getPicker() {
		return picker;
	}

	public void setPicker(User picker) {
		this.picker = picker;
	}

	public Prescription getPrescription() {
		return prescription;
	}

	public void setPrescription(Prescription prescription) {
		this.prescription = prescription;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<AssignPickerDetail> getAssignPickerDetails() {
		return assignPickerDetails;
	}

	public void setAssignPickerDetails(List<AssignPickerDetail> assignPickerDetails) {
		this.assignPickerDetails = assignPickerDetails;
	}

	public AssignPickerDetail addAssignPickerDetail(AssignPickerDetail assignPickerDetail) {
		if (getAssignPickerDetails() == null)
			this.assignPickerDetails = new ArrayList<AssignPickerDetail>();
		
		getAssignPickerDetails().add(assignPickerDetail);
		assignPickerDetail.setAssignPicker(this);

		return assignPickerDetail;
	}
	
	public AssignPicker(User picker, Prescription prescription, Status status) {
		super();
		this.picker = picker;
		this.prescription = prescription;
		this.status = status;
	}

}