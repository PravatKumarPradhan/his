package com.param.opd.coversheet.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.param.exception.ApplicationException;

import com.param.global.common.Response;
import com.param.opd.coversheet.dao.IPatientFamilyHistoryDao;
import com.param.opd.coversheet.dto.PatientFamilyHistoryDto;
import com.param.opd.coversheet.model.PatientFamilyHistory;

@Service
public class patientFamilyHistoryServiceImpl implements IpatientFamilyHistoryService {

	@Autowired
	private IPatientFamilyHistoryDao iPatientFamilyHistoryDao;
	
	@Override
	@Transactional
	public Response savePatientFamilyHistory(PatientFamilyHistoryDto patientFamilyHistoryDto)throws ApplicationException {
		// TODO Auto-generated method stub
		return iPatientFamilyHistoryDao.savePatientFamilyHistory(patientFamilyHistoryDto);
	}

	

	@Override
	@Transactional
	public Response getOldPatientFamilyHistory(
			PatientFamilyHistoryDto patientFamilyHistoryDto)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return iPatientFamilyHistoryDao.getOldPatientFamilyHistory(patientFamilyHistoryDto);
	}

	@Override
	@Transactional
	public Response getCurrentPatientFamilyHistory(
			PatientFamilyHistoryDto patientFamilyHistoryDto)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return iPatientFamilyHistoryDao.getCurrentPatientFamilyHistory(patientFamilyHistoryDto);
	}



	@Override
	@Transactional
	public Response updatePatientFamilyHistory(
			PatientFamilyHistoryDto patientFamilyHistoryDto)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return iPatientFamilyHistoryDao.updatePatientFamilyHistory(patientFamilyHistoryDto);
	}



	@Override
	@Transactional
	public Response updateStatusEnterInError(
			PatientFamilyHistoryDto patientFamilyHistoryDto)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return iPatientFamilyHistoryDao.updateStatusEnterInError(patientFamilyHistoryDto);
	}



	@Override
	@Transactional
	public Response updateSaveNurseReviewedFlag(
			PatientFamilyHistory patientFamilyHistory)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return iPatientFamilyHistoryDao.updateSaveNurseReviewedFlag(patientFamilyHistory);
	}

}
