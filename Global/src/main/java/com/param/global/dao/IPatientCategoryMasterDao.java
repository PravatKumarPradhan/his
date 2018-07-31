package com.param.global.dao;

import com.param.global.common.Response;
import com.param.global.dto.PatientCategoryMasterDto;
import com.param.global.model.PatientCategoryMaster;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface IPatientCategoryMasterDao extends IGenericDao<PatientCategoryMaster, Integer>{


	public Response getPatientCategoryMaster(PatientCategoryMasterDto patientCategoryMasterDto) throws ApplicationException;

	public Response getPatientCategoryMasterByName(PatientCategoryMasterDto patientCategoryMasterDto) throws ApplicationException;

	public Response savePatientCategoryMaster(PatientCategoryMasterDto patientCategoryMasterDto) throws ApplicationException;

	public Response getPatientCategoryMasterById(int patientCategoryId, int orgId) throws ApplicationException;

	public Response getPatientCategoryMasterByNameNotId(PatientCategoryMasterDto patientCategoryMasterDto) throws ApplicationException;

	public Response updatePatientCategoryMaster(PatientCategoryMasterDto patientCategoryMasterDto) throws ApplicationException;

	public Response updatePatientCategoryMasterStatus(PatientCategoryMasterDto patientCategoryMasterDto) throws ApplicationException;

	public Response getPatientCategoryMasterList(PatientCategoryMasterDto patientCategoryMasterDto) throws ApplicationException;

	public Response getPatientCategoryMasterCount(PatientCategoryMasterDto patientCategoryMasterDto) throws ApplicationException;
	public Response getPatientCategoryByOrg(int orgId)throws ApplicationException;
	
}
