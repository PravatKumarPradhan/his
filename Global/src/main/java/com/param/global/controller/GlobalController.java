package com.param.global.controller;

import java.util.List;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.param.common.dto.ErrorResponse;
import com.param.entity.model.master.VisitType;
import com.param.entity.model.pharmacy.SaleType;
import com.param.global.dto.global.AgainstGrnRequest;
import com.param.global.dto.global.AgainstPOSearchResponse;
import com.param.global.dto.global.AgainstPurchaseOrder;
import com.param.global.dto.global.AgainstPurchaseRequest;
import com.param.global.dto.global.AssetCategory;
import com.param.global.dto.global.AssetType;
import com.param.global.dto.global.CancelReason;
import com.param.global.dto.global.Currency;
import com.param.global.dto.global.Doctor;
import com.param.global.dto.global.Floor;
import com.param.global.dto.global.FormulationType;
import com.param.global.dto.global.ManufacturerResponseDto;
import com.param.global.dto.global.Patient;
import com.param.global.dto.global.PatientDetailRequest;
import com.param.global.dto.global.PatientDetailResponse;
import com.param.global.dto.global.PatientIssueDropdown;
import com.param.global.dto.global.ProductCategory;
import com.param.global.dto.global.PurchaseRequestSearchDto;
import com.param.global.dto.global.RejectReason;
import com.param.global.dto.global.ScrapReason;
import com.param.global.dto.global.Status;
import com.param.global.dto.global.Store;
import com.param.global.dto.global.VendorResponseDto;
import com.param.global.dto.global.Ward;
import com.param.global.dto.items.ItemsDetailsListResponse;
import com.param.global.service.global.IGlobalService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/api/global")
public class GlobalController {

	@Autowired
	IGlobalService GlobalService;

	@RequestMapping(value = "/assets/types", method = RequestMethod.GET)
	@ApiOperation(value = "Get list for populating dropdown values", response = AssetType.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	public @ResponseBody ResponseEntity<Object> getAssetTypes() throws JSONException {
		return new ResponseEntity<Object>(GlobalService.getAssetTypeList(), HttpStatus.OK);
	}

	@RequestMapping(value = "/assets/categories/{assetTypeId}", method = RequestMethod.GET)
	@ApiOperation(value = "Get list for populating dropdown values", response = AssetCategory.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	public @ResponseBody ResponseEntity<Object> getAssetCategories(@PathVariable Integer assetTypeId)
			throws JSONException {
		return new ResponseEntity<Object>(GlobalService.getAssetCategoryList(assetTypeId), HttpStatus.OK);
	}

	@RequestMapping(value = "/products/categories/{assetCategoryId}", method = RequestMethod.GET)
	@ApiOperation(value = "Get list for populating dropdown values", response = ProductCategory.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	public @ResponseBody ResponseEntity<Object> getProductCategories(@PathVariable Integer assetCategoryId)
			throws JSONException {
		return new ResponseEntity<Object>(GlobalService.getProductCategoryList(assetCategoryId), HttpStatus.OK);
	}

	@RequestMapping(value = "/formulation/types/{productCategoryId}", method = RequestMethod.GET)
	@ApiOperation(value = "Get list for populating dropdown values", response = FormulationType.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	public @ResponseBody ResponseEntity<Object> getformulationTypes(@PathVariable Integer productCategoryId)
			throws JSONException {
		return new ResponseEntity<Object>(GlobalService.getFormulationTypeList(productCategoryId), HttpStatus.OK);
	}

	@RequestMapping(value = "/currencies", method = RequestMethod.GET)
	@ApiOperation(value = "Get Currency", response = Currency.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	public ResponseEntity<Object> getCurrency() {
		ResponseEntity<Object> response = new ResponseEntity<Object>(GlobalService.getCurrency(), HttpStatus.OK);

		return response;
	}

	@RequestMapping(value = "/status", method = RequestMethod.GET)
	@ApiOperation(value = "Get list Of Status", response = Status.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	public @ResponseBody ResponseEntity<Object> getAllStatus() throws Exception {
		return new ResponseEntity<Object>(GlobalService.getStatusList(), HttpStatus.OK);
	}

	@RequestMapping(value = "/visitType", method = RequestMethod.GET)
	@ApiOperation(value = "Get list Of Status", response = VisitType.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	public @ResponseBody ResponseEntity<Object> getAllVisitType() throws Exception {
		return new ResponseEntity<Object>(GlobalService.getVisitTypeList(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/pharmacy/patient/issue/dropdown", method = RequestMethod.GET)
	@ApiOperation(value = "Get list Of Status", response = PatientIssueDropdown.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	public @ResponseBody ResponseEntity<Object> getPatientIssueDropdown() throws Exception {
		return new ResponseEntity<Object>(GlobalService.getPatientIssueDropdown(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/sale/type", method = RequestMethod.GET)
	@ApiOperation(value = "Get list Of Status", response = SaleType.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	public @ResponseBody ResponseEntity<Object> getAllSaleType() throws Exception {
		return new ResponseEntity<Object>(GlobalService.getSaleTypeList(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/reject/reasons", method = RequestMethod.GET)
	@ApiOperation(value = "Get list Of Reject Reason", response = RejectReason.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	public @ResponseBody ResponseEntity<Object> getAllRejectReason() throws Exception {
		return new ResponseEntity<Object>(GlobalService.getRejectReasonList(), HttpStatus.OK);
	}

	@RequestMapping(value = "/cancel/reasons", method = RequestMethod.GET)
	@ApiOperation(value = "Get list Of Cancel Reason", response = CancelReason.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	public @ResponseBody ResponseEntity<Object> getAllCancelReason() throws Exception {
		return new ResponseEntity<Object>(GlobalService.getCancelReasonList(), HttpStatus.OK);
	}

	@RequestMapping(value = "/user/stores", method = RequestMethod.GET)
	@ApiOperation(value = "Get list Of stores mapped to the user", response = Store.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	public @ResponseBody ResponseEntity<Object> getUserStore() throws Exception {
		return new ResponseEntity<Object>(GlobalService.getUserStoreList(), HttpStatus.OK);
	}
	
	/*Dropdown For storeType*/
	@RequestMapping(value = "/store/type", method = RequestMethod.GET)
	@ApiOperation(value = "Get list Of stores types", response = Store.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), 
			@ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	public @ResponseBody ResponseEntity<Object> getStoreType() throws Exception {
		return new ResponseEntity<Object>(GlobalService.getStoreTypeList(), HttpStatus.OK);
	}

	@RequestMapping(value = "/stores/indents/{fromStoreId}/{toStoreId}", method = RequestMethod.GET)
	@ApiOperation(value = "Get list Of store indents", response = Store.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Suceess|OK"),
			@ApiResponse(code = 401, message = "not authorized!"),
			@ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	public @ResponseBody ResponseEntity<Object> getStoreIndents(@PathVariable Integer fromStoreId, @PathVariable Integer toStoreId) throws Exception {
		return new ResponseEntity<Object>(GlobalService.getStoreIndentList(fromStoreId, toStoreId), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/patient/search", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get Patient details list ", response = PatientDetailResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	public ResponseEntity<Object> patientIndentPatientSearch(@RequestBody PatientDetailRequest patientDetailsSearch) {
		try
		{
			return new ResponseEntity<Object>(GlobalService.patientIndentPatientSearch(patientDetailsSearch), HttpStatus.OK);
			
		}
		catch(Exception ex)
		{
			return new ResponseEntity<Object>(new ErrorResponse("", "Error while searching Patient details", ex), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
		
	@RequestMapping(value = "/patient/search/{name}", method = RequestMethod.GET)
	@ApiOperation(value = "Get list of item Details By Name", response = Patient.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Suceess|OK"),
			@ApiResponse(code = 401, message = "not authorized!"),
			@ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	public ResponseEntity<List<Patient>> patientSearch(@PathVariable("name") String name) {
		return new ResponseEntity<List<Patient>>(GlobalService.patientSearch(name), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/patient/search/uhid/{uhid}", method = RequestMethod.GET)
	@ApiOperation(value = "Get list of item Details By uhid", response = Patient.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Suceess|OK"),
			@ApiResponse(code = 401, message = "not authorized!"),
			@ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	public ResponseEntity<List<Patient>> patientSearchByUhid(@PathVariable("uhid") String uhid) {
		return new ResponseEntity<List<Patient>>(GlobalService.patientSearchByUhid(uhid), HttpStatus.OK);
	}

	@RequestMapping(value = "/doctor/search/{name}", method = RequestMethod.GET)
	@ApiOperation(value = "Get list of item Details By Name", response = Doctor.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	public ResponseEntity<List<Doctor>> doctorSearch(@PathVariable("name") String name) {
		return new ResponseEntity<List<Doctor>>(GlobalService.doctorSearch(name), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/ward", method = RequestMethod.GET)
	@ApiOperation(value = "Get list of wards", response = Doctor.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	public ResponseEntity<List<Ward>> getWardList() {
		return new ResponseEntity<List<Ward>>(GlobalService.getWardList(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/floor", method = RequestMethod.GET)
	@ApiOperation(value = "Get list of floors", response = Doctor.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	public ResponseEntity<List<Floor>> getFloorList() {
		return new ResponseEntity<List<Floor>>(GlobalService.getFloorList(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/vendor/search/{name}", method = RequestMethod.GET)
	@ApiOperation(value = "Get list of Purchase Order", notes = "Get list of  Purchase Order", response = VendorResponseDto.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	public ResponseEntity<Object> getItemRequestVendorAutoPopulateList(@PathVariable("name") String name) throws Exception {
		return new ResponseEntity<Object>(GlobalService.getVendorList(name), HttpStatus.OK);

	}
	
	@RequestMapping(value = "/manufacturer/search/{name}", method = RequestMethod.GET)
	@ApiOperation(value = "Get list of Purchase Order", notes = "Get list of  Purchase Order", response = ManufacturerResponseDto.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	public ResponseEntity<Object> getItemRequestManufacturerAutoPopulateList(@PathVariable("name") String name) throws Exception {
		return new ResponseEntity<Object>(GlobalService.getManufacturerList(name), HttpStatus.OK);

	}
	
	@RequestMapping(value = "/against/purchase/request/search", method = RequestMethod.POST)
	@ApiOperation(value = "Get list of Purchase Request", notes = "Get list of  Purchase Request", response = PurchaseRequestSearchDto.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess|OK"),
	@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
	@ApiResponse(code = 404, message = "not found!!!") })
	public @ResponseBody ResponseEntity<Object> getAgainstPurchseRequestSearch(@RequestBody AgainstPurchaseRequest againstPurchaseRequest) throws Exception{
				
		return new ResponseEntity<Object>(GlobalService.getAgainstPurchseRequestSearch(againstPurchaseRequest), HttpStatus.OK);

	}
	
	@RequestMapping(value = "/against/purchase/request/details", method = RequestMethod.POST)
	@ApiOperation(value = "Get list items against purchase requests  ", response = PurchaseRequestSearchDto.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	public @ResponseBody ResponseEntity<Object> getItemDetailsAgainstPurchaseRequest(@RequestBody List<AgainstPurchaseRequest> againstPurchaseRequestList) throws Exception{
			return new ResponseEntity<Object>(GlobalService.getItemsDetailsAgainstPurchseRequest(againstPurchaseRequestList), HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/direct/purchase/request/details", method = RequestMethod.POST)
	@ApiOperation(value = "Get list of items direct Purchase Request", response = ItemsDetailsListResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	public ResponseEntity<Object> getItemDetailsDirectPurchaseRequest(@RequestBody AgainstPurchaseRequest againstPurchaseRequest) throws Exception {
		return new ResponseEntity<Object>(GlobalService.getItemDetailsDirectPurchaseRequest(againstPurchaseRequest), HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/against/purchase/order/search/{storeId}", method = RequestMethod.POST)
	@ApiOperation(value = "Get list of Purchase Order", notes = "Get list of  Purchase Order", response = AgainstPOSearchResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess|OK"),
	@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
	@ApiResponse(code = 404, message = "not found!!!") })
	public @ResponseBody ResponseEntity<Object> getAgainstPurchseOrderSearch(@RequestBody AgainstPurchaseOrder againstPurchaseOrder, @PathVariable Integer storeId) throws Exception{
		againstPurchaseOrder.setStoreId(storeId);
		return new ResponseEntity<Object>(GlobalService.getAgainstPurchseOrderSearch(againstPurchaseOrder), HttpStatus.OK);

	}
	
	@RequestMapping(value = "/against/purchase/order/details", method = RequestMethod.POST)
	@ApiOperation(value = "Get list items against purchase requests  ", response = PurchaseRequestSearchDto.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	public @ResponseBody ResponseEntity<Object> getItemDetailsAgainstPurchaseOrder(@RequestBody List<AgainstPurchaseOrder> againstPurchaseOrderList) throws Exception{
			return new ResponseEntity<Object>(GlobalService.getItemDetailsAgainstPurchaseOrder(againstPurchaseOrderList), HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/fixed/assets/types", method = RequestMethod.GET)
	@ApiOperation(value = "Get list for populating dropdown values", response = AssetType.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	public @ResponseBody ResponseEntity<Object> getFixedAssetTypes() throws JSONException {
		return new ResponseEntity<Object>(GlobalService.getFixedAssetTypes(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/racks/{storeId}", method = RequestMethod.GET)
	@ApiOperation(value = "Get list of racks", response = AssetType.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	public @ResponseBody ResponseEntity<Object> getRacks(@PathVariable Integer storeId) throws JSONException {
		return new ResponseEntity<Object>(GlobalService.getRacks(storeId), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/racks/shelves/{rackId}", method = RequestMethod.GET)
	@ApiOperation(value = "Get list of shelves", response = AssetType.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	public @ResponseBody ResponseEntity<Object> getShelves(@PathVariable Integer rackId) throws JSONException {
		return new ResponseEntity<Object>(GlobalService.getShelves(rackId), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/against/grn", method = RequestMethod.POST)
	@ApiOperation(value = "Get list of GRN", notes = "Get list of GRN", response = AgainstPOSearchResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess|OK"),
	@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
	@ApiResponse(code = 404, message = "not found!!!") })
	public @ResponseBody ResponseEntity<Object> getAgainstGrn(@RequestBody AgainstGrnRequest request) throws Exception{
		return new ResponseEntity<Object>(GlobalService.getAgainstGrn(request), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/scrap/reasons", method = RequestMethod.GET)
	@ApiOperation(value = "Get list Of Scrap Reason", response = ScrapReason.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	public @ResponseBody ResponseEntity<Object> getAllScrapReason() throws Exception {
		return new ResponseEntity<Object>(GlobalService.getScrapReasonList(), HttpStatus.OK);
	}
}
