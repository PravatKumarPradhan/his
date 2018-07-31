package com.param.lis.global.service;

import in.param.exception.ApplicationException;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.param.entity.lis.global.AgeGroupMaster;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.ITransactionConstants;
import com.param.lis.global.common.Response;
import com.param.lis.global.dao.IAgeGroupMasterDao;
import com.param.lis.global.dto.AgeGroupMasterDto;

@Service
@SuppressWarnings({"rawtypes","unchecked"})
public class AgeGroupMasterServiceImpl implements IAgeGroupMasterService,ICommonConstants, IError,ITransactionConstants{

	@Autowired
	private IAgeGroupMasterDao iAgeGroupMasterDao;
	
	@Override
	@Transactional
	public Response addAgeGroupMaster(AgeGroupMasterDto ageGroupMasterDto)throws ApplicationException {
		try{
			Response res = iAgeGroupMasterDao.checkAgeGroupCodeAvaiable(ageGroupMasterDto);
			if(res.getListObject() != null && res.getListObject().size() > 0){
				return new Response<>(ERROR, ERR_500, AGE_GROUP_CODE_EXISIS, null, null);
			}else{
				long time = new Date().getTime();
				ageGroupMasterDto.setCreatedDate(time);
				ageGroupMasterDto.setUpdatedDate(time);
				Integer ageFromday;
				
				if (DAY == ageGroupMasterDto.getAgeTypeGrpfromId()) {
					ageFromday = ageGroupMasterDto.getAgeGroupFrom() * DAYS;
					ageGroupMasterDto.setAgeFromDay(ageFromday);
				} else if (MONTHS == ageGroupMasterDto.getAgeTypeGrpfromId()) {
					ageFromday = ageGroupMasterDto.getAgeGroupFrom() * MONTHS_TO_DAYS;
					ageGroupMasterDto.setAgeFromDay(ageFromday);
				} else if (YEAR == ageGroupMasterDto.getAgeTypeGrpfromId()) {
					ageFromday =  ageGroupMasterDto.getAgeGroupFrom()* YEAAR_TO_DAYS;
					ageGroupMasterDto.setAgeFromDay(ageFromday);
				}
				Integer ageToday;
				if (DAY == ageGroupMasterDto.getAgeTypeGrptoId()) {
					ageToday = ageGroupMasterDto.getAgeGroupTo() * DAYS;
					ageGroupMasterDto.setAgeToday(ageToday);
				} else if (MONTHS == ageGroupMasterDto.getAgeTypeGrptoId()) {
					ageToday =  ageGroupMasterDto.getAgeGroupTo() * MONTHS_TO_DAYS;
					ageGroupMasterDto.setAgeToday(ageToday);
				} else if (YEAR == ageGroupMasterDto.getAgeTypeGrptoId()) {
					ageToday =  ageGroupMasterDto.getAgeGroupTo() * YEAAR_TO_DAYS;
					ageGroupMasterDto.setAgeToday(ageToday);
				}
				String str =ageGroupMasterDto.getAgeGroupFrom()+ (ageGroupMasterDto.getAgeTypeGrpfromId() == DAY ? " Days " : ageGroupMasterDto.getAgeTypeGrpfromId() == MONTHS ? " Months " : " Year ") 
							+ " To " +  ageGroupMasterDto.getAgeGroupTo() + (ageGroupMasterDto.getAgeTypeGrptoId() == DAY ? " Days " :ageGroupMasterDto.getAgeTypeGrptoId() == MONTHS ? " Months " : " Year ");
				
				System.out.println(str);
				ageGroupMasterDto.setAgeTypeGrpName(str);
				iAgeGroupMasterDao.addAgeGroupMaster(ageGroupMasterDto);
				return new Response<>(SUCCESS, SUCCESS_200, AGE_GROUP_ADD_SUCC, null, null);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERR_201, AGE_GROUP_ADD_FAIL, null, null);
	}

	@Override
	@Transactional
	public Response getAgeGroupMasterById(Integer orgId, Integer mediaId)
			throws ApplicationException {
		try
		{
			return iAgeGroupMasterDao.getAgeGroupMasterById(orgId, mediaId);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}


	@Override
	@Transactional
	public Response ActivateInactivateAgeGroupMaster(Integer orgId,
			Integer ageGroupId, Character ageGroupStatus)
			throws ApplicationException {
		try
		{
			Response resp= iAgeGroupMasterDao.ActivateInactivateAgeGroupMaster(orgId, ageGroupId, ageGroupStatus);
			AgeGroupMaster ageGroupMst = resp.getObject() != null ? (AgeGroupMaster)resp.getObject() : null;
			if(ageGroupMst != null){
				if (ageGroupMst.getAgeGroupStatus() == ACTIVE)
				{
					return new Response(SUCCESS, SUCCESS_200, AGE_GROUP_ACTIVATE_SUCC, null, null);
				} else if (ageGroupMst.getAgeGroupStatus()== INACTIVE)
				{
					return new Response(SUCCESS, SUCCESS_200, AGE_GROUP_INACTIVATE_SUCC, null, null);
				} else
				{
					if (ageGroupStatus == ACTIVE)
					{
						return new Response(SUCCESS, SUCCESS_200, AGE_GROUP_ACTIVATE_FAIL, null, null);
					} else
					{
						return new Response(SUCCESS, SUCCESS_200, AGE_GROUP_INACTIVATE_FAIL, null, null);
					}
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
	public Response listAgeGroupMaster(Integer orgId, Integer offset,
			Integer recordPerPage) throws ApplicationException {
		try
		{
			Response res =  iAgeGroupMasterDao.listAgeGroupMaster(orgId, offset, recordPerPage);
			if(res.getListObject() != null && res.getListObject().size() > 0){
				List<AgeGroupMasterDto> ageGroupMst = res.getListObject() != null ? (List<AgeGroupMasterDto>)res.getListObject() : null;
				if(ageGroupMst.isEmpty())
				{
					return new Response(ERROR, ERR_500, SAMPLE_RECORDS_NOT_FOUND, null, null);
				}
				else{
					return new Response(SUCCESS, SUCCESS_200, null, ageGroupMst, null);
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
	public Response getToTalAgeGroupMasterRecord(Integer orgId)
			throws ApplicationException {
		try
		{
			return iAgeGroupMasterDao.getToTalAgeGroupMasterRecord(orgId);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response updateAgeGroupMaster(AgeGroupMasterDto ageGroupMasterDto)
			throws ApplicationException {
		try{
			Response res = iAgeGroupMasterDao.updateCheckAgeGroupCodeAvaiable(ageGroupMasterDto);
			if(res.getListObject() != null && res.getListObject().size() > 0){
				return new Response<>(ERROR, ERR_500,AGE_GROUP_CODE_EXISIS, null, null);
			}else{
				ageGroupMasterDto.setUpdatedDate(new Date().getTime());
	            Integer ageFromday;
				
				if (DAY == ageGroupMasterDto.getAgeTypeGrpfromId()) {
					ageFromday = ageGroupMasterDto.getAgeGroupFrom() * DAYS;
					ageGroupMasterDto.setAgeFromDay(ageFromday);
				} else if (MONTHS == ageGroupMasterDto.getAgeTypeGrpfromId()) {
					ageFromday = ageGroupMasterDto.getAgeGroupFrom() * MONTHS_TO_DAYS;
					ageGroupMasterDto.setAgeFromDay(ageFromday);
				} else if (YEAR == ageGroupMasterDto.getAgeTypeGrpfromId()) {
					ageFromday =  ageGroupMasterDto.getAgeGroupFrom()* YEAAR_TO_DAYS;
					ageGroupMasterDto.setAgeFromDay(ageFromday);
				}
				Integer ageToday;
				if (DAY == ageGroupMasterDto.getAgeTypeGrptoId()) {
					ageToday = ageGroupMasterDto.getAgeGroupTo() * DAYS;
					ageGroupMasterDto.setAgeToday(ageToday);
				} else if (MONTHS == ageGroupMasterDto.getAgeTypeGrptoId()) {
					ageToday =  ageGroupMasterDto.getAgeGroupTo() * MONTHS_TO_DAYS;
					ageGroupMasterDto.setAgeToday(ageToday);
				} else if (YEAR == ageGroupMasterDto.getAgeTypeGrptoId()) {
					ageToday =  ageGroupMasterDto.getAgeGroupTo() * YEAAR_TO_DAYS;
					ageGroupMasterDto.setAgeToday(ageToday);
				}
				String str =ageGroupMasterDto.getAgeGroupFrom()+ (ageGroupMasterDto.getAgeTypeGrpfromId() == DAY ? " Days " : ageGroupMasterDto.getAgeTypeGrpfromId() == MONTHS ? " Months " : " Year ") 
							+ " To " +  ageGroupMasterDto.getAgeGroupTo()+ (ageGroupMasterDto.getAgeTypeGrptoId() == DAY ? " Days " :ageGroupMasterDto.getAgeTypeGrptoId() == MONTHS ? " Months " : " Year ");
				
				System.out.println(str);
				ageGroupMasterDto.setAgeTypeGrpName(str);
				Response resdata =  iAgeGroupMasterDao.updateAgeGroupMaster(ageGroupMasterDto);
				if (resdata.getStatus().equalsIgnoreCase(SUCCESS))
				{
					return new Response(SUCCESS, SUCCESS_200, AGE_GROUP_UPDATE_SUCC, null, null);
				} else
				{
					return new Response(ERROR, ERR_500, AGE_GROUP_UPDATE_FAIL, null, null);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERR_201,AGE_GROUP_UPDATE_FAIL, null, null);
		
	}



}
