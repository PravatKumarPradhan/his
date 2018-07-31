package com.param.global.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dao.IReferalDetailsDao;
import com.param.global.dto.ReferalDetailsDto;

import in.param.exception.ApplicationException;

@Service
@SuppressWarnings("rawtypes")
public class ReferalDetailsServiceImpl implements IReferalDetailsService,ICommonConstants{

	@Autowired
	IReferalDetailsDao iReferalDetailsDao;
	
	@Override
	public Response saveReferalDetails(ReferalDetailsDto referalDetailsDto) throws ApplicationException {
		try {
			return iReferalDetailsDao.saveReferalDetails(referalDetailsDto);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

}
