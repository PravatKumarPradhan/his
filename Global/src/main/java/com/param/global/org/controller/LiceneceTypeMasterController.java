package com.param.global.org.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.org.dto.LiceneceTypeMasterDto;
import com.param.global.org.service.ILiceneceTypeMasterService;

@RestController
@RequestMapping("/org")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class LiceneceTypeMasterController implements ICommonConstants {

	@Autowired
	ILiceneceTypeMasterService iLiceneceTypeMasterService;

	@RequestMapping(value = "/saveLiceneceTypeMaster", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response saveNutritionalMaster(@RequestBody LiceneceTypeMasterDto liceneceTypeMasterDto) {
		try {
			return iLiceneceTypeMasterService.saveLiceneceTypeMaster(liceneceTypeMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}

	@RequestMapping(value = "/getLiceneceTypeMasterById/{licenceId}/{orgId}", method = RequestMethod.GET)
	public @ResponseBody Response getLiceneceTypeMasterById(@PathVariable("licenceId") int licenceId,@PathVariable("orgId") int orgId) {
		try {
			return iLiceneceTypeMasterService.getLiceneceTypeMasterById(licenceId,orgId);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null, e.getMessage());

		}
	}
	@RequestMapping(value = "/getLiceneceTypeMasterList/{orgId}", method = RequestMethod.GET)
	public @ResponseBody Response getLiceneceTypeMasterList(@PathVariable("orgId") int orgId) {
		try {
			return iLiceneceTypeMasterService.getLiceneceTypeMasterList(orgId);
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null, e.getMessage());
		}
	}
	@RequestMapping(value = "/updateLiceneceTypeMaster", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateLiceneceTypeMaster(@RequestBody LiceneceTypeMasterDto liceneceTypeMasterDto) {
		try {
			return iLiceneceTypeMasterService.updateLiceneceTypeMaster(liceneceTypeMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}
	@RequestMapping(value = "/updateLiceneceTypeMasterStatus", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateLiceneceTypeMasterStatus(@RequestBody LiceneceTypeMasterDto liceneceTypeMasterDto) {
		try {
			return iLiceneceTypeMasterService.updateLiceneceTypeMasterStatus(liceneceTypeMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}
}
