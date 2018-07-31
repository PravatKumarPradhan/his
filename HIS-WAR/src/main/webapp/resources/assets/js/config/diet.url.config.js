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
var KITCHEN ="/kitchen";
var PATIENT ="/patient";

//Nutrition Master 
var SAVENUTRITIONMASTER = API + GLOBAL + KITCHEN + "/saveNutritionalMaster"; 
var UPDATENUTRITIONMASTER = API + GLOBAL + KITCHEN + "/updateNutritionalMaster"; 
var GETNUTRITIONALMASTERBYID = API + GLOBAL + KITCHEN + "/getNutritionalMasterById"; 
var GETNUTRITIONALMASTERLIST = API + GLOBAL + KITCHEN + "/getNutritionalMasterList";
var UPDATENUTRITIONMASTERSTATUS = API + GLOBAL + KITCHEN + "/updateNutritionMasterStatus";

//Diet Master
var GETDIETTYPEMASTERLIST = API + GLOBAL + KITCHEN + "/getDietTypeMasterList";
var SAVEDIETTYPEMASTER = API + GLOBAL + KITCHEN + "/saveDietTypeMaster";
var GETDIETTYPEMASTERBYID = API + GLOBAL + KITCHEN + "/getDietTypeMasterById";
var UPDATEDIETTYPEMASTERSTATUS = API + GLOBAL + KITCHEN + "/updateDietTypeMasterStatus";
var UPDATEDIETTYPEMASTER = API + GLOBAL + KITCHEN + "/updateDietTypeMaster";

//Key Ingredients Master          
var GETALLKEYINGREDIENTSMASTER = API + GLOBAL + KITCHEN + "/getAllKeyIngredientsMaster";
var GETKEYINGREDIENTSDETAILSBYID = API + GLOBAL + KITCHEN + "/getKeyIngredientsDetailsById";
var GETUNITMASTERDETAILS = API + GLOBAL + KITCHEN + "/getUnitMasterDetails";
var GETNUTRITIONALMASTERLIST = API + GLOBAL + KITCHEN + "/getNutritionalMasterList";
var SAVEKEYINGREDIENTSMASTER = API + GLOBAL + KITCHEN + "/saveKeyIngredientsMaster";
var UPDATEKEYINGREDIENTSMASTERSTATUS = API + GLOBAL + KITCHEN + "/updateKeyIngredientsMasterStatus";
var GETKEYINGREDIENTSMASTERBYID = API + GLOBAL + KITCHEN + "/getKeyIngredientsMasterById";
var UPDATEKEYINGREDIENTSMASTER = API + GLOBAL + KITCHEN + "/updateKeyIngredientsMaster";

