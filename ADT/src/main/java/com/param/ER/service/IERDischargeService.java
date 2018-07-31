package com.param.ER.service;

import com.param.adt.common.Response;
import com.param.adt.discharge.dto.DischargeDto;

import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IERDischargeService {

	Response getERDischargePatientList(DischargeDto dischargeDto) throws ApplicationException;

}
