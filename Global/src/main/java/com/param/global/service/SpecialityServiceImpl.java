package com.param.global.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dao.ISpecialityMasterDao;
import com.param.global.dto.SpecialityMasterDto;

import in.param.exception.ApplicationException;

@Service
@SuppressWarnings({ "rawtypes", "unchecked" })
public class SpecialityServiceImpl implements ISpecialityService, ICommonConstants {

	@Autowired
	private ISpecialityMasterDao iSpecialityMasterDao;

	/*
	 * @Autowired SessionObject sessionObject;
	 */

	@Transactional
	@Override
	public Response getSpecialityMasterList(SpecialityMasterDto specialityMasterDto) throws ApplicationException {

		// TODO Auto-generated method stub
		try {
			return iSpecialityMasterDao.getSpecialityMasterList(specialityMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Transactional
	@Override
	public Response addSpecialityMaster(SpecialityMasterDto specialityMasterDto) throws ApplicationException {
		try {
			Response deptResponse = iSpecialityMasterDao.getSpecialityByName(specialityMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {
				return new Response(ERROR, null, ALREADY_EXIST, null, null);

			} else {
				specialityMasterDto.setStatus('A');
//				specialityMasterDto.setGeneralLedgerId(001);
				iSpecialityMasterDao.addSpecialityMaster(specialityMasterDto);
				return new Response(SUCCESS, null, COMMON_SUCCESS, null, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Transactional
	@Override
	public Response updateSpecialityMaster(SpecialityMasterDto SpecialityMasterDto) throws ApplicationException {
		try {
			Response deptResponse = iSpecialityMasterDao.getSpecialityByNameNotId(SpecialityMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {

				return new Response(ERROR, null, ALREADY_EXIST, null, null);

			} else {
				SpecialityMasterDto.setCreatedBy(SpecialityMasterDto.getCreatedBy());
				

				iSpecialityMasterDao.updateSpecialityMaster(SpecialityMasterDto);
				return new Response(SUCCESS, null, COMMON_UPDATE, null, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Transactional
	@Override
	public Response getSpecialityByName(SpecialityMasterDto SpecialityMasterDto) throws ApplicationException {
		try {
			return iSpecialityMasterDao.getSpecialityByName(SpecialityMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	/*
	 * @Override public Response getActiveSpecialityList() throws
	 * ApplicationException { // TODO Auto-generated method stub return null; }
	 */

	@Transactional
	@Override
	public Response getSpecialityById(SpecialityMasterDto specialityMasterDto) {
		try {
			return iSpecialityMasterDao.getSpecialityById(specialityMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Transactional
	@Override
	public Response updateStatus(SpecialityMasterDto specialityMasterDto) {
		try {
			Response deptResponse = iSpecialityMasterDao.getSpecialityById(specialityMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {
				
				iSpecialityMasterDao.updateSpecialityStatus(specialityMasterDto);
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
	public Response getActiveSpecialityMasterList(SpecialityMasterDto specialityMasterDto) throws ApplicationException {
		try {
			return iSpecialityMasterDao.getActiveSpecialityList(specialityMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}
	@Transactional
	@Override
	public Response getSpecialityListForSubSpeciality() throws ApplicationException {
		try {
			return iSpecialityMasterDao.getSpecialityListForSubSpeciality();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}
	@Transactional
	@Override
	public Response getSpecialityCount(SpecialityMasterDto specialityMasterDto) throws ApplicationException {
		try {
			return iSpecialityMasterDao.getCount(specialityMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}
	@Transactional
	@Override
	public Response getOrgActiveSpecialityList(SpecialityMasterDto specialityMasterDto) throws ApplicationException {
		try {
			return iSpecialityMasterDao.getOrgActiveSpecialityList(specialityMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

}
