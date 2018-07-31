package com.param.lis.global.service;

import in.param.exception.ApplicationException;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.param.entity.lis.global.SampleTypeMediaMappingMaster;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.Response;
import com.param.lis.global.dao.ISampleTypeMediaMappingMasterDao;
import com.param.lis.global.dto.AntibioticGroupAdditionMasterDto;
import com.param.lis.global.dto.SampleTypeMediaMappingMasterDto;

@Service
@SuppressWarnings({"rawtypes","unchecked"})
public class SampleTypeMediaMappingMasterServiceImpl implements ISampleTypeMediaMappingMasterService,ICommonConstants, IError{

	@Autowired
	private ISampleTypeMediaMappingMasterDao iSampleTypeMediaMappingMasterDao;
	
	@Override
	@Transactional
	public Response addSampleTypeMediaMappingMaster(SampleTypeMediaMappingMasterDto sampleTypeMediaMappingMasterDto)throws ApplicationException {
		
		try{	
			Response res = iSampleTypeMediaMappingMasterDao.checkSampleTypeMediaMappingMaster(sampleTypeMediaMappingMasterDto);
			if(res.getListObject() != null && res.getListObject().size() > 0){
				return new Response<>(ERROR, ERR_500, SAMPLE_TYPE_CODE_EXISIS, null, null);
			}else{
			Iterator ite=sampleTypeMediaMappingMasterDto.getSelectediaampleMediaMpprList().iterator(); 
			while (ite.hasNext()) {
				SampleTypeMediaMappingMasterDto sampleTypeMediaMappingMaster = new SampleTypeMediaMappingMasterDto();
				Integer mediaId = (Integer) ite.next();
				long time = new Date().getTime();
				sampleTypeMediaMappingMaster.setCreatedDate(time);
				sampleTypeMediaMappingMasterDto.setUpdatedDate(time);
				sampleTypeMediaMappingMaster.setSampleId(sampleTypeMediaMappingMasterDto.getSampleId());
				sampleTypeMediaMappingMaster.setMediaId(mediaId);
				
				sampleTypeMediaMappingMaster.setSampleMediaStatus(sampleTypeMediaMappingMasterDto.getSampleMediaStatus());
				sampleTypeMediaMappingMaster.setOrgId(sampleTypeMediaMappingMasterDto.getOrgId());
				sampleTypeMediaMappingMaster.setCreatedBy(sampleTypeMediaMappingMasterDto.getCreatedBy());
				sampleTypeMediaMappingMaster.setIsDeleted('N');
				iSampleTypeMediaMappingMasterDao.addSampleTypeMediaMappingMaster(sampleTypeMediaMappingMaster);
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
	public Response getSampleTypeMediaMappingMasterById(Integer orgId, Integer reagentId)
			throws ApplicationException {
		try
		{
			return iSampleTypeMediaMappingMasterDao.getSampleTypeMediaMappingMasterById(orgId, reagentId);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}


	@Override
	@Transactional
	public Response ActivateInactivateSampleTypeMediaMappingMaster(Integer orgId,
			Integer reagentId, Character reagentStatus)
			throws ApplicationException {
		
		try
		{ 
		    return iSampleTypeMediaMappingMasterDao.ActivateInactivateSampleTypeMediaMappingMaster(orgId, reagentId, reagentStatus);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response listSampleTypeMediaMappingMaster(Integer orgId, Integer offset,
			Integer recordPerPage) throws ApplicationException {
		try
		{
			Response res =  iSampleTypeMediaMappingMasterDao.listSampleTypeMediaMappingMaster(orgId, offset, recordPerPage);
			if(res.getListObject() != null && res.getListObject().size() > 0){
				List<SampleTypeMediaMappingMasterDto> unitMst = res.getListObject() != null ? (List<SampleTypeMediaMappingMasterDto>)res.getListObject() : null;
				if(unitMst.isEmpty())
				{
					return new Response(ERROR, ERR_500, SAMPLE_TYPE_RECORDS_NOT_FOUND, null, null);
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
	public Response getToTalSampleTypeMediaMappingMaster(Integer orgId)
			throws ApplicationException {
		try
		{
			return iSampleTypeMediaMappingMasterDao.getToTalSampleTypeMediaMappingMaster(orgId);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response updateSampleTypeMediaMappingMaster(SampleTypeMediaMappingMasterDto sampleTypeMediaMappingMasterDto)
			throws ApplicationException {
	
		
      try{	
			
			List<SampleTypeMediaMappingMasterDto> listantibioticGroupAdditionMasterDto = new ArrayList();
			Iterator ite=sampleTypeMediaMappingMasterDto.getSelectediaampleMediaMpprList().iterator(); 
			while (ite.hasNext()) {
				SampleTypeMediaMappingMasterDto sampleTypeMediaMappingMaster = new SampleTypeMediaMappingMasterDto();
				Integer mediaId = (Integer) ite.next();
				long time = new Date().getTime();
				sampleTypeMediaMappingMaster.setCreatedDate(time);
				sampleTypeMediaMappingMasterDto.setUpdatedDate(time);
				sampleTypeMediaMappingMaster.setSampleId(sampleTypeMediaMappingMasterDto.getSampleId());;
				sampleTypeMediaMappingMaster.setMediaId(mediaId);
				sampleTypeMediaMappingMaster.setSampleMediaStatus(sampleTypeMediaMappingMasterDto.getSampleMediaStatus());
				sampleTypeMediaMappingMaster.setOrgId(sampleTypeMediaMappingMasterDto.getOrgId());
				sampleTypeMediaMappingMaster.setCreatedBy(sampleTypeMediaMappingMasterDto.getCreatedBy());
				sampleTypeMediaMappingMaster.setIsDeleted(sampleTypeMediaMappingMasterDto.getIsDeleted());
				listantibioticGroupAdditionMasterDto.add(sampleTypeMediaMappingMaster);
			}
			if(!listantibioticGroupAdditionMasterDto.isEmpty())
			{
				return iSampleTypeMediaMappingMasterDao
				.updateSampleTypeMediaMappingMaster(listantibioticGroupAdditionMasterDto);
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
	public Response getSampleTypeAddtionClassMasterById(Integer orgId,
			Integer sampleId) throws ApplicationException {
		try
		{
			return iSampleTypeMediaMappingMasterDao.getSampleTypeAddtionClassMasterById(orgId, sampleId);
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
			return iSampleTypeMediaMappingMasterDao.getMediaMasterClassById(mediaId);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}


}
