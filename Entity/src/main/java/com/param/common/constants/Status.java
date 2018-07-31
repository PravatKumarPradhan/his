package com.param.common.constants;

public enum Status {
	New(1), 
	Pending(2), 
	PartiallyIssued(3), 
	Completed(4), 
	Approved(5), 
	PartiallyApproved(6), 
	Reject(7), 
	Closed(8), 
	Cancelled(9), 
	PendingApproval(10), 
	Intervention(11), 
	InterventionResolved(12), 
	ActiveMedication(13), 
	Prescribed(14), 
	PendingAssignPicker(15), 
	Issued(16), 
	PendingIssue(17),
	PartiallyCompleted(18);

	private Integer statusId;

	public Integer getStatus() {
		return this.statusId;
	}

	private Status(Integer statusId) {
		this.statusId = statusId;
	}
}