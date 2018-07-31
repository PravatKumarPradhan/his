package com.param.global.service.global;

import java.util.List;

import com.param.entity.model.adt.AdmissionDetail;
import com.param.entity.model.master.VisitType;
import com.param.global.dto.global.AgainstGrnRequest;
import com.param.global.dto.global.AgainstPurchaseOrder;
import com.param.global.dto.global.AgainstPurchaseRequest;
import com.param.global.dto.global.AssetCategory;
import com.param.global.dto.global.AssetType;
import com.param.global.dto.global.CancelReason;
import com.param.global.dto.global.Currency;
import com.param.global.dto.global.DirectPurchaseRequestResponse;
import com.param.global.dto.global.Doctor;
import com.param.global.dto.global.FixedAssetType;
import com.param.global.dto.global.Floor;
import com.param.global.dto.global.FormulationType;
import com.param.global.dto.global.OtherChargesResponse;
import com.param.global.dto.global.Patient;
import com.param.global.dto.global.PatientDetailRequest;
import com.param.global.dto.global.PatientDetailResponse;
import com.param.global.dto.global.PatientIssueDropdown;
import com.param.global.dto.global.ProductCategory;
import com.param.global.dto.global.RejectReason;
import com.param.global.dto.global.SaleType;
import com.param.global.dto.global.ScrapReason;
import com.param.global.dto.global.Status;
import com.param.global.dto.global.Store;
import com.param.global.dto.global.StoreIndentResponse;
import com.param.global.dto.global.StoreType;
import com.param.global.dto.global.Ward;

public interface IGlobalService {

	List<AssetType> getAssetTypeList();

	List<AssetCategory> getAssetCategoryList(Integer assetTypeId);

	List<ProductCategory> getProductCategoryList(Integer assetCategoryId);

	List<FormulationType> getFormulationTypeList(Integer productCategoryId);

	Currency getCurrency();

	List<Status> getStatusList();
	
	List<VisitType> getVisitTypeList();
	
	PatientIssueDropdown getPatientIssueDropdown();
	
	List<SaleType> getSaleTypeList();

	List<RejectReason> getRejectReasonList();

	List<CancelReason> getCancelReasonList();
	
	List<Patient> patientSearch(String name);

	List<Patient> patientSearchByUhid(String uhid);
	
	List<Doctor> doctorSearch(String name);
	
	List<Ward> getWardList();
	
	List<Floor> getFloorList();
	
	List<Store> getUserStoreList();
	
	List<StoreType> getStoreTypeList();

	List<StoreIndentResponse> getStoreIndentList(Integer fromStoreId, Integer toStoreId);
	
	PatientDetailResponse patientIndentPatientSearch(PatientDetailRequest patientDetailsSearch);

	AdmissionDetail getPatientWardBedDetails(Integer adminssionId);

	Object getManufacturerList(String name);

	Object getVendorList(String name);

	Object getAgainstPurchseRequestSearch(AgainstPurchaseRequest purchaseRequestResponse);

	Object getItemsDetailsAgainstPurchseRequest(List<AgainstPurchaseRequest> againstPurchaseRequestList);

	List<OtherChargesResponse> getOtherCharges();

	DirectPurchaseRequestResponse getItemDetailsDirectPurchaseRequest(AgainstPurchaseRequest againstPurchaseRequest);

	Object getAgainstPurchseOrderSearch(AgainstPurchaseOrder againstPurchaseOrder);

	Object getItemDetailsAgainstPurchaseOrder(List<AgainstPurchaseOrder> againstPurchaseOrderList);

	List<FixedAssetType> getFixedAssetTypes();

	Object getRacks(Integer storeId);

	Object getShelves(Integer rackId);

	Object getAgainstGrn(AgainstGrnRequest request);

	List<ScrapReason> getScrapReasonList();
	
}