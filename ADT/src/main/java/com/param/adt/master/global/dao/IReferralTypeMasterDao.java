package com.param.adt.master.global.dao;

import com.param.adt.common.Response;
import com.param.adt.master.global.dto.ReferralTypeMasterDto;
import com.param.adt.master.global.model.ReferralTypeMaster;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IReferralTypeMasterDao extends IGenericDao<ReferralTypeMaster, Integer>{

	
	Response getReferralTypeByName(ReferralTypeMasterDto referralTypeMasterDto) throws ApplicationException;

	Response addReferralTypeTypeMaster(ReferralTypeMasterDto referralTypeMasterDto) throws ApplicationException;

	Response getReferralTypeMasterList(ReferralTypeMasterDto referralTypeMasterDto) throws ApplicationException;

	Response getReferralTypeById(ReferralTypeMasterDto referralTypeMasterDto) throws ApplicationException;

	Response getActiveReferralTypeList(ReferralTypeMasterDto referralTypeMasterDto) throws ApplicationException;

	Response getReferralTypeByNameNotId(ReferralTypeMasterDto referralTypeMasterDto) throws ApplicationException;

	Response updateReferralTypeMaster(ReferralTypeMasterDto referralTypeMasterDto) throws ApplicationException;

	Response updateStatusForReferralType(ReferralTypeMasterDto referralTypeMasterDto) throws ApplicationException;

	Response getCount(ReferralTypeMasterDto referralTypeMasterDto)throws ApplicationException;
	
	

}
