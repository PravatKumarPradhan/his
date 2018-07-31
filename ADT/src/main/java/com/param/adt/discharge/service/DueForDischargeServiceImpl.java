package com.param.adt.discharge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.adt.common.ICommonConstants;
import com.param.adt.common.Response;
import com.param.adt.discharge.dao.IDueForDischargeDao;
import com.param.adt.discharge.dto.DischargeDto;
import com.param.global.dto.AdmissionDto;

import in.param.exception.ApplicationException;

@Service
@SuppressWarnings({"rawtypes","unchecked"})
public class DueForDischargeServiceImpl implements IDueForDischargeService,ICommonConstants {

	@Autowired
	IDueForDischargeDao iDueForDischargeDao;
	
	@Override
	public Response saveDischargeRequest(DischargeDto dischargeDto) throws ApplicationException{
		try {
			dischargeDto.setDischargeTime(dischargeDto.getCreatedDate());
			return iDueForDischargeDao.saveDischargeRequest(dischargeDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getDischagePatientList(AdmissionDto admissionDto) throws ApplicationException 
	{
		try {
			return iDueForDischargeDao.getDischagePatientList(admissionDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response updatePDD(DischargeDto dischargeDto) throws ApplicationException {
		try {
			return iDueForDischargeDao.updatePDD(dischargeDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

}
