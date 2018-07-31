package com.param.global.dao;


import com.param.global.common.Response;
import com.param.global.dto.SurgeryGradeMasterDto;
import com.param.global.model.SurgeryGradeMaster;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface ISurgeryGradeMasterDao extends IGenericDao<SurgeryGradeMaster, Integer>{

	Response getSurgeryGradeByName(SurgeryGradeMasterDto surgeryGradeMasterDto)throws ApplicationException;

	Response addSurgeryGradeMaster(SurgeryGradeMasterDto surgeryGradeMasterDto)throws ApplicationException;

	Response getSurgeryGradeMasterList(SurgeryGradeMasterDto surgeryGradeMasterDto)throws ApplicationException;

	Response getSurgeryGradeByNameNotId(SurgeryGradeMasterDto surgeryGradeMasterDto)throws ApplicationException;

	Response updateSurgeryGradeMaster(SurgeryGradeMasterDto surgeryGradeMasterDto)throws ApplicationException;

	Response getSurgeryGradeByID(SurgeryGradeMasterDto surgeryGradeMasterDto)throws ApplicationException;

	Response updateSurgeryGradeStatus(SurgeryGradeMasterDto surgeryGradeMasterDto)throws ApplicationException;

	Response getActiveSurgeryGradeMasterList()throws ApplicationException;

}
