package com.param.opd.coversheet.dao;

import in.param.common.dao.IGenericDao;
import in.param.exception.ApplicationException;

import com.param.global.common.Response;
import com.param.opd.coversheet.dto.AppointmentVitalMapperDto;
import com.param.opd.coversheet.model.AppointmentVitalMapper;

public interface IAppointmentVitalMapperDao extends IGenericDao<AppointmentVitalMapper, Integer>{

	public Response saveAppointmentVitalMapper(AppointmentVitalMapperDto appointmentVitalMapperDto)throws ApplicationException;

	public Response getListOfAppointmentVitalMapper(AppointmentVitalMapperDto appointmentVitalMapperDto)throws ApplicationException;
	
	public Response updateAppointmentVitalMapper(AppointmentVitalMapperDto appointmentVitalMapperDto)throws ApplicationException;
	
	public Response getListOfAppointmentVitalMapperByPatientId(AppointmentVitalMapperDto appointmentVitalMapperDto)throws ApplicationException;
	
	public Response getOldAppointmentVitalMapper(AppointmentVitalMapperDto appointmentVitalMapperDto)throws ApplicationException;
	
	public Response getCurrentAppointmentVitalMapper(AppointmentVitalMapperDto appointmentVitalMapperDto)throws ApplicationException;
	
	public Response updateAppointmentVitalMapperDto(AppointmentVitalMapperDto appointmentVitalMapperDto)throws ApplicationException;
	
	public Response updateSaveNurseReviewedFlag(AppointmentVitalMapper appointmentVitalMapper)throws ApplicationException;
}
