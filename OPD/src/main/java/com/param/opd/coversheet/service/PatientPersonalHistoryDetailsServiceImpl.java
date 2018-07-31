package com.param.opd.coversheet.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.param.exception.ApplicationException;

import com.param.global.common.Response;
import com.param.opd.coversheet.dao.IPatientPersonalHistoryDetailsDao;
import com.param.opd.coversheet.dto.PatientPersonalHistoryDetailsDto;
import com.param.opd.coversheet.model.PatientPersonalHistoryDetails;

@Service
public class PatientPersonalHistoryDetailsServiceImpl implements IPatientPersonalHistoryDetailsService{

	@Autowired
	private IPatientPersonalHistoryDetailsDao iPatientPersonalHistoryDetailsDao;
	
	@Override
	@Transactional
	public Response savePatientPersonalHistoryDetails(PatientPersonalHistoryDetailsDto patientPersonalHistoryDetailsDto)throws ApplicationException 
	{
		// TODO Auto-generated method stub
		return iPatientPersonalHistoryDetailsDao.savePatientPersonalHistoryDetails(patientPersonalHistoryDetailsDto);
	}

	

	@Override
	public Response editPatientPersonalHistoryDetails(
			PatientPersonalHistoryDetailsDto patientPersonalHistoryDetailsDto)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public Response getListOfOldPatientPersonalHistoryDetails(
			PatientPersonalHistoryDetailsDto patientPersonalHistoryDetailsDto)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return iPatientPersonalHistoryDetailsDao.getListOfOldPatientPersonalHistoryDetails(patientPersonalHistoryDetailsDto);
	}

	@Override
	@Transactional
	public Response getListOfCurrentPatientPersonalHistoryDetails(
			PatientPersonalHistoryDetailsDto patientPersonalHistoryDetailsDto)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return iPatientPersonalHistoryDetailsDao.getListOfCurrentPatientPersonalHistoryDetails(patientPersonalHistoryDetailsDto);
	}



	@Override
	@Transactional
	public Response updatePatientPersonalHistoryDetails(
			PatientPersonalHistoryDetailsDto patientPersonalHistoryDetailsDto)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return iPatientPersonalHistoryDetailsDao.updatePatientPersonalHistoryDetails(patientPersonalHistoryDetailsDto);
	}



	@Override
	@Transactional
	public Response updateStatusEnterInError(
			PatientPersonalHistoryDetailsDto patientPersonalHistoryDetailsDto)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return iPatientPersonalHistoryDetailsDao.updateStatusEnterInError(patientPersonalHistoryDetailsDto);
	}



	@Override
	@Transactional
	public Response updateSaveNurseReviewedFlag(
			PatientPersonalHistoryDetails patientPersonalHistoryDetails)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return iPatientPersonalHistoryDetailsDao.updateSaveNurseReviewedFlag(patientPersonalHistoryDetails);
	}

}
