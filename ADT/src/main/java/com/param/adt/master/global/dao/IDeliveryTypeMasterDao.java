package com.param.adt.master.global.dao;

import com.param.adt.common.Response;
import com.param.adt.master.global.dto.DeliveryTypeMasterDto;
import com.param.adt.master.global.model.DeliveryTypeMaster;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IDeliveryTypeMasterDao extends IGenericDao<DeliveryTypeMaster, Integer>{

	
	Response getDeliveryTypeByName(DeliveryTypeMasterDto deliveryTypeMasterDto) throws ApplicationException;

	Response addDeliveryTypeMaster(DeliveryTypeMasterDto deliveryTypeMasterDto) throws ApplicationException;

	Response getDeliveryTypeByNameNotId(DeliveryTypeMasterDto deliveryTypeMasterDto) throws ApplicationException;

	Response updateDeliveryTypeMaster(DeliveryTypeMasterDto deliveryTypeMasterDto) throws ApplicationException;

	Response getDeliveryTypeById(DeliveryTypeMasterDto deliveryTypeMasterDto) throws ApplicationException;

	Response updateDeliveryTypeStatus(DeliveryTypeMasterDto deliveryTypeMasterDto) throws ApplicationException;

	Response getActiveDeliveryTypeList() throws ApplicationException;

	Response getDeliveryTypeMasterList(DeliveryTypeMasterDto deliveryTypeMasterDto) throws ApplicationException;

	Response getCount(DeliveryTypeMasterDto deliveryTypeMasterDto) throws ApplicationException;

}
