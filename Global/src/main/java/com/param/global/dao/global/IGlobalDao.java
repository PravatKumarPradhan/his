package com.param.global.dao.global;

import java.util.List;

import com.param.entity.model.adt.Admission;
import com.param.entity.model.adt.AdmissionDetail;
import com.param.entity.model.adt.FloorMasters;
import com.param.entity.model.adt.Priority;
import com.param.entity.model.adt.WardMasters;
import com.param.entity.model.doctor.Doctor;
import com.param.entity.model.doctor.DoctorSpecialityMapper;
import com.param.entity.model.inventory.Batch;
import com.param.entity.model.inventory.IndentType;
import com.param.entity.model.inventory.StockTransaction;
import com.param.entity.model.inventory.StoreIndent;
import com.param.entity.model.inventory.StoreIndentDetail;
import com.param.entity.model.inventory.StoreStock;
import com.param.entity.model.master.AssetCategory;
import com.param.entity.model.master.AssetType;
import com.param.entity.model.master.CancelReason;
import com.param.entity.model.master.Currency;
import com.param.entity.model.master.DiscountCategory;
import com.param.entity.model.master.DiscountType;
import com.param.entity.model.master.FixedAssetType;
import com.param.entity.model.master.FormulationType;
import com.param.entity.model.master.Gender;
import com.param.entity.model.master.Generic;
import com.param.entity.model.master.Item;
import com.param.entity.model.master.Manufacturer;
import com.param.entity.model.master.Order;
import com.param.entity.model.master.OtherCharge;
import com.param.entity.model.master.PaymentMode;
import com.param.entity.model.master.PharmacologicalClassification;
import com.param.entity.model.master.Prefix;
import com.param.entity.model.master.ProductCategory;
import com.param.entity.model.master.RejectReason;
import com.param.entity.model.master.ReturnReason;
import com.param.entity.model.master.ScrapReason;
import com.param.entity.model.master.Screen;
import com.param.entity.model.master.ScreenStatus;
import com.param.entity.model.master.Status;
import com.param.entity.model.master.StorageUnit;
import com.param.entity.model.master.Store;
import com.param.entity.model.master.StoreType;
import com.param.entity.model.master.StrengthUnit;
import com.param.entity.model.master.Tax;
import com.param.entity.model.master.User;
import com.param.entity.model.master.Vendor;
import com.param.entity.model.master.VisitType;
import com.param.entity.model.opd.Encounter;
import com.param.entity.model.patient.PatientRegistration;
import com.param.entity.model.pharmacy.IssueDetail;
import com.param.entity.model.pharmacy.IssueDetailBatchMapper;
import com.param.entity.model.pharmacy.SaleDetail;
import com.param.entity.model.pharmacy.SaleType;
import com.param.entity.model.procurement.PurchaseOrder;
import com.param.entity.model.procurement.PurchaseOrderStaged;
import com.param.entity.model.procurement.PurchaseRequest;
import com.param.global.dto.global.AgainstGrnDetailResponse;
import com.param.global.dto.global.AgainstGrnRequest;
import com.param.global.dto.global.AgainstGrnResponse;
import com.param.global.dto.global.AgainstPurchaseOrder;
import com.param.global.dto.global.AgainstPurchaseRequest;
import com.param.global.dto.global.PatientDetailRequest;
import com.param.global.dto.global.PurchaseOrderDetailResponse;
import com.param.global.dto.global.PurchaseRequestDetailResponse;
import com.param.global.dto.global.Rack;
import com.param.global.dto.global.Shelf;
import com.param.global.dto.items.ItemsDetailsResponse;
import com.param.global.dto.items.UomResponse;

public interface IGlobalDao {

	Integer getBatchQuantity(Long batchId);

	Integer getStoreStockByBatch(Integer storeId, Long batchId);

	Batch getBatch(Integer storeId, Long batchId);

	Admission getAdmission(Integer admissionId);

	DiscountCategory getDiscountCategory(Integer discountCategoryId);

	DiscountType getDiscountType(Integer discountTypeId);

	Doctor getDoctor(Integer doctorId);

	Order getOrder(Integer orderId);

	PatientRegistration getPatient(Integer patientId);
	
	Admission admissionPatient(PatientDetailRequest patientDetailsSearch);
	
	List<Encounter> encounterPatient(PatientDetailRequest patientDetailsSearch);

	Status getStatus(Integer statusId);

	Store getStore(Integer storeId);

	VisitType getVisitType(Integer visitTypeId);

	Screen getScreen(Integer screenId);
	
	SaleDetail getSaleDetailById(Long id);

	List<ScreenStatus> getScreenStatus(Integer screenId);

	List<SaleType> getSalesTypeList();

	List<Prefix> getPrefixList();

	List<Gender> getGenderList();

	List<DiscountCategory> getDiscountCategoryList();

	List<Store> getStoreList();

	List<Store> getUserStoreList();
	
	List<StoreType> getStoreTypeList();

	List<IndentType> getIndentTypeList();

	List<Priority> getPriorityList();

	List<Batch> getBatchDetails(Integer id);

	Long getStoreStockByItem(Integer storeId, Integer itemId);
	
	StoreStock getStoreStockByBatchAndStore(Long batchId, Integer storeId);

	Long getLastMonthSale(Integer storeId, Integer itemId);

	Long getCurrentMonthSale(Integer storeId, Integer itemId);

	Integer getStoreMaxQuantityByItem(Integer storeId, Integer itemId);

	Long getStoreStockInTransitByItem(Integer storeId, Integer itemId);

	List<AssetType> getAssetTypeList();

	List<AssetCategory> getAssetCategoryList(Integer assetTypeId);

	List<ProductCategory> getProductCategoryList(Integer assetCategoryId);

	List<UomResponse> getUnitOfMeasurementListByItem(Integer itemId);

	List<FormulationType> getFormulationTypeList(Integer productCategoryId);

	List<Status> getStatusList();

	List<VisitType> getVisitTypeList();
	
	List<SaleType> getSaleTypeList();
	
	Currency getCurrency();

	List<RejectReason> getRejectReasonList();

	List<CancelReason> getCancelReasonList();

	List<StoreIndent> getStoreIndentList(Integer fromStoreId, Integer toStoreId);

	List<StoreIndentDetail> getStoreIndentDetailsList(Integer storeIndentId);

	List<PharmacologicalClassification> getPharmacologicalClassificationList();

	List<Manufacturer> getManufacturerList();

	List<StrengthUnit> getStrengthUnitList();

	List<StorageUnit> getStorageUnitList();

	List<Generic> getGenericList(Integer productCategoryId);

	List<Tax> getTaxList();
	
	List<PatientRegistration> patientSearchByName(String name);
	
	List<PatientRegistration> patientSearchByUhid(String uhid);
	
	List<DoctorSpecialityMapper> doctorSearchByName(String name);
	
	List<WardMasters> getWardList();
	
	List<FloorMasters> getFloorList();
	
	List<DoctorSpecialityMapper> doctorSearchByIdValue(Integer id);
	
	AdmissionDetail getPatientWardBedDetails(Integer adminssionId);

	List<StoreIndentDetail> getStoreIndentDetails(List<Integer> storeIndentDetailIds);

	List<UomResponse> getBatchUomList(Long batchId);

	ItemsDetailsResponse itemsDetail(int id);

	List<Manufacturer> getManufacturerList(String name);

	List<Vendor> getVendorList(String name);
	
	User getUserById(Integer id);
	
	List<User> getUser();
	
	Item getItemById(Integer id);

	List<PurchaseRequest> searchLocalPuchaseRequest(AgainstPurchaseRequest purchaseRequestResponse,List<Integer> statusIds);

	List<PurchaseRequestDetailResponse> getItemsDetailsAgainstPr(Long Id, Integer itemId);

	List<OtherCharge> getOtherCharges();

	List<PaymentMode> getPaymentModeList();
	
	Batch getBatch(Long id);

	List<PurchaseOrder> searchPurchaseOrder(AgainstPurchaseOrder againstPurchaseOrder, List<Integer> statusIds);

	List<PurchaseOrderDetailResponse> getItemsDetailsAgainstPO(Integer Id, Integer itemId);

	PurchaseOrder getPurchaseOrderList(Integer id);

	List<PurchaseOrderStaged> getPurchaseOrderStaged(Integer id);
	
	StoreStock saveStoreStockLeaseQuantity(StoreStock storeStock);
	
	StoreStock updateStoreStockLeaseQuantity(StoreStock storeStock);
	
	Batch updateBatchLeaseQuantity(Batch batch);
	
	StockTransaction saveStockTransaction(StockTransaction stockTransaction);

	PurchaseRequest getPurchaseRequestList(Long id);
	
	List<IssueDetailBatchMapper> getIssueDetailBatchMapper(Integer id);
	
	IssueDetailBatchMapper getIssueDetailBatchMapperById(Integer id);
	
	List<IssueDetail> getIssueDeatilByIssueId(Integer issueId);

	Batch saveBatch(Batch batch);

	List<FixedAssetType> getFixedAssetTypes();
	
	List<ReturnReason> getReturnReason();

	List<Rack> getRacks(Integer storeId);

	List<Shelf> getShelves(Integer rackId);

	List<AgainstGrnResponse> getGrn(AgainstGrnRequest request);

	List<AgainstGrnDetailResponse> getGrnDetails(List<Integer> grnId);

	List<ScrapReason> getScrapReasonList();
}
