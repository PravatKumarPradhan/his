package com.param.global.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.param.exception.ApplicationException;

import com.param.global.common.Response;
import com.param.global.dao.IAllergyTypeMasterDao;
import com.param.global.dto.AllergyTypeMasterDto;

@Service
public class AllergyTypeMasterServiceImpl implements IAllergyTypeMasterService {

	@Autowired
	private IAllergyTypeMasterDao iAllergyTypeMasterDao;
	
	@Override
	@Transactional
	public Response getListOfAllergyTypeMaster(AllergyTypeMasterDto allergyTypeMasterDto)throws ApplicationException {
		// TODO Auto-generated method stub
		return iAllergyTypeMasterDao.getListOfAllergyTypeMaster(allergyTypeMasterDto);
	}

}
