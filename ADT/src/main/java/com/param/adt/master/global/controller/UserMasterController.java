package com.param.adt.master.global.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.param.adt.common.ICommonConstants;
import com.param.adt.common.Response;
import com.param.adt.master.global.dto.OrganizationMasterDto;
import com.param.adt.master.global.dto.UserMasterDto;
import com.param.adt.master.global.service.IUserMasterService;
@SuppressWarnings({"rawtypes","unchecked"})
@Controller
@RequestMapping("/adt")
public class UserMasterController implements ICommonConstants 
{
	@Autowired
	IUserMasterService iUserMasterService;
	
	
	
	@RequestMapping(value= "/userLogin" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody  Response userLogin(@RequestBody UserMasterDto userMasterDto){
		try{
			System.out.println("1");
			return iUserMasterService.userLogin(userMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	@RequestMapping(value="/organizationMasterList",method = RequestMethod.GET)
	public @ResponseBody Response organizationList(){
		
		return iUserMasterService.getOrganizationList();
	}
	
	@RequestMapping(value="/getUnitByOrganization",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Response unitByOrganizationList(@RequestBody OrganizationMasterDto organizationMasterDto){
		System.out.println(organizationMasterDto.getOrganizationId());
		return iUserMasterService.getUnitListByOrganization(organizationMasterDto);
	}
	

	@RequestMapping(value = "/validateUserName" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response validateUserName(@RequestBody UserMasterDto userMasterDto){
		try{
			return iUserMasterService.validateUniqueUserName(userMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}
	
	
	
	
}
