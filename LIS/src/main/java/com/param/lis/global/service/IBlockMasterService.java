package com.param.lis.global.service;

import in.param.exception.ApplicationException;

import com.param.lis.global.common.Response;
import com.param.lis.global.dto.BlockMasterDto;

@SuppressWarnings("rawtypes")
public interface IBlockMasterService {
	public Response addBlockMaster(BlockMasterDto blockMasterDto)throws ApplicationException;
	public Response getBlockMasterById(Integer orgId, Integer blockId) throws ApplicationException;
	public Response updateBlockMaster(BlockMasterDto blockMasterDto) throws ApplicationException;
	public Response ActivateInactivateBlockMaster(Integer orgId, Integer blockId, Character blockStatus) throws ApplicationException;
	public Response listBlockMaster(Integer orgId, Integer offset, Integer recordPerPage) throws ApplicationException;
	public Response getToTalBlockMaster(Integer orgId) throws ApplicationException;
}
