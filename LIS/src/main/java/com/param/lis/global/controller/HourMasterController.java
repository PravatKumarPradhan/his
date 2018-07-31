package com.param.lis.global.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.Response;
import com.param.lis.global.dto.HourMasterDto;
import com.param.lis.global.service.IHourMasterService;

@RestController
@SuppressWarnings("rawtypes")
@RequestMapping("/LIS/global")
public class HourMasterController implements ICommonConstants, IError{

	@Autowired
	private IHourMasterService iHourMasterService;
	

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/listOfHour", method = RequestMethod.GET)
	public @ResponseBody Response getListHourMaster()
	{
		Response<HourMasterDto, Integer> response = null;
		try
		{
			response = iHourMasterService.getListHourMaster();
			return response;
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	


}
