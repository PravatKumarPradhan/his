package com.param.lis.global.service;

import in.param.exception.ApplicationException;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.param.entity.lis.global.BacteriaMaster;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.Response;
import com.param.lis.global.dao.IBacteriaMasterDao;
import com.param.lis.global.dto.BacteriaMasterDto;
import com.param.lis.global.dto.GlobalCommonDto;

@Service
@SuppressWarnings({"rawtypes","unchecked"})
public class BacteriaMasterServiceImpl implements IBacteriaMasterService,ICommonConstants, IError{

	@Autowired
	private IBacteriaMasterDao iBacteriaMasterDao;
	
	@Override
	@Transactional
	public Response addBacteriaMaster(BacteriaMasterDto bacteriaMasterDto)throws ApplicationException {
		try{
			Response res = iBacteriaMasterDao.checkBacteriaMaster(bacteriaMasterDto);
			if(res.getListObject() != null && res.getListObject().size() > 0){
				return new Response<>(ERROR, ERR_500,BACTERIA_CODE_EXISIS, null, null);
			}else{
				long time = new Date().getTime();
				bacteriaMasterDto.setCreatedDate(time);
				bacteriaMasterDto.setUpdatedDate(time);
				iBacteriaMasterDao.addBacteriaMaster(bacteriaMasterDto);
				return new Response<>(SUCCESS, SUCCESS_200,BACTERIA_ADD_SUCC, null, null);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERR_201,BACTERIA_ADD_FAIL, null, null);
	}

	@Override
	@Transactional
	public Response getBacteriaMasterById(Integer orgId, Integer reagentId)
			throws ApplicationException {
		try
		{
			return iBacteriaMasterDao.getBacteriaMasterById(orgId, reagentId);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}


	@Override
	@Transactional
	public Response ActivateInactivateBacteriaMaster(Integer orgId,
			Integer reagentId, Character reagentStatus)
			throws ApplicationException {
		try
		{
			Response resp= iBacteriaMasterDao.ActivateInactivateBacteriaMaster(orgId, reagentId, reagentStatus);
			BacteriaMaster bacteriaMst = resp.getObject() != null ? (BacteriaMaster)resp.getObject() : null;
			if(bacteriaMst != null){
				if (bacteriaMst.getStatus() == ACTIVE)
				{
					return new Response(SUCCESS, SUCCESS_200,BACTERIA_ACTIVATE_SUCC, null, null);
				} else if (bacteriaMst.getStatus()== INACTIVE)
				{
					return new Response(SUCCESS, SUCCESS_200,BACTERIA_INACTIVATE_SUCC, null, null);
				} else
				{
					if (reagentStatus == ACTIVE)
					{
						return new Response(SUCCESS, SUCCESS_200,BACTERIA_ACTIVATE_FAIL, null, null);
					} else
					{
						return new Response(SUCCESS, SUCCESS_200,BACTERIA_INACTIVATE_FAIL, null, null);
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
	public Response listBacteriaMaster(Integer orgId, Integer offset,
			Integer recordPerPage) throws ApplicationException {
		try
		{
			Response res =  iBacteriaMasterDao.listBacteriaMaster(orgId, offset, recordPerPage);
			if(res.getListObject() != null && res.getListObject().size() > 0){
				List<GlobalCommonDto> unitMst = res.getListObject() != null ? (List<GlobalCommonDto>)res.getListObject() : null;
				if(unitMst.isEmpty())
				{
					return new Response(ERROR, ERR_500, SAMPLE_RECORDS_NOT_FOUND, null, null);
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
	public Response getToTalBacteriaMaster(Integer orgId)
			throws ApplicationException {
		try
		{
			return iBacteriaMasterDao.getToTalBacteriaMaster(orgId);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response updateBacteriaMaster(BacteriaMasterDto bacteriaMasterDto)
			throws ApplicationException {
		
		try{
			Response res = iBacteriaMasterDao.updateCheckBacteriaCodeAvaiable(bacteriaMasterDto);
			if(res.getListObject() != null && res.getListObject().size() > 0){
				return new Response<>(ERROR, ERR_500,BACTERIA_CODE_EXISIS, null, null);
			}else{
				bacteriaMasterDto.setUpdatedDate(new Date().getTime());
				Response resdata =  iBacteriaMasterDao.updateBacteriaMaster(bacteriaMasterDto);
				if (resdata.getStatus().equalsIgnoreCase(SUCCESS))
				{
					return new Response(SUCCESS, SUCCESS_200,BACTERIA_UPDATE_SUCC, null, null);
				} else
				{
					return new Response(ERROR, ERR_500,BACTERIA_UPDATE_FAIL, null, null);
				}
			
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	}


}
