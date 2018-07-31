package com.param.adt.transfer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.param.adt.common.ICommonConstants;
import com.param.adt.common.Response;
import com.param.adt.master.global.dto.BedMasterDto;
import com.param.adt.transfer.dto.TransferDto;
import com.param.adt.transfer.service.IBedToBedTransferService;

@Controller
@RequestMapping("/adt")
@SuppressWarnings({"rawtypes","unchecked"})
public class BedToBedTransferController implements ICommonConstants{
	
	@Autowired
	IBedToBedTransferService iBedToBedTransferService;
	
	@RequestMapping(value = "/getInitiateTransferRequestList", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getInitiateTransferRequestList(@RequestBody TransferDto transferDto) {
		try {
			return iBedToBedTransferService.getInitiateTransferRequestList(transferDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/getBedListByWardIdForBedtoBedTransfer", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getBedListByWardId(@RequestBody BedMasterDto bedMasterDto) {
		try {
			
			return iBedToBedTransferService.getBedListByWardId(bedMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new Response(ERROR, null, null, null, null);
	}
	
}
