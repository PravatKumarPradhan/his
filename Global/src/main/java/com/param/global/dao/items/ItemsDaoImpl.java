package com.param.global.dao.items;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.param.common.constants.Constants;
import com.param.common.dao.AbstractDao;
import com.param.entity.model.master.Item;
import com.param.global.dto.items.BatchListResponse;
import com.param.global.dto.items.BatchSearchResponse;
import com.param.global.dto.items.ItemListResponse;
import com.param.global.dto.items.ItemsDetailsListResponse;
import com.param.global.dto.items.ItemsRequest;
import com.param.global.dto.items.ItemsResponse;
import com.param.global.dto.items.ItemsSearchResponse;
import com.param.global.utility.Util;

@Repository
public class ItemsDaoImpl extends AbstractDao<Integer, Item> implements IItemsDao {

	@Override
	public List<ItemsSearchResponse> itemsSearch(boolean isOtc, String search) {

		String hql = "SELECT item.id AS id, item.code || ' | ' || item.name AS details " + "	FROM Item AS item "
				+ "		INNER JOIN item.generic AS generic WITH generic.isDeleted = false AND generic.isActive = true "
				+ "		INNER JOIN generic.productCategory AS productCategory WITH productCategory.isDeleted = false AND productCategory.isActive = true "
				+ "		INNER JOIN productCategory.storeMapperList AS storeProduct WITH storeProduct.isDeleted = false AND storeProduct.isActive = true "
				+ "	WHERE item.isDeleted = false AND item.isActive = true AND item.unit.unitId = :unitId AND item.isOtc = :isOtc "
				+ "		AND (upper(item.code) like upper(:search) OR upper(item.name) like upper(:search)) "
				+ "		AND storeProduct.store.storeId = :storeId "
				+ "		AND storeProduct.store.isDeleted = false AND storeProduct.store.isActive = true "
				+ "		AND item.id NOT IN (SELECT itemRestriction.item.id FROM StoreItemRestriction AS itemRestriction "
				+ "							WHERE itemRestriction.store.storeId = :storeId AND itemRestriction.unit.unitId = :unitId "
				+ "							AND itemRestriction.isDeleted = false AND itemRestriction.isActive = true) "
				+ "	ORDER BY item.id";

		Query query = getSession().createQuery(hql);
		query.setParameter("storeId", Constants.StoreId);
		query.setParameter("unitId", Constants.UnitId);
		query.setParameter("isOtc", isOtc);
		query.setParameter("search", search + '%');
		query.setResultTransformer(Transformers.aliasToBean(ItemsSearchResponse.class));

		return query.list();
	}

	@Override
	public List<BatchSearchResponse> batchSearch(boolean isOtc, String search) {

		String hql = "SELECT item.id AS id, batch.id AS batchId, batch.batchNo || ' | ' || TO_CHAR(batch.expiryDate, 'Mon-yyyy') AS details "
				+ "	FROM Batch as batch "
				+ "		INNER JOIN batch.item AS item WITH item.isActive = true and item.isDeleted = false "
				+ "		INNER JOIN item.generic AS generic WITH generic.isDeleted = false AND generic.isActive = true "
				+ "		INNER JOIN generic.productCategory AS productCategory WITH productCategory.isDeleted = false AND productCategory.isActive = true "
				+ "		INNER JOIN productCategory.storeMapperList AS storeProduct WITH storeProduct.isDeleted = false AND storeProduct.isActive = true "
				+ "	WHERE batch.isDeleted = false AND batch.isActive = true AND batch.unit.unitId = :unitId AND item.isOtc = :isOtc "
				+ "		AND (upper(batch.batchNo) like upper(:search)) "
				+ "		AND storeProduct.store.storeId = :storeId "
				+ "		AND storeProduct.store.isDeleted = false AND storeProduct.store.isActive = true "
				+ "		AND item.id NOT IN (SELECT itemRestriction.item.id FROM StoreItemRestriction AS itemRestriction "
				+ "							WHERE itemRestriction.store.storeId = :storeId AND itemRestriction.unit.unitId = :unitId "
				+ "							AND itemRestriction.isDeleted = false AND itemRestriction.isActive = true) "
				+ "	ORDER BY item.id";

		Query query = getSession().createQuery(hql);
		query.setParameter("storeId", Constants.StoreId);
		query.setParameter("unitId", Constants.UnitId);
		query.setParameter("isOtc", isOtc);
		query.setParameter("search", search + '%');
		query.setResultTransformer(Transformers.aliasToBean(BatchSearchResponse.class));

		return query.list();
	}

	@Override
	public List<ItemListResponse> itemsList(ItemsRequest itemsRequest) {
		String hql = "SELECT item.id AS itemId, assetCategory.category AS assetCategory, productCategory.category AS productCategory, item.code AS itemCode, item.name AS itemName "
				+ "	FROM Item AS item "
				+ "		INNER JOIN item.generic AS generic WITH generic.isDeleted = false AND generic.isActive = true "
				+ "		INNER JOIN generic.productCategory AS productCategory WITH productCategory.isDeleted = false AND productCategory.isActive = true "
				+ "		INNER JOIN productCategory.assetCategory AS assetCategory WITH assetCategory.isDeleted = false AND assetCategory.isActive = true "
				+ "		INNER JOIN assetCategory.assetType as assetType WITH assetType.isDeleted = false AND assetType.isActive = true "
				+ "	WHERE item.isDeleted = false AND item.isActive = true AND item.unit.unitId = :unitId "
				+ "		AND (:consignment = 0 OR item.isConsignment = :isConsignment) "
				+ "		AND (:itemCode = '' OR upper(item.code) like upper(:itemCode)) "
				+ "		AND (:itemName = '' OR upper(item.name) like upper(:itemName)) "
				+ " 	AND (:assetTypeId = 0 OR assetType.id = :assetTypeId) "
				+ " 	AND (:assetCategoryId = 0 OR assetCategory.id = :assetCategoryId) "
				+ " 	AND (:productCategoryId = 0 OR productCategory.id = :productCategoryId) "
				+ "		AND ((:fromStoreId = 0 OR :vedId = 0) OR generic.id in (SELECT generic.id "
				+ "												FROM StorewiseGenericMapper AS storeGeneric "
				+ "													INNER JOIN storeGeneric.generic AS generic WITH generic.isActive = true AND generic.isDeleted = false "
				+ "													INNER JOIN storeGeneric.ved AS ved WITH ved.id = :vedId AND ved.isActive = true AND ved.isDeleted = false "
				+ "													INNER JOIN storeGeneric.store AS store WITH store.storeId = :fromStoreId AND store.isActive = true AND store.isDeleted = false )) "
				+ "		AND (:fromStoreId = 0 OR productCategory.id in (SELECT storeProduct.productCategory.id "
				+ "														FROM StoreProductCategoryMapper AS storeProduct "
				+ "														WHERE storeProduct.store.storeId = :fromStoreId) "
				+ "							AND item.id NOT IN (SELECT itemRestriction.item.id FROM StoreItemRestriction AS itemRestriction "
				+ "												WHERE itemRestriction.store.storeId = :fromStoreId "
				+ "												AND itemRestriction.isDeleted = false AND itemRestriction.isActive = true)) "
				+ "	    AND (:toStoreId = 0 OR productCategory.id in (SELECT storeProduct.productCategory.id "
				+ "														FROM StoreProductCategoryMapper AS storeProduct WHERE storeProduct.store.storeId = :toStoreId) "
				+ "							AND item.id NOT IN (SELECT itemRestriction.item.id FROM StoreItemRestriction AS itemRestriction "
				+ "												WHERE itemRestriction.store.storeId = :toStoreId "
				+ "												AND itemRestriction.isDeleted = false AND itemRestriction.isActive = true)) "
				+ "ORDER BY item.id ";

		Query query = getSession().createQuery(hql);
		query.setParameter("itemCode", itemsRequest.getItemCode() != null ? itemsRequest.getItemCode() + '%' : "");
		query.setParameter("itemName", itemsRequest.getItemName() != null ? itemsRequest.getItemName() + '%' : "");
		query.setParameter("assetTypeId", itemsRequest.getAssetTypeId() != null ? itemsRequest.getAssetTypeId() : 0);
		query.setParameter("assetCategoryId",
				itemsRequest.getAssetCategoryId() != null ? itemsRequest.getAssetCategoryId() : 0);
		query.setParameter("productCategoryId",
				itemsRequest.getProductCategoryId() != null ? itemsRequest.getProductCategoryId() : 0);
		query.setParameter("fromStoreId", itemsRequest.getFromStoreId() != null ? itemsRequest.getFromStoreId() : 0);
		query.setParameter("toStoreId", itemsRequest.getToStoreId() != null ? itemsRequest.getToStoreId() : 0);
		query.setParameter("vedId", itemsRequest.getVedId() != null ? itemsRequest.getVedId() : 0);
		query.setParameter("isConsignment", itemsRequest.getIsConsignment() != null ? itemsRequest.getIsConsignment() : false);
		query.setParameter("consignment", itemsRequest.getIsConsignment() != null ? 1 : 0);
		query.setParameter("unitId", Constants.UnitId);
		query.setResultTransformer(Transformers.aliasToBean(ItemListResponse.class));

		return query.list();
	}

	@Override
	public List<ItemsResponse> itemsListByBatchExpiry(ItemsRequest itemsRequest) {
		String hql = "SELECT item.id AS itemId, item.code AS itemCode, item.name AS itemName, batch.id AS batchId, batch.batchNo AS batchNo, batch.expiryDate AS expiry, "
				+ " 	batch.lotNo AS lotNo, goodReceiptNote.grnNumber AS grnNo, COALESCE(SUM(storeStock.availableQty), 0) AS availableQuantity, goodReceiptNote.addedDate AS grnDate, "
				+ "		batch.cop AS cop, purchaseTax.id AS taxId, purchaseTax.name AS taxName, purchaseTax.taxPercentage AS taxPercentage, vendor.id AS vendorId, "
				+ "		vendor.contactPersonName AS vendor, generic.name AS genericName "
				+ "	FROM Batch AS batch "
				+ " 	INNER JOIN batch.item AS item WITH item.isDeleted = false AND item.isActive = true "
				+ "		INNER JOIN item.purchaseTax AS purchaseTax WITH purchaseTax.isActive = true AND purchaseTax.isDeleted = false "
				+ "		INNER JOIN item.generic AS generic WITH generic.isDeleted = false AND generic.isActive = true "
				+ "		INNER JOIN generic.productCategory AS productCategory WITH productCategory.isDeleted = false AND productCategory.isActive = true "
				+ "		INNER JOIN productCategory.assetCategory AS assetCategory WITH assetCategory.isDeleted = false AND assetCategory.isActive = true "
				+ "		INNER JOIN assetCategory.assetType as assetType WITH assetType.isDeleted = false AND assetType.isActive = true "
				+ "		LEFT JOIN batch.storeStockList AS storeStock WITH storeStock.isDeleted = false AND storeStock.isActive = true "
				+ "		LEFT JOIN storeStock.store AS store WITH store.isDeleted = false AND store.isActive = true "
				+ "		LEFT JOIN batch.vendor AS vendor WITH vendor.isActive = true AND vendor.isDeleted = false "
				+ " 	LEFT JOIN batch.goodReceiptNoteDetail as goodReceiptNoteDetail WITH goodReceiptNoteDetail.isDeleted = false AND goodReceiptNoteDetail.isActive = true "
				+ " 	LEFT JOIN goodReceiptNoteDetail.goodReceiptNote as goodReceiptNote WITH goodReceiptNote.isDeleted = false AND goodReceiptNote.isActive = true "
				+ "	WHERE batch.isDeleted = false AND batch.isActive = true AND batch.unit.unitId = :unitId "
				+ " 	AND batch.addedDate BETWEEN :fromDate AND :toDate "
				+ " 	AND (:assetTypeId = 0 OR assetType.id = :assetTypeId) "
				+ "		AND (:consignment = 0 OR item.isConsignment = :isConsignment) "
				+ " 	AND (:vendorId = 0 OR vendor.id = :vendorId) "
				+ "		AND (:fromStoreId = 0 OR store.storeId = :fromStoreId) "
				+ "		AND (:fromStoreId = 0 OR productCategory.id in (SELECT storeProduct.productCategory.id "
				+ "														FROM StoreProductCategoryMapper AS storeProduct "
				+ "														WHERE storeProduct.store.storeId = :fromStoreId) "
				+ "							AND item.id NOT IN (SELECT itemRestriction.item.id FROM StoreItemRestriction AS itemRestriction "
				+ "												WHERE itemRestriction.store.storeId = :fromStoreId "
				+ "												AND itemRestriction.isDeleted = false AND itemRestriction.isActive = true)) "
				+ "	    AND (:toStoreId = 0 OR productCategory.id in (SELECT storeProduct.productCategory.id "
				+ "														FROM StoreProductCategoryMapper AS storeProduct WHERE storeProduct.store.storeId = :toStoreId) "
				+ "							AND item.id NOT IN (SELECT itemRestriction.item.id FROM StoreItemRestriction AS itemRestriction "
				+ "												WHERE itemRestriction.store.storeId = :toStoreId "
				+ "												AND itemRestriction.isDeleted = false AND itemRestriction.isActive = true)) "
				+ "	GROUP BY item.id, item.code, item.name, batch.id, batch.batchNo, batch.expiryDate, batch.lotNo, goodReceiptNote.grnNumber, goodReceiptNote.addedDate, "
				+ "		batch.cop, purchaseTax.id, purchaseTax.name, purchaseTax.taxPercentage, vendor.id, vendor.contactPersonName, generic.name "
				+ "	ORDER BY batch.expiryDate DESC";
		
		Query query = getSession().createQuery(hql);
		query.setParameter("fromDate", Util.getDate(itemsRequest.getFromDate()));
		query.setParameter("toDate", Util.getDate(itemsRequest.getToDate()));
		query.setParameter("assetTypeId", itemsRequest.getAssetTypeId() != null ? itemsRequest.getAssetTypeId() : 0);
		query.setParameter("fromStoreId", itemsRequest.getFromStoreId() != null ? itemsRequest.getFromStoreId() : 0);
		query.setParameter("toStoreId", itemsRequest.getToStoreId() != null ? itemsRequest.getToStoreId() : 0);
		query.setParameter("isConsignment", itemsRequest.getIsConsignment() != null ? itemsRequest.getIsConsignment() : false);
		query.setParameter("vendorId", itemsRequest.getVendorId() != null ? itemsRequest.getVendorId() : 0);
		query.setParameter("consignment", itemsRequest.getIsConsignment() != null ? 1 : 0);
		query.setParameter("unitId", Constants.UnitId);
	
		query.setResultTransformer(Transformers.aliasToBean(ItemsResponse.class));

		return query.list();

	}
	@Override
	public List<ItemsDetailsListResponse> itemsDetails(List<Integer> idList, Integer storeId) {
		String hql = "SELECT item.id AS itemId, item.code AS itemCode, item.name AS itemName, generic.id AS genericId, generic.name AS genericName, "
				+ "		manufacturer.id AS manufacturerId, manufacturer.name AS manufacturerName, purchaseTax.id AS taxId, purchaseTax.name AS taxName, "
				+ "		purchaseTax.taxPercentage AS taxPercent, item.isOtc AS isOtc, COALESCE(SUM(storeStock.availableQty), 0) AS stockAvailable " 
				+ "	FROM Item as item"
				+ "		INNER JOIN item.generic AS generic WITH generic.isDeleted = false AND generic.isActive = true "
				+ "		INNER JOIN item.manufacturer AS manufacturer WITH manufacturer.isDeleted = false AND manufacturer.isActive = true "
				+ "		INNER JOIN item.purchaseTax AS purchaseTax WITH purchaseTax.isDeleted = false AND purchaseTax.isActive = true "				
				+ "		LEFT JOIN item.batchList AS batch WITH batch.isDeleted = false AND batch.isActive = true"				
				+ "		LEFT JOIN batch.storeStockList AS storeStock WITH storeStock.isDeleted = false AND storeStock.isActive = true "
				+ "		LEFT JOIN storeStock.store AS store WITH store.storeId = :storeId AND store.isDeleted = false AND store.isActive = true "
				+ "	WHERE item.id in (:id) AND item.isDeleted = false AND item.isActive = true "
				+ " GROUP BY item.id, item.code, item.name, generic.id, generic.name, manufacturer.id, manufacturer.name, purchaseTax.id, purchaseTax.name, " 
				+ "		purchaseTax.taxPercentage, item.isOtc "				
				+ "	ORDER BY item.id ";
		
		Query query = getSession().createQuery(hql);
		query.setParameterList("id", idList);
		query.setParameter("storeId", storeId);
		query.setResultTransformer(Transformers.aliasToBean(ItemsDetailsListResponse.class));

		return query.list();
	}

	@Override
	public List<BatchListResponse> batchList(ItemsRequest itemRequest) {
		String hql = "SELECT item.id AS itemId, assetCategory.category AS assetCategory, productCategory.category AS productCategory, generic.name AS genericName, "
				+ " 	item.code AS itemCode, item.name AS itemName, batch.id AS batchId, batch.batchNo AS batchNo, batch.expiryDate AS expiry, "
				+ " 	batch.rate AS mrp, COALESCE(SUM(storeStock.availableQty), 0) AS availableQuantity, batch.lotNo AS lotNo, batch.cop AS cop, "
				+ "		purchaseTax.id AS taxId, purchaseTax.name AS taxName, purchaseTax.taxPercentage AS taxPercentage, vendor.id AS vendorId, "
				+ "		vendor.contactPersonName AS vendor "
				+ "	FROM Batch AS batch "				
				+ "		INNER JOIN batch.item AS item WITH item.isDeleted = false AND item.isActive = true "
				+ "		INNER JOIN item.purchaseTax AS purchaseTax WITH purchaseTax.isActive = true AND purchaseTax.isDeleted = false "
				+ "		INNER JOIN item.generic AS generic WITH generic.isDeleted = false AND generic.isActive = true "
				+ "		INNER JOIN generic.productCategory AS productCategory WITH productCategory.isDeleted = false AND productCategory.isActive = true "
				+ "		INNER JOIN productCategory.assetCategory AS assetCategory WITH assetCategory.isDeleted = false AND assetCategory.isActive = true "
				+ "		INNER JOIN assetCategory.assetType as assetType WITH assetType.isDeleted = false AND assetType.isActive = true "
				+ "		LEFT JOIN batch.storeStockList AS storeStock WITH storeStock.isDeleted = false AND storeStock.isActive = true "
				+ "		LEFT JOIN storeStock.store AS store WITH store.isDeleted = false AND store.isActive = true "
				+ "		LEFT JOIN batch.vendor AS vendor WITH vendor.isActive = true AND vendor.isDeleted = false "
				+ "	WHERE batch.isDeleted = false AND batch.isActive = true AND batch.unit.unitId = :unitId "
				+ "		AND (:consignment = 0 OR item.isConsignment = :isConsignment) "
				+ " 	AND (:assetTypeId = 0 OR assetType.id = :assetTypeId) "
				+ " 	AND (:assetCategoryId = 0 OR assetCategory.id = :assetCategoryId) "
				+ " 	AND (:productCategoryId = 0 OR productCategory.id = :productCategoryId) "
				+ " 	AND (:vendorId = 0 OR vendor.id = :vendorId) "
				+ "		AND (:storeId = 0 OR store.storeId = :storeId) "
				+ "		AND (:storeId = 0 OR productCategory.id in (SELECT storeProduct.productCategory.id "
				+ "														FROM StoreProductCategoryMapper AS storeProduct "
				+ "														WHERE storeProduct.store.storeId = :storeId) "
				+ "							AND item.id NOT IN (SELECT itemRestriction.item.id FROM StoreItemRestriction AS itemRestriction "
				+ "												WHERE itemRestriction.store.storeId = :storeId "
				+ "												AND itemRestriction.isDeleted = false AND itemRestriction.isActive = true)) "
				+ "	    AND (:toStoreId = 0 OR productCategory.id in (SELECT storeProduct.productCategory.id "
				+ "														FROM StoreProductCategoryMapper AS storeProduct WHERE storeProduct.store.storeId = :toStoreId) "
				+ "							AND item.id NOT IN (SELECT itemRestriction.item.id FROM StoreItemRestriction AS itemRestriction "
				+ "												WHERE itemRestriction.store.storeId = :toStoreId "
				+ "												AND itemRestriction.isDeleted = false AND itemRestriction.isActive = true)) "
				+ "	GROUP BY item.id, assetCategory.category, productCategory.category, generic.name, item.code, item.name, batch.id, batch.batchNo, batch.expiryDate, batch.rate, "
				+ "		batch.lotNo, batch.cop, purchaseTax.id, purchaseTax.name, purchaseTax.taxPercentage, vendor.id, vendor.contactPersonName "
				+ "	ORDER BY item.id, batch.id ";

		Query query = getSession().createQuery(hql);
		query.setParameter("assetTypeId", itemRequest.getAssetTypeId() != null ? itemRequest.getAssetTypeId() : 0);
		query.setParameter("assetCategoryId",
				itemRequest.getAssetCategoryId() != null ? itemRequest.getAssetCategoryId() : 0);
		query.setParameter("productCategoryId",
				itemRequest.getProductCategoryId() != null ? itemRequest.getProductCategoryId() : 0);
		query.setParameter("vendorId", itemRequest.getVendorId() != null ? itemRequest.getVendorId() : 0);
		query.setParameter("storeId", itemRequest.getStoreId() != null ? itemRequest.getStoreId() : 0);
		query.setParameter("toStoreId", itemRequest.getToStoreId() != null ? itemRequest.getToStoreId() : 0);
		query.setParameter("isConsignment", itemRequest.getIsConsignment() != null ? itemRequest.getIsConsignment() : false);
		query.setParameter("consignment", itemRequest.getIsConsignment() != null ? 1 : 0);
		query.setParameter("unitId", Constants.UnitId);
		query.setResultTransformer(Transformers.aliasToBean(BatchListResponse.class));

		return query.list();
	}

}
