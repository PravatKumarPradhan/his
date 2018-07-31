package com.param.global.service.global;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.entity.model.adt.Admission;
import com.param.entity.model.adt.AdmissionDetail;
import com.param.entity.model.adt.FloorMasters;
import com.param.entity.model.adt.WardMasters;
import com.param.entity.model.doctor.DoctorSpecialityMapper;
import com.param.entity.model.master.Manufacturer;
import com.param.entity.model.master.OtherCharge;
import com.param.entity.model.master.Vendor;
import com.param.entity.model.master.VisitType;
import com.param.entity.model.opd.Encounter;
import com.param.entity.model.patient.PatientRegistration;
import com.param.entity.model.procurement.PurchaseOrder;
import com.param.entity.model.procurement.PurchaseOrderDetail;
import com.param.entity.model.procurement.PurchaseRequest;
import com.param.entity.model.procurement.PurchaseRequestDetail;
import com.param.global.dao.global.IGlobalDao;
import com.param.global.dao.items.IItemsDao;
import com.param.global.dto.global.AgainstGrnDetailResponse;
import com.param.global.dto.global.AgainstGrnRequest;
import com.param.global.dto.global.AgainstGrnResponse;
import com.param.global.dto.global.AgainstPOSearchResponse;
import com.param.global.dto.global.AgainstPurchaseOrder;
import com.param.global.dto.global.AgainstPurchaseRequest;
import com.param.global.dto.global.AgainstPurchaseRequestResponse;
import com.param.global.dto.global.AssetCategory;
import com.param.global.dto.global.AssetType;
import com.param.global.dto.global.CancelReason;
import com.param.global.dto.global.Currency;
import com.param.global.dto.global.DirectPurchaseRequestResponse;
import com.param.global.dto.global.Doctor;
import com.param.global.dto.global.FixedAssetType;
import com.param.global.dto.global.Floor;
import com.param.global.dto.global.FormulationType;
import com.param.global.dto.global.ItemResponse;
import com.param.global.dto.global.ManufacturerResponseDto;
import com.param.global.dto.global.OtherChargesResponse;
import com.param.global.dto.global.Patient;
import com.param.global.dto.global.PatientDetailRequest;
import com.param.global.dto.global.PatientDetailResponse;
import com.param.global.dto.global.PatientIssueDropdown;
import com.param.global.dto.global.ProductCategory;
import com.param.global.dto.global.PurchaseOrderDetailResponse;
import com.param.global.dto.global.PurchaseOrderResponse;
import com.param.global.dto.global.PurchaseOrderStaged;
import com.param.global.dto.global.PurchaseRequestDetailResponse;
import com.param.global.dto.global.PurchaseRequestSearchDto;
import com.param.global.dto.global.RejectReason;
import com.param.global.dto.global.SaleType;
import com.param.global.dto.global.ScrapReason;
import com.param.global.dto.global.Status;
import com.param.global.dto.global.Store;
import com.param.global.dto.global.StoreIndentResponse;
import com.param.global.dto.global.StoreType;
import com.param.global.dto.global.VendorResponseDto;
import com.param.global.dto.global.VisitDetails;
import com.param.global.dto.global.Ward;
import com.param.global.dto.items.ItemsDetailsListResponse;
import com.param.global.utility.Util;

import in.param.exception.ApplicationException;

@Service("GlobalService")
public class GlobalService implements IGlobalService {

	@Autowired
	IGlobalDao GlobalDao;

	@Autowired
	IItemsDao ItemsDao;

	@Override
	@Transactional
	public List<AssetType> getAssetTypeList() {
		List<AssetType> response = new ArrayList<AssetType>();

		for (com.param.entity.model.master.AssetType assetType : GlobalDao.getAssetTypeList()) {
			response.add(new AssetType(assetType.getId(), assetType.getType()));
		}
		return response;
	}

	@Override
	@Transactional
	public List<AssetCategory> getAssetCategoryList(Integer assetTypeId) {
		List<AssetCategory> response = new ArrayList<AssetCategory>();

		for (com.param.entity.model.master.AssetCategory assetCategory : GlobalDao.getAssetCategoryList(assetTypeId)) {
			response.add(new AssetCategory(assetCategory.getId(), assetCategory.getCategory()));

		}
		return response;

	}

	@Override
	@Transactional
	public List<ProductCategory> getProductCategoryList(Integer assetCategoryId) {
		List<ProductCategory> response = new ArrayList<ProductCategory>();

		for (com.param.entity.model.master.ProductCategory productCategory : GlobalDao
				.getProductCategoryList(assetCategoryId)) {
			response.add(new ProductCategory(productCategory.getId(), productCategory.getCategory()));
		}
		return response;
	}

	@Override
	@Transactional
	public List<FormulationType> getFormulationTypeList(Integer productCategoryId) {
		List<FormulationType> response = new ArrayList<FormulationType>();
		for (com.param.entity.model.master.FormulationType formulationType : GlobalDao
				.getFormulationTypeList(productCategoryId)) {
			response.add(new FormulationType(formulationType.getId(), formulationType.getType()));
		}
		return response;
	}

	@Override
	@Transactional
	public Currency getCurrency() {

		com.param.entity.model.master.Currency currency = GlobalDao.getCurrency();

		Currency response = new Currency(currency.getCurrencyId(), currency.getCurrencyName(),
				currency.getCurrencySymbol());

		return response;
	}

	@Override
	@Transactional
	public List<Status> getStatusList() {
		List<Status> response = new ArrayList<Status>();

		for (com.param.entity.model.master.Status status : GlobalDao.getStatusList()) {
			response.add(new Status(status.getId(), status.getStatus()));
		}
		return response;
	}

	@Override
	@Transactional
	public List<VisitType> getVisitTypeList() {
		List<VisitType> response = new ArrayList<VisitType>();

		for (com.param.entity.model.master.VisitType visitType : GlobalDao.getVisitTypeList()) {
			response.add(new VisitType(visitType.getVisitTypeId(), visitType.getVisitTypeName()));
		}
		return response;
	}

	@Override
	@Transactional
	public PatientIssueDropdown getPatientIssueDropdown() {
		PatientIssueDropdown response = new PatientIssueDropdown();

		for (com.param.entity.model.master.VisitType visitType : GlobalDao.getVisitTypeList()) {
			response.addVisitType(new com.param.global.dto.global.VisitType(visitType.getVisitTypeId(),
					visitType.getVisitTypeName()));
		}

		for (WardMasters ward : GlobalDao.getWardList()) {
			response.addWard(new Ward(ward.getWardId(), ward.getWardName()));
		}

		for (FloorMasters floor : GlobalDao.getFloorList()) {
			response.addFloor(new Floor(floor.getFloorId(), floor.getFloorName()));
		}

		for (com.param.entity.model.master.Store store : GlobalDao.getStoreList()) {
			response.addStore(new com.param.global.dto.global.Store(store.getStoreId(), store.getStoreName()));
		}

		return response;

	}

	@Override
	@Transactional
	public List<SaleType> getSaleTypeList() {
		List<SaleType> response = new ArrayList<SaleType>();

		for (com.param.entity.model.pharmacy.SaleType saleType : GlobalDao.getSaleTypeList()) {
			response.add(new SaleType(saleType.getId(), saleType.getType()));
		}
		return response;
	}

	@Override
	@Transactional
	public List<RejectReason> getRejectReasonList() {
		List<RejectReason> response = new ArrayList<RejectReason>();

		for (com.param.entity.model.master.RejectReason rejectReason : GlobalDao.getRejectReasonList()) {
			response.add(new RejectReason(rejectReason.getId(), rejectReason.getCode(), rejectReason.getReason()));
		}
		return response;
	}

	@Override
	@Transactional
	public List<CancelReason> getCancelReasonList() {
		List<CancelReason> response = new ArrayList<CancelReason>();

		for (com.param.entity.model.master.CancelReason cancelReason : GlobalDao.getCancelReasonList()) {
			response.add(new CancelReason(cancelReason.getId(), cancelReason.getCode(), cancelReason.getReason()));
		}
		return response;
	}

	@Override
	@Transactional
	public List<Store> getUserStoreList() {
		List<Store> response = new ArrayList<Store>();

		for (com.param.entity.model.master.Store store : GlobalDao.getUserStoreList()) {
			response.add(new Store(store.getStoreId(), store.getStoreName()));
		}

		return response;
	}

	@Override
	@Transactional
	public List<StoreType> getStoreTypeList() {
		List<StoreType> response = new ArrayList<StoreType>();

		for (com.param.entity.model.master.StoreType storeType : GlobalDao.getStoreTypeList()) {
			response.add(new StoreType(storeType.getId(), storeType.getType()));
		}
		return response;
	}

	@Override
	@Transactional
	public List<StoreIndentResponse> getStoreIndentList(Integer fromStoreId, Integer toStoreId) {
		List<StoreIndentResponse> response = new ArrayList<StoreIndentResponse>();

		for (com.param.entity.model.inventory.StoreIndent indent : GlobalDao.getStoreIndentList(fromStoreId,
				toStoreId)) {
			StoreIndentResponse result = new StoreIndentResponse(indent.getId(), indent.getAddedDate(),
					indent.getIndentNo(), indent.getFromStore().getStoreName(), indent.getAddedBy().getUserName(),
					indent.getApprovedBy().getUserName(), indent.getApprovedDate());

			for (com.param.entity.model.inventory.StoreIndentDetail detail : GlobalDao
					.getStoreIndentDetailsList(indent.getId())) {
				result.addItems(new ItemResponse(detail.getId(), detail.getItem().getId(), detail.getItem().getCode(),
						detail.getItem().getName(), detail.getAuthorizedQuantity(), detail.getPendingQuantity()));
			}

			if (result.getItems() != null && !result.getItems().isEmpty()) {
				response.add(result);
			}
		}

		return response;
	}

	@Override
	@Transactional
	public PatientDetailResponse patientIndentPatientSearch(PatientDetailRequest patientDetailsSearch) {
		PatientDetailResponse patientDetails = new PatientDetailResponse();
		if (patientDetailsSearch.getVisitType() == 1) {
			PatientRegistration patient = GlobalDao.getPatient(patientDetailsSearch.getPatientId());
			String patientName = patient.getPrefix().getPrefixCode() + " " + patient.getFirstName() + " " + patient.getMiddleName() + " " + patient.getLastName();
			Integer age = Util.getAgeByBirthDate(patient.getDob().toLocalDateTime().toLocalDate());
			String patientDetail = patientName + " | " + age + " | " + patient.getGender().getGenderCode();
			patientDetails = new PatientDetailResponse(patient.getPatientId(), patientName, patientDetail,
					patient.getUhidNumber());
			VisitType visitType = GlobalDao.getVisitType(patientDetailsSearch.getVisitType());
			patientDetails.setVisitTypeId(visitType.getVisitTypeId());
			patientDetails.setVisitType(visitType.getVisitTypeCode());
			List<VisitDetails> visitDetails = new ArrayList<VisitDetails>();
			for (Encounter encounter : GlobalDao.encounterPatient(patientDetailsSearch)) {
				VisitDetails visitDetail = new VisitDetails(encounter.getDoctor().getDoctorId(),
						encounter.getEncounterNumber());
				visitDetail.setEncounterId(encounter.getEncounterId());
				visitDetails.add(visitDetail);
			}
			patientDetails.setVisitDetails(visitDetails);
		} else {
			PatientRegistration patient = GlobalDao.getPatient(patientDetailsSearch.getPatientId());
			String patientName = patient.getPrefix().getPrefixCode() + " " + patient.getFirstName() + " " + patient.getMiddleName() + " " + patient.getLastName();
			Integer age = Util.getAgeByBirthDate(patient.getDob().toLocalDateTime().toLocalDate());
			String patientDetail = patientName + " | " + age + " | " + patient.getGender().getGenderCode();
			patientDetails = new PatientDetailResponse(patient.getPatientId(), patientName, patientDetail,
					patient.getUhidNumber());
			VisitType visitType = GlobalDao.getVisitType(patientDetailsSearch.getVisitType());
			patientDetails.setVisitTypeId(visitType.getVisitTypeId());
			patientDetails.setVisitType(visitType.getVisitTypeCode());
			Admission admission = GlobalDao.admissionPatient(patientDetailsSearch);
			List<VisitDetails> visitDetails = new ArrayList<VisitDetails>();
			VisitDetails visitDetail = new VisitDetails(admission.getDoctor().getDoctorId(),
					admission.getAdmissionNumber());
			visitDetail.setAdmissionId(admission.getAdmissionId());
			visitDetails.add(visitDetail);
			if (admission.getAdmissionDetail() != null) {
				patientDetails.setWardId(admission.getAdmissionDetail().get(0).getWardMaster().getWardId());
				patientDetails.setWardName(admission.getAdmissionDetail().get(0).getWardMaster().getWardName());
				patientDetails.setBedId(admission.getAdmissionDetail().get(0).getBedMaster().getBedId());
				patientDetails.setBedNo(admission.getAdmissionDetail().get(0).getBedMaster().getBedNumber());
			}
			patientDetails.setVisitDetails(visitDetails);
		}
		return patientDetails;

	}

	@Override
	@Transactional
	public List<Patient> patientSearch(String name) {

		List<Patient> patientDetailsList = new ArrayList<Patient>();
		for (PatientRegistration patient : GlobalDao.patientSearchByName(name)) {
			String patientName = patient.getPrefix().getPrefixCode() + " " + patient.getFirstName() + " " + patient.getMiddleName() + " " + patient.getLastName();
			String uhid = patient.getUhidNumber();
			Integer age = Util.getAgeByBirthDate(patient.getDob().toLocalDateTime().toLocalDate());
			String patientDetails = uhid + " | " + patientName + " | " + age + " | "
					+ patient.getGender().getGenderCode() + " | " + patient.getMobileNumber();
			Patient patientDetail = new Patient(patient.getPatientId(), patientName, uhid, patientDetails);
			patientDetailsList.add(patientDetail);
		}
		return patientDetailsList;
	}

	@Override
	@Transactional
	public List<Patient> patientSearchByUhid(String uhid) {

		List<Patient> patientDetailsList = new ArrayList<Patient>();
		for (PatientRegistration patient : GlobalDao.patientSearchByUhid(uhid)) {
			String patientName = patient.getPrefix().getPrefixCode() + " " + patient.getFirstName() + " " + patient.getMiddleName() + " " + patient.getLastName();
			String uhidNumber = patient.getUhidNumber();
			Integer age = Util.getAgeByBirthDate(patient.getDob().toLocalDateTime().toLocalDate());
			String patientDetails = uhid + " | " + patientName + " | " + age + " | "
					+ patient.getGender().getGenderCode() + " | " + patient.getMobileNumber();
			Patient patientDetail = new Patient(patient.getPatientId(), patientName, uhidNumber, patientDetails);
			patientDetailsList.add(patientDetail);
		}
		return patientDetailsList;
	}

	@Override
	@Transactional
	public List<Doctor> doctorSearch(String name) {
		List<Doctor> doctorDetailsList = new ArrayList<Doctor>();
		for (DoctorSpecialityMapper doctorSpeciality : GlobalDao.doctorSearchByName(name)) {
			String doctorName = doctorSpeciality.getDoctor().getFirstName() + " "
					+ doctorSpeciality.getDoctor().getMiddleName() + " " + doctorSpeciality.getDoctor().getLastName();
			String doctorDetails = doctorName + " | " + doctorSpeciality.getSpeciality().getSpecialityName();
			Doctor doctorDetail = new Doctor(doctorSpeciality.getDoctor().getDoctorId(), doctorName,
					doctorSpeciality.getSpeciality().getSpecialityName(), doctorDetails);
			doctorDetailsList.add(doctorDetail);
		}
		return doctorDetailsList;
	}

	@Override
	@Transactional
	public List<Ward> getWardList() {
		List<Ward> wards = new ArrayList<Ward>();
		for (WardMasters ward : GlobalDao.getWardList()) {
			Ward wardObj = new Ward(ward.getWardId(), ward.getWardName());
			wards.add(wardObj);
		}
		return wards;
	}

	@Override
	@Transactional
	public List<Floor> getFloorList() {
		List<Floor> floors = new ArrayList<Floor>();
		for (FloorMasters floor : GlobalDao.getFloorList()) {
			Floor floorObj = new Floor(floor.getFloorId(), floor.getFloorName());
			floors.add(floorObj);
		}
		return floors;
	}

	@Override
	public AdmissionDetail getPatientWardBedDetails(Integer admissionId) throws ApplicationException {
		try {
			return GlobalDao.getPatientWardBedDetails(admissionId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	@Transactional
	public Object getVendorList(String name) {
		List<VendorResponseDto> vendorList = new ArrayList<VendorResponseDto>();
		for (Vendor vendor : GlobalDao.getVendorList(name)) {

			VendorResponseDto vendorResponse = new VendorResponseDto(vendor.getId(),
					vendor.getCode() + "|" + vendor.getName() + "|" + vendor.getAddress(), vendor.getCode(),
					vendor.getName(), vendor.getAddress());
			vendorList.add(vendorResponse);
		}
		return vendorList;
	}

	@Override
	@Transactional
	public Object getManufacturerList(String name) {
		List<ManufacturerResponseDto> manufacturerList = new ArrayList<ManufacturerResponseDto>();
		for (Manufacturer manufacture : GlobalDao.getManufacturerList(name)) {

			ManufacturerResponseDto manufacturerResponse = new ManufacturerResponseDto(manufacture.getId(),
					manufacture.getCode() + "|" + manufacture.getName() + "|" + manufacture.getAddress(),
					manufacture.getCode(), manufacture.getName(), manufacture.getAddress());
			manufacturerList.add(manufacturerResponse);
		}
		return manufacturerList;
	}

	@Override
	@Transactional
	public Object getAgainstPurchseRequestSearch(AgainstPurchaseRequest againstPurchaseRequest) {

		againstPurchaseRequest.setToDate((new DateTime(againstPurchaseRequest.getToDate())).plusDays(1).toDate());

		List<PurchaseRequestSearchDto> purchaseRequestResponseList = new ArrayList<PurchaseRequestSearchDto>();
		List<Integer> statusIds = new ArrayList<Integer>();
		statusIds.add(com.param.common.constants.Status.Approved.getStatus());
		statusIds.add(com.param.common.constants.Status.PartiallyApproved.getStatus());

		for (PurchaseRequest purchaseRequest : GlobalDao.searchLocalPuchaseRequest(againstPurchaseRequest, statusIds)) {

			PurchaseRequestSearchDto purchaseReqResponseDto = new PurchaseRequestSearchDto(purchaseRequest.getId(),
					purchaseRequest.getPurchaseNo(), purchaseRequest.getAddedDate(),
					purchaseRequest.getStore().getStoreId(), purchaseRequest.getStore().getStoreName(),
					purchaseRequest.getAddedBy().getUserName(), purchaseRequest.getApprovedBy().getUserName());

			List<PurchaseRequestDetailResponse> purchaseRequestDeatailResponseList = new ArrayList<PurchaseRequestDetailResponse>();

			for (PurchaseRequestDetail purchaseReqDetails : purchaseRequest.getPurchaseRequestDetailList()) {

				PurchaseRequestDetailResponse pucrhaseRequestDeatilResp = new PurchaseRequestDetailResponse(
						purchaseReqDetails.getId(), purchaseReqDetails.getItem().getId(),
						purchaseReqDetails.getItem().getCode(), purchaseReqDetails.getItem().getName(),
						purchaseReqDetails.getUomType().getId(), purchaseReqDetails.getUomType().getType(),
						purchaseReqDetails.getUomUnit().getId(), purchaseReqDetails.getUomUnit().getUnits(),
						purchaseReqDetails.getApprovedQuantity() != null ? purchaseReqDetails.getApprovedQuantity()
								: 0);
				purchaseRequestDeatailResponseList.add(pucrhaseRequestDeatilResp);

			}
			purchaseReqResponseDto.setItems(purchaseRequestDeatailResponseList);
			purchaseRequestResponseList.add(purchaseReqResponseDto);
		}

		return purchaseRequestResponseList;
	}

	@Override
	@Transactional
	public Object getItemsDetailsAgainstPurchseRequest(List<AgainstPurchaseRequest> againstPurchaseRequestList) {
		AgainstPurchaseRequestResponse againstPurchaseReq = new AgainstPurchaseRequestResponse();
		List<PurchaseRequestSearchDto> response = new ArrayList<PurchaseRequestSearchDto>();
		for (AgainstPurchaseRequest againstPurchaseRequest : againstPurchaseRequestList) {

			PurchaseRequest purchaseRequest = GlobalDao.getPurchaseRequestList((long) againstPurchaseRequest.getPrId());

			PurchaseRequestSearchDto purchaseRequestSearchDto = new PurchaseRequestSearchDto(purchaseRequest.getId(),
					purchaseRequest.getPurchaseNo());
			List<PurchaseRequestDetailResponse> prDeatailList = new ArrayList<PurchaseRequestDetailResponse>();
			for (Integer itemId : againstPurchaseRequest.getItemIds()) {

				for (PurchaseRequestDetailResponse purchasedReqDetail : GlobalDao
						.getItemsDetailsAgainstPr(purchaseRequest.getId(), itemId)) {

					purchasedReqDetail.setPrQuantity(
							purchasedReqDetail.getPrQuantity() != null ? purchasedReqDetail.getPrQuantity() : 0);
					purchasedReqDetail.setUom(GlobalDao.getUnitOfMeasurementListByItem(itemId));
					prDeatailList.add(purchasedReqDetail);
				}

				purchaseRequestSearchDto.setItems(prDeatailList);
			}
			if (purchaseRequestSearchDto.getItems() != null && !purchaseRequestSearchDto.getItems().isEmpty()) {
				response.add(purchaseRequestSearchDto);
			}
		}
		againstPurchaseReq.setPurchaseRequestList(response);
		againstPurchaseReq.setOtherChargesList(getOtherCharges());
		return againstPurchaseReq;
	}

	@Override
	@Transactional
	public List<OtherChargesResponse> getOtherCharges() {

		List<OtherChargesResponse> otherChargesList = new ArrayList<OtherChargesResponse>();
		for (OtherCharge otherCharges : GlobalDao.getOtherCharges()) {

			OtherChargesResponse response = new OtherChargesResponse(otherCharges.getId(),
					otherCharges.getDescription());
			otherChargesList.add(response);
		}

		return otherChargesList;
	}

	@Override
	@Transactional
	public DirectPurchaseRequestResponse getItemDetailsDirectPurchaseRequest(
			AgainstPurchaseRequest againstPurchaseRequest) {

		DirectPurchaseRequestResponse directPoResponse = new DirectPurchaseRequestResponse();

		List<ItemsDetailsListResponse> response = ItemsDao.itemsDetails(againstPurchaseRequest.getItemIds(),
				againstPurchaseRequest.getStoreId());

		for (ItemsDetailsListResponse item : response) {

			Long storeStock = GlobalDao.getStoreStockByItem(againstPurchaseRequest.getStoreId(), item.getItemId());
			Long stockInTransit = GlobalDao.getStoreStockInTransitByItem(againstPurchaseRequest.getStoreId(),
					item.getItemId());
			Long lastMonthSale = GlobalDao.getLastMonthSale(againstPurchaseRequest.getStoreId(), item.getItemId());
			Long currentMonthSale = GlobalDao.getCurrentMonthSale(againstPurchaseRequest.getStoreId(),
					item.getItemId());
			Integer maxStoreQuantity = GlobalDao.getStoreMaxQuantityByItem(againstPurchaseRequest.getStoreId(),
					item.getItemId());
			Long maxOrderQuantity = maxStoreQuantity - (storeStock + stockInTransit);

			item.setStockAvailable(storeStock);
			item.setStockInTransit(stockInTransit);
			item.setLastMonthSale(lastMonthSale);
			item.setCurrentMonthSale(currentMonthSale);
			item.setMaxOrderQuantity(maxOrderQuantity);
			item.setUom(GlobalDao.getUnitOfMeasurementListByItem(item.getItemId()));
		}
		directPoResponse.setItemDetailsList(response);
		directPoResponse.setOtherChargesList(getOtherCharges());
		return directPoResponse;
	}

	@Override
	@Transactional
	public Object getAgainstPurchseOrderSearch(AgainstPurchaseOrder againstPurchaseOrder) {

		againstPurchaseOrder.setToDate((new DateTime(againstPurchaseOrder.getToDate())).plusDays(1).toDate());
		List<AgainstPOSearchResponse> againstPOSearchResponseList = new ArrayList<AgainstPOSearchResponse>();
		List<Integer> statusIds = new ArrayList<Integer>();
		statusIds.add(com.param.common.constants.Status.Approved.getStatus());
		statusIds.add(com.param.common.constants.Status.PartiallyApproved.getStatus());

		for (PurchaseOrder purchaseOrder : GlobalDao.searchPurchaseOrder(againstPurchaseOrder, statusIds)) {

			AgainstPOSearchResponse againstPOSearchResponse = new AgainstPOSearchResponse(purchaseOrder.getId(),
					purchaseOrder.getVendor().getName(), purchaseOrder.getStore().getStoreId(),
					purchaseOrder.getStore().getStoreName(), purchaseOrder.getPoNumber(), purchaseOrder.getAddedDate());

			List<PurchaseOrderDetailResponse> purchaseOrderDetailResponseList = new ArrayList<PurchaseOrderDetailResponse>();

			for (PurchaseOrderDetail detail : purchaseOrder.getPurchaseOrderDetailList()) {

				PurchaseOrderDetailResponse purchaseOrderDetailResp = new PurchaseOrderDetailResponse(detail.getId(),
						detail.getItem().getId(), detail.getItem().getCode(), detail.getItem().getName(),
						detail.getPoApprovedQuantity() != null ? detail.getPoApprovedQuantity() : 0,
						detail.getBonusApprovedQuantity() != null ? detail.getBonusApprovedQuantity() : 0,
						detail.getCop(), detail.getMrp());
				purchaseOrderDetailResponseList.add(purchaseOrderDetailResp);

			}
			againstPOSearchResponse.setItems(purchaseOrderDetailResponseList);
			againstPOSearchResponseList.add(againstPOSearchResponse);
		}

		return againstPOSearchResponseList;
	}

	@Override
	@Transactional
	public Object getItemDetailsAgainstPurchaseOrder(List<AgainstPurchaseOrder> againstPurchaseOrderList) {
		PurchaseOrderResponse purchaseOrderResponseList = new PurchaseOrderResponse();
		List<AgainstPOSearchResponse> response = new ArrayList<AgainstPOSearchResponse>();
		for (AgainstPurchaseOrder againstPurchaseOrder : againstPurchaseOrderList) {
			PurchaseOrder purchaseOrder = GlobalDao.getPurchaseOrderList(againstPurchaseOrder.getPoId());

			AgainstPOSearchResponse purchaseOrderSearchDto = new AgainstPOSearchResponse(purchaseOrder.getId(),
					purchaseOrder.getPoNumber());
			List<PurchaseOrderDetailResponse> poDeatailList = new ArrayList<PurchaseOrderDetailResponse>();
			for (Integer itemId : againstPurchaseOrder.getItemIds()) {

				for (PurchaseOrderDetailResponse poDetail : GlobalDao.getItemsDetailsAgainstPO(purchaseOrder.getId(),
						itemId)) {

					poDetail.setPoQuantity(poDetail.getPoQuantity() != null ? poDetail.getPoQuantity() : 0);
					poDetail.setBonusQuantity(poDetail.getBonusQuantity() != null ? poDetail.getBonusQuantity() : 0);
					poDetail.setUom(GlobalDao.getUnitOfMeasurementListByItem(itemId));
					poDetail.setPoStagedList(getPoStagedList(poDetail.getId()));
					poDeatailList.add(poDetail);
				}

				purchaseOrderSearchDto.setItems(poDeatailList);
			}
			if (purchaseOrderSearchDto.getItems() != null && !purchaseOrderSearchDto.getItems().isEmpty()) {
				response.add(purchaseOrderSearchDto);
			}
		}
		purchaseOrderResponseList.setPurchaseOrderList(response);
		purchaseOrderResponseList.setOtherChargesList(getOtherCharges());
		return purchaseOrderResponseList;
	}

	private List<PurchaseOrderStaged> getPoStagedList(Integer id) {
		List<PurchaseOrderStaged> poStageList = new ArrayList<PurchaseOrderStaged>();
		for (com.param.entity.model.procurement.PurchaseOrderStaged poStaged : GlobalDao.getPurchaseOrderStaged(id)) {

			PurchaseOrderStaged poStage = new PurchaseOrderStaged(poStaged.getId(), poStaged.getQuantity(),
					poStaged.getStagedDate());
			poStageList.add(poStage);
		}
		return poStageList;
	}

	@Override
	@Transactional
	public List<FixedAssetType> getFixedAssetTypes() {
		List<FixedAssetType> response = new ArrayList<FixedAssetType>();

		for (com.param.entity.model.master.FixedAssetType fixedAssetType : GlobalDao.getFixedAssetTypes()) {
			response.add(new FixedAssetType(fixedAssetType.getId(), fixedAssetType.getType()));
		}
		return response;
	}

	@Override
	@Transactional
	public Object getRacks(Integer storeId) {
		return GlobalDao.getRacks(storeId);
	}

	@Override
	@Transactional
	public Object getShelves(Integer rackId) {
		return GlobalDao.getShelves(rackId);
	}

	@Override
	@Transactional
	public Object getAgainstGrn(AgainstGrnRequest request) {
		request.setToDate((new DateTime(request.getToDate())).plusDays(1).toDate());
		
		List<AgainstGrnResponse> response = new ArrayList<AgainstGrnResponse>();
		
		List<AgainstGrnResponse> grnList = GlobalDao.getGrn(request);

		List<AgainstGrnDetailResponse> grnDetails = GlobalDao
				.getGrnDetails(grnList.stream().map(AgainstGrnResponse::getId).collect(Collectors.toList()));

		for (AgainstGrnResponse againstGrnResponse : grnList) {
			List<AgainstGrnDetailResponse> details = grnDetails.stream()
					.filter(grn -> grn.getGrnId().equals(againstGrnResponse.getId())).collect(Collectors.toList());
			
			if (details != null && !details.isEmpty()) {
				againstGrnResponse.setItems(details);
				response.add(againstGrnResponse);
			}
		}

		return response;
	}

	@Override
	@Transactional
	public List<ScrapReason> getScrapReasonList() {
		List<ScrapReason> response = new ArrayList<ScrapReason>();

		for (com.param.entity.model.master.ScrapReason scrapReason : GlobalDao.getScrapReasonList()) {
			response.add(new ScrapReason(scrapReason.getId(), scrapReason.getCode(), scrapReason.getReason()));
		}	
		return response;
	}
}
