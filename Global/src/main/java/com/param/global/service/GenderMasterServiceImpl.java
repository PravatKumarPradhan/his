package com.param.global.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dao.IGenderMasterDao;
import com.param.global.dto.GenderMasterDto;

import in.param.exception.ApplicationException;
@SuppressWarnings({"unchecked","rawtypes"})
@Service
public class GenderMasterServiceImpl implements IGenderMasterService,ICommonConstants{
	
	@Autowired
	private IGenderMasterDao iGenderMasterDao;

	@Override
	public Response getGenderMasterList(GenderMasterDto genderMasterDto) throws ApplicationException {
		
		try {
			return iGenderMasterDao.getGenderMasterList(genderMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}


	@Override
	public Response addGenderMaster(GenderMasterDto genderMasterDto) {
		// TODO Auto-generated method stub
		try {

			Response deptResponse = iGenderMasterDao.getGenderByName(genderMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {
				
					return new Response(ERROR, null, ALREADY_EXIST, null, null);
			
			} else {
				/*genderMasterDto.setCreatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta","dd-M-yyyy HH:mm:ss"));
				genderMasterDto.setUpdatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta","dd-M-yyyy HH:mm:ss"));*/
				iGenderMasterDao.addGenderMaster(genderMasterDto);
				return new Response(SUCCESS, null, COMMON_SUCCESS, null, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getGenderById(GenderMasterDto genderMasterDto) throws ApplicationException {
		try {
			return iGenderMasterDao.getGenderByID(genderMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateGenderMaster(GenderMasterDto genderMasterDto) {
		try {
			Response deptResponse = iGenderMasterDao.getGenderByNameNotId(genderMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {

				return new Response(ERROR, null, ALREADY_EXIST, null, null);

			} else {
				/*genderMasterDto.setUpdatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta","dd-M-yyyy HH:mm:ss"));*/
				iGenderMasterDao.updateGenderMaster(genderMasterDto);
				return new Response(SUCCESS, null, COMMON_UPDATE, null, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateStatusForGender(GenderMasterDto genderMasterDto) throws ApplicationException {
		try {
			Response deptResponse = iGenderMasterDao.getGenderByID(genderMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {
				/*genderMasterDto.setUpdatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta","dd-M-yyyy HH:mm:ss"));*/
				iGenderMasterDao.updateGenderStatus(genderMasterDto);
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
	public Response getActiveGenderList() throws ApplicationException {
		try {
			return iGenderMasterDao.getActiveGenderList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}


	@Override
	public Response getActiveGenderList(GenderMasterDto genderMasterDto) throws ApplicationException {
		try {
			return iGenderMasterDao.getCount(genderMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}
	
}
