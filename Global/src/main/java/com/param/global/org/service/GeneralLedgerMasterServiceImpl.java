package com.param.global.org.service;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.org.dao.IGeneralLedgerMasterServiceDao;
import com.param.global.org.dto.GeneralLedgerMasterDto;
import in.param.exception.ApplicationException;

@Service
@SuppressWarnings({ "rawtypes", "unchecked" })
public class GeneralLedgerMasterServiceImpl implements IGeneralLedgerMasterService, ICommonConstants {

	@Autowired
	IGeneralLedgerMasterServiceDao iGeneralLedgerMasterServiceDao;

	@Override
	public Response saveGeneralLedgerMaster(GeneralLedgerMasterDto generalLedgerMasterDto) throws ApplicationException {
		try {
			Response response = iGeneralLedgerMasterServiceDao.getGeneralLedgerMasterByName(generalLedgerMasterDto);
			if (response.getListObject().size() > 0 && response.getListObject() != null) {
				return new Response(ERROR, ALREADY_EXIST_CODE, ALREADY_EXIST, null, null);
			} else {
				return iGeneralLedgerMasterServiceDao.saveGeneralLedgerMaster(generalLedgerMasterDto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, null, null, null);
	}

	@Override
	public Response getGeneralLedgerMasterById(int lederId, int orgId) throws ApplicationException {
		try {
			return iGeneralLedgerMasterServiceDao.getGeneralLedgerMasterById(lederId, orgId);
		} catch (HibernateException he) {
			he.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, null, null, null);
	}

	@Override
	public Response getGeneralLedgerMasterList(int orgId) throws ApplicationException {
		try {
			return iGeneralLedgerMasterServiceDao.getGeneralLedgerMasterList(orgId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, null, null, null);
	}

	@Override
	public Response updateGeneralLedgerMaster(GeneralLedgerMasterDto generalLedgerMasterDto)
			throws ApplicationException {
		try {
			Response deptResponse = iGeneralLedgerMasterServiceDao.getLedgerByNameNotId(generalLedgerMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {
				return new Response(ERROR, null, ALREADY_EXIST, null, null);

			}else{
				return iGeneralLedgerMasterServiceDao.updateGeneralLedgerMaster(generalLedgerMasterDto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, null, null, null);
	}

	@Override
	public Response updateGeneralLedgerMasterStatus(GeneralLedgerMasterDto generalLedgerMasterDto)
			throws ApplicationException {
		try {
			return iGeneralLedgerMasterServiceDao.updateGeneralLedgerMasterStatus(generalLedgerMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, null, null, null);
	}

	@Override
	public Response getGeneralLedgerMasterActiveList(int orgId) throws ApplicationException {
		try {
			return iGeneralLedgerMasterServiceDao.getGeneralLedgerMasterActiveList(orgId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, null, null, null);
	}

}
