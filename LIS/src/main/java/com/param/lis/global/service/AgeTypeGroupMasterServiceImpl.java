package com.param.lis.global.service;

import in.param.exception.ApplicationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.Response;
import com.param.lis.global.dao.IAgeTypeGroupMasterDao;

@Service
@SuppressWarnings({"rawtypes","unchecked"})
public class AgeTypeGroupMasterServiceImpl implements IAgeTypeGroupMasterService,ICommonConstants, IError{

	@Autowired
	private IAgeTypeGroupMasterDao iAgeTypeGroupMasterDao;

	@Override
	@Transactional
	public Response getListAgeTypeGroupMaster() throws ApplicationException {
		try
		{
			Response res =  iAgeTypeGroupMasterDao.getListAgeTypeGroupMaster();
			if(res.getListObject() != null && res.getListObject().size() > 0){
				return new Response(SUCCESS, SUCCESS_200, null, res.getListObject(), null);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	}

	


}
