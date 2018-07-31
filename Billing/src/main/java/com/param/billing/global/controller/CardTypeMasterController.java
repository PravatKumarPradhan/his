package com.param.billing.global.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.param.billing.common.ICommonConstants;
import com.param.billing.common.Response;
import com.param.billing.global.dto.CardTypeMasterDto;
import com.param.billing.global.service.ICardTypeMasterService;

@RestController
@RequestMapping("api/billing")
public class CardTypeMasterController implements ICommonConstants{

	@Autowired
	private ICardTypeMasterService iCardTypeMasterService;
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="cardTypeMaster",method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	public Response getActiveBankMasterList(@RequestBody CardTypeMasterDto cardTypeMasterDto) {
		try {
			return iCardTypeMasterService.gerActiveCardTypeMaster(cardTypeMasterDto);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
}
