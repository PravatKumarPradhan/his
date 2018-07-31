package com.param.global.org.dao;

import com.param.global.common.Response;
import com.param.global.org.dto.GeneralLedgerMasterDto;

import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface IGeneralLedgerMasterServiceDao {


	Response getGeneralLedgerMasterByName(GeneralLedgerMasterDto generalLedgerMasterDto) throws ApplicationException;

	Response saveGeneralLedgerMaster(GeneralLedgerMasterDto generalLedgerMasterDto) throws ApplicationException;

	Response getGeneralLedgerMasterById(int lederId, int orgId) throws ApplicationException;

	Response getGeneralLedgerMasterList(int orgId) throws ApplicationException;

	Response getLedgerByNameNotId(GeneralLedgerMasterDto generalLedgerMasterDto) throws ApplicationException;

	Response updateGeneralLedgerMaster(GeneralLedgerMasterDto generalLedgerMasterDto) throws ApplicationException;

	Response updateGeneralLedgerMasterStatus(GeneralLedgerMasterDto generalLedgerMasterDto) throws ApplicationException;

	Response getGeneralLedgerMasterActiveList(int orgId) throws ApplicationException;

}
