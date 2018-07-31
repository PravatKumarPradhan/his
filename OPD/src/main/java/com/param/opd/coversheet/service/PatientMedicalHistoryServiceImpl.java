package com.param.opd.coversheet.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.param.exception.ApplicationException;

import com.param.global.common.Response;
import com.param.opd.coversheet.dao.IPatientMedicalHistoryDao;
import com.param.opd.coversheet.dto.PatientMedicalHistoryDto;
import com.param.opd.coversheet.model.PatientMedicalHistory;

@Service
@SuppressWarnings("rawtypes")
public class PatientMedicalHistoryServiceImpl implements IPatientMedicalHistoryService {

	@Autowired
	private IPatientMedicalHistoryDao iPatientMedicalHistoryDao;
	
	
	@Override
	@Transactional
	public Response savePatientMedicalHistory(PatientMedicalHistoryDto patientMedicalHistoryDto)throws ApplicationException {
		// TODO Auto-generated method stub
		return iPatientMedicalHistoryDao.savePatientMedicalHistory(patientMedicalHistoryDto);
	}

	
	@Override
	@Transactional
	public Response getOldPatientMedicalHistory(
			PatientMedicalHistoryDto patientMedicalHistoryDto)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return iPatientMedicalHistoryDao.getOldPatientMedicalHistory(patientMedicalHistoryDto);
	}

	@Override
	@Transactional
	public Response getCurrentPatientMedicalHistory(
			PatientMedicalHistoryDto patientMedicalHistoryDto)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return iPatientMedicalHistoryDao.getCurrentPatientMedicalHistory(patientMedicalHistoryDto);
	}

	@Override
	@Transactional
	public Response updatePatientMedicalHistory(
			PatientMedicalHistoryDto patientMedicalHistoryDto)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return iPatientMedicalHistoryDao.updatePatientMedicalHistory(patientMedicalHistoryDto);
	}

	@Override
	@Transactional
	public Response updateSaveNurseReviewedFlag(
			PatientMedicalHistory patientMedicalHistory)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return iPatientMedicalHistoryDao.updateSaveNurseReviewedFlag(patientMedicalHistory);
	}

	@Override
	@Transactional
	public Response updateStatusEnterInError(
			PatientMedicalHistoryDto patientMedicalHistoryDto)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return iPatientMedicalHistoryDao.updateStatusEnterInError(patientMedicalHistoryDto);
	}

}
