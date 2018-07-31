package com.param.billing.global.dao;

import com.param.billing.common.Response;
import com.param.billing.global.dto.BankMasterDto;
import com.param.billing.global.model.BankMaster;
import com.param.global.dto.PatientCategoryMasterDto;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IBankMasterDao extends IGenericDao<BankMaster, Integer>{
	public Response getActiveBankMaster(BankMasterDto bankMasterDto)throws ApplicationException;

	public Response getBankMasterCount(BankMasterDto bankMasterDto) throws ApplicationException;

	public Response updateBankMasterStatus(BankMasterDto bankMasterDto)throws ApplicationException;

	public Response updateBankMaster(BankMasterDto bankMasterDto)throws ApplicationException;

	public Response getBankMasterList(BankMasterDto bankMasterDto)throws ApplicationException;

	public Response getBankMasterById(int bankId, int orgId)throws ApplicationException;
	public Response saveBankMaster(BankMasterDto bankMasterDto)throws ApplicationException;
	public Response getBankMasterByNameNotId(BankMasterDto bankMasterDto) throws ApplicationException;
	public Response getBankMasterByName(BankMasterDto bankMasterDto) throws ApplicationException;
}