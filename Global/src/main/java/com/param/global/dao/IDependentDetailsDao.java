package com.param.global.dao;

import com.param.global.common.Response;
import com.param.global.dto.DependentDetailsDto;
import com.param.global.model.DependentDetails;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IDependentDetailsDao extends IGenericDao<DependentDetails, Integer>{

	Response saveDependentDetails(DependentDetailsDto detailsDto)throws ApplicationException;

	Response getDependentDetails(DependentDetailsDto dependentDetailsDto)throws ApplicationException;

	Response updateDependentDetails(DependentDetailsDto dependentDetailsDtoNew)throws ApplicationException;

	Response inactivePreviousDependents(DependentDetailsDto dependentDetailsDto)throws ApplicationException;

	Response searchDependentDetailsOfDoctor(DependentDetailsDto dependentDetailsDto)throws ApplicationException;

	Response searchDependentDetailsOfEmployee(DependentDetailsDto dependentDetailsDto)throws ApplicationException;

}
