package com.param.billing.unit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.param.billing.exception.ServiceException;
import com.param.billing.unit.dto.DoctorShareReqDto;
import com.param.billing.unit.dto.IndividualDoctorShareReqDto;
import com.param.billing.unit.service.IGlobalDoctorShareService;
import com.param.billing.unit.service.IIndividualDoctorShareService;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.service.dto.CommonDto;


@RestController
@RequestMapping(value="unit/DoctorShare")
@SuppressWarnings({"rawtypes","unchecked"})
public class GlobalDoctorShareMasterController  implements ICommonConstants{
	@Autowired
	private IGlobalDoctorShareService iGlobalDoctorShareService;
	
	@Autowired
	private IIndividualDoctorShareService iIndividualDoctorShareService;
	
	@RequestMapping(value = "/saveGlobalDoctorShare" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response saveGlobalDoctorShare(@RequestBody DoctorShareReqDto doctorShareReqDto){
		Response<DoctorShareReqDto, Integer> response = null;
		try{
			response= iGlobalDoctorShareService.saveGlobalDoctorShare(doctorShareReqDto);
			return response;
		}catch(ServiceException se){
			se.printStackTrace();
			throw se;
		}catch(Exception e){
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}
	
	
	@RequestMapping(value = "/saveIndividualDoctorShare" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response saveIndividualDoctorShare(@RequestBody IndividualDoctorShareReqDto individualDoctorShareReqDto){
		Response<IndividualDoctorShareReqDto, Integer> response = null;
		try{
			 response= iIndividualDoctorShareService.saveIndividualDoctorShare(individualDoctorShareReqDto);
			return response;
		}catch(ServiceException se){
			se.printStackTrace();
			throw se;
		}catch(Exception e){
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}
	
	
/*	@RequestMapping(value="/searchCompanyContract" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response searchCompanyContract(@RequestBody ServiceForCompnayContarctReqDto reqDto){
		try{
			return iCompanyContractService.searchCompanyContract(reqDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}*/
	
	
	@RequestMapping(value = "/getDoctorGradeMaster/{orgId}/{orgUnitId}", method = RequestMethod.GET)
	public @ResponseBody Response getDoctorGradeMaster(@PathVariable(value = "orgId") Integer orgId,@PathVariable(value = "orgUnitId") Integer orgUnitId)
	{
		Response<CommonDto, Integer> response = null;
		try
		{
			response = iGlobalDoctorShareService.getDoctorGradeMaster(orgId, orgUnitId);
			return response;
		} catch (Exception e)
		{
			return new Response(ERROR, ERROR, ERROR, null, null);
		}
	}
	
	
}
