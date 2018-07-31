package com.param.global.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dao.IUnitServiceMasterDetailsDao;
import com.param.global.dto.UnitServiceMasterDetailsDto;

import in.param.exception.ApplicationException;

@Service
@SuppressWarnings({ "rawtypes", "unchecked" })
public class UnitServiceMasterDetailsServiceImpl implements IUnitServiceMasterDetailsService, ICommonConstants {

	@Autowired
	private IUnitServiceMasterDetailsDao iUnitServiceMasterDetailsDao;

	@Override
	@Transactional
	public Response saveUnitServiceMasterDetails(UnitServiceMasterDetailsDto unitServiceMasterDetailsDto)
			throws ApplicationException {
		try {
			return iUnitServiceMasterDetailsDao.saveUnitServiceMasterDetails(unitServiceMasterDetailsDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	@Transactional
	public Response getPaginatedUnitServiceMasterDetailsList(UnitServiceMasterDetailsDto unitServiceMasterDetailsDto)
			throws ApplicationException {
		try {
			return iUnitServiceMasterDetailsDao.getPaginatedUnitServiceMasterDetailsList(unitServiceMasterDetailsDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	@Transactional
	public Response getUnitServiceMasterDetailsCount(UnitServiceMasterDetailsDto unitServiceMasterDetailsDto)
			throws ApplicationException {
		try {
			return iUnitServiceMasterDetailsDao.getUnitServiceMasterDetailsCount(unitServiceMasterDetailsDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	@Transactional
	public Response getUnitServiceMasterDetailsById(UnitServiceMasterDetailsDto unitServiceMasterDetailsDto)
			throws ApplicationException {
		try {
			return iUnitServiceMasterDetailsDao.getUnitServiceMasterDetailsById(unitServiceMasterDetailsDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	@Transactional
	public Response updateUnitServiceMasterDetails(UnitServiceMasterDetailsDto unitServiceMasterDetailsDto)
			throws ApplicationException {
		try {
			return iUnitServiceMasterDetailsDao.updateUnitServiceMasterDetails(unitServiceMasterDetailsDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	@Transactional
	public Response getAllActiveTax(Integer unitId) throws ApplicationException {
		try {
			return iUnitServiceMasterDetailsDao.getAllActiveTax(unitId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

}
