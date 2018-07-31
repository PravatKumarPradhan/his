package com.param.adt.admission.dao;

import com.param.adt.admission.model.Admission;
import com.param.adt.common.Response;
import com.param.adt.master.unit.dto.WardMasterDto;
import com.param.global.dto.AdmissionDto;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IAdmittedPatientListDao extends IGenericDao<Admission, Integer>
{
	Response getAdmittedPatientList(AdmissionDto admissionDto) throws ApplicationException;

	Response getActiveWardListByFloorId(WardMasterDto wardMasterDto) throws ApplicationException;

	Response getKinDetailsByAdmissionId(AdmissionDto admissionDto)throws ApplicationException;

	Response getAdmittedPatientByFloorWard(AdmissionDto admissionDto) throws ApplicationException;

	Response serachPatientForMapOfBed(AdmissionDto admissionDto)throws ApplicationException;

}
