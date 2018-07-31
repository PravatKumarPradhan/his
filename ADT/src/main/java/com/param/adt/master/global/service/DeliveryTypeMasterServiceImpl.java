package com.param.adt.master.global.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.adt.common.ADTCommonDateUtils;
import com.param.adt.common.ICommonConstants;
import com.param.adt.common.Response;
import com.param.adt.master.global.dao.IDeliveryTypeMasterDao;
import com.param.adt.master.global.dto.DeliveryTypeMasterDto;

import in.param.exception.ApplicationException;
@SuppressWarnings({"rawtypes","unchecked"})
@Service
public class DeliveryTypeMasterServiceImpl implements IDeliveryTypeMasterService,ICommonConstants
{
	@Autowired
	private IDeliveryTypeMasterDao iDeliveryTypeMasterDao; 
	
	@Override
	public Response addDeliveryTypeMaster(DeliveryTypeMasterDto deliveryTypeMasterDto) throws ApplicationException {
		try {
			Response deptResponse = iDeliveryTypeMasterDao.getDeliveryTypeByName(deliveryTypeMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {
				
					return new Response(ERROR, null, ALREADY_EXIST, null, null);
				
			} else {
				deliveryTypeMasterDto
						.setCreatedDate(ADTCommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));
				deliveryTypeMasterDto
						.setUpdatedDate(ADTCommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));
				iDeliveryTypeMasterDao.addDeliveryTypeMaster(deliveryTypeMasterDto);
				return new Response(SUCCESS, null, COMMON_SUCCESS, null, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getDeliveryTypeMasterList(DeliveryTypeMasterDto deliveryTypeMasterDto) throws ApplicationException {
		try {
			return iDeliveryTypeMasterDao.getDeliveryTypeMasterList(deliveryTypeMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateDeliveryTypeMaster(DeliveryTypeMasterDto deliveryTypeMasterDto) throws ApplicationException {
		try {
			Response deptResponse = iDeliveryTypeMasterDao.getDeliveryTypeByNameNotId(deliveryTypeMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {
				return new Response(ERROR, null, ALREADY_EXIST, null, null);

			} else {
				deliveryTypeMasterDto
						.setUpdatedDate(ADTCommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));
				iDeliveryTypeMasterDao.updateDeliveryTypeMaster(deliveryTypeMasterDto);
				return new Response(SUCCESS, null, COMMON_UPDATE, null, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getDeliveryTypeById(DeliveryTypeMasterDto deliveryTypeMasterDto) throws ApplicationException {
		try {
			return iDeliveryTypeMasterDao.getDeliveryTypeById(deliveryTypeMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateStatusForDeliveryType(DeliveryTypeMasterDto deliveryTypeMasterDto)
			throws ApplicationException {
		try {
			Response deptResponse = iDeliveryTypeMasterDao.getDeliveryTypeById(deliveryTypeMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {
				
				deliveryTypeMasterDto
						.setUpdatedDate(ADTCommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));
				iDeliveryTypeMasterDao.updateDeliveryTypeStatus(deliveryTypeMasterDto);
				return new Response(SUCCESS, null, COMMON_UPDATE, null, null);

			} else {
				return new Response(ERROR, null, ALREADY_EXIST, null, null);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getActiveDeliveryTypeList() throws ApplicationException {
		try {
			return iDeliveryTypeMasterDao.getActiveDeliveryTypeList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getDeliveryTypeCount(DeliveryTypeMasterDto deliveryTypeMasterDto) throws ApplicationException {
		try {
			return iDeliveryTypeMasterDao.getCount(deliveryTypeMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

}
