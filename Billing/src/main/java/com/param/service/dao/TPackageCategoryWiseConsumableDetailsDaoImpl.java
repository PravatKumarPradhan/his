package com.param.service.dao;

import java.util.Date;

import org.springframework.stereotype.Repository;

import com.param.billing.exception.ServiceException;
import com.param.global.common.GlobalCommonDateUtils;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.service.dto.TPackageCategoryWiseConsumableDetailsDto;
import com.param.service.global.model.TPackageCategoryWiseConsumableDetails;

import in.param.common.dao.GenericDao;

@Repository
@SuppressWarnings({"rawtypes"})
public class TPackageCategoryWiseConsumableDetailsDaoImpl extends GenericDao<TPackageCategoryWiseConsumableDetails, Integer> implements ITPackageCategoryWiseConsumableDetailsDao, ICommonConstants{

	public TPackageCategoryWiseConsumableDetailsDaoImpl() {
		super(TPackageCategoryWiseConsumableDetails.class);
	}

	@Override
	public Response saveTPackageCategoryWiseConsumableDetails(TPackageCategoryWiseConsumableDetailsDto detailsDto)throws ServiceException {
		try{
			TPackageCategoryWiseConsumableDetails details = new TPackageCategoryWiseConsumableDetails();
			details.setCategoryId(detailsDto.getCategoryId());
			details.setCreatedBy(detailsDto.getCreatedBy());
			details.setCreatedDate(detailsDto.getCreatedDate() != null && !detailsDto.getCreatedDate().isEmpty() ? GlobalCommonDateUtils.getDate(detailsDto.getCreatedDate(), "dd-M-yyyy HH:mm:ss") : new Date());
			details.setNumberToBeUse(detailsDto.getNumberToBeUse());
			details.setOrgId(detailsDto.getOrgId());
			details.setOrgUnitId(detailsDto.getOrgUnitId());
			details.setPackageId(detailsDto.getPackageId());
			details.setStatus(ACTIVE);
			details.setUpdatedBy(detailsDto.getUpdatedBy());
			details.setUpdatedDate(null);
			details = save(details);
			return new Response<>(SUCCESS, SUCCESS_CODE, null, null, details);
		}catch(Exception e){
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}

}
