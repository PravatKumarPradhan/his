package com.param.global.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dto.ReferalDetailsDto;
import com.param.global.service.IReferalDetailsService;

@Controller
@RequestMapping("api/public")
@SuppressWarnings("rawtypes")
public class ReferalDetailsController implements ICommonConstants{

	@Autowired
	IReferalDetailsService iReferalDetailsService;
	
	@RequestMapping(value="saveReferalDetails", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public Response saveReferalDetails(@RequestBody ReferalDetailsDto referalDetailsDto) {
		try {
			return iReferalDetailsService.saveReferalDetails(referalDetailsDto);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	
}
