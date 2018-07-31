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
import com.param.adt.transfer.service.IPendingTransferRequestService;

@Controller
@RequestMapping("/adt")
@SuppressWarnings({"rawtypes","unchecked"})
public class PendingTransferRequestController implements ICommonConstants
{
	@Autowired
	IPendingTransferRequestService iPendingTransferRequestService;
	
	@RequestMapping(value = "/getPendingTransferRequestList", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getPendingTransferRequestListForDoctor(@RequestBody TransferDto transferDto) {
		try {
			return iPendingTransferRequestService.getPendingTransferRequestListForDoctor(transferDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new Response(ERROR, null, null, null, null);
	}
	
	@RequestMapping(value = "/getPendingTransferRequestListForADT", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getPendingTransferRequestListForADT(@RequestBody TransferDto transferDto) {
		try {
			return iPendingTransferRequestService.getPendingTransferRequestListForADT(transferDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}
	
	@RequestMapping(value = "/acceptTransferRequestForADT", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response acceptTransferRequestForADT(@RequestBody TransferDto transferDto) {
		try {
			return iPendingTransferRequestService.acceptTransferRequestForADT(transferDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}
	
	@RequestMapping(value = "/rejectTransferRequestForADT", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response rejectTransferRequestForADT(@RequestBody TransferDto transferDto) {
		try {
			return iPendingTransferRequestService.rejectTransferRequestForADT(transferDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}
	
	
/*	@RequestMapping(value = "/acceptRejectTransferRequest", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response acceptRejectTransferRequest(@RequestBody TransferDto transferDto) {
		try {
			return iPendingTransferRequestService.acceptRejectTransferRequest(transferDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}*/
	
	
	@RequestMapping(value = "/acceptTransferRequestForDoctor", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response acceptTransferRequestForDoctor(@RequestBody TransferDto transferDto) {
		try {
			return iPendingTransferRequestService.acceptTransferRequestForDoctor(transferDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}
	
	@RequestMapping(value = "/rejectTransferRequestForDoctor", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response rejectTransferRequestForDoctor(@RequestBody TransferDto transferDto) {
		try {
			return iPendingTransferRequestService.rejectTransferRequestForDoctor(transferDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}
	

	
	@RequestMapping(value = "/acceptTransferRequest", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response acceptTransferRequest(@RequestBody TransferDto transferDto) {
		try {
			return iPendingTransferRequestService.acceptTransferRequest(transferDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}
	
	@RequestMapping(value = "/rejectTransferRequest", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response rejectTransferRequest(@RequestBody TransferDto transferDto) {
		try {
			return iPendingTransferRequestService.rejectTransferRequest(transferDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}
	
	
	@RequestMapping(value = "/getFinalTransferRequestList", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getFinalTransferRequestList(@RequestBody TransferDto transferDto) {
		try {
			return iPendingTransferRequestService.getFinalTransferRequestList(transferDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}
	
	@RequestMapping(value = "/getFinalTransferRequestListByAdmissionId", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getFinalTransferRequestListByAdmissionId(@RequestBody TransferDto transferDto) {
		try {
			return iPendingTransferRequestService.getFinalTransferRequestListByAdmission(transferDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}
	
	@RequestMapping(value = "/acceptFinalTransferRequest", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response acceptFinalTransferRequest(@RequestBody TransferDto transferDto) {
		try {
			return iPendingTransferRequestService.acceptFinalTransferRequest(transferDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}
	
	@RequestMapping(value = "/rejectFinalTransferRequest", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response rejectFinalTransferRequest(@RequestBody TransferDto transferDto) {
		try {
			return iPendingTransferRequestService.rejectFinalTransferRequest(transferDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}
	
	
	
}
