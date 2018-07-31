package com.param.global.service;

import java.net.URI;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.google.gson.Gson;
import com.param.global.common.ICommonConstants;
import com.param.global.common.Response;
import com.param.global.common.ScheduleActions;
import com.param.global.common.ScheduleTables;
import com.param.global.dao.IDoctorMasterDao;
import com.param.global.dao.IGlobalUnitMasterDao;
import com.param.global.dao.IModalityMasterDao;
import com.param.global.dao.IPatientRegistrationDao;
import com.param.global.dao.IScheduleLogsDao;
import com.param.global.dto.EncounterSyncDto;
import com.param.global.dto.ScheduleLogsDto;
import com.param.global.dto.sync.DoctorMasterDtoSync;
import com.param.global.dto.sync.ModalityMasterDtoSync;
import com.param.global.dto.sync.OrganisationMasterDtoSync;
import com.param.global.dto.sync.PatientMasterDto;
import com.param.global.dto.sync.UnitMasterDtoSync;
import com.param.global.opd.dao.IEncounterMasterDao;
import com.param.global.org.dao.IGlobalOrganisationMasterDao;

@Component
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ScheduledLogsScheduler {

	@Autowired
	IScheduleLogsDao iScheduleLogsDao;
	@Autowired
	IPatientRegistrationDao iPatientMasterDao;
	@Autowired
	IModalityMasterDao iModalityMasterDao;
	@Autowired
	IGlobalUnitMasterDao iUnitMasterDao; 
	@Autowired
	IGlobalOrganisationMasterDao iGlobalOrganisationMasterDao;
	@Autowired
	IEncounterMasterDao iEncounterMasterDao; 
	@Autowired
	IDoctorMasterDao iDoctorMasterDao;	
	@Autowired
	RestTemplate restTemplate;
	@Autowired
	private Gson gson;


	@Transactional
	//@Scheduled(fixedDelay = 120000, initialDelay = 60000)
	public void syncSchedulingLogs() {

		try {
			Response pendingSchedule = iScheduleLogsDao.getPendingScheduleLogs();
			URI url = null;
			Response response = null;
			Response commonResponse = null;
			if (pendingSchedule.getListObject() != null && pendingSchedule.getListObject().size() > 0) {

				List<ScheduleLogsDto> listScheduleLogsDto = pendingSchedule.getListObject();

				for (ScheduleLogsDto logsDto : listScheduleLogsDto) {

					String table = logsDto.getTableName();
					String action = logsDto.getAction();
					switch (table) {
					case ScheduleTables.PATIENT_MASTER:
						response = iPatientMasterDao.getPatientByIdForSync(logsDto.getRecordId());
						List<PatientMasterDto> listDoctorSchedulingDto = response.getListObject();
						switch (action) {
						case ScheduleActions.INSERT:
							for (PatientMasterDto docSchedulingDto : listDoctorSchedulingDto) {
								url = URI.create(ScheduleActions.MYLIFE_GLOBAL_URI + "/sync/patients/realtime");
								String stringResponse = restTemplate.postForObject(url, docSchedulingDto, String.class);
								commonResponse = gson.fromJson(stringResponse, Response.class);
								if (commonResponse.getStatus().equalsIgnoreCase(ICommonConstants.SUCCESS))
									iScheduleLogsDao.markScheduleLogsCompleted(logsDto.getScheduleLogsId());
								else
									iScheduleLogsDao.markScheduleLogsError(logsDto.getScheduleLogsId());
							}
							break;
						}
						break;
					case ScheduleTables.MODALITY_MASTER:
						response = iModalityMasterDao.getModalityByIdForSync(logsDto.getRecordId());
						List<ModalityMasterDtoSync> modalityMasterDtoList = response.getListObject();
						switch (action) {
						case ScheduleActions.INSERT:
							for (ModalityMasterDtoSync modalityDto : modalityMasterDtoList) {
								url = URI.create(ScheduleActions.BASE_URL + "/modality");
								String stringResponse = restTemplate.postForObject(url, modalityDto, String.class);
								commonResponse = gson.fromJson(stringResponse, Response.class);
								if (commonResponse.getStatus().equalsIgnoreCase(ICommonConstants.SUCCESS))
									iScheduleLogsDao.markScheduleLogsCompleted(logsDto.getScheduleLogsId());
								else
									iScheduleLogsDao.markScheduleLogsError(logsDto.getScheduleLogsId());
							}
							break;
						}
						break;		
					case ScheduleTables.UNIT_MASTER:
						response = iUnitMasterDao.getUnitMasterForSyncById(logsDto.getRecordId());
						List<UnitMasterDtoSync> unitMasterDtoList = response.getListObject();
						switch (action) {
						case ScheduleActions.INSERT:
							for (UnitMasterDtoSync unitDto : unitMasterDtoList) {
								//mylife
								url = URI.create(ScheduleActions.MYLIFE_GLOBAL_URI + "/globalunit");
								String stringResponse = restTemplate.postForObject(url, unitDto, String.class);
								commonResponse = gson.fromJson(stringResponse, Response.class);
								//for global scheduling
								url = URI.create(ScheduleActions.BASE_URL + "/unit");
								String stringResponse1 = restTemplate.postForObject(url, unitDto, String.class);
								commonResponse = gson.fromJson(stringResponse1, Response.class);
								if (commonResponse.getStatus().equalsIgnoreCase(ICommonConstants.SUCCESS))
									iScheduleLogsDao.markScheduleLogsCompleted(logsDto.getScheduleLogsId());
								else
									iScheduleLogsDao.markScheduleLogsError(logsDto.getScheduleLogsId());
							}
							break;
						}
						break;	
					case ScheduleTables.HOSPITAL_MASTER:
						response = iGlobalOrganisationMasterDao.getGlobalOrganisationMasterForSyncById(logsDto.getRecordId());
						List<OrganisationMasterDtoSync> orgMasterDtoList = response.getListObject();
						switch (action) {
						case ScheduleActions.INSERT:
							for (OrganisationMasterDtoSync orgDto : orgMasterDtoList) {
								//for mylife
								url = URI.create(ScheduleActions.MYLIFE_GLOBAL_URI + "/globalHospital");
								String stringResponse = restTemplate.postForObject(url, orgDto, String.class);
								commonResponse = gson.fromJson(stringResponse, Response.class);
								//for global scheduling
								url = URI.create(ScheduleActions.BASE_URL + "/organisation");
								String stringResponse1 = restTemplate.postForObject(url, orgDto, String.class);
								commonResponse = gson.fromJson(stringResponse1, Response.class);
								if (commonResponse.getStatus().equalsIgnoreCase(ICommonConstants.SUCCESS))
									iScheduleLogsDao.markScheduleLogsCompleted(logsDto.getScheduleLogsId());
								else
									iScheduleLogsDao.markScheduleLogsError(logsDto.getScheduleLogsId());
							}
							break;
						}
						break;	
					case ScheduleTables.DOCTOR_MASTER:
						response = iDoctorMasterDao.getDoctorForSyncById(logsDto.getRecordId());
						List<DoctorMasterDtoSync> docterDtoList = response.getListObject();
						switch (action) {
						case ScheduleActions.INSERT:
							for (DoctorMasterDtoSync docterDto : docterDtoList) {
								url = URI.create(ScheduleActions.BASE_URL + "/doctor");
								String stringResponse = restTemplate.postForObject(url, docterDto, String.class);
								commonResponse = gson.fromJson(stringResponse, Response.class);
								if (commonResponse.getStatus().equalsIgnoreCase(ICommonConstants.SUCCESS))
									iScheduleLogsDao.markScheduleLogsCompleted(logsDto.getScheduleLogsId());
								else
									iScheduleLogsDao.markScheduleLogsError(logsDto.getScheduleLogsId());
							}
							break;
						}
						break;
						
					case ScheduleTables.ENCOUNTER_MASTER:
						response = iEncounterMasterDao.getEncounterByIdForSync(logsDto.getRecordId());
						
							switch(action){
							case ScheduleActions.INSERT:
								EncounterSyncDto encounterSyncDto = null;
								if(response.getObject() != null){
									 encounterSyncDto = (EncounterSyncDto) response.getObject();
								}
								url = URI.create(ScheduleActions.BASE_URL + "/appointment/bookAppointment");
								String stringResponse = restTemplate.postForObject(url, encounterSyncDto, String.class);
								commonResponse = gson.fromJson(stringResponse, Response.class);
								if(commonResponse.getStatus().equalsIgnoreCase(ICommonConstants.SUCCESS)){
									iScheduleLogsDao.markScheduleLogsCompleted(logsDto.getScheduleLogsId());
								}else{
									iScheduleLogsDao.markScheduleLogsError(logsDto.getScheduleLogsId());
								}
								break;
							}
						
						break;
					}

				}

			}

		} catch (Exception e) {
			e.printStackTrace(); 
		}

	}
}
