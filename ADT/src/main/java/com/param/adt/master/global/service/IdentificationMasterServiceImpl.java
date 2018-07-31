package com.param.adt.master.global.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.adt.common.ICommonConstants;
import com.param.adt.common.Response;
import com.param.adt.master.global.dao.IIdentificationMasterDao;
import com.param.adt.master.global.dto.IdentificationMasterDto;

import in.param.exception.ApplicationException;
@Service
@SuppressWarnings({"rawtypes","unchecked"})
public class IdentificationMasterServiceImpl implements ICommonConstants, IIdentificationMasterService
{

	@Autowired
	private IIdentificationMasterDao identificationDao;
	
	
	@Override
	public Response addIdentificationMaster(IdentificationMasterDto identificationMasterDto)
			throws ApplicationException {
		try {

			Response deptResponse = identificationDao.getIdentificationByName(identificationMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {
				
					return new Response(ERROR, null, ALREADY_EXIST, null, null);

				} else {
					/*identificationMasterDto.setCreatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta","dd-M-yyyy HH:mm:ss"));
				identificationMasterDto.setUpdatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta","dd-M-yyyy HH:mm:ss"));*/
					identificationDao.addIdentificationMaster(identificationMasterDto);
					return new Response(SUCCESS, null, COMMON_SUCCESS, null, null);
				}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	} 

	@Override
	public Response getIdentificationMasterList(IdentificationMasterDto identificationMasterDto) throws ApplicationException {
		try {
			return identificationDao.getIdentificationMasterList(identificationMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateIdentificationMaster(IdentificationMasterDto identificationMasterDto) throws ApplicationException {
		try {
			Response deptResponse = identificationDao.getIdentificationByNameNotId(identificationMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {

				return new Response(ERROR, null, ALREADY_EXIST, null, null);

			} else {
				/*identificationMasterDto.setUpdatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta","dd-M-yyyy HH:mm:ss"));*/
				identificationDao.updateIdentificationMaster(identificationMasterDto);
				return new Response(SUCCESS, null, COMMON_UPDATE, null, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getIdentificationById(IdentificationMasterDto identificationMasterDto) throws ApplicationException {
		try {
			return identificationDao.getIdentificationById(identificationMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateStatusForIdentification(IdentificationMasterDto identificationMasterDto)
			throws ApplicationException {
		try {
			Response deptResponse = identificationDao.getIdentificationById(identificationMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {
				/*System.out.println("Date:"+CommonDateUtils.getStringDateByzone("Asia/Calcutta","dd-M-yyyy HH:mm:ss"));
				identificationMasterDto.setUpdatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta","dd-M-yyyy HH:mm:ss"));*/
				identificationDao.updateIdentificationStatus(identificationMasterDto);
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
	public Response getActiveIdentificationList() throws ApplicationException {
		try {
			return identificationDao.getActiveIdentificationList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getIdentificationCount(IdentificationMasterDto identificationMasterDto)
			throws ApplicationException {
		try {
			return identificationDao.getCount(identificationMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

}
