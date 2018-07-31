package com.param.adt.master.global.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.adt.common.ICommonConstants;
import com.param.adt.common.Response;
import com.param.adt.master.global.dao.IDocumentTypeMasterDao;
import com.param.adt.master.global.dto.DocumentTypeMasterDto;

import in.param.exception.ApplicationException;
@Service
@SuppressWarnings({"rawtypes","unchecked"})
public class DocumentTypeMasterServiceImpl implements IDocumentTypeMasterService, ICommonConstants {

	
	@Autowired 
	IDocumentTypeMasterDao iDocumentTypeMasterDao;
	
	@Override
	public Response addDocumentTypeMaster(DocumentTypeMasterDto documentTypeMasterDto) throws ApplicationException {
		try {
			Response deptResponse = iDocumentTypeMasterDao.getDocumentTypeByName(documentTypeMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {

				return new Response(ERROR, null, ALREADY_EXIST, null, null);

			} else {

				/*documentTypeMasterDto
						.setCreatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyy HH:mm:ss"));
				documentTypeMasterDto
						.setUpdatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyy HH:mm:ss"));*/
				iDocumentTypeMasterDao.addDocumentTypeTypeMaster(documentTypeMasterDto);
				return new Response(SUCCESS, null, COMMON_SUCCESS, null, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
			return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getDocumentTypeMasterList(DocumentTypeMasterDto documentTypeMasterDto) throws ApplicationException {
		try {
			return iDocumentTypeMasterDao.getDocumentTypeMasterList(documentTypeMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateDocumentTypeMaster(DocumentTypeMasterDto documentTypeMasterDto) throws ApplicationException {
		try {
			Response deptResponse = iDocumentTypeMasterDao.getDocumentTypeByNameNotId(documentTypeMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {
				return new Response(ERROR, null, ALREADY_EXIST, null, null);

			} else {

				/*documentTypeMasterDto
						.setUpdatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyy HH:mm:ss"));*/

				iDocumentTypeMasterDao.updateDocumentTypeMaster(documentTypeMasterDto);
				return new Response(SUCCESS, null, COMMON_UPDATE, null, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getDocumentTypeById(DocumentTypeMasterDto documentTypeMasterDto) throws ApplicationException {
		try {
			return iDocumentTypeMasterDao.getDocumentTypeById(documentTypeMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response updateStatusForDocumentType(DocumentTypeMasterDto documentTypeMasterDto)
			throws ApplicationException {
		try {
			Response deptResponse = iDocumentTypeMasterDao.getDocumentTypeById(documentTypeMasterDto);
			if (deptResponse.getListObject() != null && deptResponse.getListObject().size() > 0) {

				/*documentTypeMasterDto
						.setUpdatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyy HH:mm:ss"));*/

				iDocumentTypeMasterDao.updateHolidayStatus(documentTypeMasterDto);
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
	public Response getActiveDocumentTypeList() throws ApplicationException {
		try {
			return iDocumentTypeMasterDao.getActiveDocumentTypeList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@Override
	public Response getDocumentTypeCount(DocumentTypeMasterDto documentTypeMasterDto) throws ApplicationException {
		try {
			return iDocumentTypeMasterDao.getCount(documentTypeMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

}
