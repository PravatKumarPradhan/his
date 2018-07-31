package com.param.global.org.common.service;

import javax.transaction.Transactional;

import com.param.global.common.Response;
import com.param.global.org.common.dto.QualificationMasterDto;

import in.param.exception.ApplicationException;

@Transactional
@SuppressWarnings("rawtypes")
public interface IQualificationMasterService {

	
	Response saveQualificationMaster(QualificationMasterDto qualificationMasterDto) throws ApplicationException; 

	Response getQualificationMasterById(int qualificationId, int orgId) throws ApplicationException;

	Response getQualificationMasterList(QualificationMasterDto qualificationMasterDto) throws ApplicationException;

	Response updateQualificationMaster(QualificationMasterDto qualificationMasterDto)throws ApplicationException;

	Response updateQualificationMasterStatus(QualificationMasterDto qualificationMasterDto)throws ApplicationException;

	Response getQualificationMasterCount(QualificationMasterDto qualificationMasterDto) throws ApplicationException;

}
