package com.param.adt.master.unit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextException;
import org.springframework.stereotype.Service;

import com.param.adt.common.Response;
import com.param.adt.master.unit.dao.IBillingBedCategoryMasterDao;
import com.param.adt.master.unit.dto.BillingBedCategoryMasterDto;
import com.param.global.common.ICommonConstants;

import in.param.exception.ApplicationException;

@Service
@SuppressWarnings({"rawtypes","unchecked"})
public class BillingBedCategoryMasterServiceImpl implements IBillingBedCategoryMasterService, ICommonConstants {

	@Autowired
	IBillingBedCategoryMasterDao iBillingBedCategoryMasterDao;

	@Override
	public Response getActiveBillingBedCategoryList(BillingBedCategoryMasterDto bedCategoryMasterDto)
			throws ApplicationContextException {
		try {
			return iBillingBedCategoryMasterDao.getActiveBillingBedCategoryList(bedCategoryMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getActiveBillingBedCategoryByBedCat(BillingBedCategoryMasterDto bedCategoryMasterDto)
			throws ApplicationException {
		try {
			return iBillingBedCategoryMasterDao.getActiveBillingBedCategoryByBedCat(bedCategoryMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response saveBillingBedCategoryMaster(BillingBedCategoryMasterDto billingBedCategoryMasterDto)
			throws ApplicationException {
		Response response = iBillingBedCategoryMasterDao.getBillingBedCategoryByName(billingBedCategoryMasterDto);

		try {
			if (response.getListObject().size() > 0 && response.getListObject() != null) {
				return new Response(ERROR, null, ALREADY_EXIST, null, null);
			} else {
				return iBillingBedCategoryMasterDao.saveBillingBedCategoryMaster(billingBedCategoryMasterDto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getBillingBedCategoryById(int billingBedId, int orgId, int unitId) throws ApplicationException {
		try {
			return iBillingBedCategoryMasterDao.getBillingBedCategoryById(billingBedId, orgId, unitId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getBillingBedCategoryList(BillingBedCategoryMasterDto billingBedCategoryMasterDto)
			throws ApplicationException {
		try {
			return iBillingBedCategoryMasterDao.getBillingBedCategoryList(billingBedCategoryMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response updateBillingBedCategoryMaster(BillingBedCategoryMasterDto billingBedCategoryMasterDto)
			throws ApplicationException {
		Response response = iBillingBedCategoryMasterDao.getBillingBedCategoryByNameNotById(billingBedCategoryMasterDto);

		try {
			if (response.getListObject().size() > 0 && response.getListObject() != null) {
				return new Response(ERROR, null, ALREADY_EXIST, null, null);
			} else {
				return iBillingBedCategoryMasterDao.updateBillingBedCategoryMaster(billingBedCategoryMasterDto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response updateBillingBedCategoryMasterStatus(BillingBedCategoryMasterDto billingBedCategoryMasterDto)
			throws ApplicationException {
		try {
			return iBillingBedCategoryMasterDao.updateBillingBedCategoryMasterStatus(billingBedCategoryMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getBillingBedCategoryMasterCount(BillingBedCategoryMasterDto billingBedCategoryMasterDto)
			throws ApplicationException {
		try {
			return iBillingBedCategoryMasterDao.getBillingBedCategoryMasterCount(billingBedCategoryMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

}
