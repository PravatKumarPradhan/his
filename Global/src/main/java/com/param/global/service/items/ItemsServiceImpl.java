package com.param.global.service.items;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.entity.model.inventory.Batch;
import com.param.global.dao.global.IGlobalDao;
import com.param.global.dao.items.IItemsDao;
import com.param.global.dto.items.BatchListResponse;
import com.param.global.dto.items.BatchSearchResponse;
import com.param.global.dto.items.ItemListResponse;
import com.param.global.dto.items.ItemsDetailsListResponse;
import com.param.global.dto.items.ItemsDetailsResponse;
import com.param.global.dto.items.ItemsRequest;
import com.param.global.dto.items.ItemsResponse;
import com.param.global.dto.items.ItemsSearchResponse;
import com.param.global.dto.items.UomResponse;
import com.param.global.utility.Util;

@Service
public class ItemsServiceImpl implements IItemsService {

	@Autowired
	IItemsDao ItemsDao;

	@Autowired
	IGlobalDao GlobalDao;

	@Override
	@Transactional
	public List<ItemsSearchResponse> itemsSearch(boolean isOtc, String search) {
		return ItemsDao.itemsSearch(isOtc, search);
	}

	@Override
	@Transactional
	public List<BatchSearchResponse> batchSearch(boolean isOtc, String search) {
		return ItemsDao.batchSearch(isOtc, search);
	}

	@Override
	@Transactional
	public ItemsDetailsResponse itemsDetail(int id) throws Exception {
		List<Batch> batchList = GlobalDao.getBatchDetails(id);

		if (batchList.isEmpty()) {
			throw new Exception("Selected item is out of stock");
		} else {
			ItemsDetailsResponse response = GlobalDao.itemsDetail(id);

			for (Batch batch : batchList) {
				response.addBatch(Util.PopulateBatch(batch));
			}

			return response;
		}
	}

	@Override
	@Transactional
	public List<ItemListResponse> itemsList(ItemsRequest itemRequest) {
		return ItemsDao.itemsList(itemRequest);
	}

	@Override
	@Transactional
	public List<ItemsResponse> itemsListByBatchExpiry(ItemsRequest itemsRequest) {
		List<ItemsResponse> response = ItemsDao.itemsListByBatchExpiry(itemsRequest);

		for (ItemsResponse batch : response) {
			List<UomResponse> uomList = GlobalDao.getBatchUomList(batch.getBatchId());

			if (uomList != null && !uomList.isEmpty()) {
				UomResponse sUom = Util.getStoreUom(uomList);
				UomResponse dUom = Util.getOpDispenseUom(uomList);

				Integer conversion = sUom.getConversion() * dUom.getConversion();

				batch.setUom(sUom.getUomTypeId(), sUom.getUomType(), sUom.getUomUnitId(), sUom.getUomUnit());
				batch.setAvailableQuantity(batch.getAvailableQuantity() / conversion);
			}
		}
		return response;
	}

	@Override
	@Transactional
	public List<ItemsDetailsListResponse> itemsDetails(ItemsRequest itemsRequest) {

		List<ItemsDetailsListResponse> response = ItemsDao.itemsDetails(itemsRequest.getItemIds(),
				itemsRequest.getStoreId());

		for (ItemsDetailsListResponse item : response) {

			Long stockInTransit = GlobalDao.getStoreStockInTransitByItem(itemsRequest.getStoreId(), item.getItemId());
			Long lastMonthSale = GlobalDao.getLastMonthSale(itemsRequest.getStoreId(), item.getItemId());
			Long currentMonthSale = GlobalDao.getCurrentMonthSale(itemsRequest.getStoreId(), item.getItemId());
			Integer maxStoreQuantity = GlobalDao.getStoreMaxQuantityByItem(itemsRequest.getStoreId(), item.getItemId());
			Long maxOrderQuantity = maxStoreQuantity - (item.getStockAvailable() + stockInTransit);

			item.setStockInTransit(stockInTransit);
			item.setLastMonthSale(lastMonthSale);
			item.setCurrentMonthSale(currentMonthSale);
			item.setMaxOrderQuantity(maxOrderQuantity);
			item.setUom(GlobalDao.getUnitOfMeasurementListByItem(item.getItemId()));
		}

		return response;
	}

	@Override
	@Transactional
	public List<BatchListResponse> batchList(ItemsRequest itemRequest) {

		List<BatchListResponse> response = ItemsDao.batchList(itemRequest);

		for (BatchListResponse batch : response) {
			List<UomResponse> uomList = GlobalDao.getBatchUomList(batch.getBatchId());
			
			if (uomList != null && !uomList.isEmpty()) {
				UomResponse sUom = Util.getStoreUom(uomList);
				UomResponse dUom = Util.getOpDispenseUom(uomList);

				Integer conversion = sUom.getConversion() * dUom.getConversion();

				batch.setUom(sUom.getUomTypeId(), sUom.getUomType(), sUom.getUomUnitId(), sUom.getUomUnit());
				batch.setAvailableQuantity(batch.getAvailableQuantity() / conversion);
			}
		}

		return response;
	}

	@Override
	@Transactional
	public List<ItemsDetailsListResponse> batchDetails(ItemsRequest itemsRequest) {

		List<ItemsDetailsListResponse> response = ItemsDao.itemsDetails(itemsRequest.getItemIds(),
				itemsRequest.getStoreId());

		for (ItemsDetailsListResponse item : response) {

			Long storeStock = GlobalDao.getStoreStockByItem(itemsRequest.getStoreId(), item.getItemId());

			item.setUom(GlobalDao.getUnitOfMeasurementListByItem(item.getItemId()));

			item.setStockAvailable(storeStock);

			for (Batch batch : GlobalDao.getBatchDetails(item.getItemId())) {
				item.addBatch(Util.PopulateBatchWithStock(batch));
			}
		}

		return response;
	}

}
