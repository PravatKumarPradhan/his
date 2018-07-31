package com.param.global.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dto.ServiceMasterDto;
import com.param.global.dto.ServiceSearchReqDto;
import com.param.global.dto.UnitServiceMapperDto;
import com.param.global.service.IUnitServiceMapperService;

@RestController
@RequestMapping("/global")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class UnitServiceMapperController implements ICommonConstants{
	@Autowired
	private IUnitServiceMapperService iUnitServiceMapperService;
	
	/***
	 * Controller method to Add UnitServiceMapper
	 * @param unitServiceMapperDto
	 * @return Response
	 */
	@RequestMapping(value="/UnitServiceMapper", method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public Response saveUnitServiceMapper(@RequestBody UnitServiceMapperDto unitServiceMapperDto) {
		try {
			return iUnitServiceMapperService.saveUnitServiceMapper(unitServiceMapperDto);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR, COMMON_ERROR_MESSAGE, null, null);
	}
	
	/**
	 * Controller method to search Services by speciality, subSpeciality, unitId, orgId (Autocomplete)
	 * @param searchKeyword
	 * @param specialityId
	 * @param subSplecialityId
	 * @param unitId
	 * @param orgId
	 * @return Response
	 */
	@RequestMapping(value="/UnitServiceMapper/search/{searchKeyword}/speciality/{specialityId}/subSpeciality/{subSplecialityId}/unit/{unitId}/org/{orgId}", method = RequestMethod.GET)
	public Response searchUnitServiceMapper(@PathVariable("searchKeyword") String searchKeyword,
			@PathVariable("specialityId") Integer specialityId,
			@PathVariable("subSplecialityId") Integer subSplecialityId,
			@PathVariable("unitId")Integer unitId,
			@PathVariable("orgId") Integer orgId) {
		try {
			return iUnitServiceMapperService.searchServiceMasterBySpecialitySubSplecialityUnitAndOrg(searchKeyword, specialityId, subSplecialityId, unitId, orgId);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR, COMMON_ERROR_MESSAGE, null, null);
	}
	
	/**
	 * Controller method to get Mapped ServicesList By Unit (Paginated)
	 * @param unitId
	 * @param orgId
	 * @param offset
	 * @param noOfRecordsPerPage
	 * @return Response
	 */
	@RequestMapping(value="/UnitServiceMapper/unit/{unitId}/org/{orgId}/{offset}/{noOfRecordsPerPage}", method = RequestMethod.GET)
	public Response getMappedServicesListByUnit(@PathVariable("unitId") Integer unitId,
			@PathVariable("orgId") Integer orgId,
			@PathVariable("offset")Integer offset,
			@PathVariable("noOfRecordsPerPage") Integer noOfRecordsPerPage) {
		try {
			UnitServiceMapperDto unitServiceMapperDto = new UnitServiceMapperDto();
			unitServiceMapperDto.setUnitId(unitId);
			unitServiceMapperDto.setOrgnisationId(orgId);
			unitServiceMapperDto.setOffset(offset);
			unitServiceMapperDto.setRecordPerPage(noOfRecordsPerPage);
			return iUnitServiceMapperService.getMappedServicesListByUnit(unitServiceMapperDto);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR, COMMON_ERROR_MESSAGE, null, null);
	}
	
	/**
	 * Controller method to get Count of Mapped Services List By Unit (for pagination)
	 * @param unitId
	 * @param orgId
	 * @return Response
	 */
	@RequestMapping(value="/UnitServiceMapper/count/unit/{unitId}/org/{orgId}", method = RequestMethod.GET)
	public Response getCountOfMappedServicesListByUnit(@PathVariable("unitId") Integer unitId,
			@PathVariable("orgId") Integer orgId){
		try {
			UnitServiceMapperDto unitServiceMapperDto = new UnitServiceMapperDto();
			unitServiceMapperDto.setUnitId(unitId);
			unitServiceMapperDto.setOrgnisationId(orgId);
			return iUnitServiceMapperService.getCountOfMappedServicesListByUnit(unitServiceMapperDto);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR, COMMON_ERROR_MESSAGE, null, null);
	}
	
	/**
	 * Controller method to Update UnitServiceMapper Status
	 * @param serviceId
	 * @param status
	 * @param unitId
	 * @param orgId
	 * @return Response
	 */
	@RequestMapping(value="/UnitServiceMapper/serviceId/{serviceId}/status/{status}/unit/{unitId}/org/{orgId}", method = RequestMethod.PUT)
	public Response updateUnitServiceMapperStatus(@PathVariable("serviceId") Integer serviceId,
												  @PathVariable("status") char status,
												  @PathVariable("unitId") Integer unitId,
												  @PathVariable("orgId") Integer orgId) {
		try {
			UnitServiceMapperDto unitServiceMapperDto = new UnitServiceMapperDto();
			unitServiceMapperDto.setServiceId(serviceId);
			unitServiceMapperDto.setStatus(status);
			unitServiceMapperDto.setUnitId(unitId);
			unitServiceMapperDto.setOrgnisationId(orgId);
			return iUnitServiceMapperService.updateUnitServiceMapperStatus(unitServiceMapperDto);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR, COMMON_ERROR_MESSAGE, null, null);
	}
	
	@RequestMapping(value="/serviceMaster/{searchKeyword}/unit/{unitId}/org/{orgId}", method = RequestMethod.GET)
	public Response searchServicesByNameAndCode(@PathVariable("searchKeyword") String searchKeyword,
												  @PathVariable("unitId") Integer unitId,
												  @PathVariable("orgId") Integer orgId) {
		try {
			ServiceMasterDto serviceMasterDto = new ServiceMasterDto();
			serviceMasterDto.setSearchKeyword(searchKeyword);
			serviceMasterDto.setUnitId(unitId);
			serviceMasterDto.setOrganizationId(orgId);
			return iUnitServiceMapperService.searchServicesByNameAndCode(serviceMasterDto);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR, COMMON_ERROR_MESSAGE, null, null);
	}
	
	@RequestMapping(value="/serviceMaster/{searchKeyword}/unit/{unitId}/org/{orgId}", method = RequestMethod.POST)
	public Response searchActiveServices(@PathVariable("searchKeyword") String searchKeyword,
												  @PathVariable("unitId") Integer unitId,
												  @PathVariable("orgId") Integer orgId, @RequestBody ServiceSearchReqDto serviceSearchReqDto) {
		try {
			serviceSearchReqDto.setServiceName(searchKeyword);
			serviceSearchReqDto.setUnitId(unitId);
			serviceSearchReqDto.setOrganisationId(orgId);
			return iUnitServiceMapperService.searchActiveServices(serviceSearchReqDto);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR, COMMON_ERROR_MESSAGE, null, null);
	}
	
	@RequestMapping(value="/searchUnitServiceByMultipleSpecialityAndSubSpeciality", method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public Response searchUnitServiceByMultipleSpecialityAndSubSpeciality(@RequestBody UnitServiceMapperDto unitServiceMapperDto) {
		try {
			return iUnitServiceMapperService.searchUnitServiceByMultipleSpecialityAndSubSpeciality(unitServiceMapperDto);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR, COMMON_ERROR_MESSAGE, null, null);
	}
}
