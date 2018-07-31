package com.param.global.dao.items;

import java.util.List;

import com.param.global.dto.items.BatchListResponse;
import com.param.global.dto.items.BatchSearchResponse;
import com.param.global.dto.items.ItemListResponse;
import com.param.global.dto.items.ItemsDetailsListResponse;
import com.param.global.dto.items.ItemsRequest;
import com.param.global.dto.items.ItemsResponse;
import com.param.global.dto.items.ItemsSearchResponse;

public interface IItemsDao {
	
	List<ItemsSearchResponse> itemsSearch(boolean isOtc, String search);

	List<ItemListResponse> itemsList(ItemsRequest itemsRequest);

	List<BatchListResponse> batchList(ItemsRequest itemsRequest);

	List<BatchSearchResponse> batchSearch(boolean isOtc, String search);

	List<ItemsDetailsListResponse> itemsDetails(List<Integer> idList, Integer storeId);

	List<ItemsResponse> itemsListByBatchExpiry(ItemsRequest itemsRequest);
}