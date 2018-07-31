package com.param.global.opd.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

import javax.transaction.Transactional;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.param.entity.model.master.Tax;
import com.param.global.common.GlobalCommonDateUtils;
import com.param.global.common.IAppointmentAndSlotStatusMasterConstants;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.common.ScheduleActions;
import com.param.global.dao.IDoctorConsultationServiceMapperDao;
import com.param.global.dao.IKinDetailsDao;
import com.param.global.dao.IOPConsultationConfigurationMasterDao;
import com.param.global.dao.IScheduleLogsDao;
import com.param.global.dao.IUnitHolidayCalenderDao;
import com.param.global.dao.IWeekendMasterDao;
import com.param.global.dto.DoctorConsultationServiceMapperDto;
import com.param.global.dto.HolidayCalenderMasterDto;
import com.param.global.dto.KinDetailsDto;
import com.param.global.dto.OPConsultationConfigurationMasterDto;
import com.param.global.dto.OrderDetailsMasterDto;
import com.param.global.dto.OrderMasterDto;
import com.param.global.dto.PatientSponsorDetailsDto;
import com.param.global.dto.ReferalDetailsDto;
import com.param.global.dto.ScheduleLogsDto;
import com.param.global.dto.ServiceMasterDto;
import com.param.global.dto.SlotMasterDto;
import com.param.global.dto.UnitHolidayCalenderMapperDto;
import com.param.global.dto.UnitServiceTariffMasterDto;
import com.param.global.dto.WeekendMasterDto;
import com.param.global.opd.dao.IAppointmentSchedulingDao;
import com.param.global.opd.dao.IEncounterMasterDao;
import com.param.global.service.IOrderMasterService;
import com.param.global.service.IPatientSponsorDetailsService;
import com.param.global.service.IReferalDetailsService;
import com.param.global.service.IServiceMasterService;
import com.param.opd.encounter.dto.AppointmentStatusMasterDto;
import com.param.opd.encounter.dto.EncounterMasterDto;

import in.param.exception.ApplicationException;

@Service
@SuppressWarnings({"rawtypes","unchecked","unused"})
public class EncounterMasterServiceImpl implements IEncounterMasterService,ICommonConstants, IAppointmentAndSlotStatusMasterConstants{
	
	
	@Autowired
	IEncounterMasterDao iEncounterMasterDao; 
	
	@Autowired
	IKinDetailsDao iKinDetailsDao;
	
	@Autowired
	IAppointmentSchedulingDao iAppointmentSchedulingDao;
	
	@Autowired
	IOrderMasterService iOrderMasterService; 
	
	@Autowired
	IDoctorConsultationServiceMapperDao iDoctorConsultationServiceMapperService;
	
	@Autowired
	IOPConsultationConfigurationMasterDao iOPConsultationConfigurationMasterService; 
	
	@Autowired
	IUnitHolidayCalenderDao iUnitHolidayCalenderDao;
	
	@Autowired
	IWeekendMasterDao iWeekendMasterService;
	
	@Autowired
	SlotsCreationService slotsCreationService;
	
	@Autowired
	IPatientSponsorDetailsService iPatientSponsorDetailsService; 
	
	@Autowired
	IReferalDetailsService iReferalDetailsService;
	
	@Autowired
	IScheduleLogsDao iScheduleLogsDao;
	
	@Autowired
	IServiceMasterService iServiceMasterService;
	
	
	@Override
	@Transactional
	public Response saveEncounterMaster(EncounterMasterDto encounterMasterDto) throws ApplicationException {
		try {
			Iterator<KinDetailsDto> itrKinDetailsDto =  encounterMasterDto.getListKinDetailsDto().iterator();
			int index=0;
			
			Integer serviceId=null;
			while(itrKinDetailsDto.hasNext()) {
				KinDetailsDto kinDetailsDto = itrKinDetailsDto.next();
				if(kinDetailsDto.getKinDetailsId() == 0 ) {
					if(kinDetailsDto.getKinName().equals(""))
						continue;
					Response res = iKinDetailsDao.saveKinDetails(kinDetailsDto);
					if(index == 0) {
						if(encounterMasterDto.getKinDetailsId() == null) {
							Integer kinDetailsId = (Integer) res.getObject(); 
							encounterMasterDto.setKinDetailsId(kinDetailsId);
						}
					}
				}
				else {
					iKinDetailsDao.updateKinDetails(kinDetailsDto);
					encounterMasterDto.setKinDetailsId(kinDetailsDto.getKinDetailsId());
				}
				index ++;
			}
			encounterMasterDto.setEncounterNumber(generateEncounterNumber());
			
			if(encounterMasterDto.getUserDefinedVisitTypeId()==1 || encounterMasterDto.getUserDefinedVisitTypeId()==2 || encounterMasterDto.getUserDefinedVisitTypeId()==3)
			{
				DoctorConsultationServiceMapperDto doctorConsultationServiceMapperDto=new DoctorConsultationServiceMapperDto();
				doctorConsultationServiceMapperDto.setOrganizationId(encounterMasterDto.getOrganizationId());
				doctorConsultationServiceMapperDto.setUnitId(encounterMasterDto.getUnitId());
				doctorConsultationServiceMapperDto.setDoctorId(encounterMasterDto.getDoctorId());
				doctorConsultationServiceMapperDto.setSpecialityId(encounterMasterDto.getSpecialityId());
				
				Response response= iDoctorConsultationServiceMapperService.getDoctorConsultationServiceBySpecialityAndDoctorId(doctorConsultationServiceMapperDto);
				if(response.getStatus().equals(SUCCESS))
				{
					DoctorConsultationServiceMapperDto doctorConsultationServiceMapperDto2=(DoctorConsultationServiceMapperDto) response.getObject();
					if(encounterMasterDto.getUserDefinedVisitTypeId()==1)
						serviceId=doctorConsultationServiceMapperDto2.getNewVisitServiceId();
					
					if(encounterMasterDto.getUserDefinedVisitTypeId()==2)
						serviceId=doctorConsultationServiceMapperDto2.getFollowupVisitServiceId();
					
					if(encounterMasterDto.getUserDefinedVisitTypeId()==3)
						serviceId=doctorConsultationServiceMapperDto2.getSecondaryVisitServiceId();
					encounterMasterDto.setServiceId(serviceId);
					
				}
				}
			//Saving encounter
			Response encounterResponse = iEncounterMasterDao.saveEncounterMaster(encounterMasterDto);
			if(encounterResponse.getStatus().equals(SUCCESS))
			{
				EncounterMasterDto encounterMasterDto2 =(EncounterMasterDto) encounterResponse.getObject();
				
				this.updateAutorenderServices(encounterMasterDto2);
				//Adding the doctorConsultationService in order master against encounter id
				if(serviceId!=null)
					this.saveOrderDetails(encounterMasterDto2,serviceId);
				
				PatientSponsorDetailsDto patientSponsorDetailsDto =new PatientSponsorDetailsDto();
					patientSponsorDetailsDto.setOrganizationId(encounterMasterDto2.getOrganizationId());
					patientSponsorDetailsDto.setUnitId(encounterMasterDto2.getUnitId());
					patientSponsorDetailsDto.setVisitTypeId(encounterMasterDto2.getVisitTypeId());
					patientSponsorDetailsDto.setEncounterId(encounterMasterDto2.getEncounterId());
					patientSponsorDetailsDto.setPaymentEntitlementId(encounterMasterDto2.getPaymentEntitlementTypeId());
					if(patientSponsorDetailsDto.getPaymentEntitlementId()==1)
						patientSponsorDetailsDto.setPayeeId(1);
					patientSponsorDetailsDto.setPriorityId(1);
					patientSponsorDetailsDto.setCreatedBy(encounterMasterDto2.getCreatedBy());
					patientSponsorDetailsDto.setCreatedDate(GlobalCommonDateUtils.getStringDateFromLongDate(encounterMasterDto2.getCreatedDate(),"yyyy-M-dd HH:mm:ss"));
					patientSponsorDetailsDto.setUpdatedBy(encounterMasterDto2.getUpdatedBy());
					patientSponsorDetailsDto.setUpdatedDate(GlobalCommonDateUtils.getStringDateFromLongDate(encounterMasterDto2.getUpdatedDate(),"yyyy-M-dd HH:mm:ss"));
					patientSponsorDetailsDto.setStatus('A');
					//Saving Patient Sponsor
					Response patientSponsorDetailsResponse = iPatientSponsorDetailsService.savePatientSponsorDetails(patientSponsorDetailsDto);
					if(encounterResponse.getStatus().equals(SUCCESS) && encounterMasterDto2.getReferralTypeId()!=null)
					{
						ReferalDetailsDto referalDetailsDto = new ReferalDetailsDto();
						referalDetailsDto.setOrganizationId(encounterMasterDto2.getOrganizationId());
						referalDetailsDto.setUnitId(encounterMasterDto2.getUnitId());
						referalDetailsDto.setVisitTypeId(encounterMasterDto2.getVisitTypeId());
						referalDetailsDto.setEncounterId(encounterMasterDto2.getEncounterId());
						referalDetailsDto.setReferralId(encounterMasterDto2.getReferralId());
						referalDetailsDto.setReferralTypeId(encounterMasterDto2.getReferralTypeId());
						referalDetailsDto.setCreatedBy(encounterMasterDto2.getCreatedBy());
						referalDetailsDto.setCreatedDate(GlobalCommonDateUtils.getStringDateFromLongDate(encounterMasterDto2.getCreatedDate(),"yyyy-M-dd HH:mm:ss"));
						referalDetailsDto.setUpdatedBy(encounterMasterDto2.getUpdatedBy());
						referalDetailsDto.setUpdatedDate(GlobalCommonDateUtils.getStringDateFromLongDate(encounterMasterDto2.getUpdatedDate(),"yyyy-M-dd HH:mm:ss"));
						referalDetailsDto.setStatus('A');
						//Saving Referal Details
						Response response = iReferalDetailsService.saveReferalDetails(referalDetailsDto);
						
					}
					
					//save schedule logs
					ScheduleLogsDto scheduleLogsDto=new ScheduleLogsDto();
				 	scheduleLogsDto.setAction(ScheduleActions.INSERT);
				 	scheduleLogsDto.setAddedBy(1);
				 	scheduleLogsDto.setAddedDate(GlobalCommonDateUtils.getStringDate(new Date(), "dd-M-yyyy hh:mm:ss"));
				 	scheduleLogsDto.setApplication(ScheduleActions.HIS);
				 	scheduleLogsDto.setDateTime(GlobalCommonDateUtils.getStringDate(new Date(), "dd-M-yyyy hh:mm:ss"));
				 	scheduleLogsDto.setErrorCount(0);
				 	scheduleLogsDto.setPriority(ScheduleActions.PRIORITY_NORM);
				 	scheduleLogsDto.setRecordId(encounterMasterDto2.getEncounterId());
				 	scheduleLogsDto.setStatus(ScheduleActions.STATUS_PENDING);
				 	scheduleLogsDto.setTableName("EncounterMaster");						 	
				 	iScheduleLogsDao.saveScheduleLogs(scheduleLogsDto);
			}
			
			return encounterResponse;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	@Transactional
	public Response getEncounterDetailsById(Integer encounterId) throws ApplicationException {
		try {
			return iEncounterMasterDao.getEncounterDetailsById(encounterId);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	
	@Transactional
	private String generateEncounterNumber() throws ApplicationException {
		try {
			Long incrementedId = iEncounterMasterDao.getNextValEncounterMasterId();
			String encouterPrefix="EC-18-";
			
			StringBuilder encounter=new StringBuilder(encouterPrefix);
			
				int length = Long.valueOf(incrementedId).toString().length();
				encounter = length == 1 ? (encounter.append("000000").append(incrementedId))
						: length == 2 ? (encounter.append("00000").append(incrementedId))
								: length == 3 ? (encounter.append("0000").append(incrementedId))
										: length == 4 ? (encounter.append("000").append(incrementedId))
												: length == 5 ? (encounter.append("00").append(incrementedId))
														: length == 6 ? (encounter.append("0").append(incrementedId))
																: length == 7 ? (encounter.append(incrementedId)) : encounter.append("");
				return encounter.toString();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		return "error";
	}
	
	@Transactional
	public Integer assignVisitTypeIdAccordingToConditions(SlotMasterDto encounterMasterDto) throws ApplicationException
	{
		try {
			Integer visitTypeId = 0;
			boolean breakList = false;
			String encounterDate = null;
			List<EncounterMasterDto> encounterMasterDtosNewList = new ArrayList<>();
			List<Integer> listForVisigtTypeCount = new ArrayList<>();
			Set<String> listOfHolidayAndLinkedSet = new LinkedHashSet<>();
			Integer daysCount = 0;
			Integer dateConflictCount = 0;
			String currentDate = encounterMasterDto.getCurrentDate();
			List<String> totalDates =null;
			ListIterator<String> datesItr = null;
			DateTime extendedDate =null;
			DateTime lastDate =null;
			

			//<-----(1) Fetching last encounter details of patient with same doctor till last primary visit
			EncounterMasterDto enMasterDto = new EncounterMasterDto();
				enMasterDto.setOrganizationId(encounterMasterDto.getOrganizationId());
				enMasterDto.setUnitId(encounterMasterDto.getUnitId());
				enMasterDto.setDoctorId(encounterMasterDto.getDoctorId());
				enMasterDto.setPatientId(encounterMasterDto.getPatientId());
				
			//--Fetching records in descending order--//
			List<EncounterMasterDto> encounterMasterDtosList = iEncounterMasterDao
					.getEncounterDetailsByPatientDoctorVisitTypeId(enMasterDto);

			//<--- (1.1) checking whether  the list is empty or not if not that means patient has some entries in encounter master with same doctor
			if (!encounterMasterDtosList.isEmpty() || encounterMasterDtosList.size() > 0) 
			{
				ListIterator<EncounterMasterDto> itr = encounterMasterDtosList.listIterator();
				while (itr.hasNext() && breakList == false) {
					EncounterMasterDto masterDto = itr.next();
					
					/*//--created a new list(encounterMasterDtosNewList) which will contain records till his last primary visit 
					as we are fetching the list in descending order--//*/
					encounterMasterDtosNewList.add(masterDto);
					
					//--created a list(listForVisigtTypeCount) which will store only the visit types of all records in the encounterMasterDtosNewList--//
					listForVisigtTypeCount.add(masterDto.getUserDefinedVisitTypeId());
					
					if (masterDto.getUserDefinedVisitTypeId() == 1) {
						
						//--getting encounter date of last primary visit--//
						encounterDate = masterDto.getEncounterDateString();
						
						//-- then breaking the loop as we do not require rest of the data--//
						breakList = true;
					}

				}
			//---->(1.1)
			} else {
				/*//<---(1.2) if the list is empty, that means that patient does not have
				 any appointments in the past with that specific doctor, then it has to be its primary visit--->*/
				return visitTypeId = 1;
			}

			//<-----(2) Getting total followUp Visits in followUpCount and secondary visit in secondaryCount 
			Integer followUpCount = Collections.frequency(listForVisigtTypeCount, 2);
			Integer secondaryCount = Collections.frequency(listForVisigtTypeCount, 3);
			//----->(2)
			
			//<-----Retrieving list of holidays
			UnitHolidayCalenderMapperDto unitHolidayCalenderMapperDto = new UnitHolidayCalenderMapperDto();
			unitHolidayCalenderMapperDto.setOrganizationId(encounterMasterDto.getOrganizationId());
			unitHolidayCalenderMapperDto.setUnitId(encounterMasterDto.getUnitId());
			Response holidayListRes = iUnitHolidayCalenderDao
					.getActiveUnitHolidayCalenerList(unitHolidayCalenderMapperDto);
			List<HolidayCalenderMasterDto> holidayList = holidayListRes.getListObject();
			// ----->

			// <-----Retrieving list of weekends
			WeekendMasterDto weekendMasterDto = new WeekendMasterDto();
			weekendMasterDto.setOrganizationId(encounterMasterDto.getOrganizationId());
			weekendMasterDto.setUnitId(encounterMasterDto.getUnitId());
			Response weekendRes = iWeekendMasterService.getWeekendMasterList(weekendMasterDto);
			List<WeekendMasterDto> weekendsList = weekendRes.getListObject();
			// ----->

			//<-----Merging holidays and weekends list to avoid duplication. (Used linked hash set)
			ListIterator<HolidayCalenderMasterDto> holidayItr = holidayList.listIterator();
			while (holidayItr.hasNext()) {
				HolidayCalenderMasterDto holidayCalenderMasterDto = holidayItr.next();
				listOfHolidayAndLinkedSet.add(holidayCalenderMasterDto.getHolidayDate().substring(0,10));
			}
			
			ListIterator<WeekendMasterDto> weekendItr = weekendsList.listIterator();
			while (weekendItr.hasNext()) {
				WeekendMasterDto weekendMasterDto2 = weekendItr.next();
				listOfHolidayAndLinkedSet.add(weekendMasterDto2.getWeekendDate().substring(0,10));
			}
			
				//<-- Converting set to list
				List<String> ListOfHolidayAndLinkedList = new ArrayList<>(listOfHolidayAndLinkedSet);
				//-->
			//----->
			
			
			
			
			
			//<-----(3)Calling OP Consultation Configuration of speciality.
			OPConsultationConfigurationMasterDto opConsultationConfigurationMasterDto = new OPConsultationConfigurationMasterDto();
			opConsultationConfigurationMasterDto.setOrganizationId(encounterMasterDto.getOrganizationId());
			opConsultationConfigurationMasterDto.setUnitId(encounterMasterDto.getUnitId());
			opConsultationConfigurationMasterDto.setSpecialityId(encounterMasterDto.getSpecialityId());
			Response res = iOPConsultationConfigurationMasterService
					.getOPConsultationConfigurationBySpecialityId(opConsultationConfigurationMasterDto);
			
			if (res.getStatus().equals(SUCCESS)) {
				OPConsultationConfigurationMasterDto opConsultationConfigurationMasterRes = (OPConsultationConfigurationMasterDto) res
						.getObject();

				//<-----(3.1) First of all check whether current date is between first visit to last date of secondary 
				Integer lastDateFromEncounterDateCount = opConsultationConfigurationMasterRes.getFollowupVisitDays()
														+ opConsultationConfigurationMasterRes.getSecondaryVisitDays();

				DateTime enounterDateOfLastPrimaryVisit = new DateTime(encounterDate);
				DateTime lastDateFromEncounterDate = enounterDateOfLastPrimaryVisit.plusDays(lastDateFromEncounterDateCount);
				
				totalDates = slotsCreationService
						.getListOfDatesAsPerDayBetweenTwoDates(enounterDateOfLastPrimaryVisit, lastDateFromEncounterDate);

				datesItr = totalDates.listIterator();
				while (datesItr.hasNext()) {
					
					if (slotsCreationService.isBetweenHolidaysAndWeekends(ListOfHolidayAndLinkedList,
							datesItr.next()) == true)
						//---counting the date which are within holiday and weekend then---//
						dateConflictCount++; 
												
				}

				//--- adding the dateConflictCount in daysCount to get actual extension of dates.
				dateConflictCount = lastDateFromEncounterDateCount + dateConflictCount;
				extendedDate = enounterDateOfLastPrimaryVisit.plusDays(dateConflictCount);
				dateConflictCount=0;
				//<---(3.1.1) Checking whether the current date lies between encounterDate and extendedDate
				if (slotsCreationService.isDateBetweenTwoSpecifiedDates(encounterDate,
						extendedDate.toString().substring(0, 10), currentDate) == false) {
					//<--(3.1.1.1) If not then visit type has to be 1
					return visitTypeId = 1;
					//-->
				} else {

					//<--(3.1.1.2) If current date lies between encounterDate and extendedDate that means he is eligible for either follow up or secondary visit  
					ListIterator<EncounterMasterDto> encounterMasterDtosNewListItr = encounterMasterDtosNewList
							.listIterator();
					
					//--fetching only the last record of encounterMasterDtosNewList in lastEncounterOfPatientsObj --//
					EncounterMasterDto lastEncounterOfPatientsObj = null;
					while(encounterMasterDtosNewListItr.hasNext()){
						lastEncounterOfPatientsObj = encounterMasterDtosNewListItr.next();
						break;
					}
					//<--(3.1.1.2.1) if visit type of last encounter is primary then 
					if (lastEncounterOfPatientsObj != null &&  lastEncounterOfPatientsObj.getUserDefinedVisitTypeId() == 1) {

						//<--(3.1.1.2.1.1) check is that patient is eligible for follow up or not 
						daysCount = opConsultationConfigurationMasterRes.getFollowupVisitDays();
						
						lastDate = enounterDateOfLastPrimaryVisit
								.plusDays(daysCount);

						totalDates = slotsCreationService
								.getListOfDatesAsPerDayBetweenTwoDates(enounterDateOfLastPrimaryVisit, lastDate);

						datesItr = totalDates.listIterator();
						while (datesItr.hasNext()) {
							
							if (slotsCreationService.isBetweenHolidaysAndWeekends(ListOfHolidayAndLinkedList,
									datesItr.next()) == true)
								dateConflictCount++; 
						}

						dateConflictCount = daysCount + dateConflictCount;
						extendedDate = enounterDateOfLastPrimaryVisit.plusDays(dateConflictCount);
						dateConflictCount=0;
						if (slotsCreationService.isDateBetweenTwoSpecifiedDates(encounterDate,
								extendedDate.toString().substring(0,10), currentDate) == true) {
							//<--(3.1.1.2.1.1.1) if he is eligible for follow up then visit type will set to 2  
							return visitTypeId = 2;
							//-->
						} else {
							//<--(3.1.1.2.1.1.2)else visit type will set to 3 
							return visitTypeId = 3;
							//-->
						}
						//-->(3.1.1.2.1.1)
					}
					//-->(3.1.1.2.1)

					//<--(3.1.1.2.2)if visit type of last encounter is follow up then 
					if (lastEncounterOfPatientsObj.getUserDefinedVisitTypeId() == 2) {
						
						//<--(3.1.1.2.2.1) first check the follow up count of patient and follow up count specified in OPConsultationMaster 
						if (followUpCount >= opConsultationConfigurationMasterRes.getFollowupVisitCount())
						{
							//<--(3.1.1.2.2.1.1) If followUpCount is less than or equal to getFollowupVisitCount then patient is eligible for secondary
							daysCount = opConsultationConfigurationMasterRes.getSecondaryVisitDays();
							
							lastDate = enounterDateOfLastPrimaryVisit
									.plusDays(daysCount);

							totalDates = slotsCreationService
									.getListOfDatesAsPerDayBetweenTwoDates(enounterDateOfLastPrimaryVisit, lastDate);

							datesItr = totalDates.listIterator();
							while (datesItr.hasNext()) {
								
								if (slotsCreationService.isBetweenHolidaysAndWeekends(ListOfHolidayAndLinkedList,
										datesItr.next()) == true)
									dateConflictCount++; 
							}

							dateConflictCount = daysCount + dateConflictCount;
							extendedDate = enounterDateOfLastPrimaryVisit.plusDays(dateConflictCount);
							dateConflictCount=0;
							//<--(3.1.1.2.2.1.1.1) If it exceeds then patient is eligible for secondary
							if (slotsCreationService.isDateBetweenTwoSpecifiedDates(encounterDate,
									extendedDate.toString().substring(0, 10), currentDate) == true) {
								//<--(3.1.1.2.2.1.1.1) If yes then visit type will set to 3
								return visitTypeId = 3;
								//-->(3.1.1.2.2.1.1.1)
							}else{
								//<--(3.1.1.2.2.1.1.2) else visit type will set to 1
								return visitTypeId = 1;
								//-->(3.1.1.2.2.1.1.2)
							}
							
							//-->(3.1.1.2.2.1.1.1)	
						//-->(3.1.1.2.2.1.1)
							
						}
						//-->(3.1.1.2.2.1)
						
						
						//<--(3.1.1.2.2.2) else check either that patient is eligible for another follow up  
						else {
							
							daysCount = opConsultationConfigurationMasterRes.getFollowupVisitDays();
							
							lastDate = enounterDateOfLastPrimaryVisit.plusDays(daysCount);

							totalDates = slotsCreationService
									.getListOfDatesAsPerDayBetweenTwoDates(enounterDateOfLastPrimaryVisit, lastDate);

							datesItr = totalDates.listIterator();
							while (datesItr.hasNext()) {
								if (slotsCreationService.isBetweenHolidaysAndWeekends(ListOfHolidayAndLinkedList,
										datesItr.next()) == true)
									dateConflictCount++; 
							}

							dateConflictCount = daysCount + dateConflictCount;
							extendedDate = enounterDateOfLastPrimaryVisit.plusDays(dateConflictCount);
							dateConflictCount=0;	

							if (slotsCreationService.isDateBetweenTwoSpecifiedDates(encounterDate,
									extendedDate.toString().substring(0,10), currentDate) == true) {
								//<--(3.1.1.2.2.2.1) if eligible for next follow up then visit type will set to 2
								return visitTypeId = 2;
								//-->(3.1.1.2.2.2.1)
							} else {
								//<--(3.1.1.2.2.2.2)
								return visitTypeId = 3;
								//<--(3.1.1.2.2.2.2)
							}
						}
						//-->(3.1.1.2.2.2)

					}
					//-->(3.1.1.2.2)
					
					
					//<--(3.1.1.2.1)if visit type of last encounter is secondary then
					if (lastEncounterOfPatientsObj.getUserDefinedVisitTypeId() == 3) {
						
						//<--(3.1.1.2.1.1) if secondaryCount is less than or equal to getSecondaryVisitCount of OP Consultation Configuration
						if (secondaryCount >= opConsultationConfigurationMasterRes.getSecondaryVisitCount())
							//<--(3.1.1.2.1.1.1) Set visit type to 1
							return visitTypeId = 1;
							//-->(3.1.1.2.1.1.1)
						//-->(3.1.1.2.1.1)
						
						//<--(3.1.1.2.1.2)else check whether that patient is eligible for another secondary visit
						else {
								
								daysCount = opConsultationConfigurationMasterRes.getSecondaryVisitDays();
								
								lastDate = enounterDateOfLastPrimaryVisit
										.plusDays(daysCount);

								totalDates = slotsCreationService
										.getListOfDatesAsPerDayBetweenTwoDates(enounterDateOfLastPrimaryVisit, lastDate);

								datesItr = totalDates.listIterator();
								while (datesItr.hasNext()) {
									if (slotsCreationService.isBetweenHolidaysAndWeekends(ListOfHolidayAndLinkedList,
											datesItr.next()) == true)

										dateConflictCount++; 
								}

								dateConflictCount = daysCount + dateConflictCount;
								extendedDate = enounterDateOfLastPrimaryVisit.plusDays(dateConflictCount);
								dateConflictCount=0;	

								if (slotsCreationService.isDateBetweenTwoSpecifiedDates(encounterDate,
										extendedDate.toString().substring(0,10), currentDate) == true) {
									//<--(3.1.1.2.1.2.1)
									return visitTypeId = 3;
									//-->(3.1.1.2.1.2.1)
								} else {
									//<--(3.1.1.2.1.2.2)
									return visitTypeId = 1;
									//-->(3.1.1.2.1.2.2)
								}

							}//-->(3.1.1.2.1.2)
					}
					//-->(3.1.1.2.1)
				}
		

			} else {
				System.out.println("OPConsultationConfigurationForSpecialityIdDoesNotExist");
			}
			
			// ----->(3)
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	
	private Integer saveOrderDetails(EncounterMasterDto encounterMasterDto,Integer serviceId)
	{
		try{
			
			
			OrderMasterDto orderMasterDto = new OrderMasterDto();
				orderMasterDto.setVisitTypeId(1);// op==1
				orderMasterDto.setEncounterId(encounterMasterDto.getEncounterId());
				orderMasterDto.setPatientId(encounterMasterDto.getPatientId());
				orderMasterDto.setCreatedBy(encounterMasterDto.getCreatedBy());
				orderMasterDto.setUpdatedBy(encounterMasterDto.getUpdatedBy());
				orderMasterDto.setCreatedDate(encounterMasterDto.getCreatedDate());
				orderMasterDto.setUpdatedDate(encounterMasterDto.getUpdatedDate());
				orderMasterDto.setOrderDate(encounterMasterDto.getCreatedDate());
				orderMasterDto.setOrderStatus('A');
				orderMasterDto.setOrgId(encounterMasterDto.getOrganizationId());
				orderMasterDto.setOrgUnitId(encounterMasterDto.getUnitId());
				orderMasterDto.setOrderStatusId(1);// order status==1
				
			//-------order details-------//
			OrderDetailsMasterDto orderDetailsMasterDto = new OrderDetailsMasterDto();
				orderDetailsMasterDto.setServiceId(serviceId);
				orderDetailsMasterDto.setServiceIsBilled(0);
				orderDetailsMasterDto.setOrderQty(1);
				orderDetailsMasterDto.setCreatedBy(encounterMasterDto.getCreatedBy());
				orderDetailsMasterDto.setUpdatedBy(encounterMasterDto.getUpdatedBy());
				orderDetailsMasterDto.setOrderDate(encounterMasterDto.getCreatedDate());
				orderDetailsMasterDto.setOrderSchDate(encounterMasterDto.getCreatedDate());
				orderDetailsMasterDto.setOrdRenderDatetime(encounterMasterDto.getCreatedDate());
				orderDetailsMasterDto.setCreatedDate(encounterMasterDto.getCreatedDate());
				orderDetailsMasterDto.setUpdatedDate(encounterMasterDto.getUpdatedDate());
				orderDetailsMasterDto.setStatus('A');
				orderDetailsMasterDto.setOrgId(encounterMasterDto.getOrganizationId());
				orderDetailsMasterDto.setOrgUnitId(encounterMasterDto.getUnitId());
				orderDetailsMasterDto.setOrdDocId(encounterMasterDto.getDoctorId());
				orderDetailsMasterDto.setOrdDocSplId(encounterMasterDto.getSpecialityId());
				orderDetailsMasterDto.setServiceRenderingDeptId(1000);
				
					UnitServiceTariffMasterDto unitServiceTariffMasterDto =new UnitServiceTariffMasterDto();
						unitServiceTariffMasterDto.setOrganizationId(encounterMasterDto.getOrganizationId());
						unitServiceTariffMasterDto.setUnitId(encounterMasterDto.getUnitId());
						unitServiceTariffMasterDto.setVisitTypeId(1);
						unitServiceTariffMasterDto.setBillingBedCategoryId(1);	//By default its OPD ---> Id==1
						unitServiceTariffMasterDto.setPatientTypeId(1); //need to add after patient registration 
						unitServiceTariffMasterDto.setPaymentEntitlementId(encounterMasterDto.getPaymentEntitlementTypeId());
						unitServiceTariffMasterDto.setServiceId(serviceId);
						unitServiceTariffMasterDto.setOrderDate(encounterMasterDto.getCurrentDate());
						unitServiceTariffMasterDto.setTariffId(encounterMasterDto.getDefaultSelfTariffId());
					unitServiceTariffMasterDto = iEncounterMasterDao.getBasePriceByServiceTariffMaster(unitServiceTariffMasterDto);
				
				orderDetailsMasterDto.setServiceAmt(new BigDecimal(unitServiceTariffMasterDto.getRate()));
				orderDetailsMasterDto.setOrdConcession(new BigDecimal(0.00));
				orderDetailsMasterDto.setOrdDiscount(new BigDecimal(0.00));
				orderDetailsMasterDto.setTariffId(encounterMasterDto.getDefaultSelfTariffId());
				orderDetailsMasterDto.setBillingClassId(1);//By default its OPD ---> Id==1
				orderDetailsMasterDto.setOrderEmergencyFlag('N');
				orderDetailsMasterDto.setServiceRendered(1);
				orderDetailsMasterDto.setOrdRenderDatetime(encounterMasterDto.getCreatedDate());
				orderDetailsMasterDto.setServiceChargeable(1);
				orderDetailsMasterDto.setServiceIsBilled(0);
				orderDetailsMasterDto.setIsDrug('N');
				
				Tax tax = iEncounterMasterDao.getTaxPercentageByServiceId(serviceId);
				if(tax.getTaxPercentage()!= null)
				{
					orderDetailsMasterDto.setTaxId(tax.getId());
					orderDetailsMasterDto.setTaxPer(new BigDecimal(tax.getTaxPercentage()));
				}else
				{
					orderDetailsMasterDto.setTaxPer(new BigDecimal(0.00));
				}
				BigDecimal serviceAmt = new BigDecimal(unitServiceTariffMasterDto.getRate());
				BigDecimal orderTotalAmt = serviceAmt.multiply(new BigDecimal(1));
				BigDecimal taxAmt = orderTotalAmt.multiply((new BigDecimal(tax.getTaxPercentage()).multiply(new BigDecimal(0.01))));
				BigDecimal netAmt = orderTotalAmt.add(taxAmt);
				
				orderDetailsMasterDto.setOrdTotalAmt(orderTotalAmt);
				orderDetailsMasterDto.setTaxAmount(taxAmt);
				orderDetailsMasterDto.setNetAmount(netAmt);
				orderDetailsMasterDto.setSelfPayable(netAmt);
				orderDetailsMasterDto.setPayeeId(1);
				orderDetailsMasterDto.setCreditPayable(new BigDecimal(0.00));
				
			List orderDetailsList = new ArrayList();
			orderDetailsList.add(orderDetailsMasterDto);
			
			orderMasterDto.setListOrderDetailsMasterDto(orderDetailsList);
			iOrderMasterService.saveOrderMaster(orderMasterDto);
			return 1;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return 0;
		}
	
	
	
	public Response updateAutorenderServices(EncounterMasterDto encounterMasterDto){
		
		try {
			//--------get Auto-Rendered Services---------//
			
			OrderDetailsMasterDto orderDetailsMasterDto = new OrderDetailsMasterDto();
			
			List<ServiceMasterDto> listServiceMasterDto=iEncounterMasterDao.getAutoRenderedServicesByPatient(encounterMasterDto).getListObject();
			
			if(listServiceMasterDto.size()>0)
			{
				ListIterator<ServiceMasterDto> iterator = listServiceMasterDto.listIterator();
				
				if(encounterMasterDto.getPaymentEntitlementTypeId()==1)
				{
					while(iterator.hasNext())
					{
						ServiceMasterDto masterDto = iterator.next();
						
						UnitServiceTariffMasterDto unitServiceTariffMasterDto =new UnitServiceTariffMasterDto();
							unitServiceTariffMasterDto.setOrganizationId(encounterMasterDto.getOrganizationId());
							unitServiceTariffMasterDto.setUnitId(encounterMasterDto.getUnitId());
							unitServiceTariffMasterDto.setVisitTypeId(1);
							unitServiceTariffMasterDto.setBillingBedCategoryId(1);	//By default its OPD ---> Id==1
							unitServiceTariffMasterDto.setPatientTypeId(1); //need to add after patient registration 
							unitServiceTariffMasterDto.setPaymentEntitlementId(encounterMasterDto.getPaymentEntitlementTypeId());
							unitServiceTariffMasterDto.setServiceId(masterDto.getServiceMasterId());
							unitServiceTariffMasterDto.setOrderDate(masterDto.getOrderDateString());
							unitServiceTariffMasterDto.setTariffId(encounterMasterDto.getDefaultSelfTariffId());
							unitServiceTariffMasterDto = iEncounterMasterDao.getBasePriceByServiceTariffMaster(unitServiceTariffMasterDto);
							
							if(unitServiceTariffMasterDto!=null) {
								orderDetailsMasterDto.setOrderId(masterDto.getOrderId());
								orderDetailsMasterDto.setEncounterId(encounterMasterDto.getEncounterId());
								orderDetailsMasterDto.setOrderDetailsId(masterDto.getOrderDetailsId());
								orderDetailsMasterDto.setServiceAmt(new BigDecimal(unitServiceTariffMasterDto.getRate()));
								orderDetailsMasterDto.setOrdConcession(new BigDecimal(masterDto.getConcession()));
								orderDetailsMasterDto.setOrdDiscount(new BigDecimal(0.00));
								orderDetailsMasterDto.setTariffId(encounterMasterDto.getDefaultSelfTariffId());
								orderDetailsMasterDto.setBillingClassId(1);//By default its OPD ---> Id==1
								orderDetailsMasterDto.setOrderEmergencyFlag('N');
								orderDetailsMasterDto.setServiceRendered(1);
								orderDetailsMasterDto.setServiceChargeable(1);
								orderDetailsMasterDto.setServiceRenderingDeptId(1000);
								orderDetailsMasterDto.setServiceIsBilled(0);
								orderDetailsMasterDto.setIsDrug('N');
								
								if(masterDto.getTaxPercentage()!= null)
								{
									orderDetailsMasterDto.setTaxId(masterDto.getTaxId());
									orderDetailsMasterDto.setTaxPer(new BigDecimal(masterDto.getTaxPercentage()));
								}else
								{
									orderDetailsMasterDto.setTaxId(null);
									orderDetailsMasterDto.setTaxPer(new BigDecimal(0.00));
								}
								
								
								BigDecimal serviceAmt = new BigDecimal(unitServiceTariffMasterDto.getRate());
								BigDecimal orderTotalAmt = serviceAmt.multiply(new BigDecimal(1));
								
								
								//-----------------------------------------------------
								
								
								
								BigDecimal taxAmt = orderTotalAmt.multiply((new BigDecimal(masterDto.getTaxPercentage()).multiply(new BigDecimal(0.01))));
								BigDecimal netAmt = orderTotalAmt.add(taxAmt);
								
								orderDetailsMasterDto.setOrdTotalAmt(orderTotalAmt);
								orderDetailsMasterDto.setTaxAmount(taxAmt);
								orderDetailsMasterDto.setNetAmount(netAmt);
								orderDetailsMasterDto.setSelfPayable(netAmt);
								orderDetailsMasterDto.setPayeeId(1);
								orderDetailsMasterDto.setCreditPayable(new BigDecimal(0.00));
								
								iEncounterMasterDao.updateAutorenderServices(orderDetailsMasterDto);
								
							}
							else
							{
								return new Response<>(ERROR, COMMON_ERROR_CODE, "Cannot get service price as order date !> effective date !!", null, null);
							}
						
					}
				}
			}
			
			return new Response<>(SUCCESS, SUCCESS_CODE, null, listServiceMasterDto, null);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, COMMON_ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}
	

	@Override
	@Transactional
	public Response getEncounterDetails(EncounterMasterDto encounterMasterDto) {
		try {
			return iEncounterMasterDao.getEncounterDetails(encounterMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	@Transactional
	public Response getListOfEncounterMasterDao(SlotMasterDto slotMasterDto) throws ApplicationException {
		try {
			return iEncounterMasterDao.getListOfEncounterMasterDao(slotMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	@Transactional
	public Response getSearchedListEncounterMaster(SlotMasterDto slotMasterDto)throws ApplicationException {
		try{
			return iEncounterMasterDao.getSearchedListEncounterMaster(slotMasterDto);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	@Transactional
	public Response getListOfAppointmentStatus(AppointmentStatusMasterDto appointmentStatusMasterDto)throws ApplicationException {
		try {
			return iEncounterMasterDao.getListOfAppointmentStatus(appointmentStatusMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
	}

	@Override
	@Transactional
	public Response getListOfEncountersByPatientId(EncounterMasterDto encounterMasterDto) throws ApplicationException {
		try {
			return iEncounterMasterDao.getListOfEncountersByPatientId(encounterMasterDto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Response<>(ERROR, ERROR_CODE, COMMON_ERROR_MESSAGE, null, null);
		
	}

}

