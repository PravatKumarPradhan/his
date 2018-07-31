package com.param.adt.master.global.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.adt.common.ICommonConstants;
import com.param.adt.common.Response;
import com.param.adt.master.global.dao.IAdmissionTypeMasterDao;
import com.param.adt.master.global.dto.AdmissionTypeMasterDto;

import in.param.exception.ApplicationException;

@Service
@SuppressWarnings({ "rawtypes", "unchecked" })
public class AdmissionTypeMasterServiceImpl implements IAdmissionTypeMasterService,ICommonConstants {
	
	@Autowired
	IAdmissionTypeMasterDao iAdmissionTypeMasterDao;

	@Override
	public Response addAdmissionTypeMaster(AdmissionTypeMasterDto admissionTypeMasterDto) throws ApplicationException {
		try {
			Response deptResponse = iAdmissionTypeMasterDao.getAdmissionTypeByName(admissionTypeMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {

				return new Response(ERROR, null, ALREADY_EXIST, null, null);

			} else {

				iAdmissionTypeMasterDao.addAdmissionTypeMaster(admissionTypeMasterDto);
				return new Response(SUCCESS, null, COMMON_SUCCESS, null, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getAdmissionTypeMasterList(AdmissionTypeMasterDto admissionTypeMasterDto) throws ApplicationException {
		try {
			return iAdmissionTypeMasterDao.getAdmissionTypeMasterList(admissionTypeMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateAdmissionTypeMaster(AdmissionTypeMasterDto admissionTypeMasterDto)
			throws ApplicationException {
		try {
			Response deptResponse = iAdmissionTypeMasterDao.getAdmissionTypeByNameNotId(admissionTypeMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {
				return new Response(ERROR, null, ALREADY_EXIST, null, null);

			} else {

				/*admissionTypeMasterDto
						.setUpdatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyy HH:mm:ss"));*/

				iAdmissionTypeMasterDao.updateAdmissionTypeMaster(admissionTypeMasterDto);
				return new Response(SUCCESS, null, COMMON_UPDATE, null, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getAdmissionTypeById(AdmissionTypeMasterDto admissionTypeMasterDto) throws ApplicationException {
		try {
			return iAdmissionTypeMasterDao.getAdmissionTypeById(admissionTypeMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateStatusForAdmissionType(AdmissionTypeMasterDto admissionTypeMasterDto)
			throws ApplicationException {
		try {
			Response deptResponse = iAdmissionTypeMasterDao.getAdmissionTypeById(admissionTypeMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {

				/*admissionTypeMasterDto
						.setUpdatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyy HH:mm:ss"));*/

				iAdmissionTypeMasterDao.updateAdmissionTypeStatus(admissionTypeMasterDto);
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
	public Response getActiveAdmissionTypeList() throws ApplicationException {
		// TODO Auto-generated method stub
		try {
			return iAdmissionTypeMasterDao.getActiveAdmissionTypeMasterList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getAdmissionTypeCount(AdmissionTypeMasterDto admissionTypeMasterDto) throws ApplicationException {
		try {
			return iAdmissionTypeMasterDao.getCount(admissionTypeMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

} 
