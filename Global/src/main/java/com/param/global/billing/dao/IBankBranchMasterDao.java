package com.param.global.billing.dao;

import com.param.global.billing.dto.BankBranchMasterDto;
import com.param.global.common.Response;
import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface IBankBranchMasterDao {


	Response saveBankBranchMaster(BankBranchMasterDto bankMasterDto) throws ApplicationException;

	Response getBankBranchMasterById(int branchId, int orgId) throws ApplicationException;

	Response getBankBranchMasterList(BankBranchMasterDto bankMasterDto) throws ApplicationException;

	Response updateBankBranchMaster(BankBranchMasterDto bankMasterDto) throws ApplicationException;

	Response updateBankBranchMasterStatus(BankBranchMasterDto bankMasterDto) throws ApplicationException;

	Response getBankBranchMasterCount(BankBranchMasterDto bankMasterDto) throws ApplicationException;
	
	Response getBankBranchMasterByNameNotId(BankBranchMasterDto bankMasterDto) throws ApplicationException;
	
	Response getBankBranchMasterByName(BankBranchMasterDto bankMasterDto) throws ApplicationException;
}
