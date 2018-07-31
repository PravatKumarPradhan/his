package com.param.billing.unit.controller;

import org.json4s.jackson.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.param.billing.exception.ServiceException;
import com.param.billing.unit.dto.DoctorShareReqDto;
import com.param.billing.unit.service.ICompanyContractService;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dto.CompanyMasterDto;
import com.param.service.dto.CompanyContractReqDto;
import com.param.service.dto.ServiceForCompnayContarctReqDto;


@RestController
@RequestMapping(value="unit/CompanyContract")
@SuppressWarnings({"rawtypes","unchecked"})
public class CompanyContractController  implements ICommonConstants{
	@Autowired
	private ICompanyContractService iCompanyContractService;
	
	@RequestMapping(value="/getCompanyMasterList" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getCompanyMasterList(@RequestBody CompanyMasterDto companyMasterDto){
		Response<CompanyMasterDto, Integer> response = null;
		try{
			 response= iCompanyContractService.getCompanyMasterList(companyMasterDto);
			return response;
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	
	@RequestMapping(value="/getAssociateCompanyMaster" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getAssociateCompanyMaster(@RequestBody CompanyMasterDto companyMasterDto){
		try{
			return iCompanyContractService.getAssociateCompanyMaster(companyMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/getVisitTypeList" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getVisitTypeList(@RequestBody CompanyMasterDto companyMasterDto){
		try{
			return iCompanyContractService.getVisitTypeList(companyMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	
	@RequestMapping(value="/getGradeListByAssociateCompanyId" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getGradeListByAssociateCompanyId(@RequestBody CompanyMasterDto companyMasterDto){
		try{
			return iCompanyContractService.getGradeListByAssociateCompanyId(companyMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value = "/createCompanyContarct" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response createCompanyContarct(@RequestBody CompanyContractReqDto reqDto){
		try{
			return iCompanyContractService.createCompanyContarct(reqDto);
		}catch(ServiceException se){
			se.printStackTrace();
			throw se;
		}catch(Exception e){
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}
	
	@RequestMapping(value="/searchCompanyContract" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response searchCompanyContract(@RequestBody ServiceForCompnayContarctReqDto reqDto){
		try{
			return iCompanyContractService.searchCompanyContract(reqDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	
	
}
