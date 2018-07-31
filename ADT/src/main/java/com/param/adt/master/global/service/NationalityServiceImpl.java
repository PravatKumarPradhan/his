package com.param.adt.master.global.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.adt.common.ICommonConstants;
import com.param.adt.common.Response;
import com.param.adt.master.global.dao.INationalityMasterDao;
import com.param.adt.master.global.dto.NationalityMasterDto;

import in.param.exception.ApplicationException;

@Service
@SuppressWarnings({ "rawtypes", "unchecked" })
public class NationalityServiceImpl implements INationalityMasterService, ICommonConstants {

	@Autowired
	INationalityMasterDao iNatioanlityMasterDao;

	@Transactional
	@Override
	public Response addNationalityMaster(NationalityMasterDto nationalityMasterDto) throws ApplicationException {
		try {

			Response deptResponse = iNatioanlityMasterDao.getNationalityByName(nationalityMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {
				
					return new Response(ERROR, null, ALREADY_EXIST, null, null);
				
			} else {
				/*nationalityMasterDto
						.setCreatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));
				nationalityMasterDto
						.setUpdatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));*/
				iNatioanlityMasterDao.addNationalityTypeMaster(nationalityMasterDto);
				return new Response(SUCCESS, null, COMMON_SUCCESS, null, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Transactional
	@Override
	public Response getNationalityMasterList(NationalityMasterDto nationalityMasterDto) throws ApplicationException {
		try {
			return iNatioanlityMasterDao.getNationalityMasterList(nationalityMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}
	
	

	@Transactional
	@Override
	public Response updateNationalityMaster(NationalityMasterDto nationalityMasterDto) throws ApplicationException {
		try {
			Response deptResponse = iNatioanlityMasterDao.getNationalityByNameNotId(nationalityMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {

				return new Response(ERROR, null, ALREADY_EXIST, null, null);

			} else {
				/*nationalityMasterDto
						.setUpdatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));*/
				iNatioanlityMasterDao.updateNationalityMaster(nationalityMasterDto);
				return new Response(SUCCESS, null, COMMON_UPDATE, null, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Transactional
	@Override
	public Response getNationalityById(NationalityMasterDto nationalityMasterDto) throws ApplicationException {
		try {
			return iNatioanlityMasterDao.getNationalityByID(nationalityMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Transactional
	@Override
	public Response updateStatusForNationality(NationalityMasterDto nationalityMasterDto) throws ApplicationException {
		try {
			Response deptResponse = iNatioanlityMasterDao.getNationalityByID(nationalityMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {
				/*nationalityMasterDto
						.setUpdatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));*/
				iNatioanlityMasterDao.updateNationalityStatus(nationalityMasterDto);
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
	public Response getActiveNationalityList() throws ApplicationException {
		try {
			return iNatioanlityMasterDao.getActiveNationalityMasterList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}
	
	@Transactional
	@Override
	public Response getNationalityCount(NationalityMasterDto nationalityMasterDto) throws ApplicationException {
		try {
			return iNatioanlityMasterDao.getCount(nationalityMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

}
