package com.param.billing.global.transaction.config.dao;

import com.param.billing.common.Response;
import com.param.billing.global.transaction.config.dto.TarrifPaymentEntitlementMpprDto;
import com.param.billing.global.transaction.model.ServiceTarrifPaymentEntitlementManager;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;

public interface ITarrifPaymentCategoryDao extends IGenericDao<ServiceTarrifPaymentEntitlementManager, Integer>{

	Response savePaymentEntitlementFactor(TarrifPaymentEntitlementMpprDto paymentEntitlementMpprDto) throws ApplicationException;
	
	/**
	 * Method to get multiplication factor for Payment Category by given tarrif master Id
	 * @param tarrifMasterId
	 * @return
	 * @throws ApplicationException
	 */
	Response getPaymentCategoryMultiplicationFactorByTarrifId(Integer tarrifMasterId) throws ApplicationException;
}
