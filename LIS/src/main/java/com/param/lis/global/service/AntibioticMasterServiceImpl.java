package com.param.lis.global.service;

import in.param.exception.ApplicationException;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.param.entity.lis.global.AntibioticMaster;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.Response;
import com.param.lis.global.dao.IAntibioticMasterDao;
import com.param.lis.global.dto.AntibioticMasterDto;
import com.param.lis.global.dto.GlobalCommonDto;

@Service
@SuppressWarnings({"rawtypes","unchecked"})
public class AntibioticMasterServiceImpl implements IAntibioticMasterService,ICommonConstants, IError{

	@Autowired
	private IAntibioticMasterDao iAntibioticMasterDao;
	
	@Override
	@Transactional
	public Response addAntibioticMaster(AntibioticMasterDto antibioticMasterDto)throws ApplicationException {
		try{
			Response res = iAntibioticMasterDao.checkAntibioticMaster(antibioticMasterDto);
			if(res.getListObject() != null && res.getListObject().size() > 0){
				return new Response<>(ERROR, ERR_500,ANTIBIOTICS_CODE_EXISIS, null, null);
			}else{
				long time = new Date().getTime();
				antibioticMasterDto.setCreatedDate(time);
				antibioticMasterDto.setUpdatedDate(time);
				iAntibioticMasterDao.addAntibioticMaster(antibioticMasterDto);
				return new Response<>(SUCCESS, SUCCESS_200,ANTIBIOTICS_ADD_SUCC, null, null);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERR_201,ANTIBIOTICS_ADD_FAIL, null, null);
	}

	@Override
	@Transactional
	public Response getAntibioticMasterById(Integer orgId, Integer antibioticGroupId)
			throws ApplicationException {
		try
		{
			return iAntibioticMasterDao.getAntibioticMasterById(orgId, antibioticGroupId);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}


	@Override
	@Transactional
	public Response ActivateInactivateAntibioticMaster(Integer orgId,
			Integer reagentId, Character reagentStatus)
			throws ApplicationException {
		try
		{
			Response resp= iAntibioticMasterDao.ActivateInactivateAntibioticMaster(orgId, reagentId, reagentStatus);
			AntibioticMaster antibioticMst = resp.getObject() != null ? (AntibioticMaster)resp.getObject() : null;
			if(antibioticMst != null){
				if (antibioticMst.getStatus() == ACTIVE)
				{
					return new Response(SUCCESS, SUCCESS_200, ANTIBIOTICS_ACTIVATE_SUCC, null, null);
				} else if (antibioticMst.getStatus()== INACTIVE)
				{
					return new Response(SUCCESS, SUCCESS_200, ANTIBIOTICS_INACTIVATE_SUCC, null, null);
				} else
				{
					if (reagentStatus == ACTIVE)
					{
						return new Response(SUCCESS, SUCCESS_200, ANTIBIOTICS_ACTIVATE_FAIL, null, null);
					} else
					{
						return new Response(SUCCESS, SUCCESS_200, ANTIBIOTICS_INACTIVATE_FAIL, null, null);
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
	public Response listAntibioticMaster(Integer orgId, Integer offset,
			Integer recordPerPage) throws ApplicationException {
		try
		{
			Response res =  iAntibioticMasterDao.listAntibioticMaster(orgId, offset, recordPerPage);
			if(res.getListObject() != null && res.getListObject().size() > 0){
				List<GlobalCommonDto> unitMst = res.getListObject() != null ? (List<GlobalCommonDto>)res.getListObject() : null;
				if(unitMst.isEmpty())
				{
					return new Response(ERROR, ERR_500, ANTIBIOTICS__CORDS_NOT_FOUND, null, null);
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
	public Response getToTalAntibioticMaster(Integer orgId)
			throws ApplicationException {
		try
		{
			return iAntibioticMasterDao.getToTalAntibioticMaster(orgId);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response updateAntibioticMaster(AntibioticMasterDto antibioticMasterDto)
			throws ApplicationException {
		
		
		try{
			Response res = iAntibioticMasterDao.updateCheckeAntibioticMasterCodeAvaiable(antibioticMasterDto);
			if(res.getListObject() != null && res.getListObject().size() > 0){
				return new Response<>(ERROR, ERR_500,ANTIBIOTICS_CODE_EXISIS, null, null);
			}else{
				antibioticMasterDto.setUpdatedDate(new Date().getTime());
				Response resdata =  iAntibioticMasterDao.updateAntibioticMaster(antibioticMasterDto);
				if (resdata.getStatus().equalsIgnoreCase(SUCCESS))
				{
					return new Response(SUCCESS, SUCCESS_200, ANTIBIOTICS_UPDATE_SUCC, null, null);
				} else
				{
					return new Response(ERROR, ERR_500, ANTIBIOTICS_UPDATE_FAIL, null, null);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERR_201,ANTIBIOTICS_UPDATE_FAIL, null, null);
		
	}

	@Override
	@Transactional
	public Response getListAntibioticMaster() throws ApplicationException {
		try
		{
			Response res =  iAntibioticMasterDao.getListAntibioticMaster();
			if(res.getListObject() != null && res.getListObject().size() > 0){
				return new Response(SUCCESS, SUCCESS_200, null, res.getListObject(), null);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	}


}
