package com.param.opd.coversheet.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.param.exception.ApplicationException;

import com.param.global.common.Response;
import com.param.opd.coversheet.dao.IDiagnosisMasterDao;
import com.param.opd.coversheet.dto.DiagnosisMasterDto;

@Service
public class DiagnosisMasterServiceImpl implements IDiagnosisMasterService {

	@Autowired
	private IDiagnosisMasterDao iDiagnosisMasterDao;
	
	@Override
	@Transactional
	public Response getListOfDiagnosisMaster(DiagnosisMasterDto diagnosisMasterDto) throws ApplicationException {
		// TODO Auto-generated method stub
		return iDiagnosisMasterDao.getListOfDiagnosisMaster(diagnosisMasterDto);
	}

}
