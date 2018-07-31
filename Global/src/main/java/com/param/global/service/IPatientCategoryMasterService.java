package com.param.global.service;
import in.param.exception.ApplicationException;

import javax.transaction.Transactional;

import com.param.global.common.Response;
import com.param.global.dto.PatientCategoryMasterDto;

@Transactional
@SuppressWarnings("rawtypes")
public interface IPatientCategoryMasterService {


	public Response getPatientCategoryMaster(PatientCategoryMasterDto patientCategoryMasterDto) throws ApplicationException;

	public Response savePatientCategoryMaster(PatientCategoryMasterDto patientCategoryMasterDto) throws ApplicationException;

	public Response getPatientCategoryMasterById(int patientCategoryId, int orgId) throws ApplicationException;

	public Response updatePatientCategoryMaster(PatientCategoryMasterDto patientCategoryMasterDto) throws ApplicationException;

	public Response updatePatientCategoryMasterStatus(PatientCategoryMasterDto patientCategoryMasterDto) throws ApplicationException;

	public Response getPatientCategoryMasterList(PatientCategoryMasterDto patientCategoryMasterDto) throws ApplicationException;

	public Response getPatientCategoryMasterCount(PatientCategoryMasterDto patientCategoryMasterDto) throws ApplicationException;
	
	public Response getPatientCategoryByOrg(int orgId)throws ApplicationException;
}
