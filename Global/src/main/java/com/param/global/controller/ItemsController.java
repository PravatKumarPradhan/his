package com.param.global.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.param.common.dto.ErrorResponse;
import com.param.global.dto.items.BatchListResponse;
import com.param.global.dto.items.ItemsDetailsListResponse;
import com.param.global.dto.items.ItemsDetailsResponse;
import com.param.global.dto.items.ItemsRequest;
import com.param.global.dto.items.ItemsSearchResponse;
import com.param.global.service.items.IItemsService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/api/global/items")
public class ItemsController {

	@Autowired
	IItemsService ItemsService;

	@RequestMapping(value = "/{isOTC}/{search}", method = RequestMethod.GET)
	@ApiOperation(value = "Get list of items", response = ItemsSearchResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	public ResponseEntity<Object> search(@PathVariable("isOTC") boolean isOtc, @PathVariable("search") String search)
			throws Exception {
		try {
			return new ResponseEntity<Object>(ItemsService.itemsSearch(isOtc, search), HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<Object>(new ErrorResponse("HIS-0101", "Error while fetching the items list", ex), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "Get item details by itemId", response = ItemsDetailsResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	public ResponseEntity<Object> detail(@PathVariable("id") int id) throws Exception {
		try {
			return new ResponseEntity<Object>(ItemsService.itemsDetail(id), HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<Object>(new ErrorResponse("HIS-0102", "Error while fetching the item detail", ex), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	@ApiOperation(value = "Get items list", response = ItemsDetailsListResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	public ResponseEntity<Object> getItemsList(@RequestBody ItemsRequest itemsRequest) throws Exception {
		try {
			return new ResponseEntity<Object>(ItemsService.itemsList(itemsRequest), HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<Object>(new ErrorResponse("HIS-0103", "Error while fetching the items list", ex), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/details", method = RequestMethod.POST)
	@ApiOperation(value = "Get items details by itemIds", response = ItemsDetailsListResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	public ResponseEntity<Object> details(@RequestBody ItemsRequest itemsRequest) throws Exception {
		try {
			return new ResponseEntity<Object>(ItemsService.itemsDetails(itemsRequest), HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<Object>(new ErrorResponse("HIS-0104", "Error while fetching the items details", ex), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/batches", method = RequestMethod.POST)
	@ApiOperation(value = "Get batch list", response = BatchListResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	public ResponseEntity<Object> batchList(@RequestBody ItemsRequest itemsRequest) throws Exception {
		try {
			return new ResponseEntity<Object>(ItemsService.batchList(itemsRequest), HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<Object>(new ErrorResponse("HIS-0105", "Error while fetching the batch list", ex), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/batches/{isOTC}/{search}", method = RequestMethod.GET)
	@ApiOperation(value = "Get list of batches", response = ItemsSearchResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	public ResponseEntity<Object> batchSearch(@PathVariable("isOTC") boolean isOtc, @PathVariable("search") String search)
			throws Exception {
		try {
			return new ResponseEntity<Object>(ItemsService.batchSearch(isOtc, search), HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<Object>(new ErrorResponse("HIS-0106", "Error while fetching the batch list", ex), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/batches/details", method = RequestMethod.POST)
	@ApiOperation(value = "Get items and batch details by itemIds", response = ItemsDetailsListResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	public ResponseEntity<Object> batchDetails(@RequestBody ItemsRequest itemsRequest) throws Exception {
		try {
			return new ResponseEntity<Object>(ItemsService.batchDetails(itemsRequest), HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<Object>(new ErrorResponse("HIS-0107", "Error while fetching the items details", ex), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}	

	/*Items by near expiry*/
	@RequestMapping(value = "/batches/near/expiry", method = RequestMethod.POST)
	@ApiOperation(value = "Get items list", response = ItemsDetailsListResponse.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Suceess|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), @ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	public ResponseEntity<Object> getItemsListByExpiry(@RequestBody ItemsRequest itemsRequest) throws Exception {
		try {
			return new ResponseEntity<Object>(ItemsService.itemsListByBatchExpiry(itemsRequest), HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<Object>(new ErrorResponse("HIS-0108", "Error while fetching the items list", ex), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
