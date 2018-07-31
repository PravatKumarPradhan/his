package com.param.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.param.global.common.Response;
import com.param.service.dto.PackageSearchRequestDto;
import com.param.service.services.IMPackageMasterService;
import com.param.service.services.IPackageConsumptionService;

@RestController
@RequestMapping(value="/api/packages")
public class PacakgeConsumptionController {
	
	@Autowired
	private IMPackageMasterService iMPackageMasterService; 
	
	@Autowired
	private IPackageConsumptionService iPackageConsumptionService;

	/**
	 * SEARCH PACKAGE SERVICE - AUTOCOMPLETE
	 * @param organisationId
	 * @param unitId
	 * @param packageTypeId
	 * @param searchKeyword
	 * @return
	 */
	@RequestMapping(value="/{packageTypeId}/{searchKeyword}")
	public Response getPackageServiceByPackageType(
			@RequestHeader(value="organisationId")Integer organisationId,
			@RequestHeader(value="unitId")Integer unitId,
			@PathVariable(value="pacakgeTypeId") Integer packageTypeId,
			@PathVariable(value="searchKeyword") String searchKeyword){
		try{
			PackageSearchRequestDto packageSearchRequestDto = new PackageSearchRequestDto();
			packageSearchRequestDto.setPackageTypeId(packageTypeId);
			packageSearchRequestDto.setPackageName(searchKeyword);
			packageSearchRequestDto.setOrgId(organisationId);
			packageSearchRequestDto.setUnitId(unitId);
			
			return iPackageConsumptionService.getPackageServiceByPackageType(packageSearchRequestDto);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		return null;
	}
	
	@RequestMapping(value="/{packageId}")
	public Response getPackageServiceDetailsByPackageId(@PathVariable(value="packageId")Integer packageId){
		try{
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}
	
}
