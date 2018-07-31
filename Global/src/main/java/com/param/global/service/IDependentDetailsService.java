package com.param.global.service;

import javax.transaction.Transactional;

import com.param.global.common.Response;
import com.param.global.dto.DependentDetailsDto;

import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IDependentDetailsService {

	@Transactional
	Response saveDependentDetails(DependentDetailsDto dependentDetailsDto)throws ApplicationException;

	@Transactional
	Response getDependentDetails(DependentDetailsDto dependentDetailsDto)throws ApplicationException;

	@Transactional
	Response updateDependentDetails(DependentDetailsDto dependentDetailsDto)throws ApplicationException;

	@Transactional
	Response searchDependentDetailsByCriteria(DependentDetailsDto dependentDetailsDto)throws ApplicationException;

}
