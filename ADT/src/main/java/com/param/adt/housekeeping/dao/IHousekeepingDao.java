package com.param.adt.housekeeping.dao;

import com.param.adt.common.Response;
import com.param.adt.housekeeping.model.Housekeeping;
import com.param.adt.master.global.dto.BedMasterDto;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;

@SuppressWarnings("rawtypes")
public interface IHousekeepingDao extends IGenericDao<Housekeeping, Integer>{

	
	Response getBedsForHousekeepingList(BedMasterDto bedMasterDto)throws ApplicationException;

	Response getActiveUserList(BedMasterDto bedMasterDto)throws ApplicationException;

	Response assignUserForCleaning(BedMasterDto bedMasterDto)throws ApplicationException;

	Response markAsCleaned(BedMasterDto bedMasterDto)throws ApplicationException;

	Response getListOfVacantBedsForNursing(BedMasterDto bedMasterDto)throws ApplicationException;

	Response acceptRequestForNursing(BedMasterDto bedMasterDto)throws ApplicationException;

	Response rejectRequestForNursing(BedMasterDto bedMasterDto)throws ApplicationException;

	Response searchBedByMultipleCriteriaForHousekeeping(BedMasterDto bedMasterDto)throws ApplicationException;

	Response searchBedByMultipleCriteriaForNursing(BedMasterDto bedMasterDto)throws ApplicationException;

}
