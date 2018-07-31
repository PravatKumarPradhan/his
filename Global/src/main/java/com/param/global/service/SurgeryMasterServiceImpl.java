package com.param.global.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.param.exception.ApplicationException;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dao.ISurgeryMasterDao;
import com.param.global.dto.SurgeryMasterDto;

@Service
@SuppressWarnings({ "unchecked", "rawtypes" })
public class SurgeryMasterServiceImpl implements ISurgeryMasterService, ICommonConstants {

	@Autowired
	private ISurgeryMasterDao iSurgeryMasterDao;

	@Override
	@Transactional
	public Response getListOfSurgeryMaster(SurgeryMasterDto surgeryMasterDto) throws ApplicationException {
		// TODO Auto-generated method stub
		return iSurgeryMasterDao.getListOfSurgeryMaster(surgeryMasterDto);
	}

	@Override
	@Transactional
	public Response getSurgeryMasterList(SurgeryMasterDto surgeryMasterDto) throws ApplicationException {
		try {
			return iSurgeryMasterDao.getSurgeryMasterList(surgeryMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);

	}

	@Override
	@Transactional
	public Response saveSurgeryMaster(SurgeryMasterDto surgeryMasterDto) throws ApplicationException {

		try {
			Response deptResponse = iSurgeryMasterDao.getSurgeryMasterByName(surgeryMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {

				return new Response(ERROR, null, ALREADY_EXIST, null, null);

			} else {
				iSurgeryMasterDao.saveSurgeryMaster(surgeryMasterDto);
				return new Response(SUCCESS, null, COMMON_SUCCESS, null, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	@Transactional
	public Response getSurgeryMasterById(SurgeryMasterDto surgeryMasterDto) throws ApplicationException {
		try {
			return iSurgeryMasterDao.getSurgeryMasterById(surgeryMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	@Transactional
	public Response updateSurgeryMaster(SurgeryMasterDto surgeryMasterDto) throws ApplicationException {
		try {
			return iSurgeryMasterDao.updateSurgeryMaster(surgeryMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	@Transactional
	public Response updateStatusForSurgeryMaster(SurgeryMasterDto surgeryMasterDto) throws ApplicationException {
		try {
			return iSurgeryMasterDao.updateStatusForSurgeryMaster(surgeryMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

}
