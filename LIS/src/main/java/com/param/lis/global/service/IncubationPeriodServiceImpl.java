package com.param.lis.global.service;

import in.param.exception.ApplicationException;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.param.entity.lis.global.IncubationPeriodMaster;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.Response;
import com.param.lis.global.dao.IIncubationPeriodMasterDao;
import com.param.lis.global.dto.IncubationPeriodMasterDto;

@Service
@SuppressWarnings({"rawtypes","unchecked"})
public class IncubationPeriodServiceImpl implements IIncubationPeriodMasterService,ICommonConstants, IError{

	@Autowired
	private IIncubationPeriodMasterDao iIncubationPeriodMasterDao;
	
	@Override
	@Transactional
	public Response addIncubationPeriodMaster(IncubationPeriodMasterDto incubationPeriodDto)throws ApplicationException {
		try{
			Response res = iIncubationPeriodMasterDao.checkIncubationPeriodMaster(incubationPeriodDto);
			if(res.getListObject() != null && res.getListObject().size() > 0){
				return new Response<>(ERROR, ERR_500,INCUBATION_PERIOD_MASTER_CODE_EXISIS, null, null);
			}else{
				long time = new Date().getTime();
				incubationPeriodDto.setCreatedDate(time);
				incubationPeriodDto.setUpdatedDate(time);
				iIncubationPeriodMasterDao.addIncubationPeriodMaster(incubationPeriodDto);
				return new Response<>(SUCCESS, SUCCESS_200,INCUBATION_PERIOD_MASTER_ADD_SUCC, null, null);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERR_201,INCUBATION_PERIOD_MASTER_ADD_FAIL, null, null);
	}

	@Override
	@Transactional
	public Response getIncubationPeriodMasterById(Integer orgId, Integer reagentId)
			throws ApplicationException {
		try
		{
			return iIncubationPeriodMasterDao.getIncubationPeriodMasterById(orgId, reagentId);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}


	@Override
	@Transactional
	public Response ActivateInactivateIncubationPeriodMaster(Integer orgId,
			Integer incubationPeriodID, Character incubationPeriodStatus)
			throws ApplicationException {
		try
		{
			Response resp= iIncubationPeriodMasterDao.ActivateInactivateIncubationPeriodMaster(orgId, incubationPeriodID, incubationPeriodStatus);
			IncubationPeriodMaster incubationPeriodMst = resp.getObject() != null ? (IncubationPeriodMaster)resp.getObject() : null;
			if(incubationPeriodMst != null){
				if (incubationPeriodMst.getStatus() == ACTIVE)
				{
					return new Response(SUCCESS, SUCCESS_200,INCUBATION_PERIOD_MASTER_ACTIVATE_SUCC, null, null);
				} else if (incubationPeriodMst.getStatus()== INACTIVE)
				{
					return new Response(SUCCESS, SUCCESS_200,INCUBATION_PERIOD_MASTER_INACTIVATE_SUCC, null, null);
				} else
				{
					if (incubationPeriodStatus == ACTIVE)
					{
						return new Response(SUCCESS, SUCCESS_200,INCUBATION_PERIOD_MASTER_ACTIVATE_SUCC, null, null);
					} else
					{
						return new Response(SUCCESS, SUCCESS_200,INCUBATION_PERIOD_MASTER_INACTIVATE_FAIL, null, null);
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
	public Response listIncubationPeriodMaster(Integer orgId, Integer offset,
			Integer recordPerPage) throws ApplicationException {
		try
		{
			Response res =  iIncubationPeriodMasterDao.listIncubationPeriodMaster(orgId, offset, recordPerPage);
			if(res.getListObject() != null && res.getListObject().size() > 0){
				List<IncubationPeriodMasterDto> unitMst = res.getListObject() != null ? (List<IncubationPeriodMasterDto>)res.getListObject() : null;
				if(unitMst.isEmpty())
				{
					return new Response(ERROR, ERR_500, INCUBATION_PERIOD_MASTER_NOT_FOUND, null, null);
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
	public Response getToTalIncubationPeriodMaster(Integer orgId)
			throws ApplicationException {
		try
		{
			return iIncubationPeriodMasterDao.getToTalIncubationPeriodMaster(orgId);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response updateIncubationPeriodMaster(IncubationPeriodMasterDto incubationPeriodMasterDto)
			throws ApplicationException {
		
		try{
			Response res = iIncubationPeriodMasterDao.updateCheckIncubationPeriodCodeAvaiable(incubationPeriodMasterDto);
			if(res.getListObject() != null && res.getListObject().size() > 0){
				return new Response<>(ERROR, ERR_500,INCUBATION_PERIOD_MASTER_CODE_EXISIS, null, null);
			}else{
				incubationPeriodMasterDto.setUpdatedDate(new Date().getTime());
				if(incubationPeriodMasterDto.getIncubationTimeId()==1)
				{
					incubationPeriodMasterDto.setIncubationPeriodTime(incubationPeriodMasterDto.getIncubationValue()+" Hours");
					incubationPeriodMasterDto.setIncubationInHours(incubationPeriodMasterDto.getIncubationValue());
					
				}
				if(incubationPeriodMasterDto.getIncubationTimeId()==2)
				{
					incubationPeriodMasterDto.setIncubationPeriodTime(incubationPeriodMasterDto.getIncubationValue()+" Days");
					incubationPeriodMasterDto.setIncubationInHours(incubationPeriodMasterDto.getIncubationValue()*24);
				}
				Response resdata =  iIncubationPeriodMasterDao.updateIncubationPeriodMaster(incubationPeriodMasterDto);
				if (resdata.getStatus().equalsIgnoreCase(SUCCESS))
				{
					return new Response(SUCCESS, SUCCESS_200,INCUBATION_PERIOD_MASTER_UPDATE_SUCC, null, null);
				} else
				{
					return new Response(ERROR, ERR_500,INCUBATION_PERIOD_MASTER_UPDATE_FAIL, null, null);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERR_201,REPORT_TYPRUPDATE_FAIL, null, null);
	}


}
