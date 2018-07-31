package com.param.opd.coversheet.service;

import in.param.exception.ApplicationException;

import com.param.global.common.Response;
import com.param.opd.coversheet.dto.ComplaintAppointmentMapperDto;
import com.param.opd.coversheet.model.ComplaintAppointmentMapper;

@SuppressWarnings("rawtypes")
public interface IComplaintAppointmentMapperService {

	public Response saveComplaintAppointmentMapper(ComplaintAppointmentMapperDto complaintAppointmentMapperDto)throws ApplicationException;

	public Response getListOfComplaintAppointmentMapper(ComplaintAppointmentMapperDto complaintAppointmentMapperDto)throws ApplicationException;
	
	public Response editListOfComplaintAppointmentMapper(ComplaintAppointmentMapperDto complaintAppointmentMapperDto)throws ApplicationException;
	
	public Response updateComplaintAppointmentMapper(ComplaintAppointmentMapperDto complaintAppointmentMapperDto)throws ApplicationException;
	
	public Response updateSaveNurseReviewedFlag(ComplaintAppointmentMapper complaintAppointmentMapper)throws ApplicationException;
}
