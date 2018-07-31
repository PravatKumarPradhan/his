package com.param.lis.global.service;

import in.param.exception.ApplicationException;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.param.entity.lis.global.AntibioticClassMaster;
import com.param.entity.lis.global.ColonyMaster;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.Response;
import com.param.lis.global.dao.IColonyMasterDao;
import com.param.lis.global.dto.ColonyMasterDto;
import com.param.lis.global.dto.GlobalCommonDto;

@Service
@SuppressWarnings({"rawtypes","unchecked"})
public class ColonyMasterServiceImpl implements IColonyMasterService,ICommonConstants, IError{

	@Autowired
	private IColonyMasterDao iColonyMasterDao;
	
	@Override
	@Transactional
	public Response addColonyMaster(ColonyMasterDto colonyMasterDto)throws ApplicationException {
		try{
			Response res = iColonyMasterDao.checkColonyMaster(colonyMasterDto);
			if(res.getListObject() != null && res.getListObject().size() > 0){
				return new Response<>(ERROR, ERR_500,COLONY_CODE_EXISIS, null, null);
			}else{
				long time = new Date().getTime();
				colonyMasterDto.setCreatedDate(time);
				colonyMasterDto.setUpdatedDate(time);
				iColonyMasterDao.addColonyMaster(colonyMasterDto);
				return new Response<>(SUCCESS, SUCCESS_200,COLONY_ADD_SUCC, null, null);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERR_201,COLONY_ADD_FAIL, null, null);
	}

	@Override
	@Transactional
	public Response getColonyMasterById(Integer orgId, Integer reagentId)
			throws ApplicationException {
		try
		{
			return iColonyMasterDao.getColonyMasterById(orgId, reagentId);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}


	@Override
	@Transactional
	public Response ActivateInactivateColonyMaster(Integer orgId,
			Integer colonyId, Character colonyStatus)
			throws ApplicationException {
		try
		{
			Response resp= iColonyMasterDao.ActivateInactivateColonyMaster(orgId, colonyId, colonyStatus);
			ColonyMaster colonyMst = resp.getObject() != null ? (ColonyMaster)resp.getObject() : null;
			if(colonyMst != null){
				if (colonyMst.getStatus() == ACTIVE)
				{
					return new Response(SUCCESS, SUCCESS_200,COLONY_ACTIVATE_SUCC, null, null);
				} else if (colonyMst.getStatus()== INACTIVE)
				{
					return new Response(SUCCESS, SUCCESS_200,COLONY_INACTIVATE_SUCC, null, null);
				} else
				{
					if (colonyStatus == ACTIVE)
					{
						return new Response(SUCCESS, SUCCESS_200,COLONY_ACTIVATE_FAIL, null, null);
					} else
					{
						return new Response(SUCCESS, SUCCESS_200,COLONY_INACTIVATE_FAIL, null, null);
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
	public Response listColonyMaster(Integer orgId, Integer offset,
			Integer recordPerPage) throws ApplicationException {
		try
		{
			Response res =  iColonyMasterDao.listColonyMaster(orgId, offset, recordPerPage);
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
	public Response getToTalColonyMaster(Integer orgId)
			throws ApplicationException {
		try
		{
			return iColonyMasterDao.getToTalColonyMaster(orgId);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response updateColonyMaster(ColonyMasterDto colonyMasterDto)
			throws ApplicationException {
		
		try{
			Response res = iColonyMasterDao.updateCheckColonyCodeAvaiable(colonyMasterDto);
			if(res.getListObject() != null && res.getListObject().size() > 0){
				return new Response<>(ERROR, ERR_500,COLONY_CODE_EXISIS, null, null);
			}else{
				colonyMasterDto.setUpdatedDate(new Date().getTime());
				Response resdata =  iColonyMasterDao.updateColonyMaster(colonyMasterDto);
				if (resdata.getStatus().equalsIgnoreCase(SUCCESS))
				{
					return new Response(SUCCESS, SUCCESS_200,COLONY_UPDATE_SUCC, null, null);
				} else
				{
					return new Response(ERROR, ERR_500,COLONY_UPDATE_FAIL, null, null);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERR_201,COLONY_UPDATE_FAIL, null, null);
	
	}


}
