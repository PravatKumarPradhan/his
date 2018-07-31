package com.param.billing.opd.dao;

import com.param.billing.exception.BillingException;
import com.param.billing.global.dto.DepositSearchReqDto;
import com.param.billing.global.dto.VisitTypeEncounterReqDto;
import com.param.billing.global.model.DepositMaster;
import com.param.billing.master.dto.DepositMasterDto;
import com.param.global.common.Response;

import in.param.common.dao.IGenericDao;

@SuppressWarnings("rawtypes")
public interface IDepositMasterDao extends IGenericDao<DepositMaster, Integer>{
	public Response saveDeposit(DepositMasterDto dto)throws BillingException;
	public Long getCurrentId()throws BillingException;
	public Response searchDeposit(DepositSearchReqDto dto)throws BillingException;
	public Response getEncountersByPatIdForDeposit(VisitTypeEncounterReqDto reqDto)throws BillingException;
	public Response getAdmissionByPatVisitForDeposit(VisitTypeEncounterReqDto reqDto)throws BillingException;
	public Response getAvailableDepositByPatient(int orgId, int unitId, int patientId)throws BillingException;
}
