package com.param.adt.templet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.adt.templet.dao.IPatientTempletDao;
import com.param.adt.templet.dto.PatientTempletDto;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;

import in.param.exception.ApplicationException;

@Service
@SuppressWarnings({ "rawtypes", "unchecked" })
public class PatientTempletServiceImpl implements IPatientTempletService, ICommonConstants {

	@Autowired
	IPatientTempletDao iPatientTempletDao;

	@Override
	public Response savePatientTemplet(PatientTempletDto patientTempletDto) throws ApplicationException {
		try {
			return iPatientTempletDao.savePatientTemplet(patientTempletDto);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

}
