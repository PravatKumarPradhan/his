package com.param.global.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.param.exception.ApplicationException;

import com.param.global.common.Response;
import com.param.global.dao.IVitalMasterDao;
import com.param.global.dto.VitalMasterDto;

@Service
public class VitalMasterServiceImpl implements IVitalMasterService {

	@Autowired
	private IVitalMasterDao iVitalMasterDao;
	
	@Override
	@Transactional
	public Response getVitalMasterList(VitalMasterDto vitalMasterDto)throws ApplicationException {
		// TODO Auto-generated method stub
		return iVitalMasterDao.getVitalMasterList(vitalMasterDto);
	}

}
