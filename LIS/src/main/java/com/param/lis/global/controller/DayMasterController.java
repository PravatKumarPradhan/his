package com.param.lis.global.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;

@RestController
@SuppressWarnings("rawtypes")
@RequestMapping("/LIS/global")
public class DayMasterController implements ICommonConstants, IError{

	/*@Autowired
	private IDayMasterService iDayMasterService;*/
	

	/*@SuppressWarnings("unchecked")
	@RequestMapping(value = "/listOfDay", method = RequestMethod.GET)
	public @ResponseBody Response getListHourMaster()
	{
		Response<DayMasterDto, Integer> response = null;
		try
		{
			response = iDayMasterService.getListDayMaster();
			return response;
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}*/
	


}
