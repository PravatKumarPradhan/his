package com.param.lis.unit.controller;

import java.util.List;
import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.param.entity.lis.unit.LabTemplateMaster;
import com.param.lis.common.dto.CommonDto;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.Response;



import com.param.lis.transaction.service.LabTemplateService;
import com.param.lis.unit.dto.LabTemplateMasterDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/LIS/unit")

@SuppressWarnings({ "unchecked", "rawtypes" })
@Api(value="LabTemplateController",tags="Templates")
public class LabTemplateController implements ICommonConstants, IError {

	
	@Autowired
	LabTemplateService labTemplateService;
	
	@Autowired
	ServletContext context;

	@RequestMapping(value = "/labtemplates/{orgId}/{orgUnitId}/{offset}/{recordPerPage}", method = RequestMethod.GET )
	@ApiOperation(value="Get list of Lab Templates", response = LabTemplateMaster.class)
	@ApiResponses(value = {
			    @ApiResponse(code = 200, message = "Suceess|OK"),
	            @ApiResponse(code = 401, message = "not authorized!"),
	            @ApiResponse(code = 403, message = "forbidden!!!"),
	            @ApiResponse(code = 404, message = "not found!!!") })
	public ResponseEntity<List<LabTemplateMasterDto>> allLabTemplateData(@PathVariable("orgId") Integer orgId,@PathVariable("orgUnitId") Integer orgUnitId,
			@PathVariable("offset") Integer offset,@PathVariable("recordPerPage") Integer recordPerPage) {
		List<LabTemplateMasterDto> labTemplateMasterDto = labTemplateService
				.retrieveAllLabTemplates(orgId,orgUnitId, offset,  recordPerPage);
		return new ResponseEntity<>(labTemplateMasterDto, HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/labtemplates", method=RequestMethod.POST,consumes="application/json") 
	@ApiOperation(value="Create Lab Template", response = LabTemplateMaster.class)
	@ApiResponses(value = {
			    @ApiResponse(code = 200, message = "Template Added Successfully."),
	            @ApiResponse(code = 401, message = "You are not authorised to add Template."),
	            @ApiResponse(code = 403, message = "Create Template is forbidden.!!!"),
	            @ApiResponse(code = 404, message = "Resource Not Found"),
	            @ApiResponse(code = 405, message = "Method not Found")})
	 public ResponseEntity<Boolean>  saveLabTemplate( @RequestBody LabTemplateMasterDto labTemplateMasterDto){ 	
	 return new ResponseEntity<>(labTemplateService.createLabTemplate(labTemplateMasterDto),HttpStatus.CREATED); 
	 }
	
	
	@RequestMapping(value = "/labtemplates/{labTemplateId}", method = RequestMethod.PUT)
	@ApiOperation(value="Delete labTemplate", response = LabTemplateMaster.class)
	@ApiResponses(value = {
			    @ApiResponse(code = 200, message = "Template Deleted Successfully."),
	            @ApiResponse(code = 401, message = "You are not authorised to delete Template."),
	            @ApiResponse(code = 403, message = "Delete Template is forbidden.!!!"),
	            @ApiResponse(code = 404, message = "Resource Not Found"),
	            @ApiResponse(code = 405, message = "Method not Found")})
	public ResponseEntity<Boolean> deleteLabTemplate(@PathVariable("labTemplateId") Integer labTemplateId) {
       Boolean islabTemplateDeleted = labTemplateService.deleteLabTemplate(labTemplateId);
		if (!islabTemplateDeleted) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(true, HttpStatus.ACCEPTED);
		}
	}
	
	@RequestMapping(value = "/labtemplates", method = RequestMethod.PUT)
	@ApiOperation(value="Update measurementUnit", response = LabTemplateMaster.class)
	@ApiResponses(value = {
			    @ApiResponse(code = 200, message = "Template Updated Successfully."),
	            @ApiResponse(code = 401, message = "You are not authorised to update Template."),
	            @ApiResponse(code = 403, message = "update Template is forbidden.!!!"),
	            @ApiResponse(code = 404, message = "Resource Not Found"),
	            @ApiResponse(code = 405, message = "Method not Found")})
	public ResponseEntity<Boolean> updateLabTemplate(@RequestBody LabTemplateMasterDto labTemplateMasterDto) 
	{
        Boolean isLabTemplateUpdated = labTemplateService.updateLabTemplate(labTemplateMasterDto);
        return new ResponseEntity<>(isLabTemplateUpdated, HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value = "/labtemplates/{labTemplateId}", method = RequestMethod.GET)
	@ApiOperation(value=" Get labTemplates By Id ", response = LabTemplateMasterDto.class)
	@ApiResponses(value = {
	            @ApiResponse(code = 200, message = "Suceess|OK"),
	            @ApiResponse(code = 401, message = "not authorized!"),
	            @ApiResponse(code = 403, message = "forbidden!!!"),
	            @ApiResponse(code = 404, message = "not found!!!") })
	public ResponseEntity<LabTemplateMasterDto> labTemplateById(@PathVariable("labTemplateId") Integer labTemplateId) {
		 
		LabTemplateMasterDto labTemplateMasterDto = labTemplateService.findLabTemplateById(labTemplateId);
		return new ResponseEntity<>(labTemplateMasterDto, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/read/labtemplate", headers=("content-type=multipart/*"), method = RequestMethod.POST)
	@ApiOperation(value="Read Lab Template Data", response = LabTemplateMasterDto.class)
	@ApiResponses(value = {
	                @ApiResponse(code = 200, message = "Suceess|OK"),
	                @ApiResponse(code = 401, message = "not authorized!"),
	                @ApiResponse(code = 403, message = "forbidden!!!"),
	                @ApiResponse(code = 404, message = "not found!!!") })
	    public ResponseEntity<String> readTemplateData(@RequestParam("file") MultipartFile inputFile)
	    {
	        String templateData  = labTemplateService.readTemplateData(inputFile);
	        if(templateData==null)	
	        {
	          return new ResponseEntity<>(templateData, HttpStatus.NOT_FOUND);
	        }
	        else {
	          return new ResponseEntity<>(templateData, HttpStatus.OK);
	        }
	    }
	

	  @RequestMapping(value = "/labTemplates/{orgId}/{orgUnitId}", method = RequestMethod.GET)
	  public @ResponseBody Response getLabTemplates(@PathVariable(value = "orgId") Integer orgId,@PathVariable(value = "orgUnitId") Integer orgUnitId) {
	       Response<LabTemplateMasterDto, Integer> response = null;
	        try {
	            response = labTemplateService.getLabTemplates(orgId,orgUnitId);
	            return response;
	        } catch (Exception e) 
	        {
	            e.printStackTrace();
	            return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	        }
	    }	

	


	@RequestMapping(value = "/getToTalLabTemplateMasterRecord/{orgId}/{orgUnitId}", method = RequestMethod.GET)
	public @ResponseBody Response getToTalLabTemplateMasterRecord(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "orgUnitId") Integer orgUnitId) {
		Response<LabTemplateMaster, Integer> response = null;
		try {
			response = labTemplateService.getToTalLabTemplateMasterRecord(orgId,orgUnitId);
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@RequestMapping(value = "/activateInactivateLabTemplateMaster/{orgId}/{labTemplateId}/{status}", method = RequestMethod.GET)
	public @ResponseBody Response updateAccountingLedger(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "labTemplateId") Integer labTemplateId,
			@PathVariable(value = "status") Character status) {
		Response<LabTemplateMaster, Integer> response = null;
		try {
			response = labTemplateService.activateInactivateLabTemplateMaster(orgId, labTemplateId, status);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		return response;
	}

	
	@RequestMapping(value = "/getTemplateTypes/{orgId}/{orgUnitId}", method = RequestMethod.GET)
	public @ResponseBody Response getTemplateTypes(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "orgUnitId") Integer orgUnitId) {
		Response<CommonDto, Integer> response = null;
		try {
			response = labTemplateService.getTemplateTypes(orgId, orgUnitId);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		return response;
	}

	@RequestMapping(value = "/labTemplatesbydoctor/{orgId}/{orgUnitId}/{doctorId}/{templateTypeId}", method = RequestMethod.GET)
    public @ResponseBody Response getLabTemplatesByDoctor(
           @PathVariable(value = "orgId") Integer orgId,
           @PathVariable(value = "orgUnitId") Integer orgUnitId,
           @PathVariable(value = "doctorId") Integer doctorId,
           @PathVariable(value = "templateTypeId") Integer templateTypeId) {
        Response<LabTemplateMaster, Integer> response = null;
        try {
            response = labTemplateService.getLabTemplatesByDoctor(orgId, orgUnitId,doctorId, templateTypeId);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
        }
        return response;
    }

	
	@RequestMapping(value = "/getTemplateTypeExistOrNot/{orgId}/{orgUnitId}/{templateTypeId}", method = RequestMethod.GET)
	public @ResponseBody Response getTemplateTypeExistOrNot(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "orgUnitId") Integer orgUnitId,@PathVariable(value = "templateTypeId") Integer templateTypeId) {
		Response<LabTemplateMaster, Integer> response = null;
		try {
			response = labTemplateService.isTemplateExistOrNot(orgId, orgUnitId, templateTypeId);
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
}
