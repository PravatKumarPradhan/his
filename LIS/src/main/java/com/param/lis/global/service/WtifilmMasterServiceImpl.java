package com.param.lis.global.service;

import in.param.exception.ApplicationException;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.param.entity.lis.global.WtfilmStudyMaster;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.Response;
import com.param.lis.global.dao.IWtfilmStudyMasterDao;
import com.param.lis.global.dto.GlobalCommonDto;
import com.param.lis.global.dto.WtfilmStudyMasterDto;

@Service
@SuppressWarnings({"rawtypes","unchecked"})
public class WtifilmMasterServiceImpl implements IWtfilmStudyMasterService,ICommonConstants, IError{

	@Autowired
	private IWtfilmStudyMasterDao iWtfilmStudyMasterDao;
	
	@Override
	@Transactional
	public Response addWtfilmStudyMaster(WtfilmStudyMasterDto wtfilmStudyMasterDto)throws ApplicationException {
		try{
			Response res = iWtfilmStudyMasterDao.checkWtfilmStudyMaster(wtfilmStudyMasterDto);
			if(res.getListObject() != null && res.getListObject().size() > 0){
				return new Response<>(ERROR, ERR_500,WTIFILMSTUDY_CODE_EXISIS, null, null);
			}else{
				long time = new Date().getTime();
				wtfilmStudyMasterDto.setCreatedDate(time);
				wtfilmStudyMasterDto.setUpdatedDate(time);
				iWtfilmStudyMasterDao.addWtfilmStudyMaster(wtfilmStudyMasterDto);
				return new Response<>(SUCCESS, SUCCESS_200,WTIFILMSTUDY_ADD_SUCC, null, null);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERR_201,WTIFILMSTUDY_ADD_FAIL, null, null);
	}

	@Override
	@Transactional
	public Response getWtfilmStudyMasterById(Integer orgId, Integer reagentId)
			throws ApplicationException {
		try
		{
			return iWtfilmStudyMasterDao.getWtfilmStudyMasterById(orgId, reagentId);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}


	@Override
	@Transactional
	public Response ActivateInactivateWtfilmStudyMaster(Integer orgId,
			Integer reagentId, Character reagentStatus)
			throws ApplicationException {
		try
		{
			Response resp= iWtfilmStudyMasterDao.ActivateInactivateWtfilmStudyMaster(orgId, reagentId, reagentStatus);
			WtfilmStudyMaster wtfilmStudyMst = resp.getObject() != null ? (WtfilmStudyMaster)resp.getObject() : null;
			if(wtfilmStudyMst != null){
				if (wtfilmStudyMst.getStatus() == ACTIVE)
				{
					return new Response(SUCCESS, SUCCESS_200,WTIFILMSTUDY_ACTIVATE_SUCC, null, null);
				} else if (wtfilmStudyMst.getStatus()== INACTIVE)
				{
					return new Response(SUCCESS, SUCCESS_200,WTIFILMSTUDY_INACTIVATE_SUCC, null, null);
				} else
				{
					if (reagentStatus == ACTIVE)
					{
						return new Response(SUCCESS, SUCCESS_200,WTIFILMSTUDY_ACTIVATE_FAIL, null, null);
					} else
					{
						return new Response(SUCCESS, SUCCESS_200,WTIFILMSTUDY_INACTIVATE_FAIL, null, null);
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
	public Response listWtfilmStudyMaster(Integer orgId, Integer offset,
			Integer recordPerPage) throws ApplicationException {
		try
		{
			Response res =  iWtfilmStudyMasterDao.listWtfilmStudyMaster(orgId, offset, recordPerPage);
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
	public Response getToTalWtfilmStudyMaster(Integer orgId)
			throws ApplicationException {
		try
		{
			return iWtfilmStudyMasterDao.getToTalWtfilmStudyMaster(orgId);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response updateWtfilmStudyMaster(WtfilmStudyMasterDto WtfilmStudyMasterDto)
			throws ApplicationException {
		
		try{
			Response res = iWtfilmStudyMasterDao.updateCheckWtfilmStudyCodeAvaiable(WtfilmStudyMasterDto);
			if(res.getListObject() != null && res.getListObject().size() > 0){
				return new Response<>(ERROR, ERR_500,WTIFILMSTUDY_CODE_EXISIS, null, null);
			}else{
				WtfilmStudyMasterDto.setUpdatedDate(new Date().getTime());
				Response resdata =  iWtfilmStudyMasterDao.updateWtfilmStudyMaster(WtfilmStudyMasterDto);
				if (resdata.getStatus().equalsIgnoreCase(SUCCESS))
				{
					return new Response(SUCCESS, SUCCESS_200,WTIFILMSTUDY_UPDATE_SUCC, null, null);
				} else
				{
					return new Response(ERROR, ERR_500,WTIFILMSTUDY_UPDATE_FAIL, null, null);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERR_201,WTIFILMSTUDY_UPDATE_FAIL, null, null);
	
	}


}
