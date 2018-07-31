package com.param.global.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dao.IPrefixMasterDao;
import com.param.global.dto.PrefixMasterDto;

import in.param.exception.ApplicationException;
@SuppressWarnings({"rawtypes","unchecked"})
@Service
public class PrefixMasterServiceImpl implements IPrefixMasterService,ICommonConstants
{

	@Autowired
	private IPrefixMasterDao iPrefixMasterDao; 

	@Override
	public Response addPrefixMaster(PrefixMasterDto prefixMasterDto) throws ApplicationException {
		try {

			Response deptResponse = iPrefixMasterDao.getPrefixByName(prefixMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {
				
					return new Response(ERROR, null, ALREADY_EXIST, null, null);
				
			} else {
				/*prefixMasterDto
						.setCreatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));
				prefixMasterDto
						.setUpdatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));*/
				iPrefixMasterDao.addPrefixTypeMaster(prefixMasterDto);
				return new Response(SUCCESS, null, COMMON_SUCCESS, null, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getPrefixMasterList(PrefixMasterDto prefixMasterDto) throws ApplicationException {
		try {
			return iPrefixMasterDao.getPrefixMasterList(prefixMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updatePrefixMaster(PrefixMasterDto prefixMasterDto) throws ApplicationException {
		try {
			Response deptResponse = iPrefixMasterDao.getPrefixByNameNotId(prefixMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {

				return new Response(ERROR, null, ALREADY_EXIST, null, null);

			} else {
				/*prefixMasterDto
						.setUpdatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));*/
				iPrefixMasterDao.updatePrefixMaster(prefixMasterDto);
				return new Response(SUCCESS, null, COMMON_UPDATE, null, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getPrefixById(PrefixMasterDto prefixMasterDto) throws ApplicationException {
		try {
			return iPrefixMasterDao.getPrefixByID(prefixMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	
	}

	@Override
	public Response updateStatusForPrefix(PrefixMasterDto prefixMasterDto) throws ApplicationException {
		try {
			Response deptResponse = iPrefixMasterDao.getPrefixByID(prefixMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {
				/*prefixMasterDto
						.setUpdatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));*/
				iPrefixMasterDao.updatePrefixStatus(prefixMasterDto);
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
	public Response getActivePrefixList() throws ApplicationException {
		try {
			return iPrefixMasterDao.getActivePrefixList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getPrefixCount(PrefixMasterDto prefixMasterDto) throws ApplicationException {
		try {
			return iPrefixMasterDao.getCount(prefixMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	
}
