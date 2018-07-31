package com.param.global.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dao.IVisitTypeMasterDao;

import in.param.exception.ApplicationException;
@Service
@SuppressWarnings("rawtypes")
public class VisitTypeMasterServiceImpl implements IVisitTypeMasterService, ICommonConstants{
	@Autowired
	private IVisitTypeMasterDao iVisitTypeMasterDao;
	
	@Override
	@Transactional
	public Response getActiveVisitTypeList(Integer unitId, Integer orgId) throws ApplicationException {
		try {
			return iVisitTypeMasterDao.getActiveVisitTypeList(unitId, orgId);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
}
