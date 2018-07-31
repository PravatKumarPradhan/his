package com.param.lis.global.service;
/*package com.param.labinfosystem.global.service;

import in.param.exception.ApplicationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.param.labinfosystem.global.common.ICommonConstants;
import com.param.labinfosystem.global.common.IError;
import com.param.labinfosystem.global.common.Response;
import com.param.labinfosystem.global.dao.IDayMasterDao;
import com.param.labinfosystem.global.dao.IHourMasterDao;

@Service
@SuppressWarnings({"rawtypes","unchecked"})
public class DayMasterServiceImpl implements IDayMasterService,ICommonConstants, IError{

	@Autowired
	private IDayMasterDao iDayMasterDao;

	@Override
	@Transactional
	public Response getListDayMaster() throws ApplicationException {
		try
		{
			Response res =  iDayMasterDao.getListDayMaster();
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
*/