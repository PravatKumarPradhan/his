package com.param.billing.global.transaction.dao;

import java.util.Date;

import org.springframework.stereotype.Repository;

import com.param.billing.dto.TPackageConsumptionServiceDetailDto;
import com.param.billing.exception.BillingException;
import com.param.billing.packages.model.TPackageConsumptionServiceDetail;
import com.param.global.common.GlobalCommonDateUtils;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;

import in.param.common.dao.GenericDao;

@Repository
@SuppressWarnings({"rawtypes"})
public class TPackageConsumptionServiceDetailDaoImpl extends GenericDao<TPackageConsumptionServiceDetail, Integer> implements ITPackageConsumptionServiceDetailDao, ICommonConstants{

	public TPackageConsumptionServiceDetailDaoImpl() {
		super(TPackageConsumptionServiceDetail.class);
	}

	@Override
	public Response saveTPackageConsumptionServiceDetail(TPackageConsumptionServiceDetailDto dto) throws Exception {
		try{
			TPackageConsumptionServiceDetail details = new TPackageConsumptionServiceDetail();
			details.setPackageConsumptionMasterId(dto.getPackageConsumptionMasterId());
			details.setServiceId(dto.getServiceId());
			details.setPackageQuantity(dto.getPackageQuantity());
			details.setBalancePackageQuantity(dto.getBalancePackageQuantity());
			details.setApportionedPrice(dto.getApportionedPrice());
			details.setIsServiceItem(dto.getIsServiceItem());
			details.setItemId(dto.getItemId());
			details.setPackageEoDetailId(dto.getPackageEoDetailId());
			details.setOrganisationId(dto.getOrganisationId());
			details.setUnitId(dto.getUnitId());
			details.setCreatedBy(dto.getCreatedBy());
			details.setCreatedDate(dto.getCreatedDate() != null && !dto.getCreatedDate().isEmpty() ? GlobalCommonDateUtils.getDate(dto.getCreatedDate(), "dd-M-yyyy HH:mm:ss") : new Date());
			details.setUpdatedBy(dto.getUpdatedBy());
			details.setUpdatedDate(dto.getUpdatedDate() != null && !dto.getUpdatedDate().isEmpty() ? GlobalCommonDateUtils.getDate(dto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss") : new Date());
			details.setStatus(ACTIVE);
			save(details);
			return new Response<>(SUCCESS, SUCCESS_CODE, null, null, details);
		}catch(Exception e){
			e.printStackTrace();
			throw new BillingException(e.getMessage());
		}
	}
	
}
