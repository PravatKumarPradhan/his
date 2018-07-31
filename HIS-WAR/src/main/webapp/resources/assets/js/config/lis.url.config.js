
/**===========================>LIS COMMON URL START<================================================**/


var LIS = "/LIS";
var LIS_GLOBAL = "/LIS/global";
var LIS_COMMON = "/LIS/common";
var LIS_UNIT = "/LIS/unit";
var LIS_TRANSACTION = "/LIS/transaction";
var LIS_CENTRAL_RECEIVING = "/LIS/centralReceiving";
var LIS_BIOCHEMISTRY = "/LIS/BioChemistry";
var LIS_MICROBIOLOGY = "/LIS/Microbiology";
var LIS_HISTOPATHOLOGY = "/LIS/histopathology"; 
var LIS_REPORT_HAND_OVER = "/LIS/ReportHandOver";
var LIS_REPORTS = "/LIS/Reports";
var LIS_OUT_SOURCE_LAB = "/LIS/OutSource";

var S ="/";


/**LIS COMMON LIST URL*/
var GET_SAMPLE_STAUS_LIST = "/getSampleStatusList";
var GET_REJECTED_REASON_LIST = "/getRejectionReasonList";
var GET_DEPARMENT_LIST = "/getDeparmentList";
var GET_SAMPLETYPE_LIST = "/getSampleTypeList";
var GET_UNIT_LIST = "/getUnitList";
var GET_CONTAINER_LIST = "/getContainerList";
var GET_REPORTTYPE_LIST = "/getReportTypeList";
var GET_TECHNIQUE_LIST = "/getTechniqueList";
var GET_PREREQUISITES_LIST = "/getPrerequisitesList";
var GET_HEADER_LIST = "/getHeaderList";
var GET_PARAMETER_LIST = "/getParameterList";
var GET_AGE_GROUP_LIST  = "/getAgeGroupList";
var GET_GENDER_LIST  = "/getGenderList";
var GET_DAY_LIST  = "/getDayList";
var GET_TURN_ARR_TIME  = "/getDayList";
var GET_TRUNAROUND_TIME_LIST = "/getTrunAroundTimeList";
var AUTO_COMPLETE_SERVICES = "/autoCompleteServices";
var CHECK_SERVICE_AVAIABLE ="/checkServiceAvaiable";
var GET_ANTIBIOTICS_AUTOCOMPLETE ="/autoCompleteServicesForAntibiotic/orgId";
var GET_MICRO_LAB_PRIORITY_LIST ="/getMicrolabPriorityList/orgId";
var GET_SPECIMAN_TYPE_LIST ="/spmecimantype";
var GET_LAB_TEMPLATES = "/labTemplates";
var GET_LAB_TEMPLATES_EXISTS_OR_NOT = "/getTemplateTypeExistOrNot";
var TEST_PARAMETERS  = "/testParameters";
var GET_REF_RANGE_TYPES = "/refRengeTypes";
var TEXTUAL_REFERENCE_RANGES = "/textualReferenceRanges";
var MICROBIOLOGY_TEST_LIST = "/microbiology/test";


var INCUBATION_TIME_LIST ="/incubationTime/orgId";
var MEDIA_LIST_BY_SAMPLEID ="/media/list/sampleId";
var INCUBATION_PERIOD_LIST ="/incubationPeriod/list/orgId";
var STAINING_LIST_BY_ORG_ID ="/staining/list/orgId";
var MICRO_STAINING_LIST_BY_ORG_ID ="/micribiology/stain";
var MICRO_ORGANISM_GROUP_LIST_BY_ORG_ID ="/microOrganism/list/orgId";
var MICRO_GET_ORGANISM_BY_GROUP ="/microOrgasm/list/organismGroup";
var GET_ANTIBIOTICS_BY_ORGAINSM = "/antibiotics/list/orgId"

/** Sample master**/
var ADD_SAMPLE_MASTER  = "/addSample";
var EDIT_SAMPLE_MASTER = "/getSamplebyId";
var UPDATE_SAMPLE_MASTER = "/updateSample";
var ACTIVE_INACTIVE_SAMPLE_MASTER ="/activeInactiveSample";
var LIST_SAMPLE_MASTER = "/listSampleMaster";
var COUNT_SAMPLE_MASTER  ="/getTotalSampleMasterRecord";

/** Container master**/
var ADD_CONTAINER_MASTER  = "/addContainer";
var EDIT_CONTAINER_MASTER = "/getContainerbyId";
var UPDATE_CONTAINER_MASTER = "/updateContainer";
var ACTIVE_INACTIVE_CONTAINER_MASTER ="/activeInactiveContainer";
var LIST_CONTAINER_MASTER = "/listContainerMaster";
var COUNT_CONTAINER_MASTER  ="/getTotalContainerMasterRecord";

/** Techinque master**/
var ADD_TECHINQUE_MASTER  = "/addTechinque";
var EDIT_TECHINQUE_MASTER = "/getTechinquebyId";
var UPDATE_TECHINQUE_MASTER = "/updateTechinque";
var ACTIVE_INACTIVE_TECHINQUE_MASTER ="/activeInactiveTechinque";
var LIST_TECHINQUE_MASTER = "/listTechinqueMaster";
var COUNT_TECHINQUE_MASTER  ="/getTotalTechinqueMasterRecord";

/** Prerequisites master**/
var ADD_PREREQUISITES_MASTER  = "/addPrerequisites";
var EDIT_PREREQUISITES_MASTER = "/getPrerequisitesbyId";
var UPDATE_PREREQUISITES_MASTER = "/updatePrerequisites";
var ACTIVE_INACTIVE_PREREQUISITES_MASTER ="/activeInactivePrerequisites";
var LIST_PREREQUISITES_MASTER = "/listPrerequisitesMaster";
var COUNT_PREREQUISITES_MASTER  ="/getTotalPrerequisitesMasterRecord";

/** Age Group master**/
var ADD_AGE_GROUP_MASTER  = "/addAgeGroupMaster";
var EDIT_AGE_GROUP_MASTER = "/getAgeGroupById";
var UPDATE_AGE_GROUP_MASTER = "/updateAgeGroup";
var ACTIVE_INACTIVE_AGE_GROUP_MASTER ="/activeInactiveAgeGroup";
var LIST_AGE_GROUP_MASTER = "/listAgeGroupMaster";
var COUNT_AGE_GROUP_MASTER  ="/getToTalAgeGroupMasterRecord";
var GET_AGE_TYPE_GROUP_LIST = "/listOfAgeTypeGroup";


/** Unit master**/
var ADD_UNIT_MASTER  = "/addUnitMaster";
var EDIT_UNIT_MASTER = "/getUnitById";
var UPDATE_UNIT_MASTER = "/updateUnit";
var ACTIVE_INACTIVE_UNIT_MASTER ="/activeInactiveUnit";
var LIST_UNIT_MASTER = "/listUnitMaster";
var COUNT_UNIT_MASTER  ="/getToTalUnitMasterRecord";


/** Reagent master**/
var ADD_REAGENT_MASTER  = "/addReagentMaster";
var EDIT_REAGENT_MASTER = "/getReagentById";
var UPDATE_REAGENT_MASTER = "/updateReagent";
var ACTIVE_INACTIVE_REAGENT_MASTER ="/activeInactiveReagent";
var LIST_REAGENT_MASTER = "/listReagentMaster";
var COUNT_REAGENT_MASTER  ="/getToTalReagentMasterRecord";


/** Report Type master**/
var ADD_REPORT_TYPE_MASTER  = "/addReportTypeMaster";
var EDIT_REPORT_TYPE_MASTER = "/getaddReportTypeById";
var UPDATE_REPORT_TYPE_MASTER = "/updateReportType";
var ACTIVE_INACTIVE_REPORT_TYPE_MASTER ="/activeInactiveReportType";
var LIST_REPORT_TYPE_MASTER = "/listReportType";
var COUNT_REPORT_TYPE_MASTER  ="/getToTalReportTypeMasterRecord";


/** Bacteria Shape master**/
var ADD_BACTERIA_SHAPE_MASTER  = "/addBacteria";
var EDIT_BACTERIA_SHAPE_MASTER = "/getaddBacteriaById";
var UPDATE_BACTERIA_SHAPE_MASTER = "/updateBacteriaMaster";
var ACTIVE_INACTIVE_BACTERIA_SHAPE_MASTER ="/activeInactiveBacteria";
var LIST_BACTERIA_SHAPE_MASTER = "/listBacteria";
var COUNT_BACTERIA_SHAPE_MASTER  ="/getToTallistBacteria";

/** Wet Film Study master**/
var ADD_WT_FILM_STUDY_MASTER  = "/addWtfilmStudy";
var EDIT_WT_FILM_STUDY_MASTER = "/getaddWtfilmStudyById";
var UPDATE_WT_FILM_STUDY_MASTER = "/updateWtifilmStudyMaster";
var ACTIVE_INACTIVE_WT_FILM_STUDY_MASTER ="/activeInactiveWtfilmStudy";
var LIST_WT_FILM_STUDY_MASTER = "/listWtfilmStudy";
var COUNT_WT_FILM_STUDY_MASTER  ="/getToTallistlistWtfilmStudy";

/** Media master**/
var ADD_MEDIA_MASTER  = "/saveMediaMaster";
var EDIT_MEDIA_MASTER = "/getMediabyId";
var UPDATE_MEDIA_MASTER = "/updateMedia";
var ACTIVE_INACTIVE_MEDIA_MASTER ="/activeInactiveMedia";
var LIST_MEDIA_MASTER = "/listMediaMaster";
var COUNT_MEDIA_MASTER  ="/getTotalMediaMasterRecord";


/** Colony master**/
var ADD_COLONY_MASTER  = "/addColony";
var EDIT_COLONY_MASTER = "/getaddColonyById";
var UPDATE_COLONY_MASTER = "/updateColonyMaster";
var ACTIVE_INACTIVE_COLONY_MASTER ="/activeInactiveColony";
var LIST_COLONY_MASTER = "/listColony";
var COUNT_COLONY_MASTER  ="/getToTallistlistColony";


/** Organism master**/
var ADD_ORGANISM_MASTER  = "/addOrganismMaster";
var EDIT_ORGANISM_MASTER = "/getaddOrganismMasterById";
var UPDATE_ORGANISM_MASTER = "/updateOrganismMaster";
var ACTIVE_INACTIVE_ORGANISM_MASTER ="/activeInactiveOrganismMaster";
var LIST_ORGANISM_MASTER = "/listOrganismMaster";
var COUNT_ORGANISM_MASTER  ="/getToTallistlistOrganismMaster";

/** Organism Group master**/
var ADD_ORGANISM_GROUP_MASTER  = "/addOrganisumGroup";
var EDIT_ORGANISM_GROUP_MASTER = "/getaddOrganisumGroupById";
var UPDATE_ORGANISM_GROUP_MASTER = "/updateOrganisumGroupMaster";
var ACTIVE_INACTIVE_ORGANISM_GROUP_MASTER ="/activeInactiveOrganisumGroup";
var LIST_ORGANISM_GROUP_MASTER = "/listOrganisumGroup";
var COUNT_ORGANISM_GROUP_MASTER  ="/getToTallistlistOrganisumGroup";


/** Stainig master**/
var ADD_STAINIG_MASTER  = "/addStainigMaster";
var EDIT_STAINIG_MASTER = "/getaddStainigMasterById";
var UPDATE_STAINIG_MASTER = "/updateStainigMaster";
var ACTIVE_INACTIVE_STAINIG_MASTER ="/activeInactiveStainigMaster";
var LIST_STAINIG_MASTER = "/listStainigMaster";
var COUNT_STAINIG_MASTER  ="/getToTallistStainigMaster";




/** Antibiotic master**/
var SAVE_ANTIBIOTIC  = "/saveAntibiotic";
var EDIT_ANTIBIOTIC = "/getAntibioticById";
var UPDATE_ANTIBIOTIC = "/updateAntibiotic";
var ACTIVE_INACTIVE_ANTIBIOTIC ="/activateInactivateAntibiotic";
var LIST_ANTIBIOTIC_LIST = "/listAntibiotic";
var COUNT_ANTIBIOTIC_RECORD  ="/getToTalAntibiotic";

	
/** Antibiotics class master**/
var ADD_ANTIBIOTIC_MASTER  = "/addAntibiotics";
var EDIT_ANTIBIOTIC_MASTER = "/getaddAntibioticsById";
var UPDATE_ANTIBIOTIC_MASTER = "/updateAntibioticsMaster";
var ACTIVE_INACTIVE_ANTIBIOTIC_MASTER ="/activeInactiveAntibiotics";
var LIST_ANTIBIOTIC_MASTER = "/listAntibiotics";
var COUNT_ANTIBIOTIC_MASTER  ="/getToTallistlistAntibiotics";


/** Speciman master**/
var ADD_SPECIMAN_MASTER  = "/addSpeciman";
var EDIT_SPECIMAN_MASTER = "/getaddspecimanById";
var UPDATE_SPECIMAN_MASTER = "/updateSpecimanMaster";
var ACTIVE_INACTIVE_SPECIMAN_MASTER ="/activeInactiveSpeciman";
var LIST_SPECIMAN_MASTER = "/listSpeciman";
var COUNT_SPECIMAN_MASTER  ="/getToTallistlistSpeciman";
var BLOCK_REQURIED_SPECIMAN_MASTER ="/specimanBlockRequriedInSpecimanMaster";
var GROSS_REQURIED_SPECIMAN_MASTER ="/specimanGrossRequriedInSpecimanMaster";

/** IncubationTest master**/
var ADD_INCUBATION_TEST_MASTER  = "/addIncubationTest";
var EDIT_INCUBATION_TEST_MASTER = "/getIncubationTestById";
var UPDATE_INCUBATION_TEST_MASTER = "/updateIncubationTestMaster";
var ACTIVE_INACTIVE_INCUBATION_TEST_MASTER ="/activeInactiveIncubationTest";
var LIST_INCUBATION_TEST_MASTER = "/listIncubationTest";
var COUNT_INCUBATION_TEST_MASTER  ="/getToTalIncubationTest";


/** Antibiotics Group master**/

var ADD_ANTIBIOTIC_GROUP_MASTER  = "/addAntibioticsGroup";
var EDIT_ANTIBIOTIC_GROUP_MASTER = "/getAntibioticsGroupMasterById";
var UPDATE_ANTIBIOTIC_GROUP_MASTER = "/updateAntibioticsGroupMaster";
var ACTIVE_INACTIVE_ANTIBIOTIC_GROUP_MASTER ="/activateInactivateAntibioticsGroupMaster";
var LIST_ANTIBIOTIC_GROUP_MASTER = "/listAntibioticsGroupMaster";
var COUNT_ANTIBIOTIC_GROUP_MASTER  ="/getToTalAntibioticsGroupMaster";


/** Sample Type Media master**/

var ADD_SAMPLE_TYPE_MEDIA_MASTER  = "/addSampleTypeMedia";
var EDIT_SAMPLE_TYPE_MEDIA_MASTER = "/getSampleTypeMediaById";
var UPDATE_SAMPLE_TYPE_MEDIA_MASTER = "/updateSampleTypeMedia";
var ACTIVE_INACTIVE_SAMPLE_TYPE_MEDIA_MASTER ="/activeInactiveSampleTypeMedia";
var LIST_SAMPLE_TYPE_MEDIA_MASTER = "/listSampleTypeMedia";
var COUNT_SAMPLE_TYPE_MEDIA_MASTER  ="/getToTallistSampleTypeMedia";
var GET_SAMPLE_TYPE_LIST="/getSampleTypeList";
var GET_MEDIA_LIST="/MediaList";


/** Sensitivity Test Result master**/

var ADD_SENSITIVITY_TEST_RESULT_MASTER  = "/addSensitivityTestResult";
var EDIT_SENSITIVITY_TEST_RESULT_MASTER = "/getaddSensitivityTestResultyId";
var UPDATE_SENSITIVITY_TEST_RESULT_MASTER = "/updateSensitivityTestResult";
var ACTIVE_INACTIVE_SENSITIVITY_TEST_RESULT_MASTER ="/activeInactiveSensitivityTestResult";
var LIST_SENSITIVITY_TEST_RESULT_MASTER = "/listSensitivityTestResult";
var COUNT_SENSITIVITY_TEST_RESULT_MASTER  ="/getToTallistlistSensitivityTestResult";


/** Slide Procedure master**/

var ADD_SLIDE_PROCEDURE_MASTER  = "/addSlideProcedure";
var EDIT_SLIDE_PROCEDURE_MASTER = "/getslideProcedureById";
var UPDATE_SLIDE_PROCEDURE_MASTER = "/updateSlideProcedureMaster";
var ACTIVE_SLIDE_PROCEDURE_MASTER ="/activeInactiveSlideProcedure";
var LIST_SLIDE_PROCEDURE_MASTER = "/listSlideProcedure";
var COUNT_SLIDE_PROCEDURE_MASTER  ="/getToTallistlistSlideProcedure";


/** Block master**/

var ADD_BLOCK_MASTER  = "/addBlock";
var EDIT_BLOCK_MASTER = "/getaddBlockMasterById";
var UPDATE_BLOCK_MASTER = "/updateBlockMaster";
var ACTIVE_BLOCK_MASTER ="/activeInactiveBlock";
var LIST_BLOCK_MASTER = "/listBlock";
var COUNT_BLOCK_MASTER  ="/getToTallistBlock";

/** Incubation Period master**/

var ADD_INCUBATION_PERIOD_MASTER  = "/addIncubationPeriod";
var EDIT_INCUBATION_PERIOD_MASTER = "/getIncubationPeriodById";
var UPDATE_INCUBATION_PERIOD_MASTER = "/updateIncubationTestMaster";
var ACTIVE_INCUBATION_PERIOD_MASTER ="/activeInactiveIncubationPeriod";
var LIST_INCUBATION_PERIOD_MASTER = "/listIncubationPeriod";
var COUNT_INCUBATION_PERIOD_MASTER  ="/getToTalIncubationPeriod";

var GET_INCUBATION_LIST_OF_DAY = "/listOfDay";
var GET_INCUBATION_LIST_OF_HOUR = "/listOfHour";

/** Bacteria CLASSFICATION master**/
var ADD_BACTERIA_CLASSFICATION_MASTER  = "/addBacteriaClassfication";
var EDIT_BACTERIA_CLASSFICATION_MASTER = "/getaddBacteriaClassficationById";
var UPDATE_BACTERIA_CLASSFICATION_MASTER = "/updateBacteriaClassficationMaster";
var ACTIVE_INACTIVE_BACTERIA_CLASSFICATION_MASTER ="/activeInactiveBacteriaClassfication ";
var LIST_BACTERIA_CLASSFICATION_MASTER = "/listBacteriaClassfication";
var COUNT_BACTERIA_CLASSFICATION_MASTER  ="/getToTallistBacteriaClassfication";


/** Antibiotic-addtion header mapper master**/
var GET_ANTIBIOTIC_ADDTION_ANTIBIOTIC_LIST = "/listOfAntibiotic";

var GET_ANTIBIOTICS_LIST_BY_ANTIBIOTIC_CLASS_ID = "/getAntibioticByAntibioticClassId";

var ADD_ANTIBIOTIC_ADDTION_MASTER  = "/addAntibioticAddtionClass";
var UPDATE_ANTIBIOTIC_ADDTION_MASTER = "/updateAntibioticAddtion";
var GET_ANTIBIOTIC_ADDTION_ANTIBIOTIC_CLASS_LIST = "/listOfListAntibioticClass";
var GET_TOTAL_ANTIBIOTIC_ADDTION_ANTIBIOTIC_CLASS_LIST = "/listAntibioticAddtionClassMaster";
var GET_COUNT_ANTIBIOTIC_ADDTION_ANTIBIOTIC_CLASS_LIST = "/getToTalAntibioticAddtionClass";
var ACTIVE_INACTIVE_INCUBATION_ANTIBIOTIC_ADDTION_MASTER ="/activeInactiveAntibioticAddtion";
var EDIT_ANTIBIOTIC_ADDTION_MASTER = "/getAntibioticAddtionClassMasterById";



/** antibiotic group addition antibiotic mapper master**/

var GET_ANTIBIOTIC_GORUP_ADDTION_ANTIBIOTIC_LIST = "/listOfAntibiotic";

var GET_ANTIBIOTIC_LIST_BY_ANTIBIOTIC_GROUP_ID = "/getAntibioticByAddtionGroupId";
var ADD_ANTIBIOTIC_GROUP_ADDTION_MASTER  = "/addAntibioticGroupAddtion";
var UPDATE_ANTIBIOTIC_GROUP_ADDTION_MASTER = "/updateAntibioticGroupAddtion";
var GET_ANTIBIOTIC_ADDTION_ANTIBIOTIC_GROUP_LIST = "/antibioticGroupList";
var GET_TOTAL_ANTIBIOTIC_ADDTION_ANTIBIOTIC_GROUP_LIST = "/listAntibioticAntibioticGroupMaster";
var GET_COUNT_ANTIBIOTIC_ADDTION_ANTIBIOTIC_GROUP_LIST = "/getToTalAntibioticAntibioticGroupAddtion";
var ACTIVE_INACTIVE_ANTIBIOTIC_GROUP_ADDTION_MAPPER_MASTER ="/activeInactiveAntibioticGroupAddtion";
var EDIT_ANTIBIOTIC_GROUP_ADDTION_MASTER = "/getAntibioticAddtionGroupMasterById";



/** OrganismGorup-addtion Organism mapper master**/
var GET_ORGANIM_ADDTION_ORGANIM_LIST = "/getOrganismList";

var GET_ORGANIMS_LIST_BY_ORGANIM_CLASS_ID = "/getOrganismGroupById"; 

var ADD_ORGANIM_ADDTION_MASTER  = "/addOrganismGroupOrganismMapper";
var UPDATE_ORGANIM_ADDTION_MASTER = "/updateOrganismGroupOrganismMapperMaster";
var GET_ORGANIM_ADDTION_ORGANIM_CLASS_LIST = "/getOrganismGroupList";
var GET_TOTAL_ORGANIM_ADDTION_ORGANIM_CLASS_LIST = "/listOrganismGroupOrganismMapperMaster";
var GET_COUNT_ORGANIM_ADDTION_ORGANIM_CLASS_LIST = "/getToTalOrganismGroupOrganismMapper";
var ACTIVE_INACTIVE_INCUBATION_ORGANIM_ADDTION_MASTER ="/activeInactiveOrganismGroupOrganismMapperMaster";
var EDIT_ORGANIM_ADDTION_MASTER = "/getOrganismGroupOrganismMapperMasterById";



/** MediaColony-addtion header mapper master**/
var GET_MEDIA_ADDTION_COLONY_LIST = "/colonyList";

var GET_COLONY_LIST_BY_MEDIA_CLASS_ID = "/getMediaByMediaId";
var ADD_MEDIA_ADDTION_MASTER  = "/addMediaColonyMapper";
var UPDATE_MEDIA_ADDTION_MASTER = "/updateMediaColonyMapperMaster";
var GET_COLONY_ADDTION_MEDIA_CLASS_LIST = "/MediaList";
var GET_TOTAL_COLONY_ADDTION_MEDIA_CLASS_LIST = "/listMediaColonyMapperMaster";
var GET_COUNT_COLONY_ADDTION_MEDIA_CLASS_LIST = "/getToTalMediaColonyMapper";
var ACTIVE_INACTIVE_INCUBATION_MEDIA_ADDTION_MASTER ="/activeInactiveMediaColonyMapperMaster";
var EDIT_MEDIA_ADDTION_MASTER = "/getMediaColonyMapperMasterById";
var CHECK_TRANSACTION_EXISTS_MASTER  = "/checkTranSactionExists";

/** mapping of Micro organism master**/
var GET_ORGANISM_LIST = "/getOrganismList";
var GET_ANTIBIOTIC_GROUP_LIST = "/antibioticGroupList";
var GET_ANTIBIOTIC_LIST_BY_ANTIBIOTIC_GROUP_ID = "/antibiotic/list";
var ADD_ANTIBIOTIC_ORGANISM_LIST_MAPPER_MASTER  = "/addAntibioticOrganismMpprMaster";
var GET_TOTAL_ANTIBIOTIC_ORGANISM_LIST_MAPPER_MASTER = "/listAntibioticOrganismMpprMaster";
var ACTIVE_INACTIVE_ANTIBIOTIC_ORGANISM_LIST_MAPPER_MASTER ="/activateInactivateAntibioticOrganismMpprMaster";
var GET_COUNT_ANTIBIOTIC_ORGANISM_LIST_MAPPER_MASTER_LIST = "/getToTalAntibioticOrganismMpprMaster";
var GET_ANTIBIOTICS_LIST_BY_ORGANISM_ID = "/getAntibioticByOrganismId";

/** SampleTypeMedia-addtion header mapper master**/
var GET_SAMPLE_TYPE_ADDTION_MEDIA_LIST = "/MediaList"; 

var GET_SAMPLE_TYPE_LIST_BY_MEDIA_CLASS_ID = "/getMediaMasterClassById";
var ADD_SAMPLE_TYPE_ADDTION_MASTER  = "/addSampleTypeMedia";
var UPDATE_SAMPLE_TYPE_ADDTION_MASTER = "/updateSampleTypeMedia";
var GET_SAMPLE_TYPE_ADDTION_MEDIA_CLASS_LIST = "/colonyList";
var GET_TOTAL_SAMPLE_TYPE_ADDTION_MEDIA_CLASS_LIST = "/listSampleTypeMedia";
var GET_COUNT_SAMPLE_TYPE_ADDTION_MEDIA_CLASS_LIST = "/getToTallistSampleTypeMedia"; 
var ACTIVE_INACTIVE_SAMPLE_TYPE_MEDIA_ADDTION_MASTER ="/activeInactiveSampleTypeMedia";
var EDIT_SAMPLE_TYPE_ADDTION_MASTER = "/getSampleTypeAddtionClassMasterById";



/**=========================>UNIT MASTER<===============================*/

/**Department Master*/
var ADD_DEPARTMENT_MASTER  = "/addDepartment";
var EDIT_DEPARTMENT_MASTER = "/getDepartmentbyId";
var UPDATE_DEPARTMENT_MASTER = "/updateDepartment";
var ACTIVE_INACTIVE_DEPARTMENT_MASTER ="/activeInactiveDepartment";
var LIST_DEPARTMENT_MASTER = "/listDepartmentMaster";
var COUNT_DEPARTMENT_MASTER  ="/getTotalDepartmentMasterRecord";

/**Phlebotomy Master*/
var ADD_PHLEBOTOMY_MASTER  = "/addPhlebotomyMaster";
var EDIT_PHLEBOTOMY_MASTER = "/getPhlebotomyById";
var UPDATE_PHLEBOTOMY_MASTER = "/updatePhlebotomy";
var ACTIVE_INACTIVE_PHLEBOTOMY_MASTER ="/activeInactivePhlebotomy";
var LIST_PHLEBOTOMY_MASTER = "/listPhlebotomyMaster";
var COUNT_PHLEBOTOMY_MASTER  ="/getTotalPhlebotomyMasterRecord";


/**Storage Location Master*/
var ADD_STORAGE_LOCATION_MASTER  = "/addStorageLocation";
var EDIT_STORAGE_LOCATION_MASTER = "/getStorageLocationbyId";
var UPDATE_STORAGE_LOCATION_MASTER = "/updateStorageLocation";
var ACTIVE_INACTIVE_STORAGE_LOCATION_MASTER ="/activeInactiveStorageLocation";
var LIST_STORAGE_LOCATION_MASTER = "/listStorageLocationMaster";
var COUNT_STORAGE_LOCATION_MASTER  ="/getTotalStorageLocationMasterRecord";

/**Header Master*/
var ADD_HEADER_MASTER  = "/addHeader";
var EDIT_HEADER_MASTER = "/getHeaderbyId";
var UPDATE_HEADER_MASTER = "/updateHeader";
var ACTIVE_INACTIVE_HEADER_MASTER ="/activeInactiveHeader";
var LIST_HEADER_MASTER = "/listHeaderMaster";
var COUNT_HEADER_MASTER  ="/getTotalHeaderMasterRecord";

/**Parameter Master*/
var ADD_PARAMETER_MASTER  = "/addParameter";
var ACTIVE_INACTIVE_PARAMETER ="/activeInactiveParameter ";
var LIST_PARAMETER_MASTER = "/listParameterMaster";
var GET_TOTAL_PARAMETER_RECORD  ="/getTotalParameterMasterRecord";

var REFRANGES_LIST  ="/refRanges";
var GET_HELP_VALUES = "/helpValues";

/**Single Parameter Test Master*/
var ADD_SINGLE_PARAMETER_TEST  = "/addSingleParameterTest";
var GET_SINGLE_PARAMETER_TEST ="/getSingleParameterTest";
var UPDATE_SINGLEPARAMETER_TEST = "/updateSingleParameterTest";
var ACTIVE_INACTIVE_TEST  ="/activeInactiveTest";
var LIST_TEST_MASTER  ="/listTestMaster";
var GET_TOTAL_TEST_RECORD  ="/getTotalTestRecord";

/**Multy Parameter Test Master*/
var ADD_MULTIPARAMETER_TEST  = "/addMultiParameterTest";
var GET_MULTYPARAMETER_TEST ="/getMultyParameterTest ";
var UPDATE_MULTIPARAMETER_TEST = "/updateMultiParameterTest";
var ACTIVE_INACTIVEMULTIPARAM_TEST  ="/activeInactiveMultiParamTest";
var LIST_MULTIPARAM_TEST_MASTER  ="/listMultiParamTestMaster";
var GET_TOTAL_MULTITEST_RECORD  ="/getTotalMultiTestRecord";

/**Parameter Master*/
var ADD_PARAMETER_MASTER  = "/addParameter";
var GET_PARAMETER_MASTER ="/getParameterById";
var UPDATE_PARAMETER_MASTER = "/updateParameter";
var ACTIVE_INACTIVE_PARAMETER_MASTER  ="/activeInactiveParameter";
var LIST_PARAMETER_MASTER  ="/listParameterMaster";
var GET_TOTAL_PARAMETER_RECORD  ="/getTotalParameterMasterRecord";


/**LAB TEMPLATE Master*/
var ADD_LAB_TEMPLATE_MASTER  = "/labtemplates";
var EDIT_LAB_TEMPLATE_MASTER = "/labtemplates";
var UPDATE_LAB_TEMPLATE_MASTER = "/labtemplates";
var ACTIVE_INACTIVE_LAB_TEMPLATE_MASTER ="/activateInactivateLabTemplateMaster";
var LIST_LAB_TEMPLATE_MASTER = "/labtemplates";
var COUNT_LAB_TEMPLATE_MASTER  ="/getToTalLabTemplateMasterRecord";
var LIST_LAB_TEMPLATE_TYPES = "/getTemplateTypes";
var GET_TEMPLATE = "/labTemplatesbydoctor";


/** Storage Location master for slide blocks**/
var ADD_STORAGE_SLIDE_MASTER  = "/addRack";
var EDIT_STORAGE_SLIDE_MASTER = "/getRackbyId";
var UPDATE_STORAGE_SLIDE_MASTER = "/updateRackMaster";
var ACTIVE_INACTIVE_STORAGE_SLIDE_MASTER ="/activeInactiveRack";
var LIST_STORAGE_SLIDE_MASTER = "/listRackMaster";
var COUNT_STORAGE_SLIDE_MASTER  ="/getTotalRackMasterRecord";



/**
 * profile panel Test Master
 */

var GET_SERVICE_PANEL_TEST  = "/getUnitServiceMapperList";
var LIST_PRIOFILE_PANEL_MASTER = "/PanelList";
var COUNT_PRIOFILE_PANEL_MASTER  ="/getTotalRackMasterRecord";
var ACTIVE_INACTIVE_PRIOFILE_PANEL_MASTER ="/PanelStatus";
var ADD_PANEL_MASTER ="/Panel";
var EDIT_SERVICE_PANEL_MASTER ="/Panel";
var UPDATE_SERVICE_PANEL_MASTER = "/Panel";


var FORMULA_API  = "/formula";
var FORMULA_STATUS_API  = "/formula/status";

var GET_PARAMETER_FOR_FORMULA = "/parameter";
var FORMULA_DETAILS  ="/formulaDetails";

/** LIS TRASACTION*/
/**PHLEBOTOMY */
var GET_PHLEBOTOMY_WORKLIST = "/getPhlebotomyWorlist";
var GET_TOTAL_PHLOBOTOMYRECORD = "/getTotalPhlebotomyWorkRecord";
var GET_PHLEBOTOMY_WORKLIST_DETAILS  ="/getPhlebotomyWorlistDetails";	
var COLLECT_SAMPLE  ="/sampleCollection";	
var GET_COLLECTED_SAMPLE  ="/getCollectedSample";	
var GET_TOTAL_SENDTO_CR_RECORD = "/getTotalSendToCrRecord";
var SEND_TO_CR  ="/sendToCr";	




/** CENTRAL RECEIVING */
var SAMPLE_LIST_FOR_INOUT_PATIENT = "/getSampleListForInOutPatient";
var ACCPET_REJECT_SAMPLE_FOR_IN_OUT = "/acceptOrRejectSample"; 
var CENTRIFUGATION_WORKLIST = "/getCentrifugationWorklist"; 
var SEND_TO_DEPARMENT = "/Centrifugation/Department"; 


/** SAMPLE REJECTION */
var GET_REJECTED_SAMPLE_LIST = "/getRejectedSampleList";
var GET_TOTAL_RECORD_REJECTED_SAMPLE = "/getTotalRecordRejectedSample";

/** SAMPLE RECOLLECTION */
var SAMPLE_RECOLLECTION = "/sampleRecollect";
var GET_TOTAL_RECORD_INPATIENT = "/getTotalRecordINPatient";
var GET_TOTAL_RECORD_OUTPATIENT = "/getTotalRecordOutPatient";
var GET_TOTAL_CENTRIFUGATIONWORKLIST_RECORD = "/getTotalcentrifugationWorkListRecord";

/** BIOCHMISTRY */
var SAMPLE_ACCEPTANCE_PENDING = "/Aacceptance/Pending";
var SAMPLE_ACCEPTANCE_PENDING_COUNT = "/Aacceptance/Pending/Count";
var BIO_CHEM_SMP_ACCEPT_REJECT = "/Biochemistry/Pending/Sample";
var BIO_CHEM_WORKLIST = "/Worklist";
var BIO_CHEM_WORKLIST_COUNT  = "/Worklist/Count";
var BIO_CHEM_SEND_TO_REPORT_ENTRY  = "/Worklist/ReportEntry";
var GET_RESULT_ID_BY_TEST  = "/ResultId";
var PRIVOUSRESULTBYSAMPLE_DETAILS  = "/PrivousResultBySample/Details";
var LAB_PREVIOUS_RESULT_BY_LEVEL  = "/previouResults";

/** WORK LIST FOR REPORT ENTERY**/
var WORK_LIST_FOR_DEPT_RESULT_ENTERY = "/ResultEntry";
var WORK_LIST_FOR_DEPT_RESULT_ENTERY_COUNT = "/ResultEntry/Count";
var GET_WORK_LIST_DEPT_RESULT_ENTERY_DETAILS = "/ResultEntry/Details";
var WORK_RESULT_ENTERY_DETAILS = "/ResultEntry/EntryDetails";
var RETEST_WORK_RESULT_ENTERY_DETAILS = "/retest";
var RECOLLECT_WORK_RESULT_ENTERY_DETAILS = "/recollect";
var GET_WORK_RESULT_ENTERY_DETAILS = "/resultEntryData";

/** WORK LIST FOR VALIDATION ENTERY**/

var WORK_LIST_FOR_VALIDATION_ENTERY = "/ValidationWorkList";
var WORK_LIST_FOR_VALIDATION_ENTERY_COUNT ="/ValidationWorkList/Count";
var GET_WORK_LIST_VALIDATION_ENTERY_DETAILS = "/ValidationWorkList/Details";
var WORK_VALIDATION_ENTERY_DETAILS = "/ValidationWorkList/EntryDetails";

/** WORK LIST FOR REPORT RELEASE**/

var WORK_LIST_FOR_REPORT_RELEASE = "/ReportReleaseWorkList";
var WORK_LIST_FOR_REPORT_RELEASE_COUNT ="/ReportReleaseWorkList/Count";
var GET_WORK_LIST_REPORT_RELEASE_DETAILS = "/ReportReleaseWorkList/Details";
var WORK_REPORT_RELEASE_DETAILS = "/ReportReleaseWorkList/EntryDetails";

/** REPORT HANDOVER**/

var LIST_FOR_REPORT_HANDOVER_RELEASE = "/ReportHandOverList";
var LIST_FOR_REPORT_HANDOVER_RELEASE_COUNT ="/ReportHandOverList/Count";

/**PATIENT ARRIVAL */
var GET_PATIENT_ARRIVAL_COUNT = "/patientArrivalCount";
var GET_PATIENT_ARRIVAL_LIST ="/patientArrival";
var ADD_PATIENT_ARRIVAL_DETAILS ="/patientArrivalData";

/** BIOCHEM REPORTS **/
var PRINT_REPORTS = "/Print";


/** MICROBIOLOGY */
var MICROBIOLOGY_ACCEPTANCE_PENDING = "/Aacceptance/Pending";
var MICROBIOLOGY_ACCEPTANCE_PENDING_COUNT = "/Aacceptance/Pending/Count";
var MICROBIOLOGY_SMP_ACCEPT_REJECT = "/Microbiology/Pending/Sample";
var MICROBIOLOGY_WORKLIST = "/MicroWorklist";
var MICROBIOLOGY_WORKLIST_COUNT  = "/MicroWorklist/Count";
var MICROBIOLOGY_SEND_TO_REPORT_ENTRY  = "/Worklist/ReportEntry";
var MICROBIOLOGY_PARAMETER_HISTORY  = "/parameterHistory";


/**Incubation Media */
var ADD_INCUBATION_MEDIA  = "/incubation/media";
var GET_INCUBATION_MEDIA  = "/incubation/media";

/**Incubation Objservation */
var SAVE_INCUBATION_TRANSACTION="/start/incubation";
var UPDATE_INCUBATION_TRANSACTION="/restart/incubation";
var GET_INCUBATION_TRANSACTION="/incubation/details";

var SAVE_INCUBATION_OBSERVATION_LIST = "/incubation/observation";
var INCUBATION_OBSERVATION_LIST = "/incubation/observation/list";
var INCUBATION_OBSERVATION_LIST_COUNT ="/incubation/observation/Count";
var INCUBATION_OBSERVATION_DETAILS ="/incubation/observation/details";



/**Macropic Examination*/
var MACROSCOPIC_EXAMINATION_WORKLIST = "/macroscopic/examination/list";
var MACROSCOPIC_EXAMINATION_WORKLIST_COUNT = "/macroscopic/examination/list/count";
var SAVE_MACROSCOPIC_EXAMINATION ="/macroscopic/examination";
var UPDATE_MACROSCOPIC_EXAMINATION ="/macroscopic/examination";
var GET_MACROSCOPIC_EXAMINATION ="/macroscopic/examination";


/**Biochemical Test Result Entry*/
var BIOCHEMICAL_RESULT_WORKLIST = "/biochemical/result/list";
var BIOCHEMICAL_RESULT_WORKLIST_COUNT = "/biochemical/result/list/count";
var SAVE_BIOCHEMICAL_RESULT ="/biochemical/resultEntry";
var UPDATE_BIOCHEMICAL_RESULT ="/biochemical/resultEntry";
var GET_BIOCHEMICAL_RESULT ="/biochemical/resultEntry/details";


/**Macropic Examination*/
var MICROSCOPIC_EXAMINATION_WORKLIST = "/microscopic/examination/list";
var MICROSCOPIC_EXAMINATION_WORKLIST_COUNT = "/microscopic/examination/list/count";
var SAVE_MICROSCOPIC_EXAMINATION ="/microscopic/examination";
var GET_MICROSCOPIC_EXAMINATION ="/microscopic/examination";

/*var GET_INCUBATION_DETAILS ="/incubation/details";
var GET_MACROSOCIPIC_DETAILS ="/macroscopic/details";
var GET_MICROSOCIPIC_DETAILS ="/microscopic/details";
*/
/*
 *  SENSITIVITY TESTING
 */
var SENSITIVITY_TESTING_LIST = "/SensitivityTesting/observation/list";
var SENSITIVITY_TESTING_LIST_COUNT ="/SensitivityTesting/observation/Count";
var SENSITIVITY_TESTING_DETAILS ="/SensitivityTesting/observation/details";


var SAVE_SENSITIVTY_DETAILS  ="/sensitivityTesting/observation";
var GET_SENSITIVITY_CHECK_TEST_DETAILS  ="/sensitivityTesting/getSensitivityDetails";



/**
 * HISTOPATHOLOGY
 */
var HISTOPATHOLOGY_SPECIMAN_RECEIPT_LIST = "/listSpecimanReceipt";
var HISTOPATHOLOGY_SPECIMAN_RECEIPT_COUNT = "/getTotalSpecimanReceiptRecord";
var HISTOPATHOLOGY_SPECIMEN_COLLECTION = "/specimenCollection";

var HISTO_MICRO_SCOPIC_WORKLIST = "/Specimans";
var HISTO_MICRO_SCOPIC_WORKLIST_COUNT ="/Specimans/count";

/**Speciman Search Code*/
var HISTO_MICRO_PATIENT_SEARCH = "/patient/search";
var HISTO_MICRO_VISIT_LIST = "/patient/visit/list";
var HISTO_MICRO_SPECIMAN_LIST = "/specimen/list";
var HISTO_MICRO_TEST_LIST = "/test/list";
var HISTO_MICRO_SEARCH_MICRO_ORGANISM_LIST = "/specimen/search";
var HISTO_MICRO_CREATE_GROSS ="/mascroscopic/examination";


/**Block Creation*/
var HISTO_BLOCK_CREATION = "/block/creation";
var HISTO_BLOCK_CREATION_COUNT = "/block/creation/count";
var HISTO_GET_GROSS_DETAILS = "/gross";
var HISTO_CREATE_BLICKS = "/blocks";

/**Slide Creation*/
var HISTO_SLIDE_CREATION = "/slide/creation";
var HISTO_SLIDE_CREATION_COUNT = "/slide/creation/count";
var HISTO_GET_SLIDE_DETAILS = "/slide";
var HISTO_CREATE_SLIDE = "/slides";

/**Microscopic Examiniation Work List Creation*/
var HISTO_MICRO_EXAM_WORK_LIST_CREATION = "/microscopic/examination";
var HISTO_MICRO_EXAM_WORK_LIST_CREATION_COUNT = "/microscopic/examination/count";
var HISTO_GET_EXAMINIATION_DETAILS = "/miscroscopic/slides"; 
var HISTO_MICRO_EXAM_WORK_LIST_CREATE_SLIDE = "/miscroscopic/slide/examination";
var GET_STAINING_LIST = "/staining/list/orgId/";
var HISTO_RESULT_ENTRY = "/saveTemplateResult";
var HISTO_GET_RESULT_ENTRY = "/getReport";

var HISTO_GET_MACROSCOPIC_EXAM_DETIALS = "/getMicroscopicExamDataBySubSpecimenId";



/**Restain Request URI*/
var HISTO_RESTAIN_REQUEST_DETAILS = "/restainRequest/details";
var HISTO_RESTAIN_REQUEST = "/restainRequest";
var HISTO_RESTAIN_REQUEST_WORKLIST = "/restainRequest/worklist";
var HISTO_RESTAIN_REQUEST_WORKLIST_COUNT = "/restainRequest/worklist/count";
var HISTO_RESTAIN_REQUEST_WORKLIST_DETAILS = "/restainRequest/worklist/details";
var HISTO_RESTAIN_REQUEST_WORKLIST_SLIDES = "/restainRequest/worklist/slides";

var GET_MAX_SLIDE_NO = "/maxslide";

/** OUT SOURCE LAB**/

var LIST_OUT_SOURCE_LAB = "/OutSourceList";
var LIST_OUT_SOURCE_LAB_COUNT ="/OutSource/count";
var ADD_OUT_SOURCE_MST_LAB ="/addOutSourceMasterDetails";

/** Pending list For Sample Dispatch OUT SOURCE LAB**/

var LIST_PENDING_LIST_FOR_SAMPLE_DISPATCH = "/pendingListForSampleDispatch";
var LIST_PENDING_LIST_FOR_SAMPLE_DISPATCH_COUNT ="/PendingListForSampleDispatch/count";
var ADD_PENDING_LIST_FOR_SAMPLE_DISPATCH_LAB ="/addPendingListOutSourceDetails";

var GET_OUT_SOURCE_DETAILS_BY_OUT_SOURCE_ID ="/getOutSourceDetailsByOutSourceId";
var GET_OUT_SOURCE_LAB_LIST = "/getAccountPayableList";

/**
 * STORAGE
 */
var SLIDE_STORAGE_LIST = "/slide/storage/list";
var SLIDE_STORAGE_LIST_COUNT = "/slide/storage/list/count";
var ACCEPT_SLIDE_STORAGE= "/slide/storage";
var GET_RACK_LIST= "/getRackList";
var GET_SHELF_LIST_BY_RACK_ID= "/getRackListByShelfId";
var GET_SLIDES_STORAGE_DTLS= "/slides/storage/details";
var STORAGED_SLIDE_LIST ="/stored/slide";
var STORAGED_SLIDE__LIST_COUNT ="/stored/slide/count";

/**
 * FROZEN SECTION REQUEST
 */
var FROZEN_SECTION_REQUEST_LIST = "/frozenSectionRequest/list";
var FROZEN_SECTION_REQUEST_LIST_COUNT = "/frozenSectionRequest/count";
var ACCEPT_FROZEN_SECTION_REQUEST= "/frozenSection/Accept";

/**
 * DASHBORD
 */
var LAB_DASHBORD_LIST = "/Dashboard";
var LAB_DASHBORD_LIST_COUNT = "/Dashboard/Count";
var LAB_DASHBORD_SEARCH= "/Dashboard/Search";
var GET_LAB_SAMPLE_DETAILS= "/sampleDetails";

var GET_SAMPLE_STATUS_DETAILS= "/getSampleStatus";
var SEARCH_DOCTOR_DETAILS= "/Dashboard/doctor/Search";
var SEARCH_SAMPLE_NO_DETAILS= "/Dashboard/sampleNo/Search";



var SEARCH_PHLEBOTOMY_WORK_LIST_PATIENT= "/PhlebotomyWorklist/patient/Search";
var PHLEBOTOMY_WORK_LIST_SEARCH= "/PhlebotomyWorklist/Search";

var SEARCH_SEND_TO_CR_LIST_PATIENT= "/SendToCR/patient/Search";
var SEND_TO_CR_SEARCH= "/SendToCRlist/Search";

var SEARCH_GENRAL_LAB_SAMPLE_NO_LIST= "/GenralLab/sampleNo/Search";

var OUT_PATIENT_LIST_SEARCH= "/outPatientList/Search";
var CENTRIFUGATION_WORK_LIST_SEARCH= "/CentrifugationWorkList/Search";
var BIOCHECMISTRY_PATIENT_LIST_SEARCH ="/bioChemistry/patient/Search";
var BIOCHECMISTRY_SAMPLE_NO_LIST_SEARCH="/bioChemistry/sampleNo/Search";

var BIOCHEMISTRY_LIST_SEARCH= "/bioChemistryList/Search";
var BIOCHEMISTRY_WORK_LIST_DEPT_SEARCH = "/WorklistForDepartmentList/Search";

var OUT_SOURCE_LIST_PATIENT_LIST_SEARCH ="/OutSourcelist/patient/Search";
var OUT_SOURCE_COMMON_LIST_PATIENT_LIST_SEARCH ="/OutSourceCommonList/Search";

/**===========================>LIS COMMON URL END<================================================**/