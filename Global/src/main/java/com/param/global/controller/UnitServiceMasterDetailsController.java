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
import com.param.global.dto.UnitServiceMasterDetailsDto;
import com.param.global.service.IUnitServiceMasterDetailsService;

@RestController
@RequestMapping(value="api/global")
@SuppressWarnings({ "rawtypes"})
public class UnitServiceMasterDetailsController implements ICommonConstants{
	@Autowired
	private IUnitServiceMasterDetailsService iUnitServiceMasterDetailsService;
	
	/**
	 * Controller method to Save UnitServiceMasterDetails
	 * @param unitServiceMasterDetailsDto
	 * @return Response
	 */
	@RequestMapping(value="/UnitServiceMasterDetails",method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public Response saveUnitServiceMasterDetails(@RequestBody UnitServiceMasterDetailsDto unitServiceMasterDetailsDto) {
		try {
			return iUnitServiceMasterDetailsService.saveUnitServiceMasterDetails(unitServiceMasterDetailsDto);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	/**
	 * controller method to get UnitServiceMasterDetails List(Paginated)
	 * @param orgId
	 * @param unitId
	 * @param offset
	 * @param recordPerPage
	 * @return Response
	 */
	@RequestMapping(value="/UnitServiceMasterDetails/org/{orgId}/unit/{unitId}/{offset}/{recordPerPage}",method=RequestMethod.GET)
	public Response getPaginatedUnitServiceMasterDetailsList(@PathVariable("orgId") Integer orgId,
				@PathVariable("unitId") Integer unitId,
				@PathVariable("offset") Integer offset,
				@PathVariable("recordPerPage") Integer recordPerPage) {
		try {
			UnitServiceMasterDetailsDto unitServiceMasterDetailsDto = new UnitServiceMasterDetailsDto();
			unitServiceMasterDetailsDto.setOrganizationId(orgId);
			unitServiceMasterDetailsDto.setUnitId(unitId);
			unitServiceMasterDetailsDto.setOffset(offset);
			unitServiceMasterDetailsDto.setRecordPerPage(recordPerPage);
			return iUnitServiceMasterDetailsService.getPaginatedUnitServiceMasterDetailsList(unitServiceMasterDetailsDto);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	/**
	 * Controller method to get UnitServiceMasterDetails Count(for Paginatation)
	 * @param orgId
	 * @param unitId
	 * @return Response
	 */
	@RequestMapping(value="/UnitServiceMasterDetails/count/org/{orgId}/unit/{unitId}",method=RequestMethod.GET)
	public Response getUnitServiceMasterDetailsCount(@PathVariable("orgId") Integer orgId, @PathVariable("unitId") Integer unitId) {
		try {
			UnitServiceMasterDetailsDto unitServiceMasterDetailsDto = new UnitServiceMasterDetailsDto();
			unitServiceMasterDetailsDto.setOrganizationId(orgId);
			unitServiceMasterDetailsDto.setUnitId(unitId);
			return iUnitServiceMasterDetailsService.getUnitServiceMasterDetailsCount(unitServiceMasterDetailsDto);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	/***
	 * Controller method to get UnitServiceMasterDetails By Id
	 * @param unitServiceMasterDetailsId
	 * @return Response
	 */
	@RequestMapping(value="/UnitServiceMasterDetails/{UnitServiceMasterDetailsId}",method=RequestMethod.GET)
	public Response getUnitServiceMasterDetailsById(@PathVariable("UnitServiceMasterDetailsId") Integer unitServiceMasterDetailsId) {
		try {
			UnitServiceMasterDetailsDto unitServiceMasterDetailsDto = new UnitServiceMasterDetailsDto();
			unitServiceMasterDetailsDto.setUnitServiceMasterDetailsId(unitServiceMasterDetailsId);
			return iUnitServiceMasterDetailsService.getUnitServiceMasterDetailsById(unitServiceMasterDetailsDto);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	/***
	 * Controller method to update UnitServiceMasterDetails
	 * @param unitServiceMasterDetailsDto
	 * @return Response
	 */
	@RequestMapping(value="/UnitServiceMasterDetails",method=RequestMethod.PUT)
	public Response updateUnitServiceMasterDetails(@RequestBody UnitServiceMasterDetailsDto unitServiceMasterDetailsDto) {
		try {
			return iUnitServiceMasterDetailsService.updateUnitServiceMasterDetails(unitServiceMasterDetailsDto);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	@RequestMapping(value="/getAllActiveTax/{unitId}",method=RequestMethod.GET)
	public Response getAllActiveTax(@PathVariable("unitId") Integer unitId) {
		try {
			
			return iUnitServiceMasterDetailsService.getAllActiveTax(unitId);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
}
