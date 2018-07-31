package com.param.opd.coversheet.dao;


import com.param.global.common.Response;
import com.param.opd.coversheet.dto.ComplaintAppointmentMapperDto;
import com.param.opd.coversheet.model.ComplaintAppointmentMapper;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;

public interface IComplaintAppointmentMapperDao extends IGenericDao<ComplaintAppointmentMapper, Integer>{

	public Response saveComplaintAppointmentMapper(ComplaintAppointmentMapperDto complaintAppointmentMapperDto)throws ApplicationException;

	public Response getListOfComplaintAppointmentMapper(ComplaintAppointmentMapperDto complaintAppointmentMapperDto)throws ApplicationException;
	
	public Response editListOfComplaintAppointmentMapper(ComplaintAppointmentMapperDto complaintAppointmentMapperDto)throws ApplicationException;
	
	public Response updateComplaintAppointmentMapper(ComplaintAppointmentMapperDto complaintAppointmentMapperDto)throws ApplicationException;
	
	public Response updateSaveNurseReviewedFlag(ComplaintAppointmentMapper complaintAppointmentMapper)throws ApplicationException;
}
