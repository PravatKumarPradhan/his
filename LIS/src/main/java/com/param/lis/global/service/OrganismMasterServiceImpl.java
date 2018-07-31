package com.param.lis.global.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.param.entity.lis.global.OrganismMaster;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.Response;
import com.param.lis.global.dao.IOrganismMasterDao;
import com.param.lis.global.dto.GlobalCommonDto;
import com.param.lis.global.dto.OrganismMasterDto;
import com.param.lis.global.dto.OrganisumGroupMasterDto;
import in.param.exception.ApplicationException;

@Service
@SuppressWarnings({"rawtypes","unchecked"})
public class OrganismMasterServiceImpl implements IOrganismMasterService,ICommonConstants, IError{

	@Autowired
	private IOrganismMasterDao  iIOrganismMasterDao;
	
	@Override
	@Transactional
	public Response addOrganismMaster(OrganismMasterDto organismMasterDto)throws ApplicationException {
		try{
			Response res = iIOrganismMasterDao.checkOrganismMaster(organismMasterDto);
			if(res.getListObject() != null && res.getListObject().size() > 0){
				return new Response<>(ERROR, ERR_500,ORGANISUM_CODE_EXISIS, null, null);
			}else{
				long time = new Date().getTime();
				organismMasterDto.setCreatedDate(time);
				organismMasterDto.setUpdatedDate(time);
				iIOrganismMasterDao.addOrganismMaster(organismMasterDto);
				return new Response<>(SUCCESS, SUCCESS_200,ORGANISUM_ADD_SUCC, null, null);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERR_201,ORGANISUM_ADD_FAIL, null, null);
	}

	@Override
	@Transactional
	public Response getOrganismMasterMasterById(Integer orgId, Integer reagentId)
			throws ApplicationException {
		try
		{
			return iIOrganismMasterDao.getOrganismMasterById(orgId, reagentId);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}


	@Override
	@Transactional
	public Response ActivateInactivateOrganismMaster(Integer orgId,
			Integer organisumGroupId, Character organisumGroupStatus)
			throws ApplicationException {
		try
		{
			Response resp= iIOrganismMasterDao.ActivateInactivateOrganismMaster(orgId, organisumGroupId, organisumGroupStatus);
			OrganismMaster organisMst = resp.getObject() != null ? (OrganismMaster)resp.getObject() : null;
			if(organisMst != null){
				if (organisMst.getStatus() == ACTIVE)
				{
					return new Response(SUCCESS, SUCCESS_200,ORGANISUM_ACTIVATE_SUCC, null, null);
				} else if (organisMst.getStatus()== INACTIVE)
				{
					return new Response(SUCCESS, SUCCESS_200,ORGANISUM_INACTIVATE_SUCC, null, null);
				} else
				{
					if (organisumGroupStatus == ACTIVE)
					{
						return new Response(SUCCESS, SUCCESS_200,ORGANISUM_ACTIVATE_FAIL, null, null);
					} else
					{
						return new Response(SUCCESS, SUCCESS_200,ORGANISUM_INACTIVATE_FAIL, null, null);
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
	public Response listOrganismMaster(Integer orgId, Integer offset,
			Integer recordPerPage) throws ApplicationException {
		try
		{
			Response res =  iIOrganismMasterDao.listOrganismMaster(orgId, offset, recordPerPage);
			if(res.getListObject() != null && res.getListObject().size() > 0){
				List<GlobalCommonDto> orgMst = res.getListObject() != null ? (List<GlobalCommonDto>)res.getListObject() : null;
				if(orgMst.isEmpty())
				{
					return new Response(ERROR, ERR_500, ORGANISUM_NOT_FOUND, null, null);
				}
				else{
					return new Response(SUCCESS, SUCCESS_200, null, orgMst, null);
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
	public Response getToTalOrganismMaster(Integer orgId)
			throws ApplicationException {
		try
		{
			return iIOrganismMasterDao.getToTalOrganismMaster(orgId);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	
	@Override
	@Transactional
	public Response updateOrganismMaster(OrganismMasterDto organismMasterDto)
			throws ApplicationException {
		
		try{
			Response res = iIOrganismMasterDao.updateCheckOrganismMasterCodeAvaiable(organismMasterDto);
			if(res.getListObject() != null && res.getListObject().size() > 0){
				return new Response<>(ERROR, ERR_500,ORGANISUM_CODE_EXISIS, null, null);
			}else{
				organismMasterDto.setUpdatedDate(new Date().getTime());
				Response resdata =  iIOrganismMasterDao.updateOrganismMaster(organismMasterDto);
				if (resdata.getStatus().equalsIgnoreCase(SUCCESS))
				{
					return new Response(SUCCESS, SUCCESS_200,ORGANISUM_UPDATE_SUCC, null, null);
				} else
				{
					return new Response(ERROR, ERR_500,ORGANISUM_UPDATE_FAIL, null, null);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERR_201,ORGANISUM_UPDATE_FAIL, null, null);
	
	}



}
