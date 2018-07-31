package com.param.global.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dao.IDistrictMasterDao;
import com.param.global.dto.DistrictMasterDto;
import com.param.global.dto.StateMasterDto;

import in.param.exception.ApplicationException;
@SuppressWarnings({"rawtypes","unchecked"})
@Repository
public class DistrictMasterServiceImpl implements IDistrictMasterService,ICommonConstants 
{

	@Autowired
	private IDistrictMasterDao iDistrictMaster;

	@Override
	public Response addDistrictMaster(DistrictMasterDto districtMasterDto) throws ApplicationException {
		try {
			Response deptResponse = iDistrictMaster.getDistrictByName(districtMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {
				
					return new Response(ERROR, null, ALREADY_EXIST, null, null);
				
			} else {
				/*districtMasterDto
						.setCreatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));
				districtMasterDto
						.setUpdatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));*/
				iDistrictMaster.addDistrictMaster(districtMasterDto);
				return new Response(SUCCESS, null, COMMON_SUCCESS, null, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getDistrictMasterList(DistrictMasterDto districtMasterDto) throws ApplicationException {
		try {
			return iDistrictMaster.getDistrictMasterList(districtMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateDistrictMaster(DistrictMasterDto districtMasterDto) throws ApplicationException {
		try {
			Response deptResponse = iDistrictMaster.getDistrictByNameNotId(districtMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {
				return new Response(ERROR, null, ALREADY_EXIST, null, null);

			} else {
				/*districtMasterDto
						.setUpdatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));*/
				iDistrictMaster.updateDistrictMaster(districtMasterDto);
				return new Response(SUCCESS, null, COMMON_UPDATE, null, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getDistrictById(DistrictMasterDto districtMasterDto) throws ApplicationException {
		try {
			return iDistrictMaster.getDistrictById(districtMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateStatusForDistrict(DistrictMasterDto districtMasterDto) throws ApplicationException {
		try {
			Response deptResponse = iDistrictMaster.getDistrictById(districtMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {
				
				/*districtMasterDto
						.setUpdatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));*/
				iDistrictMaster.updateDistrictStatus(districtMasterDto);
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
	public Response getActiveDistrictList() throws ApplicationException {
		try {
			return iDistrictMaster.getActiveDistrictList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getActiveStateListByCountryId(StateMasterDto stateMasterDto) throws ApplicationException {
		try {
			return iDistrictMaster.getActiveStateListByCountryId(stateMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getDistrictCount(DistrictMasterDto districtMasterDto) throws ApplicationException {
		try {
			return iDistrictMaster.getCount(districtMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

}
