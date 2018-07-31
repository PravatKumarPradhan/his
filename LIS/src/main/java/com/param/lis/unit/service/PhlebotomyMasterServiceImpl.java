package com.param.lis.unit.service;

import in.param.exception.ApplicationException;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.Response;
import com.param.lis.unit.dao.IPhlebotomyMasterDao;
import com.param.lis.unit.dto.PhlebotomyMasterDto;

@Service
@SuppressWarnings({"rawtypes","unchecked"})
public class PhlebotomyMasterServiceImpl implements IPhlebotomyMasterService,ICommonConstants, IError{

	@Autowired
	private IPhlebotomyMasterDao iPhlebotomyMasterDao;
	
	@Override
	@Transactional
	public Response addPhlebotomyMaster(PhlebotomyMasterDto phlebotomyMasterDto)throws ApplicationException {
		try{
			Response res = iPhlebotomyMasterDao.checkPhlebotomyCodeAvaiable(phlebotomyMasterDto);
			if(res.getListObject() != null && res.getListObject().size() > 0){
				return new Response<>(ERROR, ERR_500, PHLEBOTOMY_CODE_EXISIS, null, null);
			}else{
				long time = new Date().getTime();
				phlebotomyMasterDto.setCreatedDate(time);
				phlebotomyMasterDto.setUpdatedDate(time);
				iPhlebotomyMasterDao.addPhlebotomyMaster(phlebotomyMasterDto);
				return new Response<>(SUCCESS, SUCCESS_200, PHLEBOTOMY_ADD_SUCC, null, null);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERR_201, PHLEBOTOMY_ADD_FAIL, null, null);
	}

	@Override
	@Transactional
	public Response getPhlebotomyMasterById(Integer orgId, Integer unitId,Integer phlebotomyId)
			throws ApplicationException {
		try
		{
			return iPhlebotomyMasterDao.getPhlebotomyMasteById(orgId,unitId,phlebotomyId);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}


	@Override
	@Transactional
	public Response ActivateInactivatePhlebotomyMaster(Integer orgId,
			Integer phlebotomyId, Character phlebotomyStatus)
			throws ApplicationException {
		
		try
		{
			return iPhlebotomyMasterDao.ActivateInactivatePhlebotomyMaster(orgId,phlebotomyId, phlebotomyStatus);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response listPhlebotomyMaster(Integer orgId, Integer offset,
			Integer recordPerPage) throws ApplicationException {
		try
		{
			return iPhlebotomyMasterDao.listPhlebotomyMaster(orgId, offset, recordPerPage);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response getToTalPhlebotomyMasterRecord(Integer orgId)
			throws ApplicationException {
		try
		{
			return iPhlebotomyMasterDao.getToTalPhlebotomyMasterRecord(orgId);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response updatePhlebotomyMaster(PhlebotomyMasterDto phlebotomyMasterDto)
			throws ApplicationException {
		
		try{
			Response res = iPhlebotomyMasterDao.updateCheckPhlebotomyCodeAvaiable(phlebotomyMasterDto);
			if(res.getListObject() != null && res.getListObject().size() > 0){
				return new Response<>(ERROR, ERR_500,PHLEBOTOMY_CODE_EXISIS, null, null);
			}else{
				phlebotomyMasterDto.setUpdatedDate(new Date().getTime());
				Response ress = iPhlebotomyMasterDao.updatePhlebotomyMaster(phlebotomyMasterDto);
				if (ress.getStatus().equalsIgnoreCase(SUCCESS))
				{
					return new Response(SUCCESS, SUCCESS_200,PHLEBOTOMY_UPDATE_SUCC, null, null);
				} else
				{
					return new Response(ERROR, ERR_500,PHLEBOTOMY_UPDATE_FAIL, null, null);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERR_201,PHLEBOTOMY_ADD_FAIL, null, null);
		
	}


}
