package com.param.global.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.param.exception.ApplicationException;

import com.param.global.common.Response;
import com.param.global.dao.ISeverityMasterDao;
import com.param.global.dto.SeverityMasterDto;

@Service
public class SeverityMasterServiceImpl implements ISeverityMasterService{

	@Autowired
	private ISeverityMasterDao iSeverityMasterDao;
	
	@Override
	@Transactional
	public Response getListOfSeverityMaster(SeverityMasterDto severityMasterDto)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return iSeverityMasterDao.getListOfSeverityMaster(severityMasterDto);
	}

}
