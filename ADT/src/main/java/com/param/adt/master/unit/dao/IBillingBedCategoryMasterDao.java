package com.param.adt.master.unit.dao;

import com.param.adt.common.Response;
import com.param.adt.master.unit.dto.BillingBedCategoryMasterDto;

import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IBillingBedCategoryMasterDao {

	Response getActiveBillingBedCategoryList(BillingBedCategoryMasterDto bedCategoryMasterDto)throws ApplicationException;

	Response getActiveBillingBedCategoryByBedCat(BillingBedCategoryMasterDto bedCategoryMasterDto)throws ApplicationException;
	
	Response saveBillingBedCategoryMaster(BillingBedCategoryMasterDto billingBedCategoryMasterDto)throws ApplicationException;

	Response getBillingBedCategoryById(int billingBedId, int orgId, int unitId)throws ApplicationException;

	Response getBillingBedCategoryList(BillingBedCategoryMasterDto billingBedCategoryMasterDto)throws ApplicationException;

	Response updateBillingBedCategoryMaster(BillingBedCategoryMasterDto billingBedCategoryMasterDto)throws ApplicationException;

	Response updateBillingBedCategoryMasterStatus(BillingBedCategoryMasterDto billingBedCategoryMasterDto)throws ApplicationException;

	Response getBillingBedCategoryMasterCount(BillingBedCategoryMasterDto billingBedCategoryMasterDto)throws ApplicationException;
	
	Response getBillingBedCategoryByName(BillingBedCategoryMasterDto billingBedCategoryMasterDto)throws ApplicationException;
	
	Response getBillingBedCategoryByNameNotById(BillingBedCategoryMasterDto billingBedCategoryMasterDto)throws ApplicationException;

}
