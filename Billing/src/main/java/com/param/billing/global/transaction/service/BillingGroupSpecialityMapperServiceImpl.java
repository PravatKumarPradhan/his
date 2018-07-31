package com.param.billing.global.transaction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.param.billing.common.ICommonConstants;
import com.param.billing.common.IError;
import com.param.billing.common.Response;
import com.param.billing.global.dto.SpecialityMasterDto;
import com.param.billing.global.transaction.dao.IBillingGroupSpecialityMapperDao;
import com.param.billing.global.transaction.dto.BillingGroupSpecialityMapperDto;
import com.param.billing.global.transaction.dto.BillingGroupSpecialityMasterDto;
import com.param.billing.global.transaction.model.BillingGroupSpecialityMapperId;

import in.param.exception.ApplicationException;
@Service
public class BillingGroupSpecialityMapperServiceImpl implements IBillingGroupSpecialityMapperService, ICommonConstants, IError{

	@Autowired
	private IBillingGroupSpecialityMapperDao iBillingGroupSpecialityMapperDao;
	
	@SuppressWarnings("rawtypes")
	@Override
	@Transactional
	public Response saveBillingGroupSpecialityMapper(BillingGroupSpecialityMasterDto billingGroupSpecialityMasterDto)
			throws ApplicationException {
		try {
				BillingGroupSpecialityMapperDto billingGroupSpecialityMapperDto = null;
				BillingGroupSpecialityMapperId billingGroupSpecialityMapperId = null;
			for(SpecialityMasterDto specialityMasterDto : billingGroupSpecialityMasterDto.getListSpecialityMasterDto()) {
				billingGroupSpecialityMapperDto = new BillingGroupSpecialityMapperDto();
				billingGroupSpecialityMapperId = new BillingGroupSpecialityMapperId();
				
				billingGroupSpecialityMapperId.setBillingGroupMasterId(billingGroupSpecialityMasterDto.getBillingGroupId());
				billingGroupSpecialityMapperId.setSpecialityMasterId(specialityMasterDto.getSpecialityId());
				billingGroupSpecialityMapperDto.setBillingGroupSpecialityMapperId(billingGroupSpecialityMapperId);
				billingGroupSpecialityMapperDto.setStatus(ACTIVE);
				billingGroupSpecialityMapperDto.setOrgnizationId(billingGroupSpecialityMasterDto.getOrgnisationId());
				billingGroupSpecialityMapperDto.setUnitId(billingGroupSpecialityMasterDto.getUnitId());
				/*billingGroupSpecialityMapperDto.setCratedDate();
				billingGroupSpecialityMapperDto.setCreatedBy();
				billingGroupSpecialityMapperDto.setUpdatedBy();
				billingGroupSpecialityMapperDto.setUpdatedDate();*/
				iBillingGroupSpecialityMapperDao.saveBillingGroupSpecialityMapper(billingGroupSpecialityMapperDto);
			}
			return new Response<>(SUCCESS, SUCCESS_CODE, COMMON_SUCCESS, null, null);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	}

}
