package com.param.billing.unit.dao;

import com.param.global.common.Response;
import com.param.service.dto.TContractGroupPharmacyExclusionDetailsDto;
import com.param.service.global.model.TContractGroupPharmacyExclusionDetails;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface ITContractGroupPharmacyExclusionDetailsDto extends IGenericDao<TContractGroupPharmacyExclusionDetails,Integer> {
	public  Response saveContractGroupPharmacyExclusionDetails(TContractGroupPharmacyExclusionDetailsDto tContractGroupPharmacyExclusionReqDto)throws ApplicationException;

}
