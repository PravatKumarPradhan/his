package com.param.global.billing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.global.billing.dao.IBankBranchMasterDao;
import com.param.global.billing.dto.BankBranchMasterDto;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;

import in.param.exception.ApplicationException;

@Service
@SuppressWarnings("rawtypes")
public class BankBranchMasterServiceImpl implements IBankBranchMasterService, ICommonConstants {

	@Autowired
	IBankBranchMasterDao iBankBranchMasterDao;

	@Override
	public Response saveBankBranchMaster(BankBranchMasterDto bankMasterDto) throws ApplicationException {
		Response response = iBankBranchMasterDao.getBankBranchMasterByName(bankMasterDto);
		try {
			if (response.getListObject().size() > 0 && response.getListObject() != null) {
				return new Response<>(ERROR, ALREADY_EXIST_CODE, ALREADY_EXIST, null, null);
			} else {
				return iBankBranchMasterDao.saveBankBranchMaster(bankMasterDto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getBankBranchMasterById(int branchId, int orgId) throws ApplicationException {
		try {
			return iBankBranchMasterDao.getBankBranchMasterById(branchId, orgId);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getBankBranchMasterList(BankBranchMasterDto bankMasterDto) throws ApplicationException {
		try {
			return iBankBranchMasterDao.getBankBranchMasterList(bankMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response updateBankBranchMaster(BankBranchMasterDto bankMasterDto) throws ApplicationException {
		Response response = iBankBranchMasterDao.getBankBranchMasterByNameNotId(bankMasterDto);
		try {
			if(response.getListObject().size()>0&&response.getListObject()!=null){
				return new Response<>(ERROR, ALREADY_EXIST_CODE, ALREADY_EXIST, null, null);
			}else{
			return iBankBranchMasterDao.updateBankBranchMaster(bankMasterDto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response updateBankBranchMasterStatus(BankBranchMasterDto bankMasterDto) throws ApplicationException {
		try {
			return iBankBranchMasterDao.updateBankBranchMasterStatus(bankMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getBankBranchMasterCount(BankBranchMasterDto bankMasterDto) throws ApplicationException {
		try {
			return iBankBranchMasterDao.getBankBranchMasterCount(bankMasterDto);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

}
