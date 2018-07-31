package com.param.service.dao;

import java.util.Date;

import org.springframework.stereotype.Repository;

import com.param.billing.exception.ServiceException;
import com.param.global.common.GlobalCommonDateUtils;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.service.dto.TPackageIncExcDetailsDto;
import com.param.service.global.model.TPackageIncExcDetails;

import in.param.common.dao.GenericDao;

@Repository
@SuppressWarnings({"rawtypes"})
public class TPackageIncExcDetailsDaoImpl extends GenericDao<TPackageIncExcDetails, Integer> implements ITPackageIncExcDetailsDao, ICommonConstants{

	public TPackageIncExcDetailsDaoImpl() {
		super(TPackageIncExcDetails.class);
	}

	@Override
	public Response saveTPackageIncExcDetails(TPackageIncExcDetailsDto detailsDto) throws ServiceException {
		try{
			TPackageIncExcDetails details = new TPackageIncExcDetails();
			details.setCreatedBy(detailsDto.getCreatedBy());
			details.setCreatedDate(detailsDto.getCreatedDate() != null && !detailsDto.getCreatedDate().isEmpty() ? GlobalCommonDateUtils.getDate(detailsDto.getCreatedDate(), "dd-M-yyyy HH:mm:ss") : new Date());
			details.setIsInclusiveService(detailsDto.getIsInclusiveService());
			details.setIsServiceItem(detailsDto.getIsServiceItem());
			details.setItemId(detailsDto.getItemId());
			details.setOrgId(detailsDto.getOrgId());
			details.setOrgUnitId(detailsDto.getOrgUnitId());
			details.setPackageCategoryConsumableId(detailsDto.getPackageCategoryConsumableId());
			details.setPackageId(detailsDto.getPackageId());
			details.setServiceId(detailsDto.getServiceId());
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
