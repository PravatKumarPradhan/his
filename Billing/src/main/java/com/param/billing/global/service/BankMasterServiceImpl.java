package com.param.billing.global.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.param.billing.common.ICommonConstants;
import com.param.billing.common.Response;
import com.param.billing.global.dao.IBankMasterDao;
import com.param.billing.global.dto.BankMasterDto;

import in.param.exception.ApplicationException;

@Service
@SuppressWarnings({"rawtypes","unchecked"})
public class BankMasterServiceImpl implements IBankMasterService, ICommonConstants {

	@Autowired
	private IBankMasterDao iBankMasterDao;

	@Override
	@Transactional
	public Response getActiveBankMaster(BankMasterDto bankMasterDto) throws ApplicationException {
		try {
			return iBankMasterDao.getActiveBankMaster(bankMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response saveBankMaster(BankMasterDto bankMasterDto) throws ApplicationException {
		try {
			Response response=iBankMasterDao.getBankMasterByName(bankMasterDto);
			
			if(response.getListObject().size()>0 && response.getListObject()!=null){
				return new Response<>(ERROR, ALREADY_EXIST_CODE, ALREADY_EXIST, null, null);
			}else{
				return iBankMasterDao.saveBankMaster(bankMasterDto);
			}
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getBankMasterById(int bankId, int orgId) throws ApplicationException {
		try {
			return iBankMasterDao.getBankMasterById(bankId, orgId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getBankMasterList(BankMasterDto bankMasterDto) throws ApplicationException {
		try {
			return iBankMasterDao.getBankMasterList(bankMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}


	@Override
	public Response updateBankMaster(BankMasterDto bankMasterDto) throws ApplicationException {
		try {
			Response response=iBankMasterDao.getBankMasterByNameNotId(bankMasterDto);
			if(response.getListObject().size()>0 && response.getListObject()!=null){
				return new Response(ERROR, null, ALREADY_EXIST, null, null);
			}else{
				return iBankMasterDao.updateBankMaster(bankMasterDto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response updateBankMasterStatus(BankMasterDto bankMasterDto) throws ApplicationException {
		try {
			return iBankMasterDao.updateBankMasterStatus(bankMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getBankMasterCount(BankMasterDto bankMasterDto) throws ApplicationException {
		try {
			return iBankMasterDao.getBankMasterCount(bankMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

}
