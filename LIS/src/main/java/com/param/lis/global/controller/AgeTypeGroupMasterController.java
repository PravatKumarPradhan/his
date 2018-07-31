package com.param.lis.global.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.Response;
import com.param.lis.global.dto.AgeTypeGroupMasterDto;
import com.param.lis.global.service.IAgeTypeGroupMasterService;

@RestController
@SuppressWarnings("rawtypes")
@RequestMapping("/LIS/global")
public class AgeTypeGroupMasterController implements ICommonConstants, IError{

	@Autowired
	private IAgeTypeGroupMasterService iAgeTypeGroupMasterService;
	

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/listOfAgeTypeGroup", method = RequestMethod.GET)
	public @ResponseBody Response getListAgeTypeGroupMaster()
	{
		Response<AgeTypeGroupMasterDto, Integer> response = null;
		try
		{
			response = iAgeTypeGroupMasterService.getListAgeTypeGroupMaster();
			return response;
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	


}
