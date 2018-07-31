package com.param.global.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.param.exception.ApplicationException;

import com.param.global.common.Response;
import com.param.global.dao.IAllergyMasterDao;
import com.param.global.dto.AllergyMasterDto;

@Service
public class AllergyMasterServiceImpl implements IAllergyMasterService {

	@Autowired
	private IAllergyMasterDao iAllergyMasterDao;
	
	@Override
	@Transactional
	public Response getListOfAllergyMaster(AllergyMasterDto allergyMasterDto)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return iAllergyMasterDao.getListOfAllergyMaster(allergyMasterDto);
	}

}
