package com.param.global.service;

import javax.transaction.Transactional;

import com.param.global.common.Response;
import com.param.global.dto.OPConsultationConfigurationMasterDto;

import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IOPConsultationConfigurationMasterService {

	
	@Transactional
	Response OPConsultationConfiguration(OPConsultationConfigurationMasterDto opConsultationConfigurationMasterDto)throws ApplicationException;

	@Transactional
	Response getOPConsultationConfigurationList(
			OPConsultationConfigurationMasterDto opConsultationConfigurationMasterDto)throws ApplicationException;

	@Transactional
	Response getOPConsultationConfigurationListById(
			OPConsultationConfigurationMasterDto opConsultationConfigurationMasterDto) throws  ApplicationException;

	@Transactional
	Response updateOPConsultationConfigurationMaster(
			OPConsultationConfigurationMasterDto opConsultationConfigurationMasterDto)throws ApplicationException;

	@Transactional
	Response getOPConsultationConfigurationBySpecialityId(
			OPConsultationConfigurationMasterDto opConsultationConfigurationMasterDto);

}
