package com.param.adt.master.global.dao;

import com.param.adt.common.Response;
import com.param.adt.master.global.dto.ReferralMasterDto;
import com.param.adt.master.global.model.ReferralMaster;
import com.param.global.dto.AreaMasterDto;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IReferralMasterDao extends IGenericDao<ReferralMaster, Integer>{

	
	Response getReferralByName(ReferralMasterDto referralMasterDto) throws ApplicationException;

	Response addReferralTypeTypeMaster(ReferralMasterDto referralMasterDto) throws ApplicationException;

	Response getReferralMasterList(ReferralMasterDto referralMasterDto) throws ApplicationException;

	Response getReferralById(ReferralMasterDto referralMasterDto) throws ApplicationException;

	Response getActiveReferralList() throws ApplicationException;

	Response getReferralByNameNotId(ReferralMasterDto referralMasterDto)throws ApplicationException;

	Response updateReferralMaster(ReferralMasterDto referralMasterDto) throws ApplicationException;

	Response updateReferralMasterStatus(ReferralMasterDto referralMasterDto)throws ApplicationException;

	Response getAreaByCityId(AreaMasterDto areaMasterDto) throws ApplicationException;

	Response getCount(ReferralMasterDto referralMasterDto)throws ApplicationException;

	Response getReferralListByReferralTypeId(ReferralMasterDto referralMasterDto)throws ApplicationException;

}
