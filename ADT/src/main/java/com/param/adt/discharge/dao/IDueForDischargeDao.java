package com.param.adt.discharge.dao;

import com.param.adt.common.Response;
import com.param.adt.discharge.dto.DischargeDto;
import com.param.adt.discharge.model.Discharge;
import com.param.global.dto.AdmissionDto;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IDueForDischargeDao extends IGenericDao<Discharge, Integer>{

	Response saveDischargeRequest(DischargeDto dischargeDto) throws ApplicationException;

	Response getDischagePatientList(AdmissionDto admissionDto)throws ApplicationException;

	Response updatePDD(DischargeDto dischargeDto);

}
