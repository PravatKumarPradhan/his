package com.param.lis.transaction.service;

import java.util.List;

import in.param.exception.ApplicationException;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.param.entity.lis.transaction.LabSampleDetailsMaster;
import com.param.lis.global.common.ICommonConstants;
import com.param.lis.global.common.IError;
import com.param.lis.global.common.Response;
import com.param.lis.transaction.dao.IPatientArrivalDao;
import com.param.lis.transaction.dto.LabCommonDto;
import com.param.lis.transaction.dto.LabSampleDetailsMasterDto;
import com.param.lis.transaction.dto.PatientArrivalMapperMasterDto;

@Service
@SuppressWarnings(
{ "rawtypes", "unchecked" })
public class PatientArrivalServiceImpl implements IPatientArrivalService, ICommonConstants, IError {

//PatientArrivalServiceImpl
	final static Logger logger = Logger.getLogger(PatientArrivalServiceImpl.class);


	@Autowired
	IPatientArrivalDao iPatientArrivalDao; 
	
	@Autowired
	private SessionFactory sessionFactory;
	@Override
	@Transactional
	public Response patientArrival(LabCommonDto labCommonDto)
			throws ApplicationException {
		try
		{
			Response res =  iPatientArrivalDao.patientArrival(labCommonDto);
			if(res.getListObject() != null && res.getListObject().size() > 0){
				return new Response(SUCCESS, SUCCESS_200, null, res.getListObject(), null);
			}else{
				return new Response(SUCCESS, SUCCESS_200,null, null, null);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
		
	}

	@Override
	@Transactional
	public Response patientArrivalList(LabCommonDto labCommonDto) throws ApplicationException {
		try
		{
			return  iPatientArrivalDao.patientArrivalList(labCommonDto);
			
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	@Transactional
	public Response patientArrivalMapperMaster(
			List<PatientArrivalMapperMasterDto> patientArrivalMapperMasterDto)
			throws ApplicationException {
		try
		{
			return  iPatientArrivalDao.patientArrivalMapperMaster(patientArrivalMapperMasterDto);
			
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}

	@Override
	public Response patientNotArrivalData(
			LabSampleDetailsMaster labSampleDetailsMaster)
			throws ApplicationException {
		try
		{
			return  iPatientArrivalDao.patientNotArrivalData(labSampleDetailsMaster);
			
		} catch (Exception e)
		{
			e.printStackTrace();
			return new Response(ERROR, ERR_500, ERR_500_MESSAGE, null, null);
		}
	}
}




