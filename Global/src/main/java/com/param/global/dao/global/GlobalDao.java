package com.param.global.dao.global;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.param.common.constants.Constants;
import com.param.common.dao.AbstractDao;
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
import com.param.global.utility.Util;

import in.param.exception.ApplicationException;

@Repository
public class GlobalDao extends AbstractDao<Integer, Integer> implements IGlobalDao {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public Integer getBatchQuantity(Long batchId) {

		String hql = "SELECT batch.quantity FROM Batch AS batch \r\n"
				+ "WHERE batch.id = :batchId AND batch.isDeleted = false AND batch.isActive = true AND batch.unit.unitId = :unitId \r\n";

		Query query = getSession().createQuery(hql);
		query.setParameter("batchId", batchId);
		query.setParameter("unitId", Constants.UnitId);

		return (Integer) query.uniqueResult();
	}

	@Override
	public Integer getStoreStockByBatch(Integer storeId, Long batchId) {

		String hql = "SELECT storeStock.availableQty FROM StoreStock AS storeStock \r\n"
				+ "		INNER JOIN storeStock.store AS store WITH store.id = :storeId AND store.isDeleted = false AND store.isActive = true AND store.unit.unitId = :unitId \r\n"
				+ "		INNER JOIN storeStock.batch AS batch WITH batch.id = :batchId AND batch.isDeleted = false AND batch.isActive = true AND batch.unit.unitId = :unitId \r\n"
				+ "WHERE storeStock.isDeleted = false AND storeStock.isActive = true AND storeStock.unit.unitId = :unitId \r\n";

		Query query = getSession().createQuery(hql);
		query.setParameter("storeId", Constants.StoreId);
		query.setParameter("unitId", Constants.UnitId);
		query.setParameter("batchId", batchId);

		return (Integer) query.uniqueResult();
	}

	@Override
	public Batch getBatch(Integer storeId, Long batchId) {

		String hql = "SELECT batch FROM Batch AS batch "
				+ "		INNER JOIN FETCH batch.storeStockList AS storeStock "
				+ "		INNER JOIN FETCH storeStock.store AS store "
				+ "WHERE batch.id = :batchId AND batch.isDeleted = false AND batch.isActive = true AND batch.unit.unitId = :unitId "
				+ "		AND storeStock.isDeleted = false AND storeStock.isActive = true AND storeStock.unit.unitId = :unitId "
				+ "		AND store.isDeleted = false AND store.isActive = true AND store.storeId = :storeId AND store.unit.unitId = :unitId ";
		Query query = getSession().createQuery(hql);
		query.setParameter("storeId", Constants.StoreId);
		query.setParameter("unitId", Constants.UnitId);
		query.setParameter("batchId", batchId);

		return (Batch) query.uniqueResult();
	}

	@Override
	public Admission getAdmission(Integer admissionId) {
		if (admissionId == null)
			return null;

		String hql = "SELECT admission FROM Admission AS admission WHERE admission.admissionId = :admissionId AND admission.status = 'A' AND admission.unit.unitId = :unitId \r\n";

		Query query = getSession().createQuery(hql);
		query.setParameter("admissionId", admissionId);
		query.setParameter("unitId", Constants.UnitId);

		return (Admission) query.uniqueResult();
	}

	@Override
	public DiscountCategory getDiscountCategory(Integer discountCategoryId) {
		String hql = "SELECT discountCategory FROM DiscountCategory AS discountCategory \r\n"
				+ "WHERE discountCategory.id = :discountCategoryId AND discountCategory.isDeleted = false AND discountCategory.isActive = true AND discountCategory.unit.unitId = :unitId \r\n";

		Query query = getSession().createQuery(hql);
		query.setParameter("discountCategoryId", discountCategoryId);
		query.setParameter("unitId", Constants.UnitId);

		return (DiscountCategory) query.uniqueResult();
	}

	@Override
	public DiscountType getDiscountType(Integer discountTypeId) {
		String hql = "SELECT discountType FROM DiscountType AS discountType \r\n"
				+ "WHERE discountType.id = :discountTypeId AND discountType.isDeleted = false AND discountType.isActive = true AND discountType.unit.unitId = :unitId \r\n";

		Query query = getSession().createQuery(hql);
		query.setParameter("discountTypeId", discountTypeId);
		query.setParameter("unitId", Constants.UnitId);

		return (DiscountType) query.uniqueResult();
	}

	@Override
	public Doctor getDoctor(Integer doctorId) {
		if (doctorId == null)
			return null;

		String hql = "SELECT doctor FROM Doctor AS doctor WHERE doctor.doctorId = :doctorId AND doctor.status = 'A' AND doctor.unit.unitId = :unitId ";

		Query query = getSession().createQuery(hql);
		query.setParameter("doctorId", doctorId);
		query.setParameter("unitId", Constants.UnitId);

		return (Doctor) query.uniqueResult();
	}

	@Override
	public Order getOrder(Integer orderId) {
		if (orderId == null)
			return null;

		String hql = "SELECT order FROM Order AS order WHERE order.orderId = :orderId AND order.orderStatus = 'A' AND order.orgUnitId = :unitId ";

		Query query = getSession().createQuery(hql);
		query.setParameter("orderId", orderId);
		query.setParameter("unitId", Constants.UnitId);

		return (Order) query.uniqueResult();
	}

	@Override
	public PatientRegistration getPatient(Integer patientId) {
		if (patientId == null)
			return null;

		String hql = "SELECT patient FROM PatientRegistrations AS patient WHERE patient.patientId = :patientId AND patient.status = 'A' AND patient.unitId = :unitId ";

		Query query = getSession().createQuery(hql);
		query.setParameter("patientId", patientId);
		query.setParameter("unitId", Constants.UnitId);

		return (PatientRegistration) query.uniqueResult();
	}

	@Override
	public Admission admissionPatient(PatientDetailRequest patientDetailsSearch) {
		try {
			String hql = " SELECT admission FROM Admissions AS admission "
					+ " INNER JOIN admission.patient AS patient WITH patient.status = 'A' "
					+ " INNER JOIN admission.visitType AS visitType WITH visitType.status = 'A' "
					+ " INNER JOIN admission.doctor AS doctor WITH doctor.status = 'A' "
					+ " INNER JOIN admission.admissionDetail AS admissionDetail WITH admissionDetail.status = 'A' "
					+ " INNER JOIN admissionDetail.bedMaster AS bedMaster WITH bedMaster.status = 'A' "
					+ " INNER JOIN admissionDetail.wardMaster AS wardMaster WITH wardMaster.status = 'A' "
					+ " WHERE admission.status = 'A' AND admissionDetail.isDischarge = 'N' AND admission.unit.unitId = :unitId "
					+ " AND ( patient.id = :patientId ) "
					+ " AND ( visitType.visitTypeId = :visitType OR :visitType = 0 ) ";
			Query query = getSession().createQuery(hql);
			query.setParameter("visitType",
					patientDetailsSearch.getVisitType() != null ? patientDetailsSearch.getVisitType() : 0);
			query.setParameter("patientId", patientDetailsSearch.getPatientId());
			query.setParameter("unitId", Constants.UnitId);

			return (Admission) query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Encounter> encounterPatient(PatientDetailRequest patientDetailsSearch) {
		try {
			String hql = " SELECT encounter FROM Encounters AS encounter "
					+ " INNER JOIN encounter.patient AS patient WITH patient.status = 'A' "
					+ " INNER JOIN encounter.visitType AS visitType WITH visitType.status = 'A' "
					+ " INNER JOIN encounter.doctor AS doctor WITH doctor.status = 'A' "
					+ " WHERE encounter.status = 'A' AND encounter.unitId = :unitId " + " AND patient.id = :patientId "
					+ " AND ( visitType.visitTypeId = :visitType OR :visitType = 0 ) ";
			Query query = getSession().createQuery(hql);
			query.setParameter("visitType",
					patientDetailsSearch.getVisitType() != null ? patientDetailsSearch.getVisitType() : 0);
			query.setParameter("patientId", patientDetailsSearch.getPatientId());
			query.setParameter("unitId", Constants.UnitId);

			return query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public Status getStatus(Integer statusId) {
		if (statusId == null)
			return null;

		String hql = "SELECT status FROM Status AS status WHERE status.id = :statusId AND status.isDeleted = false AND status.isActive = true ";

		Query query = getSession().createQuery(hql);
		query.setParameter("statusId", statusId);

		return (Status) query.uniqueResult();
	}

	@Override
	public Store getStore(Integer storeId) {
		if (storeId == null)
			return null;

		String hql = "SELECT store FROM Store AS store WHERE store.storeId = :storeId AND store.isDeleted = false AND store.isActive = true AND store.unit.unitId = :unitId ";

		Query query = getSession().createQuery(hql);
		query.setParameter("storeId", storeId);
		query.setParameter("unitId", Constants.UnitId);

		return (Store) query.uniqueResult();
	}

	@Override
	public VisitType getVisitType(Integer visitTypeId) {
		if (visitTypeId == null)
			return null;

		String hql = "SELECT visitType FROM VisitType AS visitType WHERE visitType.visitTypeId = :visitTypeId AND visitType.status = 'A' AND visitType.organization.organizationId = :organizationId ";

		Query query = getSession().createQuery(hql);
		query.setParameter("visitTypeId", visitTypeId);
		query.setParameter("organizationId", Constants.OrgId);

		return (VisitType) query.uniqueResult();
	}

	@Override
	public Screen getScreen(Integer screenId) {
		String hql = "SELECT screen FROM Screen AS screen WHERE screen.id = :screenId AND screen.isDeleted = false AND screen.isActive = true ";

		Query query = getSession().createQuery(hql);
		query.setParameter("screenId", screenId);

		return (Screen) query.uniqueResult();
	}

	@Override
	public SaleDetail getSaleDetailById(Long id) {
		String hql = "SELECT saleDetail FROM SaleDetail AS saleDetail WHERE saleDetail.id = :id AND saleDetail.isDeleted = false AND saleDetail.isActive = true ";

		Query query = getSession().createQuery(hql);
		query.setParameter("id", id);

		return (SaleDetail) query.uniqueResult();
	}

	@Override
	public List<ScreenStatus> getScreenStatus(Integer screenId) {
		String hql = "SELECT screenStatus FROM ScreenStatus AS screenStatus \r\n"
				+ "		INNER JOIN screenStatus.screen as screen WITH screen.id = :screenId AND screen.isDeleted = false AND screen.isActive = true \r\n"
				+ "		INNER JOIN screenStatus.status as status WITH status.isDeleted = false AND status.isActive = true\r\n"
				+ "WHERE screenStatus.isActive = true AND screenStatus.isDeleted = false \r\n"
				+ "ORDER BY screenStatus.orderBy \r\n";

		Query query = getSession().createQuery(hql);
		query.setParameter("screenId", screenId);

		return query.list();
	}

	@Override
	public List<SaleType> getSalesTypeList() {
		String hql = "SELECT saleType FROM SaleType AS saleType WHERE saleType.isActive = true AND saleType.isDeleted = false AND saleType.unit.unitId = :unitId ";

		Query query = getSession().createQuery(hql);
		query.setParameter("unitId", Constants.UnitId);

		return query.list();
	}

	@Override
	public List<Prefix> getPrefixList() {
		String hql = "SELECT prefix FROM Prefix AS prefix \r\n"
				+ "WHERE prefix.status = 'A' AND prefix.organization.organizationId = :organizationId \r\n";

		Query query = getSession().createQuery(hql);
		query.setParameter("organizationId", Constants.OrgId);

		return query.list();
	}

	@Override
	public List<Gender> getGenderList() {
		String hql = "SELECT gender FROM Gender AS gender \r\n"
				+ "WHERE gender.status = 'A' AND gender.organization.organizationId = :organizationId \r\n";

		Query query = getSession().createQuery(hql);
		query.setParameter("organizationId", Constants.OrgId);

		return query.list();
	}

	@Override
	public List<DiscountCategory> getDiscountCategoryList() {
		String hql = "SELECT discountCategory FROM DiscountCategory AS discountCategory \r\n"
				+ "WHERE discountCategory.isDeleted = false AND discountCategory.isActive = true AND discountCategory.unit.unitId = :unitId \r\n";

		Query query = getSession().createQuery(hql);
		query.setParameter("unitId", Constants.UnitId);

		return query.list();
	}

	@Override
	public List<Store> getUserStoreList() {
		String hql = "SELECT store FROM Store AS store \r\n"
				+ "		INNER JOIN store.userStoreMappersList as userStore WITH userStore.userId = :userId AND userStore.isActive = true \r\n"
				+ "					AND userStore.isDeleted = false AND userStore.unit.unitId = :unitId \r\n"
				+ "WHERE store.isDeleted = false AND store.isActive = true AND store.unit.unitId = :unitId \r\n"
				+ "ORDER BY store.storeId";

		Query query = getSession().createQuery(hql);
		query.setParameter("userId", Constants.UserId);
		query.setParameter("unitId", Constants.UnitId);

		return query.list();
	}

	@Override
	public List<StoreType> getStoreTypeList() {
		String hql = "SELECT storeType FROM StoreType AS storeType WHERE storeType.isDeleted = false AND storeType.isActive = true AND storeType.unit.unitId = :unitId \r\n"
				+ "ORDER BY storeType.id";

		Query query = getSession().createQuery(hql);
		query.setParameter("unitId", Constants.UnitId);

		return query.list();
	}

	@Override
	public List<Store> getStoreList() {
		String hql = "SELECT store FROM Store AS store WHERE store.isDeleted = false AND store.isActive = true AND store.unit.unitId = :unitId \r\n"
				+ "ORDER BY store.storeId";

		Query query = getSession().createQuery(hql);
		query.setParameter("unitId", Constants.UnitId);

		return query.list();
	}

	@Override
	public List<IndentType> getIndentTypeList() {
		String hql = "SELECT indentType FROM IndentType AS indentType \r\n"
				+ "WHERE indentType.isDeleted = false AND indentType.isActive = true AND indentType.unit.unitId = :unitId \r\n"
				+ "ORDER BY indentType.id";

		Query query = getSession().createQuery(hql);
		query.setParameter("unitId", Constants.UnitId);

		return query.list();
	}

	@Override
	public List<Priority> getPriorityList() {
		String hql = "SELECT priority FROM Priority AS priority WHERE priority.status = 'A' AND priority.unitId = :unitId \r\n"
				+ "ORDER BY priority.priorityId";

		Query query = getSession().createQuery(hql);
		query.setParameter("unitId", Constants.UnitId);

		return query.list();
	}

	@Override
	public List<Batch> getBatchDetails(Integer id) {
		Date current = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(current);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		current = cal.getTime();

		String hql = "SELECT batch FROM Batch AS batch "
				+ "		INNER JOIN batch.item AS item WITH item.isDeleted = false AND item.isActive = true AND item.unit.unitId = :unitId "
				+ "		INNER JOIN FETCH batch.storeStockList AS storeStock "
				+ "WHERE item.id = :id AND batch.isDeleted = false AND batch.isActive = true AND batch.isQuarantine = false AND batch.unit.unitId = :unitId "
				+ "		AND  storeStock.store.storeId = :storeId AND storeStock.availableQty > 0 AND batch.expiryDate > :greaterThanCurrentMonth "
				+ " ORDER BY batch.expiryDate ASC";

		Query query = getSession().createQuery(hql);
		query.setParameter("id", id);
		query.setParameter("unitId", Constants.UnitId);
		query.setParameter("storeId", Constants.StoreId);
		query.setParameter("greaterThanCurrentMonth", current);

		return query.list();
	}

	@Override
	public Long getStoreStockByItem(Integer storeId, Integer itemId) {

		String hql = "SELECT COALESCE(SUM(storeStock.availableQty), 0) FROM Batch AS batch \r\n"
				+ "		INNER JOIN batch.storeStockList AS storeStock WITH storeStock.isDeleted = false AND storeStock.isActive = true AND storeStock.unit.unitId = :unitId \r\n"
				+ "		INNER JOIN batch.item AS item WITH item.isDeleted = false AND item.isActive = true AND item.unit.unitId = :unitId \r\n"
				+ "		INNER JOIN storeStock.store AS store WITH store.isDeleted = false AND store.isActive = true AND store.unit.unitId = :unitId \r\n"
				+ "WHERE store.storeId = :storeId AND item.id = :itemId \r\n"
				+ "		AND batch.isDeleted = false AND batch.isActive = true AND batch.unit.unitId = :unitId \r\n";

		Query query = getSession().createQuery(hql);
		query.setParameter("storeId", storeId);
		query.setParameter("itemId", itemId);
		query.setParameter("unitId", Constants.UnitId);

		return (Long) query.uniqueResult();
	}

	@Override
	public StoreStock getStoreStockByBatchAndStore(Long batchId, Integer storeId) {
		String hql = " SELECT storeStock FROM StoreStock AS storeStock "
				+ "		INNER JOIN storeStock.batch AS batch WITH batch.isDeleted = false AND batch.isActive = true AND batch.unit.unitId = :unitId \r\n"
				+ "		INNER JOIN storeStock.store AS store WITH store.isDeleted = false AND store.isActive = true AND store.unit.unitId = :unitId \r\n"
				+ "		WHERE store.storeId = :storeId AND batch.id = :batchId \r\n "
				+ " 	AND storeStock.isActive = true AND storeStock.isDeleted = false AND storeStock.unit.unitId = :unitId ";

		Query query = getSession().createQuery(hql);
		query.setParameter("storeId", storeId);
		query.setParameter("batchId", batchId);
		query.setParameter("unitId", Constants.UnitId);

		return (StoreStock) query.uniqueResult();
	}

	@Override
	public Integer getStoreMaxQuantityByItem(Integer storeId, Integer itemId) {
		String hql = "SELECT COALESCE(storeItem.maxQty, 0) FROM StoreItemMapper AS storeItem "
				+ "		INNER JOIN storeItem.store AS store WITH store.storeId = :storeId "
				+ "		INNER JOIN storeItem.item AS item WITH item.id = :itemId";

		Query query = getSession().createQuery(hql);
		query.setParameter("storeId", storeId);
		query.setParameter("itemId", itemId);

		Integer maxStoreQuantity = (Integer) query.uniqueResult();
		return maxStoreQuantity != null ? maxStoreQuantity : 0;
	}

	@Override
	public Long getStoreStockInTransitByItem(Integer storeId, Integer itemId) {
		String hql = "SELECT COALESCE(SUM(storeIndentDetail.inTransitQuantity), 0) \r\n"
				+ "FROM StoreIndentDetail AS storeIndentDetail \r\n"
				+ "		INNER JOIN storeIndentDetail.storeIndent AS storeIndent \r\n"
				+ "		INNER JOIN storeIndentDetail.item AS item \r\n"
				+ "WHERE item.id = :itemId AND storeIndent.fromStore.storeId = :storeId \r\n"
				+ "		AND storeIndentDetail.isActive = true AND storeIndentDetail.isDeleted = false ";

		Query query = getSession().createQuery(hql);
		query.setParameter("storeId", storeId);
		query.setParameter("itemId", itemId);

		return (Long) query.uniqueResult();
	}

	@Override
	public Long getLastMonthSale(Integer storeId, Integer itemId) {

		String hql = "SELECT COALESCE(SUM(lastMonthSale.saleQuantity), 0) FROM LastMonthSale AS lastMonthSale \r\n"
				+ "		INNER JOIN lastMonthSale.item AS item WITH item.id = :itemId AND item.isDeleted = false AND item.isActive = true AND item.unit.unitId = :unitId \r\n"
				+ "		INNER JOIN lastMonthSale.store AS store WITH store.storeId = :storeId AND store.isDeleted = false AND store.isActive = true AND store.unit.unitId = :unitId \r\n"
				+ "WHERE lastMonthSale.isDeleted = false AND lastMonthSale.isActive = true AND lastMonthSale.unit.unitId = :unitId  \r\n";

		Query query = getSession().createQuery(hql);
		query.setParameter("storeId", storeId);
		query.setParameter("itemId", itemId);
		query.setParameter("unitId", Constants.UnitId);

		return (Long) query.uniqueResult();
	}

	@Override
	public Long getCurrentMonthSale(Integer storeId, Integer itemId) {
		String hql = "SELECT COALESCE(SUM(saleDetail.leaseQuantity), 0) FROM SaleDetail AS saleDetail \r\n"
				+ "		INNER JOIN saleDetail.sale AS sale WITH sale.isDeleted = false AND sale.isActive = true AND sale.unit.unitId = :unitId \r\n"
				+ "		INNER JOIN sale.store AS store WITH store.id = :storeId AND store.isDeleted = false AND store.isActive = true \r\n"
				+ "		INNER JOIN saleDetail.batch AS batch \r\n"
				+ "		INNER JOIN batch.item AS item WITH item.id = :itemId \r\n"
				+ "WHERE saleDetail.isDeleted = false AND saleDetail.isActive = true AND saleDetail.unit.unitId = :unitId \r\n"
				+ "		AND date_trunc('month', saleDetail.addedDate) = date_trunc('month', current_date) \r\n";

		Query query = getSession().createQuery(hql);
		query.setParameter("storeId", storeId);
		query.setParameter("itemId", itemId);
		query.setParameter("unitId", Constants.UnitId);

		return (Long) query.uniqueResult();
	}

	public List<AssetType> getAssetTypeList() {
		String hql = "SELECT assetType FROM AssetType AS assetType \r\n"
				+ "WHERE assetType.isActive = true AND assetType.isDeleted = false  AND assetType.unit.unitId = :unitId "
				+ "ORDER BY assetType.type";

		Query query = getSession().createQuery(hql);
		query.setParameter("unitId", Constants.UnitId);

		return query.list();
	}

	@Override
	public List<AssetCategory> getAssetCategoryList(Integer assetTypeId) {
		String hql = "SELECT assetCategory FROM AssetCategory AS assetCategory "
				+ "	INNER JOIN assetCategory.assetType as assetType WITH assetType.id = :assetTypeId AND assetType.isDeleted = false AND assetType.isActive = true "
				+ "WHERE assetCategory.isActive = true AND assetCategory.isDeleted = false AND assetCategory.unit.unitId = :unitId "
				+ "ORDER BY assetCategory.category ";

		Query query = getSession().createQuery(hql);
		query.setParameter("assetTypeId", assetTypeId);
		query.setParameter("unitId", Constants.UnitId);

		return query.list();
	}

	@Override
	public List<ProductCategory> getProductCategoryList(Integer assetCategoryId) {
		String hql = "SELECT productCategory FROM ProductCategory AS productCategory "
				+ "	INNER JOIN productCategory.assetCategory as assetCategory WITH assetCategory.id = :assetCategoryId AND assetCategory.isDeleted = false AND assetCategory.isActive = true "
				+ "WHERE productCategory.isActive = true AND productCategory.isDeleted = false AND productCategory.unit.unitId = :unitId "
				+ "ORDER BY productCategory.category ";

		Query query = getSession().createQuery(hql);
		query.setParameter("assetCategoryId", assetCategoryId);
		query.setParameter("unitId", Constants.UnitId);

		return query.list();
	}

	@Override
	public List<UomResponse> getUnitOfMeasurementListByItem(Integer itemId) {

		String hql = "SELECT uomType.id AS uomTypeId, uomType.type AS uomType, uomUnit.id AS uomUnitId, uomUnit.units AS uomUnit, "
				+ "		uomType.code || ' - ' || uomUnit.units AS uomDetails, uom.conversions AS conversion, "
				+ "		uom.ipUom AS ipUom, uom.opUom AS opUom, uom.storeUom AS storeUom "
				+ "	FROM UnitOfMeasurement AS uom "
				+ "		INNER JOIN uom.uomType AS uomType WITH uomType.isActive = true AND uomType.isDeleted = false "
				+ "		INNER JOIN uom.uomUnit AS uomUnit WITH uomUnit.isActive=true AND uomUnit.isDeleted = false "
				+ "	WHERE uom.item.id = :itemId AND uom.isDeleted = false AND uom.isActive = true "
				+ "	ORDER BY uomType.id ";

		Query query = getSession().createQuery(hql);
		query.setParameter("itemId", itemId);
		query.setResultTransformer(Transformers.aliasToBean(UomResponse.class));

		return query.list();
	}

	@Override
	public List<Status> getStatusList() {
		String hql = "SELECT status FROM Status AS status "
				+ "WHERE status.isActive = true AND status.isDeleted = false ";

		Query query = getSession().createQuery(hql);
		return query.list();
	}

	@Override
	public List<VisitType> getVisitTypeList() {
		String hql = "SELECT visitType FROM VisitType AS visitType "
				+ "WHERE visitType.status = 'A' AND visitType.organization.organizationId = :organizationId ";

		Query query = getSession().createQuery(hql);
		query.setParameter("organizationId", Constants.OrgId);

		return query.list();
	}

	@Override
	public List<SaleType> getSaleTypeList() {
		String hql = "SELECT saleType FROM SaleType AS saleType "
				+ "WHERE saleType.isActive = true AND saleType.isDeleted = false "
				+ " AND saleType.organization.organizationId = :organizationId ";

		Query query = getSession().createQuery(hql);
		query.setParameter("organizationId", Constants.OrgId);

		return query.list();
	}

	@Override
	public Currency getCurrency() {
		String hql = "SELECT currency FROM Currency AS currency WHERE currency.unitId = :unitId AND  currency.status = 'A'\r\n";

		Query query = getSession().createQuery(hql);
		query.setParameter("unitId", Constants.UnitId);

		return (Currency) query.uniqueResult();
	}

	@Override
	public List<RejectReason> getRejectReasonList() {
		String hql = "SELECT rejectReason FROM RejectReason AS rejectReason \r\n"
				+ "WHERE rejectReason.isActive = true AND rejectReason.isDeleted = false AND rejectReason.unit.unitId = :unitId\r\n";

		Query query = getSession().createQuery(hql);
		query.setParameter("unitId", Constants.UnitId);
		return query.list();
	}

	@Override
	public List<CancelReason> getCancelReasonList() {
		String hql = "SELECT cancelReason FROM CancelReason AS cancelReason \r\n"
				+ "WHERE cancelReason.isActive = true AND cancelReason.isDeleted = false AND cancelReason.unit.unitId = :unitId\r\n";

		Query query = getSession().createQuery(hql);
		query.setParameter("unitId", Constants.UnitId);
		return query.list();
	}

	@Override
	public List<StoreIndent> getStoreIndentList(Integer fromStoreId, Integer toStoreId) {
		String hql = "SELECT storeIndent FROM StoreIndent AS storeIndent \r\n"
				+ "		LEFT JOIN storeIndent.fromStore as fromStore "
				+ "		LEFT JOIN storeIndent.toStore as toStore  "
				+ "WHERE storeIndent.isActive = true AND storeIndent.isDeleted = false AND storeIndent.unit.unitId = :unitId "
				+ "		AND fromStore.storeId = :fromStoreId AND toStore.storeId = :toStoreId "
				+ "		AND (storeIndent.approvalStatus.id = 5 OR storeIndent.approvalStatus.id = 6) ";

		Query query = getSession().createQuery(hql);
		query.setParameter("fromStoreId", fromStoreId);
		query.setParameter("toStoreId", toStoreId);
		query.setParameter("unitId", Constants.UnitId);
		return query.list();
	}

	public List<PharmacologicalClassification> getPharmacologicalClassificationList() {
		String hql = "SELECT pharmacologicalClassification FROM PharmacologicalClassification AS pharmacologicalClassification \r\n"
				+ "WHERE pharmacologicalClassification.isActive = true AND pharmacologicalClassification.isDeleted = false AND pharmacologicalClassification.unit.unitId = :unitId\r\n";

		Query query = getSession().createQuery(hql);
		query.setParameter("unitId", Constants.UnitId);
		return query.list();
	}

	@Override
	public List<StoreIndentDetail> getStoreIndentDetailsList(Integer storeIndentId) {
		String hql = "SELECT storeIndentDetail FROM StoreIndentDetail AS storeIndentDetail \r\n"
				+ "WHERE storeIndentDetail.storeIndent.id = :storeIndentId "
				+ "		AND (storeIndentDetail.status.id = 5 OR storeIndentDetail.status.id = 6) "
				+ "		AND storeIndentDetail.purchaseRequestDetail is null "
				+ "		AND storeIndentDetail.isActive = true AND storeIndentDetail.isDeleted = false";

		Query query = getSession().createQuery(hql);
		query.setParameter("storeIndentId", storeIndentId);
		return query.list();
	}

	@Override
	public List<StoreIndentDetail> getStoreIndentDetails(List<Integer> storeIndentDetailIds) {
		String hql = "SELECT storeIndentDetail FROM StoreIndentDetail AS storeIndentDetail \r\n"
				+ "WHERE storeIndentDetail.id in :storeIndentDetailIds "
				+ "		AND storeIndentDetail.isActive = true AND storeIndentDetail.isDeleted = false";

		Query query = getSession().createQuery(hql);

		query.setParameterList("storeIndentDetailIds", storeIndentDetailIds);
		return query.list();
	}

	public java.util.List<Manufacturer> getManufacturerList() {
		String hql = "SELECT manufacturer FROM Manufacturer AS manufacturer \r\n"
				+ "WHERE manufacturer.isActive = true AND manufacturer.isDeleted = false AND manufacturer.unit.unitId = :unitId\r\n";

		Query query = getSession().createQuery(hql);
		query.setParameter("unitId", Constants.UnitId);
		return query.list();
	}

	@Override
	public List<StrengthUnit> getStrengthUnitList() {
		String hql = "SELECT strengthUnit FROM StrengthUnit AS strengthUnit \r\n"
				+ "WHERE strengthUnit.isActive = true AND strengthUnit.isDeleted = false AND strengthUnit.unit.unitId = :unitId\r\n";

		Query query = getSession().createQuery(hql);
		query.setParameter("unitId", Constants.UnitId);
		return query.list();
	}

	@Override
	public List<StorageUnit> getStorageUnitList() {
		String hql = "SELECT storageUnit FROM StorageUnit AS storageUnit \r\n"
				+ "WHERE storageUnit.isActive = true AND storageUnit.isDeleted = false AND storageUnit.unit.unitId = :unitId\r\n";

		Query query = getSession().createQuery(hql);
		query.setParameter("unitId", Constants.UnitId);
		return query.list();
	}

	@Override
	public List<FormulationType> getFormulationTypeList(Integer productCategoryId) {
		String hql = "SELECT formulationType FROM FormulationType AS formulationType \r\n"
				+ "INNER JOIN formulationType.productCategory as productCategory WITH productCategory.id = :productCategoryId AND productCategory.isDeleted = false AND productCategory.isActive = true AND productCategory.unit.unitId = :unitId \r\n"
				+ "WHERE formulationType.isActive = true AND formulationType.isDeleted = false  AND formulationType.unit.unitId = :unitId \r\n";

		Query query = getSession().createQuery(hql);
		query.setParameter("productCategoryId", productCategoryId);
		query.setParameter("unitId", Constants.UnitId);

		return query.list();
	}

	@Override
	public List<Generic> getGenericList(Integer productCategoryId) {
		String hql = "SELECT generic FROM Generic AS generic \r\n"
				+ "INNER JOIN generic.productCategory as productCategory WITH productCategory.id = :productCategoryId AND productCategory.isDeleted = false AND productCategory.isActive = true AND productCategory.unit.unitId = :unitId \r\n"
				+ "WHERE generic.isActive = true AND generic.isDeleted = false  AND generic.unit.unitId = :unitId \r\n";

		Query query = getSession().createQuery(hql);
		query.setParameter("productCategoryId", productCategoryId);
		query.setParameter("unitId", Constants.UnitId);

		return query.list();
	}

	@Override
	public List<Tax> getTaxList() {
		String hql = "SELECT tax FROM Tax AS tax \r\n"
				+ "WHERE tax.isActive = true AND tax.isDeleted = false AND tax.unit.unitId = :unitId\r\n";

		Query query = getSession().createQuery(hql);
		query.setParameter("unitId", Constants.UnitId);

		return query.list();
	}

	@Override
	public List<PatientRegistration> patientSearchByName(String name) {

		String hql = "SELECT patient FROM PatientRegistrations AS patient "
				+ "INNER JOIN patient.prefix AS prefix WITH prefix.status = 'A' "
				+ "WHERE patient.status = 'A' AND patient.unitId = :unitId "
				+ " AND ((:name = '' OR lower(patient.firstName) LIKE :name) OR (:name = '' OR lower(patient.middleName) LIKE :name) "
				+ " OR (:name = '' OR lower(patient.lastName) LIKE :name)) ";

		Query query = getSession().createQuery(hql);
		query.setParameter("name", name != null ? name.toLowerCase() + '%' : "");
		query.setParameter("unitId", Constants.UnitId);

		return query.list();
	}

	@Override
	public List<PatientRegistration> patientSearchByUhid(String uhid) {

		String hql = "SELECT patient FROM PatientRegistrations AS patient "
				+ "INNER JOIN patient.prefix AS prefix WITH prefix.status = 'A' "
				+ "WHERE patient.status = 'A' AND patient.unitId = :unitId "
				+ " AND ((:uhid = '' OR lower(patient.uhidNumber) LIKE :uhid )) ";

		Query query = getSession().createQuery(hql);
		query.setParameter("uhid", uhid != null ? uhid.toLowerCase() + '%' : "");
		query.setParameter("unitId", Constants.UnitId);

		return query.list();
	}

	@Override
	public List<DoctorSpecialityMapper> doctorSearchByName(String name) {

		String hql = "SELECT doctorSpeciality FROM DoctorSpeciality AS doctorSpeciality "
				+ " INNER JOIN doctorSpeciality.doctor AS doctor WITH doctor.status = 'A' "
				+ " INNER JOIN doctorSpeciality.speciality AS speciality WITH speciality.status = 'A' "
				+ " WHERE  doctorSpeciality.status = 'A' AND doctorSpeciality.unitId = :unitId "
				+ " AND ((:name = '' OR lower(doctor.firstName) LIKE :name) OR (:name = '' OR lower(doctor.middleName) LIKE :name) "
				+ " OR (:name = '' OR lower(doctor.lastName) LIKE :name)) ";

		Query query = getSession().createQuery(hql);
		query.setParameter("name", name != null ? name.toLowerCase() + '%' : "");
		query.setParameter("unitId", Constants.UnitId);

		return query.list();
	}

	@Override
	public List<WardMasters> getWardList() {
		String hql = "SELECT wardMaster FROM WardMasters AS wardMaster "
				+ " WHERE wardMaster.organizationId = :organizationId AND wardMaster.unitId = :unitId"
				+ " AND wardMaster.status = 'A' ";

		Query query = getSession().createQuery(hql);
		query.setParameter("organizationId", Constants.OrgId);
		query.setParameter("unitId", Constants.UnitId);

		return query.list();
	}

	@Override
	public List<FloorMasters> getFloorList() {
		String hql = "SELECT floorMaster FROM FloorMasters AS floorMaster "
				+ " WHERE floorMaster.organizationId = :organizationId AND floorMaster.unitId = :unitId"
				+ " AND floorMaster.status = 'A' ";

		Query query = getSession().createQuery(hql);
		query.setParameter("organizationId", Constants.OrgId);
		query.setParameter("unitId", Constants.UnitId);

		return query.list();
	}

	@Override
	public List<DoctorSpecialityMapper> doctorSearchByIdValue(Integer id) {

		String hql = "SELECT doctorSpeciality FROM DoctorSpeciality AS doctorSpeciality "
				+ " INNER JOIN doctorSpeciality.doctor AS doctor WITH doctor.status = 'A' "
				+ " INNER JOIN doctorSpeciality.speciality AS speciality WITH speciality.status = 'A' "
				+ " WHERE  doctorSpeciality.status = 'A' AND doctorSpeciality.unitId = :unitId "
				+ " AND (:id = doctor.id)";

		Query query = getSession().createQuery(hql);
		query.setParameter("id", id);
		query.setParameter("unitId", Constants.UnitId);

		return query.list();
	}

	@Override
	public AdmissionDetail getPatientWardBedDetails(Integer admissionId) throws ApplicationException {
		try {
			String hql = "SELECT admissionDetail FROM AdmissionDetail AS admissionDetail "
					+ " INNER JOIN admissionDetail.admission AS admission WITH admission.status = 'A' "
					+ " WHERE (:untiId = admissionDetail.unitId) AND admissionDetail.status = 'A' AND (:admissionId = admission.admissionId)";

			Query query = getSession().createQuery(hql);
			query.setParameter("admissionId", admissionId);
			query.setParameter("untiId", Constants.UnitId);

			return (AdmissionDetail) query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<UomResponse> getBatchUomList(Long batchId) {
		String hql = "SELECT  uomType.id AS uomTypeId, uomType.type AS uomType, "
				+ "uomUnit.id AS uomUnitId, uomUnit.units AS uomUnit, batchUomMapper.conversions AS conversion, "
				+ "batchUomMapper.ipUom AS ipUom, batchUomMapper.opUom AS opUom, batchUomMapper.storeUom AS storeUom  "
				+ "FROM BatchUomMapper AS batchUomMapper " + "		INNER JOIN batchUomMapper.uomUnit AS uomUnit "
				+ "		INNER JOIN batchUomMapper.uomType AS uomType "
				+ " WHERE batchUomMapper.batch.id = :batchId ORDER BY uomType.id ";

		Query query = getSession().createQuery(hql);
		query.setParameter("batchId", batchId);
		query.setResultTransformer(Transformers.aliasToBean(UomResponse.class));

		return query.list();
	}

	@Override
	public List<Vendor> getVendorList(String name) {

		String hql = "SELECT vendor FROM Vendor as vendor "
				+ " WHERE lower(vendor.name) LIKE :name OR lower(vendor.code) LIKE :name "
				+ " AND vendor.isActive = true AND vendor.isDeleted = false ";
		Query query = getSession().createQuery(hql);
		query.setParameter("name", name != null ? name.toLowerCase() + '%' : "");
		return query.list();

	}

	@Override
	public List<Manufacturer> getManufacturerList(String name) {

		String hql = "SELECT manufacturer FROM Manufacturer as manufacturer "
				+ " WHERE lower(manufacturer.name) LIKE :name OR lower(manufacturer.code) LIKE :name "
				+ " AND manufacturer.isActive = true AND manufacturer.isDeleted = false ";
		Query query = getSession().createQuery(hql);
		query.setParameter("name", name != null ? name.toLowerCase() + '%' : "");
		// query.setParameter("unitId", Constants.UnitId);
		return query.list();

	}

	@Override
	public ItemsDetailsResponse itemsDetail(int id) {
		String hql = "SELECT item.id AS itemId, item.code AS itemCode, item.name AS itemName, generic.id AS genericId, generic.name AS genericName, "
				+ "		manufacturer.id AS manufacturerId, manufacturer.name AS manufacturerName, salesTax.id AS taxId, salesTax.name AS taxName, "
				+ "		salesTax.taxPercentage AS taxPercent, item.isOtc AS isOtc " + " FROM Item AS item "
				+ "		INNER JOIN item.generic AS generic WITH generic.isDeleted = false AND generic.isActive = true "
				+ "		INNER JOIN item.manufacturer AS manufacturer WITH manufacturer.isDeleted = false AND manufacturer.isActive = true "
				+ "		INNER JOIN item.salesTax AS salesTax WITH salesTax.isDeleted = false AND salesTax.isActive = true "
				+ "WHERE item.id = :id AND item.isDeleted = false AND item.isActive = true ";

		Query query = getSession().createQuery(hql);
		query.setParameter("id", id);
		query.setResultTransformer(Transformers.aliasToBean(ItemsDetailsResponse.class));

		return (ItemsDetailsResponse) query.uniqueResult();
	}

	@Override
	public User getUserById(Integer id) {
		String hql = "SELECT user FROM User AS user WHERE user.status = 'A' AND user.id = :id AND user.unitId = :unitId ";

		Query query = getSession().createQuery(hql);
		query.setParameter("id", id);
		query.setParameter("unitId", Constants.UnitId);

		return (User) query.uniqueResult();
	}

	@Override
	public List<User> getUser() {
		String hql = "SELECT user FROM User AS user WHERE user.status = 'A' AND user.unitId = :unitId ";

		Query query = getSession().createQuery(hql);
		query.setParameter("unitId", Constants.UnitId);

		return query.list();
	}

	@Override
	public Item getItemById(Integer id) {
		String hql = "SELECT item FROM Item AS item WHERE item.isActive = true AND item.isDeleted = false AND item.id = :id AND item.unit.unitId = :unitId ";

		Query query = getSession().createQuery(hql);
		query.setParameter("id", id);
		query.setParameter("unitId", Constants.UnitId);

		return (Item) query.uniqueResult();
	}

	public List<PurchaseRequest> searchLocalPuchaseRequest(AgainstPurchaseRequest purchaseRequestResponse,
			List<Integer> statusIds) {
		String hql = "SELECT purchaseRequest FROM PurchaseRequest AS purchaseRequest "
				+ " 	INNER JOIN purchaseRequest.addedBy as user WITH user.unitId = :unitId "
				+ " 	LEFT JOIN purchaseRequest.store as store WITH store.unit.unitId = :unitId "
				+ " 	INNER JOIN purchaseRequest.approvalStatus as approvalStatus "
				+ " WHERE purchaseRequest.isActive = true AND purchaseRequest.isDeleted = false AND purchaseRequest.unit.unitId = :unitId"
				+ "		AND purchaseRequest.addedDate BETWEEN :fromDate AND :toDate "
				+ " 	AND (:storeId = 0 OR store.id = :storeId) "
				+ " 	AND (:purchaseNo = '' OR lower(purchaseRequest.purchaseNo) LIKE :purchaseNo) "
				+ "     AND (approvalStatus.id IN (:approvalStatusId))" + " ORDER BY purchaseRequest.id DESC ";

		Query query = getSession().createQuery(hql);
		query.setParameter("fromDate", Util.getDate(purchaseRequestResponse.getFromDate()));
		query.setParameter("toDate", Util.getDate(purchaseRequestResponse.getToDate()));
		query.setParameter("storeId",
				purchaseRequestResponse.getStoreId() != null ? purchaseRequestResponse.getStoreId() : 0);
		query.setParameter("purchaseNo",
				purchaseRequestResponse.getPrNo() != null ? purchaseRequestResponse.getPrNo().toLowerCase() + '%' : "");
		query.setParameterList("approvalStatusId", statusIds);
		query.setParameter("unitId", Constants.UnitId);
		return query.list();
	}

	@Override
	public PurchaseRequest getPurchaseRequestList(Long id) {
		String hql = "SELECT purchaseRequest FROM PurchaseRequest AS purchaseRequest "
				+ "WHERE purchaseRequest.id = :id  AND purchaseRequest.isActive = true AND purchaseRequest.isDeleted = false ";
		Query query = getSession().createQuery(hql);
		query.setParameter("id", id);
		return (PurchaseRequest) query.uniqueResult();
	}

	@Override
	public List<PurchaseRequestDetailResponse> getItemsDetailsAgainstPr(Long Id, Integer itemId) {
		String hql = " SELECT purchaseRequestDetail.id AS id,purchaseRequestDetail.approvedQuantity AS prQuantity, item.id AS itemId, item.code AS itemCode, item.name AS itemName, generic.id AS genericId, generic.name AS genericName, "
				+ "	manufacturer.id AS manufacturerId, manufacturer.name AS manufacturerName, purchaseTax.id AS taxId, purchaseTax.name AS taxName, "
				+ " purchaseTax.taxPercentage AS taxPercent, uomType.id AS uomTypeId, uomType.type AS uomType, uomUnit.id AS uomUnitId, uomUnit.units AS uomUnit"
				+ " FROM PurchaseRequestDetail AS purchaseRequestDetail "
				+ "  INNER JOIN purchaseRequestDetail.item  AS item"
				+ "  INNER JOIN item.generic AS generic WITH generic.isDeleted = false AND generic.isActive = true "
				+ "  INNER JOIN item.manufacturer AS manufacturer WITH manufacturer.isDeleted = false AND manufacturer.isActive = true "
				+ "  INNER JOIN item.purchaseTax AS purchaseTax WITH purchaseTax.isDeleted = false AND purchaseTax.isActive = true "
				+ "  INNER JOIN purchaseRequestDetail.uomType AS uomType WITH uomType.isDeleted = false AND uomType.isActive = true "
				+ "  INNER JOIN purchaseRequestDetail.uomUnit AS uomUnit WITH uomUnit.isDeleted = false AND uomUnit.isActive = true "
				+ "  WHERE purchaseRequestDetail.item.id = :itemId AND purchaseRequestDetail.purchaseRequest.id = :Id AND purchaseRequestDetail.isActive = true AND purchaseRequestDetail.isDeleted = false "
				+ "  ORDER BY purchaseRequestDetail.id ";

		Query query = getSession().createQuery(hql);
		query.setParameter("itemId", itemId);
		query.setParameter("Id", Id);

		query.setResultTransformer(Transformers.aliasToBean(PurchaseRequestDetailResponse.class));
		return query.list();

	}

	@Override
	public List<OtherCharge> getOtherCharges() {
		String hql = "SELECT otherCharge FROM OtherCharge AS otherCharge WHERE otherCharge.isActive = true AND otherCharge.isDeleted = false\r\n";

		Query query = getSession().createQuery(hql);
		return query.list();
	}

	@Override
	public List<PaymentMode> getPaymentModeList() {
		String hql = "SELECT paymentMode FROM PaymentMode AS paymentMode \r\n" + "WHERE paymentMode.status = 'A' ";
		Query query = getSession().createQuery(hql);
		return query.list();
	}

	@Override
	public Batch getBatch(Long id) {
		String hql = "SELECT batch FROM Batch as batch  WHERE batch.id = :id  AND batch.isActive = true AND batch.isDeleted = false";

		Query query = getSession().createQuery(hql);
		query.setParameter("id", id);

		return (Batch) query.uniqueResult();

	}

	@Override
	public List<PurchaseOrder> searchPurchaseOrder(AgainstPurchaseOrder againstPurchaseOrder, List<Integer> statusIds) {
		String hql = "SELECT purchaseOrder FROM PurchaseOrder AS purchaseOrder "
				+ " 	INNER JOIN purchaseOrder.addedBy as user WITH user.unitId = :unitId "
				+ " 	LEFT JOIN purchaseOrder.store as store WITH store.unit.unitId = :unitId "
				+ "		LEFT JOIN purchaseOrder.status as status "
				+ " 	INNER JOIN purchaseOrder.approvalStatus as approvalStatus "
				+ " 	LEFT JOIN purchaseOrder.assetType as assetType "
				+ " 	LEFT JOIN purchaseOrder.requestType as requestType "
				+ " 	LEFT JOIN purchaseOrder.vendor as vendor "
				+ " WHERE purchaseOrder.isActive = true AND purchaseOrder.isDeleted = false AND purchaseOrder.unit.unitId = :unitId "
				+ " 	AND purchaseOrder.addedDate BETWEEN :fromDate AND :toDate "
				+ "		AND (:storeId = 0 OR store.id = :storeId) "
				+ " 	AND (:vendorId = 0 OR purchaseOrder.vendor.id = :vendorId) "
				+ " 	AND (:vendorName = '' OR lower(purchaseOrder.vendor.name) LIKE :vendorName ) "
				+ "     AND (approvalStatus.id IN (:approvalStatusId))" + " ORDER BY purchaseOrder.id DESC";

		Query query = getSession().createQuery(hql);
		query.setParameter("fromDate", Util.getDate(againstPurchaseOrder.getFromDate()));
		query.setParameter("toDate", Util.getDate(againstPurchaseOrder.getToDate()));
		query.setParameter("storeId",
				againstPurchaseOrder.getStoreId() != null ? againstPurchaseOrder.getStoreId() : 0);
		if (againstPurchaseOrder.getVendorId() != null && againstPurchaseOrder.getVendorId() > 0) {
			query.setParameter("vendorId",
					againstPurchaseOrder.getVendorId() != null ? againstPurchaseOrder.getVendorId() : 0);
			query.setParameter("vendorName", "");
		} else if (againstPurchaseOrder.getVendorName() != null) {
			query.setParameter("vendorId", 0);
			query.setParameter("vendorName",
					againstPurchaseOrder.getVendorName() != null
							? againstPurchaseOrder.getVendorName().toLowerCase() + '%'
							: "");
		} else {
			query.setParameter("vendorId", 0);
			query.setParameter("vendorName", "");
		}
		query.setParameterList("approvalStatusId", statusIds);
		query.setParameter("unitId", Constants.UnitId);
		return query.list();
	}

	@Override
	public PurchaseOrder getPurchaseOrderList(Integer id) {
		String hql = "SELECT purchaseOrder FROM PurchaseOrder AS purchaseOrder "
				+ "WHERE purchaseOrder.id = :id  AND purchaseOrder.isActive = true AND purchaseOrder.isDeleted = false ";
		Query query = getSession().createQuery(hql);
		query.setParameter("id", id);

		return (PurchaseOrder) query.uniqueResult();
	}

	@Override
	public List<PurchaseOrderStaged> getPurchaseOrderStaged(Integer id) {
		String hql = "SELECT purchaseOrderStaged FROM PurchaseOrderStaged AS purchaseOrderStaged "
				+ "WHERE purchaseOrderStaged.purchaseOrderDetail.id = :id  AND purchaseOrderDetail.isActive = true AND purchaseOrderDetail.isDeleted = false ";
		Query query = getSession().createQuery(hql);
		query.setParameter("id", id);
		return query.list();
	}

	@Override
	public List<PurchaseOrderDetailResponse> getItemsDetailsAgainstPO(Integer Id, Integer itemId) {
		String hql = " SELECT purchaseOrderDetail.id AS id, item.id AS itemId, item.code AS itemCode, item.name AS itemName, "
				+ " purchaseOrderDetail.poApprovedQuantity AS poQuantity,purchaseOrderDetail.bonusApprovedQuantity AS bonusQuantity,purchaseOrderDetail.cop AS cop,purchaseOrderDetail.mrp AS mrp, "
				+ " generic.id AS genericId, generic.name AS genericName, "
				+ "	manufacturer.id AS manufacturerId, manufacturer.name AS manufacturerName, purchaseTax.id AS taxId, purchaseTax.name AS taxName, "
				+ " purchaseTax.taxPercentage AS taxPercent" + " FROM PurchaseOrderDetail AS purchaseOrderDetail "
				+ "  INNER JOIN purchaseOrderDetail.item  AS item"
				+ "  INNER JOIN item.generic AS generic WITH generic.isDeleted = false AND generic.isActive = true "
				+ "  INNER JOIN item.manufacturer AS manufacturer WITH manufacturer.isDeleted = false AND manufacturer.isActive = true "
				+ "  INNER JOIN item.purchaseTax AS purchaseTax WITH purchaseTax.isDeleted = false AND purchaseTax.isActive = true "
				+ "  WHERE purchaseOrderDetail.item.id = :itemId AND purchaseOrderDetail.purchaseOrder.id = :Id AND purchaseOrderDetail.isActive = true AND purchaseOrderDetail.isDeleted = false "
				+ "  ORDER BY purchaseOrderDetail.id ";

		Query query = getSession().createQuery(hql);
		query.setParameter("itemId", itemId);
		query.setParameter("Id", Id);

		query.setResultTransformer(Transformers.aliasToBean(PurchaseOrderDetailResponse.class));
		return query.list();

	}

	@Override
	public StoreStock updateStoreStockLeaseQuantity(StoreStock storeStock) {
		getSession().update(storeStock);
		return storeStock;

		/*
		 * String hql =
		 * " UPDATE StoreStock AS storeStock SET storeStock.availableQty = :availableQty WHERE storeStock.store.storeId = :store AND storeStock.batch.id = :batch"
		 * ; Query query = getSession().createQuery(hql);
		 * query.setParameter("availableQty", storeStock.getAvailableQty());
		 * query.setParameter("store", storeStock.getStore().getStoreId());
		 * query.setParameter("batch", storeStock.getBatch().getId()); return
		 * storeStock;
		 */

	}

	@Override
	public StoreStock saveStoreStockLeaseQuantity(StoreStock storeStock) {
		getSession().save(storeStock);
		return storeStock;
	}

	@Override
	public Batch saveBatch(Batch batch) {
		getSession().save(batch);
		return batch;
	}

	@Override
	public Batch updateBatchLeaseQuantity(Batch batch) {
		getSession().update(batch);
		return batch;
	}

	@Override
	public StockTransaction saveStockTransaction(StockTransaction stockTransaction) {
		getSession().save(stockTransaction);
		return stockTransaction;
	}

	@Override
	public List<IssueDetailBatchMapper> getIssueDetailBatchMapper(Integer id) {
		String hql = " SELECT issueDetailBatchMapper FROM IssueDetailBatchMapper AS issueDetailBatchMapper "
				+ "	INNER JOIN issueDetailBatchMapper.issueDetail AS issueDetail "
				+ " WHERE issueDetail.id = :id AND issueDetail.isActive = true AND issueDetail.isDeleted = false "
				+ " ORDER BY issueDetail.id ";

		Query query = getSession().createQuery(hql);
		query.setParameter("id", id);

		return query.list();
	}

	@Override
	public IssueDetailBatchMapper getIssueDetailBatchMapperById(Integer id) {

		String hql = " SELECT issueDetailBatchMapper FROM IssueDetailBatchMapper AS issueDetailBatchMapper "
				+ " WHERE issueDetailBatchMapper.id = :id AND issueDetailBatchMapper.isActive = true AND issueDetailBatchMapper.isDeleted = false ";

		Query query = getSession().createQuery(hql);
		query.setParameter("id", id);

		return (IssueDetailBatchMapper) query.uniqueResult();

	}

	@Override
	public List<IssueDetail> getIssueDeatilByIssueId(Integer issueDetailId) {
		try {
			String hql = "  SELECT issueDetail FROM IssueDetail AS issueDetail "
					+ " INNER JOIN issueDetail.issue AS issue WITH issue.isActive = true AND issue.isDeleted = false "
					+ " WHERE issueDetail.isActive = true AND issueDetail.isDeleted = false " + " AND issue.id = :id ";

			Query query = getSession().createQuery(hql);
			query.setParameter("id", issueDetailId);

			return query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<FixedAssetType> getFixedAssetTypes() {
		String hql = "SELECT fixedAssetType FROM FixedAssetType AS fixedAssetType \r\n"
				+ "WHERE fixedAssetType.isActive = true AND fixedAssetType.isDeleted = false  AND fixedAssetType.unit.unitId = :unitId "
				+ "ORDER BY fixedAssetType.type";

		Query query = getSession().createQuery(hql);
		query.setParameter("unitId", Constants.UnitId);

		return query.list();
	}

	@Override
	public List<ReturnReason> getReturnReason() {
		String hql = "SELECT returnReason FROM ReturnReason AS returnReason WHERE returnReason.isDeleted = false AND returnReason.isActive = true "
				+ " AND returnReason.unit.unitId = :unitId ";

		Query query = getSession().createQuery(hql);
		query.setParameter("unitId", Constants.UnitId);

		return query.list();
	}

	@Override
	public List<Rack> getRacks(Integer storeId) {
		String hql = "SELECT rack.id AS id, rack.name AS rack FROM Rack AS rack "
				+ "		INNER JOIN rack.store AS store WITH store.storeId = :storeId AND store.isActive = true AND store.isDeleted = false "
				+ " WHERE rack.isActive = true AND rack.isDeleted = false " + "	ORDER BY rack.id ";

		Query query = getSession().createQuery(hql);
		query.setParameter("storeId", storeId);
		query.setResultTransformer(Transformers.aliasToBean(Rack.class));

		return query.list();
	}

	@Override
	public List<Shelf> getShelves(Integer rackId) {
		String hql = "SELECT shelf.id AS id, shelf.name AS shelf FROM Shelf AS shelf "
				+ "		INNER JOIN shelf.rack AS rack WITH rack.id = :rackId AND rack.isActive = true AND rack.isDeleted = false "
				+ " WHERE shelf.isActive = true AND shelf.isDeleted = false " + "	ORDER BY shelf.id ";

		Query query = getSession().createQuery(hql);
		query.setParameter("rackId", rackId);
		query.setResultTransformer(Transformers.aliasToBean(Shelf.class));

		return query.list();
	}

	@Override
	public List<AgainstGrnResponse> getGrn(AgainstGrnRequest request) {
		String hql = "SELECT grn.id AS id, grn.grnNumber AS grnNo, grn.addedDate AS grnDate, vendor.id AS vendorId, vendor.contactPersonName AS vendor, "
				+ "		store.storeId AS storeId, store.storeName AS store " + "	FROM GoodReceiptNote AS grn "
				+ "		INNER JOIN grn.vendor AS vendor WITH vendor.isActive = true AND vendor.isDeleted = false "
				+ "		INNER JOIN grn.store AS store WITH store.isActive = true AND store.isDeleted = false "
				+ "		INNER JOIN grn.status AS status WITH status.id = :statusId AND status.isActive = true AND status.isDeleted = false "
				+ " WHERE grn.isActive = true AND grn.isDeleted = false AND grn.unit.unitId = :unitId "
				+ "		AND grn.addedDate BETWEEN :fromDate AND :toDate "
				+ "		AND (:storeId = 0 OR store.id = :storeId) "
				+ " 	AND (:vendorId = 0 OR vendor.id = :vendorId) " + "	ORDER BY grn.id ";

		Query query = getSession().createQuery(hql);
		query.setParameter("fromDate", Util.getDate(request.getFromDate()));
		query.setParameter("toDate", Util.getDate(request.getToDate()));
		query.setParameter("storeId", request.getStoreId() != null ? request.getStoreId() : 0);
		query.setParameter("vendorId", request.getVendorId() != null ? request.getVendorId() : 0);
		query.setParameter("statusId", com.param.common.constants.Status.Closed.getStatus());
		query.setParameter("unitId", Constants.UnitId);

		query.setResultTransformer(Transformers.aliasToBean(AgainstGrnResponse.class));

		return query.list();
	}

	@Override
	public List<AgainstGrnDetailResponse> getGrnDetails(List<Integer> grnId) {
		String hql = "SELECT grn.id AS grnId, grnDetail.id AS grnDetailId, item.id AS itemId, item.code AS itemCode, item.name AS itemName, batch.id AS batchId, batch.batchNo AS batchNo, batch.expiryDate AS expiry, "
				+ "		batch.lotNo AS lotNo, batch.cop AS cop, vendor.id AS vendorId, vendor.contactPersonName AS vendor, COALESCE(SUM(storeStock.availableQty), 0) AS availableQuantity, "
				+ "		grnStore.storeId AS storeId, grnStore.storeName AS store, purchaseTax.id AS taxId, purchaseTax.name AS taxName, purchaseTax.taxPercentage AS taxPercentage, "
				+ "		uomType.id AS uomTypeId, uomType.type AS uomType, uomUnit.id AS uomUnitId, uomUnit.units AS uomUnit, (COALESCE(SUM(staged.receivedQuantity), 0) - COALESCE(grnDetail.grnApprovedQuantity, 0)) AS pendingQuantity "
				+ "	FROM GoodReceiptNote AS grn "
				+ "		INNER JOIN grn.vendor AS vendor WITH vendor.isActive = true AND vendor.isDeleted = false "
				+ "		INNER JOIN grn.store AS grnStore WITH grnStore.isActive = true AND grnStore.isDeleted = false "
				+ "		INNER JOIN grn.goodReceiptNoteDetailsList AS grnDetail WITH grnDetail.isActive = true AND grnDetail.isDeleted = false "
				+ "		INNER JOIN grnDetail.grnStagedQuantityList AS staged WITH staged.isActive = true AND staged.isDeleted = false "
				+ "		INNER JOIN grnDetail.status AS status WITH status.id = :statusId AND status.isActive = true AND status.isDeleted = false "
				+ "		INNER JOIN grnDetail.batch AS batch WITH batch.isActive = true AND batch.isDeleted = false "
				+ "		INNER JOIN batch.item AS item WITH item.isDeleted = false AND item.isActive = true "
				+ "		INNER JOIN item.purchaseTax AS purchaseTax WITH purchaseTax.isActive = true AND purchaseTax.isDeleted = false "
				+ "		LEFT JOIN batch.storeStockList AS storeStock WITH storeStock.isDeleted = false AND storeStock.isActive = true "
				+ "		LEFT JOIN storeStock.store AS store WITH store.isDeleted = false AND store.isActive = true "
				+ "		LEFT JOIN batch.batchUomList AS uom WITH uom.storeUom = true AND uom.isActive = true AND uom.isDeleted = false "
				+ "		LEFT JOIN uom.uomType AS uomType WITH uomType.isActive = true AND uomType.isDeleted = false "
				+ "		LEFT JOIN uom.uomUnit AS uomUnit WITH uomUnit.isActive=true AND uomUnit.isDeleted = false "
				+ " WHERE grn.isActive = true AND grn.isDeleted = false AND grn.id IN (:id) "
				+ "		AND store.storeId = grnStore.storeId "
				+ "	GROUP BY grn.id, grnDetail.id, item.id, item.code, item.name, batch.id, batch.batchNo, batch.expiryDate, batch.lotNo, batch.cop, vendor.id, vendor.contactPersonName, "
				+ "		grnStore.storeId, grnStore.storeName, purchaseTax.id, purchaseTax.name, purchaseTax.taxPercentage, uomType.id, uomType.type, uomUnit.id, uomUnit.units "
				+ "	ORDER BY grn.id ";

		Query query = getSession().createQuery(hql);
		query.setParameterList("id", grnId);
		query.setParameter("statusId", com.param.common.constants.Status.Closed.getStatus());

		query.setResultTransformer(Transformers.aliasToBean(AgainstGrnDetailResponse.class));

		return query.list();
	}

	@Override
	public List<ScrapReason> getScrapReasonList() {
		String hql = "SELECT scrapReason FROM ScrapReason AS scrapReason \r\n"
				+ "WHERE scrapReason.isActive = true AND scrapReason.isDeleted = false AND scrapReason.unit.unitId = :unitId\r\n";

		Query query = getSession().createQuery(hql);
		query.setParameter("unitId", Constants.UnitId);
		return query.list();
	}
}
