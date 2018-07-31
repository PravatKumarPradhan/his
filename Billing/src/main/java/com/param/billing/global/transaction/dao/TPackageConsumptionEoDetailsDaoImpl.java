package com.param.billing.global.transaction.dao;

import java.util.Date;

import org.springframework.stereotype.Repository;

import com.param.billing.dto.TPackageConsumptionEoDetailsDto;
import com.param.billing.exception.BillingException;
import com.param.billing.packages.model.TPackageConsumptionEoDetails;
import com.param.global.common.GlobalCommonDateUtils;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;

import in.param.common.dao.GenericDao;

@Repository
@SuppressWarnings({"rawtypes"})
public class TPackageConsumptionEoDetailsDaoImpl extends GenericDao<TPackageConsumptionEoDetails, Integer> implements ITPackageConsumptionEoDetailsDao, ICommonConstants{

	public TPackageConsumptionEoDetailsDaoImpl() {
		super(TPackageConsumptionEoDetails.class);
	}

	@Override
	public Response saveTPackageConsumptionEoDetails(TPackageConsumptionEoDetailsDto dto) throws BillingException {
		try{
			TPackageConsumptionEoDetails details = new TPackageConsumptionEoDetails();
			details.setPackageConsumptionMasterId(dto.getPackageConsumptionMasterId());
			details.setIsServiceItemBed(dto.getIsServiceItemBed());
			details.setPacakgeEighterorGroupId(dto.getPacakgeEighterorGroupId());
			details.setProductCatId(dto.getProductCatId());
			details.setBedCatId(dto.getBedCatId());
			details.setTotalQuantity(dto.getTotalQuantity());
			details.setBalanceQuantity(dto.getBalanceQuantity());
			details.setGroupPrice(dto.getGroupPrice());
			details.setOrganisationId(dto.getOrganisationId());
			details.setUnitId(dto.getUnitId());
			details.setCreatedBy(dto.getCreatedBy());
			details.setCreatedDate(dto.getCreatedDate() != null && !dto.getCreatedDate().isEmpty() ? GlobalCommonDateUtils.getDate(dto.getCreatedDate(),"dd-M-yyyy HH:mm:ss") : new Date());
			details.setUpdatedBy(dto.getUpdatedBy());
			details.setUpdatedDate(dto.getUpdatedDate() != null && !dto.getUpdatedDate().isEmpty() ? GlobalCommonDateUtils.getDate(dto.getUpdatedDate(),"dd-M-yyyy HH:mm:ss") : new Date());
			details = save(details);
			return new Response<>(SUCCESS, SUCCESS_CODE, null, null, details);
		}catch(Exception e){
			e.printStackTrace();
			throw new BillingException(e.getMessage());
		}
	}

}
