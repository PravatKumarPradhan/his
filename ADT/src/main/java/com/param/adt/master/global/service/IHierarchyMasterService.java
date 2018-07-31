package com.param.adt.master.global.service;

import javax.transaction.Transactional;

import com.param.adt.common.Response;
import com.param.adt.master.global.dto.HierarchyMasterDto;

import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IHierarchyMasterService {

	@Transactional
	Response addHierarchyMaster(HierarchyMasterDto hierarchyMasterDto) throws ApplicationException;

	@Transactional
	Response getHierarchyMasterList(HierarchyMasterDto hierarchyMasterDto) throws ApplicationException;

	@Transactional
	Response updateHierarchyMaster(HierarchyMasterDto hierarchyMasterDto) throws ApplicationException;

	@Transactional
	Response getHierarchyById(HierarchyMasterDto hierarchyMasterDto) throws ApplicationException;

	@Transactional
	Response updateStatusForHierarchy(HierarchyMasterDto hierarchyMasterDto) throws ApplicationException;

	@Transactional
	Response getActiveHierarchyList() throws ApplicationException;

	@Transactional
	Response getHierarchyCount(HierarchyMasterDto hierarchyMasterDto) throws ApplicationException;
	

}
