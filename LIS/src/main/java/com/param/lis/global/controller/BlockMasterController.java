package com.param.lis.global.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.Response;
import com.param.lis.global.dto.BlockMasterDto;
import com.param.lis.global.dto.GlobalCommonDto;
import com.param.lis.global.service.IBlockMasterService;

@RestController
@SuppressWarnings("rawtypes")
@RequestMapping("/LIS/global")
public class BlockMasterController implements ICommonConstants, IError{

	@Autowired
	private IBlockMasterService iBlockMasterService;
	
	@RequestMapping(value = "/addBlock" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public Response addBlockMaster(@RequestBody BlockMasterDto BlockMasterDto){
		try{
			return iBlockMasterService.addBlockMaster(BlockMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERR_201,BLOCK_ADD_SUCC, null, null);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getaddBlockMasterById/{orgId}/{blockId}", method = RequestMethod.GET)
	public @ResponseBody Response getaddBlockMasterById(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "blockId") Integer blockId)
	{
		Response<GlobalCommonDto, Integer> response = null;
		try
		{
			response = iBlockMasterService.getBlockMasterById(orgId, blockId);
			return response;
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}	
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/updateBlockMaster", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateBlockMaster(@RequestBody BlockMasterDto BlockMasterDto)
	{
		Response<BlockMasterDto, Integer> response = null;
		try
		{
			response = iBlockMasterService.updateBlockMaster(BlockMasterDto);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		return response;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/activeInactiveBlock/{orgId}/{blockId}/{blockStatus}", method = RequestMethod.GET)
	public @ResponseBody Response ActivateInactivateBlockMaster(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "blockId") Integer blockId,
			@PathVariable(value = "blockStatus") Character blockStatus)
	{
		Response<BlockMasterDto, Integer> response = null;
		try
		{
			response = iBlockMasterService.ActivateInactivateBlockMaster(orgId, blockId, blockStatus);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		return response;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/listBlock/{orgId}/{offset}/{recordPerPage}", method = RequestMethod.GET)
	public @ResponseBody Response listBlockMaster(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "offset") Integer offset,
			@PathVariable(value = "recordPerPage") Integer recordPerPage)
	{
		Response<GlobalCommonDto, Integer> response = null;
		try
		{
			response = iBlockMasterService.listBlockMaster(orgId, offset, recordPerPage);
			return response;
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getToTallistBlock/{orgId}", method = RequestMethod.GET)
	public @ResponseBody Response getToTallistBlockMaster(@PathVariable(value= "orgId") Integer orgId)
		{
			Response<BlockMasterDto, Integer> response = null;
			try
				{
					response = iBlockMasterService.getToTalBlockMaster(orgId);
					return response;
				}
			catch (Exception e)
				{
					e.printStackTrace();
					return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
				}
		}


}
