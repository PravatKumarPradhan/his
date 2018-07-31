package com.param.common.constants;

public enum Screen {
	WalkinSale(1), StoreIndent(2), OpeningBalance(3), PurchaseRequest(4), LocalPurchaseRequest(5), StoreIndentApproval(6), LocalPurchaseRequestApproval(7), 
	PurchaseRequestApproval(8), BillList(9), PurchaseOrderCreation(10), PurchaseOrderApproval(11), GoodReceiptNote(12), GoodReceiptNoteApproval(13), 
	PendingStoreIndent(14), SaleWorkList(15), SaleReturn(16), ItemRequest(17), ItemRequestApproval(18), MaterialPicker(19),ItemCreationPending(21), 
	MaterialIssue(20), PharmacologyValidation(22),HandoverWorklist(23), AssignPicker(24), InPatientPendingIssue(25), InPatientIssueAcceptance(26), PatientIndent(27), BillableConsumption(28),
	MaterialIssueAcceptance(29), PendingIssues(30), PhysicalInspection(31), IssueAcceptance(32), IssueRejection(33), MaterialRejectAcceptance(34),
	RejectedRejection(35), GRNAgainstPo(36), QuotationEnquiry(37), EnquirySendtoVendor(38), StockDashboard(39), BatchExpiry(40), MaterialReturn(41), BatchExpiryApproval(42),
	PatientIssueConsumable(43), NonBillableConsumption(44),	ConsumableAcceptance(45), MaterialReturnAcceptance(46), PendingHandoverWorklist(47), PatientIssueRejection(48),
	PatientReturnAcceptance(49), ReturnableGatepass(50), ReturnableGatepassApproval(51),QuotationFillIn(52),ScrapRequest(53),
    PatientIssueReturn(54), ScrapRequestApproval(55), IssueWorklist(56), RequestAssetDisposalWorklist(57),WasteAssetDisposalWorklist(58), WitnessApproval(59),
    ReturnOfEmptyAmpoules(60), DeliveryWorklist(61), AcceptanceEmptyAmpoules(62),TechnicalApproval(65);

	private Integer screenId;

	public Integer getScreen() {
		return this.screenId;
	}

	private Screen(Integer screenId) {
		this.screenId = screenId;
	}
}