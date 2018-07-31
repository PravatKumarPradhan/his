package com.param.global.service;

import com.param.global.common.Response;
import com.param.global.dto.SurgeryGradeMasterDto;

import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface ISurgeryGradeMasterService {

	Response addSurgeryGradeMaster(SurgeryGradeMasterDto surgeryGradeMasterDto) throws ApplicationException;

	Response getSurgeryGradeMasterList(SurgeryGradeMasterDto surgeryGradeMasterDto) throws ApplicationException;

	Response updateSurgeryGradeMaster(SurgeryGradeMasterDto surgeryGradeMasterDto) throws ApplicationException;

	Response getSurgeryGradeById(SurgeryGradeMasterDto surgeryGradeMasterDto) throws ApplicationException;

	Response updateStatusForSurgeryGrade(SurgeryGradeMasterDto surgeryGradeMasterDto) throws ApplicationException;

	Response getActiveSurgeryGradeList() throws ApplicationException;

}
