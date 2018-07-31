package com.param.service.controller;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.param.billing.exception.ServiceException;
import com.param.global.common.Response;
import com.param.global.dto.ServiceSearchReqDto;
import com.param.service.dto.AllInclusivePackageReqDto;
import com.param.service.dto.EhcPackageReqDto;
import com.param.service.dto.EitherOrPackageReqDto;
import com.param.service.dto.MultiencounterPackageReqDto;
import com.param.service.dto.PackageWithCapReqDto;
import com.param.service.dto.ServiceForPackageReqDto;
import com.param.service.services.IMPackageMasterService;

@RestController
@RequestMapping(value = "/api/packages")
@SuppressWarnings({"rawtypes"})
public class MPackageController {

	@Autowired
	private IMPackageMasterService iMPackageMasterService;
	
	@RequestMapping(value = "/ehc" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public Response createEhcPackage(@RequestBody EhcPackageReqDto reqDto){
		try{
			return iMPackageMasterService.createEhcPackage(reqDto);
		}catch(ServiceException se){
			se.printStackTrace();
			throw se;
		}catch(Exception e){
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/services/search" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public Response searchServiceForPackages(@RequestBody ServiceForPackageReqDto reqDto){
		try{
			return iMPackageMasterService.searchServiceForPackage(reqDto);
		}catch(ServiceException se){
			se.printStackTrace();
			throw se;
		}catch(Exception e){
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}
	
	
	@RequestMapping(value = "/eitheror" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public Response createEitherOrPackage(@RequestBody EitherOrPackageReqDto reqDto){
		try{
			return iMPackageMasterService.createEitherOrPackage(reqDto);
		}catch(ServiceException se){
			se.printStackTrace();
			throw se;
		}catch(Exception e){
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/ehcPackages" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public Response getEHCPackagesList(@RequestBody ServiceForPackageReqDto reqDto){
		try{
			return iMPackageMasterService.getEHCPackagesList(reqDto);
		}catch(ServiceException se){
			se.printStackTrace();
			throw se;
		}catch(Exception e){
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/packagewithcap" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public Response createPackageWithCap(@RequestBody PackageWithCapReqDto reqDto){
		try{
			return iMPackageMasterService.createPackageWithCap(reqDto);
		}catch(ServiceException se){
			se.printStackTrace();
			throw se;
		}catch(Exception e){
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/multiencounter" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public Response createMultiEncounterPackage(@RequestBody MultiencounterPackageReqDto reqDto){
		try{
			return iMPackageMasterService.createMultiencouterPackage(reqDto);
		}catch(ServiceException se){
			se.printStackTrace();
			throw se;
		}catch(Exception e){
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/allInclusive" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public Response saveAllInclusivePackage(@RequestBody AllInclusivePackageReqDto reqDto){
		try{
			return iMPackageMasterService.SaveAllInclusivePackage(reqDto);
		}catch(ServiceException se){
			se.printStackTrace();
			throw se;
		}catch(Exception e){
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/bill/services/search" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public Response serviceSearchForBilling(@RequestBody ServiceSearchReqDto reqDto){
		try{
			return iMPackageMasterService.searchServicesForBilling(reqDto);
		}catch(ServiceException se){
			se.printStackTrace();
			throw se;
		}catch(Exception e){
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/bill/package/{ids}" , method = RequestMethod.GET)
	public Response packageDetailsByIdForBill(@PathVariable("ids") String[] packageIds){
		try{
			List<String> listStrIds = Arrays.asList(packageIds);
			List<Integer> intPackageIds = new LinkedList<>();
			listStrIds.stream().forEach(i -> intPackageIds.add(Integer.parseInt(i)));
			return iMPackageMasterService.getPackageDetailsByIdForBilling(intPackageIds);
		}catch(ServiceException se){
			se.printStackTrace();
			throw se;
		}catch(Exception e){
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}
	
	@RequestMapping(value="/patientId/{patientId}/encounterId/{encounterId}/visitTypeId/{visitTypeId}")
	public Response getExistingActivePackageByPatientEncounter(@PathVariable("patientId") Integer patientId,
			@PathVariable("encounterId") Integer encounterId, @PathVariable("visitTypeId") Integer visitTypeId,
			@RequestHeader("organisationId")Integer organisationId, @RequestHeader("unitId") Integer unitId){
		try{
			return iMPackageMasterService.getExistingActivePackageByPatientEncounter(patientId, encounterId, visitTypeId,organisationId, unitId);
		}catch(Exception e){
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}
}
