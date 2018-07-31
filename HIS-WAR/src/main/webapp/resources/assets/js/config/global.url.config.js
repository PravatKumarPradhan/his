/*******************************************************************************
 * Global URL Configuration /
 ******************************************************************************/
var STATUS = "/status";
var KIN_DETAILS = API + GLOBAL + "/kinDetails";
var SOURCE_MASTER = API + GLOBAL + "/sourceMaster";
var COMPANY_MASTER = API + GLOBAL + "/companyMaster";
var ASSOCIATED_COMPANY_MASTER = API + GLOBAL + "/associatedCompanyMaster";
var EMPLOYEEREGISTRATION = API + GLOBAL + "/employeeRegistration";
var GETEMPLOYEEDETAILS = API + GLOBAL + "/getEmployeeDetails";
var UPDATEEMPLOYEEDETAILS = API + GLOBAL + "/updateEmployeeDetails";
var UPDATEEMPLOYEESTATUS = API + GLOBAL + "/updateEmployeeStatus";
var DOCTORREGISTRATION = API + GLOBAL + "/doctorRegistration";
var UPDATEDOCTORDETAILS = API + GLOBAL + "/updateDoctorDetails";
var GETDOCTORDETAILS = API + GLOBAL + "/getDoctorDetails";
var UPDATEDOCTORSTATUS = API + GLOBAL + "/updateDoctorStatus";
var SEARCHDOCTORDETAILSBYCRITERIA = API + GLOBAL
		+ "/searchDoctorDetailsByCriteria";
var SEARCHEMPLOYEEBYCRITERIA = API + GLOBAL + "/searchEmployeeByCriteria";
var SEARCHDEPENDENTDETAILSBYCRITERIA = API + GLOBAL
		+ "/searchDependentDetailsByCriteria";

// Licence type Master
var GETLICENECETYPEMASTERLIST = "/org/getLiceneceTypeMasterList";
var SAVELICENECETYPEMASTER = "/org/saveLiceneceTypeMaster";
var GETLICENECETYPEMASTERBYID = "/org/getLiceneceTypeMasterById";
var UPDATELICENECETYPEMASTERSTATUS = "/org/updateLiceneceTypeMasterStatus";
var UPDATELICENECETYPEMASTER = "/org/updateLiceneceTypeMaster";
// generAL LEDGER
var GETGENERALLEDGERMASTERACTIVELIST = "/org/getGeneralLedgerMasterActiveList";
//Global->Billing
// doctor category
var GETDOCTORCATEGORYMASTERLIST = "/billing/getDoctorCategoryMasterList";
var GETDOCTORCATEGORYCOUNT = "/billing/getDoctorCategoryCount";
var SAVEDOCTORCATEGORYMASTER = "/billing/saveDoctorCategoryMaster";
var GETDOCTORCATEGORYMASTERBYID = "/billing/getDoctorCategoryMasterById";
var UPDATEDOCTORCATEGORYMASTERSTATUS = "/billing/updateDoctorCategoryMasterStatus";
var UPDATEDOCTORCATEGORYMASTER = "/billing/updateDoctorCategoryMaster";
// employee category
var GETEMPLOYEECATEGORYMASTERLIST = "/billing/getEmployeeCategoryMasterList";
var GETEMPLOYEECATEGORYCOUNT = "/billing/getEmployeeCategoryCount";
var SAVEEMPLOYEECATEGORYMASTER = "/billing/saveEmployeeCategoryMaster";
var GETEMPLOYEECATEGORYMASTERBYID = "/billing/getEmployeeCategoryMasterById";
var UPDATEEMPLOYEECATEGORYMASTERSTATUS = "/billing/updateEmployeeCategoryMasterStatus";
var UPDATEEMPLOYEECATEGORYMASTER = "/billing/updateEmployeeCategoryMaster";
// patient category
var GETPATIENTCATEGORYMASTERLIST = GLOBAL + "/getPatientCategoryMasterList";
var GETPATIENTCATEGORYMASTERCOUNT = GLOBAL + "/getPatientCategoryMasterCount";
var SAVEPATIENTCATEGORYMASTER = GLOBAL + "/savePatientCategoryMaster";
var GETPATIENTCATEGORYMASTERBYID = GLOBAL + "/getPatientCategoryMasterById";
var UPDATEPATIENTCATEGORYMASTERSTATUS = GLOBAL
		+ "/updatePatientCategoryMasterStatus";
var UPDATEPATIENTCATEGORYMASTER = GLOBAL + "/updatePatientCategoryMaster";
// currency master
var SAVECURRENCYMASTER = "/billing/saveCurrencyMaster";
var GETCURRENCYMASTERBYID = "/billing/getCurrencyMasterById";
var GETCURRENCYMASTERLIST = "/billing/getCurrencyMasterList";
var UPDATECURRENCYMASTER = "/billing/updateCurrencyMaster";
var UPDATECURRENCYMASTERSTATUS = "/billing/updateCurrencyMasterStatus";
var GETCURRENCYCOUNT = "/billing/getCurrencyCount";
// bank branch master
var SAVEBANKBRANCHMASTER = "/api/billing/saveBankBranchMaster";
var GETBANKBRANCHMASTERBYID = "/api/billing/getBankBranchMasterById";
var GETBANKBRANCHMASTERLIST = "/api/billing/getBankBranchMasterList";
var UPDATEBANKBRANCHMASTER = "/api/billing/updateBankBranchMaster";
var UPDATEBANKBRANCHMASTERSTATUS = "/api/billing/updateBankBranchMasterStatus";
var GETBANKBRANCHMASTERCOUNT = "/api/billing/getBankBranchMasterCount";
//currency Denomination master
var SAVECURRENCYDENOMINATIONMASTER = "/api/billing/saveCurrencyDenominationMaster";
var GETCURRENCYDENOMINATIONMASTERBYID = "/api/billing/getCurrencyDenominationMasterById";
var GETCURRENCYDENOMINATIONMASTERLIST = "/api/billing/getCurrencyDenominationMasterList";
var UPDATECURRENCYDENOMINATIONMASTER = "/api/billing/updateCurrencyDenominationMaster";
var UPDATECURRENCYDENOMINATIONMASTERSTATUS = "/api/billing/updateCurrencyDenominationMasterStatus";
var GETCURRENCYDENOMINATIONCOUNT = "/api/billing/getCurrencyDenominationCount";

//Global->Common
// Rejection master
var SAVEREJECTIONMASTER = "/api/common/saveRejectionMaster";
var GETREJECTIONMASTERBYID = "/api/common/getRejectionMasterById";
var GETREJECTIONMASTERLIST = "/api/common/getRejectionMasterList";
var UPDATEREJECTIONMASTER = "/api/common/updateRejectionMaster";
var UPDATEREJECTIONMASTERSTATUS = "/api/common/updateRejectionMasterStatus";
var GETREJECTIONMASTERCOUNT = "/api/common/getRejectionMasterCount";
//Reason Type master
var SAVEREASONTYPEMASTER = "/api/common/saveReasonTypeMaster";
var GETREASONTYPEMASTERBYID = "/api/common/getReasonTypeMasterById";
var GETREASONTYPEMASTERLIST = "/api/common/getReasonTypeMasterList";
var UPDATEREASONTYPEMASTER = "/api/common/updateReasonTypeMaster";
var UPDATEREASONTYPEMASTERSTATUS = "/api/common/updateReasonTypeMasterStatus";
var GETREASONTYPEMASTERCOUNT = "/api/common/getReasonTypeMasterCount";
//Qualification master
var SAVEQUALIFICATIONMASTER = "/api/common/saveQualificationMaster";
var GETQUALIFICATIONMASTERBYID = "/api/common/getQualificationMasterById";
var GETQUALIFICATIONMASTERLIST = "/api/common/getQualificationMasterList";
var UPDATEQUALIFICATIONMASTER = "/api/common/updateQualificationMaster";
var UPDATEQUALIFICATIONMASTERSTATUS = "/api/common/updateQualificationMasterStatus";
var GETQUALIFICATIONMASTERCOUNT = "/api/common/getQualificationMasterCount";
//Degree master
var SAVEDEGREEMASTER = "/api/common/saveDegreeMaster";
var GETDEGREEMASTERBYID = "/api/common/getDegreeMasterById";
var GETDEGREEMASTERLIST = "/api/common/getDegreeMasterList";
var UPDATEDEGREEMASTER = "/api/common/updateDegreeMaster";
var UPDATEDEGREEMASTERSTATUS = "/api/common/updateDegreeMasterStatus";
var GETDEGREEMASTERCOUNT = "/api/common/getQualificationMasterCount";
//Designation master
var SAVEDESIGNATIONMASTER = "/api/common/saveDesignationMaster";
var GETDESIGNATIONMASTERBYID = "/api/common/getDesignationMasterById";
var GETDESIGNATIONMASTERLIST = "/api/common/getDesignationMasterList";
var UPDATEDESIGNATIONMASTER = "/api/common/updateDesignationMaster";
var UPDATEDESIGNATIONMASTERSTATUS = "/api/common/updateDesignationMasterStatus";
var GETDESIGNATIONMASTERCOUNT = "/api/common/getDesignationMasterCount";
//Reason master
var SAVEREASONMASTER = "/api/common/saveReasonMaster";
var GETREASONMASTERBYID = "/api/common/getReasonMasterById";
var GETREASONMASTERLIST = "/api/common/getReasonMasterList";
var UPDATEREASONMASTER = "/api/common/updateReasonMaster";
var UPDATEREASONMASTERSTATUS = "/api/common/updateReasonMasterStatus";
var GETREASONMASTERCOUNT = "/api/common/getReasonMasterCount";
var GETALLACTIVETAX = "/api/global/getAllActiveTax";

