package com.param.adt.admission.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.param.adt.admission.dto.AdmissionNoteDto;
import com.param.adt.admission.model.AdmissionNote;
import com.param.adt.admission.service.IAdmissionRequestService;
import com.param.adt.admission.service.IBedAllocationService;
import com.param.adt.admission.service.IPendingRequestService;
import com.param.adt.common.ICommonConstants;
import com.param.adt.common.Response;
import com.param.global.dto.AdmissionDto;

@Controller
@RequestMapping("/adt")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class PendingRequestController implements ICommonConstants {
	@Autowired
	private IPendingRequestService iPendingRequestService;

	@Autowired
	IAdmissionRequestService iAdmissionRequestService;

	@Autowired
	IBedAllocationService iBedAllocationService;
	

	@RequestMapping(value = "/getPendingAdmissionRequestDetails", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getPendingAdmissionRequestDetails(@RequestBody AdmissionNoteDto admissionNoteDto) {

		try {
			return iPendingRequestService.getPendingAdmissionRequestDetails(admissionNoteDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@RequestMapping(value = "/getPendingAdmissionCount", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response getPendingAdmissionCount(@RequestBody AdmissionNoteDto admissionNoteDto) {

		try {
			return iPendingRequestService.getPendingAdmissionCount(admissionNoteDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@RequestMapping(value = "/getAvtivePatientCategoryList", method = RequestMethod.GET)
	public @ResponseBody Response getAvtivePatientCategoryList() {
		try {
			return iPendingRequestService.getAvtivePatientCategoryList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@RequestMapping(value = "/getAvtivePaymentEntitlementList", method = RequestMethod.GET)
	public @ResponseBody Response getAvtivePaymentEntitlementList() {
		try {
			return iPendingRequestService.getAvtivePaymentEntitlementList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@RequestMapping(value = "/updatePendingAdmissionRequestDetails", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updatePendingAdmissionRequestDetails(@RequestBody AdmissionNoteDto admissionNoteDto) {
		try {
			Response res = iPendingRequestService.updatePendingAdmissionRequestDetails(admissionNoteDto);
			AdmissionNote admissionNoteObiject = (AdmissionNote) res.getObject();
			if (res.getStatus().equals(SUCCESS)) {
				Response res2 = iPendingRequestService.updateAdmissionRequestMapperDetails(admissionNoteDto,
						admissionNoteObiject);
				
				if(admissionNoteDto.getBedCategoryWaitingListId()==0 && !(admissionNoteDto.getDoa().equals(admissionNoteDto.getOldDoa())))
				{
					if(res2.getStatus().equals(SUCCESS))
					{
						Response res4 = iAdmissionRequestService.saveWaitingListNumber(admissionNoteDto);
						Integer bedCategoryWatingListId=(Integer)res4.getObject();
						admissionNoteDto.setBedCategoryWaitingListId(bedCategoryWatingListId);
					}
				}
				
				if(!(admissionNoteDto.getDoa().equals(admissionNoteDto.getOldDoa())) || !(admissionNoteDto.getBedCategoryId().equals(admissionNoteDto.getOldBedCategoryId())) && admissionNoteDto.getBedCategoryWaitingListId()>0)
				{
					if (res2.getStatus().equals(SUCCESS)) {
					
					AdmissionDto admissionDto = new AdmissionDto();
						admissionDto.setOrganizationId(admissionNoteDto.getOrganizationId());
						admissionDto.setUnitId(admissionNoteDto.getUnitId());
						admissionDto.setAdmissionNoteId(admissionNoteDto.getAdmissionNoteId());
						admissionDto.setBedCategoryId(admissionNoteDto.getOldBedCategoryId());
						admissionDto.setDoa(admissionNoteDto.getOldDoa());
						admissionDto.setWaitListNumber(admissionNoteDto.getWaitListNumber());
						admissionDto.setStatus('A');
						admissionDto.setCreatedBy(admissionNoteDto.getUpdatedBy());
						admissionDto.setUpdatedBy(admissionNoteDto.getUpdatedBy());
						admissionDto.setCreatedDate(admissionNoteDto.getUpdatedDate());
						admissionDto.setUpdatedDate(admissionNoteDto.getUpdatedDate());
						admissionDto.setBedCategoryWaitingListId(admissionNoteDto.getBedCategoryWaitingListId());
					Response res3 = iBedAllocationService.updateWatingListNumber(admissionDto);
					
					if (res3.getStatus().equals(SUCCESS)) {
						
						Response res4 = iAdmissionRequestService.saveWaitingListNumber(admissionNoteDto);
						
						if (res4.getStatus().equals(SUCCESS)) {
							AdmissionDto admissionDto2 = new AdmissionDto();
								admissionDto2.setOrganizationId(admissionNoteDto.getOrganizationId());
								admissionDto2.setUnitId(admissionNoteDto.getUnitId());
								admissionDto2.setAdmissionNoteId(admissionNoteDto.getAdmissionNoteId());
								admissionDto2.setBedCategoryId(admissionNoteDto.getBedCategoryId());
								admissionDto2.setDoa(admissionNoteDto.getDoa());
								admissionDto2.setWaitListNumber(admissionNoteDto.getWaitListNumber());
								admissionDto2.setStatus('A');
								admissionDto2.setCreatedBy(admissionNoteDto.getUpdatedBy());
								admissionDto2.setUpdatedBy(admissionNoteDto.getUpdatedBy());
								admissionDto2.setCreatedDate(admissionNoteDto.getUpdatedDate());
								admissionDto2.setUpdatedDate(admissionNoteDto.getUpdatedDate());
								admissionDto2.setBedCategoryWaitingListId(admissionNoteDto.getBedCategoryWaitingListId());
							return iBedAllocationService.updateWatingListNumber(admissionDto2);
							
						}
					}
				}
				}else
				{
					return new Response (SUCCESS,null,null,null,null);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@RequestMapping(value = "/updateReserveBedDetails", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response updateReserveBedDetails(@RequestBody AdmissionNoteDto admissionNoteDto) {
		try {
			return iPendingRequestService.updateReserveBedDetails(admissionNoteDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@RequestMapping(value = "/uploadAdmissionDocuments", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Response uploadAdmissionDocuments(@RequestBody AdmissionNoteDto admissionNoteDto) {
		try {
			return iPendingRequestService.uploadAdmissionDocuments(admissionNoteDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

}
