package com.param.service.services;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.global.common.ICommonConstants;
import com.param.global.common.IError;
import com.param.global.common.Response;
import com.param.service.dao.IEHCpackageDao;
import com.param.service.dto.ServiceForPackageReqDto;

import in.param.exception.ApplicationException;

@Service
@SuppressWarnings({"rawtypes", "unchecked"})
public class EHCpackageServiceImpl implements  IEHCpackageService, ICommonConstants, IError {

	 @Autowired
	  IEHCpackageDao iEHCpackageDao;


	  final static Logger logger = Logger.getLogger(EHCpackageServiceImpl.class);
	
	
	 @Override
	 @Transactional
	public Response getPackgeType(Integer orgId, Integer orgUnitId) throws ApplicationException {
		 try {
		      return iEHCpackageDao.getPackgeType(orgId,orgUnitId);
		    } catch (Exception e) {
		      logger.error("Exection", e);
		      return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		    }
	}


	@Override
	@Transactional
	public Response getAutoCompleteServices(Integer orgId, Integer orgUnitId, String serviceName)
			throws ApplicationException {
		try {
			return iEHCpackageDao.getAutoCompleteServices(orgId, orgUnitId, serviceName);
		} catch (Exception e) {
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}


	@Override
	@Transactional
	public Response getTariff(Integer orgId, Integer orgUnitId) throws ApplicationException {
		try {
			return iEHCpackageDao.getTariff(orgId, orgUnitId);
		} catch (Exception e) {
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}


	@Override
	@Transactional
	public Response getPaymentEntitlementByTariffId(Integer orgId, Integer orgUnitId, Integer tariffId)
			throws ApplicationException {
		try {
			return iEHCpackageDao.getPaymentEntitlementByTariffId(orgId, orgUnitId,tariffId);
		} catch (Exception e) {
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}


	@Override
	@Transactional
	public Response getPatientTypeByTariffId(Integer orgId, Integer orgUnitId, Integer tariffId)
			throws ApplicationException {
		try {
			return iEHCpackageDao.getPatientTypeByTariffId(orgId, orgUnitId,tariffId);
		} catch (Exception e) {
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}


	@Override
	@Transactional
	public Response getAutoCompletePackageCode(ServiceForPackageReqDto reqDto)
			throws ApplicationException {
		try {
			return iEHCpackageDao.getAutoCompletePackageCode(reqDto);
		} catch (Exception e) {
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}


	@Override
	@Transactional
	public Response getAutoCompletePackageName(Integer orgId, Integer orgUnitId, String serviceName)
			throws ApplicationException {
		try {
			return iEHCpackageDao.getAutoCompletePackageName(orgId, orgUnitId,serviceName);
		} catch (Exception e) {
			logger.error("Exection", e);
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

}
