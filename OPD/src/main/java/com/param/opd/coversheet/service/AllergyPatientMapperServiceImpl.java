package com.param.opd.coversheet.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.param.exception.ApplicationException;

import com.param.global.common.Response;
import com.param.opd.coversheet.dao.IAllergyPatientMapperDao;
import com.param.opd.coversheet.dto.AllergyPatientMapperDto;
import com.param.opd.coversheet.model.AllergyPatientMapper;

@Service
public class AllergyPatientMapperServiceImpl implements
		IAllergyPatientMapperService {

	@Autowired
	private IAllergyPatientMapperDao iAllergyPatientMapperDao;
	
	@Override
	@Transactional
	public Response saveAllergyPatientMapper(
			AllergyPatientMapperDto allergyPatientMapperDto)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return iAllergyPatientMapperDao.saveAllergyPatientMapper(allergyPatientMapperDto);
	}

	@Override
	@Transactional
	public Response getListOfAllergyPatientMapper(
			AllergyPatientMapperDto allergyPatientMapperDto)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return iAllergyPatientMapperDao.getListOfAllergyPatientMapper(allergyPatientMapperDto);
	}

	@Override
	@Transactional
	public Response cancelAllergyPatientMapper(
			AllergyPatientMapperDto allergyPatientMapperDto)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return iAllergyPatientMapperDao.cancelAllergyPatientMapper(allergyPatientMapperDto);
	}

	@Override
	@Transactional
	public Response getOldAllergyPatientMapper(
			AllergyPatientMapperDto allergyPatientMapperDto)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return iAllergyPatientMapperDao.getOldAllergyPatientMapper(allergyPatientMapperDto);
	}

	@Override
	@Transactional
	public Response getCurrentAllergyPatientMapper(
			AllergyPatientMapperDto allergyPatientMapperDto)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return iAllergyPatientMapperDao.getCurrentAllergyPatientMapper(allergyPatientMapperDto);
	}

	@Override
	@Transactional
	public Response updateAllergyPatientMapper(
			AllergyPatientMapperDto allergyPatientMapperDto)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return iAllergyPatientMapperDao.updateAllergyPatientMapper(allergyPatientMapperDto);
	}

	@Override
	@Transactional
	public Response updateSaveNurseReviewedFlag(
			AllergyPatientMapper allergyPatientMapper)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return iAllergyPatientMapperDao.updateSaveNurseReviewedFlag(allergyPatientMapper);
	}

	@Override
	@Transactional
	public Response updateStatusEnterInError(
			AllergyPatientMapperDto allergyPatientMapperDto)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return iAllergyPatientMapperDao.updateStatusEnterInError(allergyPatientMapperDto);
	}

}
