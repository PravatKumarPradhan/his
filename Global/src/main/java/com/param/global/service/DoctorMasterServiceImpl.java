package com.param.global.service;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.common.ScheduleActions;
import com.param.global.dao.IDoctorMasterDao;
import com.param.global.dao.IScheduleLogsDao;
import com.param.global.dto.DependentDetailsDto;
import com.param.global.dto.DoctorMasterDto;
import com.param.global.dto.PatientRegistrationDto;
import com.param.global.dto.ScheduleLogsDto;
import com.param.global.model.DoctorMaster;
import com.param.global.model.PatientRegistration;

import in.param.exception.ApplicationException;

@Service
@SuppressWarnings({ "rawtypes", "unchecked" })
public class DoctorMasterServiceImpl implements IDoctorMasterService,ICommonConstants {

	@Autowired
	private IDoctorMasterDao iDoctorMasterDao;
	
	@Autowired
	IDependentDetailsService iDependentDetailsService;
	
	@Autowired
	private IScheduleLogsDao iScheduleLogsDao;
	
	
	@Override
	public Response getDoctorMasterList(DoctorMasterDto doctorMasterDto)throws ApplicationException {
		try{
			return iDoctorMasterDao.getDoctorMasterList(doctorMasterDto);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response saveDoctorRegistration(DoctorMasterDto doctorMasterDto) throws ApplicationException {
		try {

			Response res = iDoctorMasterDao.checkUniqueDoctor(doctorMasterDto);
			/*
			 * if (res.getListObject().size() > 0) { return new Response(ERROR,
			 * null, "Already exist !!", null, null); } else {
			 */
			Response res1 = iDoctorMasterDao.saveDoctorRegistration(doctorMasterDto);
			doctorMasterDto.setDoctorId((Integer) res1.getObject());
			if (res1.getStatus().equals(SUCCESS)) {

				Response docSpe = iDoctorMasterDao.saveDoctorSpecialityMapper(doctorMasterDto);
				if (docSpe.getStatus().equals(SUCCESS)) {
					Response patientResponse = iDoctorMasterDao.saveDoctorDetails(doctorMasterDto);

					if (patientResponse.getStatus().equals(SUCCESS)
							&& doctorMasterDto.getDependentDetailsDtosList().size() > 0) {

						DependentDetailsDto dependentDetailsDto = new DependentDetailsDto();
						dependentDetailsDto.setTypeId(doctorMasterDto.getTypeId());
						dependentDetailsDto.setEmpDocId(doctorMasterDto.getDoctorId());
						dependentDetailsDto.setDependentDetailsDtoList(doctorMasterDto.getDependentDetailsDtosList());
						Response DependentRes = iDependentDetailsService.saveDependentDetails(dependentDetailsDto);
						if (DependentRes.getStatus().equals(SUCCESS)) {
							//doctor Entry in log table start
							DoctorMaster doctorRegistration=new DoctorMaster();
							DoctorMasterDto doctorRegistrationDto= (DoctorMasterDto)patientResponse.getObject();
							ScheduleLogsDto scheduleLogsDto=new ScheduleLogsDto();
							 	scheduleLogsDto.setAction(ScheduleActions.INSERT);
							 	scheduleLogsDto.setAddedBy(1);
							 	scheduleLogsDto.setAddedDate(doctorRegistrationDto.getCreatedDate());
							 	scheduleLogsDto.setApplication(ScheduleActions.HIS);
							 	scheduleLogsDto.setDateTime(doctorRegistrationDto.getCreatedDate());
							 	scheduleLogsDto.setErrorCount(0);
							 	scheduleLogsDto.setPriority(ScheduleActions.PRIORITY_NORM);
							 	scheduleLogsDto.setRecordId(doctorRegistrationDto.getDoctorId());
							 	scheduleLogsDto.setStatus(ScheduleActions.STATUS_PENDING);
							 	String[] tableName = doctorRegistration.getClass().getName().split("\\.");
							 	scheduleLogsDto.setTableName(tableName[tableName.length - 1]);						 	
							iScheduleLogsDao.saveScheduleLogs(scheduleLogsDto);
							//doctor Entry in log table end
							return new Response(SUCCESS, SUCCESS_CODE, COMMON_SUCCESS, null, null);
						}

					}
				}
			}
			// }

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR, COMMON_ERROR_MESSAGE, null, null);
	}


	@Override
	public Response getDoctorDetails(DoctorMasterDto doctorMasterDto) throws ApplicationException {
		try {
			List<DoctorMasterDto> doctorMasterDtosList = new LinkedList<DoctorMasterDto>(); 
			Response responseDocDetails = iDoctorMasterDao.getDoctorDetails(doctorMasterDto);
			if(responseDocDetails.getListObject().size()>0)
			{
				ListIterator<DoctorMasterDto> iterator = responseDocDetails.getListObject().listIterator();
				while(iterator.hasNext())
				{
					DoctorMasterDto masterDtoList = iterator.next();
					
					DependentDetailsDto dependentDetailsDto = new DependentDetailsDto();
						dependentDetailsDto.setTypeId(doctorMasterDto.getTypeId());
						dependentDetailsDto.setEmpDocId(masterDtoList.getDoctorId());
					
					Response dependentRes= iDependentDetailsService.getDependentDetails(dependentDetailsDto);
						masterDtoList.setDependentDetailsDtosList(dependentRes.getListObject());
					
					doctorMasterDtosList.add(masterDtoList);
					
				}
				
			}
			
			return new Response(SUCCESS, SUCCESS_CODE, null, doctorMasterDtosList, null);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR, COMMON_ERROR_MESSAGE, null, null);
	}

	
	@Override
	public Response updateDoctorDetails(DoctorMasterDto doctorMasterDto) throws ApplicationException {
		try {
			Response resEmpDependent = null;
			Response res = iDoctorMasterDao.checkUniqueDoctor(doctorMasterDto);
			if (res.getStatus().equals(SUCCESS)) {
				Response resEmpDetails = iDoctorMasterDao.updateDoctorDetails(doctorMasterDto);
				if (resEmpDetails.getStatus().equals(SUCCESS)) {

					DependentDetailsDto dependentDetailsDto = new DependentDetailsDto();
					dependentDetailsDto.setEmpDocId(doctorMasterDto.getDoctorId());
					dependentDetailsDto.setTypeId(doctorMasterDto.getTypeId());
					dependentDetailsDto.setUpdatedBy(doctorMasterDto.getUpdatedBy());
					dependentDetailsDto.setUpdatedDate(doctorMasterDto.getUpdatedDate());
					dependentDetailsDto.setDependentDetailsDtoList(doctorMasterDto.getDependentDetailsDtosList());
					resEmpDependent = iDependentDetailsService.updateDependentDetails(dependentDetailsDto);

					if (resEmpDependent.getStatus().equals(SUCCESS))
						return new Response(SUCCESS, null, COMMON_UPDATE, null, null);
					else
						return new Response(ERROR, null, "Problem in updating Dependent Details !!", null, null);
				}
			} else
				return new Response(ERROR, null, "Employee does not exist !!", null, null);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR, COMMON_ERROR_MESSAGE, null, null);
	}


	@Override
	public Response updateDoctorStatus(DoctorMasterDto doctorMasterDto) throws ApplicationException {
		try {

			return iDoctorMasterDao.updateDoctorStatus(doctorMasterDto);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response searchDoctorDetailsByCriteria(DoctorMasterDto doctorMasterDto) throws ApplicationException {
		try {
			List<DoctorMasterDto> doctorMasterDtosList = new LinkedList<DoctorMasterDto>(); 
			Response responseDocDetails = iDoctorMasterDao.searchDoctorDetailsByCriteria(doctorMasterDto);
			if(responseDocDetails.getListObject().size()>0)
			{
				ListIterator<DoctorMasterDto> iterator = responseDocDetails.getListObject().listIterator();
				while(iterator.hasNext())
				{
					DoctorMasterDto masterDtoList = iterator.next();
					
					DependentDetailsDto dependentDetailsDto = new DependentDetailsDto();
						dependentDetailsDto.setTypeId(doctorMasterDto.getTypeId());
						dependentDetailsDto.setEmpDocId(masterDtoList.getDoctorId());
					
					Response dependentRes= iDependentDetailsService.getDependentDetails(dependentDetailsDto);
						masterDtoList.setDependentDetailsDtosList(dependentRes.getListObject());
					
					doctorMasterDtosList.add(masterDtoList);
					
				}
				
			}
			
			return new Response(SUCCESS, SUCCESS_CODE, null, doctorMasterDtosList, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR, COMMON_ERROR_MESSAGE, null, null);
	}

}
