package com.param.adt.admission.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.param.adt.admission.service.IMapOfBedService;
import com.param.adt.common.ICommonConstants;
import com.param.adt.common.Response;
import com.param.adt.transfer.dto.TransferDto;

@Controller
@RequestMapping("/adt")
@SuppressWarnings({"rawtypes","unchecked"})
public class MapOfBedController implements ICommonConstants
{
	@Autowired
	IMapOfBedService iMapOfBedService;
	
	
	@RequestMapping(value = "/getTransferRequestListByAdmissoinId", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getTransferRequestListByAdmissoinId(@RequestBody TransferDto transferDto) {
		try {
			return iMapOfBedService.getTransferRequestListByAdmissoinId(transferDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new Response(ERROR, null, null, null, null);
	}
	
}
