package com.param.billing.global.service;

import javax.transaction.Transactional;

import com.param.billing.common.Response;
import com.param.billing.global.dto.BankMasterDto;

import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
@Transactional
public interface IBankMasterService {
	public Response getActiveBankMaster(BankMasterDto bankMasterDto) throws ApplicationException;

	public Response saveBankMaster(BankMasterDto bankMasterDto) throws ApplicationException;

	public Response getBankMasterById(int bankId, int orgId) throws ApplicationException;

	public Response getBankMasterList(BankMasterDto bankMasterDto) throws ApplicationException;

	public Response updateBankMaster(BankMasterDto bankMasterDto) throws ApplicationException;

	public Response updateBankMasterStatus(BankMasterDto bankMasterDto) throws ApplicationException;

	public Response getBankMasterCount(BankMasterDto bankMasterDto) throws ApplicationException;
}
