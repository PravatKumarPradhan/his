package com.param.adt.master.global.service;

import javax.transaction.Transactional;

import com.param.adt.common.Response;
import com.param.adt.master.global.dto.AdmissionTypeMasterDto;

import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface IAdmissionTypeMasterService {

	@Transactional
	Response addAdmissionTypeMaster(AdmissionTypeMasterDto admissionTypeMasterDto) throws ApplicationException;

	@Transactional
	Response getAdmissionTypeMasterList(AdmissionTypeMasterDto admissionTypeMasterDto) throws ApplicationException;

	@Transactional
	Response updateAdmissionTypeMaster(AdmissionTypeMasterDto admissionTypeMasterDto) throws ApplicationException;

	@Transactional
	Response getAdmissionTypeById(AdmissionTypeMasterDto admissionTypeMasterDto) throws ApplicationException;

	@Transactional
	Response updateStatusForAdmissionType(AdmissionTypeMasterDto admissionTypeMasterDto) throws ApplicationException;

	@Transactional
	Response getActiveAdmissionTypeList() throws ApplicationException;

	@Transactional
	Response getAdmissionTypeCount(AdmissionTypeMasterDto admissionTypeMasterDto) throws ApplicationException;

}
