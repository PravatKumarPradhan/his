package com.param.billing.opd.service;

import com.param.billing.exception.BillingException;
import com.param.billing.global.dto.DepositCollectionReqDto;
import com.param.billing.global.dto.DepositSearchReqDto;
import com.param.billing.global.dto.VisitTypeEncounterReqDto;
import com.param.global.common.Response;

@SuppressWarnings("rawtypes")
public interface IDepositMasterService {
	public Response addDeposit(DepositCollectionReqDto reqDto)throws BillingException;
	public Response searchDeposit(DepositSearchReqDto dto)throws BillingException;
	public Response getEncountersForDeposit(VisitTypeEncounterReqDto reqDto)throws BillingException;
	public Response getAvailableDepositByPatient(int orgId, int unitId, int patientId)throws BillingException;
}
