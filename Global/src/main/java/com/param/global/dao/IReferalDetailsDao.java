package com.param.global.dao;

import com.param.global.common.Response;
import com.param.global.dto.ReferalDetailsDto;
import com.param.global.model.ReferalDetails;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IReferalDetailsDao extends IGenericDao<ReferalDetails, Integer>{

	Response saveReferalDetails(ReferalDetailsDto referalDetailsDto)throws ApplicationException;

}
