package com.param.global.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dao.IBedCategoryMasterDao;
import com.param.global.dto.BedCategoryMasterDto;

import in.param.exception.ApplicationException;
@Service
@SuppressWarnings({"rawtypes","unchecked"})
public class BedCategoryMasterServiceImpl implements IBedCategoryMasterService,ICommonConstants 
{

	@Autowired
	private IBedCategoryMasterDao iBedCategoryDao;
	
	@Override
	public Response addBedCategoryMaster(BedCategoryMasterDto bedCategoryMasterDto) throws ApplicationException {
		try {
			Response deptResponse = iBedCategoryDao.getBedCategoryByName(bedCategoryMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {

				return new Response(ERROR, null, ALREADY_EXIST, null, null);

			} else {

				
				bedCategoryMasterDto.setxHierarchyId(bedCategoryMasterDto.getHierarchyId());
				bedCategoryMasterDto.setxOccupancyUnitId(bedCategoryMasterDto.getOccupancyUnitId());
				iBedCategoryDao.addBedCategoryTypeMaster(bedCategoryMasterDto);
				return new Response(SUCCESS, null, COMMON_SUCCESS, null, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getBedCategoryMasterList(BedCategoryMasterDto bedCategoryMasterDto) throws ApplicationException {
		try {
			return iBedCategoryDao.getBedCategoryMasterList(bedCategoryMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateBedCategoryMaster(BedCategoryMasterDto bedCategoryMasterDto) throws ApplicationException {
		try {
			Response deptResponse = iBedCategoryDao.getBedCategoryByNameNotId(bedCategoryMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {
				return new Response(ERROR, null, ALREADY_EXIST, null, null);

			} else {

				/*bedCategoryMasterDto
						.setUpdatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyy HH:mm:ss"));*/

				iBedCategoryDao.updateBedCategoryMaster(bedCategoryMasterDto);
				return new Response(SUCCESS, null, COMMON_UPDATE, null, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getBedCategoryById(BedCategoryMasterDto bedCategoryMasterDto) throws ApplicationException {
		try {
			return iBedCategoryDao.getBedCategoryById(bedCategoryMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateStatusForBedCategory(BedCategoryMasterDto bedCategoryMasterDto) throws ApplicationException {
		try {
			Response deptResponse = iBedCategoryDao.getBedCategoryById(bedCategoryMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {

				/*.*/

				iBedCategoryDao.updateBedCategoryStatus(bedCategoryMasterDto);
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
	public Response getActiveBedCategoryList() throws ApplicationException {
		try {
			return iBedCategoryDao.getActiveBedCategoryList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getBedCategoryCount(BedCategoryMasterDto bedCategoryMasterDto) throws ApplicationException {
		try {
			return iBedCategoryDao.getCount(bedCategoryMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

}
