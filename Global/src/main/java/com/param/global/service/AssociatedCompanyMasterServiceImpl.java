package com.param.global.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dao.IAssociatedCompanyMasterDao;
import com.param.global.dto.AssociatedCompanyMasterDto;

import in.param.exception.ApplicationException;

@Service
@SuppressWarnings("rawtypes")
public class AssociatedCompanyMasterServiceImpl implements IAssociatedCompanyMasterService, ICommonConstants{

	@Autowired
	private IAssociatedCompanyMasterDao iAssociatedCompanyMasterDao;
	
	@Override
	@Transactional
	public Response getAssociatedCompanyMaster(AssociatedCompanyMasterDto associatedCompanyMasterDto)
			throws ApplicationException {
		try {
				return iAssociatedCompanyMasterDao.getAssociatedCompanyMaster(associatedCompanyMasterDto);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, COMMON_ERROR_CODE, null, null, null);
	}

}
