package com.param.lis.global.service;

import in.param.exception.ApplicationException;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.param.entity.lis.global.AntibioticGroupMaster;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.Response;
import com.param.lis.global.dao.IAntibioticsGroupMasterDao;
import com.param.lis.global.dto.AntibioticGroupMasterDto;
import com.param.lis.global.dto.GlobalCommonDto;

@Service
@SuppressWarnings({"rawtypes","unchecked"})
public class AntibioticsGroupMasterServiceImpl implements IAntibioticsGroupMasterService,ICommonConstants, IError{

	@Autowired
	private IAntibioticsGroupMasterDao iAntibioticsGroupMasterDao;
	
	@Override
	@Transactional
	public Response addAntibioticsGroupMaster(AntibioticGroupMasterDto antibioticGroupMasterDto)throws ApplicationException {
		try{
			Response res = iAntibioticsGroupMasterDao.checkAntibioticsGroupMaster(antibioticGroupMasterDto);
			if(res.getListObject() != null && res.getListObject().size() > 0){
				return new Response<>(ERROR, ERR_500,ANTIBIOTICS_GROUPCODE_EXISIS, null, null);
			}else{
				long time = new Date().getTime();
				antibioticGroupMasterDto.setCreatedDate(time);
				antibioticGroupMasterDto.setUpdatedDate(time);
				iAntibioticsGroupMasterDao.addAntibioticsGroupMaster(antibioticGroupMasterDto);
				return new Response<>(SUCCESS, SUCCESS_200,ANTIBIOTICS_GROUP_ADD_SUCC, null, null);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERR_201,ANTIBIOTICS_GROUPADD_FAIL, null, null);
	}

	@Override
	@Transactional
	public Response getAntibioticsGroupMasterById(Integer orgId, Integer antibioticGroupId)
			throws ApplicationException {
		try
		{
			return iAntibioticsGroupMasterDao.getAntibioticsGroupMasterById(orgId, antibioticGroupId);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}


	@Override
	@Transactional
	public Response ActivateInactivateAntibioticsGroupMaster(Integer orgId,
			Integer reagentId, Character reagentStatus)
			throws ApplicationException {
		try
		{
			Response resp= iAntibioticsGroupMasterDao.ActivateInactivateAntibioticsGroupMaster(orgId, reagentId, reagentStatus);
			AntibioticGroupMaster antibioticMst = resp.getObject() != null ? (AntibioticGroupMaster)resp.getObject() : null;
			if(antibioticMst != null){
				if (antibioticMst.getStatus() == ACTIVE)
				{
					return new Response(SUCCESS, SUCCESS_200, ANTIBIOTICS_GROUPACTIVATE_SUCC, null, null);
				} else if (antibioticMst.getStatus()== INACTIVE)
				{
					return new Response(SUCCESS, SUCCESS_200, ANTIBIOTICS_GROUPINACTIVATE_SUCC, null, null);
				} else
				{
					if (reagentStatus == ACTIVE)
					{
						return new Response(SUCCESS, SUCCESS_200, ANTIBIOTICS_GROUPACTIVATE_FAIL, null, null);
					} else
					{
						return new Response(SUCCESS, SUCCESS_200, ANTIBIOTICS_GROUPINACTIVATE_FAIL, null, null);
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
	public Response listAntibioticsGroupMaster(Integer orgId, Integer offset,
			Integer recordPerPage) throws ApplicationException {
		try
		{
			Response res =  iAntibioticsGroupMasterDao.listAntibioticsGroupMaster(orgId, offset, recordPerPage);
			if(res.getListObject() != null && res.getListObject().size() > 0){
				List<GlobalCommonDto> unitMst = res.getListObject() != null ? (List<GlobalCommonDto>)res.getListObject() : null;
				if(unitMst.isEmpty())
				{
					return new Response(ERROR, ERR_500, ANTIBIOTICS_GROUP_CORDS_NOT_FOUND, null, null);
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
	public Response getToTalAntibioticsGroupMaster(Integer orgId)
			throws ApplicationException {
		try
		{
			return iAntibioticsGroupMasterDao.getToTalAntibioticsGroupMaster(orgId);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response updateAntibioticsGroupMaster(AntibioticGroupMasterDto antibioticGroupMasterDto)
			throws ApplicationException {
		
		try{
			Response res = iAntibioticsGroupMasterDao.updateCheckAntibioticsGroupCodeAvaiable(antibioticGroupMasterDto);
			if(res.getListObject() != null && res.getListObject().size() > 0){
				return new Response<>(ERROR, ERR_500,ANTIBIOTICS_GROUPCODE_EXISIS, null, null);
			}else{
				antibioticGroupMasterDto.setUpdatedDate(new Date().getTime());
				Response resdata =  iAntibioticsGroupMasterDao.updateAntibioticsGroupMaster(antibioticGroupMasterDto);
				if (resdata.getStatus().equalsIgnoreCase(SUCCESS))
				{
					return new Response(SUCCESS, SUCCESS_200, ANTIBIOTICS_GROUPUPDATE_SUCC, null, null);
				} else
				{
					return new Response(ERROR, ERR_500, ANTIBIOTICS_GROUPUPDATE_FAIL, null, null);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERR_201,ANTIBIOTICS_GROUPUPDATE_FAIL, null, null);
		
	}


}
