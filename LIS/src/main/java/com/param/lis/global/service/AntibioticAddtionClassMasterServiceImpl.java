package com.param.lis.global.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.param.exception.ApplicationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.param.entity.lis.global.AgeGroupMaster;
import com.param.entity.lis.global.AntibioticAdditionMaster;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.Response;
import com.param.lis.global.dao.IAntibioticAddtionClassMasterDao;
import com.param.lis.global.dto.AntibioticAdditionMasterDto;
@Service
@SuppressWarnings({"rawtypes","unchecked"})
public class AntibioticAddtionClassMasterServiceImpl implements IAntibioticAddtionClassMasterService ,ICommonConstants, IError{

	@Autowired
	private IAntibioticAddtionClassMasterDao iAntibioticAddtionClassMasterDao;
	

	@Transactional
	@Override
	public Response addAntibioticAddtionClassMaster(
			AntibioticAdditionMasterDto antibioticAdditionMasterDto)
			throws ApplicationException {
		try{	
			Response res = iAntibioticAddtionClassMasterDao.checkAntibioticAddtionClassAvaiable(antibioticAdditionMasterDto);
			if(res.getListObject() != null && res.getListObject().size() > 0){
				return new Response<>(ERROR, ERR_500, ANTIBIOTICS_ADDITION_CODE_EXISIS, null, null);
			}else{
			Iterator ite=antibioticAdditionMasterDto.getSelectAntibioticClassList().iterator(); 
			while (ite.hasNext()) {
				AntibioticAdditionMasterDto antibioticAdditionDto = new AntibioticAdditionMasterDto();
				Integer antiboiticId = (Integer) ite.next();
				long time = new Date().getTime();
				antibioticAdditionDto.setCreatedDate(time);
				antibioticAdditionDto.setAntiboiticClassId(antibioticAdditionMasterDto.getAntiboiticClassId());
				antibioticAdditionDto.setAntiboiticId(antiboiticId);
				antibioticAdditionDto.setStatus(antibioticAdditionMasterDto.getStatus());
				antibioticAdditionDto.setOrgId(antibioticAdditionMasterDto.getOrgId());
				antibioticAdditionDto.setCreatedBy(antibioticAdditionMasterDto.getCreatedBy());
				iAntibioticAddtionClassMasterDao
						.addAntibioticAddtionClassMaster(antibioticAdditionDto);
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
	public Response listAntibioticAddtionClassMaster(Integer orgId,
			Integer offset, Integer recordPerPage) throws ApplicationException {
		try
		{
			Response res =  iAntibioticAddtionClassMasterDao.listAntibioticAddtionClassMaster(orgId, offset, recordPerPage);
			if(res.getListObject() != null && res.getListObject().size() > 0){
				List<AntibioticAdditionMasterDto> antibioticAddtionClassMst = res.getListObject() != null ? (List<AntibioticAdditionMasterDto>)res.getListObject() : null;
				if(antibioticAddtionClassMst.isEmpty())
				{
					return new Response(ERROR, ERR_500, ANTIBIOTICS_ADDITION_CORDS_NOT_FOUND, null, null);
				}
				else{
					return new Response(SUCCESS, SUCCESS_200, null, antibioticAddtionClassMst, null);
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
	public Response getToTalAntibioticAddtionClassMaster(Integer orgId)
			throws ApplicationException {
		try
		{
			return iAntibioticAddtionClassMasterDao.getToTalAntibioticAddtionClassMaster(orgId);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}


	@Override
	@Transactional
	public Response activateInactivateAntibioticAddtionClassMaster(
			Integer orgId, Integer antiboiticClassId, Character status)
			throws ApplicationException {
		try
		{ 
		    return iAntibioticAddtionClassMasterDao.activateInactivateAntibioticAddtionClassMaster(orgId, antiboiticClassId, status);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}


	@Override
	@Transactional
	public Response updateAntibioticAddtionClassMaster(
			AntibioticAdditionMasterDto antibioticAdditionMasterDto)
			throws ApplicationException {
		try{	
			
			List<AntibioticAdditionMasterDto> listAntibioticAdditionMasterDto = new ArrayList();
			Iterator ite=antibioticAdditionMasterDto.getSelectAntibioticClassList().iterator(); 
			while (ite.hasNext()) {
				AntibioticAdditionMasterDto antibioticAdditionDto = new AntibioticAdditionMasterDto();
				Integer antiboiticId = (Integer) ite.next();
				long time = new Date().getTime();
				antibioticAdditionDto.setUpdatedDate(time);
				antibioticAdditionDto.setAntiboiticClassId(antibioticAdditionMasterDto.getAntiboiticClassId());
				antibioticAdditionDto.setAntiboiticId(antiboiticId);
				antibioticAdditionDto.setStatus(antibioticAdditionMasterDto.getStatus());
				antibioticAdditionDto.setOrgId(antibioticAdditionMasterDto.getOrgId());
				antibioticAdditionDto.setUpdatedBy(antibioticAdditionMasterDto.getUpdatedBy());
				listAntibioticAdditionMasterDto.add(antibioticAdditionDto);
			}
			if(!listAntibioticAdditionMasterDto.isEmpty())
			{
				return iAntibioticAddtionClassMasterDao
				.updateAntibioticAddtionClassMaster(listAntibioticAdditionMasterDto);
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
	public Response getAntibioticAddtionClassMasterById(Integer orgId,
			Integer antiboiticClassId) throws ApplicationException {
		try
		{
			return iAntibioticAddtionClassMasterDao.getAntibioticAddtionClassMasterById(orgId, antiboiticClassId);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}


	@Override
	@Transactional
	public Response getAntibioticMasterClassById(Integer antiboiticClassId)
			throws ApplicationException {
		try
		{
			return iAntibioticAddtionClassMasterDao.getAntibioticMasterClassById(antiboiticClassId);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}


}
