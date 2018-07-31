package com.param.adt.mortuary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.param.adt.common.ICommonConstants;
import com.param.adt.mortuary.service.IMortuaryService;
import com.param.global.common.Response;
import com.param.global.dto.AdmissionDto;
import com.param.global.dto.MortuaryDto;
import com.param.global.service.IKinDetailsService;

@Controller
@RequestMapping("/adt")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class MortuaryController implements ICommonConstants {

	@Autowired
	private IMortuaryService iMortuaryService;

	@Autowired
	private IKinDetailsService iKinDetailsService;

	@RequestMapping(value = "/saveMortuaryRequestFromMapOfBed", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response saveMortuaryRequestFromMapOfBed(@RequestBody MortuaryDto mortuaryDto) {
		try {
			return iMortuaryService.saveMortuaryRequest(mortuaryDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR, COMMON_ERROR_MESSAGE, null, null);
	}

	@RequestMapping(value = "/saveDeathRegistation", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response saveDeathRegistation(@RequestBody MortuaryDto mortuaryDto) {
		try {
			Response res1 = iMortuaryService.saveDeathRegistation(mortuaryDto);
			if (res1.getStatus().equals(SUCCESS)) {
				MortuaryDto mortuaryDto2 = (MortuaryDto) res1.getObject();
				Response res2 = iMortuaryService.saveMortuaryRequest(mortuaryDto2);
				if (res2.getStatus().equals(SUCCESS)) {
					AdmissionDto admissionDto = new AdmissionDto();
					admissionDto.setKinArray(mortuaryDto.getKinArray());
					admissionDto.setOrganizationId(mortuaryDto.getOrganizationId());
					admissionDto.setUnitId(mortuaryDto.getUnitId());
					admissionDto.setCreatedBy(mortuaryDto.getCreatedBy());
					admissionDto.setCreatedDate(mortuaryDto.getCreatedDate());
					admissionDto.setVisitTypeId(mortuaryDto.getVisitTypeId());
					admissionDto.setdPatientId(mortuaryDto2.getdPatientId());
					return iKinDetailsService.saveKinDetails(admissionDto);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR, COMMON_ERROR_MESSAGE, null, null);
	}

	@RequestMapping(value = "/getPendingMortuaryRequestList", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getPendingMortuaryRequestList(@RequestBody MortuaryDto mortuaryDto) {
		try {
			return iMortuaryService.getPendingMortuaryRequestList(mortuaryDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR, COMMON_ERROR_MESSAGE, null, null);
	}

	@RequestMapping(value = "/acceptRejectMortuaryRequestFromPendingList", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response acceptRejectMortuaryRequestFromPendingList(@RequestBody MortuaryDto mortuaryDto) {
		try {
			return iMortuaryService.acceptRejectMortuaryRequestFromPendingList(mortuaryDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR, COMMON_ERROR_MESSAGE, null, null);
	}

	@RequestMapping(value = "/getReservedMortuaryRequestList", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getReservedMortuaryRequestList(@RequestBody MortuaryDto mortuaryDto) {
		try {
			return iMortuaryService.getReservedMortuaryRequestList(mortuaryDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR, COMMON_ERROR_MESSAGE, null, null);
	}

	@RequestMapping(value = "/saveBedAllocationMortuary", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response saveBedAllocationMortuary(@RequestBody MortuaryDto mortuaryDto) {
		try {
			return iMortuaryService.saveBedAllocationMortuary(mortuaryDto);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR, COMMON_ERROR_MESSAGE, null, null);
	}

	@RequestMapping(value = "/getAdmittedMortuaryList", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getAdmittedMortuaryList(@RequestBody MortuaryDto mortuaryDto) {
		try {
			return iMortuaryService.getAdmittedMortuaryList(mortuaryDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR, COMMON_ERROR_MESSAGE, null, null);
	}

	@RequestMapping(value = "/releaseMorturyBed", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response releaseMorturyBed(@RequestBody MortuaryDto mortuaryDto) {
		try {
			return iMortuaryService.releaseMorturyBed(mortuaryDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR, COMMON_ERROR_MESSAGE, null, null);
	}

	@RequestMapping(value = "/getDataForMorturyMapOfBed", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getDataForMorturyMapOfBed(@RequestBody MortuaryDto mortuaryDto) {
		try {
			return iMortuaryService.getDataForMorturyMapOfBed(mortuaryDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR, COMMON_ERROR_MESSAGE, null, null);
	}

	@RequestMapping(value = "/cancelMorgueRequest", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response cancelMorgueRequest(@RequestBody MortuaryDto mortuaryDto) {
		try {
			return iMortuaryService.cancelMorgueRequest(mortuaryDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR, COMMON_ERROR_MESSAGE, null, null);
	}

}
