

/* Start ADT URL Variables */
/* Start ADT URL Variables */

var ORGANIZATIONLIST = "/adt/organizationMasterList";
var UNITLIST = "/adt/getUnitByOrganization";
var LOGIN = "/adt/validateUserName";

var ADDSPECIALITY = "/adt/saveSpecialtiyMaster";
var GETSPECIALITYLIST = "/adt/getSpecialityList";
var GETSINGLESPECIALITY = "/adt/getSpecialityById";
var UPDATESPECIALITY = "/adt/updateSpecialityMaster";
var UPDATESPECIALITYSTATUS = "/adt/updateStatus";
var GETSPECIALITY = "/adt/getSpecialityList";
var GETSPECIALITYACTIVE = "/adt/getSpecialityListForSubSpeciality";
var GETACTIVESPECIALITY = "/adt/getActiveSpecialityList";
var GETORGACTIVESPECIALITYLIST = "/adt/getOrgActiveSpecialityList";
var SUBSPECIALITY = "/adt/subspeciality";

var ADDSUBSPECIALITY = "/adt/saveSubSpecialtiyMaster";
var GETSUBSPECIALITYLIST = "/adt/getSubSpecialityList";
var GETSINGLESUBSPECIALITY = "/adt/getSubSpecialityById";
var UPDATESUBSPECIALITY = "/adt/updateSubSpecialityMaster";
var UPDATESUBSPECIALITYSTATUS = "/adt/updateStatusForSubSpeciality";
var GETSUBSPECIALITYBYSPECIALITYID = "/adt/getSubSpecialityBySpecialityId";
var GETSUBSPECIALITYBYSPECIALITYARRAY = "/adt/getSubSpecialityBySpecialityArray";
var GETSUBSPECIALITYBYSPECIALITYARRAYFORTARIFF="/adt/getSubSpecialityBySpecialityArrayForTariff";
var GETALLACTIVESUBSPECIALITYLIST = "/adt/getActiveUnitSubSpecialityList";

var ADDICUTYPE = "/adt/saveICUTypeMaster";
var GETICUTYPELIST = "/adt/getICUTypeList";
var GETSINGLEICUTYPE = "/adt/getICUById";
var UPDATEICUTYPE = "/adt/updateICUtypeMaster";
var UPDATEICUTYPESTATUS = "/adt/updateStatusForICU";
var GETUNITICUTYPELIST = "/adt/getUnitICUTypeList";
var GETACTIVEUNITICUTYPELIST = "/adt/getActiveUnitICUTypeList";
var GETDOCTORFORICU = "/adt/getDoctorForICU";


var ADDNATIONALITY = "/adt/saveNationalityMaster";
var GETNATIONALITYLIST = "/adt/getNationalityMasterList";
var GETSINGLENATIONALITY = "/adt/getNationalityById";
var UPDATENATIONALITY = "/adt/updateNationalityMaster";
var UPDATENATIONALITYSTATUS = "/adt/updateStatusForNationality";
var GETACTIVENATIONALITYLIST = "/adt/getActiveNationalityList";

var ADDRACE = "/adt/saveRaceMaster";
var GETRACELIST = "/adt/getRaceMasterList";
var GETSINGLERACE = "/adt/getRaceById";
var UPDATERACE = "/adt/updateRaceMaster";
var UPDATERACESTATUS = "/adt/updateStatusForRace";

var ADDCOUNTRY = "/adt/saveCountryMaster";
var GETCOUNTRYLIST = "/adt/getCountryMasterList";
var GETSINGLECOUNTRY = "/adt/getCountryById";
var UPDATECOUNTRY = "/adt/updateCountryMaster";
var UPDATECOUNTRYSTATUS = "/adt/updateStatusForCountry";
var GETACTIVECOUNTRYLIST = "/adt/getActiveCountryList";

var ADDSTATE = "/adt/saveStateMaster";
var GETSTATELIST = "/adt/getStateMasterList";
var GETSINGLESTATE = "/adt/getStateById";
var UPDATESTATE = "/adt/updateStateMaster";
var UPDATESTATESTATUS = "/adt/updateStatusForState";
var GETACTIVESTATELIST = "/adt/getActiveStateList";
var GETSTATELISTBYCOUNTRYID = "/adt/getActiveStateListByCountryId";

var ADDDISTRICT = "/adt/saveDistrictMaster";
var GETDISTRICTLIST = "/adt/getDistrictMasterList";
var GETSINGLEDISTRICT = "/adt/getDistrictById";
var UPDATEDISTRICT = "/adt/updateDistrictMaster";
var UPDATEDISTRICTSTATUS = "/adt/updateStatusForDistrict";
var GETDISTRICTLISTBYSTATEID = "/adt/getActiveDistrictListByStateId";

var ADDCITY = "/adt/saveCityMaster";
var GETCITYLIST = "/adt/getCityMasterList";
var GETSINGLECITY = "/adt/getCityById";
var UPDATECITY = "/adt/updateCityMaster";
var UPDATECITYSTATUS = "/adt/updateStatusForCity";
var GETCITYLISTBYDISTRICTID = "/adt/getActiveCityListByDistrictId";

var ADDAREA = "/adt/saveAreaMaster";
var GETAREALIST = "/adt/getAreaMasterList";
var GETSINGLEAREA = "/adt/getAreaById";
var UPDATEAREA = "/adt/updateAreaMaster";
var UPDATEAREASTATUS = "/adt/updateStatusForArea";
var GETAREALISTBYCITY = "/adt/getAreaByCityId";

var ADDIDENTIFICATION = "/adt/saveIdentificationMaster";
var GETIDENTIFICATIONLIST = "/adt/getIdentificationMasterList";
var GETSINGLEIDENTIFICATION = "/adt/getIdentificationById";
var UPDATEIDENTIFICATION = "/adt/updateIdentificationMaster";
var UPDATEIDENTIFICATIONSTATUS = "/adt/updateStatusForIdentification";

var ADDSEX = "/adt/saveGenderMaster";
var GETSEXLIST = "/adt/getGenderMasterList";
var GETSINGLESEX = "/adt/getGenderById";
var UPDATESEX = "/adt/updateGenderMaster";
var UPDATESEXSTATUS = "/adt/updateStatusForGender";
var GETACTIVEGENDERLIST = "/adt/getActiveGenderList";

var ADDRELATION = "/adt/saveRelationMaster";
var GETRELATIONLIST = "/adt/getRelationMasterList";
var GETSINGLERELATION = "/adt/getRelationById";
var UPDATERELATION = "/adt/updateRelationMaster";
var UPDATERELATIONSTATUS = "/adt/updateStatusForRelation";

var ADDMARITALSTATUS = "/adt/saveMaritalStatusMaster";
var GETMARITALSTATUSLIST = "/adt/getMaritalStatusMasterList";
var GETSINGLEMARITALSTATUS = "/adt/getMaritalById";
var UPDATEMARITAL = "/adt/updateMaritalStatusMaster";
var UPDATEMARITALSTATUS = "/adt/updateStatusForMaritalStatus";
var GETACTIVEMARITALSTATUSLIST = "/adt/getActiveMaritalStatusList";

var ADDRELIGION = "/adt/saveReligionMaster";
var GETRELIGIONLIST = "/adt/getReligionMasterList";
var GETSINGLERELIGION = "/adt/getReligionById";
var UPDATERELIGION = "/adt/updateReligionMaster";
var UPDATERELIGIONSTATUS = "/adt/updateStatusForReligion";
var GETACTIVERELIGIONLIST = "/adt/getActiveReligionList";

var ADDOCCUPATION = "/adt/saveOccupationMaster";
var GETOCCUPATIONLIST = "/adt/getOccupationMasterList";
var GETSINGLEOCCUPATION = "/adt/getOccupationMasterById";
var UPDATEOCCUPATION = "/adt/updateOccupationMaster";
var UPDATEOCCUPATIONSTATUS = "/adt/updateStatusForOccupation";
var GETACTIVEOCCUPATIONLIST = "/adt/getActiveOccupationList";

var ADDPATIENTSOURCE = "/adt/savePatientSourceMaster";
var GETPATIENTSOURCELIST = "/adt/getPatientSourceMasterList";
var GETSINGLEPATIENTSOURCE = "/adt/getPatientSourceById";
var UPDATEPATIENTSOURCE = "/adt/updatePatientSourceMaster";
var UPDATEPATIENTSOURCESTATUS = "/adt/updateStatusForPatientSource";
var GETACTIVEPATIENTSOURCELIST = "/adt/getActivePatientSourceList";

var ADDREFERRALTYPE = "/adt/saveReferralTypeMaster";
var GETREFERRALTYPELIST = "/adt/getReferralTypeMasterList";
var GETSINGLEREFERRALTYPE = "/adt/getReferralTypeById";
var UPDATEREFERRALTYPE = "/adt/updateReferralTypeMaster";
var UPDATEREFERRALTYPESTATUS = "/adt/updateStatusForReferralType";
var GETACTIVEREFERRALTYPELIST = "/adt/getActiveReferralTypeList";

var ADDREFERRAL = "/adt/saveReferralMaster";
var GETREFERRALLIST = "/adt/getReferralMasterList";
var GETSINGLEREFERRAL = "/adt/getReferralById";
var UPDATEREFERRAL = "/adt/updateReferralMaster";
var UPDATEREFERRALSTATUS = "/adt/updateStatusForReferral";
var GETACTIVEREFERRALLIST = "/adt/getActiveReferralList";

var ADDDELIVERYTYPE = "/adt/saveDeliveryTypeMaster";
var GETDELIVERYTYPELIST = "/adt/getDeliveryTypeMaster";
var GETSINGLEDELIVERYTYPE = "/adt/getDeliveryTypeById";
var UPDATEDELIVERYTYPE = "/adt/updateDeliveryTypeMaster";
var UPDATEDELIVERYTYPESTATUS = "/adt/updateStatusForDeliveryType";
var GETACTIVEDELIVERYTYPELIST = "/adt/getActiveDeliveryTypeList";

var ADDENCOUNTERTYPE = "/adt/saveEncounterTypeMaster";
var GETENCOUNTERTYPELIST = "/adt/getEncounterTypeMasterList";
var GETSINGLEENCOUNTERTYPE = "/adt/getEncounterTypeById";
var UPDATEENCOUNTERTYPE = "/adt/updateEncounterTypeMaster";
var UPDATEENCOUNTERTYPESTATUS = "/adt/updateStatusForEncounterType";
var GETACTIVEENCOUNTERTYPELIST = "/adt/getActiveEncounterTypeList";

var ADDPREFIX = "/adt/savePrefixMaster";
var GETPREFIXLIST = "/adt/getPrefixMasterList";
var GETSINGLEPREFIX = "/adt/getPrefixById";
var UPDATEPREFIX = "/adt/updatePrefixMaster";
var UPDATEPREFIXSTATUS = "/adt/updateStatusForPrefix";
var GETACTIVEPREFIXLIST = "/adt/getActivePrefixList";

var ADDCONSENTTYPE = "/adt/saveConsentTypeMaster";
var GETCONSENTTYPELIST = "/adt/getConsentTypeMasterList";
var GETSINGLECONSENTTYPE = "/adt/getConsentTypeById";
var UPDATECONSENTTYPE = "/adt/updateConsentTypeMaster";
var UPDATECONSENTTYPESTATUS = "/adt/updateStatusForConsentType";
var GETACTIVECONSENTTYPELIST = "/adt/getActiveConsentTypeList";

var ADDCONSENT = "/adt/saveConsentMaster";
var GETCONSENTLIST = "/adt/getConsentMasterList";
var GETSINGLECONSENT = "/adt/getConsentById";
var UPDATECONSENT = "/adt/updateConsentMaster";
var UPDATECONSENTSTATUS = "/adt/updateStatusForConsent";
var GETACTIVECONSENTLIST = "/adt/getActiveConsentList";

var ADDOCCUPANCYUNIT = "/adt/saveOccupancyUnitMaster";
var GETOCCUPANCYUNITLIST = "/adt/getOccupancyUnitMasterList";
var GETSINGLEOCCUPANCYUNIT = "/adt/getOccupancyUnitById";
var UPDATEOCCUPANCYUNIT = "/adt/updateOccupancyUnitMaster";
var UPDATEOCCUPANCYUNITSTATUS = "/adt/updateStatusForOccupancyUnit";
var GETACTIVEOCCUPANCYUNITLIST = "/adt/getActiveOccupancyUnitList";

var ADDDOCUMENTTYPE = "/adt/saveDocumentTypeMaster";
var GETDOCUMENTTYPELIST = "/adt/getDocumentTypeMasterList";
var GETSINGLEDOCUMENTTYPE = "/adt/getDocumentTypeById";
var UPDATEDOCUMENTTYPE = "/adt/updateDocumentTypeMaster";
var UPDATEDOCUMENTTYPESTATUS = "/adt/updateStatusForDocumentType";
var GETACTIVEDOCUMENTTYPELIST = "/adt/getActiveDocumentTypeList";

var ADDHIERARCHY = "/adt/saveHierarchyMaster";
var GETHIERARCHYLIST = "/adt/getHierarchyMasterList";
var GETSINGLEHIERARCHY = "/adt/getHierarchyById";
var UPDATEHIERARCHY = "/adt/updateHierarchyMaster";
var UPDATEHIERACHYSTATUS = "/adt/updateStatusForHierarchy";
var GETACTIVEHIERARCHYLIST = "/adt/getActiveHierarchyList";

var ADDBEDCATEGORY = "/adt/saveBedCategoryMaster";
var GETBEDCATEGORYLIST = "/adt/getBedCategoryMasterList";
var GETSINGLEBEDCATEGORY = "/adt/getBedCategoryById";
var UPDATEBEDCATEGORY = "/adt/updateBedCategoryMaster";
var UPDATEBEDCATEGORYSTATUS = "/adt/updateStatusForBedCategory";
var GETACTIVEBEDCATEGORYLIST = "/adt/getActiveBedCategoryList";

var ADDBEDTYPE = "/adt/saveBedTypeMaster";
var GETBEDTYPELIST = "/adt/getBedTypeMasterList";
var GETSINGLEBEDTYPE = "/adt/getBedTypeById";
var UPDATEBEDTYPE = "/adt/updateBedTypeMaster";
var UPDATEBEDTYPESTATUS = "/adt/updateStatusForBedType";
var GETACTIVEBEDTYPELIST = "/adt/getActiveBedTypeList";

var ADDADMISSIONTYPE = "/adt/saveAdmissionTypeMaster";
var GETADMISSIONTYPELIST = "/adt/getAdmissionTypeMasterList";
var GETSINGLEADMISSIONTYPE = "/adt/getAdmissionTypeById";
var UPDATEADMISSIONTYPE = "/adt/updateAdmissionTypeMaster";
var UPDATEADMISSIONTYPESTATUS = "/adt/updateStatusForAdmissionType";
var GETACTIVEADMISSIONTYPELIST = "/adt/getActiveAdmissionTypeList";

var ADDDISCHARGETYPE = "/adt/saveDischargeTypeMaster";
var GETDISCHARGETYPELIST = "/adt/getDischargeTypeMasterList";
var GETSINGLEDISCHARGETYPE = "/adt/getDischargeTypeById";
var UPDATEDISCHARGETYPE = "/adt/updateDischargeTypeMaster";
var UPDATEDISCHARGETYPESTATUS = "/adt/updateStatusForDischargeType";
var GETACTIVEDISCHARGETYPELIST = "/adt/getActiveDischargeTypeList";

var ADDSHORTLEAVEREASON = "/adt/saveShortLeaveReasonMaster";
var GETSHORTLEAVEREASONLIST = "/adt/getShortLeaveReasonMasterList";
var GETSINGLESHORTLEAVEREASON = "/adt/getShortLeaveReasonById";
var UPDATESHORTLEAVEREASON = "/adt/updateShortLeaveReasonMaster";
var UPDATESHORTLEAVEREASONSTATUS = "/adt/updateStatusForShortLeaveReason";
var GETACTIVESHORTLEAVEREASONLIST = "/adt/getActiveShortLeaveReasonList";

var ADDHOLIDAY = "/adt/saveHolidayCalenderMaster";
var GETHOLIDAYLIST = "/adt/getHolidayCalenderMasterList";
var GETSINGLEHOLIDAY = "/adt/getHolidayCalenderById";
var UPDATEHOLIDAY = "/adt/updateHolidayCalenderMaster";
var UPDATEHOLIDAYSTATUS = "/adt/updateStatusForHolidayCalender";
var GETDAYOBJ = "/adt/getActiveDayListById";
var GETPATIENTBYKEYWORD = "/adt/getPatientListLike";
var GETDOCTORBYSPECIALITYID = "/adt/getDoctorListAganistSpeciality";
var GETACTIVEREASONLIST = "/adt/getReasonMasterList";
var ADDADMISSIONREQUEST = "/adt/saveAdmissionRequest";
var GETPATIENTDETAILSBYPATIENTID = "/adt/getPatientVisitDetailsById";
var GETPENDINGADMISSIONREQUEST = "/adt/getPendingAdmissionRequestDetails";
var GETACTIVEPATIENTCATEGORY = "/adt/getAvtivePatientCategoryList";
var GETACTIVEPAYMENTENTITLEMENT = "/adt/getAvtivePaymentEntitlementList";
var UPDATEPENDINGADMISSION = "/adt/updatePendingAdmissionRequestDetails";
var GETBEDLISTBYID = "/adt/getBedListById";

/*
 * New Added Start Vivek
 */

var GETACTIVEBEDLIST = "/adt/getActiveBedList";
var GETACTIVEFLOORLIST = "/adt/getActiveFloorList";
var GETACTIVEWARDLISTBYFLOORID = "/adt/getActiveWardListByFloorIdByGender";
var GETACTIVENURSINGSTATIONLISTBYWARDID = "/adt/getNursingStationListByWard";
var GETACTIVEBEDCATEGORYLISTBYUNIT = "/adt/getBedCategoryListByOrgUnit";
var GETACTIVEBEDSTATUSLIST = "/adt/getBedStatusList";
var GETACTIVEBILLINGBEDCATEGORYBYBEDCATEGORYID = "/adt/getBillingBedCategoryListByBedCategory";
var GETACTIVEMEALPREFERENCELIST = "/adt/getMealPreference";
var ADDBEDALLOCATION = "/adt/savePatientAdmission";
var GETACTIVERELATIONLIST = "/adt/getActiveRelationList";
var GETACTIVEIDENTIFICATION = "/adt/getActiveIdentificationList";
var UPDATERESERVATIONADMISSION = "/adt/updateReserveBedDetails";
var UPLOADPATIENTDOCUMENTS = "/adt/uploadAdmissionDocuments";
var GETWARDLISTBYFLOORIDORGUNITID = "/adt/getActiveWardListByFloorId";
var GETADMITTEDPATIENTLIST = "/adt/getAdmittedPatientList";
var GETKINDETAILSBYADMISSIONID = "/adt/getKinDetailsByAdmissionId";
var GETBEDRESERVEDLIST = "/adt/getBedReservationList";
var UNRESERVERESHEDULEPATIENT = "/adt/unreservePatientBooking";
var GETACTIVEPRIORITYLIST = "/adt/getActivePriorityList";
var ADDUNKNOWNPATIENT = "/adt/saveUnknownPatient";
var GETADMITTEDPATIENTLISTBYFLOORWARD = "/adt/getAdmittedPatientByFloorWard";

var GETBEDLISTBYMULTIPLECRITERIA = "/adt/getBedListByMultipleCriteria";

var GETVISITORPASSTYPELIST = "/adt/getVisitorTypeList";

var GETPASSISSUEDLIST = "/adt/getVisitorsList";

var GETPASSISSUEDDETAILSBYADMISSIONID = "/adt/getPassIssuedDetailsByAdmissionId";

var ADDVISITORPASSISSUE = "/adt/saveVisitors";

var UPDATEVISITORSDETAILS = "/adt/updateVisitorsDetails";

var GETBEDLISTBYFLOORWARDID = "/adt/getBedListByWardId";

var GETADMITTEDPATIENTLISTBYFLOORWARDBED = "/adt/getAdmittedPatientListByMultipleCriteria";

var COUNTSEXMASTER = "/adt/getGenderCount";

var COUNTRELATIONMASTER = "/adt/getRelationCount";

var COUNTMARITALMASTER = "/adt/getMaritalStatusCount"

var COUNTRELIGIONMASTER = "/adt/getReligionCount";

var COUNTOCCUPATIONMASTER = "/adt/getOccupationCount";

var COUNTPATIENTSOURCEMASTER = "/adt/getPatientSourceCount";

var COUNTREFERRALTYPEMASTER = "/adt/getReferralTypeCount";

var COUNTSPECIALITYMASTER = "/adt/getSpecialityCount";

var COUNTSUBSPECIALITYMASTER = "/adt/getSubSpecialityCount"

var COUNTICUTYPEMASTER = "/adt/getICUCount";

var COUNTHOLIDAYMASTER = "/adt/getHolidayCount";

var COUNTDOCUMENTTYPEMASTER = "/adt/getDocumentTypeCount";

var COUNTNATIONALITYMASTER = "/adt/getNationalityCount";

var COUNTRACEMASTER = "/adt/getRaceCount";

var COUNTCOUNTRYMASTER = "/adt/getCountryCount";

var COUNTSTATEMASTER = "/adt/getStateCount";

var COUNTDISTRICTMASTER = "/adt/getDistrictCount";

var COUNTCITYMASTER = "/adt/getCityCount";

var COUNTAREAMASTER = "/adt/getAreaCount";

var COUNTIDENTIFICATIONMASTER = "/adt/getIdentificationCount";

var COUNTREFERRALMASTER = "/adt/getReferralCount";

var COUNTENCOUNTERTYPEMASTER = "/adt/getEncounterTypeCount";

var COUNTPREFIXMASTER = "/adt/getPrefixCount";

var COUNTCONSENTTYPEMASTER = "/adt/getConsentTypeCount";

var COUNTCONSENTMASTER = "/adt/getConsentCount";

var COUNTBEDCATEGORYMASTER = "/adt/getBedCategoryCount";

var COUNTBEDTYPEMASTER = "/adt/getBedTypeCount";

var COUNTADMISSIONTYPEMASTER = "/adt/getAdmissionTypeCount";

var COUNTOCCUPANCYUNITMASTER = "/adt/getOccupancyUnitCount";

var COUNTDISCHAGRETYPEMASTER = "/adt/getDischargeTypeCount";

var COUNTSHORTLEAVEREASONMASTER = "/adt/getShortLeaveCount";

var COUNTPENDINGADMISSIONLIST = "/adt/getPendingAdmissionCount";

var COUNTBEDRESERVETIONLIST = "/adt/getBedReservationCount"

var GETBEDTYPEALLOCATIONERLIST = "/adt/getERBedTypeAllocationList"; //3-11-2017 changes

var ADDBEDTYPEALLOCATIONER = "/adt/updateERPatientAdmission"; //3-11-2017 changes

var COUNTBEDTYPEALLOCATIONRE = "/adt/getERBedTypeAllocationCount" //3-11-2017 changes

	var GETERADMITTEDPATIENTLIST  = "/adt/getERAdmissionList"; //3-11-2017 changes
var COUNTERADMITTEDPATIENT = "/adt/getERAdmissionCount"; //3-11-2017 changes
var GETERADMITTEDPATIENTLISTBYALLOCATIONPATIENT = "/adt/searchERPatient";

var GETACTIVEERBEDLIST = "/adt/getERBedList"; //3-11-2017 New Added

var GETACTIVEDOCTORLIST = "/adt/getActiveDoctorList";

var GETERMAPOFBEDLIST = "/adt/getDataForMapOfBed";

var GETTRANSFERPATIENTLIST = "/adt/getTransferRequestList";
var ADDTRANSFERREQUEST = "/adt/saveTransferRequest";
var GETTRANSFERREQUESTLIST = "/adt/getPendingTransferRequestList";
var ACCEPTREJECTREQUESTBYDOCTOR = "/adt/acceptRejectTransferRequest";
var GETERPATIENTBYKEYWORD = "/adt/getERPatient";

var GETBEDAVAILABILTYLIST = "/adt/bedAvailiblityList"; 

var ADDDISCHARGEREQUEST  = "/adt/saveDischargeRequest";

var UPDATEDISCHARGEPDD = "/adt/updateDischargePdd";

var GETDISCHARGEADMITTEDPATIENTLIST = "/adt/getDischagePatientList";

var GETDISCHARGEPATIENTLISTFORNURSING = "/adt/getDischargePatientListForNursing";
var GETPATIENTPENDINGSERVICESLIST = "/adt/getPatientServiceOrderList";

var CANCELPAENDINGSERVICEORDER = "/adt/updatePatientServiceOrder";
var GETREADYFORBILLINGPATIENTLIST = "/adt/getPatientReadyForBillingList";
var FINALDISCHARGEFORPATIENT = "/adt/updatePatientReadyForBillingStatus";
var GETFINALDISCHARGEPATIENTLIST = "/adt/getPatientFinalDischargeList";
var PATIENTFINALDISCHARGE = "/adt/vacateBed";
var GETTRANSFERREQUESTACCEPTANCELIST = "/adt/getPendingTransferRequestListForADT";
var ACCEPTTRANSFERREQUESTFORADT = "/adt/acceptTransferRequestForADT";
var REJECTTRANSFERREQUESTFORADT = "/adt/rejectTransferRequestForADT";

var REJECTTRANSFERREQUESTBYDOCTOR = "/adt/rejectTransferRequestForDoctor";
var ACCEPTTRANSFERREQUESTBYDOCTOR ="/adt/acceptTransferRequestForDoctor";
var ACCEPTTRANSFERREQUESTONREQUEST = "/adt/acceptTransferRequest";
var REJECTTRANSFERREQUESTONREQUEST = "/adt/rejectTransferRequest";
var GETFINALTRANSFERREQUESTLIST = "/adt/getFinalTransferRequestList";
var ACCEPTFINALTRANSFERREQUEST = "/adt/acceptFinalTransferRequest";
var REJECTFINALTRANSFERREQUEST = "/adt/rejectFinalTransferRequest";
var GETINITIATETRANSFERREQUEST  = "/adt/getInitiateTransferRequestList";
var GETBEDLISTBYWARDIDFORBEDTOBED = "/adt/getBedListByWardIdForBedtoBedTransfer";

var PATIENTSERACHBYMULTIPLECRITERIAUNKNOWN= "/adt/patientSearchByMultipleCriteria";
var GETDISCHARGEPATIENTLISTFORER = "/adt/getERDischargePatientList";
var GETADMITTEDPATIENTLISTBYMULTIPLECRITERIA = "/adt/serachPatientForMapOfBed";
var GETERADMITTEDPATIENTBYMULTIPLECRITERIA = "/adt/searchPatientForMapOfBed";

var GETINITIATETRANSFERREQUESTFORBM = "/adt/getTransferRequestListByAdmissoinId";
var GETADMITTEDPATIENTBYKEWWORD = "/adt/searchAdmittedPatientByNameAndUhid";
var GETMODALITYLISTAGAINTPATIENT = "/adt/getPatientServiceOrderListByAdmissionId";
var SAVEMODALITYTRANSFERREQUEST ="/adt/saveModalityTransferRequest";
var GETMODALITYTRANSFERLISTAGAINTPATIENT = "/adt/getModalityTransferRequestList";
var GETOTLISTAGAINTPATIENT = "/adt/getOTRequestListByAdmissionId";
var GETOTTRANSFERLISTAGAINTPATIENT = "/adt/getOTTransferRequestList";
var SAVEOTTRANSFERREQUEST="/adt/saveOTTransferRequest";
var SAVEICUREQUEST = "/adt/generateTransferRequestForICUTransfer";
var GETICUTRANSFERPATIENTLIST = "/adt/getICUTransferRequestList";
var GETACTIVEBEDLISTBYICUTYPEID = "/adt/getICUBedsByIUCTypeId";
var GETALLPATIENTSERVICEORDERLIST = "/adt/getAllPatientServiceOrderList";
var SEARCHPATIENTSERVICEORDERSBYMULCRITERIA = "/adt/searchPatientServiceOrdersByMulCriteria";
var GETALLOTREQUESTLIST = "/adt/getOTRequestList";
var GETOTREQUESTLISTBYADMISSIONID = "/adt/getOTTransferRequestListByAdmissionId";
var SAVETRANSFEROFCAREREQUEST = "/adt/saveTransferOfCareRequest";
var GETTRANSFEROFCAREREQUESTLIST = "/adt/getTransferOfCareRequestList";
var GETTRANSFEROFCAREREQUESTTODOCTORLIST = "/adt/getTransferOfCareRequestListForDoctor";
var SAVEACCEPTTRANSFEROFCAREREQUEST = "/adt/acceptTransferOfCareRequest";
var REJECTTRANSFEROFCAREREQUEST = "/adt/rejectTransferOfCareRequest";
var ACCEPTCONSULTORDERREQUEST = "/adt/acceptConsultOrderRequest";
var SEARCHCONSULTORDERREQUESTBYADMISSIONID = "/adt/searchConsultOrderRequestByAdmissionId";
var GETBEDLISTFORHOUSEKEEPING = "/adt/getBedsForHousekeepingList";
var GETUSERSLIST = "/adt/getActiveUserList";
var UPDATEHOUSEKEEPING = "/adt/assignUserForCleaning";
var UPDATEFORMARKEDASCLEAR = "/adt/markAsCleaned";
var GETLISTOFVACANTBEDSFORNURSING = "/adt/getListOfVacantBedsForNursing";
var HOUSEKEEPINGACCEPTREQUESTFORNURSING = "/adt/acceptRequestForNursing";
var HOUSEKEEPINGREJECTREQUESTFORNURSING = "/adt/rejectRequestForNursing";
var SEARCHBEDBYMULTIPLECRITERIAFORHOUSEKEEPING = "/adt/searchBedByMultipleCriteriaForHousekeeping";
var SEARCHBEDBYMULTIPLECRITERIAFORNURSING = "/adt/searchBedByMultipleCriteriaForNursing";
var GETTRANSFERREQUESTLISTBYADMISSIONIDFORB2B = "/adt/getTransferRequestListByAdmissionIdForB2B";
var GETICUTRANSFERREQUESTLISTBYADMISSIONID = "/adt/getICUTransferRequestListByAdmissionId";
var GETFINALTRANSFERREQUESTLISTBYADMISSIONID = "/adt/getFinalTransferRequestListByAdmissionId";
var GETSHORTLEAVEREASONLIST = "/adt/getActiveShortLeaveReasonList";
var ADDSHORTLEAVEREQUEST = "/adt/saveShortLeaveRequest";
var GETSHORTLEAVEREQUESTLIST = "/adt/getShortLeaveRequestList";
var GETSHORTLEAVEREQUESTLISTFORDOCTOR = "/adt/getShortLeaveRequestListForDoctor";
var ACCEPTSHOTLEAVEBYDOCTOR = "/adt/acceptShotLeaveByDoctor";
var REJECTSHOTLEAVEBYDOCTOR = "/adt/rejectShotLeaveByDoctor";
var RELEASEPATIENTFORSHOTLEAVE = "/adt/releasePatientForShotLeave";

var GETMORTUARYBEDLIST = "/adt/getMortuaryBedList";
var GETMORTUARYBEDCOUNT = "/adt/getMortuaryBedCount";
var SAVEMORTUARYBEDS = "/adt/saveMortuaryBeds";
var UPDATEMORTUARYBEDMASTER = "/adt/updateMortuaryBedMaster";
var UPDATESTATUSFORMORTUARYBED = "/adt/updateStatusForMortuaryBed";
var GETACTIVEMORTUARYBEDS = "/adt/getActiveMortuaryBeds";
var GETMOURTUARYBEDBYID = "/adt/getMortuaryBedListById";

var SAVEDEATHREGISTATION = "/adt/saveDeathRegistation";
var SAVEMORTUARYREQUESTFROMMAPOFBED = "/adt/saveMortuaryRequestFromMapOfBed";
var GETPENDINGMORTUARYREQUESTLIST = "/adt/getPendingMortuaryRequestList";
var ACCEPTMORTUARYREQUESTFROMPENDINGLIST = "/adt/acceptRejectMortuaryRequestFromPendingList";
var GETRESERVEMORTUARYREQUESTLIST = "/adt/getReservedMortuaryRequestList";
var GETNEXTOFKINLISTBYPATIENTID  = "/api/global/getKinDeatilsForMortuaryAllocation";
var SAVEKINDEATILSFORMORTUARYALLOCATION ="/api/global/saveKinDeatilsForMortuaryAllocation";

var GETMORTUARTBEDLISTBYSTATUSID = "/adt/getMorturyBedListByStatusId";

var SAVEBEDALLOCATIONMORTUARY = "/adt/saveBedAllocationMortuary";

var GETADMITTEDMORTUARYLIST = "/adt/getAdmittedMortuaryList";

var RELEASEMORTURYBED = "/adt/releaseMorturyBed";

var GETDATAFORMORTURYMAPOFBED = "/adt/getDataForMorturyMapOfBed";

var CANCELMORGUEREQUEST = "/adt/cancelMorgueRequest";

var GETTEMPORYPATIENTFORPREREGISTRATION = "/api/global/patientSearch";

var GETDOCTORSLISTAGANISTSPECIALITIES ="/adt/getDoctorsListAganistSpecialities";

//unit level speciality 
var SAVEUNITSPECIALTIY ="/adt/saveUnitSpecialtiy";
var GETUNITSPECIALITYLIST ="/adt/getUnitSpecialityList";
var GETUNITSPECIALITYCOUNT ="/adt/getUnitSpecialityCount";
var UPDATESTATUSFORUNITSPECIALITYLIST ="/adt/updateStatusForUnitSpecialityList";
var GETUNITSPECIALITYBYID ="/adt/getUnitSpecialityById";
var GETACTIVEUNITSPECIALITYLIST ="/adt/getActiveUnitSpecialityList";
//unit level sub-speciality 
var SAVEUNITSUBSPECIALTIY ="/adt/saveUnitSubSpecialtiy";
var GETUNITSUBSPECIALITYMASTERLIST ="/adt/getUnitSubSpecialityList";
var GETUNITSUBSPECIALITYMASTERCOUNT ="/adt/getUnitSubSpecialityCount";
var UPDATESTATUSFORUNITSUBSPECIALITY ="/adt/updateStatusForUnitSubSpeciality";
var GETSUBSPECIALITYNOTINUNIT ="/adt/getSubspecialityNotInUnit";
//unit-org-floor master
var GETFLOORMASTERLIST ="/adt/getFloorMasterList";
var SAVEFLOORMASTER ="/adt/saveFloorMaster";
var GETFLOORCOUNT ="/adt/getFloorCount";
var UPDATESTATUSFORFLOOR ="/adt/updateStatusForFloor";
var GETFLOORBYID ="/adt/getFloorById";
var UPDATEFLOORMASTER ="/adt/updateFloorMaster";

/* Start ADT URL Variables */