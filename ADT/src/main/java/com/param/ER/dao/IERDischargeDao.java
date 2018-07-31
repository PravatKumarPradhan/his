package com.param.ER.dao;

import javax.transaction.Transactional;

import com.param.adt.common.Response;
import com.param.adt.discharge.dto.DischargeDto;
import com.param.adt.discharge.model.Discharge;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IERDischargeDao extends IGenericDao<Discharge, Integer>{

	@Transactional
	Response getERDischargePatientList(DischargeDto dischargeDto)throws ApplicationException;

}
