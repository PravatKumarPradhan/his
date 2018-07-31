package com.param.global.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.param.exception.ApplicationException;

import com.param.global.common.Response;
import com.param.global.dao.ISystemObservationPropertyDao;
import com.param.global.dto.SystemObservationMasterDto;

@Service
public class SystemObservationPropertyServiceImpl implements ISystemObservationPropertyService {

	@Autowired
	private ISystemObservationPropertyDao iSystemObservationPropertyDao;
	
	@Override
	@Transactional
	public Response saveSystemObservationProperty(SystemObservationMasterDto systemObservationPropertyMasterDto)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return iSystemObservationPropertyDao.saveSystemObservationProperty(systemObservationPropertyMasterDto);
	}

	@Override
	@Transactional
	public Response getSystemObservationProperty(SystemObservationMasterDto systemObservationPropertyMasterDto)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return iSystemObservationPropertyDao.getSystemObservationProperty(systemObservationPropertyMasterDto);
	}

	@Override
	@Transactional
	public Response updateSystemObservationPropertyStatus(SystemObservationMasterDto systemObservationPropertyMasterDto)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return iSystemObservationPropertyDao.updateSystemObservationPropertyStatus(systemObservationPropertyMasterDto);
	}

	@Override
	@Transactional
	public Response updateSystemObservationSinglePropertyStatus(
			SystemObservationMasterDto systemObservationPropertyMasterDto) throws ApplicationException {
		// TODO Auto-generated method stub
		return iSystemObservationPropertyDao.updateSystemObservationSinglePropertyStatus(systemObservationPropertyMasterDto);
	}

}
