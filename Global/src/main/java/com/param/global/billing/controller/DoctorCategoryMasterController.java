package com.param.global.billing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.param.global.billing.dto.DoctorCategoryMasterDto;
import com.param.global.billing.service.IDoctorCategoryMasterService;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;

@RestController
@RequestMapping("/billing")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class DoctorCategoryMasterController implements ICommonConstants {

	@Autowired
	IDoctorCategoryMasterService iDoctorCategoryMasterService;

	@RequestMapping(value = "/saveDoctorCategoryMaster", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response saveDoctorCategoryMaster(@RequestBody DoctorCategoryMasterDto doctorCategoryMasterDto) {
		try {
			return iDoctorCategoryMasterService.saveDoctorCategoryMaster(doctorCategoryMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/getDoctorCategoryMasterById/{doctorId}/{orgId}", method = RequestMethod.GET)
	public @ResponseBody Response getDoctorCategoryMasterById(@PathVariable("doctorId") int doctorId,@PathVariable("orgId") int orgId) {
		try {
			return iDoctorCategoryMasterService.getDoctorCategoryMasterById(doctorId,orgId);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null, e.getMessage());

		}
	}
	@RequestMapping(value = "/getDoctorCategoryMasterList", method = RequestMethod.POST , consumes= MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getDoctorCategoryMasterList(@RequestBody DoctorCategoryMasterDto doctorCategoryMasterDto) {
		try {
			return iDoctorCategoryMasterService.getDoctorCategoryMasterList(doctorCategoryMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null, e.getMessage());
		}
	}
	@RequestMapping(value = "/updateDoctorCategoryMaster", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateDoctorCategoryMaster(@RequestBody DoctorCategoryMasterDto doctorCategoryMasterDto) {
		try {
			return iDoctorCategoryMasterService.updateDoctorCategoryMaster(doctorCategoryMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}
	@RequestMapping(value = "/updateDoctorCategoryMasterStatus", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateDoctorCategoryMasterStatus(@RequestBody DoctorCategoryMasterDto doctorCategoryMasterDto) {
		try {
			return iDoctorCategoryMasterService.updateDoctorCategoryMasterStatus(doctorCategoryMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}
	@RequestMapping(value="/getDoctorCategoryCount" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getDoctorCategoryCount(@RequestBody DoctorCategoryMasterDto doctorCategoryMasterDto){
		try{
			
			return iDoctorCategoryMasterService.getDoctorCategoryCount(doctorCategoryMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null,null);
	}

}
