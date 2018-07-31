package com.param.adt.master.global.service;

import javax.transaction.Transactional;

import com.param.adt.common.Response;
import com.param.adt.master.global.dto.ReferralTypeMasterDto;

import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IReferralTypeMasterService {

	@Transactional
	Response addReferralTypeMaster(ReferralTypeMasterDto referralTypeMasterDto) throws ApplicationException;

	@Transactional
	Response getReferralTypeMasterList(ReferralTypeMasterDto referralTypeMasterDto) throws ApplicationException;

	@Transactional
	Response updateReferralTypeMaster(ReferralTypeMasterDto referralTypeMasterDto) throws ApplicationException;

	@Transactional
	Response getReferralTypeById(ReferralTypeMasterDto referralTypeMasterDto) throws ApplicationException;

	@Transactional
	Response updateStatusForReferralType(ReferralTypeMasterDto referralTypeMasterDto) throws ApplicationException;

	@Transactional
	Response getActiveReferralTypeList(ReferralTypeMasterDto referralTypeMasterDto) throws ApplicationException;

	@Transactional
	Response getReferralTypeCount(ReferralTypeMasterDto referralTypeMasterDto)throws ApplicationException;

	
	

}
