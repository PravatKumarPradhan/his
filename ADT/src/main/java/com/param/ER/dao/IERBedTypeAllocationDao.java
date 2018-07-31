package com.param.ER.dao;

import com.param.ER.dto.ERBedTypeAllocationDto;
import com.param.ER.model.ERBedTypeAllocation;
import com.param.adt.common.Response;
import com.param.adt.transfer.dto.TransferDto;
import com.param.global.dto.AdmissionDto;
import com.param.global.dto.DoctorMasterDto;
import com.param.global.dto.UnknownPatientRegistrationDto;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IERBedTypeAllocationDao extends IGenericDao<ERBedTypeAllocation, Integer>{

	
	Response getUnknownPatientsList(UnknownPatientRegistrationDto unknownPatientRegistrationDto) throws ApplicationException;

	Response getERBedTypeAllocationList(ERBedTypeAllocationDto erBedTypeAllocationDto)throws ApplicationException;

	Response getCount(ERBedTypeAllocationDto erBedTypeAllocationDto)throws ApplicationException;

	Response getERBedList(ERBedTypeAllocationDto erBedTypeAllocationDto)throws ApplicationException;

	Response updateERPatientDetails(AdmissionDto admissionDto)throws ApplicationException;

	Response getActiveDoctorList(DoctorMasterDto doctorMasterDto)throws ApplicationException;

	Response updateERPatientAdmission(AdmissionDto admissionDto)throws ApplicationException;

	Response saveTransfer(TransferDto transferDto)throws ApplicationException;

}
