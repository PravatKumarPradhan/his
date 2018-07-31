/******************************************
 * OPD API URL Configuration
 ******************************************/
var API="/api";
var BILLING = "/billing";
var GLOBAL ="/global";
var SPECIALITY = "/speciality";
var SUB_SPECIALITY= "/subSpeciality";
var ORG = "/org";
var UNIT = "/unit";
var SEARCH = "/search";
var COUNT = "/count";
var STATUS="/status";
var OPD ="/opd";
var PATIENT ="/patient";

var VISIT_TYPE_MASTER = API + GLOBAL + "/visitTypeMaster"; 
var SAVE_APPOINTMENT_MASTER=API + OPD + "/appointment";
var ADDPATIENTREGISTRATION = API + GLOBAL +"/patientRegistration";
var GETPATIENTLIST = API + GLOBAL +"/getPatientDetails";
var UPDATEPATIENTREGISTRATION = API + GLOBAL +"/updatePatient";
var ADDDOCTORSLOTS = API + OPD +"/generateSlots";
var GETALLSLOTS =  API + OPD + "/getSlots";
var GETALLSLOTSBLOCKUNBLOCK =  API + OPD + "/getSlots/blockUnblock";
var GETALLSLOTSCOUNT =  API + OPD + "/getSlotCount";
var GETSLOTDETAILS  =  API + OPD + "/getSlotDetails";
var GETMODALITYBYMODALITYTYPEID = API+ OPD +"/getModalityByModalityTypeId";
var UPDATESLOTSTATUS = API+OPD+"/updateSlotStatus";
var ADDMODALITYTYPEMASTER =  API + OPD + "/saveModalityTypeMaster";
var GETMODALITYTYPEMASTERLIST =  API + OPD + "/getModalityTypeMasterList";
var UPDATEMODALITYTYPEMASTER =  API + OPD + "/updateModalityTypeMaster";
var UPDATESTATUSFORMODALITYTYPE =  API + OPD + "/updateStatusForModalityType";
var GETACTIVEMODALITYTYPELIST =  API + OPD + "/getActiveModalityTypeList";
var GETMODALITYTYPECOUNT =  API + OPD + "/getModalityTypeCount";
var GETMODALITYTYPEBYID = API + OPD + "/getModalityTypeById";
var ADDMODALITYMASTER =  API + OPD + "/saveModalityMaster";
var GETMODALITYMASTERLIST =  API + OPD + "/getModalityMasterList";
var UPDATEMODALITYMASTER =  API + OPD + "/updateModalityMaster";
var UPDATESTATUSFORMODALITY =  API + OPD + "/updateStatusForModality";
var GETACTIVEMODALITYLIST =  API + OPD + "/getActiveModalityList";
var GETMODALITYCOUNT =  API + OPD + "/getModalityCount";
var GETMODALITYBYID = API + OPD + "/getModalityById";
var GETACTIVEMODALITYLIST = API + OPD +"/getActiveModalityList";
var GETYEARLYSLOTS = API + OPD +"/getYearlySlots";
var GETDAILYSLOTS = API + OPD +"/getDailySlots";
var SCHEDULEAPPOINTMENT = API + OPD +"/scheduleAppointment";
var GETAPPOINTMENTSCHEDULELIST = API + OPD +"/scheduleAppointmentList";
var CANCELSCHEDULEAPPOINTMENT = API + OPD +"/cancelScheduledAppointment";
var RESCHEDULEAPPOINTMENT =  API + OPD +"/rescheduleAppointment";
var SEARCHAPPOINTMENTSBYCRITERIA =  API + OPD +"/searchAppointmentsByCriteria"; 
var PATIENTAUTOFILLSEARCH = API + OPD +"/patientAutofillSearch";
var GETMODALITYBYSUBSPECIALITYARRAY = API + OPD +"/getModalityBySubSpecialityArray";
var GETMODALITYBYSUBSPECIALITYID = API + OPD +"/getModalityBySubSpecialityId";

var GETMONTHLYSLOTS = API + OPD +"/getMonthlySlots";
var GETWEEKLYSLOTS = API + OPD+"/getWeeklySlots";
var GETCOMPLAINTSLIST = API + OPD+"/getComplaintAppointment";
var SAVECOMPLAINTSLIST = API + OPD + "/saveComplaintAppointment";

var GETPATIENTPERSONALHISTORYDETAILS = API +OPD+"/getPatientPersonalHistoryDetails";
var SAVEPATIENTPERSONALHISTORYDETAILS = API + OPD+ "/savePatientPersonalHistoryDetails";
var GETPATIENTFAMILYHISTORY = API + OPD + "/getPatientFamilyHistory";
var SAVEPATIENTFAMILYHISTORY = API + OPD + "/savePatientFamilyHistory";
var GETSURGERYMASTERLISTAUTOFILLSERACH = API+GLOBAL+"/getSurgeryMasterListAutoFillSerach";
var GETPATIENTSURGICALHISTORY = API + OPD + "/getPatientSurgicalHistory";
var SAVEPATIENTSURGICALHISTORY = API + OPD +"/savePatientSurgicalHistory";
var GETPATIENTMEDICALHISTORYDETAILS = API +OPD+"/getPatientMedicalHistoryDetails";
var SAVEPATIENTMEDICALHISTORYDETAILS = API + OPD +"/savePatientMedicalHistoryDetails";
var GETDOCTORMASTERLISTAUTOFILLSERACH = API + GLOBAL+"/getDoctorMasterListAutoFillSerach";
var GETDRUGMASTERLIST = API + GLOBAL + "/getDrugMasterList";
var GETIMMUNIZATIONHISTORYLIST = API +OPD+"/getImmunizationHistoryMapper";
var SAVEIMMUNIZATIONHISTORYMAPPER = API+OPD+"/saveImmunizationHistoryMapper";
var GETPATIENTHABITDETAILS = API+OPD+"/getPatientHabitDetails";
var SAVEPATIENTHABITDETAILS = API+OPD+"/savePatientHabitDetails";
var GETALLERGYTYPEMASTER = API + GLOBAL + "/getAllergyTypeMaster";
var GETALLERGYMASTERBYALLERYTYPEID = API+GLOBAL+"/getAllergyMaster";
var GETSEVERITYMASTER = API + GLOBAL +"/getSeverityMaster";
var GETALLERGYPATIENTMAPPER = API + OPD+"/getAllergyPatientMapper";
var SAVEALLERGYPATIENTMAPPER = API + OPD + "/saveAllergyPatientMapper";
var CANCELALLERGYPATIENTMAPPER = API+OPD+"/cancelAllergyPatientMapper";
var GETVITALMASTERLIST = API + GLOBAL + "/getVitalMasterList";
var SAVEAPPOINTMENTVITALMAPPER = API + OPD + "/saveAppointmentVitalMapper";
var GETAPPOINTMENTVITALMAPPERBYPATIENTIDANDAPPOINTMENTID = API + OPD +"/getAppointmentVitalMapperByPatientIdAndAppointmentId";
var GETAPPOINTMENTVITALMAPPERBYTRAND = API + OPD + "/getAppointmentVitalMapperByTrand";
var GETSYSTEMMASTER = API+GLOBAL+"/getSystemMaster";

var GETGENERALEXAMINATIONAPPOMAPPER = API + OPD + "/getGeneralExaminationAppoMapper";
var SAVESYSTEMOBSERVATIONPROPERTY =  API + GLOBAL + "/saveSystemObservationProperty";
var GETLISTOFSYSTEMOBSERVATIONPROPERTY = API + GLOBAL + "/getListOfSystemObservationProperty";
var UPDATESYSTEMOBSERVATIONPROPERTYSTATUS = API + GLOBAL + "/updateSystemObservationPropertyStatus";
var UPDATESYSTEMOBSERVATIONSINGLEPROPERTYSTATUS = API + GLOBAL + "/updateSystemObservationSinglePropertyStatus";


var GETLISTOFSYSTEMMASTER =  API+GLOBAL+"/getListOfSystemMaster";
var SAVESYSTEMMASTER =  API+GLOBAL+"/saveSystemMaster";
var UPDATESYSTEMMASTERSTATUS =  API+GLOBAL+"/updateSystemMasterStatus";
var GETLISTOFSYSTEMMASTERBYID =  API+GLOBAL+"/getListOfSystemMasterById";
var UPDATESYSTEMMASTER =  API+GLOBAL+"/updateSystemMaster";
var GETLISTOFSYSTEMMASTERBYTYPE = API + GLOBAL + "/getListOfSystemMasterByType";
var SAVEPATIENTDIAGNOSIS = API + OPD + "/savePatientDiagnosis";
var GETPATIENTDIAGNOSISBYPATIENTID = API + OPD + "/getPatientDiagnosisByPatientId";
var GETPATIENTTRAVELHISTORYDETAILS = API + OPD + "/getPatientTravelHistoryDetails";
var SAVEPATIENTTRAVELHISTORYDETAILS = API + OPD + "/savePatientTravelHistoryDetails";
var GETSURGERYGRADEMASTERLIST = API + GLOBAL+"/getSurgeryGradeMasterList";
var SAVESURGERYGRADEMASTER = API + GLOBAL+"/saveSurgeryGradeMaster";
var UPDATESTATUSFORSURGERYGRADE = API + GLOBAL+"/updateStatusForSurgeryGrade";
var GETSURGERYGRADEBYID = API + GLOBAL+"/getSurgeryGradeById";
var UPDATESURGERYGRADEMASTER = API + GLOBAL+"/updateSurgeryGradeMaster";

var GETSURGERYMASTERLIST = API + GLOBAL+"/getSurgeryMasterList";
var SAVESURGERYMASTER = API + GLOBAL+"/saveSurgeryMaster";
var GETSURGERYMASTERBYID = API + GLOBAL+"/getSurgeryMasterById";
var UPDATESURGERYMASTER = API + GLOBAL+"/updateSurgeryMaster";
var UPDATESTATUSFORSURGERYMASTER = API + GLOBAL+"/updateStatusForSurgeryMaster";



var GETOPCONSULTATIONCONFIGURATIONLIST = API + OPD + UNIT + "/getOPConsultationConfigurationList";
var SAVEOPCONSULTATIONCONFIGURATION = API + OPD + UNIT + "/OPConsultationConfiguration";
var GETOPCONSULTATIONCONFIGURATIONLISTBYID = API + OPD + UNIT + "/getOPConsultationConfigurationListById";
var UPDATEOPCONSULTATIONCONFIGURATIONMASTER = API + OPD + UNIT + "/updateOPConsultationConfigurationMaster";

var DIAGNOSISAUTOFILLSEARCH = API + OPD + "/diagnosisAutoFillSearch";

var GETSYSTEMGENERATEDVISITTYPEID = API + OPD +"/getSystemGeneratedVisitTypeId"; 

var GETENCOUNTERDETAILS = API + OPD + "/getEncounterDetails";

var SAVEGENERALEXAMINATIONAPPOMAPPER = API + OPD + "/saveGeneralExaminationAppoMapper";

var GETLISTOFENCOUNTERMASTER = API + OPD + "/getListOfEncounterMaster";

var GETSEARCHEDLISTENCOUNTERMASTER = API + OPD + "/getSearchedListEncounterMaster";
var GETCLINICMASTERLISTBYDOCTORID = API + GLOBAL + "/getClinicMasterListByDoctorId";
var GETLISTOFNURSINGSTATIONBYCLINICID = API + GLOBAL + "/getListOfNursingStationByClinicId";
var GETLISTOFNURSINGSTATION = API + GLOBAL + "/getListOfNursingStation";
var GETLISTOFAPPOINTMENTSTATUSMASTER = API + OPD + "/getListOfAppointmentStatusMaster";
var UPDATECOMPLAINTAPPOINTMENT = API + OPD + "/updateComplaintAppointment";

var GETOLDPATIENTPERSONALHISTORYDETAILS = API + OPD + "/getOldPatientPersonalHistoryDetails";
var GETCURRENTPATIENTPERSONALHISTORYDETAILS = API + OPD + "/getCurrentPatientPersonalHistoryDetails";
var UPDATESTATUSENTERINERROR = API + OPD + "/updateStatusEnterInError";
var UPDATEPATIENTPERSONALHISTORYDETAILS = API + OPD + "/updatePatientPersonalHistoryDetails";
var GETOLDPATIENTFAMILYHISTORY = API + OPD + "/getOldPatientFamilyHistory";
var GETCURRENTPATIENTFAMILYHISTORY = API + OPD + "/getCurrentPatientFamilyHistory";
var UPDATEPATIENTFAMILYHISTORY  = API + OPD + "/updatePatientFamilyHistory";
var UPDATEFAMILYSTATUSENTERINERROR = API + OPD + "/updateFamilyStatusEnterInError";
var GETOLDPATIENTSURGICALHISTORY = API + OPD + "/getOldPatientSurgicalHistory";
var GETCURRENTPATIENTSURGICALHISTORY = API + OPD + "/getCurrentPatientSurgicalHistory";
var UPDATEPATIENTSURGICALHISTORY = API + OPD + "/updatePatientSurgicalHistory";
var UPDATEENTERERRORSTATUSPATIENTSURGICALHISTORY = API + OPD + "/updateEnterErrorStatusPatientSurgicalHistory";
var GETOLDPATIENTMEDICALHISTORYDETAILS = API + OPD + "/getOldPatientMedicalHistoryDetails";
var GETCURRENTPATIENTMEDICALHISTORYDETAILS = API + OPD + "/getCurrentPatientMedicalHistoryDetails";
var UPDATEPATIENTMEDICALHISTORYDETAILS = API + OPD + "/updatePatientMedicalHistoryDetails";
var UPDATEENTERERRORSTATUSPATIENTMEDICALHISTORYDETAILS = API + OPD + "/updateEnterErrorStatusPatientMedicalHistoryDetails";
var GETOLDPATIENTTRAVELHISTORYDETAILS = API + OPD + "/getOldPatientTravelHistoryDetails";
var GETCURRENTPATIENTTRAVELHISTORYDETAILS = API + OPD + "/getCurrentPatientTravelHistoryDetails";
var UPDATEPATIENTTRAVELHISTORYDETAILS = API + OPD + "/updatePatientTravelHistoryDetails";
var UPDATEENTERERRORSTATUSPATIENTTRAVELHISTORYDETAILS =  API + OPD + "/updateEnterErrorStatusPatientTravelHistoryDetails";
var GETOLDIMMUNIZATIONHISTORYMAPPER = API + OPD + "/getOldImmunizationHistoryMapper";
var GETCURRENTIMMUNIZATIONHISTORYMAPPER = API + OPD + "/getCurrentImmunizationHistoryMapper";
var UPDATEIMMUNIZATIONHISTORYMAPPER = API + OPD + "/updateImmunizationHistoryMapper";
var GETOLDPATIENTHABITDETAILS = API + OPD + "/getOldPatientHabitDetails";
var GETCURRENTPATIENTHABITDETAILS = API + OPD + "/getCurrentPatientHabitDetails";
var UPDATEENTERERRORSTATUSPATIENTHABITDETAILS = API + OPD + "/updateEnterErrorStatusPatientHabitDetails";
var GETOLDALLERGYPATIENTMAPPER = API + OPD + "/getOldAllergyPatientMapper";
var GETCURRENTALLERGYPATIENTMAPPER = API + OPD + "/getCurrentAllergyPatientMapper";
var UPDATEALLERGYPATIENTMAPPER = API + OPD + "/updateAllergyPatientMapper";
var UPDATEENTERERRORSTATUSALLERGYPATIENTMAPPER = API + OPD + "/updateEnterErrorStatusAllergyPatientMapper";
var ENCOUNTERDETAILS = API + OPD + "/encounterDetails";
var GETCURRENTAPPOINTMENTVITALMAPPER = API + OPD + "/getCurrentAppointmentVitalMapper";

var GETOLDENCOUNTERLIST = API + OPD + "/getOldEncounterList";
var GETGENERALEXAMLIST = API+OPD+"/getGeneralExaminationAppoMapper"

var BLOCKEDSCHEDULEAPPOINTMENTLIST = API + OPD + "/blockedScheduleAppointmentList";
var CONVERTPATIENT = API + GLOBAL + "/convertPatient";
var BLOCKUNBLOCKSLOT = API + OPD + "/blockUnblockslot";
var GETBLOCKEDUNBLOCKEDSLOTDETAILS = API + OPD + "/getBlockedUnblockedSlotDetails";
var GETSPECIALITYLISTBYDOCTORID = "/adt/getSpecialityListByDoctorId";
var ISOVERBOOKINGALLOWEDORNOT = API + OPD +"/isOverBookingAllowedOrNot";




