package com.param.global.billing.service;

import javax.transaction.Transactional;

import com.param.global.billing.dto.DoctorCategoryMasterDto;
import com.param.global.common.Response;

import in.param.exception.ApplicationException;

@Transactional
@SuppressWarnings("rawtypes")
public interface IDoctorCategoryMasterService {

	
	Response saveDoctorCategoryMaster(DoctorCategoryMasterDto doctorCategoryMasterDto) throws ApplicationException;

	Response getDoctorCategoryMasterById(int doctorId, int orgId) throws ApplicationException;

	Response getDoctorCategoryMasterList(DoctorCategoryMasterDto doctorCategoryMasterDto) throws ApplicationException;

	Response updateDoctorCategoryMaster(DoctorCategoryMasterDto doctorCategoryMasterDto) throws ApplicationException;

	Response updateDoctorCategoryMasterStatus(DoctorCategoryMasterDto doctorCategoryMasterDto)
			throws ApplicationException;

	Response getDoctorCategoryCount(DoctorCategoryMasterDto doctorCategoryMasterDto) throws ApplicationException;

}
