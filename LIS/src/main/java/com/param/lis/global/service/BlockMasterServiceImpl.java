package com.param.lis.global.service;

import in.param.exception.ApplicationException;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.param.entity.lis.global.BlockMaster;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.Response;
import com.param.lis.global.dao.IBlockMasterDao;
import com.param.lis.global.dto.BlockMasterDto;
import com.param.lis.global.dto.GlobalCommonDto;

@Service
@SuppressWarnings({"rawtypes","unchecked"})
public class BlockMasterServiceImpl implements IBlockMasterService,ICommonConstants, IError{

	@Autowired
	private IBlockMasterDao iBlockMasterDao;
	
	@Override
	@Transactional
	public Response addBlockMaster(BlockMasterDto blockMasterDto)throws ApplicationException {
		try{
			Response res = iBlockMasterDao.checkBlockMaster(blockMasterDto);
			if(res.getListObject() != null && res.getListObject().size() > 0){
				return new Response<>(ERROR, ERR_500,BLOCK_CODE_EXISIS, null, null);
			}else{
				long time = new Date().getTime();
				blockMasterDto.setCreatedDate(time);
				blockMasterDto.setUpdatedDate(time);
				iBlockMasterDao.addBlockMaster(blockMasterDto);
				return new Response<>(SUCCESS, SUCCESS_200,BLOCK_ADD_SUCC, null, null);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERR_201,BLOCK_ADD_FAIL, null, null);
	}

	@Override
	@Transactional
	public Response getBlockMasterById(Integer orgId, Integer reagentId)
			throws ApplicationException {
		try
		{
			return iBlockMasterDao.getBlockMasterById(orgId, reagentId);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}


	@Override
	@Transactional
	public Response ActivateInactivateBlockMaster(Integer orgId,
			Integer reagentId, Character reagentStatus)
			throws ApplicationException {
		try
		{
			Response resp= iBlockMasterDao.ActivateInactivateBlockMaster(orgId, reagentId, reagentStatus);
			BlockMaster bacteriaMst = resp.getObject() != null ? (BlockMaster)resp.getObject() : null;
			if(bacteriaMst != null){
				if (bacteriaMst.getStatus() == ACTIVE)
				{
					return new Response(SUCCESS, SUCCESS_200,BLOCK_ACTIVATE_SUCC, null, null);
				} else if (bacteriaMst.getStatus()== INACTIVE)
				{
					return new Response(SUCCESS, SUCCESS_200,BLOCK_INACTIVATE_SUCC, null, null);
				} else
				{
					if (reagentStatus == ACTIVE)
					{
						return new Response(SUCCESS, SUCCESS_200,BLOCK_ACTIVATE_FAIL, null, null);
					} else
					{
						return new Response(SUCCESS, SUCCESS_200,BLOCK_INACTIVATE_FAIL, null, null);
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
	public Response listBlockMaster(Integer orgId, Integer offset,
			Integer recordPerPage) throws ApplicationException {
		try
		{
			Response res =  iBlockMasterDao.listBlockMaster(orgId, offset, recordPerPage);
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
	public Response getToTalBlockMaster(Integer orgId)
			throws ApplicationException {
		try
		{
			return iBlockMasterDao.getToTalBlockMaster(orgId);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response updateBlockMaster(BlockMasterDto blockMasterDto)
			throws ApplicationException {
		
		try{
			Response res = iBlockMasterDao.updateCheckBlockCodeAvaiable(blockMasterDto);
			if(res.getListObject() != null && res.getListObject().size() > 0){
				return new Response<>(ERROR, ERR_500,BLOCK_CODE_EXISIS, null, null);
			}else{
				blockMasterDto.setUpdatedDate(new Date().getTime());
				Response resdata =  iBlockMasterDao.updateBlockMaster(blockMasterDto);
				if (resdata.getStatus().equalsIgnoreCase(SUCCESS))
				{
					return new Response(SUCCESS, SUCCESS_200,BLOCK_UPDATE_SUCC, null, null);
				} else
				{
					return new Response(ERROR, ERR_500,BLOCK_UPDATE_FAIL, null, null);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERR_201,BLOCK_UPDATE_FAIL, null, null);
		
	}


}
