package com.param.adt.templet.dao;

import com.param.adt.templet.dto.PatientTempletDto;
import com.param.global.common.Response;

import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface IPatientTempletDao {

	
	Response savePatientTemplet(PatientTempletDto patientTempletDto) throws ApplicationException;

}
