package com.param.global.service.items;

import java.util.List;

import com.param.global.dto.items.BatchListResponse;
import com.param.global.dto.items.BatchSearchResponse;
import com.param.global.dto.items.ItemListResponse;
import com.param.global.dto.items.ItemsDetailsListResponse;
import com.param.global.dto.items.ItemsDetailsResponse;
import com.param.global.dto.items.ItemsRequest;
import com.param.global.dto.items.ItemsResponse;
import com.param.global.dto.items.ItemsSearchResponse;

public interface IItemsService {

	List<ItemsSearchResponse> itemsSearch(boolean isOtc, String search);

	ItemsDetailsResponse itemsDetail(int id) throws Exception;
	
	List<ItemListResponse> itemsList(ItemsRequest itemRequest);

	List<ItemsDetailsListResponse> itemsDetails(ItemsRequest itemRequest);

	List<BatchListResponse> batchList(ItemsRequest itemRequest);

	List<BatchSearchResponse> batchSearch(boolean isOtc, String search);

	List<ItemsDetailsListResponse> batchDetails(ItemsRequest itemsRequest);

	
	
	List<ItemsResponse> itemsListByBatchExpiry(ItemsRequest itemsRequest);
}
