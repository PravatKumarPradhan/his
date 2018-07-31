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
import com.param.lis.global.dao.IAntibioticGroupAddtionMasterDao;
import com.param.lis.global.dto.AntibioticGroupAdditionMasterDto;
@Service
@SuppressWarnings({"rawtypes","unchecked"})
public class AntibioticGroupAddtionMasterServiceImpl implements IAntibioticGroupAddtionMasterService ,ICommonConstants, IError{

	@Autowired
	private IAntibioticGroupAddtionMasterDao iAntibioticGroupAddtionMasterDao;
	

	@Transactional
	@Override
	public Response addAntibioticGroupAddtionMaster(
			AntibioticGroupAdditionMasterDto antibioticGroupAdditionMasterDto)
			throws ApplicationException {
		try{	
			Response res = iAntibioticGroupAddtionMasterDao.checkAntibioticGroupAvaiable(antibioticGroupAdditionMasterDto);
			if(res.getListObject() != null && res.getListObject().size() > 0){
				return new Response<>(ERROR, ERR_500, ANTIBIOTICS_ADDITION_CODE_EXISIS, null, null);
			}else{
			Iterator ite=antibioticGroupAdditionMasterDto.getSelectAntiboiticGroupList().iterator(); 
			while (ite.hasNext()) {
				AntibioticGroupAdditionMasterDto antibioticGroupAdditionMaster = new AntibioticGroupAdditionMasterDto();
				Integer antiboiticId = (Integer) ite.next();
				long time = new Date().getTime();
				antibioticGroupAdditionMaster.setCreatedDate(time);
				antibioticGroupAdditionMaster.setAntiboiticGroupId(antibioticGroupAdditionMasterDto.getAntiboiticGroupId());
				antibioticGroupAdditionMaster.setAntiboiticId(antiboiticId);
				antibioticGroupAdditionMaster.setStatus(antibioticGroupAdditionMasterDto.getStatus());
				antibioticGroupAdditionMaster.setOrgId(antibioticGroupAdditionMasterDto.getOrgId());
				antibioticGroupAdditionMaster.setCreatedBy(antibioticGroupAdditionMasterDto.getCreatedBy());
				antibioticGroupAdditionMaster.setIsDeleted('N');
				iAntibioticGroupAddtionMasterDao
						.addAntibioticGroupAddtionMaster(antibioticGroupAdditionMaster);
			}
			}
			return new Response<>(SUCCESS, SUCCESS_200,ANTIBIOTICS_ADDITION_ADD_SUCC, null, null);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERR_201,ANTIBIOTICS_ADDITION_ADD_FAIL, null, null);
	}


	@Override
	@Transactional
	public Response listAntibioticGroupAddtionMaster(Integer orgId,
			Integer offset, Integer recordPerPage) throws ApplicationException {
		try
		{
			Response res =  iAntibioticGroupAddtionMasterDao.listAntibioticGroupAddtionMaster(orgId, offset, recordPerPage);
			if(res.getListObject() != null && res.getListObject().size() > 0){
				List<AntibioticGroupAdditionMasterDto> antibioticGroupAdditionMst = res.getListObject() != null ? (List<AntibioticGroupAdditionMasterDto>)res.getListObject() : null;
				if(antibioticGroupAdditionMst== null)
				{
					return new Response(ERROR, ERR_500, ANTIBIOTICS_ADDITION_CORDS_NOT_FOUND, null, null);
				}
				else{
					return new Response(SUCCESS, SUCCESS_200, null, antibioticGroupAdditionMst, null);
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
	public Response getToTalAntibioticGroupAddtionMaster(Integer orgId)
			throws ApplicationException {
		try
		{
			return iAntibioticGroupAddtionMasterDao.getToTalAntibioticGroupAddtionMaster(orgId);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}


	@Override
	@Transactional
	public Response activateInactivateAntibioticGroupAddtionMaster(
			Integer orgId, Integer antiboiticGroupId, Character status)
			throws ApplicationException {
		try
		{ 
		    return iAntibioticGroupAddtionMasterDao.activateInactivateAntibioticGroupAddtionMaster(orgId, antiboiticGroupId, status);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}


	@Override
	@Transactional
	public Response updateAntibioticGroupAddtionMaster(
			AntibioticGroupAdditionMasterDto antibioticGroupAdditionMasterDto)
			throws ApplicationException {
		try{	
			
			List<AntibioticGroupAdditionMasterDto> listantibioticGroupAdditionMasterDto = new ArrayList();
			Iterator ite=antibioticGroupAdditionMasterDto.getSelectAntiboiticGroupList().iterator(); 
			while (ite.hasNext()) {
				AntibioticGroupAdditionMasterDto antibioticAdditionDto = new AntibioticGroupAdditionMasterDto();
				Integer antiboiticId = (Integer) ite.next();
				long time = new Date().getTime();
				antibioticAdditionDto.setUpdatedDate(time);
				antibioticAdditionDto.setAntiboiticGroupId(antibioticGroupAdditionMasterDto.getAntiboiticGroupId());
				antibioticAdditionDto.setAntiboiticId(antiboiticId);
				antibioticAdditionDto.setStatus(antibioticGroupAdditionMasterDto.getStatus());
				antibioticAdditionDto.setOrgId(antibioticGroupAdditionMasterDto.getOrgId());
				antibioticAdditionDto.setUpdatedBy(antibioticGroupAdditionMasterDto.getUpdatedBy());
				listantibioticGroupAdditionMasterDto.add(antibioticAdditionDto);
			}
			if(!listantibioticGroupAdditionMasterDto.isEmpty())
			{
				return iAntibioticGroupAddtionMasterDao
				.updateAntibioticGroupAddtionMaster(listantibioticGroupAdditionMasterDto);
			}
			else {
				return new Response<>(ERROR, ERR_201,ANTIBIOTICS_GROUPADD_FAIL, null, null);
			}
			
		}catch(Exception e){
			e.printStackTrace();
			return new Response<>(ERROR, ERR_201,ANTIBIOTICS_GROUPADD_FAIL, null, null);
		}
		
		
	}


	@Override
	@Transactional
	public Response getAntibioticGroupAddtionMasterById(Integer orgId,
			Integer antiboiticGroupId) throws ApplicationException {
		try
		{
			return iAntibioticGroupAddtionMasterDao.getAntibioticGroupAddtionMasterById(orgId, antiboiticGroupId);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}


	@Override
	@Transactional
	public Response getAntibioticGroupMasterClassById(Integer antibioticGroupId)
			throws ApplicationException {
		try
		{
			return iAntibioticGroupAddtionMasterDao.getAntibioticGroupClassById(antibioticGroupId);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}


}
