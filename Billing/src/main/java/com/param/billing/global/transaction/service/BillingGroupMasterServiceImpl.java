package com.param.billing.global.transaction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.param.billing.common.ICommonConstants;
import com.param.billing.common.IError;
import com.param.billing.common.Response;
import com.param.billing.global.dto.BillingGroupMasterDto;
import com.param.billing.global.transaction.dao.IBillingGroupMasterDao;
import com.param.billing.global.transaction.dao.IBillingGroupMasterService;
import com.param.billing.global.transaction.dao.IBillingGroupSpecialityMapperDao;
import com.param.billing.global.transaction.dto.BillingGroupSpecialityMapperDto;
import com.param.billing.global.transaction.dto.BillingGroupSpecialityMasterDto;

import in.param.exception.ApplicationException;
@Service
@SuppressWarnings("rawtypes")
public class BillingGroupMasterServiceImpl implements IBillingGroupMasterService, ICommonConstants, IError{
	@Autowired
	private IBillingGroupMasterDao iBillingGroupMasterDao;
	@Autowired
	private IBillingGroupSpecialityMapperDao iBillingGroupSpecialityMapperDao; 
	
	@Autowired
	private IBillingGroupSpecialityMapperService iBillingGroupSpecialityMapperService;

	@Override
	@Transactional
	public Response saveBillingGroupMaster(BillingGroupSpecialityMasterDto billingGroupSpecialityMasterDto)
			throws ApplicationException {
		try {
			return iBillingGroupMasterDao.saveBillingGroupMaster(billingGroupSpecialityMasterDto);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	}

	@Override
	@Transactional
	public Response getBillingGroupMasterList(BillingGroupMasterDto billingGroupMasterDto) throws ApplicationException {
		try {
			return iBillingGroupMasterDao.getBillingGroupMasterList(billingGroupMasterDto);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	}

	@Override
	@Transactional
	public Response getBillingGroupMasterById(BillingGroupMasterDto billingGroupMasterDto) throws ApplicationException {
		try {
			return iBillingGroupMasterDao.getBillingGroupMasterById(billingGroupMasterDto);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	}

	@Override
	@Transactional
	public Response changeBillingGroupMasterStatus(BillingGroupMasterDto billingGroupMasterDto)
			throws ApplicationException {
		try {
			return iBillingGroupMasterDao.changeBillingGroupMasterStatus(billingGroupMasterDto);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	
	}

	@Override
	@Transactional
	public Response updateBillingGroupMaster(BillingGroupSpecialityMasterDto billingGroupSpecialityMasterDto)
			throws ApplicationException {
		try {
			Response billGroupUpdateResponse = iBillingGroupMasterDao.updateBillingGroupMaster(billingGroupSpecialityMasterDto);
			if(billGroupUpdateResponse.getStatus().equalsIgnoreCase(ERROR))
				return billGroupUpdateResponse;
			else {
				BillingGroupSpecialityMapperDto billingGroupSpecialityMapperDto = new BillingGroupSpecialityMapperDto();
				billingGroupSpecialityMapperDto.setBillingGroupMasterId(billingGroupSpecialityMasterDto.getBillingGroupId());
				Response deleteBillGroupSplecialityResponse = iBillingGroupSpecialityMapperDao.deleteBillingGroupSpecialityMapper(billingGroupSpecialityMapperDto);
				if(deleteBillGroupSplecialityResponse.getStatus().equalsIgnoreCase(SUCCESS)) {
					iBillingGroupSpecialityMapperService.saveBillingGroupSpecialityMapper(billingGroupSpecialityMasterDto);
				}
				return new Response<>(SUCCESS, SUCCESS_CODE, COMMON_UPDATE, null, null);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	}

	@Override
	@Transactional
	public Response getBillingGroupMasterTotalCount(BillingGroupMasterDto billingGroupMasterDto)
			throws ApplicationException {
		try {
			return iBillingGroupMasterDao.getBillingGroupMasterTotalCount(billingGroupMasterDto);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	}
}
