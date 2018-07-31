package com.param.global.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.param.exception.ApplicationException;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dao.ISystemMasterDao;
import com.param.global.dto.MasterOfSytemDto;
import com.param.global.dto.SystemMasterDto;

@Service
@SuppressWarnings({ "rawtypes", "unchecked" })
public class SystemMasterServiceImpl implements ISystemMasterService, ICommonConstants {

	@Autowired
	private ISystemMasterDao iSystemMasterDao;

	@Override
	@Transactional
	public Response getListOfSystemMaster(SystemMasterDto systemMasterDto) throws ApplicationException {
		return iSystemMasterDao.getListOfSystemMaster(systemMasterDto);
	}

	@Override
	@Transactional
	public Response saveSystemMaster(MasterOfSytemDto masterOfSytemDto) throws ApplicationException {
		return iSystemMasterDao.saveSystemMaster(masterOfSytemDto);
	}

	@Override
	@Transactional
	public Response getSystemMaster(MasterOfSytemDto masterOfSytemDto) throws ApplicationException {
		return iSystemMasterDao.getSystemMaster(masterOfSytemDto);
	}

	@Override
	@Transactional
	public Response updateSystemMasterStatus(MasterOfSytemDto masterOfSytemDto) throws ApplicationException {
		return iSystemMasterDao.updateSystemMasterStatus(masterOfSytemDto);
	}

	// added by dinesh jagatap on 21-04-18 Start
	@Override
	@Transactional
	public Response getListOfSystemMasterById(MasterOfSytemDto masterOfSytemDto) throws ApplicationException {
		try {
			return iSystemMasterDao.getListOfSystemMasterById(masterOfSytemDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	@Transactional
	public Response updateSystemMaster(MasterOfSytemDto masterOfSytemDto) throws ApplicationException {
		try {
			return iSystemMasterDao.updateSystemMaster(masterOfSytemDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	@Transactional
	public Response getListOfSystemMasterByType(MasterOfSytemDto masterOfSytemDto) throws ApplicationException {
		try {
				return iSystemMasterDao.getListOfSystemMasterByType(masterOfSytemDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

}
