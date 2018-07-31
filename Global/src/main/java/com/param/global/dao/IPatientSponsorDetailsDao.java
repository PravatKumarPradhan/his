package com.param.global.dao;

import com.param.global.common.Response;
import com.param.global.dto.PatientSponsorDetailsDto;
import com.param.global.model.PatientSponsorDetails;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;

public interface IPatientSponsorDetailsDao extends IGenericDao<PatientSponsorDetails, Integer>{

	Response savePatientSponsorDetails(PatientSponsorDetailsDto patientSponsorDetailsDto)throws ApplicationException;

}
