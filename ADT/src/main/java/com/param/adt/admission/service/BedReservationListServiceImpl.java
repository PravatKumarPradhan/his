package com.param.adt.admission.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.adt.admission.dao.IBedReservationListDao;
import com.param.adt.admission.dto.UnreservedPatientDto;
import com.param.adt.common.ICommonConstants;
import com.param.adt.common.Response;
import com.param.global.dto.AdmissionDto;

import in.param.exception.ApplicationException;
@Service
@SuppressWarnings({"rawtypes","unchecked"})
public class BedReservationListServiceImpl implements IBedReservationListService,ICommonConstants
{

	@Autowired
	IBedReservationListDao iBedReservationListDao;
	

	@Override
	public Response getBedReservationList(AdmissionDto admissionDto) throws ApplicationException {
		try {
			return iBedReservationListDao.getBedReservationList(admissionDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);

	}


	@Override
	public Response unreservePatrientBooking(UnreservedPatientDto unreservedPatientDto) throws ApplicationException 
	{
		try{
			
			if(unreservedPatientDto.getIsCancelReservation()=='Y')
			{
				
				unreservedPatientDto.setIsCancelReservation('Y');
				unreservedPatientDto.setIsRescheduleReservation('N');
				unreservedPatientDto.setStatus('I');
				/*unreservedPatientDto.setCreatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));
				unreservedPatientDto.setUpdatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));*/
				iBedReservationListDao.cancelReservation(unreservedPatientDto);
			}
			else
			{
				unreservedPatientDto.setIsRescheduleReservation('Y');
				unreservedPatientDto.setDoa(unreservedPatientDto.getNewDoa());
				unreservedPatientDto.setActiveStatus('P');
				/*unreservedPatientDto.setCreatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));
				unreservedPatientDto.setUpdatedDate(CommonDateUtils.getStringDateByzone("Asia/Calcutta", "dd-M-yyyy HH:mm:ss"));*/
				iBedReservationListDao.rescheduleReservation(unreservedPatientDto);
			}
			
			return new Response<>(SUCCESS, null, COMMON_SUCCESS, null, null);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
		
	}


	@Override
	public Response getBedReservationCount(AdmissionDto admissionDto) throws ApplicationException {
		try {
			return iBedReservationListDao.getCount(admissionDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, null, null, null);
	}


}
