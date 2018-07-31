package com.param.global.unit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.unit.dto.WingMasterDto;
import com.param.global.unit.service.IWingMasterService;

@RestController
@RequestMapping(value="/adt")
@SuppressWarnings("rawtypes")
public class WingMasterController implements ICommonConstants{
	
	@Autowired
	private IWingMasterService iWingMasterService;
	
	@RequestMapping(value="/getWingMasterListByOrgAndUnit/{organizationId}/{unitId}" ,method = RequestMethod.GET)
	public Response getWingMasterListByOrgAndUnit(@PathVariable("organizationId") Integer organizationId , @PathVariable("unitId") Integer unitId){
		try{
			WingMasterDto wingMasterDto = new WingMasterDto();
			wingMasterDto.setOrganizationId(organizationId);
			wingMasterDto.setUnitId(unitId);
			return iWingMasterService.getWingMasterListByOrgAndUnit(wingMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, null, null, null, null);
	}

}
