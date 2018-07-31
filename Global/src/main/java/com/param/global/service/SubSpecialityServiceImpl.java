package com.param.global.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dao.ISubSpecialityMasterDao;
import com.param.global.dto.SubSpecialityMasterDto;

import in.param.exception.ApplicationException;

@SuppressWarnings({ "rawtypes", "unchecked" })
@Service
public class SubSpecialityServiceImpl implements ICommonConstants, ISubSpecialityService {

	@Autowired
	private ISubSpecialityMasterDao iSubSpecialityMasterDao;

	@Transactional
	@Override
	public Response addSubSpecialityMaster(SubSpecialityMasterDto subspecialityMasterDto) throws ApplicationException {
		try {
			Response deptResponse = iSubSpecialityMasterDao.getSubSpecialityByName(subspecialityMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {

				return new Response(ERROR, null, ALREADY_EXIST, null, null);

			} else {

				/*subspecialityMasterDto
						.setCreatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));
				subspecialityMasterDto
						.setUpdatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));*/

				iSubSpecialityMasterDao.addSubSpecialityMaster(subspecialityMasterDto);
				return new Response(SUCCESS, null, COMMON_SUCCESS, null, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Transactional
	@Override
	public Response getSubSpecialityMasterList(SubSpecialityMasterDto subSpecialityMasterDto) throws ApplicationException {
		// TODO Auto-generated method stub
		try {
			return iSubSpecialityMasterDao.getSubSpecialityMasterList(subSpecialityMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Transactional
	@Override
	public Response getSubSpecialityById(SubSpecialityMasterDto subSpecialityMasterDto) throws ApplicationException {
		try {
			return iSubSpecialityMasterDao.getSubSpecialityById(subSpecialityMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Transactional
	@Override
	public Response updateStatusForSubSpeciality(SubSpecialityMasterDto subSpecialityMasterDto)
			throws ApplicationException {
		try {
			Response deptResponse = iSubSpecialityMasterDao.getSubSpecialityById(subSpecialityMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {
				/*subSpecialityMasterDto
						.setUpdatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));*/
				iSubSpecialityMasterDao.updateSubSpecialityStatus(subSpecialityMasterDto);
				return new Response(SUCCESS, null, COMMON_UPDATE, null, null);

			} else {
				return new Response(ERROR, null, ALREADY_EXIST, null, null);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);

	}

	@Transactional
	@Override
	public Response updateSubSpecialityMaster(SubSpecialityMasterDto subspecialityMasterDto)
			throws ApplicationException {
		try {
			Response deptResponse = iSubSpecialityMasterDao.getSubSpecialityByNameNotId(subspecialityMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {

				return new Response(ERROR, null, ALREADY_EXIST, null, null);

			} else {
				/*subspecialityMasterDto
						.setUpdatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));*/
				iSubSpecialityMasterDao.updateSubSpecialityMaster(subspecialityMasterDto);
				return new Response(SUCCESS, null, COMMON_UPDATE, null, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);

	}
	
	@Transactional
	@Override
	public Response getSubSpecialityCount(SubSpecialityMasterDto subSpecialityMasterDto) throws ApplicationException {
		try {
			return iSubSpecialityMasterDao.getCount(subSpecialityMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Transactional
	@Override
	public Response getSubSpecialityBySpecialityId(SubSpecialityMasterDto subspecialityMasterDto)
			throws ApplicationException {
		try {
			return iSubSpecialityMasterDao.getSubSpecialityBySpecialityId(subspecialityMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Transactional
	@Override
	public Response getSubspecialityNotInUnit(int id) throws ApplicationException {
		try {
			return iSubSpecialityMasterDao.getSubspecialityNotInUnit(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}


}
