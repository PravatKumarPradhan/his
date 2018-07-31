package com.param.global.utility;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.param.entity.model.base.UomEntity;
import com.param.entity.model.inventory.Batch;
import com.param.entity.model.inventory.BatchUomMapper;
import com.param.entity.model.master.UnitOfMeasurement;
import com.param.entity.model.master.UomType;
import com.param.global.dto.items.BatchResponse;
import com.param.global.dto.items.UomResponse;

public class Util {

	public static Integer getAgeByBirthDate(LocalDate birthDate) {
		Timestamp now = new Timestamp(System.currentTimeMillis());
		return Period.between(birthDate, now.toLocalDateTime().toLocalDate()).getYears();
	}

	public static UomResponse getStoreUom(List<UomResponse> uomList) {

		for (UomResponse uom : uomList) {
			if (uom.getStoreUom()) {
				return uom;
			}
		}

		return null;
	}

	public static UomResponse getOpDispenseUom(List<UomResponse> uomList) {

		for (UomResponse uom : uomList) {
			if (uom.getOpUom()) {
				return uom;
			}
		}

		return null;
	}

	public static int getOpUomConversion(List<UomResponse> uomList) {
		int conversion = 1;
		boolean opUom = false;

		for (UomResponse uom : uomList) {
			if (uom.getOpUom() || opUom) {
				opUom = true;
				conversion = conversion * uom.getConversion();
			}
		}

		return conversion;
	}

	public static int getIpUomConversion(List<UomResponse> uomList) {
		int conversion = 1;
		boolean ipUom = false;

		for (UomResponse uom : uomList) {
			if (uom.getIpUom() || ipUom) {
				ipUom = true;
				conversion = conversion * uom.getConversion();
			}
		}

		return conversion;
	}

	public static int getStoreUomConversion(List<UomResponse> uomList) {
		int conversion = 1;
		boolean storeUom = false;

		for (UomResponse uom : uomList) {
			if (uom.getStoreUom() || storeUom) {
				storeUom = true;
				conversion = conversion * uom.getConversion();
			}
		}

		return conversion;
	}

	public static BatchResponse PopulateBatch(Batch batch) {
		
			return new BatchResponse(batch.getId(), batch.getBatchNo(), batch.getExpiryDate(), batch.getSellingPrice(),
					PopulateUom(batch));
		
	}
	
	public static BatchResponse PopulateBatchWithStock(Batch batch) {
		
			return new BatchResponse(batch.getId(), batch.getBatchNo(), batch.getExpiryDate(), batch.getSellingPrice(), batch.getStoreStockList().get(0).getAvailableQty(),
					PopulateUom(batch));
		
	}

	public static List<UomResponse> PopulateUom(Batch batch) {
		List<UomResponse> response = new ArrayList<UomResponse>();

		for (BatchUomMapper uomMapper : batch.getBatchUomList()) {
			response.add(new UomResponse(uomMapper.getUomType().getId(), uomMapper.getUomType().getType(),
					uomMapper.getUomUnit().getId(), uomMapper.getUomUnit().getUnits(),
					uomMapper.getUomType().getCode() + " - " + uomMapper.getUomUnit().getUnits(),
					uomMapper.getConversions(), uomMapper.getIpUom(), uomMapper.getOpUom(), uomMapper.getStoreUom()));
		}

		return response;
	}

	public static List<UomResponse> PopulateUom(List<UnitOfMeasurement> unitOfMeasurementList) {
		List<UomResponse> response = new ArrayList<UomResponse>();

		for (UnitOfMeasurement uom : unitOfMeasurementList) {
			response.add(new UomResponse(uom.getUomType().getId(), uom.getUomType().getType(), uom.getUomUnit().getId(),
					uom.getUomUnit().getUnits(), uom.getUomType().getCode() + " - " + uom.getUomUnit().getUnits(),
					uom.getConversions(), uom.getIpUom(), uom.getOpUom(), uom.getStoreUom()));
		}

		return response;
	}

	public static Date getDate(Date date) {

		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date dateFinal = new SimpleDateFormat("yyyy-MM-dd").parse(dateFormat.format(date));
			return dateFinal;
		} catch (Exception ex) {
			return date;
		}
	}
	
	public static <T extends UomEntity> Integer GetLeaseQuantity(Integer quantity, List<T> uomList, UomType uomType) {
		int conversion = 1;
		boolean calculate = false;

		uomList.sort(new Comparator<T>() {
			@Override
			public int compare(T uom1, T uom2) {
				if (uom1.getUomType().getId().equals(uom2.getUomType().getId())) {
					return 0;
				}

				return uom1.getUomType().getId() < uom2.getUomType().getId() ? -1 : 1;
			}
		});

		for (T uom : uomList) {
			if (uom.getUomType().getId().equals(uomType.getId()) || calculate) {
				calculate = true;
				conversion = conversion * uom.getConversions();
			}
		}

		return quantity * conversion;
	}
}
