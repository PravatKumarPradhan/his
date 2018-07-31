package com.param.lis.global.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.param.entity.lis.global.OrganismGroupMaster;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.Response;
import com.param.lis.global.dao.IOrganisumGroupMasterDao;
import com.param.lis.global.dto.GlobalCommonDto;
import com.param.lis.global.dto.OrganisumGroupMasterDto;
import in.param.exception.ApplicationException;

@Service
@SuppressWarnings({"rawtypes","unchecked"})
public class OrganisumGroupMasterServiceImpl implements IOrganisumGroupMasterService,ICommonConstants, IError{

	@Autowired
	private IOrganisumGroupMasterDao  iOrganisumGroupMasterDao;
	
	@Override
	@Transactional
	public Response addOrganisumGroupMaster(OrganisumGroupMasterDto organisumGroupMasterDto)throws ApplicationException {
		try{
			Response res = iOrganisumGroupMasterDao.checkOrganisumGroupMaster(organisumGroupMasterDto);
			if(res.getListObject() != null && res.getListObject().size() > 0){
				return new Response<>(ERROR, ERR_500,ORGANISUM_GROUP_CODE_EXISIS, null, null);
			}else{
				long time = new Date().getTime();
				organisumGroupMasterDto.setCreatedDate(time);
				organisumGroupMasterDto.setUpdatedDate(time);
				iOrganisumGroupMasterDao.addOrganisumGroupMaster(organisumGroupMasterDto);
				return new Response<>(SUCCESS, SUCCESS_200,ORGANISUM_GROUP_ADD_SUCC, null, null);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERR_201,ORGANISUM_GROUP_ADD_FAIL, null, null);
	}

	@Override
	@Transactional
	public Response getOrganisumGroupMasterById(Integer orgId, Integer reagentId)
			throws ApplicationException {
		try
		{
			return iOrganisumGroupMasterDao.getOrganisumGroupMasterById(orgId, reagentId);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}


	@Override
	@Transactional
	public Response ActivateInactivateOrganisumGroupMaster(Integer orgId,
			Integer organisumGroupId, Character organisumGroupStatus)
			throws ApplicationException {
		try
		{
			Response resp= iOrganisumGroupMasterDao.ActivateInactivateOrganisumGroupMaster(orgId, organisumGroupId, organisumGroupStatus);
			OrganismGroupMaster organisumGroupMst = resp.getObject() != null ? (OrganismGroupMaster)resp.getObject() : null;
			if(organisumGroupMst != null){
				if (organisumGroupMst.getStatus() == ACTIVE)
				{
					return new Response(SUCCESS, SUCCESS_200,ORGANISUM_GROUP_ACTIVATE_SUCC, null, null);
				} else if (organisumGroupMst.getStatus()== INACTIVE)
				{
					return new Response(SUCCESS, SUCCESS_200,ORGANISUM_GROUP_INACTIVATE_SUCC, null, null);
				} else
				{
					if (organisumGroupStatus == ACTIVE)
					{
						return new Response(SUCCESS, SUCCESS_200,ORGANISUM_GROUP_ACTIVATE_FAIL, null, null);
					} else
					{
						return new Response(SUCCESS, SUCCESS_200,ORGANISUM_GROUP_INACTIVATE_FAIL, null, null);
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
	public Response listOrganisumGroupMaster(Integer orgId, Integer offset,
			Integer recordPerPage) throws ApplicationException {
		try
		{
			Response res =  iOrganisumGroupMasterDao.listOrganisumGroupMaster(orgId, offset, recordPerPage);
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
	public Response getToTalOrganisumGroupMaster(Integer orgId)
			throws ApplicationException {
		try
		{
			return iOrganisumGroupMasterDao.getToTalOrganisumGroupMaster(orgId);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response updateOrganisumGroupMaster(OrganisumGroupMasterDto organisumGroupMasterDto)
			throws ApplicationException {
		
		try{
			Response res = iOrganisumGroupMasterDao.updateCheckOrganisumGroupMasterCodeAvaiable(organisumGroupMasterDto);
			if(res.getListObject() != null && res.getListObject().size() > 0){
				return new Response<>(ERROR, ERR_500,ORGANISUM_GROUP_CODE_EXISIS, null, null);
			}else{
				organisumGroupMasterDto.setUpdatedDate(new Date().getTime());
				Response resdata =  iOrganisumGroupMasterDao.updateOrganisumGroupMaster(organisumGroupMasterDto);
				if (resdata.getStatus().equalsIgnoreCase(SUCCESS))
				{
					return new Response(SUCCESS, SUCCESS_200,ORGANISUM_GROUP_UPDATE_SUCC, null, null);
				} else
				{
					return new Response(ERROR, ERR_500,ORGANISUM_GROUP_UPDATE_FAIL, null, null);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERR_201,ORGANISUM_GROUP_UPDATE_FAIL, null, null);
	
	}




}
