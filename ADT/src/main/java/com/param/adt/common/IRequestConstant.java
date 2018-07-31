/**
 * 
 */
package com.param.adt.common;

/**
 * @author Jyoti
 *
 */
public interface IRequestConstant {
	
	/******* DOCTOR TYPE MASTER CONSTANT ******/
	String LIST_DOCTOR_TYPE = "/listDoctorType";
	String ADD_DOCTOR_TYPE = "/addDoctorType";
	String UPDATE_DOCTOR_TYPE = "/updateDoctorType";
	String EDIT_DOCTOR_TYPE = "/editDoctorType";
	String DELETE_DOCTOR_TYPE = "/deleteDoctorType";
	String GET_LIST_OF_DOCTOR = "/getlistOfDoctor";
	String GET_DOCTOR_DETAILS_BY_ID = "/getDoctorDetailsById";
	String UPDATE_DOCTOR = "/updateDoctor";
	
	/******* GROUP MASTER CONSTANT ******/
	String LIST_GROUP = "/listGroup";
	String ADD_GROUP = "/addGroup";
	String UPDATE_GROUP = "/updateGroup";
	String EDIT_GROUP = "/editGroup";
	String DELETE_GROUP = "/deleteGroup";
	
	/******* SUB GROUP MASTER CONSTANT ******/
	String LIST_SUB_GROUP = "/listSubGroup";
	String ADD_SUB_GROUP = "/addSubGroup";
	String UPDATE_SUB_GROUP = "/updateSubGroup";
	String EDIT_SUB_GROUP = "/editSubGroup";
	String DELETE_SUB_GROUP = "/deleteSubGroup";
	String GET_SUB_GROUP_BY_GROUP_ID = "/getSubGroupByGroupId";
	
	/******* DOCTOR CHARGE MASTER CONSTANT ******/
	String LIST_DOCTOR_CHARGE = "/listDoctorCharge";
	String ADD_DOCTOR_CHARGE = "/addDoctorCharge";
	String UPDATE_DOCTOR_CHARGE = "/updateDoctorCharge";
	String EDIT_DOCTOR_CHARGE = "/editDoctorCharge";
	
	
	/******* DOCTOR ROSTER MASTER CONSTANT ******/
	String LIST_DOCTOR_ROSTER = "/listDoctorRoster";
	String ADD_DOCTOR_ROSTER = "/addDoctorRoster";
	String UPDATE_DOCTOR_ROSTER = "/updateDoctorRoster";
	String EDIT_DOCTOR_ROSTER = "/editDoctorRoster";
	String GET_DOCTOR_BY_UNIT_AND_DEPARTMENT ="/getDoctorByUnitAndDepartmentId";
	
	
	/******* CLASSIFICATION MASTER CONSTANT ******/
	String LIST_CLASSIFICATION_MASTER = "/listClasificationMaster";
	String ADD_CLASSIFICATION_MASTER = "/addClasificationMaster";
	String UPDATE_CLASSIFICATION_MASTER = "/updateClasificationMaster";
	String EDIT_CLASSIFICATION_MASTER = "/editClasificationMaster";
	String DELETE_CLASSIFICATION_MASTER = "/deleteClasificationMaster";
	
	/******* DOCTOR SLOT CONSTANT ******/
	String GET_DOCTOR_SLOT_PAGE = "/getDoctorSlotPage";
	String DOCTOR_SCHEDULE = "/doctorSchedule";
	String ADD_DOCTOR_SCHEDULE = "/addDoctorSchedule";
	String GET_DOCTOR_SLOTS = "/getDoctorSlots";
	String DOCTOR_SLOTS = "/doctorSlots";
	
	
	/******* DEPARTMENT MASTER CONSTANT ******/
	String GET_DEPARTMENT_BY_DOCTOR_ID = "/getDepartmentByDoctorId";
	String DOCTOR_REGISTRATION="/doctorRegistration";
	
	/****** DOCTOR REGISTRATION *******/
	String REGISTER_DOCTOR = "/registerDoctor";
	
	/***************************************************************** PATIENT MODULE CONSTATNS ************************************************************************/
	
	/*******PATIENT CATEGORY MASTER CONSTANT ******/
	String GET_PATIENT_CATEGORY_LIST = "/getPatientCategoryList";
	String ADD_PATIENT_CATEGORY_MASTER = "/addPatientCategoryMaster";
	String EDIT_PATIENT_CATEGORY_MASTER = "/editPatientCategoryMaster";
	String UPDATE_PATIENT_CATEGORY_MASTER = "/updatePatientCategoryMaster";
	
	
/***************************************************************** GLOBAL MODULE CONSTATNS ************************************************************************/
	
	/*******BLOOD GROUP MASTER CONSTANT ******/
	String GET_BLOOD_GROUP_LIST = "/getBloodGroupList";
	String ADD_BLOOD_GROUP_MASTER = "/addBloodGroupMaster";
	String EDIT_BLOOD_GROUP_MASTER = "/editBloodGroupMaster";
	String UPDATE_BLOOD_GROUP_MASTER = "/updateBloodGroupMaster";
	
	/*******STATE MASTER CONSTANT ******/
	String GET_STATE_MASTER_LIST = "/getStateMasterList";
	String ADD_STATE_MASTER_MASTER = "/addStateMaster";
	String EDIT_STATE_MASTER_MASTER = "/editStateMaster";
	String UPDATE_STATE_MASTER_MASTER = "/updateStateMaster";
	
	/*******CUNTRY MASTER CONSTANT ******/
	String GET_COUNTRY_MASTER_LIST = "/getCountryMasterList";
	String ADD_COUNTRY_MASTER_MASTER = "/addCountryMaster";
	String EDIT_COUNTRY_MASTER_MASTER = "/editCountryMaster";
	String UPDATE_COUNTRY_MASTER_MASTER = "/updateCountryMaster";
	
	/*******CITY MASTER CONSTANT ******/
	String GET_CITY_MASTER_LIST = "/getCityMasterList";
	String ADD_CITY_MASTER = "/addCityMaster";
	String EDIT_CITY__MASTER = "/editCityMaster";
	String UPDATE_CITY__MASTER = "/updateCityMaster";
	
	/*******ADDRESS TYPE CONSTANT ******/
	String GET_ADDRESS_TYPE_LIST = "/getAddressMasterList";
	String ADD_ADDRESS_TYPE_MASTER = "/addAddressMaster";
	String EDIT_ADDRESS_TYPE__MASTER = "/editAddressMaster";
	String UPDATE_ADDRESS_TYPE__MASTER = "/updateAddressMaster";
	
	/*******NATIONALITY CONSTANT ******/
	String GET_NATIONALITY_MASTER_LIST = "/getNationalityMasterList";
	String ADD_NATIONALITY_MASTER = "/addNationalityMaster";
	String EDIT_NATIONALITY__MASTER = "/editNationalityMaster";
	String UPDATE_NATIONALITY__MASTER = "/updateNationalityMaster";
	
	/*******RACE CONSTANT ******/
	String GET_RACE_MASTER_LIST = "/getRaceMasterList";
	String ADD_RACE_MASTER = "/addRaceMaster";
	String EDIT_RACE__MASTER = "/editRaceMaster";
	String UPDATE_RACE__MASTER = "/updateRaceMaster";
	
	/*******GENDER CONSTANT ******/
	String GET_GENDER_MASTER_LIST = "/getGenderMasterList";
	String ADD_GENDER_MASTER = "/addGenderMaster";
	String EDIT_GENDER__MASTER = "/editGenderMaster";
	String UPDATE_GENDER__MASTER = "/updateGenderMaster";
	
	/*******PREFIX CONSTANT ******/
	String GET_PREFIX_MASTER_LIST = "/getPrefixMasterList";
	String ADD_PREFIX_MASTER = "/addPrefixMaster";
	String EDIT_PREFIX__MASTER = "/editPrefixMaster";
	String UPDATE_PREFIX__MASTER = "/updatePrefixMaster";
	
	/*******SLOT CONSTANT ******/
	String GET_SLOT_MASTER_LIST = "/getSlotMasterList";
	String ADD_SLOT_MASTER = "/addSlotMaster";
	String EDIT_SLOT__MASTER = "/editSlotMaster";
	String UPDATE_SLOT__MASTER = "/updateSlotMaster";
	
	/*******DEPARTMENT MASTER CONSTANT ******/
	String GET_DEPARTMENT_MASTER_LIST = "/getDepartmentMasterList";
	String ADD_DEPARTMENT_MASTER = "/addDepartmentMaster";
	String EDIT_DEPARTMENT__MASTER = "/editDepartmentMaster";
	String UPDATE_DEPARTMENT__MASTER = "/updateDepartmentMaster";
	
	/******* PATIENT TYPE MASTER CONSTANT ******/
	String GET_LIST_OF_PATIENT = "/getlistOfPatient";
	String GET_PATIENT_BY_ID_FOR_UPDATE = "/getPatientByIdForUpdate";
	
	/***************************************************************** SERVICE MODULE CONSTATNS ************************************************************************/
	
	/*******COMPANY MASTER CONSTANT ******/
	String GET_COMPANY_MASTER_LIST = "/getCompanyMasterList";
	String ADD_COMPANY_MASTER = "/addCompanyMaster";
	String EDIT_COMPANY__MASTER = "/editCompanyMaster";
	String UPDATE_COMPANY__MASTER = "/updateCompanyMaster";
	
	/*******ASSOCIATED COMPANY MASTER CONSTANT ******/
	String GET_ASSOCIATED_COMPANY_MASTER_LIST = "/getAssociatedCompanyMasterList";
	String ADD_ASSOCIATED_COMPANY_MASTER = "/addAssociatedCompanyMaster";
	String EDIT_ASSOCIATED_COMPANY__MASTER = "/editAssociatedCompanyMaster";
	String UPDATE_ASSOCIATED_COMPANY__MASTER = "/updateAssociatedCompanyMaster";
	
	/***************************************************************** REPORT MODULE CONSTATNS ************************************************************************/
	
	/*******REPORT DOCTOR COLLECTION CONSTANT ******/
	String GET_REPORT_DOCTOR_COLLECTION = "/getReportDoctorCollection";
	
	/*******REPORT SERVICE COLLECTION CONSTANT ******/
	String GET_REPORT_SERVICE_COLLECTION = "/getReportServiceCollection";
	
	/*******REPORT DOCTOR DETAILS CONSTANT ******/
	String GET_DOCTOR_DETAILS = "/getReportDoctorDetails";
	
	/*******REPORT SERVICE SUMMARY CONSTANT ******/
	String GET_SERVICE_SUMMARY = "/getReportServiceSummary";
	
	/*******REPORT SERVICE DETAILS CONSTANT ******/
	String GET_SERVICE_DETAILS = "/getReportServiceDetails";
	
}
