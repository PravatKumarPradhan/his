package com.param.global.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.param.exception.ApplicationException;

import com.param.global.common.Response;
import com.param.global.dao.IDrugMasterDao;
import com.param.global.dto.DrugMasterDto;

@Service
public class DrugMasterServiceImpl implements IDrugMasterService {

	@Autowired
	private IDrugMasterDao iDrugMasterDao;
	
	@Override
	@Transactional
	public Response getDrugMasterList(DrugMasterDto drugMasterDto)throws ApplicationException {
		// TODO Auto-generated method stub
		return iDrugMasterDao.getDrugMasterList(drugMasterDto);
	}

}
