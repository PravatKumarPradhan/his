package com.param.adt.master.global.service;

import javax.transaction.Transactional;

import com.param.adt.common.Response;
import com.param.adt.master.global.dto.DeliveryTypeMasterDto;

import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IDeliveryTypeMasterService {

	@Transactional
	Response addDeliveryTypeMaster(DeliveryTypeMasterDto deliveryTypeMasterDto) throws ApplicationException;

	@Transactional
	Response getDeliveryTypeMasterList(DeliveryTypeMasterDto deliveryTypeMasterDto) throws ApplicationException;

	@Transactional
	Response updateDeliveryTypeMaster(DeliveryTypeMasterDto deliveryTypeMasterDto) throws ApplicationException;

	@Transactional
	Response getDeliveryTypeById(DeliveryTypeMasterDto deliveryTypeMasterDto) throws ApplicationException;

	@Transactional
	Response updateStatusForDeliveryType(DeliveryTypeMasterDto deliveryTypeMasterDto) throws ApplicationException;

	@Transactional
	Response getActiveDeliveryTypeList() throws ApplicationException;

	@Transactional
	Response getDeliveryTypeCount(DeliveryTypeMasterDto deliveryTypeMasterDto) throws ApplicationException;

}
