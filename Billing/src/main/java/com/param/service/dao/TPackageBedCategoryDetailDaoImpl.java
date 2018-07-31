package com.param.service.dao;

import java.util.Date;

import org.springframework.stereotype.Repository;

import com.param.billing.exception.ServiceException;
import com.param.global.common.GlobalCommonDateUtils;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.service.dto.TPackageBedCategoryDetailDto;
import com.param.service.global.model.TPackageBedCategoryDetail;

import in.param.common.dao.GenericDao;

@Repository
@SuppressWarnings({"rawtypes"})
public class TPackageBedCategoryDetailDaoImpl extends GenericDao<TPackageBedCategoryDetail, Integer> implements ITPackageBedCategoryDetailDao, ICommonConstants{

	public TPackageBedCategoryDetailDaoImpl(){
		super(TPackageBedCategoryDetail.class);
	}

	@Override
	public Response saveTPackageBedCategoryDetail(TPackageBedCategoryDetailDto detailDto) throws ServiceException {
		try{
			TPackageBedCategoryDetail detail = new TPackageBedCategoryDetail();
			detail.setApplicableDays(detailDto.getApplicableDays());
			detail.setBedCategoryId(detailDto.getBedCategoryId());
			detail.setCreatedBy(detailDto.getCreatedBy());
			detail.setCreatedDate(detailDto.getCreatedDate() != null && !detailDto.getCreatedDate().isEmpty() ? GlobalCommonDateUtils.getDate(detailDto.getCreatedDate(), "dd-M-yyyy HH:mm:ss") : new Date());
			detail.setOrgId(detailDto.getOrgId());
			detail.setOrgUnitId(detailDto.getOrgUnitId());
			detail.setPackageId(detailDto.getPackageId());
			detail.setStatus(ACTIVE);
			detail.setTotalAmount(detailDto.getTotalAmount());
			detail.setUpdatedBy(detailDto.getUpdatedBy());
			detail.setUpdatedDate(null);
			detail = save(detail);
			return new Response<>(SUCCESS, SUCCESS_CODE, null, null, detail);
		}catch(Exception e){
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}
	
}
