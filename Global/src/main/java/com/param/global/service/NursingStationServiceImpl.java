package com.param.global.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.param.exception.ApplicationException;

import com.param.global.common.Response;
import com.param.global.dao.INursingStationDao;
import com.param.global.dto.NursingStationMasterDto;

@Service
public class NursingStationServiceImpl implements INursingStationService {

	@Autowired
	private INursingStationDao iNursingStationDao;
	
	
	@Override
	@Transactional
	public Response getListOfNursingStationMaster(NursingStationMasterDto nursingStationMasterDto)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return iNursingStationDao.getListOfNursingStationMaster(nursingStationMasterDto);
	}


	@Override
	@Transactional
	public Response getListOfNursingStationMasterByClinicId(
			NursingStationMasterDto nursingStationMasterDto)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return iNursingStationDao.getListOfNursingStationMasterByClinicId(nursingStationMasterDto);
	}

}
