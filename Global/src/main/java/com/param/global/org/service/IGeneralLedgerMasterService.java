package com.param.global.org.service;

import javax.transaction.Transactional;

import com.param.global.common.Response;
import com.param.global.org.dto.GeneralLedgerMasterDto;

import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
@Transactional
public interface IGeneralLedgerMasterService {


	Response saveGeneralLedgerMaster(GeneralLedgerMasterDto generalLedgerMasterDto) throws ApplicationException;

	Response getGeneralLedgerMasterById(int lederId, int orgId)throws ApplicationException;

	Response getGeneralLedgerMasterList(int orgId)throws ApplicationException;

	Response updateGeneralLedgerMaster(GeneralLedgerMasterDto generalLedgerMasterDto)throws ApplicationException;

	Response updateGeneralLedgerMasterStatus(GeneralLedgerMasterDto generalLedgerMasterDto)throws ApplicationException;

	Response getGeneralLedgerMasterActiveList(int orgId) throws ApplicationException;


}
