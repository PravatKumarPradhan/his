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
import com.param.lis.global.dao.IAntibioticOrganismMpprMasterDao;
import com.param.lis.global.dto.AntibioticOrganismMpprMasterDto;
@Service
@SuppressWarnings({"rawtypes","unchecked"})
public class AntibioticOrganismMpprMasterServiceImpl implements IAntibioticOrganismMpprMasterService ,ICommonConstants, IError{

	@Autowired
	private IAntibioticOrganismMpprMasterDao iAntibioticOrganismMpprMasterDao;
	

	@Transactional
	@Override
	public Response addAntibioticOrganismMpprMaster(
			AntibioticOrganismMpprMasterDto antibioticAdditionMasterDto)
			throws ApplicationException {
		try{	
			Response res = iAntibioticOrganismMpprMasterDao.checkAntibioticOrganismMpprMasterAvaiable(antibioticAdditionMasterDto);
			if(res.getListObject() != null && res.getListObject().size() > 0){
				return new Response<>(ERROR, ERR_500, ANTIBIOTICS_ORGANISM_CODE_EXISIS, null, null);
			}else{
			Iterator ite=antibioticAdditionMasterDto.getSelectAntiboiticOrganismMppList().iterator(); 
			while (ite.hasNext()) {
				AntibioticOrganismMpprMasterDto antibioticAdditionDto = new AntibioticOrganismMpprMasterDto();
				Integer antiboiticId = (Integer) ite.next();
				long time = new Date().getTime();
				antibioticAdditionDto.setCreatedDate(time);
				antibioticAdditionDto.setOrganismId(antibioticAdditionMasterDto.getOrganismId());
				antibioticAdditionDto.setAntiboiticId(antiboiticId);
				antibioticAdditionDto.setStatus(antibioticAdditionMasterDto.getStatus());
				antibioticAdditionDto.setOrgId(antibioticAdditionMasterDto.getOrgId());
				antibioticAdditionDto.setCreatedBy(antibioticAdditionMasterDto.getCreatedBy());
				iAntibioticOrganismMpprMasterDao
						.addAntibioticOrganismMpprMaster(antibioticAdditionDto);
			}
			}
			return new Response<>(SUCCESS, SUCCESS_200,ANTIBIOTICS_ORGANISM_ADD_SUCC, null, null);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERR_201,ANTIBIOTICS_ORGANISM_ADD_FAIL, null, null);
	}


	@Override
	@Transactional
	public Response listAntibioticOrganismMpprMaster(Integer orgId,
			Integer offset, Integer recordPerPage) throws ApplicationException {
		try
		{
			Response res =  iAntibioticOrganismMpprMasterDao.listAntibioticOrganismMpprMaster(orgId, offset, recordPerPage);
			if(res.getListObject() != null && res.getListObject().size() > 0){
				List<AntibioticOrganismMpprMasterDto> antibioticAddtionClassMst = res.getListObject() != null ? (List<AntibioticOrganismMpprMasterDto>)res.getListObject() : null;
				if(antibioticAddtionClassMst.isEmpty())
				{
					return new Response(ERROR, ERR_500, ANTIBIOTICS_ORGANISM_CORDS_NOT_FOUND, null, null);
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
	public Response getToTalAntibioticOrganismMpprMaster(Integer orgId)
			throws ApplicationException {
		try
		{
			return iAntibioticOrganismMpprMasterDao.getToTalAntibioticOrganismMpprMaster(orgId);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}


	@Override
	@Transactional
	public Response activateInactivateAntibioticOrganismMpprMaster(
			Integer orgId, Integer organismId, Character status)
			throws ApplicationException {
		try
		{ 
		    return iAntibioticOrganismMpprMasterDao.activateInactivateAntibioticOrganismMpprMaster(orgId, organismId, status);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}


	@Override
	@Transactional
	public Response updateAntibioticOrganismMpprMaster(
			AntibioticOrganismMpprMasterDto antibioticAdditionMasterDto)
			throws ApplicationException {
		try{	
			
			List<AntibioticOrganismMpprMasterDto> listAntibioticOrganismMpprMasterDto = new ArrayList();
			Iterator ite=antibioticAdditionMasterDto.getSelectAntiboiticOrganismMppList().iterator(); 
			while (ite.hasNext()) {
				AntibioticOrganismMpprMasterDto antibioticOrganismMpprMasterDto = new AntibioticOrganismMpprMasterDto();
				Integer antiboiticId = (Integer) ite.next();
				long time = new Date().getTime();
				antibioticOrganismMpprMasterDto.setUpdatedDate(time);
				antibioticOrganismMpprMasterDto.setOrganismId(antibioticAdditionMasterDto.getOrganismId());
				antibioticOrganismMpprMasterDto.setAntiboiticId(antiboiticId);
				antibioticOrganismMpprMasterDto.setStatus(antibioticAdditionMasterDto.getStatus());
				antibioticOrganismMpprMasterDto.setOrgId(antibioticAdditionMasterDto.getOrgId());
				antibioticOrganismMpprMasterDto.setUpdatedBy(antibioticAdditionMasterDto.getUpdatedBy());
				listAntibioticOrganismMpprMasterDto.add(antibioticOrganismMpprMasterDto);
			}
			if(!listAntibioticOrganismMpprMasterDto.isEmpty())
			{
				return iAntibioticOrganismMpprMasterDao
				.updateAntibioticOrganismMpprMaster(listAntibioticOrganismMpprMasterDto);
			}
			else {
				return new Response<>(ERROR, ERR_201,ANTIBIOTICS_ORGANISM_ADD_FAIL, null, null);
			}
			
		}catch(Exception e){
			e.printStackTrace();
			return new Response<>(ERROR, ERR_201,ANTIBIOTICS_ORGANISM_ADD_FAIL, null, null);
		}
		
		
	}


	@Override
	@Transactional
	public Response getAntibioticOrganismMpprMasterById(Integer orgId,
			Integer antiboiticClassId) throws ApplicationException {
		try
		{
			return iAntibioticOrganismMpprMasterDao.getAntibioticOrganismMpprMasterById(orgId, antiboiticClassId);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}


	@Override
	@Transactional
	public Response getAntibioticByOrganismId(Integer organismId)
			throws ApplicationException {
		try
		{
			return iAntibioticOrganismMpprMasterDao.getAntibioticByOrganismId(organismId);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}


}
