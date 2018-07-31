package com.param.lis.global.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.param.exception.ApplicationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.Response;
import com.param.lis.global.dao.IOrganismGroupOrganismMapperMasterDao;
import com.param.lis.global.dto.OrganismGroupOrganismMapperMasterDto;
@Service
@SuppressWarnings({"rawtypes","unchecked"})
public class OrganismGroupOrganismMapperMasterServiceImpl implements IOrganismGroupOrganismMapperMasterService ,ICommonConstants, IError{

	@Autowired
	private IOrganismGroupOrganismMapperMasterDao iOrganismGroupOrganismMapperMasterDao;
	

	@Transactional
	@Override
	public Response addOrganismGroupOrganismMapperMaster(
			OrganismGroupOrganismMapperMasterDto mediaColonyMapperMasterDto)
			throws ApplicationException {
		try{	
			Response res = iOrganismGroupOrganismMapperMasterDao.checkOrganismGroupMapperAvaiable(mediaColonyMapperMasterDto);
			if(res.getListObject() != null && res.getListObject().size() > 0){
				return new Response<>(ERROR, ERR_500,ORGANIGROUP_ORGANI_MAPPER_CODE_EXISIS, null, null);
			}else{
			Iterator ite=mediaColonyMapperMasterDto.getSelecteOrgGroupMpprList().iterator(); 
			while (ite.hasNext()) {
				OrganismGroupOrganismMapperMasterDto organismGroupOrganismMapperMasterDto = new OrganismGroupOrganismMapperMasterDto();
				Integer organismId = (Integer) ite.next();
				long time = new Date().getTime();
				organismGroupOrganismMapperMasterDto.setCreatedDate(time);
				organismGroupOrganismMapperMasterDto.setOrganismGroupId(mediaColonyMapperMasterDto.getOrganismGroupId());
				organismGroupOrganismMapperMasterDto.setOrganismId(organismId);
				organismGroupOrganismMapperMasterDto.setStatus('A');
				organismGroupOrganismMapperMasterDto.setIsDeleted('N');
				organismGroupOrganismMapperMasterDto.setOrgId(mediaColonyMapperMasterDto.getOrgId());
				organismGroupOrganismMapperMasterDto.setCreatedBy(mediaColonyMapperMasterDto.getCreatedBy());
				iOrganismGroupOrganismMapperMasterDao
						.addOrganismGroupOrganismMapperMaster(organismGroupOrganismMapperMasterDto);
			}
			}
			return new Response<>(SUCCESS, SUCCESS_200,ORGANIGROUP_ORGANI_MAPPER_ADD_SUCC, null, null);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERR_201,ORGANIGROUP_ORGANI_MAPPER_ADD_FAIL, null, null);
	}


	@Override
	@Transactional
	public Response listOrganismGroupOrganismMapperMaster(Integer orgId,
			Integer offset, Integer recordPerPage) throws ApplicationException {
		try
		{
			Response res =  iOrganismGroupOrganismMapperMasterDao.listOrganismGroupOrganismMapperMaster(orgId, offset, recordPerPage);
			if(res.getListObject() != null && res.getListObject().size() > 0){
				List<OrganismGroupOrganismMapperMasterDto> OrganismGroupOrganismMapperMst = res.getListObject() != null ? (List<OrganismGroupOrganismMapperMasterDto>)res.getListObject() : null;
				if(OrganismGroupOrganismMapperMst.isEmpty())
				{
					return new Response(ERROR, ERR_500,ORGANIGROUP_ORGANI_MAPPER_CORDS_NOT_FOUND, null, null);
				}
				else{
					return new Response(SUCCESS, SUCCESS_200, null, OrganismGroupOrganismMapperMst, null);
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
	public Response getToTalOrganismGroupOrganismMapperMaster(Integer orgId)
			throws ApplicationException {
		try
		{
			return iOrganismGroupOrganismMapperMasterDao.getToTalOrganismGroupOrganismMapperMaster(orgId);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}


	@Override
	@Transactional
	public Response activateInactivateOrganismGroupOrganismMapperMaster(
			Integer orgId, Integer organismGroupId, Character status)
			throws ApplicationException {
		try
		{ 
		    return iOrganismGroupOrganismMapperMasterDao.activateInactivateOrganismGroupOrganismMapperMaster(orgId, organismGroupId, status);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}


	@Override
	@Transactional
	public Response updateOrganismGroupOrganismMapperMaster(
			OrganismGroupOrganismMapperMasterDto mediaColonyMapperMasterDto)
			throws ApplicationException {
		try{	
			
			List<OrganismGroupOrganismMapperMasterDto> listOrganismGroupOrganismMapperMasterDto = new ArrayList();
			Iterator ite=mediaColonyMapperMasterDto.getSelecteOrgGroupMpprList().iterator(); 
			while (ite.hasNext()) {
				OrganismGroupOrganismMapperMasterDto organismGroupOrganismMapperMasterDto = new OrganismGroupOrganismMapperMasterDto();
				Integer organismId = (Integer) ite.next();
				long time = new Date().getTime();
				organismGroupOrganismMapperMasterDto.setUpdatedDate(time);
				organismGroupOrganismMapperMasterDto.setOrganismId(organismId);
				organismGroupOrganismMapperMasterDto.setStatus(mediaColonyMapperMasterDto.getStatus());
				organismGroupOrganismMapperMasterDto.setOrgId(mediaColonyMapperMasterDto.getOrgId());
				organismGroupOrganismMapperMasterDto.setUpdatedBy(mediaColonyMapperMasterDto.getUpdatedBy());
				listOrganismGroupOrganismMapperMasterDto.add(organismGroupOrganismMapperMasterDto);
			}
			if(!listOrganismGroupOrganismMapperMasterDto.isEmpty())
			{
				return iOrganismGroupOrganismMapperMasterDao
				.updateOrganismGroupOrganismMapperMaster(listOrganismGroupOrganismMapperMasterDto);
			}
			else {
				return new Response<>(ERROR, ERR_201,ORGANIGROUP_ORGANI_MAPPER_ADD_FAIL, null, null);
			}
			
		}catch(Exception e){
			e.printStackTrace();
			return new Response<>(ERROR, ERR_201,ORGANIGROUP_ORGANI_MAPPER_ADD_FAIL, null, null);
		}
		
		
	}


	@Override
	@Transactional
	public Response getOrganismGroupOrganismMapperMasterById(Integer orgId,
			Integer organismGroupId) throws ApplicationException {
		try
		{
			return iOrganismGroupOrganismMapperMasterDao.getOrganismGroupOrganismMapperMasterById(orgId, organismGroupId);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}


	@Override
	@Transactional
	public Response getOrganismGroupOrganismClassById(Integer organismGroupId)
			throws ApplicationException {
		try
		{
			return iOrganismGroupOrganismMapperMasterDao.getOrganismGroupMasterClassById(organismGroupId);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}


}
