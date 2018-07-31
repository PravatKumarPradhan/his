package com.param.adt.housekeeping.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.adt.common.ICommonConstants;
import com.param.adt.common.Response;
import com.param.adt.housekeeping.dao.IHousekeepingDao;
import com.param.adt.master.global.dto.BedMasterDto;

import in.param.exception.ApplicationException;


@Service
@SuppressWarnings({"unchecked","rawtypes"})
public class HousekeepingServiceImpl implements IHousekeepingService,ICommonConstants{

	@Autowired
	IHousekeepingDao iHousekeepingDao;
	
	@Override
	public Response getBedsForHousekeepingList(BedMasterDto bedMasterDto) throws ApplicationException {
		try {
			return iHousekeepingDao.getBedsForHousekeepingList(bedMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getActiveUserList(BedMasterDto bedMasterDto) throws ApplicationException {
		try {
			return iHousekeepingDao.getActiveUserList(bedMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response assignUserForCleaning(BedMasterDto bedMasterDto) throws ApplicationException {
		try {
			return iHousekeepingDao.assignUserForCleaning(bedMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response markAsCleaned(BedMasterDto bedMasterDto) throws ApplicationException {
		try {
			return iHousekeepingDao.markAsCleaned(bedMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getListOfVacantBedsForNursing(BedMasterDto bedMasterDto) throws ApplicationException {
		try {
			return iHousekeepingDao.getListOfVacantBedsForNursing(bedMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response acceptRequestForNursing(BedMasterDto bedMasterDto) throws ApplicationException {
		try {
			return iHousekeepingDao.acceptRequestForNursing(bedMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response rejectRequestForNursing(BedMasterDto bedMasterDto) throws ApplicationException {
		try {
			return iHousekeepingDao.rejectRequestForNursing(bedMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response searchBedByMultipleCriteriaForHousekeeping(BedMasterDto bedMasterDto) {
		try {
			return iHousekeepingDao.searchBedByMultipleCriteriaForHousekeeping(bedMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response searchBedByMultipleCriteriaForNursing(BedMasterDto bedMasterDto) throws ApplicationException {
		try {
			return iHousekeepingDao.searchBedByMultipleCriteriaForNursing(bedMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

}
