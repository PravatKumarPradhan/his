package com.param.global.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dto.DependentDetailsDto;
import com.param.global.service.IDependentDetailsService;

@RestController
@RequestMapping(value="api/global")
@SuppressWarnings({ "unchecked", "rawtypes"})
public class DependentDetailsController implements ICommonConstants{

	@Autowired
	IDependentDetailsService iDependentDetailsService;
	
	@RequestMapping(value="/dependentDetails", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response savedependentDetails(@RequestBody DependentDetailsDto dependentDetailsDto) {
		try {
			return iDependentDetailsService.saveDependentDetails(dependentDetailsDto);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR, COMMON_ERROR_MESSAGE, null, null);
	}
	
	@RequestMapping(value="/getDependentDetails", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getDependentDetails(@RequestBody DependentDetailsDto dependentDetailsDto) {
		try {
			return iDependentDetailsService.getDependentDetails(dependentDetailsDto);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR, COMMON_ERROR_MESSAGE, null, null);
	}
	
	@RequestMapping(value="/searchDependentDetailsByCriteria", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response searchDependentDetailsByCriteria(@RequestBody DependentDetailsDto dependentDetailsDto) {
		try {
			return iDependentDetailsService.searchDependentDetailsByCriteria(dependentDetailsDto);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR, COMMON_ERROR_MESSAGE, null, null);
	}
	
}
