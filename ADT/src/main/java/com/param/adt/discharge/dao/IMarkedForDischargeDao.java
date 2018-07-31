package com.param.adt.discharge.dao;

import com.param.adt.common.Response;
import com.param.adt.discharge.dto.DischargeDto;
import com.param.adt.discharge.model.Discharge;
import com.param.global.dto.AdmissionDto;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IMarkedForDischargeDao extends IGenericDao<Discharge, Integer>{


	Response getDischagePatientListForNursing(DischargeDto dischargeDto)throws ApplicationException;

	Response getPatientServiceOrderList(AdmissionDto admissionDto) throws ApplicationException;

	Response updatePatientServiceOrder(AdmissionDto admissionDto)throws ApplicationException;

	Response getPatientReadyForBillingList(DischargeDto dischargeDto)throws ApplicationException;

	Response updatePatientReadyForBillingStatus(DischargeDto dischargeDto)throws ApplicationException;

	Response getPatientFinalDischargeList(DischargeDto dischargeDto)throws ApplicationException;

	Response vacateBed(DischargeDto dischargeDto)throws ApplicationException;

	Response saveHousekeepingLog(DischargeDto dischargeDto2)throws ApplicationException;

}
