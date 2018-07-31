package com.param.adt.master.global.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.adt.common.ICommonConstants;
import com.param.adt.common.Response;
import com.param.adt.master.global.dao.IEncounterTypeMasterDao;
import com.param.adt.master.global.dto.EncounterTypeMasterDto;

import in.param.exception.ApplicationException;
@Service
@SuppressWarnings({"rawtypes","unchecked"})
public class EncounterTypeMasterServiceImpl implements IEncounterTypeMasterService,ICommonConstants
{

	@Autowired
	private IEncounterTypeMasterDao iEncounterTypeMasterDao;
	
	@Override
	public Response addEncounterTypeMaster(EncounterTypeMasterDto encounterTypeMasterDto) throws ApplicationException {
		try {
			Response deptResponse = iEncounterTypeMasterDao.getEncounterTypeByName(encounterTypeMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {
				
					return new Response(ERROR, null, ALREADY_EXIST, null, null);
				
			} else {
				/*encounterTypeMasterDto
						.setCreatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));
				encounterTypeMasterDto
						.setUpdatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));*/
				iEncounterTypeMasterDao.addEncounterTypeMaster(encounterTypeMasterDto);
				return new Response(SUCCESS, null, COMMON_SUCCESS, null, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getEncounterTypeMasterList(EncounterTypeMasterDto encounterTypeMasterDto) throws ApplicationException {
		try {
			return iEncounterTypeMasterDao.getEncounterTypeMasterList(encounterTypeMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateEncounterTypeMaster(EncounterTypeMasterDto encounterTypeMasterDto)
			throws ApplicationException {
		try {
			Response deptResponse = iEncounterTypeMasterDao.getEncounterTypeByNameNotId(encounterTypeMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {
				return new Response(ERROR, null, ALREADY_EXIST, null, null);

			} else {
				/*encounterTypeMasterDto
						.setUpdatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));*/
				iEncounterTypeMasterDao.updateEncounterTypeMaster(encounterTypeMasterDto);
				return new Response(SUCCESS, null, COMMON_UPDATE, null, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getEncounterTypeById(EncounterTypeMasterDto encounterTypeMasterDto) throws ApplicationException {
		try {
			return iEncounterTypeMasterDao.getEncounterTypeById(encounterTypeMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateStatusForEncounterType(EncounterTypeMasterDto encounterTypeMasterDto)
			throws ApplicationException {
		try {
			Response deptResponse = iEncounterTypeMasterDao.getEncounterTypeById(encounterTypeMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {
				
				/*encounterTypeMasterDto
						.setUpdatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));*/
				iEncounterTypeMasterDao.updateEncounterTypeStatus(encounterTypeMasterDto);
				return new Response(SUCCESS, null, COMMON_UPDATE, null, null);

			} else {
				return new Response(ERROR, null, ALREADY_EXIST, null, null);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getActiveEncounterTypeList() throws ApplicationException {
		try {
			return iEncounterTypeMasterDao.getActiveEncounterTypeList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getEncounterCount(EncounterTypeMasterDto encounterTypeMasterDto) throws ApplicationException {
		try {
			return iEncounterTypeMasterDao.getCount(encounterTypeMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

}
