package com.param.billing.opd.service;

import java.util.Date;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.param.billing.common.SerialNoUtility;
import com.param.billing.exception.BillingException;
import com.param.billing.global.dto.DepositCollectionReqDto;
import com.param.billing.global.dto.DepositSearchReqDto;
import com.param.billing.global.dto.PaymentDepositDetailsDto;
import com.param.billing.global.dto.VisitTypeEncounterReqDto;
import com.param.billing.global.model.DepositMaster;
import com.param.billing.master.dto.DepositMasterDto;
import com.param.billing.opd.dao.IDepositMasterDao;
import com.param.billing.opd.dao.IPaymentDepositDetailsDao;
import com.param.global.common.GlobalCommonDateUtils;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;

@Service
@SuppressWarnings("rawtypes")
public class DepositMasterServiceImpl implements IDepositMasterService, ICommonConstants{

	@Autowired
	private IDepositMasterDao iDepositMasterDao;
	@Autowired
	private SerialNoUtility serialNoUtility;
	@Autowired
	private IPaymentDepositDetailsDao iPaymentDepositDetailsDao;
	
	@Override
	@Transactional
	public Response addDeposit(DepositCollectionReqDto reqDto) throws BillingException {
		try{
			Date date = new Date();
			String dateTime = GlobalCommonDateUtils.getStringDate(date, "dd-M-yyyy HH:mm:ss");
			
			//get current deposit id
			Long depositId = iDepositMasterDao.getCurrentId();
			//save deposit master 
			DepositMasterDto deposit = reqDto.getDepositMasterDto();
			deposit.setAvailableDeposit(deposit.getDepositAmount());
			deposit.setConsumedDeposit(0);
			deposit.setCreatedDate(dateTime);
			deposit.setDepositDate(dateTime);
			deposit.setDepositNo(serialNoUtility.generateDepositNo(depositId));
			deposit.setStatus(ACTIVE);
			deposit.setUpdatedBy(deposit.getCreatedBy());
			deposit.setUpdatedDate(dateTime);
			Response depositRes = iDepositMasterDao.saveDeposit(deposit);
			if(depositRes.getCode().equalsIgnoreCase(SUCCESS_CODE) && depositRes.getObject() != null){
				DepositMaster depositMaster = (DepositMaster)depositRes.getObject();
				
				//save payment details
				ListIterator<PaymentDepositDetailsDto> paymentItr = reqDto.getListPaymentDepositDetailsDto().listIterator();
				PaymentDepositDetailsDto detailsDto = null;
				while(paymentItr.hasNext()){
					detailsDto = paymentItr.next();
					detailsDto.setCreatedDate(dateTime);
					detailsDto.setDepositId(depositMaster.getDepositMasterId());
					detailsDto.setUpdatedBy(deposit.getCreatedBy());
					detailsDto.setUpdatedDate(dateTime);
					iPaymentDepositDetailsDao.savePaymentDepositDetails(detailsDto);
				}
			}
			return new Response<>(SUCCESS, SUCCESS_CODE, DEPOSIT_ADDED_MSG + deposit.getDepositNo(), null, null);
		}catch(BillingException be){
			throw be;
		}catch(Exception e){
			e.printStackTrace();
			throw new BillingException(e.getMessage());
		}
	}

	@Override
	@Transactional
	public Response searchDeposit(DepositSearchReqDto dto) throws BillingException {
		try{
			return iDepositMasterDao.searchDeposit(dto);
		}catch(BillingException be){
			throw be;
		}catch(Exception e){
			e.printStackTrace();
			throw new BillingException(e.getMessage());
		}
	}

	@Override
	@Transactional
	public Response getEncountersForDeposit(VisitTypeEncounterReqDto reqDto) throws BillingException {
		try{
			Response response = new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
			switch(reqDto.getVisitTypeId()){
			case 1 :
				response = iDepositMasterDao.getEncountersByPatIdForDeposit(reqDto);
				break;
			case 2 :
				response = iDepositMasterDao.getAdmissionByPatVisitForDeposit(reqDto);
				break;
			case 3 :
				response = iDepositMasterDao.getAdmissionByPatVisitForDeposit(reqDto);
				break;
			}
			return response;
		}catch(BillingException be){
			throw be;
		}catch(Exception e){
			e.printStackTrace();
			throw new BillingException(e.getMessage());
		}
	}

	@Override
	@Transactional
	public Response getAvailableDepositByPatient(int orgId, int unitId, int patientId) throws BillingException {
		try{
			return iDepositMasterDao.getAvailableDepositByPatient(orgId, unitId, patientId);
		}catch(BillingException be){
			throw be;
		}catch(Exception e){
			e.printStackTrace();
			throw new BillingException(e.getMessage());
		}
	}

}
