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
import com.param.lis.global.dto.GlobalCommonDto;
import com.param.lis.unit.dto.DepartmentMasterDto;
import com.param.lis.unit.service.IDepartmentMasterService;

@RestController
@SuppressWarnings(
{ "rawtypes", "unchecked" })
@RequestMapping(value = "/LIS/global")
public class DepartmentMasterController implements ICommonConstants, IError
{
	@Autowired
	private IDepartmentMasterService iDepartmentMasterService;

	@RequestMapping(value = "/addDepartment", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response addDepartment(@RequestBody DepartmentMasterDto departmentMasterDto)
	{
		Response<DepartmentMasterDto, Integer> response = null;
		try
		{
			response = iDepartmentMasterService.addDepartmentMaster(departmentMasterDto);
			return response;
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}


	@RequestMapping(value = "/getDepartmentbyId/{orgId}/{unitId}/{departmentId}", method = RequestMethod.GET)
	public @ResponseBody Response getDepartmentbyId(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "unitId") Integer unitId,
			@PathVariable(value = "departmentId") Integer departmentId)
	{
		Response<GlobalCommonDto, Integer> response = null;
		try
		{
			response = iDepartmentMasterService.getDepartmentMasterById(orgId,unitId,departmentId);
			return response;
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}	
	}

	@RequestMapping(value = "/updateDepartment", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateSample(@RequestBody DepartmentMasterDto departmentMasterDto)
	{
		Response<DepartmentMasterDto, Integer> response = null;
		try
		{
			response = iDepartmentMasterService.updateDepartmentMaster(departmentMasterDto);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		return response;
	}

	@RequestMapping(value = "/activeInactiveDepartment/{orgId}/{departmentId}/{departmentStatus}", method = RequestMethod.GET)
	public @ResponseBody Response activeInactiveDepartment(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "departmentId") Integer departmentId,
			@PathVariable(value = "departmentStatus") Character departmentStatus)
	{
		Response<DepartmentMasterDto, Integer> response = null;
		try
		{
			response = iDepartmentMasterService.ActivateInactivateDepartmentMaster(orgId, departmentId, departmentStatus);
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		return response;
	}

	@RequestMapping(value = "/listDepartmentMaster/{orgId}/{offset}/{recordPerPage}", method = RequestMethod.GET)
	public @ResponseBody Response listDepartmentMaster(@PathVariable(value = "orgId") Integer orgId,
			@PathVariable(value = "offset") Integer offset,
			@PathVariable(value = "recordPerPage") Integer recordPerPage)
	{
		Response<GlobalCommonDto, Integer> response = null;
		try
		{
			response = iDepartmentMasterService.listDepartmentMaster(orgId, offset, recordPerPage);
			return response;
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
	
	@RequestMapping(value = "/getTotalDepartmentMasterRecord/{orgId}", method = RequestMethod.GET)
	public @ResponseBody Response getTotalDepartmentMasterRecord(@PathVariable(value= "orgId") Integer orgId)
		{
			Response<DepartmentMasterDto, Integer> response = null;
			try
				{
					response = iDepartmentMasterService.getToTalDepartmentMasterRecord(orgId);
					return response;
				}
			catch (Exception e)
				{
					e.printStackTrace();
					return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
				}
		}


}
