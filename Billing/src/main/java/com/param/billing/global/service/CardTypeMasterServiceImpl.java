package com.param.billing.global.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.param.billing.common.ICommonConstants;
import com.param.billing.common.Response;
import com.param.billing.global.dao.ICardTypeMasterDao;
import com.param.billing.global.dto.CardTypeMasterDto;

import in.param.exception.ApplicationException;
@Service
@SuppressWarnings("rawtypes")
public class CardTypeMasterServiceImpl implements ICardTypeMasterService, ICommonConstants{

	@Autowired
	private ICardTypeMasterDao iCardTypeMasterDao;

	@Override
	@Transactional
	public Response gerActiveCardTypeMaster(CardTypeMasterDto cardTypeMasterDto) throws ApplicationException {
		try {
			return iCardTypeMasterDao.gerActiveCardTypeMaster(cardTypeMasterDto);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	
}
