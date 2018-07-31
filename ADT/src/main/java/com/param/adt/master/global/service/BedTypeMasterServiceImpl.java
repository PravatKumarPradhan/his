package com.param.adt.master.global.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.adt.common.ICommonConstants;
import com.param.adt.common.Response;
import com.param.adt.master.global.dao.IBedTypeMasterDao;
import com.param.adt.master.global.dto.BedTypeMasterDto;

import in.param.exception.ApplicationException;

@Service
@SuppressWarnings({ "rawtypes", "unchecked" })
public class BedTypeMasterServiceImpl implements IBedTypeMasterService, ICommonConstants{
	

	@Autowired
	IBedTypeMasterDao iBedTypeMasterDao;

	@Override
	public Response addBedTypeMaster(BedTypeMasterDto bedTypeMasterDto) {
		try {
			Response deptResponse = iBedTypeMasterDao.getBedTypeByName(bedTypeMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {

				return new Response(ERROR, null, ALREADY_EXIST, null, null);

			} else {

				/*bedTypeMasterDto
						.setCreatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyy HH:mm:ss"));
				bedTypeMasterDto
						.setUpdatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyy HH:mm:ss"));
				*/
				iBedTypeMasterDao.addBedTypeMaster(bedTypeMasterDto);
				return new Response(SUCCESS, null, COMMON_SUCCESS, null, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getBedTypeMasterList(BedTypeMasterDto bedTypeMasterDto) {
		try {
			return iBedTypeMasterDao.getBedTypeMasterList(bedTypeMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateBedTypeMaster(BedTypeMasterDto bedTypeMasterDto) {
		try {
			Response deptResponse = iBedTypeMasterDao.getBedTypeByNameNotId(bedTypeMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {
				return new Response(ERROR, null, ALREADY_EXIST, null, null);

			} else {

				/*bedTypeMasterDto
						.setUpdatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyy HH:mm:ss"));
*/
				iBedTypeMasterDao.updateBedTypeMaster(bedTypeMasterDto);
				return new Response(SUCCESS, null, COMMON_UPDATE, null, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getBedTypeById(BedTypeMasterDto bedTypeMasterDto) {
		try {
			return iBedTypeMasterDao.getBedTypeById(bedTypeMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateStatusForBedType(BedTypeMasterDto bedTypeMasterDto) {
		try {
			Response deptResponse = iBedTypeMasterDao.getBedTypeById(bedTypeMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {

				/*bedTypeMasterDto
						.setUpdatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyy HH:mm:ss"));*/

				iBedTypeMasterDao.updateBedTypeStatus(bedTypeMasterDto);
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
	public Response getActiveBedTypeList() {
		try {
			return iBedTypeMasterDao.getActiveBedTypeMasterList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getBedTypeCount(BedTypeMasterDto bedTypeMasterDto) throws ApplicationException {
		try {
			return iBedTypeMasterDao.getCount(bedTypeMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

}
