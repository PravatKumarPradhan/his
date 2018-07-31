package com.param.opd.coversheet.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.param.exception.ApplicationException;

import com.param.global.common.Response;
import com.param.opd.coversheet.dao.IComplaintAppointmentMapperDao;
import com.param.opd.coversheet.dto.ComplaintAppointmentMapperDto;
import com.param.opd.coversheet.model.ComplaintAppointmentMapper;

@Service
@SuppressWarnings("rawtypes")
public class ComplaintAppointmentMapperServiceImpl implements IComplaintAppointmentMapperService {

	@Autowired
	private IComplaintAppointmentMapperDao iComplaintAppointmentMapperDao;
	
	@Override
	@Transactional
	public Response saveComplaintAppointmentMapper(ComplaintAppointmentMapperDto complaintAppointmentMapperDto)throws ApplicationException {
		// TODO Auto-generated method stub
		return iComplaintAppointmentMapperDao.saveComplaintAppointmentMapper(complaintAppointmentMapperDto);
	}

	@Override
	@Transactional
	public Response getListOfComplaintAppointmentMapper(ComplaintAppointmentMapperDto complaintAppointmentMapperDto)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return iComplaintAppointmentMapperDao.getListOfComplaintAppointmentMapper(complaintAppointmentMapperDto);
	}

	@Override
	@Transactional
	public Response editListOfComplaintAppointmentMapper(ComplaintAppointmentMapperDto complaintAppointmentMapperDto)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return iComplaintAppointmentMapperDao.editListOfComplaintAppointmentMapper(complaintAppointmentMapperDto);
	}

	@Override
	@Transactional
	public Response updateComplaintAppointmentMapper(
			ComplaintAppointmentMapperDto complaintAppointmentMapperDto)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return iComplaintAppointmentMapperDao.updateComplaintAppointmentMapper(complaintAppointmentMapperDto);
	}

	@Override
	@Transactional
	public Response updateSaveNurseReviewedFlag(
			ComplaintAppointmentMapper complaintAppointmentMapper)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return iComplaintAppointmentMapperDao.updateSaveNurseReviewedFlag(complaintAppointmentMapper);
	}

}
