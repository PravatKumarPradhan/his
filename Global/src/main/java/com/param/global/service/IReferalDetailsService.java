package com.param.global.service;

import javax.transaction.Transactional;

import com.param.global.common.Response;
import com.param.global.dto.ReferalDetailsDto;

import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IReferalDetailsService {

	@Transactional
	Response saveReferalDetails(ReferalDetailsDto referalDetailsDto)throws ApplicationException;

}
