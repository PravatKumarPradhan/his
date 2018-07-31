package com.param.opd.coversheet.service;

import javax.transaction.Transactional;

import in.param.exception.ApplicationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.global.common.Response;
import com.param.opd.coversheet.dao.IPatientSurgicalHistoryDao;
import com.param.opd.coversheet.dto.PatientSurgicalHistoryDto;
import com.param.opd.coversheet.model.PatientFamilyHistory;
import com.param.opd.coversheet.model.PatientSurgicalHistory;

@Service
public class PatientSurgicalHistoryServiceImpl implements IPatientSurgicalHistoryService{

	@Autowired
	private IPatientSurgicalHistoryDao iPatientSurgicalHistoryDao;
	
	@Override
	@Transactional
	public Response savePatientSurgicalHistory(PatientSurgicalHistoryDto patientSurgicalHistoryDto)throws ApplicationException {
		// TODO Auto-generated method stub
		return iPatientSurgicalHistoryDao.savePatientSurgicalHistory(patientSurgicalHistoryDto);
	}

	
	@Override
	@Transactional
	public Response getOldListOfPatientSurgicalHistory(
			PatientSurgicalHistoryDto patientSurgicalHistoryDto)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return iPatientSurgicalHistoryDao.getOldListOfPatientSurgicalHistory(patientSurgicalHistoryDto);
	}

	@Override
	@Transactional
	public Response getCurrentListOfPatientSurgicalHistory(
			PatientSurgicalHistoryDto patientSurgicalHistoryDto)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return iPatientSurgicalHistoryDao.getCurrentListOfPatientSurgicalHistory(patientSurgicalHistoryDto);
	}


	@Override
	@Transactional
	public Response updatePatientSurgicalHistory(
			PatientSurgicalHistoryDto patientSurgicalHistoryDto)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return iPatientSurgicalHistoryDao.updatePatientSurgicalHistory(patientSurgicalHistoryDto);
	}


	@Override
	@Transactional
	public Response updateStatusEnterInError(
			PatientSurgicalHistoryDto patientSurgicalHistoryDto)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return iPatientSurgicalHistoryDao.updateStatusEnterInError(patientSurgicalHistoryDto);
	}


	@Override
	@Transactional
	public Response updateSaveNurseReviewedFlag(
			PatientSurgicalHistory patientSurgicalHistory)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return iPatientSurgicalHistoryDao.updateSaveNurseReviewedFlag(patientSurgicalHistory);
	}

}
