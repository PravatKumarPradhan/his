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
import com.param.adt.transfer.dto.TransferOTRequestDto;
import com.param.adt.transfer.service.ITransferToOTService;
import com.param.global.dto.AdmissionDto;

@Controller
@RequestMapping("/adt")
@SuppressWarnings({"rawtypes","unchecked"})
public class TransferToOTController implements ICommonConstants
{
	
	@Autowired
	ITransferToOTService iTransferRequestService;
	
	

	@RequestMapping(value="/getOTRequestListByAdmissionId" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getOTRequestListByAdmissionId(@RequestBody AdmissionDto admissionDto){
		try{			
			return iTransferRequestService.getOTRequestListByAdmissionId(admissionDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null,null);
	}
	
	@RequestMapping(value="/getOTRequestList" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getOTRequestList(@RequestBody AdmissionDto admissionDto){
		try{			
			return iTransferRequestService.getOTRequestList(admissionDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null,null);
	}
	
	@RequestMapping(value="/saveOTTransferRequest" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response saveOTTransferRequest(@RequestBody TransferOTRequestDto transferOTRequestDto){
		try{			
			return iTransferRequestService.saveOTTransferRequest(transferOTRequestDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null,null);
	}
	
	@RequestMapping(value="/getOTTransferRequestList" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getOTTransferRequestList(@RequestBody TransferOTRequestDto transferOTRequestDto){
		try{			
			return iTransferRequestService.getOTTransferRequestList(transferOTRequestDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null,null);
	}
	@RequestMapping(value="/getOTTransferRequestListByAdmissionId" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getOTTransferRequestListAdmissionId(@RequestBody TransferOTRequestDto transferOTRequestDto){
		try{			
			return iTransferRequestService.getOTTransferRequestListAdmissionId(transferOTRequestDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null,null);
	}
}
