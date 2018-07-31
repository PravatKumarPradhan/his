package com.param.adt.master.global.dao;

import com.param.adt.common.Response;
import com.param.adt.master.global.dto.HierarchyMasterDto;
import com.param.adt.master.global.model.HierarchyMaster;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;
@SuppressWarnings("rawtypes")
public interface IHierarchyMasterDao extends IGenericDao<HierarchyMaster, Integer>{

	
	Response getHierarchyByName(HierarchyMasterDto hierarchyMasterDto) throws ApplicationException;

	Response addHierarchyTypeMaster(HierarchyMasterDto hierarchyMasterDto) throws ApplicationException;

	Response getHierarchyMasterList(HierarchyMasterDto hierarchyMasterDto) throws ApplicationException;

	Response getHierarchyById(HierarchyMasterDto hierarchyMasterDto) throws ApplicationException;

	Response getActiveHierarchyList() throws ApplicationException;

	Response getHierarchyByNameNotId(HierarchyMasterDto hierarchyMasterDto) throws ApplicationException;

	Response updateHierarchyMaster(HierarchyMasterDto hierarchyMasterDto) throws ApplicationException;

	Response updateHolidayStatus(HierarchyMasterDto hierarchyMasterDto) throws ApplicationException;

	Response getCount(HierarchyMasterDto hierarchyMasterDto) throws ApplicationException;

}
