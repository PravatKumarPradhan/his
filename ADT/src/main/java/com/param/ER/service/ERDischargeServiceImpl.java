package com.param.ER.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.ER.dao.IERDischargeDao;
import com.param.adt.common.ICommonConstants;
import com.param.adt.common.Response;
import com.param.adt.discharge.dto.DischargeDto;

import in.param.exception.ApplicationException;
@Service
@SuppressWarnings({"rawtypes","unchecked"})
public class ERDischargeServiceImpl implements IERDischargeService,ICommonConstants{

	@Autowired
	IERDischargeDao ierDischargeDao;
	
	
	@Override
	public Response getERDischargePatientList(DischargeDto dischargeDto) throws ApplicationException {
		try {
			return ierDischargeDao.getERDischargePatientList(dischargeDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

}
