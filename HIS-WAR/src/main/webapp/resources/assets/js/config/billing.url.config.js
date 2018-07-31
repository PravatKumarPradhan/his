/**
 * Billing API URL Configuration
 */
var API="/api";
var BILLING = "/billing";
var BILLING_GLOBAL = "/billing/global";
var BILLING_TRANSACTION = "/billing/transaction";
var GLOBAL ="/global";
var ORGANIZATION_MASTER = "/organizationMaster";
var SPECIALITY = "/speciality";
var SUB_SPECIALITY= "/subSpeciality";
var ORG = "/org";
var UNIT = "/unit";
var SEARCH = "/search";
var COUNT = "/count";
var STATUS="/status";

/*VARIABLE PRICING SEQUENCE*/
var VARIABLE_PRICING = "/variable-pricing";
var GET_VARIABLE_PRICING_SEQ_FOR_UNIT = "/getVariablePricingSequence";
var SAVE_VARIABLE_PRICING_SEQ_FOR_UNIT = "/saveVariablePricingSequence";
var variablePricingSequence = "/variablePricingSequence"
	
var SAVE_VARIABLE_PRICING = "/saveVariablePricing";
var GET_VARIABLE_PRICING_VALUES = "/getVariablePricingFactorByVisitId"

	
/*GLOBAL SERVICE MASTER*/
var SAVE_ORGANIZATION_SERVICE_MASTER ="/saveOrganizationServiceMaster";
var GET_SUBSPECIALITY_BY_SPECIALITY_ID="/adt/getSubSpecialityBySpecialityId";
var GET_ORGANIZATION_SERVICE_MASTER_LIST=BILLING+"/listOrganizationServiceMaster";
var GET_ORGANIZATION_SERVICE_MASTER_COUNT =BILLING+"/getOrganizationServiceMasterCount";
var GET_ORGANIZATION_SERVICE_MASTER_BY_ID ="/getOrganizationServiceMasterById";
var CHANGE_ORGANIZATION_SERVICE_MASTER_STATUS ="/changeOrganizationServiceMasterStatus";
var UPDATEGLOBALSERVICEMASTER =BILLING+"/updateGlobalServiceMaster";

/*BILLING GROUP MASTER*/
var CREATE_BILLING_GROUP_MASTER = "/createBillingGroupMaster";
var CHANGE_BILLING_GROUP_MASTER_STATUS="/changeBillingGroupMasterStatus";
var GET_BILLING_GROUP_MASTER_ID="/getBillingGroupMasterById";
var UPDATE_BILLING_GROUP_MASTER="/updateBillingGroupMaster";
var GET_BILLING_GROUP_MASTER_COUNT="/getBillingGroupMasterCount";
var GET_BILLING_GROUP_MASTER_LIST="/getBillingGroupMasterList";

/*UNIT SERVICE MAPPING*/
var UNIT_SERVICE_MAPPER="/UnitServiceMapper";
var SAVEDOCTORCONSULTATIONSERVICEMAPPER = "/doctorConsultationServiceMapper";
var GETDOCTORCONSULTATIONSERVICEMAPPERLIST = "/getDoctorConsultationServiceMapperList";
var GETDOCTORCONSULTATIONSERVICEMAPPERBYID = "/getDoctorConsultationServiceMapperById";
var UPDATEDOCTORCONSULTATIONSERVICEMAPPER="/updateDoctorConsultationServiceMapper";
var GETDOCTORCONSULTATIONSERVICEBYSPECIALITYANDDOCTORID="/getDoctorConsultationServiceBySpecialityAndDoctorId";

/*UNIT SERVICE MASTER DETAILS*/
var UNIT_SERVICE_MASTER_DETAILS ="/UnitServiceMasterDetails";

/*SERVIC MASTER LIST WITH PRICING DETAILS*/
var URI_TO_GET_SERVICE_MASTER_LIST_WITH_PRICE_BY_VISITTYPE="/listServiceMasterWithBasePriceByVisitType";

//for adding tariff master
var SAVETARIFFMASTER=API+UNIT+"/saveTariffMaster";
var GETTARIFFMASTERLIST=API+UNIT+"/getTariffMasterList";
var GETPAYMENTENTITLEMENTLISTBYTARIFFID=API+UNIT+"/getPaymentEntitlementListByTariffid";
var GETTARIFFLISTBYID=API+UNIT+"/getTariffListById";
var UPDATETARIFFMASTER=API+UNIT+"/updateTariffMaster";
var UPDATETARIFFMASTERSTATUS=API+UNIT+"/updateTariffMasterStatus";
var UPDATESTATUSFORTARIFFPAYMENTMAPPER=API+UNIT+"/updateStatusForTariffPaymentMapper";
var GETACTIVETARIFFMASTERLIST=API+UNIT+"/getActiveTariffMasterList";
var GETPAYMENTENTITLEMENTLISTBYTARIFFIDLIST=API+UNIT+"/getPaymentEntitlementListByTariffIdList";
var GETMASTERSERVICELISTBYMULTIPLEPARAMETERS=API+UNIT+"/getMasterServiceListByMultipleParameters";
var SAVEUNITSERVICETARIFFMASTER=API+UNIT+"/saveUnitServiceTariffMaster";
var SAVEBILL = API + BILLING +"/saveBill";


