package com.param.adt.admission.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.param.adt.admission.dto.BedAllocationDto;
import com.param.adt.admission.service.IBedAllocationService;
import com.param.adt.common.IADTConstants;
import com.param.adt.common.ICommonConstants;
import com.param.adt.common.Response;
import com.param.adt.master.global.dto.BedCategoryMasterDto;
import com.param.adt.master.global.dto.BedMasterDto;
import com.param.adt.master.unit.dto.BillingBedCategoryMasterDto;
import com.param.adt.master.unit.dto.MealPreferenceMasterDto;
import com.param.global.dto.AdmissionDto;
import com.param.global.service.IKinDetailsService;

@Controller
@RequestMapping("/adt")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class BedAllocationController implements ICommonConstants, IADTConstants {
	@Autowired
	IBedAllocationService iBedAllocationService;
	@Autowired
	IKinDetailsService iKinDetailsService;

	@RequestMapping(value = "/getActiveBedList", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getActiveBedList(@RequestBody BedMasterDto bedMasterDto) {
		try {
			return iBedAllocationService.getActiveBedList(bedMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@RequestMapping(value = "/getBedListByMultipleCriteria", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getBedListByMultipleCriteria(@RequestBody BedMasterDto bedMasterDto) {
		try {
			return iBedAllocationService.getBedListByMultipleCriteria(bedMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@RequestMapping(value = "/getActiveFloorList", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getActiveFloorList(@RequestBody BedAllocationDto bedMasterDto) {
		try {
			return iBedAllocationService.getActiveFloorMasterList(bedMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@RequestMapping(value = "/getActiveWardListByFloorIdByGender", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getActiveWardListByFloorId(@RequestBody BedAllocationDto bedMasterDto) {
		try {
			return iBedAllocationService.getWardListByFloorId(bedMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@RequestMapping(value = "/getNursingStationListByWard", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getNursingStationListByWard(@RequestBody BedAllocationDto bedMasterDto) {
		try {
			return iBedAllocationService.getNursingStationListByWard(bedMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@RequestMapping(value = "/getBedCategoryListByOrgUnit", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getBedCategoryListByOrgUnit(@RequestBody BedCategoryMasterDto bedCategoryMasterDto) {
		try {
			return iBedAllocationService.getBedCategoryList(bedCategoryMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@RequestMapping(value = "/getBedStatusList", method = RequestMethod.GET)
	public @ResponseBody Response getBedStatusList() {
		try {
			return iBedAllocationService.getBedStatusList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}


	@RequestMapping(value = "/getMealPreference", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getMealPreference(@RequestBody MealPreferenceMasterDto mealPreferenceMasterDto) {
		try {
			return iBedAllocationService.getMealPreference(mealPreferenceMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@RequestMapping(value = "/savePatientAdmission ", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response savePatientAdmission(@RequestBody AdmissionDto admission) {

		try {
			Response res = iBedAllocationService.savePatientAdmission(admission);
			AdmissionDto admissionDto = (AdmissionDto) res.getObject();
			admissionDto.setActiveStatus('B'); // B----> Booked
			admissionDto.setBedStatusId(5); // 5----> Booked
			if (res.getStatus().equals(SUCCESS)) {
				Response res2 = iBedAllocationService.savePatientDetails(admissionDto);
				if (res2.getStatus().equals(SUCCESS)) {
					Response res3 = iBedAllocationService.saveTransfer(admissionDto);
					if (res3.getStatus().equals(SUCCESS)) {
						com.param.global.common.Response res4 = iKinDetailsService.saveKinDetails(admissionDto);
						if (res4.getStatus().equals(SUCCESS)) {
							if (admissionDto.getBedCategoryWaitingListId() != null) {
								Response res5 = iBedAllocationService.updateWatingListNumber(admissionDto);
								if (res5.getStatus().equals(SUCCESS)) {
									return new Response(SUCCESS, null, ADMISSION_SAVED, null, null);
								}
							} else {
								return new Response(SUCCESS, null, ADMISSION_SAVED, null, null);
							}
						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

}
