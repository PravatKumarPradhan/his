package com.param.opd.unit.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.dto.BlockSlotReqDto;
import com.param.global.dto.DayMasterDto;
import com.param.global.dto.DoctorMasterDto;
import com.param.global.dto.SlotBlockUnblockRequestDto;
import com.param.global.dto.SlotMasterDto;
import com.param.global.dto.SlotPatientDetails;
import com.param.global.dto.TimeSlotsDto;
import com.param.global.opd.service.SlotsCreationService;
import com.param.opd.coversheet.dto.WeeklySlotsDto;
import com.param.opd.unit.dao.ISlotBlockUnblockDetailDao;
import com.param.opd.unit.dao.ISlotMasterDao;

import in.param.exception.ApplicationException;

@Service
@SuppressWarnings({ "rawtypes", "unchecked" })
public class SlotMasterServiceImpl implements ISlotMasterService, ICommonConstants {

	@Autowired
	ISlotMasterDao iSlotMasterDao;

	@Autowired
	SlotsCreationService slotsCreationService;
	
	@Autowired
	ISlotBlockUnblockDetailDao iSlotBlockUnblockDetailDao;

	@Override
	public Response generateSlots(SlotMasterDto slotMasterDto) throws ApplicationException {

		try {
			List<List<DayMasterDto>> datesMasterList = new ArrayList<>();
			List<TimeSlotsDto> timeSlotsDtos = new LinkedList<>();
			List<String> timeSlotsList = new ArrayList<String>();
			Integer docId=null,speId = null,modalityId=null,subSpecilaityId=null;
			
			// Iterating master list
			for (int i = 0; i < slotMasterDto.getSlotMasterDtosList().size(); i++) {
				Iterator<SlotMasterDto> itr = slotMasterDto.getSlotMasterDtosList().iterator();

				while (itr.hasNext()) {

					SlotMasterDto slotMasterDtosObj = itr.next();

						// Generating list of time slots
						timeSlotsList = slotsCreationService.getTimeSlot(slotMasterDtosObj.getFromTime().toString(),
								slotMasterDtosObj.getToTime().toString(), 
								slotMasterDtosObj.getTimePerSlot());
						
						if((timeSlotsList.isEmpty())){ 
							 return new Response(ERROR, null, "Please enter accurate slot time !!", null, null); 
						}
						
						TimeSlotsDto timeSlotsDto = null;
						for (int k = 0; k < (timeSlotsList.size() - 1); k++) {
							timeSlotsDto = new TimeSlotsDto();
								timeSlotsDto.setFromTime(timeSlotsList.get(k));
								timeSlotsDto.setToTime(timeSlotsList.get(k + 1));
							// checking slots are in between break time or not
							if (slotsCreationService.isSlotIsBetweenBreakTime(slotMasterDtosObj.getBreakSlotsList(),
									timeSlotsDto) == true)
								timeSlotsDtos.add(timeSlotsDto);
						}
						
						//generating list of dates excluding holidays
						datesMasterList = slotsCreationService.getListOfDatesAsPerDayBetweenTwoDates(
								slotMasterDtosObj.getFromDate(), 
								slotMasterDtosObj.getToDate(),
								slotMasterDtosObj.getDayArrayList(), 
								slotMasterDto.getOrganizationId(),
								slotMasterDto.getUnitId());
	
						ListIterator doctorItr = slotMasterDto.getDoctorArrayList().listIterator();
						while (doctorItr.hasNext()) 
						{
							DoctorMasterDto doctorMasterDto = (DoctorMasterDto) doctorItr.next();
								if(slotMasterDto.getSlotTypeId()==1){
									docId = doctorMasterDto.getDoctorId();
									speId = doctorMasterDto.getSpecialityId();
								}
								if(slotMasterDto.getSlotTypeId()==2){
									modalityId=doctorMasterDto.getModalityId();
									subSpecilaityId=doctorMasterDto.getSubSpecialityId();
									speId = doctorMasterDto.getSpecialityId();
								}
								
							ListIterator superItr = datesMasterList.listIterator();	//Iterator for list of dto's
							ListIterator childItr = null; //Iterator for days and dates from DayMasterDto
							while (superItr.hasNext()) {
								List<List<DayMasterDto>> childList = (List<List<DayMasterDto>>) superItr.next();
	
								childItr = childList.listIterator();
								while (childItr.hasNext()) {
									DayMasterDto data = (DayMasterDto) childItr.next();
										String date = data.getDay();
										Integer dayId = data.getDayId();
	
									ListIterator timeSlotsDtosItrList = timeSlotsDtos.listIterator();
									while (timeSlotsDtosItrList.hasNext()) {
	
										TimeSlotsDto timeSlotsDto2 = (TimeSlotsDto) timeSlotsDtosItrList.next();
	
										SlotMasterDto slotMasterDtoNew = new SlotMasterDto();
											slotMasterDtoNew.setOrganizationId(slotMasterDto.getOrganizationId());
											slotMasterDtoNew.setUnitId(slotMasterDto.getUnitId());
											slotMasterDtoNew.setSpecialityId(speId);
											slotMasterDtoNew.setDoctorId(docId);
											slotMasterDtoNew.setFromDate(date);// appointmentDate
											slotMasterDtoNew.setDayId(dayId);
											slotMasterDtoNew.setFromTime(timeSlotsDto2.getFromTime());
											slotMasterDtoNew.setToTime(timeSlotsDto2.getToTime());
											slotMasterDtoNew.setCreatedBy(slotMasterDto.getCreatedBy());
											slotMasterDtoNew.setUpdatedBy(slotMasterDto.getUpdatedBy());
											slotMasterDtoNew.setCreatedDate(slotMasterDto.getCreatedDate());
											slotMasterDtoNew.setUpdatedDate(slotMasterDto.getUpdatedDate());
											slotMasterDtoNew.setStatus('A');
											slotMasterDtoNew.setSlotTypeId(slotMasterDto.getSlotTypeId());
											slotMasterDtoNew.setModalityId(modalityId);
											slotMasterDtoNew.setSubSpecialityId(subSpecilaityId);
										iSlotMasterDao.generateSlots(slotMasterDtoNew);
									}
								}
							}
						}
					}
				}

			return new Response(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, null, null);
		} catch (Exception e) {
			return new Response(ERROR, null, null, null, null);
		}
	}

	@Override
	public Response generateSlotsForECH(SlotMasterDto slotMasterDto) throws ApplicationException {
		try {
			List<List<DayMasterDto>> datesMasterList = new ArrayList<>();

			for (int i = 0; i < slotMasterDto.getSlotMasterDtosList().size(); i++) {
				Iterator<SlotMasterDto> itr = slotMasterDto.getSlotMasterDtosList().iterator();

				while (itr.hasNext()) {

					SlotMasterDto slotMasterDtosObj = itr.next();

					// Generating list of dates excluding holidays
					datesMasterList = slotsCreationService.getListOfDatesAsPerDayBetweenTwoDates(
							slotMasterDtosObj.getFromDate(), slotMasterDtosObj.getToDate(),
							slotMasterDtosObj.getDayArrayList(), slotMasterDto.getOrganizationId(),
							slotMasterDto.getUnitId());

					ListIterator superItr = datesMasterList.listIterator();	// Iterator for list of dto's
					ListIterator childItr = null; // Iterator for child list for retrieving days and dates from superItr.
					while (superItr.hasNext()) {
						List<List<DayMasterDto>> childList = (List<List<DayMasterDto>>) superItr.next();

						childItr = childList.listIterator();
						while (childItr.hasNext()) {
							DayMasterDto data = (DayMasterDto) childItr.next();
								String date = data.getDay();
								Integer dayId = data.getDayId();
							// Iterating loop till number of slots	
							for (int j = 0; j < slotMasterDtosObj.getNoOfSlots(); j++) {
								SlotMasterDto slotMasterDtoNew = new SlotMasterDto();
									slotMasterDtoNew.setOrganizationId(slotMasterDto.getOrganizationId());
									slotMasterDtoNew.setUnitId(slotMasterDto.getUnitId());
									slotMasterDtoNew.setFromDate(date);// AppointmentDate
									slotMasterDtoNew.setDayId(dayId);
									slotMasterDtoNew.setCreatedBy(slotMasterDto.getCreatedBy());
									slotMasterDtoNew.setUpdatedBy(slotMasterDto.getUpdatedBy());
									slotMasterDtoNew.setCreatedDate(slotMasterDto.getCreatedDate());
									slotMasterDtoNew.setUpdatedDate(slotMasterDto.getUpdatedDate());
									slotMasterDtoNew.setStatus('A');
									slotMasterDtoNew.setSlotTypeId(slotMasterDto.getSlotTypeId());
								iSlotMasterDao.generateSlots(slotMasterDtoNew);
							}
						}
					}

				}
			}

			return new Response(SUCCESS, SUCCESS_CODE, SUCCESS_MESSAGE, null, null);
		} catch (Exception e) {
			return new Response(ERROR, null, null, null, null);
		}
	}
	
	@Override
	public Response getSlots(SlotMasterDto slotMasterDto) throws ApplicationException {
		try {
			return iSlotMasterDao.getSlots(slotMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getSlotCount(SlotMasterDto slotMasterDto) throws ApplicationException {
		try {
			return iSlotMasterDao.getSlotCount(slotMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getSlotDetails(SlotMasterDto slotMasterDto) throws ApplicationException {
		try {
			return iSlotMasterDao.getSlotDetails(slotMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response updateSlotStatus(SlotMasterDto slotMasterDto) throws ApplicationException {
		try {
			return iSlotMasterDao.updateSlotStatus(slotMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}
	
	@Override
	public Response updateStatusOfSlot(SlotMasterDto slotMasterDto) throws ApplicationException {
		try {
			return iSlotMasterDao.updateStatusOfSlot(slotMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getMonthlySlots(SlotMasterDto slotMasterDto) throws ApplicationException {
		try {

			DateTime dt = new DateTime().withYear(slotMasterDto.getYear()).withMonthOfYear(slotMasterDto.getMonth());
			
			DateTime start = dt.withDayOfMonth(1).withTimeAtStartOfDay();
			DateTime end = start.plusMonths(1).minusMillis(1);
			
			slotMasterDto.setFromDate(start.toString().substring(0, 10));
			slotMasterDto.setToDate(end.toString().substring(0, 10));
			List<SlotMasterDto> appointmentsForMonthsList = (List<SlotMasterDto>)iSlotMasterDao.getAppointmentsForMonth(slotMasterDto);
			
			List<String> finalDateList = new LinkedList<>();
			while (!start.isAfter(end)) {

				int nameOfWeekday = start.getDayOfWeek();
				if(slotsCreationService.isSlotAvailble(appointmentsForMonthsList,start.toString().substring(0,10))==true)
					finalDateList.add(nameOfWeekday+"_"+start.toString().substring(0,10)+"_Y");
				else
					finalDateList.add(nameOfWeekday+"_"+start.toString().substring(0,10)+"_N");
			    
				start = start.plusDays(1);
			
			}
			
			List<List<String>> finalList=new LinkedList<>();
			List<String> newList=new LinkedList<>();
			ListIterator<String> itr=finalDateList.listIterator();
			int index=0;
			while(itr.hasNext())
			{
				String addData=itr.next();
				Integer i = (Integer.parseInt(addData.substring(0,1)));
				newList.add(addData);
				index++;
				if(i==7 || (index==finalDateList.size())){
					finalList.add(newList);
					newList=new LinkedList<>();
				}
				
			}

			return new Response<>(SUCCESS,null,null,finalList,null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return new Response<>(ERROR,null,null,null,null);
	}

	@Override
	public Response getYearlySlots(SlotMasterDto slotMasterDto) throws ApplicationException {
		try {
			return iSlotMasterDao.getYearlySlots(slotMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getDailySlots(SlotMasterDto slotMasterDto) throws ApplicationException {
		try {
			
			Integer slotId=0;
			WeeklySlotsDto masterDto = null;
			List<WeeklySlotsDto> slotMasterDtoFinalList= new LinkedList<>();
			Response weeklySlotsRes =  iSlotMasterDao.getDailySlots(slotMasterDto);
			List<SlotMasterDto> weeklySlotsList =  weeklySlotsRes.getListObject();
			List<SlotPatientDetails> details2 = new LinkedList<>();
			
			
			ListIterator<SlotMasterDto> iterator = weeklySlotsList.listIterator(); //main list
			while(iterator.hasNext())
			{
				SlotMasterDto iteratorObj = iterator.next();
				
				if(slotId.equals(iteratorObj.getSlotId()))
				{
					SlotPatientDetails patientDetails2 = new SlotPatientDetails();
						patientDetails2.setAppointmentId(iteratorObj.getAppointmentId());
						patientDetails2.setPatientId(iteratorObj.getPatientId());
						patientDetails2.setPatientName(iteratorObj.getPatientName());
						patientDetails2.setAddress(iteratorObj.getAddress());
						patientDetails2.setEmail(iteratorObj.getEmail());
						patientDetails2.setMobileNumber(iteratorObj.getMobileNumber());
						patientDetails2.setUhidNumber(iteratorObj.getUhidNumber());	
					
					details2.add(patientDetails2);
					masterDto.setSlotPatientDetailsList(details2);	
					
					int lastEntry = slotMasterDtoFinalList.size();
					slotMasterDtoFinalList.remove(lastEntry-1);
					
					masterDto.setSlotPatientDetailsListsSize(details2.size());
					slotMasterDtoFinalList.add(masterDto);
					
				}
				else
				{
				WeeklySlotsDto newSlotMasterDtoObj = new WeeklySlotsDto();
					newSlotMasterDtoObj.setSlotId(iteratorObj.getSlotId());
					newSlotMasterDtoObj.setSpecialityId(iteratorObj.getSpecialityId());
					newSlotMasterDtoObj.setDoctorId(iteratorObj.getDoctorId());
					newSlotMasterDtoObj.setAppointmentDate(iteratorObj.getAppointmentDate());
					newSlotMasterDtoObj.setAppointmentDate2(iteratorObj.getAppointmentDate2());
					newSlotMasterDtoObj.setFromTimeString(iteratorObj.getFromTimeString());
					newSlotMasterDtoObj.setFromTimeString2(iteratorObj.getFromTimeString2());
					newSlotMasterDtoObj.setToTimeString(iteratorObj.getToTimeString());
					newSlotMasterDtoObj.setToTimeString2(iteratorObj.getToTimeString2());
					newSlotMasterDtoObj.setDoctorName(iteratorObj.getDoctorName());
					newSlotMasterDtoObj.setSpecialityName(iteratorObj.getSpecialityName());
					newSlotMasterDtoObj.setSlotStatusId(iteratorObj.getSlotStatusId());
					newSlotMasterDtoObj.setIsBlockedUnblocked(iteratorObj.getIsBlockedUnblocked());
					if(iteratorObj.getAppointmentId()!=null)
					{
					SlotPatientDetails patientDetails2 = new SlotPatientDetails();
						patientDetails2.setAppointmentId(iteratorObj.getAppointmentId());
						patientDetails2.setPatientId(iteratorObj.getPatientId());
						patientDetails2.setPatientName(iteratorObj.getPatientName());
						patientDetails2.setAddress(iteratorObj.getAddress());
						patientDetails2.setEmail(iteratorObj.getEmail());
						patientDetails2.setMobileNumber(iteratorObj.getMobileNumber());
						patientDetails2.setUhidNumber(iteratorObj.getUhidNumber());	
					List<SlotPatientDetails> details = new LinkedList<>();
					details.add(patientDetails2);
					newSlotMasterDtoObj.setSlotPatientDetailsList(details);	
					details2=details;
					newSlotMasterDtoObj.setSlotPatientDetailsListsSize(details.size());
					}
					else
					{
						newSlotMasterDtoObj.setSlotPatientDetailsListsSize(0);
					}
					slotMasterDtoFinalList.add(newSlotMasterDtoObj);
				
					slotId=iteratorObj.getSlotId();
					masterDto=newSlotMasterDtoObj;
					
					
				}		
			}
			return new Response(SUCCESS, null, null, slotMasterDtoFinalList, null);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getWeeklySlots(SlotMasterDto slotMasterDto) throws ApplicationException {
		try {
			Integer slotId=0;
			WeeklySlotsDto masterDto = null;
			List<WeeklySlotsDto> slotMasterDtoFinalList= new LinkedList<>();
			Response weeklySlotsRes =  iSlotMasterDao.getWeeklySlots(slotMasterDto);
			List<SlotMasterDto> weeklySlotsList =  weeklySlotsRes.getListObject();
			List<SlotPatientDetails> details2 = new LinkedList<>();
			
			
			ListIterator<SlotMasterDto> iterator = weeklySlotsList.listIterator(); //main list
			while(iterator.hasNext())
			{
				SlotMasterDto iteratorObj = iterator.next();
				
				if(slotId.equals(iteratorObj.getSlotId()))
				{
					SlotPatientDetails patientDetails2 = new SlotPatientDetails();
						patientDetails2.setAppointmentId(iteratorObj.getAppointmentId());
						patientDetails2.setPatientId(iteratorObj.getPatientId());
						patientDetails2.setPatientName(iteratorObj.getPatientName());
						patientDetails2.setAddress(iteratorObj.getAddress());
						patientDetails2.setEmail(iteratorObj.getEmail());
						patientDetails2.setMobileNumber(iteratorObj.getMobileNumber());
						patientDetails2.setUhidNumber(iteratorObj.getUhidNumber());	
					
					details2.add(patientDetails2);
					masterDto.setSlotPatientDetailsList(details2);	
					
					int lastEntry = slotMasterDtoFinalList.size();
					slotMasterDtoFinalList.remove(lastEntry-1);
					
					masterDto.setSlotPatientDetailsListsSize(details2.size());
					slotMasterDtoFinalList.add(masterDto);
					
				}
				else
				{
				WeeklySlotsDto newSlotMasterDtoObj = new WeeklySlotsDto();
					newSlotMasterDtoObj.setSlotId(iteratorObj.getSlotId());
					newSlotMasterDtoObj.setSpecialityId(iteratorObj.getSpecialityId());
					newSlotMasterDtoObj.setDoctorId(iteratorObj.getDoctorId());
					newSlotMasterDtoObj.setAppointmentDate(iteratorObj.getAppointmentDate());
					newSlotMasterDtoObj.setAppointmentDate2(iteratorObj.getAppointmentDate2());
					newSlotMasterDtoObj.setFromTimeString(iteratorObj.getFromTimeString());
					newSlotMasterDtoObj.setFromTimeString2(iteratorObj.getFromTimeString2());
					newSlotMasterDtoObj.setToTimeString(iteratorObj.getToTimeString());
					newSlotMasterDtoObj.setToTimeString2(iteratorObj.getToTimeString2());
					newSlotMasterDtoObj.setDoctorName(iteratorObj.getDoctorName());
					newSlotMasterDtoObj.setSpecialityName(iteratorObj.getSpecialityName());
					newSlotMasterDtoObj.setSlotStatusId(iteratorObj.getSlotStatusId());
					newSlotMasterDtoObj.setIsBlockedUnblocked(iteratorObj.getIsBlockedUnblocked());
					if(iteratorObj.getAppointmentId()!=null)
					{
					SlotPatientDetails patientDetails2 = new SlotPatientDetails();
						patientDetails2.setAppointmentId(iteratorObj.getAppointmentId());
						patientDetails2.setPatientId(iteratorObj.getPatientId());
						patientDetails2.setPatientName(iteratorObj.getPatientName());
						patientDetails2.setAddress(iteratorObj.getAddress());
						patientDetails2.setEmail(iteratorObj.getEmail());
						patientDetails2.setMobileNumber(iteratorObj.getMobileNumber());
						patientDetails2.setUhidNumber(iteratorObj.getUhidNumber());	
					List<SlotPatientDetails> details = new LinkedList<>();
					details.add(patientDetails2);
					newSlotMasterDtoObj.setSlotPatientDetailsList(details);	
					details2=details;
					newSlotMasterDtoObj.setSlotPatientDetailsListsSize(details.size());
					}
					else
					{
						newSlotMasterDtoObj.setSlotPatientDetailsListsSize(0);
					}
					slotMasterDtoFinalList.add(newSlotMasterDtoObj);
				
					slotId=iteratorObj.getSlotId();
					masterDto=newSlotMasterDtoObj;
					
					
				}		
			}
			return new Response(SUCCESS, null, null, slotMasterDtoFinalList, null);
			
			//return iSlotMasterDao.getWeeklySlots(slotMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response blockUnblockDoctorSlots(BlockSlotReqDto blockSlotReqDto)
			throws ApplicationException {
		try{
			Response updateSlotResponse  = iSlotMasterDao.blockUnblockSlots(blockSlotReqDto);
			if(updateSlotResponse.getStatus()==SUCCESS)
			{
				Response updateSlotResponse2 = iSlotBlockUnblockDetailDao.saveUpdatePrevSlotBlockDetails(blockSlotReqDto);
			
				if(updateSlotResponse2.getStatus()==SUCCESS)
				{
					iSlotBlockUnblockDetailDao.saveSlotBlockDetails(blockSlotReqDto);
				}
				
			}	
			return updateSlotResponse;
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getSlotsForBlockUnblock(
			SlotBlockUnblockRequestDto slotReqDto) throws ApplicationException {
		try {
			return iSlotMasterDao.getSlotsForBlockUnblock(slotReqDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public Response getBlockedUnblockedSlotDetails(SlotMasterDto slotMasterDto) throws ApplicationException {
		try {
			return iSlotMasterDao.getBlockedUnblockedSlotDetails(slotMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	public  Response isOverBookingAllowedOrNot(SlotMasterDto slotMasterDto) throws ApplicationException {
		try {
			
			Integer isOverbookingAllowed = null;
			Integer overbookingCount = null;
			List<Integer> intRes = new LinkedList<>();
			slotMasterDto.setSlotStatusId(1); //pending
			Response res = iSlotMasterDao.isOverBookingAllowedOrNot(slotMasterDto);
			
			slotMasterDto.setSlotStatusId(6);//overbooked
			Response res2 = iSlotMasterDao.isOverBookingAllowedOrNot(slotMasterDto);
			
			if(res.getStatus().equals(SUCCESS) && res2.getStatus().equals(SUCCESS))
			{
				if(res.getListObject().size()>0)
					isOverbookingAllowed = 0;		//over booking not allowed
				else 
					isOverbookingAllowed =  1;		//over booking allowed
				
				if(res2.getListObject().size()>0)
					overbookingCount = res2.getListObject().size();		//over booking not allowed
				else 
					overbookingCount =  0;
				
					intRes.add(isOverbookingAllowed);
					intRes.add(overbookingCount);
					
			}else
			{
				intRes=null;
			}
				
				
			return new Response(SUCCESS, null, null, intRes, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response(ERROR, null, COMMON_ERROR_MESSAGE, null, null);
	}
	
}
