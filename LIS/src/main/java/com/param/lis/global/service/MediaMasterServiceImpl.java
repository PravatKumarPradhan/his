package com.param.lis.global.service;

import in.param.exception.ApplicationException;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.Response;
import com.param.lis.global.dao.IMediaMasterDao;
import com.param.lis.global.dto.MediaMasterDto;

@Service
@SuppressWarnings({"rawtypes","unchecked"})
public class MediaMasterServiceImpl implements IMediaMasterService,ICommonConstants, IError{

	@Autowired
	private IMediaMasterDao iMediaMasterDao;
	
	@Override
	@Transactional
	public Response saveMediaMaster(MediaMasterDto mediaMasterDto)throws ApplicationException {
		try{
			Response res = iMediaMasterDao.checkMediaCodeAvaiable(mediaMasterDto);
			if(res.getListObject() != null && res.getListObject().size() > 0){
				return new Response<>(ERROR, ERR_500, MEDIA_CODE_EXISIS, null, null);
			}else{
				long time = new Date().getTime();
				mediaMasterDto.setCreatedDate(time);
				mediaMasterDto.setUpdatedDate(time);
				iMediaMasterDao.saveMediaMaster(mediaMasterDto);
				return new Response<>(SUCCESS, SUCCESS_200, MEDIA_ADD_SUCC, null, null);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERR_201, MEDIA_ADD_FAIL, null, null);
	}

	@Override
	@Transactional
	public Response getMediaMasterById(Integer orgId, Integer mediaId)
			throws ApplicationException {
		try
		{
			return iMediaMasterDao.getMediaMasterById(orgId, mediaId);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}


	@Override
	@Transactional
	public Response ActivateInactivateMediaMaster(Integer orgId,
			Integer mediaId, Character mediaStatus)
			throws ApplicationException {
		
		try
		{
			return iMediaMasterDao.ActivateInactivateMediaMaster(orgId, mediaId, mediaStatus);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response listMediaMaster(Integer orgId, Integer offset,
			Integer recordPerPage) throws ApplicationException {
		try
		{
			return iMediaMasterDao.listMediaMaster(orgId, offset, recordPerPage);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response getToTalMediaMasterRecord(Integer orgId)
			throws ApplicationException {
		try
		{
			return iMediaMasterDao.getToTalMediaMasterRecord(orgId);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response updateMediaMaster(MediaMasterDto mediaMasterDto)
			throws ApplicationException {
		
		
		try{
			Response res = iMediaMasterDao.updateCheckMediaCodeAvaiable(mediaMasterDto);
			if(res.getListObject() != null && res.getListObject().size() > 0){
				return new Response<>(ERROR, ERR_500,MEDIA_CODE_EXISIS, null, null);
			}else{
				mediaMasterDto.setUpdatedDate(new Date().getTime());
				Response resdata =  iMediaMasterDao.updateMediaMaster(mediaMasterDto);
				if (resdata.getStatus().equalsIgnoreCase(SUCCESS))
				{
					return new Response(SUCCESS, SUCCESS_200,MEDIA_UPDATE_SUCC, null, null);
				} else
				{
					return new Response(ERROR, ERR_500,MEDIA_UPDATE_FAIL, null, null);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERR_201,MEDIA_UPDATE_FAIL, null, null);
		
	}


}
