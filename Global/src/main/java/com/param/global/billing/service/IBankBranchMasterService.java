package com.param.global.billing.service;

import javax.transaction.Transactional;

import com.param.global.billing.dto.BankBranchMasterDto;
import com.param.global.common.Response;

import in.param.exception.ApplicationException;

@Transactional
@SuppressWarnings("rawtypes")
public interface IBankBranchMasterService {

	Response saveBankBranchMaster(BankBranchMasterDto bankMasterDto) throws ApplicationException;

	Response getBankBranchMasterById(int branchId, int orgId) throws ApplicationException;

	Response getBankBranchMasterList(BankBranchMasterDto bankMasterDto) throws ApplicationException;

	Response updateBankBranchMaster(BankBranchMasterDto bankMasterDto) throws ApplicationException;

	Response updateBankBranchMasterStatus(BankBranchMasterDto bankMasterDto) throws ApplicationException;

	Response getBankBranchMasterCount(BankBranchMasterDto bankMasterDto) throws ApplicationException;

}
