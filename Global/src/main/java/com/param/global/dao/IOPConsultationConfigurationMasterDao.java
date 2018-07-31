package com.param.global.dao;

import com.param.global.common.Response;
import com.param.global.dto.OPConsultationConfigurationMasterDto;
import com.param.global.model.OPConsultationConfigurationMaster;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IOPConsultationConfigurationMasterDao extends IGenericDao<OPConsultationConfigurationMaster, Integer>{


	Response checkOPConsultationExistOrNot(OPConsultationConfigurationMasterDto opConsultationConfigurationMasterDto)throws ApplicationException;

	Response saveOPConsultationConfiguration(OPConsultationConfigurationMasterDto opConsultationConfigurationMasterDto);

	Response getOPConsultationConfigurationList(
			OPConsultationConfigurationMasterDto opConsultationConfigurationMasterDto)throws ApplicationException;

	Response getOPConsultationConfigurationListById(
			OPConsultationConfigurationMasterDto opConsultationConfigurationMasterDto) throws ApplicationException;

	Response updateOPConsultationConfigurationMaster(
			OPConsultationConfigurationMasterDto opConsultationConfigurationMasterDto)throws ApplicationException;

	Response getOPConsultationConfigurationBySpecialityId(
			OPConsultationConfigurationMasterDto opConsultationConfigurationMasterDto)throws ApplicationException;

}
