package com.param.lis.global.dao;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;
import com.param.entity.lis.global.BlockMaster;
import com.param.lis.global.common.Response;
import com.param.lis.global.dto.BlockMasterDto;
import com.param.lis.global.dto.ReportTypeMasterDto;

@SuppressWarnings("rawtypes")
public interface IBlockMasterDao extends IGenericDao<BlockMaster, Integer>{
	
	public Response addBlockMaster(BlockMasterDto blockMasterDto)throws ApplicationException;
	public Response getBlockMasterById(Integer orgId, Integer blockId) throws ApplicationException;
	public Response updateBlockMaster(BlockMasterDto blockMasterDto) throws ApplicationException;
	public Response updateCheckBlockCodeAvaiable(BlockMasterDto blockMasterDto) throws ApplicationException;
	public Response ActivateInactivateBlockMaster(Integer orgId, Integer blockId, Character blockStatus) throws ApplicationException;
	public Response listBlockMaster(Integer orgId, Integer offset, Integer recordPerPage) throws ApplicationException;
	public Response checkBlockMaster(BlockMasterDto blockMasterDto) throws ApplicationException;
	public Response getToTalBlockMaster(Integer orgId) throws ApplicationException;
}
