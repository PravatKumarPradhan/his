package com.param.global.service;

import javax.transaction.Transactional;

import com.param.global.common.Response;
import com.param.global.dto.ModalityMasterDto;
import in.param.exception.ApplicationException;


@SuppressWarnings("rawtypes")
public interface IModalityMasterService {

	@Transactional
	Response saveModalityMaster(ModalityMasterDto modalityMasterDto)throws ApplicationException;

	@Transactional
	Response getModalityMasterList(ModalityMasterDto modalityMasterDto)throws ApplicationException;

	@Transactional
	Response updateModalityMaster(ModalityMasterDto modalityMasterDto)throws ApplicationException;

	@Transactional
	Response updateStatusForModality(ModalityMasterDto modalityMasterDto)throws ApplicationException;

	@Transactional
	Response getActiveModalityList(ModalityMasterDto modalityMasterDto)throws ApplicationException;

	@Transactional
	Response getModalityCount(ModalityMasterDto modalityMasterDto)throws ApplicationException;

	@Transactional
	Response getModalityById(ModalityMasterDto modalityMasterDto)throws ApplicationException;

	@Transactional
	Response getModalityBySubSpecialityArray(ModalityMasterDto modalityMasterDto)throws ApplicationException;

	@Transactional
	Response getModalityBySubSpecialityId(ModalityMasterDto modalityMasterDto);
	
	

}
