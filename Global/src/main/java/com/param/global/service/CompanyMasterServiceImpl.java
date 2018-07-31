package com.param.global.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dao.ICompanyMasterDao;
import com.param.global.dto.CompanyMasterDto;

import in.param.exception.ApplicationException;

@Service
@SuppressWarnings("rawtypes")
public class CompanyMasterServiceImpl implements ICompanyMasterService, ICommonConstants{

	@Autowired
	private ICompanyMasterDao iCompanyMasterDao;
	
	@Override
	@Transactional
	public Response getActiveCompanyMasterList(CompanyMasterDto companyMasterDto) throws ApplicationException {
		try {
			return iCompanyMasterDao.getActiveCompanyMasterList(companyMasterDto);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, COMMON_ERROR_CODE, null, null, null);
	}

}
