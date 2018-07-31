package com.param.adt.master.global.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.adt.common.ICommonConstants;
import com.param.adt.common.Response;
import com.param.adt.master.global.dao.IHierarchyMasterDao;
import com.param.adt.master.global.dto.HierarchyMasterDto;

import in.param.exception.ApplicationException;

@Service
@SuppressWarnings({ "rawtypes", "unchecked" })
public class HierarchyMasterServiceImpl implements IHierarchyMasterService, ICommonConstants {

	@Autowired
	IHierarchyMasterDao iHierarchyMasterDao;

	@Override
	public Response addHierarchyMaster(HierarchyMasterDto hierarchyMasterDto) throws ApplicationException {
		try {
			Response deptResponse = iHierarchyMasterDao.getHierarchyByName(hierarchyMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {

				return new Response(ERROR, null, ALREADY_EXIST, null, null);

			} else {

				/*hierarchyMasterDto
						.setCreatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyy HH:mm:ss"));
				hierarchyMasterDto
						.setUpdatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyy HH:mm:ss"));*/
				iHierarchyMasterDao.addHierarchyTypeMaster(hierarchyMasterDto);
				return new Response(SUCCESS, null, COMMON_SUCCESS, null, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getHierarchyMasterList(HierarchyMasterDto hierarchyMasterDto) throws ApplicationException {
		try {
			return iHierarchyMasterDao.getHierarchyMasterList(hierarchyMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateHierarchyMaster(HierarchyMasterDto hierarchyMasterDto) throws ApplicationException {
		try {
			Response deptResponse = iHierarchyMasterDao.getHierarchyByNameNotId(hierarchyMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {
				return new Response(ERROR, null, ALREADY_EXIST, null, null);

			} else {

				/*hierarchyMasterDto
						.setUpdatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyy HH:mm:ss"));*/

				iHierarchyMasterDao.updateHierarchyMaster(hierarchyMasterDto);
				return new Response(SUCCESS, null, COMMON_UPDATE, null, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getHierarchyById(HierarchyMasterDto hierarchyMasterDto) throws ApplicationException {
		try {
			return iHierarchyMasterDao.getHierarchyById(hierarchyMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateStatusForHierarchy(HierarchyMasterDto hierarchyMasterDto) throws ApplicationException {
		try {
			Response deptResponse = iHierarchyMasterDao.getHierarchyById(hierarchyMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {

				/*hierarchyMasterDto
						.setUpdatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyy HH:mm:ss"));*/

				iHierarchyMasterDao.updateHolidayStatus(hierarchyMasterDto);
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
	public Response getActiveHierarchyList() throws ApplicationException {
		try {
			return iHierarchyMasterDao.getActiveHierarchyList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getHierarchyCount(HierarchyMasterDto hierarchyMasterDto) throws ApplicationException {
		try {
			return iHierarchyMasterDao.getCount(hierarchyMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

}
