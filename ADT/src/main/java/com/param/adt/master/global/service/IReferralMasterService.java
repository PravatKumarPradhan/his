package com.param.adt.master.global.service;

import javax.transaction.Transactional;

import com.param.adt.common.Response;
import com.param.adt.master.global.dto.ReferralMasterDto;
import com.param.global.dto.AreaMasterDto;

import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IReferralMasterService {

	@Transactional
	Response addReferralMaster(ReferralMasterDto referralMasterDto) throws ApplicationException;

	@Transactional 
	Response getReferralMasterList(ReferralMasterDto referralMasterDto) throws ApplicationException;

	@Transactional
	Response updateReferralMaster(ReferralMasterDto referralMasterDto) throws ApplicationException;

	@Transactional
	Response getReferralById(ReferralMasterDto referralMasterDto) throws ApplicationException;

	@Transactional
	Response updateStatusForReferral(ReferralMasterDto referralMasterDto) throws ApplicationException;

	@Transactional
	Response getActiveReferralList() throws ApplicationException;
	
	@Transactional
	Response getAreaByCityId(AreaMasterDto areaMasterDto) throws ApplicationException;

	@Transactional
	Response getReferralCount(ReferralMasterDto referralMasterDto)throws ApplicationException;

	@Transactional
	Response getReferralListByReferralTypeId(ReferralMasterDto referralMasterDto)throws ApplicationException;
}
