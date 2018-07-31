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
import com.param.adt.transfer.model.TransferRequest;
import com.param.adt.transfer.service.ITransferToICUService;
import com.param.global.dto.DoctorMasterDto;

@Controller
@RequestMapping("/adt")
@SuppressWarnings({"rawtypes","unchecked"})
public class TransferToICUController implements ICommonConstants
{
	@Autowired
	ITransferToICUService iTransferToICUService;
	
	
	@RequestMapping(value="/getDoctorForICU" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getDoctorForICU(@RequestBody DoctorMasterDto doctorMasterDto){
		try{			
			return iTransferToICUService.getDoctorForICU(doctorMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null,null);
	}
	
	@RequestMapping(value="/generateTransferRequestForICUTransfer" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response generateTransferRequestForICUTransfer(@RequestBody TransferDto transferDto){
		try{			
			Response res= iTransferToICUService.saveICUTransferRequest(transferDto);
			TransferRequest transferRrquest=(TransferRequest) res.getObject();
			transferDto.setTransferRequestId(transferRrquest.getTransferRequestId());
			if(res.getStatus().equals(SUCCESS))
				return iTransferToICUService.saveICUTransfer(transferDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null,null);
	}
	
	@RequestMapping(value="/getICUTransferRequestList" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getICUTransferRequestList(@RequestBody TransferDto transferDto){
		try{			
			
				return iTransferToICUService.getICUTransferRequestList(transferDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null,null);
	}
	
	@RequestMapping(value="/getICUTransferRequestListByAdmissionId" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getICUTransferRequestListByAdmissionId(@RequestBody TransferDto transferDto){
		try{			
			
				return iTransferToICUService.getICUTransferRequestListByAdmissionId(transferDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null,null);
	}
	
	@RequestMapping(value="/getICUBedsByIUCTypeId" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getICUBedsByIUCTypeId(@RequestBody TransferDto transferDto){
		try{			
			
				return iTransferToICUService.getICUBedsByIUCTypeId(transferDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null,null);
	}
	
}
