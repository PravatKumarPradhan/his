package com.param.lis.unit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.param.lis.common.dto.CommonDto;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.Response;
import com.param.lis.global.dto.GlobalCommonDto;
import com.param.lis.unit.dto.RackMasterDto;
import com.param.lis.unit.dto.TPanelMasterDto;
import com.param.lis.unit.service.IPanelTestService;
import io.swagger.annotations.Api;

@RestController
@SuppressWarnings({"rawtypes", "unchecked" })
@RequestMapping(value = "/LIS/unit")
@Api(value="PanelTestController",tags="TpanelTestMpprMaster")
public class PanelTestController implements ICommonConstants, IError
{
  @Autowired
  private IPanelTestService iPanelTestService;
  
  @RequestMapping(value = "/Panel", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody Response addPanel(@RequestBody TPanelMasterDto tpanelTestMpprMasterDto)
  {
      Response<TPanelMasterDto, Integer> response = null;
      try
      {
          response = iPanelTestService.addPanel(tpanelTestMpprMasterDto);
          return response;
      } catch (Exception e)
      {
          e.printStackTrace();
          return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
      }
  }


  @RequestMapping(value = "/Panel/{serviceId}/{orgId}/{orgUnitId}", method = RequestMethod.GET)
  public @ResponseBody Response getPanelById(@PathVariable(value = "serviceId") Integer serviceId,
          @PathVariable(value = "orgId") Integer orgId,
          @PathVariable(value = "orgUnitId") Integer orgUnitId)
  {
      Response<TPanelMasterDto, Integer> response = null;
      try
      {
          response = iPanelTestService.getPanelById(serviceId,orgId,orgUnitId);
          return response;
      } catch (Exception e)
      {
          e.printStackTrace();
          return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
      }   
  }

  @RequestMapping(value = "/Panel", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody Response updatePanel(@RequestBody TPanelMasterDto tpanelTestMpprMasterDto)
  {
      Response<TPanelMasterDto, Integer> response = null;
      try
      {
          response = iPanelTestService.updatePanel(tpanelTestMpprMasterDto);
      } catch (Exception e)
      {
          e.printStackTrace();
          return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
      }
      return response;
  }

  @RequestMapping(value = "/PanelStatus/{serviceId}/{orgId}/{orgUnitid}/{status}", method = RequestMethod.GET)
  public @ResponseBody Response activeInactivePanelStatus(@PathVariable(value = "serviceId") Integer serviceId,
          @PathVariable(value = "orgId") Integer orgId,
          @PathVariable(value = "orgUnitid") Integer orgUnitid,
     @PathVariable(value = "status") Character status)
  {
      Response<TPanelMasterDto, Integer> response = null;
      try
      {
          response = iPanelTestService.activeInactivePanelStatus(serviceId,orgId, orgUnitid, status);
      } catch (Exception e)
      {
          e.printStackTrace();
          return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
      }
      return response;
  }

  @RequestMapping(value = "/PanelList/{orgId}/{orgUnitId}/{offset}/{recordPerPage}", method = RequestMethod.GET)
  public @ResponseBody Response getPanelList(@PathVariable(value = "orgId") Integer orgId,
          @PathVariable(value= "orgUnitId") Integer orgUnitId,
          @PathVariable(value = "offset") Integer offset,
          @PathVariable(value = "recordPerPage") Integer recordPerPage)
  {
      Response<TPanelMasterDto, Integer> response = null;
      try
      {
          response = iPanelTestService.getPanelList(orgId,orgUnitId, offset, recordPerPage);
          return response;
      } catch (Exception e)
      {
          e.printStackTrace();
          return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
      }
  }
  
  @RequestMapping(value = "/PanelListCount/{orgId}/{orgUnitId}", method = RequestMethod.GET)
  public @ResponseBody Response getPanelListCount(@PathVariable(value= "orgId") Integer orgId,@PathVariable(value= "orgUnitId") Integer orgUnitId)
      {
          Response<TPanelMasterDto, Integer> response = null;
          try
              {
                  response = iPanelTestService.getPanelListCount(orgId,orgUnitId);
                  return response;
              }
          catch (Exception e)
              {
                  e.printStackTrace();
                  return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
              }
      }
  
	@RequestMapping(value = "/getUnitServiceMapperList/{orgId}/{orgUnitId}", method = RequestMethod.GET)
	public @ResponseBody Response getUnitServiceMapperList(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "orgUnitId") Integer orgUnitId)
	{
		Response<TPanelMasterDto, Integer> response = null;
		try
		{
			response = iPanelTestService.getUnitServiceMapperList(orgId, orgUnitId);
			return response;
		} catch (Exception e)
		{
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	   @RequestMapping(value = "/getPanelDesc/{panelId}/{orgId}/{orgUnitId}", method = RequestMethod.GET)
	    public @ResponseBody Response getPanelDescByPanelId(@PathVariable(value = "panelId") Integer panelId,
	        @PathVariable(value = "orgId") Integer orgId,
	            @PathVariable(value = "orgUnitId") Integer orgUnitId)
	    {
	        Response<TPanelMasterDto, Integer> response = null;
	        try
	        {
	            response = iPanelTestService.getPanelDescByPanelId(panelId,orgId, orgUnitId);
	            return response;
	        } catch (Exception e)
	        {
	            return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
	        }
	    }

}
