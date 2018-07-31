package com.param.adt.master.global.dao;

import com.param.adt.common.Response;
import com.param.adt.master.global.dto.AdmissionTypeMasterDto;

import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface IAdmissionTypeMasterDao {

	Response getAdmissionTypeByName(AdmissionTypeMasterDto admissionTypeMasterDto) throws ApplicationException;

	Response addAdmissionTypeMaster(AdmissionTypeMasterDto admissionTypeMasterDto) throws ApplicationException;

	Response getAdmissionTypeMasterList(AdmissionTypeMasterDto admissionTypeMasterDto) throws ApplicationException;

	Response getAdmissionTypeByNameNotId(AdmissionTypeMasterDto admissionTypeMasterDto) throws ApplicationException;

	Response updateAdmissionTypeMaster(AdmissionTypeMasterDto admissionTypeMasterDto) throws ApplicationException;

	Response getAdmissionTypeById(AdmissionTypeMasterDto admissionTypeMasterDto) throws ApplicationException;

	Response updateAdmissionTypeStatus(AdmissionTypeMasterDto admissionTypeMasterDto) throws ApplicationException;

	Response getActiveAdmissionTypeMasterList() throws ApplicationException;

	Response getCount(AdmissionTypeMasterDto admissionTypeMasterDto) throws ApplicationException;

}
