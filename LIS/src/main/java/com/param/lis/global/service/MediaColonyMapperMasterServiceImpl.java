package com.param.lis.global.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.param.exception.ApplicationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.param.lis.common.dto.CheckTranSactionExistsDto;
import com.param.lis.common.dto.CommonDto;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.Response;
import com.param.lis.global.dao.IMediaColonyMapperMasterDao;
import com.param.lis.global.dto.MediaColonyMapperMasterDto;
@Service
@SuppressWarnings({"rawtypes","unchecked"})
public class MediaColonyMapperMasterServiceImpl implements IMediaColonyMapperMasterService ,ICommonConstants, IError{

	@Autowired
	private IMediaColonyMapperMasterDao iMediaColonyMapperMasterDao;
	

	@Transactional
	@Override
	public Response addMediaColonyMapperMaster(
			MediaColonyMapperMasterDto mediaColonyMapperMasterDto)
			throws ApplicationException {
		try{	
			Response res = iMediaColonyMapperMasterDao.checkMediaColonyMapperAvaiable(mediaColonyMapperMasterDto);
			if(res.getListObject() != null && res.getListObject().size() > 0){
				return new Response<>(ERROR, ERR_500, MEDIA_COLONY_MAPPER_CODE_EXISIS, null, null);
			}else{
			Iterator ite=mediaColonyMapperMasterDto.getSelectediaColonyMpprList().iterator(); 
			while (ite.hasNext()) {
				MediaColonyMapperMasterDto mediaColonyMapperMstDto = new MediaColonyMapperMasterDto();
				Integer colonyId = (Integer) ite.next();
				long time = new Date().getTime();
				mediaColonyMapperMstDto.setCreatedDate(time);
				mediaColonyMapperMstDto.setMediaId(mediaColonyMapperMasterDto.getMediaId());
				mediaColonyMapperMstDto.setColonyId(colonyId);
				mediaColonyMapperMstDto.setStatus('A');
				mediaColonyMapperMstDto.setIsDeleted('N');
				mediaColonyMapperMstDto.setOrgId(mediaColonyMapperMasterDto.getOrgId());
				mediaColonyMapperMstDto.setCreatedBy(mediaColonyMapperMasterDto.getCreatedBy());
				iMediaColonyMapperMasterDao
						.addMediaColonyMapperMaster(mediaColonyMapperMstDto);
			}
			}
			return new Response<>(SUCCESS, SUCCESS_200,MEDIA_COLONY_MAPPER_ADD_SUCC, null, null);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERR_201,MEDIA_COLONY_MAPPER_ADD_FAIL, null, null);
	}


	@Override
	@Transactional
	public Response listMediaColonyMapperMaster(Integer orgId,
			Integer offset, Integer recordPerPage) throws ApplicationException {
		try
		{
			Response res =  iMediaColonyMapperMasterDao.listMediaColonyMapperMaster(orgId, offset, recordPerPage);
			if(res.getListObject() != null && res.getListObject().size() > 0){
				List<MediaColonyMapperMasterDto> mediaColonyMapperMst = res.getListObject() != null ? (List<MediaColonyMapperMasterDto>)res.getListObject() : null;
				if(mediaColonyMapperMst.isEmpty())
				{
					return new Response(ERROR, ERR_500, MEDIA_COLONY_MAPPER_CORDS_NOT_FOUND, null, null);
				}
				else{
					return new Response(SUCCESS, SUCCESS_200, null, mediaColonyMapperMst, null);
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
	public Response getToTalMediaColonyMapperMaster(Integer orgId)
			throws ApplicationException {
		try
		{
			return iMediaColonyMapperMasterDao.getToTalMediaColonyMapperMaster(orgId);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}


	@Override
	@Transactional
	public Response activateInactivateMediaColonyMapperMaster(
			Integer orgId, Integer mediaId, Character status)
			throws ApplicationException {
		try
		{ 
		    return iMediaColonyMapperMasterDao.activateInactivateMediaColonyMapperMaster(orgId, mediaId, status);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}


	@Override
	@Transactional
	public Response updateMediaColonyMapperMaster(
			MediaColonyMapperMasterDto mediaColonyMapperMasterDto)
			throws ApplicationException {
		try{	
			
			List<MediaColonyMapperMasterDto> listMediaColonyMapperMasterDto = new ArrayList();
			Iterator ite=mediaColonyMapperMasterDto.getSelectediaColonyMpprList().iterator(); 
			while (ite.hasNext()) {
				MediaColonyMapperMasterDto mediaColonyMapperDto = new MediaColonyMapperMasterDto();
				Integer colonyId = (Integer) ite.next();
				long time = new Date().getTime();
				mediaColonyMapperDto.setUpdatedDate(time);
				mediaColonyMapperDto.setMediaId(mediaColonyMapperMasterDto.getMediaId());
				mediaColonyMapperDto.setColonyId(colonyId);;
				mediaColonyMapperDto.setStatus(mediaColonyMapperMasterDto.getStatus());
				mediaColonyMapperDto.setOrgId(mediaColonyMapperMasterDto.getOrgId());
				mediaColonyMapperDto.setUpdatedBy(mediaColonyMapperMasterDto.getUpdatedBy());
				listMediaColonyMapperMasterDto.add(mediaColonyMapperDto);
			}
			if(!listMediaColonyMapperMasterDto.isEmpty())
			{
				return iMediaColonyMapperMasterDao
				.updateMediaColonyMapperMaster(listMediaColonyMapperMasterDto);
			}
			else {
				return new Response<>(ERROR, ERR_201,MEDIA_COLONY_MAPPER_ADD_FAIL, null, null);
			}
			
		}catch(Exception e){
			e.printStackTrace();
			return new Response<>(ERROR, ERR_201,MEDIA_COLONY_MAPPER_ADD_FAIL, null, null);
		}
		
		
	}


	@Override
	@Transactional
	public Response getMediaColonyMapperMasterById(Integer orgId,
			Integer mediaId) throws ApplicationException {
		try
		{
			return iMediaColonyMapperMasterDao.getMediaColonyMapperMasterById(orgId, mediaId);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}


	@Override
	@Transactional
	public Response getMediaMasterClassById(Integer mediaId)
			throws ApplicationException {
		try
		{
			return iMediaColonyMapperMasterDao.getMediaMasterClassById(mediaId);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}


	@Override
	@Transactional
	public Response getToTalMediaRecordCount(CheckTranSactionExistsDto checkTranSactionExistsDto)
			throws ApplicationException {
		try
		{
			return iMediaColonyMapperMasterDao.getToTalMediaRecordCount(checkTranSactionExistsDto);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}


}
