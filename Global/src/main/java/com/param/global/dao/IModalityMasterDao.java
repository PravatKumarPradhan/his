package com.param.global.dao;

import com.param.global.common.Response;
import com.param.global.dto.ModalityMasterDto;
import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface IModalityMasterDao {

	Response getModalityByName(ModalityMasterDto modalityMasterDto) throws ApplicationException;

	Response saveModalityMaster(ModalityMasterDto modalityMasterDto)throws ApplicationException;

	Response getModalityMasterList(ModalityMasterDto modalityMasterDto)throws ApplicationException;

	Response getModalityByNameNotId(ModalityMasterDto modalityMasterDto)throws ApplicationException;

	Response updateModalityMaster(ModalityMasterDto modalityMasterDto)throws ApplicationException;

	Response getModalityById(ModalityMasterDto modalityMasterDto)throws ApplicationException;

	Response updateModalityStatus(ModalityMasterDto modalityMasterDto)throws ApplicationException;

	Response getActiveModalityList(ModalityMasterDto modalityMasterDto)throws ApplicationException;

	Response getModalityCount(ModalityMasterDto modalityMasterDto)throws ApplicationException;

	Response getModalityBySubSpecialityArray(ModalityMasterDto modalityMasterDto)throws ApplicationException;

	Response getModalityBySubSpecialityId(ModalityMasterDto modalityMasterDto);

	Response getModalityByIdForSync(Integer recordId) throws ApplicationException;

}
