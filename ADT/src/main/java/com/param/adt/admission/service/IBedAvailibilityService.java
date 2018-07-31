package com.param.adt.admission.service;

import javax.transaction.Transactional;

import com.param.adt.admission.dto.BedAvailibailityDto;
import com.param.adt.common.Response;

import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IBedAvailibilityService {

	@Transactional
	Response bedAvailiblityList(BedAvailibailityDto bedAvailibailityDto)throws ApplicationException;

}
