package com.param.lis.unit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.Response;
import com.param.lis.unit.dto.FormulaMasterDto;
import com.param.lis.unit.service.IFormulaMasterService;
import io.swagger.annotations.Api;

@RestController
@SuppressWarnings({"rawtypes", "unchecked" })
@RequestMapping(value = "/LIS/unit")
@Api(value="FormulaMasterController",tags="FormulaMaster")
public class FormulaMasterController implements ICommonConstants, IError
{
  @Autowired
  private IFormulaMasterService iFormulaMasterService;
  
  @RequestMapping(value = "/formula", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody Response addFormula(@RequestBody FormulaMasterDto formulaMasterDto)
  {
      Response<FormulaMasterDto, Integer> response = null;
      try
      {
          response = iFormulaMasterService.addFormula(formulaMasterDto);
          return response;
      } catch (Exception e)
      {
          e.printStackTrace();
          return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
      }
  }

  @RequestMapping(value = "/formula/{formulaId}/{orgId}/{orgUnitId}", method = RequestMethod.GET)
  public @ResponseBody Response getFormulaById(@PathVariable(value = "formulaId") Integer formulaId,
          @PathVariable(value = "orgId") Integer orgId,
          @PathVariable(value = "orgUnitId") Integer orgUnitId)
  {
      Response<FormulaMasterDto, Integer> response = null;
      try
      {
          response = iFormulaMasterService.getFormulaById(formulaId,orgId,orgUnitId);
          return response;
      } catch (Exception e)
      {
          e.printStackTrace();
          return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
      }   
  }

  @RequestMapping(value = "/formula", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody Response updateFormula(@RequestBody FormulaMasterDto formulaMasterDto)
  {
      Response<FormulaMasterDto, Integer> response = null;
      try
      {
          response = iFormulaMasterService.updateFormula(formulaMasterDto);
      } catch (Exception e)
      {
          e.printStackTrace();
          return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
      }
      return response;
  }

  @RequestMapping(value = "/formula/status/{formulaId}/{orgId}/{orgUnitid}/{status}", method = RequestMethod.GET)
  public @ResponseBody Response activeInactiveFormula(@PathVariable(value = "formulaId") Integer formulaId,
          @PathVariable(value = "orgId") Integer orgId,
          @PathVariable(value = "orgUnitid") Integer orgUnitid,
     @PathVariable(value = "status") Character status)
  {
      Response<FormulaMasterDto, Integer> response = null;
      try
      {
          response = iFormulaMasterService.activeInactiveFormula(formulaId,orgId, orgUnitid, status);
      } catch (Exception e)
      {
          e.printStackTrace();
          return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
      }
      return response;
  }

  @RequestMapping(value = "/formula/{orgId}/{orgUnitId}/{offset}/{recordPerPage}", method = RequestMethod.GET)
  public @ResponseBody Response getFormulaList(@PathVariable(value = "orgId") Integer orgId,
          @PathVariable(value= "orgUnitId") Integer orgUnitId,
          @PathVariable(value = "offset") Integer offset,
          @PathVariable(value = "recordPerPage") Integer recordPerPage)
  {
      Response<FormulaMasterDto, Integer> response = null;
      try
      {
          response = iFormulaMasterService.getFormulaList(orgId,orgUnitId, offset, recordPerPage);
          return response;
      } catch (Exception e)
      {
          e.printStackTrace();
          return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
      }
  }
  
  @RequestMapping(value = "/formula/{orgId}/{orgUnitId}", method = RequestMethod.GET)
  public @ResponseBody Response getFormulaListCount(@PathVariable(value= "orgId") Integer orgId,@PathVariable(value= "orgUnitId") Integer orgUnitId)
      {
          Response<FormulaMasterDto, Integer> response = null;
          try
              {
                  response = iFormulaMasterService.getFormulaListCount(orgId,orgUnitId);
                  return response;
              }
          catch (Exception e)
              {
                  e.printStackTrace();
                  return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
              }
      }
  
	@RequestMapping(value = "/parameter/{orgId}/{orgUnitId}/{isFormula}", method = RequestMethod.GET)
	public @ResponseBody Response getParameterListForFormula(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "orgUnitId") Integer orgUnitId,
			  @PathVariable(value = "isFormula") Character isFormula)	
	{
		Response<FormulaMasterDto, Integer> response = null;
		try
		{
			response = iFormulaMasterService.getParameterListForFormula(orgId, orgUnitId,isFormula);
			return response;
		} catch (Exception e)
		{
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	   @RequestMapping(value = "/formulaDetails/{formulaId}/{orgId}/{orgUnitId}", method = RequestMethod.GET)
	    public @ResponseBody Response getFormulaDetails(
	        @PathVariable(value = "formulaId") Integer formulaId,
	        @PathVariable(value = "orgId") Integer orgId,
	            @PathVariable(value = "orgUnitId") Integer orgUnitId)
	    {
	        Response<FormulaMasterDto, Integer> response = null;
	        try
	        {
	            response = iFormulaMasterService.getFormulaDetails(formulaId,orgId, orgUnitId);
	            return response;
	        } catch (Exception e)
	        {
	            return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	        }
	    }

}
