package com.param.adt.templet.service;

import javax.transaction.Transactional;

import com.param.adt.templet.dto.PatientTempletDto;
import com.param.global.common.Response;

import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface IPatientTempletService {

	@Transactional
	Response savePatientTemplet(PatientTempletDto patientTempletDto) throws ApplicationException;

}
