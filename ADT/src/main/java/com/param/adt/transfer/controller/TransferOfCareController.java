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
import com.param.adt.transfer.dto.TransferDto;
import com.param.adt.transfer.service.ITransferOfCareService;

@Controller
@RequestMapping("/adt")
@SuppressWarnings({"rawtypes","unchecked"})
public class TransferOfCareController implements ICommonConstants
{
	@Autowired
	ITransferOfCareService iTransferOfCareService;
	
	@RequestMapping(value="/saveTransferOfCareRequest" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response saveTransferOfCareRequest(@RequestBody TransferDto transferDto){
		try{			
			return iTransferOfCareService.saveTransferOfCareRequest(transferDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null,null);
	}
	
	@RequestMapping(value="/getTransferOfCareRequestList" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getTransferOfCareRequestList(@RequestBody TransferDto transferDto){
		try{			
			return iTransferOfCareService.getTransferOfCareRequestList(transferDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null,null);
	}
	
	@RequestMapping(value="/getTransferOfCareRequestListForDoctor" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getTransferOfCareRequestListForDoctor(@RequestBody TransferDto transferDto){
		try{			
			return iTransferOfCareService.getTransferOfCareRequestListForDoctor(transferDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null,null);
	}
	
	@RequestMapping(value="/acceptTransferOfCareRequest" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response acceptTransferOfCareRequest(@RequestBody TransferDto transferDto){
		try{			
			return iTransferOfCareService.acceptTransferOfCareRequest(transferDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null,null);
	}
	
	@RequestMapping(value="/rejectTransferOfCareRequest" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response rejectTransferOfCareRequest(@RequestBody TransferDto transferDto){
		try{			
			return iTransferOfCareService.rejectTransferOfCareRequest(transferDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null,null);
	}
	
	@RequestMapping(value="/acceptConsultOrderRequest" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response acceptConsultOrderRequest(@RequestBody TransferDto transferDto){
		try{			
			return iTransferOfCareService.acceptConsultOrderRequest(transferDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null,null);
	}
	
	@RequestMapping(value="/searchConsultOrderRequestByAdmissionId" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response searchConsultOrderRequestByAdmissionId(@RequestBody TransferDto transferDto){
		try{			
			return iTransferOfCareService.searchConsultOrderRequestByAdmissionId(transferDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null,null);
	}
	
}
