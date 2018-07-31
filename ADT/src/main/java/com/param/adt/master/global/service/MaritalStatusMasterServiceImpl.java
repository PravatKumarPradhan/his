package com.param.adt.master.global.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.adt.common.ICommonConstants;
import com.param.adt.common.Response;
import com.param.adt.master.global.dao.IMaritalStatusMasterDao;
import com.param.adt.master.global.dto.MaritalStatusDto;

import in.param.exception.ApplicationException;

@SuppressWarnings({ "unchecked", "rawtypes" })
@Service
public class MaritalStatusMasterServiceImpl implements IMaritalStatusMasterService, ICommonConstants {

	@Autowired
	private IMaritalStatusMasterDao iMaritalStatusMasterDao;

	@Override
	public Response addMaritalStatusMaster(MaritalStatusDto maritalStatusDto) throws ApplicationException {
		try {

			Response deptResponse = iMaritalStatusMasterDao.getMaritalStatusByName(maritalStatusDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {

				return new Response(ERROR, null, ALREADY_EXIST, null, null);

			} else {
				/*maritalStatusDto
						.setCreatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));
				maritalStatusDto
						.setUpdatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));*/
				iMaritalStatusMasterDao.addMaritalStatusMaster(maritalStatusDto);
				return new Response(SUCCESS, null, COMMON_SUCCESS, null, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getMaritalById(MaritalStatusDto maritalStatusDto) throws ApplicationException {
		try {
			return iMaritalStatusMasterDao.getMaritalStatusByID(maritalStatusDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateMaritalStatusMaster(MaritalStatusDto maritalStatusDto) throws ApplicationException {
		try {
			Response deptResponse = iMaritalStatusMasterDao.getMaritalStatusByNameNotId(maritalStatusDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {

				return new Response(ERROR, null, ALREADY_EXIST, null, null);

			} else {
				/*maritalStatusDto
						.setUpdatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));*/
				iMaritalStatusMasterDao.updateMaritalStatusMaster(maritalStatusDto);
				return new Response(SUCCESS, null, COMMON_UPDATE, null, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateStatusForMarital(MaritalStatusDto maritalStatusDto) throws ApplicationException {
		try {
			Response deptResponse = iMaritalStatusMasterDao.getMaritalStatusByID(maritalStatusDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {
				/*maritalStatusDto
						.setUpdatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));*/
				iMaritalStatusMasterDao.updateMaritalStatus(maritalStatusDto);
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
	public Response getActiveMaritalStatusList() throws ApplicationException {
		try {
			return iMaritalStatusMasterDao.getActiveMaritalStatusList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getMaritalStatusMasterList(MaritalStatusDto maritalStatusDto) throws ApplicationException {
		try {
			return iMaritalStatusMasterDao.getMaritalStatusMasterList(maritalStatusDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getMaritalStatusCount(MaritalStatusDto maritalStatusDto) throws ApplicationException {
		try {
			return iMaritalStatusMasterDao.getCount(maritalStatusDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

}
