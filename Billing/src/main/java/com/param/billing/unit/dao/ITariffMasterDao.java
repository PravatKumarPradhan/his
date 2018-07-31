package com.param.billing.unit.dao;

import com.param.billing.global.transaction.model.TariffMaster;
import com.param.global.common.Response;
import com.param.global.dto.TariffMasterDto;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface ITariffMasterDao extends IGenericDao<TariffMaster,Integer>{

	Response saveTariffMaster(TariffMasterDto tariffMasterDto)throws ApplicationException;
	
	Response saveTariffPaymentEntitlementMapper(TariffMasterDto tariffMasterDto)throws ApplicationException;

	Response getTariffListByName(TariffMasterDto tariffMasterDto)throws ApplicationException;

	Response getTariffMasterList(TariffMasterDto tariffMasterDto)throws ApplicationException;

	Response getPaymentEntitlementListByTariffid(TariffMasterDto tariffMasterDto)throws ApplicationException;

	Response getTariffListById(TariffMasterDto tariffMasterDto)throws ApplicationException;

	Response getTariffListByNameNotId(TariffMasterDto tariffMasterDto)throws ApplicationException;

	Response updateTariffMaster(TariffMasterDto tariffMasterDto)throws ApplicationException;

	Response inactiveOldPaymentEntitlements(TariffMasterDto tariffMasterDto)throws ApplicationException;

	Response updateStatusForTariff(TariffMasterDto tariffMasterDto)throws ApplicationException;

	Response updateStatusForTariffPaymentMapper(TariffMasterDto tariffMasterDto)throws ApplicationException;

	Response getActiveTariffMasterList(TariffMasterDto tariffMasterDto)throws ApplicationException;

	Response getPaymentEntitlementListByTariffIdList(TariffMasterDto tariffMasterDto)throws ApplicationException;;
	
}
