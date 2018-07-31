package com.param.global.service;

import in.param.exception.ApplicationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dao.IRelationMasterDao;
import com.param.global.dto.RelationMasterDto;

@SuppressWarnings({ "rawtypes", "unchecked" })
@Service
public class RelationMasterServiceImpl implements IRelationMasterService, ICommonConstants {
	@Autowired
	private IRelationMasterDao iRelationDao;

	@Override
	public Response getRelationMasterList(RelationMasterDto relationMasterDto) throws ApplicationException {

		try {
			return iRelationDao.getRelationMasterList(relationMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response addRelationMaster(RelationMasterDto relationMasterDto) throws ApplicationException {
		try {

			Response deptResponse = iRelationDao.getCountryByName(relationMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {
				
					return new Response(ERROR, null, ALREADY_EXIST, null, null);
				
			} else {
				/*relationMasterDto
						.setCreatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));
				relationMasterDto
						.setUpdatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));*/
				iRelationDao.addCountryMaster(relationMasterDto);
				return new Response(SUCCESS, null, COMMON_SUCCESS, null, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getRelationById(RelationMasterDto relationMasterDto) throws ApplicationException {
		try {
			return iRelationDao.getRelationById(relationMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateRelationMaster(RelationMasterDto relationMasterDto) throws ApplicationException {
		try {
			Response deptResponse = iRelationDao.getRelationByNameNotId(relationMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {

				return new Response(ERROR, null, ALREADY_EXIST, null, null);

			} else {
				/*relationMasterDto
						.setUpdatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));*/
				iRelationDao.updateRelationMaster(relationMasterDto);
				return new Response(SUCCESS, null, COMMON_UPDATE, null, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateStatusForRelation(RelationMasterDto relationMasterDto) throws ApplicationException {
		try {
			Response deptResponse = iRelationDao.getRelationById(relationMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {
				/*relationMasterDto
						.setUpdatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));*/
				iRelationDao.updateRelationStatus(relationMasterDto);
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
	public Response getActiveRelationList() throws ApplicationException {
		try {
			return iRelationDao.getActiveRelationList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getRelationCount(RelationMasterDto relationMasterDto) throws ApplicationException {
		try {
			return iRelationDao.getCount(relationMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

}
