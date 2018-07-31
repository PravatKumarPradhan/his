package com.param.global.unit.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.param.exception.ApplicationException;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.unit.dao.IWingMasterDao;
import com.param.global.unit.dto.WingMasterDto;

@SuppressWarnings("rawtypes")
@Service
public class WingMasterServiceImpl implements IWingMasterService, ICommonConstants{

	@Autowired
	IWingMasterDao iWingMasterDao;
	
	
	@Override
	@Transactional
	public Response getWingMasterListByOrgAndUnit(WingMasterDto wingMasterDto)throws ApplicationException {
		try{
			return iWingMasterDao.getWingMasterListByOrgAndUnit(wingMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, null, null, null, null);
	}

}
