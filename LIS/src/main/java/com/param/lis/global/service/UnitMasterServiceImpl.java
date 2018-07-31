package com.param.lis.global.service;

import in.param.exception.ApplicationException;

import java.util.Date;
import java.util.List;

import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.param.entity.lis.global.LabUnitMaster;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.Response;
import com.param.lis.global.dao.IMediaMasterDao;
import com.param.lis.global.dao.IUnitMasterDao;
import com.param.lis.global.dto.GlobalCommonDto;
import com.param.lis.global.dto.MediaMasterDto;
import com.param.lis.global.dto.UnitMasterDto;

@Service
@SuppressWarnings({"rawtypes","unchecked"})
public class UnitMasterServiceImpl implements IUnitMasterService,ICommonConstants, IError{

	@Autowired
	private IUnitMasterDao iUnitMasterDao;
	
	@Override
	@Transactional
	public Response addUnitMaster(UnitMasterDto unitMasterDto)throws ApplicationException {
		try{
			Response res = iUnitMasterDao.checkUnitCodeAvaiable(unitMasterDto);
			if(res.getListObject() != null && res.getListObject().size() > 0){
				return new Response<>(ERROR, ERR_500, UNIT_CODE_EXISIS, null, null);
			}else{
				long time = new Date().getTime();
				unitMasterDto.setCreatedDate(time);
				unitMasterDto.setUpdatedDate(time);
				iUnitMasterDao.addUnitMaster(unitMasterDto);
				return new Response<>(SUCCESS, SUCCESS_200, UNIT_ADD_SUCC, null, null);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERR_201, UNIT_ADD_FAIL, null, null);
	}

	@Override
	@Transactional
	public Response getUnitMasterById(Integer orgId, Integer mediaId)
			throws ApplicationException {
		try
		{
			return iUnitMasterDao.getUnitMasterById(orgId, mediaId);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}


	@Override
	@Transactional
	public Response ActivateInactivateUnitMaster(Integer orgId,
			Integer mediaId, Character unitStatus)
			throws ApplicationException {
		try
		{
			Response resp= iUnitMasterDao.ActivateInactivateUnitMaster(orgId, mediaId, unitStatus);
			LabUnitMaster unitMst = resp.getObject() != null ? (LabUnitMaster)resp.getObject() : null;
			if(unitMst != null){
				if (unitMst.getStatus() == ACTIVE)
				{
					return new Response(SUCCESS, SUCCESS_200, UNIT_ACTIVATE_SUCC, null, null);
				} else if (unitMst.getStatus()== INACTIVE)
				{
					return new Response(SUCCESS, SUCCESS_200, UNIT_INACTIVATE_SUCC, null, null);
				} else
				{
					if (unitStatus == ACTIVE)
					{
						return new Response(SUCCESS, SUCCESS_200, UNIT_ACTIVATE_FAIL, null, null);
					} else
					{
						return new Response(SUCCESS, SUCCESS_200, UNIT_INACTIVATE_FAIL, null, null);
					}
				}
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		return null;
	}

	@Override
	@Transactional
	public Response listUnitMaster(Integer orgId, Integer offset,
			Integer recordPerPage) throws ApplicationException {
		try
		{
			Response res =  iUnitMasterDao.listUnitMaster(orgId, offset, recordPerPage);
			if(res.getListObject() != null && res.getListObject().size() > 0){
				List<GlobalCommonDto> unitMst = res.getListObject() != null ? (List<GlobalCommonDto>)res.getListObject() : null;
				if(unitMst.isEmpty())
				{
					return new Response(ERROR, ERR_500, SAMPLE_RECORDS_NOT_FOUND, null, null);
				}
				else{
					return new Response(SUCCESS, SUCCESS_200, null, unitMst, null);
				}
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	}

	@Override
	@Transactional
	public Response getToTalUnitMasterRecord(Integer orgId)
			throws ApplicationException {
		try
		{
			return iUnitMasterDao.getToTalUnitMasterRecord(orgId);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response updateUnitMaster(UnitMasterDto unitMasterDto)
			throws ApplicationException {
		
		try{
			Response res = iUnitMasterDao.updateCheckUnitCodeAvaiable(unitMasterDto);
			if(res.getListObject() != null && res.getListObject().size() > 0){
				return new Response<>(ERROR, ERR_500,UNIT_CODE_EXISIS, null, null);
			}else{
				unitMasterDto.setUpdatedDate(new Date().getTime());
				Response resdata =  iUnitMasterDao.updateUnitMaster(unitMasterDto);
				if (resdata.getStatus().equalsIgnoreCase(SUCCESS))
				{
					return new Response(SUCCESS, SUCCESS_200, UNIT_UPDATE_SUCC, null, null);
				} else
				{
					return new Response(ERROR, ERR_500, UNIT_UPDATE_FAIL, null, null);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	}

	

}
