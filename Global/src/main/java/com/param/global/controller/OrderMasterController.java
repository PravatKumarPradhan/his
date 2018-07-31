package com.param.global.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dto.OrderMasterDto;
import com.param.global.service.IOrderMasterService;

@RestController
@RequestMapping(value="api/global")
@SuppressWarnings("rawtypes")
public class OrderMasterController implements ICommonConstants{

	@Autowired
	private IOrderMasterService iOrderMasterService;
	
	@RequestMapping(value="orderMaster",method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public Response saveOrderMaster(@RequestBody OrderMasterDto orderMasterDto) {
		try {
			//RequestJsonCheck.mapJsonToObject(orderMasterDto, OrderMasterDto.class);
			return iOrderMasterService.saveOrderMaster(orderMasterDto);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
}
