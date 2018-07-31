package com.param.billing.global.transaction.dao;

import java.util.Date;

import org.springframework.stereotype.Repository;

import com.param.billing.dto.TPackageConsumptionCapDetailsDto;
import com.param.billing.exception.BillingException;
import com.param.billing.packages.model.TPackageConsumptionCapDetails;
import com.param.global.common.GlobalCommonDateUtils;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;

import in.param.common.dao.GenericDao;

@Repository
@SuppressWarnings({"rawtypes"})
public class TPackageConsumptionCapDetailsDaoImpl extends GenericDao<TPackageConsumptionCapDetails, Integer> implements ITPackageConsumptionCapDetailsDao, ICommonConstants{

	public TPackageConsumptionCapDetailsDaoImpl() {
		super(TPackageConsumptionCapDetails.class);
	}

	@Override
	public Response saveTPackageConsumptionCapDetails(TPackageConsumptionCapDetailsDto dto) throws BillingException {
		try{
			TPackageConsumptionCapDetails details = new TPackageConsumptionCapDetails();
			details.setPackageConsumptionMasterId(dto.getPackageConsumptionMasterId());
			details.setDepartmentId(dto.getDepartmentId());
			details.setSubDepartmentId(dto.getSubDepartmentId());
			details.setIsServiceItem(dto.getIsServiceItem());
			details.setProductCategoryId(dto.getProductCategoryId());
			details.setDepartmentCapAmount(dto.getDepartmentCapAmount());
			details.setSubDepartmentCapAmount(dto.getSubDepartmentCapAmount());
			details.setDepartmentBalanceCapAmount(dto.getDepartmentBalanceCapAmount());
			details.setSubDepartmentBalanceCapAmount(dto.getSubDepartmentBalanceCapAmount());
			details.setOrganisationId(dto.getOrganisationId());
			details.setUnitId(dto.getUnitId());
			details.setCreatedBy(dto.getCreatedBy());
			details.setCreatedDate(dto.getCreatedDate() != null && !dto.getCreatedDate().isEmpty() ? GlobalCommonDateUtils.getDate(dto.getCreatedDate(), "dd-M-yyyy HH:mm:ss") : new Date());
			details.setUpdatedBy(dto.getUpdatedBy());
			details.setUpdatedDate(dto.getUpdatedDate() != null && !dto.getUpdatedDate().isEmpty() ? GlobalCommonDateUtils.getDate(dto.getUpdatedDate(), "dd-M-yyyy HH:mm:ss") : null);
			details.setStatus(dto.getStatus());
			details = save(details);
			return new Response<>(SUCCESS, SUCCESS_CODE, null, null, null);
		}catch(Exception e){
			e.printStackTrace();
			throw new BillingException(e.getMessage());
		}
	}

}
