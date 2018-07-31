package com.param.opd.coversheet.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.global.common.Response;
import com.param.opd.coversheet.dao.IPatientTravelHistoryDetailsDao;
import com.param.opd.coversheet.dto.PatientTravelHistoryDetailsDto;
import com.param.opd.coversheet.model.PatientTravelHistoryDetails;

import in.param.exception.ApplicationException;


@Service
public class PatientTravelHistoryDetailsServiceImpl implements IPatientTravelHistoryDetailsService {

	@Autowired
	private IPatientTravelHistoryDetailsDao iPatientTravelHistoryDetailsDao;
	
	@Override
	@Transactional
	public Response getListOfPatientTravelHistoryDetails (
			PatientTravelHistoryDetailsDto patientTravelHistoryDetailsDto) throws ApplicationException {
			
				return iPatientTravelHistoryDetailsDao.getListOfPatientTravelHistoryDetails(patientTravelHistoryDetailsDto);
	}

	@Override
	@Transactional
	public Response savePatientTravelHistoryDetails(PatientTravelHistoryDetailsDto patientTravelHistoryDetailsDto) throws ApplicationException {
			
			return iPatientTravelHistoryDetailsDao.savePatientTravelHistoryDetails(patientTravelHistoryDetailsDto);
	}

	@Override
	@Transactional
	public Response getOldPatientTravelHistory(
			PatientTravelHistoryDetailsDto patientTravelHistoryDetailsDto)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return iPatientTravelHistoryDetailsDao.getOldPatientTravelHistory(patientTravelHistoryDetailsDto);
	}

	@Override
	@Transactional
	public Response getCurrentPatientTravelHistory(
			PatientTravelHistoryDetailsDto patientTravelHistoryDetailsDto)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return iPatientTravelHistoryDetailsDao.getCurrentPatientTravelHistory(patientTravelHistoryDetailsDto);
	}

	@Override
	@Transactional
	public Response updatePatientTravelHistory(
			PatientTravelHistoryDetailsDto patientTravelHistoryDetailsDto)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return iPatientTravelHistoryDetailsDao.updatePatientTravelHistory(patientTravelHistoryDetailsDto);
	}

	@Override
	@Transactional
	public Response updateSaveNurseReviewedFlag(
			PatientTravelHistoryDetails patientTravelHistoryDetails)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return iPatientTravelHistoryDetailsDao.updateSaveNurseReviewedFlag(patientTravelHistoryDetails);
	}

	@Override
	@Transactional
	public Response updateStatusEnterInError(
			PatientTravelHistoryDetailsDto patientTravelHistoryDetailsDto)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return iPatientTravelHistoryDetailsDao.updateStatusEnterInError(patientTravelHistoryDetailsDto);
	}

}
