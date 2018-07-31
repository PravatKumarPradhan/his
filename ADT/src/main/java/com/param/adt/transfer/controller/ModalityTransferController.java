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
import com.param.adt.transfer.dto.TransferModalityRequestDto;
import com.param.adt.transfer.service.IModalityTransferService;
import com.param.global.dto.AdmissionDto;
import com.param.global.dto.PatientSearchDto;

@Controller
@RequestMapping("/adt")
@SuppressWarnings({"rawtypes","unchecked"})
public class ModalityTransferController implements ICommonConstants{

	@Autowired
	IModalityTransferService iModalityTransferService;
	
	
	@RequestMapping(value="/searchAdmittedPatientByNameAndUhid" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response searchAdmittedPatientByNameAndUhid(@RequestBody AdmissionDto admissionDto){
		try{			
			return iModalityTransferService.searchAdmittedPatientByNameAndUhid(admissionDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null,null);
	}
	
	@RequestMapping(value="/getPatientServiceOrderListByAdmissionId" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getPatientServiceOrderListByAdmissionId(@RequestBody AdmissionDto admissionDto){
		try{			
			return iModalityTransferService.getPatientServiceOrderListByAdmissionId(admissionDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null,null);
	}
	
	@RequestMapping(value="/getAllPatientServiceOrderList" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getPatientServiceOrderList(@RequestBody AdmissionDto admissionDto){
		try{			
			return iModalityTransferService.getPatientServiceOrderList(admissionDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null,null);
	}
	
	@RequestMapping(value="/saveModalityTransferRequest" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response saveModalityTransferRequest(@RequestBody TransferModalityRequestDto transferModalityRequestDto){
		try{			
			return iModalityTransferService.saveModalityTransferRequest(transferModalityRequestDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null,null);
	}
	
	@RequestMapping(value="/getModalityTransferRequestList" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getModalityTransferRequestList(@RequestBody TransferModalityRequestDto transferModalityRequestDto){
		try{			
			return iModalityTransferService.getModalityTransferRequestList(transferModalityRequestDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null,null);
	}
	
	@RequestMapping(value="/searchPatientServiceOrdersByMulCriteria" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response searchPatientServiceOrdersByMulCriteria(@RequestBody PatientSearchDto patientSearchDto){
		try{			
			return iModalityTransferService.searchPatientServiceOrdersByMulCriteria(patientSearchDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null,null);
	}
}
